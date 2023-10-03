package com.android.settings.development;

import android.bluetooth.BluetoothCodecConfig;
import android.content.Context;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class BluetoothAudioCodecPreferenceController extends AbstractBluetoothA2dpPreferenceController {
    /* access modifiers changed from: protected */
    public int getDefaultIndex() {
        return 0;
    }

    public String getPreferenceKey() {
        return "bluetooth_select_a2dp_codec";
    }

    public BluetoothAudioCodecPreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
    }

    /* access modifiers changed from: protected */
    public String[] getListValues() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_values);
    }

    /* access modifiers changed from: protected */
    public String[] getListSummaries() {
        return this.mContext.getResources().getStringArray(C1715R.array.bluetooth_a2dp_codec_summaries_cm);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003c, code lost:
        r7 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003e, code lost:
        r7 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0040, code lost:
        r7 = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0042, code lost:
        r7 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0044, code lost:
        r7 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0046, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0064, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0065, code lost:
        r9.mBluetoothA2dpConfigStore.setCodecType(r7);
        r9.mBluetoothA2dpConfigStore.setCodecPriority(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x006f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeConfigurationValues(java.lang.Object r10) {
        /*
            r9 = this;
            androidx.preference.ListPreference r0 = r9.mPreference
            java.lang.String r10 = r10.toString()
            int r10 = r0.findIndexOfValue(r10)
            r0 = 0
            r1 = 6
            r2 = 4
            r3 = 5
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            r8 = 1000000(0xf4240, float:1.401298E-39)
            switch(r10) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0065;
                case 2: goto L_0x0046;
                case 3: goto L_0x0044;
                case 4: goto L_0x0042;
                case 5: goto L_0x0040;
                case 6: goto L_0x003e;
                case 7: goto L_0x003c;
                case 8: goto L_0x002b;
                case 9: goto L_0x001a;
                default: goto L_0x0018;
            }
        L_0x0018:
            goto L_0x0064
        L_0x001a:
            com.android.settings.development.BluetoothA2dpConfigStore r10 = r9.mBluetoothA2dpConfigStore
            monitor-enter(r10)
            android.bluetooth.BluetoothA2dp r1 = r9.mBluetoothA2dp     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0026
            android.bluetooth.BluetoothA2dp r9 = r9.mBluetoothA2dp     // Catch:{ all -> 0x0028 }
            r9.disableOptionalCodecs(r0)     // Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r10)     // Catch:{ all -> 0x0028 }
            return
        L_0x0028:
            r9 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0028 }
            throw r9
        L_0x002b:
            com.android.settings.development.BluetoothA2dpConfigStore r10 = r9.mBluetoothA2dpConfigStore
            monitor-enter(r10)
            android.bluetooth.BluetoothA2dp r1 = r9.mBluetoothA2dp     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0037
            android.bluetooth.BluetoothA2dp r9 = r9.mBluetoothA2dp     // Catch:{ all -> 0x0039 }
            r9.enableOptionalCodecs(r0)     // Catch:{ all -> 0x0039 }
        L_0x0037:
            monitor-exit(r10)     // Catch:{ all -> 0x0039 }
            return
        L_0x0039:
            r9 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0039 }
            throw r9
        L_0x003c:
            r7 = r1
            goto L_0x0065
        L_0x003e:
            r7 = r2
            goto L_0x0065
        L_0x0040:
            r7 = r3
            goto L_0x0065
        L_0x0042:
            r7 = r4
            goto L_0x0065
        L_0x0044:
            r7 = r5
            goto L_0x0065
        L_0x0046:
            r7 = r6
            goto L_0x0065
        L_0x0048:
            androidx.preference.ListPreference r10 = r9.mPreference
            java.lang.String r10 = r10.getValue()
            androidx.preference.ListPreference r0 = r9.mPreference
            int r10 = r0.findIndexOfValue(r10)
            switch(r10) {
                case 0: goto L_0x0064;
                case 1: goto L_0x0064;
                case 2: goto L_0x0062;
                case 3: goto L_0x0060;
                case 4: goto L_0x005e;
                case 5: goto L_0x005c;
                case 6: goto L_0x005a;
                case 7: goto L_0x0058;
                default: goto L_0x0057;
            }
        L_0x0057:
            goto L_0x0064
        L_0x0058:
            r8 = r7
            goto L_0x003c
        L_0x005a:
            r8 = r7
            goto L_0x003e
        L_0x005c:
            r8 = r7
            goto L_0x0040
        L_0x005e:
            r8 = r7
            goto L_0x0042
        L_0x0060:
            r8 = r7
            goto L_0x0044
        L_0x0062:
            r8 = r7
            goto L_0x0046
        L_0x0064:
            r8 = r7
        L_0x0065:
            com.android.settings.development.BluetoothA2dpConfigStore r10 = r9.mBluetoothA2dpConfigStore
            r10.setCodecType(r7)
            com.android.settings.development.BluetoothA2dpConfigStore r9 = r9.mBluetoothA2dpConfigStore
            r9.setCodecPriority(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.development.BluetoothAudioCodecPreferenceController.writeConfigurationValues(java.lang.Object):void");
    }

    /* access modifiers changed from: protected */
    public int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig) {
        switch (bluetoothCodecConfig.getCodecType()) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 6;
            case 5:
                return 5;
            case 6:
                return 7;
            default:
                return 0;
        }
    }
}
