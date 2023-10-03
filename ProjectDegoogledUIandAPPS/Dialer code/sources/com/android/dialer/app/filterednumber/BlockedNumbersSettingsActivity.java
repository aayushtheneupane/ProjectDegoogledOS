package com.android.dialer.app.filterednumber;

import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.android.dialer.R;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ScreenEvent$Type;

public class BlockedNumbersSettingsActivity extends AppCompatActivity {
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.blocked_numbers_activity);
        if (bundle == null) {
            showManagementUi();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        onBackPressed();
        return true;
    }

    public void showManagementUi() {
        BlockedNumbersFragment blockedNumbersFragment = (BlockedNumbersFragment) getFragmentManager().findFragmentByTag("blocked_management");
        if (blockedNumbersFragment == null) {
            blockedNumbersFragment = new BlockedNumbersFragment();
        }
        getFragmentManager().beginTransaction().replace(R.id.blocked_numbers_activity_container, blockedNumbersFragment, "blocked_management").commit();
        ((LoggingBindingsStub) Logger.get(this)).logScreenView(ScreenEvent$Type.BLOCKED_NUMBER_MANAGEMENT, this);
    }

    public void showNumbersToImportPreviewUi() {
        ViewNumbersToImportFragment viewNumbersToImportFragment = (ViewNumbersToImportFragment) getFragmentManager().findFragmentByTag("view_numbers_to_import");
        if (viewNumbersToImportFragment == null) {
            viewNumbersToImportFragment = new ViewNumbersToImportFragment();
        }
        getFragmentManager().beginTransaction().replace(R.id.blocked_numbers_activity_container, viewNumbersToImportFragment, "view_numbers_to_import").addToBackStack((String) null).commit();
    }
}
