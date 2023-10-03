package com.android.incallui;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p002v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.android.dialer.R;

public class ManageConferenceActivity extends AppCompatActivity {
    private boolean isVisible;

    public boolean isVisible() {
        return this.isVisible;
    }

    public void onBackPressed() {
        InCallPresenter.getInstance().bringToForeground(false);
        finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        InCallPresenter.getInstance().setManageConferenceActivity(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView((int) R.layout.activity_manage_conference);
        if (getSupportFragmentManager().findFragmentById(R.id.manageConferencePanel) == null) {
            ConferenceManagerFragment conferenceManagerFragment = new ConferenceManagerFragment();
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.manageConferencePanel, conferenceManagerFragment);
            beginTransaction.commit();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            InCallPresenter.getInstance().setManageConferenceActivity((ManageConferenceActivity) null);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.isVisible = true;
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.isVisible = false;
    }
}
