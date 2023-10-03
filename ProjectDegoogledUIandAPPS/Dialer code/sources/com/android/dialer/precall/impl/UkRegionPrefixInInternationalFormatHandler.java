package com.android.dialer.precall.impl;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.precall.impl.MalformedNumberRectifier;
import com.google.common.base.Optional;

class UkRegionPrefixInInternationalFormatHandler implements MalformedNumberRectifier.MalformedNumberHandler {
    UkRegionPrefixInInternationalFormatHandler() {
    }

    public Optional<String> handle(Context context, String str) {
        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("uk_region_prefix_in_international_format_fix_enabled", true)) {
            return Optional.absent();
        }
        if (!PhoneNumberUtils.normalizeNumber(str).startsWith("+440")) {
            return Optional.absent();
        }
        int i = 0;
        LogUtil.m9i("UkRegionPrefixInInternationalFormatHandler.handle", "removing (0) in UK numbers", new Object[0]);
        String convertKeypadLettersToDigits = PhoneNumberUtils.convertKeypadLettersToDigits(str);
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (true) {
            if (i >= convertKeypadLettersToDigits.length()) {
                break;
            }
            char charAt = convertKeypadLettersToDigits.charAt(i);
            if (charAt != "+440".charAt(i2)) {
                sb.append(charAt);
            } else {
                i2++;
                if (i2 == 4) {
                    sb.append(convertKeypadLettersToDigits.substring(i + 1));
                    break;
                }
                sb.append(charAt);
            }
            i++;
        }
        return Optional.m67of(sb.toString());
    }
}
