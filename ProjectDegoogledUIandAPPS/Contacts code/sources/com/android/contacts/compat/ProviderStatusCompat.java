package com.android.contacts.compat;

public class ProviderStatusCompat {
    public static final int STATUS_BUSY = 1;
    public static final int STATUS_EMPTY = (USE_CURRENT_VERSION ? 2 : 4);
    public static final boolean USE_CURRENT_VERSION = CompatUtils.isMarshmallowCompatible();

    static {
        boolean z = USE_CURRENT_VERSION;
    }
}
