package com.android.dialer.app;

import android.content.Context;
import com.android.dialer.app.legacybindings.DialerLegacyBindings;
import com.android.dialer.app.legacybindings.DialerLegacyBindingsFactory;
import com.android.dialer.app.legacybindings.DialerLegacyBindingsStub;
import java.util.Objects;

public class Bindings {
    private static DialerLegacyBindings legacyInstance;

    public static DialerLegacyBindings getLegacy(Context context) {
        Objects.requireNonNull(context);
        DialerLegacyBindings dialerLegacyBindings = legacyInstance;
        if (dialerLegacyBindings != null) {
            return dialerLegacyBindings;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof DialerLegacyBindingsFactory) {
            legacyInstance = ((DialerLegacyBindingsFactory) applicationContext).newDialerLegacyBindings();
        }
        if (legacyInstance == null) {
            legacyInstance = new DialerLegacyBindingsStub();
        }
        return legacyInstance;
    }
}
