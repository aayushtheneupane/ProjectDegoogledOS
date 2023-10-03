package com.android.dialer.main;

import android.content.Intent;
import android.os.Bundle;

public interface MainActivityPeer {

    public interface PeerSupplier {
        MainActivityPeer getPeer();
    }

    void onActivityCreate(Bundle bundle);

    void onActivityPause();

    void onActivityResult(int i, int i2, Intent intent);

    void onActivityResume();

    void onActivityStop();

    boolean onBackPressed();

    void onNewIntent(Intent intent);

    void onSaveInstanceState(Bundle bundle);

    void onUserLeaveHint();
}
