package com.android.settings.users;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.ContactsContract;
import android.provider.SearchIndexableResource;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.internal.util.UserIcons;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.SettingsActivity;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;
import com.android.settings.core.SubSettingLauncher;
import com.android.settings.dashboard.SummaryLoader;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.users.EditUserInfoController;
import com.android.settings.users.MultiUserSwitchBarController;
import com.android.settings.widget.SwitchBar;
import com.android.settings.widget.SwitchBarController;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.havoc.config.center.C1715R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class UserSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceClickListener, View.OnClickListener, MultiUserSwitchBarController.OnMultiUserSwitchChangedListener, DialogInterface.OnDismissListener, EditUserInfoController.OnContentChangedCallback {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        /* access modifiers changed from: protected */
        public boolean isPageSearchEnabled(Context context) {
            return UserCapabilities.create(context).mEnabled;
        }

        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.user_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }

        public List<String> getNonIndexableKeysFromXml(Context context, int i, boolean z) {
            List<String> nonIndexableKeysFromXml = super.getNonIndexableKeysFromXml(context, i, z);
            new AddUserWhenLockedPreferenceController(context, "user_settings_add_users_when_locked").updateNonIndexableKeys(nonIndexableKeysFromXml);
            new AutoSyncDataPreferenceController(context, (Fragment) null).updateNonIndexableKeys(nonIndexableKeysFromXml);
            new AutoSyncPersonalDataPreferenceController(context, (Fragment) null).updateNonIndexableKeys(nonIndexableKeysFromXml);
            new AutoSyncWorkDataPreferenceController(context, (Fragment) null).updateNonIndexableKeys(nonIndexableKeysFromXml);
            return nonIndexableKeysFromXml;
        }
    };
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = $$Lambda$UserSettings$Eg6plZiaX7G7UUvF4Q46lU8PMRw.INSTANCE;
    private static final IntentFilter USER_REMOVED_INTENT_FILTER = new IntentFilter("android.intent.action.USER_REMOVED");
    private static SparseArray<Bitmap> sDarkDefaultUserBitmapCache = new SparseArray<>();
    RestrictedPreference mAddUser;
    private AddUserWhenLockedPreferenceController mAddUserWhenLockedPreferenceController;
    /* access modifiers changed from: private */
    public int mAddedUserId = 0;
    /* access modifiers changed from: private */
    public boolean mAddingUser;
    private String mAddingUserName;
    private Drawable mDefaultIconDrawable;
    private EditUserInfoController mEditUserInfoController = new EditUserInfoController();
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                UserSettings.this.updateUserList();
            } else if (i == 2) {
                UserSettings.this.onUserCreated(message.arg1);
            } else if (i == 3) {
                UserSettings.this.onManageUserClicked(message.arg1, true);
            }
        }
    };
    UserPreference mMePreference;
    private MultiUserFooterPreferenceController mMultiUserFooterPreferenceController;
    /* access modifiers changed from: private */
    public int mRemovingUserId = -1;
    private boolean mShouldUpdateUserList = true;
    private MultiUserSwitchBarController mSwitchBarController;
    /* access modifiers changed from: private */
    public UserCapabilities mUserCaps;
    private BroadcastReceiver mUserChangeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            if (intent.getAction().equals("android.intent.action.USER_REMOVED")) {
                int unused = UserSettings.this.mRemovingUserId = -1;
            } else if (intent.getAction().equals("android.intent.action.USER_INFO_CHANGED") && (intExtra = intent.getIntExtra("android.intent.extra.user_handle", -1)) != -1) {
                UserSettings.this.mUserIcons.remove(intExtra);
            }
            UserSettings.this.mHandler.sendEmptyMessage(1);
        }
    };
    /* access modifiers changed from: private */
    public SparseArray<Bitmap> mUserIcons = new SparseArray<>();
    PreferenceGroup mUserListCategory;
    /* access modifiers changed from: private */
    public final Object mUserLock = new Object();
    /* access modifiers changed from: private */
    public UserManager mUserManager;

    public int getDialogMetricsCategory(int i) {
        switch (i) {
            case 1:
                return 591;
            case 2:
                return 595;
            case 3:
                return 596;
            case 4:
                return 597;
            case 5:
                return 594;
            case 6:
                return 598;
            case 7:
                return 599;
            case 8:
                return 600;
            case 9:
                return 601;
            default:
                return 0;
        }
    }

    public int getHelpResource() {
        return C1715R.string.help_url_users;
    }

    public int getMetricsCategory() {
        return 96;
    }

    static {
        USER_REMOVED_INTENT_FILTER.addAction("android.intent.action.USER_INFO_CHANGED");
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        SwitchBar switchBar = settingsActivity.getSwitchBar();
        this.mSwitchBarController = new MultiUserSwitchBarController(settingsActivity, new SwitchBarController(switchBar), this);
        getSettingsLifecycle().addObserver(this.mSwitchBarController);
        switchBar.show();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.user_settings);
        FragmentActivity activity = getActivity();
        if (!Utils.isDeviceProvisioned(activity)) {
            activity.finish();
            return;
        }
        this.mAddUserWhenLockedPreferenceController = new AddUserWhenLockedPreferenceController(activity, "user_settings_add_users_when_locked");
        this.mMultiUserFooterPreferenceController = new MultiUserFooterPreferenceController(activity).setFooterMixin(this.mFooterPreferenceMixin);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mAddUserWhenLockedPreferenceController.displayPreference(preferenceScreen);
        this.mMultiUserFooterPreferenceController.displayPreference(preferenceScreen);
        preferenceScreen.findPreference(this.mAddUserWhenLockedPreferenceController.getPreferenceKey()).setOnPreferenceChangeListener(this.mAddUserWhenLockedPreferenceController);
        if (bundle != null) {
            if (bundle.containsKey("adding_user")) {
                this.mAddedUserId = bundle.getInt("adding_user");
            }
            if (bundle.containsKey("removing_user")) {
                this.mRemovingUserId = bundle.getInt("removing_user");
            }
            this.mEditUserInfoController.onRestoreInstanceState(bundle);
        }
        this.mUserCaps = UserCapabilities.create(activity);
        this.mUserManager = (UserManager) activity.getSystemService("user");
        if (this.mUserCaps.mEnabled) {
            int myUserId = UserHandle.myUserId();
            this.mUserListCategory = (PreferenceGroup) findPreference("user_list");
            this.mMePreference = new UserPreference(getPrefContext(), (AttributeSet) null, myUserId, (View.OnClickListener) null, (View.OnClickListener) null);
            this.mMePreference.setKey("user_me");
            this.mMePreference.setOnPreferenceClickListener(this);
            if (this.mUserCaps.mIsAdmin) {
                this.mMePreference.setSummary((int) C1715R.string.user_admin);
            }
            this.mAddUser = (RestrictedPreference) findPreference("user_add");
            if (!this.mUserCaps.mCanAddRestrictedProfile) {
                this.mAddUser.setTitle((int) C1715R.string.user_add_user_menu);
            }
            this.mAddUser.setOnPreferenceClickListener(this);
            activity.registerReceiverAsUser(this.mUserChangeReceiver, UserHandle.ALL, USER_REMOVED_INTENT_FILTER, (String) null, this.mHandler);
            updateUI();
            this.mShouldUpdateUserList = false;
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mUserCaps.mEnabled) {
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            AddUserWhenLockedPreferenceController addUserWhenLockedPreferenceController = this.mAddUserWhenLockedPreferenceController;
            addUserWhenLockedPreferenceController.updateState(preferenceScreen.findPreference(addUserWhenLockedPreferenceController.getPreferenceKey()));
            if (this.mShouldUpdateUserList) {
                updateUI();
            }
        }
    }

    public void onPause() {
        this.mShouldUpdateUserList = true;
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        UserCapabilities userCapabilities = this.mUserCaps;
        if (userCapabilities != null && userCapabilities.mEnabled) {
            getActivity().unregisterReceiver(this.mUserChangeReceiver);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mEditUserInfoController.onSaveInstanceState(bundle);
        bundle.putInt("adding_user", this.mAddedUserId);
        bundle.putInt("removing_user", this.mRemovingUserId);
    }

    public void startActivityForResult(Intent intent, int i) {
        this.mEditUserInfoController.startingActivityForResult();
        super.startActivityForResult(intent, i);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean canSwitchUsers = this.mUserManager.canSwitchUsers();
        if (!this.mUserCaps.mIsAdmin && canSwitchUsers) {
            String userName = this.mUserManager.getUserName();
            MenuItem add = menu.add(0, 1, 0, getResources().getString(C1715R.string.user_remove_user_menu, new Object[]{userName}));
            add.setShowAsAction(0);
            RestrictedLockUtilsInternal.setMenuItemAsDisabledByAdmin(getContext(), add, RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getContext(), "no_remove_user", UserHandle.myUserId()));
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1) {
            return super.onOptionsItemSelected(menuItem);
        }
        onRemoveUserClicked(UserHandle.myUserId());
        return true;
    }

    public void onMultiUserSwitchChanged(boolean z) {
        updateUI();
    }

    private void updateUI() {
        this.mUserCaps.updateAddUserCapabilities(getActivity());
        loadProfile();
        updateUserList();
    }

    private void loadProfile() {
        if (this.mUserCaps.mIsGuest) {
            this.mMePreference.setIcon(getEncircledDefaultIcon());
            this.mMePreference.setTitle((int) C1715R.string.user_exit_guest_title);
            return;
        }
        new AsyncTask<Void, Void, String>() {
            /* access modifiers changed from: protected */
            public void onPostExecute(String str) {
                UserSettings.this.finishLoadProfile(str);
            }

            /* access modifiers changed from: protected */
            public String doInBackground(Void... voidArr) {
                UserInfo userInfo = UserSettings.this.mUserManager.getUserInfo(UserHandle.myUserId());
                String str = userInfo.iconPath;
                if (str == null || str.equals("")) {
                    UserSettings.copyMeProfilePhoto(UserSettings.this.getActivity(), userInfo);
                }
                return userInfo.name;
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void finishLoadProfile(String str) {
        if (getActivity() != null) {
            this.mMePreference.setTitle((CharSequence) getString(C1715R.string.user_you, str));
            int myUserId = UserHandle.myUserId();
            Bitmap userIcon = this.mUserManager.getUserIcon(myUserId);
            if (userIcon != null) {
                this.mMePreference.setIcon(encircle(userIcon));
                this.mUserIcons.put(myUserId, userIcon);
            }
        }
    }

    private boolean hasLockscreenSecurity() {
        return new LockPatternUtils(getActivity()).isSecure(UserHandle.myUserId());
    }

    /* access modifiers changed from: private */
    public void launchChooseLockscreen() {
        Intent intent = new Intent("android.app.action.SET_NEW_PASSWORD");
        intent.putExtra("minimum_quality", 65536);
        startActivityForResult(intent, 10);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10) {
            this.mEditUserInfoController.onActivityResult(i, i2, intent);
        } else if (i2 != 0 && hasLockscreenSecurity()) {
            addUserNow(2);
        }
    }

    /* access modifiers changed from: private */
    public void onAddUserClicked(int i) {
        synchronized (this.mUserLock) {
            if (this.mRemovingUserId == -1 && !this.mAddingUser) {
                if (i == 1) {
                    showDialog(2);
                } else if (i == 2) {
                    if (hasLockscreenSecurity()) {
                        addUserNow(2);
                    } else {
                        showDialog(7);
                    }
                }
            }
        }
    }

    private void onRemoveUserClicked(int i) {
        synchronized (this.mUserLock) {
            if (this.mRemovingUserId == -1 && !this.mAddingUser) {
                this.mRemovingUserId = i;
                showDialog(1);
            }
        }
    }

    /* access modifiers changed from: private */
    public UserInfo createRestrictedProfile() {
        UserInfo createRestrictedProfile = this.mUserManager.createRestrictedProfile(this.mAddingUserName);
        if (createRestrictedProfile == null || assignDefaultPhoto(getActivity(), createRestrictedProfile.id)) {
            return createRestrictedProfile;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public UserInfo createTrustedUser() {
        UserInfo createUser = this.mUserManager.createUser(this.mAddingUserName, 0);
        if (createUser == null || assignDefaultPhoto(getActivity(), createUser.id)) {
            return createUser;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void onManageUserClicked(int i, boolean z) {
        this.mAddingUser = false;
        if (i == -11) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("guest_user", true);
            new SubSettingLauncher(getContext()).setDestination(UserDetailsSettings.class.getName()).setArguments(bundle).setTitleRes(C1715R.string.user_guest).setSourceMetricsCategory(getMetricsCategory()).launch();
            return;
        }
        UserInfo userInfo = this.mUserManager.getUserInfo(i);
        if (userInfo.isRestricted() && this.mUserCaps.mIsAdmin) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("user_id", i);
            bundle2.putBoolean("new_user", z);
            new SubSettingLauncher(getContext()).setDestination(RestrictedProfileSettings.class.getName()).setArguments(bundle2).setTitleRes(C1715R.string.user_restrictions_title).setSourceMetricsCategory(getMetricsCategory()).launch();
        } else if (userInfo.id == UserHandle.myUserId()) {
            OwnerInfoSettings.show(this);
        } else if (this.mUserCaps.mIsAdmin) {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("user_id", i);
            new SubSettingLauncher(getContext()).setDestination(UserDetailsSettings.class.getName()).setArguments(bundle3).setTitleText(userInfo.name).setSourceMetricsCategory(getMetricsCategory()).launch();
        }
    }

    /* access modifiers changed from: private */
    public void onUserCreated(int i) {
        this.mAddedUserId = i;
        this.mAddingUser = false;
        if (!isResumed()) {
            Log.w("UserSettings", "Cannot show dialog after onPause");
        } else if (this.mUserManager.getUserInfo(i).isRestricted()) {
            showDialog(4);
        } else {
            showDialog(3);
        }
    }

    public void onDialogShowing() {
        super.onDialogShowing();
        setOnDismissListener(this);
    }

    public Dialog onCreateDialog(int i) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        final int i2 = 2;
        switch (i) {
            case 1:
                return UserDialogs.createRemoveDialog(getActivity(), this.mRemovingUserId, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserSettings.this.removeUserNow();
                    }
                });
            case 2:
                final SharedPreferences preferences = getActivity().getPreferences(0);
                final boolean z = preferences.getBoolean("key_add_user_long_message_displayed", false);
                int i3 = z ? C1715R.string.user_add_user_message_short : C1715R.string.user_add_user_message_long;
                if (i == 2) {
                    i2 = 1;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle((int) C1715R.string.user_add_user_title);
                builder.setMessage(i3);
                builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserSettings.this.addUserNow(i2);
                        if (!z) {
                            preferences.edit().putBoolean("key_add_user_long_message_displayed", true).apply();
                        }
                    }
                });
                builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
                return builder.create();
            case 3:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(activity);
                builder2.setTitle((int) C1715R.string.user_setup_dialog_title);
                builder2.setMessage((int) C1715R.string.user_setup_dialog_message);
                builder2.setPositiveButton((int) C1715R.string.user_setup_button_setup_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserSettings userSettings = UserSettings.this;
                        userSettings.switchUserNow(userSettings.mAddedUserId);
                    }
                });
                builder2.setNegativeButton((int) C1715R.string.user_setup_button_setup_later, (DialogInterface.OnClickListener) null);
                return builder2.create();
            case 4:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(activity);
                builder3.setMessage((int) C1715R.string.user_setup_profile_dialog_message);
                builder3.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserSettings userSettings = UserSettings.this;
                        userSettings.switchUserNow(userSettings.mAddedUserId);
                    }
                });
                builder3.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
                return builder3.create();
            case 5:
                AlertDialog.Builder builder4 = new AlertDialog.Builder(activity);
                builder4.setMessage((int) C1715R.string.user_cannot_manage_message);
                builder4.setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
                return builder4.create();
            case 6:
                ArrayList arrayList = new ArrayList();
                HashMap hashMap = new HashMap();
                hashMap.put("title", getString(C1715R.string.user_add_user_item_title));
                hashMap.put("summary", getString(C1715R.string.user_add_user_item_summary));
                HashMap hashMap2 = new HashMap();
                hashMap2.put("title", getString(C1715R.string.user_add_profile_item_title));
                hashMap2.put("summary", getString(C1715R.string.user_add_profile_item_summary));
                arrayList.add(hashMap);
                arrayList.add(hashMap2);
                AlertDialog.Builder builder5 = new AlertDialog.Builder(activity);
                SimpleAdapter simpleAdapter = new SimpleAdapter(builder5.getContext(), arrayList, C1715R.layout.two_line_list_item, new String[]{"title", "summary"}, new int[]{C1715R.C1718id.title, C1715R.C1718id.summary});
                builder5.setTitle((int) C1715R.string.user_add_user_type_title);
                builder5.setAdapter(simpleAdapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserSettings.this.onAddUserClicked(i == 0 ? 1 : 2);
                    }
                });
                return builder5.create();
            case 7:
                AlertDialog.Builder builder6 = new AlertDialog.Builder(activity);
                builder6.setMessage((int) C1715R.string.user_need_lock_message);
                builder6.setPositiveButton((int) C1715R.string.user_set_lock_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserSettings.this.launchChooseLockscreen();
                    }
                });
                builder6.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
                return builder6.create();
            case 8:
                AlertDialog.Builder builder7 = new AlertDialog.Builder(activity);
                builder7.setTitle((int) C1715R.string.user_exit_guest_confirm_title);
                builder7.setMessage((int) C1715R.string.user_exit_guest_confirm_message);
                builder7.setPositiveButton((int) C1715R.string.user_exit_guest_dialog_remove, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserSettings.this.exitGuest();
                    }
                });
                builder7.setNegativeButton(17039360, (DialogInterface.OnClickListener) null);
                return builder7.create();
            case 9:
                return this.mEditUserInfoController.createDialog(this, (Drawable) null, this.mMePreference.getTitle(), C1715R.string.profile_info_settings_title, this, Process.myUserHandle());
            default:
                return null;
        }
    }

    /* access modifiers changed from: private */
    public void removeUserNow() {
        if (this.mRemovingUserId == UserHandle.myUserId()) {
            removeThisUser();
        } else {
            new Thread() {
                public void run() {
                    synchronized (UserSettings.this.mUserLock) {
                        UserSettings.this.mUserManager.removeUser(UserSettings.this.mRemovingUserId);
                        UserSettings.this.mHandler.sendEmptyMessage(1);
                    }
                }
            }.start();
        }
    }

    private void removeThisUser() {
        if (!this.mUserManager.canSwitchUsers()) {
            Log.w("UserSettings", "Cannot remove current user when switching is disabled");
            return;
        }
        try {
            ActivityManager.getService().switchUser(0);
            ((UserManager) getContext().getSystemService(UserManager.class)).removeUser(UserHandle.myUserId());
        } catch (RemoteException unused) {
            Log.e("UserSettings", "Unable to remove self user");
        }
    }

    /* access modifiers changed from: private */
    public void addUserNow(final int i) {
        String str;
        synchronized (this.mUserLock) {
            this.mAddingUser = true;
            if (i == 1) {
                str = getString(C1715R.string.user_new_user_name);
            } else {
                str = getString(C1715R.string.user_new_profile_name);
            }
            this.mAddingUserName = str;
            new Thread() {
                public void run() {
                    UserInfo userInfo;
                    if (i == 1) {
                        userInfo = UserSettings.this.createTrustedUser();
                    } else {
                        userInfo = UserSettings.this.createRestrictedProfile();
                    }
                    if (userInfo == null) {
                        boolean unused = UserSettings.this.mAddingUser = false;
                        return;
                    }
                    synchronized (UserSettings.this.mUserLock) {
                        if (i == 1) {
                            UserSettings.this.mHandler.sendEmptyMessage(1);
                            if (!UserSettings.this.mUserCaps.mDisallowSwitchUser) {
                                UserSettings.this.mHandler.sendMessage(UserSettings.this.mHandler.obtainMessage(2, userInfo.id, userInfo.serialNumber));
                            }
                        } else {
                            UserSettings.this.mHandler.sendMessage(UserSettings.this.mHandler.obtainMessage(3, userInfo.id, userInfo.serialNumber));
                        }
                    }
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    public void switchUserNow(int i) {
        try {
            ActivityManager.getService().switchUser(i);
        } catch (RemoteException unused) {
        }
    }

    /* access modifiers changed from: private */
    public void exitGuest() {
        if (this.mUserCaps.mIsGuest) {
            removeThisUser();
        }
    }

    /* access modifiers changed from: package-private */
    public void updateUserList() {
        UserPreference userPreference;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            List<UserInfo> users = this.mUserManager.getUsers(true);
            boolean isVoiceCapable = Utils.isVoiceCapable(activity);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int i = -11;
            arrayList2.add(this.mMePreference);
            for (UserInfo userInfo : users) {
                if (userInfo.supportsSwitchToByUser()) {
                    if (userInfo.id == UserHandle.myUserId()) {
                        userPreference = this.mMePreference;
                    } else if (userInfo.isGuest()) {
                        i = userInfo.id;
                    } else {
                        boolean z = this.mUserCaps.mIsAdmin && (isVoiceCapable || userInfo.isRestricted());
                        boolean z2 = this.mUserCaps.mIsAdmin && !isVoiceCapable && !userInfo.isRestricted() && !userInfo.isGuest();
                        Context prefContext = getPrefContext();
                        int i2 = userInfo.id;
                        UserSettings userSettings = z ? this : null;
                        UserPreference userPreference2 = r13;
                        UserPreference userPreference3 = new UserPreference(prefContext, (AttributeSet) null, i2, userSettings, z2 ? this : null);
                        userPreference2.setKey("id=" + userInfo.id);
                        arrayList2.add(userPreference2);
                        if (userInfo.isAdmin()) {
                            userPreference2.setSummary((int) C1715R.string.user_admin);
                        }
                        userPreference2.setTitle((CharSequence) userInfo.name);
                        userPreference2.setSelectable(false);
                        userPreference = userPreference2;
                    }
                    if (userPreference != null) {
                        if (!isInitialized(userInfo)) {
                            if (userInfo.isRestricted()) {
                                userPreference.setSummary((int) C1715R.string.user_summary_restricted_not_set_up);
                            } else {
                                userPreference.setSummary((int) C1715R.string.user_summary_not_set_up);
                            }
                            if (!this.mUserCaps.mDisallowSwitchUser) {
                                userPreference.setOnPreferenceClickListener(this);
                                userPreference.setSelectable(this.mUserManager.canSwitchUsers());
                            }
                        } else if (userInfo.isRestricted()) {
                            userPreference.setSummary((int) C1715R.string.user_summary_restricted_profile);
                        }
                        if (userInfo.iconPath == null) {
                            userPreference.setIcon(getEncircledDefaultIcon());
                        } else if (this.mUserIcons.get(userInfo.id) == null) {
                            arrayList.add(Integer.valueOf(userInfo.id));
                            userPreference.setIcon(getEncircledDefaultIcon());
                        } else {
                            setPhotoId(userPreference, userInfo);
                        }
                    }
                }
            }
            if (this.mAddingUser) {
                UserPreference userPreference4 = new UserPreference(getPrefContext(), (AttributeSet) null, -10, (View.OnClickListener) null, (View.OnClickListener) null);
                userPreference4.setEnabled(false);
                userPreference4.setTitle((CharSequence) this.mAddingUserName);
                userPreference4.setIcon(getEncircledDefaultIcon());
                arrayList2.add(userPreference4);
            }
            UserCapabilities userCapabilities = this.mUserCaps;
            if (!userCapabilities.mIsGuest && (userCapabilities.mCanAddGuest || userCapabilities.mDisallowAddUserSetByAdmin)) {
                UserPreference userPreference5 = new UserPreference(getPrefContext(), (AttributeSet) null, -11, (!this.mUserCaps.mIsAdmin || !isVoiceCapable) ? null : this, (View.OnClickListener) null);
                userPreference5.setTitle((int) C1715R.string.user_guest);
                userPreference5.setIcon(getEncircledDefaultIcon());
                userPreference5.setKey("user_guest");
                arrayList2.add(userPreference5);
                UserCapabilities userCapabilities2 = this.mUserCaps;
                if (userCapabilities2.mDisallowAddUser) {
                    userPreference5.setDisabledByAdmin(userCapabilities2.mEnforcedAdmin);
                } else if (userCapabilities2.mDisallowSwitchUser) {
                    userPreference5.setDisabledByAdmin(RestrictedLockUtilsInternal.getDeviceOwner(activity));
                } else {
                    userPreference5.setDisabledByAdmin((RestrictedLockUtils.EnforcedAdmin) null);
                }
                if (!this.mUserManager.canSwitchUsers()) {
                    userPreference5.setSelectable(false);
                }
                userPreference5.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(i) {
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final boolean onPreferenceClick(Preference preference) {
                        return UserSettings.this.lambda$updateUserList$0$UserSettings(this.f$1, preference);
                    }
                });
            }
            Collections.sort(arrayList2, UserPreference.SERIAL_NUMBER_COMPARATOR);
            getActivity().invalidateOptionsMenu();
            if (arrayList.size() > 0) {
                loadIconsAsync(arrayList);
            }
            if (this.mUserCaps.mCanAddRestrictedProfile) {
                this.mUserListCategory.setTitle((int) C1715R.string.user_list_title);
            } else {
                this.mUserListCategory.setTitle((CharSequence) null);
            }
            this.mUserListCategory.removeAll();
            this.mAddUserWhenLockedPreferenceController.updateState(getPreferenceScreen().findPreference(this.mAddUserWhenLockedPreferenceController.getPreferenceKey()));
            this.mMultiUserFooterPreferenceController.updateState((Preference) null);
            this.mUserListCategory.setVisible(this.mUserCaps.mUserSwitcherEnabled);
            updateAddUser(activity);
            if (this.mUserCaps.mUserSwitcherEnabled) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    UserPreference userPreference6 = (UserPreference) it.next();
                    userPreference6.setOrder(Integer.MAX_VALUE);
                    this.mUserListCategory.addPreference(userPreference6);
                }
            }
        }
    }

    public /* synthetic */ boolean lambda$updateUserList$0$UserSettings(int i, Preference preference) {
        UserInfo createGuest;
        if (i == -11 && (createGuest = this.mUserManager.createGuest(getContext(), preference.getTitle().toString())) != null) {
            i = createGuest.id;
        }
        try {
            ActivityManager.getService().switchUser(i);
            return true;
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return true;
        }
    }

    private void updateAddUser(Context context) {
        UserCapabilities userCapabilities = this.mUserCaps;
        if ((userCapabilities.mCanAddUser || userCapabilities.mDisallowAddUserSetByAdmin) && Utils.isDeviceProvisioned(context) && this.mUserCaps.mUserSwitcherEnabled) {
            this.mAddUser.setVisible(true);
            boolean canAddMoreUsers = this.mUserManager.canAddMoreUsers();
            this.mAddUser.setEnabled(canAddMoreUsers && !this.mAddingUser && this.mUserManager.canSwitchUsers());
            RestrictedLockUtils.EnforcedAdmin enforcedAdmin = null;
            if (!canAddMoreUsers) {
                this.mAddUser.setSummary((CharSequence) getString(C1715R.string.user_add_max_count, Integer.valueOf(getMaxRealUsers())));
            } else {
                this.mAddUser.setSummary((CharSequence) null);
            }
            if (this.mAddUser.isEnabled()) {
                RestrictedPreference restrictedPreference = this.mAddUser;
                UserCapabilities userCapabilities2 = this.mUserCaps;
                if (userCapabilities2.mDisallowAddUser) {
                    enforcedAdmin = userCapabilities2.mEnforcedAdmin;
                }
                restrictedPreference.setDisabledByAdmin(enforcedAdmin);
                return;
            }
            return;
        }
        this.mAddUser.setVisible(false);
    }

    private int getMaxRealUsers() {
        int maxSupportedUsers = UserManager.getMaxSupportedUsers() + 1;
        int i = 0;
        for (UserInfo isManagedProfile : this.mUserManager.getUsers()) {
            if (isManagedProfile.isManagedProfile()) {
                i++;
            }
        }
        return maxSupportedUsers - i;
    }

    private void loadIconsAsync(List<Integer> list) {
        new AsyncTask<List<Integer>, Void, Void>() {
            /* access modifiers changed from: protected */
            public void onPostExecute(Void voidR) {
                UserSettings.this.updateUserList();
            }

            /* access modifiers changed from: protected */
            public Void doInBackground(List<Integer>... listArr) {
                for (Integer intValue : listArr[0]) {
                    int intValue2 = intValue.intValue();
                    Bitmap userIcon = UserSettings.this.mUserManager.getUserIcon(intValue2);
                    if (userIcon == null) {
                        userIcon = UserSettings.getDefaultUserIconAsBitmap(UserSettings.this.getContext().getResources(), intValue2);
                    }
                    UserSettings.this.mUserIcons.append(intValue2, userIcon);
                }
                return null;
            }
        }.execute(new List[]{list});
    }

    private Drawable getEncircledDefaultIcon() {
        if (this.mDefaultIconDrawable == null) {
            this.mDefaultIconDrawable = encircle(getDefaultUserIconAsBitmap(getContext().getResources(), -10000));
        }
        return this.mDefaultIconDrawable;
    }

    private void setPhotoId(Preference preference, UserInfo userInfo) {
        Bitmap bitmap = this.mUserIcons.get(userInfo.id);
        if (bitmap != null) {
            preference.setIcon(encircle(bitmap));
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        if (preference == this.mMePreference) {
            if (this.mUserCaps.mIsGuest) {
                showDialog(8);
                return true;
            } else if (this.mUserManager.isLinkedUser()) {
                onManageUserClicked(UserHandle.myUserId(), false);
            } else {
                showDialog(9);
            }
        } else if (preference instanceof UserPreference) {
            UserInfo userInfo = this.mUserManager.getUserInfo(((UserPreference) preference).getUserId());
            if (!isInitialized(userInfo)) {
                Handler handler = this.mHandler;
                handler.sendMessage(handler.obtainMessage(2, userInfo.id, userInfo.serialNumber));
            }
        } else if (preference == this.mAddUser) {
            if (this.mUserCaps.mCanAddRestrictedProfile) {
                showDialog(6);
            } else {
                onAddUserClicked(1);
            }
        }
        return false;
    }

    private boolean isInitialized(UserInfo userInfo) {
        return (userInfo.flags & 16) != 0;
    }

    private Drawable encircle(Bitmap bitmap) {
        return CircleFramedDrawable.getInstance(getActivity(), bitmap);
    }

    public void onClick(View view) {
        if (view.getTag() instanceof UserPreference) {
            int userId = ((UserPreference) view.getTag()).getUserId();
            int id = view.getId();
            if (id == C1715R.C1718id.manage_user) {
                onManageUserClicked(userId, false);
            } else if (id == C1715R.C1718id.trash_user) {
                RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getContext(), "no_remove_user", UserHandle.myUserId());
                if (checkIfRestrictionEnforced != null) {
                    RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), checkIfRestrictionEnforced);
                } else {
                    onRemoveUserClicked(userId);
                }
            }
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        synchronized (this.mUserLock) {
            this.mRemovingUserId = -1;
            updateUserList();
        }
    }

    public void onPhotoChanged(Drawable drawable) {
        this.mMePreference.setIcon(drawable);
    }

    public void onLabelChanged(CharSequence charSequence) {
        this.mMePreference.setTitle(charSequence);
    }

    /* access modifiers changed from: private */
    public static Bitmap getDefaultUserIconAsBitmap(Resources resources, int i) {
        Bitmap bitmap = sDarkDefaultUserBitmapCache.get(i);
        if (bitmap != null) {
            return bitmap;
        }
        Bitmap convertToBitmap = UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(resources, i, false));
        sDarkDefaultUserBitmapCache.put(i, convertToBitmap);
        return convertToBitmap;
    }

    static boolean assignDefaultPhoto(Context context, int i) {
        if (context == null) {
            return false;
        }
        ((UserManager) context.getSystemService("user")).setUserIcon(i, getDefaultUserIconAsBitmap(context.getResources(), i));
        return true;
    }

    static void copyMeProfilePhoto(Context context, UserInfo userInfo) {
        Uri uri = ContactsContract.Profile.CONTENT_URI;
        int myUserId = userInfo != null ? userInfo.id : UserHandle.myUserId();
        InputStream openContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), uri, true);
        if (openContactPhotoInputStream == null) {
            assignDefaultPhoto(context, myUserId);
            return;
        }
        ((UserManager) context.getSystemService("user")).setUserIcon(myUserId, BitmapFactory.decodeStream(openContactPhotoInputStream));
        try {
            openContactPhotoInputStream.close();
        } catch (IOException unused) {
        }
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Context mContext;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
        }

        public void setListening(boolean z) {
            if (z) {
                UserInfo userInfo = ((UserManager) this.mContext.getSystemService(UserManager.class)).getUserInfo(UserHandle.myUserId());
                this.mSummaryLoader.setSummary(this, this.mContext.getString(C1715R.string.users_summary, new Object[]{userInfo.name}));
            }
        }
    }

    static /* synthetic */ SummaryLoader.SummaryProvider lambda$static$1(Activity activity, SummaryLoader summaryLoader) {
        return new SummaryProvider(activity, summaryLoader);
    }
}
