package com.android.contacts.testing;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import com.google.common.collect.Maps;
import java.util.HashMap;

public class InjectedServices {
    private ContentResolver mContentResolver;
    private SharedPreferences mSharedPreferences;
    private HashMap<String, Object> mSystemServices;

    public void setContentResolver(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    public ContentResolver getContentResolver() {
        return this.mContentResolver;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    public SharedPreferences getSharedPreferences() {
        return this.mSharedPreferences;
    }

    public void setSystemService(String str, Object obj) {
        if (this.mSystemServices == null) {
            this.mSystemServices = Maps.newHashMap();
        }
        this.mSystemServices.put(str, obj);
    }

    public Object getSystemService(String str) {
        HashMap<String, Object> hashMap = this.mSystemServices;
        if (hashMap != null) {
            return hashMap.get(str);
        }
        return null;
    }
}
