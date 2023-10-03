package com.android.dialer.phonenumbercache;

import android.content.Context;
import java.util.Objects;

public class PhoneNumberCache {
    private static PhoneNumberCacheBindingsStub phoneNumberCacheBindings;

    public static PhoneNumberCacheBindingsStub get(Context context) {
        Objects.requireNonNull(context);
        PhoneNumberCacheBindingsStub phoneNumberCacheBindingsStub = phoneNumberCacheBindings;
        if (phoneNumberCacheBindingsStub != null) {
            return phoneNumberCacheBindingsStub;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof PhoneNumberCacheBindingsFactory) {
            phoneNumberCacheBindings = ((PhoneNumberCacheBindingsFactory) applicationContext).newPhoneNumberCacheBindings();
        }
        if (phoneNumberCacheBindings == null) {
            phoneNumberCacheBindings = new PhoneNumberCacheBindingsStub();
        }
        return phoneNumberCacheBindings;
    }
}
