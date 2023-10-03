package com.android.incallui.telecomeventui;

import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;

public class InternationalCallOnWifiDialogActivity extends AppCompatActivity implements CallList.Listener {
    private String callId;

    public void onCallListChange(CallList callList) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.callId = getIntent().getStringExtra("extra_call_id");
        if (TextUtils.isEmpty(this.callId)) {
            finish();
            return;
        }
        String str = this.callId;
        InternationalCallOnWifiDialogFragment internationalCallOnWifiDialogFragment = new InternationalCallOnWifiDialogFragment();
        Bundle bundle2 = new Bundle();
        Assert.isNotNull(str);
        bundle2.putString("call_id", str);
        internationalCallOnWifiDialogFragment.setArguments(bundle2);
        internationalCallOnWifiDialogFragment.show(getSupportFragmentManager(), "tag_international_call_on_wifi");
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
