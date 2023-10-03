package com.android.messaging.p041ui.attachmentchooser;

import android.app.Fragment;
import android.os.Bundle;
import com.android.messaging.R;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.attachmentchooser.AttachmentChooserActivity */
public class AttachmentChooserActivity extends BugleActionBarActivity implements C1105a {
    /* renamed from: da */
    public void mo7192da() {
        setResult(-1);
        finish();
    }

    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof AttachmentChooserFragment) {
            String stringExtra = getIntent().getStringExtra("conversation_id");
            C1424b.m3594t(stringExtra);
            AttachmentChooserFragment attachmentChooserFragment = (AttachmentChooserFragment) fragment;
            attachmentChooserFragment.mo7197e(stringExtra);
            attachmentChooserFragment.mo7195a((C1105a) this);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.attachment_chooser_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
