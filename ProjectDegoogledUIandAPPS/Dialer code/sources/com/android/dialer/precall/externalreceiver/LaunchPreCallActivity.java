package com.android.dialer.precall.externalreceiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProvider;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCall;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

public class LaunchPreCallActivity extends Activity {
    private static final ImmutableList<String> HANDLED_INTENT_EXTRAS = ImmutableList.m81of("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", "android.telecom.extra.OUTGOING_CALL_EXTRAS", "android.telecom.extra.PHONE_ACCOUNT_HANDLE", "android.telecom.extra.CALL_SUBJECT", "phone_account_handle", "is_video_call", "call_subject", "allow_assisted_dial");

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.PRECALL_INITIATED_EXTERNAL);
        ConfigProvider configProvider = ConfigProviderComponent.get(getApplicationContext()).getConfigProvider();
        Intent intent = getIntent();
        CallIntentBuilder callIntentBuilder = new CallIntentBuilder(intent.getData(), CallInitiationType$Type.EXTERNAL_INITIATION);
        PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) intent.getParcelableExtra("phone_account_handle");
        if (phoneAccountHandle == null) {
            phoneAccountHandle = (PhoneAccountHandle) intent.getParcelableExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE");
        }
        callIntentBuilder.setPhoneAccountHandle(phoneAccountHandle).setIsVideoCall(intent.getBooleanExtra("is_video_call", false)).setCallSubject(intent.getStringExtra("call_subject")).setAllowAssistedDial(intent.getBooleanExtra("allow_assisted_dial", ((SharedPrefConfigProvider) configProvider).getBoolean("assisted_dialing_default_precall_state", false)));
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putAll(extras);
            if (extras.containsKey("android.telecom.extra.START_CALL_WITH_VIDEO_STATE")) {
                int i = extras.getInt("android.telecom.extra.START_CALL_WITH_VIDEO_STATE");
                if (i == 0) {
                    callIntentBuilder.setIsVideoCall(false);
                } else if (i == 1 || i == 2) {
                    LogUtil.m10w("LaunchPreCallActivity.filterExtras", "unsupported video state " + i + ", overriding to STATE_BIDIRECTIONAL", new Object[0]);
                    callIntentBuilder.setIsVideoCall(true);
                } else if (i != 3) {
                    LogUtil.m10w("LaunchPreCallActivity.filterExtras", GeneratedOutlineSupport.outline5("unknown video state ", i), new Object[0]);
                    callIntentBuilder.setIsVideoCall(false);
                } else {
                    callIntentBuilder.setIsVideoCall(true);
                }
            }
            if (extras.containsKey("android.telecom.extra.OUTGOING_CALL_EXTRAS")) {
                callIntentBuilder.getInCallUiIntentExtras().putAll(extras.getBundle("android.telecom.extra.OUTGOING_CALL_EXTRAS"));
            }
            if (extras.containsKey("android.telecom.extra.PHONE_ACCOUNT_HANDLE")) {
                callIntentBuilder.setPhoneAccountHandle((PhoneAccountHandle) extras.getParcelable("android.telecom.extra.PHONE_ACCOUNT_HANDLE"));
            }
            if (extras.containsKey("android.telecom.extra.CALL_SUBJECT")) {
                callIntentBuilder.setCallSubject(extras.getString("android.telecom.extra.CALL_SUBJECT"));
            }
            UnmodifiableIterator<String> it = HANDLED_INTENT_EXTRAS.iterator();
            while (it.hasNext()) {
                bundle2.remove(it.next());
            }
            callIntentBuilder.getPlaceCallExtras().putAll(bundle2);
        }
        PreCall.start(this, callIntentBuilder);
        finish();
    }
}
