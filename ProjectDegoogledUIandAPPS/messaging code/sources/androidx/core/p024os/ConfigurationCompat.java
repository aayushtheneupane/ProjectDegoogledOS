package androidx.core.p024os;

import android.content.res.Configuration;
import android.os.Build;

/* renamed from: androidx.core.os.ConfigurationCompat */
public final class ConfigurationCompat {
    private ConfigurationCompat() {
    }

    public static LocaleListCompat getLocales(Configuration configuration) {
        int i = Build.VERSION.SDK_INT;
        return LocaleListCompat.wrap(configuration.getLocales());
    }
}
