package com.android.dialer.assisteddialing.p003ui;

import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import android.view.MenuItem;

/* renamed from: com.android.dialer.assisteddialing.ui.AssistedDialingSettingActivity */
public class AssistedDialingSettingActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getFragmentManager().beginTransaction().replace(16908290, new AssistedDialingSettingFragment()).commit();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
