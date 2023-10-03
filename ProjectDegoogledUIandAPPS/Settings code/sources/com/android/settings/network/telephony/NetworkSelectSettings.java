package com.android.settings.network.telephony;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.CellInfo;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.internal.telephony.OperatorInfo;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.network.telephony.NetworkScanHelper;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.utils.ThreadUtils;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkSelectSettings extends DashboardFragment {
    private final NetworkScanHelper.NetworkScanCallback mCallback = new NetworkScanHelper.NetworkScanCallback() {
        public void onResults(List<CellInfo> list) {
            NetworkSelectSettings.this.mHandler.obtainMessage(2, list).sendToTarget();
        }

        public void onComplete() {
            NetworkSelectSettings.this.mHandler.obtainMessage(4).sendToTarget();
        }

        public void onError(int i) {
            NetworkSelectSettings.this.mHandler.obtainMessage(3, i, 0).sendToTarget();
        }
    };
    List<CellInfo> mCellInfoList;
    PreferenceCategory mConnectedPreferenceCategory;
    private List<String> mForbiddenPlmns;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                NetworkSelectSettings.this.setProgressBarVisible(false);
                NetworkSelectSettings.this.getPreferenceScreen().setEnabled(true);
                NetworkSelectSettings.this.mSelectedPreference.setSummary(((Boolean) message.obj).booleanValue() ? C1715R.string.network_connected : C1715R.string.network_could_not_connect);
            } else if (i == 2) {
                List access$000 = NetworkSelectSettings.this.aggregateCellInfoList((List) message.obj);
                Log.d("NetworkSelectSettings", "CellInfoList after aggregation: " + CellInfoUtil.cellInfoListToString(access$000));
                NetworkSelectSettings.this.mCellInfoList = new ArrayList(access$000);
                List<CellInfo> list = NetworkSelectSettings.this.mCellInfoList;
                if (list == null || list.size() == 0) {
                    NetworkSelectSettings.this.addMessagePreference(C1715R.string.empty_networks_list);
                } else {
                    NetworkSelectSettings.this.updateAllPreferenceCategory();
                }
            } else if (i == 3) {
                NetworkSelectSettings.this.stopNetworkQuery();
                NetworkSelectSettings.this.addMessagePreference(C1715R.string.network_query_error);
            } else if (i == 4) {
                NetworkSelectSettings.this.stopNetworkQuery();
                NetworkSelectSettings networkSelectSettings = NetworkSelectSettings.this;
                if (networkSelectSettings.mCellInfoList == null) {
                    networkSelectSettings.addMessagePreference(C1715R.string.empty_networks_list);
                }
            }
        }
    };
    private MetricsFeatureProvider mMetricsFeatureProvider;
    private final ExecutorService mNetworkScanExecutor = Executors.newFixedThreadPool(1);
    private NetworkScanHelper mNetworkScanHelper;
    PreferenceCategory mPreferenceCategory;
    private View mProgressHeader;
    NetworkOperatorPreference mSelectedPreference;
    private boolean mShow4GForLTE = false;
    private Preference mStatusMessagePreference;
    private int mSubId = -1;
    TelephonyManager mTelephonyManager;
    private boolean mUseNewApi;

    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "NetworkSelectSettings";
    }

    public int getMetricsCategory() {
        return 1581;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.choose_network;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUseNewApi = getContext().getResources().getBoolean(17891456);
        this.mSubId = getArguments().getInt("android.provider.extra.SUB_ID");
        this.mConnectedPreferenceCategory = (PreferenceCategory) findPreference("connected_network_operator_preference");
        this.mPreferenceCategory = (PreferenceCategory) findPreference("network_operators_preference");
        this.mStatusMessagePreference = new Preference(getContext());
        this.mStatusMessagePreference.setSelectable(false);
        this.mSelectedPreference = null;
        this.mTelephonyManager = TelephonyManager.from(getContext()).createForSubscriptionId(this.mSubId);
        this.mNetworkScanHelper = new NetworkScanHelper(this.mTelephonyManager, this.mCallback, this.mNetworkScanExecutor);
        PersistableBundle configForSubId = ((CarrierConfigManager) getContext().getSystemService("carrier_config")).getConfigForSubId(this.mSubId);
        if (configForSubId != null) {
            this.mShow4GForLTE = configForSubId.getBoolean("show_4g_for_lte_data_icon_bool");
        }
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(getContext()).getMetricsFeatureProvider();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() != null) {
            this.mProgressHeader = setPinnedHeaderView((int) C1715R.layout.progress_header).findViewById(C1715R.C1718id.progress_bar_animation);
            setProgressBarVisible(false);
        }
        forceUpdateConnectedPreferenceCategory();
    }

    public void onStart() {
        super.onStart();
        updateForbiddenPlmns();
        int i = 1;
        setProgressBarVisible(true);
        NetworkScanHelper networkScanHelper = this.mNetworkScanHelper;
        if (this.mUseNewApi) {
            i = 2;
        }
        networkScanHelper.startNetworkScan(i);
    }

    /* access modifiers changed from: package-private */
    public void updateForbiddenPlmns() {
        List<String> list;
        String[] forbiddenPlmns = this.mTelephonyManager.getForbiddenPlmns();
        if (forbiddenPlmns != null) {
            list = Arrays.asList(forbiddenPlmns);
        } else {
            list = new ArrayList<>();
        }
        this.mForbiddenPlmns = list;
    }

    public void onStop() {
        super.onStop();
        stopNetworkQuery();
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference != this.mSelectedPreference) {
            stopNetworkQuery();
            NetworkOperatorPreference networkOperatorPreference = this.mSelectedPreference;
            if (networkOperatorPreference != null) {
                networkOperatorPreference.setSummary((CharSequence) null);
            }
            this.mSelectedPreference = (NetworkOperatorPreference) preference;
            CellInfo cellInfo = this.mSelectedPreference.getCellInfo();
            this.mSelectedPreference.setSummary((int) C1715R.string.network_connecting);
            this.mMetricsFeatureProvider.action(getContext(), 1210, (Pair<Integer, Object>[]) new Pair[0]);
            if (this.mConnectedPreferenceCategory.getPreferenceCount() > 0) {
                NetworkOperatorPreference networkOperatorPreference2 = (NetworkOperatorPreference) this.mConnectedPreferenceCategory.getPreference(0);
                if (!CellInfoUtil.getNetworkTitle(cellInfo).equals(CellInfoUtil.getNetworkTitle(networkOperatorPreference2.getCellInfo()))) {
                    networkOperatorPreference2.setSummary((int) C1715R.string.network_disconnected);
                }
            }
            setProgressBarVisible(true);
            getPreferenceScreen().setEnabled(false);
            ThreadUtils.postOnBackgroundThread((Runnable) new Runnable(CellInfoUtil.getOperatorInfoFromCellInfo(cellInfo)) {
                private final /* synthetic */ OperatorInfo f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    NetworkSelectSettings.this.lambda$onPreferenceTreeClick$0$NetworkSelectSettings(this.f$1);
                }
            });
        }
        return true;
    }

    public /* synthetic */ void lambda$onPreferenceTreeClick$0$NetworkSelectSettings(OperatorInfo operatorInfo) {
        Message obtainMessage = this.mHandler.obtainMessage(1);
        obtainMessage.obj = Boolean.valueOf(this.mTelephonyManager.setNetworkSelectionModeManual(operatorInfo, true));
        obtainMessage.sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void updateAllPreferenceCategory() {
        updateConnectedPreferenceCategory();
        this.mPreferenceCategory.removeAll();
        for (int i = 0; i < this.mCellInfoList.size(); i++) {
            if (!this.mCellInfoList.get(i).isRegistered()) {
                NetworkOperatorPreference networkOperatorPreference = new NetworkOperatorPreference(this.mCellInfoList.get(i), getPrefContext(), this.mForbiddenPlmns, this.mShow4GForLTE);
                networkOperatorPreference.setKey(CellInfoUtil.getNetworkTitle(this.mCellInfoList.get(i)));
                networkOperatorPreference.setOrder(i);
                this.mPreferenceCategory.addPreference(networkOperatorPreference);
            }
        }
    }

    private void forceUpdateConnectedPreferenceCategory() {
        int dataState = this.mTelephonyManager.getDataState();
        TelephonyManager telephonyManager = this.mTelephonyManager;
        if (dataState == 2) {
            List networkRegistrationInfoListForTransportType = telephonyManager.getServiceState().getNetworkRegistrationInfoListForTransportType(1);
            if (networkRegistrationInfoListForTransportType == null || networkRegistrationInfoListForTransportType.size() == 0) {
                this.mConnectedPreferenceCategory.setVisible(false);
                return;
            }
            CellInfo wrapCellInfoWithCellIdentity = CellInfoUtil.wrapCellInfoWithCellIdentity(((NetworkRegistrationInfo) networkRegistrationInfoListForTransportType.get(0)).getCellIdentity());
            if (wrapCellInfoWithCellIdentity != null) {
                NetworkOperatorPreference networkOperatorPreference = new NetworkOperatorPreference(wrapCellInfoWithCellIdentity, getPrefContext(), this.mForbiddenPlmns, this.mShow4GForLTE);
                networkOperatorPreference.setTitle(MobileNetworkUtils.getCurrentCarrierNameForDisplay(getPrefContext(), this.mSubId));
                networkOperatorPreference.setSummary((int) C1715R.string.network_connected);
                networkOperatorPreference.setIcon(4);
                this.mConnectedPreferenceCategory.addPreference(networkOperatorPreference);
                return;
            }
            this.mConnectedPreferenceCategory.setVisible(false);
            return;
        }
        this.mConnectedPreferenceCategory.setVisible(false);
    }

    private void updateConnectedPreferenceCategory() {
        CellInfo cellInfo;
        Iterator<CellInfo> it = this.mCellInfoList.iterator();
        while (true) {
            if (!it.hasNext()) {
                cellInfo = null;
                break;
            }
            cellInfo = it.next();
            if (cellInfo.isRegistered()) {
                break;
            }
        }
        if (cellInfo != null) {
            addConnectedNetworkOperatorPreference(cellInfo);
        }
    }

    private void addConnectedNetworkOperatorPreference(CellInfo cellInfo) {
        this.mConnectedPreferenceCategory.removeAll();
        NetworkOperatorPreference networkOperatorPreference = new NetworkOperatorPreference(cellInfo, getPrefContext(), this.mForbiddenPlmns, this.mShow4GForLTE);
        networkOperatorPreference.setSummary((int) C1715R.string.network_connected);
        this.mConnectedPreferenceCategory.addPreference(networkOperatorPreference);
        this.mConnectedPreferenceCategory.setVisible(true);
    }

    /* access modifiers changed from: protected */
    public void setProgressBarVisible(boolean z) {
        View view = this.mProgressHeader;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    /* access modifiers changed from: private */
    public void addMessagePreference(int i) {
        setProgressBarVisible(false);
        this.mStatusMessagePreference.setTitle(i);
        this.mConnectedPreferenceCategory.setVisible(false);
        this.mPreferenceCategory.removeAll();
        this.mPreferenceCategory.addPreference(this.mStatusMessagePreference);
    }

    /* access modifiers changed from: private */
    public List<CellInfo> aggregateCellInfoList(List<CellInfo> list) {
        HashMap hashMap = new HashMap();
        for (CellInfo next : list) {
            String operatorNumeric = CellInfoUtil.getOperatorInfoFromCellInfo(next).getOperatorNumeric();
            if (next.isRegistered() || !hashMap.containsKey(operatorNumeric)) {
                hashMap.put(operatorNumeric, next);
            } else if (!((CellInfo) hashMap.get(operatorNumeric)).isRegistered() && ((CellInfo) hashMap.get(operatorNumeric)).getCellSignalStrength().getLevel() <= next.getCellSignalStrength().getLevel()) {
                hashMap.put(operatorNumeric, next);
            }
        }
        return new ArrayList(hashMap.values());
    }

    /* access modifiers changed from: private */
    public void stopNetworkQuery() {
        setProgressBarVisible(false);
        NetworkScanHelper networkScanHelper = this.mNetworkScanHelper;
        if (networkScanHelper != null) {
            networkScanHelper.stopNetworkQuery();
        }
    }

    public void onDestroy() {
        this.mNetworkScanExecutor.shutdown();
        super.onDestroy();
    }
}
