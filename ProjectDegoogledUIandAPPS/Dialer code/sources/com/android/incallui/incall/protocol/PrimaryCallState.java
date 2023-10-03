package com.android.incallui.incall.protocol;

import android.graphics.drawable.Drawable;
import android.telecom.DisconnectCause;
import android.text.TextUtils;
import com.android.dialer.assisteddialing.TransformationInfo;
import com.android.dialer.common.Assert;
import com.android.dialer.preferredsim.suggestion.SuggestionProvider;
import com.android.incallui.incall.protocol.AutoValue_PrimaryCallState;
import com.google.auto.value.AutoValue;
import java.util.Locale;

@AutoValue
public abstract class PrimaryCallState {

    public static abstract class Builder {
        /* access modifiers changed from: package-private */
        public abstract PrimaryCallState autoBuild();

        public PrimaryCallState build() {
            PrimaryCallState autoBuild = autoBuild();
            if (!TextUtils.isEmpty(autoBuild.customLabel())) {
                Assert.checkArgument(autoBuild.state() == 16);
            }
            return autoBuild;
        }

        public abstract Builder setAssistedDialingExtras(TransformationInfo transformationInfo);

        public abstract Builder setCallSubject(String str);

        public abstract Builder setCallbackNumber(String str);

        public abstract Builder setConnectTimeMillis(long j);

        public abstract Builder setConnectionIcon(Drawable drawable);

        public abstract Builder setConnectionLabel(String str);

        public abstract Builder setCustomLabel(String str);

        public abstract Builder setDisconnectCause(DisconnectCause disconnectCause);

        public abstract Builder setGatewayNumber(String str);

        public abstract Builder setIsAssistedDialed(boolean z);

        public abstract Builder setIsBusinessNumber(boolean z);

        public abstract Builder setIsConference(boolean z);

        public abstract Builder setIsForwardedNumber(boolean z);

        public abstract Builder setIsHdAttempting(boolean z);

        public abstract Builder setIsHdAudioCall(boolean z);

        public abstract Builder setIsRemotelyHeld(boolean z);

        public abstract Builder setIsVideoCall(boolean z);

        public abstract Builder setIsVoiceMailNumber(boolean z);

        public abstract Builder setIsWifi(boolean z);

        public abstract Builder setIsWorkCall(boolean z);

        public abstract Builder setPrimaryColor(int i);

        public abstract Builder setSessionModificationState(int i);

        public abstract Builder setShouldShowContactPhoto(boolean z);

        public abstract Builder setSimSuggestionReason(SuggestionProvider.Reason reason);

        public abstract Builder setState(int i);

        public abstract Builder setSupportsCallOnHold(boolean z);

        public abstract Builder setSwapToSecondaryButtonState(int i);
    }

    public static Builder builder() {
        AutoValue_PrimaryCallState.Builder builder = new AutoValue_PrimaryCallState.Builder();
        builder.setState(2);
        builder.setIsVideoCall(false);
        builder.setSessionModificationState(0);
        builder.setDisconnectCause(new DisconnectCause(0));
        builder.setIsWifi(false);
        builder.setIsConference(false);
        builder.setIsWorkCall(false);
        builder.setIsHdAttempting(false);
        builder.setIsHdAudioCall(false);
        builder.setIsForwardedNumber(false);
        builder.setShouldShowContactPhoto(false);
        builder.setConnectTimeMillis(0);
        builder.setIsVoiceMailNumber(false);
        builder.setIsRemotelyHeld(false);
        builder.setIsBusinessNumber(false);
        builder.setSupportsCallOnHold(true);
        builder.setSwapToSecondaryButtonState(0);
        builder.setIsAssistedDialed(false);
        builder.setPrimaryColor(0);
        return builder;
    }

    public static PrimaryCallState empty() {
        return builder().build();
    }

    public abstract TransformationInfo assistedDialingExtras();

    public abstract String callSubject();

    public abstract String callbackNumber();

    public abstract long connectTimeMillis();

    public abstract Drawable connectionIcon();

    public abstract String connectionLabel();

    public abstract String customLabel();

    public abstract DisconnectCause disconnectCause();

    public abstract String gatewayNumber();

    public abstract boolean isAssistedDialed();

    public abstract boolean isBusinessNumber();

    public abstract boolean isConference();

    public abstract boolean isForwardedNumber();

    public abstract boolean isHdAttempting();

    public abstract boolean isHdAudioCall();

    public abstract boolean isRemotelyHeld();

    public abstract boolean isVideoCall();

    public abstract boolean isVoiceMailNumber();

    public abstract boolean isWifi();

    public abstract boolean isWorkCall();

    public abstract int sessionModificationState();

    public abstract SuggestionProvider.Reason simSuggestionReason();

    public abstract int state();

    public abstract boolean supportsCallOnHold();

    public abstract int swapToSecondaryButtonState();

    public String toString() {
        return String.format(Locale.US, "PrimaryCallState, state: %d, connectionLabel: %s", new Object[]{Integer.valueOf(state()), connectionLabel()});
    }
}
