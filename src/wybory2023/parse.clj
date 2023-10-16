(ns wybory2023.parse
  (:require [jsonista.core :as json]))

(defn parse-json [f]
  (json/read-value (slurp f) json/keyword-keys-object-mapper))

(defn value [{:keys [type value]}]
  (case type
    2 (or (:parts value) (:value value))
    0 (->> value (filter #(= (:type %) "Signed Int")) first :value Long/parseLong)))

(defn parse-committee
  [committee]
  (let [[a b c d] (->> committee value first value (map value))
        [id list name short-name] (if (number? b) [a b c d] [a nil b c])]
    {:id id, :list list, :name name, :short-name short-name}))

(defn parse-committees
  [data]
  (map parse-committee (:parts data)))

(defn index-by [f seq]
  (into {}
        (map (fn [[k v]] [k (first v)]))
        (group-by f seq)))

(comment
  (def komitety (parse-json "/tmp/x/protobuf-decoder/src/komitety.json"))
  (def committees (index-by :id (parse-committees komitety))))

(def committees
  {29686
 {:id 29686,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW BEZPARTYJNY ANTYSYSTEMOWY",
  :short-name "KWW BEZPARTYJNY ANTYSYSTEMOWY"},
 29674
 {:id 29674,
  :list nil,
  :name "KOMITET WYBORCZY PRAWICA",
  :short-name "KW PRAWICA"},
 29712
 {:id 29712,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW SAMORZĄDOWA INICJATYWA RAZEM",
  :short-name "KWW SAMORZĄDOWA INICJATYWA RAZEM"},
 29646
 {:id 29646,
  :list nil,
  :name "KOMITET WYBORCZY SAMOOBRONA RZECZPOSPOLITEJ POLSKIEJ",
  :short-name "KW SAMOOBRONA RZECZPOSPOLITEJ POLSKIEJ"},
 29653
 {:id 29653,
  :list nil,
  :name
  "KOMITET WYBORCZY WYBORCÓW KRZYSZTOF KWIATKOWSKI - PAKT SENACKI",
  :short-name "KWW KRZYSZTOF KWIATKOWSKI - PAKT SENACKI"},
 29680
 {:id 29680,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW JERZEGO MARKOWSKIEGO",
  :short-name "KWW JERZEGO MARKOWSKIEGO"},
 29633
 {:id 29633,
  :list 10,
  :name "KOMITET WYBORCZY NORMALNY KRAJ",
  :short-name "KW NORMALNY KRAJ"},
 29615
 {:id 29615,
  :list 5,
  :name "KOMITET WYBORCZY KONFEDERACJA WOLNOŚĆ I NIEPODLEGŁOŚĆ",
  :short-name "KW KONFEDERACJA WOLNOŚĆ I NIEPODLEGŁOŚĆ"},
 29676
 {:id 29676,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW BEATA MNICH",
  :short-name "KOMITET WYBORCZY WYBORCÓW BEATA MNICH"},
 29657
 {:id 29657,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW ROBERTA ROGUSKIEGO",
  :short-name "KWW ROBERTA ROGUSKIEGO"},
 29672
 {:id 29672,
  :list nil,
  :name "KOMITET WYBORCZY ALTERNATYWA SPOŁECZNA",
  :short-name "KOMITET WYBORCZY ALTERNATYWA SPOŁECZNA"},
 29675
 {:id 29675,
  :list nil,
  :name
  "KOMITET WYBORCZY WYBORCÓW ZYGMUNT FRANKIEWICZ - PAKT SENACKI",
  :short-name "KWW ZYGMUNT FRANKIEWICZ - PAKT SENACKI"},
 29662
 {:id 29662,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW DARIUSZA MĘCZYKOWSKIEGO",
  :short-name "KWW DARIUSZA MĘCZYKOWSKIEGO"},
 29690
 {:id 29690,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW BEZPARTYJNI SAMORZĄDOWCY GALICJI",
  :short-name "KWW BEZPARTYJNI SAMORZĄDOWCY GALICJI"},
 29648
 {:id 29648,
  :list nil,
  :name "KOMITET WYBORCZY POLSKIEJ PARTII PIRATÓW",
  :short-name "KW POLSKA PARTIA PIRATÓW"},
 29640
 {:id 29640,
  :list nil,
  :name
  "KOMITET WYBORCZY PIAST - JEDNOŚĆ MYŚLI EUROPEJSKICH NARODÓW I ŚWIATA",
  :short-name "KW PIAST-JMENIŚ"},
 29682
 {:id 29682,
  :list nil,
  :name "KOMITET WYBORCZY PARTII REPUBLIKAŃSKIEJ",
  :short-name "KOMITET WYBORCZY PARTII REPUBLIKAŃSKIEJ"},
 29630
 {:id 29630,
  :list nil,
  :name "KOMITET WYBORCZY ZJEDNOCZENI",
  :short-name "KW ZJEDNOCZENI"},
 29658
 {:id 29658,
  :list nil,
  :name "KOMITET WYBORCZY WOLNI I SOLIDARNI",
  :short-name "KW WIS"},
 29667
 {:id 29667,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW PROF. JOANNY SENYSZYN",
  :short-name "KWW PROF. JOANNY SENYSZYN"},
 29707
 {:id 29707,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW WIELKOPOLSKA INICJATYWA SENACKA",
  :short-name "KWW WIELKOPOLSKA INICJATYWA SENACKA"},
 29654
 {:id 29654,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW PAKT OBYWATELSKI LASECKI",
  :short-name "KWW PAKT OBYWATELSKI LASECKI"},
 29683
 {:id 29683,
  :list nil,
  :name "KOMITET WYBORCZY ŚLĄSKA PARTIA REGIONALNA",
  :short-name "KW ŚLĄSKA PARTIA REGIONALNA"},
 29637
 {:id 29637,
  :list 9,
  :name "KOMITET WYBORCZY WYBORCÓW RUCHU DOBROBYTU I POKOJU",
  :short-name "KWW RDIP"},
 29666
 {:id 29666,
  :list nil,
  :name "KOMITET WYBORCZY KONGRES NOWEJ PRAWICY",
  :short-name "KW NOWA PRAWICA"},
 29678
 {:id 29678,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW JÓZEFA ZAJĄCA",
  :short-name "KWW JÓZEFA ZAJĄCA"},
 29665
 {:id 29665,
  :list nil,
  :name "KOMITET WYBORCZY STRONNICTWA LUDOWEGO „OJCOWIZNA” RP",
  :short-name "KW OJCOWIZNA RP"},
 29687
 {:id 29687,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW TAK DLA SENATU RP JAN KURIATA",
  :short-name "KWW TAK DLA SENATU RP JAN KURIATA"},
 29696
 {:id 29696,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW NIEPODLEGŁA ŻYJE",
  :short-name "KWW NIEPODLEGŁA ŻYJE"},
 29635
 {:id 29635,
  :list nil,
  :name "KOMITET WYBORCZY WOLNA EUROPA",
  :short-name "KW WOLNA EUROPA"},
 29709
 {:id 29709,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW EUROPEJSKA LEWICA",
  :short-name "KWW EUROPEJSKA LEWICA"},
 29627
 {:id 29627,
  :list nil,
  :name "KOMITET WYBORCZY RUCH SPOŁECZNY AGROUNIA TAK",
  :short-name "KW RUCH SPOŁECZNY AGROUNIA TAK"},
 29668
 {:id 29668,
  :list nil,
  :name
  "KOMITET WYBORCZY WYBORCÓW PROFESORA KRZYSZTOFA GUTKOWSKIEGO",
  :short-name "KWW PROFESORA KRZYSZTOFA GUTKOWSKIEGO"},
 29645
 {:id 29645,
  :list nil,
  :name "KOMITET WYBORCZY ŚLONZOKI RAZEM",
  :short-name "KW ŚLONZOKI RAZEM"},
 29669
 {:id 29669,
  :list nil,
  :name "KOMITET WYBORCZY NOWA DEMOKRACJA - TAK",
  :short-name "KW NOWA DEMOKRACJA - TAK"},
 29692
 {:id 29692,
  :list nil,
  :name "KOMITET WYBORCZY CZWARTA RZECZYPOSPOLITA POLSKA",
  :short-name "KW IV RP"},
 29688
 {:id 29688,
  :list nil,
  :name
  "KOMITET WYBORCZY WYBORCÓW MIROSŁAWA PIASECKIEGO KANDYDATA NA SENATORA RZECZYPOSPOLITEJ POLSKIEJ",
  :short-name "KWW M. PIASECKIEGO KANDYDATA NA SENATORA RP"},
 29655
 {:id 29655,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW KRZYSZTOFA LECHOWSKIEGO",
  :short-name "KWW KRZYSZTOFA LECHOWSKIEGO"},
 29697
 {:id 29697,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW KAJETANA GORNIGA",
  :short-name "KOMITET WYBORCZY WYBORCÓW KAJETANA GORNIGA"},
 29673
 {:id 29673,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW NASZA LEWICA",
  :short-name "KWW NASZA LEWICA"},
 29679
 {:id 29679,
  :list nil,
  :name "KOMITET WYBORCZY WOLNOŚCIOWCY",
  :short-name "KOMITET WYBORCZY WOLNOŚCIOWCY"},
 29693
 {:id 29693,
  :list nil,
  :name "KOMITET WYBORCZY SPOŁECZNY INTERES",
  :short-name "KW SPOŁECZNY INTERES"},
 29659
 {:id 29659,
  :list nil,
  :name
  "KOMITET WYBORCZY WYBORCÓW NIEZALEŻNY KANDYDAT DAWID BOROWIAK",
  :short-name "KWW NIEZALEŻNY KANDYDAT DAWID BOROWIAK"},
 29624
 {:id 29624,
  :list 2,
  :name
  "KOALICYJNY KOMITET WYBORCZY TRZECIA DROGA POLSKA 2050 SZYMONA HOŁOWNI - POLSKIE STRONNICTWO LUDOWE",
  :short-name "KKW TRZECIA DROGA PSL-PL2050 SZYMONA HOŁOWNI"},
 29619
 {:id 29619,
  :list 7,
  :name "KOMITET WYBORCZY POLSKA JEST JEDNA",
  :short-name "KW POLSKA JEST JEDNA"},
 29700
 {:id 29700,
  :list nil,
  :name "KOMITET WYBORCZY PARTIA KONSERWATYWNA",
  :short-name "KW KONSERWATYŚCI"},
 29702
 {:id 29702,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW Z WIELKOPOLSKI DO SENATU",
  :short-name "KWW Z WIELKOPOLSKI DO SENATU"},
 29643
 {:id 29643,
  :list 11,
  :name "KOMITET WYBORCZY WYBORCÓW MNIEJSZOŚĆ NIEMIECKA",
  :short-name "KWW MNIEJSZOŚĆ NIEMIECKA"},
 29695
 {:id 29695,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW E-PARLAMENT-NOWA CYWILIZACJA",
  :short-name "KWW E-PARLAMENT-NOWA CYWILIZACJA"},
 29628
 {:id 29628,
  :list 11,
  :name "KOMITET WYBORCZY ANTYPARTIA",
  :short-name "KW ANTYPARTIA"},
 29639
 {:id 29639,
  :list 11,
  :name "KOMITET WYBORCZY RUCH NAPRAWY POLSKI",
  :short-name "KW RUCH NAPRAWY POLSKI"},
 29656
 {:id 29656,
  :list nil,
  :name
  "KOMITET WYBORCZY WYBORCÓW LIDIA STAROŃ - ZAWSZE PO STRONIE LUDZI",
  :short-name "KWW LIDIA STAROŃ"},
 29638
 {:id 29638,
  :list nil,
  :name "KOMITET WYBORCZY POLSKA 2050",
  :short-name "KW POLSKA 2050"},
 29699
 {:id 29699,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW MARIUSZ KAZIMIERZ WÓJTOWICZ",
  :short-name "KWW MARIUSZ KAZIMIERZ WÓJTOWICZ"},
 29701
 {:id 29701,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW KANDYDAT ZIEM GÓRSKICH",
  :short-name "KWW KANDYDAT ZIEM GÓRSKICH"},
 29623
 {:id 29623,
  :list nil,
  :name "KOMITET WYBORCZY PARTIA WOLNOŚCI",
  :short-name "KW PARTIA WOLNOŚCI"},
 29620
 {:id 29620,
  :list nil,
  :name "KOMITET WYBORCZY PATRIOCI POLSKA",
  :short-name "KW PATRIOCI POLSKA"},
 29650
 {:id 29650,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW PAKT SENAT DLA OBYWATELI",
  :short-name "KWW PAKT SENAT DLA OBYWATELI"},
 29647
 {:id 29647,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW ANDRZEJ DZIUBA - PAKT SENACKI",
  :short-name "KWW ANDRZEJ DZIUBA - PAKT SENACKI"},
 29684
 {:id 29684,
  :list nil,
  :name "KOMITET WYBORCZY RUCH JEDNOŚCI POLAKÓW",
  :short-name "KW RUCH JEDNOŚCI POLAKÓW"},
 29622
 {:id 29622,
  :list nil,
  :name "KOMITET WYBORCZY ZWIĄZKU SŁOWIAŃSKIEGO",
  :short-name "KW ZWIĄZKU SŁOWIAŃSKIEGO"},
 29705
 {:id 29705,
  :list nil,
  :name
  "KOMITET WYBORCZY WYBORCÓW KRZYSZTOFA WAWRZYŃCA BORKOWSKIEGO PAKT SENACKI",
  :short-name "KWW KW BORKOWSKIEGO PAKT SENACKI"},
 29664
 {:id 29664,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW JANA MARII JACKOWSKIEGO",
  :short-name "KWW JANA MARII JACKOWSKIEGO"},
 29694
 {:id 29694,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW UWIERZ W POLSKĘ",
  :short-name "KWW UWIERZ W POLSKĘ"},
 29651
 {:id 29651,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW MARCINA NOWAKA",
  :short-name "KWW MARCINA NOWAKA"},
 29661
 {:id 29661,
  :list nil,
  :name
  "KOMITET WYBORCZY WYBORCÓW MIROSŁAWA AUGUSTYNIAKA KANDYDATA NA SENATORA RZECZPOSPOLITEJ POLSKIEJ",
  :short-name "KWW M. AUGUSTYNIAK"},
 29716
 {:id 29716,
  :list nil,
  :name "KOMITET WYBORCZY RODACY KAMRACI",
  :short-name "KW KAMRACI"},
 29711
 {:id 29711,
  :list nil,
  :name "KOMITET WYBORCZY STRONNICTWO „PIAST”",
  :short-name "KOMITET WYBORCZY STRONNICTWO „PIAST”"},
 29621
 {:id 29621,
  :list 6,
  :name
  "KOALICYJNY KOMITET WYBORCZY KOALICJA OBYWATELSKA PO .N IPL ZIELONI",
  :short-name "KKW KOALICJA OBYWATELSKA PO .N IPL ZIELONI"},
 29671
 {:id 29671,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW POROZUMIENIE OBYWATELSKIE",
  :short-name "KWW POROZUMIENIE OBYWATELSKIE"},
 29632
 {:id 29632,
  :list nil,
  :name "KOMITET WYBORCZY ODPOWIEDZIALNOŚĆ",
  :short-name "KW ODPOWIEDZIALNOŚĆ"},
 29670
 {:id 29670,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW WADIM TYSZKIEWICZ - PAKT SENACKI",
  :short-name "KWW WADIM TYSZKIEWICZ - PAKT SENACKI"},
 29617
 {:id 29617,
  :list 3,
  :name "KOMITET WYBORCZY NOWA LEWICA",
  :short-name "KW NOWA LEWICA"},
 29691
 {:id 29691,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW POLSKA SOCJALNA",
  :short-name "KWW POLSKA SOCJALNA"},
 29703
 {:id 29703,
  :list nil,
  :name "KOMITET WYBORCZY RUCH NARODOWY",
  :short-name "KW RN"},
 29629
 {:id 29629,
  :list nil,
  :name "KOMITET WYBORCZY ZJEDNOCZENIE CHRZEŚCIJAŃSKICH RODZIN",
  :short-name "KW ZJEDNOCZENIE CHRZEŚCIJAŃSKICH RODZIN"},
 29625
 {:id 29625,
  :list 4,
  :name "KOMITET WYBORCZY PRAWO I SPRAWIEDLIWOŚĆ",
  :short-name "KW PRAWO I SPRAWIEDLIWOŚĆ"},
 29681
 {:id 29681,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW LIBERALNA DEMOKRACJA",
  :short-name "KWW LIBERALNA DEMOKRACJA"},
 29626
 {:id 29626,
  :list nil,
  :name "KOMITET WYBORCZY BEZPARTYJNI",
  :short-name "KW BEZPARTYJNI"},
 29618
 {:id 29618,
  :list 8,
  :name "KOMITET WYBORCZY POLSKA LIBERALNA STRAJK PRZEDSIĘBIORCÓW",
  :short-name "KW POLSKA LIBERALNA STRAJK PRZEDSIĘBIORCÓW"},
 29616
 {:id 29616,
  :list 1,
  :name "KOMITET WYBORCZY BEZPARTYJNI SAMORZĄDOWCY",
  :short-name "KW BEZPARTYJNI SAMORZĄDOWCY"},
 29685
 {:id 29685,
  :list nil,
  :name
  "KOMITET WYBORCZY WYBORCÓW LUCYNY KULIŃSKIEJ W SŁUŻBIE RZECZYPOSPOLITEJ",
  :short-name "KOMITET WYBORCZY WYBORCÓW LUCYNY KULIŃSKIEJ"},
 29689
 {:id 29689,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW ZAMOJSZCZYZNY",
  :short-name "KWW ZAMOJSZCZYZNY"},
 29677
 {:id 29677,
  :list nil,
  :name "KOMITET WYBORCZY SAMOOBRONA",
  :short-name "KW SAMOOBRONA"},
 29652
 {:id 29652,
  :list nil,
  :name "KOMITET WYBORCZY NOWA NADZIEJA",
  :short-name "KW NOWA NADZIEJA"},
 29706
 {:id 29706,
  :list nil,
  :name "KOMITET WYBORCZY WYBORCÓW WSPÓLNIE DLA CZĘSTOCHOWY",
  :short-name "KWW WSPÓLNIE DLA CZĘSTOCHOWY"}})

(defn parse-result [v]
  (when (vector? v)
    (let [[list votes] v]
      {:list (value list), :votes (value votes), :id (value (last v))})))

(defn parse-region [i v]
  {:region (inc i)
   :results (-> v value (nth 1) value (nth 7) value (nth 1) value
                (->> (drop 16))
                (->> (map (fn [result]
                            (parse-result (value result)))))
                (->> (filter #(some-> % :votes pos?))))})

(defn parse-results [data]
  (->> data
       :parts
       (drop 1)
       (take 41)
       (map-indexed parse-region)))
