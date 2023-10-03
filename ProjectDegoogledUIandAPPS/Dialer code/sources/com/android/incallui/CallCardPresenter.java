package com.android.incallui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Trace;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.appcompat.R$style;
import android.telecom.Call;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import com.android.dialer.R;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.contacts.ContactsComponent;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.logging.ContactLookupResult$Type;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.multimedia.MultimediaData;
import com.android.dialer.storage.StorageComponent;
import com.android.incallui.ContactInfoCache;
import com.android.incallui.InCallPresenter;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.DialerCallListener;
import com.android.incallui.calllocation.CallLocation;
import com.android.incallui.incall.protocol.InCallScreen;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import com.android.incallui.incall.protocol.PrimaryCallState;
import com.android.incallui.incall.protocol.PrimaryInfo;
import com.android.incallui.incall.protocol.SecondaryInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.ref.WeakReference;

public class CallCardPresenter implements InCallPresenter.InCallStateListener, InCallPresenter.IncomingCallListener, InCallPresenter.InCallDetailsListener, InCallPresenter.InCallEventListener, InCallScreenDelegate, DialerCallListener {
    private final CallLocation callLocation;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final Handler handler = new Handler();
    /* access modifiers changed from: private */
    public InCallScreen inCallScreen;
    private boolean isFullscreen = false;
    private boolean isInCallScreenReady;
    private DialerCall primary;
    private ContactInfoCache.ContactCacheEntry primaryContactInfo;
    private String primaryNumber;
    private DialerCall secondary;
    private ContactInfoCache.ContactCacheEntry secondaryContactInfo;
    private String secondaryNumber;
    private final Runnable sendAccessibilityEventRunnable = new Runnable() {
        /* JADX WARNING: Removed duplicated region for block: B:22:0x00ac  */
        /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r8 = this;
                com.android.incallui.CallCardPresenter r0 = com.android.incallui.CallCardPresenter.this
                android.content.Context r1 = r0.context
                com.android.incallui.CallCardPresenter r2 = com.android.incallui.CallCardPresenter.this
                com.android.incallui.incall.protocol.InCallScreen r2 = r2.inCallScreen
                java.lang.String r3 = "accessibility"
                java.lang.Object r3 = r1.getSystemService(r3)
                android.view.accessibility.AccessibilityManager r3 = (android.view.accessibility.AccessibilityManager) r3
                boolean r3 = r3.isEnabled()
                java.lang.String r4 = "CallCardPresenter.sendAccessibilityEvent"
                r5 = 0
                r6 = 1
                if (r3 != 0) goto L_0x0027
                java.lang.Object[] r1 = new java.lang.Object[r5]
                java.lang.String r2 = "accessibility is off"
                com.android.dialer.common.LogUtil.m10w(r4, r2, r1)
            L_0x0025:
                r1 = r5
                goto L_0x008b
            L_0x0027:
                if (r2 != 0) goto L_0x0031
                java.lang.Object[] r1 = new java.lang.Object[r5]
                java.lang.String r2 = "incallscreen is null"
                com.android.dialer.common.LogUtil.m10w(r4, r2, r1)
                goto L_0x0025
            L_0x0031:
                android.support.v4.app.Fragment r3 = r2.getInCallScreenFragment()
                if (r3 == 0) goto L_0x0083
                android.view.View r7 = r3.getView()
                if (r7 == 0) goto L_0x0083
                android.view.View r3 = r3.getView()
                android.view.ViewParent r3 = r3.getParent()
                if (r3 != 0) goto L_0x0048
                goto L_0x0083
            L_0x0048:
                java.lang.String r3 = "display"
                java.lang.Object r1 = r1.getSystemService(r3)
                android.hardware.display.DisplayManager r1 = (android.hardware.display.DisplayManager) r1
                android.view.Display r1 = r1.getDisplay(r5)
                int r1 = r1.getState()
                r3 = 2
                if (r1 != r3) goto L_0x005d
                r1 = r6
                goto L_0x005e
            L_0x005d:
                r1 = r5
            L_0x005e:
                java.lang.Object[] r3 = new java.lang.Object[r6]
                java.lang.Boolean r4 = java.lang.Boolean.valueOf(r1)
                r3[r5] = r4
                if (r1 != 0) goto L_0x0069
                goto L_0x0025
            L_0x0069:
                r1 = 16384(0x4000, float:2.2959E-41)
                android.view.accessibility.AccessibilityEvent r1 = android.view.accessibility.AccessibilityEvent.obtain(r1)
                r2.dispatchPopulateAccessibilityEvent(r1)
                android.support.v4.app.Fragment r2 = r2.getInCallScreenFragment()
                android.view.View r2 = r2.getView()
                android.view.ViewParent r3 = r2.getParent()
                r3.requestSendAccessibilityEvent(r2, r1)
                r1 = r6
                goto L_0x008b
            L_0x0083:
                java.lang.Object[] r1 = new java.lang.Object[r5]
                java.lang.String r2 = "fragment/view/parent is null"
                com.android.dialer.common.LogUtil.m10w(r4, r2, r1)
                goto L_0x0025
            L_0x008b:
                r1 = r1 ^ r6
                boolean unused = r0.shouldSendAccessibilityEvent = r1
                java.lang.Object[] r0 = new java.lang.Object[r6]
                com.android.incallui.CallCardPresenter r1 = com.android.incallui.CallCardPresenter.this
                boolean r1 = r1.shouldSendAccessibilityEvent
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                r0[r5] = r1
                java.lang.String r1 = "CallCardPresenter.sendAccessibilityEventRunnable"
                java.lang.String r2 = "still should send: %b"
                com.android.dialer.common.LogUtil.m9i(r1, r2, r0)
                com.android.incallui.CallCardPresenter r0 = com.android.incallui.CallCardPresenter.this
                boolean r0 = r0.shouldSendAccessibilityEvent
                if (r0 != 0) goto L_0x00b5
                com.android.incallui.CallCardPresenter r0 = com.android.incallui.CallCardPresenter.this
                android.os.Handler r0 = r0.handler
                r0.removeCallbacks(r8)
            L_0x00b5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.CallCardPresenter.C06281.run():void");
        }
    };
    /* access modifiers changed from: private */
    public boolean shouldSendAccessibilityEvent;

    public static class ContactLookupCallback implements ContactInfoCache.ContactInfoCacheCallback {
        private final WeakReference<CallCardPresenter> callCardPresenter;
        private final boolean isPrimary;

        public ContactLookupCallback(CallCardPresenter callCardPresenter2, boolean z) {
            this.callCardPresenter = new WeakReference<>(callCardPresenter2);
            this.isPrimary = z;
        }

        public void onContactInfoComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
            CallCardPresenter callCardPresenter2 = (CallCardPresenter) this.callCardPresenter.get();
            if (callCardPresenter2 != null) {
                callCardPresenter2.onContactInfoComplete(str, contactCacheEntry, this.isPrimary);
            }
        }

        public void onImageLoadComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
            CallCardPresenter callCardPresenter2 = (CallCardPresenter) this.callCardPresenter.get();
            if (callCardPresenter2 != null) {
                CallCardPresenter.access$500(callCardPresenter2, str, contactCacheEntry);
            }
        }
    }

    public CallCardPresenter(Context context2) {
        LogUtil.m9i("CallCardPresenter.constructor", (String) null, new Object[0]);
        Assert.isNotNull(context2);
        this.context = context2.getApplicationContext();
        this.callLocation = ((DaggerAospDialerRootComponent) ((HasRootComponent) this.context.getApplicationContext()).component()).callLocationComponent().getCallLocation();
    }

    static /* synthetic */ void access$500(CallCardPresenter callCardPresenter, String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
        if (callCardPresenter.inCallScreen != null && contactCacheEntry.photo != null) {
            DialerCall dialerCall = callCardPresenter.primary;
            if (dialerCall == null || !str.equals(dialerCall.getId())) {
                DialerCall dialerCall2 = callCardPresenter.secondary;
                if (dialerCall2 != null && str.equals(dialerCall2.getId())) {
                    callCardPresenter.secondaryContactInfo = contactCacheEntry;
                    callCardPresenter.updateSecondaryDisplayInfo();
                    return;
                }
                return;
            }
            callCardPresenter.primaryContactInfo = contactCacheEntry;
            callCardPresenter.updatePrimaryDisplayInfo();
        }
    }

    private String getNameForCall(ContactInfoCache.ContactCacheEntry contactCacheEntry) {
        String displayName = ContactsComponent.get(this.context).contactDisplayPreferences().getDisplayName(contactCacheEntry.namePrimary, contactCacheEntry.nameAlternative);
        if (!TextUtils.isEmpty(displayName)) {
            return displayName;
        }
        if (TextUtils.isEmpty(contactCacheEntry.number)) {
            return null;
        }
        return BidiFormatter.getInstance().unicodeWrap(contactCacheEntry.number, TextDirectionHeuristics.LTR);
    }

    private boolean hasOutgoingGatewayCall() {
        DialerCall dialerCall = this.primary;
        if (dialerCall != null && R$style.isDialing(dialerCall.getState()) && this.primary.getGatewayInfo() != null && !this.primary.getGatewayInfo().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isBatteryTooLowForEmergencyLocation() {
        Intent registerReceiver = this.context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra("status", -1);
        if (intExtra == 2 || intExtra == 5) {
            return false;
        }
        float intExtra2 = (((float) registerReceiver.getIntExtra("level", -1)) * 100.0f) / ((float) registerReceiver.getIntExtra("scale", -1));
        long j = ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getLong("min_battery_percent_for_emergency_location", 10);
        LogUtil.m9i("CallCardPresenter.isBatteryTooLowForEmergencyLocation", "percent charged: " + intExtra2 + ", min required charge: " + j, new Object[0]);
        if (intExtra2 < ((float) j)) {
            return true;
        }
        return false;
    }

    private static boolean isIncomingEmergencyCall(DialerCall dialerCall) {
        return dialerCall != null && dialerCall.isIncoming() && dialerCall.isPotentialEmergencyCallback();
    }

    private static boolean isOutgoingEmergencyCall(DialerCall dialerCall) {
        return dialerCall != null && !dialerCall.isIncoming() && dialerCall.isEmergencyCall();
    }

    private void maybeShowManageConferenceCallButton() {
        this.inCallScreen.showManageConferenceCallButton(shouldShowManageConference());
    }

    private void maybeStartSearch(DialerCall dialerCall, boolean z) {
        if (dialerCall != null && !dialerCall.isConferenceCall()) {
            ContactInfoCache.getInstance(this.context).findInfo(dialerCall, dialerCall.getState() == 4, new ContactLookupCallback(this, z));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r2 = r4.primary;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        r2 = r4.secondary;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onContactInfoComplete(java.lang.String r5, com.android.incallui.ContactInfoCache.ContactCacheEntry r6, boolean r7) {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            if (r7 == 0) goto L_0x0012
            com.android.incallui.call.DialerCall r2 = r4.primary
            if (r2 == 0) goto L_0x0012
            java.lang.String r2 = r2.getId()
            boolean r2 = android.text.TextUtils.equals(r5, r2)
            if (r2 != 0) goto L_0x0022
        L_0x0012:
            if (r7 != 0) goto L_0x0024
            com.android.incallui.call.DialerCall r2 = r4.secondary
            if (r2 == 0) goto L_0x0024
            java.lang.String r2 = r2.getId()
            boolean r2 = android.text.TextUtils.equals(r5, r2)
            if (r2 == 0) goto L_0x0024
        L_0x0022:
            r2 = r0
            goto L_0x0025
        L_0x0024:
            r2 = r1
        L_0x0025:
            if (r2 == 0) goto L_0x0035
            if (r7 == 0) goto L_0x002f
            r4.primaryContactInfo = r6
            r4.updatePrimaryDisplayInfo()
            goto L_0x0042
        L_0x002f:
            r4.secondaryContactInfo = r6
            r4.updateSecondaryDisplayInfo()
            goto L_0x0042
        L_0x0035:
            java.lang.String r7 = "dropping stale contact lookup info for "
            java.lang.String r7 = com.android.tools.p006r8.GeneratedOutlineSupport.outline8(r7, r5)
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "CallCardPresenter.onContactInfoComplete"
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r3, (java.lang.String) r7, (java.lang.Object[]) r2)
        L_0x0042:
            com.android.incallui.call.CallList r7 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r5 = r7.getCallById(r5)
            if (r5 == 0) goto L_0x0054
            com.android.incallui.call.DialerCall$LogState r5 = r5.getLogState()
            com.android.dialer.logging.ContactLookupResult$Type r7 = r6.contactLookupResult
            r5.contactLookupResult = r7
        L_0x0054:
            android.net.Uri r5 = r6.lookupUri
            if (r5 == 0) goto L_0x006a
            android.content.Context r4 = r4.context
            com.android.contacts.common.model.ContactLoader r6 = new com.android.contacts.common.model.ContactLoader
            r6.<init>(r4, r5, r0)
            com.android.incallui.CallerInfoUtils$1 r4 = new com.android.incallui.CallerInfoUtils$1
            r4.<init>()
            r6.registerListener(r1, r4)
            r6.startLoading()
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.CallCardPresenter.onContactInfoComplete(java.lang.String, com.android.incallui.ContactInfoCache$ContactCacheEntry, boolean):void");
    }

    private boolean shouldShowCallSubject(DialerCall dialerCall) {
        if (dialerCall == null) {
            return false;
        }
        if (!(this.primary.getState() == 4 || this.primary.getState() == 5) || TextUtils.isEmpty(dialerCall.getCallSubject()) || dialerCall.getNumberPresentation() != 1 || !dialerCall.isCallSubjectSupported()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean shouldShowLocation() {
        /*
            r6 = this;
            android.content.Context r0 = r6.context
            com.android.dialer.configprovider.ConfigProviderComponent r0 = com.android.dialer.configprovider.ConfigProviderComponent.get(r0)
            com.android.dialer.configprovider.ConfigProvider r0 = r0.getConfigProvider()
            com.android.dialer.configprovider.SharedPrefConfigProvider r0 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r0
            r1 = 1
            java.lang.String r2 = "config_enable_emergency_location"
            boolean r0 = r0.getBoolean(r2, r1)
            r2 = 0
            java.lang.String r3 = "CallCardPresenter.getLocationFragment"
            if (r0 != 0) goto L_0x0020
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r0 = "disabled by config."
            com.android.dialer.common.LogUtil.m9i(r3, r0, r6)
            return r2
        L_0x0020:
            com.android.incallui.call.DialerCall r0 = r6.primary
            boolean r0 = isOutgoingEmergencyCall(r0)
            java.lang.String r4 = "CallCardPresenter.shouldShowLocation"
            if (r0 == 0) goto L_0x0032
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r5 = "new emergency call"
            com.android.dialer.common.LogUtil.m9i(r4, r5, r0)
            goto L_0x0051
        L_0x0032:
            com.android.incallui.call.DialerCall r0 = r6.primary
            boolean r0 = isIncomingEmergencyCall(r0)
            if (r0 == 0) goto L_0x0042
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r5 = "potential emergency callback"
            com.android.dialer.common.LogUtil.m9i(r4, r5, r0)
            goto L_0x0051
        L_0x0042:
            com.android.incallui.call.DialerCall r0 = r6.secondary
            boolean r0 = isIncomingEmergencyCall(r0)
            if (r0 == 0) goto L_0x0053
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r5 = "has potential emergency callback"
            com.android.dialer.common.LogUtil.m9i(r4, r5, r0)
        L_0x0051:
            r0 = r1
            goto L_0x0054
        L_0x0053:
            r0 = r2
        L_0x0054:
            if (r0 != 0) goto L_0x005e
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r0 = "shouldn't show location"
            com.android.dialer.common.LogUtil.m9i(r3, r0, r6)
            return r2
        L_0x005e:
            android.content.Context r0 = r6.context
            java.lang.String r4 = "android.permission.ACCESS_FINE_LOCATION"
            int r0 = android.support.p000v4.content.ContextCompat.checkSelfPermission(r0, r4)
            if (r0 != 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r1 = r2
        L_0x006a:
            if (r1 != 0) goto L_0x0074
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r0 = "no location permission."
            com.android.dialer.common.LogUtil.m9i(r3, r0, r6)
            return r2
        L_0x0074:
            boolean r0 = r6.isBatteryTooLowForEmergencyLocation()
            if (r0 == 0) goto L_0x0082
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r0 = "low battery."
            com.android.dialer.common.LogUtil.m9i(r3, r0, r6)
            return r2
        L_0x0082:
            com.android.incallui.incall.protocol.InCallScreen r0 = r6.inCallScreen
            android.support.v4.app.Fragment r0 = r0.getInCallScreenFragment()
            android.support.v4.app.FragmentActivity r0 = r0.getActivity()
            boolean r0 = r0.isInMultiWindowMode()
            if (r0 == 0) goto L_0x009a
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r0 = "in multi-window mode"
            com.android.dialer.common.LogUtil.m9i(r3, r0, r6)
            return r2
        L_0x009a:
            com.android.incallui.call.DialerCall r0 = r6.primary
            boolean r0 = r0.isVideoCall()
            if (r0 == 0) goto L_0x00aa
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r0 = "emergency video calls not supported"
            com.android.dialer.common.LogUtil.m9i(r3, r0, r6)
            return r2
        L_0x00aa:
            com.android.incallui.calllocation.CallLocation r0 = r6.callLocation
            android.content.Context r6 = r6.context
            r0.canGetLocation(r6)
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r0 = "can't get current location"
            com.android.dialer.common.LogUtil.m9i(r3, r0, r6)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.CallCardPresenter.shouldShowLocation():boolean");
    }

    private boolean shouldShowManageConference() {
        DialerCall dialerCall = this.primary;
        if (dialerCall != null && dialerCall.can(128) && !this.isFullscreen) {
            return true;
        }
        return false;
    }

    private boolean shouldShowNoteSentToast(DialerCall dialerCall) {
        if (dialerCall == null || !(!TextUtils.isEmpty(dialerCall.getCallSubject())) || (dialerCall.getState() != 6 && dialerCall.getState() != 13)) {
            return false;
        }
        return true;
    }

    private void updateContactEntry(ContactInfoCache.ContactCacheEntry contactCacheEntry, boolean z) {
        if (z) {
            this.primaryContactInfo = contactCacheEntry;
            updatePrimaryDisplayInfo();
            return;
        }
        this.secondaryContactInfo = contactCacheEntry;
        updateSecondaryDisplayInfo();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        r0 = r15.primaryContactInfo;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x025b  */
    /* JADX WARNING: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x011f A[SYNTHETIC, Splitter:B:56:0x011f] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0211 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0223  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updatePrimaryCallState() {
        /*
            r15 = this;
            com.android.incallui.incall.protocol.InCallScreen r0 = r15.inCallScreen
            if (r0 == 0) goto L_0x025e
            com.android.incallui.call.DialerCall r0 = r15.primary
            if (r0 == 0) goto L_0x025e
            r1 = 32
            boolean r0 = r0.hasProperty(r1)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0021
            com.android.incallui.ContactInfoCache$ContactCacheEntry r0 = r15.primaryContactInfo
            if (r0 == 0) goto L_0x001f
            long r3 = r0.userType
            r5 = 1
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x001f
            goto L_0x0021
        L_0x001f:
            r0 = r1
            goto L_0x0022
        L_0x0021:
            r0 = r2
        L_0x0022:
            com.android.incallui.call.DialerCall r3 = r15.primary
            r4 = 3
            if (r3 == 0) goto L_0x002f
            int r3 = r3.getState()
            if (r3 != r4) goto L_0x002f
            r3 = r2
            goto L_0x0030
        L_0x002f:
            r3 = r1
        L_0x0030:
            if (r3 == 0) goto L_0x003e
            com.android.incallui.call.DialerCall r3 = r15.primary
            r5 = 16
            boolean r3 = r3.hasProperty(r5)
            if (r3 == 0) goto L_0x003e
            r3 = r2
            goto L_0x003f
        L_0x003e:
            r3 = r1
        L_0x003f:
            if (r3 != 0) goto L_0x0055
            com.android.incallui.call.DialerCall r5 = r15.primary
            r6 = 67108864(0x4000000, float:1.5046328E-36)
            boolean r5 = r5.hasProperty(r6)
            if (r5 != 0) goto L_0x0055
            android.content.Context r5 = r15.context
            boolean r5 = com.android.dialer.oem.MotorolaUtils.shouldBlinkHdIconWhenConnectingCall(r5)
            if (r5 == 0) goto L_0x0055
            r5 = r2
            goto L_0x0056
        L_0x0055:
            r5 = r1
        L_0x0056:
            com.android.incallui.ContactInfoCache$ContactCacheEntry r6 = r15.primaryContactInfo
            if (r6 == 0) goto L_0x0060
            boolean r6 = r6.isBusiness
            if (r6 == 0) goto L_0x0060
            r6 = r2
            goto L_0x0061
        L_0x0060:
            r6 = r1
        L_0x0061:
            com.android.incallui.call.DialerCall r7 = r15.primary
            int r7 = r7.getVideoState()
            com.android.incallui.call.DialerCall r8 = r15.primary
            int r8 = r8.getState()
            boolean r7 = com.android.incallui.VideoCallPresenter.showIncomingVideo(r7, r8)
            r7 = r7 ^ r2
            com.android.incallui.incall.protocol.InCallScreen r8 = r15.inCallScreen
            com.android.incallui.incall.protocol.PrimaryCallState$Builder r9 = com.android.incallui.incall.protocol.PrimaryCallState.builder()
            com.android.incallui.call.DialerCall r10 = r15.primary
            int r10 = r10.getState()
            r9.setState(r10)
            com.android.incallui.call.DialerCall r10 = r15.primary
            boolean r10 = r10.isVideoCall()
            r9.setIsVideoCall(r10)
            com.android.incallui.call.DialerCall r10 = r15.primary
            com.android.incallui.videotech.VideoTech r10 = r10.getVideoTech()
            int r10 = r10.getSessionModificationState()
            r9.setSessionModificationState(r10)
            com.android.incallui.call.DialerCall r10 = r15.primary
            android.telecom.DisconnectCause r10 = r10.getDisconnectCause()
            r9.setDisconnectCause(r10)
            android.content.Context r10 = r15.context
            java.lang.String r11 = "android.permission.READ_PHONE_STATE"
            int r10 = android.support.p000v4.content.ContextCompat.checkSelfPermission(r10, r11)
            java.lang.String r11 = "CallCardPresenter.getConnectionLabel"
            r12 = 0
            if (r10 == 0) goto L_0x00ae
            goto L_0x00f6
        L_0x00ae:
            com.android.incallui.call.DialerCall r10 = r15.primary
            android.telecom.StatusHints r10 = r10.getStatusHints()
            if (r10 == 0) goto L_0x00c9
            java.lang.CharSequence r13 = r10.getLabel()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x00c9
            java.lang.CharSequence r10 = r10.getLabel()
            java.lang.String r10 = r10.toString()
            goto L_0x00fe
        L_0x00c9:
            boolean r10 = r15.hasOutgoingGatewayCall()
            if (r10 == 0) goto L_0x00f8
            com.android.incallui.incall.protocol.InCallScreen r10 = r15.inCallScreen
            if (r10 == 0) goto L_0x00f8
            android.content.Context r10 = r15.context
            android.content.pm.PackageManager r10 = r10.getPackageManager()
            com.android.incallui.call.DialerCall r13 = r15.primary     // Catch:{ NameNotFoundException -> 0x00f0 }
            android.telecom.GatewayInfo r13 = r13.getGatewayInfo()     // Catch:{ NameNotFoundException -> 0x00f0 }
            java.lang.String r13 = r13.getGatewayProviderPackageName()     // Catch:{ NameNotFoundException -> 0x00f0 }
            android.content.pm.ApplicationInfo r13 = r10.getApplicationInfo(r13, r1)     // Catch:{ NameNotFoundException -> 0x00f0 }
            java.lang.CharSequence r10 = r10.getApplicationLabel(r13)     // Catch:{ NameNotFoundException -> 0x00f0 }
            java.lang.String r10 = r10.toString()     // Catch:{ NameNotFoundException -> 0x00f0 }
            goto L_0x00fe
        L_0x00f0:
            r10 = move-exception
            java.lang.String r13 = "gateway Application Not Found."
            com.android.dialer.common.LogUtil.m7e((java.lang.String) r11, (java.lang.String) r13, (java.lang.Throwable) r10)
        L_0x00f6:
            r10 = r12
            goto L_0x00fe
        L_0x00f8:
            com.android.incallui.call.DialerCall r10 = r15.primary
            java.lang.String r10 = r10.getCallProviderLabel()
        L_0x00fe:
            r9.setConnectionLabel(r10)
            com.android.incallui.InCallPresenter r10 = com.android.incallui.InCallPresenter.getInstance()
            com.android.incallui.ThemeColorManager r10 = r10.getThemeColorManager()
            int r10 = r10.getPrimaryColor()
            r9.setPrimaryColor(r10)
            com.android.incallui.call.DialerCall r10 = r15.primary
            android.os.Bundle r10 = r10.getIntentExtras()
            java.lang.String r13 = "sim_suggestion_reason"
            java.lang.String r10 = r10.getString(r13)
            if (r10 != 0) goto L_0x011f
            goto L_0x012f
        L_0x011f:
            com.android.dialer.preferredsim.suggestion.SuggestionProvider$Reason r10 = com.android.dialer.preferredsim.suggestion.SuggestionProvider.Reason.valueOf(r10)     // Catch:{ IllegalArgumentException -> 0x0124 }
            goto L_0x0130
        L_0x0124:
            java.lang.String r13 = "unknown reason "
            java.lang.String r10 = com.android.tools.p006r8.GeneratedOutlineSupport.outline8(r13, r10)
            java.lang.Object[] r13 = new java.lang.Object[r1]
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r11, (java.lang.String) r10, (java.lang.Object[]) r13)
        L_0x012f:
            r10 = r12
        L_0x0130:
            r9.setSimSuggestionReason(r10)
            com.android.incallui.call.DialerCall r10 = r15.primary
            android.telecom.StatusHints r10 = r10.getStatusHints()
            if (r10 == 0) goto L_0x014e
            android.graphics.drawable.Icon r11 = r10.getIcon()
            if (r11 == 0) goto L_0x014e
            android.graphics.drawable.Icon r10 = r10.getIcon()
            android.content.Context r11 = r15.context
            android.graphics.drawable.Drawable r10 = r10.loadDrawable(r11)
            if (r10 == 0) goto L_0x014e
            goto L_0x014f
        L_0x014e:
            r10 = r12
        L_0x014f:
            r9.setConnectionIcon(r10)
            boolean r10 = r15.hasOutgoingGatewayCall()
            if (r10 == 0) goto L_0x016c
            com.android.incallui.call.DialerCall r10 = r15.primary
            android.telecom.GatewayInfo r10 = r10.getGatewayInfo()
            android.net.Uri r10 = r10.getGatewayAddress()
            if (r10 != 0) goto L_0x0167
            java.lang.String r10 = ""
            goto L_0x016d
        L_0x0167:
            java.lang.String r10 = r10.getSchemeSpecificPart()
            goto L_0x016d
        L_0x016c:
            r10 = r12
        L_0x016d:
            r9.setGatewayNumber(r10)
            com.android.incallui.call.DialerCall r10 = r15.primary
            boolean r10 = r15.shouldShowCallSubject(r10)
            if (r10 == 0) goto L_0x017f
            com.android.incallui.call.DialerCall r10 = r15.primary
            java.lang.String r10 = r10.getCallSubject()
            goto L_0x0180
        L_0x017f:
            r10 = r12
        L_0x0180:
            r9.setCallSubject(r10)
            android.content.Context r10 = r15.context
            com.android.incallui.call.DialerCall r11 = r15.primary
            java.lang.String r11 = r11.getCallbackNumber()
            com.android.incallui.call.DialerCall r13 = r15.primary
            java.lang.String r13 = r13.getSimCountryIso()
            java.lang.String r10 = com.android.dialer.phonenumberutil.PhoneNumberHelper.formatNumber(r10, r11, r13)
            r9.setCallbackNumber(r10)
            com.android.incallui.call.DialerCall r10 = r15.primary
            r11 = 8
            boolean r10 = r10.hasProperty(r11)
            r9.setIsWifi(r10)
            com.android.incallui.call.DialerCall r10 = r15.primary
            boolean r10 = r10.isConferenceCall()
            r11 = 2
            if (r10 == 0) goto L_0x01b6
            com.android.incallui.call.DialerCall r10 = r15.primary
            boolean r10 = r10.hasProperty(r11)
            if (r10 != 0) goto L_0x01b6
            r10 = r2
            goto L_0x01b7
        L_0x01b6:
            r10 = r1
        L_0x01b7:
            r9.setIsConference(r10)
            r9.setIsWorkCall(r0)
            r9.setIsHdAttempting(r5)
            r9.setIsHdAudioCall(r3)
            com.android.incallui.call.DialerCall r0 = r15.primary
            java.lang.String r0 = r0.getLastForwardedNumber()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x01da
            com.android.incallui.call.DialerCall r0 = r15.primary
            boolean r0 = r0.isCallForwarded()
            if (r0 == 0) goto L_0x01d8
            goto L_0x01da
        L_0x01d8:
            r0 = r1
            goto L_0x01db
        L_0x01da:
            r0 = r2
        L_0x01db:
            r9.setIsForwardedNumber(r0)
            r9.setShouldShowContactPhoto(r7)
            com.android.incallui.call.DialerCall r0 = r15.primary
            long r13 = r0.getConnectTimeMillis()
            r9.setConnectTimeMillis(r13)
            com.android.incallui.call.DialerCall r0 = r15.primary
            boolean r0 = r0.isVoiceMailNumber()
            r9.setIsVoiceMailNumber(r0)
            com.android.incallui.call.DialerCall r0 = r15.primary
            boolean r0 = r0.isRemotelyHeld()
            r9.setIsRemotelyHeld(r0)
            r9.setIsBusinessNumber(r6)
            com.android.incallui.call.CallList r0 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r0 = r0.getActiveOrBackgroundCall()
            com.android.incallui.call.CallList r3 = com.android.incallui.call.CallList.getInstance()
            com.android.incallui.call.DialerCall r3 = r3.getIncomingCall()
            if (r0 == 0) goto L_0x021a
            if (r3 == 0) goto L_0x021a
            if (r0 == r3) goto L_0x021a
            boolean r0 = r3.can(r2)
            goto L_0x021b
        L_0x021a:
            r0 = r2
        L_0x021b:
            r9.setSupportsCallOnHold(r0)
            com.android.incallui.call.DialerCall r0 = r15.secondary
            if (r0 != 0) goto L_0x0223
            goto L_0x022e
        L_0x0223:
            com.android.incallui.call.DialerCall r0 = r15.primary
            int r0 = r0.getState()
            if (r0 != r4) goto L_0x022d
            r1 = r11
            goto L_0x022e
        L_0x022d:
            r1 = r2
        L_0x022e:
            r9.setSwapToSecondaryButtonState(r1)
            com.android.incallui.call.DialerCall r0 = r15.primary
            boolean r0 = r0.isAssistedDialed()
            r9.setIsAssistedDialed(r0)
            r9.setCustomLabel(r12)
            com.android.incallui.call.DialerCall r0 = r15.primary
            com.android.dialer.assisteddialing.TransformationInfo r0 = r0.getAssistedDialingExtras()
            r9.setAssistedDialingExtras(r0)
            com.android.incallui.incall.protocol.PrimaryCallState r0 = r9.build()
            r8.setCallState(r0)
            com.android.incallui.incall.protocol.InCallScreen r15 = r15.inCallScreen
            android.support.v4.app.Fragment r15 = r15.getInCallScreenFragment()
            android.support.v4.app.FragmentActivity r15 = r15.getActivity()
            com.android.incallui.InCallActivity r15 = (com.android.incallui.InCallActivity) r15
            if (r15 == 0) goto L_0x025e
            r15.onPrimaryCallStateChanged()
        L_0x025e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.CallCardPresenter.updatePrimaryCallState():void");
    }

    private void updatePrimaryDisplayInfo() {
        String str;
        InCallScreen inCallScreen2 = this.inCallScreen;
        if (inCallScreen2 != null) {
            DialerCall dialerCall = this.primary;
            if (dialerCall == null) {
                inCallScreen2.setPrimary(PrimaryInfo.empty());
                return;
            }
            boolean z = !VideoCallPresenter.showIncomingVideo(dialerCall.getVideoState(), this.primary.getState());
            boolean hasProperty = this.primary.hasProperty(32);
            this.primary.getEnrichedCallSession();
            if (this.primary.isConferenceCall()) {
                InCallScreen inCallScreen3 = this.inCallScreen;
                PrimaryInfo.Builder builder = PrimaryInfo.builder();
                builder.setName(CallerInfoUtils.getConferenceString(this.context, this.primary.hasProperty(2)));
                builder.setNameIsNumber(false);
                builder.setPhotoType(0);
                builder.setIsSipCall(false);
                builder.setIsContactPhotoShown(z);
                builder.setIsWorkCall(hasProperty);
                builder.setIsSpam(false);
                builder.setIsLocalContact(false);
                builder.setAnsweringDisconnectsOngoingCall(false);
                shouldShowLocation();
                builder.setShouldShowLocation(false);
                builder.setShowInCallButtonGrid(true);
                builder.setNumberPresentation(this.primary.getNumberPresentation());
                inCallScreen3.setPrimary(builder.build());
            } else if (this.primaryContactInfo != null) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("update primary display info for ");
                outline13.append(this.primaryContactInfo);
                outline13.toString();
                String nameForCall = getNameForCall(this.primaryContactInfo);
                boolean z2 = !TextUtils.isEmpty(this.primary.getChildNumber());
                boolean z3 = !TextUtils.isEmpty(this.primary.getLastForwardedNumber());
                boolean shouldShowCallSubject = shouldShowCallSubject(this.primary);
                if (shouldShowCallSubject) {
                    str = null;
                } else if (z2) {
                    str = this.context.getString(R.string.child_number, new Object[]{this.primary.getChildNumber()});
                } else if (z3) {
                    str = this.primary.getLastForwardedNumber();
                } else {
                    str = this.primaryContactInfo.number;
                }
                boolean z4 = nameForCall != null && nameForCall.equals(this.primaryContactInfo.number);
                boolean z5 = this.primaryContactInfo.userType == 1;
                InCallScreen inCallScreen4 = this.inCallScreen;
                PrimaryInfo.Builder builder2 = PrimaryInfo.builder();
                builder2.setNumber(str);
                builder2.setName(this.primary.updateNameIfRestricted(nameForCall));
                builder2.setNameIsNumber(z4);
                builder2.setLocation(z4 || this.primaryContactInfo.shouldShowLocation ? this.primaryContactInfo.location : null);
                builder2.setLabel((z2 || shouldShowCallSubject) ? null : this.primaryContactInfo.label);
                builder2.setPhoto(this.primaryContactInfo.photo);
                builder2.setPhotoUri(this.primaryContactInfo.displayPhotoUri);
                builder2.setPhotoType(this.primaryContactInfo.photoType);
                builder2.setIsSipCall(this.primaryContactInfo.isSipCall);
                builder2.setIsContactPhotoShown(z);
                builder2.setIsWorkCall(hasProperty || z5);
                builder2.setIsSpam(this.primary.isSpam());
                builder2.setIsLocalContact(this.primaryContactInfo.contactLookupResult == ContactLookupResult$Type.LOCAL_CONTACT);
                builder2.setAnsweringDisconnectsOngoingCall(this.primary.answeringDisconnectsForegroundVideoCall());
                shouldShowLocation();
                builder2.setShouldShowLocation(false);
                builder2.setContactInfoLookupKey(this.primaryContactInfo.lookupKey);
                builder2.setMultimediaData((MultimediaData) null);
                builder2.setShowInCallButtonGrid(true);
                builder2.setNumberPresentation(this.primary.getNumberPresentation());
                inCallScreen4.setPrimary(builder2.build());
            } else {
                this.inCallScreen.setPrimary(PrimaryInfo.empty());
            }
            if (this.isInCallScreenReady) {
                InCallScreen inCallScreen5 = this.inCallScreen;
                shouldShowLocation();
                inCallScreen5.showLocationUi((Fragment) null);
                return;
            }
            LogUtil.m9i("CallCardPresenter.updatePrimaryDisplayInfo", "UI not ready, not showing location", new Object[0]);
        }
    }

    private void updateSecondaryDisplayInfo() {
        InCallScreen inCallScreen2 = this.inCallScreen;
        if (inCallScreen2 != null) {
            DialerCall dialerCall = this.secondary;
            if (dialerCall == null) {
                SecondaryInfo.Builder builder = SecondaryInfo.builder();
                builder.setIsFullscreen(this.isFullscreen);
                inCallScreen2.setSecondary(builder.build());
                return;
            }
            boolean isMergeInProcess = dialerCall.isMergeInProcess();
            boolean z = false;
            if (isMergeInProcess) {
                LogUtil.m9i("CallCardPresenter.updateSecondaryDisplayInfo", "secondary call is merge in process, clearing info", new Object[0]);
                InCallScreen inCallScreen3 = this.inCallScreen;
                SecondaryInfo.Builder builder2 = SecondaryInfo.builder();
                builder2.setIsFullscreen(this.isFullscreen);
                inCallScreen3.setSecondary(builder2.build());
            } else if (this.secondary.isConferenceCall()) {
                InCallScreen inCallScreen4 = this.inCallScreen;
                SecondaryInfo.Builder builder3 = SecondaryInfo.builder();
                builder3.setShouldShow(true);
                builder3.setName(CallerInfoUtils.getConferenceString(this.context, this.secondary.hasProperty(2)));
                builder3.setProviderLabel(this.secondary.getCallProviderLabel());
                builder3.setIsConference(true);
                builder3.setIsVideoCall(this.secondary.isVideoCall());
                builder3.setIsFullscreen(this.isFullscreen);
                inCallScreen4.setSecondary(builder3.build());
            } else if (this.secondaryContactInfo != null) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("");
                outline13.append(this.secondaryContactInfo);
                outline13.toString();
                String nameForCall = getNameForCall(this.secondaryContactInfo);
                if (nameForCall != null && nameForCall.equals(this.secondaryContactInfo.number)) {
                    z = true;
                }
                InCallScreen inCallScreen5 = this.inCallScreen;
                SecondaryInfo.Builder builder4 = SecondaryInfo.builder();
                builder4.setShouldShow(true);
                builder4.setName(this.secondary.updateNameIfRestricted(nameForCall));
                builder4.setNameIsNumber(z);
                builder4.setLabel(this.secondaryContactInfo.label);
                builder4.setProviderLabel(this.secondary.getCallProviderLabel());
                builder4.setIsVideoCall(this.secondary.isVideoCall());
                builder4.setIsFullscreen(this.isFullscreen);
                inCallScreen5.setSecondary(builder4.build());
            } else {
                InCallScreen inCallScreen6 = this.inCallScreen;
                SecondaryInfo.Builder builder5 = SecondaryInfo.builder();
                builder5.setIsFullscreen(this.isFullscreen);
                inCallScreen6.setSecondary(builder5.build());
            }
        }
    }

    public void onDetailsChanged(DialerCall dialerCall, Call.Details details) {
        updatePrimaryCallState();
        if (dialerCall.can(128) != details.can(128)) {
            maybeShowManageConferenceCallButton();
        }
    }

    public void onDialerCallChildNumberChange() {
        if (this.primary != null) {
            updatePrimaryDisplayInfo();
        }
    }

    public void onDialerCallDisconnect() {
    }

    public void onDialerCallLastForwardedNumberChange() {
        if (this.primary != null) {
            updatePrimaryDisplayInfo();
            updatePrimaryCallState();
        }
    }

    public void onDialerCallSessionModificationStateChange() {
        LogUtil.enterBlock("CallCardPresenter.onDialerCallSessionModificationStateChange");
        DialerCall dialerCall = this.primary;
        if (dialerCall != null) {
            this.inCallScreen.setEndCallButtonEnabled(dialerCall.getVideoTech().getSessionModificationState() != 3, true);
            updatePrimaryCallState();
        }
    }

    public void onDialerCallUpdate() {
    }

    public void onDialerCallUpgradeToVideo() {
    }

    public void onEndCallClicked() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("disconnecting call: ");
        outline13.append(this.primary);
        LogUtil.m9i("CallCardPresenter.onEndCallClicked", outline13.toString(), new Object[0]);
        DialerCall dialerCall = this.primary;
        if (dialerCall != null) {
            dialerCall.disconnect();
        }
        StorageComponent.get(this.context).unencryptedSharedPrefs().edit().putBoolean("post_call_disconnect_pressed", true).apply();
    }

    public void onFullscreenModeChanged(boolean z) {
        this.isFullscreen = z;
        if (this.inCallScreen != null) {
            maybeShowManageConferenceCallButton();
        }
    }

    public void onHandoverToWifiFailure() {
    }

    public void onInCallScreenDelegateInit(InCallScreen inCallScreen2) {
        Assert.isNotNull(inCallScreen2);
        this.inCallScreen = inCallScreen2;
        DialerCall firstCall = CallList.getInstance().getFirstCall();
        if (firstCall != null) {
            this.primary = firstCall;
            if (shouldShowNoteSentToast(this.primary)) {
                this.inCallScreen.showNoteSentToast();
            }
            firstCall.addListener(this);
            if (!firstCall.isConferenceCall()) {
                ContactInfoCache.getInstance(this.context).findInfo(firstCall, firstCall.getState() == 4, new ContactLookupCallback(this, true));
            } else {
                updateContactEntry((ContactInfoCache.ContactCacheEntry) null, true);
            }
        }
        onStateChange((InCallPresenter.InCallState) null, InCallPresenter.getInstance().getInCallState(), CallList.getInstance());
    }

    public void onInCallScreenPaused() {
    }

    public void onInCallScreenReady() {
        LogUtil.m9i("CallCardPresenter.onInCallScreenReady", (String) null, new Object[0]);
        Assert.checkState(!this.isInCallScreenReady);
        if (this.primaryContactInfo != null) {
            updatePrimaryDisplayInfo();
        }
        InCallPresenter.getInstance().addListener(this);
        InCallPresenter.getInstance().addIncomingCallListener(this);
        InCallPresenter.getInstance().addDetailsListener(this);
        InCallPresenter.getInstance().addInCallEventListener(this);
        this.isInCallScreenReady = true;
        if (isOutgoingEmergencyCall(this.primary)) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.EMERGENCY_NEW_EMERGENCY_CALL);
        } else if (isIncomingEmergencyCall(this.primary) || isIncomingEmergencyCall(this.secondary)) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.EMERGENCY_CALLBACK);
        }
        shouldShowLocation();
    }

    public void onInCallScreenResumed() {
        updatePrimaryDisplayInfo();
        if (this.shouldSendAccessibilityEvent) {
            this.handler.postDelayed(this.sendAccessibilityEventRunnable, 500);
        }
    }

    public void onInCallScreenUnready() {
        LogUtil.m9i("CallCardPresenter.onInCallScreenUnready", (String) null, new Object[0]);
        Assert.checkState(this.isInCallScreenReady);
        InCallPresenter.getInstance().removeListener(this);
        InCallPresenter.getInstance().removeIncomingCallListener(this);
        InCallPresenter.getInstance().removeDetailsListener(this);
        InCallPresenter.getInstance().removeInCallEventListener(this);
        DialerCall dialerCall = this.primary;
        if (dialerCall != null) {
            dialerCall.removeListener(this);
        }
        this.callLocation.close();
        this.primary = null;
        this.primaryContactInfo = null;
        this.secondaryContactInfo = null;
        this.isInCallScreenReady = false;
    }

    public void onIncomingCall(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, DialerCall dialerCall) {
        onStateChange(inCallState, inCallState2, CallList.getInstance());
    }

    public void onInternationalCallOnWifi() {
    }

    public void onManageConferenceClicked() {
        ((InCallActivity) this.inCallScreen.getInCallScreenFragment().getActivity()).showConferenceFragment(true);
    }

    public void onSecondaryInfoClicked() {
        if (this.secondary == null) {
            LogUtil.m8e("CallCardPresenter.onSecondaryInfoClicked", "secondary info clicked but no secondary call.", new Object[0]);
            return;
        }
        ((LoggingBindingsStub) Logger.get(this.context)).logCallImpression(DialerImpression$Type.IN_CALL_SWAP_SECONDARY_BUTTON_PRESSED, this.primary.getUniqueCallId(), this.primary.getTimeAddedMs());
        LogUtil.m9i("CallCardPresenter.onSecondaryInfoClicked", "swapping call to foreground: " + this.secondary, new Object[0]);
        this.secondary.unhold();
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        DialerCall dialerCall;
        DialerCall dialerCall2;
        boolean z;
        InCallPresenter.InCallState inCallState3;
        InCallPresenter.InCallState inCallState4;
        Trace.beginSection("CallCardPresenter.onStateChange");
        int i = 2;
        Object[] objArr = {inCallState, inCallState2};
        if (this.inCallScreen == null) {
            Trace.endSection();
            return;
        }
        if (inCallState2 == InCallPresenter.InCallState.INCOMING) {
            dialerCall2 = callList.getIncomingCall();
            dialerCall = null;
        } else if (inCallState2 == InCallPresenter.InCallState.PENDING_OUTGOING || inCallState2 == InCallPresenter.InCallState.OUTGOING) {
            dialerCall2 = callList.getOutgoingCall();
            if (dialerCall2 == null) {
                dialerCall2 = callList.getPendingOutgoingCall();
            }
            dialerCall = InCallPresenter.getCallToDisplay(callList, (DialerCall) null, true);
        } else if (inCallState2 == InCallPresenter.InCallState.INCALL) {
            dialerCall2 = InCallPresenter.getCallToDisplay(callList, (DialerCall) null, false);
            dialerCall = InCallPresenter.getCallToDisplay(callList, dialerCall2, true);
        } else {
            dialerCall = null;
            dialerCall2 = null;
        }
        "primary call: " + dialerCall2;
        "secondary call: " + dialerCall;
        String number = dialerCall2 != null ? dialerCall2.getNumber() : null;
        String number2 = dialerCall != null ? dialerCall.getNumber() : null;
        boolean z2 = !DialerCall.areSame(this.primary, dialerCall2) || !TextUtils.equals(this.primaryNumber, number);
        boolean z3 = !DialerCall.areSame(this.secondary, dialerCall) || !TextUtils.equals(this.secondaryNumber, number2);
        this.secondary = dialerCall;
        this.secondaryNumber = number2;
        DialerCall dialerCall3 = this.primary;
        this.primary = dialerCall2;
        this.primaryNumber = number;
        if (this.primary != null) {
            this.inCallScreen.updateInCallScreenColors();
        }
        if (z2 && shouldShowNoteSentToast(dialerCall2)) {
            this.inCallScreen.showNoteSentToast();
        }
        if (this.primary != null && (z2 || this.inCallScreen.isManageConferenceVisible() != shouldShowManageConference())) {
            if (dialerCall3 != null) {
                dialerCall3.removeListener(this);
            }
            this.primary.addListener(this);
            this.primaryContactInfo = ContactInfoCache.buildCacheEntryFromCall(this.context, this.primary);
            updatePrimaryDisplayInfo();
            maybeStartSearch(this.primary, true);
        }
        if (dialerCall3 != null && this.primary == null) {
            dialerCall3.removeListener(this);
        }
        if (z3) {
            DialerCall dialerCall4 = this.secondary;
            if (dialerCall4 == null) {
                this.secondaryContactInfo = null;
                updateSecondaryDisplayInfo();
            } else {
                this.secondaryContactInfo = ContactInfoCache.buildCacheEntryFromCall(this.context, dialerCall4);
                updateSecondaryDisplayInfo();
                maybeStartSearch(this.secondary, false);
            }
        }
        DialerCall dialerCall5 = this.primary;
        if (dialerCall5 != null) {
            i = dialerCall5.getState();
            updatePrimaryCallState();
        } else {
            this.inCallScreen.setCallState(PrimaryCallState.empty());
        }
        maybeShowManageConferenceCallButton();
        InCallScreen inCallScreen2 = this.inCallScreen;
        if (this.primary == null || ((!R$style.isConnectingOrConnected(i) && i != 9 && i != 10) || i == 4 || this.primary.getVideoTech().getSessionModificationState() == 3)) {
            z = false;
        } else {
            z = true;
        }
        inCallScreen2.setEndCallButtonEnabled(z, i != 4);
        this.shouldSendAccessibilityEvent = false;
        Context context2 = this.context;
        if (context2 != null && ((AccessibilityManager) context2.getSystemService("accessibility")).isEnabled() && ((inCallState != (inCallState3 = InCallPresenter.InCallState.OUTGOING) && inCallState2 == inCallState3) || ((inCallState != (inCallState4 = InCallPresenter.InCallState.INCOMING) && inCallState2 == inCallState4) || z2))) {
            LogUtil.m9i("CallCardPresenter.maybeSendAccessibilityEvent", "schedule accessibility announcement", new Object[0]);
            this.shouldSendAccessibilityEvent = true;
            this.handler.postDelayed(this.sendAccessibilityEventRunnable, 500);
        }
        Trace.endSection();
    }

    public void onWiFiToLteHandover() {
    }
}
