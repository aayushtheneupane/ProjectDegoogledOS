package com.android.messaging.p041ui.appsettings;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.core.app.NavUtils;
import com.android.messaging.R;
import com.android.messaging.p041ui.BugleActionBarActivity;

/* renamed from: com.android.messaging.ui.appsettings.ApnSettingsActivity */
public class ApnSettingsActivity extends BugleActionBarActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        C1094j jVar = new C1094j();
        jVar.setSubId(getIntent().getIntExtra("sub_id", -1));
        getFragmentManager().beginTransaction().replace(16908290, jVar).commit();
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i != 1001) {
            return null;
        }
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.restore_default_apn));
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
}
