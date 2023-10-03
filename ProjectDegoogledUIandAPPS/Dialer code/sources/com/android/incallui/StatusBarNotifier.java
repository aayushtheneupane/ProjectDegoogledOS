package com.android.incallui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Trace;
import android.support.p002v7.appcompat.R$style;
import android.telecom.CallAudioState;
import android.telecom.PhoneAccount;
import android.telecom.TelecomManager;
import android.text.BidiFormatter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.contacts.ContactsComponent;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.oem.MotorolaUtils;
import com.android.incallui.ContactInfoCache;
import com.android.incallui.InCallPresenter;
import com.android.incallui.async.PausableExecutorImpl;
import com.android.incallui.call.CallList;
import com.android.incallui.call.DialerCall;
import com.android.incallui.call.DialerCallListener;
import com.android.incallui.call.TelecomAdapter;
import com.android.incallui.ringtone.DialerRingtoneManager;
import com.android.incallui.ringtone.InCallTonePlayer;
import com.android.incallui.ringtone.ToneGeneratorFactory;

public class StatusBarNotifier implements InCallPresenter.InCallStateListener, EnrichedCallManager.StateChangedListener, ContactInfoCache.ContactInfoCacheCallback {
    private static final long[] VIBRATE_PATTERN = {0, 1000, 1000};
    private int callState = 0;
    private final ContactInfoCache contactInfoCache;
    private final Context context;
    private int currentNotification = 0;
    /* access modifiers changed from: private */
    public final DialerRingtoneManager dialerRingtoneManager;
    private Uri ringtone;
    private CallAudioState savedCallAudioState;
    private String savedContent = null;
    private String savedContentTitle;
    private int savedIcon = 0;
    private Bitmap savedLargeIcon;
    private StatusBarCallListener statusBarCallListener;
    private int videoState = 0;

    private class StatusBarCallListener implements DialerCallListener {
        private DialerCall dialerCall;

        StatusBarCallListener(DialerCall dialerCall2) {
            this.dialerCall = dialerCall2;
            this.dialerCall.addListener(this);
        }

        /* access modifiers changed from: package-private */
        public void cleanup() {
            this.dialerCall.removeListener(this);
        }

        public void onDialerCallChildNumberChange() {
        }

        public void onDialerCallDisconnect() {
        }

        public void onDialerCallLastForwardedNumberChange() {
        }

        public void onDialerCallSessionModificationStateChange() {
            if (this.dialerCall.getVideoTech().getSessionModificationState() == 0) {
                cleanup();
                StatusBarNotifier.this.updateNotification();
            }
        }

        public void onDialerCallUpdate() {
            if (CallList.getInstance().getIncomingCall() == null) {
                StatusBarNotifier.this.dialerRingtoneManager.stopCallWaitingTone();
            }
        }

        public void onDialerCallUpgradeToVideo() {
        }

        public void onHandoverToWifiFailure() {
        }

        public void onInternationalCallOnWifi() {
        }

        public void onWiFiToLteHandover() {
        }
    }

    public StatusBarNotifier(Context context2, ContactInfoCache contactInfoCache2) {
        Trace.beginSection("StatusBarNotifier.Constructor");
        Assert.isNotNull(context2);
        this.context = context2;
        this.contactInfoCache = contactInfoCache2;
        this.dialerRingtoneManager = new DialerRingtoneManager(new InCallTonePlayer(new ToneGeneratorFactory(), new PausableExecutorImpl()), CallList.getInstance());
        this.currentNotification = 0;
        Trace.endSection();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00fb, code lost:
        if (com.android.incallui.InCallPresenter.getInstance().isShowingInCallUi() != false) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0111, code lost:
        if ((r19.getActiveOrBackgroundCall() != null && com.android.incallui.InCallPresenter.getInstance().isShowingInCallUi()) != false) goto L_0x0115;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void buildAndSendNotification(com.android.incallui.call.CallList r19, com.android.incallui.call.DialerCall r20, com.android.incallui.ContactInfoCache.ContactCacheEntry r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r21
            java.lang.String r2 = "StatusBarNotifier.buildAndSendNotification"
            android.os.Trace.beginSection(r2)
            com.android.incallui.call.DialerCall r3 = r18.getCallToShow(r19)
            if (r3 == 0) goto L_0x0577
            java.lang.String r4 = r3.getId()
            java.lang.String r5 = r20.getId()
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x001f
            goto L_0x0577
        L_0x001f:
            java.lang.String r4 = "prepare work"
            android.os.Trace.beginSection(r4)
            int r4 = r3.getState()
            com.android.incallui.audiomode.AudioModeProvider r5 = com.android.incallui.audiomode.AudioModeProvider.getInstance()
            android.telecom.CallAudioState r5 = r5.getAudioState()
            java.lang.String r6 = "read icon and strings"
            android.os.Trace.beginSection(r6)
            int r6 = r0.getIconToDisplay(r3)
            android.content.Context r7 = r0.context
            java.lang.String r8 = "StatusBarNotifier.getLargeIconToDisplay"
            android.os.Trace.beginSection(r8)
            android.content.res.Resources r8 = r7.getResources()
            android.graphics.drawable.Drawable r9 = r1.photo
            if (r9 == 0) goto L_0x0053
            boolean r10 = r9 instanceof android.graphics.drawable.BitmapDrawable
            if (r10 == 0) goto L_0x0053
            android.graphics.drawable.BitmapDrawable r9 = (android.graphics.drawable.BitmapDrawable) r9
            android.graphics.Bitmap r9 = r9.getBitmap()
            goto L_0x0054
        L_0x0053:
            r9 = 0
        L_0x0054:
            android.graphics.drawable.Drawable r10 = r1.photo
            r11 = 17104901(0x1050005, float:2.4428256E-38)
            r12 = 17104902(0x1050006, float:2.442826E-38)
            r13 = 2
            if (r10 != 0) goto L_0x009f
            float r9 = r8.getDimension(r11)
            int r9 = (int) r9
            float r10 = r8.getDimension(r12)
            int r10 = (int) r10
            boolean r11 = r3.isVoiceMailNumber()
            boolean r12 = r3.isSpam()
            boolean r14 = r1.isBusiness
            int r15 = r3.getNumberPresentation()
            boolean r16 = r3.isConferenceCall()
            if (r16 == 0) goto L_0x0085
            boolean r13 = r3.hasProperty(r13)
            if (r13 != 0) goto L_0x0085
            r13 = 1
            goto L_0x0086
        L_0x0085:
            r13 = 0
        L_0x0086:
            int r11 = com.android.dialer.lettertile.LetterTileDrawable.getContactTypeFromPrimitives(r11, r12, r14, r15, r13)
            com.android.dialer.lettertile.LetterTileDrawable r12 = new com.android.dialer.lettertile.LetterTileDrawable
            r12.<init>(r8)
            java.lang.String r13 = r1.namePrimary
            if (r13 != 0) goto L_0x0095
            java.lang.String r13 = r1.number
        L_0x0095:
            java.lang.String r14 = r1.lookupKey
            r15 = 1
            r12.setCanonicalDialerLetterTileDetails(r13, r14, r15, r11)
            android.graphics.Bitmap r9 = r12.getBitmap(r9, r10)
        L_0x009f:
            boolean r10 = r3.isSpam()
            if (r10 == 0) goto L_0x00b5
            r9 = 2131230830(0x7f08006e, float:1.8077724E38)
            android.content.res.Resources$Theme r7 = r7.getTheme()
            android.graphics.drawable.Drawable r7 = r8.getDrawable(r9, r7)
            r8 = 0
            android.graphics.Bitmap r9 = android.support.p002v7.appcompat.R$style.drawableToBitmap(r7, r8, r8)
        L_0x00b5:
            android.os.Trace.endSection()
            long r7 = r1.userType
            java.lang.CharSequence r7 = r0.getContentString(r3, r7)
            java.lang.String r8 = r0.getContentTitle(r1, r3)
            android.os.Trace.endSection()
            com.android.incallui.videotech.VideoTech r10 = r3.getVideoTech()
            int r10 = r10.getSessionModificationState()
            r11 = 3
            if (r10 != r11) goto L_0x00d2
            r10 = 1
            goto L_0x00d3
        L_0x00d2:
            r10 = 0
        L_0x00d3:
            r12 = 4
            r13 = 5
            if (r4 == r12) goto L_0x00de
            if (r4 == r13) goto L_0x00de
            if (r10 == 0) goto L_0x00dc
            goto L_0x00de
        L_0x00dc:
            r11 = 1
            goto L_0x0115
        L_0x00de:
            android.content.Context r12 = r0.context
            com.android.dialer.configprovider.ConfigProviderComponent r12 = com.android.dialer.configprovider.ConfigProviderComponent.get(r12)
            com.android.dialer.configprovider.ConfigProvider r12 = r12.getConfigProvider()
            com.android.dialer.configprovider.SharedPrefConfigProvider r12 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r12
            java.lang.String r13 = "quiet_incoming_call_if_ui_showing"
            r14 = 1
            boolean r12 = r12.getBoolean(r13, r14)
            if (r12 == 0) goto L_0x00fe
            com.android.incallui.InCallPresenter r12 = com.android.incallui.InCallPresenter.getInstance()
            boolean r12 = r12.isShowingInCallUi()
            if (r12 == 0) goto L_0x0114
            goto L_0x0115
        L_0x00fe:
            com.android.incallui.call.DialerCall r12 = r19.getActiveOrBackgroundCall()
            if (r12 == 0) goto L_0x0110
            com.android.incallui.InCallPresenter r12 = com.android.incallui.InCallPresenter.getInstance()
            boolean r12 = r12.isShowingInCallUi()
            if (r12 == 0) goto L_0x0110
            r12 = 1
            goto L_0x0111
        L_0x0110:
            r12 = 0
        L_0x0111:
            if (r12 == 0) goto L_0x0114
            goto L_0x0115
        L_0x0114:
            r11 = 2
        L_0x0115:
            android.os.Trace.endSection()
            java.lang.String r12 = r7.toString()
            int r13 = r3.getVideoState()
            android.net.Uri r14 = r1.contactRingtoneUri
            if (r8 == 0) goto L_0x012c
            java.lang.String r15 = r0.savedContentTitle
            boolean r15 = r8.equals(r15)
            if (r15 == 0) goto L_0x0132
        L_0x012c:
            if (r8 != 0) goto L_0x0134
            java.lang.String r15 = r0.savedContentTitle
            if (r15 == 0) goto L_0x0134
        L_0x0132:
            r15 = 1
            goto L_0x0135
        L_0x0134:
            r15 = 0
        L_0x0135:
            android.graphics.Bitmap r1 = r0.savedLargeIcon
            if (r1 != 0) goto L_0x013c
            if (r9 == 0) goto L_0x0145
            goto L_0x0147
        L_0x013c:
            if (r9 == 0) goto L_0x0147
            boolean r1 = r1.sameAs(r9)
            if (r1 != 0) goto L_0x0145
            goto L_0x0147
        L_0x0145:
            r1 = 0
            goto L_0x0148
        L_0x0147:
            r1 = 1
        L_0x0148:
            r20 = r10
            int r10 = r0.savedIcon
            if (r10 != r6) goto L_0x0175
            java.lang.String r10 = r0.savedContent
            boolean r10 = java.util.Objects.equals(r10, r12)
            if (r10 == 0) goto L_0x0175
            int r10 = r0.callState
            if (r10 != r4) goto L_0x0175
            int r10 = r0.videoState
            if (r10 != r13) goto L_0x0175
            if (r1 != 0) goto L_0x0175
            if (r15 != 0) goto L_0x0175
            android.net.Uri r10 = r0.ringtone
            boolean r10 = java.util.Objects.equals(r10, r14)
            if (r10 == 0) goto L_0x0175
            android.telecom.CallAudioState r10 = r0.savedCallAudioState
            boolean r10 = java.util.Objects.equals(r10, r5)
            if (r10 != 0) goto L_0x0173
            goto L_0x0175
        L_0x0173:
            r10 = 0
            goto L_0x0176
        L_0x0175:
            r10 = 1
        L_0x0176:
            r19 = r10
            r10 = 9
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r16 = r7
            int r7 = r0.savedIcon
            if (r7 == r6) goto L_0x0184
            r7 = 1
            goto L_0x0185
        L_0x0184:
            r7 = 0
        L_0x0185:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            r17 = 0
            r10[r17] = r7
            java.lang.String r7 = r0.savedContent
            boolean r7 = java.util.Objects.equals(r7, r12)
            r17 = 1
            r7 = r7 ^ 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            r10[r17] = r7
            int r7 = r0.callState
            if (r7 == r4) goto L_0x01a3
            r7 = 1
            goto L_0x01a4
        L_0x01a3:
            r7 = 0
        L_0x01a4:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            r17 = 2
            r10[r17] = r7
            int r7 = r0.videoState
            if (r7 == r13) goto L_0x01b2
            r7 = 1
            goto L_0x01b3
        L_0x01b2:
            r7 = 0
        L_0x01b3:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            r17 = 3
            r10[r17] = r7
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r7 = 4
            r10[r7] = r1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r15)
            r7 = 5
            r10[r7] = r1
            android.net.Uri r1 = r0.ringtone
            boolean r1 = java.util.Objects.equals(r1, r14)
            r1 = r1 ^ 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r7 = 6
            r10[r7] = r1
            r1 = 7
            android.telecom.CallAudioState r7 = r0.savedCallAudioState
            boolean r7 = java.util.Objects.equals(r7, r5)
            r7 = r7 ^ 1
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            r10[r1] = r7
            int r1 = r0.currentNotification
            if (r1 == r11) goto L_0x01ed
            r1 = 1
            goto L_0x01ee
        L_0x01ed:
            r1 = 0
        L_0x01ee:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r7 = 8
            r10[r7] = r1
            int r1 = r0.currentNotification
            if (r1 == r11) goto L_0x01fc
            r1 = 1
            goto L_0x01fe
        L_0x01fc:
            r1 = r19
        L_0x01fe:
            r0.savedIcon = r6
            r0.savedContent = r12
            r0.callState = r4
            r0.videoState = r13
            r0.savedLargeIcon = r9
            r0.savedContentTitle = r8
            r0.ringtone = r14
            r0.savedCallAudioState = r5
            if (r1 != 0) goto L_0x0214
            android.os.Trace.endSection()
            return
        L_0x0214:
            if (r9 == 0) goto L_0x0236
            android.content.Context r1 = r0.context
            android.content.res.Resources r1 = r1.getResources()
            r10 = 17104902(0x1050006, float:2.442826E-38)
            float r1 = r1.getDimension(r10)
            int r1 = (int) r1
            android.content.Context r10 = r0.context
            android.content.res.Resources r10 = r10.getResources()
            r12 = 17104901(0x1050005, float:2.4428256E-38)
            float r10 = r10.getDimension(r12)
            int r10 = (int) r10
            android.graphics.Bitmap r9 = android.support.p002v7.appcompat.R$style.getRoundedBitmap(r9, r10, r1)
        L_0x0236:
            android.app.Notification$Builder r1 = new android.app.Notification$Builder
            android.content.Context r10 = r0.context
            r1.<init>(r10)
            android.app.Notification$Builder r10 = r1.setSmallIcon(r6)
            android.content.Context r12 = r0.context
            com.android.dialer.theme.base.ThemeComponent r12 = com.android.dialer.theme.base.ThemeComponent.get(r12)
            com.android.dialer.theme.base.Theme r12 = r12.theme()
            com.android.dialer.theme.base.impl.AospThemeImpl r12 = (com.android.dialer.theme.base.impl.AospThemeImpl) r12
            int r12 = r12.getColorPrimary()
            android.app.Notification$Builder r10 = r10.setColor(r12)
            r12 = 0
            java.lang.CharSequence r12 = r0.getContentString(r3, r12)
            r10.setContentTitle(r12)
            r0.setNotificationWhen(r3, r4, r1)
            android.app.Notification$Builder r10 = new android.app.Notification$Builder
            android.content.Context r12 = r0.context
            r10.<init>(r12)
            r12 = 1
            r10.setOngoing(r12)
            r10.setOnlyAlertOnce(r12)
            r10.setPriority(r12)
            android.app.Notification r12 = r1.build()
            r10.setPublicVersion(r12)
            android.content.Context r12 = r0.context
            r13 = 0
            android.content.Intent r12 = com.android.incallui.InCallActivity.getIntent(r12, r13, r13, r13)
            android.content.Context r14 = r0.context
            android.app.PendingIntent r12 = android.app.PendingIntent.getActivity(r14, r13, r12, r13)
            r10.setContentIntent(r12)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r14 = "notificationType="
            r12.append(r14)
            r12.append(r11)
            java.lang.String r12 = r12.toString()
            java.lang.Object[] r13 = new java.lang.Object[r13]
            com.android.dialer.common.LogUtil.m9i(r2, r12, r13)
            java.lang.String r12 = "phone_ongoing_call"
            r13 = 1
            if (r11 == r13) goto L_0x02f6
            r1 = 2
            if (r11 == r1) goto L_0x02b1
            r1 = 3
            if (r11 == r1) goto L_0x02ab
            goto L_0x0302
        L_0x02ab:
            int r1 = android.os.Build.VERSION.SDK_INT
            r10.setChannelId(r12)
            goto L_0x0302
        L_0x02b1:
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.String r1 = "phone_incoming_call"
            r10.setChannelId(r1)
            android.content.Context r1 = r0.context
            r12 = 0
            r13 = 1
            android.content.Intent r1 = com.android.incallui.InCallActivity.getIntent(r1, r12, r12, r13)
            android.content.Context r14 = r0.context
            android.app.PendingIntent r1 = android.app.PendingIntent.getActivity(r14, r13, r1, r12)
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r14 = "setting fullScreenIntent: "
            r12.append(r14)
            r12.append(r1)
            r12.toString()
            r10.setFullScreenIntent(r1, r13)
            java.lang.String r1 = "call"
            r10.setCategory(r1)
            r1 = 2
            r10.setPriority(r1)
            int r12 = r0.currentNotification
            if (r12 == r1) goto L_0x0302
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r12 = "Canceling old notification so this one can be noisy"
            com.android.dialer.common.LogUtil.m9i(r2, r12, r1)
            com.android.incallui.call.TelecomAdapter r1 = com.android.incallui.call.TelecomAdapter.getInstance()
            r1.stopForegroundNotification()
            goto L_0x0302
        L_0x02f6:
            int r13 = android.os.Build.VERSION.SDK_INT
            r13 = 1
            r1.setColorized(r13)
            r10.setColorized(r13)
            r10.setChannelId(r12)
        L_0x0302:
            r1 = r16
            r10.setContentText(r1)
            r10.setSmallIcon(r6)
            r10.setContentTitle(r8)
            r10.setLargeIcon(r9)
            com.android.incallui.InCallPresenter r1 = com.android.incallui.InCallPresenter.getInstance()
            com.android.incallui.ThemeColorManager r1 = r1.getThemeColorManager()
            int r1 = r1.getPrimaryColor()
            r10.setColor(r1)
            r1 = 2131099833(0x7f0600b9, float:1.781203E38)
            r6 = 2131821143(0x7f110257, float:1.927502E38)
            r8 = 2131231078(0x7f080166, float:1.8078227E38)
            r9 = 2131099830(0x7f0600b6, float:1.7812024E38)
            if (r20 == 0) goto L_0x0385
            r5 = 0
            r10.setUsesChronometer(r5)
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r7 = "StatusBarNotifier.addDismissUpgradeRequestAction"
            java.lang.String r12 = "will show \"dismiss upgrade\" action in the incoming call Notification"
            com.android.dialer.common.LogUtil.m9i(r7, r12, r5)
            android.content.Context r5 = r0.context
            java.lang.String r7 = "com.android.incallui.ACTION_DECLINE_VIDEO_UPGRADE_REQUEST"
            android.app.PendingIntent r5 = createNotificationPendingIntent(r5, r7)
            android.app.Notification$Action$Builder r7 = new android.app.Notification$Action$Builder
            android.content.Context r12 = r0.context
            android.graphics.drawable.Icon r12 = android.graphics.drawable.Icon.createWithResource(r12, r8)
            android.text.Spannable r1 = r0.getActionText(r6, r1)
            r7.<init>(r12, r1, r5)
            android.app.Notification$Action r1 = r7.build()
            r10.addAction(r1)
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r5 = "StatusBarNotifier.addAcceptUpgradeRequestAction"
            java.lang.String r6 = "will show \"accept upgrade\" action in the incoming call Notification"
            com.android.dialer.common.LogUtil.m9i(r5, r6, r1)
            android.content.Context r1 = r0.context
            java.lang.String r5 = "com.android.incallui.ACTION_ACCEPT_VIDEO_UPGRADE_REQUEST"
            android.app.PendingIntent r1 = createNotificationPendingIntent(r1, r5)
            android.app.Notification$Action$Builder r5 = new android.app.Notification$Action$Builder
            android.content.Context r6 = r0.context
            android.graphics.drawable.Icon r6 = android.graphics.drawable.Icon.createWithResource(r6, r8)
            r7 = 2131821140(0x7f110254, float:1.9275015E38)
            android.text.Spannable r7 = r0.getActionText(r7, r9)
            r5.<init>(r6, r7, r1)
            android.app.Notification$Action r1 = r5.build()
            r10.addAction(r1)
            goto L_0x04d2
        L_0x0385:
            r0.setNotificationWhen(r3, r4, r10)
            r12 = 3
            if (r4 == r12) goto L_0x0448
            if (r4 == r7) goto L_0x0448
            boolean r12 = android.support.p002v7.appcompat.R$style.isDialing(r4)
            if (r12 == 0) goto L_0x0395
            goto L_0x0448
        L_0x0395:
            r5 = 4
            if (r4 == r5) goto L_0x039b
            r5 = 5
            if (r4 != r5) goto L_0x04d2
        L_0x039b:
            android.content.Context r5 = r0.context
            java.lang.String r7 = "com.android.incallui.ACTION_DECLINE_INCOMING_CALL"
            android.app.PendingIntent r5 = createNotificationPendingIntent(r5, r7)
            android.app.Notification$Action$Builder r7 = new android.app.Notification$Action$Builder
            android.content.Context r12 = r0.context
            r13 = 2131230996(0x7f080114, float:1.807806E38)
            android.graphics.drawable.Icon r12 = android.graphics.drawable.Icon.createWithResource(r12, r13)
            android.text.Spannable r1 = r0.getActionText(r6, r1)
            r7.<init>(r12, r1, r5)
            android.app.Notification$Action r1 = r7.build()
            r10.addAction(r1)
            boolean r1 = r3.isVideoCall()
            if (r1 == 0) goto L_0x03f2
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r5 = "StatusBarNotifier.addVideoCallAction"
            java.lang.String r6 = "will show \"video\" action in the incoming call Notification"
            com.android.dialer.common.LogUtil.m9i(r5, r6, r1)
            android.content.Context r1 = r0.context
            java.lang.String r5 = "com.android.incallui.ACTION_ANSWER_VIDEO_INCOMING_CALL"
            android.app.PendingIntent r1 = createNotificationPendingIntent(r1, r5)
            android.app.Notification$Action$Builder r5 = new android.app.Notification$Action$Builder
            android.content.Context r6 = r0.context
            android.graphics.drawable.Icon r6 = android.graphics.drawable.Icon.createWithResource(r6, r8)
            r7 = 2131821142(0x7f110256, float:1.9275019E38)
            r8 = 2131099831(0x7f0600b7, float:1.7812026E38)
            android.text.Spannable r7 = r0.getActionText(r7, r8)
            r5.<init>(r6, r7, r1)
            android.app.Notification$Action r1 = r5.build()
            r10.addAction(r1)
            goto L_0x04d2
        L_0x03f2:
            android.content.Context r1 = r0.context
            java.lang.String r5 = "com.android.incallui.ACTION_ANSWER_VOICE_INCOMING_CALL"
            android.app.PendingIntent r1 = createNotificationPendingIntent(r1, r5)
            android.app.Notification$Action$Builder r5 = new android.app.Notification$Action$Builder
            android.content.Context r6 = r0.context
            r7 = 2131230990(0x7f08010e, float:1.8078048E38)
            android.graphics.drawable.Icon r6 = android.graphics.drawable.Icon.createWithResource(r6, r7)
            r7 = 2131821141(0x7f110255, float:1.9275017E38)
            android.text.Spannable r7 = r0.getActionText(r7, r9)
            r5.<init>(r6, r7, r1)
            android.app.Notification$Action r1 = r5.build()
            r10.addAction(r1)
            boolean r1 = r3.isSpeakEasyEligible()
            if (r1 != 0) goto L_0x041e
            goto L_0x04d2
        L_0x041e:
            android.content.Context r1 = r0.context
            com.android.dialer.configprovider.ConfigProviderComponent r1 = com.android.dialer.configprovider.ConfigProviderComponent.get(r1)
            com.android.dialer.configprovider.ConfigProvider r1 = r1.getConfigProvider()
            com.android.dialer.configprovider.SharedPrefConfigProvider r1 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r1
            java.lang.String r5 = "enable_speakeasy_notification_button"
            r6 = 0
            boolean r1 = r1.getBoolean(r5, r6)
            if (r1 != 0) goto L_0x0435
            goto L_0x04d2
        L_0x0435:
            android.content.Context r1 = r0.context
            com.android.incallui.speakeasy.SpeakEasyComponent r1 = com.android.incallui.speakeasy.SpeakEasyComponent.get(r1)
            com.android.incallui.speakeasy.SpeakEasyCallManager r1 = r1.speakEasyCallManager()
            android.content.Context r5 = r0.context
            com.android.incallui.speakeasy.SpeakEasyCallManagerStub r1 = (com.android.incallui.speakeasy.SpeakEasyCallManagerStub) r1
            r1.isAvailable(r5)
            goto L_0x04d2
        L_0x0448:
            android.content.Context r1 = r0.context
            java.lang.String r6 = "com.android.incallui.ACTION_HANG_UP_ONGOING_CALL"
            android.app.PendingIntent r1 = createNotificationPendingIntent(r1, r6)
            android.app.Notification$Action$Builder r6 = new android.app.Notification$Action$Builder
            android.content.Context r8 = r0.context
            r9 = 2131230979(0x7f080103, float:1.8078026E38)
            android.graphics.drawable.Icon r8 = android.graphics.drawable.Icon.createWithResource(r8, r9)
            android.content.Context r9 = r0.context
            r12 = 2131821144(0x7f110258, float:1.9275023E38)
            java.lang.CharSequence r9 = r9.getText(r12)
            r6.<init>(r8, r9, r1)
            android.app.Notification$Action r1 = r6.build()
            r10.addAction(r1)
            int r1 = r5.getSupportedRouteMask()
            r6 = 2
            r1 = r1 & r6
            if (r1 != r6) goto L_0x0477
            goto L_0x04d2
        L_0x0477:
            int r1 = r5.getRoute()
            if (r1 != r7) goto L_0x04a4
            android.content.Context r1 = r0.context
            java.lang.String r5 = "com.android.incallui.ACTION_TURN_OFF_SPEAKER"
            android.app.PendingIntent r1 = createNotificationPendingIntent(r1, r5)
            android.app.Notification$Action$Builder r5 = new android.app.Notification$Action$Builder
            android.content.Context r6 = r0.context
            r7 = 2131231042(0x7f080142, float:1.8078154E38)
            android.graphics.drawable.Icon r6 = android.graphics.drawable.Icon.createWithResource(r6, r7)
            android.content.Context r7 = r0.context
            r8 = 2131821145(0x7f110259, float:1.9275025E38)
            java.lang.CharSequence r7 = r7.getText(r8)
            r5.<init>(r6, r7, r1)
            android.app.Notification$Action r1 = r5.build()
            r10.addAction(r1)
            goto L_0x04d2
        L_0x04a4:
            int r1 = r5.getRoute()
            r1 = r1 & 5
            if (r1 == 0) goto L_0x04d2
            android.content.Context r1 = r0.context
            java.lang.String r5 = "com.android.incallui.ACTION_TURN_ON_SPEAKER"
            android.app.PendingIntent r1 = createNotificationPendingIntent(r1, r5)
            android.app.Notification$Action$Builder r5 = new android.app.Notification$Action$Builder
            android.content.Context r6 = r0.context
            r7 = 2131231084(0x7f08016c, float:1.807824E38)
            android.graphics.drawable.Icon r6 = android.graphics.drawable.Icon.createWithResource(r6, r7)
            android.content.Context r7 = r0.context
            r8 = 2131821146(0x7f11025a, float:1.9275027E38)
            java.lang.CharSequence r7 = r7.getText(r8)
            r5.<init>(r6, r7, r1)
            android.app.Notification$Action r1 = r5.build()
            r10.addAction(r1)
        L_0x04d2:
            r1 = r21
            android.net.Uri r5 = r1.lookupUri
            if (r5 == 0) goto L_0x04e8
            long r6 = r1.userType
            r8 = 1
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x04e8
            java.lang.String r5 = r5.toString()
            r10.addPerson(r5)
            goto L_0x0504
        L_0x04e8:
            java.lang.String r5 = r3.getNumber()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x0504
            java.lang.String r5 = r3.getNumber()
            java.lang.String r6 = "tel"
            r7 = 0
            android.net.Uri r5 = android.net.Uri.fromParts(r6, r5, r7)
            java.lang.String r5 = r5.toString()
            r10.addPerson(r5)
        L_0x0504:
            java.lang.String r5 = "fire notification"
            android.os.Trace.beginSection(r5)
            android.app.Notification r5 = r10.build()
            com.android.incallui.ringtone.DialerRingtoneManager r6 = r0.dialerRingtoneManager
            android.net.Uri r7 = r1.contactRingtoneUri
            boolean r6 = r6.shouldPlayRingtone(r4, r7)
            if (r6 == 0) goto L_0x0546
            int r6 = r5.flags
            r6 = r6 | 4
            r5.flags = r6
            android.net.Uri r1 = r1.contactRingtoneUri
            r5.sound = r1
            android.media.AudioAttributes$Builder r1 = new android.media.AudioAttributes$Builder
            r1.<init>()
            r6 = 2
            r1.setContentType(r6)
            r6 = 6
            r1.setUsage(r6)
            android.media.AudioAttributes r1 = r1.build()
            r5.audioAttributes = r1
            com.android.incallui.ringtone.DialerRingtoneManager r1 = r0.dialerRingtoneManager
            android.content.Context r6 = r0.context
            android.content.ContentResolver r6 = r6.getContentResolver()
            boolean r1 = r1.shouldVibrate(r6)
            if (r1 == 0) goto L_0x0546
            long[] r1 = VIBRATE_PATTERN
            r5.vibrate = r1
        L_0x0546:
            com.android.incallui.ringtone.DialerRingtoneManager r1 = r0.dialerRingtoneManager
            boolean r1 = r1.shouldPlayCallWaitingTone(r4)
            if (r1 == 0) goto L_0x0553
            com.android.incallui.ringtone.DialerRingtoneManager r1 = r0.dialerRingtoneManager
            r1.playCallWaitingTone()
        L_0x0553:
            java.lang.String r1 = "displaying notification for "
            java.lang.String r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline5(r1, r11)
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]
            com.android.dialer.common.LogUtil.m9i(r2, r1, r4)
            com.android.incallui.call.TelecomAdapter r1 = com.android.incallui.call.TelecomAdapter.getInstance()
            r2 = 1
            r1.startForegroundNotification(r2, r5)
            android.os.Trace.endSection()
            com.android.incallui.latencyreport.LatencyReport r1 = r3.getLatencyReport()
            r1.onNotificationShown()
            r0.currentNotification = r11
            android.os.Trace.endSection()
            return
        L_0x0577:
            android.os.Trace.endSection()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.StatusBarNotifier.buildAndSendNotification(com.android.incallui.call.CallList, com.android.incallui.call.DialerCall, com.android.incallui.ContactInfoCache$ContactCacheEntry):void");
    }

    static void clearAllCallNotifications() {
        LogUtil.m8e("StatusBarNotifier.clearAllCallNotifications", "something terrible happened, clear all InCall notifications", new Object[0]);
        TelecomAdapter.getInstance().stopForegroundNotification();
    }

    private static PendingIntent createNotificationPendingIntent(Context context2, String str) {
        return PendingIntent.getBroadcast(context2, 0, new Intent(str, (Uri) null, context2, NotificationBroadcastReceiver.class), 0);
    }

    private Spannable getActionText(int i, int i2) {
        SpannableString spannableString = new SpannableString(this.context.getText(i));
        int i3 = Build.VERSION.SDK_INT;
        spannableString.setSpan(new ForegroundColorSpan(this.context.getColor(i2)), 0, spannableString.length(), 0);
        return spannableString;
    }

    private DialerCall getCallToShow(CallList callList) {
        if (callList == null) {
            return null;
        }
        DialerCall incomingCall = callList.getIncomingCall();
        if (incomingCall == null) {
            incomingCall = callList.getOutgoingCall();
        }
        if (incomingCall == null) {
            incomingCall = callList.getVideoUpgradeRequestCall();
        }
        return incomingCall == null ? callList.getActiveOrBackgroundCall() : incomingCall;
    }

    private CharSequence getContentString(DialerCall dialerCall, long j) {
        boolean z = dialerCall.getState() == 4 || dialerCall.getState() == 5;
        if (z && dialerCall.getNumberPresentation() == 1) {
            if (!TextUtils.isEmpty(dialerCall.getChildNumber())) {
                return this.context.getString(R.string.child_number, new Object[]{dialerCall.getChildNumber()});
            } else if (!TextUtils.isEmpty(dialerCall.getCallSubject()) && dialerCall.isCallSubjectSupported()) {
                return dialerCall.getCallSubject();
            }
        }
        String string = this.context.getString(R.string.notification_call_wifi_brand);
        int i = dialerCall.hasProperty(8) ? R.string.notification_ongoing_call_wifi_template : R.string.notification_ongoing_call;
        if (z) {
            if (dialerCall.isSpam()) {
                i = R.string.notification_incoming_spam_call;
            } else {
                dialerCall.getEnrichedCallSession();
                if (dialerCall.hasProperty(8)) {
                    i = R.string.notification_incoming_call_wifi_template;
                } else {
                    if (dialerCall.getAccountHandle() != null) {
                        if (dialerCall.getCallCapableAccounts() != null && dialerCall.getCallCapableAccounts().size() > 1) {
                            return getMultiSimIncomingText(dialerCall);
                        }
                    }
                    i = dialerCall.isVideoCall() ? R.string.notification_incoming_video_call : R.string.notification_incoming_call;
                }
            }
        } else if (dialerCall.getState() == 8) {
            i = R.string.notification_on_hold;
        } else if (R$style.isDialing(dialerCall.getState())) {
            i = R.string.notification_dialing;
        } else if (dialerCall.isVideoCall()) {
            i = dialerCall.getVideoTech().isPaused() ? R.string.notification_ongoing_paused_video_call : R.string.notification_ongoing_video_call;
        } else if (dialerCall.getVideoTech().getSessionModificationState() == 3) {
            i = R.string.notification_requesting_video_call;
        }
        boolean hasProperty = dialerCall.hasProperty(32);
        if (j == 1 || hasProperty) {
            if (i == R.string.notification_ongoing_call) {
                i = R.string.notification_ongoing_work_call;
            } else if (i == R.string.notification_incoming_call) {
                i = R.string.notification_incoming_work_call;
            }
            string = this.context.getString(R.string.notification_call_wifi_work_brand);
        }
        if (i != R.string.notification_incoming_call_wifi_template && i != R.string.notification_ongoing_call_wifi_template) {
            return this.context.getString(i);
        }
        return this.context.getString(i, new Object[]{string});
    }

    private CharSequence getMultiSimIncomingText(DialerCall dialerCall) {
        PhoneAccount phoneAccount = ((TelecomManager) this.context.getSystemService(TelecomManager.class)).getPhoneAccount(dialerCall.getAccountHandle());
        if (phoneAccount == null) {
            return this.context.getString(R.string.notification_incoming_call);
        }
        SpannableString spannableString = new SpannableString(this.context.getString(R.string.notification_incoming_call_mutli_sim, new Object[]{phoneAccount.getLabel()}));
        int lastIndexOf = spannableString.toString().lastIndexOf(phoneAccount.getLabel().toString());
        spannableString.setSpan(new ForegroundColorSpan(phoneAccount.getHighlightColor()), lastIndexOf, phoneAccount.getLabel().length() + lastIndexOf, 17);
        return spannableString;
    }

    private void setNotificationWhen(DialerCall dialerCall, int i, Notification.Builder builder) {
        if (i == 3) {
            builder.setUsesChronometer(true);
            builder.setWhen(dialerCall.getConnectTimeMillis());
            return;
        }
        builder.setUsesChronometer(false);
    }

    private void setStatusBarCallListener(StatusBarCallListener statusBarCallListener2) {
        StatusBarCallListener statusBarCallListener3 = this.statusBarCallListener;
        if (statusBarCallListener3 != null) {
            statusBarCallListener3.cleanup();
        }
        this.statusBarCallListener = statusBarCallListener2;
    }

    /* access modifiers changed from: package-private */
    public String getContentTitle(ContactInfoCache.ContactCacheEntry contactCacheEntry, DialerCall dialerCall) {
        if (dialerCall.isConferenceCall()) {
            return CallerInfoUtils.getConferenceString(this.context, dialerCall.hasProperty(2));
        }
        String displayName = ContactsComponent.get(this.context).contactDisplayPreferences().getDisplayName(contactCacheEntry.namePrimary, contactCacheEntry.nameAlternative);
        if (!TextUtils.isEmpty(displayName)) {
            return displayName;
        }
        if (TextUtils.isEmpty(contactCacheEntry.number)) {
            return null;
        }
        return BidiFormatter.getInstance().unicodeWrap(contactCacheEntry.number, TextDirectionHeuristics.LTR);
    }

    public int getIconToDisplay(DialerCall dialerCall) {
        if (dialerCall.getState() == 8) {
            return R.drawable.quantum_ic_phone_paused_vd_theme_24;
        }
        if (dialerCall.getVideoTech().getSessionModificationState() == 3 || dialerCall.isVideoCall()) {
            return R.drawable.quantum_ic_videocam_vd_white_24;
        }
        if (dialerCall.hasProperty(16) && MotorolaUtils.shouldShowHdIconInNotification(this.context)) {
            return R.drawable.ic_hd_call;
        }
        if (dialerCall.hasProperty(128)) {
            return R.drawable.quantum_ic_phone_locked_vd_theme_24;
        }
        return ReturnToCallController.isEnabled(this.context) ? R.drawable.quantum_ic_call_vd_theme_24 : R.drawable.on_going_call;
    }

    public void onContactInfoComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
        DialerCall callById = CallList.getInstance().getCallById(str);
        if (callById != null) {
            callById.getLogState().contactLookupResult = contactCacheEntry.contactLookupResult;
            buildAndSendNotification(CallList.getInstance(), callById, contactCacheEntry);
        }
    }

    public void onImageLoadComplete(String str, ContactInfoCache.ContactCacheEntry contactCacheEntry) {
        DialerCall callById = CallList.getInstance().getCallById(str);
        if (callById != null) {
            buildAndSendNotification(CallList.getInstance(), callById, contactCacheEntry);
        }
    }

    public void onStateChange(InCallPresenter.InCallState inCallState, InCallPresenter.InCallState inCallState2, CallList callList) {
        Object[] objArr = {inCallState, inCallState2};
        updateNotification();
    }

    public void updateNotification() {
        DialerCall callToShow = getCallToShow(CallList.getInstance());
        boolean z = false;
        if (callToShow != null) {
            Trace.beginSection("StatusBarNotifier.showNotification");
            if (callToShow.getState() == 4 || callToShow.getState() == 5) {
                z = true;
            }
            setStatusBarCallListener(new StatusBarCallListener(callToShow));
            this.contactInfoCache.findInfo(callToShow, z, this);
            Trace.endSection();
            return;
        }
        if (this.statusBarCallListener != null) {
            setStatusBarCallListener((StatusBarCallListener) null);
        }
        if (this.currentNotification != 0) {
            TelecomAdapter.getInstance().stopForegroundNotification();
            this.currentNotification = 0;
        }
    }
}
