package com.android.incallui.incall.protocol;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.android.dialer.common.LogUtil;
import com.android.dialer.multimedia.MultimediaData;
import com.android.incallui.incall.protocol.AutoValue_PrimaryInfo;
import com.google.auto.value.AutoValue;
import java.util.Locale;

@AutoValue
public abstract class PrimaryInfo {

    public static abstract class Builder {
        public abstract PrimaryInfo build();

        public abstract Builder setAnsweringDisconnectsOngoingCall(boolean z);

        public abstract Builder setContactInfoLookupKey(String str);

        public abstract Builder setIsContactPhotoShown(boolean z);

        public abstract Builder setIsLocalContact(boolean z);

        public abstract Builder setIsSipCall(boolean z);

        public abstract Builder setIsSpam(boolean z);

        public abstract Builder setIsWorkCall(boolean z);

        public abstract Builder setLabel(String str);

        public abstract Builder setLocation(String str);

        public abstract Builder setMultimediaData(MultimediaData multimediaData);

        public abstract Builder setName(String str);

        public abstract Builder setNameIsNumber(boolean z);

        public abstract Builder setNumber(String str);

        public abstract Builder setNumberPresentation(int i);

        public abstract Builder setPhoto(Drawable drawable);

        public abstract Builder setPhotoType(int i);

        public abstract Builder setPhotoUri(Uri uri);

        public abstract Builder setShouldShowLocation(boolean z);

        public abstract Builder setShowInCallButtonGrid(boolean z);
    }

    public static Builder builder() {
        return new AutoValue_PrimaryInfo.Builder();
    }

    public static PrimaryInfo empty() {
        AutoValue_PrimaryInfo.Builder builder = new AutoValue_PrimaryInfo.Builder();
        builder.setNameIsNumber(false);
        builder.setPhotoType(0);
        builder.setIsSipCall(false);
        builder.setIsContactPhotoShown(false);
        builder.setIsWorkCall(false);
        builder.setIsSpam(false);
        builder.setIsLocalContact(false);
        builder.setAnsweringDisconnectsOngoingCall(false);
        builder.setShouldShowLocation(false);
        builder.setShowInCallButtonGrid(true);
        builder.setNumberPresentation(-1);
        return builder.build();
    }

    public abstract boolean answeringDisconnectsOngoingCall();

    public abstract String contactInfoLookupKey();

    public abstract boolean isContactPhotoShown();

    public abstract boolean isLocalContact();

    public abstract boolean isSpam();

    public abstract String label();

    public abstract String location();

    public abstract MultimediaData multimediaData();

    public abstract String name();

    public abstract boolean nameIsNumber();

    public abstract String number();

    public abstract int numberPresentation();

    public abstract Drawable photo();

    public abstract int photoType();

    public abstract Uri photoUri();

    public abstract boolean shouldShowLocation();

    public abstract boolean showInCallButtonGrid();

    public String toString() {
        return String.format(Locale.US, "PrimaryInfo, number: %s, name: %s, location: %s, label: %s, photo: %s, photoType: %d, isPhotoVisible: %b, MultimediaData: %s", new Object[]{LogUtil.sanitizePhoneNumber(number()), LogUtil.sanitizePii(name()), LogUtil.sanitizePii(location()), label(), photo(), Integer.valueOf(photoType()), Boolean.valueOf(isContactPhotoShown()), multimediaData()});
    }
}
