package com.android.settings.deviceinfo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.DeviceInfoUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumberPreferenceController extends BasePreferenceController {
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private final List<Preference> mPreferenceList = new ArrayList();
    private final SubscriptionManager mSubscriptionManager = ((SubscriptionManager) this.mContext.getSystemService(SubscriptionManager.class));
    private final TelephonyManager mTelephonyManager = ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class));

    public boolean isCopyableSlice() {
        return true;
    }

    public boolean isSliceable() {
        return true;
    }

    public boolean useDynamicSliceSummary() {
        return true;
    }

    public PhoneNumberPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mTelephonyManager.isVoiceCapable() ? 0 : 3;
    }

    public CharSequence getSummary() {
        return getFirstPhoneNumber();
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mPreferenceList.add(findPreference);
        int order = findPreference.getOrder();
        for (int i = 1; i < this.mTelephonyManager.getPhoneCount(); i++) {
            Preference createNewPreference = createNewPreference(preferenceScreen.getContext());
            createNewPreference.setOrder(order + i);
            createNewPreference.setKey(KEY_PHONE_NUMBER + i);
            preferenceScreen.addPreference(createNewPreference);
            this.mPreferenceList.add(createNewPreference);
        }
    }

    public void updateState(Preference preference) {
        for (int i = 0; i < this.mPreferenceList.size(); i++) {
            Preference preference2 = this.mPreferenceList.get(i);
            preference2.setTitle(getPreferenceTitle(i));
            preference2.setSummary(getPhoneNumber(i));
        }
    }

    public void copy() {
        ((ClipboardManager) this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", getFirstPhoneNumber()));
        Context context = this.mContext;
        Toast.makeText(this.mContext, context.getString(C1715R.string.copyable_slice_toast, new Object[]{context.getText(C1715R.string.status_number)}), 0).show();
    }

    private CharSequence getFirstPhoneNumber() {
        List activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.isEmpty()) {
            return this.mContext.getText(C1715R.string.device_info_default);
        }
        return getFormattedPhoneNumber((SubscriptionInfo) activeSubscriptionInfoList.get(0));
    }

    private CharSequence getPhoneNumber(int i) {
        SubscriptionInfo subscriptionInfo = getSubscriptionInfo(i);
        if (subscriptionInfo == null) {
            return this.mContext.getText(C1715R.string.device_info_default);
        }
        return getFormattedPhoneNumber(subscriptionInfo);
    }

    private CharSequence getPreferenceTitle(int i) {
        if (this.mTelephonyManager.getPhoneCount() <= 1) {
            return this.mContext.getString(C1715R.string.status_number);
        }
        return this.mContext.getString(C1715R.string.status_number_sim_slot, new Object[]{Integer.valueOf(i + 1)});
    }

    /* access modifiers changed from: package-private */
    public SubscriptionInfo getSubscriptionInfo(int i) {
        List<SubscriptionInfo> activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList == null) {
            return null;
        }
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
            if (subscriptionInfo.getSimSlotIndex() == i) {
                return subscriptionInfo;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public CharSequence getFormattedPhoneNumber(SubscriptionInfo subscriptionInfo) {
        String formattedPhoneNumber = DeviceInfoUtils.getFormattedPhoneNumber(this.mContext, subscriptionInfo);
        if (TextUtils.isEmpty(formattedPhoneNumber)) {
            return this.mContext.getString(C1715R.string.device_info_default);
        }
        return BidiFormatter.getInstance().unicodeWrap(formattedPhoneNumber, TextDirectionHeuristics.LTR);
    }

    /* access modifiers changed from: package-private */
    public Preference createNewPreference(Context context) {
        return new Preference(context);
    }
}
