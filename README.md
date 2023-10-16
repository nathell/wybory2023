# Wybory 2023

Kod do ściągania wyników cząstkowych z PKW i przeliczania d'Hondtem na bieżąco.

Licencja: [Opinionated Queer License](https://oql.avris.it/license?c=Daniel%20Janus). Kod w katalogu `js/` jest zmodyfikowaną wersją programu [protobuf-decoder](https://github.com/pawitp/protobuf-decoder) i udostępniany jest na licencji MIT (por. plik `LICENSE.MIT`).

## Jak uruchomić?

Będziesz potrzebować Clojure i Node.js.

```bash
mkdir -p archive/raw       # tylko raz
cd js; yarn install; cd .. # tylko raz
clojure -X wybory2023.run/run
```
