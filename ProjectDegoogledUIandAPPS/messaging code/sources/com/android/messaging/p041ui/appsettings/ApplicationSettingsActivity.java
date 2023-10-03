package com.android.messaging.p041ui.appsettings;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.app.NavUtils;
import com.android.messaging.R;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.p041ui.LicenseActivity;

/* renamed from: com.android.messaging.ui.appsettings.ApplicationSettingsActivity */
public class ApplicationSettingsActivity extends BugleActionBarActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().getBooleanExtra("top_level_settings", false)) {
            getSupportActionBar().setTitle((CharSequence) getString(R.string.settings_activity_title));
        }
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(16908290, new C1095k());
        beginTransaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (super.onCreateOptionsMenu(menu)) {
            return true;
        }
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        } else if (itemId != R.id.action_license) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            startActivity(new Intent(this, LicenseActivity.class));
            return true;
        }
    }
}
