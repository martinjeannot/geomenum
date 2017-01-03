/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * List all the countries our application can deal with.
 */
public final class Countries {

    private static final Map<String, Country> COUNTRIES;

    public static Map<String, Country> getCountries() {
        return COUNTRIES;
    }

    public static Collection<Country> getCountriesSortedByName(final Locale lang) {
        List<Country> countriesSortedByName = Lists.newArrayList(COUNTRIES.values());

        if(lang != null) {
            Collections.sort(countriesSortedByName, new Comparator<Country>() {
                @Override
                public int compare(Country o1, Country o2) {
                    return o1.getName(lang).compareTo(o2.getName(lang));
                }
            });
        }

        return countriesSortedByName;
    }

    //~ Country class ==================================================================================================

    public static final class Country {

        private final Locale locale;
        private final String internationalDialingCode;

        public Country(Locale locale, String internationalDialingCode) {
            this.locale = locale;
            this.internationalDialingCode = internationalDialingCode;
        }

        public String getName(Locale lang) {
            return lang == null ? locale.getDisplayCountry() : locale.getDisplayCountry(lang);
        }

        public String getIsoCode() {
            return locale.getCountry();
        }

        public String getInternationalDialingCode() {
            return internationalDialingCode;
        }
    }

    //~ International Dialing Codes ====================================================================================

    /**
     * This is a list of country calling codes or dial in code defined by ITU-T recommendations E.123 and E.164,
     * also called IDD (International Direct Dialling) or ISD (International Subscriber Dialling) codes.
     */
    private static final Map<String, String> internationalDialingCodes = ImmutableMap.<String, String>builder()
            .put("AD", "376")
            .put("AE", "971")
            .put("AF", "93")
            .put("AG", "1268")
            .put("AI", "1264")
            .put("AL", "355")
            .put("AM", "374")
            .put("AN", "")
            .put("AO", "244")
            .put("AQ", "672")
            .put("AR", "54")
            .put("AS", "1684")
            .put("AT", "43")
            .put("AU", "61")
            .put("AW", "297")
            .put("AX", "358")
            .put("AZ", "994")
            .put("BA", "387")
            .put("BB", "1246")
            .put("BD", "880")
            .put("BE", "32")
            .put("BF", "226")
            .put("BG", "359")
            .put("BH", "973")
            .put("BI", "257")
            .put("BJ", "229")
            .put("BL", "590")
            .put("BM", "1441")
            .put("BN", "673")
            .put("BO", "591")
            .put("BQ", "5997")
            .put("BR", "55")
            .put("BS", "1242")
            .put("BT", "975")
            .put("BV", "")
            .put("BW", "267")
            .put("BY", "375")
            .put("BZ", "501")
            .put("CA", "1")
            .put("CC", "61")
            .put("CD", "243")
            .put("CF", "236")
            .put("CG", "242")
            .put("CH", "41")
            .put("CI", "225")
            .put("CK", "682")
            .put("CL", "56")
            .put("CM", "237")
            .put("CN", "86")
            .put("CO", "57")
            .put("CR", "506")
            .put("CU", "53")
            .put("CV", "238")
            .put("CW", "5999")
            .put("CX", "61")
            .put("CY", "357")
            .put("CZ", "420")
            .put("DE", "49")
            .put("DJ", "253")
            .put("DK", "45")
            .put("DM", "1767")
            .put("DO", "1809/1829/1849")
            .put("DZ", "213")
            .put("EC", "593")
            .put("EE", "372")
            .put("EG", "20")
            .put("EH", "212")
            .put("ER", "291")
            .put("ES", "34")
            .put("ET", "251")
            .put("FI", "358")
            .put("FJ", "679")
            .put("FK", "500")
            .put("FM", "691")
            .put("FO", "298")
            .put("FR", "33")
            .put("GA", "241")
            .put("GB", "44")
            .put("GD", "1473")
            .put("GE", "995")
            .put("GF", "594")
            .put("GG", "44")
            .put("GH", "233")
            .put("GI", "350")
            .put("GL", "299")
            .put("GM", "220")
            .put("GN", "224")
            .put("GP", "590")
            .put("GQ", "240")
            .put("GR", "30")
            .put("GS", "500")
            .put("GT", "502")
            .put("GU", "1671")
            .put("GW", "245")
            .put("GY", "592")
            .put("HK", "852")
            .put("HM", "")
            .put("HN", "504")
            .put("HR", "385")
            .put("HT", "509")
            .put("HU", "36")
            .put("ID", "62")
            .put("IE", "353")
            .put("IL", "972")
            .put("IM", "44")
            .put("IN", "91")
            .put("IO", "246")
            .put("IQ", "964")
            .put("IR", "98")
            .put("IS", "354")
            .put("IT", "39")
            .put("JE", "44")
            .put("JM", "1876")
            .put("JO", "962")
            .put("JP", "81")
            .put("KE", "254")
            .put("KG", "996")
            .put("KH", "855")
            .put("KI", "686")
            .put("KM", "269")
            .put("KN", "1869")
            .put("KP", "850")
            .put("KR", "82")
            .put("KW", "965")
            .put("KY", "1345")
            .put("KZ", "76/77")
            .put("LA", "856")
            .put("LB", "961")
            .put("LC", "1758")
            .put("LI", "423")
            .put("LK", "94")
            .put("LR", "231")
            .put("LS", "266")
            .put("LT", "370")
            .put("LU", "352")
            .put("LV", "371")
            .put("LY", "218")
            .put("MA", "212")
            .put("MC", "377")
            .put("MD", "373")
            .put("ME", "382")
            .put("MF", "590")
            .put("MG", "261")
            .put("MH", "692")
            .put("MK", "389")
            .put("ML", "223")
            .put("MM", "95")
            .put("MN", "976")
            .put("MO", "853")
            .put("MP", "1670")
            .put("MQ", "596")
            .put("MR", "222")
            .put("MS", "1664")
            .put("MT", "356")
            .put("MU", "230")
            .put("MV", "960")
            .put("MW", "265")
            .put("MX", "52")
            .put("MY", "60")
            .put("MZ", "258")
            .put("NA", "264")
            .put("NC", "687")
            .put("NE", "227")
            .put("NF", "672")
            .put("NG", "234")
            .put("NI", "505")
            .put("NL", "31")
            .put("NO", "47")
            .put("NP", "977")
            .put("NR", "674")
            .put("NU", "683")
            .put("NZ", "64")
            .put("OM", "968")
            .put("PA", "507")
            .put("PE", "51")
            .put("PF", "689")
            .put("PG", "675")
            .put("PH", "63")
            .put("PK", "92")
            .put("PL", "48")
            .put("PM", "508")
            .put("PN", "64")
            .put("PR", "1787/1939")
            .put("PS", "970")
            .put("PT", "351")
            .put("PW", "680")
            .put("PY", "595")
            .put("QA", "974")
            .put("RE", "262")
            .put("RO", "40")
            .put("RS", "381")
            .put("RU", "7")
            .put("RW", "250")
            .put("SA", "966")
            .put("SB", "677")
            .put("SC", "248")
            .put("SD", "249")
            .put("SE", "46")
            .put("SG", "65")
            .put("SH", "290")
            .put("SI", "386")
            .put("SJ", "4779")
            .put("SK", "421")
            .put("SL", "232")
            .put("SM", "378")
            .put("SN", "221")
            .put("SO", "252")
            .put("SR", "597")
            .put("ST", "239")
            .put("SV", "503")
            .put("SX", "1721")
            .put("SY", "963")
            .put("SZ", "268")
            .put("TC", "1649")
            .put("TD", "235")
            .put("TF", "")
            .put("TG", "228")
            .put("TH", "66")
            .put("TJ", "992")
            .put("TK", "690")
            .put("TL", "670")
            .put("TM", "993")
            .put("TN", "216")
            .put("TO", "676")
            .put("TR", "90")
            .put("TT", "1868")
            .put("TV", "688")
            .put("TW", "886")
            .put("TZ", "255")
            .put("UA", "380")
            .put("UG", "256")
            .put("UM", "")
            .put("US", "1")
            .put("UY", "598")
            .put("UZ", "998")
            .put("VA", "379/3906698")
            .put("VC", "1784")
            .put("VE", "58")
            .put("VG", "1284")
            .put("VI", "1340")
            .put("VN", "84")
            .put("VU", "678")
            .put("WF", "681")
            .put("WS", "685")
            .put("YE", "967")
            .put("YT", "262")
            .put("ZA", "27")
            .put("ZM", "260")
            .put("ZW", "263")
            .build();

    //~ Countries Initialization =======================================================================================

    static {
        ImmutableMap.Builder<String, Country> countryMapBuilder = ImmutableMap.builder();

        for(String countryCode : Locale.getISOCountries()) {
            countryMapBuilder.put(countryCode, new Country(
                    new Locale("", countryCode),
                    internationalDialingCodes.get(countryCode)));
        }

        COUNTRIES = countryMapBuilder.build();
    }
}
