package com.android.dialer.shortcuts;

import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.ActivityCompat;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.interactions.PhoneNumberInteraction;
import com.android.dialer.util.TransactionSafeActivity;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class CallContactActivity extends TransactionSafeActivity implements PhoneNumberInteraction.DisambigDialogDismissedListener, PhoneNumberInteraction.InteractionErrorListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private Uri contactUri;

    private void makeCall() {
        CallSpecificAppData.Builder newBuilder = CallSpecificAppData.newBuilder();
        newBuilder.setAllowAssistedDialing(true);
        newBuilder.setCallInitiationType(CallInitiationType$Type.LAUNCHER_SHORTCUT);
        PhoneNumberInteraction.startInteractionForPhoneCall(this, this.contactUri, false, (CallSpecificAppData) newBuilder.build());
    }

    public void interactionError(int i) {
        if (i == 1 || i == 2) {
            Toast.makeText(this, R.string.dialer_shortcut_contact_not_found_or_has_no_number, 0).show();
        }
        finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!"com.android.dialer.shortcuts.CALL_CONTACT".equals(getIntent().getAction())) {
            return;
        }
        if (((SharedPrefConfigProvider) ConfigProviderComponent.get(this).getConfigProvider()).getBoolean("dynamic_shortcuts_enabled", true)) {
            LogUtil.m9i("CallContactActivity.onCreate", "shortcut clicked", new Object[0]);
            this.contactUri = getIntent().getData();
            makeCall();
            return;
        }
        LogUtil.m9i("CallContactActivity.onCreate", "dynamic shortcuts disabled", new Object[0]);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LogUtil.enterBlock("CallContactActivity.onDestroy");
    }

    public void onDisambigDialogDismissed() {
        finish();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1 && i != 2) {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Unsupported request code: ", i));
        } else if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, R.string.dialer_shortcut_no_permissions, 0).show();
            finish();
        } else {
            makeCall();
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null) {
            this.contactUri = (Uri) bundle.getParcelable("uri_key");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("uri_key", this.contactUri);
    }
}
