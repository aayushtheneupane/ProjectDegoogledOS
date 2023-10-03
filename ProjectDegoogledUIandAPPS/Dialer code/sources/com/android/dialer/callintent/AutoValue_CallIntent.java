package com.android.dialer.callintent;

import android.net.Uri;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.callintent.CallIntent;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableMap;

final class AutoValue_CallIntent extends CallIntent {
    private final boolean allowAssistedDial;
    private final CallSpecificAppData callSpecificAppData;
    private final String callSubject;
    private final boolean isVideoCall;
    private final ImmutableMap<String, Long> longInCallUiIntentExtras;
    private final ImmutableMap<String, Long> longPlaceCallExtras;
    private final Uri number;
    private final PhoneAccountHandle phoneAccountHandle;
    private final ImmutableMap<String, String> stringInCallUiIntentExtras;
    private final ImmutableMap<String, String> stringPlaceCallExtras;

    static final class Builder extends CallIntent.Builder {
        private Boolean allowAssistedDial;
        private CallSpecificAppData callSpecificAppData;
        private String callSubject;
        private Boolean isVideoCall;
        private ImmutableMap<String, Long> longInCallUiIntentExtras;
        private ImmutableMap.Builder<String, Long> longInCallUiIntentExtrasBuilder$;
        private ImmutableMap<String, Long> longPlaceCallExtras;
        private ImmutableMap.Builder<String, Long> longPlaceCallExtrasBuilder$;
        private Uri number;
        private PhoneAccountHandle phoneAccountHandle;
        private ImmutableMap<String, String> stringInCallUiIntentExtras;
        private ImmutableMap.Builder<String, String> stringInCallUiIntentExtrasBuilder$;
        private ImmutableMap<String, String> stringPlaceCallExtras;
        private ImmutableMap.Builder<String, String> stringPlaceCallExtrasBuilder$;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public CallIntent autoBuild() {
            ImmutableMap.Builder<String, String> builder = this.stringInCallUiIntentExtrasBuilder$;
            if (builder != null) {
                this.stringInCallUiIntentExtras = builder.build();
            } else if (this.stringInCallUiIntentExtras == null) {
                this.stringInCallUiIntentExtras = ImmutableMap.m82of();
            }
            ImmutableMap.Builder<String, Long> builder2 = this.longInCallUiIntentExtrasBuilder$;
            if (builder2 != null) {
                this.longInCallUiIntentExtras = builder2.build();
            } else if (this.longInCallUiIntentExtras == null) {
                this.longInCallUiIntentExtras = ImmutableMap.m82of();
            }
            ImmutableMap.Builder<String, String> builder3 = this.stringPlaceCallExtrasBuilder$;
            if (builder3 != null) {
                this.stringPlaceCallExtras = builder3.build();
            } else if (this.stringPlaceCallExtras == null) {
                this.stringPlaceCallExtras = ImmutableMap.m82of();
            }
            ImmutableMap.Builder<String, Long> builder4 = this.longPlaceCallExtrasBuilder$;
            if (builder4 != null) {
                this.longPlaceCallExtras = builder4.build();
            } else if (this.longPlaceCallExtras == null) {
                this.longPlaceCallExtras = ImmutableMap.m82of();
            }
            String str = "";
            if (this.number == null) {
                str = GeneratedOutlineSupport.outline8(str, " number");
            }
            if (this.callSpecificAppData == null) {
                str = GeneratedOutlineSupport.outline8(str, " callSpecificAppData");
            }
            if (this.isVideoCall == null) {
                str = GeneratedOutlineSupport.outline8(str, " isVideoCall");
            }
            if (this.allowAssistedDial == null) {
                str = GeneratedOutlineSupport.outline8(str, " allowAssistedDial");
            }
            if (str.isEmpty()) {
                return new AutoValue_CallIntent(this.number, this.callSpecificAppData, this.phoneAccountHandle, this.isVideoCall.booleanValue(), this.callSubject, this.allowAssistedDial.booleanValue(), this.stringInCallUiIntentExtras, this.longInCallUiIntentExtras, this.stringPlaceCallExtras, this.longPlaceCallExtras, (C04181) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap.Builder<String, Long> longInCallUiIntentExtrasBuilder() {
            if (this.longInCallUiIntentExtrasBuilder$ == null) {
                if (this.longInCallUiIntentExtras == null) {
                    this.longInCallUiIntentExtrasBuilder$ = ImmutableMap.builder();
                } else {
                    this.longInCallUiIntentExtrasBuilder$ = ImmutableMap.builder();
                    this.longInCallUiIntentExtrasBuilder$.putAll(this.longInCallUiIntentExtras);
                    this.longInCallUiIntentExtras = null;
                }
            }
            return this.longInCallUiIntentExtrasBuilder$;
        }

        public CallIntent.Builder setAllowAssistedDial(boolean z) {
            this.allowAssistedDial = Boolean.valueOf(z);
            return this;
        }

        public CallIntent.Builder setCallSpecificAppData(CallSpecificAppData callSpecificAppData2) {
            if (callSpecificAppData2 != null) {
                this.callSpecificAppData = callSpecificAppData2;
                return this;
            }
            throw new NullPointerException("Null callSpecificAppData");
        }

        public CallIntent.Builder setCallSubject(String str) {
            this.callSubject = str;
            return this;
        }

        public CallIntent.Builder setIsVideoCall(boolean z) {
            this.isVideoCall = Boolean.valueOf(z);
            return this;
        }

        public CallIntent.Builder setNumber(Uri uri) {
            if (uri != null) {
                this.number = uri;
                return this;
            }
            throw new NullPointerException("Null number");
        }

        public CallIntent.Builder setPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle2) {
            this.phoneAccountHandle = phoneAccountHandle2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap.Builder<String, String> stringInCallUiIntentExtrasBuilder() {
            if (this.stringInCallUiIntentExtrasBuilder$ == null) {
                if (this.stringInCallUiIntentExtras == null) {
                    this.stringInCallUiIntentExtrasBuilder$ = ImmutableMap.builder();
                } else {
                    this.stringInCallUiIntentExtrasBuilder$ = ImmutableMap.builder();
                    this.stringInCallUiIntentExtrasBuilder$.putAll(this.stringInCallUiIntentExtras);
                    this.stringInCallUiIntentExtras = null;
                }
            }
            return this.stringInCallUiIntentExtrasBuilder$;
        }
    }

    /* synthetic */ AutoValue_CallIntent(Uri uri, CallSpecificAppData callSpecificAppData2, PhoneAccountHandle phoneAccountHandle2, boolean z, String str, boolean z2, ImmutableMap immutableMap, ImmutableMap immutableMap2, ImmutableMap immutableMap3, ImmutableMap immutableMap4, C04181 r11) {
        this.number = uri;
        this.callSpecificAppData = callSpecificAppData2;
        this.phoneAccountHandle = phoneAccountHandle2;
        this.isVideoCall = z;
        this.callSubject = str;
        this.allowAssistedDial = z2;
        this.stringInCallUiIntentExtras = immutableMap;
        this.longInCallUiIntentExtras = immutableMap2;
        this.stringPlaceCallExtras = immutableMap3;
        this.longPlaceCallExtras = immutableMap4;
    }

    /* access modifiers changed from: package-private */
    public boolean allowAssistedDial() {
        return this.allowAssistedDial;
    }

    /* access modifiers changed from: package-private */
    public CallSpecificAppData callSpecificAppData() {
        return this.callSpecificAppData;
    }

    /* access modifiers changed from: package-private */
    public String callSubject() {
        return this.callSubject;
    }

    public boolean equals(Object obj) {
        PhoneAccountHandle phoneAccountHandle2;
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CallIntent)) {
            return false;
        }
        CallIntent callIntent = (CallIntent) obj;
        if (!this.number.equals(callIntent.number()) || !this.callSpecificAppData.equals(callIntent.callSpecificAppData()) || ((phoneAccountHandle2 = this.phoneAccountHandle) != null ? !phoneAccountHandle2.equals(callIntent.phoneAccountHandle()) : callIntent.phoneAccountHandle() != null) || this.isVideoCall != callIntent.isVideoCall() || ((str = this.callSubject) != null ? !str.equals(callIntent.callSubject()) : callIntent.callSubject() != null) || this.allowAssistedDial != callIntent.allowAssistedDial() || !this.stringInCallUiIntentExtras.equals(callIntent.stringInCallUiIntentExtras()) || !this.longInCallUiIntentExtras.equals(callIntent.longInCallUiIntentExtras()) || !this.stringPlaceCallExtras.equals(callIntent.stringPlaceCallExtras()) || !this.longPlaceCallExtras.equals(callIntent.longPlaceCallExtras())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((this.number.hashCode() ^ 1000003) * 1000003) ^ this.callSpecificAppData.hashCode()) * 1000003;
        PhoneAccountHandle phoneAccountHandle2 = this.phoneAccountHandle;
        int i = 0;
        int i2 = 1231;
        int hashCode2 = (((hashCode ^ (phoneAccountHandle2 == null ? 0 : phoneAccountHandle2.hashCode())) * 1000003) ^ (this.isVideoCall ? 1231 : 1237)) * 1000003;
        String str = this.callSubject;
        if (str != null) {
            i = str.hashCode();
        }
        int i3 = (hashCode2 ^ i) * 1000003;
        if (!this.allowAssistedDial) {
            i2 = 1237;
        }
        return this.longPlaceCallExtras.hashCode() ^ ((((((((i3 ^ i2) * 1000003) ^ this.stringInCallUiIntentExtras.hashCode()) * 1000003) ^ this.longInCallUiIntentExtras.hashCode()) * 1000003) ^ this.stringPlaceCallExtras.hashCode()) * 1000003);
    }

    /* access modifiers changed from: package-private */
    public boolean isVideoCall() {
        return this.isVideoCall;
    }

    /* access modifiers changed from: package-private */
    public ImmutableMap<String, Long> longInCallUiIntentExtras() {
        return this.longInCallUiIntentExtras;
    }

    /* access modifiers changed from: package-private */
    public ImmutableMap<String, Long> longPlaceCallExtras() {
        return this.longPlaceCallExtras;
    }

    /* access modifiers changed from: package-private */
    public Uri number() {
        return this.number;
    }

    /* access modifiers changed from: package-private */
    public PhoneAccountHandle phoneAccountHandle() {
        return this.phoneAccountHandle;
    }

    /* access modifiers changed from: package-private */
    public ImmutableMap<String, String> stringInCallUiIntentExtras() {
        return this.stringInCallUiIntentExtras;
    }

    /* access modifiers changed from: package-private */
    public ImmutableMap<String, String> stringPlaceCallExtras() {
        return this.stringPlaceCallExtras;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CallIntent{number=");
        outline13.append(this.number);
        outline13.append(", callSpecificAppData=");
        outline13.append(this.callSpecificAppData);
        outline13.append(", phoneAccountHandle=");
        outline13.append(this.phoneAccountHandle);
        outline13.append(", isVideoCall=");
        outline13.append(this.isVideoCall);
        outline13.append(", callSubject=");
        outline13.append(this.callSubject);
        outline13.append(", allowAssistedDial=");
        outline13.append(this.allowAssistedDial);
        outline13.append(", stringInCallUiIntentExtras=");
        outline13.append(this.stringInCallUiIntentExtras);
        outline13.append(", longInCallUiIntentExtras=");
        outline13.append(this.longInCallUiIntentExtras);
        outline13.append(", stringPlaceCallExtras=");
        outline13.append(this.stringPlaceCallExtras);
        outline13.append(", longPlaceCallExtras=");
        return GeneratedOutlineSupport.outline11(outline13, this.longPlaceCallExtras, "}");
    }
}
