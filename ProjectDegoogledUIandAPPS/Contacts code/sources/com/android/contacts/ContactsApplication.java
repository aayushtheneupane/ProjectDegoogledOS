package com.android.contacts;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import com.android.contacts.testing.InjectedServices;

public class ContactsApplication extends Application {
    private static final boolean ENABLE_FRAGMENT_LOG = false;
    private static final boolean ENABLE_LOADER_LOG = false;
    public static final String STRICT_MODE_TAG = "ContactsStrictMode";
    private static InjectedServices sInjectedServices;

    public static void injectServices(InjectedServices injectedServices) {
        sInjectedServices = injectedServices;
    }

    public static InjectedServices getInjectedServices() {
        return sInjectedServices;
    }

    public ContentResolver getContentResolver() {
        ContentResolver contentResolver;
        InjectedServices injectedServices = sInjectedServices;
        if (injectedServices == null || (contentResolver = injectedServices.getContentResolver()) == null) {
            return super.getContentResolver();
        }
        return contentResolver;
    }

    public SharedPreferences getSharedPreferences(String str, int i) {
        SharedPreferences sharedPreferences;
        InjectedServices injectedServices = sInjectedServices;
        if (injectedServices == null || (sharedPreferences = injectedServices.getSharedPreferences()) == null) {
            return super.getSharedPreferences(str, i);
        }
        return sharedPreferences;
    }

    public Object getSystemService(String str) {
        Object systemService;
        InjectedServices injectedServices = sInjectedServices;
        if (injectedServices == null || (systemService = injectedServices.getSystemService(str)) == null) {
            return super.getSystemService(str);
        }
        return systemService;
    }

    public void onCreate() {
        super.onCreate();
        if (Log.isLoggable("ContactsPerf", 3)) {
            Log.d("ContactsPerf", "ContactsApplication.onCreate start");
        }
        if (Log.isLoggable(STRICT_MODE_TAG, 3)) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        }
        new DelayedInitializer().execute();
        if (Log.isLoggable("ContactsPerf", 3)) {
            Log.d("ContactsPerf", "ContactsApplication.onCreate finish");
        }
    }

    private class DelayedInitializer extends AsyncTask<Void, Void, Void> {
        private DelayedInitializer() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            PreferenceManager.getDefaultSharedPreferences(ContactsApplication.this);
            ContactsApplication.this.getContentResolver().getType(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, 1));
            return null;
        }

        public void execute() {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[]) null);
        }
    }
}
