package com.android.incallui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Trace;
import android.support.p002v7.appcompat.R$style;
import android.telecom.Call;
import android.telecom.CallAudioState;
import android.telecom.DisconnectCause;
import android.telecom.PhoneAccountHandle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.ArraySet;
import android.view.Window;
import android.widget.Toast;
import com.android.dialer.CallConfiguration;
import com.android.dialer.Mode;
import com.android.dialer.R;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.blocking.FilteredNumbersUtil;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.telecom.TelecomUtil;
import com.android.incallui.answerproximitysensor.PseudoScreenState;
import com.android.incallui.audiomode.AudioModeProvider;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.ExternalCallList;
import com.android.incallui.call.TelecomAdapter;
import com.android.incallui.disconnectdialog.DisconnectMessage;
import com.android.incallui.incalluilock.InCallUiLock;
import com.android.incallui.latencyreport.LatencyReport;
import com.android.incallui.spam.SpamCallListListener;
import com.android.incallui.speakeasy.SpeakEasyCallManager;
import com.android.incallui.telecomeventui.InternationalCallOnWifiDialogActivity;
import com.android.incallui.telecomeventui.InternationalCallOnWifiDialogFragment;
import com.android.incallui.videosurface.impl.VideoSurfaceTextureImpl;
import com.android.incallui.videosurface.protocol.VideoSurfaceTexture;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class InCallPresenter implements CallList.Listener, AudioModeProvider.AudioModeListener {
    private static final Bundle EMPTY_EXTRAS = new Bundle();
    private static InCallPresenter inCallPresenter;
    private CallList.Listener activeCallsListener;
    private boolean addCallClicked = false;
    private boolean automaticallyMutedByAddCall = false;
    private boolean boundAndWaitingForOutgoingCall;
    /* access modifiers changed from: private */
    public final Call.Callback callCallback = new Call.Callback() {
        public void onConferenceableCallsChanged(Call call, List<Call> list) {
            LogUtil.m9i("InCallPresenter.onConferenceableCallsChanged", GeneratedOutlineSupport.outline6("onConferenceableCallsChanged: ", call), new Object[0]);
            Call.Details details = call.getDetails();
            DialerCall dialerCallFromTelecomCall = InCallPresenter.this.callList.getDialerCallFromTelecomCall(call);
            if (dialerCallFromTelecomCall == null) {
                LogUtil.m10w("InCallPresenter.onDetailsChanged", GeneratedOutlineSupport.outline6("DialerCall not found in call list: ", call), new Object[0]);
            } else if (!details.hasProperty(64) || InCallPresenter.this.externalCallList.isCallTracked(call)) {
                for (InCallDetailsListener onDetailsChanged : InCallPresenter.this.detailsListeners) {
                    onDetailsChanged.onDetailsChanged(dialerCallFromTelecomCall, details);
                }
            } else {
                LogUtil.m9i("InCallPresenter.onDetailsChanged", GeneratedOutlineSupport.outline6("Call became external: ", call), new Object[0]);
                InCallPresenter.this.callList.onInternalCallMadeExternal(InCallPresenter.this.context, call);
                InCallPresenter.this.externalCallList.onCallAdded(call);
            }
        }

        public void onDetailsChanged(Call call, Call.Details details) {
            DialerCall dialerCallFromTelecomCall = InCallPresenter.this.callList.getDialerCallFromTelecomCall(call);
            if (dialerCallFromTelecomCall == null) {
                LogUtil.m10w("InCallPresenter.onDetailsChanged", GeneratedOutlineSupport.outline6("DialerCall not found in call list: ", call), new Object[0]);
            } else if (!details.hasProperty(64) || InCallPresenter.this.externalCallList.isCallTracked(call)) {
                for (InCallDetailsListener onDetailsChanged : InCallPresenter.this.detailsListeners) {
                    onDetailsChanged.onDetailsChanged(dialerCallFromTelecomCall, details);
                }
            } else {
                LogUtil.m9i("InCallPresenter.onDetailsChanged", GeneratedOutlineSupport.outline6("Call became external: ", call), new Object[0]);
                InCallPresenter.this.callList.onInternalCallMadeExternal(InCallPresenter.this.context, call);
                InCallPresenter.this.externalCallList.onCallAdded(call);
            }
        }

        public void onPostDialWait(Call call, String str) {
            DialerCall dialerCallFromTelecomCall = InCallPresenter.this.callList.getDialerCallFromTelecomCall(call);
            if (dialerCallFromTelecomCall == null) {
                LogUtil.m10w("InCallPresenter.onPostDialWait", GeneratedOutlineSupport.outline6("DialerCall not found in call list: ", call), new Object[0]);
            } else {
                InCallPresenter.this.onPostDialCharWait(dialerCallFromTelecomCall.getId(), str);
            }
        }
    };
    /* access modifiers changed from: private */
    public CallList callList;
    private final Set<CanAddCallListener> canAddCallListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private ContactInfoCache contactInfoCache;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public final Set<InCallDetailsListener> detailsListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private InCallDndHandler dndHandler;
    /* access modifiers changed from: private */
    public ExternalCallList externalCallList;
    private ExternalCallList.ExternalCallListener externalCallListener = new ExternalCallList.ExternalCallListener() {
        public void onExternalCallAdded(Call call) {
        }

        public void onExternalCallPulled(Call call) {
            LatencyReport latencyReport = new LatencyReport(call);
            latencyReport.onCallBlockingDone();
            InCallPresenter.this.callList.onCallAdded(InCallPresenter.this.context, call, latencyReport);
            call.registerCallback(InCallPresenter.this.callCallback);
        }

        public void onExternalCallRemoved(Call call) {
        }

        public void onExternalCallUpdated(Call call) {
        }
    };
    private ExternalCallNotifier externalCallNotifier;
    /* access modifiers changed from: private */
    public FilteredNumberAsyncQueryHandler filteredQueryHandler;
    private InCallActivity inCallActivity;
    private InCallCameraManager inCallCameraManager;
    private final Set<InCallEventListener> inCallEventListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private InCallState inCallState = InCallState.NO_CALLS;
    private final Set<InCallUiListener> inCallUiListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private final Set<InCallUiLock> inCallUiLocks = new ArraySet();
    private final List<IncomingCallListener> incomingCallListeners = new CopyOnWriteArrayList();
    private boolean isChangingConfigurations = false;
    private boolean isFullScreen = false;
    private final Set<InCallStateListener> listeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private VideoSurfaceTexture localVideoSurfaceTexture;
    private ManageConferenceActivity manageConferenceActivity;
    /* access modifiers changed from: private */
    public final FilteredNumberAsyncQueryHandler.OnCheckBlockedListener onCheckBlockedListener = new FilteredNumberAsyncQueryHandler.OnCheckBlockedListener() {
        public void onCheckComplete(Integer num) {
            if (num != null && num.intValue() != -1) {
                TelecomUtil.silenceRinger(InCallPresenter.this.context);
            }
        }
    };
    private final Set<InCallOrientationListener> orientationListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private PhoneStateListener phoneStateListener = new PhoneStateListener() {
        public void onCallStateChanged(int i, String str) {
            if (i == 1 && !FilteredNumbersUtil.hasRecentEmergencyCall(InCallPresenter.this.context)) {
                InCallPresenter.this.filteredQueryHandler.isBlockedNumber(InCallPresenter.this.onCheckBlockedListener, str, R$style.getCurrentCountryIso(InCallPresenter.this.context));
            }
        }
    };
    private ProximitySensor proximitySensor;
    private final PseudoScreenState pseudoScreenState = new PseudoScreenState();
    private VideoSurfaceTexture remoteVideoSurfaceTexture;
    private boolean screenTimeoutEnabled = true;
    private boolean serviceConnected;
    private CallList.Listener spamCallListListener;
    private SpeakEasyCallManager speakEasyCallManager;
    private StatusBarNotifier statusBarNotifier;
    private ThemeColorManager themeColorManager;
    private InCallVibrationHandler vibrationHandler;

    public interface CanAddCallListener {
    }

    public interface InCallDetailsListener {
        void onDetailsChanged(DialerCall dialerCall, Call.Details details);
    }

    public interface InCallEventListener {
        void onFullscreenModeChanged(boolean z);
    }

    public interface InCallOrientationListener {
    }

    public enum InCallState {
        NO_CALLS,
        INCOMING,
        INCALL,
        WAITING_FOR_ACCOUNT,
        PENDING_OUTGOING,
        OUTGOING;

        public boolean isConnectingOrConnected() {
            return this == INCOMING || this == OUTGOING || this == INCALL;
        }
    }

    public interface InCallStateListener {
        void onStateChange(InCallState inCallState, InCallState inCallState2, CallList callList);
    }

    public interface InCallUiListener {
        void onUiShowing(boolean z);
    }

    private class InCallUiLockImpl implements InCallUiLock {
        private final String tag;

        /* synthetic */ InCallUiLockImpl(String str, C06371 r3) {
            this.tag = str;
        }

        public void release() {
            Assert.isMainThread();
            InCallPresenter.this.releaseInCallUiLock(this);
        }

        public String toString() {
            return GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("InCallUiLock["), this.tag, "]");
        }
    }

    public interface IncomingCallListener {
        void onIncomingCall(InCallState inCallState, InCallState inCallState2, DialerCall dialerCall);
    }

    InCallPresenter() {
    }

    private void applyScreenTimeout() {
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 == null) {
            LogUtil.m8e("InCallPresenter.applyScreenTimeout", "InCallActivity is null.", new Object[0]);
            return;
        }
        Window window = inCallActivity2.getWindow();
        if (this.screenTimeoutEnabled) {
            window.clearFlags(128);
        } else {
            window.addFlags(128);
        }
    }

    private void attemptCleanup() {
        ExternalCallList externalCallList2;
        if (isReadyForTearDown()) {
            LogUtil.m9i("InCallPresenter.attemptCleanup", "Cleaning up", new Object[0]);
            cleanupSurfaces();
            this.isChangingConfigurations = false;
            ContactInfoCache contactInfoCache2 = this.contactInfoCache;
            if (contactInfoCache2 != null) {
                contactInfoCache2.clearCache();
            }
            this.contactInfoCache = null;
            ProximitySensor proximitySensor2 = this.proximitySensor;
            if (proximitySensor2 != null) {
                removeListener(proximitySensor2);
                this.proximitySensor.tearDown();
            }
            this.proximitySensor = null;
            StatusBarNotifier statusBarNotifier2 = this.statusBarNotifier;
            if (statusBarNotifier2 != null) {
                removeListener(statusBarNotifier2);
                ((EnrichedCallManagerStub) EnrichedCallComponent.get(this.context).getEnrichedCallManager()).unregisterStateChangedListener(this.statusBarNotifier);
            }
            ExternalCallNotifier externalCallNotifier2 = this.externalCallNotifier;
            if (!(externalCallNotifier2 == null || (externalCallList2 = this.externalCallList) == null)) {
                externalCallList2.removeExternalCallListener(externalCallNotifier2);
            }
            this.statusBarNotifier = null;
            InCallVibrationHandler inCallVibrationHandler = this.vibrationHandler;
            if (inCallVibrationHandler != null) {
                removeListener(inCallVibrationHandler);
            }
            this.vibrationHandler = null;
            InCallDndHandler inCallDndHandler = this.dndHandler;
            if (inCallDndHandler != null) {
                removeListener(inCallDndHandler);
            }
            this.dndHandler = null;
            CallList callList2 = this.callList;
            if (callList2 != null) {
                callList2.removeListener(this);
                this.callList.removeListener(this.spamCallListListener);
            }
            this.callList = null;
            this.context = null;
            this.inCallActivity = null;
            this.manageConferenceActivity = null;
            this.listeners.clear();
            this.incomingCallListeners.clear();
            this.detailsListeners.clear();
            this.canAddCallListeners.clear();
            this.orientationListeners.clear();
            this.inCallEventListeners.clear();
            this.inCallUiListeners.clear();
            if (!this.inCallUiLocks.isEmpty()) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("held in call locks: ");
                outline13.append(this.inCallUiLocks);
                LogUtil.m8e("InCallPresenter.attemptCleanup", outline13.toString(), new Object[0]);
                this.inCallUiLocks.clear();
            }
        }
    }

    private void attemptFinishActivity() {
        this.screenTimeoutEnabled = true;
        boolean z = this.inCallActivity != null && isActivityStarted();
        LogUtil.m9i("InCallPresenter.attemptFinishActivity", GeneratedOutlineSupport.outline10("Hide in call UI: ", z), new Object[0]);
        if (z) {
            this.inCallActivity.setExcludeFromRecents(true);
            this.inCallActivity.finish();
        }
    }

    static DialerCall getCallToDisplay(CallList callList2, DialerCall dialerCall, boolean z) {
        DialerCall activeCall = callList2.getActiveCall();
        if (activeCall != null && activeCall != dialerCall) {
            return activeCall;
        }
        DialerCall secondActiveCall = callList2.getSecondActiveCall();
        if (secondActiveCall != null && secondActiveCall != dialerCall) {
            return secondActiveCall;
        }
        if (!z) {
            DialerCall disconnectingCall = callList2.getDisconnectingCall();
            if (disconnectingCall != null && disconnectingCall != dialerCall) {
                return disconnectingCall;
            }
            DialerCall disconnectedCall = callList2.getDisconnectedCall();
            if (!(disconnectedCall == null || disconnectedCall == dialerCall)) {
                return disconnectedCall;
            }
        }
        DialerCall backgroundCall = callList2.getBackgroundCall();
        if (backgroundCall == null || backgroundCall == dialerCall) {
            return callList2.getSecondBackgroundCall();
        }
        return backgroundCall;
    }

    public static synchronized InCallPresenter getInstance() {
        InCallPresenter inCallPresenter2;
        synchronized (InCallPresenter.class) {
            if (inCallPresenter == null) {
                Trace.beginSection("InCallPresenter.Constructor");
                inCallPresenter = new InCallPresenter();
                Trace.endSection();
            }
            inCallPresenter2 = inCallPresenter;
        }
        return inCallPresenter2;
    }

    public static boolean isCallWithNoValidAccounts(DialerCall dialerCall) {
        if (dialerCall != null && !dialerCall.isEmergencyCall()) {
            Bundle intentExtras = dialerCall.getIntentExtras();
            if (intentExtras == null) {
                intentExtras = EMPTY_EXTRAS;
            }
            ArrayList parcelableArrayList = intentExtras.getParcelableArrayList("selectPhoneAccountAccounts");
            if (dialerCall.getAccountHandle() == null && (parcelableArrayList == null || parcelableArrayList.isEmpty())) {
                LogUtil.m9i("InCallPresenter.isCallWithNoValidAccounts", GeneratedOutlineSupport.outline6("No valid accounts for call ", dialerCall), new Object[0]);
                return true;
            }
        }
        return false;
    }

    private void notifyVideoPauseController(boolean z) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("mIsChangingConfigurations=");
        outline13.append(this.isChangingConfigurations);
        outline13.toString();
        if (!this.isChangingConfigurations) {
            VideoPauseController.getInstance().onUiShowing(z);
        }
    }

    /* access modifiers changed from: private */
    public void releaseInCallUiLock(InCallUiLock inCallUiLock) {
        Assert.isMainThread();
        LogUtil.m9i("InCallPresenter.releaseInCallUiLock", "releasing %s", inCallUiLock);
        this.inCallUiLocks.remove(inCallUiLock);
        if (this.inCallUiLocks.isEmpty()) {
            LogUtil.m9i("InCallPresenter.releaseInCallUiLock", "all locks released", new Object[0]);
            if (this.inCallState == InCallState.NO_CALLS) {
                LogUtil.m9i("InCallPresenter.releaseInCallUiLock", "no more calls, finishing UI", new Object[0]);
                attemptFinishActivity();
                attemptCleanup();
            }
        }
    }

    public static synchronized void setInstanceForTesting(InCallPresenter inCallPresenter2) {
        synchronized (InCallPresenter.class) {
            inCallPresenter = inCallPresenter2;
        }
    }

    private boolean shouldStartInBubbleModeWithExtras(Bundle bundle) {
        byte[] byteArray;
        if (!ReturnToCallController.isEnabled(this.context) || bundle == null || (byteArray = bundle.getByteArray("call_configuration")) == null) {
            return false;
        }
        try {
            CallConfiguration parseFrom = CallConfiguration.parseFrom(byteArray);
            LogUtil.m9i("InCallPresenter.shouldStartInBubbleMode", "call mode: " + parseFrom.getCallMode(), new Object[0]);
            if (parseFrom.getCallMode() == Mode.BUBBLE) {
                return true;
            }
            return false;
        } catch (InvalidProtocolBufferException unused) {
            return false;
        }
    }

    private void showDialogOrToastForDisconnectedCall(DialerCall dialerCall) {
        String str;
        if (dialerCall.getState() == 10) {
            if (dialerCall.getAccountHandle() == null && !dialerCall.isConferenceCall()) {
                Bundle intentExtras = dialerCall.getIntentExtras();
                if (intentExtras == null) {
                    intentExtras = new Bundle();
                }
                ArrayList parcelableArrayList = intentExtras.getParcelableArrayList("selectPhoneAccountAccounts");
                if (parcelableArrayList == null || parcelableArrayList.isEmpty()) {
                    if ("tel".equals(dialerCall.getHandle().getScheme())) {
                        str = this.context.getString(R.string.callFailed_simError);
                    } else {
                        str = this.context.getString(R.string.incall_error_supp_service_unknown);
                    }
                    dialerCall.setDisconnectCause(new DisconnectCause(1, (CharSequence) null, str, str));
                }
            }
            if (isActivityStarted()) {
                InCallActivity inCallActivity2 = this.inCallActivity;
                inCallActivity2.showDialogOrToastForDisconnectedCall(new DisconnectMessage(inCallActivity2, dialerCall));
                return;
            }
            CharSequence charSequence = new DisconnectMessage(this.context, dialerCall).toastMessage;
            if (charSequence != null) {
                Toast.makeText(this.context, charSequence, 1).show();
            }
        }
    }

    private InCallState startOrFinishUi(InCallState inCallState2) {
        Trace.beginSection("InCallPresenter.startOrFinishUi");
        "startOrFinishUi: " + this.inCallState + " -> " + inCallState2;
        if (inCallState2 == this.inCallState) {
            Trace.endSection();
            return inCallState2;
        }
        boolean z = InCallState.WAITING_FOR_ACCOUNT == inCallState2;
        InCallActivity inCallActivity2 = this.inCallActivity;
        boolean z2 = !isShowingInCallUi() || !(inCallActivity2 != null && inCallActivity2.getCallCardFragmentVisible());
        boolean z3 = (InCallState.PENDING_OUTGOING == inCallState2 && z2 && isCallWithNoValidAccounts(this.callList.getPendingOutgoingCall())) | (InCallState.OUTGOING == inCallState2 && z2) | (InCallState.PENDING_OUTGOING == this.inCallState && InCallState.INCALL == inCallState2 && !isShowingInCallUi());
        if (this.inCallActivity != null && !isActivityStarted()) {
            LogUtil.m9i("InCallPresenter.startOrFinishUi", "Undo the state change: " + inCallState2 + " -> " + this.inCallState, new Object[0]);
            Trace.endSection();
            return this.inCallState;
        }
        if ((inCallState2 == InCallState.INCOMING || inCallState2 == InCallState.PENDING_OUTGOING) && !z3 && isActivityStarted()) {
            this.inCallActivity.dismissPendingDialogs();
        }
        if ((z3 || z) && !shouldStartInBubbleMode()) {
            LogUtil.m9i("InCallPresenter.startOrFinishUi", "Start in call UI", new Object[0]);
            showInCall(false, !z);
        } else if (inCallState2 == InCallState.NO_CALLS) {
            this.inCallState = inCallState2;
            attemptFinishActivity();
            attemptCleanup();
        }
        Trace.endSection();
        return inCallState2;
    }

    private void updateActivity(InCallActivity inCallActivity2) {
        Trace.beginSection("InCallPresenter.updateActivity");
        boolean z = true;
        boolean z2 = false;
        if (inCallActivity2 != null) {
            if (this.inCallActivity == null) {
                this.context = inCallActivity2.getApplicationContext();
                LogUtil.m9i("InCallPresenter.updateActivity", "UI Initialized", new Object[0]);
            } else {
                z = false;
            }
            this.inCallActivity = inCallActivity2;
            this.inCallActivity.setExcludeFromRecents(false);
            CallList callList2 = this.callList;
            if (!(callList2 == null || callList2.getDisconnectedCall() == null)) {
                showDialogOrToastForDisconnectedCall(this.callList.getDisconnectedCall());
            }
            if (this.inCallState == InCallState.NO_CALLS) {
                LogUtil.m9i("InCallPresenter.updateActivity", "UI Initialized, but no calls left. Shut down", new Object[0]);
                attemptFinishActivity();
                Trace.endSection();
                return;
            }
        } else {
            LogUtil.m9i("InCallPresenter.updateActivity", "UI Destroyed", new Object[0]);
            this.inCallActivity = null;
            z2 = true;
        }
        if (z) {
            onCallListChange(this.callList);
        }
        if (z2) {
            attemptCleanup();
        }
        Trace.endSection();
    }

    public InCallUiLock acquireInCallUiLock(String str) {
        Assert.isMainThread();
        InCallUiLockImpl inCallUiLockImpl = new InCallUiLockImpl(str, (C06371) null);
        this.inCallUiLocks.add(inCallUiLockImpl);
        return inCallUiLockImpl;
    }

    public void addCallClicked() {
        if (!this.addCallClicked) {
            this.addCallClicked = true;
            if (!AudioModeProvider.getInstance().getAudioState().isMuted()) {
                TelecomAdapter.getInstance().mute(true);
                this.automaticallyMutedByAddCall = true;
            }
            TelecomAdapter.getInstance().addCall();
        }
    }

    public void addCanAddCallListener(CanAddCallListener canAddCallListener) {
        Objects.requireNonNull(canAddCallListener);
        this.canAddCallListeners.add(canAddCallListener);
    }

    public void addDetailsListener(InCallDetailsListener inCallDetailsListener) {
        Objects.requireNonNull(inCallDetailsListener);
        this.detailsListeners.add(inCallDetailsListener);
    }

    public void addInCallEventListener(InCallEventListener inCallEventListener) {
        Objects.requireNonNull(inCallEventListener);
        this.inCallEventListeners.add(inCallEventListener);
    }

    public void addInCallUiListener(InCallUiListener inCallUiListener) {
        this.inCallUiListeners.add(inCallUiListener);
    }

    public void addIncomingCallListener(IncomingCallListener incomingCallListener) {
        Objects.requireNonNull(incomingCallListener);
        this.incomingCallListeners.add(incomingCallListener);
    }

    public void addListener(InCallStateListener inCallStateListener) {
        Objects.requireNonNull(inCallStateListener);
        this.listeners.add(inCallStateListener);
    }

    public void addOrientationListener(InCallOrientationListener inCallOrientationListener) {
        Objects.requireNonNull(inCallOrientationListener);
        this.orientationListeners.add(inCallOrientationListener);
    }

    public void bringToForeground(boolean z) {
        if (!isShowingInCallUi() && this.inCallState != InCallState.NO_CALLS) {
            showInCall(z, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void cleanupSurfaces() {
        VideoSurfaceTexture videoSurfaceTexture = this.remoteVideoSurfaceTexture;
        if (videoSurfaceTexture != null) {
            ((VideoSurfaceTextureImpl) videoSurfaceTexture).setDoneWithSurface();
            this.remoteVideoSurfaceTexture = null;
        }
        VideoSurfaceTexture videoSurfaceTexture2 = this.localVideoSurfaceTexture;
        if (videoSurfaceTexture2 != null) {
            ((VideoSurfaceTextureImpl) videoSurfaceTexture2).setDoneWithSurface();
            this.localVideoSurfaceTexture = null;
        }
    }

    public void enableScreenTimeout(boolean z) {
        "enableScreenTimeout: value=" + z;
        this.screenTimeoutEnabled = z;
        applyScreenTimeout();
    }

    public CallList getCallList() {
        return this.callList;
    }

    /* access modifiers changed from: package-private */
    public ExternalCallNotifier getExternalCallNotifier() {
        return this.externalCallNotifier;
    }

    public InCallCameraManager getInCallCameraManager() {
        InCallCameraManager inCallCameraManager2;
        synchronized (this) {
            if (this.inCallCameraManager == null) {
                this.inCallCameraManager = new InCallCameraManager(this.context);
            }
            inCallCameraManager2 = this.inCallCameraManager;
        }
        return inCallCameraManager2;
    }

    public InCallState getInCallState() {
        return this.inCallState;
    }

    /* access modifiers changed from: package-private */
    public VideoSurfaceTexture getLocalVideoSurfaceTexture() {
        if (this.localVideoSurfaceTexture == null) {
            boolean z = false;
            Context context2 = this.context;
            if (context2 != null) {
                z = context2.getPackageManager().hasSystemFeature("com.google.android.feature.PIXEL_2017_EXPERIENCE");
            }
            this.localVideoSurfaceTexture = new VideoSurfaceTextureImpl(z, 1);
        }
        return this.localVideoSurfaceTexture;
    }

    public InCallState getPotentialStateFromCallList(CallList callList2) {
        InCallState inCallState2 = InCallState.NO_CALLS;
        if (callList2 == null) {
            return inCallState2;
        }
        if (callList2.getIncomingCall() != null) {
            inCallState2 = InCallState.INCOMING;
        } else if (callList2.getWaitingForAccountCall() != null) {
            inCallState2 = InCallState.WAITING_FOR_ACCOUNT;
        } else if (callList2.getPendingOutgoingCall() != null) {
            inCallState2 = InCallState.PENDING_OUTGOING;
        } else if (callList2.getOutgoingCall() != null) {
            inCallState2 = InCallState.OUTGOING;
        } else if (!(callList2.getActiveCall() == null && callList2.getBackgroundCall() == null && callList2.getDisconnectedCall() == null && callList2.getDisconnectingCall() == null)) {
            inCallState2 = InCallState.INCALL;
        }
        return (inCallState2 != InCallState.NO_CALLS || !this.boundAndWaitingForOutgoingCall) ? inCallState2 : InCallState.PENDING_OUTGOING;
    }

    public ProximitySensor getProximitySensor() {
        return this.proximitySensor;
    }

    public PseudoScreenState getPseudoScreenState() {
        return this.pseudoScreenState;
    }

    /* access modifiers changed from: package-private */
    public VideoSurfaceTexture getRemoteVideoSurfaceTexture() {
        if (this.remoteVideoSurfaceTexture == null) {
            boolean z = false;
            Context context2 = this.context;
            if (context2 != null) {
                z = context2.getPackageManager().hasSystemFeature("com.google.android.feature.PIXEL_2017_EXPERIENCE");
            }
            this.remoteVideoSurfaceTexture = new VideoSurfaceTextureImpl(z, 2);
        }
        return this.remoteVideoSurfaceTexture;
    }

    public SpeakEasyCallManager getSpeakEasyCallManager() {
        return this.speakEasyCallManager;
    }

    public ThemeColorManager getThemeColorManager() {
        return this.themeColorManager;
    }

    public boolean handleCallKey() {
        CallList callList2 = this.callList;
        DialerCall incomingCall = callList2.getIncomingCall();
        "incomingCall: " + incomingCall;
        if (incomingCall != null) {
            incomingCall.answer(0);
            return true;
        }
        DialerCall activeCall = callList2.getActiveCall();
        if (activeCall != null) {
            boolean can = activeCall.can(4);
            boolean can2 = activeCall.can(8);
            "activeCall: " + activeCall + ", canMerge: " + can + ", canSwap: " + can2;
            if (can) {
                TelecomAdapter.getInstance().merge(activeCall.getId());
                return true;
            } else if (can2) {
                TelecomAdapter.getInstance().swap(activeCall.getId());
                return true;
            }
        }
        DialerCall backgroundCall = callList2.getBackgroundCall();
        if (backgroundCall != null) {
            boolean can3 = backgroundCall.can(1);
            "heldCall: " + backgroundCall + ", canHold: " + can3;
            if (backgroundCall.getState() == 8 && can3) {
                backgroundCall.unhold();
            }
        }
        return true;
    }

    public boolean isActivityStarted() {
        InCallActivity inCallActivity2 = this.inCallActivity;
        return inCallActivity2 != null && !inCallActivity2.isDestroyed() && !this.inCallActivity.isFinishing();
    }

    public boolean isChangingConfigurations() {
        return this.isChangingConfigurations;
    }

    public boolean isDialpadVisible() {
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 == null) {
            return false;
        }
        return inCallActivity2.isDialpadVisible();
    }

    public boolean isFullscreen() {
        return this.isFullScreen;
    }

    public boolean isInCallUiLocked() {
        Assert.isMainThread();
        if (this.inCallUiLocks.isEmpty()) {
            return false;
        }
        for (InCallUiLock inCallUiLock : this.inCallUiLocks) {
            LogUtil.m9i("InCallPresenter.isInCallUiLocked", "still locked by %s", inCallUiLock);
        }
        return true;
    }

    public boolean isReadyForTearDown() {
        return this.inCallActivity == null && !this.serviceConnected && this.inCallState == InCallState.NO_CALLS;
    }

    public boolean isShowingInCallUi() {
        if (!isActivityStarted()) {
            return false;
        }
        ManageConferenceActivity manageConferenceActivity2 = this.manageConferenceActivity;
        if (manageConferenceActivity2 == null || !manageConferenceActivity2.isVisible()) {
            return this.inCallActivity.isVisible();
        }
        return true;
    }

    public void maybeStartRevealAnimation(Intent intent) {
        Bundle bundleExtra;
        if (intent != null && this.inCallActivity == null && (bundleExtra = intent.getBundleExtra("android.telecom.extra.OUTGOING_CALL_EXTRAS")) != null && !bundleExtra.containsKey("selectPhoneAccountAccounts")) {
            Point point = (Point) bundleExtra.getParcelable("touchPoint");
            setBoundAndWaitingForOutgoingCall(true, (PhoneAccountHandle) intent.getParcelableExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE"));
            if (shouldStartInBubbleModeWithExtras(bundleExtra)) {
                LogUtil.m9i("InCallPresenter.maybeStartRevealAnimation", "shouldStartInBubbleMode", new Object[0]);
                return;
            }
            Intent intent2 = InCallActivity.getIntent(this.context, false, true, false);
            intent2.putExtra("touchPoint", point);
            this.context.startActivity(intent2);
        }
    }

    public void notifyFullscreenModeChange(boolean z) {
        for (InCallEventListener onFullscreenModeChanged : this.inCallEventListeners) {
            onFullscreenModeChanged.onFullscreenModeChanged(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void onActivityStarted() {
        notifyVideoPauseController(true);
        applyScreenTimeout();
    }

    /* access modifiers changed from: package-private */
    public void onActivityStopped() {
        notifyVideoPauseController(false);
    }

    public void onAudioStateChanged(CallAudioState callAudioState) {
        StatusBarNotifier statusBarNotifier2 = this.statusBarNotifier;
        if (statusBarNotifier2 != null) {
            statusBarNotifier2.updateNotification();
        }
    }

    public void onBringToForeground(boolean z) {
        LogUtil.m9i("InCallPresenter.onBringToForeground", "Bringing UI to foreground.", new Object[0]);
        bringToForeground(z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCallAdded(final android.telecom.Call r15) {
        /*
            r14 = this;
            java.lang.String r0 = "InCallPresenter.onCallAdded"
            android.os.Trace.beginSection(r0)
            com.android.incallui.latencyreport.LatencyReport r6 = new com.android.incallui.latencyreport.LatencyReport
            r6.<init>(r15)
            int r0 = r15.getState()
            r1 = 2
            r2 = 64
            r11 = 0
            if (r0 == r1) goto L_0x0016
        L_0x0014:
            r0 = r11
            goto L_0x0062
        L_0x0016:
            android.content.Context r0 = r14.context
            boolean r0 = android.support.design.R$dimen.isUserUnlocked(r0)
            java.lang.String r1 = "InCallPresenter.shouldAttemptBlocking"
            if (r0 != 0) goto L_0x0028
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.String r3 = "not attempting to block incoming call because user is locked"
            com.android.dialer.common.LogUtil.m9i(r1, r3, r0)
            goto L_0x0014
        L_0x0028:
            boolean r0 = android.support.p002v7.appcompat.R$style.isEmergencyCall(r15)
            if (r0 == 0) goto L_0x0036
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.String r3 = "Not attempting to block incoming emergency call"
            com.android.dialer.common.LogUtil.m9i(r1, r3, r0)
            goto L_0x0014
        L_0x0036:
            android.content.Context r0 = r14.context
            boolean r0 = com.android.dialer.blocking.FilteredNumbersUtil.hasRecentEmergencyCall(r0)
            if (r0 == 0) goto L_0x0046
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.String r3 = "Not attempting to block incoming call due to recent emergency call"
            com.android.dialer.common.LogUtil.m9i(r1, r3, r0)
            goto L_0x0014
        L_0x0046:
            android.telecom.Call$Details r0 = r15.getDetails()
            boolean r0 = r0.hasProperty(r2)
            if (r0 == 0) goto L_0x0051
            goto L_0x0014
        L_0x0051:
            android.content.Context r0 = r14.context
            boolean r0 = com.android.dialer.blocking.FilteredNumberCompat.useNewFiltering(r0)
            if (r0 == 0) goto L_0x0061
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.String r3 = "not attempting to block incoming call because framework blocking is in use"
            com.android.dialer.common.LogUtil.m9i(r1, r3, r0)
            goto L_0x0014
        L_0x0061:
            r0 = 1
        L_0x0062:
            if (r0 == 0) goto L_0x0095
            android.content.Context r0 = r14.context
            java.lang.String r0 = android.support.p002v7.appcompat.R$style.getCurrentCountryIso(r0)
            java.lang.String r12 = android.support.p002v7.appcompat.R$style.getNumber(r15)
            long r9 = java.lang.System.currentTimeMillis()
            java.util.concurrent.atomic.AtomicBoolean r3 = new java.util.concurrent.atomic.AtomicBoolean
            r3.<init>(r11)
            android.os.Handler r4 = new android.os.Handler
            r4.<init>()
            com.android.incallui.InCallPresenter$5 r5 = new com.android.incallui.InCallPresenter$5
            r5.<init>(r3, r6, r15)
            r1 = 1000(0x3e8, double:4.94E-321)
            r4.postDelayed(r5, r1)
            com.android.incallui.InCallPresenter$6 r13 = new com.android.incallui.InCallPresenter$6
            r1 = r13
            r2 = r14
            r7 = r15
            r8 = r12
            r1.<init>(r3, r4, r5, r6, r7, r8, r9)
            com.android.dialer.blocking.FilteredNumberAsyncQueryHandler r1 = r14.filteredQueryHandler
            r1.isBlockedNumber(r13, r12, r0)
            goto L_0x00af
        L_0x0095:
            android.telecom.Call$Details r0 = r15.getDetails()
            boolean r0 = r0.hasProperty(r2)
            if (r0 == 0) goto L_0x00a5
            com.android.incallui.call.ExternalCallList r0 = r14.externalCallList
            r0.onCallAdded(r15)
            goto L_0x00af
        L_0x00a5:
            r6.onCallBlockingDone()
            com.android.incallui.call.CallList r0 = r14.callList
            android.content.Context r1 = r14.context
            r0.onCallAdded(r1, r15, r6)
        L_0x00af:
            r0 = 0
            r14.setBoundAndWaitingForOutgoingCall(r11, r0)
            android.telecom.Call$Callback r0 = r14.callCallback
            r15.registerCallback(r0)
            android.content.Context r14 = r14.context
            android.content.Context r14 = r14.getApplicationContext()
            com.android.dialer.calllog.config.CallLogConfigComponent r0 = com.android.dialer.calllog.config.CallLogConfigComponent.get(r14)
            com.android.dialer.calllog.config.CallLogConfig r0 = r0.callLogConfig()
            com.android.dialer.calllog.config.CallLogConfigImpl r0 = (com.android.dialer.calllog.config.CallLogConfigImpl) r0
            boolean r0 = r0.isCallLogFrameworkEnabled()
            if (r0 != 0) goto L_0x00cf
            goto L_0x00eb
        L_0x00cf:
            com.android.dialer.phonelookup.PhoneLookupComponent r0 = com.android.dialer.phonelookup.PhoneLookupComponent.get(r14)
            com.android.dialer.phonelookup.composite.CompositePhoneLookup r0 = r0.compositePhoneLookup()
            com.google.common.util.concurrent.ListenableFuture r0 = r0.lookup((android.telecom.Call) r15)
            com.android.incallui.PhoneLookupHistoryRecorder$1 r1 = new com.android.incallui.PhoneLookupHistoryRecorder$1
            r1.<init>(r14, r15)
            com.android.dialer.common.concurrent.DialerExecutorComponent r14 = com.android.dialer.common.concurrent.DialerExecutorComponent.get(r14)
            com.google.common.util.concurrent.ListeningExecutorService r14 = r14.backgroundExecutor()
            com.google.common.util.concurrent.Futures.addCallback(r0, r1, r14)
        L_0x00eb:
            android.os.Trace.endSection()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.InCallPresenter.onCallAdded(android.telecom.Call):void");
    }

    public void onCallListChange(CallList callList2) {
        DialerCall waitingForAccountCall;
        Trace.beginSection("InCallPresenter.onCallListChange");
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null && inCallActivity2.isInCallScreenAnimating()) {
            Trace.endSection();
        } else if (callList2 == null) {
            Trace.endSection();
        } else {
            InCallState potentialStateFromCallList = getPotentialStateFromCallList(callList2);
            InCallState inCallState2 = this.inCallState;
            "onCallListChange oldState= " + inCallState2 + " newState=" + potentialStateFromCallList;
            if (potentialStateFromCallList == InCallState.INCOMING && (waitingForAccountCall = callList2.getWaitingForAccountCall()) != null) {
                waitingForAccountCall.disconnect();
                if (isActivityStarted()) {
                    this.inCallActivity.dismissPendingDialogs();
                }
            }
            InCallState startOrFinishUi = startOrFinishUi(potentialStateFromCallList);
            "onCallListChange newState changed to " + startOrFinishUi;
            boolean z = false;
            LogUtil.m9i("InCallPresenter.onCallListChange", "Phone switching state: " + inCallState2 + " -> " + startOrFinishUi, new Object[0]);
            this.inCallState = startOrFinishUi;
            DialerCall dialerCall = null;
            if (startOrFinishUi == InCallState.INCOMING) {
                dialerCall = callList2.getIncomingCall();
            } else if (startOrFinishUi == InCallState.PENDING_OUTGOING || startOrFinishUi == InCallState.OUTGOING) {
                dialerCall = callList2.getOutgoingCall();
                if (dialerCall == null) {
                    dialerCall = callList2.getPendingOutgoingCall();
                }
            } else if (startOrFinishUi == InCallState.INCALL) {
                dialerCall = getCallToDisplay(callList2, (DialerCall) null, false);
            }
            if (dialerCall != null) {
                onForegroundCallChanged(dialerCall);
            }
            for (InCallStateListener next : this.listeners) {
                "Notify " + next + " of state " + this.inCallState.toString();
                next.onStateChange(inCallState2, this.inCallState, callList2);
            }
            if (isActivityStarted()) {
                if (!(callList2.getActiveOrBackgroundCall() == null && callList2.getOutgoingCall() == null)) {
                    z = true;
                }
                this.inCallActivity.dismissKeyguard(z);
            }
            Trace.endSection();
        }
    }

    public void onCallRemoved(Call call) {
        if (call.getDetails().hasProperty(64)) {
            this.externalCallList.onCallRemoved(call);
            return;
        }
        this.callList.onCallRemoved(this.context, call);
        call.unregisterCallback(this.callCallback);
    }

    public void onCanAddCallChanged(boolean z) {
        Iterator<CanAddCallListener> it = this.canAddCallListeners.iterator();
        while (it.hasNext()) {
            ((CallButtonPresenter) it.next()).onCanAddCallChanged(z);
        }
    }

    public void onDeviceOrientationChange(int i) {
        "onDeviceOrientationChange: orientation= " + i;
        CallList callList2 = this.callList;
        if (callList2 != null) {
            callList2.notifyCallsOfDeviceRotation(i);
        } else {
            LogUtil.m10w("InCallPresenter.onDeviceOrientationChange", "CallList is null.", new Object[0]);
        }
        Iterator<InCallOrientationListener> it = this.orientationListeners.iterator();
        while (it.hasNext()) {
            ((VideoCallPresenter) it.next()).onDeviceOrientationChanged(i);
        }
    }

    public void onDisconnect(DialerCall dialerCall) {
        showDialogOrToastForDisconnectedCall(dialerCall);
        onCallListChange(this.callList);
        boolean z = false;
        if (isActivityStarted()) {
            this.inCallActivity.dismissKeyguard(false);
        }
        if (dialerCall.isEmergencyCall()) {
            FilteredNumbersUtil.recordLastEmergencyCallTime(this.context);
        }
        if (!this.callList.hasLiveCall() && !dialerCall.getLogState().isIncoming) {
            String number = dialerCall.getNumber();
            if (number != null && (number.length() <= 8 || number.startsWith("*#*#") || number.endsWith("#*#*"))) {
                z = true;
            }
            if (!z && !dialerCall.isVoiceMailNumber()) {
                Context context2 = this.context;
                StorageComponent.get(context2).unencryptedSharedPrefs().edit().putLong("post_call_call_connect_time", dialerCall.getConnectTimeMillis()).putLong("post_call_call_disconnect_time", System.currentTimeMillis()).putString("post_call_call_number", dialerCall.getNumber()).apply();
            }
        }
    }

    public void onForegroundCallChanged(DialerCall dialerCall) {
        this.themeColorManager.onForegroundCallChanged(this.context, dialerCall);
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.onForegroundCallChanged(dialerCall);
        }
    }

    public void onHandoverToWifiFailed(DialerCall dialerCall) {
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.showDialogOrToastForWifiHandoverFailure(dialerCall);
        } else {
            Toast.makeText(this.context, R.string.video_call_lte_to_wifi_failed_message, 0).show();
        }
    }

    public void onIncomingCall(DialerCall dialerCall) {
        Trace.beginSection("InCallPresenter.onIncomingCall");
        InCallState startOrFinishUi = startOrFinishUi(InCallState.INCOMING);
        InCallState inCallState2 = this.inCallState;
        LogUtil.m9i("InCallPresenter.onIncomingCall", "Phone switching state: " + inCallState2 + " -> " + startOrFinishUi, new Object[0]);
        this.inCallState = startOrFinishUi;
        Trace.beginSection("listener.onIncomingCall");
        for (IncomingCallListener onIncomingCall : this.incomingCallListeners) {
            onIncomingCall.onIncomingCall(inCallState2, this.inCallState, dialerCall);
        }
        Trace.endSection();
        Trace.beginSection("onPrimaryCallStateChanged");
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.onPrimaryCallStateChanged();
        }
        Trace.endSection();
        Trace.endSection();
    }

    public void onInternationalCallOnWifi(DialerCall dialerCall) {
        LogUtil.enterBlock("InCallPresenter.onInternationalCallOnWifi");
        if (!InternationalCallOnWifiDialogFragment.shouldShow(this.context)) {
            LogUtil.m9i("InCallPresenter.onInternationalCallOnWifi", "InternationalCallOnWifiDialogFragment.shouldShow returned false", new Object[0]);
            return;
        }
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.showDialogForInternationalCallOnWifi(dialerCall);
            return;
        }
        Intent intent = new Intent(this.context, InternationalCallOnWifiDialogActivity.class);
        intent.setFlags(402653184);
        intent.putExtra("extra_call_id", dialerCall.getId());
        this.context.startActivity(intent);
    }

    public void onPostDialCharWait(String str, String str2) {
        if (!isActivityStarted() || !this.inCallActivity.isVisible()) {
            Intent intent = new Intent(this.context, PostCharDialogActivity.class);
            intent.setFlags(402653184);
            intent.putExtra("extra_call_id", str);
            intent.putExtra("extra_post_dial_string", str2);
            this.context.startActivity(intent);
            return;
        }
        this.inCallActivity.showDialogForPostCharWait(str, str2);
    }

    public void onServiceBind() {
    }

    public void onServiceUnbind() {
        getInstance().setBoundAndWaitingForOutgoingCall(false, (PhoneAccountHandle) null);
    }

    public void onSessionModificationStateChange(DialerCall dialerCall) {
        boolean z = true;
        LogUtil.m9i("InCallPresenter.onSessionModificationStateChange", "state: %d", Integer.valueOf(dialerCall.getVideoTech().getSessionModificationState()));
        ProximitySensor proximitySensor2 = this.proximitySensor;
        if (proximitySensor2 == null) {
            LogUtil.m9i("InCallPresenter.onSessionModificationStateChange", "proximitySensor is null", new Object[0]);
            return;
        }
        if (!dialerCall.hasSentVideoUpgradeRequest() && !dialerCall.hasReceivedVideoUpgradeRequest()) {
            z = false;
        }
        proximitySensor2.setIsAttemptingVideoCall(z);
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.onPrimaryCallStateChanged();
        }
    }

    public void onSpeakEasyStateChange() {
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.onPrimaryCallStateChanged();
        }
    }

    public void onUiShowing(boolean z) {
        ProximitySensor proximitySensor2 = this.proximitySensor;
        if (proximitySensor2 != null) {
            proximitySensor2.onInCallShowing(z);
        }
        if (z) {
            refreshMuteState();
        } else {
            updateIsChangingConfigurations();
        }
        for (InCallUiListener onUiShowing : this.inCallUiListeners) {
            onUiShowing.onUiShowing(z);
        }
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.onPrimaryCallStateChanged();
        }
    }

    public void onUpgradeToRtt(DialerCall dialerCall, int i) {
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.showDialogForRttRequest(dialerCall, i);
        }
    }

    public void onUpgradeToVideo(DialerCall dialerCall) {
        if (R$style.hasReceivedVideoUpgradeRequest(dialerCall.getVideoTech().getSessionModificationState()) && this.inCallState == InCallState.INCOMING) {
            LogUtil.m9i("InCallPresenter.onUpgradeToVideo", "rejecting upgrade request due to existing incoming call", new Object[0]);
            dialerCall.getVideoTech().declineVideoRequest();
        }
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.onPrimaryCallStateChanged();
        }
    }

    public void onWiFiToLteHandover(DialerCall dialerCall) {
        if (!dialerCall.hasShownWiFiToLteHandoverToast()) {
            Toast.makeText(this.context, R.string.video_call_wifi_to_lte_handover_toast, 1).show();
            dialerCall.setHasShownWiFiToLteHandoverToast();
        }
    }

    public void refreshMuteState() {
        LogUtil.m9i("InCallPresenter.refreshMuteState", "refreshMuteStateAfterAddCall: %b addCallClicked: %b", Boolean.valueOf(this.automaticallyMutedByAddCall), Boolean.valueOf(this.addCallClicked));
        if (this.addCallClicked) {
            if (this.automaticallyMutedByAddCall) {
                TelecomAdapter.getInstance().mute(false);
                this.automaticallyMutedByAddCall = false;
            }
            this.addCallClicked = false;
        }
    }

    public void refreshUi() {
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.onPrimaryCallStateChanged();
        }
    }

    public void removeCanAddCallListener(CanAddCallListener canAddCallListener) {
        if (canAddCallListener != null) {
            this.canAddCallListeners.remove(canAddCallListener);
        }
    }

    public void removeDetailsListener(InCallDetailsListener inCallDetailsListener) {
        if (inCallDetailsListener != null) {
            this.detailsListeners.remove(inCallDetailsListener);
        }
    }

    public void removeInCallEventListener(InCallEventListener inCallEventListener) {
        if (inCallEventListener != null) {
            this.inCallEventListeners.remove(inCallEventListener);
        }
    }

    public boolean removeInCallUiListener(InCallUiListener inCallUiListener) {
        return this.inCallUiListeners.remove(inCallUiListener);
    }

    public void removeIncomingCallListener(IncomingCallListener incomingCallListener) {
        if (incomingCallListener != null) {
            this.incomingCallListeners.remove(incomingCallListener);
        }
    }

    public void removeListener(InCallStateListener inCallStateListener) {
        if (inCallStateListener != null) {
            this.listeners.remove(inCallStateListener);
        }
    }

    public void removeOrientationListener(InCallOrientationListener inCallOrientationListener) {
        if (inCallOrientationListener != null) {
            this.orientationListeners.remove(inCallOrientationListener);
        }
    }

    public void setActivity(InCallActivity inCallActivity2) {
        if (inCallActivity2 != null) {
            InCallActivity inCallActivity3 = this.inCallActivity;
            if (!(inCallActivity3 == null || inCallActivity3 == inCallActivity2)) {
                LogUtil.m10w("InCallPresenter.setActivity", "Setting a second activity before destroying the first.", new Object[0]);
            }
            updateActivity(inCallActivity2);
            return;
        }
        throw new IllegalArgumentException("registerActivity cannot be called with null");
    }

    public void setBoundAndWaitingForOutgoingCall(boolean z, PhoneAccountHandle phoneAccountHandle) {
        LogUtil.m9i("InCallPresenter.setBoundAndWaitingForOutgoingCall", GeneratedOutlineSupport.outline10("setBoundAndWaitingForOutgoingCall: ", z), new Object[0]);
        this.boundAndWaitingForOutgoingCall = z;
        this.themeColorManager.setPendingPhoneAccountHandle(phoneAccountHandle);
        if (z && this.inCallState == InCallState.NO_CALLS) {
            this.inCallState = InCallState.PENDING_OUTGOING;
        }
    }

    public void setFullScreen(boolean z) {
        setFullScreen(z, false);
    }

    public void setInCallAllowsOrientationChange(boolean z) {
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 == null) {
            LogUtil.m8e("InCallPresenter.setInCallAllowsOrientationChange", "InCallActivity is null. Can't set requested orientation.", new Object[0]);
        } else {
            inCallActivity2.setAllowOrientationChange(z);
        }
    }

    public void setManageConferenceActivity(ManageConferenceActivity manageConferenceActivity2) {
        this.manageConferenceActivity = manageConferenceActivity2;
    }

    public void setThemeColorManager(ThemeColorManager themeColorManager2) {
        this.themeColorManager = themeColorManager2;
    }

    public void setUp(Context context2, CallList callList2, ExternalCallList externalCallList2, StatusBarNotifier statusBarNotifier2, ExternalCallNotifier externalCallNotifier2, ContactInfoCache contactInfoCache2, ProximitySensor proximitySensor2, FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler, SpeakEasyCallManager speakEasyCallManager2) {
        Trace.beginSection("InCallPresenter.setUp");
        if (this.serviceConnected) {
            LogUtil.m9i("InCallPresenter.setUp", "New service connection replacing existing one.", new Object[0]);
            if (context2 == this.context && callList2 == this.callList) {
                Trace.endSection();
                return;
            }
            throw new IllegalStateException();
        }
        Objects.requireNonNull(context2);
        this.context = context2;
        this.contactInfoCache = contactInfoCache2;
        this.statusBarNotifier = statusBarNotifier2;
        this.externalCallNotifier = externalCallNotifier2;
        addListener(this.statusBarNotifier);
        ((EnrichedCallManagerStub) EnrichedCallComponent.get(this.context).getEnrichedCallManager()).registerStateChangedListener(this.statusBarNotifier);
        this.vibrationHandler = new InCallVibrationHandler(context2);
        addListener(this.vibrationHandler);
        this.dndHandler = new InCallDndHandler(context2);
        addListener(this.dndHandler);
        this.proximitySensor = proximitySensor2;
        addListener(this.proximitySensor);
        if (this.themeColorManager == null) {
            this.themeColorManager = new ThemeColorManager(new InCallUIMaterialColorMapUtils(this.context));
        }
        this.callList = callList2;
        this.externalCallList = externalCallList2;
        externalCallList2.addExternalCallListener(this.externalCallNotifier);
        externalCallList2.addExternalCallListener(this.externalCallListener);
        this.serviceConnected = true;
        this.callList.addListener(this);
        this.spamCallListListener = new SpamCallListListener(context2, DialerExecutorComponent.get(context2).dialerExecutorFactory());
        this.callList.addListener(this.spamCallListListener);
        this.activeCallsListener = new ActiveCallsCallListListener(context2);
        this.callList.addListener(this.activeCallsListener);
        VideoPauseController.getInstance().setUp(this);
        this.filteredQueryHandler = filteredNumberAsyncQueryHandler;
        this.speakEasyCallManager = speakEasyCallManager2;
        ((TelephonyManager) this.context.getSystemService(TelephonyManager.class)).listen(this.phoneStateListener, 32);
        AudioModeProvider.getInstance().addListener(this);
        MotorolaInCallUiNotifier motorolaInCallUiNotifier = new MotorolaInCallUiNotifier(context2);
        addInCallUiListener(motorolaInCallUiNotifier);
        addListener(motorolaInCallUiNotifier);
        Trace.endSection();
    }

    public boolean shouldStartInBubbleMode() {
        if (!ReturnToCallController.isEnabled(this.context)) {
            return false;
        }
        DialerCall pendingOutgoingCall = this.callList.getPendingOutgoingCall();
        if (pendingOutgoingCall == null) {
            pendingOutgoingCall = this.callList.getOutgoingCall();
        }
        if (pendingOutgoingCall == null) {
            pendingOutgoingCall = this.callList.getDisconnectedCall();
        }
        if (pendingOutgoingCall == null || pendingOutgoingCall.isEmergencyCall()) {
            return false;
        }
        boolean shouldStartInBubbleModeWithExtras = shouldStartInBubbleModeWithExtras(pendingOutgoingCall.getIntentExtras());
        if (shouldStartInBubbleModeWithExtras) {
            ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.START_CALL_IN_BUBBLE_MODE, pendingOutgoingCall.getUniqueCallId(), pendingOutgoingCall.getTimeAddedMs());
        }
        return shouldStartInBubbleModeWithExtras;
    }

    public void showConferenceCallManager(boolean z) {
        ManageConferenceActivity manageConferenceActivity2;
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            inCallActivity2.showConferenceFragment(z);
        }
        if (!z && (manageConferenceActivity2 = this.manageConferenceActivity) != null) {
            manageConferenceActivity2.finish();
        }
    }

    public void showInCall(boolean z, boolean z2) {
        LogUtil.m9i("InCallPresenter.showInCall", "Showing InCallActivity", new Object[0]);
        Context context2 = this.context;
        context2.startActivity(InCallActivity.getIntent(context2, z, z2, false));
    }

    public void tearDown() {
        this.callList.clearOnDisconnect();
        this.serviceConnected = false;
        ((TelephonyManager) this.context.getSystemService(TelephonyManager.class)).listen(this.phoneStateListener, 0);
        attemptCleanup();
        VideoPauseController.getInstance().tearDown();
        AudioModeProvider.getInstance().removeListener(this);
    }

    public void unsetActivity(InCallActivity inCallActivity2) {
        if (inCallActivity2 != null) {
            InCallActivity inCallActivity3 = this.inCallActivity;
            if (inCallActivity3 == null) {
                LogUtil.m9i("InCallPresenter.unsetActivity", "No InCallActivity currently set, no need to unset.", new Object[0]);
            } else if (inCallActivity3 != inCallActivity2) {
                LogUtil.m10w("InCallPresenter.unsetActivity", "Second instance of InCallActivity is trying to unregister when another instance is active. Ignoring.", new Object[0]);
            } else {
                updateActivity((InCallActivity) null);
            }
        } else {
            throw new IllegalArgumentException("unregisterActivity cannot be called with null");
        }
    }

    /* access modifiers changed from: package-private */
    public void updateIsChangingConfigurations() {
        this.isChangingConfigurations = false;
        InCallActivity inCallActivity2 = this.inCallActivity;
        if (inCallActivity2 != null) {
            this.isChangingConfigurations = inCallActivity2.isChangingConfigurations();
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("updateIsChangingConfigurations = ");
        outline13.append(this.isChangingConfigurations);
        outline13.toString();
    }

    public void setFullScreen(boolean z, boolean z2) {
        LogUtil.m9i("InCallPresenter.setFullScreen", GeneratedOutlineSupport.outline10("setFullScreen = ", z), new Object[0]);
        if (isDialpadVisible()) {
            "setFullScreen overridden as dialpad is shown = " + false;
            z = false;
        }
        if (this.isFullScreen != z || z2) {
            this.isFullScreen = z;
            notifyFullscreenModeChange(this.isFullScreen);
        }
    }
}
