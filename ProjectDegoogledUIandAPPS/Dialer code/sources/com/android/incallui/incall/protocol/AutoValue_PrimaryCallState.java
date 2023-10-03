package com.android.incallui.incall.protocol;

import android.graphics.drawable.Drawable;
import android.telecom.DisconnectCause;
import com.android.dialer.assisteddialing.TransformationInfo;
import com.android.dialer.preferredsim.suggestion.SuggestionProvider;
import com.android.incallui.incall.protocol.PrimaryCallState;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_PrimaryCallState extends PrimaryCallState {
    private final TransformationInfo assistedDialingExtras;
    private final String callSubject;
    private final String callbackNumber;
    private final long connectTimeMillis;
    private final Drawable connectionIcon;
    private final String connectionLabel;
    private final String customLabel;
    private final DisconnectCause disconnectCause;
    private final String gatewayNumber;
    private final boolean isAssistedDialed;
    private final boolean isBusinessNumber;
    private final boolean isConference;
    private final boolean isForwardedNumber;
    private final boolean isHdAttempting;
    private final boolean isHdAudioCall;
    private final boolean isRemotelyHeld;
    private final boolean isVideoCall;
    private final boolean isVoiceMailNumber;
    private final boolean isWifi;
    private final boolean isWorkCall;
    private final int primaryColor;
    private final int sessionModificationState;
    private final boolean shouldShowContactPhoto;
    private final SuggestionProvider.Reason simSuggestionReason;
    private final int state;
    private final boolean supportsCallOnHold;
    private final int swapToSecondaryButtonState;

    static final class Builder extends PrimaryCallState.Builder {
        private TransformationInfo assistedDialingExtras;
        private String callSubject;
        private String callbackNumber;
        private Long connectTimeMillis;
        private Drawable connectionIcon;
        private String connectionLabel;
        private String customLabel;
        private DisconnectCause disconnectCause;
        private String gatewayNumber;
        private Boolean isAssistedDialed;
        private Boolean isBusinessNumber;
        private Boolean isConference;
        private Boolean isForwardedNumber;
        private Boolean isHdAttempting;
        private Boolean isHdAudioCall;
        private Boolean isRemotelyHeld;
        private Boolean isVideoCall;
        private Boolean isVoiceMailNumber;
        private Boolean isWifi;
        private Boolean isWorkCall;
        private Integer primaryColor;
        private Integer sessionModificationState;
        private Boolean shouldShowContactPhoto;
        private SuggestionProvider.Reason simSuggestionReason;
        private Integer state;
        private Boolean supportsCallOnHold;
        private Integer swapToSecondaryButtonState;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public PrimaryCallState autoBuild() {
            String str = "";
            if (this.state == null) {
                str = GeneratedOutlineSupport.outline8(str, " state");
            }
            if (this.isVideoCall == null) {
                str = GeneratedOutlineSupport.outline8(str, " isVideoCall");
            }
            if (this.sessionModificationState == null) {
                str = GeneratedOutlineSupport.outline8(str, " sessionModificationState");
            }
            if (this.disconnectCause == null) {
                str = GeneratedOutlineSupport.outline8(str, " disconnectCause");
            }
            if (this.primaryColor == null) {
                str = GeneratedOutlineSupport.outline8(str, " primaryColor");
            }
            if (this.isWifi == null) {
                str = GeneratedOutlineSupport.outline8(str, " isWifi");
            }
            if (this.isConference == null) {
                str = GeneratedOutlineSupport.outline8(str, " isConference");
            }
            if (this.isWorkCall == null) {
                str = GeneratedOutlineSupport.outline8(str, " isWorkCall");
            }
            if (this.isHdAttempting == null) {
                str = GeneratedOutlineSupport.outline8(str, " isHdAttempting");
            }
            if (this.isHdAudioCall == null) {
                str = GeneratedOutlineSupport.outline8(str, " isHdAudioCall");
            }
            if (this.isForwardedNumber == null) {
                str = GeneratedOutlineSupport.outline8(str, " isForwardedNumber");
            }
            if (this.shouldShowContactPhoto == null) {
                str = GeneratedOutlineSupport.outline8(str, " shouldShowContactPhoto");
            }
            if (this.connectTimeMillis == null) {
                str = GeneratedOutlineSupport.outline8(str, " connectTimeMillis");
            }
            if (this.isVoiceMailNumber == null) {
                str = GeneratedOutlineSupport.outline8(str, " isVoiceMailNumber");
            }
            if (this.isRemotelyHeld == null) {
                str = GeneratedOutlineSupport.outline8(str, " isRemotelyHeld");
            }
            if (this.isBusinessNumber == null) {
                str = GeneratedOutlineSupport.outline8(str, " isBusinessNumber");
            }
            if (this.supportsCallOnHold == null) {
                str = GeneratedOutlineSupport.outline8(str, " supportsCallOnHold");
            }
            if (this.swapToSecondaryButtonState == null) {
                str = GeneratedOutlineSupport.outline8(str, " swapToSecondaryButtonState");
            }
            if (this.isAssistedDialed == null) {
                str = GeneratedOutlineSupport.outline8(str, " isAssistedDialed");
            }
            if (str.isEmpty()) {
                return new AutoValue_PrimaryCallState(this.state.intValue(), this.isVideoCall.booleanValue(), this.sessionModificationState.intValue(), this.disconnectCause, this.connectionLabel, this.primaryColor.intValue(), this.simSuggestionReason, this.connectionIcon, this.gatewayNumber, this.callSubject, this.callbackNumber, this.isWifi.booleanValue(), this.isConference.booleanValue(), this.isWorkCall.booleanValue(), this.isHdAttempting.booleanValue(), this.isHdAudioCall.booleanValue(), this.isForwardedNumber.booleanValue(), this.shouldShowContactPhoto.booleanValue(), this.connectTimeMillis.longValue(), this.isVoiceMailNumber.booleanValue(), this.isRemotelyHeld.booleanValue(), this.isBusinessNumber.booleanValue(), this.supportsCallOnHold.booleanValue(), this.swapToSecondaryButtonState.intValue(), this.isAssistedDialed.booleanValue(), this.customLabel, this.assistedDialingExtras, (C07201) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public PrimaryCallState.Builder setAssistedDialingExtras(TransformationInfo transformationInfo) {
            this.assistedDialingExtras = transformationInfo;
            return this;
        }

        public PrimaryCallState.Builder setCallSubject(String str) {
            this.callSubject = str;
            return this;
        }

        public PrimaryCallState.Builder setCallbackNumber(String str) {
            this.callbackNumber = str;
            return this;
        }

        public PrimaryCallState.Builder setConnectTimeMillis(long j) {
            this.connectTimeMillis = Long.valueOf(j);
            return this;
        }

        public PrimaryCallState.Builder setConnectionIcon(Drawable drawable) {
            this.connectionIcon = drawable;
            return this;
        }

        public PrimaryCallState.Builder setConnectionLabel(String str) {
            this.connectionLabel = str;
            return this;
        }

        public PrimaryCallState.Builder setCustomLabel(String str) {
            this.customLabel = str;
            return this;
        }

        public PrimaryCallState.Builder setDisconnectCause(DisconnectCause disconnectCause2) {
            if (disconnectCause2 != null) {
                this.disconnectCause = disconnectCause2;
                return this;
            }
            throw new NullPointerException("Null disconnectCause");
        }

        public PrimaryCallState.Builder setGatewayNumber(String str) {
            this.gatewayNumber = str;
            return this;
        }

        public PrimaryCallState.Builder setIsAssistedDialed(boolean z) {
            this.isAssistedDialed = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsBusinessNumber(boolean z) {
            this.isBusinessNumber = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsConference(boolean z) {
            this.isConference = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsForwardedNumber(boolean z) {
            this.isForwardedNumber = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsHdAttempting(boolean z) {
            this.isHdAttempting = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsHdAudioCall(boolean z) {
            this.isHdAudioCall = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsRemotelyHeld(boolean z) {
            this.isRemotelyHeld = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsVideoCall(boolean z) {
            this.isVideoCall = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsVoiceMailNumber(boolean z) {
            this.isVoiceMailNumber = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsWifi(boolean z) {
            this.isWifi = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setIsWorkCall(boolean z) {
            this.isWorkCall = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setPrimaryColor(int i) {
            this.primaryColor = Integer.valueOf(i);
            return this;
        }

        public PrimaryCallState.Builder setSessionModificationState(int i) {
            this.sessionModificationState = Integer.valueOf(i);
            return this;
        }

        public PrimaryCallState.Builder setShouldShowContactPhoto(boolean z) {
            this.shouldShowContactPhoto = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setSimSuggestionReason(SuggestionProvider.Reason reason) {
            this.simSuggestionReason = reason;
            return this;
        }

        public PrimaryCallState.Builder setState(int i) {
            this.state = Integer.valueOf(i);
            return this;
        }

        public PrimaryCallState.Builder setSupportsCallOnHold(boolean z) {
            this.supportsCallOnHold = Boolean.valueOf(z);
            return this;
        }

        public PrimaryCallState.Builder setSwapToSecondaryButtonState(int i) {
            this.swapToSecondaryButtonState = Integer.valueOf(i);
            return this;
        }
    }

    /* synthetic */ AutoValue_PrimaryCallState(int i, boolean z, int i2, DisconnectCause disconnectCause2, String str, int i3, SuggestionProvider.Reason reason, Drawable drawable, String str2, String str3, String str4, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, long j, boolean z9, boolean z10, boolean z11, boolean z12, int i4, boolean z13, String str5, TransformationInfo transformationInfo, C07201 r32) {
        this.state = i;
        this.isVideoCall = z;
        this.sessionModificationState = i2;
        this.disconnectCause = disconnectCause2;
        this.connectionLabel = str;
        this.primaryColor = i3;
        this.simSuggestionReason = reason;
        this.connectionIcon = drawable;
        this.gatewayNumber = str2;
        this.callSubject = str3;
        this.callbackNumber = str4;
        this.isWifi = z2;
        this.isConference = z3;
        this.isWorkCall = z4;
        this.isHdAttempting = z5;
        this.isHdAudioCall = z6;
        this.isForwardedNumber = z7;
        this.shouldShowContactPhoto = z8;
        this.connectTimeMillis = j;
        this.isVoiceMailNumber = z9;
        this.isRemotelyHeld = z10;
        this.isBusinessNumber = z11;
        this.supportsCallOnHold = z12;
        this.swapToSecondaryButtonState = i4;
        this.isAssistedDialed = z13;
        this.customLabel = str5;
        this.assistedDialingExtras = transformationInfo;
    }

    public TransformationInfo assistedDialingExtras() {
        return this.assistedDialingExtras;
    }

    public String callSubject() {
        return this.callSubject;
    }

    public String callbackNumber() {
        return this.callbackNumber;
    }

    public long connectTimeMillis() {
        return this.connectTimeMillis;
    }

    public Drawable connectionIcon() {
        return this.connectionIcon;
    }

    public String connectionLabel() {
        return this.connectionLabel;
    }

    public String customLabel() {
        return this.customLabel;
    }

    public DisconnectCause disconnectCause() {
        return this.disconnectCause;
    }

    public boolean equals(Object obj) {
        String str;
        SuggestionProvider.Reason reason;
        Drawable drawable;
        String str2;
        String str3;
        String str4;
        String str5;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrimaryCallState)) {
            return false;
        }
        PrimaryCallState primaryCallState = (PrimaryCallState) obj;
        if (this.state == ((AutoValue_PrimaryCallState) primaryCallState).state) {
            AutoValue_PrimaryCallState autoValue_PrimaryCallState = (AutoValue_PrimaryCallState) primaryCallState;
            if (this.isVideoCall == autoValue_PrimaryCallState.isVideoCall && this.sessionModificationState == autoValue_PrimaryCallState.sessionModificationState && this.disconnectCause.equals(primaryCallState.disconnectCause()) && ((str = this.connectionLabel) != null ? str.equals(autoValue_PrimaryCallState.connectionLabel) : autoValue_PrimaryCallState.connectionLabel == null) && this.primaryColor == autoValue_PrimaryCallState.primaryColor && ((reason = this.simSuggestionReason) != null ? reason.equals(autoValue_PrimaryCallState.simSuggestionReason) : autoValue_PrimaryCallState.simSuggestionReason == null) && ((drawable = this.connectionIcon) != null ? drawable.equals(autoValue_PrimaryCallState.connectionIcon) : autoValue_PrimaryCallState.connectionIcon == null) && ((str2 = this.gatewayNumber) != null ? str2.equals(autoValue_PrimaryCallState.gatewayNumber) : autoValue_PrimaryCallState.gatewayNumber == null) && ((str3 = this.callSubject) != null ? str3.equals(autoValue_PrimaryCallState.callSubject) : autoValue_PrimaryCallState.callSubject == null) && ((str4 = this.callbackNumber) != null ? str4.equals(autoValue_PrimaryCallState.callbackNumber) : autoValue_PrimaryCallState.callbackNumber == null) && this.isWifi == autoValue_PrimaryCallState.isWifi && this.isConference == autoValue_PrimaryCallState.isConference && this.isWorkCall == autoValue_PrimaryCallState.isWorkCall && this.isHdAttempting == primaryCallState.isHdAttempting() && this.isHdAudioCall == primaryCallState.isHdAudioCall() && this.isForwardedNumber == primaryCallState.isForwardedNumber() && this.shouldShowContactPhoto == autoValue_PrimaryCallState.shouldShowContactPhoto && this.connectTimeMillis == autoValue_PrimaryCallState.connectTimeMillis && this.isVoiceMailNumber == autoValue_PrimaryCallState.isVoiceMailNumber && this.isRemotelyHeld == primaryCallState.isRemotelyHeld() && this.isBusinessNumber == autoValue_PrimaryCallState.isBusinessNumber && this.supportsCallOnHold == primaryCallState.supportsCallOnHold() && this.swapToSecondaryButtonState == autoValue_PrimaryCallState.swapToSecondaryButtonState && this.isAssistedDialed == autoValue_PrimaryCallState.isAssistedDialed && ((str5 = this.customLabel) != null ? str5.equals(autoValue_PrimaryCallState.customLabel) : autoValue_PrimaryCallState.customLabel == null)) {
                TransformationInfo transformationInfo = this.assistedDialingExtras;
                if (transformationInfo == null) {
                    if (autoValue_PrimaryCallState.assistedDialingExtras == null) {
                        return true;
                    }
                } else if (transformationInfo.equals(autoValue_PrimaryCallState.assistedDialingExtras)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String gatewayNumber() {
        return this.gatewayNumber;
    }

    public int hashCode() {
        int i = 1231;
        int hashCode = (((((((this.state ^ 1000003) * 1000003) ^ (this.isVideoCall ? 1231 : 1237)) * 1000003) ^ this.sessionModificationState) * 1000003) ^ this.disconnectCause.hashCode()) * 1000003;
        String str = this.connectionLabel;
        int i2 = 0;
        int hashCode2 = (((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.primaryColor) * 1000003;
        SuggestionProvider.Reason reason = this.simSuggestionReason;
        int hashCode3 = (hashCode2 ^ (reason == null ? 0 : reason.hashCode())) * 1000003;
        Drawable drawable = this.connectionIcon;
        int hashCode4 = (hashCode3 ^ (drawable == null ? 0 : drawable.hashCode())) * 1000003;
        String str2 = this.gatewayNumber;
        int hashCode5 = (hashCode4 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.callSubject;
        int hashCode6 = (hashCode5 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.callbackNumber;
        int hashCode7 = (((((((((((((hashCode6 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003) ^ (this.isWifi ? 1231 : 1237)) * 1000003) ^ (this.isConference ? 1231 : 1237)) * 1000003) ^ (this.isWorkCall ? 1231 : 1237)) * 1000003) ^ (this.isHdAttempting ? 1231 : 1237)) * 1000003) ^ (this.isHdAudioCall ? 1231 : 1237)) * 1000003) ^ (this.isForwardedNumber ? 1231 : 1237)) * 1000003;
        int i3 = this.shouldShowContactPhoto ? 1231 : 1237;
        long j = this.connectTimeMillis;
        int i4 = (((((((((((((hashCode7 ^ i3) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ (this.isVoiceMailNumber ? 1231 : 1237)) * 1000003) ^ (this.isRemotelyHeld ? 1231 : 1237)) * 1000003) ^ (this.isBusinessNumber ? 1231 : 1237)) * 1000003) ^ (this.supportsCallOnHold ? 1231 : 1237)) * 1000003) ^ this.swapToSecondaryButtonState) * 1000003;
        if (!this.isAssistedDialed) {
            i = 1237;
        }
        int i5 = (i4 ^ i) * 1000003;
        String str5 = this.customLabel;
        int hashCode8 = (i5 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        TransformationInfo transformationInfo = this.assistedDialingExtras;
        if (transformationInfo != null) {
            i2 = transformationInfo.hashCode();
        }
        return hashCode8 ^ i2;
    }

    public boolean isAssistedDialed() {
        return this.isAssistedDialed;
    }

    public boolean isBusinessNumber() {
        return this.isBusinessNumber;
    }

    public boolean isConference() {
        return this.isConference;
    }

    public boolean isForwardedNumber() {
        return this.isForwardedNumber;
    }

    public boolean isHdAttempting() {
        return this.isHdAttempting;
    }

    public boolean isHdAudioCall() {
        return this.isHdAudioCall;
    }

    public boolean isRemotelyHeld() {
        return this.isRemotelyHeld;
    }

    public boolean isVideoCall() {
        return this.isVideoCall;
    }

    public boolean isVoiceMailNumber() {
        return this.isVoiceMailNumber;
    }

    public boolean isWifi() {
        return this.isWifi;
    }

    public boolean isWorkCall() {
        return this.isWorkCall;
    }

    public int sessionModificationState() {
        return this.sessionModificationState;
    }

    public SuggestionProvider.Reason simSuggestionReason() {
        return this.simSuggestionReason;
    }

    public int state() {
        return this.state;
    }

    public boolean supportsCallOnHold() {
        return this.supportsCallOnHold;
    }

    public int swapToSecondaryButtonState() {
        return this.swapToSecondaryButtonState;
    }
}
