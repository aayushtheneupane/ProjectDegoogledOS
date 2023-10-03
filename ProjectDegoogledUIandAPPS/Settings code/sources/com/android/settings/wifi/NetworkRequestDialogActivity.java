package com.android.settings.wifi;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

public class NetworkRequestDialogActivity extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        NetworkRequestDialogFragment.newInstance().show(getSupportFragmentManager(), "NetworkRequestDialogFragment");
    }
}
