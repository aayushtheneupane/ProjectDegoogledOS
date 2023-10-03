package com.android.dialer.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.inject.HasRootComponent;

public abstract class StorageComponent {

    public interface HasComponent {
    }

    public static StorageComponent get(Context context) {
        return ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).storageComponent();
    }

    public abstract SharedPreferences unencryptedSharedPrefs();
}
