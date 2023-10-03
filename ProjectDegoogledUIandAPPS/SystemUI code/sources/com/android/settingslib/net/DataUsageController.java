package com.android.settingslib.net;

import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.INetworkStatsService;
import android.net.NetworkPolicy;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.Range;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.ArrayUtils;
import java.time.ZonedDateTime;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;

public class DataUsageController {
    private static final boolean DEBUG = Log.isLoggable("DataUsageController", 3);
    private static final StringBuilder PERIOD_BUILDER = new StringBuilder(50);
    private static final Formatter PERIOD_FORMATTER = new Formatter(PERIOD_BUILDER, Locale.getDefault());
    private Callback mCallback;
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private NetworkNameProvider mNetworkController;
    private final NetworkStatsManager mNetworkStatsManager;
    private final NetworkPolicyManager mPolicyManager = NetworkPolicyManager.from(this.mContext);
    private final INetworkStatsService mStatsService = INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats"));
    private int mSubscriptionId;

    public interface Callback {
        void onMobileDataEnabled(boolean z);
    }

    public static class DataUsageInfo {
        public String carrier;
        public long cycleEnd;
        public long cycleStart;
        public long limitLevel;
        public String period;
        public long startDate;
        public long usageLevel;
        public long warningLevel;
    }

    public interface NetworkNameProvider {
        String getMobileDataNetworkName();
    }

    public DataUsageController(Context context) {
        this.mContext = context;
        this.mConnectivityManager = ConnectivityManager.from(context);
        this.mNetworkStatsManager = (NetworkStatsManager) context.getSystemService(NetworkStatsManager.class);
        this.mSubscriptionId = -1;
    }

    public void setNetworkController(NetworkNameProvider networkNameProvider) {
        this.mNetworkController = networkNameProvider;
    }

    public long getDefaultWarningLevel() {
        return ((long) this.mContext.getResources().getInteger(17694996)) * 1048576;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    private DataUsageInfo warn(String str) {
        Log.w("DataUsageController", "Failed to get data usage, " + str);
        return null;
    }

    public DataUsageInfo getDataUsageInfo() {
        return getDataUsageInfo(DataUsageUtils.getMobileTemplate(this.mContext, this.mSubscriptionId));
    }

    public DataUsageInfo getDataUsageInfo(NetworkTemplate networkTemplate) {
        long j;
        long j2;
        NetworkPolicy findNetworkPolicy = findNetworkPolicy(networkTemplate);
        long currentTimeMillis = System.currentTimeMillis();
        Iterator cycleIterator = findNetworkPolicy != null ? findNetworkPolicy.cycleIterator() : null;
        if (cycleIterator == null || !cycleIterator.hasNext()) {
            j = currentTimeMillis;
            j2 = currentTimeMillis - 2419200000L;
        } else {
            Range range = (Range) cycleIterator.next();
            j2 = ((ZonedDateTime) range.getLower()).toInstant().toEpochMilli();
            j = ((ZonedDateTime) range.getUpper()).toInstant().toEpochMilli();
        }
        long usageLevel = getUsageLevel(networkTemplate, j2, j);
        if (usageLevel < 0) {
            return warn("no entry data");
        }
        DataUsageInfo dataUsageInfo = new DataUsageInfo();
        dataUsageInfo.startDate = j2;
        dataUsageInfo.usageLevel = usageLevel;
        dataUsageInfo.period = formatDateRange(j2, j);
        dataUsageInfo.cycleStart = j2;
        dataUsageInfo.cycleEnd = j;
        if (findNetworkPolicy != null) {
            long j3 = findNetworkPolicy.limitBytes;
            if (j3 <= 0) {
                j3 = 0;
            }
            dataUsageInfo.limitLevel = j3;
            long j4 = findNetworkPolicy.warningBytes;
            if (j4 <= 0) {
                j4 = 0;
            }
            dataUsageInfo.warningLevel = j4;
        } else {
            dataUsageInfo.warningLevel = getDefaultWarningLevel();
        }
        NetworkNameProvider networkNameProvider = this.mNetworkController;
        if (networkNameProvider != null) {
            dataUsageInfo.carrier = networkNameProvider.getMobileDataNetworkName();
        }
        return dataUsageInfo;
    }

    public DataUsageInfo getDailyDataUsageInfo(NetworkTemplate networkTemplate) {
        NetworkPolicy findNetworkPolicy = findNetworkPolicy(networkTemplate);
        long currentTimeMillis = System.currentTimeMillis();
        long todayMillis = currentTimeMillis - DataUsageUtils.getTodayMillis();
        long usageLevel = getUsageLevel(networkTemplate, todayMillis, currentTimeMillis);
        if (usageLevel < 0) {
            return warn("no entry data");
        }
        DataUsageInfo dataUsageInfo = new DataUsageInfo();
        dataUsageInfo.startDate = todayMillis;
        dataUsageInfo.usageLevel = usageLevel;
        dataUsageInfo.period = formatDateRange(todayMillis, currentTimeMillis);
        dataUsageInfo.cycleStart = todayMillis;
        dataUsageInfo.cycleEnd = currentTimeMillis;
        if (findNetworkPolicy != null) {
            long j = findNetworkPolicy.limitBytes;
            if (j <= 0) {
                j = 0;
            }
            dataUsageInfo.limitLevel = j;
            long j2 = findNetworkPolicy.warningBytes;
            if (j2 <= 0) {
                j2 = 0;
            }
            dataUsageInfo.warningLevel = j2;
        } else {
            dataUsageInfo.warningLevel = getDefaultWarningLevel();
        }
        NetworkNameProvider networkNameProvider = this.mNetworkController;
        if (networkNameProvider != null) {
            dataUsageInfo.carrier = networkNameProvider.getMobileDataNetworkName();
        }
        return dataUsageInfo;
    }

    private long getUsageLevel(NetworkTemplate networkTemplate, long j, long j2) {
        try {
            NetworkStats.Bucket querySummaryForDevice = this.mNetworkStatsManager.querySummaryForDevice(networkTemplate, j, j2);
            if (querySummaryForDevice != null) {
                return querySummaryForDevice.getRxBytes() + querySummaryForDevice.getTxBytes();
            }
            Log.w("DataUsageController", "Failed to get data usage, no entry data");
            return -1;
        } catch (RemoteException unused) {
            Log.w("DataUsageController", "Failed to get data usage, remote call failed");
            return -1;
        }
    }

    private NetworkPolicy findNetworkPolicy(NetworkTemplate networkTemplate) {
        NetworkPolicy[] networkPolicies;
        NetworkPolicyManager networkPolicyManager = this.mPolicyManager;
        if (networkPolicyManager == null || networkTemplate == null || (networkPolicies = networkPolicyManager.getNetworkPolicies()) == null) {
            return null;
        }
        for (NetworkPolicy networkPolicy : networkPolicies) {
            if (networkPolicy != null && networkTemplate.equals(networkPolicy.template)) {
                return networkPolicy;
            }
        }
        return null;
    }

    @VisibleForTesting
    public TelephonyManager getTelephonyManager() {
        int i = this.mSubscriptionId;
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            i = SubscriptionManager.getDefaultDataSubscriptionId();
        }
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            int[] activeSubscriptionIdList = SubscriptionManager.from(this.mContext).getActiveSubscriptionIdList();
            if (!ArrayUtils.isEmpty(activeSubscriptionIdList)) {
                i = activeSubscriptionIdList[0];
            }
        }
        return TelephonyManager.from(this.mContext).createForSubscriptionId(i);
    }

    public void setMobileDataEnabled(boolean z) {
        Log.d("DataUsageController", "setMobileDataEnabled: enabled=" + z);
        getTelephonyManager().setDataEnabled(z);
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onMobileDataEnabled(z);
        }
    }

    public boolean isMobileDataSupported() {
        if (!this.mConnectivityManager.isNetworkSupported(0) || getTelephonyManager().getSimState() != 5) {
            return false;
        }
        return true;
    }

    public boolean isMobileDataEnabled() {
        return getTelephonyManager().isDataEnabled();
    }

    private String formatDateRange(long j, long j2) {
        String formatter;
        synchronized (PERIOD_BUILDER) {
            PERIOD_BUILDER.setLength(0);
            formatter = DateUtils.formatDateRange(this.mContext, PERIOD_FORMATTER, j, j2, 65552, (String) null).toString();
        }
        return formatter;
    }
}
