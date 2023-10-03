package com.android.dialer.phonelookup;

import android.support.p002v7.appcompat.R$style;
import com.android.dialer.phonelookup.blockednumber.SystemBlockedNumberPhoneLookup;
import com.android.dialer.phonelookup.cequint.CequintPhoneLookup;
import com.android.dialer.phonelookup.cnap.CnapPhoneLookup;
import com.android.dialer.phonelookup.cp2.Cp2DefaultDirectoryPhoneLookup;
import com.android.dialer.phonelookup.cp2.Cp2ExtendedDirectoryPhoneLookup;
import com.android.dialer.phonelookup.emergency.EmergencyPhoneLookup;
import com.android.dialer.phonelookup.spam.SpamPhoneLookup;
import com.google.common.collect.ImmutableList;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PhoneLookupModule_ProvidePhoneLookupListFactory implements Factory<ImmutableList<PhoneLookup>> {
    private final Provider<CequintPhoneLookup> cequintPhoneLookupProvider;
    private final Provider<CnapPhoneLookup> cnapPhoneLookupProvider;
    private final Provider<Cp2DefaultDirectoryPhoneLookup> cp2DefaultDirectoryPhoneLookupProvider;
    private final Provider<Cp2ExtendedDirectoryPhoneLookup> cp2ExtendedDirectoryPhoneLookupProvider;
    private final Provider<EmergencyPhoneLookup> emergencyPhoneLookupProvider;
    private final Provider<SpamPhoneLookup> spamPhoneLookupProvider;
    private final Provider<SystemBlockedNumberPhoneLookup> systemBlockedNumberPhoneLookupProvider;

    public PhoneLookupModule_ProvidePhoneLookupListFactory(Provider<CequintPhoneLookup> provider, Provider<CnapPhoneLookup> provider2, Provider<Cp2DefaultDirectoryPhoneLookup> provider3, Provider<Cp2ExtendedDirectoryPhoneLookup> provider4, Provider<EmergencyPhoneLookup> provider5, Provider<SystemBlockedNumberPhoneLookup> provider6, Provider<SpamPhoneLookup> provider7) {
        this.cequintPhoneLookupProvider = provider;
        this.cnapPhoneLookupProvider = provider2;
        this.cp2DefaultDirectoryPhoneLookupProvider = provider3;
        this.cp2ExtendedDirectoryPhoneLookupProvider = provider4;
        this.emergencyPhoneLookupProvider = provider5;
        this.systemBlockedNumberPhoneLookupProvider = provider6;
        this.spamPhoneLookupProvider = provider7;
    }

    public Object get() {
        ImmutableList of = ImmutableList.m80of(this.cequintPhoneLookupProvider.get(), this.cnapPhoneLookupProvider.get(), this.cp2DefaultDirectoryPhoneLookupProvider.get(), this.cp2ExtendedDirectoryPhoneLookupProvider.get(), this.emergencyPhoneLookupProvider.get(), this.systemBlockedNumberPhoneLookupProvider.get(), this.spamPhoneLookupProvider.get());
        R$style.checkNotNull1(of, "Cannot return null from a non-@Nullable @Provides method");
        return of;
    }
}
