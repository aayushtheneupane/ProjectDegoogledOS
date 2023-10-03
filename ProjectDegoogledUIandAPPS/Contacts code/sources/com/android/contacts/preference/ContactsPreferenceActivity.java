package com.android.contacts.preference;

import android.app.Activity;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.text.TextUtils;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatCallback;
import androidx.appcompat.app.AppCompatDelegate;
import com.android.contacts.R;
import com.android.contacts.editor.SelectAccountDialogFragment;
import com.android.contacts.list.ProviderStatusWatcher;
import com.android.contacts.model.account.AccountWithDataSet;
import com.android.contacts.preference.DisplayOptionsPreferenceFragment;
import com.android.contacts.util.AccountSelectionUtil;

public final class ContactsPreferenceActivity extends PreferenceActivity implements DisplayOptionsPreferenceFragment.ProfileListener, SelectAccountDialogFragment.Listener {
    private boolean mAreContactsAvailable;
    private AppCompatDelegate mCompatDelegate;
    private String mNewLocalProfileExtra;
    private ProviderStatusWatcher mProviderStatusWatcher;

    public void onAccountSelectorCancelled() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.mCompatDelegate = AppCompatDelegate.create((Activity) this, (AppCompatCallback) null);
        super.onCreate(bundle);
        this.mCompatDelegate.onCreate(bundle);
        ActionBar supportActionBar = this.mCompatDelegate.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayOptions(4, 4);
        }
        this.mProviderStatusWatcher = ProviderStatusWatcher.getInstance(this);
        this.mNewLocalProfileExtra = getIntent().getStringExtra("newLocalProfile");
        this.mAreContactsAvailable = this.mProviderStatusWatcher.getProviderStatus() == 0;
        if (bundle == null) {
            getFragmentManager().beginTransaction().replace(16908290, DisplayOptionsPreferenceFragment.newInstance(this.mNewLocalProfileExtra, this.mAreContactsAvailable), "display_options").commit();
            setActivityTitle(R.string.activity_title_settings);
        } else if (((AboutPreferenceFragment) getFragmentManager().findFragmentByTag("about_contacts")) != null) {
            setActivityTitle(R.string.setting_about);
        } else {
            setActivityTitle(R.string.activity_title_settings);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.mCompatDelegate.onPostCreate(bundle);
    }

    public MenuInflater getMenuInflater() {
        return this.mCompatDelegate.getMenuInflater();
    }

    public void setContentView(int i) {
        this.mCompatDelegate.setContentView(i);
    }

    public void setContentView(View view) {
        this.mCompatDelegate.setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mCompatDelegate.setContentView(view, layoutParams);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mCompatDelegate.addContentView(view, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        this.mCompatDelegate.onPostResume();
    }

    /* access modifiers changed from: protected */
    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        this.mCompatDelegate.setTitle(charSequence);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mCompatDelegate.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mCompatDelegate.onDestroy();
    }

    public void invalidateOptionsMenu() {
        this.mCompatDelegate.invalidateOptionsMenu();
    }

    /* access modifiers changed from: protected */
    public void showAboutFragment() {
        getFragmentManager().beginTransaction().replace(16908290, AboutPreferenceFragment.newInstance(), "about_contacts").addToBackStack((String) null).commit();
        setActivityTitle(R.string.setting_about);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            setActivityTitle(R.string.activity_title_settings);
            getFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }

    private void setActivityTitle(int i) {
        ActionBar supportActionBar = this.mCompatDelegate.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(i);
        }
    }

    public void onProfileLoaded(Cursor cursor) {
        int i;
        long j;
        boolean z;
        String str;
        if (cursor == null || !cursor.moveToFirst()) {
            str = null;
            j = -1;
            z = false;
            i = 0;
        } else {
            boolean z2 = cursor.getInt(2) == 1;
            str = cursor.getString(1);
            long j2 = cursor.getLong(0);
            i = cursor.getInt(3);
            z = z2;
            j = j2;
        }
        ((DisplayOptionsPreferenceFragment) getFragmentManager().findFragmentByTag("display_options")).updateMyInfoPreference(z, (!z || !TextUtils.isEmpty(str)) ? str : getString(R.string.missing_name), j, i);
    }

    public void onAccountChosen(AccountWithDataSet accountWithDataSet, Bundle bundle) {
        AccountSelectionUtil.doImport(this, bundle.getInt("resourceId"), accountWithDataSet, bundle.getInt("subscriptionId"));
    }
}
