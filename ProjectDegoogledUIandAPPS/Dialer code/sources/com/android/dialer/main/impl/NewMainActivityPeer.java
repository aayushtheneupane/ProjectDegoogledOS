package com.android.dialer.main.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import com.android.dialer.R;
import com.android.dialer.calllog.CallLogComponent;
import com.android.dialer.calllog.p004ui.NewCallLogFragment;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.main.MainActivityPeer;
import com.android.dialer.main.impl.bottomnav.BottomNavBar;
import com.android.dialer.voicemail.listui.NewVoicemailFragment;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;

public class NewMainActivityPeer implements MainActivityPeer {
    private final MainActivity mainActivity;

    private static final class MainBottomNavBarBottomNavTabListener implements BottomNavBar.OnBottomNavTabSelectedListener {
        private final Context appContext;
        private final FragmentManager supportFragmentManager;

        /* synthetic */ MainBottomNavBarBottomNavTabListener(FragmentManager fragmentManager, Context context, C04981 r3) {
            this.supportFragmentManager = fragmentManager;
            this.appContext = context;
        }

        private void hideAllFragments() {
            FragmentTransaction beginTransaction = this.supportFragmentManager.beginTransaction();
            Fragment findFragmentByTag = this.supportFragmentManager.findFragmentByTag("speed_dial");
            if (findFragmentByTag != null) {
                beginTransaction.hide(findFragmentByTag);
            }
            Fragment findFragmentByTag2 = this.supportFragmentManager.findFragmentByTag("call_log");
            if (findFragmentByTag2 != null) {
                if (findFragmentByTag2.isVisible()) {
                    Futures.addCallback(CallLogComponent.get(this.appContext).getClearMissedCalls().clearAll(), new DefaultFutureCallback(), MoreExecutors.directExecutor());
                }
                beginTransaction.hide(findFragmentByTag2);
            }
            if (this.supportFragmentManager.findFragmentByTag("voicemail") != null) {
                beginTransaction.hide(this.supportFragmentManager.findFragmentByTag("voicemail"));
            }
            beginTransaction.commit();
        }

        public void onCallLogSelected() {
            hideAllFragments();
            NewCallLogFragment newCallLogFragment = (NewCallLogFragment) this.supportFragmentManager.findFragmentByTag("call_log");
            if (newCallLogFragment == null) {
                FragmentTransaction beginTransaction = this.supportFragmentManager.beginTransaction();
                beginTransaction.add(R.id.fragment_container, new NewCallLogFragment(), "call_log");
                beginTransaction.commit();
                return;
            }
            FragmentTransaction beginTransaction2 = this.supportFragmentManager.beginTransaction();
            beginTransaction2.show(newCallLogFragment);
            beginTransaction2.commit();
        }

        public void onContactsSelected() {
            hideAllFragments();
        }

        public void onSpeedDialSelected() {
            hideAllFragments();
        }

        public void onVoicemailSelected() {
            hideAllFragments();
            NewVoicemailFragment newVoicemailFragment = (NewVoicemailFragment) this.supportFragmentManager.findFragmentByTag("voicemail");
            if (newVoicemailFragment == null) {
                FragmentTransaction beginTransaction = this.supportFragmentManager.beginTransaction();
                beginTransaction.add(R.id.fragment_container, new NewVoicemailFragment(), "voicemail");
                beginTransaction.commit();
                return;
            }
            FragmentTransaction beginTransaction2 = this.supportFragmentManager.beginTransaction();
            beginTransaction2.show(newVoicemailFragment);
            beginTransaction2.commit();
        }
    }

    public NewMainActivityPeer(MainActivity mainActivity2) {
        this.mainActivity = mainActivity2;
    }

    public void onActivityCreate(Bundle bundle) {
        this.mainActivity.setContentView((int) R.layout.main_activity);
        MainBottomNavBarBottomNavTabListener mainBottomNavBarBottomNavTabListener = new MainBottomNavBarBottomNavTabListener(this.mainActivity.getSupportFragmentManager(), this.mainActivity.getApplicationContext(), (C04981) null);
        BottomNavBar bottomNavBar = (BottomNavBar) this.mainActivity.findViewById(R.id.bottom_nav_bar);
        bottomNavBar.addOnTabSelectedListener(mainBottomNavBarBottomNavTabListener);
        bottomNavBar.selectTab(0);
    }

    public void onActivityPause() {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onActivityResume() {
    }

    public void onActivityStop() {
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onNewIntent(Intent intent) {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public void onUserLeaveHint() {
    }
}
