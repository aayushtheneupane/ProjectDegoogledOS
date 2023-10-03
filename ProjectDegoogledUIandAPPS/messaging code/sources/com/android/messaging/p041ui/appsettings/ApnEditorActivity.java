package com.android.messaging.p041ui.appsettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import androidx.core.app.NavUtils;
import com.android.messaging.p041ui.BugleActionBarActivity;

/* renamed from: com.android.messaging.ui.appsettings.ApnEditorActivity */
public class ApnEditorActivity extends BugleActionBarActivity {

    /* renamed from: Tb */
    private C1088d f1715Tb;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f1715Tb = new C1088d();
        this.f1715Tb.setSubId(getIntent().getIntExtra("sub_id", -1));
        getFragmentManager().beginTransaction().replace(16908290, this.f1715Tb).commit();
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        if (i != 0) {
            return super.onCreateDialog(i);
        }
        return new AlertDialog.Builder(this).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).setMessage(bundle.getString("error_msg")).create();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (!this.f1715Tb.m2706qa(false)) {
            return true;
        }
        finish();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPrepareDialog(int i, Dialog dialog, Bundle bundle) {
        String string;
        super.onPrepareDialog(i, dialog);
        if (i == 0 && (string = bundle.getString("error_msg")) != null) {
            ((AlertDialog) dialog).setMessage(string);
        }
    }
}
