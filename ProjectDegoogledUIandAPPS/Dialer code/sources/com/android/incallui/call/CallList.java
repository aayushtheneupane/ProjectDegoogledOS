package com.android.incallui.call;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Trace;
import android.telecom.Call;
import android.telecom.DisconnectCause;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.metrics.MetricsComponent;
import com.android.dialer.metrics.StubMetrics;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.shortcuts.ShortcutUsageReporter;
import com.android.dialer.spam.SpamComponent;
import com.android.dialer.spam.stub.SpamSettingsStub;
import com.android.dialer.storage.StorageComponent;
import com.android.incallui.latencyreport.LatencyReport;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CallList implements DialerCallDelegate {
    private static CallList instance = new CallList();
    private final Map<String, DialerCall> callById = new ArrayMap();
    private final Map<Call, DialerCall> callByTelecomCall = new ArrayMap();
    private final Handler handler = new Handler() {
        public void handleMessage(Message message) {
            if (message.what != 1) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Message not expected: ");
                outline13.append(message.what);
                LogUtil.m8e("CallList.handleMessage", outline13.toString(), new Object[0]);
                return;
            }
            Object obj = message.obj;
            new Object[1][0] = obj;
            CallList.this.finishDisconnectedCall((DialerCall) obj);
        }
    };
    /* access modifiers changed from: private */
    public final Set<Listener> listeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
    private final Set<DialerCall> pendingDisconnectCalls = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));

    private class DialerCallListenerImpl implements DialerCallListener {
        private final DialerCall call;

        DialerCallListenerImpl(DialerCall dialerCall) {
            Assert.isNotNull(dialerCall);
            this.call = dialerCall;
        }

        public void onDialerCallChildNumberChange() {
        }

        public void onDialerCallDisconnect() {
            if (CallList.this.updateCallInMap(this.call)) {
                LogUtil.m9i("DialerCallListenerImpl.onDialerCallDisconnect", String.valueOf(this.call), new Object[0]);
                CallList.access$300(CallList.this, this.call);
            }
        }

        public void onDialerCallLastForwardedNumberChange() {
        }

        public void onDialerCallSessionModificationStateChange() {
            for (Listener onSessionModificationStateChange : CallList.this.listeners) {
                onSessionModificationStateChange.onSessionModificationStateChange(this.call);
            }
        }

        public void onDialerCallSpeakEasyStateChange() {
            for (Listener onSpeakEasyStateChange : CallList.this.listeners) {
                onSpeakEasyStateChange.onSpeakEasyStateChange();
            }
        }

        public void onDialerCallUpdate() {
            Trace.beginSection("CallList.onDialerCallUpdate");
            CallList.this.onUpdateCall(this.call);
            CallList.this.notifyGenericListeners();
            Trace.endSection();
        }

        public void onDialerCallUpgradeToRtt(int i) {
            for (Listener onUpgradeToRtt : CallList.this.listeners) {
                onUpgradeToRtt.onUpgradeToRtt(this.call, i);
            }
        }

        public void onDialerCallUpgradeToVideo() {
            for (Listener onUpgradeToVideo : CallList.this.listeners) {
                onUpgradeToVideo.onUpgradeToVideo(this.call);
            }
        }

        public void onHandoverToWifiFailure() {
            for (Listener onHandoverToWifiFailed : CallList.this.listeners) {
                onHandoverToWifiFailed.onHandoverToWifiFailed(this.call);
            }
        }

        public void onInternationalCallOnWifi() {
            LogUtil.enterBlock("DialerCallListenerImpl.onInternationalCallOnWifi");
            for (Listener onInternationalCallOnWifi : CallList.this.listeners) {
                onInternationalCallOnWifi.onInternationalCallOnWifi(this.call);
            }
        }

        public void onWiFiToLteHandover() {
            for (Listener onWiFiToLteHandover : CallList.this.listeners) {
                onWiFiToLteHandover.onWiFiToLteHandover(this.call);
            }
        }
    }

    public interface Listener {
        void onCallListChange(CallList callList);

        void onDisconnect(DialerCall dialerCall);

        void onHandoverToWifiFailed(DialerCall dialerCall);

        void onIncomingCall(DialerCall dialerCall);

        void onInternationalCallOnWifi(DialerCall dialerCall);

        void onSessionModificationStateChange(DialerCall dialerCall);

        void onSpeakEasyStateChange() {
        }

        void onUpgradeToRtt(DialerCall dialerCall, int i) {
        }

        void onUpgradeToVideo(DialerCall dialerCall);

        void onWiFiToLteHandover(DialerCall dialerCall);
    }

    static /* synthetic */ void access$300(CallList callList, DialerCall dialerCall) {
        for (Listener onDisconnect : callList.listeners) {
            onDisconnect.onDisconnect(dialerCall);
        }
    }

    /* access modifiers changed from: private */
    public void finishDisconnectedCall(DialerCall dialerCall) {
        if (this.pendingDisconnectCalls.contains(dialerCall)) {
            this.pendingDisconnectCalls.remove(dialerCall);
        }
        dialerCall.setState(2);
        updateCallInMap(dialerCall);
        notifyGenericListeners();
    }

    public static CallList getInstance() {
        return instance;
    }

    /* access modifiers changed from: private */
    public void notifyGenericListeners() {
        Trace.beginSection("CallList.notifyGenericListeners");
        for (Listener onCallListChange : this.listeners) {
            onCallListChange.onCallListChange(this);
        }
        Trace.endSection();
    }

    public static void setCallListInstance(CallList callList) {
        instance = callList;
    }

    /* access modifiers changed from: private */
    public boolean updateCallInMap(DialerCall dialerCall) {
        Trace.beginSection("CallList.updateCallInMap");
        Objects.requireNonNull(dialerCall);
        int i = 0;
        boolean z = true;
        if (dialerCall.getState() != 10) {
            int state = dialerCall.getState();
            if (!(2 == state || state == 0)) {
                this.callById.put(dialerCall.getId(), dialerCall);
                this.callByTelecomCall.put(dialerCall.getTelecomCall(), dialerCall);
            } else if (this.callById.containsKey(dialerCall.getId())) {
                this.callById.remove(dialerCall.getId());
                this.callByTelecomCall.remove(dialerCall.getTelecomCall());
            }
            Trace.endSection();
            return z;
        } else if (this.callById.containsKey(dialerCall.getId())) {
            Message obtainMessage = this.handler.obtainMessage(1, dialerCall);
            Handler handler2 = this.handler;
            if (dialerCall.getState() == 10) {
                switch (dialerCall.getDisconnectCause().getCode()) {
                    case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                    case 3:
                        i = 2000;
                        break;
                    case 2:
                        i = 200;
                        break;
                    case 4:
                    case 5:
                    case 6:
                        break;
                    default:
                        i = 5000;
                        break;
                }
                handler2.sendMessageDelayed(obtainMessage, (long) i);
                this.pendingDisconnectCalls.add(dialerCall);
                this.callById.put(dialerCall.getId(), dialerCall);
                this.callByTelecomCall.put(dialerCall.getTelecomCall(), dialerCall);
                Trace.endSection();
                return z;
            }
            throw new IllegalStateException();
        }
        z = false;
        Trace.endSection();
        return z;
    }

    public void addListener(Listener listener) {
        Objects.requireNonNull(listener);
        this.listeners.add(listener);
        listener.onCallListChange(this);
    }

    public void clearOnDisconnect() {
        for (DialerCall next : this.callById.values()) {
            int state = next.getState();
            if (!(state == 2 || state == 0 || state == 10)) {
                next.setState(10);
                next.setDisconnectCause(new DisconnectCause(0));
                updateCallInMap(next);
            }
        }
        notifyGenericListeners();
    }

    public DialerCall getActiveCall() {
        return getFirstCallWithState(3);
    }

    public DialerCall getActiveOrBackgroundCall() {
        DialerCall activeCall = getActiveCall();
        return activeCall == null ? getBackgroundCall() : activeCall;
    }

    public Collection<DialerCall> getAllCalls() {
        return this.callById.values();
    }

    public DialerCall getBackgroundCall() {
        return getFirstCallWithState(8);
    }

    public DialerCall getCallById(String str) {
        return this.callById.get(str);
    }

    public DialerCall getCallWithState(int i, int i2) {
        int i3 = 0;
        for (DialerCall next : this.callById.values()) {
            if (next.getState() == i) {
                if (i3 >= i2) {
                    return next;
                }
                i3++;
            }
        }
        return null;
    }

    public DialerCall getCallWithStateAndNumber(int i, String str) {
        for (DialerCall next : this.callById.values()) {
            if (TextUtils.equals(next.getNumber(), str) && next.getState() == i) {
                return next;
            }
        }
        return null;
    }

    public DialerCall getDialerCallFromTelecomCall(Call call) {
        return this.callByTelecomCall.get(call);
    }

    public DialerCall getDisconnectedCall() {
        return getFirstCallWithState(10);
    }

    public DialerCall getDisconnectingCall() {
        return getFirstCallWithState(9);
    }

    public DialerCall getFirstCall() {
        DialerCall incomingCall = getIncomingCall();
        if (incomingCall == null) {
            incomingCall = getPendingOutgoingCall();
        }
        if (incomingCall == null) {
            incomingCall = getOutgoingCall();
        }
        if (incomingCall == null) {
            incomingCall = getFirstCallWithState(3);
        }
        if (incomingCall == null) {
            incomingCall = getDisconnectingCall();
        }
        return incomingCall == null ? getDisconnectedCall() : incomingCall;
    }

    public DialerCall getFirstCallWithState(int i) {
        return getCallWithState(i, 0);
    }

    public DialerCall getIncomingCall() {
        DialerCall firstCallWithState = getFirstCallWithState(4);
        return firstCallWithState == null ? getFirstCallWithState(5) : firstCallWithState;
    }

    /* access modifiers changed from: package-private */
    public InCallUiLegacyBindingsStub getLegacyBindings(Context context) {
        Objects.requireNonNull(context);
        Context applicationContext = context.getApplicationContext();
        InCallUiLegacyBindingsStub newInCallUiLegacyBindings = applicationContext instanceof InCallUiLegacyBindingsFactory ? ((InCallUiLegacyBindingsFactory) applicationContext).newInCallUiLegacyBindings() : null;
        return newInCallUiLegacyBindings == null ? new InCallUiLegacyBindingsStub() : newInCallUiLegacyBindings;
    }

    public DialerCall getOutgoingCall() {
        DialerCall firstCallWithState = getFirstCallWithState(6);
        if (firstCallWithState == null) {
            firstCallWithState = getFirstCallWithState(7);
        }
        return firstCallWithState == null ? getFirstCallWithState(15) : firstCallWithState;
    }

    public DialerCall getOutgoingOrActive() {
        DialerCall outgoingCall = getOutgoingCall();
        return outgoingCall == null ? getActiveCall() : outgoingCall;
    }

    public DialerCall getPendingOutgoingCall() {
        return getFirstCallWithState(13);
    }

    public DialerCall getSecondActiveCall() {
        return getCallWithState(3, 1);
    }

    public DialerCall getSecondBackgroundCall() {
        return getCallWithState(8, 1);
    }

    public DialerCall getVideoUpgradeRequestCall() {
        for (DialerCall next : this.callById.values()) {
            if (next.getVideoTech().getSessionModificationState() == 3) {
                return next;
            }
        }
        return null;
    }

    public DialerCall getWaitingForAccountCall() {
        return getFirstCallWithState(12);
    }

    /* access modifiers changed from: package-private */
    public boolean hasActiveRttCall() {
        for (DialerCall isActiveRttCall : getAllCalls()) {
            if (isActiveRttCall.isActiveRttCall()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasLiveCall() {
        DialerCall firstCall = getFirstCall();
        return (firstCall == null || firstCall == getDisconnectingCall() || firstCall == getDisconnectedCall()) ? false : true;
    }

    public void notifyCallsOfDeviceRotation(int i) {
        for (DialerCall videoTech : this.callById.values()) {
            videoTech.getVideoTech().setDeviceOrientation(i);
        }
    }

    public void onCallAdded(Context context, Call call, LatencyReport latencyReport) {
        DialerImpression$Type dialerImpression$Type;
        Trace.beginSection("CallList.onCallAdded");
        if (call.getState() == 9) {
            ((StubMetrics) MetricsComponent.get(context).metrics()).startTimer("CallList.onCallAdded_To_InCallActivity.onCreate_Outgoing");
        } else if (call.getState() == 2) {
            ((StubMetrics) MetricsComponent.get(context).metrics()).startTimer("CallList.onCallAdded_To_InCallActivity.onCreate_Incoming");
        }
        final DialerCall dialerCall = new DialerCall(context, this, call, latencyReport, true);
        if (getFirstCall() != null) {
            if (getFirstCall().isVideoCall()) {
                if (dialerCall.isVideoCall()) {
                    dialerImpression$Type = DialerImpression$Type.VIDEO_CALL_WITH_INCOMING_VIDEO_CALL;
                } else {
                    dialerImpression$Type = DialerImpression$Type.VIDEO_CALL_WITH_INCOMING_VOICE_CALL;
                }
            } else if (dialerCall.isVideoCall()) {
                dialerImpression$Type = DialerImpression$Type.VOICE_CALL_WITH_INCOMING_VIDEO_CALL;
            } else {
                dialerImpression$Type = DialerImpression$Type.VOICE_CALL_WITH_INCOMING_VOICE_CALL;
            }
            Assert.checkArgument(dialerImpression$Type != null);
            ((LoggingBindingsStub) Logger.get(context)).logCallImpression(dialerImpression$Type, dialerCall.getUniqueCallId(), dialerCall.getTimeAddedMs());
        }
        ((EnrichedCallManagerStub) EnrichedCallComponent.get(context).getEnrichedCallManager()).registerCapabilitiesListener(dialerCall);
        Trace.beginSection("checkSpam");
        dialerCall.addListener(new DialerCallListenerImpl(dialerCall));
        "callState=" + dialerCall.getState();
        ((SpamSettingsStub) SpamComponent.get(context).spamSettings()).isSpamEnabled();
        Trace.endSection();
        Trace.beginSection("checkBlock");
        new FilteredNumberAsyncQueryHandler(context).isBlockedNumber(new FilteredNumberAsyncQueryHandler.OnCheckBlockedListener(this) {
            public void onCheckComplete(Integer num) {
                if (num != null && num.intValue() != -1) {
                    dialerCall.setBlockedStatus(true);
                }
            }
        }, dialerCall.getNumber(), dialerCall.getCountryIso());
        Trace.endSection();
        if (dialerCall.getState() == 4 || dialerCall.getState() == 5) {
            if (dialerCall.isActiveRttCall()) {
                if (!dialerCall.isPhoneAccountRttCapable()) {
                    LogUtil.enterBlock("RttPromotion.setEnabled");
                    StorageComponent.get(context).unencryptedSharedPrefs().edit().putBoolean("rtt_promotion_enabled", true).apply();
                }
                ((LoggingBindingsStub) Logger.get(context)).logCallImpression(DialerImpression$Type.INCOMING_RTT_CALL, dialerCall.getUniqueCallId(), dialerCall.getTimeAddedMs());
            }
            Trace.beginSection("CallList.onIncoming");
            if (updateCallInMap(dialerCall)) {
                LogUtil.m9i("CallList.onIncoming", String.valueOf(dialerCall), new Object[0]);
            }
            for (Listener onIncomingCall : this.listeners) {
                onIncomingCall.onIncomingCall(dialerCall);
            }
            Trace.endSection();
        } else {
            if (dialerCall.isActiveRttCall()) {
                ((LoggingBindingsStub) Logger.get(context)).logCallImpression(DialerImpression$Type.OUTGOING_RTT_CALL, dialerCall.getUniqueCallId(), dialerCall.getTimeAddedMs());
            }
            onUpdateCall(dialerCall);
            notifyGenericListeners();
        }
        if (dialerCall.getState() != 4) {
            ShortcutUsageReporter.onOutgoingCallAdded(context, dialerCall.getNumber());
        }
        Trace.endSection();
    }

    public void onCallRemoved(Context context, Call call) {
        if (this.callByTelecomCall.containsKey(call)) {
            DialerCall dialerCall = this.callByTelecomCall.get(call);
            Assert.checkArgument(!dialerCall.isExternalCall());
            ((EnrichedCallManagerStub) EnrichedCallComponent.get(context).getEnrichedCallManager()).unregisterCapabilitiesListener(dialerCall);
            if (dialerCall.getLogState() != null && !dialerCall.getLogState().isLogged) {
                getLegacyBindings(context).logCall(dialerCall);
                dialerCall.getLogState().isLogged = true;
            }
            if (updateCallInMap(dialerCall)) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Removing call not previously disconnected ");
                outline13.append(dialerCall.getId());
                LogUtil.m10w("CallList.onCallRemoved", outline13.toString(), new Object[0]);
            }
            dialerCall.onRemovedFromCallList();
        }
        if (!hasLiveCall()) {
            DialerCall.clearRestrictedCount();
        }
    }

    public void onErrorDialogDismissed() {
        Iterator<DialerCall> it = this.pendingDisconnectCalls.iterator();
        while (it.hasNext()) {
            it.remove();
            finishDisconnectedCall(it.next());
        }
    }

    public void onInCallUiShown(boolean z) {
        for (DialerCall latencyReport : this.callById.values()) {
            latencyReport.getLatencyReport().onInCallUiShown(z);
        }
    }

    public void onInternalCallMadeExternal(Context context, Call call) {
        if (this.callByTelecomCall.containsKey(call)) {
            DialerCall dialerCall = this.callByTelecomCall.get(call);
            if (dialerCall.getLogState() != null && !dialerCall.getLogState().isLogged) {
                getLegacyBindings(context).logCall(dialerCall);
                dialerCall.getLogState().isLogged = true;
            }
            dialerCall.unregisterCallback();
            this.callById.remove(dialerCall.getId());
            this.callByTelecomCall.remove(call);
        }
    }

    /* access modifiers changed from: package-private */
    public void onUpdateCall(DialerCall dialerCall) {
        Trace.beginSection("CallList.onUpdateCall");
        String.valueOf(dialerCall);
        if (this.callById.containsKey(dialerCall.getId()) || !dialerCall.isExternalCall()) {
            if (updateCallInMap(dialerCall)) {
                LogUtil.m9i("CallList.onUpdateCall", String.valueOf(dialerCall), new Object[0]);
            }
            Trace.endSection();
        }
    }

    public void removeListener(Listener listener) {
        if (listener != null) {
            this.listeners.remove(listener);
        }
    }
}
