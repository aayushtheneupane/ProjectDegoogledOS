package com.android.dialer.contacts.displaypreference;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ContactDisplayPreferencesImpl_Factory implements Factory<ContactDisplayPreferencesImpl> {
    private final Provider<Context> appContextProvider;

    public ContactDisplayPreferencesImpl_Factory(Provider<Context> provider) {
        this.appContextProvider = provider;
    }

    public Object get() {
        return new ContactDisplayPreferencesImpl(this.appContextProvider.get());
    }
}
