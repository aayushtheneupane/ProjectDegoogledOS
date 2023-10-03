package com.android.incallui;

import android.content.Context;
import android.text.TextUtils;
import com.android.dialer.R;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.incallui.call.DialerCall;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Arrays;

public class CallerInfoUtils {
    static CallerInfo buildCallerInfo(Context context, DialerCall dialerCall) {
        String str;
        CallerInfo callerInfo = new CallerInfo();
        callerInfo.cnapName = dialerCall.getCnapName();
        callerInfo.name = callerInfo.cnapName;
        callerInfo.numberPresentation = dialerCall.getNumberPresentation();
        dialerCall.getCnapNamePresentation();
        callerInfo.callSubject = dialerCall.getCallSubject();
        boolean z = false;
        callerInfo.contactExists = false;
        callerInfo.countryIso = PhoneNumberHelper.getCurrentCountryIso(context, dialerCall.getAccountHandle());
        String number = dialerCall.getNumber();
        if (!TextUtils.isEmpty(number)) {
            if (!PhoneNumberHelper.isUriNumber(number)) {
                String[] split = number.split("&");
                String str2 = split[0];
                if (split.length > 1) {
                    String str3 = split[1];
                }
                int i = callerInfo.numberPresentation;
                if (str2 != null) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("modifyForSpecialCnapCases: initially, number=");
                    outline13.append(toLogSafePhoneNumber(str2));
                    outline13.append(", presentation=");
                    outline13.append(i);
                    outline13.append(" ci ");
                    outline13.append(callerInfo);
                    outline13.toString();
                    if (Arrays.asList(context.getResources().getStringArray(R.array.absent_num)).contains(str2) && i == 1) {
                        str2 = context.getString(R.string.unknown);
                        callerInfo.numberPresentation = 3;
                    }
                    int i2 = callerInfo.numberPresentation;
                    if (i2 == 1 || (i2 != i && i == 1)) {
                        if (str2.equals("PRIVATE") || str2.equals("P") || str2.equals("RES") || str2.equals("PRIVATENUMBER")) {
                            str = PhoneNumberHelper.getDisplayNameForRestrictedNumber(context).toString();
                            callerInfo.numberPresentation = 2;
                        } else {
                            if (str2.equals("UNAVAILABLE") || str2.equals("UNKNOWN") || str2.equals("UNA") || str2.equals("U")) {
                                z = true;
                            }
                            if (z) {
                                str = context.getString(R.string.unknown);
                                callerInfo.numberPresentation = 3;
                            }
                            StringBuilder outline132 = GeneratedOutlineSupport.outline13("SpecialCnap: number=");
                            outline132.append(toLogSafePhoneNumber(str2));
                            outline132.append("; presentation now=");
                            outline132.append(callerInfo.numberPresentation);
                            outline132.toString();
                        }
                        str2 = str;
                        StringBuilder outline1322 = GeneratedOutlineSupport.outline13("SpecialCnap: number=");
                        outline1322.append(toLogSafePhoneNumber(str2));
                        outline1322.append("; presentation now=");
                        outline1322.append(callerInfo.numberPresentation);
                        outline1322.toString();
                    }
                    StringBuilder outline133 = GeneratedOutlineSupport.outline13("returning number string=");
                    outline133.append(toLogSafePhoneNumber(str2));
                    outline133.toString();
                }
                number = str2;
            }
            callerInfo.phoneNumber = number;
        }
        if (dialerCall.isVoiceMailNumber()) {
            callerInfo.markAsVoiceMail(context);
        }
        ContactInfoCache.getInstance(context).maybeInsertCnapInformationIntoCache(context, dialerCall, callerInfo);
        return callerInfo;
    }

    public static String getConferenceString(Context context, boolean z) {
        return context.getResources().getString(z ? R.string.generic_conference_call_name : R.string.conference_call_name);
    }

    static String toLogSafePhoneNumber(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '-' || charAt == '@' || charAt == '.' || charAt == '&') {
                sb.append(charAt);
            } else {
                sb.append('x');
            }
        }
        return sb.toString();
    }
}
