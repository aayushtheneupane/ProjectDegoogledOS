package com.android.settings.deviceinfo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.UserManager;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.android.internal.util.Preconditions;
import com.android.settings.password.ChooseLockSettingsHelper;
import com.havoc.config.center.C1715R;

public class StorageWizardMoveConfirm extends StorageWizardBase {
    private ApplicationInfo mApp;
    private String mPackageName;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mVolume == null) {
            finish();
            return;
        }
        setContentView(C1715R.layout.storage_wizard_generic);
        try {
            this.mPackageName = getIntent().getStringExtra("android.intent.extra.PACKAGE_NAME");
            this.mApp = getPackageManager().getApplicationInfo(this.mPackageName, 0);
            Preconditions.checkState(getPackageManager().getPackageCandidateVolumes(this.mApp).contains(this.mVolume));
            String charSequence = getPackageManager().getApplicationLabel(this.mApp).toString();
            String bestVolumeDescription = this.mStorage.getBestVolumeDescription(this.mVolume);
            setIcon(C1715R.C1717drawable.ic_swap_horiz);
            setHeaderText(C1715R.string.storage_wizard_move_confirm_title, charSequence);
            setBodyText(C1715R.string.storage_wizard_move_confirm_body, charSequence, bestVolumeDescription);
            setNextButtonText(C1715R.string.move_app, new CharSequence[0]);
            setBackButtonVisibility(4);
        } catch (PackageManager.NameNotFoundException unused) {
            finish();
        }
    }

    public void onNavigateNext(View view) {
        if (StorageManager.isFileEncryptedNativeOrEmulated()) {
            for (UserInfo userInfo : ((UserManager) getSystemService(UserManager.class)).getUsers()) {
                if (!StorageManager.isUserKeyUnlocked(userInfo.id)) {
                    Log.d("StorageSettings", "User " + userInfo.id + " is currently locked; requesting unlock");
                    new ChooseLockSettingsHelper(this).launchConfirmationActivityForAnyUser(100, (CharSequence) null, (CharSequence) null, TextUtils.expandTemplate(getText(C1715R.string.storage_wizard_move_unlock), new CharSequence[]{userInfo.name}), userInfo.id);
                    return;
                }
            }
        }
        String charSequence = getPackageManager().getApplicationLabel(this.mApp).toString();
        int movePackage = getPackageManager().movePackage(this.mPackageName, this.mVolume);
        Intent intent = new Intent(this, StorageWizardMoveProgress.class);
        intent.putExtra("android.content.pm.extra.MOVE_ID", movePackage);
        intent.putExtra("android.intent.extra.TITLE", charSequence);
        intent.putExtra("android.os.storage.extra.VOLUME_ID", this.mVolume.getId());
        startActivity(intent);
        finishAffinity();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 100) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            onNavigateNext((View) null);
        } else {
            Log.w("StorageSettings", "Failed to confirm credentials");
        }
    }
}
