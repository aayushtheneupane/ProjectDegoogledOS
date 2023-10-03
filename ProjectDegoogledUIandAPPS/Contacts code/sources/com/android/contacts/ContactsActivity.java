package com.android.contacts;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.android.contacts.ContactSaveService;
import com.android.contacts.activities.TransactionSafeActivity;
import com.android.contacts.testing.InjectedServices;

public abstract class ContactsActivity extends TransactionSafeActivity implements ContactSaveService.Listener {
    private ContentResolver mContentResolver;

    public ContentResolver getContentResolver() {
        if (this.mContentResolver == null) {
            InjectedServices injectedServices = ContactsApplication.getInjectedServices();
            if (injectedServices != null) {
                this.mContentResolver = injectedServices.getContentResolver();
            }
            if (this.mContentResolver == null) {
                this.mContentResolver = super.getContentResolver();
            }
        }
        return this.mContentResolver;
    }

    public SharedPreferences getSharedPreferences(String str, int i) {
        SharedPreferences sharedPreferences;
        InjectedServices injectedServices = ContactsApplication.getInjectedServices();
        if (injectedServices == null || (sharedPreferences = injectedServices.getSharedPreferences()) == null) {
            return super.getSharedPreferences(str, i);
        }
        return sharedPreferences;
    }

    public Object getSystemService(String str) {
        Object systemService = super.getSystemService(str);
        if (systemService != null) {
            return systemService;
        }
        return getApplicationContext().getSystemService(str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ContactSaveService.registerListener(this);
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ContactSaveService.unregisterListener(this);
        super.onDestroy();
    }

    public void onServiceCompleted(Intent intent) {
        onNewIntent(intent);
    }
}
