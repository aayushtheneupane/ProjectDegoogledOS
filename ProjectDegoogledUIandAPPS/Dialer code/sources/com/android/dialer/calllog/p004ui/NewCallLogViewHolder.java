package com.android.dialer.calllog.p004ui;

import android.app.Activity;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.calllog.model.CoalescedRow;
import com.android.dialer.calllog.p004ui.NewCallLogAdapter;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.precall.PreCall;
import com.android.dialer.time.Clock;
import com.android.dialer.widget.ContactPhotoView;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import java.util.concurrent.ExecutorService;

/* renamed from: com.android.dialer.calllog.ui.NewCallLogViewHolder */
final class NewCallLogViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public final Activity activity;
    private final ImageView assistedDialIcon;
    private final ImageView callButton;
    private final TextView callCountTextView;
    private final View callLogEntryRootView;
    private final ImageView callTypeIcon;
    private final Clock clock;
    private final ContactPhotoView contactPhotoView;
    /* access modifiers changed from: private */
    public long currentRowId;
    private final ImageView hdIcon;
    private final TextView phoneAccountView;
    /* access modifiers changed from: private */
    public final NewCallLogAdapter.PopCounts popCounts;
    private final TextView primaryTextView;
    private final RealtimeRowProcessor realtimeRowProcessor;
    private final TextView secondaryTextView;
    private final ExecutorService uiExecutorService;
    private final ImageView wifiIcon;

    /* renamed from: com.android.dialer.calllog.ui.NewCallLogViewHolder$RealtimeRowFutureCallback */
    private class RealtimeRowFutureCallback implements FutureCallback<CoalescedRow> {
        private final CoalescedRow originalRow;

        RealtimeRowFutureCallback(CoalescedRow coalescedRow) {
            this.originalRow = coalescedRow;
        }

        public void onFailure(Throwable th) {
            throw new RuntimeException("realtime processing failed", th);
        }

        public void onSuccess(Object obj) {
            CoalescedRow coalescedRow = (CoalescedRow) obj;
            if (this.originalRow.getId() != NewCallLogViewHolder.this.currentRowId) {
                NewCallLogViewHolder.this.popCounts.didNotPop++;
            } else if (!coalescedRow.equals(this.originalRow)) {
                NewCallLogViewHolder.this.displayRow(coalescedRow);
                NewCallLogViewHolder.this.popCounts.popped++;
            } else {
                NewCallLogViewHolder.this.popCounts.didNotPop++;
            }
        }
    }

    NewCallLogViewHolder(Activity activity2, View view, Clock clock2, RealtimeRowProcessor realtimeRowProcessor2, NewCallLogAdapter.PopCounts popCounts2) {
        super(view);
        this.activity = activity2;
        this.callLogEntryRootView = view;
        this.contactPhotoView = (ContactPhotoView) view.findViewById(R.id.contact_photo_view);
        this.primaryTextView = (TextView) view.findViewById(R.id.primary_text);
        this.callCountTextView = (TextView) view.findViewById(R.id.call_count);
        this.secondaryTextView = (TextView) view.findViewById(R.id.secondary_text);
        this.callTypeIcon = (ImageView) view.findViewById(R.id.call_type_icon);
        this.hdIcon = (ImageView) view.findViewById(R.id.hd_icon);
        this.wifiIcon = (ImageView) view.findViewById(R.id.wifi_icon);
        this.assistedDialIcon = (ImageView) view.findViewById(R.id.assisted_dial_icon);
        this.phoneAccountView = (TextView) view.findViewById(R.id.phone_account);
        this.callButton = (ImageView) view.findViewById(R.id.call_button);
        this.clock = clock2;
        this.realtimeRowProcessor = realtimeRowProcessor2;
        this.popCounts = popCounts2;
        this.uiExecutorService = DialerExecutorComponent.get(activity2).uiExecutor();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x011d, code lost:
        if (r0 != 7) goto L_0x0133;
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void displayRow(com.android.dialer.calllog.model.CoalescedRow r8) {
        /*
            r7 = this;
            android.widget.TextView r0 = r7.primaryTextView
            android.app.Activity r1 = r7.activity
            java.lang.CharSequence r1 = com.android.dialer.calllogutils.CallLogDates.buildPrimaryText(r1, r8)
            r0.setText(r1)
            android.widget.TextView r0 = r7.secondaryTextView
            android.app.Activity r1 = r7.activity
            com.android.dialer.time.Clock r2 = r7.clock
            java.lang.CharSequence r1 = com.android.dialer.calllogutils.CallLogDates.buildSecondaryTextForEntries(r1, r2, r8)
            r0.setText(r1)
            boolean r0 = r7.isUnreadMissedCall(r8)
            if (r0 == 0) goto L_0x003c
            android.widget.TextView r0 = r7.primaryTextView
            r1 = 2131886715(0x7f12027b, float:1.9408017E38)
            r0.setTextAppearance(r1)
            android.widget.TextView r0 = r7.callCountTextView
            r0.setTextAppearance(r1)
            android.widget.TextView r0 = r7.secondaryTextView
            r1 = 2131886717(0x7f12027d, float:1.940802E38)
            r0.setTextAppearance(r1)
            android.widget.TextView r0 = r7.phoneAccountView
            r1 = 2131886713(0x7f120279, float:1.9408013E38)
            r0.setTextAppearance(r1)
            goto L_0x0059
        L_0x003c:
            android.widget.TextView r0 = r7.primaryTextView
            r1 = 2131886714(0x7f12027a, float:1.9408015E38)
            r0.setTextAppearance(r1)
            android.widget.TextView r0 = r7.callCountTextView
            r0.setTextAppearance(r1)
            android.widget.TextView r0 = r7.secondaryTextView
            r1 = 2131886716(0x7f12027c, float:1.9408019E38)
            r0.setTextAppearance(r1)
            android.widget.TextView r0 = r7.phoneAccountView
            r1 = 2131886712(0x7f120278, float:1.940801E38)
            r0.setTextAppearance(r1)
        L_0x0059:
            com.android.dialer.CoalescedIds r0 = r8.getCoalescedIds()
            int r0 = r0.getCoalescedIdCount()
            r1 = 1
            r2 = 0
            r3 = 8
            if (r0 <= r1) goto L_0x0084
            android.widget.TextView r4 = r7.callCountTextView
            java.util.Locale r5 = java.util.Locale.getDefault()
            java.lang.Object[] r6 = new java.lang.Object[r1]
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6[r2] = r0
            java.lang.String r0 = "(%d)"
            java.lang.String r0 = java.lang.String.format(r5, r0, r6)
            r4.setText(r0)
            android.widget.TextView r0 = r7.callCountTextView
            r0.setVisibility(r2)
            goto L_0x0089
        L_0x0084:
            android.widget.TextView r0 = r7.callCountTextView
            r0.setVisibility(r3)
        L_0x0089:
            com.android.dialer.widget.ContactPhotoView r0 = r7.contactPhotoView
            android.app.Activity r4 = r7.activity
            com.android.dialer.glidephotomanager.PhotoInfo$Builder r4 = com.android.dialer.calllogutils.CallLogDates.fromCoalescedRow(r4, r8)
            com.google.protobuf.GeneratedMessageLite r4 = r4.build()
            com.android.dialer.glidephotomanager.PhotoInfo r4 = (com.android.dialer.glidephotomanager.PhotoInfo) r4
            r0.setPhoto(r4)
            android.app.Activity r0 = r7.activity
            boolean r4 = r7.isUnreadMissedCall(r8)
            if (r4 == 0) goto L_0x00a6
            r4 = 2131099751(0x7f060067, float:1.7811864E38)
            goto L_0x00a9
        L_0x00a6:
            r4 = 2131099750(0x7f060066, float:1.7811862E38)
        L_0x00a9:
            int r0 = r0.getColor(r4)
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
            int r4 = r8.getFeatures()
            r5 = 4
            r4 = r4 & r5
            if (r4 != r5) goto L_0x00c4
            android.widget.ImageView r4 = r7.hdIcon
            r4.setVisibility(r2)
            android.widget.ImageView r4 = r7.hdIcon
            r4.setImageTintList(r0)
            goto L_0x00c9
        L_0x00c4:
            android.widget.ImageView r4 = r7.hdIcon
            r4.setVisibility(r3)
        L_0x00c9:
            android.app.Activity r4 = r7.activity
            int r6 = r8.getFeatures()
            boolean r4 = com.android.dialer.oem.MotorolaUtils.shouldShowWifiIconInCallLog(r4, r6)
            if (r4 == 0) goto L_0x00e0
            android.widget.ImageView r4 = r7.wifiIcon
            r4.setVisibility(r2)
            android.widget.ImageView r4 = r7.wifiIcon
            r4.setImageTintList(r0)
            goto L_0x00e5
        L_0x00e0:
            android.widget.ImageView r4 = r7.wifiIcon
            r4.setVisibility(r3)
        L_0x00e5:
            int r4 = r8.getFeatures()
            java.lang.Integer r6 = com.android.dialer.compat.telephony.TelephonyManagerCompat.FEATURES_ASSISTED_DIALING
            int r6 = r6.intValue()
            r4 = r4 & r6
            java.lang.Integer r6 = com.android.dialer.compat.telephony.TelephonyManagerCompat.FEATURES_ASSISTED_DIALING
            int r6 = r6.intValue()
            if (r4 != r6) goto L_0x0103
            android.widget.ImageView r4 = r7.assistedDialIcon
            r4.setVisibility(r2)
            android.widget.ImageView r4 = r7.assistedDialIcon
            r4.setImageTintList(r0)
            goto L_0x0108
        L_0x0103:
            android.widget.ImageView r0 = r7.assistedDialIcon
            r0.setVisibility(r3)
        L_0x0108:
            int r0 = r8.getCallType()
            r4 = 2131230984(0x7f080108, float:1.8078036E38)
            if (r0 == r1) goto L_0x0130
            r6 = 2
            if (r0 == r6) goto L_0x012c
            r6 = 3
            if (r0 == r6) goto L_0x0133
            if (r0 == r5) goto L_0x0124
            r5 = 6
            if (r0 == r5) goto L_0x0120
            r5 = 7
            if (r0 == r5) goto L_0x0130
            goto L_0x0133
        L_0x0120:
            r4 = 2131230973(0x7f0800fd, float:1.8078014E38)
            goto L_0x0133
        L_0x0124:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Voicemails not expected in call log"
            r7.<init>(r8)
            throw r7
        L_0x012c:
            r4 = 2131230981(0x7f080105, float:1.807803E38)
            goto L_0x0133
        L_0x0130:
            r4 = 2131230986(0x7f08010a, float:1.807804E38)
        L_0x0133:
            android.widget.ImageView r0 = r7.callTypeIcon
            r0.setImageResource(r4)
            boolean r0 = r7.isUnreadMissedCall(r8)
            if (r0 == 0) goto L_0x0151
            android.widget.ImageView r0 = r7.callTypeIcon
            android.app.Activity r4 = r7.activity
            r5 = 2131099703(0x7f060037, float:1.7811767E38)
            int r4 = r4.getColor(r5)
            android.content.res.ColorStateList r4 = android.content.res.ColorStateList.valueOf(r4)
            r0.setImageTintList(r4)
            goto L_0x0163
        L_0x0151:
            android.widget.ImageView r0 = r7.callTypeIcon
            android.app.Activity r4 = r7.activity
            r5 = 2131099702(0x7f060036, float:1.7811765E38)
            int r4 = r4.getColor(r5)
            android.content.res.ColorStateList r4 = android.content.res.ColorStateList.valueOf(r4)
            r0.setImageTintList(r4)
        L_0x0163:
            java.lang.String r0 = r8.getPhoneAccountComponentName()
            java.lang.String r4 = r8.getPhoneAccountId()
            android.telecom.PhoneAccountHandle r0 = com.android.dialer.telecom.TelecomUtil.composePhoneAccountHandle(r0, r4)
            if (r0 != 0) goto L_0x0177
            android.widget.TextView r0 = r7.phoneAccountView
            r0.setVisibility(r3)
            goto L_0x01b3
        L_0x0177:
            android.app.Activity r4 = r7.activity
            java.lang.String r4 = com.android.dialer.calllogutils.CallLogDates.getAccountLabel(r4, r0)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x0189
            android.widget.TextView r0 = r7.phoneAccountView
            r0.setVisibility(r3)
            goto L_0x01b3
        L_0x0189:
            android.app.Activity r5 = r7.activity
            int r0 = com.android.dialer.calllogutils.CallLogDates.getAccountColor(r5, r0)
            if (r0 != 0) goto L_0x01a4
            android.app.Activity r0 = r7.activity
            android.content.res.Resources r0 = r0.getResources()
            r5 = 2131099738(0x7f06005a, float:1.7811838E38)
            android.app.Activity r6 = r7.activity
            android.content.res.Resources$Theme r6 = r6.getTheme()
            int r0 = r0.getColor(r5, r6)
        L_0x01a4:
            android.widget.TextView r5 = r7.phoneAccountView
            r5.setText(r4)
            android.widget.TextView r4 = r7.phoneAccountView
            r4.setTextColor(r0)
            android.widget.TextView r0 = r7.phoneAccountView
            r0.setVisibility(r2)
        L_0x01b3:
            com.android.dialer.DialerPhoneNumber r0 = r8.getNumber()
            java.lang.String r0 = r0.getNormalizedNumber()
            int r4 = r8.getNumberPresentation()
            boolean r0 = com.android.dialer.phonenumberutil.PhoneNumberHelper.canPlaceCallsTo(r0, r4)
            if (r0 != 0) goto L_0x01cb
            android.widget.ImageView r0 = r7.callButton
            r0.setVisibility(r3)
            goto L_0x0232
        L_0x01cb:
            android.widget.ImageView r0 = r7.callButton
            r0.setVisibility(r2)
            int r0 = r8.getFeatures()
            r0 = r0 & r1
            if (r0 != r1) goto L_0x0200
            android.widget.ImageView r0 = r7.callButton
            r3 = 2131231077(0x7f080165, float:1.8078225E38)
            r0.setImageResource(r3)
            android.widget.ImageView r0 = r7.callButton
            android.app.Activity r3 = r7.activity
            android.content.res.Resources r3 = r3.getResources()
            r4 = 2131820562(0x7f110012, float:1.9273842E38)
            java.lang.CharSequence r3 = r3.getText(r4)
            java.lang.CharSequence[] r1 = new java.lang.CharSequence[r1]
            android.app.Activity r4 = r7.activity
            java.lang.CharSequence r4 = com.android.dialer.calllogutils.CallLogDates.buildPrimaryText(r4, r8)
            r1[r2] = r4
            java.lang.CharSequence r1 = android.text.TextUtils.expandTemplate(r3, r1)
            r0.setContentDescription(r1)
            goto L_0x0228
        L_0x0200:
            android.widget.ImageView r0 = r7.callButton
            r3 = 2131230988(0x7f08010c, float:1.8078044E38)
            r0.setImageResource(r3)
            android.widget.ImageView r0 = r7.callButton
            android.app.Activity r3 = r7.activity
            android.content.res.Resources r3 = r3.getResources()
            r4 = 2131820563(0x7f110013, float:1.9273844E38)
            java.lang.CharSequence r3 = r3.getText(r4)
            java.lang.CharSequence[] r1 = new java.lang.CharSequence[r1]
            android.app.Activity r4 = r7.activity
            java.lang.CharSequence r4 = com.android.dialer.calllogutils.CallLogDates.buildPrimaryText(r4, r8)
            r1[r2] = r4
            java.lang.CharSequence r1 = android.text.TextUtils.expandTemplate(r3, r1)
            r0.setContentDescription(r1)
        L_0x0228:
            android.widget.ImageView r0 = r7.callButton
            com.android.dialer.calllog.ui.-$$Lambda$NewCallLogViewHolder$pvTcfyttjDO9Xp_toenUroEQi14 r1 = new com.android.dialer.calllog.ui.-$$Lambda$NewCallLogViewHolder$pvTcfyttjDO9Xp_toenUroEQi14
            r1.<init>(r8)
            r0.setOnClickListener(r1)
        L_0x0232:
            android.view.View r0 = r7.itemView
            android.app.Activity r7 = r7.activity
            com.android.dialer.calllog.ui.menu.-$$Lambda$NewCallLogMenu$xlO-BwSS-tKEoqwyjerx-qRBfF8 r1 = new com.android.dialer.calllog.ui.menu.-$$Lambda$NewCallLogMenu$xlO-BwSS-tKEoqwyjerx-qRBfF8
            r1.<init>(r7, r8)
            r0.setOnClickListener(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calllog.p004ui.NewCallLogViewHolder.displayRow(com.android.dialer.calllog.model.CoalescedRow):void");
    }

    private boolean isUnreadMissedCall(CoalescedRow coalescedRow) {
        return coalescedRow.getCallType() == 3 && !coalescedRow.getIsRead();
    }

    /* access modifiers changed from: package-private */
    public void bind(CoalescedRow coalescedRow) {
        this.currentRowId = coalescedRow.getId();
        displayRow(coalescedRow);
        this.callLogEntryRootView.setContentDescription(CallLogDates.buildDescriptionForEntry(this.activity, this.clock, coalescedRow));
        this.callLogEntryRootView.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, NewCallLogViewHolder.this.activity.getResources().getString(R.string.a11y_new_call_log_entry_tap_action)));
            }
        });
        Futures.addCallback(this.realtimeRowProcessor.applyRealtimeProcessing(coalescedRow), new RealtimeRowFutureCallback(coalescedRow), this.uiExecutorService);
    }

    public /* synthetic */ void lambda$setCallButon$0$NewCallLogViewHolder(CoalescedRow coalescedRow, View view) {
        Activity activity2 = this.activity;
        CallIntentBuilder callIntentBuilder = new CallIntentBuilder(coalescedRow.getNumber().getNormalizedNumber(), CallInitiationType$Type.CALL_LOG);
        boolean z = true;
        if ((coalescedRow.getFeatures() & 1) != 1) {
            z = false;
        }
        CallIntentBuilder isVideoCall = callIntentBuilder.setIsVideoCall(z);
        ((DuoStub) DuoComponent.get(activity2).getDuo()).isDuoAccount(coalescedRow.getPhoneAccountComponentName());
        PreCall.start(activity2, isVideoCall.setIsDuoCall(false));
    }
}
