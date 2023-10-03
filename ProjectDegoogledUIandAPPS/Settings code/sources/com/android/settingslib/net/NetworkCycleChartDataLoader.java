package com.android.settingslib.net;

import android.app.usage.NetworkStats;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.android.settingslib.net.NetworkCycleChartData;
import com.android.settingslib.net.NetworkCycleData;
import com.android.settingslib.net.NetworkCycleDataLoader;
import java.util.ArrayList;
import java.util.List;

public class NetworkCycleChartDataLoader extends NetworkCycleDataLoader<List<NetworkCycleChartData>> {
    private final List<NetworkCycleChartData> mData;

    private NetworkCycleChartDataLoader(Builder builder) {
        super(builder);
        this.mData = new ArrayList();
    }

    /* access modifiers changed from: package-private */
    public void recordUsage(long j, long j2) {
        long j3;
        try {
            NetworkStats.Bucket querySummaryForDevice = this.mNetworkStatsManager.querySummaryForDevice(this.mNetworkTemplate, j, j2);
            if (querySummaryForDevice == null) {
                j3 = 0;
            } else {
                j3 = querySummaryForDevice.getRxBytes() + querySummaryForDevice.getTxBytes();
            }
            if (j3 > 0) {
                NetworkCycleChartData.Builder builder = new NetworkCycleChartData.Builder();
                builder.setUsageBuckets(getUsageBuckets(j, j2));
                builder.setStartTime(j);
                builder.setEndTime(j2);
                builder.setTotalUsage(j3);
                this.mData.add(builder.build());
            }
        } catch (RemoteException e) {
            Log.e("NetworkCycleChartLoader", "Exception querying network detail.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public List<NetworkCycleChartData> getCycleUsage() {
        return this.mData;
    }

    public static Builder<?> builder(Context context) {
        return new Builder<NetworkCycleChartDataLoader>(context) {
            public NetworkCycleChartDataLoader build() {
                return new NetworkCycleChartDataLoader(this);
            }
        };
    }

    private List<NetworkCycleData> getUsageBuckets(long j, long j2) {
        ArrayList arrayList = new ArrayList();
        long j3 = j + NetworkCycleChartData.BUCKET_DURATION_MS;
        long j4 = j;
        while (true) {
            long j5 = j3;
            if (j5 > j2) {
                return arrayList;
            }
            long j6 = 0;
            try {
                NetworkStats.Bucket querySummaryForDevice = this.mNetworkStatsManager.querySummaryForDevice(this.mNetworkTemplate, j4, j5);
                if (querySummaryForDevice != null) {
                    j6 = querySummaryForDevice.getRxBytes() + querySummaryForDevice.getTxBytes();
                }
            } catch (RemoteException e) {
                Log.e("NetworkCycleChartLoader", "Exception querying network detail.", e);
            }
            NetworkCycleData.Builder builder = new NetworkCycleData.Builder();
            builder.setStartTime(j4);
            builder.setEndTime(j5);
            builder.setTotalUsage(j6);
            arrayList.add(builder.build());
            j3 = NetworkCycleChartData.BUCKET_DURATION_MS + j5;
            j4 = j5;
        }
    }

    public static abstract class Builder<T extends NetworkCycleChartDataLoader> extends NetworkCycleDataLoader.Builder<T> {
        public Builder(Context context) {
            super(context);
        }
    }
}
