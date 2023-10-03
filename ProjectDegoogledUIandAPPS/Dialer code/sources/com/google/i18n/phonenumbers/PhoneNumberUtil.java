package com.google.i18n.phonenumbers;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.internal.RegexBasedMatcher;
import com.google.i18n.phonenumbers.internal.RegexCache;
import java.util.ArrayList;
import java.util.Arrays;
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

public class PhoneNumberUtil {
    private static final Map<Character, Character> ALPHA_MAPPINGS;
    private static final Map<Character, Character> ALPHA_PHONE_MAPPINGS;
    private static final Pattern CAPTURING_DIGIT_PATTERN = Pattern.compile("(\\p{Nd})");
    private static final Map<Character, Character> DIALLABLE_CHAR_MAPPINGS;
    private static final Pattern EXTN_PATTERN;
    private static final String EXTN_PATTERNS_FOR_PARSING = createExtnPattern(GeneratedOutlineSupport.outline8(",;", "xｘ#＃~～"));
    private static final Pattern FIRST_GROUP_PATTERN = Pattern.compile("(\\$\\d)");
    private static final Set<Integer> GEO_MOBILE_COUNTRIES;
    private static final Map<Integer, String> MOBILE_TOKEN_MAPPINGS;
    static final Pattern PLUS_CHARS_PATTERN = Pattern.compile("[+＋]+");
    static final Pattern SECOND_NUMBER_START_PATTERN = Pattern.compile("[\\\\/] *x");
    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～]+");
    static final Pattern UNWANTED_END_CHAR_PATTERN = Pattern.compile("[[\\P{N}&&\\P{L}]&&[^#]]+$");
    private static final String VALID_ALPHA = (Arrays.toString(ALPHA_MAPPINGS.keySet().toArray()).replaceAll("[, \\[\\]]", "") + Arrays.toString(ALPHA_MAPPINGS.keySet().toArray()).toLowerCase().replaceAll("[, \\[\\]]", ""));
    private static final Pattern VALID_ALPHA_PHONE_PATTERN = Pattern.compile("(?:.*?[A-Za-z]){3}.*");
    private static final String VALID_PHONE_NUMBER;
    private static final Pattern VALID_PHONE_NUMBER_PATTERN = Pattern.compile(VALID_PHONE_NUMBER + "(?:" + EXTN_PATTERNS_FOR_PARSING + ")?", 66);
    private static final Pattern VALID_START_CHAR_PATTERN = Pattern.compile("[+＋\\p{Nd}]");
    private static PhoneNumberUtil instance = null;
    private static final Logger logger = Logger.getLogger(PhoneNumberUtil.class.getName());
    private final Map<Integer, List<String>> countryCallingCodeToRegionCodeMap;
    private final Set<Integer> countryCodesForNonGeographicalRegion = new HashSet();
    private final RegexBasedMatcher matcherApi = RegexBasedMatcher.create();
    private final MultiFileMetadataSourceImpl metadataSource;
    private final Set<String> nanpaRegions = new HashSet(35);
    private final RegexCache regexCache = new RegexCache(100);
    private final Set<String> supportedRegions = new HashSet(320);

    public enum MatchType {
        NOT_A_NUMBER,
        NO_MATCH,
        SHORT_NSN_MATCH,
        NSN_MATCH,
        EXACT_MATCH
    }

    public enum PhoneNumberFormat {
        E164,
        INTERNATIONAL,
        NATIONAL,
        RFC3966
    }

    public enum PhoneNumberType {
        FIXED_LINE,
        MOBILE,
        FIXED_LINE_OR_MOBILE,
        TOLL_FREE,
        PREMIUM_RATE,
        SHARED_COST,
        VOIP,
        PERSONAL_NUMBER,
        PAGER,
        UAN,
        VOICEMAIL,
        UNKNOWN
    }

    public enum ValidationResult {
        IS_POSSIBLE,
        IS_POSSIBLE_LOCAL_ONLY,
        INVALID_COUNTRY_CODE,
        TOO_SHORT,
        INVALID_LENGTH,
        TOO_LONG
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(52, "1");
        hashMap.put(54, "9");
        MOBILE_TOKEN_MAPPINGS = Collections.unmodifiableMap(hashMap);
        HashSet hashSet = new HashSet();
        hashSet.add(86);
        Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet();
        hashSet2.add(52);
        hashSet2.add(54);
        hashSet2.add(55);
        hashSet2.add(62);
        hashSet2.addAll(hashSet);
        GEO_MOBILE_COUNTRIES = Collections.unmodifiableSet(hashSet2);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(GeneratedOutlineSupport.outline3('0', hashMap2, '0', '1'), '1');
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
        hashMap3.put(GeneratedOutlineSupport.outline3('8', hashMap3, GeneratedOutlineSupport.outline3('8', hashMap3, 'T', 'U'), 'V'), '8');
        hashMap3.put('W', '9');
        hashMap3.put('X', '9');
        hashMap3.put('Y', '9');
        hashMap3.put('Z', '9');
        ALPHA_MAPPINGS = Collections.unmodifiableMap(hashMap3);
        HashMap hashMap4 = new HashMap(100);
        hashMap4.putAll(ALPHA_MAPPINGS);
        hashMap4.putAll(hashMap2);
        ALPHA_PHONE_MAPPINGS = Collections.unmodifiableMap(hashMap4);
        HashMap hashMap5 = new HashMap();
        hashMap5.putAll(hashMap2);
        hashMap5.put(GeneratedOutlineSupport.outline3('*', hashMap5, GeneratedOutlineSupport.outline3('+', hashMap5, '+', '*'), '#'), '#');
        DIALLABLE_CHAR_MAPPINGS = Collections.unmodifiableMap(hashMap5);
        HashMap hashMap6 = new HashMap();
        for (Character charValue : ALPHA_MAPPINGS.keySet()) {
            char charValue2 = charValue.charValue();
            hashMap6.put(GeneratedOutlineSupport.outline3(charValue2, hashMap6, Character.valueOf(Character.toLowerCase(charValue2)), charValue2), Character.valueOf(charValue2));
        }
        hashMap6.putAll(hashMap2);
        hashMap6.put(GeneratedOutlineSupport.outline3('.', hashMap6, GeneratedOutlineSupport.outline3(' ', hashMap6, GeneratedOutlineSupport.outline3(' ', hashMap6, GeneratedOutlineSupport.outline3(' ', hashMap6, GeneratedOutlineSupport.outline3('/', hashMap6, GeneratedOutlineSupport.outline3('/', hashMap6, GeneratedOutlineSupport.outline3('-', hashMap6, GeneratedOutlineSupport.outline3('-', hashMap6, GeneratedOutlineSupport.outline3('-', hashMap6, GeneratedOutlineSupport.outline3('-', hashMap6, GeneratedOutlineSupport.outline3('-', hashMap6, GeneratedOutlineSupport.outline3('-', hashMap6, GeneratedOutlineSupport.outline3('-', hashMap6, GeneratedOutlineSupport.outline3('-', hashMap6, GeneratedOutlineSupport.outline3('-', hashMap6, '-', 65293), 8208), 8209), 8210), 8211), 8212), 8213), 8722), '/'), 65295), ' '), 12288), 8288), '.'), 65294), '.');
        Collections.unmodifiableMap(hashMap6);
        Pattern.compile("[\\d]+(?:[~⁓∼～][\\d]+)?");
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("\\p{Nd}{2}|[+＋]*+(?:[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～*]*\\p{Nd}){3,}[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～*");
        outline13.append(VALID_ALPHA);
        outline13.append("\\p{Nd}");
        outline13.append("]*");
        VALID_PHONE_NUMBER = outline13.toString();
        createExtnPattern("xｘ#＃~～");
        StringBuilder outline132 = GeneratedOutlineSupport.outline13("(?:");
        outline132.append(EXTN_PATTERNS_FOR_PARSING);
        outline132.append(")$");
        EXTN_PATTERN = Pattern.compile(outline132.toString(), 66);
        Pattern.compile("(\\D+)");
        Pattern.compile("\\(?\\$1\\)?");
    }

    PhoneNumberUtil(MultiFileMetadataSourceImpl multiFileMetadataSourceImpl, Map<Integer, List<String>> map) {
        this.metadataSource = multiFileMetadataSourceImpl;
        this.countryCallingCodeToRegionCodeMap = map;
        for (Map.Entry next : map.entrySet()) {
            List list = (List) next.getValue();
            if (list.size() != 1 || !"001".equals(list.get(0))) {
                this.supportedRegions.addAll(list);
            } else {
                this.countryCodesForNonGeographicalRegion.add(next.getKey());
            }
        }
        if (this.supportedRegions.remove("001")) {
            logger.log(Level.WARNING, "invalid metadata (country calling code was mapped to the non-geo entity as well as specific region(s))");
        }
        this.nanpaRegions.addAll(map.get(1));
    }

    private static Phonenumber$PhoneNumber copyCoreFieldsOnly(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        Phonenumber$PhoneNumber phonenumber$PhoneNumber2 = new Phonenumber$PhoneNumber();
        phonenumber$PhoneNumber2.setCountryCode(phonenumber$PhoneNumber.getCountryCode());
        phonenumber$PhoneNumber2.setNationalNumber(phonenumber$PhoneNumber.getNationalNumber());
        if (phonenumber$PhoneNumber.getExtension().length() > 0) {
            phonenumber$PhoneNumber2.setExtension(phonenumber$PhoneNumber.getExtension());
        }
        if (phonenumber$PhoneNumber.isItalianLeadingZero()) {
            phonenumber$PhoneNumber2.setItalianLeadingZero(true);
            phonenumber$PhoneNumber2.setNumberOfLeadingZeros(phonenumber$PhoneNumber.getNumberOfLeadingZeros());
        }
        return phonenumber$PhoneNumber2;
    }

    private static String createExtnPattern(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(";ext=(\\p{Nd}{1,7})|[  \\t,]*(?:e?xt(?:ensi(?:ó?|ó))?n?|ｅ?ｘｔｎ?|доб|[");
        sb.append(str);
        sb.append("]|int|anexo|ｉｎｔ)[:\\.．]?[  \\t,-]*");
        sb.append("(\\p{Nd}{1,7})");
        sb.append("#?|[- ]+(");
        return GeneratedOutlineSupport.outline12(sb, "\\p{Nd}", "{1,5})#");
    }

    private static boolean descHasPossibleNumberData(Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDesc) {
        return (phonemetadata$PhoneNumberDesc.getPossibleLengthCount() == 1 && phonemetadata$PhoneNumberDesc.getPossibleLength(0) == -1) ? false : true;
    }

    private String formatNsn(String str, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, PhoneNumberFormat phoneNumberFormat, CharSequence charSequence) {
        List<Phonemetadata$NumberFormat> list;
        Phonemetadata$NumberFormat phonemetadata$NumberFormat;
        String str2;
        if (phonemetadata$PhoneMetadata.intlNumberFormats().size() == 0 || phoneNumberFormat == PhoneNumberFormat.NATIONAL) {
            list = phonemetadata$PhoneMetadata.numberFormats();
        } else {
            list = phonemetadata$PhoneMetadata.intlNumberFormats();
        }
        Iterator<Phonemetadata$NumberFormat> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                phonemetadata$NumberFormat = null;
                break;
            }
            phonemetadata$NumberFormat = it.next();
            int leadingDigitsPatternSize = phonemetadata$NumberFormat.leadingDigitsPatternSize();
            if ((leadingDigitsPatternSize == 0 || this.regexCache.getPatternForRegex(phonemetadata$NumberFormat.getLeadingDigitsPattern(leadingDigitsPatternSize - 1)).matcher(str).lookingAt()) && this.regexCache.getPatternForRegex(phonemetadata$NumberFormat.getPattern()).matcher(str).matches()) {
                break;
            }
        }
        if (phonemetadata$NumberFormat == null) {
            return str;
        }
        String format = phonemetadata$NumberFormat.getFormat();
        Matcher matcher = this.regexCache.getPatternForRegex(phonemetadata$NumberFormat.getPattern()).matcher(str);
        if (phoneNumberFormat != PhoneNumberFormat.NATIONAL || charSequence == null || charSequence.length() <= 0 || phonemetadata$NumberFormat.getDomesticCarrierCodeFormattingRule().length() <= 0) {
            String nationalPrefixFormattingRule = phonemetadata$NumberFormat.getNationalPrefixFormattingRule();
            if (phoneNumberFormat != PhoneNumberFormat.NATIONAL || nationalPrefixFormattingRule == null || nationalPrefixFormattingRule.length() <= 0) {
                str2 = matcher.replaceAll(format);
            } else {
                str2 = matcher.replaceAll(FIRST_GROUP_PATTERN.matcher(format).replaceFirst(nationalPrefixFormattingRule));
            }
        } else {
            str2 = matcher.replaceAll(FIRST_GROUP_PATTERN.matcher(format).replaceFirst(phonemetadata$NumberFormat.getDomesticCarrierCodeFormattingRule().replace("$CC", charSequence)));
        }
        if (phoneNumberFormat == PhoneNumberFormat.RFC3966) {
            Matcher matcher2 = SEPARATOR_PATTERN.matcher(str2);
            if (matcher2.lookingAt()) {
                str2 = matcher2.replaceFirst("");
            }
            str2 = matcher2.reset(str2).replaceAll("-");
        }
        return str2;
    }

    public static String getCountryMobileToken(int i) {
        return MOBILE_TOKEN_MAPPINGS.containsKey(Integer.valueOf(i)) ? MOBILE_TOKEN_MAPPINGS.get(Integer.valueOf(i)) : "";
    }

    public static synchronized PhoneNumberUtil getInstance() {
        PhoneNumberUtil phoneNumberUtil;
        synchronized (PhoneNumberUtil.class) {
            if (instance == null) {
                MetadataLoader metadataLoader = MetadataManager.DEFAULT_METADATA_LOADER;
                if (metadataLoader != null) {
                    setInstance(new PhoneNumberUtil(new MultiFileMetadataSourceImpl(metadataLoader), AlternateFormatsCountryCodeSet.getCountryCodeToRegionCodeMap()));
                } else {
                    throw new IllegalArgumentException("metadataLoader could not be null.");
                }
            }
            phoneNumberUtil = instance;
        }
        return phoneNumberUtil;
    }

    private Phonemetadata$PhoneMetadata getMetadataForRegionOrCallingCode(int i, String str) {
        if (!"001".equals(str)) {
            return getMetadataForRegion(str);
        }
        if (!this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(i))) {
            return null;
        }
        return this.metadataSource.getMetadataForNonGeographicalRegion(i);
    }

    private PhoneNumberType getNumberTypeHelper(String str, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata) {
        if (!isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getGeneralDesc())) {
            return PhoneNumberType.UNKNOWN;
        }
        if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getPremiumRate())) {
            return PhoneNumberType.PREMIUM_RATE;
        }
        if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getTollFree())) {
            return PhoneNumberType.TOLL_FREE;
        }
        if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getSharedCost())) {
            return PhoneNumberType.SHARED_COST;
        }
        if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getVoip())) {
            return PhoneNumberType.VOIP;
        }
        if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getPersonalNumber())) {
            return PhoneNumberType.PERSONAL_NUMBER;
        }
        if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getPager())) {
            return PhoneNumberType.PAGER;
        }
        if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getUan())) {
            return PhoneNumberType.UAN;
        }
        if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getVoicemail())) {
            return PhoneNumberType.VOICEMAIL;
        }
        if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getFixedLine())) {
            if (phonemetadata$PhoneMetadata.getSameMobileAndFixedLinePattern()) {
                return PhoneNumberType.FIXED_LINE_OR_MOBILE;
            }
            if (isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getMobile())) {
                return PhoneNumberType.FIXED_LINE_OR_MOBILE;
            }
            return PhoneNumberType.FIXED_LINE;
        } else if (phonemetadata$PhoneMetadata.getSameMobileAndFixedLinePattern() || !isNumberMatchingDesc(str, phonemetadata$PhoneMetadata.getMobile())) {
            return PhoneNumberType.UNKNOWN;
        } else {
            return PhoneNumberType.MOBILE;
        }
    }

    private boolean hasValidCountryCallingCode(int i) {
        return this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(i));
    }

    private boolean isNationalNumberSuffixOfTheOther(Phonenumber$PhoneNumber phonenumber$PhoneNumber, Phonenumber$PhoneNumber phonenumber$PhoneNumber2) {
        String valueOf = String.valueOf(phonenumber$PhoneNumber.getNationalNumber());
        String valueOf2 = String.valueOf(phonenumber$PhoneNumber2.getNationalNumber());
        return valueOf.endsWith(valueOf2) || valueOf2.endsWith(valueOf);
    }

    private boolean isValidRegionCode(String str) {
        return str != null && this.supportedRegions.contains(str);
    }

    static boolean isViablePhoneNumber(CharSequence charSequence) {
        if (charSequence.length() < 2) {
            return false;
        }
        return VALID_PHONE_NUMBER_PATTERN.matcher(charSequence).matches();
    }

    private void maybeAppendFormattedExtension(Phonenumber$PhoneNumber phonenumber$PhoneNumber, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        if (phonenumber$PhoneNumber.hasExtension() && phonenumber$PhoneNumber.getExtension().length() > 0) {
            if (phoneNumberFormat == PhoneNumberFormat.RFC3966) {
                sb.append(";ext=");
                sb.append(phonenumber$PhoneNumber.getExtension());
            } else if (phonemetadata$PhoneMetadata.hasPreferredExtnPrefix()) {
                sb.append(phonemetadata$PhoneMetadata.getPreferredExtnPrefix());
                sb.append(phonenumber$PhoneNumber.getExtension());
            } else {
                sb.append(" ext. ");
                sb.append(phonenumber$PhoneNumber.getExtension());
            }
        }
    }

    static StringBuilder normalize(StringBuilder sb) {
        if (VALID_ALPHA_PHONE_PATTERN.matcher(sb).matches()) {
            sb.replace(0, sb.length(), normalizeHelper(sb, ALPHA_PHONE_MAPPINGS, true));
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

    private static String normalizeHelper(CharSequence charSequence, Map<Character, Character> map, boolean z) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        for (int i = 0; i < charSequence.length(); i++) {
            char charAt = charSequence.charAt(i);
            Character ch = map.get(Character.valueOf(Character.toUpperCase(charAt)));
            if (ch != null) {
                sb.append(ch);
            } else if (!z) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    private void parseHelper(CharSequence charSequence, String str, boolean z, boolean z2, Phonenumber$PhoneNumber phonenumber$PhoneNumber) throws NumberParseException {
        int i;
        CharSequence charSequence2;
        String str2 = str;
        Phonenumber$PhoneNumber phonenumber$PhoneNumber2 = phonenumber$PhoneNumber;
        if (charSequence == null) {
            throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The phone number supplied was null.");
        } else if (charSequence.length() <= 250) {
            StringBuilder sb = new StringBuilder();
            String charSequence3 = charSequence.toString();
            int indexOf = charSequence3.indexOf(";phone-context=");
            String str3 = "";
            if (indexOf >= 0) {
                int i2 = indexOf + 15;
                if (i2 < charSequence3.length() - 1 && charSequence3.charAt(i2) == '+') {
                    int indexOf2 = charSequence3.indexOf(59, i2);
                    if (indexOf2 > 0) {
                        sb.append(charSequence3.substring(i2, indexOf2));
                    } else {
                        sb.append(charSequence3.substring(i2));
                    }
                }
                int indexOf3 = charSequence3.indexOf("tel:");
                sb.append(charSequence3.substring(indexOf3 >= 0 ? indexOf3 + 4 : 0, indexOf));
            } else {
                Matcher matcher = VALID_START_CHAR_PATTERN.matcher(charSequence3);
                if (matcher.find()) {
                    charSequence2 = charSequence3.subSequence(matcher.start(), charSequence3.length());
                    Matcher matcher2 = UNWANTED_END_CHAR_PATTERN.matcher(charSequence2);
                    if (matcher2.find()) {
                        charSequence2 = charSequence2.subSequence(0, matcher2.start());
                    }
                    Matcher matcher3 = SECOND_NUMBER_START_PATTERN.matcher(charSequence2);
                    if (matcher3.find()) {
                        charSequence2 = charSequence2.subSequence(0, matcher3.start());
                    }
                } else {
                    charSequence2 = str3;
                }
                sb.append(charSequence2);
            }
            int indexOf4 = sb.indexOf(";isub=");
            if (indexOf4 > 0) {
                sb.delete(indexOf4, sb.length());
            }
            if (isViablePhoneNumber(sb)) {
                if (z2) {
                    if (!(isValidRegionCode(str2) || (sb.length() != 0 && PLUS_CHARS_PATTERN.matcher(sb).lookingAt()))) {
                        throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Missing or invalid default region.");
                    }
                }
                if (z) {
                    phonenumber$PhoneNumber2.setRawInput(charSequence3);
                }
                Matcher matcher4 = EXTN_PATTERN.matcher(sb);
                if (matcher4.find() && isViablePhoneNumber(sb.substring(0, matcher4.start()))) {
                    int groupCount = matcher4.groupCount();
                    int i3 = 1;
                    while (true) {
                        if (i3 > groupCount) {
                            break;
                        } else if (matcher4.group(i3) != null) {
                            str3 = matcher4.group(i3);
                            sb.delete(matcher4.start(), sb.length());
                            break;
                        } else {
                            i3++;
                        }
                    }
                }
                if (str3.length() > 0) {
                    phonenumber$PhoneNumber2.setExtension(str3);
                }
                Phonemetadata$PhoneMetadata metadataForRegion = getMetadataForRegion(str2);
                StringBuilder sb2 = new StringBuilder();
                try {
                    i = maybeExtractCountryCode(sb, metadataForRegion, sb2, z, phonenumber$PhoneNumber);
                } catch (NumberParseException e) {
                    NumberParseException numberParseException = e;
                    Matcher matcher5 = PLUS_CHARS_PATTERN.matcher(sb);
                    if (numberParseException.getErrorType() != NumberParseException.ErrorType.INVALID_COUNTRY_CODE || !matcher5.lookingAt()) {
                        throw new NumberParseException(numberParseException.getErrorType(), numberParseException.getMessage());
                    }
                    i = maybeExtractCountryCode(sb.substring(matcher5.end()), metadataForRegion, sb2, z, phonenumber$PhoneNumber);
                    if (i == 0) {
                        throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Could not interpret numbers after plus-sign.");
                    }
                }
                if (i != 0) {
                    String regionCodeForCountryCode = getRegionCodeForCountryCode(i);
                    if (!regionCodeForCountryCode.equals(str2)) {
                        metadataForRegion = getMetadataForRegionOrCallingCode(i, regionCodeForCountryCode);
                    }
                } else {
                    normalize(sb);
                    sb2.append(sb);
                    if (str2 != null) {
                        phonenumber$PhoneNumber2.setCountryCode(metadataForRegion.getCountryCode());
                    } else if (z) {
                        phonenumber$PhoneNumber.clearCountryCodeSource();
                    }
                }
                if (sb2.length() >= 2) {
                    if (metadataForRegion != null) {
                        StringBuilder sb3 = new StringBuilder();
                        StringBuilder sb4 = new StringBuilder(sb2);
                        maybeStripNationalPrefixAndCarrierCode(sb4, metadataForRegion, sb3);
                        ValidationResult testNumberLength = testNumberLength(sb4, metadataForRegion);
                        if (!(testNumberLength == ValidationResult.TOO_SHORT || testNumberLength == ValidationResult.IS_POSSIBLE_LOCAL_ONLY || testNumberLength == ValidationResult.INVALID_LENGTH)) {
                            if (z && sb3.length() > 0) {
                                phonenumber$PhoneNumber2.setPreferredDomesticCarrierCode(sb3.toString());
                            }
                            sb2 = sb4;
                        }
                    }
                    int length = sb2.length();
                    if (length < 2) {
                        throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
                    } else if (length <= 17) {
                        if (sb2.length() > 1 && sb2.charAt(0) == '0') {
                            phonenumber$PhoneNumber2.setItalianLeadingZero(true);
                            int i4 = 1;
                            while (i4 < sb2.length() - 1 && sb2.charAt(i4) == '0') {
                                i4++;
                            }
                            if (i4 != 1) {
                                phonenumber$PhoneNumber2.setNumberOfLeadingZeros(i4);
                            }
                        }
                        phonenumber$PhoneNumber2.setNationalNumber(Long.parseLong(sb2.toString()));
                    } else {
                        throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied is too long to be a phone number.");
                    }
                } else {
                    throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
                }
            } else {
                throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The string supplied did not seem to be a phone number.");
            }
        } else {
            throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied was too long to parse.");
        }
    }

    private void prefixNumberWithCountryCallingCode(int i, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        int ordinal = phoneNumberFormat.ordinal();
        if (ordinal == 0) {
            sb.insert(0, i).insert(0, '+');
        } else if (ordinal == 1) {
            sb.insert(0, " ").insert(0, i).insert(0, '+');
        } else if (ordinal == 3) {
            sb.insert(0, "-").insert(0, i).insert(0, '+').insert(0, "tel:");
        }
    }

    static synchronized void setInstance(PhoneNumberUtil phoneNumberUtil) {
        synchronized (PhoneNumberUtil.class) {
            instance = phoneNumberUtil;
        }
    }

    private ValidationResult testNumberLength(CharSequence charSequence, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata) {
        return testNumberLength(charSequence, phonemetadata$PhoneMetadata, PhoneNumberType.UNKNOWN);
    }

    public boolean canBeInternationallyDialled(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        Phonemetadata$PhoneMetadata metadataForRegion = getMetadataForRegion(getRegionCodeForNumber(phonenumber$PhoneNumber));
        if (metadataForRegion == null) {
            return true;
        }
        return !isNumberMatchingDesc(getNationalSignificantNumber(phonenumber$PhoneNumber), metadataForRegion.getNoInternationalDialling());
    }

    public String format(Phonenumber$PhoneNumber phonenumber$PhoneNumber, PhoneNumberFormat phoneNumberFormat) {
        if (phonenumber$PhoneNumber.getNationalNumber() == 0 && phonenumber$PhoneNumber.hasRawInput()) {
            String rawInput = phonenumber$PhoneNumber.getRawInput();
            if (rawInput.length() > 0) {
                return rawInput;
            }
        }
        StringBuilder sb = new StringBuilder(20);
        sb.setLength(0);
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phonenumber$PhoneNumber);
        if (phoneNumberFormat == PhoneNumberFormat.E164) {
            sb.append(nationalSignificantNumber);
            prefixNumberWithCountryCallingCode(countryCode, PhoneNumberFormat.E164, sb);
        } else if (!hasValidCountryCallingCode(countryCode)) {
            sb.append(nationalSignificantNumber);
        } else {
            Phonemetadata$PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
            sb.append(formatNsn(nationalSignificantNumber, metadataForRegionOrCallingCode, phoneNumberFormat, (CharSequence) null));
            maybeAppendFormattedExtension(phonenumber$PhoneNumber, metadataForRegionOrCallingCode, phoneNumberFormat, sb);
            prefixNumberWithCountryCallingCode(countryCode, phoneNumberFormat, sb);
        }
        return sb.toString();
    }

    public String formatNationalNumberWithCarrierCode(Phonenumber$PhoneNumber phonenumber$PhoneNumber, CharSequence charSequence) {
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phonenumber$PhoneNumber);
        if (!hasValidCountryCallingCode(countryCode)) {
            return nationalSignificantNumber;
        }
        Phonemetadata$PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, getRegionCodeForCountryCode(countryCode));
        StringBuilder sb = new StringBuilder(20);
        sb.append(formatNsn(nationalSignificantNumber, metadataForRegionOrCallingCode, PhoneNumberFormat.NATIONAL, charSequence));
        maybeAppendFormattedExtension(phonenumber$PhoneNumber, metadataForRegionOrCallingCode, PhoneNumberFormat.NATIONAL, sb);
        prefixNumberWithCountryCallingCode(countryCode, PhoneNumberFormat.NATIONAL, sb);
        return sb.toString();
    }

    public String formatNumberForMobileDialing(Phonenumber$PhoneNumber phonenumber$PhoneNumber, String str, boolean z) {
        String format;
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        String str2 = "";
        if (!hasValidCountryCallingCode(countryCode)) {
            return phonenumber$PhoneNumber.hasRawInput() ? phonenumber$PhoneNumber.getRawInput() : str2;
        }
        Phonenumber$PhoneNumber clearExtension = new Phonenumber$PhoneNumber().mergeFrom(phonenumber$PhoneNumber).clearExtension();
        String regionCodeForCountryCode = getRegionCodeForCountryCode(countryCode);
        PhoneNumberType numberType = getNumberType(clearExtension);
        boolean z2 = false;
        boolean z3 = numberType != PhoneNumberType.UNKNOWN;
        if (str.equals(regionCodeForCountryCode)) {
            if (numberType == PhoneNumberType.FIXED_LINE || numberType == PhoneNumberType.MOBILE || numberType == PhoneNumberType.FIXED_LINE_OR_MOBILE) {
                z2 = true;
            }
            if (regionCodeForCountryCode.equals("CO") && numberType == PhoneNumberType.FIXED_LINE) {
                format = formatNationalNumberWithCarrierCode(clearExtension, "3");
            } else if (!regionCodeForCountryCode.equals("BR") || !z2) {
                if (z3 && regionCodeForCountryCode.equals("HU")) {
                    StringBuilder sb = new StringBuilder();
                    Phonemetadata$PhoneMetadata metadataForRegion = getMetadataForRegion(regionCodeForCountryCode);
                    String str3 = null;
                    if (metadataForRegion == null) {
                        logger.log(Level.WARNING, "Invalid or missing region code (" + regionCodeForCountryCode + ") provided.");
                    } else {
                        String nationalPrefix = metadataForRegion.getNationalPrefix();
                        if (nationalPrefix.length() != 0) {
                            str3 = nationalPrefix.replace("~", str2);
                        }
                    }
                    sb.append(str3);
                    sb.append(" ");
                    sb.append(format(clearExtension, PhoneNumberFormat.NATIONAL));
                    format = sb.toString();
                } else if (countryCode == 1) {
                    Phonemetadata$PhoneMetadata metadataForRegion2 = getMetadataForRegion(str);
                    if (!canBeInternationallyDialled(clearExtension) || testNumberLength(getNationalSignificantNumber(clearExtension), metadataForRegion2) == ValidationResult.TOO_SHORT) {
                        format = format(clearExtension, PhoneNumberFormat.NATIONAL);
                    } else {
                        format = format(clearExtension, PhoneNumberFormat.INTERNATIONAL);
                    }
                } else if ((regionCodeForCountryCode.equals("001") || ((regionCodeForCountryCode.equals("MX") || regionCodeForCountryCode.equals("CL") || regionCodeForCountryCode.equals("UZ")) && z2)) && canBeInternationallyDialled(clearExtension)) {
                    format = format(clearExtension, PhoneNumberFormat.INTERNATIONAL);
                } else {
                    format = format(clearExtension, PhoneNumberFormat.NATIONAL);
                }
            } else if (clearExtension.getPreferredDomesticCarrierCode().length() > 0) {
                if (clearExtension.getPreferredDomesticCarrierCode().length() > 0) {
                    str2 = clearExtension.getPreferredDomesticCarrierCode();
                }
                str2 = formatNationalNumberWithCarrierCode(clearExtension, str2);
            }
            str2 = format;
        } else if (z3 && canBeInternationallyDialled(clearExtension)) {
            if (z) {
                return format(clearExtension, PhoneNumberFormat.INTERNATIONAL);
            }
            return format(clearExtension, PhoneNumberFormat.E164);
        }
        return z ? str2 : normalizeHelper(str2, DIALLABLE_CHAR_MAPPINGS, true);
    }

    /* access modifiers changed from: package-private */
    public Phonemetadata$PhoneMetadata getMetadataForRegion(String str) {
        if (!(str != null && this.supportedRegions.contains(str))) {
            return null;
        }
        return this.metadataSource.getMetadataForRegion(str);
    }

    public String getNationalSignificantNumber(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        StringBuilder sb = new StringBuilder();
        if (phonenumber$PhoneNumber.isItalianLeadingZero() && phonenumber$PhoneNumber.getNumberOfLeadingZeros() > 0) {
            char[] cArr = new char[phonenumber$PhoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            sb.append(new String(cArr));
        }
        sb.append(phonenumber$PhoneNumber.getNationalNumber());
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public Phonemetadata$PhoneNumberDesc getNumberDescByType(Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, PhoneNumberType phoneNumberType) {
        switch (phoneNumberType.ordinal()) {
            case 0:
            case 2:
                return phonemetadata$PhoneMetadata.getFixedLine();
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
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

    public PhoneNumberType getNumberType(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        Phonemetadata$PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(phonenumber$PhoneNumber.getCountryCode(), getRegionCodeForNumber(phonenumber$PhoneNumber));
        if (metadataForRegionOrCallingCode == null) {
            return PhoneNumberType.UNKNOWN;
        }
        return getNumberTypeHelper(getNationalSignificantNumber(phonenumber$PhoneNumber), metadataForRegionOrCallingCode);
    }

    public String getRegionCodeForCountryCode(int i) {
        List list = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(i));
        if (list == null) {
            return "ZZ";
        }
        return (String) list.get(0);
    }

    public String getRegionCodeForNumber(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        List<String> list = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(countryCode));
        if (list == null) {
            Logger logger2 = logger;
            Level level = Level.INFO;
            logger2.log(level, "Missing/invalid country_code (" + countryCode + ")");
            return null;
        } else if (list.size() == 1) {
            return (String) list.get(0);
        } else {
            String nationalSignificantNumber = getNationalSignificantNumber(phonenumber$PhoneNumber);
            for (String str : list) {
                Phonemetadata$PhoneMetadata metadataForRegion = getMetadataForRegion(str);
                if (metadataForRegion.hasLeadingDigits()) {
                    if (this.regexCache.getPatternForRegex(metadataForRegion.getLeadingDigits()).matcher(nationalSignificantNumber).lookingAt()) {
                        return str;
                    }
                } else if (getNumberTypeHelper(nationalSignificantNumber, metadataForRegion) != PhoneNumberType.UNKNOWN) {
                    return str;
                }
            }
            return null;
        }
    }

    public List<String> getRegionCodesForCountryCode(int i) {
        List list = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList(0);
        }
        return Collections.unmodifiableList(list);
    }

    public boolean isNumberGeographical(PhoneNumberType phoneNumberType, int i) {
        return phoneNumberType == PhoneNumberType.FIXED_LINE || phoneNumberType == PhoneNumberType.FIXED_LINE_OR_MOBILE || (GEO_MOBILE_COUNTRIES.contains(Integer.valueOf(i)) && phoneNumberType == PhoneNumberType.MOBILE);
    }

    public MatchType isNumberMatch(Phonenumber$PhoneNumber phonenumber$PhoneNumber, Phonenumber$PhoneNumber phonenumber$PhoneNumber2) {
        Phonenumber$PhoneNumber copyCoreFieldsOnly = copyCoreFieldsOnly(phonenumber$PhoneNumber);
        Phonenumber$PhoneNumber copyCoreFieldsOnly2 = copyCoreFieldsOnly(phonenumber$PhoneNumber2);
        if (copyCoreFieldsOnly.hasExtension() && copyCoreFieldsOnly2.hasExtension() && !copyCoreFieldsOnly.getExtension().equals(copyCoreFieldsOnly2.getExtension())) {
            return MatchType.NO_MATCH;
        }
        int countryCode = copyCoreFieldsOnly.getCountryCode();
        int countryCode2 = copyCoreFieldsOnly2.getCountryCode();
        if (countryCode == 0 || countryCode2 == 0) {
            copyCoreFieldsOnly.setCountryCode(countryCode2);
            if (copyCoreFieldsOnly.exactlySameAs(copyCoreFieldsOnly2)) {
                return MatchType.NSN_MATCH;
            }
            if (isNationalNumberSuffixOfTheOther(copyCoreFieldsOnly, copyCoreFieldsOnly2)) {
                return MatchType.SHORT_NSN_MATCH;
            }
            return MatchType.NO_MATCH;
        } else if (copyCoreFieldsOnly.exactlySameAs(copyCoreFieldsOnly2)) {
            return MatchType.EXACT_MATCH;
        } else {
            if (countryCode != countryCode2 || !isNationalNumberSuffixOfTheOther(copyCoreFieldsOnly, copyCoreFieldsOnly2)) {
                return MatchType.NO_MATCH;
            }
            return MatchType.SHORT_NSN_MATCH;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isNumberMatchingDesc(String str, Phonemetadata$PhoneNumberDesc phonemetadata$PhoneNumberDesc) {
        int length = str.length();
        List<Integer> possibleLengthList = phonemetadata$PhoneNumberDesc.getPossibleLengthList();
        if (possibleLengthList.size() <= 0 || possibleLengthList.contains(Integer.valueOf(length))) {
            return this.matcherApi.matchNationalNumber(str, phonemetadata$PhoneNumberDesc, false);
        }
        return false;
    }

    public boolean isValidNumber(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        return isValidNumberForRegion(phonenumber$PhoneNumber, getRegionCodeForNumber(phonenumber$PhoneNumber));
    }

    public boolean isValidNumberForRegion(Phonenumber$PhoneNumber phonenumber$PhoneNumber, String str) {
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        Phonemetadata$PhoneMetadata metadataForRegionOrCallingCode = getMetadataForRegionOrCallingCode(countryCode, str);
        if (metadataForRegionOrCallingCode == null) {
            return false;
        }
        if (!"001".equals(str)) {
            Phonemetadata$PhoneMetadata metadataForRegion = getMetadataForRegion(str);
            if (metadataForRegion == null) {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid region code: ", str));
            } else if (countryCode != metadataForRegion.getCountryCode()) {
                return false;
            }
        }
        if (getNumberTypeHelper(getNationalSignificantNumber(phonenumber$PhoneNumber), metadataForRegionOrCallingCode) != PhoneNumberType.UNKNOWN) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int maybeExtractCountryCode(java.lang.CharSequence r6, com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r7, java.lang.StringBuilder r8, boolean r9, com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r10) throws com.google.i18n.phonenumbers.NumberParseException {
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
            com.google.i18n.phonenumbers.internal.RegexCache r2 = r5.regexCache
            java.util.regex.Pattern r6 = r2.getPatternForRegex(r6)
            normalize(r0)
            java.util.regex.Matcher r6 = r6.matcher(r0)
            boolean r2 = r6.lookingAt()
            if (r2 == 0) goto L_0x0076
            int r6 = r6.end()
            java.util.regex.Pattern r2 = CAPTURING_DIGIT_PATTERN
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
            r10.setCountryCodeSource(r6)
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
            java.util.Map<java.lang.Integer, java.util.List<java.lang.String>> r9 = r5.countryCallingCodeToRegionCodeMap
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
            if (r7 == 0) goto L_0x012f
            int r6 = r7.getCountryCode()
            java.lang.String r2 = java.lang.String.valueOf(r6)
            java.lang.String r3 = r0.toString()
            boolean r4 = r3.startsWith(r2)
            if (r4 == 0) goto L_0x012f
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r2 = r2.length()
            java.lang.String r2 = r3.substring(r2)
            r4.<init>(r2)
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r2 = r7.getGeneralDesc()
            r3 = 0
            r5.maybeStripNationalPrefixAndCarrierCode(r4, r7, r3)
            com.google.i18n.phonenumbers.internal.RegexBasedMatcher r3 = r5.matcherApi
            boolean r3 = r3.matchNationalNumber(r0, r2, r1)
            if (r3 != 0) goto L_0x0119
            com.google.i18n.phonenumbers.internal.RegexBasedMatcher r3 = r5.matcherApi
            boolean r2 = r3.matchNationalNumber(r4, r2, r1)
            if (r2 != 0) goto L_0x0121
        L_0x0119:
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r5 = r5.testNumberLength(r0, r7)
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r7 = com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult.TOO_LONG
            if (r5 != r7) goto L_0x012f
        L_0x0121:
            r8.append(r4)
            if (r9 == 0) goto L_0x012b
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber$CountryCodeSource r5 = com.google.i18n.phonenumbers.Phonenumber$PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN
            r10.setCountryCodeSource(r5)
        L_0x012b:
            r10.setCountryCode(r6)
            return r6
        L_0x012f:
            r10.setCountryCode(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.PhoneNumberUtil.maybeExtractCountryCode(java.lang.CharSequence, com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata, java.lang.StringBuilder, boolean, com.google.i18n.phonenumbers.Phonenumber$PhoneNumber):int");
    }

    /* access modifiers changed from: package-private */
    public boolean maybeStripNationalPrefixAndCarrierCode(StringBuilder sb, Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata, StringBuilder sb2) {
        int length = sb.length();
        String nationalPrefixForParsing = phonemetadata$PhoneMetadata.getNationalPrefixForParsing();
        if (!(length == 0 || nationalPrefixForParsing.length() == 0)) {
            Matcher matcher = this.regexCache.getPatternForRegex(nationalPrefixForParsing).matcher(sb);
            if (matcher.lookingAt()) {
                Phonemetadata$PhoneNumberDesc generalDesc = phonemetadata$PhoneMetadata.getGeneralDesc();
                boolean matchNationalNumber = this.matcherApi.matchNationalNumber(sb, generalDesc, false);
                int groupCount = matcher.groupCount();
                String nationalPrefixTransformRule = phonemetadata$PhoneMetadata.getNationalPrefixTransformRule();
                if (nationalPrefixTransformRule != null && nationalPrefixTransformRule.length() != 0 && matcher.group(groupCount) != null) {
                    StringBuilder sb3 = new StringBuilder(sb);
                    sb3.replace(0, length, matcher.replaceFirst(nationalPrefixTransformRule));
                    if (matchNationalNumber && !this.matcherApi.matchNationalNumber(sb3.toString(), generalDesc, false)) {
                        return false;
                    }
                    if (sb2 != null && groupCount > 1) {
                        sb2.append(matcher.group(1));
                    }
                    sb.replace(0, sb.length(), sb3.toString());
                    return true;
                } else if (matchNationalNumber && !this.matcherApi.matchNationalNumber(sb.substring(matcher.end()), generalDesc, false)) {
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

    public Phonenumber$PhoneNumber parse(CharSequence charSequence, String str) throws NumberParseException {
        Phonenumber$PhoneNumber phonenumber$PhoneNumber = new Phonenumber$PhoneNumber();
        parseHelper(charSequence, str, false, true, phonenumber$PhoneNumber);
        return phonenumber$PhoneNumber;
    }

    public Phonenumber$PhoneNumber parseAndKeepRawInput(CharSequence charSequence, String str) throws NumberParseException {
        Phonenumber$PhoneNumber phonenumber$PhoneNumber = new Phonenumber$PhoneNumber();
        parseHelper(charSequence, str, true, true, phonenumber$PhoneNumber);
        return phonenumber$PhoneNumber;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult testNumberLength(java.lang.CharSequence r4, com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata r5, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType r6) {
        /*
            r3 = this;
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r0 = r3.getNumberDescByType(r5, r6)
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
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r2 = com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE
            if (r6 != r2) goto L_0x0080
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r6 = com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType.FIXED_LINE
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r6 = r3.getNumberDescByType(r5, r6)
            boolean r6 = descHasPossibleNumberData(r6)
            if (r6 != 0) goto L_0x0036
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r6 = com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType.MOBILE
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = r3.testNumberLength(r4, r5, r6)
            return r3
        L_0x0036:
            com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType r6 = com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType.MOBILE
            com.google.i18n.phonenumbers.Phonemetadata$PhoneNumberDesc r3 = r3.getNumberDescByType(r5, r6)
            boolean r6 = descHasPossibleNumberData(r3)
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
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult.INVALID_LENGTH
            return r3
        L_0x0092:
            int r4 = r4.length()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            boolean r5 = r0.contains(r5)
            if (r5 == 0) goto L_0x00a3
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult.IS_POSSIBLE_LOCAL_ONLY
            return r3
        L_0x00a3:
            java.lang.Object r3 = r6.get(r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            if (r3 != r4) goto L_0x00b2
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult.IS_POSSIBLE
            return r3
        L_0x00b2:
            if (r3 <= r4) goto L_0x00b7
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult.TOO_SHORT
            return r3
        L_0x00b7:
            int r3 = r6.size()
            r5 = 1
            int r3 = r3 - r5
            java.lang.Object r3 = r6.get(r3)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            if (r3 >= r4) goto L_0x00cc
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult.TOO_LONG
            return r3
        L_0x00cc:
            int r3 = r6.size()
            java.util.List r3 = r6.subList(r5, r3)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            boolean r3 = r3.contains(r4)
            if (r3 == 0) goto L_0x00e1
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult.IS_POSSIBLE
            goto L_0x00e3
        L_0x00e1:
            com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult r3 = com.google.i18n.phonenumbers.PhoneNumberUtil.ValidationResult.INVALID_LENGTH
        L_0x00e3:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.PhoneNumberUtil.testNumberLength(java.lang.CharSequence, com.google.i18n.phonenumbers.Phonemetadata$PhoneMetadata, com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberType):com.google.i18n.phonenumbers.PhoneNumberUtil$ValidationResult");
    }

    public MatchType isNumberMatch(CharSequence charSequence, CharSequence charSequence2) {
        try {
            return isNumberMatch(parse(charSequence, "ZZ"), charSequence2);
        } catch (NumberParseException e) {
            if (e.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                try {
                    return isNumberMatch(parse(charSequence2, "ZZ"), charSequence);
                } catch (NumberParseException e2) {
                    if (e2.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                        try {
                            Phonenumber$PhoneNumber phonenumber$PhoneNumber = new Phonenumber$PhoneNumber();
                            Phonenumber$PhoneNumber phonenumber$PhoneNumber2 = new Phonenumber$PhoneNumber();
                            parseHelper(charSequence, (String) null, false, false, phonenumber$PhoneNumber);
                            parseHelper(charSequence2, (String) null, false, false, phonenumber$PhoneNumber2);
                            return isNumberMatch(phonenumber$PhoneNumber, phonenumber$PhoneNumber2);
                        } catch (NumberParseException unused) {
                            return MatchType.NOT_A_NUMBER;
                        }
                    }
                    return MatchType.NOT_A_NUMBER;
                }
            }
            return MatchType.NOT_A_NUMBER;
        }
    }

    public MatchType isNumberMatch(Phonenumber$PhoneNumber phonenumber$PhoneNumber, CharSequence charSequence) {
        try {
            return isNumberMatch(phonenumber$PhoneNumber, parse(charSequence, "ZZ"));
        } catch (NumberParseException e) {
            if (e.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                String regionCodeForCountryCode = getRegionCodeForCountryCode(phonenumber$PhoneNumber.getCountryCode());
                try {
                    if (!regionCodeForCountryCode.equals("ZZ")) {
                        MatchType isNumberMatch = isNumberMatch(phonenumber$PhoneNumber, parse(charSequence, regionCodeForCountryCode));
                        return isNumberMatch == MatchType.EXACT_MATCH ? MatchType.NSN_MATCH : isNumberMatch;
                    }
                    Phonenumber$PhoneNumber phonenumber$PhoneNumber2 = new Phonenumber$PhoneNumber();
                    parseHelper(charSequence, (String) null, false, false, phonenumber$PhoneNumber2);
                    return isNumberMatch(phonenumber$PhoneNumber, phonenumber$PhoneNumber2);
                } catch (NumberParseException unused) {
                    return MatchType.NOT_A_NUMBER;
                }
            }
            return MatchType.NOT_A_NUMBER;
        }
    }
}
