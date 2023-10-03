package com.android.incallui;

import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;

public class PostCharDialogActivity extends AppCompatActivity implements CallList.Listener {
    private String callId;

    public void onCallListChange(CallList callList) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.callId = getIntent().getStringExtra("extra_call_id");
        String stringExtra = getIntent().getStringExtra("extra_post_dial_string");
        String str = this.callId;
        if (str == null || stringExtra == null) {
            finish();
            return;
        }
        new PostCharDialogFragment(str, stringExtra).show(getSupportFragmentManager(), "tag_international_call_on_wifi");
        CallList.getInstance().addListener(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        CallList.getInstance().removeListener(this);
    }

    public void onDisconnect(DialerCall dialerCall) {
        if (this.callId.equals(dialerCall.getId())) {
            finish();
        }
    }

    public void onHandoverToWifiFailed(DialerCall dialerCall) {
    }

    public void onIncomingCall(DialerCall dialerCall) {
    }

    public void onInternationalCallOnWifi(DialerCall dialerCall) {
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (!isChangingConfigurations()) {
            finish();
        }
    }

    public void onSessionModificationStateChange(DialerCall dialerCall) {
    }

    public void onUpgradeToRtt(DialerCall dialerCall, int i) {
    }

    public void onUpgradeToVideo(DialerCall dialerCall) {
    }

    public void onWiFiToLteHandover(DialerCall dialerCall) {
    }
}
