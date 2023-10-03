package com.android.dialer.app.contactinfo;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p002v7.app.AppCompatActivity;
import com.android.dialer.phonenumbercache.ContactInfo;
import com.android.dialer.util.ExpirableCache;

public class ExpirableCacheHeadlessFragment extends Fragment {
    private ExpirableCache<NumberWithCountryIso, ContactInfo> retainedCache;

    public static ExpirableCacheHeadlessFragment attach(AppCompatActivity appCompatActivity) {
        FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
        ExpirableCacheHeadlessFragment expirableCacheHeadlessFragment = (ExpirableCacheHeadlessFragment) supportFragmentManager.findFragmentByTag("ExpirableCacheHeadlessFragment");
        if (expirableCacheHeadlessFragment != null) {
            return expirableCacheHeadlessFragment;
        }
        ExpirableCacheHeadlessFragment expirableCacheHeadlessFragment2 = new ExpirableCacheHeadlessFragment();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.add((Fragment) expirableCacheHeadlessFragment2, "ExpirableCacheHeadlessFragment");
        beginTransaction.commitNowAllowingStateLoss();
        return expirableCacheHeadlessFragment2;
    }

    public ExpirableCache<NumberWithCountryIso, ContactInfo> getRetainedCache() {
        return this.retainedCache;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.retainedCache = ExpirableCache.create(100);
        setRetainInstance(true);
    }
}
