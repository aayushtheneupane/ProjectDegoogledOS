package com.android.incallui.callpending;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentTransaction;
import android.telecom.CallAudioState;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.multimedia.MultimediaData;
import com.android.incallui.audiomode.AudioModeProvider;
import com.android.incallui.incall.impl.InCallFragment;
import com.android.incallui.incall.protocol.InCallButtonUi;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;
import com.android.incallui.incall.protocol.InCallButtonUiDelegateFactory;
import com.android.incallui.incall.protocol.InCallScreen;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import com.android.incallui.incall.protocol.InCallScreenDelegateFactory;
import com.android.incallui.incall.protocol.PrimaryCallState;
import com.android.incallui.incall.protocol.PrimaryInfo;
import java.io.FileNotFoundException;

public class CallPendingActivity extends FragmentActivity implements InCallButtonUiDelegateFactory, InCallScreenDelegateFactory {
    private final BroadcastReceiver finishReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            LogUtil.m9i("CallPendingActivity.onReceive", "finish broadcast received", new Object[0]);
            if (intent.getAction().equals("dialer.intent.action.CALL_PENDING_ACTIVITY_FINISH")) {
                CallPendingActivity.this.finish();
            }
        }
    };
    private InCallButtonUiDelegate inCallButtonUiDelegate;
    private InCallScreenDelegate inCallScreenDelegate;

    public static Intent getFinishBroadcast() {
        return new Intent("dialer.intent.action.CALL_PENDING_ACTIVITY_FINISH");
    }

    public static Intent getIntent(Context context, String str, String str2, String str3, String str4, String str5, Uri uri, long j) {
        Intent intent = new Intent(context, CallPendingActivity.class);
        intent.putExtra("extra_name", str);
        intent.putExtra("extra_number", str2);
        intent.putExtra("extra_label", str3);
        intent.putExtra("extra_lookup_key", str4);
        intent.putExtra("extra_call_pending_label", str5);
        intent.putExtra("extra_photo_uri", uri);
        intent.putExtra("extra_session_id", j);
        return intent;
    }

    public InCallButtonUiDelegate newInCallButtonUiDelegate() {
        InCallButtonUiDelegate inCallButtonUiDelegate2 = this.inCallButtonUiDelegate;
        if (inCallButtonUiDelegate2 != null) {
            return inCallButtonUiDelegate2;
        }
        C07092 r0 = new InCallButtonUiDelegate() {
            public void addCallClicked() {
            }

            public void callRecordClicked(boolean z) {
            }

            public void changeToRttClicked() {
            }

            public void changeToVideoClicked() {
            }

            public Context getContext() {
                return CallPendingActivity.this;
            }

            public CallAudioState getCurrentAudioState() {
                return AudioModeProvider.getInstance().getAudioState();
            }

            public void holdClicked(boolean z) {
            }

            public void mergeClicked() {
            }

            public void muteClicked(boolean z, boolean z2) {
            }

            public void onEndCallClicked() {
            }

            public void onInCallButtonUiReady(InCallButtonUi inCallButtonUi) {
                inCallButtonUi.showButton(2, true);
                inCallButtonUi.showButton(1, true);
                inCallButtonUi.showButton(0, true);
                inCallButtonUi.showButton(8, true);
                inCallButtonUi.enableButton(2, false);
                inCallButtonUi.enableButton(1, false);
                inCallButtonUi.enableButton(0, false);
                inCallButtonUi.enableButton(8, false);
            }

            public void onInCallButtonUiUnready() {
            }

            public void onRestoreInstanceState(Bundle bundle) {
            }

            public void onSaveInstanceState(Bundle bundle) {
            }

            public void pauseVideoClicked(boolean z) {
            }

            public void setAudioRoute(int i) {
            }

            public void showAudioRouteSelector() {
            }

            public void showDialpadClicked(boolean z) {
            }

            public void swapClicked() {
            }

            public void swapSimClicked() {
            }

            public void toggleCameraClicked() {
            }

            public void toggleSpeakerphone() {
            }
        };
        this.inCallButtonUiDelegate = r0;
        return r0;
    }

    public InCallScreenDelegate newInCallScreenDelegate() {
        InCallScreenDelegate inCallScreenDelegate2 = this.inCallScreenDelegate;
        if (inCallScreenDelegate2 != null) {
            return inCallScreenDelegate2;
        }
        C07103 r0 = new InCallScreenDelegate() {
            public void onEndCallClicked() {
                CallPendingActivity.this.finish();
            }

            public void onInCallScreenDelegateInit(InCallScreen inCallScreen) {
            }

            public void onInCallScreenPaused() {
            }

            public void onInCallScreenReady() {
            }

            public void onInCallScreenResumed() {
            }

            public void onInCallScreenUnready() {
            }

            public void onManageConferenceClicked() {
            }

            public void onSecondaryInfoClicked() {
            }
        };
        this.inCallScreenDelegate = r0;
        return r0;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pending_incall_screen);
        registerReceiver(this.finishReceiver, new IntentFilter("dialer.intent.action.CALL_PENDING_ACTIVITY_FINISH"));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.finishReceiver);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Drawable drawable;
        super.onResume();
        InCallScreen inCallScreen = (InCallScreen) getSupportFragmentManager().findFragmentByTag("tag_in_call_screen");
        ((EnrichedCallManagerStub) EnrichedCallComponent.get(this).getEnrichedCallManager()).getSession(getIntent().getLongExtra("extra_session_id", -1));
        LogUtil.m9i("CallPendingActivity.createPrimaryInfo", "Null session.", new Object[0]);
        Uri uri = (Uri) getIntent().getParcelableExtra("extra_photo_uri");
        try {
            drawable = Drawable.createFromStream(getContentResolver().openInputStream(uri), uri.toString());
        } catch (FileNotFoundException e) {
            LogUtil.m7e("CallPendingActivity.createPrimaryInfo", "Contact photo not found", (Throwable) e);
            drawable = null;
        }
        String stringExtra = getIntent().getStringExtra("extra_name");
        String stringExtra2 = getIntent().getStringExtra("extra_number");
        PrimaryInfo.Builder builder = PrimaryInfo.builder();
        builder.setNumber(stringExtra2);
        builder.setName(stringExtra);
        builder.setNameIsNumber(stringExtra != null && stringExtra.equals(stringExtra2));
        builder.setLabel(getIntent().getStringExtra("extra_label"));
        builder.setPhoto(drawable);
        builder.setPhotoUri(uri);
        builder.setPhotoType(2);
        builder.setIsSipCall(false);
        builder.setIsContactPhotoShown(true);
        builder.setIsWorkCall(false);
        builder.setIsSpam(false);
        builder.setIsLocalContact(true);
        builder.setAnsweringDisconnectsOngoingCall(false);
        builder.setShouldShowLocation(false);
        builder.setContactInfoLookupKey(getIntent().getStringExtra("extra_lookup_key"));
        builder.setMultimediaData((MultimediaData) null);
        builder.setShowInCallButtonGrid(false);
        builder.setNumberPresentation(1);
        inCallScreen.setPrimary(builder.build());
        PrimaryCallState.Builder builder2 = PrimaryCallState.builder();
        builder2.setState(16);
        builder2.setCustomLabel(getIntent().getStringExtra("extra_call_pending_label"));
        inCallScreen.setCallState(builder2.build());
        inCallScreen.setEndCallButtonEnabled(true, true);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        InCallFragment inCallFragment = new InCallFragment();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add(R.id.main, inCallFragment, "tag_in_call_screen");
        beginTransaction.commit();
    }
}
