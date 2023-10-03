package com.android.settings.bluetooth;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.havoc.config.center.C1715R;

public final class DevicePickerActivity extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1715R.layout.bluetooth_device_picker);
    }
}
