package com.android.dialer.assisteddialing;

import android.text.TextUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public final class CountryCodeProvider {
    static final List<String> DEFAULT_COUNTRY_CODES = Arrays.asList(new String[]{"CA", "GB", "JP", "MX", "US"});
    private final Set<String> supportedCountryCodes;

    CountryCodeProvider(ConfigProvider configProvider) {
        List list;
        String string = ((SharedPrefConfigProvider) configProvider).getString("assisted_dialing_csv_country_codes", "");
        if (TextUtils.isEmpty(string)) {
            LogUtil.m9i("Constraints.parseConfigProviderCountryCodes", "configProviderCountryCodes was empty, returning default", new Object[0]);
            list = DEFAULT_COUNTRY_CODES;
        } else {
            StringTokenizer stringTokenizer = new StringTokenizer(string, ",");
            if (stringTokenizer.countTokens() < 1) {
                LogUtil.m9i("Constraints.parseConfigProviderCountryCodes", "insufficient provided country codes", new Object[0]);
                list = DEFAULT_COUNTRY_CODES;
            } else {
                list = new ArrayList();
                while (true) {
                    if (!stringTokenizer.hasMoreTokens()) {
                        break;
                    }
                    String nextToken = stringTokenizer.nextToken();
                    if (nextToken == null) {
                        LogUtil.m9i("Constraints.parseConfigProviderCountryCodes", "Unexpected empty value, returning default.", new Object[0]);
                        list = DEFAULT_COUNTRY_CODES;
                        break;
                    } else if (nextToken.length() != 2) {
                        LogUtil.m9i("Constraints.parseConfigProviderCountryCodes", "Unexpected locale %s, returning default", nextToken);
                        list = DEFAULT_COUNTRY_CODES;
                        break;
                    } else {
                        list.add(nextToken);
                    }
                }
            }
        }
        this.supportedCountryCodes = (Set) list.stream().map($$Lambda$CountryCodeProvider$8g2oNCFYTxRUkOMRqXjGOluxP5c.INSTANCE).collect(Collectors.toCollection($$Lambda$Wul933eTbCG5LciVpnY1xFYbHAc.INSTANCE));
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Using country codes: ");
        outline13.append(this.supportedCountryCodes);
        LogUtil.m9i("CountryCodeProvider.CountryCodeProvider", outline13.toString(), new Object[0]);
    }

    public boolean isSupportedCountryCode(String str) {
        return this.supportedCountryCodes.contains(str);
    }
}
