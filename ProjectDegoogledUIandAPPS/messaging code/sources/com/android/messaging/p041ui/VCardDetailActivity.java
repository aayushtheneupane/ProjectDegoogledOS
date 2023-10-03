package com.android.messaging.p041ui;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import com.android.messaging.R;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.VCardDetailActivity */
public class VCardDetailActivity extends BugleActionBarActivity {
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof VCardDetailFragment) {
            Uri uri = (Uri) getIntent().getParcelableExtra("vcard_uri");
            C1424b.m3594t(uri);
            ((VCardDetailFragment) fragment).mo7090b(uri);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.vcard_detail_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
