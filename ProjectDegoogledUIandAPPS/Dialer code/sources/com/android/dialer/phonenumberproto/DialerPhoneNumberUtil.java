package com.android.dialer.phonenumberproto;

import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import com.google.i18n.phonenumbers.ShortNumberInfo;

public class DialerPhoneNumberUtil {
    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private final ShortNumberInfo shortNumberInfo = ShortNumberInfo.getInstance();

    public DialerPhoneNumberUtil() {
        Assert.isWorkerThread();
    }

    private boolean isServiceNumber(String str) {
        return str.contains("#") || str.startsWith("*");
    }

    public boolean isMatch(DialerPhoneNumber dialerPhoneNumber, DialerPhoneNumber dialerPhoneNumber2) {
        Phonenumber$PhoneNumber phonenumber$PhoneNumber;
        Assert.isWorkerThread();
        if (dialerPhoneNumber.getNormalizedNumber().isEmpty() || dialerPhoneNumber2.getNormalizedNumber().isEmpty() || !dialerPhoneNumber.getCountryIso().equals(dialerPhoneNumber2.getCountryIso())) {
            return false;
        }
        Phonenumber$PhoneNumber phonenumber$PhoneNumber2 = null;
        try {
            phonenumber$PhoneNumber = this.phoneNumberUtil.parse(dialerPhoneNumber.getNormalizedNumber(), dialerPhoneNumber.getCountryIso());
        } catch (NumberParseException unused) {
            phonenumber$PhoneNumber = null;
        }
        try {
            phonenumber$PhoneNumber2 = this.phoneNumberUtil.parse(dialerPhoneNumber2.getNormalizedNumber(), dialerPhoneNumber2.getCountryIso());
        } catch (NumberParseException unused2) {
        }
        if (isServiceNumber(dialerPhoneNumber.getNormalizedNumber()) || isServiceNumber(dialerPhoneNumber2.getNormalizedNumber()) || phonenumber$PhoneNumber == null || phonenumber$PhoneNumber2 == null) {
            return dialerPhoneNumber.getNormalizedNumber().equals(dialerPhoneNumber2.getNormalizedNumber());
        }
        if (this.shortNumberInfo.isPossibleShortNumber(phonenumber$PhoneNumber) || this.shortNumberInfo.isPossibleShortNumber(phonenumber$PhoneNumber2)) {
            return dialerPhoneNumber.getNormalizedNumber().equals(dialerPhoneNumber2.getNormalizedNumber());
        }
        PhoneNumberUtil.MatchType isNumberMatch = this.phoneNumberUtil.isNumberMatch(phonenumber$PhoneNumber, phonenumber$PhoneNumber2);
        if ((isNumberMatch == PhoneNumberUtil.MatchType.SHORT_NSN_MATCH || isNumberMatch == PhoneNumberUtil.MatchType.NSN_MATCH || isNumberMatch == PhoneNumberUtil.MatchType.EXACT_MATCH) && dialerPhoneNumber.getPostDialPortion().equals(dialerPhoneNumber2.getPostDialPortion())) {
            return true;
        }
        return false;
    }

    public DialerPhoneNumber parse(String str, String str2) {
        Assert.isWorkerThread();
        DialerPhoneNumber.Builder newBuilder = DialerPhoneNumber.newBuilder();
        if (str2 != null) {
            newBuilder.setCountryIso(str2);
        }
        if (str == null) {
            return (DialerPhoneNumber) newBuilder.build();
        }
        if (isServiceNumber(str)) {
            newBuilder.setNormalizedNumber(str);
            return (DialerPhoneNumber) newBuilder.build();
        }
        String extractPostDialPortion = PhoneNumberUtils.extractPostDialPortion(str);
        if (!extractPostDialPortion.isEmpty()) {
            newBuilder.setPostDialPortion(extractPostDialPortion);
        }
        String extractNetworkPortion = PhoneNumberUtils.extractNetworkPortion(str);
        try {
            Phonenumber$PhoneNumber parse = this.phoneNumberUtil.parse(extractNetworkPortion, str2);
            if (this.phoneNumberUtil.isValidNumber(parse)) {
                String format = this.phoneNumberUtil.format(parse, PhoneNumberUtil.PhoneNumberFormat.E164);
                if (!TextUtils.isEmpty(format)) {
                    if (!extractPostDialPortion.isEmpty()) {
                        format = format + extractPostDialPortion;
                    }
                    newBuilder.setNormalizedNumber(format);
                    newBuilder.setIsValid(true);
                    return (DialerPhoneNumber) newBuilder.build();
                }
                throw new IllegalStateException("e164 number should not be empty: " + LogUtil.sanitizePii(str));
            }
        } catch (NumberParseException unused) {
        }
        newBuilder.setNormalizedNumber(extractNetworkPortion + extractPostDialPortion);
        return (DialerPhoneNumber) newBuilder.build();
    }
}
