package com.android.incallui;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.graphics.ColorUtils;
import android.telecom.PhoneAccountHandle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.Toast;
import com.android.contacts.common.widget.SelectPhoneAccountDialogFragment;
import com.android.contacts.common.widget.SelectPhoneAccountDialogOptions;
import com.android.dialer.R;
import com.android.dialer.animation.AnimUtils;
import com.android.dialer.animation.AnimationListenerAdapter;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.common.concurrent.UiListener;
import com.android.dialer.dialpadview.DialpadView;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ScreenEvent$Type;
import com.android.dialer.metrics.MetricsComponent;
import com.android.dialer.metrics.StubMetrics;
import com.android.dialer.preferredsim.PreferredAccountRecorder;
import com.android.dialer.preferredsim.PreferredAccountWorker;
import com.android.dialer.preferredsim.impl.PreferredAccountWorkerImpl;
import com.android.dialer.util.CallUtil;
import com.android.incallui.InCallPresenter;
import com.android.incallui.answer.impl.AnswerFragment;
import com.android.incallui.answer.protocol.AnswerScreen;
import com.android.incallui.answer.protocol.AnswerScreenDelegate;
import com.android.incallui.answer.protocol.AnswerScreenDelegateFactory;
import com.android.incallui.answerproximitysensor.PseudoScreenState;
import com.android.incallui.audiomode.AudioModeProvider;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.TelecomAdapter;
import com.android.incallui.callpending.CallPendingActivity;
import com.android.incallui.disconnectdialog.DisconnectMessage;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;
import com.android.incallui.incall.protocol.InCallButtonUiDelegateFactory;
import com.android.incallui.incall.protocol.InCallScreen;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import com.android.incallui.incall.protocol.InCallScreenDelegateFactory;
import com.android.incallui.incalluilock.InCallUiLock;
import com.android.incallui.rtt.impl.RttChatFragment;
import com.android.incallui.rtt.protocol.RttCallScreen;
import com.android.incallui.rtt.protocol.RttCallScreenDelegate;
import com.android.incallui.rtt.protocol.RttCallScreenDelegateFactory;
import com.android.incallui.speakeasy.SpeakEasyCallManager;
import com.android.incallui.telecomeventui.InternationalCallOnWifiDialogFragment;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;
import com.android.incallui.video.protocol.VideoCallScreenDelegateFactory;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Optional;

public class InCallActivity extends TransactionSafeFragmentActivity implements AnswerScreenDelegateFactory, InCallScreenDelegateFactory, InCallButtonUiDelegateFactory, VideoCallScreenDelegateFactory, RttCallScreenDelegateFactory, PseudoScreenState.StateChangedListener {
    private static Optional<Integer> audioRouteForTesting = Optional.empty();
    private boolean allowOrientationChange;
    private boolean animateDialpadOnShow;
    private GradientDrawable backgroundDrawable;
    private int[] backgroundDrawableColors;
    private Animation dialpadSlideInAnimation;
    private Animation dialpadSlideOutAnimation;
    private boolean didShowAnswerScreen;
    private boolean didShowInCallScreen;
    private boolean didShowRttCallScreen;
    private boolean didShowSpeakEasyScreen;
    private boolean didShowVideoCallScreen;
    private boolean dismissKeyguard;
    private String dtmfTextToPrepopulate;
    private Dialog errorDialog;
    private InCallOrientationEventListener inCallOrientationEventListener;
    private boolean isInShowMainInCallFragment;
    private boolean isRecreating;
    private boolean isVisible;
    private String lastShownSpeakEasyScreenUniqueCallid = "";
    private boolean needDismissPendingDialogs;
    private UiListener<PreferredAccountWorker.Result> preferredAccountWorkerResultListener;
    private View pseudoBlackScreenOverlay;
    private DialogFragment rttRequestDialogFragment;
    private SelectPhoneAccountDialogFragment selectPhoneAccountDialogFragment;
    private SelectPhoneAccountListener selectPhoneAccountListener;
    private int showDialpadRequest = 1;
    private SpeakEasyCallManager speakEasyCallManager;
    private boolean touchDownWhenPseudoScreenOff;

    private static final class SelectPhoneAccountListener extends SelectPhoneAccountDialogFragment.SelectPhoneAccountListener {
        private final Context appContext;

        SelectPhoneAccountListener(Context context) {
            this.appContext = context;
        }

        public void onDialogDismissed(String str) {
            DialerCall callById = CallList.getInstance().getCallById(str);
            LogUtil.m9i("com.android.incallui.InCallActivity.SelectPhoneAccountListener", GeneratedOutlineSupport.outline6("Disconnecting call:\n%s", callById), new Object[0]);
            if (callById != null) {
                callById.disconnect();
            }
        }

        public void onPhoneAccountSelected(PhoneAccountHandle phoneAccountHandle, boolean z, String str) {
            DialerCall callById = CallList.getInstance().getCallById(str);
            LogUtil.m9i("com.android.incallui.InCallActivity.SelectPhoneAccountListener", "Phone account select with call:\n%s", callById);
            if (callById != null) {
                callById.phoneAccountSelected(phoneAccountHandle, false);
                if (callById.getPreferredAccountRecorder() != null) {
                    callById.getPreferredAccountRecorder().record(this.appContext, phoneAccountHandle, z);
                }
            }
        }
    }

    private static class ShouldShowUiResult {
        public final DialerCall call;
        public final boolean shouldShow;

        ShouldShowUiResult(boolean z, DialerCall dialerCall) {
            this.shouldShow = z;
            this.call = dialerCall;
        }
    }

    private void enableInCallOrientationEventListener(boolean z) {
        if (z) {
            this.inCallOrientationEventListener.enable(true);
        } else {
            this.inCallOrientationEventListener.disable();
        }
    }

    private AnswerScreen getAnswerScreen() {
        return (AnswerScreen) getSupportFragmentManager().findFragmentByTag("tag_answer_screen");
    }

    private DialpadFragment getDialpadFragment() {
        FragmentManager dialpadFragmentManager = getDialpadFragmentManager();
        if (dialpadFragmentManager == null) {
            return null;
        }
        return (DialpadFragment) dialpadFragmentManager.findFragmentByTag("tag_dialpad_fragment");
    }

    private InCallScreen getInCallOrRttCallScreen() {
        return this.didShowRttCallScreen ? getRttCallScreen() : this.didShowInCallScreen ? (InCallScreen) getSupportFragmentManager().findFragmentByTag("tag_in_call_screen") : null;
    }

    public static Intent getIntent(Context context, boolean z, boolean z2, boolean z3) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.setFlags(268697600);
        intent.setClass(context, InCallActivity.class);
        if (z) {
            intent.putExtra("InCallActivity.show_dialpad", true);
        }
        intent.putExtra("InCallActivity.new_outgoing_call", z2);
        intent.putExtra("InCallActivity.for_full_screen_intent", z3);
        return intent;
    }

    private RttCallScreen getRttCallScreen() {
        return (RttCallScreen) getSupportFragmentManager().findFragmentByTag("tag_rtt_call_screen");
    }

    private VideoCallScreen getVideoCallScreen() {
        return (VideoCallScreen) getSupportFragmentManager().findFragmentByTag("tag_video_call_screen");
    }

    private boolean hideAnswerScreenFragment(FragmentTransaction fragmentTransaction) {
        if (!this.didShowAnswerScreen) {
            return false;
        }
        AnswerScreen answerScreen = getAnswerScreen();
        if (answerScreen != null) {
            fragmentTransaction.remove((AnswerFragment) answerScreen);
        }
        this.didShowAnswerScreen = false;
        return true;
    }

    /* access modifiers changed from: private */
    public void hideDialpadFragment() {
        DialpadFragment dialpadFragment;
        FragmentManager dialpadFragmentManager = getDialpadFragmentManager();
        if (dialpadFragmentManager != null && (dialpadFragment = getDialpadFragment()) != null) {
            FragmentTransaction beginTransaction = dialpadFragmentManager.beginTransaction();
            beginTransaction.hide(dialpadFragment);
            beginTransaction.commitAllowingStateLoss();
            dialpadFragmentManager.executePendingTransactions();
            dialpadFragment.setUserVisibleHint(false);
            getInCallOrRttCallScreen().onInCallScreenDialpadVisibilityChange(false);
        }
    }

    private boolean hideInCallScreenFragment(FragmentTransaction fragmentTransaction) {
        if (!this.didShowInCallScreen) {
            return false;
        }
        InCallScreen inCallScreen = (InCallScreen) getSupportFragmentManager().findFragmentByTag("tag_in_call_screen");
        if (inCallScreen != null) {
            fragmentTransaction.remove(inCallScreen.getInCallScreenFragment());
        }
        this.didShowInCallScreen = false;
        return true;
    }

    private boolean hideRttCallScreenFragment(FragmentTransaction fragmentTransaction) {
        if (!this.didShowRttCallScreen) {
            return false;
        }
        RttCallScreen rttCallScreen = getRttCallScreen();
        if (rttCallScreen != null) {
            RttChatFragment rttChatFragment = (RttChatFragment) rttCallScreen;
            rttChatFragment.getRttCallScreenFragment();
            fragmentTransaction.remove(rttChatFragment);
        }
        this.didShowRttCallScreen = false;
        return true;
    }

    private boolean hideSpeakEasyFragment(FragmentTransaction fragmentTransaction) {
        Fragment findFragmentByTag;
        if (!this.didShowSpeakEasyScreen || (findFragmentByTag = getSupportFragmentManager().findFragmentByTag("tag_speak_easy_screen")) == null) {
            return false;
        }
        fragmentTransaction.remove(findFragmentByTag);
        this.didShowSpeakEasyScreen = false;
        return true;
    }

    private boolean hideVideoCallScreenFragment(FragmentTransaction fragmentTransaction) {
        if (!this.didShowVideoCallScreen) {
            return false;
        }
        VideoCallScreen videoCallScreen = (VideoCallScreen) getSupportFragmentManager().findFragmentByTag("tag_video_call_screen");
        if (videoCallScreen != null) {
            fragmentTransaction.remove(videoCallScreen.getVideoCallScreenFragment());
        }
        this.didShowVideoCallScreen = false;
        return true;
    }

    private void internalResolveIntent(Intent intent) {
        ArrayList arrayList;
        DialerCall activeOrBackgroundCall;
        if (intent.getAction().equals("android.intent.action.MAIN")) {
            boolean z = true;
            if (intent.hasExtra("InCallActivity.show_dialpad")) {
                this.showDialpadRequest = intent.getBooleanExtra("InCallActivity.show_dialpad", false) ? 2 : 1;
                this.animateDialpadOnShow = true;
                if (this.showDialpadRequest == 2 && (activeOrBackgroundCall = CallList.getInstance().getActiveOrBackgroundCall()) != null && activeOrBackgroundCall.getState() == 8) {
                    activeOrBackgroundCall.unhold();
                }
            }
            DialerCall outgoingCall = CallList.getInstance().getOutgoingCall();
            if (outgoingCall == null) {
                outgoingCall = CallList.getInstance().getPendingOutgoingCall();
            }
            if (intent.getBooleanExtra("InCallActivity.new_outgoing_call", false)) {
                intent.removeExtra("InCallActivity.new_outgoing_call");
                if (InCallPresenter.isCallWithNoValidAccounts(outgoingCall)) {
                    LogUtil.m9i("InCallActivity.internalResolveIntent", "Call with no valid accounts, disconnecting", new Object[0]);
                    outgoingCall.disconnect();
                }
                dismissKeyguard(true);
            }
            DialerCall waitingForAccountCall = CallList.getInstance().getWaitingForAccountCall();
            if (waitingForAccountCall == null) {
                z = false;
            } else {
                PreferredAccountWorker preferredAccountWorker = ((DaggerAospDialerRootComponent) ((HasRootComponent) getApplicationContext()).component()).preferredSimComponent().preferredAccountWorker();
                Bundle intentExtras = waitingForAccountCall.getIntentExtras();
                if (intentExtras == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = intentExtras.getParcelableArrayList("selectPhoneAccountAccounts");
                }
                this.preferredAccountWorkerResultListener.listen(this, ((PreferredAccountWorkerImpl) preferredAccountWorker).selectAccount(waitingForAccountCall.getNumber(), arrayList), new DialerExecutor.SuccessListener(waitingForAccountCall) {
                    private final /* synthetic */ DialerCall f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onSuccess(Object obj) {
                        InCallActivity.this.lambda$showPhoneAccountSelectionDialog$0$InCallActivity(this.f$1, (PreferredAccountWorker.Result) obj);
                    }
                }, $$Lambda$InCallActivity$PdafVQkcqLAvpJqLIYQtzrrkqoE.INSTANCE);
            }
            if (z) {
                hideMainInCallFragment();
            }
        }
    }

    static /* synthetic */ void lambda$showPhoneAccountSelectionDialog$1(Throwable th) {
        throw new RuntimeException(th);
    }

    private void onDialogDismissed() {
        this.errorDialog = null;
        CallList.getInstance().onErrorDialogDismissed();
    }

    public static void setAudioRouteForTesting(int i) {
        audioRouteForTesting = Optional.of(Integer.valueOf(i));
    }

    /* JADX WARNING: Removed duplicated region for block: B:115:0x02fe  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0301  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x04b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void showMainInCallFragment() {
        /*
            r20 = this;
            r0 = r20
            java.lang.String r1 = "InCallActivity.showMainInCallFragment"
            android.os.Trace.beginSection(r1)
            boolean r2 = r0.isVisible
            r3 = 0
            if (r2 != 0) goto L_0x0017
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r2 = "not visible yet/anymore"
            com.android.dialer.common.LogUtil.m9i(r1, r2, r0)
            android.os.Trace.endSection()
            return
        L_0x0017:
            boolean r2 = r0.isInShowMainInCallFragment
            if (r2 == 0) goto L_0x0026
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r2 = "already in method, bailing"
            com.android.dialer.common.LogUtil.m9i(r1, r2, r0)
            android.os.Trace.endSection()
            return
        L_0x0026:
            r2 = 1
            r0.isInShowMainInCallFragment = r2
            com.android.incallui.call.CallList r4 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r4 = r4.getIncomingCall()
            java.lang.String r5 = "InCallActivity.getShouldShowAnswerUi"
            r6 = 0
            if (r4 == 0) goto L_0x0049
            boolean r7 = r4.isSpeakEasyCall()
            if (r7 != 0) goto L_0x0049
            java.lang.Object[] r7 = new java.lang.Object[r3]
            java.lang.String r8 = "found incoming call"
            com.android.dialer.common.LogUtil.m9i(r5, r8, r7)
            com.android.incallui.InCallActivity$ShouldShowUiResult r5 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r5.<init>(r2, r4)
            goto L_0x0092
        L_0x0049:
            com.android.incallui.call.CallList r4 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r4 = r4.getVideoUpgradeRequestCall()
            if (r4 == 0) goto L_0x0060
            java.lang.Object[] r7 = new java.lang.Object[r3]
            java.lang.String r8 = "found video upgrade request"
            com.android.dialer.common.LogUtil.m9i(r5, r8, r7)
            com.android.incallui.InCallActivity$ShouldShowUiResult r5 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r5.<init>(r2, r4)
            goto L_0x0092
        L_0x0060:
            com.android.incallui.call.CallList r4 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r4 = r4.getFirstCall()
            if (r4 != 0) goto L_0x0072
            com.android.incallui.call.CallList r4 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r4 = r4.getBackgroundCall()
        L_0x0072:
            boolean r7 = r0.didShowAnswerScreen
            if (r7 == 0) goto L_0x008d
            if (r4 == 0) goto L_0x0080
            int r7 = r4.getState()
            r8 = 10
            if (r7 != r8) goto L_0x008d
        L_0x0080:
            java.lang.Object[] r7 = new java.lang.Object[r3]
            java.lang.String r8 = "found disconnecting incoming call"
            com.android.dialer.common.LogUtil.m9i(r5, r8, r7)
            com.android.incallui.InCallActivity$ShouldShowUiResult r5 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r5.<init>(r2, r4)
            goto L_0x0092
        L_0x008d:
            com.android.incallui.InCallActivity$ShouldShowUiResult r5 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r5.<init>(r3, r6)
        L_0x0092:
            com.android.incallui.call.CallList r4 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r4 = r4.getFirstCall()
            java.lang.String r7 = "InCallActivity.getShouldShowVideoUi"
            java.lang.String r8 = "null call"
            if (r4 != 0) goto L_0x00ab
            java.lang.Object[] r4 = new java.lang.Object[r3]
            com.android.dialer.common.LogUtil.m9i(r7, r8, r4)
            com.android.incallui.InCallActivity$ShouldShowUiResult r4 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r4.<init>(r3, r6)
            goto L_0x00df
        L_0x00ab:
            boolean r9 = r4.isVideoCall()
            if (r9 == 0) goto L_0x00bf
            java.lang.Object[] r9 = new java.lang.Object[r3]
            java.lang.String r10 = "found video call"
            com.android.dialer.common.LogUtil.m9i(r7, r10, r9)
            com.android.incallui.InCallActivity$ShouldShowUiResult r7 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r7.<init>(r2, r4)
        L_0x00bd:
            r4 = r7
            goto L_0x00df
        L_0x00bf:
            boolean r9 = r4.hasSentVideoUpgradeRequest()
            if (r9 != 0) goto L_0x00d2
            boolean r9 = r4.hasReceivedVideoUpgradeRequest()
            if (r9 == 0) goto L_0x00cc
            goto L_0x00d2
        L_0x00cc:
            com.android.incallui.InCallActivity$ShouldShowUiResult r4 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r4.<init>(r3, r6)
            goto L_0x00df
        L_0x00d2:
            java.lang.Object[] r9 = new java.lang.Object[r3]
            java.lang.String r10 = "upgrading to video"
            com.android.dialer.common.LogUtil.m9i(r7, r10, r9)
            com.android.incallui.InCallActivity$ShouldShowUiResult r7 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r7.<init>(r2, r4)
            goto L_0x00bd
        L_0x00df:
            com.android.incallui.call.CallList r7 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r7 = r7.getFirstCall()
            java.lang.String r9 = "InCallActivity.getShouldShowRttUi"
            if (r7 != 0) goto L_0x00f6
            java.lang.Object[] r7 = new java.lang.Object[r3]
            com.android.dialer.common.LogUtil.m9i(r9, r8, r7)
            com.android.incallui.InCallActivity$ShouldShowUiResult r7 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r7.<init>(r3, r6)
            goto L_0x0122
        L_0x00f6:
            boolean r8 = r7.isActiveRttCall()
            if (r8 == 0) goto L_0x010a
            java.lang.Object[] r8 = new java.lang.Object[r3]
            java.lang.String r10 = "found rtt call"
            com.android.dialer.common.LogUtil.m9i(r9, r10, r8)
            com.android.incallui.InCallActivity$ShouldShowUiResult r8 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r8.<init>(r2, r7)
        L_0x0108:
            r7 = r8
            goto L_0x0122
        L_0x010a:
            boolean r8 = r7.hasSentRttUpgradeRequest()
            if (r8 == 0) goto L_0x011d
            java.lang.Object[] r8 = new java.lang.Object[r3]
            java.lang.String r10 = "upgrading to rtt"
            com.android.dialer.common.LogUtil.m9i(r9, r10, r8)
            com.android.incallui.InCallActivity$ShouldShowUiResult r8 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r8.<init>(r2, r7)
            goto L_0x0108
        L_0x011d:
            com.android.incallui.InCallActivity$ShouldShowUiResult r7 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r7.<init>(r3, r6)
        L_0x0122:
            com.android.incallui.speakeasy.SpeakEasyCallManager r8 = r20.getSpeakEasyCallManager()
            if (r8 != 0) goto L_0x012f
            com.android.incallui.InCallActivity$ShouldShowUiResult r8 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r8.<init>(r3, r6)
            goto L_0x019f
        L_0x012f:
            com.android.incallui.call.CallList r9 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r9 = r9.getIncomingCall()
            if (r9 == 0) goto L_0x0142
            com.android.incallui.call.CallList r9 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r9 = r9.getIncomingCall()
            goto L_0x014a
        L_0x0142:
            com.android.incallui.call.CallList r9 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r9 = r9.getActiveCall()
        L_0x014a:
            if (r9 != 0) goto L_0x0175
            com.android.incallui.call.CallList r8 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r8 = r8.getBackgroundCall()
            if (r8 == 0) goto L_0x016f
            boolean r10 = r8.isSpeakEasyCall()
            if (r10 == 0) goto L_0x016f
            java.lang.Object[] r9 = new java.lang.Object[r3]
            java.lang.String r10 = "InCallActivity.getShouldShowSpeakEasyUi"
            java.lang.String r11 = "taking call off hold"
            com.android.dialer.common.LogUtil.m9i(r10, r11, r9)
            r8.unhold()
            com.android.incallui.InCallActivity$ShouldShowUiResult r9 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r9.<init>(r2, r8)
            r8 = r9
            goto L_0x019f
        L_0x016f:
            com.android.incallui.InCallActivity$ShouldShowUiResult r8 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r8.<init>(r3, r9)
            goto L_0x019f
        L_0x0175:
            boolean r10 = r9.isSpeakEasyCall()
            if (r10 == 0) goto L_0x019a
            boolean r10 = r9.isSpeakEasyEligible()
            if (r10 != 0) goto L_0x0182
            goto L_0x019a
        L_0x0182:
            com.android.incallui.speakeasy.SpeakEasyCallManagerStub r8 = (com.android.incallui.speakeasy.SpeakEasyCallManagerStub) r8
            java.util.Optional r8 = r8.getSpeakEasyFragment(r9)
            boolean r8 = r8.isPresent()
            if (r8 != 0) goto L_0x0194
            com.android.incallui.InCallActivity$ShouldShowUiResult r8 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r8.<init>(r3, r9)
            goto L_0x019f
        L_0x0194:
            com.android.incallui.InCallActivity$ShouldShowUiResult r8 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r8.<init>(r2, r9)
            goto L_0x019f
        L_0x019a:
            com.android.incallui.InCallActivity$ShouldShowUiResult r8 = new com.android.incallui.InCallActivity$ShouldShowUiResult
            r8.<init>(r3, r9)
        L_0x019f:
            r9 = 9
            java.lang.Object[] r9 = new java.lang.Object[r9]
            boolean r10 = r5.shouldShow
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r9[r3] = r10
            boolean r10 = r7.shouldShow
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r9[r2] = r10
            boolean r10 = r4.shouldShow
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r11 = 2
            r9[r11] = r10
            r10 = 3
            boolean r12 = r8.shouldShow
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r9[r10] = r12
            r10 = 4
            boolean r12 = r0.didShowAnswerScreen
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r9[r10] = r12
            r10 = 5
            boolean r12 = r0.didShowInCallScreen
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r9[r10] = r12
            r10 = 6
            boolean r12 = r0.didShowRttCallScreen
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r9[r10] = r12
            r10 = 7
            boolean r12 = r0.didShowVideoCallScreen
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r9[r10] = r12
            r10 = 8
            boolean r12 = r0.didShowSpeakEasyScreen
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r9[r10] = r12
            java.lang.String r10 = "shouldShowAnswerUi: %b, shouldShowRttUi: %b, shouldShowVideoUi: %b, shouldShowSpeakEasyUi: %b, didShowAnswerScreen: %b, didShowInCallScreen: %b, didShowRttCallScreen: %b, didShowVideoCallScreen: %b, didShowSpeakEasyScreen: %b"
            com.android.dialer.common.LogUtil.m9i(r1, r10, r9)
            boolean r1 = r4.shouldShow
            r0.setAllowOrientationChange(r1)
            android.support.v4.app.FragmentManager r1 = r20.getSupportFragmentManager()
            android.support.v4.app.FragmentTransaction r1 = r1.beginTransaction()
            boolean r9 = r5.shouldShow
            r10 = 2131296657(0x7f090191, float:1.8211237E38)
            if (r9 == 0) goto L_0x032e
            boolean r4 = r0.hideInCallScreenFragment(r1)
            boolean r6 = r0.hideVideoCallScreenFragment(r1)
            r4 = r4 | r6
            boolean r6 = r0.hideRttCallScreenFragment(r1)
            r4 = r4 | r6
            boolean r6 = r0.hideSpeakEasyFragment(r1)
            r4 = r4 | r6
            com.android.incallui.call.DialerCall r5 = r5.call
            boolean r6 = r0.didShowAnswerScreen
            if (r6 == 0) goto L_0x022a
            if (r5 != 0) goto L_0x022a
        L_0x0227:
            r2 = r3
            goto L_0x032b
        L_0x022a:
            if (r5 == 0) goto L_0x022e
            r6 = r2
            goto L_0x022f
        L_0x022e:
            r6 = r3
        L_0x022f:
            java.lang.Object[] r7 = new java.lang.Object[r3]
            java.lang.String r8 = "didShowAnswerScreen was false but call was still null"
            com.android.dialer.common.Assert.checkArgument(r6, r8, r7)
            boolean r15 = r5.hasReceivedVideoUpgradeRequest()
            boolean r6 = r0.didShowAnswerScreen
            if (r6 == 0) goto L_0x0283
            com.android.incallui.answer.protocol.AnswerScreen r6 = r20.getAnswerScreen()
            com.android.incallui.answer.impl.AnswerFragment r6 = (com.android.incallui.answer.impl.AnswerFragment) r6
            java.lang.String r7 = r6.getCallId()
            java.lang.String r8 = r5.getId()
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0269
            boolean r7 = r6.isVideoCall()
            boolean r8 = r5.isVideoCall()
            if (r7 != r8) goto L_0x0269
            boolean r7 = r6.isVideoUpgradeRequest()
            if (r7 != r15) goto L_0x0269
            boolean r7 = r6.isActionTimeout()
            if (r7 != 0) goto L_0x0269
            goto L_0x0227
        L_0x0269:
            boolean r6 = r6.isActionTimeout()
            java.lang.String r7 = "InCallActivity.showAnswerScreenFragment"
            if (r6 == 0) goto L_0x0279
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r8 = "answer fragment exists but has been accepted/rejected and timed out"
            com.android.dialer.common.LogUtil.m9i(r7, r8, r6)
            goto L_0x0280
        L_0x0279:
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r8 = "answer fragment exists but arguments do not match"
            com.android.dialer.common.LogUtil.m9i(r7, r8, r6)
        L_0x0280:
            r0.hideAnswerScreenFragment(r1)
        L_0x0283:
            java.lang.String r12 = r5.getId()
            boolean r13 = r5.isActiveRttCall()
            boolean r14 = r5.isVideoCall()
            com.android.incallui.videotech.VideoTech r6 = r5.getVideoTech()
            boolean r16 = r6.isSelfManagedCamera()
            com.android.incallui.call.CallList r6 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r6 = r6.getActiveCall()
            java.lang.String r7 = "InCallActivity.shouldAllowAnswerAndRelease"
            if (r6 != 0) goto L_0x02ab
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r6 = "no active call"
            com.android.dialer.common.LogUtil.m9i(r7, r6, r5)
            goto L_0x02f2
        L_0x02ab:
            java.lang.Class<android.telephony.TelephonyManager> r6 = android.telephony.TelephonyManager.class
            java.lang.Object r6 = r0.getSystemService(r6)
            android.telephony.TelephonyManager r6 = (android.telephony.TelephonyManager) r6
            int r6 = r6.getPhoneType()
            if (r6 != r11) goto L_0x02c1
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r6 = "PHONE_TYPE_CDMA not supported"
            com.android.dialer.common.LogUtil.m9i(r7, r6, r5)
            goto L_0x02f2
        L_0x02c1:
            boolean r6 = r5.isVideoCall()
            if (r6 != 0) goto L_0x02eb
            boolean r5 = r5.hasReceivedVideoUpgradeRequest()
            if (r5 == 0) goto L_0x02ce
            goto L_0x02eb
        L_0x02ce:
            com.android.dialer.configprovider.ConfigProviderComponent r5 = com.android.dialer.configprovider.ConfigProviderComponent.get(r20)
            com.android.dialer.configprovider.ConfigProvider r5 = r5.getConfigProvider()
            com.android.dialer.configprovider.SharedPrefConfigProvider r5 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r5
            java.lang.String r6 = "answer_and_release_enabled"
            boolean r5 = r5.getBoolean(r6, r2)
            if (r5 != 0) goto L_0x02e8
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r6 = "disabled by config"
            com.android.dialer.common.LogUtil.m9i(r7, r6, r5)
            goto L_0x02f2
        L_0x02e8:
            r17 = r2
            goto L_0x02f4
        L_0x02eb:
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r6 = "video call"
            com.android.dialer.common.LogUtil.m9i(r7, r6, r5)
        L_0x02f2:
            r17 = r3
        L_0x02f4:
            com.android.incallui.call.CallList r5 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r5 = r5.getBackgroundCall()
            if (r5 == 0) goto L_0x0301
            r18 = r2
            goto L_0x0303
        L_0x0301:
            r18 = r3
        L_0x0303:
            com.android.incallui.speakeasy.SpeakEasyCallManager r5 = r20.getSpeakEasyCallManager()
            android.content.Context r6 = r20.getApplicationContext()
            com.android.incallui.speakeasy.SpeakEasyCallManagerStub r5 = (com.android.incallui.speakeasy.SpeakEasyCallManagerStub) r5
            r5.isAvailable(r6)
            r19 = 0
            com.android.incallui.answer.impl.AnswerFragment r5 = com.android.incallui.answer.impl.AnswerFragment.newInstance(r12, r13, r14, r15, r16, r17, r18, r19)
            r5.getAnswerScreenFragment()
            java.lang.String r6 = "tag_answer_screen"
            r1.add(r10, r5, r6)
            com.android.dialer.logging.LoggingBindings r5 = com.android.dialer.logging.Logger.get(r20)
            com.android.dialer.logging.ScreenEvent$Type r6 = com.android.dialer.logging.ScreenEvent$Type.INCOMING_CALL
            com.android.dialer.logging.LoggingBindingsStub r5 = (com.android.dialer.logging.LoggingBindingsStub) r5
            r5.logScreenView(r6, r0)
            r0.didShowAnswerScreen = r2
        L_0x032b:
            r2 = r2 | r4
            goto L_0x04b5
        L_0x032e:
            boolean r5 = r4.shouldShow
            if (r5 == 0) goto L_0x03a4
            boolean r5 = r0.hideInCallScreenFragment(r1)
            com.android.incallui.call.DialerCall r4 = r4.call
            boolean r6 = r0.didShowVideoCallScreen
            java.lang.String r7 = "InCallActivity.showVideoCallScreenFragment"
            if (r6 == 0) goto L_0x035c
            com.android.incallui.video.protocol.VideoCallScreen r6 = r20.getVideoCallScreen()
            java.lang.String r6 = r6.getCallId()
            java.lang.String r8 = r4.getId()
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x0352
            r2 = r3
            goto L_0x0392
        L_0x0352:
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r8 = "video call fragment exists but arguments do not match"
            com.android.dialer.common.LogUtil.m9i(r7, r8, r6)
            r0.hideVideoCallScreenFragment(r1)
        L_0x035c:
            java.lang.Object[] r6 = new java.lang.Object[r2]
            r6[r3] = r4
            java.lang.String r8 = "call: %s"
            com.android.dialer.common.LogUtil.m9i(r7, r8, r6)
            java.lang.String r6 = r4.getId()
            com.android.incallui.videotech.VideoTech r4 = r4.getVideoTech()
            boolean r4 = r4.shouldUseSurfaceView()
            if (r4 == 0) goto L_0x0378
            com.android.incallui.video.impl.SurfaceViewVideoCallFragment r4 = com.android.incallui.video.impl.SurfaceViewVideoCallFragment.newInstance(r6)
            goto L_0x037c
        L_0x0378:
            com.android.incallui.video.impl.VideoCallFragment r4 = com.android.incallui.video.impl.VideoCallFragment.newInstance(r6)
        L_0x037c:
            android.support.v4.app.Fragment r4 = r4.getVideoCallScreenFragment()
            java.lang.String r6 = "tag_video_call_screen"
            r1.add(r10, r4, r6)
            com.android.dialer.logging.LoggingBindings r4 = com.android.dialer.logging.Logger.get(r20)
            com.android.dialer.logging.ScreenEvent$Type r6 = com.android.dialer.logging.ScreenEvent$Type.INCALL
            com.android.dialer.logging.LoggingBindingsStub r4 = (com.android.dialer.logging.LoggingBindingsStub) r4
            r4.logScreenView(r6, r0)
            r0.didShowVideoCallScreen = r2
        L_0x0392:
            r2 = r2 | r5
            boolean r4 = r0.hideRttCallScreenFragment(r1)
            r2 = r2 | r4
            boolean r4 = r0.hideSpeakEasyFragment(r1)
            r2 = r2 | r4
            boolean r4 = r0.hideAnswerScreenFragment(r1)
        L_0x03a1:
            r2 = r2 | r4
            goto L_0x04b5
        L_0x03a4:
            boolean r4 = r7.shouldShow
            if (r4 == 0) goto L_0x0414
            boolean r4 = r0.hideInCallScreenFragment(r1)
            boolean r5 = r0.hideVideoCallScreenFragment(r1)
            r4 = r4 | r5
            boolean r5 = r0.hideAnswerScreenFragment(r1)
            r4 = r4 | r5
            boolean r5 = r0.hideSpeakEasyFragment(r1)
            r4 = r4 | r5
            com.android.incallui.call.DialerCall r5 = r7.call
            boolean r7 = r0.didShowRttCallScreen
            java.lang.String r8 = "InCallActivity.showRttCallScreenFragment"
            if (r7 == 0) goto L_0x03e3
            com.android.incallui.rtt.protocol.RttCallScreen r7 = r20.getRttCallScreen()
            com.android.incallui.rtt.impl.RttChatFragment r7 = (com.android.incallui.rtt.impl.RttChatFragment) r7
            java.lang.String r7 = r7.getCallId()
            java.lang.String r9 = r5.getId()
            boolean r7 = r7.equals(r9)
            if (r7 == 0) goto L_0x03d9
            goto L_0x0227
        L_0x03d9:
            java.lang.Object[] r7 = new java.lang.Object[r3]
            java.lang.String r9 = "RTT call id doesn't match"
            com.android.dialer.common.LogUtil.m9i(r8, r9, r7)
            r0.hideRttCallScreenFragment(r1)
        L_0x03e3:
            java.lang.String r5 = r5.getId()
            com.android.incallui.rtt.impl.RttChatFragment r5 = com.android.incallui.rtt.impl.RttChatFragment.newInstance(r5)
            r5.getRttCallScreenFragment()
            java.lang.String r7 = "tag_rtt_call_screen"
            r1.add(r10, r5, r7)
            com.android.dialer.logging.LoggingBindings r5 = com.android.dialer.logging.Logger.get(r20)
            com.android.dialer.logging.ScreenEvent$Type r7 = com.android.dialer.logging.ScreenEvent$Type.INCALL
            com.android.dialer.logging.LoggingBindingsStub r5 = (com.android.dialer.logging.LoggingBindingsStub) r5
            r5.logScreenView(r7, r0)
            r0.didShowRttCallScreen = r2
            android.support.v4.app.DialogFragment r5 = r0.rttRequestDialogFragment
            if (r5 == 0) goto L_0x032b
            java.lang.Object[] r5 = new java.lang.Object[r3]
            java.lang.String r7 = "dismiss RTT request dialog"
            com.android.dialer.common.LogUtil.m9i(r8, r7, r5)
            android.support.v4.app.DialogFragment r5 = r0.rttRequestDialogFragment
            r5.dismiss()
            r0.rttRequestDialogFragment = r6
            goto L_0x032b
        L_0x0414:
            boolean r4 = r8.shouldShow
            if (r4 == 0) goto L_0x0480
            boolean r4 = r0.hideInCallScreenFragment(r1)
            boolean r5 = r0.hideVideoCallScreenFragment(r1)
            r4 = r4 | r5
            boolean r5 = r0.hideAnswerScreenFragment(r1)
            r4 = r4 | r5
            boolean r5 = r0.hideRttCallScreenFragment(r1)
            r4 = r4 | r5
            com.android.incallui.call.DialerCall r5 = r8.call
            boolean r6 = r0.didShowSpeakEasyScreen
            java.lang.String r7 = "InCallActivity.showSpeakEasyFragment"
            if (r6 == 0) goto L_0x0452
            java.lang.String r6 = r0.lastShownSpeakEasyScreenUniqueCallid
            java.lang.String r8 = r5.getUniqueCallId()
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x0448
            java.lang.Object[] r2 = new java.lang.Object[r3]
            java.lang.String r5 = "found existing fragment"
            com.android.dialer.common.LogUtil.m9i(r7, r5, r2)
            goto L_0x0227
        L_0x0448:
            r0.hideSpeakEasyFragment(r1)
            java.lang.Object[] r6 = new java.lang.Object[r3]
            java.lang.String r8 = "hid existing fragment"
            com.android.dialer.common.LogUtil.m9i(r7, r8, r6)
        L_0x0452:
            com.android.incallui.speakeasy.SpeakEasyCallManager r6 = r0.speakEasyCallManager
            com.android.incallui.speakeasy.SpeakEasyCallManagerStub r6 = (com.android.incallui.speakeasy.SpeakEasyCallManagerStub) r6
            java.util.Optional r6 = r6.getSpeakEasyFragment(r5)
            boolean r8 = r6.isPresent()
            if (r8 == 0) goto L_0x0227
            java.lang.Object r6 = r6.get()
            android.support.v4.app.Fragment r6 = (android.support.p000v4.app.Fragment) r6
            java.lang.String r8 = "tag_speak_easy_screen"
            r1.add(r10, r6, r8)
            r0.didShowSpeakEasyScreen = r2
            java.lang.String r5 = r5.getUniqueCallId()
            r0.lastShownSpeakEasyScreenUniqueCallid = r5
            java.lang.Object[] r5 = new java.lang.Object[r2]
            java.lang.String r6 = r0.lastShownSpeakEasyScreenUniqueCallid
            r5[r3] = r6
            java.lang.String r6 = "set fragment for call %s"
            com.android.dialer.common.LogUtil.m9i(r7, r6, r5)
            goto L_0x032b
        L_0x0480:
            boolean r4 = r0.didShowInCallScreen
            if (r4 == 0) goto L_0x0486
            r2 = r3
            goto L_0x04a0
        L_0x0486:
            com.android.incallui.incall.protocol.InCallScreen r4 = android.support.p002v7.appcompat.R$style.createInCallScreen()
            android.support.v4.app.Fragment r4 = r4.getInCallScreenFragment()
            java.lang.String r5 = "tag_in_call_screen"
            r1.add(r10, r4, r5)
            com.android.dialer.logging.LoggingBindings r4 = com.android.dialer.logging.Logger.get(r20)
            com.android.dialer.logging.ScreenEvent$Type r5 = com.android.dialer.logging.ScreenEvent$Type.INCALL
            com.android.dialer.logging.LoggingBindingsStub r4 = (com.android.dialer.logging.LoggingBindingsStub) r4
            r4.logScreenView(r5, r0)
            r0.didShowInCallScreen = r2
        L_0x04a0:
            boolean r4 = r0.hideVideoCallScreenFragment(r1)
            r2 = r2 | r4
            boolean r4 = r0.hideRttCallScreenFragment(r1)
            r2 = r2 | r4
            boolean r4 = r0.hideSpeakEasyFragment(r1)
            r2 = r2 | r4
            boolean r4 = r0.hideAnswerScreenFragment(r1)
            goto L_0x03a1
        L_0x04b5:
            if (r2 == 0) goto L_0x04cd
            java.lang.String r2 = "InCallActivity.commitTransaction"
            android.os.Trace.beginSection(r2)
            r1.commitNow()
            android.os.Trace.endSection()
            com.android.dialer.logging.LoggingBindings r1 = com.android.dialer.logging.Logger.get(r20)
            com.android.dialer.logging.ScreenEvent$Type r2 = com.android.dialer.logging.ScreenEvent$Type.INCALL
            com.android.dialer.logging.LoggingBindingsStub r1 = (com.android.dialer.logging.LoggingBindingsStub) r1
            r1.logScreenView(r2, r0)
        L_0x04cd:
            r0.isInShowMainInCallFragment = r3
            android.os.Trace.endSection()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.InCallActivity.showMainInCallFragment():void");
    }

    private void updateTaskDescription() {
        int i;
        if (getResources().getBoolean(R.bool.is_layout_landscape)) {
            Resources resources = getResources();
            Resources.Theme theme = getTheme();
            int i2 = Build.VERSION.SDK_INT;
            i = resources.getColor(R.color.statusbar_background_color, theme);
        } else {
            i = InCallPresenter.getInstance().getThemeColorManager().getSecondaryColor();
        }
        setTaskDescription(new ActivityManager.TaskDescription(getResources().getString(R.string.notification_ongoing_call), (Bitmap) null, i));
    }

    public void dismissKeyguard(boolean z) {
        if (this.dismissKeyguard != z) {
            this.dismissKeyguard = z;
            if (z) {
                getWindow().addFlags(4194304);
            } else {
                getWindow().clearFlags(4194304);
            }
        }
    }

    public void dismissPendingDialogs() {
        LogUtil.enterBlock("InCallActivity.dismissPendingDialogs");
        if (!this.isVisible) {
            LogUtil.m9i("InCallActivity.dismissPendingDialogs", "defer actions since activity is not visible", new Object[0]);
            this.needDismissPendingDialogs = true;
            return;
        }
        Dialog dialog = this.errorDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.errorDialog = null;
        }
        SelectPhoneAccountDialogFragment selectPhoneAccountDialogFragment2 = this.selectPhoneAccountDialogFragment;
        if (selectPhoneAccountDialogFragment2 != null) {
            selectPhoneAccountDialogFragment2.dismiss();
            this.selectPhoneAccountDialogFragment = null;
        }
        InternationalCallOnWifiDialogFragment internationalCallOnWifiDialogFragment = (InternationalCallOnWifiDialogFragment) getSupportFragmentManager().findFragmentByTag("tag_international_call_on_wifi");
        if (internationalCallOnWifiDialogFragment != null) {
            internationalCallOnWifiDialogFragment.dismiss();
        }
        AnswerScreen answerScreen = getAnswerScreen();
        if (answerScreen != null) {
            ((AnswerFragment) answerScreen).dismissPendingDialogs();
        }
        this.needDismissPendingDialogs = false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.touchDownWhenPseudoScreenOff) {
            if (motionEvent.getAction() == 1) {
                this.touchDownWhenPseudoScreenOff = false;
            }
            return true;
        } else if (InCallPresenter.getInstance().getPseudoScreenState().isOn()) {
            return super.dispatchTouchEvent(motionEvent);
        } else {
            if (motionEvent.getAction() == 0) {
                this.touchDownWhenPseudoScreenOff = true;
                LogUtil.m9i("InCallActivity.dispatchTouchEvent", "touchDownWhenPseudoScreenOff", new Object[0]);
            }
            return true;
        }
    }

    public void finish() {
        boolean z = true;
        if (!this.isVisible) {
            LogUtil.m9i("InCallActivity.shouldCloseActivityOnFinish", "allowing activity to be closed because it's not visible", new Object[0]);
        } else if (InCallPresenter.getInstance().isInCallUiLocked()) {
            LogUtil.m9i("InCallActivity.shouldCloseActivityOnFinish", "in call ui is locked, not closing activity", new Object[0]);
            z = false;
        } else {
            LogUtil.m9i("InCallActivity.shouldCloseActivityOnFinish", "activity is visible and has no locks, allowing activity to close", new Object[0]);
        }
        if (z) {
            super.finishAndRemoveTask();
        }
    }

    public boolean getCallCardFragmentVisible() {
        return this.didShowInCallScreen || this.didShowVideoCallScreen || this.didShowRttCallScreen || this.didShowSpeakEasyScreen;
    }

    public int getDialpadContainerId() {
        return getInCallOrRttCallScreen().getAnswerAndDialpadContainerResourceId();
    }

    public FragmentManager getDialpadFragmentManager() {
        InCallScreen inCallOrRttCallScreen = getInCallOrRttCallScreen();
        if (inCallOrRttCallScreen != null) {
            return inCallOrRttCallScreen.getInCallScreenFragment().getChildFragmentManager();
        }
        return null;
    }

    public SpeakEasyCallManager getSpeakEasyCallManager() {
        if (this.speakEasyCallManager == null) {
            this.speakEasyCallManager = InCallPresenter.getInstance().getSpeakEasyCallManager();
        }
        return this.speakEasyCallManager;
    }

    public void hideMainInCallFragment() {
        LogUtil.enterBlock("InCallActivity.hideMainInCallFragment");
        if (getCallCardFragmentVisible()) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            hideInCallScreenFragment(beginTransaction);
            hideVideoCallScreenFragment(beginTransaction);
            beginTransaction.commitAllowingStateLoss();
            getSupportFragmentManager().executePendingTransactions();
        }
    }

    public boolean isDialpadVisible() {
        DialpadFragment dialpadFragment = getDialpadFragment();
        return dialpadFragment != null && dialpadFragment.isAdded() && !dialpadFragment.isHidden() && dialpadFragment.getView() != null && dialpadFragment.getUserVisibleHint();
    }

    public boolean isInCallScreenAnimating() {
        return false;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public /* synthetic */ void lambda$onResume$2$InCallActivity() {
        ((StubMetrics) MetricsComponent.get(this).metrics()).recordMemory("IncallActivity.OnResume");
    }

    public /* synthetic */ void lambda$showDialogOrToastForDisconnectedCall$3$InCallActivity(InCallUiLock inCallUiLock, DialogInterface dialogInterface) {
        ((InCallPresenter.InCallUiLockImpl) inCallUiLock).release();
        onDialogDismissed();
    }

    public /* synthetic */ void lambda$showDialogOrToastForWifiHandoverFailure$4$InCallActivity(DialogInterface dialogInterface) {
        onDialogDismissed();
    }

    public /* synthetic */ void lambda$showDialogOrToastForWifiHandoverFailure$5$InCallActivity(DialerCall dialerCall, CheckBox checkBox, DialogInterface dialogInterface, int i) {
        dialerCall.setDoNotShowDialogForHandoffToWifiFailure(checkBox.isChecked());
        dialogInterface.cancel();
        onDialogDismissed();
    }

    public /* synthetic */ void lambda$showPhoneAccountSelectionDialog$0$InCallActivity(DialerCall dialerCall, PreferredAccountWorker.Result result) {
        String id = dialerCall.getId();
        if (result.getSelectedPhoneAccountHandle().isPresent()) {
            this.selectPhoneAccountListener.onPhoneAccountSelected(result.getSelectedPhoneAccountHandle().get(), false, id);
        } else if (!isVisible()) {
            LogUtil.m9i("InCallActivity.showPhoneAccountSelectionDialog", "activity ended before result returned", new Object[0]);
        } else {
            dialerCall.setPreferredAccountRecorder(new PreferredAccountRecorder(dialerCall.getNumber(), result.getSuggestion().orNull(), result.getDataId().orNull()));
            SelectPhoneAccountDialogOptions.Builder builder = result.getDialogOptionsBuilder().get();
            builder.setCallId(id);
            this.selectPhoneAccountDialogFragment = SelectPhoneAccountDialogFragment.newInstance((SelectPhoneAccountDialogOptions) builder.build(), this.selectPhoneAccountListener);
            this.selectPhoneAccountDialogFragment.show(getFragmentManager(), "tag_select_account_fragment");
        }
    }

    public AnswerScreenDelegate newAnswerScreenDelegate(AnswerScreen answerScreen) {
        AnswerFragment answerFragment = (AnswerFragment) answerScreen;
        if (CallList.getInstance().getCallById(answerFragment.getCallId()) != null) {
            return new AnswerScreenPresenter(this, answerFragment, CallList.getInstance().getCallById(answerFragment.getCallId()));
        }
        LogUtil.m9i("InCallActivity.onPrimaryCallStateChanged", "call doesn't exist, using stub", new Object[0]);
        return new AnswerScreenPresenterStub();
    }

    public InCallButtonUiDelegate newInCallButtonUiDelegate() {
        return new CallButtonPresenter(this);
    }

    public InCallScreenDelegate newInCallScreenDelegate() {
        return new CallCardPresenter(this);
    }

    public RttCallScreenDelegate newRttCallScreenDelegate(RttCallScreen rttCallScreen) {
        return new RttCallPresenter();
    }

    public VideoCallScreenDelegate newVideoCallScreenDelegate(VideoCallScreen videoCallScreen) {
        DialerCall callById = CallList.getInstance().getCallById(videoCallScreen.getCallId());
        if (callById == null || !callById.getVideoTech().shouldUseSurfaceView()) {
            return new VideoCallPresenter();
        }
        return callById.getVideoTech().createVideoCallScreenDelegate(this, videoCallScreen);
    }

    public void onBackPressed() {
        LogUtil.enterBlock("InCallActivity.onBackPressed");
        if (this.isVisible && getCallCardFragmentVisible()) {
            DialpadFragment dialpadFragment = getDialpadFragment();
            if (dialpadFragment != null && dialpadFragment.isVisible()) {
                showDialpadFragment(false, true);
            } else if (CallList.getInstance().getIncomingCall() != null) {
                LogUtil.m9i("InCallActivity.onBackPressed", "Ignore the press of the back key when an incoming call is ringing", new Object[0]);
            } else {
                super.onBackPressed();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        int i;
        Trace.beginSection("InCallActivity.onCreate");
        super.onCreate(bundle);
        this.preferredAccountWorkerResultListener = DialerExecutorComponent.get(this).createUiListener(getFragmentManager(), "preferredAccountWorkerResultListener");
        this.selectPhoneAccountListener = new SelectPhoneAccountListener(getApplicationContext());
        if (bundle != null) {
            this.didShowAnswerScreen = bundle.getBoolean("did_show_answer_screen");
            this.didShowInCallScreen = bundle.getBoolean("did_show_in_call_screen");
            this.didShowVideoCallScreen = bundle.getBoolean("did_show_video_call_screen");
            this.didShowRttCallScreen = bundle.getBoolean("did_show_rtt_call_screen");
            this.didShowSpeakEasyScreen = bundle.getBoolean("did_show_speak_easy_screen");
        }
        if (audioRouteForTesting.isPresent()) {
            i = audioRouteForTesting.get().intValue();
        } else {
            i = AudioModeProvider.getInstance().getAudioState().getRoute();
        }
        int i2 = 2;
        getWindow().addFlags((i == 2 && CallList.getInstance().getIncomingCall() == null) ? 557056 : 2654208);
        setContentView(R.layout.incall_screen);
        internalResolveIntent(getIntent());
        boolean z = getResources().getConfiguration().orientation == 2;
        boolean isRtl = CallUtil.isRtl();
        if (z) {
            this.dialpadSlideInAnimation = AnimationUtils.loadAnimation(this, isRtl ? R.anim.dialpad_slide_in_left : R.anim.dialpad_slide_in_right);
            this.dialpadSlideOutAnimation = AnimationUtils.loadAnimation(this, isRtl ? R.anim.dialpad_slide_out_left : R.anim.dialpad_slide_out_right);
        } else {
            this.dialpadSlideInAnimation = AnimationUtils.loadAnimation(this, R.anim.dialpad_slide_in_bottom);
            this.dialpadSlideOutAnimation = AnimationUtils.loadAnimation(this, R.anim.dialpad_slide_out_bottom);
        }
        this.dialpadSlideInAnimation.setInterpolator(AnimUtils.EASE_IN);
        this.dialpadSlideOutAnimation.setInterpolator(AnimUtils.EASE_OUT);
        this.dialpadSlideOutAnimation.setAnimationListener(new AnimationListenerAdapter() {
            public void onAnimationEnd(Animation animation) {
                InCallActivity.this.hideDialpadFragment();
            }
        });
        if (bundle != null && this.showDialpadRequest == 1) {
            if (bundle.containsKey("InCallActivity.show_dialpad")) {
                if (!bundle.getBoolean("InCallActivity.show_dialpad")) {
                    i2 = 3;
                }
                this.showDialpadRequest = i2;
                this.animateDialpadOnShow = false;
            }
            this.dtmfTextToPrepopulate = bundle.getString("InCallActivity.dialpad_text");
            SelectPhoneAccountDialogFragment selectPhoneAccountDialogFragment2 = (SelectPhoneAccountDialogFragment) getFragmentManager().findFragmentByTag("tag_select_account_fragment");
            if (selectPhoneAccountDialogFragment2 != null) {
                selectPhoneAccountDialogFragment2.setListener(this.selectPhoneAccountListener);
            }
        }
        this.inCallOrientationEventListener = new InCallOrientationEventListener(this);
        getWindow().getDecorView().setSystemUiVisibility(1536);
        this.pseudoBlackScreenOverlay = findViewById(R.id.psuedo_black_screen_overlay);
        sendBroadcast(CallPendingActivity.getFinishBroadcast());
        Trace.endSection();
        ((StubMetrics) MetricsComponent.get(this).metrics()).stopTimer("CallList.onCallAdded_To_InCallActivity.onCreate_Incoming");
        ((StubMetrics) MetricsComponent.get(this).metrics()).stopTimer("CallList.onCallAdded_To_InCallActivity.onCreate_Outgoing");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Trace.beginSection("InCallActivity.onDestroy");
        super.onDestroy();
        InCallPresenter.getInstance().unsetActivity(this);
        InCallPresenter.getInstance().updateIsChangingConfigurations();
        Trace.endSection();
    }

    public void onForegroundCallChanged(DialerCall dialerCall) {
        updateTaskDescription();
        if (dialerCall == null || !this.didShowAnswerScreen) {
            updateWindowBackgroundColor(0.0f);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 5) {
            if (!InCallPresenter.getInstance().handleCallKey()) {
                LogUtil.m8e("InCallActivity.onKeyDown", "InCallPresenter should always handle KEYCODE_CALL in onKeyDown", new Object[0]);
            }
            return true;
        } else if (i == 27) {
            return true;
        } else {
            if (i != 70) {
                if (i != 76) {
                    if (i == 91) {
                        TelecomAdapter.getInstance().mute(!AudioModeProvider.getInstance().getAudioState().isMuted());
                        return true;
                    } else if (!(i == 164 || i == 24)) {
                    }
                } else if (LogUtil.isVerboseEnabled()) {
                    Object[] objArr = {getWindow().getDecorView()};
                    return true;
                }
            }
            DialpadFragment dialpadFragment = getDialpadFragment();
            if (dialpadFragment == null || !dialpadFragment.isVisible() || !dialpadFragment.onDialerKeyDown(keyEvent)) {
                return super.onKeyDown(i, keyEvent);
            }
            return true;
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        DialpadFragment dialpadFragment = getDialpadFragment();
        if ((dialpadFragment == null || !dialpadFragment.isVisible() || !dialpadFragment.onDialerKeyUp(keyEvent)) && i != 5) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        LogUtil.enterBlock("InCallActivity.onNewIntent");
        if (!this.isVisible) {
            onNewIntent(intent, true);
            LogUtil.m9i("InCallActivity.onNewIntent", "Restarting InCallActivity to force screen on.", new Object[0]);
            recreate();
            return;
        }
        onNewIntent(intent, false);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        LogUtil.m9i("InCallActivity.onOptionsItemSelected", GeneratedOutlineSupport.outline6("item: ", menuItem), new Object[0]);
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Trace.beginSection("InCallActivity.onPause");
        super.onPause();
        DialpadFragment dialpadFragment = getDialpadFragment();
        if (dialpadFragment != null) {
            dialpadFragment.onDialerKeyUp((KeyEvent) null);
        }
        InCallPresenter.getInstance().getPseudoScreenState().removeListener(this);
        Trace.endSection();
    }

    public void onPrimaryCallStateChanged() {
        Trace.beginSection("InCallActivity.onPrimaryCallStateChanged");
        showMainInCallFragment();
        Trace.endSection();
    }

    public void onPseudoScreenStateChanged(boolean z) {
        int i = 0;
        LogUtil.m9i("InCallActivity.onPseudoScreenStateChanged", GeneratedOutlineSupport.outline10("isOn: ", z), new Object[0]);
        View view = this.pseudoBlackScreenOverlay;
        if (z) {
            i = 8;
        }
        view.setVisibility(i);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Trace.beginSection("InCallActivity.onResume");
        super.onResume();
        if (!InCallPresenter.getInstance().isReadyForTearDown()) {
            updateTaskDescription();
        }
        int i = this.showDialpadRequest;
        if (i != 1) {
            if (i == 2) {
                InCallPresenter.getInstance().setFullScreen(false, true);
                showDialpadFragment(true, this.animateDialpadOnShow);
                this.animateDialpadOnShow = false;
                DialpadFragment dialpadFragment = getDialpadFragment();
                if (dialpadFragment != null) {
                    dialpadFragment.setDtmfText(this.dtmfTextToPrepopulate);
                    this.dtmfTextToPrepopulate = null;
                }
            } else {
                LogUtil.m9i("InCallActivity.onResume", "Force-hide the dialpad", new Object[0]);
                if (getDialpadFragment() != null) {
                    showDialpadFragment(false, false);
                }
            }
            this.showDialpadRequest = 1;
        }
        CallList.getInstance().onInCallUiShown(getIntent().getBooleanExtra("InCallActivity.for_full_screen_intent", false));
        PseudoScreenState pseudoScreenState = InCallPresenter.getInstance().getPseudoScreenState();
        pseudoScreenState.addListener(this);
        onPseudoScreenStateChanged(pseudoScreenState.isOn());
        Trace.endSection();
        DialerExecutorModule.postDelayedOnUiThread(new Runnable() {
            public final void run() {
                InCallActivity.this.lambda$onResume$2$InCallActivity();
            }
        }, 1000);
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        if (this.needDismissPendingDialogs) {
            dismissPendingDialogs();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.enterBlock("InCallActivity.onSaveInstanceState");
        bundle.putBoolean("InCallActivity.show_dialpad", isDialpadVisible());
        DialpadFragment dialpadFragment = getDialpadFragment();
        if (dialpadFragment != null) {
            bundle.putString("InCallActivity.dialpad_text", dialpadFragment.getDtmfText());
        }
        bundle.putBoolean("did_show_answer_screen", this.didShowAnswerScreen);
        bundle.putBoolean("did_show_in_call_screen", this.didShowInCallScreen);
        bundle.putBoolean("did_show_video_call_screen", this.didShowVideoCallScreen);
        bundle.putBoolean("did_show_rtt_call_screen", this.didShowRttCallScreen);
        bundle.putBoolean("did_show_speak_easy_screen", this.didShowSpeakEasyScreen);
        super.onSaveInstanceState(bundle);
        this.isVisible = false;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        Trace.beginSection("InCallActivity.onStart");
        super.onStart();
        this.isVisible = true;
        showMainInCallFragment();
        InCallPresenter.getInstance().setActivity(this);
        enableInCallOrientationEventListener(getRequestedOrientation() == 2);
        InCallPresenter.getInstance().onActivityStarted();
        if (!this.isRecreating) {
            InCallPresenter.getInstance().onUiShowing(true);
        }
        if (isInMultiWindowMode() && !getResources().getBoolean(R.bool.incall_dialpad_allowed)) {
            showDialpadFragment(false, false);
        }
        Trace.endSection();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        DialerCall waitingForAccountCall;
        Trace.beginSection("InCallActivity.onStop");
        this.isVisible = false;
        super.onStop();
        if (!this.isRecreating && !((KeyguardManager) getSystemService(KeyguardManager.class)).isKeyguardLocked() && (waitingForAccountCall = CallList.getInstance().getWaitingForAccountCall()) != null) {
            waitingForAccountCall.disconnect();
        }
        enableInCallOrientationEventListener(false);
        InCallPresenter.getInstance().updateIsChangingConfigurations();
        InCallPresenter.getInstance().onActivityStopped();
        if (!this.isRecreating) {
            InCallPresenter.getInstance().onUiShowing(false);
        }
        Dialog dialog = this.errorDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        if (isFinishing()) {
            InCallPresenter.getInstance().unsetActivity(this);
        }
        Trace.endSection();
    }

    public void setAllowOrientationChange(boolean z) {
        if (this.allowOrientationChange != z) {
            this.allowOrientationChange = z;
            if (!z) {
                setRequestedOrientation(5);
            } else {
                setRequestedOrientation(2);
            }
            enableInCallOrientationEventListener(z);
        }
    }

    public void setExcludeFromRecents(boolean z) {
        int taskId = getTaskId();
        for (ActivityManager.AppTask next : ((ActivityManager) getSystemService(ActivityManager.class)).getAppTasks()) {
            try {
                if (next.getTaskInfo().id == taskId) {
                    next.setExcludeFromRecents(z);
                }
            } catch (RuntimeException e) {
                LogUtil.m7e("InCallActivity.setExcludeFromRecents", "RuntimeException:\n%s", (Throwable) e);
            }
        }
    }

    public void setSpeakEasyCallManager(SpeakEasyCallManager speakEasyCallManager2) {
        this.speakEasyCallManager = speakEasyCallManager2;
    }

    public void showConferenceFragment(boolean z) {
        if (z) {
            startActivity(new Intent(this, ManageConferenceActivity.class));
        }
    }

    public void showDialogForInternationalCallOnWifi(DialerCall dialerCall) {
        String id = dialerCall.getId();
        InternationalCallOnWifiDialogFragment internationalCallOnWifiDialogFragment = new InternationalCallOnWifiDialogFragment();
        Bundle bundle = new Bundle();
        Assert.isNotNull(id);
        bundle.putString("call_id", id);
        internationalCallOnWifiDialogFragment.setArguments(bundle);
        internationalCallOnWifiDialogFragment.show(getSupportFragmentManager(), "tag_international_call_on_wifi");
    }

    public void showDialogForPostCharWait(String str, String str2) {
        new PostCharDialogFragment(str, str2).show(getSupportFragmentManager(), "tag_post_char_dialog_fragment");
    }

    public void showDialogForRttRequest(DialerCall dialerCall, int i) {
        LogUtil.enterBlock("InCallActivity.showDialogForRttRequest");
        String id = dialerCall.getId();
        RttRequestDialogFragment rttRequestDialogFragment2 = new RttRequestDialogFragment();
        Bundle bundle = new Bundle();
        Assert.isNotNull(id);
        bundle.putString("call_id", id);
        bundle.putInt("rtt_request_id", i);
        rttRequestDialogFragment2.setArguments(bundle);
        this.rttRequestDialogFragment = rttRequestDialogFragment2;
        this.rttRequestDialogFragment.show(getSupportFragmentManager(), "tag_rtt_request_dialog");
    }

    public void showDialogOrToastForDisconnectedCall(DisconnectMessage disconnectMessage) {
        LogUtil.m9i("InCallActivity.showDialogOrToastForDisconnectedCall", "disconnect cause: %s", disconnectMessage);
        if (disconnectMessage.dialog != null && !isFinishing()) {
            dismissPendingDialogs();
            if (!isVisible()) {
                Toast.makeText(getApplicationContext(), disconnectMessage.toastMessage, 1).show();
                return;
            }
            this.errorDialog = disconnectMessage.dialog;
            disconnectMessage.dialog.setOnDismissListener(new DialogInterface.OnDismissListener(InCallPresenter.getInstance().acquireInCallUiLock("showErrorDialog")) {
                private final /* synthetic */ InCallUiLock f$1;

                {
                    this.f$1 = r2;
                }

                public final void onDismiss(DialogInterface dialogInterface) {
                    InCallActivity.this.lambda$showDialogOrToastForDisconnectedCall$3$InCallActivity(this.f$1, dialogInterface);
                }
            });
            disconnectMessage.dialog.getWindow().addFlags(2);
            disconnectMessage.dialog.show();
        }
    }

    public void showDialogOrToastForWifiHandoverFailure(DialerCall dialerCall) {
        if (dialerCall.showWifiHandoverAlertAsToast()) {
            Toast.makeText(this, R.string.video_call_lte_to_wifi_failed_message, 0).show();
            return;
        }
        dismissPendingDialogs();
        AlertDialog.Builder title = new AlertDialog.Builder(this).setTitle(R.string.video_call_lte_to_wifi_failed_title);
        View inflate = View.inflate(title.getContext(), R.layout.video_call_lte_to_wifi_failed, (ViewGroup) null);
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.video_call_lte_to_wifi_failed_checkbox);
        checkBox.setChecked(false);
        this.errorDialog = title.setView(inflate).setMessage(R.string.video_call_lte_to_wifi_failed_message).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public final void onCancel(DialogInterface dialogInterface) {
                InCallActivity.this.lambda$showDialogOrToastForWifiHandoverFailure$4$InCallActivity(dialogInterface);
            }
        }).setPositiveButton(17039370, new DialogInterface.OnClickListener(dialerCall, checkBox) {
            private final /* synthetic */ DialerCall f$1;
            private final /* synthetic */ CheckBox f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                InCallActivity.this.lambda$showDialogOrToastForWifiHandoverFailure$5$InCallActivity(this.f$1, this.f$2, dialogInterface, i);
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
            public final void onDismiss(DialogInterface dialogInterface) {
                ((InCallPresenter.InCallUiLockImpl) InCallUiLock.this).release();
            }
        }).create();
        this.errorDialog.show();
    }

    public void showDialpadFragment(boolean z, boolean z2) {
        if (z != isDialpadVisible()) {
            if (getDialpadFragmentManager() == null) {
                LogUtil.m9i("InCallActivity.showDialpadFragment", "Unable to obtain a FragmentManager", new Object[0]);
                return;
            }
            if (z2) {
                if (z) {
                    showDialpadFragment();
                    ((DialpadView) getDialpadFragment().getView().findViewById(R.id.dialpad_view)).animateShow();
                }
                getDialpadFragment().getView().startAnimation(z ? this.dialpadSlideInAnimation : this.dialpadSlideOutAnimation);
            } else if (z) {
                showDialpadFragment();
            } else {
                hideDialpadFragment();
            }
            ProximitySensor proximitySensor = InCallPresenter.getInstance().getProximitySensor();
            if (proximitySensor != null) {
                proximitySensor.onDialpadVisible(z);
            }
            this.showDialpadRequest = 1;
        }
    }

    public void updateWindowBackgroundColor(float f) {
        int i;
        int i2;
        int i3;
        ThemeColorManager themeColorManager = InCallPresenter.getInstance().getThemeColorManager();
        if (isInMultiWindowMode()) {
            i2 = themeColorManager.getBackgroundColorSolid();
            i = themeColorManager.getBackgroundColorSolid();
            i3 = themeColorManager.getBackgroundColorSolid();
        } else {
            i2 = themeColorManager.getBackgroundColorTop();
            i = themeColorManager.getBackgroundColorMiddle();
            i3 = themeColorManager.getBackgroundColorBottom();
        }
        if (f < 0.0f) {
            float abs = Math.abs(f);
            i2 = ColorUtils.blendARGB(i2, 1711276032, abs);
            i = ColorUtils.blendARGB(i, 1711276032, abs);
            i3 = ColorUtils.blendARGB(i3, 1711276032, abs);
        }
        boolean z = false;
        boolean z2 = true;
        if (this.backgroundDrawable == null) {
            this.backgroundDrawableColors = new int[]{i2, i, i3};
            this.backgroundDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, this.backgroundDrawableColors);
        } else {
            int[] iArr = this.backgroundDrawableColors;
            if (iArr[0] != i2) {
                iArr[0] = i2;
                z = true;
            }
            int[] iArr2 = this.backgroundDrawableColors;
            if (iArr2[1] != i) {
                iArr2[1] = i;
                z = true;
            }
            int[] iArr3 = this.backgroundDrawableColors;
            if (iArr3[2] != i3) {
                iArr3[2] = i3;
            } else {
                z2 = z;
            }
            if (z2) {
                this.backgroundDrawable.setColors(this.backgroundDrawableColors);
            }
        }
        if (z2) {
            getWindow().setBackgroundDrawable(this.backgroundDrawable);
        }
    }

    /* access modifiers changed from: package-private */
    public void onNewIntent(Intent intent, boolean z) {
        this.isRecreating = z;
        setIntent(intent);
        if (!z) {
            internalResolveIntent(intent);
        }
    }

    private void showDialpadFragment() {
        FragmentManager dialpadFragmentManager = getDialpadFragmentManager();
        if (dialpadFragmentManager != null) {
            FragmentTransaction beginTransaction = dialpadFragmentManager.beginTransaction();
            DialpadFragment dialpadFragment = getDialpadFragment();
            if (dialpadFragment == null) {
                dialpadFragment = new DialpadFragment();
                beginTransaction.add(getDialpadContainerId(), dialpadFragment, "tag_dialpad_fragment");
            } else {
                beginTransaction.show(dialpadFragment);
                dialpadFragment.setUserVisibleHint(true);
            }
            dialpadFragment.setShouldShowEndCallSpace(this.didShowInCallScreen);
            beginTransaction.commitAllowingStateLoss();
            dialpadFragmentManager.executePendingTransactions();
            ((LoggingBindingsStub) Logger.get(this)).logScreenView(ScreenEvent$Type.INCALL_DIALPAD, this);
            getInCallOrRttCallScreen().onInCallScreenDialpadVisibilityChange(true);
        }
    }
}
