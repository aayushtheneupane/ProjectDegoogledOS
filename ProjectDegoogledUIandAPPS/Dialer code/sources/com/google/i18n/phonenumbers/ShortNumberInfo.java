package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.internal.RegexBasedMatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class ShortNumberInfo {
    private static final ShortNumberInfo INSTANCE = new ShortNumberInfo(RegexBasedMatcher.create());
    private static final Set<String> REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT = new HashSet();
    private final Map<Integer, List<String>> countryCallingCodeToRegionCodeMap = AlternateFormatsCountryCodeSet.getCountryCodeToRegionCodeMap();

    static {
        Logger.getLogger(ShortNumberInfo.class.getName());
        REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT.add("BR");
        REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT.add("CL");
        REGIONS_WHERE_EMERGENCY_NUMBERS_MUST_BE_EXACT.add("NI");
    }

    ShortNumberInfo(RegexBasedMatcher regexBasedMatcher) {
    }

    public static ShortNumberInfo getInstance() {
        return INSTANCE;
    }

    public boolean isPossibleShortNumber(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        List list = this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(phonenumber$PhoneNumber.getCountryCode()));
        if (list == null) {
            list = new ArrayList(0);
        }
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        StringBuilder sb = new StringBuilder();
        if (phonenumber$PhoneNumber.isItalianLeadingZero()) {
            char[] cArr = new char[phonenumber$PhoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            sb.append(new String(cArr));
        }
        sb.append(phonenumber$PhoneNumber.getNationalNumber());
        int length = sb.toString().length();
        for (String shortNumberMetadataForRegion : unmodifiableList) {
            Phonemetadata$PhoneMetadata shortNumberMetadataForRegion2 = MetadataManager.getShortNumberMetadataForRegion(shortNumberMetadataForRegion);
            if (shortNumberMetadataForRegion2 != null && shortNumberMetadataForRegion2.getGeneralDesc().getPossibleLengthList().contains(Integer.valueOf(length))) {
                return true;
            }
        }
        return false;
    }
}
