package com.android.settings.deviceinfo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.storage.VolumeInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreateOptionsMenu;
import com.android.settingslib.core.lifecycle.events.OnOptionsItemSelected;
import com.android.settingslib.core.lifecycle.events.OnPrepareOptionsMenu;
import com.havoc.config.center.C1715R;
import java.util.Objects;

public class PrivateVolumeOptionMenuController implements LifecycleObserver, OnCreateOptionsMenu, OnPrepareOptionsMenu, OnOptionsItemSelected {
    private Context mContext;
    private PackageManager mPm;
    private VolumeInfo mVolumeInfo;

    public PrivateVolumeOptionMenuController(Context context, VolumeInfo volumeInfo, PackageManager packageManager) {
        this.mContext = context;
        this.mVolumeInfo = volumeInfo;
        this.mPm = packageManager;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 100, 0, C1715R.string.storage_menu_migrate);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (this.mVolumeInfo != null) {
            VolumeInfo primaryStorageCurrentVolume = this.mPm.getPrimaryStorageCurrentVolume();
            MenuItem findItem = menu.findItem(100);
            if (findItem != null) {
                boolean z = true;
                if (primaryStorageCurrentVolume == null || primaryStorageCurrentVolume.getType() != 1 || Objects.equals(this.mVolumeInfo, primaryStorageCurrentVolume) || !primaryStorageCurrentVolume.isMountedWritable()) {
                    z = false;
                }
                findItem.setVisible(z);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return false;
        }
        Intent intent = new Intent(this.mContext, StorageWizardMigrateConfirm.class);
        intent.putExtra("android.os.storage.extra.VOLUME_ID", this.mVolumeInfo.getId());
        this.mContext.startActivity(intent);
        return true;
    }
}
