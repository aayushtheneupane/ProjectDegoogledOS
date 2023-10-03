package com.android.dialer.assisteddialing;

import android.content.Context;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.design.R$dimen;
import android.telephony.TelephonyManager;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.function.Supplier;
import com.android.dialer.strictmode.StrictModeUtils;

public final class ConcreteCreator {
    public static AssistedDialingMediator createNewAssistedDialingMediator(TelephonyManager telephonyManager, Context context) {
        ConfigProvider configProvider = ConfigProviderComponent.get(context).getConfigProvider();
        if (telephonyManager == null) {
            LogUtil.m9i("ConcreteCreator.createNewAssistedDialingMediator", "provided TelephonyManager was null", new Object[0]);
            throw new NullPointerException("Provided TelephonyManager was null");
        } else if (!R$dimen.isUserUnlocked(context)) {
            LogUtil.m9i("ConcreteCreator.createNewAssistedDialingMediator", "user is locked", new Object[0]);
            return new AssistedDialingMediatorStub();
        } else if (!isAssistedDialingEnabled(configProvider)) {
            LogUtil.m9i("ConcreteCreator.createNewAssistedDialingMediator", "feature not enabled", new Object[0]);
            return new AssistedDialingMediatorStub();
        } else if (!PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.assisted_dialing_setting_toggle_key), true)) {
            LogUtil.m9i("ConcreteCreator.createNewAssistedDialingMediator", "disabled by local setting", new Object[0]);
            return new AssistedDialingMediatorStub();
        } else {
            return new AssistedDialingMediatorImpl(new LocationDetector(telephonyManager, (String) StrictModeUtils.bypass(new Supplier(context) {
                private final /* synthetic */ Context f$0;

                {
                    this.f$0 = r1;
                }

                public final Object get() {
                    return PreferenceManager.getDefaultSharedPreferences(this.f$0).getString(this.f$0.getString(R.string.assisted_dialing_setting_cc_key), (String) null);
                }
            })), new NumberTransformer(new Constraints(context, getCountryCodeProvider(configProvider))));
        }
    }

    public static CountryCodeProvider getCountryCodeProvider(ConfigProvider configProvider) {
        if (configProvider != null) {
            return new CountryCodeProvider(configProvider);
        }
        LogUtil.m9i("ConcreteCreator.getCountryCodeProvider", "provided configProvider was null", new Object[0]);
        throw new NullPointerException("Provided configProvider was null");
    }

    public static boolean isAssistedDialingEnabled(ConfigProvider configProvider) {
        if (configProvider == null) {
            LogUtil.m9i("ConcreteCreator.isAssistedDialingEnabled", "provided configProvider was null", new Object[0]);
            throw new NullPointerException("Provided configProvider was null");
        } else if (Build.VERSION.SDK_INT > 28 || !((SharedPrefConfigProvider) configProvider).getBoolean("assisted_dialing_enabled", false)) {
            return false;
        } else {
            return true;
        }
    }
}
