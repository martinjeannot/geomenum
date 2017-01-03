/*
 * Copyright (c) 2014 Geomenum Inc. All Rights Reserved.
 */

package com.geomenum;

import com.google.common.collect.Maps;
import org.springframework.util.FileCopyUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * WHAT IS THE PURPOSE OF MY EXISTENCE ?
 */
@Test
public class TestToto {

    @Test
    void generateI18NFilesForMobile() throws IOException {
        final String path = "/home/phnx/dev/java/workspaces/prototypes/geomenum/geomenum/src/test/resources/toto/";
        File ENjs = new File(path + "en.js");
        //FileOutputStream fos = new FileOutputStream("/home/phnx/dev/java/workspaces/prototypes/geomenum/geomenum/src/test/resources/toto/en.js");
        for (String loc : Locale.getISOLanguages()) {
            if (!"en".equals(loc)) {
                File otherLang = new File(path + loc + ".js");
                FileCopyUtils.copy(ENjs, otherLang);
            }
        }
    }

    @Test
    void geoboot1() {
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println("geobootaccount" + (rand.nextInt(100000) + 100000) + "@yopmail.com");
        }
    }

    @Test
    void phonecode() {
        Map<String, String> toto = Maps.newHashMap();
        toto.put("AF", "93");
        toto.put("AX", "358");
        toto.put("AL", "355");
        toto.put("DZ", "213");
        toto.put("AS", "1684");
        toto.put("AD", "376");
        toto.put("AO", "244");
        toto.put("AI", "1264");
        toto.put("AQ", "");
        toto.put("AG", "1268");
        toto.put("AR", "54");
        toto.put("AM", "374");
        toto.put("AW", "297");
        toto.put("SH", "247");
        toto.put("AU", "61");
        toto.put("AT", "43");
        toto.put("AZ", "994");
        toto.put("BS", "1242");
        toto.put("BH", "973");
        toto.put("BD", "880");
        toto.put("BB", "1246");
        toto.put("BY", "375");
        toto.put("BE", "32");
        toto.put("BZ", "501");
        toto.put("BJ", "229");
        toto.put("BM", "1441");
        toto.put("BT", "975");
        toto.put("BO", "591");
        toto.put("BQ", "5997");
        toto.put("BA", "387");
        toto.put("BW", "267");
        toto.put("BV", "");
        toto.put("BR", "55");
        toto.put("IO", "246");
        toto.put("VG", "1284");
        toto.put("BN", "673");
        toto.put("BG", "359");
        toto.put("BF", "226");
        toto.put("BI", "257");
        toto.put("KH", "855");
        toto.put("CM", "237");
        toto.put("CA", "1");
        toto.put("CV", "238");
        toto.put("KY", "1345");
        toto.put("CF", "236");
        toto.put("TD", "235");
        toto.put("CL", "56");
        toto.put("CN", "86");
        toto.put("CX", "61");
        toto.put("CC", "61");
        toto.put("CO", "57");
        toto.put("KM", "269");
        toto.put("CG", "242");
        toto.put("CD", "243");
        toto.put("CK", "682");
        toto.put("CR", "506");
        toto.put("HR", "385");
        toto.put("CU", "53");
        toto.put("CW", "5999");
        toto.put("CY", "357");
        toto.put("CZ", "420");
        toto.put("DK", "45");
        toto.put("DJ", "253");
        toto.put("DM", "1767");
        toto.put("DO", "1809,1829,1849");
        toto.put("EC", "593");
        toto.put("EG", "20");
        toto.put("SV", "503");
        toto.put("GQ", "240");
        toto.put("ER", "291");
        toto.put("EE", "372");
        toto.put("ET", "251");
        toto.put("FK", "500");
        toto.put("FO", "298");
        toto.put("FJ", "679");
        toto.put("FI", "358");
        toto.put("FR", "33");
        toto.put("GF", "594");
        toto.put("PF", "689");
        toto.put("TF", "");
        toto.put("GA", "241");
        toto.put("GM", "220");
        toto.put("GE", "995");
        toto.put("DE", "49");
        toto.put("GH", "233");
        toto.put("GI", "350");
        toto.put("GR", "30");
        toto.put("GL", "299");
        toto.put("GD", "1473");
        toto.put("GP", "590");
        toto.put("GU", "1671");
        toto.put("GT", "502");
        toto.put("GG", "44");
        toto.put("GN", "224");
        toto.put("GW", "245");
        toto.put("GY", "592");
        toto.put("HT", "509");
        toto.put("HM", "");
        toto.put("VA", "3906698,379");
        toto.put("HN", "504");
        toto.put("HK", "852");
        toto.put("HU", "36");
        toto.put("IS", "354");
        toto.put("IN", "91");
        toto.put("ID", "62");
        toto.put("CI", "225");
        toto.put("IR", "98");
        toto.put("IQ", "964");
        toto.put("IE", "353");
        toto.put("IM", "44");
        toto.put("IL", "972");
        toto.put("IT", "39");
        toto.put("JM", "1876");
        toto.put("JP", "81");
        toto.put("JE", "44");
        toto.put("JO", "962");
        toto.put("KZ", "76,77");
        toto.put("KE", "254");
        toto.put("KI", "686");
        toto.put("KW", "965");
        toto.put("KG", "996");
        toto.put("LA", "856");
        toto.put("LV", "371");
        toto.put("LB", "961");
        toto.put("LS", "266");
        toto.put("LR", "231");
        toto.put("LY", "218");
        toto.put("LI", "423");
        toto.put("LT", "370");
        toto.put("LU", "352");
        toto.put("MO", "853");
        toto.put("MK", "389");
        toto.put("MG", "261");
        toto.put("MW", "265");
        toto.put("MY", "60");
        toto.put("MV", "960");
        toto.put("ML", "223");
        toto.put("MT", "356");
        toto.put("MH", "692");
        toto.put("MQ", "596");
        toto.put("MR", "222");
        toto.put("MU", "230");
        toto.put("YT", "262");
        toto.put("MX", "52");
        toto.put("FM", "691");
        toto.put("MD", "373");
        toto.put("MC", "377");
        toto.put("MN", "976");
        toto.put("ME", "382");
        toto.put("MS", "1664");
        toto.put("MA", "212");
        toto.put("MZ", "258");
        toto.put("MM", "95");
        toto.put("NA", "264");
        toto.put("NR", "674");
        toto.put("NP", "977");
        toto.put("NL", "31");
        toto.put("NC", "687");
        toto.put("NZ", "64");
        toto.put("NI", "505");
        toto.put("NE", "227");
        toto.put("NG", "234");
        toto.put("NU", "683");
        toto.put("NF", "672");
        toto.put("KP", "850");
        toto.put("MP", "1670");
        toto.put("NO", "47");
        toto.put("OM", "968");
        toto.put("PK", "92");
        toto.put("PW", "680");
        toto.put("PS", "970");
        toto.put("PA", "507");
        toto.put("PG", "675");
        toto.put("PY", "595");
        toto.put("PE", "51");
        toto.put("PH", "63");
        toto.put("PN", "64");
        toto.put("PL", "48");
        toto.put("PT", "351");
        toto.put("PR", "1787,1939");
        toto.put("QA", "974");
        toto.put("XK", "377,381,386");
        toto.put("RE", "262");
        toto.put("RO", "40");
        toto.put("RU", "7");
        toto.put("RW", "250");
        toto.put("BL", "590");
        toto.put("SH", "290");
        toto.put("KN", "1869");
        toto.put("LC", "1758");
        toto.put("MF", "590");
        toto.put("PM", "508");
        toto.put("VC", "1784");
        toto.put("WS", "685");
        toto.put("SM", "378");
        toto.put("ST", "239");
        toto.put("SA", "966");
        toto.put("SN", "221");
        toto.put("RS", "381");
        toto.put("SC", "248");
        toto.put("SL", "232");
        toto.put("SG", "65");
        toto.put("SX", "1721");
        toto.put("SK", "421");
        toto.put("SI", "386");
        toto.put("SB", "677");
        toto.put("SO", "252");
        toto.put("ZA", "27");
        toto.put("GS", "500");
        toto.put("KR", "82");
        toto.put("SS", "211");
        toto.put("ES", "34");
        toto.put("LK", "94");
        toto.put("SD", "249");
        toto.put("SR", "597");
        toto.put("SJ", "4779");
        toto.put("SZ", "268");
        toto.put("SE", "46");
        toto.put("CH", "41");
        toto.put("SY", "963");
        toto.put("TW", "886");
        toto.put("TJ", "992");
        toto.put("TZ", "255");
        toto.put("TH", "66");
        toto.put("TL", "670");
        toto.put("TG", "228");
        toto.put("TK", "690");
        toto.put("TO", "676");
        toto.put("TT", "1868");
        toto.put("TN", "216");
        toto.put("TR", "90");
        toto.put("TM", "993");
        toto.put("TC", "1649");
        toto.put("TV", "688");
        toto.put("UG", "256");
        toto.put("UA", "380");
        toto.put("AE", "971");
        toto.put("GB", "44");
        toto.put("US", "1");
        toto.put("UM", "");
        toto.put("VI", "1340");
        toto.put("UY", "598");
        toto.put("UZ", "998");
        toto.put("VU", "678");
        toto.put("VE", "58");
        toto.put("VN", "84");
        toto.put("WF", "681");
        toto.put("EH", "212");
        toto.put("YE", "967");
        toto.put("ZM", "260");
        toto.put("ZW", "263");

        for(String countryCode : Locale.getISOCountries()) {
            if(toto.containsKey(countryCode)) {
                System.out.println(".put(\"" + countryCode + "\", \"" + toto.get(countryCode) + "\")");
            } else {
                System.out.println(".put(\"" + countryCode + "\", \"\")");
            }
        }
    }
}
