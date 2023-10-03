package com.android.dialer.callintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccountHandle;
import android.text.TextUtils;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.common.Assert;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.util.CallUtil;
import com.google.protobuf.InvalidProtocolBufferException;

public class CallIntentBuilder implements Parcelable {
    public static final Parcelable.Creator<CallIntentBuilder> CREATOR = new Parcelable.Creator<CallIntentBuilder>() {
        public Object createFromParcel(Parcel parcel) {
            return new CallIntentBuilder(parcel);
        }

        public Object[] newArray(int i) {
            return new CallIntentBuilder[0];
        }
    };
    private static int lightbringerButtonAppearInCollapsedCallLogItemCount;
    private static int lightbringerButtonAppearInExpandedCallLogItemCount;
    private static int lightbringerButtonAppearInSearchCount;
    private boolean allowAssistedDial;
    private final CallSpecificAppData callSpecificAppData;
    private String callSubject;
    private final Bundle inCallUiIntentExtras;
    private boolean isDuoCall;
    private boolean isVideoCall;
    private PhoneAccountHandle phoneAccountHandle;
    private final Bundle placeCallExtras;
    private Uri uri;

    public CallIntentBuilder(Uri uri2, CallSpecificAppData callSpecificAppData2) {
        this.inCallUiIntentExtras = new Bundle();
        this.placeCallExtras = new Bundle();
        Assert.isNotNull(uri2);
        this.uri = uri2;
        Assert.isNotNull(callSpecificAppData2);
        Assert.checkArgument(callSpecificAppData2.getCallInitiationType() != CallInitiationType$Type.UNKNOWN_INITIATION);
        CallSpecificAppData.Builder newBuilder = CallSpecificAppData.newBuilder(callSpecificAppData2);
        newBuilder.setLightbringerButtonAppearInExpandedCallLogItemCount(lightbringerButtonAppearInExpandedCallLogItemCount);
        newBuilder.setLightbringerButtonAppearInCollapsedCallLogItemCount(lightbringerButtonAppearInCollapsedCallLogItemCount);
        newBuilder.setLightbringerButtonAppearInSearchCount(lightbringerButtonAppearInSearchCount);
        lightbringerButtonAppearInExpandedCallLogItemCount = 0;
        lightbringerButtonAppearInCollapsedCallLogItemCount = 0;
        lightbringerButtonAppearInSearchCount = 0;
        if (PerformanceReport.isRecording()) {
            newBuilder.setTimeSinceAppLaunch(PerformanceReport.getTimeSinceAppLaunch());
            newBuilder.setTimeSinceFirstClick(PerformanceReport.getTimeSinceFirstClick());
            newBuilder.addAllUiActionsSinceAppLaunch(PerformanceReport.getActions());
            newBuilder.addAllUiActionTimestampsSinceAppLaunch(PerformanceReport.getActionTimestamps());
            newBuilder.setStartingTabIndex(PerformanceReport.getStartingTabIndex());
            newBuilder.build();
            PerformanceReport.stopRecording();
        }
        this.callSpecificAppData = (CallSpecificAppData) newBuilder.build();
    }

    public static void clearLightbringerCounts() {
        lightbringerButtonAppearInCollapsedCallLogItemCount = 0;
        lightbringerButtonAppearInExpandedCallLogItemCount = 0;
        lightbringerButtonAppearInSearchCount = 0;
    }

    public static CallIntentBuilder forVoicemail(PhoneAccountHandle phoneAccountHandle2, CallInitiationType$Type callInitiationType$Type) {
        return new CallIntentBuilder(Uri.fromParts("voicemail", "", (String) null), callInitiationType$Type).setPhoneAccountHandle(phoneAccountHandle2);
    }

    public static int getLightbringerButtonAppearInCollapsedCallLogItemCount() {
        return lightbringerButtonAppearInCollapsedCallLogItemCount;
    }

    public static int getLightbringerButtonAppearInExpandedCallLogItemCount() {
        return lightbringerButtonAppearInExpandedCallLogItemCount;
    }

    public static int getLightbringerButtonAppearInSearchCount() {
        return lightbringerButtonAppearInSearchCount;
    }

    @Deprecated
    public Intent build() {
        Intent intent = new Intent("android.intent.action.CALL", this.uri);
        intent.putExtra("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", this.isVideoCall ? 3 : 0);
        this.inCallUiIntentExtras.putLong("android.telecom.extra.CALL_CREATED_TIME_MILLIS", SystemClock.elapsedRealtime());
        R$style.putCallSpecificAppData(this.inCallUiIntentExtras, this.callSpecificAppData);
        intent.putExtra("android.telecom.extra.OUTGOING_CALL_EXTRAS", this.inCallUiIntentExtras);
        PhoneAccountHandle phoneAccountHandle2 = this.phoneAccountHandle;
        if (phoneAccountHandle2 != null) {
            intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle2);
        }
        if (!TextUtils.isEmpty(this.callSubject)) {
            intent.putExtra("android.telecom.extra.CALL_SUBJECT", this.callSubject);
        }
        intent.putExtras(this.placeCallExtras);
        return intent;
    }

    public int describeContents() {
        return 0;
    }

    public Bundle getInCallUiIntentExtras() {
        return this.inCallUiIntentExtras;
    }

    public PhoneAccountHandle getPhoneAccountHandle() {
        return this.phoneAccountHandle;
    }

    public Bundle getPlaceCallExtras() {
        return this.placeCallExtras;
    }

    public Uri getUri() {
        return this.uri;
    }

    public boolean isAssistedDialAllowed() {
        return this.allowAssistedDial;
    }

    public boolean isDuoCall() {
        return this.isDuoCall;
    }

    public boolean isVideoCall() {
        return this.isVideoCall;
    }

    public CallIntentBuilder setAllowAssistedDial(boolean z) {
        this.allowAssistedDial = z;
        return this;
    }

    public CallIntentBuilder setCallSubject(String str) {
        this.callSubject = str;
        return this;
    }

    public CallIntentBuilder setIsDuoCall(boolean z) {
        this.isDuoCall = z;
        return this;
    }

    public CallIntentBuilder setIsVideoCall(boolean z) {
        this.isVideoCall = z;
        return this;
    }

    public CallIntentBuilder setPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle2) {
        this.phoneAccountHandle = phoneAccountHandle2;
        return this;
    }

    public void setUri(Uri uri2) {
        Assert.isNotNull(uri2);
        this.uri = uri2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.uri, i);
        parcel.writeByteArray(this.callSpecificAppData.toByteArray());
        parcel.writeParcelable(this.phoneAccountHandle, i);
        parcel.writeInt(this.isVideoCall ? 1 : 0);
        parcel.writeInt(this.isDuoCall ? 1 : 0);
        parcel.writeString(this.callSubject);
        parcel.writeInt(this.allowAssistedDial ? 1 : 0);
        parcel.writeBundle(this.inCallUiIntentExtras);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CallIntentBuilder(String str, CallSpecificAppData callSpecificAppData2) {
        this(CallUtil.getCallUri(str), callSpecificAppData2);
        Assert.isNotNull(str);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public CallIntentBuilder(String str, CallInitiationType$Type callInitiationType$Type) {
        this(CallUtil.getCallUri(str), callInitiationType$Type);
        Assert.isNotNull(str);
    }

    public CallIntentBuilder(Parcel parcel) {
        CallSpecificAppData callSpecificAppData2;
        this.inCallUiIntentExtras = new Bundle();
        this.placeCallExtras = new Bundle();
        ClassLoader classLoader = CallIntentBuilder.class.getClassLoader();
        this.uri = (Uri) parcel.readParcelable(classLoader);
        try {
            callSpecificAppData2 = CallSpecificAppData.parseFrom(parcel.createByteArray());
        } catch (InvalidProtocolBufferException unused) {
            CallInitiationType$Type callInitiationType$Type = CallInitiationType$Type.UNKNOWN_INITIATION;
            CallSpecificAppData.Builder newBuilder = CallSpecificAppData.newBuilder();
            newBuilder.setCallInitiationType(callInitiationType$Type);
            callSpecificAppData2 = (CallSpecificAppData) newBuilder.build();
        }
        this.callSpecificAppData = callSpecificAppData2;
        this.phoneAccountHandle = (PhoneAccountHandle) parcel.readParcelable(classLoader);
        boolean z = true;
        this.isVideoCall = parcel.readInt() != 0;
        this.isDuoCall = parcel.readInt() != 0;
        this.callSubject = parcel.readString();
        this.allowAssistedDial = parcel.readInt() == 0 ? false : z;
        this.inCallUiIntentExtras.putAll(parcel.readBundle(classLoader));
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CallIntentBuilder(android.net.Uri r2, com.android.dialer.callintent.CallInitiationType$Type r3) {
        /*
            r1 = this;
            com.android.dialer.callintent.CallSpecificAppData$Builder r0 = com.android.dialer.callintent.CallSpecificAppData.newBuilder()
            r0.setCallInitiationType(r3)
            com.google.protobuf.GeneratedMessageLite r3 = r0.build()
            com.android.dialer.callintent.CallSpecificAppData r3 = (com.android.dialer.callintent.CallSpecificAppData) r3
            r1.<init>((android.net.Uri) r2, (com.android.dialer.callintent.CallSpecificAppData) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.callintent.CallIntentBuilder.<init>(android.net.Uri, com.android.dialer.callintent.CallInitiationType$Type):void");
    }
}
