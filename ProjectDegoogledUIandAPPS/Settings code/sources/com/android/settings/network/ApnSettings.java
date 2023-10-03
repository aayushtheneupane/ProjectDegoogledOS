package com.android.settings.network;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Telephony;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.telephony.uicc.UiccController;
import com.android.settings.RestrictedSettingsFragment;
import com.android.settingslib.RestrictedLockUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Iterator;

public class ApnSettings extends RestrictedSettingsFragment implements Preference.OnPreferenceChangeListener {
    private static final String[] CARRIERS_PROJECTION = {"_id", "name", "apn", "type", "mvno_type", "mvno_match_data", "edited"};
    /* access modifiers changed from: private */
    public static final Uri DEFAULTAPN_URI = Uri.parse("content://telephony/carriers/restore");
    private static final Uri PREFERAPN_URI = Uri.parse("content://telephony/carriers/preferapn");
    /* access modifiers changed from: private */
    public static boolean mRestoreDefaultApnMode;
    private boolean mAllowAddingApns;
    private boolean mHideImsApn;
    private boolean mHidePresetApnDetails;
    private IntentFilter mIntentFilter;
    private String mMvnoMatchData;
    private String mMvnoType;
    /* access modifiers changed from: private */
    public int mPhoneId;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.ANY_DATA_STATE")) {
                if (C09543.f51x67a69abf[ApnSettings.getMobileDataState(intent).ordinal()] == 1) {
                    if (!ApnSettings.mRestoreDefaultApnMode) {
                        ApnSettings.this.fillList();
                    } else {
                        ApnSettings.this.showDialog(1001);
                    }
                }
            } else if (intent.getAction().equals("android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED") && !ApnSettings.mRestoreDefaultApnMode) {
                int intExtra = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_ID", -1);
                if (SubscriptionManager.isValidSubscriptionId(intExtra) && ApnSettings.this.mPhoneId == SubscriptionManager.getPhoneId(intExtra) && intExtra != ApnSettings.this.mSubId) {
                    int unused = ApnSettings.this.mSubId = intExtra;
                    ApnSettings apnSettings = ApnSettings.this;
                    SubscriptionInfo unused2 = apnSettings.mSubscriptionInfo = apnSettings.getSubscriptionInfo(apnSettings.mSubId);
                }
                ApnSettings.this.fillList();
            }
        }
    };
    private RestoreApnProcessHandler mRestoreApnProcessHandler;
    private RestoreApnUiHandler mRestoreApnUiHandler;
    private HandlerThread mRestoreDefaultApnThread;
    private String mSelectedKey;
    /* access modifiers changed from: private */
    public int mSubId;
    /* access modifiers changed from: private */
    public SubscriptionInfo mSubscriptionInfo;
    private UiccController mUiccController;
    private boolean mUnavailable;
    private UserManager mUserManager;

    public int getDialogMetricsCategory(int i) {
        return i == 1001 ? 579 : 0;
    }

    public int getMetricsCategory() {
        return 12;
    }

    public ApnSettings() {
        super("no_config_mobile_networks");
    }

    /* renamed from: com.android.settings.network.ApnSettings$3 */
    static /* synthetic */ class C09543 {

        /* renamed from: $SwitchMap$com$android$internal$telephony$PhoneConstants$DataState */
        static final /* synthetic */ int[] f51x67a69abf = new int[PhoneConstants.DataState.values().length];

        static {
            try {
                f51x67a69abf[PhoneConstants.DataState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static PhoneConstants.DataState getMobileDataState(Intent intent) {
        String stringExtra = intent.getStringExtra("state");
        if (stringExtra != null) {
            return Enum.valueOf(PhoneConstants.DataState.class, stringExtra);
        }
        return PhoneConstants.DataState.DISCONNECTED;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mSubId = activity.getIntent().getIntExtra("sub_id", -1);
        this.mPhoneId = SubscriptionManager.getPhoneId(this.mSubId);
        this.mIntentFilter = new IntentFilter("android.intent.action.ANY_DATA_STATE");
        this.mIntentFilter.addAction("android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED");
        setIfOnlyAvailableForAdmins(true);
        this.mSubscriptionInfo = getSubscriptionInfo(this.mSubId);
        this.mUiccController = UiccController.getInstance();
        PersistableBundle configForSubId = ((CarrierConfigManager) getSystemService("carrier_config")).getConfigForSubId(this.mSubId);
        this.mHideImsApn = configForSubId.getBoolean("hide_ims_apn_bool");
        this.mAllowAddingApns = configForSubId.getBoolean("allow_adding_apns_bool");
        if (this.mAllowAddingApns && ApnEditor.hasAllApns(configForSubId.getStringArray("read_only_apn_types_string_array"))) {
            Log.d("ApnSettings", "not allowing adding APN because all APN types are read only");
            this.mAllowAddingApns = false;
        }
        this.mHidePresetApnDetails = configForSubId.getBoolean("hide_preset_apn_details_bool");
        this.mUserManager = UserManager.get(activity);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getEmptyTextView().setText(C1715R.string.apn_settings_not_available);
        this.mUnavailable = isUiRestricted();
        setHasOptionsMenu(!this.mUnavailable);
        if (this.mUnavailable) {
            addPreferencesFromResource(C1715R.xml.placeholder_prefs);
        } else {
            addPreferencesFromResource(C1715R.xml.apn_settings);
        }
    }

    public void onResume() {
        super.onResume();
        if (!this.mUnavailable) {
            getActivity().registerReceiver(this.mReceiver, this.mIntentFilter);
            if (!mRestoreDefaultApnMode) {
                fillList();
            }
        }
    }

    public void onPause() {
        super.onPause();
        if (!this.mUnavailable) {
            getActivity().unregisterReceiver(this.mReceiver);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        HandlerThread handlerThread = this.mRestoreDefaultApnThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
    }

    public RestrictedLockUtils.EnforcedAdmin getRestrictionEnforcedAdmin() {
        UserHandle of = UserHandle.of(this.mUserManager.getUserHandle());
        if (!this.mUserManager.hasUserRestriction("no_config_mobile_networks", of) || this.mUserManager.hasBaseUserRestriction("no_config_mobile_networks", of)) {
            return null;
        }
        return RestrictedLockUtils.EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
    }

    /* access modifiers changed from: private */
    public SubscriptionInfo getSubscriptionInfo(int i) {
        return SubscriptionManager.from(getActivity()).getActiveSubscriptionInfo(i);
    }

    /* access modifiers changed from: private */
    public void fillList() {
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        int subscriptionId = subscriptionInfo != null ? subscriptionInfo.getSubscriptionId() : -1;
        Uri withAppendedPath = Uri.withAppendedPath(Telephony.Carriers.SIM_APN_URI, String.valueOf(subscriptionId));
        StringBuilder sb = new StringBuilder("NOT (type='ia' AND (apn=\"\" OR apn IS NULL)) AND user_visible!=0");
        if (this.mHideImsApn) {
            sb.append(" AND NOT (type='ims')");
        }
        Cursor query = getContentResolver().query(withAppendedPath, CARRIERS_PROJECTION, sb.toString(), (String[]) null, "name ASC");
        if (query != null) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference("apn_list");
            preferenceGroup.removeAll();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            this.mSelectedKey = getSelectedApnKey();
            query.moveToFirst();
            while (!query.isAfterLast()) {
                boolean z = true;
                String string = query.getString(1);
                String string2 = query.getString(2);
                String string3 = query.getString(0);
                String string4 = query.getString(3);
                int i = query.getInt(6);
                this.mMvnoType = query.getString(4);
                this.mMvnoMatchData = query.getString(5);
                ApnPreference apnPreference = new ApnPreference(getPrefContext());
                apnPreference.setKey(string3);
                apnPreference.setTitle((CharSequence) string);
                apnPreference.setPersistent(false);
                apnPreference.setOnPreferenceChangeListener(this);
                apnPreference.setSubId(subscriptionId);
                if (!this.mHidePresetApnDetails || i != 0) {
                    apnPreference.setSummary((CharSequence) string2);
                } else {
                    apnPreference.setHideDetails();
                }
                if (string4 != null && string4.equals("mms")) {
                    z = false;
                }
                apnPreference.setSelectable(z);
                if (z) {
                    String str = this.mSelectedKey;
                    if (str != null && str.equals(string3)) {
                        apnPreference.setChecked();
                    }
                    arrayList.add(apnPreference);
                } else {
                    arrayList2.add(apnPreference);
                }
                query.moveToNext();
            }
            query.close();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                preferenceGroup.addPreference((Preference) it.next());
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                preferenceGroup.addPreference((Preference) it2.next());
            }
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (!this.mUnavailable) {
            if (this.mAllowAddingApns) {
                menu.add(0, 1, 0, getResources().getString(C1715R.string.menu_new)).setIcon(C1715R.C1717drawable.ic_add_24dp).setShowAsAction(1);
            }
            menu.add(0, 2, 0, getResources().getString(C1715R.string.menu_restore)).setIcon(17301589);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            addNewApn();
            return true;
        } else if (itemId != 2) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            restoreDefaultApn();
            return true;
        }
    }

    private void addNewApn() {
        Intent intent = new Intent("android.intent.action.INSERT", Telephony.Carriers.CONTENT_URI);
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        intent.putExtra("sub_id", subscriptionInfo != null ? subscriptionInfo.getSubscriptionId() : -1);
        if (!TextUtils.isEmpty(this.mMvnoType) && !TextUtils.isEmpty(this.mMvnoMatchData)) {
            intent.putExtra("mvno_type", this.mMvnoType);
            intent.putExtra("mvno_match_data", this.mMvnoMatchData);
        }
        startActivity(intent);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        Log.d("ApnSettings", "onPreferenceChange(): Preference - " + preference + ", newValue - " + obj + ", newValue type - " + obj.getClass());
        if (!(obj instanceof String)) {
            return true;
        }
        setSelectedApnKey((String) obj);
        return true;
    }

    private void setSelectedApnKey(String str) {
        this.mSelectedKey = str;
        ContentResolver contentResolver = getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("apn_id", this.mSelectedKey);
        contentResolver.update(getUriForCurrSubId(PREFERAPN_URI), contentValues, (String) null, (String[]) null);
    }

    private String getSelectedApnKey() {
        String str;
        Cursor query = getContentResolver().query(getUriForCurrSubId(PREFERAPN_URI), new String[]{"_id"}, (String) null, (String[]) null, "name ASC");
        if (query.getCount() > 0) {
            query.moveToFirst();
            str = query.getString(0);
        } else {
            str = null;
        }
        query.close();
        return str;
    }

    private boolean restoreDefaultApn() {
        showDialog(1001);
        mRestoreDefaultApnMode = true;
        if (this.mRestoreApnUiHandler == null) {
            this.mRestoreApnUiHandler = new RestoreApnUiHandler();
        }
        if (this.mRestoreApnProcessHandler == null || this.mRestoreDefaultApnThread == null) {
            this.mRestoreDefaultApnThread = new HandlerThread("Restore default APN Handler: Process Thread");
            this.mRestoreDefaultApnThread.start();
            this.mRestoreApnProcessHandler = new RestoreApnProcessHandler(this.mRestoreDefaultApnThread.getLooper(), this.mRestoreApnUiHandler);
        }
        this.mRestoreApnProcessHandler.sendEmptyMessage(1);
        return true;
    }

    /* access modifiers changed from: private */
    public Uri getUriForCurrSubId(Uri uri) {
        SubscriptionInfo subscriptionInfo = this.mSubscriptionInfo;
        int subscriptionId = subscriptionInfo != null ? subscriptionInfo.getSubscriptionId() : -1;
        if (!SubscriptionManager.isValidSubscriptionId(subscriptionId)) {
            return uri;
        }
        return Uri.withAppendedPath(uri, "subId/" + String.valueOf(subscriptionId));
    }

    private class RestoreApnUiHandler extends Handler {
        private RestoreApnUiHandler() {
        }

        public void handleMessage(Message message) {
            if (message.what == 2) {
                FragmentActivity activity = ApnSettings.this.getActivity();
                if (activity == null) {
                    boolean unused = ApnSettings.mRestoreDefaultApnMode = false;
                    return;
                }
                ApnSettings.this.fillList();
                ApnSettings.this.getPreferenceScreen().setEnabled(true);
                boolean unused2 = ApnSettings.mRestoreDefaultApnMode = false;
                ApnSettings.this.removeDialog(1001);
                Toast.makeText(activity, ApnSettings.this.getResources().getString(C1715R.string.restore_default_apn_completed), 1).show();
            }
        }
    }

    private class RestoreApnProcessHandler extends Handler {
        private Handler mRestoreApnUiHandler;

        public RestoreApnProcessHandler(Looper looper, Handler handler) {
            super(looper);
            this.mRestoreApnUiHandler = handler;
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                ApnSettings.this.getContentResolver().delete(ApnSettings.this.getUriForCurrSubId(ApnSettings.DEFAULTAPN_URI), (String) null, (String[]) null);
                this.mRestoreApnUiHandler.sendEmptyMessage(2);
            }
        }
    }

    public Dialog onCreateDialog(int i) {
        if (i != 1001) {
            return null;
        }
        C09532 r2 = new ProgressDialog(getActivity()) {
            public boolean onTouchEvent(MotionEvent motionEvent) {
                return true;
            }
        };
        r2.setMessage(getResources().getString(C1715R.string.restore_default_apn));
        r2.setCancelable(false);
        return r2;
    }
}
