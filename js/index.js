import * as fs from 'fs';
import { decodeProto, TYPES } from "./protobufDecoder.js";
import {
  decodeFixed32,
  decodeFixed64,
  decodeStringOrBytes,
  decodeVarintParts
} from "./protobufPartDecoder.js";

function decode(buf) {
    var x = decodeProto(buf);
    for (const part of x.parts) {
        switch (part.type) {
        case TYPES.VARINT:
            part.value = decodeVarintParts(part.value);
            break;
        case TYPES.LENDELIM:
            // TODO: Support repeated field
            let decoded = decodeProto(part.value);
            if (part.value.length > 0 && decoded.leftOver.length === 0) {
                part.value = decode(part.value);
            } else {
                part.value = decodeStringOrBytes(part.value);
            }
            break;
        case TYPES.FIXED64:
            part.value = decodeFixed64(part.value);
            break;
        case TYPES.FIXED32:
            part.value = decodeFixed32(part.value);
            break;
        }
    }
    return x;
}

const infile = process.argv[2];
const outfile = infile.replace(/.blob$/, ".json");

var buf = fs.readFileSync(infile, null);
fs.writeFileSync(outfile, JSON.stringify(decode(buf)), null);
