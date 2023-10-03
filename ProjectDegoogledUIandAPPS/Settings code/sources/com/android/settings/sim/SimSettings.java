package com.android.settings.sim;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.SearchIndexableResource;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.RestrictedSettingsFragment;
import com.android.settings.Utils;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class SimSettings extends RestrictedSettingsFragment implements Indexable {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            if (Utils.showSimCardTile(context)) {
                SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
                searchIndexableResource.xmlResId = C1715R.xml.sim_settings;
                arrayList.add(searchIndexableResource);
            }
            return arrayList;
        }
    };
    private List<SubscriptionInfo> mAvailableSubInfos = null;
    /* access modifiers changed from: private */
    public int[] mCallState;
    private Context mContext;
    private int mNumSlots;
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangeListener;
    private int mPhoneCount = TelephonyManager.getDefault().getPhoneCount();
    private PhoneStateListener[] mPhoneStateListener;
    private List<SubscriptionInfo> mSelectableSubInfos = null;
    private PreferenceScreen mSimCards = null;
    private List<SubscriptionInfo> mSubInfoList = null;
    private SubscriptionManager mSubscriptionManager;

    public int getMetricsCategory() {
        return 88;
    }

    public SimSettings() {
        super("no_config_sim");
        int i = this.mPhoneCount;
        this.mCallState = new int[i];
        this.mPhoneStateListener = new PhoneStateListener[i];
        this.mOnSubscriptionsChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener() {
            public void onSubscriptionsChanged() {
                SimSettings.this.updateSubscriptions();
            }
        };
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
        this.mSubscriptionManager = SubscriptionManager.from(getActivity());
        addPreferencesFromResource(C1715R.xml.sim_settings);
        this.mNumSlots = ((TelephonyManager) getActivity().getSystemService("phone")).getSimCount();
        this.mSimCards = (PreferenceScreen) findPreference("sim_cards");
        this.mAvailableSubInfos = new ArrayList(this.mNumSlots);
        this.mSelectableSubInfos = new ArrayList();
        SimSelectNotification.cancelSimSelectNotification(getActivity());
    }

    /* access modifiers changed from: private */
    public void updateSubscriptions() {
        this.mSubInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        for (int i = 0; i < this.mNumSlots; i++) {
            Preference findPreference = this.mSimCards.findPreference("sim" + i);
            if (findPreference instanceof SimPreference) {
                this.mSimCards.removePreference(findPreference);
            }
        }
        this.mAvailableSubInfos.clear();
        this.mSelectableSubInfos.clear();
        for (int i2 = 0; i2 < this.mNumSlots; i2++) {
            SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = this.mSubscriptionManager.getActiveSubscriptionInfoForSimSlotIndex(i2);
            SimPreference simPreference = new SimPreference(getPrefContext(), activeSubscriptionInfoForSimSlotIndex, i2);
            simPreference.setOrder(i2 - this.mNumSlots);
            this.mSimCards.addPreference(simPreference);
            this.mAvailableSubInfos.add(activeSubscriptionInfoForSimSlotIndex);
            if (activeSubscriptionInfoForSimSlotIndex != null) {
                this.mSelectableSubInfos.add(activeSubscriptionInfoForSimSlotIndex);
            }
        }
        updateAllOptions();
    }

    private void updateAllOptions() {
        updateSimSlotValues();
        updateActivitesCategory();
    }

    private void updateSimSlotValues() {
        int preferenceCount = this.mSimCards.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = this.mSimCards.getPreference(i);
            if (preference instanceof SimPreference) {
                ((SimPreference) preference).update();
            }
        }
    }

    private void updateActivitesCategory() {
        updateCellularDataValues();
        updateCallValues();
        updateSmsValues();
    }

    private void updateSmsValues() {
        Preference findPreference = findPreference("sim_sms");
        SubscriptionInfo defaultSmsSubscriptionInfo = this.mSubscriptionManager.getDefaultSmsSubscriptionInfo();
        findPreference.setTitle((int) C1715R.string.sms_messages_title);
        boolean z = false;
        if (defaultSmsSubscriptionInfo != null) {
            findPreference.setSummary(defaultSmsSubscriptionInfo.getDisplayName());
            if (this.mSelectableSubInfos.size() > 1) {
                z = true;
            }
            findPreference.setEnabled(z);
        } else if (defaultSmsSubscriptionInfo == null) {
            findPreference.setSummary((int) C1715R.string.sim_selection_required_pref);
            if (this.mSelectableSubInfos.size() >= 1) {
                z = true;
            }
            findPreference.setEnabled(z);
        }
    }

    /* access modifiers changed from: private */
    public void updateCellularDataValues() {
        Preference findPreference = findPreference("sim_cellular_data");
        SubscriptionInfo defaultDataSubscriptionInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
        findPreference.setTitle((int) C1715R.string.cellular_data_title);
        boolean isCallStateIdle = isCallStateIdle();
        boolean z = false;
        boolean z2 = SystemProperties.getBoolean("ril.cdma.inecmmode", false);
        if (defaultDataSubscriptionInfo != null) {
            findPreference.setSummary(defaultDataSubscriptionInfo.getDisplayName());
            if (this.mSelectableSubInfos.size() > 1 && isCallStateIdle && !z2) {
                z = true;
            }
            findPreference.setEnabled(z);
        } else if (defaultDataSubscriptionInfo == null) {
            findPreference.setSummary((int) C1715R.string.sim_selection_required_pref);
            if (this.mSelectableSubInfos.size() >= 1 && isCallStateIdle && !z2) {
                z = true;
            }
            findPreference.setEnabled(z);
        }
    }

    private void updateCallValues() {
        String str;
        Preference findPreference = findPreference("sim_calls");
        TelecomManager from = TelecomManager.from(this.mContext);
        PhoneAccountHandle userSelectedOutgoingPhoneAccount = from.getUserSelectedOutgoingPhoneAccount();
        List<PhoneAccountHandle> callCapablePhoneAccounts = from.getCallCapablePhoneAccounts();
        findPreference.setTitle((int) C1715R.string.calls_title);
        if (userSelectedOutgoingPhoneAccount == null) {
            str = this.mContext.getResources().getString(C1715R.string.sim_calls_ask_first_prefs_title);
        } else {
            str = (String) from.getPhoneAccount(userSelectedOutgoingPhoneAccount).getLabel();
        }
        findPreference.setSummary((CharSequence) str);
        boolean z = true;
        if (callCapablePhoneAccounts.size() <= 1) {
            z = false;
        }
        findPreference.setEnabled(z);
    }

    public void onResume() {
        super.onResume();
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        updateSubscriptions();
        TelephonyManager telephonyManager = (TelephonyManager) getActivity().getSystemService("phone");
        if (this.mSelectableSubInfos.size() > 1) {
            Log.d("SimSettings", "Register for call state change");
            for (int i = 0; i < this.mPhoneCount; i++) {
                telephonyManager.createForSubscriptionId(this.mSelectableSubInfos.get(i).getSubscriptionId()).listen(getPhoneStateListener(i), 32);
            }
        }
    }

    public void onPause() {
        super.onPause();
        this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
        for (int i = 0; i < this.mPhoneCount; i++) {
            PhoneStateListener[] phoneStateListenerArr = this.mPhoneStateListener;
            if (phoneStateListenerArr[i] != null) {
                telephonyManager.listen(phoneStateListenerArr[i], 0);
                this.mPhoneStateListener[i] = null;
            }
        }
    }

    private PhoneStateListener getPhoneStateListener(final int i) {
        this.mPhoneStateListener[i] = new PhoneStateListener() {
            public void onCallStateChanged(int i, String str) {
                SimSettings.this.mCallState[i] = i;
                SimSettings.this.updateCellularDataValues();
            }
        };
        return this.mPhoneStateListener[i];
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        Context context = this.mContext;
        Intent intent = new Intent(context, SimDialogActivity.class);
        intent.addFlags(268435456);
        if (preference instanceof SimPreference) {
            Intent intent2 = new Intent(context, SimPreferenceDialog.class);
            intent2.putExtra("slot_id", ((SimPreference) preference).getSlotId());
            startActivity(intent2);
        } else if (findPreference("sim_cellular_data") == preference) {
            intent.putExtra(SimDialogActivity.DIALOG_TYPE_KEY, 0);
            context.startActivity(intent);
        } else if (findPreference("sim_calls") == preference) {
            intent.putExtra(SimDialogActivity.DIALOG_TYPE_KEY, 1);
            context.startActivity(intent);
        } else if (findPreference("sim_sms") == preference) {
            intent.putExtra(SimDialogActivity.DIALOG_TYPE_KEY, 2);
            context.startActivity(intent);
        }
        return true;
    }

    private class SimPreference extends Preference {
        Context mContext;
        private int mSlotId;
        private SubscriptionInfo mSubInfoRecord;

        public SimPreference(Context context, SubscriptionInfo subscriptionInfo, int i) {
            super(context);
            this.mContext = context;
            this.mSubInfoRecord = subscriptionInfo;
            this.mSlotId = i;
            setKey("sim" + this.mSlotId);
            update();
        }

        public void update() {
            Resources resources = this.mContext.getResources();
            setTitle((CharSequence) String.format(this.mContext.getResources().getString(C1715R.string.sim_editor_title), new Object[]{Integer.valueOf(this.mSlotId + 1)}));
            SubscriptionInfo subscriptionInfo = this.mSubInfoRecord;
            if (subscriptionInfo != null) {
                if (TextUtils.isEmpty(SimSettings.this.getPhoneNumber(subscriptionInfo))) {
                    setSummary(this.mSubInfoRecord.getDisplayName());
                } else {
                    setSummary((CharSequence) this.mSubInfoRecord.getDisplayName() + " - " + PhoneNumberUtils.createTtsSpannable(SimSettings.this.getPhoneNumber(this.mSubInfoRecord)));
                    setEnabled(true);
                }
                setIcon((Drawable) new BitmapDrawable(resources, this.mSubInfoRecord.createIconBitmap(this.mContext)));
                return;
            }
            setSummary((int) C1715R.string.sim_slot_empty);
            setFragment((String) null);
            setEnabled(false);
        }

        /* access modifiers changed from: private */
        public int getSlotId() {
            return this.mSlotId;
        }
    }

    /* access modifiers changed from: private */
    public String getPhoneNumber(SubscriptionInfo subscriptionInfo) {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).getLine1Number(subscriptionInfo.getSubscriptionId());
    }

    private boolean isCallStateIdle() {
        boolean z = true;
        int i = 0;
        while (true) {
            int[] iArr = this.mCallState;
            if (i < iArr.length) {
                if (iArr[i] != 0) {
                    z = false;
                }
                i++;
            } else {
                Log.d("SimSettings", "isCallStateIdle " + z);
                return z;
            }
        }
    }
}
