package com.android.settings.security.trustagent;

import android.content.Context;
import android.os.UserHandle;
import androidx.preference.Preference;
import com.android.internal.widget.LockPatternUtils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.security.SecurityFeatureProvider;
import com.havoc.config.center.C1715R;

public class ManageTrustAgentsPreferenceController extends BasePreferenceController {
    static final String KEY_MANAGE_TRUST_AGENTS = "manage_trust_agents";
    private static final int MY_USER_ID = UserHandle.myUserId();
    private final LockPatternUtils mLockPatternUtils;
    private TrustAgentManager mTrustAgentManager;

    public ManageTrustAgentsPreferenceController(Context context) {
        super(context, KEY_MANAGE_TRUST_AGENTS);
        SecurityFeatureProvider securityFeatureProvider = FeatureFactory.getFactory(context).getSecurityFeatureProvider();
        this.mLockPatternUtils = securityFeatureProvider.getLockPatternUtils(context);
        this.mTrustAgentManager = securityFeatureProvider.getTrustAgentManager();
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_manage_trust_agents) ? 0 : 3;
    }

    public void updateState(Preference preference) {
        int trustAgentCount = getTrustAgentCount();
        if (!this.mLockPatternUtils.isSecure(MY_USER_ID)) {
            preference.setEnabled(false);
            preference.setSummary((int) C1715R.string.disabled_because_no_backup_security);
        } else if (trustAgentCount > 0) {
            preference.setEnabled(true);
            preference.setSummary((CharSequence) this.mContext.getResources().getQuantityString(C1715R.plurals.manage_trust_agents_summary_on, trustAgentCount, new Object[]{Integer.valueOf(trustAgentCount)}));
        } else {
            preference.setEnabled(true);
            preference.setSummary((int) C1715R.string.manage_trust_agents_summary);
        }
    }

    private int getTrustAgentCount() {
        return this.mTrustAgentManager.getActiveTrustAgents(this.mContext, this.mLockPatternUtils).size();
    }
}
