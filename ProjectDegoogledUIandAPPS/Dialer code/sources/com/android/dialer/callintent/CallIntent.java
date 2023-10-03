package com.android.dialer.callintent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.callintent.AutoValue_CallIntent;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.function.BiConsumer;

@AutoValue
public abstract class CallIntent implements Parcelable {
    public static final Parcelable.Creator<CallIntent> CREATOR = new Parcelable.Creator<CallIntent>() {
        public Object createFromParcel(Parcel parcel) {
            CallSpecificAppData callSpecificAppData;
            Builder builder = CallIntent.builder();
            ClassLoader classLoader = CallIntent.class.getClassLoader();
            builder.setNumber((Uri) parcel.readParcelable(classLoader));
            try {
                callSpecificAppData = CallSpecificAppData.parseFrom(parcel.createByteArray());
            } catch (InvalidProtocolBufferException unused) {
                callSpecificAppData = CallSpecificAppData.getDefaultInstance();
            }
            builder.setCallSpecificAppData(callSpecificAppData);
            builder.setPhoneAccountHandle((PhoneAccountHandle) parcel.readParcelable(classLoader));
            boolean z = true;
            builder.setIsVideoCall(parcel.readInt() != 0);
            builder.setCallSubject(parcel.readString());
            if (parcel.readInt() == 0) {
                z = false;
            }
            builder.setAllowAssistedDial(z);
            Bundle readBundle = parcel.readBundle(classLoader);
            for (String str : readBundle.keySet()) {
                builder.stringInCallUiIntentExtrasBuilder().put(str, readBundle.getString(str));
            }
            Bundle readBundle2 = parcel.readBundle(classLoader);
            for (String str2 : readBundle2.keySet()) {
                builder.longInCallUiIntentExtrasBuilder().put(str2, Long.valueOf(readBundle2.getLong(str2)));
            }
            return builder.autoBuild();
        }

        public Object[] newArray(int i) {
            return new CallIntent[0];
        }
    };
    private static int lightbringerButtonAppearInCollapsedCallLogItemCount;
    private static int lightbringerButtonAppearInExpandedCallLogItemCount;
    private static int lightbringerButtonAppearInSearchCount;

    public static abstract class Builder {
        /* access modifiers changed from: package-private */
        public abstract CallIntent autoBuild();

        /* access modifiers changed from: package-private */
        public abstract ImmutableMap.Builder<String, Long> longInCallUiIntentExtrasBuilder();

        public abstract Builder setAllowAssistedDial(boolean z);

        public abstract Builder setCallSpecificAppData(CallSpecificAppData callSpecificAppData);

        public abstract Builder setCallSubject(String str);

        public abstract Builder setIsVideoCall(boolean z);

        public abstract Builder setNumber(Uri uri);

        public abstract Builder setPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle);

        /* access modifiers changed from: package-private */
        public abstract ImmutableMap.Builder<String, String> stringInCallUiIntentExtrasBuilder();
    }

    public static Builder builder() {
        AutoValue_CallIntent.Builder builder = new AutoValue_CallIntent.Builder();
        builder.setIsVideoCall(false);
        builder.setAllowAssistedDial(false);
        return builder;
    }

    public static void clearLightbringerCounts() {
        lightbringerButtonAppearInCollapsedCallLogItemCount = 0;
        lightbringerButtonAppearInExpandedCallLogItemCount = 0;
        lightbringerButtonAppearInSearchCount = 0;
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

    /* access modifiers changed from: package-private */
    public abstract boolean allowAssistedDial();

    /* access modifiers changed from: package-private */
    public abstract CallSpecificAppData callSpecificAppData();

    /* access modifiers changed from: package-private */
    public abstract String callSubject();

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean isVideoCall();

    /* access modifiers changed from: package-private */
    public abstract ImmutableMap<String, Long> longInCallUiIntentExtras();

    /* access modifiers changed from: package-private */
    public abstract ImmutableMap<String, Long> longPlaceCallExtras();

    /* access modifiers changed from: package-private */
    public abstract Uri number();

    /* access modifiers changed from: package-private */
    public abstract PhoneAccountHandle phoneAccountHandle();

    /* access modifiers changed from: package-private */
    public abstract ImmutableMap<String, String> stringInCallUiIntentExtras();

    /* access modifiers changed from: package-private */
    public abstract ImmutableMap<String, String> stringPlaceCallExtras();

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(number(), i);
        parcel.writeByteArray(callSpecificAppData().toByteArray());
        parcel.writeParcelable(phoneAccountHandle(), i);
        parcel.writeInt(isVideoCall() ? 1 : 0);
        parcel.writeString(callSubject());
        parcel.writeInt(allowAssistedDial() ? 1 : 0);
        Bundle bundle = new Bundle();
        stringInCallUiIntentExtras().forEach(new BiConsumer(bundle) {
            private final /* synthetic */ Bundle f$0;

            {
                this.f$0 = r1;
            }

            public final void accept(Object obj, Object obj2) {
                this.f$0.putString((String) obj, (String) obj2);
            }
        });
        parcel.writeBundle(bundle);
        Bundle bundle2 = new Bundle();
        longInCallUiIntentExtras().forEach(new BiConsumer(bundle2) {
            private final /* synthetic */ Bundle f$0;

            {
                this.f$0 = r1;
            }

            public final void accept(Object obj, Object obj2) {
                this.f$0.putLong((String) obj, ((Long) obj2).longValue());
            }
        });
        parcel.writeBundle(bundle2);
    }
}
