package com.android.messaging.p041ui.appsettings;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.core.app.NavUtils;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1474sa;

/* renamed from: com.android.messaging.ui.appsettings.SettingsActivity */
public class SettingsActivity extends BugleActionBarActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (C1474sa.getDefault().mo8205bk() <= 1) {
            C1040Ea.get().mo6962a((Context) this, true);
            finish();
            return;
        }
        getFragmentManager().beginTransaction().replace(16908290, new C1104t()).commit();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
}
