package com.android.dialer.phonelookup;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.phonelookup.composite.CompositePhoneLookup;

public abstract class PhoneLookupComponent {

    public interface HasComponent {
    }

    public static PhoneLookupComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).phoneLookupComponent();
    }

    public abstract CompositePhoneLookup compositePhoneLookup();
}
