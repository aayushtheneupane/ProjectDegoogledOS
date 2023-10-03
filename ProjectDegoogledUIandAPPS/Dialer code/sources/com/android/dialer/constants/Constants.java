package com.android.dialer.constants;

import android.content.Context;

public abstract class Constants {
    private static boolean didInitializeInstance;
    private static Constants instance;

    protected Constants() {
    }

    public static synchronized Constants get() {
        Constants constants;
        synchronized (Constants.class) {
            if (!didInitializeInstance) {
                didInitializeInstance = true;
                try {
                    instance = (Constants) Class.forName(Constants.class.getName() + "Impl").getConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (ReflectiveOperationException unused) {
                    throw new AssertionError("Unable to create an instance of ConstantsImpl. To fix this error include one of the constants modules (googledialer, aosp etc...) in your target.");
                }
            }
            constants = instance;
        }
        return constants;
    }

    public abstract String getAnnotatedCallLogProviderAuthority();

    public abstract String getFileProviderAuthority();

    public abstract String getFilteredNumberProviderAuthority();

    public abstract String getPhoneLookupHistoryProviderAuthority();

    public abstract String getPreferredSimFallbackProviderAuthority();

    public abstract String getUserAgent(Context context);
}
