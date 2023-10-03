package com.android.incallui.call;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.os.Trace;
import android.support.p002v7.appcompat.R$style;
import android.telecom.Call;
import android.telecom.CallAudioState;
import android.telecom.DisconnectCause;
import android.telecom.GatewayInfo;
import android.telecom.InCallService;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.StatusHints;
import android.telecom.TelecomManager;
import android.telecom.VideoProfile;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.assisteddialing.TransformationInfo;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.compat.telephony.TelephonyManagerCompat;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.logging.ContactLookupResult$Type;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.VideoTech$Type;
import com.android.dialer.preferredsim.PreferredAccountRecorder;
import com.android.dialer.rtt.RttTranscript;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.spam.status.SpamStatus;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.time.Clock;
import com.android.dialer.util.PermissionsUtil;
import com.android.incallui.AnswerScreenPresenter;
import com.android.incallui.audiomode.AudioModeProvider;
import com.android.incallui.latencyreport.LatencyReport;
import com.android.incallui.rtt.protocol.RttChatMessage;
import com.android.incallui.videotech.VideoTech;
import com.android.incallui.videotech.duo.DuoVideoTech;
import com.android.incallui.videotech.empty.EmptyVideoTech;
import com.android.incallui.videotech.ims.ImsVideoTech;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class DialerCall implements VideoTech.VideoTechListener, EnrichedCallManager.StateChangedListener, EnrichedCallManager.CapabilitiesListener {
    public static final String CONFIG_EMERGENCY_CALLBACK_WINDOW_MILLIS = "emergency_callback_window_millis";
    private static int hiddenCounter;
    private static int idCounter;
    private int answerAndReleaseButtonDisplayedTimes = 0;
    private List<PhoneAccountHandle> callCapableAccounts;
    private int callHistoryStatus = 0;
    private String callProviderLabel;
    private String callSubject;
    private String callbackNumber;
    private int cameraDirection = -1;
    /* access modifiers changed from: private */
    public final List<CannedTextResponsesLoadedListener> cannedTextResponsesLoadedListeners = new CopyOnWriteArrayList();
    private PersistableBundle carrierConfig;
    private final List<String> childCallIds = new ArrayList();
    private String childNumber;
    private Clock clock = $$Lambda$RAnRraZd4HLErqzt4JoHEBlfQ.INSTANCE;
    /* access modifiers changed from: private */
    public final Context context;
    private String countryIso;
    private final DialerCallDelegate dialerCallDelegate;
    private boolean didDismissVideoChargesAlertDialog;
    private boolean didShowCameraPermission;
    private DisconnectCause disconnectCause;
    private boolean doNotShowDialogForHandoffToWifiFailure;
    private Uri handle;
    private boolean hasShownLteToWiFiHandoverToast;
    private boolean hasShownWiFiToLteHandoverToast;
    private final int hiddenId;

    /* renamed from: id */
    private final String f44id;
    /* access modifiers changed from: private */
    public boolean isCallForwarded;
    private boolean isCallRemoved;
    private boolean isCallSubjectSupported;
    private boolean isEmergencyCall;
    /* access modifiers changed from: private */
    public boolean isMergeInProcess;
    private boolean isOutgoing;
    /* access modifiers changed from: private */
    public boolean isRemotelyHeld;
    private boolean isSpeakEasyCall;
    private boolean isVoicemailNumber;
    private String lastForwardedNumber;
    private final LatencyReport latencyReport;
    /* access modifiers changed from: private */
    public final List<DialerCallListener> listeners = new CopyOnWriteArrayList();
    private final LogState logState = new LogState();
    private int peerDimensionHeight = -1;
    private int peerDimensionWidth = -1;
    private PhoneAccountHandle phoneAccountHandle;
    private PreferredAccountRecorder preferredAccountRecorder;
    private RttTranscript rttTranscript;
    private int secondCallWithoutAnswerAndReleasedButtonTimes = 0;
    private VideoTech$Type selectedAvailableVideoTechType = VideoTech$Type.NONE;
    private SpamStatus spamStatus;
    private int state = 0;
    /* access modifiers changed from: private */
    public final Call telecomCall;
    private final Call.Callback telecomCallCallback = new Call.Callback() {
        public void onCallDestroyed(Call call) {
            "call=" + call;
            DialerCall.this.unregisterCallback();
        }

        public void onCannedTextResponsesLoaded(Call call, List<String> list) {
            "call=" + call + " cannedTextResponses=" + list;
            for (AnswerScreenPresenter onCannedTextResponsesLoaded : DialerCall.this.cannedTextResponsesLoadedListeners) {
                onCannedTextResponsesLoaded.onCannedTextResponsesLoaded(DialerCall.this);
            }
        }

        public void onChildrenChanged(Call call, List<Call> list) {
            DialerCall.this.update();
        }

        public void onConferenceableCallsChanged(Call call, List<Call> list) {
            Object[] objArr = {call, Integer.valueOf(list.size())};
            DialerCall.this.update();
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onConnectionEvent(android.telecom.Call r3, java.lang.String r4, android.os.Bundle r5) {
            /*
                r2 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Call: "
                r0.append(r1)
                r0.append(r3)
                java.lang.String r3 = ", Event: "
                r0.append(r3)
                r0.append(r4)
                java.lang.String r3 = ", Extras: "
                r0.append(r3)
                r0.append(r5)
                r0.toString()
                int r3 = r4.hashCode()
                r5 = 1
                r0 = 0
                switch(r3) {
                    case -1863773007: goto L_0x0087;
                    case -1652183308: goto L_0x007d;
                    case -731255741: goto L_0x0073;
                    case -240628118: goto L_0x0068;
                    case 634860625: goto L_0x005d;
                    case 758141852: goto L_0x0053;
                    case 1161109851: goto L_0x0049;
                    case 1317277546: goto L_0x003f;
                    case 1673445297: goto L_0x0035;
                    case 2024477568: goto L_0x002b;
                    default: goto L_0x0029;
                }
            L_0x0029:
                goto L_0x0091
            L_0x002b:
                java.lang.String r3 = "android.telephony.event.EVENT_HANDOVER_VIDEO_FROM_WIFI_TO_LTE"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = r5
                goto L_0x0092
            L_0x0035:
                java.lang.String r3 = "android.telephony.event.EVENT_HANDOVER_TO_WIFI_FAILED"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = 3
                goto L_0x0092
            L_0x003f:
                java.lang.String r3 = "android.telecom.event.CALL_REMOTELY_HELD"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = 4
                goto L_0x0092
            L_0x0049:
                java.lang.String r3 = "android.telecom.event.CALL_MERGE_FAILED"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = r0
                goto L_0x0092
            L_0x0053:
                java.lang.String r3 = "android.telephony.event.EVENT_NOTIFY_INTERNATIONAL_CALL_ON_WFC"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = 6
                goto L_0x0092
            L_0x005d:
                java.lang.String r3 = "android.telephony.event.EVENT_CALL_FORWARDED"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = 9
                goto L_0x0092
            L_0x0068:
                java.lang.String r3 = "android.telecom.event.MERGE_COMPLETE"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = 8
                goto L_0x0092
            L_0x0073:
                java.lang.String r3 = "android.telecom.event.CALL_REMOTELY_UNHELD"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = 5
                goto L_0x0092
            L_0x007d:
                java.lang.String r3 = "android.telephony.event.EVENT_HANDOVER_VIDEO_FROM_LTE_TO_WIFI"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = 2
                goto L_0x0092
            L_0x0087:
                java.lang.String r3 = "android.telecom.event.MERGE_START"
                boolean r3 = r4.equals(r3)
                if (r3 == 0) goto L_0x0091
                r3 = 7
                goto L_0x0092
            L_0x0091:
                r3 = -1
            L_0x0092:
                java.lang.String r4 = "DialerCall.onConnectionEvent"
                switch(r3) {
                    case 0: goto L_0x00ed;
                    case 1: goto L_0x00e7;
                    case 2: goto L_0x00e1;
                    case 3: goto L_0x00db;
                    case 4: goto L_0x00d0;
                    case 5: goto L_0x00c5;
                    case 6: goto L_0x00bf;
                    case 7: goto L_0x00b2;
                    case 8: goto L_0x00a5;
                    case 9: goto L_0x0098;
                    default: goto L_0x0097;
                }
            L_0x0097:
                goto L_0x00f7
            L_0x0098:
                int r3 = android.os.Build.VERSION.SDK_INT
                com.android.incallui.call.DialerCall r3 = com.android.incallui.call.DialerCall.this
                boolean unused = r3.isCallForwarded = r5
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                r2.update()
                goto L_0x00f7
            L_0x00a5:
                java.lang.Object[] r3 = new java.lang.Object[r0]
                java.lang.String r5 = "merge complete"
                com.android.dialer.common.LogUtil.m9i(r4, r5, r3)
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                boolean unused = r2.isMergeInProcess = r0
                goto L_0x00f7
            L_0x00b2:
                java.lang.Object[] r3 = new java.lang.Object[r0]
                java.lang.String r0 = "merge start"
                com.android.dialer.common.LogUtil.m9i(r4, r0, r3)
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                boolean unused = r2.isMergeInProcess = r5
                goto L_0x00f7
            L_0x00bf:
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                r2.notifyInternationalCallOnWifi()
                goto L_0x00f7
            L_0x00c5:
                com.android.incallui.call.DialerCall r3 = com.android.incallui.call.DialerCall.this
                boolean unused = r3.isRemotelyHeld = r0
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                r2.update()
                goto L_0x00f7
            L_0x00d0:
                com.android.incallui.call.DialerCall r3 = com.android.incallui.call.DialerCall.this
                boolean unused = r3.isRemotelyHeld = r5
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                r2.update()
                goto L_0x00f7
            L_0x00db:
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                r2.notifyHandoverToWifiFailed()
                goto L_0x00f7
            L_0x00e1:
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                r2.onLteToWifiHandover()
                goto L_0x00f7
            L_0x00e7:
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                r2.notifyWiFiToLteHandover()
                goto L_0x00f7
            L_0x00ed:
                com.android.incallui.call.DialerCall r3 = com.android.incallui.call.DialerCall.this
                boolean unused = r3.isMergeInProcess = r0
                com.android.incallui.call.DialerCall r2 = com.android.incallui.call.DialerCall.this
                r2.update()
            L_0x00f7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.call.DialerCall.C07061.onConnectionEvent(android.telecom.Call, java.lang.String, android.os.Bundle):void");
        }

        public void onDetailsChanged(Call call, Call.Details details) {
            " call=" + call + " details=" + details;
            DialerCall.this.update();
        }

        public void onParentChanged(Call call, Call call2) {
            "call=" + call + " newParent=" + call2;
            DialerCall.this.update();
        }

        public void onPostDialWait(Call call, String str) {
            "call=" + call + " remainingPostDialSequence=" + str;
            DialerCall.this.update();
        }

        public void onRttInitiationFailure(Call call, int i) {
            new Object[1][0] = Integer.valueOf(i);
            Toast.makeText(DialerCall.this.context, R.string.rtt_call_not_available_toast, 1).show();
            DialerCall.this.update();
        }

        public void onRttModeChanged(Call call, int i) {
            new Object[1][0] = Integer.valueOf(i);
        }

        public void onRttRequest(Call call, int i) {
            new Object[1][0] = Integer.valueOf(i);
            for (DialerCallListener onDialerCallUpgradeToRtt : DialerCall.this.listeners) {
                onDialerCallUpgradeToRtt.onDialerCallUpgradeToRtt(i);
            }
        }

        public void onRttStatusChanged(Call call, boolean z, Call.RttCall rttCall) {
            new Object[1][0] = Boolean.valueOf(z);
            if (z) {
                ((LoggingBindingsStub) Logger.get(DialerCall.this.context)).logCallImpression(DialerImpression$Type.RTT_MID_CALL_ENABLED, DialerCall.this.getUniqueCallId(), DialerCall.this.getTimeAddedMs());
            }
            DialerCall.this.update();
        }

        public void onStateChanged(Call call, int i) {
            "call=" + call + " newState=" + i;
            DialerCall.this.update();
        }

        public void onVideoCallChanged(Call call, InCallService.VideoCall videoCall) {
            "call=" + call + " videoCall=" + videoCall;
            DialerCall.this.update();
        }
    };
    private long timeAddedMs;
    private final String uniqueCallId = UUID.randomUUID().toString();
    private VideoTech videoTech;
    private final VideoTechManager videoTechManager;

    public interface CannedTextResponsesLoadedListener {
    }

    public static class LogState {
        public CallSpecificAppData callSpecificAppData;
        public int conferencedCalls = 0;
        public ContactLookupResult$Type contactLookupResult = ContactLookupResult$Type.UNKNOWN_LOOKUP_RESULT_TYPE;
        long dialerConnectTimeMillis = 0;
        long dialerConnectTimeMillisElapsedRealtime = 0;
        public DisconnectCause disconnectCause;
        public boolean isIncoming = false;
        public boolean isLogged = false;
        public long telecomDurationMillis = 0;

        public String toString() {
            String str;
            Locale locale = Locale.US;
            Object[] objArr = new Object[5];
            objArr[0] = this.disconnectCause;
            objArr[1] = Boolean.valueOf(this.isIncoming);
            int ordinal = this.contactLookupResult.ordinal();
            objArr[2] = ordinal != 2 ? ordinal != 3 ? ordinal != 4 ? ordinal != 5 ? ordinal != 6 ? "Not found" : "Voicemail" : "Emergency" : "Remote" : "Cache" : "Local";
            CallSpecificAppData callSpecificAppData2 = this.callSpecificAppData;
            if (callSpecificAppData2 != null) {
                switch (callSpecificAppData2.getCallInitiationType().ordinal()) {
                    case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                        str = "Incoming";
                        break;
                    case 2:
                        str = "Dialpad";
                        break;
                    case 3:
                        str = "Speed Dial";
                        break;
                    case 5:
                        str = "Remote Directory";
                        break;
                    case 6:
                        str = "Smart Dial";
                        break;
                    case 7:
                        str = "Regular Search";
                        break;
                    case 8:
                        str = "DialerCall Log";
                        break;
                    case 9:
                        str = "DialerCall Log Filter";
                        break;
                    case 10:
                        str = "Voicemail Log";
                        break;
                    case 11:
                        str = "DialerCall Details";
                        break;
                    case 12:
                        str = "Quick Contacts";
                        break;
                    case 13:
                        str = "External";
                        break;
                    case 14:
                        str = "Launcher Shortcut";
                        break;
                    default:
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown: ");
                        outline13.append(callSpecificAppData2.getCallInitiationType());
                        str = outline13.toString();
                        break;
                }
            } else {
                str = "null";
            }
            objArr[3] = str;
            objArr[4] = Long.valueOf(this.telecomDurationMillis);
            return String.format(locale, "[%s, isIncoming: %s, contactLookup: %s, callInitiation: %s, duration: %s]", objArr);
        }
    }

    public static class VideoTechManager {
        private final Context context;
        private final EmptyVideoTech emptyVideoTech = new EmptyVideoTech();
        private final VideoTech rcsVideoShare;
        private VideoTech savedTech;
        private final List<VideoTech> videoTechs;

        public VideoTechManager(DialerCall dialerCall) {
            this.context = dialerCall.context;
            String number = dialerCall.getNumber();
            String replaceAll = (number == null ? "" : number).replaceAll("[^+0-9]", "");
            this.videoTechs = new ArrayList();
            this.videoTechs.add(new ImsVideoTech(Logger.get(dialerCall.context), dialerCall, dialerCall.telecomCall));
            this.rcsVideoShare = EnrichedCallComponent.get(dialerCall.context).getRcsVideoShareFactory().newRcsVideoShare(EnrichedCallComponent.get(dialerCall.context).getEnrichedCallManager(), dialerCall, replaceAll);
            this.videoTechs.add(this.rcsVideoShare);
            this.videoTechs.add(new DuoVideoTech(DuoComponent.get(dialerCall.context).getDuo(), dialerCall, dialerCall.telecomCall, replaceAll));
            this.savedTech = this.emptyVideoTech;
        }

        public void dispatchCallStateChanged(int i, PhoneAccountHandle phoneAccountHandle) {
            for (VideoTech onCallStateChanged : this.videoTechs) {
                onCallStateChanged.onCallStateChanged(this.context, i, phoneAccountHandle);
            }
        }

        /* access modifiers changed from: package-private */
        public void dispatchRemovedFromCallList() {
            for (VideoTech onRemovedFromCallList : this.videoTechs) {
                onRemovedFromCallList.onRemovedFromCallList();
            }
        }

        public VideoTech getVideoTech(PhoneAccountHandle phoneAccountHandle) {
            VideoTech videoTech = this.savedTech;
            if (videoTech == this.emptyVideoTech) {
                Iterator<VideoTech> it = this.videoTechs.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    VideoTech next = it.next();
                    if (next.isAvailable(this.context, phoneAccountHandle)) {
                        this.savedTech = next;
                        this.savedTech.becomePrimary();
                        break;
                    }
                }
            } else if ((videoTech instanceof DuoVideoTech) && this.rcsVideoShare.isAvailable(this.context, phoneAccountHandle)) {
                VideoTech videoTech2 = this.rcsVideoShare;
                this.savedTech = videoTech2;
                videoTech2.becomePrimary();
            }
            return this.savedTech;
        }
    }

    public DialerCall(Context context2, DialerCallDelegate dialerCallDelegate2, Call call, LatencyReport latencyReport2, boolean z) {
        EnrichedCallManager.Filter filter;
        Assert.isNotNull(context2);
        this.context = context2;
        this.dialerCallDelegate = dialerCallDelegate2;
        this.telecomCall = call;
        this.latencyReport = latencyReport2;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("DialerCall_");
        int i = idCounter;
        idCounter = i + 1;
        outline13.append(Integer.toString(i));
        this.f44id = outline13.toString();
        this.videoTechManager = new VideoTechManager(this);
        updateFromTelecomCall();
        if (!isHiddenNumber() || !TextUtils.isEmpty(getNumber())) {
            this.hiddenId = 0;
        } else {
            int i2 = hiddenCounter + 1;
            hiddenCounter = i2;
            this.hiddenId = i2;
        }
        if (z) {
            this.telecomCall.registerCallback(this.telecomCallCallback);
        }
        this.timeAddedMs = System.currentTimeMillis();
        if (!isExternalCall()) {
            this.logState.callSpecificAppData = R$style.getCallSpecificAppData(getIntentExtras());
            LogState logState2 = this.logState;
            if (logState2.callSpecificAppData == null) {
                CallSpecificAppData.Builder newBuilder = CallSpecificAppData.newBuilder();
                newBuilder.setCallInitiationType(CallInitiationType$Type.EXTERNAL_INITIATION);
                logState2.callSpecificAppData = (CallSpecificAppData) newBuilder.build();
            }
            if (getState() == 4) {
                LogState logState3 = this.logState;
                CallSpecificAppData.Builder builder = (CallSpecificAppData.Builder) logState3.callSpecificAppData.toBuilder();
                builder.setCallInitiationType(CallInitiationType$Type.INCOMING_INITIATION);
                logState3.callSpecificAppData = (CallSpecificAppData) builder.build();
            }
        }
        if (getNumber() != null) {
            getEnrichedCallSession();
            EnrichedCallManager enrichedCallManager = EnrichedCallComponent.get(this.context).getEnrichedCallManager();
            if (isIncoming()) {
                filter = ((EnrichedCallManagerStub) enrichedCallManager).createIncomingCallComposerFilter();
            } else {
                filter = ((EnrichedCallManagerStub) enrichedCallManager).createOutgoingCallComposerFilter();
            }
            ((EnrichedCallManagerStub) enrichedCallManager).getSession(getUniqueCallId(), getNumber(), filter);
        }
    }

    public static boolean areSame(DialerCall dialerCall, DialerCall dialerCall2) {
        if (dialerCall == null && dialerCall2 == null) {
            return true;
        }
        if (dialerCall == null || dialerCall2 == null) {
            return false;
        }
        return dialerCall.getId().equals(dialerCall2.getId());
    }

    public static void clearRestrictedCount() {
        hiddenCounter = 0;
    }

    private PhoneAccount getPhoneAccount() {
        PhoneAccountHandle accountHandle = getAccountHandle();
        if (accountHandle == null) {
            return null;
        }
        return ((TelecomManager) this.context.getSystemService(TelecomManager.class)).getPhoneAccount(accountHandle);
    }

    private boolean isHiddenNumber() {
        return getNumberPresentation() == 2 || getNumberPresentation() == 3;
    }

    /* access modifiers changed from: private */
    public void onLteToWifiHandover() {
        LogUtil.enterBlock("DialerCall.onLteToWifiHandover");
        if (!this.hasShownLteToWiFiHandoverToast) {
            Toast.makeText(this.context, R.string.video_call_lte_to_wifi_handover_toast, 1).show();
            this.hasShownLteToWiFiHandoverToast = true;
        }
    }

    /* access modifiers changed from: private */
    public void update() {
        Trace.beginSection("DialerCall.update");
        int state2 = getState();
        this.videoTech = null;
        updateFromTelecomCall();
        if (state2 == getState() || getState() != 10) {
            for (DialerCallListener onDialerCallUpdate : this.listeners) {
                onDialerCallUpdate.onDialerCallUpdate();
            }
        } else {
            for (DialerCallListener onDialerCallDisconnect : this.listeners) {
                onDialerCallDisconnect.onDialerCallDisconnect();
            }
            ((EnrichedCallManagerStub) EnrichedCallComponent.get(this.context).getEnrichedCallManager()).unregisterCapabilitiesListener(this);
            ((EnrichedCallManagerStub) EnrichedCallComponent.get(this.context).getEnrichedCallManager()).unregisterStateChangedListener(this);
        }
        Trace.endSection();
    }

    private void updateFromTelecomCall() {
        int i;
        PhoneAccount phoneAccount;
        Trace.beginSection("DialerCall.updateFromTelecomCall");
        this.telecomCall.toString();
        this.videoTechManager.dispatchCallStateChanged(this.telecomCall.getState(), getAccountHandle());
        switch (this.telecomCall.getState()) {
            case 0:
            case 9:
                i = 13;
                break;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                i = 6;
                break;
            case 2:
                i = 4;
                break;
            case 3:
                i = 8;
                break;
            case 4:
                i = 3;
                break;
            case 7:
                i = 10;
                break;
            case 8:
                i = 12;
                break;
            case 10:
                i = 9;
                break;
            case 11:
                i = 15;
                break;
            default:
                i = 0;
                break;
        }
        if (this.state != 14) {
            setState(i);
            setDisconnectCause(this.telecomCall.getDetails().getDisconnectCause());
        }
        this.childCallIds.clear();
        int size = this.telecomCall.getChildren().size();
        for (int i2 = 0; i2 < size; i2++) {
            this.childCallIds.add(this.dialerCallDelegate.getDialerCallFromTelecomCall(this.telecomCall.getChildren().get(i2)).getId());
        }
        LogState logState2 = this.logState;
        logState2.conferencedCalls = Math.max(size, logState2.conferencedCalls);
        updateFromCallExtras(this.telecomCall.getDetails().getExtras());
        Uri handle2 = this.telecomCall.getDetails().getHandle();
        if (!Objects.equals(this.handle, handle2)) {
            this.handle = handle2;
            this.isEmergencyCall = R$style.isEmergencyCall(this.telecomCall);
        }
        TelecomManager telecomManager = (TelecomManager) this.context.getSystemService(TelecomManager.class);
        PhoneAccountHandle accountHandle = this.telecomCall.getDetails().getAccountHandle();
        if (!Objects.equals(this.phoneAccountHandle, accountHandle)) {
            this.phoneAccountHandle = accountHandle;
            PhoneAccountHandle phoneAccountHandle2 = this.phoneAccountHandle;
            if (!(phoneAccountHandle2 == null || (phoneAccount = telecomManager.getPhoneAccount(phoneAccountHandle2)) == null)) {
                this.isCallSubjectSupported = phoneAccount.hasCapabilities(64);
                if (phoneAccount.hasCapabilities(4)) {
                    PhoneAccountHandle phoneAccountHandle3 = this.phoneAccountHandle;
                    if (PermissionsUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE")) {
                        int i3 = Build.VERSION.SDK_INT;
                        this.carrierConfig = TelephonyManagerCompat.getTelephonyManagerForPhoneAccountHandle(this.context, phoneAccountHandle3).getCarrierConfig();
                    }
                }
            }
        }
        if (PermissionsUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE")) {
            if (getHandle() != null && "voicemail".equals(getHandle().getScheme())) {
                this.isVoicemailNumber = true;
            } else if (!PermissionsUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE")) {
                this.isVoicemailNumber = false;
            } else {
                this.isVoicemailNumber = TelecomUtil.isVoicemailNumber(this.context, getAccountHandle(), getNumber());
            }
            this.callCapableAccounts = telecomManager.getCallCapablePhoneAccounts();
            this.countryIso = R$style.getCurrentCountryIso(this.context);
        }
        Trace.endSection();
    }

    public void addCannedTextResponsesLoadedListener(CannedTextResponsesLoadedListener cannedTextResponsesLoadedListener) {
        Assert.isMainThread();
        this.cannedTextResponsesLoadedListeners.add(cannedTextResponsesLoadedListener);
    }

    public void addListener(DialerCallListener dialerCallListener) {
        Assert.isMainThread();
        this.listeners.add(dialerCallListener);
    }

    public void answer(int i) {
        LogUtil.m9i("DialerCall.answer", GeneratedOutlineSupport.outline5("videoState: ", i), new Object[0]);
        this.telecomCall.answer(i);
    }

    public boolean answeringDisconnectsForegroundVideoCall() {
        Bundle extras = getExtras();
        if (extras == null || !extras.containsKey("android.telecom.extra.ANSWERING_DROPS_FG_CALL")) {
            return false;
        }
        return extras.getBoolean("android.telecom.extra.ANSWERING_DROPS_FG_CALL");
    }

    /* access modifiers changed from: protected */
    public boolean areCallExtrasCorrupted(Bundle bundle) {
        try {
            bundle.containsKey("android.telecom.extra.CHILD_ADDRESS");
            return false;
        } catch (IllegalArgumentException e) {
            LogUtil.m7e("DialerCall.areCallExtrasCorrupted", "callExtras is corrupted, ignoring exception", (Throwable) e);
            return true;
        }
    }

    @TargetApi(28)
    public boolean can(int i) {
        boolean z;
        int callCapabilities = this.telecomCall.getDetails().getCallCapabilities();
        if ((i & 4) != 0) {
            Iterator<Call> it = this.telecomCall.getConferenceableCalls().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                int i2 = Build.VERSION.SDK_INT;
                if (!it.next().isRttActive()) {
                    z = true;
                    break;
                }
            }
            if (!z && (callCapabilities & 4) == 0) {
                return false;
            }
            i &= -5;
        }
        if (i == (i & callCapabilities)) {
            return true;
        }
        return false;
    }

    @TargetApi(28)
    public boolean canUpgradeToRttCall() {
        if (isPhoneAccountRttCapable() && !isActiveRttCall() && !isVideoCall() && !isConferenceCall() && !CallList.getInstance().hasActiveRttCall()) {
            return true;
        }
        return false;
    }

    public boolean didDismissVideoChargesAlertDialog() {
        return this.didDismissVideoChargesAlertDialog;
    }

    public boolean didShowCameraPermission() {
        return this.didShowCameraPermission;
    }

    public void disconnect() {
        LogUtil.m9i("DialerCall.disconnect", "", new Object[0]);
        setState(9);
        for (DialerCallListener onDialerCallUpdate : this.listeners) {
            onDialerCallUpdate.onDialerCallUpdate();
        }
        this.telecomCall.disconnect();
    }

    public PhoneAccountHandle getAccountHandle() {
        Call call = this.telecomCall;
        if (call == null) {
            return null;
        }
        return call.getDetails().getAccountHandle();
    }

    public TransformationInfo getAssistedDialingExtras() {
        if (getIntentExtras() == null || getIntentExtras().getBundle("android.telecom.extra.ASSISTED_DIALING_EXTRAS") == null) {
            return null;
        }
        Bundle bundle = getIntentExtras().getBundle("android.telecom.extra.ASSISTED_DIALING_EXTRAS");
        TransformationInfo.Builder builder = TransformationInfo.builder();
        builder.setOriginalNumber(bundle.getString("TRANSFORMATION_INFO_ORIGINAL_NUMBER"));
        builder.setTransformedNumber(bundle.getString("TRANSFORMATION_INFO_TRANSFORMED_NUMBER"));
        builder.setUserHomeCountryCode(bundle.getString("TRANSFORMATION_INFO_USER_HOME_COUNTRY_CODE"));
        builder.setUserRoamingCountryCode(bundle.getString("TRANSFORMATION_INFO_USER_ROAMING_COUNTRY_CODE"));
        builder.setTransformedNumberCountryCallingCode(bundle.getInt("TRANSFORMED_NUMBER_COUNTRY_CALLING_CODE"));
        return builder.build();
    }

    public List<PhoneAccountHandle> getCallCapableAccounts() {
        return this.callCapableAccounts;
    }

    public String getCallProviderLabel() {
        List<PhoneAccountHandle> list;
        if (this.callProviderLabel == null) {
            PhoneAccount phoneAccount = getPhoneAccount();
            if (phoneAccount != null && !TextUtils.isEmpty(phoneAccount.getLabel()) && (list = this.callCapableAccounts) != null && list.size() > 1) {
                this.callProviderLabel = phoneAccount.getLabel().toString();
            }
            if (this.callProviderLabel == null) {
                this.callProviderLabel = "";
            }
        }
        return this.callProviderLabel;
    }

    public String getCallSubject() {
        return this.callSubject;
    }

    public String getCallbackNumber() {
        if (this.callbackNumber == null) {
            boolean hasProperty = hasProperty(4);
            if (isEmergencyCall() || hasProperty) {
                this.callbackNumber = ((TelecomManager) this.context.getSystemService(TelecomManager.class)).getLine1Number(getAccountHandle());
            }
            if (this.callbackNumber == null) {
                this.callbackNumber = "";
            }
        }
        return this.callbackNumber;
    }

    public int getCameraDir() {
        return this.cameraDirection;
    }

    public List<String> getCannedSmsResponses() {
        return this.telecomCall.getCannedTextResponses();
    }

    public List<String> getChildCallIds() {
        return this.childCallIds;
    }

    public String getChildNumber() {
        return this.childNumber;
    }

    public String getCnapName() {
        if (this.telecomCall == null) {
            return null;
        }
        return getTelecomCall().getDetails().getCallerDisplayName();
    }

    public int getCnapNamePresentation() {
        Call call = this.telecomCall;
        if (call == null) {
            return -1;
        }
        return call.getDetails().getCallerDisplayNamePresentation();
    }

    public long getConnectTimeMillis() {
        return this.telecomCall.getDetails().getConnectTimeMillis();
    }

    public String getCountryIso() {
        return this.countryIso;
    }

    @TargetApi(26)
    public long getCreationTimeMillis() {
        return this.telecomCall.getDetails().getCreationTimeMillis();
    }

    public DisconnectCause getDisconnectCause() {
        int i = this.state;
        if (i == 10 || i == 2) {
            return this.disconnectCause;
        }
        return new DisconnectCause(0);
    }

    public void getEnrichedCallSession() {
    }

    public Bundle getExtras() {
        Call call = this.telecomCall;
        if (call == null) {
            return null;
        }
        return call.getDetails().getExtras();
    }

    public GatewayInfo getGatewayInfo() {
        Call call = this.telecomCall;
        if (call == null) {
            return null;
        }
        return call.getDetails().getGatewayInfo();
    }

    public Uri getHandle() {
        Call call = this.telecomCall;
        if (call == null) {
            return null;
        }
        return call.getDetails().getHandle();
    }

    public String getId() {
        return this.f44id;
    }

    public Bundle getIntentExtras() {
        return this.telecomCall.getDetails().getIntentExtras();
    }

    public String getLastForwardedNumber() {
        return this.lastForwardedNumber;
    }

    public LatencyReport getLatencyReport() {
        return this.latencyReport;
    }

    public LogState getLogState() {
        return this.logState;
    }

    public int getNonConferenceState() {
        return this.state;
    }

    public String getNumber() {
        return R$style.getNumber(this.telecomCall);
    }

    public int getNumberPresentation() {
        Call call = this.telecomCall;
        if (call == null) {
            return -1;
        }
        return call.getDetails().getHandlePresentation();
    }

    public String getParentId() {
        Call parent = this.telecomCall.getParent();
        if (parent != null) {
            return this.dialerCallDelegate.getDialerCallFromTelecomCall(parent).getId();
        }
        return null;
    }

    public int getPeerDimensionHeight() {
        return this.peerDimensionHeight;
    }

    public int getPeerDimensionWidth() {
        return this.peerDimensionWidth;
    }

    public PreferredAccountRecorder getPreferredAccountRecorder() {
        return this.preferredAccountRecorder;
    }

    @TargetApi(28)
    public Call.RttCall getRttCall() {
        if (!isActiveRttCall()) {
            return null;
        }
        return getTelecomCall().getRttCall();
    }

    public RttTranscript getRttTranscript() {
        return this.rttTranscript;
    }

    public String getSimCountryIso() {
        String simCountryIso = TelephonyManagerCompat.getTelephonyManagerForPhoneAccountHandle(this.context, getAccountHandle()).getSimCountryIso();
        return !TextUtils.isEmpty(simCountryIso) ? simCountryIso.toUpperCase(Locale.US) : simCountryIso;
    }

    public int getState() {
        Call call = this.telecomCall;
        if (call == null || call.getParent() == null) {
            return this.state;
        }
        return 11;
    }

    public StatusHints getStatusHints() {
        return this.telecomCall.getDetails().getStatusHints();
    }

    /* access modifiers changed from: package-private */
    public Call getTelecomCall() {
        return this.telecomCall;
    }

    public long getTimeAddedMs() {
        return this.timeAddedMs;
    }

    public String getUniqueCallId() {
        return this.uniqueCallId;
    }

    public InCallService.VideoCall getVideoCall() {
        Call call = this.telecomCall;
        if (call == null) {
            return null;
        }
        return call.getVideoCall();
    }

    public int getVideoState() {
        return this.telecomCall.getDetails().getVideoState();
    }

    public VideoTech getVideoTech() {
        if (this.videoTech == null) {
            this.videoTech = this.videoTechManager.getVideoTech(getAccountHandle());
            if (this.selectedAvailableVideoTechType == VideoTech$Type.NONE) {
                this.selectedAvailableVideoTechType = this.videoTech.getVideoTechType();
            }
        }
        return this.videoTech;
    }

    public boolean hasProperty(int i) {
        return this.telecomCall.getDetails().hasProperty(i);
    }

    public boolean hasReceivedVideoUpgradeRequest() {
        return R$style.hasReceivedVideoUpgradeRequest(getVideoTech().getSessionModificationState());
    }

    public boolean hasSentRttUpgradeRequest() {
        return false;
    }

    public boolean hasSentVideoUpgradeRequest() {
        return R$style.hasSentVideoUpgradeRequest(getVideoTech().getSessionModificationState());
    }

    public boolean hasShownWiFiToLteHandoverToast() {
        return this.hasShownWiFiToLteHandoverToast;
    }

    public void hold() {
        LogUtil.m9i("DialerCall.hold", "", new Object[0]);
        this.telecomCall.hold();
    }

    public void increaseAnswerAndReleaseButtonDisplayedTimes() {
        this.answerAndReleaseButtonDisplayedTimes++;
    }

    public void increaseSecondCallWithoutAnswerAndReleasedButtonTimes() {
        this.secondCallWithoutAnswerAndReleasedButtonTimes++;
    }

    @TargetApi(28)
    public boolean isActiveRttCall() {
        int i = Build.VERSION.SDK_INT;
        return getTelecomCall().isRttActive();
    }

    public boolean isAssistedDialed() {
        if (getIntentExtras() == null || !getIntentExtras().getBoolean("android.telecom.extra.USE_ASSISTED_DIALING", false) || getAssistedDialingExtras() == null || Build.VERSION.SDK_INT > 28) {
            return false;
        }
        return true;
    }

    public boolean isCallForwarded() {
        return this.isCallForwarded;
    }

    public boolean isCallSubjectSupported() {
        return this.isCallSubjectSupported;
    }

    public boolean isConferenceCall() {
        return hasProperty(1);
    }

    public boolean isEmergencyCall() {
        return this.isEmergencyCall;
    }

    /* access modifiers changed from: package-private */
    public boolean isExternalCall() {
        return hasProperty(64);
    }

    /* access modifiers changed from: package-private */
    public boolean isInEmergencyCallbackWindow(long j) {
        return System.currentTimeMillis() - j < ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getLong(CONFIG_EMERGENCY_CALLBACK_WINDOW_MILLIS, TimeUnit.MINUTES.toMillis(5));
    }

    public boolean isIncoming() {
        return this.logState.isIncoming;
    }

    public boolean isMergeInProcess() {
        return this.isMergeInProcess;
    }

    public boolean isOutgoing() {
        return this.isOutgoing;
    }

    @TargetApi(28)
    public boolean isPhoneAccountRttCapable() {
        PhoneAccount phoneAccount = getPhoneAccount();
        if (phoneAccount != null && phoneAccount.hasCapabilities(4096)) {
            return true;
        }
        return false;
    }

    public boolean isPotentialEmergencyCallback() {
        if (hasProperty(4)) {
            return true;
        }
        int i = Build.VERSION.SDK_INT;
        if (getExtras() == null || getExtras().getLong("android.telecom.extra.LAST_EMERGENCY_CALLBACK_TIME_MILLIS", 0) <= 0 || !isInEmergencyCallbackWindow(getExtras().getLong("android.telecom.extra.LAST_EMERGENCY_CALLBACK_TIME_MILLIS", 0))) {
            return false;
        }
        return true;
    }

    public boolean isRemotelyHeld() {
        return this.isRemotelyHeld;
    }

    public boolean isSpam() {
        SpamStatus spamStatus2 = this.spamStatus;
        if (spamStatus2 == null || !spamStatus2.isSpam() || !isIncoming() || isPotentialEmergencyCallback()) {
            return false;
        }
        return true;
    }

    public boolean isSpeakEasyCall() {
        if (!isSpeakEasyEligible()) {
            return false;
        }
        return this.isSpeakEasyCall;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isSpeakEasyEligible() {
        /*
            r4 = this;
            android.telecom.PhoneAccount r0 = r4.getPhoneAccount()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            r2 = 4
            boolean r0 = r0.hasCapabilities(r2)
            if (r0 != 0) goto L_0x0010
            return r1
        L_0x0010:
            boolean r0 = r4.isPotentialEmergencyCallback()
            r2 = 1
            if (r0 != 0) goto L_0x0064
            boolean r0 = r4.isEmergencyCall()
            if (r0 != 0) goto L_0x0064
            boolean r0 = r4.isActiveRttCall()
            if (r0 != 0) goto L_0x0064
            boolean r0 = r4.isConferenceCall()
            if (r0 != 0) goto L_0x0064
            boolean r0 = r4.isVideoCall()
            if (r0 != 0) goto L_0x0064
            boolean r0 = r4.isVoiceMailNumber()
            if (r0 != 0) goto L_0x0064
            boolean r0 = r4.hasReceivedVideoUpgradeRequest()
            if (r0 != 0) goto L_0x0064
            android.os.Bundle r4 = r4.getIntentExtras()
            if (r4 != 0) goto L_0x0043
        L_0x0041:
            r4 = r1
            goto L_0x0061
        L_0x0043:
            java.lang.String r0 = "callid"
            java.lang.String r4 = r4.getString(r0)
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            java.lang.String r0 = "DialerCall.isVoipCallNotSupportedBySpeakeasy"
            if (r4 == 0) goto L_0x0059
            java.lang.Object[] r4 = new java.lang.Object[r1]
            java.lang.String r3 = "callid was empty"
            com.android.dialer.common.LogUtil.m9i(r0, r3, r4)
            goto L_0x0041
        L_0x0059:
            java.lang.Object[] r4 = new java.lang.Object[r1]
            java.lang.String r3 = "call is not eligible"
            com.android.dialer.common.LogUtil.m9i(r0, r3, r4)
            r4 = r2
        L_0x0061:
            if (r4 != 0) goto L_0x0064
            r1 = r2
        L_0x0064:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.call.DialerCall.isSpeakEasyEligible():boolean");
    }

    public boolean isVideoCall() {
        return getVideoTech().isTransmittingOrReceiving() || VideoProfile.isVideo(getVideoState());
    }

    public boolean isVoiceMailNumber() {
        return this.isVoicemailNumber;
    }

    public void notifyHandoverToWifiFailed() {
        LogUtil.m9i("DialerCall.notifyHandoverToWifiFailed", "", new Object[0]);
        for (DialerCallListener onHandoverToWifiFailure : this.listeners) {
            onHandoverToWifiFailure.onHandoverToWifiFailure();
        }
    }

    public void notifyInternationalCallOnWifi() {
        LogUtil.enterBlock("DialerCall.notifyInternationalCallOnWifi");
        for (DialerCallListener onInternationalCallOnWifi : this.listeners) {
            onInternationalCallOnWifi.onInternationalCallOnWifi();
        }
    }

    public void notifyWiFiToLteHandover() {
        LogUtil.m9i("DialerCall.notifyWiFiToLteHandover", "", new Object[0]);
        for (DialerCallListener onWiFiToLteHandover : this.listeners) {
            onWiFiToLteHandover.onWiFiToLteHandover();
        }
    }

    public void onCameraDimensionsChanged(int i, int i2) {
        InCallVideoCallCallbackNotifier.getInstance().cameraDimensionsChanged(this, i, i2);
    }

    public void onImpressionLoggingNeeded(DialerImpression$Type dialerImpression$Type) {
        ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(dialerImpression$Type, getUniqueCallId(), getTimeAddedMs());
        if (dialerImpression$Type == DialerImpression$Type.LIGHTBRINGER_UPGRADE_REQUESTED && getLogState().contactLookupResult == ContactLookupResult$Type.NOT_FOUND) {
            ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.LIGHTBRINGER_NON_CONTACT_UPGRADE_REQUESTED, getUniqueCallId(), getTimeAddedMs());
        }
    }

    public void onPeerDimensionsChanged(int i, int i2) {
        this.peerDimensionWidth = i;
        this.peerDimensionHeight = i2;
        InCallVideoCallCallbackNotifier.getInstance().peerDimensionsChanged(this, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void onRemovedFromCallList() {
        LogUtil.enterBlock("DialerCall.onRemovedFromCallList");
        VideoTechManager videoTechManager2 = this.videoTechManager;
        if (videoTechManager2 != null) {
            videoTechManager2.dispatchRemovedFromCallList();
        }
        if (this.rttTranscript != null && !this.isCallRemoved) {
            int i = Build.VERSION.SDK_INT;
            if (getRttCall() != null) {
                try {
                    String readImmediately = getRttCall().readImmediately();
                    if (!TextUtils.isEmpty(readImmediately)) {
                        RttTranscript rttTranscript2 = this.rttTranscript;
                        List<RttChatMessage> fromTranscript = RttChatMessage.fromTranscript(rttTranscript2);
                        RttChatMessage.updateRemoteRttChatMessage(fromTranscript, readImmediately);
                        RttTranscript.Builder newBuilder = RttTranscript.newBuilder();
                        newBuilder.setId(rttTranscript2.getId());
                        newBuilder.setNumber(rttTranscript2.getNumber());
                        newBuilder.setTimestamp(rttTranscript2.getTimestamp());
                        newBuilder.addAllMessages(RttChatMessage.toTranscriptMessageList(fromTranscript));
                        this.rttTranscript = (RttTranscript) newBuilder.build();
                    }
                } catch (IOException e) {
                    LogUtil.m7e("DialerCall.saveRttTranscript", "error when reading remaining message", (Throwable) e);
                }
            }
            if (this.rttTranscript.getMessagesCount() != 0) {
                Context context2 = this.context;
                Futures.addCallback(DialerExecutorComponent.get(context2).backgroundExecutor().submit(new Callable(context2, this.rttTranscript) {
                    private final /* synthetic */ Context f$0;
                    private final /* synthetic */ RttTranscript f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final Object call() {
                        RttTranscriptUtil.lambda$saveRttTranscript$2(this.f$0, this.f$1);
                        return null;
                    }
                }), new DefaultFutureCallback(), MoreExecutors.directExecutor());
            }
        }
        this.isCallRemoved = true;
    }

    public void onSessionModificationStateChanged() {
        Trace.beginSection("DialerCall.onSessionModificationStateChanged");
        for (DialerCallListener onDialerCallSessionModificationStateChange : this.listeners) {
            onDialerCallSessionModificationStateChange.onDialerCallSessionModificationStateChange();
        }
        Trace.endSection();
    }

    public void onUpgradedToVideo(boolean z) {
        LogUtil.enterBlock("DialerCall.onUpgradedToVideo");
        if (z) {
            CallAudioState audioState = AudioModeProvider.getInstance().getAudioState();
            if ((audioState.getSupportedRouteMask() & 2) != 0) {
                LogUtil.m8e("DialerCall.onUpgradedToVideo", "toggling speakerphone not allowed when bluetooth supported.", new Object[0]);
            } else if (audioState.getRoute() != 8) {
                TelecomAdapter.getInstance().setAudioRoute(8);
            }
        }
    }

    public void onVideoUpgradeRequestReceived() {
        LogUtil.enterBlock("DialerCall.onVideoUpgradeRequestReceived");
        for (DialerCallListener onDialerCallUpgradeToVideo : this.listeners) {
            onDialerCallUpgradeToVideo.onDialerCallUpgradeToVideo();
        }
        update();
        ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.VIDEO_CALL_REQUEST_RECEIVED, getUniqueCallId(), getTimeAddedMs());
    }

    public void phoneAccountSelected(PhoneAccountHandle phoneAccountHandle2, boolean z) {
        LogUtil.m9i("DialerCall.phoneAccountSelected", "accountHandle: %s, setDefault: %b", phoneAccountHandle2, Boolean.valueOf(z));
        this.telecomCall.phoneAccountSelected(phoneAccountHandle2, z);
    }

    public void reject(boolean z, String str) {
        LogUtil.m9i("DialerCall.reject", "", new Object[0]);
        this.telecomCall.reject(z, str);
    }

    public void removeCannedTextResponsesLoadedListener(CannedTextResponsesLoadedListener cannedTextResponsesLoadedListener) {
        Assert.isMainThread();
        this.cannedTextResponsesLoadedListeners.remove(cannedTextResponsesLoadedListener);
    }

    public void removeListener(DialerCallListener dialerCallListener) {
        Assert.isMainThread();
        this.listeners.remove(dialerCallListener);
    }

    @TargetApi(28)
    public void respondToRttRequest(boolean z, int i) {
        DialerImpression$Type dialerImpression$Type;
        LoggingBindings loggingBindings = Logger.get(this.context);
        if (z) {
            dialerImpression$Type = DialerImpression$Type.RTT_MID_CALL_ACCEPTED;
        } else {
            dialerImpression$Type = DialerImpression$Type.RTT_MID_CALL_REJECTED;
        }
        ((LoggingBindingsStub) loggingBindings).logCallImpression(dialerImpression$Type, getUniqueCallId(), getTimeAddedMs());
        getTelecomCall().respondToRttRequest(i, z);
    }

    @TargetApi(28)
    public void sendRttUpgradeRequest() {
        getTelecomCall().sendRttRequest();
    }

    public void setBlockedStatus(boolean z) {
    }

    public void setCallHistoryStatus(int i) {
        this.callHistoryStatus = i;
    }

    public void setCameraDir(int i) {
        if (i == 0 || i == 1) {
            this.cameraDirection = i;
        } else {
            this.cameraDirection = -1;
        }
    }

    /* access modifiers changed from: package-private */
    public void setClock(Clock clock2) {
        this.clock = clock2;
    }

    public void setDidDismissVideoChargesAlertDialog(boolean z) {
        this.didDismissVideoChargesAlertDialog = z;
    }

    public void setDidShowCameraPermission(boolean z) {
        this.didShowCameraPermission = z;
    }

    public void setDisconnectCause(DisconnectCause disconnectCause2) {
        this.disconnectCause = disconnectCause2;
        this.logState.disconnectCause = this.disconnectCause;
    }

    public void setDoNotShowDialogForHandoffToWifiFailure(boolean z) {
        this.doNotShowDialogForHandoffToWifiFailure = z;
    }

    public void setHasShownWiFiToLteHandoverToast() {
        this.hasShownWiFiToLteHandoverToast = true;
    }

    public void setIsSpeakEasyCall(boolean z) {
        this.isSpeakEasyCall = z;
        List<DialerCallListener> list = this.listeners;
        if (list != null) {
            for (DialerCallListener onDialerCallSpeakEasyStateChange : list) {
                onDialerCallSpeakEasyStateChange.onDialerCallSpeakEasyStateChange();
            }
        }
    }

    public void setPreferredAccountRecorder(PreferredAccountRecorder preferredAccountRecorder2) {
        this.preferredAccountRecorder = preferredAccountRecorder2;
    }

    public void setReleasedByAnsweringSecondCall(boolean z) {
    }

    public void setRttTranscript(RttTranscript rttTranscript2) {
        this.rttTranscript = rttTranscript2;
    }

    public void setState(int i) {
        if (i == 4) {
            this.logState.isIncoming = true;
        }
        if (i == 3) {
            if (this.state == 3) {
                LogUtil.m9i("DialerCall.updateCallTiming", "state is already active", new Object[0]);
                this.state = i;
            }
            this.logState.dialerConnectTimeMillis = this.clock.currentTimeMillis();
            this.logState.dialerConnectTimeMillisElapsedRealtime = SystemClock.elapsedRealtime();
        }
        if (i == 10) {
            long currentTimeMillis = getConnectTimeMillis() == 0 ? 0 : this.clock.currentTimeMillis() - getConnectTimeMillis();
            if (this.state == 10) {
                LogUtil.m9i("DialerCall.setState", "ignoring state transition from DISCONNECTED to DISCONNECTED. Duration would have changed from %s to %s", Long.valueOf(this.logState.telecomDurationMillis), Long.valueOf(currentTimeMillis));
            } else {
                LogState logState2 = this.logState;
                logState2.telecomDurationMillis = currentTimeMillis;
                if (logState2.dialerConnectTimeMillis != 0) {
                    this.clock.currentTimeMillis();
                    long j = this.logState.dialerConnectTimeMillis;
                }
                if (this.logState.dialerConnectTimeMillisElapsedRealtime != 0) {
                    SystemClock.elapsedRealtime();
                    long j2 = this.logState.dialerConnectTimeMillisElapsedRealtime;
                }
            }
        } else {
            int i2 = this.state;
            if (i2 == 6 || i2 == 13) {
                this.isOutgoing = true;
            }
        }
        this.state = i;
    }

    public boolean showVideoChargesAlertDialog() {
        PersistableBundle persistableBundle = this.carrierConfig;
        if (persistableBundle == null) {
            return false;
        }
        return persistableBundle.getBoolean("show_video_call_charges_alert_dialog_bool");
    }

    public boolean showWifiHandoverAlertAsToast() {
        return this.doNotShowDialogForHandoffToWifiFailure;
    }

    public void splitFromConference() {
        LogUtil.m9i("DialerCall.splitFromConference", "", new Object[0]);
        this.telecomCall.splitFromConference();
    }

    public String toSimpleString() {
        return super.toString();
    }

    public String toString() {
        String str;
        if (this.telecomCall == null) {
            return String.valueOf(this.f44id);
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[10];
        objArr[0] = this.f44id;
        switch (getState()) {
            case 0:
                str = "INVALID";
                break;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                str = "NEW";
                break;
            case 2:
                str = "IDLE";
                break;
            case 3:
                str = "ACTIVE";
                break;
            case 4:
                str = "INCOMING";
                break;
            case 5:
                str = "CALL_WAITING";
                break;
            case 6:
                str = "DIALING";
                break;
            case 7:
                str = "REDIALING";
                break;
            case 8:
                str = "ONHOLD";
                break;
            case 9:
                str = "DISCONNECTING";
                break;
            case 10:
                str = "DISCONNECTED";
                break;
            case 11:
                str = "CONFERENCED";
                break;
            case 12:
                str = "SELECT_PHONE_ACCOUNT";
                break;
            case 13:
                str = "CONNECTING";
                break;
            case 14:
                str = "BLOCKED";
                break;
            case 15:
                str = "PULLING";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        objArr[1] = str;
        objArr[2] = Call.Details.capabilitiesToString(this.telecomCall.getDetails().getCallCapabilities());
        objArr[3] = Call.Details.propertiesToString(this.telecomCall.getDetails().getCallProperties());
        objArr[4] = this.childCallIds;
        objArr[5] = getParentId();
        objArr[6] = this.telecomCall.getConferenceableCalls();
        objArr[7] = VideoProfile.videoStateToString(this.telecomCall.getDetails().getVideoState());
        objArr[8] = Integer.valueOf(getVideoTech().getSessionModificationState());
        objArr[9] = Integer.valueOf(getCameraDir());
        return String.format(locale, "[%s, %s, %s, %s, children:%s, parent:%s, conferenceable:%s, videoState:%s, mSessionModificationState:%d, CameraDir:%s]", objArr);
    }

    public void unhold() {
        LogUtil.m9i("DialerCall.unhold", "", new Object[0]);
        this.telecomCall.unhold();
    }

    public void unregisterCallback() {
        this.telecomCall.unregisterCallback(this.telecomCallCallback);
    }

    /* access modifiers changed from: protected */
    public void updateFromCallExtras(Bundle bundle) {
        ArrayList<String> stringArrayList;
        if (bundle != null && !areCallExtrasCorrupted(bundle)) {
            if (bundle.containsKey("android.telecom.extra.CHILD_ADDRESS")) {
                String string = bundle.getString("android.telecom.extra.CHILD_ADDRESS");
                if (!Objects.equals(string, this.childNumber)) {
                    this.childNumber = string;
                    for (DialerCallListener onDialerCallChildNumberChange : this.listeners) {
                        onDialerCallChildNumberChange.onDialerCallChildNumberChange();
                    }
                }
            }
            if (bundle.containsKey("android.telecom.extra.LAST_FORWARDED_NUMBER") && (stringArrayList = bundle.getStringArrayList("android.telecom.extra.LAST_FORWARDED_NUMBER")) != null) {
                String str = null;
                if (!stringArrayList.isEmpty()) {
                    str = stringArrayList.get(stringArrayList.size() - 1);
                }
                if (!Objects.equals(str, this.lastForwardedNumber)) {
                    this.lastForwardedNumber = str;
                    for (DialerCallListener onDialerCallLastForwardedNumberChange : this.listeners) {
                        onDialerCallLastForwardedNumberChange.onDialerCallLastForwardedNumberChange();
                    }
                }
            }
            if (bundle.containsKey("android.telecom.extra.CALL_SUBJECT")) {
                String string2 = bundle.getString("android.telecom.extra.CALL_SUBJECT");
                if (!Objects.equals(this.callSubject, string2)) {
                    this.callSubject = string2;
                }
            }
        }
    }

    public String updateNameIfRestricted(String str) {
        int i;
        if (str == null || !isHiddenNumber() || (i = this.hiddenId) == 0 || hiddenCounter <= 1) {
            return str;
        }
        return this.context.getString(R.string.unknown_counter, new Object[]{str, Integer.valueOf(i)});
    }

    public void answer() {
        answer(this.telecomCall.getDetails().getVideoState());
    }
}
