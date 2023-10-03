package com.android.contacts.common.extensions;

import android.content.Context;
import com.android.dialer.common.Assert;

public final class PhoneDirectoryExtenderAccessor {
    private static PhoneDirectoryExtender instance;

    public static PhoneDirectoryExtender get(Context context) {
        Assert.isNotNull(context);
        PhoneDirectoryExtender phoneDirectoryExtender = instance;
        if (phoneDirectoryExtender != null) {
            return phoneDirectoryExtender;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof PhoneDirectoryExtenderFactory) {
            instance = ((PhoneDirectoryExtenderFactory) applicationContext).newPhoneDirectoryExtender();
        }
        if (instance == null) {
            instance = new PhoneDirectoryExtenderStub();
        }
        return instance;
    }

    public static void setForTesting(PhoneDirectoryExtender phoneDirectoryExtender) {
        instance = phoneDirectoryExtender;
    }
}
