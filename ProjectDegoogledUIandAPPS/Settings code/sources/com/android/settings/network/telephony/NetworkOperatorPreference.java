package com.android.settings.network.telephony;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellSignalStrength;
import androidx.preference.Preference;
import com.havoc.config.center.C1715R;
import java.util.List;

public class NetworkOperatorPreference extends Preference {
    private CellInfo mCellInfo;
    private List<String> mForbiddenPlmns;
    private int mLevel = -1;
    private boolean mShow4GForLTE;
    private boolean mUseNewApi;

    public NetworkOperatorPreference(CellInfo cellInfo, Context context, List<String> list, boolean z) {
        super(context);
        this.mCellInfo = cellInfo;
        this.mForbiddenPlmns = list;
        this.mShow4GForLTE = z;
        this.mUseNewApi = context.getResources().getBoolean(17891456);
        refresh();
    }

    public CellInfo getCellInfo() {
        return this.mCellInfo;
    }

    public void refresh() {
        String networkTitle = CellInfoUtil.getNetworkTitle(this.mCellInfo);
        if (CellInfoUtil.isForbidden(this.mCellInfo, this.mForbiddenPlmns)) {
            networkTitle = networkTitle + " " + getContext().getResources().getString(C1715R.string.forbidden_network);
        }
        setTitle((CharSequence) networkTitle);
        CellSignalStrength cellSignalStrength = this.mCellInfo.getCellSignalStrength();
        int level = cellSignalStrength != null ? cellSignalStrength.getLevel() : -1;
        if (this.mLevel != level) {
            this.mLevel = level;
            updateIcon(this.mLevel);
        }
    }

    public void setIcon(int i) {
        updateIcon(i);
    }

    private int getIconIdForCell(CellInfo cellInfo) {
        int type = cellInfo.getCellIdentity().getType();
        if (type == 1) {
            return C1715R.C1717drawable.signal_strength_g;
        }
        if (type == 2) {
            return C1715R.C1717drawable.signal_strength_1x;
        }
        if (type == 3) {
            return this.mShow4GForLTE ? C1715R.C1717drawable.ic_signal_strength_4g : C1715R.C1717drawable.signal_strength_lte;
        }
        if (type == 4 || type == 5) {
            return C1715R.C1717drawable.signal_strength_3g;
        }
        return 0;
    }

    private void updateIcon(int i) {
        if (this.mUseNewApi && i >= 0 && i < 5) {
            setIcon(MobileNetworkUtils.getSignalStrengthIcon(getContext(), i, 5, getIconIdForCell(this.mCellInfo), false));
        }
    }
}
