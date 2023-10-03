package com.android.dialer.configprovider;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import com.android.dialer.common.LogUtil;
import com.android.dialer.function.Supplier;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class SharedPrefConfigProvider implements ConfigProvider {
    private final SharedPreferences sharedPreferences;

    public static class Service extends IntentService {
        public Service() {
            super("SharedPrefConfigProvider.Service");
        }

        /* access modifiers changed from: protected */
        public void onHandleIntent(Intent intent) {
            if (intent == null || intent.getExtras() == null || intent.getExtras().size() != 1) {
                LogUtil.m10w("SharedPrefConfigProvider.Service.onHandleIntent", "must set exactly one extra", new Object[0]);
                return;
            }
            String str = (String) intent.getExtras().keySet().iterator().next();
            Object obj = intent.getExtras().get(str);
            SharedPreferences.Editor edit = StorageComponent.get(getApplicationContext()).unencryptedSharedPrefs().edit();
            String outline8 = GeneratedOutlineSupport.outline8("config_provider_prefs_", str);
            if (obj instanceof Boolean) {
                edit.putBoolean(outline8, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Long) {
                edit.putLong(outline8, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                edit.putString(outline8, (String) obj);
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("unsupported extra type: ");
                outline13.append(obj.getClass());
                throw new AssertionError(outline13.toString());
            }
            edit.apply();
        }
    }

    SharedPrefConfigProvider(SharedPreferences sharedPreferences2) {
        this.sharedPreferences = sharedPreferences2;
    }

    public boolean getBoolean(String str, boolean z) {
        return ((Boolean) StrictModeUtils.bypass(new Supplier(str, z) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object get() {
                return SharedPrefConfigProvider.this.lambda$getBoolean$2$SharedPrefConfigProvider(this.f$1, this.f$2);
            }
        })).booleanValue();
    }

    public long getLong(String str, long j) {
        return ((Long) StrictModeUtils.bypass(new Supplier(str, j) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ long f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object get() {
                return SharedPrefConfigProvider.this.lambda$getLong$1$SharedPrefConfigProvider(this.f$1, this.f$2);
            }
        })).longValue();
    }

    public String getString(String str, String str2) {
        return (String) StrictModeUtils.bypass(new Supplier(str, str2) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object get() {
                return SharedPrefConfigProvider.this.lambda$getString$0$SharedPrefConfigProvider(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ Boolean lambda$getBoolean$2$SharedPrefConfigProvider(String str, boolean z) {
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        return Boolean.valueOf(sharedPreferences2.getBoolean("config_provider_prefs_" + str, z));
    }

    public /* synthetic */ Long lambda$getLong$1$SharedPrefConfigProvider(String str, long j) {
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        return Long.valueOf(sharedPreferences2.getLong("config_provider_prefs_" + str, j));
    }

    public /* synthetic */ String lambda$getString$0$SharedPrefConfigProvider(String str, String str2) {
        SharedPreferences sharedPreferences2 = this.sharedPreferences;
        return sharedPreferences2.getString("config_provider_prefs_" + str, str2);
    }
}
