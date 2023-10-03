package com.android.settings.applications.manageapplications;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.text.format.Formatter;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.android.settings.Utils;
import com.android.settingslib.applications.StorageStatsSource;
import com.havoc.config.center.C1715R;
import java.io.IOException;

public class PhotosViewHolderController implements FileViewHolderController {
    private Context mContext;
    private long mFilesSize;
    private StorageStatsSource mSource;
    private UserHandle mUser;
    private String mVolumeUuid;

    public boolean shouldShow() {
        return true;
    }

    public PhotosViewHolderController(Context context, StorageStatsSource storageStatsSource, String str, UserHandle userHandle) {
        this.mContext = context;
        this.mSource = storageStatsSource;
        this.mVolumeUuid = str;
        this.mUser = userHandle;
    }

    public void queryStats() {
        try {
            StorageStatsSource.ExternalStorageStats externalStorageStats = this.mSource.getExternalStorageStats(this.mVolumeUuid, this.mUser);
            this.mFilesSize = externalStorageStats.imageBytes + externalStorageStats.videoBytes;
        } catch (IOException e) {
            this.mFilesSize = 0;
            Log.w("PhotosViewHolderCtrl", e);
        }
    }

    public void setupView(ApplicationViewHolder applicationViewHolder) {
        applicationViewHolder.setIcon((int) C1715R.C1717drawable.ic_photo_library);
        applicationViewHolder.setTitle(this.mContext.getText(C1715R.string.storage_detail_images));
        applicationViewHolder.setSummary((CharSequence) Formatter.formatFileSize(this.mContext, this.mFilesSize));
    }

    public void onClick(Fragment fragment) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(524288);
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.FROM_STORAGE", true);
        Utils.launchIntent(fragment, intent);
    }
}
