package com.android.settingslib.wifi;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.SystemClock;
import com.android.settingslib.R$string;
import java.util.Map;

public class WifiUtils {
    public static String buildLoggingSummary(AccessPoint accessPoint, WifiConfiguration wifiConfiguration) {
        StringBuilder sb = new StringBuilder();
        WifiInfo info = accessPoint.getInfo();
        if (accessPoint.isActive() && info != null) {
            sb.append(" f=" + Integer.toString(info.getFrequency()));
        }
        sb.append(" " + getVisibilityStatus(accessPoint));
        if (wifiConfiguration != null && !wifiConfiguration.getNetworkSelectionStatus().isNetworkEnabled()) {
            sb.append(" (" + wifiConfiguration.getNetworkSelectionStatus().getNetworkStatusString());
            if (wifiConfiguration.getNetworkSelectionStatus().getDisableTime() > 0) {
                long currentTimeMillis = (System.currentTimeMillis() - wifiConfiguration.getNetworkSelectionStatus().getDisableTime()) / 1000;
                long j = currentTimeMillis % 60;
                long j2 = (currentTimeMillis / 60) % 60;
                long j3 = (j2 / 60) % 60;
                sb.append(", ");
                if (j3 > 0) {
                    sb.append(Long.toString(j3) + "h ");
                }
                sb.append(Long.toString(j2) + "m ");
                sb.append(Long.toString(j) + "s ");
            }
            sb.append(")");
        }
        if (wifiConfiguration != null) {
            WifiConfiguration.NetworkSelectionStatus networkSelectionStatus = wifiConfiguration.getNetworkSelectionStatus();
            for (int i = 0; i < 15; i++) {
                if (networkSelectionStatus.getDisableReasonCounter(i) != 0) {
                    sb.append(" " + WifiConfiguration.NetworkSelectionStatus.getNetworkDisableReasonString(i) + "=" + networkSelectionStatus.getDisableReasonCounter(i));
                }
            }
        }
        return sb.toString();
    }

    static String getVisibilityStatus(AccessPoint accessPoint) {
        String str;
        WifiInfo info = accessPoint.getInfo();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        int i = 0;
        if (!accessPoint.isActive() || info == null) {
            str = null;
        } else {
            str = info.getBSSID();
            if (str != null) {
                sb.append(" ");
                sb.append(str);
            }
            sb.append(" rssi=");
            sb.append(info.getRssi());
            sb.append(" ");
            sb.append(" score=");
            sb.append(info.score);
            if (accessPoint.getSpeed() != 0) {
                sb.append(" speed=");
                sb.append(accessPoint.getSpeedLabel());
            }
            sb.append(String.format(" tx=%.1f,", new Object[]{Double.valueOf(info.txSuccessRate)}));
            sb.append(String.format("%.1f,", new Object[]{Double.valueOf(info.txRetriesRate)}));
            sb.append(String.format("%.1f ", new Object[]{Double.valueOf(info.txBadRate)}));
            sb.append(String.format("rx=%.1f", new Object[]{Double.valueOf(info.rxSuccessRate)}));
        }
        int i2 = WifiConfiguration.INVALID_RSSI;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        int i3 = i2;
        int i4 = i3;
        int i5 = 0;
        for (ScanResult next : accessPoint.getScanResults()) {
            if (next != null) {
                int i6 = next.frequency;
                if (i6 < 4900 || i6 > 5900) {
                    int i7 = next.frequency;
                    if (i7 >= 2400 && i7 <= 2500) {
                        i++;
                        int i8 = next.level;
                        if (i8 > i3) {
                            i3 = i8;
                        }
                        if (i <= 4) {
                            sb2.append(verboseScanResultSummary(accessPoint, next, str, elapsedRealtime));
                        }
                    }
                } else {
                    i5++;
                    int i9 = next.level;
                    if (i9 > i4) {
                        i4 = i9;
                    }
                    if (i5 <= 4) {
                        sb3.append(verboseScanResultSummary(accessPoint, next, str, elapsedRealtime));
                    }
                }
            }
        }
        sb.append(" [");
        if (i > 0) {
            sb.append("(");
            sb.append(i);
            sb.append(")");
            if (i > 4) {
                sb.append("max=");
                sb.append(i3);
                sb.append(",");
            }
            sb.append(sb2.toString());
        }
        sb.append(";");
        if (i5 > 0) {
            sb.append("(");
            sb.append(i5);
            sb.append(")");
            if (i5 > 4) {
                sb.append("max=");
                sb.append(i4);
                sb.append(",");
            }
            sb.append(sb3.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    static String verboseScanResultSummary(AccessPoint accessPoint, ScanResult scanResult, String str, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(" \n{");
        sb.append(scanResult.BSSID);
        if (scanResult.BSSID.equals(str)) {
            sb.append("*");
        }
        sb.append("=");
        sb.append(scanResult.frequency);
        sb.append(",");
        sb.append(scanResult.level);
        int specificApSpeed = getSpecificApSpeed(scanResult, accessPoint.getScoredNetworkCache());
        if (specificApSpeed != 0) {
            sb.append(",");
            sb.append(accessPoint.getSpeedLabel(specificApSpeed));
        }
        sb.append(",");
        sb.append(((int) (j - (scanResult.timestamp / 1000))) / 1000);
        sb.append("s");
        sb.append("}");
        return sb.toString();
    }

    private static int getSpecificApSpeed(ScanResult scanResult, Map<String, TimestampedScoredNetwork> map) {
        TimestampedScoredNetwork timestampedScoredNetwork = map.get(scanResult.BSSID);
        if (timestampedScoredNetwork == null) {
            return 0;
        }
        return timestampedScoredNetwork.getScore().calculateBadge(scanResult.level);
    }

    public static String getMeteredLabel(Context context, WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.meteredOverride == 1 || (wifiConfiguration.meteredHint && !isMeteredOverridden(wifiConfiguration))) {
            return context.getString(R$string.wifi_metered_label);
        }
        return context.getString(R$string.wifi_unmetered_label);
    }

    public static boolean isMeteredOverridden(WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.meteredOverride != 0;
    }
}
