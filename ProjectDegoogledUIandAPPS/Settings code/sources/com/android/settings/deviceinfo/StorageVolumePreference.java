package com.android.settings.deviceinfo;

import android.content.res.ColorStateList;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.deviceinfo.StorageSettings;
import com.havoc.config.center.C1715R;

public class StorageVolumePreference extends Preference {
    private static final String TAG = "StorageVolumePreference";
    private ColorStateList mColorTintList;
    private final StorageManager mStorageManager;
    private final View.OnClickListener mUnmountListener = new View.OnClickListener() {
        public void onClick(View view) {
            new StorageSettings.UnmountTask(StorageVolumePreference.this.getContext(), StorageVolumePreference.this.mVolume).execute(new Void[0]);
        }
    };
    private int mUsedPercent = -1;
    /* access modifiers changed from: private */
    public final VolumeInfo mVolume;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StorageVolumePreference(android.content.Context r18, android.os.storage.VolumeInfo r19, long r20) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            r3 = r19
            r17.<init>(r18)
            r0 = -1
            r1.mUsedPercent = r0
            com.android.settings.deviceinfo.StorageVolumePreference$1 r4 = new com.android.settings.deviceinfo.StorageVolumePreference$1
            r4.<init>()
            r1.mUnmountListener = r4
            java.lang.Class<android.os.storage.StorageManager> r4 = android.os.storage.StorageManager.class
            java.lang.Object r4 = r2.getSystemService(r4)
            android.os.storage.StorageManager r4 = (android.os.storage.StorageManager) r4
            r1.mStorageManager = r4
            r1.mVolume = r3
            r4 = 16843817(0x1010429, float:2.3696543E-38)
            android.content.res.ColorStateList r4 = com.android.settingslib.Utils.getColorAttr(r2, r4)
            r1.mColorTintList = r4
            r4 = 2131558827(0x7f0d01ab, float:1.874298E38)
            r1.setLayoutResource(r4)
            java.lang.String r4 = r19.getId()
            r1.setKey(r4)
            android.os.storage.StorageManager r4 = r1.mStorageManager
            java.lang.String r4 = r4.getBestVolumeDescription(r3)
            r1.setTitle((java.lang.CharSequence) r4)
            java.lang.String r4 = r19.getId()
            java.lang.String r5 = "private"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0052
            r4 = 2131231369(0x7f080289, float:1.8078817E38)
            android.graphics.drawable.Drawable r4 = r2.getDrawable(r4)
            goto L_0x0059
        L_0x0052:
            r4 = 2131231366(0x7f080286, float:1.807881E38)
            android.graphics.drawable.Drawable r4 = r2.getDrawable(r4)
        L_0x0059:
            boolean r5 = r19.isMountedReadable()
            if (r5 == 0) goto L_0x00ef
            java.io.File r5 = r19.getPath()
            int r0 = r19.getType()
            r6 = 1
            r7 = 0
            if (r0 != r6) goto L_0x0092
            java.lang.Class<android.app.usage.StorageStatsManager> r0 = android.app.usage.StorageStatsManager.class
            java.lang.Object r0 = r2.getSystemService(r0)
            android.app.usage.StorageStatsManager r0 = (android.app.usage.StorageStatsManager) r0
            java.lang.String r9 = r19.getFsUuid()     // Catch:{ IOException -> 0x0087 }
            long r9 = r0.getTotalBytes(r9)     // Catch:{ IOException -> 0x0087 }
            java.lang.String r11 = r19.getFsUuid()     // Catch:{ IOException -> 0x0085 }
            long r11 = r0.getFreeBytes(r11)     // Catch:{ IOException -> 0x0085 }
            goto L_0x00a1
        L_0x0085:
            r0 = move-exception
            goto L_0x008a
        L_0x0087:
            r0 = move-exception
            r9 = r20
        L_0x008a:
            java.lang.String r11 = TAG
            android.util.Log.w(r11, r0)
            r11 = r7
            r13 = r11
            goto L_0x00a3
        L_0x0092:
            int r0 = (r20 > r7 ? 1 : (r20 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x009b
            long r9 = r5.getTotalSpace()
            goto L_0x009d
        L_0x009b:
            r9 = r20
        L_0x009d:
            long r11 = r5.getFreeSpace()
        L_0x00a1:
            long r13 = r9 - r11
        L_0x00a3:
            java.lang.String r0 = android.text.format.Formatter.formatFileSize(r2, r13)
            java.lang.String r15 = android.text.format.Formatter.formatFileSize(r2, r9)
            r7 = 2131890805(0x7f121275, float:1.9416312E38)
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r16 = 0
            r8[r16] = r0
            r8[r6] = r15
            java.lang.String r0 = r2.getString(r7, r8)
            r1.setSummary((java.lang.CharSequence) r0)
            r6 = 0
            int r0 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x00cb
            r6 = 100
            long r13 = r13 * r6
            long r13 = r13 / r9
            int r0 = (int) r13
            r1.mUsedPercent = r0
        L_0x00cb:
            android.os.storage.StorageManager r0 = r1.mStorageManager
            long r5 = r0.getStorageLowBytes(r5)
            int r0 = (r11 > r5 ? 1 : (r11 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x00f8
            r0 = 16844099(0x1010543, float:2.3697333E-38)
            android.content.res.ColorStateList r0 = com.android.settingslib.Utils.getColorAttr(r2, r0)
            r1.mColorTintList = r0
            r0 = 2131231410(0x7f0802b2, float:1.80789E38)
            android.graphics.drawable.Drawable r0 = r2.getDrawable(r0)
            r0.mutate()
            android.content.res.ColorStateList r2 = r1.mColorTintList
            r0.setTintList(r2)
            r4 = r0
            goto L_0x00f8
        L_0x00ef:
            int r2 = r19.getStateDescription()
            r1.setSummary((int) r2)
            r1.mUsedPercent = r0
        L_0x00f8:
            r1.setIcon((android.graphics.drawable.Drawable) r4)
            int r0 = r19.getType()
            if (r0 != 0) goto L_0x0115
            android.os.storage.DiskInfo r0 = r3.disk
            boolean r0 = r0.isNonRemovable()
            if (r0 != 0) goto L_0x0115
            boolean r0 = r19.isMountedReadable()
            if (r0 == 0) goto L_0x0115
            r0 = 2131558725(0x7f0d0145, float:1.8742774E38)
            r1.setWidgetLayoutResource(r0)
        L_0x0115:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.deviceinfo.StorageVolumePreference.<init>(android.content.Context, android.os.storage.VolumeInfo, long):void");
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.unmount);
        if (imageView != null) {
            imageView.setOnClickListener(this.mUnmountListener);
        }
        ProgressBar progressBar = (ProgressBar) preferenceViewHolder.findViewById(16908301);
        if (this.mVolume.getType() != 1 || this.mUsedPercent == -1) {
            progressBar.setVisibility(8);
        } else {
            progressBar.setVisibility(0);
            progressBar.setProgress(this.mUsedPercent);
            progressBar.setProgressTintList(this.mColorTintList);
        }
        super.onBindViewHolder(preferenceViewHolder);
    }
}
