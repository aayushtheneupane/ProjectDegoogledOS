package com.android.contacts.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.android.contacts.R;

public class LicenseActivity extends AppCompatActivity {
    private WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.licenses);
        this.mWebView = (WebView) findViewById(R.id.webview);
        this.mWebView.loadUrl("file:///android_asset/licenses.html");
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayOptions(4, 4);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mWebView.destroy();
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}
