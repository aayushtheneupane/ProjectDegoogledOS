package com.android.incallui.incall.protocol;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.android.dialer.multimedia.MultimediaData;
import com.android.incallui.incall.protocol.PrimaryInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_PrimaryInfo extends PrimaryInfo {
    private final boolean answeringDisconnectsOngoingCall;
    private final String contactInfoLookupKey;
    private final boolean isContactPhotoShown;
    private final boolean isLocalContact;
    private final boolean isSipCall;
    private final boolean isSpam;
    private final boolean isWorkCall;
    private final String label;
    private final String location;
    private final MultimediaData multimediaData;
    private final String name;
    private final boolean nameIsNumber;
    private final String number;
    private final int numberPresentation;
    private final Drawable photo;
    private final int photoType;
    private final Uri photoUri;
    private final boolean shouldShowLocation;
    private final boolean showInCallButtonGrid;

    static final class Builder extends PrimaryInfo.Builder {
        private Boolean answeringDisconnectsOngoingCall;
        private String contactInfoLookupKey;
        private Boolean isContactPhotoShown;
        private Boolean isLocalContact;
        private Boolean isSipCall;
        private Boolean isSpam;
        private Boolean isWorkCall;
        private String label;
        private String location;
        private MultimediaData multimediaData;
        private String name;
        private Boolean nameIsNumber;
        private String number;
        private Integer numberPresentation;
        private Drawable photo;
        private Integer photoType;
        private Uri photoUri;
        private Boolean shouldShowLocation;
        private Boolean showInCallButtonGrid;

        Builder() {
        }

        public PrimaryInfo build() {
            String str = "";
            if (this.nameIsNumber == null) {
                str = GeneratedOutlineSupport.outline8(str, " nameIsNumber");
            }
            if (this.photoType == null) {
                str = GeneratedOutlineSupport.outline8(str, " photoType");
            }
            if (this.isSipCall == null) {
                str = GeneratedOutlineSupport.outline8(str, " isSipCall");
            }
            if (this.isContactPhotoShown == null) {
                str = GeneratedOutlineSupport.outline8(str, " isContactPhotoShown");
            }
            if (this.isWorkCall == null) {
                str = GeneratedOutlineSupport.outline8(str, " isWorkCall");
            }
            if (this.isSpam == null) {
                str = GeneratedOutlineSupport.outline8(str, " isSpam");
            }
            if (this.isLocalContact == null) {
                str = GeneratedOutlineSupport.outline8(str, " isLocalContact");
            }
            if (this.answeringDisconnectsOngoingCall == null) {
                str = GeneratedOutlineSupport.outline8(str, " answeringDisconnectsOngoingCall");
            }
            if (this.shouldShowLocation == null) {
                str = GeneratedOutlineSupport.outline8(str, " shouldShowLocation");
            }
            if (this.showInCallButtonGrid == null) {
                str = GeneratedOutlineSupport.outline8(str, " showInCallButtonGrid");
            }
            if (this.numberPresentation == null) {
                str = GeneratedOutlineSupport.outline8(str, " numberPresentation");
            }
            if (str.isEmpty()) {
                return new AutoValue_PrimaryInfo(this.number, this.name, this.nameIsNumber.booleanValue(), this.label, this.location, this.photo, this.photoUri, this.photoType.intValue(), this.isSipCall.booleanValue(), this.isContactPhotoShown.booleanValue(), this.isWorkCall.booleanValue(), this.isSpam.booleanValue(), this.isLocalContact.booleanValue(), this.answeringDisconnectsOngoingCall.booleanValue(), this.shouldShowLocation.booleanValue(), this.contactInfoLookupKey, this.multimediaData, this.showInCallButtonGrid.booleanValue(), this.numberPresentation.intValue(), (C07211) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        public PrimaryInfo.Builder setAnsweringDisconnectsOngoingCall(boolean z) {
            this.answeringDisconnectsOngoingCall = Boolean.valueOf(z);
            return this;
        }

        public PrimaryInfo.Builder setContactInfoLookupKey(String str) {
            this.contactInfoLookupKey = str;
            return this;
        }

        public PrimaryInfo.Builder setIsContactPhotoShown(boolean z) {
            this.isContactPhotoShown = Boolean.valueOf(z);
            return this;
        }

        public PrimaryInfo.Builder setIsLocalContact(boolean z) {
            this.isLocalContact = Boolean.valueOf(z);
            return this;
        }

        public PrimaryInfo.Builder setIsSipCall(boolean z) {
            this.isSipCall = Boolean.valueOf(z);
            return this;
        }

        public PrimaryInfo.Builder setIsSpam(boolean z) {
            this.isSpam = Boolean.valueOf(z);
            return this;
        }

        public PrimaryInfo.Builder setIsWorkCall(boolean z) {
            this.isWorkCall = Boolean.valueOf(z);
            return this;
        }

        public PrimaryInfo.Builder setLabel(String str) {
            this.label = str;
            return this;
        }

        public PrimaryInfo.Builder setLocation(String str) {
            this.location = str;
            return this;
        }

        public PrimaryInfo.Builder setMultimediaData(MultimediaData multimediaData2) {
            this.multimediaData = multimediaData2;
            return this;
        }

        public PrimaryInfo.Builder setName(String str) {
            this.name = str;
            return this;
        }

        public PrimaryInfo.Builder setNameIsNumber(boolean z) {
            this.nameIsNumber = Boolean.valueOf(z);
            return this;
        }

        public PrimaryInfo.Builder setNumber(String str) {
            this.number = str;
            return this;
        }

        public PrimaryInfo.Builder setNumberPresentation(int i) {
            this.numberPresentation = Integer.valueOf(i);
            return this;
        }

        public PrimaryInfo.Builder setPhoto(Drawable drawable) {
            this.photo = drawable;
            return this;
        }

        public PrimaryInfo.Builder setPhotoType(int i) {
            this.photoType = Integer.valueOf(i);
            return this;
        }

        public PrimaryInfo.Builder setPhotoUri(Uri uri) {
            this.photoUri = uri;
            return this;
        }

        public PrimaryInfo.Builder setShouldShowLocation(boolean z) {
            this.shouldShowLocation = Boolean.valueOf(z);
            return this;
        }

        public PrimaryInfo.Builder setShowInCallButtonGrid(boolean z) {
            this.showInCallButtonGrid = Boolean.valueOf(z);
            return this;
        }
    }

    /* synthetic */ AutoValue_PrimaryInfo(String str, String str2, boolean z, String str3, String str4, Drawable drawable, Uri uri, int i, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, String str5, MultimediaData multimediaData2, boolean z9, int i2, C07211 r22) {
        this.number = str;
        this.name = str2;
        this.nameIsNumber = z;
        this.label = str3;
        this.location = str4;
        this.photo = drawable;
        this.photoUri = uri;
        this.photoType = i;
        this.isSipCall = z2;
        this.isContactPhotoShown = z3;
        this.isWorkCall = z4;
        this.isSpam = z5;
        this.isLocalContact = z6;
        this.answeringDisconnectsOngoingCall = z7;
        this.shouldShowLocation = z8;
        this.contactInfoLookupKey = str5;
        this.multimediaData = multimediaData2;
        this.showInCallButtonGrid = z9;
        this.numberPresentation = i2;
    }

    public boolean answeringDisconnectsOngoingCall() {
        return this.answeringDisconnectsOngoingCall;
    }

    public String contactInfoLookupKey() {
        return this.contactInfoLookupKey;
    }

    public boolean equals(Object obj) {
        String str;
        String str2;
        Drawable drawable;
        Uri uri;
        String str3;
        MultimediaData multimediaData2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PrimaryInfo)) {
            return false;
        }
        PrimaryInfo primaryInfo = (PrimaryInfo) obj;
        String str4 = this.number;
        if (str4 != null ? str4.equals(((AutoValue_PrimaryInfo) primaryInfo).number) : ((AutoValue_PrimaryInfo) primaryInfo).number == null) {
            String str5 = this.name;
            if (str5 != null ? str5.equals(((AutoValue_PrimaryInfo) primaryInfo).name) : ((AutoValue_PrimaryInfo) primaryInfo).name == null) {
                if (this.nameIsNumber == ((AutoValue_PrimaryInfo) primaryInfo).nameIsNumber && ((str = this.label) != null ? str.equals(((AutoValue_PrimaryInfo) primaryInfo).label) : ((AutoValue_PrimaryInfo) primaryInfo).label == null) && ((str2 = this.location) != null ? str2.equals(((AutoValue_PrimaryInfo) primaryInfo).location) : ((AutoValue_PrimaryInfo) primaryInfo).location == null) && ((drawable = this.photo) != null ? drawable.equals(((AutoValue_PrimaryInfo) primaryInfo).photo) : ((AutoValue_PrimaryInfo) primaryInfo).photo == null) && ((uri = this.photoUri) != null ? uri.equals(((AutoValue_PrimaryInfo) primaryInfo).photoUri) : ((AutoValue_PrimaryInfo) primaryInfo).photoUri == null)) {
                    AutoValue_PrimaryInfo autoValue_PrimaryInfo = (AutoValue_PrimaryInfo) primaryInfo;
                    if (this.photoType == autoValue_PrimaryInfo.photoType && this.isSipCall == autoValue_PrimaryInfo.isSipCall && this.isContactPhotoShown == primaryInfo.isContactPhotoShown() && this.isWorkCall == autoValue_PrimaryInfo.isWorkCall && this.isSpam == autoValue_PrimaryInfo.isSpam && this.isLocalContact == primaryInfo.isLocalContact() && this.answeringDisconnectsOngoingCall == primaryInfo.answeringDisconnectsOngoingCall() && this.shouldShowLocation == autoValue_PrimaryInfo.shouldShowLocation && ((str3 = this.contactInfoLookupKey) != null ? str3.equals(autoValue_PrimaryInfo.contactInfoLookupKey) : autoValue_PrimaryInfo.contactInfoLookupKey == null) && ((multimediaData2 = this.multimediaData) != null ? multimediaData2.equals(autoValue_PrimaryInfo.multimediaData) : autoValue_PrimaryInfo.multimediaData == null) && this.showInCallButtonGrid == primaryInfo.showInCallButtonGrid() && this.numberPresentation == autoValue_PrimaryInfo.numberPresentation) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.number;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.name;
        int i2 = 1231;
        int hashCode2 = (((hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003) ^ (this.nameIsNumber ? 1231 : 1237)) * 1000003;
        String str3 = this.label;
        int hashCode3 = (hashCode2 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.location;
        int hashCode4 = (hashCode3 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        Drawable drawable = this.photo;
        int hashCode5 = (hashCode4 ^ (drawable == null ? 0 : drawable.hashCode())) * 1000003;
        Uri uri = this.photoUri;
        int hashCode6 = (((((((((((((((((hashCode5 ^ (uri == null ? 0 : uri.hashCode())) * 1000003) ^ this.photoType) * 1000003) ^ (this.isSipCall ? 1231 : 1237)) * 1000003) ^ (this.isContactPhotoShown ? 1231 : 1237)) * 1000003) ^ (this.isWorkCall ? 1231 : 1237)) * 1000003) ^ (this.isSpam ? 1231 : 1237)) * 1000003) ^ (this.isLocalContact ? 1231 : 1237)) * 1000003) ^ (this.answeringDisconnectsOngoingCall ? 1231 : 1237)) * 1000003) ^ (this.shouldShowLocation ? 1231 : 1237)) * 1000003;
        String str5 = this.contactInfoLookupKey;
        int hashCode7 = (hashCode6 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        MultimediaData multimediaData2 = this.multimediaData;
        if (multimediaData2 != null) {
            i = multimediaData2.hashCode();
        }
        int i3 = (hashCode7 ^ i) * 1000003;
        if (!this.showInCallButtonGrid) {
            i2 = 1237;
        }
        return this.numberPresentation ^ ((i3 ^ i2) * 1000003);
    }

    public boolean isContactPhotoShown() {
        return this.isContactPhotoShown;
    }

    public boolean isLocalContact() {
        return this.isLocalContact;
    }

    public boolean isSpam() {
        return this.isSpam;
    }

    public String label() {
        return this.label;
    }

    public String location() {
        return this.location;
    }

    public MultimediaData multimediaData() {
        return this.multimediaData;
    }

    public String name() {
        return this.name;
    }

    public boolean nameIsNumber() {
        return this.nameIsNumber;
    }

    public String number() {
        return this.number;
    }

    public int numberPresentation() {
        return this.numberPresentation;
    }

    public Drawable photo() {
        return this.photo;
    }

    public int photoType() {
        return this.photoType;
    }

    public Uri photoUri() {
        return this.photoUri;
    }

    public boolean shouldShowLocation() {
        return this.shouldShowLocation;
    }

    public boolean showInCallButtonGrid() {
        return this.showInCallButtonGrid;
    }
}
