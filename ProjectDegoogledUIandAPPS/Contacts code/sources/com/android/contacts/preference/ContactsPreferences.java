package com.android.contacts.preference;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.contacts.R;
import com.android.contacts.model.account.AccountWithDataSet;
import java.util.List;

public class ContactsPreferences implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final int DISPLAY_ORDER_ALTERNATIVE = 2;
    public static final String DISPLAY_ORDER_KEY = "android.contacts.DISPLAY_ORDER";
    public static final int DISPLAY_ORDER_PRIMARY = 1;
    public static final String PHONETIC_NAME_DISPLAY_KEY = "Phonetic_name_display";
    private static final int PREFERENCE_UNASSIGNED = -1;
    public static final String PREF_DISPLAY_ONLY_PHONES = "only_phones";
    public static final boolean PREF_DISPLAY_ONLY_PHONES_DEFAULT = false;
    public static final int SORT_ORDER_ALTERNATIVE = 2;
    public static final String SORT_ORDER_KEY = "android.contacts.SORT_ORDER";
    public static final int SORT_ORDER_PRIMARY = 1;
    private final BackupManager mBackupManager;
    private final Context mContext;
    private AccountWithDataSet mDefaultAccount;
    private String mDefaultAccountKey;
    private int mDisplayOrder;
    private Handler mHandler;
    private final boolean mIsDefaultAccountUserChangeable;
    private ChangeListener mListener;
    private int mPhoneticNameDisplayPreference;
    private final SharedPreferences mPreferences;
    private int mSortOrder;

    public interface ChangeListener {
        void onChange();
    }

    public ContactsPreferences(Context context) {
        this(context, context.getResources().getBoolean(R.bool.config_default_account_user_changeable));
    }

    ContactsPreferences(Context context, boolean z) {
        this.mSortOrder = -1;
        this.mDisplayOrder = -1;
        this.mPhoneticNameDisplayPreference = -1;
        this.mDefaultAccount = null;
        this.mListener = null;
        this.mContext = context;
        this.mIsDefaultAccountUserChangeable = z;
        this.mBackupManager = new BackupManager(this.mContext);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mPreferences = this.mContext.getSharedPreferences(context.getPackageName(), 0);
        this.mDefaultAccountKey = this.mContext.getResources().getString(R.string.contact_editor_default_account_key);
        maybeMigrateSystemSettings();
    }

    public boolean isSortOrderUserChangeable() {
        return this.mContext.getResources().getBoolean(R.bool.config_sort_order_user_changeable);
    }

    public int getDefaultSortOrder() {
        return this.mContext.getResources().getBoolean(R.bool.config_default_sort_order_primary) ? 1 : 2;
    }

    public int getSortOrder() {
        if (!isSortOrderUserChangeable()) {
            return getDefaultSortOrder();
        }
        if (this.mSortOrder == -1) {
            this.mSortOrder = this.mPreferences.getInt(SORT_ORDER_KEY, getDefaultSortOrder());
        }
        return this.mSortOrder;
    }

    public void setSortOrder(int i) {
        this.mSortOrder = i;
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(SORT_ORDER_KEY, i);
        edit.commit();
        this.mBackupManager.dataChanged();
    }

    public boolean isDisplayOrderUserChangeable() {
        return this.mContext.getResources().getBoolean(R.bool.config_display_order_user_changeable);
    }

    public int getDefaultDisplayOrder() {
        return this.mContext.getResources().getBoolean(R.bool.config_default_display_order_primary) ? 1 : 2;
    }

    public int getDisplayOrder() {
        if (!isDisplayOrderUserChangeable()) {
            return getDefaultDisplayOrder();
        }
        if (this.mDisplayOrder == -1) {
            this.mDisplayOrder = this.mPreferences.getInt(DISPLAY_ORDER_KEY, getDefaultDisplayOrder());
        }
        return this.mDisplayOrder;
    }

    public void setDisplayOrder(int i) {
        this.mDisplayOrder = i;
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(DISPLAY_ORDER_KEY, i);
        edit.commit();
        this.mBackupManager.dataChanged();
    }

    public int getDefaultPhoneticNameDisplayPreference() {
        return this.mContext.getResources().getBoolean(R.bool.config_default_hide_phonetic_name_if_empty) ? 1 : 0;
    }

    public boolean isPhoneticNameDisplayPreferenceChangeable() {
        return this.mContext.getResources().getBoolean(R.bool.config_phonetic_name_display_user_changeable);
    }

    public void setPhoneticNameDisplayPreference(int i) {
        this.mPhoneticNameDisplayPreference = i;
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(PHONETIC_NAME_DISPLAY_KEY, i);
        edit.commit();
        this.mBackupManager.dataChanged();
    }

    public int getPhoneticNameDisplayPreference() {
        if (!isPhoneticNameDisplayPreferenceChangeable()) {
            return getDefaultPhoneticNameDisplayPreference();
        }
        if (this.mPhoneticNameDisplayPreference == -1) {
            this.mPhoneticNameDisplayPreference = this.mPreferences.getInt(PHONETIC_NAME_DISPLAY_KEY, getDefaultPhoneticNameDisplayPreference());
        }
        return this.mPhoneticNameDisplayPreference;
    }

    public boolean shouldHidePhoneticNamesIfEmpty() {
        return getPhoneticNameDisplayPreference() == 1;
    }

    public boolean isDefaultAccountUserChangeable() {
        return this.mIsDefaultAccountUserChangeable;
    }

    public AccountWithDataSet getDefaultAccount() {
        if (!isDefaultAccountUserChangeable()) {
            return this.mDefaultAccount;
        }
        if (this.mDefaultAccount == null) {
            String string = this.mPreferences.getString(this.mDefaultAccountKey, (String) null);
            if (!TextUtils.isEmpty(string)) {
                this.mDefaultAccount = AccountWithDataSet.unstringify(string);
            }
        }
        return this.mDefaultAccount;
    }

    public void clearDefaultAccount() {
        this.mDefaultAccount = null;
        this.mPreferences.edit().remove(this.mDefaultAccountKey).commit();
    }

    public void setDefaultAccount(AccountWithDataSet accountWithDataSet) {
        if (accountWithDataSet != null) {
            this.mDefaultAccount = accountWithDataSet;
            this.mPreferences.edit().putString(this.mDefaultAccountKey, accountWithDataSet.stringify()).commit();
            return;
        }
        throw new IllegalArgumentException("argument should not be null");
    }

    public boolean isDefaultAccountSet() {
        return this.mDefaultAccount != null || this.mPreferences.contains(this.mDefaultAccountKey);
    }

    public boolean shouldShowAccountChangedNotification(List<AccountWithDataSet> list) {
        AccountWithDataSet defaultAccount = getDefaultAccount();
        if (list.isEmpty()) {
            if (defaultAccount == null || !defaultAccount.isNullAccount()) {
                return true;
            }
            return false;
        } else if (list.size() != 1 || list.get(0).isNullAccount()) {
            return defaultAccount == null || !list.contains(defaultAccount);
        } else {
            return false;
        }
    }

    public void registerChangeListener(ChangeListener changeListener) {
        if (this.mListener != null) {
            unregisterChangeListener();
        }
        this.mListener = changeListener;
        this.mDisplayOrder = -1;
        this.mSortOrder = -1;
        this.mPhoneticNameDisplayPreference = -1;
        this.mDefaultAccount = null;
        this.mPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    public void unregisterChangeListener() {
        if (this.mListener != null) {
            this.mListener = null;
        }
        this.mPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, final String str) {
        this.mHandler.post(new Runnable() {
            public void run() {
                ContactsPreferences.this.refreshValue(str);
            }
        });
    }

    public void refreshValue(String str) {
        if (DISPLAY_ORDER_KEY.equals(str)) {
            this.mDisplayOrder = -1;
            this.mDisplayOrder = getDisplayOrder();
        } else if (SORT_ORDER_KEY.equals(str)) {
            this.mSortOrder = -1;
            this.mSortOrder = getSortOrder();
        } else if (PHONETIC_NAME_DISPLAY_KEY.equals(str)) {
            this.mPhoneticNameDisplayPreference = -1;
            this.mPhoneticNameDisplayPreference = getPhoneticNameDisplayPreference();
        } else if (this.mDefaultAccountKey.equals(str)) {
            this.mDefaultAccount = null;
            this.mDefaultAccount = getDefaultAccount();
        }
        ChangeListener changeListener = this.mListener;
        if (changeListener != null) {
            changeListener.onChange();
        }
    }

    private void maybeMigrateSystemSettings() {
        if (!this.mPreferences.contains(SORT_ORDER_KEY)) {
            int defaultSortOrder = getDefaultSortOrder();
            try {
                defaultSortOrder = Settings.System.getInt(this.mContext.getContentResolver(), SORT_ORDER_KEY);
            } catch (Settings.SettingNotFoundException unused) {
            }
            setSortOrder(defaultSortOrder);
        }
        if (!this.mPreferences.contains(DISPLAY_ORDER_KEY)) {
            int defaultDisplayOrder = getDefaultDisplayOrder();
            try {
                defaultDisplayOrder = Settings.System.getInt(this.mContext.getContentResolver(), DISPLAY_ORDER_KEY);
            } catch (Settings.SettingNotFoundException unused2) {
            }
            setDisplayOrder(defaultDisplayOrder);
        }
        if (!this.mPreferences.contains(PHONETIC_NAME_DISPLAY_KEY)) {
            int defaultPhoneticNameDisplayPreference = getDefaultPhoneticNameDisplayPreference();
            try {
                defaultPhoneticNameDisplayPreference = Settings.System.getInt(this.mContext.getContentResolver(), PHONETIC_NAME_DISPLAY_KEY);
            } catch (Settings.SettingNotFoundException unused3) {
            }
            setPhoneticNameDisplayPreference(defaultPhoneticNameDisplayPreference);
        }
        if (!this.mPreferences.contains(this.mDefaultAccountKey)) {
            String string = PreferenceManager.getDefaultSharedPreferences(this.mContext).getString(this.mDefaultAccountKey, (String) null);
            if (!TextUtils.isEmpty(string)) {
                setDefaultAccount(AccountWithDataSet.unstringify(string));
            }
        }
    }
}
