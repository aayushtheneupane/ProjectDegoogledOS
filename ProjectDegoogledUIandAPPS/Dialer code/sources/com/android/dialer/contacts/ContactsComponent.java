package com.android.dialer.contacts;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;
import com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequester;
import com.android.dialer.inject.HasRootComponent;

public abstract class ContactsComponent {

    public interface HasComponent {
    }

    public static ContactsComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).contactsComponent();
    }

    public abstract ContactDisplayPreferences contactDisplayPreferences();

    public abstract HighResolutionPhotoRequester highResolutionPhotoLoader();
}
