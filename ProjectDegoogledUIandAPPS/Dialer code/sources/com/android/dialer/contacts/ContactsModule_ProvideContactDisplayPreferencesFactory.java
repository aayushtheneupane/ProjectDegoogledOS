package com.android.dialer.contacts;

import android.content.Context;
import android.os.UserManager;
import android.support.p002v7.appcompat.R$style;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferencesImpl;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferencesStub;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ContactsModule_ProvideContactDisplayPreferencesFactory implements Factory<ContactDisplayPreferences> {
    private final Provider<Context> appContextProvider;
    private final Provider<ContactDisplayPreferencesImpl> implProvider;
    private final Provider<ContactDisplayPreferencesStub> stubProvider;

    public ContactsModule_ProvideContactDisplayPreferencesFactory(Provider<Context> provider, Provider<ContactDisplayPreferencesImpl> provider2, Provider<ContactDisplayPreferencesStub> provider3) {
        this.appContextProvider = provider;
        this.implProvider = provider2;
        this.stubProvider = provider3;
    }

    public Object get() {
        Lazy<ContactDisplayPreferencesImpl> lazy = DoubleCheck.lazy(this.implProvider);
        Object obj = this.stubProvider.get();
        if (((UserManager) this.appContextProvider.get().getSystemService(UserManager.class)).isUserUnlocked()) {
            obj = (ContactDisplayPreferences) ((DoubleCheck) lazy).get();
        }
        R$style.checkNotNull1(obj, "Cannot return null from a non-@Nullable @Provides method");
        return obj;
    }
}
