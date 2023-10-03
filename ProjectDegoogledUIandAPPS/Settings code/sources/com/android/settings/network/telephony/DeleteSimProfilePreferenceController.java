package com.android.settings.network.telephony;

import android.content.Context;
import android.content.Intent;
import android.telephony.SubscriptionInfo;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.network.SubscriptionUtil;
import java.util.Iterator;

public class DeleteSimProfilePreferenceController extends BasePreferenceController {
    private Fragment mParentFragment;
    private int mRequestCode;
    private SubscriptionInfo mSubscriptionInfo;

    public DeleteSimProfilePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void init(int i, Fragment fragment, int i2) {
        this.mParentFragment = fragment;
        Iterator<SubscriptionInfo> it = SubscriptionUtil.getAvailableSubscriptions(this.mContext).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            SubscriptionInfo next = it.next();
            if (next.getSubscriptionId() == i && next.isEmbedded()) {
                this.mSubscriptionInfo = next;
                break;
            }
        }
        this.mRequestCode = i2;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        preferenceScreen.findPreference(getPreferenceKey()).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public final boolean onPreferenceClick(Preference preference) {
                return DeleteSimProfilePreferenceController.this.lambda$displayPreference$0$DeleteSimProfilePreferenceController(preference);
            }
        });
    }

    public /* synthetic */ boolean lambda$displayPreference$0$DeleteSimProfilePreferenceController(Preference preference) {
        Intent intent = new Intent("android.telephony.euicc.action.DELETE_SUBSCRIPTION_PRIVILEGED");
        intent.putExtra("android.telephony.euicc.extra.SUBSCRIPTION_ID", this.mSubscriptionInfo.getSubscriptionId());
        this.mParentFragment.startActivityForResult(intent, this.mRequestCode);
        return true;
    }

    public int getAvailabilityStatus() {
        return this.mSubscriptionInfo != null ? 0 : 2;
    }
}
