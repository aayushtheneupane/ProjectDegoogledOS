package com.google.i18n.phonenumbers;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

final class MetadataManager {
    static final MetadataLoader DEFAULT_METADATA_LOADER = new MetadataLoader() {
        public InputStream loadMetadata(String str) {
            return MetadataManager.class.getResourceAsStream(str);
        }
    };
    private static final Logger logger = Logger.getLogger(MetadataManager.class.getName());
    private static final ConcurrentHashMap<String, Phonemetadata$PhoneMetadata> shortNumberMetadataMap = new ConcurrentHashMap<>();
    private static final Set<String> shortNumberMetadataRegionCodes;

    static {
        new ConcurrentHashMap();
        HashSet hashSet = new HashSet(62);
        hashSet.add(7);
        hashSet.add(27);
        hashSet.add(30);
        hashSet.add(31);
        hashSet.add(34);
        hashSet.add(36);
        hashSet.add(39);
        hashSet.add(43);
        hashSet.add(44);
        hashSet.add(49);
        hashSet.add(52);
        hashSet.add(54);
        hashSet.add(55);
        hashSet.add(58);
        hashSet.add(61);
        hashSet.add(62);
        hashSet.add(63);
        hashSet.add(64);
        hashSet.add(66);
        hashSet.add(81);
        hashSet.add(84);
        hashSet.add(90);
        hashSet.add(91);
        hashSet.add(94);
        hashSet.add(95);
        hashSet.add(255);
        hashSet.add(350);
        hashSet.add(351);
        hashSet.add(352);
        hashSet.add(358);
        hashSet.add(359);
        hashSet.add(372);
        hashSet.add(373);
        hashSet.add(380);
        hashSet.add(381);
        hashSet.add(385);
        hashSet.add(505);
        hashSet.add(506);
        hashSet.add(595);
        hashSet.add(675);
        hashSet.add(676);
        hashSet.add(679);
        hashSet.add(855);
        hashSet.add(856);
        hashSet.add(971);
        hashSet.add(972);
        hashSet.add(995);
        HashSet hashSet2 = new HashSet(320);
        hashSet2.add("AC");
        hashSet2.add("AD");
        hashSet2.add("AE");
        GeneratedOutlineSupport.outline19(hashSet2, "AF", "AG", "AI", "AL");
        GeneratedOutlineSupport.outline19(hashSet2, "AM", "AO", "AR", "AS");
        GeneratedOutlineSupport.outline19(hashSet2, "AT", "AU", "AW", "AX");
        GeneratedOutlineSupport.outline19(hashSet2, "AZ", "BA", "BB", "BD");
        GeneratedOutlineSupport.outline19(hashSet2, "BE", "BF", "BG", "BH");
        GeneratedOutlineSupport.outline19(hashSet2, "BI", "BJ", "BL", "BM");
        GeneratedOutlineSupport.outline19(hashSet2, "BN", "BO", "BQ", "BR");
        GeneratedOutlineSupport.outline19(hashSet2, "BS", "BT", "BW", "BY");
        GeneratedOutlineSupport.outline19(hashSet2, "BZ", "CA", "CC", "CD");
        GeneratedOutlineSupport.outline19(hashSet2, "CF", "CG", "CH", "CI");
        GeneratedOutlineSupport.outline19(hashSet2, "CK", "CL", "CM", "CN");
        GeneratedOutlineSupport.outline19(hashSet2, "CO", "CR", "CU", "CV");
        GeneratedOutlineSupport.outline19(hashSet2, "CW", "CX", "CY", "CZ");
        GeneratedOutlineSupport.outline19(hashSet2, "DE", "DJ", "DK", "DM");
        GeneratedOutlineSupport.outline19(hashSet2, "DO", "DZ", "EC", "EE");
        GeneratedOutlineSupport.outline19(hashSet2, "EG", "EH", "ER", "ES");
        GeneratedOutlineSupport.outline19(hashSet2, "ET", "FI", "FJ", "FK");
        GeneratedOutlineSupport.outline19(hashSet2, "FM", "FO", "FR", "GA");
        GeneratedOutlineSupport.outline19(hashSet2, "GB", "GD", "GE", "GF");
        GeneratedOutlineSupport.outline19(hashSet2, "GG", "GH", "GI", "GL");
        GeneratedOutlineSupport.outline19(hashSet2, "GM", "GN", "GP", "GR");
        GeneratedOutlineSupport.outline19(hashSet2, "GT", "GU", "GW", "GY");
        GeneratedOutlineSupport.outline19(hashSet2, "HK", "HN", "HR", "HT");
        GeneratedOutlineSupport.outline19(hashSet2, "HU", "ID", "IE", "IL");
        GeneratedOutlineSupport.outline19(hashSet2, "IM", "IN", "IQ", "IR");
        GeneratedOutlineSupport.outline19(hashSet2, "IS", "IT", "JE", "JM");
        GeneratedOutlineSupport.outline19(hashSet2, "JO", "JP", "KE", "KG");
        GeneratedOutlineSupport.outline19(hashSet2, "KH", "KI", "KM", "KN");
        GeneratedOutlineSupport.outline19(hashSet2, "KP", "KR", "KW", "KY");
        GeneratedOutlineSupport.outline19(hashSet2, "KZ", "LA", "LB", "LC");
        GeneratedOutlineSupport.outline19(hashSet2, "LI", "LK", "LR", "LS");
        GeneratedOutlineSupport.outline19(hashSet2, "LT", "LU", "LV", "LY");
        GeneratedOutlineSupport.outline19(hashSet2, "MA", "MC", "MD", "ME");
        GeneratedOutlineSupport.outline19(hashSet2, "MF", "MG", "MH", "MK");
        GeneratedOutlineSupport.outline19(hashSet2, "ML", "MM", "MN", "MO");
        GeneratedOutlineSupport.outline19(hashSet2, "MP", "MQ", "MR", "MS");
        GeneratedOutlineSupport.outline19(hashSet2, "MT", "MU", "MV", "MW");
        GeneratedOutlineSupport.outline19(hashSet2, "MX", "MY", "MZ", "NA");
        GeneratedOutlineSupport.outline19(hashSet2, "NC", "NE", "NF", "NG");
        GeneratedOutlineSupport.outline19(hashSet2, "NI", "NL", "NO", "NP");
        GeneratedOutlineSupport.outline19(hashSet2, "NR", "NU", "NZ", "OM");
        GeneratedOutlineSupport.outline19(hashSet2, "PA", "PE", "PF", "PG");
        GeneratedOutlineSupport.outline19(hashSet2, "PH", "PK", "PL", "PM");
        GeneratedOutlineSupport.outline19(hashSet2, "PR", "PS", "PT", "PW");
        GeneratedOutlineSupport.outline19(hashSet2, "PY", "QA", "RE", "RO");
        GeneratedOutlineSupport.outline19(hashSet2, "RS", "RU", "RW", "SA");
        GeneratedOutlineSupport.outline19(hashSet2, "SB", "SC", "SD", "SE");
        GeneratedOutlineSupport.outline19(hashSet2, "SG", "SH", "SI", "SJ");
        GeneratedOutlineSupport.outline19(hashSet2, "SK", "SL", "SM", "SN");
        GeneratedOutlineSupport.outline19(hashSet2, "SO", "SR", "ST", "SV");
        GeneratedOutlineSupport.outline19(hashSet2, "SX", "SY", "SZ", "TC");
        GeneratedOutlineSupport.outline19(hashSet2, "TD", "TG", "TH", "TJ");
        GeneratedOutlineSupport.outline19(hashSet2, "TL", "TM", "TN", "TO");
        GeneratedOutlineSupport.outline19(hashSet2, "TR", "TT", "TV", "TW");
        GeneratedOutlineSupport.outline19(hashSet2, "TZ", "UA", "UG", "US");
        GeneratedOutlineSupport.outline19(hashSet2, "UY", "UZ", "VA", "VC");
        GeneratedOutlineSupport.outline19(hashSet2, "VE", "VG", "VI", "VN");
        GeneratedOutlineSupport.outline19(hashSet2, "VU", "WF", "WS", "XK");
        GeneratedOutlineSupport.outline19(hashSet2, "YE", "YT", "ZA", "ZM");
        hashSet2.add("ZW");
        shortNumberMetadataRegionCodes = hashSet2;
    }

    private MetadataManager() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x009f A[SYNTHETIC, Splitter:B:38:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a3 A[Catch:{ IOException -> 0x00a7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata getMetadataFromMultiFilePrefix(T r4, java.util.concurrent.ConcurrentHashMap<T, com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata> r5, java.lang.String r6, com.google.i18n.phonenumbers.MetadataLoader r7) {
        /*
            java.lang.Object r0 = r5.get(r4)
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r0 = (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r0
            if (r0 == 0) goto L_0x0009
            return r0
        L_0x0009:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r6)
            java.lang.String r6 = "_"
            r0.append(r6)
            r0.append(r4)
            java.lang.String r6 = r0.toString()
            com.google.i18n.phonenumbers.MetadataManager$1 r7 = (com.google.i18n.phonenumbers.MetadataManager.C09021) r7
            java.io.InputStream r7 = r7.loadMetadata(r6)
            if (r7 == 0) goto L_0x00b0
            java.lang.String r0 = "error closing input stream (ignored)"
            java.lang.String r1 = "cannot load/parse metadata"
            r2 = 0
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0096 }
            r3.<init>(r7)     // Catch:{ IOException -> 0x0096 }
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadataCollection r2 = new com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadataCollection     // Catch:{ all -> 0x0091 }
            r2.<init>()     // Catch:{ all -> 0x0091 }
            r2.readExternal(r3)     // Catch:{ IOException -> 0x008a }
            r3.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x0043
        L_0x003b:
            r7 = move-exception
            java.util.logging.Logger r1 = logger
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            r1.log(r3, r0, r7)
        L_0x0043:
            java.util.List r7 = r2.getMetadataList()
            int r0 = r7.size()
            if (r0 == 0) goto L_0x007e
            int r0 = r7.size()
            r1 = 1
            if (r0 <= r1) goto L_0x006c
            java.util.logging.Logger r0 = logger
            java.util.logging.Level r1 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "more than one metadata in file "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            r0.log(r1, r6)
        L_0x006c:
            r6 = 0
            java.lang.Object r6 = r7.get(r6)
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r6 = (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r6
            java.lang.Object r4 = r5.putIfAbsent(r4, r6)
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r4 = (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r4
            if (r4 == 0) goto L_0x007c
            goto L_0x007d
        L_0x007c:
            r4 = r6
        L_0x007d:
            return r4
        L_0x007e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "empty metadata: "
            java.lang.String r5 = com.android.tools.p006r8.GeneratedOutlineSupport.outline8(r5, r6)
            r4.<init>(r5)
            throw r4
        L_0x008a:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException     // Catch:{ all -> 0x0091 }
            r5.<init>(r1, r4)     // Catch:{ all -> 0x0091 }
            throw r5     // Catch:{ all -> 0x0091 }
        L_0x0091:
            r4 = move-exception
            r2 = r3
            goto L_0x009d
        L_0x0094:
            r4 = move-exception
            goto L_0x009d
        L_0x0096:
            r4 = move-exception
            java.lang.RuntimeException r5 = new java.lang.RuntimeException     // Catch:{ all -> 0x0094 }
            r5.<init>(r1, r4)     // Catch:{ all -> 0x0094 }
            throw r5     // Catch:{ all -> 0x0094 }
        L_0x009d:
            if (r2 == 0) goto L_0x00a3
            r2.close()     // Catch:{ IOException -> 0x00a7 }
            goto L_0x00af
        L_0x00a3:
            r7.close()     // Catch:{ IOException -> 0x00a7 }
            goto L_0x00af
        L_0x00a7:
            r5 = move-exception
            java.util.logging.Logger r6 = logger
            java.util.logging.Level r7 = java.util.logging.Level.WARNING
            r6.log(r7, r0, r5)
        L_0x00af:
            throw r4
        L_0x00b0:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "missing metadata: "
            java.lang.String r5 = com.android.tools.p006r8.GeneratedOutlineSupport.outline8(r5, r6)
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.MetadataManager.getMetadataFromMultiFilePrefix(java.lang.Object, java.util.concurrent.ConcurrentHashMap, java.lang.String, com.google.i18n.phonenumbers.MetadataLoader):com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata");
    }

    static Phonemetadata$PhoneMetadata getShortNumberMetadataForRegion(String str) {
        if (!shortNumberMetadataRegionCodes.contains(str)) {
            return null;
        }
        return getMetadataFromMultiFilePrefix(str, shortNumberMetadataMap, "/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto", DEFAULT_METADATA_LOADER);
    }
}
