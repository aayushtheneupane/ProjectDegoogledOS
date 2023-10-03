package com.android.messaging.p041ui.conversationsettings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.android.messaging.R;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.conversationsettings.PeopleAndOptionsActivity */
public class PeopleAndOptionsActivity extends BugleActionBarActivity {
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof PeopleAndOptionsFragment) {
            String stringExtra = getIntent().getStringExtra("conversation_id");
            C1424b.m3594t(stringExtra);
            ((PeopleAndOptionsFragment) fragment).mo7600e(stringExtra);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.people_and_options_activity);
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
