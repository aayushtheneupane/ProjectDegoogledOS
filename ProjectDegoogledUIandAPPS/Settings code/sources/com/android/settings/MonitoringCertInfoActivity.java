package com.android.settings;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.appcompat.app.AlertDialog;
import com.android.settingslib.RestrictedLockUtils;
import com.havoc.config.center.C1715R;

public class MonitoringCertInfoActivity extends Activity implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    private int mUserId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        UserHandle userHandle;
        super.onCreate(bundle);
        this.mUserId = getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
        int i = this.mUserId;
        if (i == -10000) {
            userHandle = null;
        } else {
            userHandle = UserHandle.of(i);
        }
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(DevicePolicyManager.class);
        int intExtra = getIntent().getIntExtra("android.settings.extra.number_of_certificates", 1);
        CharSequence quantityText = getResources().getQuantityText(RestrictedLockUtils.getProfileOrDeviceOwner(this, userHandle) != null ? C1715R.plurals.ssl_ca_cert_settings_button : C1715R.plurals.ssl_ca_cert_dialog_title, intExtra);
        setTitle(quantityText);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(quantityText);
        builder.setCancelable(true);
        builder.setPositiveButton(getResources().getQuantityText(C1715R.plurals.ssl_ca_cert_settings_button, intExtra), (DialogInterface.OnClickListener) this);
        builder.setNeutralButton(C1715R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.setOnDismissListener(this);
        if (devicePolicyManager.getProfileOwnerAsUser(this.mUserId) != null) {
            builder.setMessage((CharSequence) getResources().getQuantityString(C1715R.plurals.ssl_ca_cert_info_message, intExtra, new Object[]{devicePolicyManager.getProfileOwnerNameAsUser(this.mUserId)}));
        } else if (devicePolicyManager.getDeviceOwnerComponentOnCallingUser() != null) {
            builder.setMessage((CharSequence) getResources().getQuantityString(C1715R.plurals.ssl_ca_cert_info_message_device_owner, intExtra, new Object[]{devicePolicyManager.getDeviceOwnerNameOnAnyUser()}));
        } else {
            builder.setIcon(17301624);
            builder.setMessage((int) C1715R.string.ssl_ca_cert_warning_message);
        }
        builder.show();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("com.android.settings.TRUSTED_CREDENTIALS_USER");
        intent.setFlags(335544320);
        intent.putExtra("ARG_SHOW_NEW_FOR_USER", this.mUserId);
        startActivity(intent);
        finish();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        finish();
    }
}
