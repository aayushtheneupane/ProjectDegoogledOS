package com.android.messaging.p041ui.appsettings;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.core.app.NavUtils;
import com.android.messaging.p041ui.BugleActionBarActivity;

/* renamed from: com.android.messaging.ui.appsettings.PerSubscriptionSettingsActivity */
public class PerSubscriptionSettingsActivity extends BugleActionBarActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String stringExtra = getIntent().getStringExtra("per_sub_setting_title");
        if (!TextUtils.isEmpty(stringExtra)) {
            getSupportActionBar().setTitle((CharSequence) stringExtra);
        }
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(16908290, new C1101q());
        beginTransaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
}
