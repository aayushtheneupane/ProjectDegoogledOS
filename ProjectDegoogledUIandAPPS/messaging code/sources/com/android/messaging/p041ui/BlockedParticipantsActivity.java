package com.android.messaging.p041ui;

import android.os.Bundle;
import android.view.MenuItem;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.BlockedParticipantsActivity */
public class BlockedParticipantsActivity extends BugleActionBarActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.blocked_participants_activity);
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
