package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.internal.C1735a;
import com.google.i18n.phonenumbers.internal.C1737c;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.google.i18n.phonenumbers.f */
public class C1734f {

    /* renamed from: IO */
    private static final Map f2574IO;

    /* renamed from: JO */
    private static final Map f2575JO;

    /* renamed from: KO */
    private static final String f2576KO = (Arrays.toString(f2574IO.keySet().toArray()).replaceAll("[, \\[\\]]", "") + Arrays.toString(f2574IO.keySet().toArray()).toLowerCase().replaceAll("[, \\[\\]]", ""));

    /* renamed from: LO */
    private static final Pattern f2577LO = Pattern.compile("[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～]+");

    /* renamed from: OO */
    private static final Pattern f2578OO = Pattern.compile("(\\p{Nd})");
    static final Pattern PLUS_CHARS_PATTERN = Pattern.compile("[+＋]+");

    /* renamed from: PO */
    private static final Pattern f2579PO = Pattern.compile("[+＋\\p{Nd}]");

    /* renamed from: QO */
    private static final Pattern f2580QO = Pattern.compile("(?:.*?[A-Za-z]){3}.*");
    static final Pattern SECOND_NUMBER_START_PATTERN = Pattern.compile("[\\\\/] *x");

    /* renamed from: SO */
    private static final String f2581SO;
    static final Pattern UNWANTED_END_CHAR_PATTERN = Pattern.compile("[[\\P{N}&&\\P{L}]&&[^#]]+$");

    /* renamed from: UO */
    private static final String f2582UO = m4684nb(C0632a.m1025k(",;", "xｘ#＃~～"));

    /* renamed from: VO */
    private static final Pattern f2583VO;

    /* renamed from: WO */
    private static final Pattern f2584WO = Pattern.compile(f2581SO + "(?:" + f2582UO + ")?", 66);

    /* renamed from: XO */
    private static final Pattern f2585XO = Pattern.compile("(\\$\\d)");
    private static C1734f instance = null;
    private static final Logger logger = Logger.getLogger(C1734f.class.getName());

    /* renamed from: BO */
    private final C1733e f2586BO;

    /* renamed from: CO */
    private final Map f2587CO;

    /* renamed from: DO */
    private final C1735a f2588DO = C1735a.create();

    /* renamed from: EO */
    private final Set f2589EO = new HashSet(35);

    /* renamed from: FO */
    private final C1737c f2590FO = new C1737c(100);

    /* renamed from: GO */
    private final Set f2591GO = new HashSet(320);

    /* renamed from: HO */
    private final Set f2592HO = new HashSet();

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(52, "1");
        hashMap.put(54, "9");
        Collections.unmodifiableMap(hashMap);
        HashSet hashSet = new HashSet();
        hashSet.add(86);
        Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet();
        hashSet2.add(52);
        hashSet2.add(54);
        hashSet2.add(55);
        hashSet2.add(62);
        hashSet2.addAll(hashSet);
        Collections.unmodifiableSet(hashSet2);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(C0632a.m1013a('0', hashMap2, (Object) '0', '1'), '1');
        hashMap2.put('2', '2');
        hashMap2.put('3', '3');
        hashMap2.put('4', '4');
        hashMap2.put('5', '5');
        hashMap2.put('6', '6');
        hashMap2.put('7', '7');
        hashMap2.put('8', '8');
        hashMap2.put('9', '9');
        HashMap hashMap3 = new HashMap(40);
        hashMap3.put('A', '2');
        hashMap3.put('B', '2');
        hashMap3.put('C', '2');
        hashMap3.put('D', '3');
        hashMap3.put('E', '3');
        hashMap3.put('F', '3');
        hashMap3.put('G', '4');
        hashMap3.put('H', '4');
        hashMap3.put('I', '4');
        hashMap3.put('J', '5');
        hashMap3.put('K', '5');
        hashMap3.put('L', '5');
        hashMap3.put('M', '6');
        hashMap3.put('N', '6');
        hashMap3.put('O', '6');
        hashMap3.put('P', '7');
        hashMap3.put('Q', '7');
        hashMap3.put('R', '7');
        hashMap3.put('S', '7');
        hashMap3.put(C0632a.m1013a('8', hashMap3, (Object) C0632a.m1013a('8', hashMap3, (Object) 'T', 'U'), 'V'), '8');
        hashMap3.put('W', '9');
        hashMap3.put('X', '9');
        hashMap3.put('Y', '9');
        hashMap3.put('Z', '9');
        f2574IO = Collections.unmodifiableMap(hashMap3);
        HashMap hashMap4 = new HashMap(100);
        hashMap4.putAll(f2574IO);
        hashMap4.putAll(hashMap2);
        f2575JO = Collections.unmodifiableMap(hashMap4);
        HashMap hashMap5 = new HashMap();
        hashMap5.putAll(hashMap2);
        hashMap5.put(C0632a.m1013a('*', hashMap5, (Object) C0632a.m1013a('+', hashMap5, (Object) '+', '*'), '#'), '#');
        Collections.unmodifiableMap(hashMap5);
        HashMap hashMap6 = new HashMap();
        for (Character charValue : f2574IO.keySet()) {
            char charValue2 = charValue.charValue();
            hashMap6.put(C0632a.m1013a(charValue2, hashMap6, (Object) Character.valueOf(Character.toLowerCase(charValue2)), charValue2), Character.valueOf(charValue2));
        }
        hashMap6.putAll(hashMap2);
        hashMap6.put(C0632a.m1013a('.', hashMap6, (Object) C0632a.m1013a(' ', hashMap6, (Object) C0632a.m1013a(' ', hashMap6, (Object) C0632a.m1013a(' ', hashMap6, (Object) C0632a.m1013a('/', hashMap6, (Object) C0632a.m1013a('/', hashMap6, (Object) C0632a.m1013a('-', hashMap6, (Object) C0632a.m1013a('-', hashMap6, (Object) C0632a.m1013a('-', hashMap6, (Object) C0632a.m1013a('-', hashMap6, (Object) C0632a.m1013a('-', hashMap6, (Object) C0632a.m1013a('-', hashMap6, (Object) C0632a.m1013a('-', hashMap6, (Object) C0632a.m1013a('-', hashMap6, (Object) C0632a.m1013a('-', hashMap6, (Object) '-', 65293), 8208), 8209), 8210), 8211), 8212), 8213), 8722), '/'), 65295), ' '), 12288), 8288), '.'), 65294), '.');
        Collections.unmodifiableMap(hashMap6);
        Pattern.compile("[\\d]+(?:[~⁓∼～][\\d]+)?");
        StringBuilder Pa = C0632a.m1011Pa("\\p{Nd}{2}|[+＋]*+(?:[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～*]*\\p{Nd}){3,}[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～*");
        Pa.append(f2576KO);
        Pa.append("\\p{Nd}");
        Pa.append("]*");
        f2581SO = Pa.toString();
        m4684nb("xｘ#＃~～");
        StringBuilder Pa2 = C0632a.m1011Pa("(?:");
        Pa2.append(f2582UO);
        Pa2.append(")$");
        f2583VO = Pattern.compile(Pa2.toString(), 66);
        Pattern.compile("(\\D+)");
        Pattern.compile("\\(?\\$1\\)?");
    }

    C1734f(C1733e eVar, Map map) {
        this.f2586BO = eVar;
        this.f2587CO = map;
        for (Map.Entry entry : map.entrySet()) {
            List list = (List) entry.getValue();
            if (list.size() != 1 || !"001".equals(list.get(0))) {
                this.f2591GO.addAll(list);
            } else {
                this.f2592HO.add(entry.getKey());
            }
        }
        if (this.f2591GO.remove("001")) {
            logger.log(Level.WARNING, "invalid metadata (country calling code was mapped to the non-geo entity as well as specific region(s))");
        }
        this.f2589EO.addAll((Collection) map.get(1));
    }

    /* renamed from: a */
    static synchronized void m4682a(C1734f fVar) {
        synchronized (C1734f.class) {
            instance = fVar;
        }
    }

    /* renamed from: f */
    private Phonemetadata$PhoneMetadata m4683f(int i, String str) {
        if (!"001".equals(str)) {
            return getMetadataForRegion(str);
        }
        if (!this.f2587CO.containsKey(Integer.valueOf(i))) {
            return null;
        }
        return this.f2586BO.getMetadataForNonGeographicalRegion(i);
    }

    public static synchronized C1734f getInstance() {
        C1734f fVar;
        synchronized (C1734f.class) {
            if (instance == null) {
                C1730b bVar = C1732d.DEFAULT_METADATA_LOADER;
                if (bVar != null) {
                    m4682a(new C1734f(new C1733e(bVar), C1729a.getCountryCodeToRegionCodeMap()));
                } else {
                    throw new IllegalArgumentException("metadataLoader could not be null.");
                }
            }
            fVar = instance;
        }
        return fVar;
    }

    static boolean isViablePhoneNumber(CharSequence charSequence) {
        if (charSequence.length() < 2) {
            return false;
        }
        return f2584WO.matcher(charSequence).matches();
    }

    /* renamed from: nb */
    private static String m4684nb(String str) {
        return ";ext=(\\p{Nd}{1,7})|[  \\t,]*(?:e?xt(?:ensi(?:ó?|ó))?n?|ｅ?ｘｔｎ?|доб|[" + str + "]|int|anexo|ｉｎｔ)[:\\.．]?[  \\t,-]*" + "(\\p{Nd}{1,7})" + "#?|[- ]+(" + "\\p{Nd}" + "{1,5})#";
    }

    static StringBuilder normalize(StringBuilder sb) {
        if (f2580QO.matcher(sb).matches()) {
            int length = sb.length();
            Map map = f2575JO;
            StringBuilder sb2 = new StringBuilder(sb.length());
            for (int i = 0; i < sb.length(); i++) {
                Character ch = (Character) map.get(Character.valueOf(Character.toUpperCase(sb.charAt(i))));
                if (ch != null) {
                    sb2.append(ch);
                }
            }
            sb.replace(0, length, sb2.toString());
        } else {
            sb.replace(0, sb.length(), normalizeDigitsOnly(sb));
        }
        return sb;
    }

    public static String normalizeDigitsOnly(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        for (int i = 0; i < charSequence.length(); i++) {
            int digit = Character.digit(charSequence.charAt(i), 10);
            if (digit != -1) {
                sb.append(digit);
            }
        }
        return sb.toString();
    }

    /* renamed from: ob */
    private int m4685ob(String str) {
        Phonemetadata$PhoneMetadata metadataForRegion = getMetadataForRegion(str);
        if (metadataForRegion != null) {
            return metadataForRegion.getCountryCode();
        }
        throw new IllegalArgumentException(C0632a.m1025k("Invalid region code: ", str));
    }

    /* renamed from: pb */
    private boolean m4686pb(String str) {
        return str != null && this.f2591GO.contains(str);
    }

    /* renamed from: r */
    private static boolean m4687r(Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDesc) {
        return (phonemetadata$PhoneNumberDesc.getPossibleLengthCount() == 1 && phonemetadata$PhoneNumberDesc.getPossibleLength(0) == -1) ? false : true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.String} */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x007c, code lost:
        r2 = r5;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo9484b(com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r9) {
        /*
            r8 = this;
            int r0 = r9.getCountryCode()
            java.util.Map r1 = r8.f2587CO
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            java.lang.Object r1 = r1.get(r2)
            java.util.List r1 = (java.util.List) r1
            r2 = 0
            r3 = 0
            r4 = 1
            if (r1 != 0) goto L_0x0033
            java.util.logging.Logger r1 = logger
            java.util.logging.Level r5 = java.util.logging.Level.INFO
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Missing/invalid country_code ("
            r6.append(r7)
            r6.append(r0)
            java.lang.String r0 = ")"
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r1.log(r5, r0)
            goto L_0x007d
        L_0x0033:
            int r0 = r1.size()
            if (r0 != r4) goto L_0x0041
            java.lang.Object r0 = r1.get(r3)
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x007d
        L_0x0041:
            java.lang.String r0 = r8.mo9480a((com.google.i18n.phonenumbers.Phonenumber$PhoneNumber) r9)
            java.util.Iterator r1 = r1.iterator()
        L_0x0049:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x007d
            java.lang.Object r5 = r1.next()
            java.lang.String r5 = (java.lang.String) r5
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r6 = r8.getMetadataForRegion(r5)
            boolean r7 = r6.hasLeadingDigits()
            if (r7 == 0) goto L_0x0074
            com.google.i18n.phonenumbers.internal.c r7 = r8.f2590FO
            java.lang.String r6 = r6.getLeadingDigits()
            java.util.regex.Pattern r6 = r7.getPatternForRegex(r6)
            java.util.regex.Matcher r6 = r6.matcher(r0)
            boolean r6 = r6.lookingAt()
            if (r6 == 0) goto L_0x0049
            goto L_0x007c
        L_0x0074:
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r6 = r8.m4678a((java.lang.String) r0, (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r6)
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r7 = com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType.UNKNOWN
            if (r6 == r7) goto L_0x0049
        L_0x007c:
            r2 = r5
        L_0x007d:
            int r0 = r9.getCountryCode()
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r1 = r8.m4683f(r0, r2)
            if (r1 == 0) goto L_0x00a3
            java.lang.String r5 = "001"
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L_0x0096
            int r2 = r8.m4685ob(r2)
            if (r0 == r2) goto L_0x0096
            goto L_0x00a3
        L_0x0096:
            java.lang.String r9 = r8.mo9480a((com.google.i18n.phonenumbers.Phonenumber$PhoneNumber) r9)
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r8 = r8.m4678a((java.lang.String) r9, (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r1)
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r9 = com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType.UNKNOWN
            if (r8 == r9) goto L_0x00a3
            r3 = r4
        L_0x00a3:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.C1734f.mo9484b(com.google.i18n.phonenumbers.Phonenumber$PhoneNumber):boolean");
    }

    public int getCountryCodeForRegion(String str) {
        if (m4686pb(str)) {
            return m4685ob(str);
        }
        Logger logger2 = logger;
        Level level = Level.WARNING;
        StringBuilder Pa = C0632a.m1011Pa("Invalid or missing region code (");
        if (str == null) {
            str = "null";
        }
        Pa.append(str);
        Pa.append(") provided.");
        logger2.log(level, Pa.toString());
        return 0;
    }

    /* access modifiers changed from: package-private */
    public Phonemetadata$PhoneMetadata getMetadataForRegion(String str) {
        if (!m4686pb(str)) {
            return null;
        }
        return this.f2586BO.getMetadataForRegion(str);
    }

    public String getRegionCodeForCountryCode(int i) {
        List list = (List) this.f2587CO.get(Integer.valueOf(i));
        if (list == null) {
            return "ZZ";
        }
        return (String) list.get(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:81:0x019d, code lost:
        if (r11 != com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.INVALID_LENGTH) goto L_0x01a1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.i18n.phonenumbers.Phonenumber$PhoneNumber parse(java.lang.CharSequence r12, java.lang.String r13) {
        /*
            r11 = this;
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r6 = new com.google.i18n.phonenumbers.Phonenumber$PhoneNumber
            r6.<init>()
            if (r12 == 0) goto L_0x022e
            int r0 = r12.length()
            r1 = 250(0xfa, float:3.5E-43)
            if (r0 > r1) goto L_0x0224
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r12 = r12.toString()
            java.lang.String r0 = ";phone-context="
            int r0 = r12.indexOf(r0)
            java.lang.String r1 = ""
            r8 = 0
            if (r0 < 0) goto L_0x0060
            int r2 = r0 + 15
            int r3 = r12.length()
            int r3 = r3 + -1
            if (r2 >= r3) goto L_0x004c
            char r3 = r12.charAt(r2)
            r4 = 43
            if (r3 != r4) goto L_0x004c
            r3 = 59
            int r3 = r12.indexOf(r3, r2)
            if (r3 <= 0) goto L_0x0045
            java.lang.String r2 = r12.substring(r2, r3)
            r7.append(r2)
            goto L_0x004c
        L_0x0045:
            java.lang.String r2 = r12.substring(r2)
            r7.append(r2)
        L_0x004c:
            java.lang.String r2 = "tel:"
            int r2 = r12.indexOf(r2)
            if (r2 < 0) goto L_0x0057
            int r2 = r2 + 4
            goto L_0x0058
        L_0x0057:
            r2 = r8
        L_0x0058:
            java.lang.String r12 = r12.substring(r2, r0)
            r7.append(r12)
            goto L_0x00a5
        L_0x0060:
            java.util.regex.Pattern r0 = f2579PO
            java.util.regex.Matcher r0 = r0.matcher(r12)
            boolean r2 = r0.find()
            if (r2 == 0) goto L_0x00a1
            int r0 = r0.start()
            int r2 = r12.length()
            java.lang.CharSequence r12 = r12.subSequence(r0, r2)
            java.util.regex.Pattern r0 = UNWANTED_END_CHAR_PATTERN
            java.util.regex.Matcher r0 = r0.matcher(r12)
            boolean r2 = r0.find()
            if (r2 == 0) goto L_0x008c
            int r0 = r0.start()
            java.lang.CharSequence r12 = r12.subSequence(r8, r0)
        L_0x008c:
            java.util.regex.Pattern r0 = SECOND_NUMBER_START_PATTERN
            java.util.regex.Matcher r0 = r0.matcher(r12)
            boolean r2 = r0.find()
            if (r2 == 0) goto L_0x00a2
            int r0 = r0.start()
            java.lang.CharSequence r12 = r12.subSequence(r8, r0)
            goto L_0x00a2
        L_0x00a1:
            r12 = r1
        L_0x00a2:
            r7.append(r12)
        L_0x00a5:
            java.lang.String r12 = ";isub="
            int r12 = r7.indexOf(r12)
            if (r12 <= 0) goto L_0x00b4
            int r0 = r7.length()
            r7.delete(r12, r0)
        L_0x00b4:
            boolean r12 = isViablePhoneNumber(r7)
            if (r12 == 0) goto L_0x021a
            boolean r12 = r11.m4686pb(r13)
            r9 = 1
            if (r12 != 0) goto L_0x00d5
            int r12 = r7.length()
            if (r12 == 0) goto L_0x00d3
            java.util.regex.Pattern r12 = PLUS_CHARS_PATTERN
            java.util.regex.Matcher r12 = r12.matcher(r7)
            boolean r12 = r12.lookingAt()
            if (r12 != 0) goto L_0x00d5
        L_0x00d3:
            r12 = r8
            goto L_0x00d6
        L_0x00d5:
            r12 = r9
        L_0x00d6:
            if (r12 == 0) goto L_0x0210
            java.util.regex.Pattern r12 = f2583VO
            java.util.regex.Matcher r12 = r12.matcher(r7)
            boolean r0 = r12.find()
            if (r0 == 0) goto L_0x0112
            int r0 = r12.start()
            java.lang.String r0 = r7.substring(r8, r0)
            boolean r0 = isViablePhoneNumber(r0)
            if (r0 == 0) goto L_0x0112
            int r0 = r12.groupCount()
            r2 = r9
        L_0x00f7:
            if (r2 > r0) goto L_0x0112
            java.lang.String r3 = r12.group(r2)
            if (r3 == 0) goto L_0x010f
            java.lang.String r1 = r12.group(r2)
            int r12 = r12.start()
            int r0 = r7.length()
            r7.delete(r12, r0)
            goto L_0x0112
        L_0x010f:
            int r2 = r2 + 1
            goto L_0x00f7
        L_0x0112:
            int r12 = r1.length()
            if (r12 <= 0) goto L_0x011b
            r6.setExtension(r1)
        L_0x011b:
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r12 = r11.getMetadataForRegion(r13)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r4 = 0
            r0 = r11
            r1 = r7
            r2 = r12
            r3 = r10
            r5 = r6
            int r0 = r0.mo9478a(r1, r2, r3, r4, r5)     // Catch:{ NumberParseException -> 0x012f }
            goto L_0x0157
        L_0x012f:
            r0 = move-exception
            java.util.regex.Pattern r1 = PLUS_CHARS_PATTERN
            java.util.regex.Matcher r1 = r1.matcher(r7)
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r2 = r0.getErrorType()
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r3 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.INVALID_COUNTRY_CODE
            if (r2 != r3) goto L_0x0202
            boolean r2 = r1.lookingAt()
            if (r2 == 0) goto L_0x0202
            int r0 = r1.end()
            java.lang.String r1 = r7.substring(r0)
            r4 = 0
            r0 = r11
            r2 = r12
            r3 = r10
            r5 = r6
            int r0 = r0.mo9478a(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x01f8
        L_0x0157:
            if (r0 == 0) goto L_0x0168
            java.lang.String r1 = r11.getRegionCodeForCountryCode(r0)
            boolean r13 = r1.equals(r13)
            if (r13 != 0) goto L_0x0177
            com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r12 = r11.m4683f(r0, r1)
            goto L_0x0177
        L_0x0168:
            normalize(r7)
            r10.append(r7)
            if (r13 == 0) goto L_0x0177
            int r13 = r12.getCountryCode()
            r6.setCountryCode(r13)
        L_0x0177:
            int r13 = r10.length()
            java.lang.String r0 = "The string supplied is too short to be a phone number."
            r1 = 2
            if (r13 < r1) goto L_0x01f0
            if (r12 == 0) goto L_0x01a0
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r10)
            r11.mo9483a((java.lang.StringBuilder) r2, (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r12, (java.lang.StringBuilder) r13)
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r11 = r11.m4679a((java.lang.CharSequence) r2, (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r12)
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r12 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.TOO_SHORT
            if (r11 == r12) goto L_0x01a0
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r12 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.IS_POSSIBLE_LOCAL_ONLY
            if (r11 == r12) goto L_0x01a0
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r12 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.INVALID_LENGTH
            if (r11 == r12) goto L_0x01a0
            goto L_0x01a1
        L_0x01a0:
            r2 = r10
        L_0x01a1:
            int r11 = r2.length()
            if (r11 < r1) goto L_0x01e8
            r12 = 17
            if (r11 > r12) goto L_0x01de
            int r11 = r2.length()
            if (r11 <= r9) goto L_0x01d2
            char r11 = r2.charAt(r8)
            r12 = 48
            if (r11 != r12) goto L_0x01d2
            r6.setItalianLeadingZero(r9)
            r11 = r9
        L_0x01bd:
            int r13 = r2.length()
            int r13 = r13 - r9
            if (r11 >= r13) goto L_0x01cd
            char r13 = r2.charAt(r11)
            if (r13 != r12) goto L_0x01cd
            int r11 = r11 + 1
            goto L_0x01bd
        L_0x01cd:
            if (r11 == r9) goto L_0x01d2
            r6.setNumberOfLeadingZeros(r11)
        L_0x01d2:
            java.lang.String r11 = r2.toString()
            long r11 = java.lang.Long.parseLong(r11)
            r6.setNationalNumber(r11)
            return r6
        L_0x01de:
            com.google.i18n.phonenumbers.NumberParseException r11 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r12 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.TOO_LONG
            java.lang.String r13 = "The string supplied is too long to be a phone number."
            r11.<init>(r12, r13)
            throw r11
        L_0x01e8:
            com.google.i18n.phonenumbers.NumberParseException r11 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r12 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.TOO_SHORT_NSN
            r11.<init>(r12, r0)
            throw r11
        L_0x01f0:
            com.google.i18n.phonenumbers.NumberParseException r11 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r12 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.TOO_SHORT_NSN
            r11.<init>(r12, r0)
            throw r11
        L_0x01f8:
            com.google.i18n.phonenumbers.NumberParseException r11 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r12 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.INVALID_COUNTRY_CODE
            java.lang.String r13 = "Could not interpret numbers after plus-sign."
            r11.<init>(r12, r13)
            throw r11
        L_0x0202:
            com.google.i18n.phonenumbers.NumberParseException r11 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r12 = r0.getErrorType()
            java.lang.String r13 = r0.getMessage()
            r11.<init>(r12, r13)
            throw r11
        L_0x0210:
            com.google.i18n.phonenumbers.NumberParseException r11 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r12 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.INVALID_COUNTRY_CODE
            java.lang.String r13 = "Missing or invalid default region."
            r11.<init>(r12, r13)
            throw r11
        L_0x021a:
            com.google.i18n.phonenumbers.NumberParseException r11 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r12 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.NOT_A_NUMBER
            java.lang.String r13 = "The string supplied did not seem to be a phone number."
            r11.<init>(r12, r13)
            throw r11
        L_0x0224:
            com.google.i18n.phonenumbers.NumberParseException r11 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r12 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.TOO_LONG
            java.lang.String r13 = "The string supplied was too long to parse."
            r11.<init>(r12, r13)
            throw r11
        L_0x022e:
            com.google.i18n.phonenumbers.NumberParseException r11 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r12 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.NOT_A_NUMBER
            java.lang.String r13 = "The phone number supplied was null."
            r11.<init>(r12, r13)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.C1734f.parse(java.lang.CharSequence, java.lang.String):com.google.i18n.phonenumbers.Phonenumber$PhoneNumber");
    }

    /* renamed from: a */
    public String mo9481a(Phonenumber$PhoneNumber phonenumber$PhoneNumber, PhoneNumberUtil$PhoneNumberFormat phoneNumberUtil$PhoneNumberFormat) {
        List list;
        Phonemetadata$NumberFormat phonemetadata$NumberFormat;
        if (phonenumber$PhoneNumber.getNationalNumber() == 0 && phonenumber$PhoneNumber.hasRawInput()) {
            String rawInput = phonenumber$PhoneNumber.getRawInput();
            if (rawInput.length() > 0) {
                return rawInput;
            }
        }
        StringBuilder sb = new StringBuilder(20);
        sb.setLength(0);
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        String a = mo9480a(phonenumber$PhoneNumber);
        if (phoneNumberUtil$PhoneNumberFormat == PhoneNumberUtil$PhoneNumberFormat.E164) {
            sb.append(a);
            m4681a(countryCode, PhoneNumberUtil$PhoneNumberFormat.E164, sb);
        } else if (!this.f2587CO.containsKey(Integer.valueOf(countryCode))) {
            sb.append(a);
        } else {
            Phonemetadata$PhoneMetadata f = m4683f(countryCode, getRegionCodeForCountryCode(countryCode));
            if (f.intlNumberFormats().size() == 0 || phoneNumberUtil$PhoneNumberFormat == PhoneNumberUtil$PhoneNumberFormat.NATIONAL) {
                list = f.numberFormats();
            } else {
                list = f.intlNumberFormats();
            }
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    phonemetadata$NumberFormat = null;
                    break;
                }
                phonemetadata$NumberFormat = (Phonemetadata$NumberFormat) it.next();
                int leadingDigitsPatternSize = phonemetadata$NumberFormat.leadingDigitsPatternSize();
                if ((leadingDigitsPatternSize == 0 || this.f2590FO.getPatternForRegex(phonemetadata$NumberFormat.getLeadingDigitsPattern(leadingDigitsPatternSize - 1)).matcher(a).lookingAt()) && this.f2590FO.getPatternForRegex(phonemetadata$NumberFormat.getPattern()).matcher(a).matches()) {
                    break;
                }
            }
            if (phonemetadata$NumberFormat != null) {
                String format = phonemetadata$NumberFormat.getFormat();
                Matcher matcher = this.f2590FO.getPatternForRegex(phonemetadata$NumberFormat.getPattern()).matcher(a);
                PhoneNumberUtil$PhoneNumberFormat phoneNumberUtil$PhoneNumberFormat2 = PhoneNumberUtil$PhoneNumberFormat.NATIONAL;
                String nationalPrefixFormattingRule = phonemetadata$NumberFormat.getNationalPrefixFormattingRule();
                if (phoneNumberUtil$PhoneNumberFormat != PhoneNumberUtil$PhoneNumberFormat.NATIONAL || nationalPrefixFormattingRule == null || nationalPrefixFormattingRule.length() <= 0) {
                    a = matcher.replaceAll(format);
                } else {
                    a = matcher.replaceAll(f2585XO.matcher(format).replaceFirst(nationalPrefixFormattingRule));
                }
                if (phoneNumberUtil$PhoneNumberFormat == PhoneNumberUtil$PhoneNumberFormat.RFC3966) {
                    Matcher matcher2 = f2577LO.matcher(a);
                    if (matcher2.lookingAt()) {
                        a = matcher2.replaceFirst("");
                    }
                    a = matcher2.reset(a).replaceAll("-");
                }
            }
            sb.append(a);
            if (phonenumber$PhoneNumber.hasExtension() && phonenumber$PhoneNumber.getExtension().length() > 0) {
                if (phoneNumberUtil$PhoneNumberFormat == PhoneNumberUtil$PhoneNumberFormat.RFC3966) {
                    sb.append(";ext=");
                    sb.append(phonenumber$PhoneNumber.getExtension());
                } else if (f.hasPreferredExtnPrefix()) {
                    sb.append(f.getPreferredExtnPrefix());
                    sb.append(phonenumber$PhoneNumber.getExtension());
                } else {
                    sb.append(" ext. ");
                    sb.append(phonenumber$PhoneNumber.getExtension());
                }
            }
            m4681a(countryCode, phoneNumberUtil$PhoneNumberFormat, sb);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public String mo9480a(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        StringBuilder sb = new StringBuilder();
        if (phonenumber$PhoneNumber.isItalianLeadingZero() && phonenumber$PhoneNumber.getNumberOfLeadingZeros() > 0) {
            char[] cArr = new char[phonenumber$PhoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            sb.append(new String(cArr));
        }
        sb.append(phonenumber$PhoneNumber.getNationalNumber());
        return sb.toString();
    }

    /* renamed from: a */
    private void m4681a(int i, PhoneNumberUtil$PhoneNumberFormat phoneNumberUtil$PhoneNumberFormat, StringBuilder sb) {
        int ordinal = phoneNumberUtil$PhoneNumberFormat.ordinal();
        if (ordinal == 0) {
            sb.insert(0, i).insert(0, '+');
        } else if (ordinal == 1) {
            sb.insert(0, " ").insert(0, i).insert(0, '+');
        } else if (ordinal == 3) {
            sb.insert(0, "-").insert(0, i).insert(0, '+').insert(0, "tel:");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Phonemetadata$PhoneNumberDesc mo9479a(Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, PhoneNumberUtil$PhoneNumberType phoneNumberUtil$PhoneNumberType) {
        switch (phoneNumberUtil$PhoneNumberType.ordinal()) {
            case 0:
            case 2:
                return phonemetadata$PhoneMetadata.getFixedLine();
            case 1:
                return phonemetadata$PhoneMetadata.getMobile();
            case 3:
                return phonemetadata$PhoneMetadata.getTollFree();
            case 4:
                return phonemetadata$PhoneMetadata.getPremiumRate();
            case 5:
                return phonemetadata$PhoneMetadata.getSharedCost();
            case 6:
                return phonemetadata$PhoneMetadata.getVoip();
            case 7:
                return phonemetadata$PhoneMetadata.getPersonalNumber();
            case 8:
                return phonemetadata$PhoneMetadata.getPager();
            case 9:
                return phonemetadata$PhoneMetadata.getUan();
            case 10:
                return phonemetadata$PhoneMetadata.getVoicemail();
            default:
                return phonemetadata$PhoneMetadata.getGeneralDesc();
        }
    }

    /* renamed from: a */
    private PhoneNumberUtil$PhoneNumberType m4678a(String str, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata) {
        if (!mo9482a(str, phonemetadata$PhoneMetadata.getGeneralDesc())) {
            return PhoneNumberUtil$PhoneNumberType.UNKNOWN;
        }
        if (mo9482a(str, phonemetadata$PhoneMetadata.getPremiumRate())) {
            return PhoneNumberUtil$PhoneNumberType.PREMIUM_RATE;
        }
        if (mo9482a(str, phonemetadata$PhoneMetadata.getTollFree())) {
            return PhoneNumberUtil$PhoneNumberType.TOLL_FREE;
        }
        if (mo9482a(str, phonemetadata$PhoneMetadata.getSharedCost())) {
            return PhoneNumberUtil$PhoneNumberType.SHARED_COST;
        }
        if (mo9482a(str, phonemetadata$PhoneMetadata.getVoip())) {
            return PhoneNumberUtil$PhoneNumberType.VOIP;
        }
        if (mo9482a(str, phonemetadata$PhoneMetadata.getPersonalNumber())) {
            return PhoneNumberUtil$PhoneNumberType.PERSONAL_NUMBER;
        }
        if (mo9482a(str, phonemetadata$PhoneMetadata.getPager())) {
            return PhoneNumberUtil$PhoneNumberType.PAGER;
        }
        if (mo9482a(str, phonemetadata$PhoneMetadata.getUan())) {
            return PhoneNumberUtil$PhoneNumberType.UAN;
        }
        if (mo9482a(str, phonemetadata$PhoneMetadata.getVoicemail())) {
            return PhoneNumberUtil$PhoneNumberType.VOICEMAIL;
        }
        if (mo9482a(str, phonemetadata$PhoneMetadata.getFixedLine())) {
            if (phonemetadata$PhoneMetadata.getSameMobileAndFixedLinePattern()) {
                return PhoneNumberUtil$PhoneNumberType.FIXED_LINE_OR_MOBILE;
            }
            if (mo9482a(str, phonemetadata$PhoneMetadata.getMobile())) {
                return PhoneNumberUtil$PhoneNumberType.FIXED_LINE_OR_MOBILE;
            }
            return PhoneNumberUtil$PhoneNumberType.FIXED_LINE;
        } else if (phonemetadata$PhoneMetadata.getSameMobileAndFixedLinePattern() || !mo9482a(str, phonemetadata$PhoneMetadata.getMobile())) {
            return PhoneNumberUtil$PhoneNumberType.UNKNOWN;
        } else {
            return PhoneNumberUtil$PhoneNumberType.MOBILE;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo9482a(String str, Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDesc) {
        int length = str.length();
        List possibleLengthList = phonemetadata$PhoneNumberDesc.getPossibleLengthList();
        if (possibleLengthList.size() <= 0 || possibleLengthList.contains(Integer.valueOf(length))) {
            return this.f2588DO.mo9490a(str, phonemetadata$PhoneNumberDesc, false);
        }
        return false;
    }

    /* renamed from: a */
    private PhoneNumberUtil$ValidationResult m4679a(CharSequence charSequence, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata) {
        return m4680a(charSequence, phonemetadata$PhoneMetadata, PhoneNumberUtil$PhoneNumberType.UNKNOWN);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0092  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult m4680a(java.lang.CharSequence r4, com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r5, com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r6) {
        /*
            r3 = this;
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r0 = r3.mo9479a((com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r5, (com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType) r6)
            java.util.List r1 = r0.getPossibleLengthList()
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0017
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r1 = r5.getGeneralDesc()
            java.util.List r1 = r1.getPossibleLengthList()
            goto L_0x001b
        L_0x0017:
            java.util.List r1 = r0.getPossibleLengthList()
        L_0x001b:
            java.util.List r0 = r0.getPossibleLengthLocalOnlyList()
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r2 = com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType.FIXED_LINE_OR_MOBILE
            if (r6 != r2) goto L_0x0080
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r6 = com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType.FIXED_LINE
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r6 = r3.mo9479a((com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r5, (com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType) r6)
            boolean r6 = m4687r(r6)
            if (r6 != 0) goto L_0x0036
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r6 = com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType.MOBILE
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = r3.m4680a((java.lang.CharSequence) r4, (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r5, (com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType) r6)
            return r3
        L_0x0036:
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r6 = com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType.MOBILE
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r3 = r3.mo9479a((com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r5, (com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType) r6)
            boolean r6 = m4687r(r3)
            if (r6 == 0) goto L_0x0080
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>(r1)
            java.util.List r1 = r3.getPossibleLengthList()
            int r1 = r1.size()
            if (r1 != 0) goto L_0x005a
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r5 = r5.getGeneralDesc()
            java.util.List r5 = r5.getPossibleLengthList()
            goto L_0x005e
        L_0x005a:
            java.util.List r5 = r3.getPossibleLengthList()
        L_0x005e:
            r6.addAll(r5)
            java.util.Collections.sort(r6)
            boolean r5 = r0.isEmpty()
            if (r5 == 0) goto L_0x006f
            java.util.List r0 = r3.getPossibleLengthLocalOnlyList()
            goto L_0x0081
        L_0x006f:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r0)
            java.util.List r3 = r3.getPossibleLengthLocalOnlyList()
            r5.addAll(r3)
            java.util.Collections.sort(r5)
            r0 = r5
            goto L_0x0081
        L_0x0080:
            r6 = r1
        L_0x0081:
            r3 = 0
            java.lang.Object r5 = r6.get(r3)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            r1 = -1
            if (r5 != r1) goto L_0x0092
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.INVALID_LENGTH
            return r3
        L_0x0092:
            int r4 = r4.length()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            boolean r5 = r0.contains(r5)
            if (r5 == 0) goto L_0x00a3
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.IS_POSSIBLE_LOCAL_ONLY
            return r3
        L_0x00a3:
            java.lang.Object r3 = r6.get(r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            if (r3 != r4) goto L_0x00b2
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.IS_POSSIBLE
            return r3
        L_0x00b2:
            if (r3 <= r4) goto L_0x00b7
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.TOO_SHORT
            return r3
        L_0x00b7:
            int r3 = r6.size()
            r5 = 1
            int r3 = r3 - r5
            java.lang.Object r3 = r6.get(r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            if (r3 >= r4) goto L_0x00cc
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.TOO_LONG
            return r3
        L_0x00cc:
            int r3 = r6.size()
            java.util.List r3 = r6.subList(r5, r3)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto L_0x00e1
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.IS_POSSIBLE
            goto L_0x00e3
        L_0x00e1:
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.INVALID_LENGTH
        L_0x00e3:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.C1734f.m4680a(java.lang.CharSequence, com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata, com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType):com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00cc  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int mo9478a(java.lang.CharSequence r6, com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r7, java.lang.StringBuilder r8, boolean r9, com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r10) {
        /*
            r5 = this;
            int r0 = r6.length()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r6)
            if (r7 == 0) goto L_0x0014
            java.lang.String r6 = r7.getInternationalPrefix()
            goto L_0x0016
        L_0x0014:
            java.lang.String r6 = "NonMatch"
        L_0x0016:
            int r2 = r0.length()
            r3 = 1
            if (r2 != 0) goto L_0x0020
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber$CountryCodeSource r6 = com.google.i18n.phonenumbers.Phonenumber$PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY
            goto L_0x007e
        L_0x0020:
            java.util.regex.Pattern r2 = PLUS_CHARS_PATTERN
            java.util.regex.Matcher r2 = r2.matcher(r0)
            boolean r4 = r2.lookingAt()
            if (r4 == 0) goto L_0x0039
            int r6 = r2.end()
            r0.delete(r1, r6)
            normalize(r0)
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber$CountryCodeSource r6 = com.google.i18n.phonenumbers.Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN
            goto L_0x007e
        L_0x0039:
            com.google.i18n.phonenumbers.internal.c r2 = r5.f2590FO
            java.util.regex.Pattern r6 = r2.getPatternForRegex(r6)
            normalize(r0)
            java.util.regex.Matcher r6 = r6.matcher(r0)
            boolean r2 = r6.lookingAt()
            if (r2 == 0) goto L_0x0076
            int r6 = r6.end()
            java.util.regex.Pattern r2 = f2578OO
            java.lang.String r4 = r0.substring(r6)
            java.util.regex.Matcher r2 = r2.matcher(r4)
            boolean r4 = r2.find()
            if (r4 == 0) goto L_0x0071
            java.lang.String r2 = r2.group(r3)
            java.lang.String r2 = normalizeDigitsOnly(r2)
            java.lang.String r4 = "0"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0071
            goto L_0x0076
        L_0x0071:
            r0.delete(r1, r6)
            r6 = r3
            goto L_0x0077
        L_0x0076:
            r6 = r1
        L_0x0077:
            if (r6 == 0) goto L_0x007c
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber$CountryCodeSource r6 = com.google.i18n.phonenumbers.Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD
            goto L_0x007e
        L_0x007c:
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber$CountryCodeSource r6 = com.google.i18n.phonenumbers.Phonenumber$PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY
        L_0x007e:
            if (r9 == 0) goto L_0x0083
            r10.mo9451a(r6)
        L_0x0083:
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber$CountryCodeSource r2 = com.google.i18n.phonenumbers.Phonenumber$PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY
            if (r6 == r2) goto L_0x00e0
            int r6 = r0.length()
            r7 = 2
            if (r6 <= r7) goto L_0x00d6
            int r6 = r0.length()
            if (r6 == 0) goto L_0x00c5
            char r6 = r0.charAt(r1)
            r7 = 48
            if (r6 != r7) goto L_0x009d
            goto L_0x00c5
        L_0x009d:
            int r6 = r0.length()
        L_0x00a1:
            r7 = 3
            if (r3 > r7) goto L_0x00c5
            if (r3 > r6) goto L_0x00c5
            java.lang.String r7 = r0.substring(r1, r3)
            int r7 = java.lang.Integer.parseInt(r7)
            java.util.Map r9 = r5.f2587CO
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)
            boolean r9 = r9.containsKey(r2)
            if (r9 == 0) goto L_0x00c2
            java.lang.String r5 = r0.substring(r3)
            r8.append(r5)
            goto L_0x00c6
        L_0x00c2:
            int r3 = r3 + 1
            goto L_0x00a1
        L_0x00c5:
            r7 = r1
        L_0x00c6:
            if (r7 == 0) goto L_0x00cc
            r10.setCountryCode(r7)
            return r7
        L_0x00cc:
            com.google.i18n.phonenumbers.NumberParseException r5 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r6 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.INVALID_COUNTRY_CODE
            java.lang.String r7 = "Country calling code supplied was not recognised."
            r5.<init>(r6, r7)
            throw r5
        L_0x00d6:
            com.google.i18n.phonenumbers.NumberParseException r5 = new com.google.i18n.phonenumbers.NumberParseException
            com.google.i18n.phonenumbers.NumberParseException$ErrorType r6 = com.google.i18n.phonenumbers.NumberParseException.ErrorType.TOO_SHORT_AFTER_IDD
            java.lang.String r7 = "Phone number had an IDD, but after this was not long enough to be a viable phone number."
            r5.<init>(r6, r7)
            throw r5
        L_0x00e0:
            if (r7 == 0) goto L_0x0131
            int r6 = r7.getCountryCode()
            java.lang.String r2 = java.lang.String.valueOf(r6)
            java.lang.String r3 = r0.toString()
            boolean r4 = r3.startsWith(r2)
            if (r4 == 0) goto L_0x0131
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r2 = r2.length()
            java.lang.String r2 = r3.substring(r2)
            r4.<init>(r2)
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r2 = r7.getGeneralDesc()
            r3 = 0
            r5.mo9483a((java.lang.StringBuilder) r4, (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r7, (java.lang.StringBuilder) r3)
            com.google.i18n.phonenumbers.internal.a r3 = r5.f2588DO
            boolean r3 = r3.mo9490a(r0, r2, r1)
            if (r3 != 0) goto L_0x0119
            com.google.i18n.phonenumbers.internal.a r3 = r5.f2588DO
            boolean r2 = r3.mo9490a(r4, r2, r1)
            if (r2 != 0) goto L_0x0123
        L_0x0119:
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r2 = com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType.UNKNOWN
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r5 = r5.m4680a((java.lang.CharSequence) r0, (com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata) r7, (com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType) r2)
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r7 = com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult.TOO_LONG
            if (r5 != r7) goto L_0x0131
        L_0x0123:
            r8.append(r4)
            if (r9 == 0) goto L_0x012d
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber$CountryCodeSource r5 = com.google.i18n.phonenumbers.Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN
            r10.mo9451a(r5)
        L_0x012d:
            r10.setCountryCode(r6)
            return r6
        L_0x0131:
            r10.setCountryCode(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.C1734f.mo9478a(java.lang.CharSequence, com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata, java.lang.StringBuilder, boolean, com.google.i18n.phonenumbers.Phonenumber$PhoneNumber):int");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo9483a(StringBuilder sb, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, StringBuilder sb2) {
        int length = sb.length();
        String nationalPrefixForParsing = phonemetadata$PhoneMetadata.getNationalPrefixForParsing();
        if (!(length == 0 || nationalPrefixForParsing.length() == 0)) {
            Matcher matcher = this.f2590FO.getPatternForRegex(nationalPrefixForParsing).matcher(sb);
            if (matcher.lookingAt()) {
                Phonemetadata$PhoneNumberDesc generalDesc = phonemetadata$PhoneMetadata.getGeneralDesc();
                boolean a = this.f2588DO.mo9490a(sb, generalDesc, false);
                int groupCount = matcher.groupCount();
                String nationalPrefixTransformRule = phonemetadata$PhoneMetadata.getNationalPrefixTransformRule();
                if (nationalPrefixTransformRule != null && nationalPrefixTransformRule.length() != 0 && matcher.group(groupCount) != null) {
                    StringBuilder sb3 = new StringBuilder(sb);
                    sb3.replace(0, length, matcher.replaceFirst(nationalPrefixTransformRule));
                    if (a && !this.f2588DO.mo9490a(sb3.toString(), generalDesc, false)) {
                        return false;
                    }
                    if (sb2 != null && groupCount > 1) {
                        sb2.append(matcher.group(1));
                    }
                    sb.replace(0, sb.length(), sb3.toString());
                    return true;
                } else if (a && !this.f2588DO.mo9490a(sb.substring(matcher.end()), generalDesc, false)) {
                    return false;
                } else {
                    if (!(sb2 == null || groupCount <= 0 || matcher.group(groupCount) == null)) {
                        sb2.append(matcher.group(1));
                    }
                    sb.delete(0, matcher.end());
                    return true;
                }
            }
        }
        return false;
    }
}
