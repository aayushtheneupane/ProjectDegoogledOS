package com.android.dialer.main.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import com.android.dialer.blockreportspam.ShowBlockReportSpamDialogReceiver;
import com.android.dialer.calllog.config.CallLogConfigComponent;
import com.android.dialer.calllog.config.CallLogConfigImpl;
import com.android.dialer.common.LogUtil;
import com.android.dialer.interactions.PhoneNumberInteraction;
import com.android.dialer.main.MainActivityPeer;
import com.android.dialer.util.TransactionSafeActivity;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class MainActivity extends TransactionSafeActivity implements MainActivityPeer.PeerSupplier, PhoneNumberInteraction.InteractionErrorListener, PhoneNumberInteraction.DisambigDialogDismissedListener {
    private MainActivityPeer activePeer;
    private ShowBlockReportSpamDialogReceiver showBlockReportSpamDialogReceiver;

    /* access modifiers changed from: protected */
    public MainActivityPeer getNewPeer() {
        if (((CallLogConfigImpl) CallLogConfigComponent.get(this).callLogConfig()).isNewPeerEnabled()) {
            return new NewMainActivityPeer(this);
        }
        return new OldMainActivityPeer(this);
    }

    public MainActivityPeer getPeer() {
        return this.activePeer;
    }

    public void interactionError(int i) {
        if (i != 4) {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("PhoneNumberInteraction error: ", i));
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.activePeer.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.activePeer.onBackPressed()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.enterBlock("MainActivity.onCreate");
        this.activePeer = getNewPeer();
        this.activePeer.onActivityCreate(bundle);
        this.showBlockReportSpamDialogReceiver = new ShowBlockReportSpamDialogReceiver(getSupportFragmentManager());
    }

    public void onDisambigDialogDismissed() {
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.activePeer.onNewIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.activePeer.onActivityPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.showBlockReportSpamDialogReceiver);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.activePeer.onActivityResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.showBlockReportSpamDialogReceiver, ShowBlockReportSpamDialogReceiver.getIntentFilter());
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.activePeer.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.activePeer.onActivityStop();
    }

    /* access modifiers changed from: protected */
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        this.activePeer.onUserLeaveHint();
    }
}
