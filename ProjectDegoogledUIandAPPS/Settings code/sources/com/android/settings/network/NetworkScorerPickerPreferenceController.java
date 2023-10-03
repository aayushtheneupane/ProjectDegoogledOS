package com.android.settings.network;

import android.content.Context;
import android.net.NetworkScoreManager;
import android.net.NetworkScorerAppData;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class NetworkScorerPickerPreferenceController extends BasePreferenceController {
    private final NetworkScoreManager mNetworkScoreManager = ((NetworkScoreManager) this.mContext.getSystemService("network_score"));

    public int getAvailabilityStatus() {
        return 0;
    }

    public NetworkScorerPickerPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void updateState(Preference preference) {
        boolean z = !this.mNetworkScoreManager.getAllValidScorers().isEmpty();
        preference.setEnabled(z);
        if (!z) {
            preference.setSummary((CharSequence) null);
            return;
        }
        NetworkScorerAppData activeScorer = this.mNetworkScoreManager.getActiveScorer();
        if (activeScorer == null) {
            preference.setSummary((CharSequence) this.mContext.getString(C1715R.string.network_scorer_picker_none_preference));
        } else {
            preference.setSummary((CharSequence) activeScorer.getRecommendationServiceLabel());
        }
    }
}
