package com.android.messaging.p041ui;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.LicenseActivity */
public class LicenseActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.license_activity);
        ((WebView) findViewById(R.id.content)).loadUrl("file:///android_asset/licenses.html");
    }
}
