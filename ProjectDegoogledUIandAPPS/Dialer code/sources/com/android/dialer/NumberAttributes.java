package com.android.dialer;

import com.android.dialer.logging.ContactSource$Type;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class NumberAttributes extends GeneratedMessageLite<NumberAttributes, Builder> implements NumberAttributesOrBuilder {
    /* access modifiers changed from: private */
    public static final NumberAttributes DEFAULT_INSTANCE = new NumberAttributes();
    private static volatile Parser<NumberAttributes> PARSER;
    private int bitField0_;
    private boolean canReportAsInvalidNumber_ = false;
    private boolean canSupportCarrierVideoCall_ = false;
    private int contactSource_ = 0;
    private String geolocation_ = "";
    private boolean isBlocked_ = false;
    private boolean isBusiness_ = false;
    private boolean isCp2InfoIncomplete_ = false;
    private boolean isEmergencyNumber_ = false;
    private boolean isSpam_ = false;
    private String lookupUri_ = "";
    private String name_ = "";
    private String numberTypeLabel_ = "";
    private long photoId_ = 0;
    private String photoUri_ = "";

    /* renamed from: com.android.dialer.NumberAttributes$1 */
    static /* synthetic */ class C02781 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f10xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|(3:15|16|18)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f10xa1df5c61 = r0
                int[] r0 = f10xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f10xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f10xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f10xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f10xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f10xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f10xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f10xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.NumberAttributes.C02781.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NumberAttributes, Builder> implements NumberAttributesOrBuilder {
        /* synthetic */ Builder(C02781 r1) {
            this();
        }

        public Builder setCanReportAsInvalidNumber(boolean z) {
            copyOnWrite();
            NumberAttributes.access$1700((NumberAttributes) this.instance, z);
            return this;
        }

        public Builder setCanSupportCarrierVideoCall(boolean z) {
            copyOnWrite();
            NumberAttributes.access$2700((NumberAttributes) this.instance, z);
            return this;
        }

        public Builder setContactSource(ContactSource$Type contactSource$Type) {
            copyOnWrite();
            NumberAttributes.access$2500((NumberAttributes) this.instance, contactSource$Type);
            return this;
        }

        public Builder setGeolocation(String str) {
            copyOnWrite();
            NumberAttributes.access$2900((NumberAttributes) this.instance, str);
            return this;
        }

        public Builder setIsBlocked(boolean z) {
            copyOnWrite();
            NumberAttributes.access$2100((NumberAttributes) this.instance, z);
            return this;
        }

        public Builder setIsBusiness(boolean z) {
            copyOnWrite();
            NumberAttributes.access$1500((NumberAttributes) this.instance, z);
            return this;
        }

        public Builder setIsCp2InfoIncomplete(boolean z) {
            copyOnWrite();
            NumberAttributes.access$1900((NumberAttributes) this.instance, z);
            return this;
        }

        public Builder setIsEmergencyNumber(boolean z) {
            copyOnWrite();
            NumberAttributes.access$3200((NumberAttributes) this.instance, z);
            return this;
        }

        public Builder setIsSpam(boolean z) {
            copyOnWrite();
            NumberAttributes.access$2300((NumberAttributes) this.instance, z);
            return this;
        }

        public Builder setLookupUri(String str) {
            copyOnWrite();
            NumberAttributes.access$900((NumberAttributes) this.instance, str);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            NumberAttributes.access$100((NumberAttributes) this.instance, str);
            return this;
        }

        public Builder setNumberTypeLabel(String str) {
            copyOnWrite();
            NumberAttributes.access$1200((NumberAttributes) this.instance, str);
            return this;
        }

        public Builder setPhotoId(long j) {
            copyOnWrite();
            NumberAttributes.access$700((NumberAttributes) this.instance, j);
            return this;
        }

        public Builder setPhotoUri(String str) {
            copyOnWrite();
            NumberAttributes.access$400((NumberAttributes) this.instance, str);
            return this;
        }

        private Builder() {
            super(NumberAttributes.DEFAULT_INSTANCE);
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private NumberAttributes() {
    }

    static /* synthetic */ void access$100(NumberAttributes numberAttributes, String str) {
        if (str != null) {
            numberAttributes.bitField0_ |= 1;
            numberAttributes.name_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1200(NumberAttributes numberAttributes, String str) {
        if (str != null) {
            numberAttributes.bitField0_ |= 16;
            numberAttributes.numberTypeLabel_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1500(NumberAttributes numberAttributes, boolean z) {
        numberAttributes.bitField0_ |= 32;
        numberAttributes.isBusiness_ = z;
    }

    static /* synthetic */ void access$1700(NumberAttributes numberAttributes, boolean z) {
        numberAttributes.bitField0_ |= 64;
        numberAttributes.canReportAsInvalidNumber_ = z;
    }

    static /* synthetic */ void access$1900(NumberAttributes numberAttributes, boolean z) {
        numberAttributes.bitField0_ |= 128;
        numberAttributes.isCp2InfoIncomplete_ = z;
    }

    static /* synthetic */ void access$2100(NumberAttributes numberAttributes, boolean z) {
        numberAttributes.bitField0_ |= 256;
        numberAttributes.isBlocked_ = z;
    }

    static /* synthetic */ void access$2300(NumberAttributes numberAttributes, boolean z) {
        numberAttributes.bitField0_ |= 512;
        numberAttributes.isSpam_ = z;
    }

    static /* synthetic */ void access$2500(NumberAttributes numberAttributes, ContactSource$Type contactSource$Type) {
        if (contactSource$Type != null) {
            numberAttributes.bitField0_ |= 1024;
            numberAttributes.contactSource_ = contactSource$Type.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2700(NumberAttributes numberAttributes, boolean z) {
        numberAttributes.bitField0_ |= 2048;
        numberAttributes.canSupportCarrierVideoCall_ = z;
    }

    static /* synthetic */ void access$2900(NumberAttributes numberAttributes, String str) {
        if (str != null) {
            numberAttributes.bitField0_ |= 4096;
            numberAttributes.geolocation_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$3200(NumberAttributes numberAttributes, boolean z) {
        numberAttributes.bitField0_ |= 8192;
        numberAttributes.isEmergencyNumber_ = z;
    }

    static /* synthetic */ void access$400(NumberAttributes numberAttributes, String str) {
        if (str != null) {
            numberAttributes.bitField0_ |= 2;
            numberAttributes.photoUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$700(NumberAttributes numberAttributes, long j) {
        numberAttributes.bitField0_ |= 4;
        numberAttributes.photoId_ = j;
    }

    static /* synthetic */ void access$900(NumberAttributes numberAttributes, String str) {
        if (str != null) {
            numberAttributes.bitField0_ |= 8;
            numberAttributes.lookupUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public static NumberAttributes getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static NumberAttributes parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (NumberAttributes) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Parser<NumberAttributes> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0125, code lost:
        if (parseUnknownField(r14, r0) == false) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0127, code lost:
        r2 = 8192;
        r3 = 4096;
        r4 = 2048;
        r15 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0130, code lost:
        r2 = 8192;
        r3 = 4096;
        r4 = 2048;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object dynamicMethod(com.google.protobuf.GeneratedMessageLite.MethodToInvoke r24, java.lang.Object r25, java.lang.Object r26) {
        /*
            r23 = this;
            r1 = r23
            int r0 = r24.ordinal()
            r2 = 8192(0x2000, float:1.14794E-41)
            r3 = 4096(0x1000, float:5.74E-42)
            r4 = 2048(0x800, float:2.87E-42)
            r5 = 1024(0x400, float:1.435E-42)
            r6 = 512(0x200, float:7.175E-43)
            r7 = 256(0x100, float:3.59E-43)
            r8 = 128(0x80, float:1.794E-43)
            r9 = 64
            r10 = 32
            r11 = 16
            r12 = 8
            r13 = 4
            r15 = 0
            switch(r0) {
                case 0: goto L_0x02f3;
                case 1: goto L_0x015d;
                case 2: goto L_0x0050;
                case 3: goto L_0x004f;
                case 4: goto L_0x0049;
                case 5: goto L_0x0043;
                case 6: goto L_0x015a;
                case 7: goto L_0x0027;
                default: goto L_0x0021;
            }
        L_0x0021:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x0027:
            com.google.protobuf.Parser<com.android.dialer.NumberAttributes> r0 = PARSER
            if (r0 != 0) goto L_0x0040
            java.lang.Class<com.android.dialer.NumberAttributes> r1 = com.android.dialer.NumberAttributes.class
            monitor-enter(r1)
            com.google.protobuf.Parser<com.android.dialer.NumberAttributes> r0 = PARSER     // Catch:{ all -> 0x003d }
            if (r0 != 0) goto L_0x003b
            com.google.protobuf.GeneratedMessageLite$DefaultInstanceBasedParser r0 = new com.google.protobuf.GeneratedMessageLite$DefaultInstanceBasedParser     // Catch:{ all -> 0x003d }
            com.android.dialer.NumberAttributes r2 = DEFAULT_INSTANCE     // Catch:{ all -> 0x003d }
            r0.<init>(r2)     // Catch:{ all -> 0x003d }
            PARSER = r0     // Catch:{ all -> 0x003d }
        L_0x003b:
            monitor-exit(r1)     // Catch:{ all -> 0x003d }
            goto L_0x0040
        L_0x003d:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x003d }
            throw r0
        L_0x0040:
            com.google.protobuf.Parser<com.android.dialer.NumberAttributes> r0 = PARSER
            return r0
        L_0x0043:
            com.android.dialer.NumberAttributes$Builder r0 = new com.android.dialer.NumberAttributes$Builder
            r0.<init>(r15)
            return r0
        L_0x0049:
            com.android.dialer.NumberAttributes r0 = new com.android.dialer.NumberAttributes
            r0.<init>()
            return r0
        L_0x004f:
            return r15
        L_0x0050:
            r0 = r25
            com.google.protobuf.CodedInputStream r0 = (com.google.protobuf.CodedInputStream) r0
            r15 = r26
            com.google.protobuf.ExtensionRegistryLite r15 = (com.google.protobuf.ExtensionRegistryLite) r15
            r15 = 0
        L_0x0059:
            if (r15 != 0) goto L_0x015a
            int r14 = r0.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            switch(r14) {
                case 0: goto L_0x0127;
                case 10: goto L_0x0118;
                case 18: goto L_0x010b;
                case 24: goto L_0x00ff;
                case 34: goto L_0x00f3;
                case 42: goto L_0x00e7;
                case 48: goto L_0x00db;
                case 56: goto L_0x00cf;
                case 64: goto L_0x00c3;
                case 72: goto L_0x00b6;
                case 80: goto L_0x00a9;
                case 88: goto L_0x008f;
                case 96: goto L_0x0082;
                case 106: goto L_0x0075;
                case 112: goto L_0x0068;
                default: goto L_0x0062;
            }     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
        L_0x0062:
            boolean r3 = r1.parseUnknownField(r14, r0)     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0125
        L_0x0068:
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r14 = r14 | r2
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            boolean r14 = r0.readBool()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.isEmergencyNumber_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x0075:
            java.lang.String r14 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r2 = r2 | r3
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.geolocation_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x0082:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r2 = r2 | r4
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            boolean r2 = r0.readBool()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.canSupportCarrierVideoCall_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x008f:
            int r2 = r0.readRawVarint32()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            com.android.dialer.logging.ContactSource$Type r14 = com.android.dialer.logging.ContactSource$Type.forNumber(r2)     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            if (r14 != 0) goto L_0x00a0
            r14 = 11
            super.mergeVarintField(r14, r2)     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x00a0:
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r14 = r14 | r5
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.contactSource_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x00a9:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r2 = r2 | r6
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            boolean r2 = r0.readBool()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.isSpam_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x00b6:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r2 = r2 | r7
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            boolean r2 = r0.readBool()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.isBlocked_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x00c3:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r2 = r2 | r8
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            boolean r2 = r0.readBool()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.isCp2InfoIncomplete_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x00cf:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r2 = r2 | r9
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            boolean r2 = r0.readBool()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.canReportAsInvalidNumber_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x00db:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r2 = r2 | r10
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            boolean r2 = r0.readBool()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.isBusiness_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x00e7:
            java.lang.String r2 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r14 = r14 | r11
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.numberTypeLabel_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x00f3:
            java.lang.String r2 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r14 = r14 | r12
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.lookupUri_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x00ff:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r2 = r2 | r13
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            long r3 = r0.readRawVarint64()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.photoId_ = r3     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x010b:
            java.lang.String r3 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            int r4 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r14 = 2
            r4 = r4 | r14
            r1.bitField0_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.photoUri_ = r3     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x0118:
            java.lang.String r3 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            int r4 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r14 = 1
            r4 = r4 | r14
            r1.bitField0_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            r1.name_ = r3     // Catch:{ InvalidProtocolBufferException -> 0x014e, IOException -> 0x013a }
            goto L_0x0130
        L_0x0125:
            if (r3 != 0) goto L_0x0130
        L_0x0127:
            r2 = 8192(0x2000, float:1.14794E-41)
            r3 = 4096(0x1000, float:5.74E-42)
            r4 = 2048(0x800, float:2.87E-42)
            r15 = 1
            goto L_0x0059
        L_0x0130:
            r2 = 8192(0x2000, float:1.14794E-41)
            r3 = 4096(0x1000, float:5.74E-42)
            r4 = 2048(0x800, float:2.87E-42)
            goto L_0x0059
        L_0x0138:
            r0 = move-exception
            goto L_0x0159
        L_0x013a:
            r0 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0138 }
            com.google.protobuf.InvalidProtocolBufferException r3 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x0138 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0138 }
            r3.<init>(r0)     // Catch:{ all -> 0x0138 }
            com.google.protobuf.InvalidProtocolBufferException r0 = r3.setUnfinishedMessage(r1)     // Catch:{ all -> 0x0138 }
            r2.<init>(r0)     // Catch:{ all -> 0x0138 }
            throw r2     // Catch:{ all -> 0x0138 }
        L_0x014e:
            r0 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0138 }
            com.google.protobuf.InvalidProtocolBufferException r0 = r0.setUnfinishedMessage(r1)     // Catch:{ all -> 0x0138 }
            r2.<init>(r0)     // Catch:{ all -> 0x0138 }
            throw r2     // Catch:{ all -> 0x0138 }
        L_0x0159:
            throw r0
        L_0x015a:
            com.android.dialer.NumberAttributes r0 = DEFAULT_INSTANCE
            return r0
        L_0x015d:
            r0 = r25
            com.google.protobuf.GeneratedMessageLite$Visitor r0 = (com.google.protobuf.GeneratedMessageLite.Visitor) r0
            r3 = r26
            com.android.dialer.NumberAttributes r3 = (com.android.dialer.NumberAttributes) r3
            int r4 = r1.bitField0_
            r14 = 1
            r4 = r4 & r14
            if (r4 != r14) goto L_0x016d
            r4 = r14
            goto L_0x016e
        L_0x016d:
            r4 = 0
        L_0x016e:
            java.lang.String r15 = r1.name_
            int r2 = r3.bitField0_
            r2 = r2 & r14
            if (r2 != r14) goto L_0x0177
            r2 = r14
            goto L_0x0178
        L_0x0177:
            r2 = 0
        L_0x0178:
            java.lang.String r14 = r3.name_
            java.lang.String r2 = r0.visitString(r4, r15, r2, r14)
            r1.name_ = r2
            int r2 = r1.bitField0_
            r4 = 2
            r2 = r2 & r4
            if (r2 != r4) goto L_0x0188
            r2 = 1
            goto L_0x0189
        L_0x0188:
            r2 = 0
        L_0x0189:
            java.lang.String r14 = r1.photoUri_
            int r15 = r3.bitField0_
            r15 = r15 & r4
            if (r15 != r4) goto L_0x0192
            r4 = 1
            goto L_0x0193
        L_0x0192:
            r4 = 0
        L_0x0193:
            java.lang.String r15 = r3.photoUri_
            java.lang.String r2 = r0.visitString(r2, r14, r4, r15)
            r1.photoUri_ = r2
            int r2 = r1.bitField0_
            r2 = r2 & r13
            if (r2 != r13) goto L_0x01a3
            r17 = 1
            goto L_0x01a5
        L_0x01a3:
            r17 = 0
        L_0x01a5:
            long r14 = r1.photoId_
            int r2 = r3.bitField0_
            r2 = r2 & r13
            if (r2 != r13) goto L_0x01af
            r20 = 1
            goto L_0x01b1
        L_0x01af:
            r20 = 0
        L_0x01b1:
            long r5 = r3.photoId_
            r16 = r0
            r18 = r14
            r21 = r5
            long r5 = r16.visitLong(r17, r18, r20, r21)
            r1.photoId_ = r5
            int r5 = r1.bitField0_
            r5 = r5 & r12
            if (r5 != r12) goto L_0x01c6
            r5 = 1
            goto L_0x01c7
        L_0x01c6:
            r5 = 0
        L_0x01c7:
            java.lang.String r6 = r1.lookupUri_
            int r13 = r3.bitField0_
            r13 = r13 & r12
            if (r13 != r12) goto L_0x01d0
            r12 = 1
            goto L_0x01d1
        L_0x01d0:
            r12 = 0
        L_0x01d1:
            java.lang.String r13 = r3.lookupUri_
            java.lang.String r5 = r0.visitString(r5, r6, r12, r13)
            r1.lookupUri_ = r5
            int r5 = r1.bitField0_
            r5 = r5 & r11
            if (r5 != r11) goto L_0x01e0
            r5 = 1
            goto L_0x01e1
        L_0x01e0:
            r5 = 0
        L_0x01e1:
            java.lang.String r6 = r1.numberTypeLabel_
            int r12 = r3.bitField0_
            r12 = r12 & r11
            if (r12 != r11) goto L_0x01ea
            r11 = 1
            goto L_0x01eb
        L_0x01ea:
            r11 = 0
        L_0x01eb:
            java.lang.String r12 = r3.numberTypeLabel_
            java.lang.String r5 = r0.visitString(r5, r6, r11, r12)
            r1.numberTypeLabel_ = r5
            int r5 = r1.bitField0_
            r5 = r5 & r10
            if (r5 != r10) goto L_0x01fa
            r5 = 1
            goto L_0x01fb
        L_0x01fa:
            r5 = 0
        L_0x01fb:
            boolean r6 = r1.isBusiness_
            int r11 = r3.bitField0_
            r11 = r11 & r10
            if (r11 != r10) goto L_0x0204
            r10 = 1
            goto L_0x0205
        L_0x0204:
            r10 = 0
        L_0x0205:
            boolean r11 = r3.isBusiness_
            boolean r5 = r0.visitBoolean(r5, r6, r10, r11)
            r1.isBusiness_ = r5
            int r5 = r1.bitField0_
            r5 = r5 & r9
            if (r5 != r9) goto L_0x0214
            r5 = 1
            goto L_0x0215
        L_0x0214:
            r5 = 0
        L_0x0215:
            boolean r6 = r1.canReportAsInvalidNumber_
            int r10 = r3.bitField0_
            r10 = r10 & r9
            if (r10 != r9) goto L_0x021e
            r9 = 1
            goto L_0x021f
        L_0x021e:
            r9 = 0
        L_0x021f:
            boolean r10 = r3.canReportAsInvalidNumber_
            boolean r5 = r0.visitBoolean(r5, r6, r9, r10)
            r1.canReportAsInvalidNumber_ = r5
            int r5 = r1.bitField0_
            r5 = r5 & r8
            if (r5 != r8) goto L_0x022e
            r5 = 1
            goto L_0x022f
        L_0x022e:
            r5 = 0
        L_0x022f:
            boolean r6 = r1.isCp2InfoIncomplete_
            int r9 = r3.bitField0_
            r9 = r9 & r8
            if (r9 != r8) goto L_0x0238
            r8 = 1
            goto L_0x0239
        L_0x0238:
            r8 = 0
        L_0x0239:
            boolean r9 = r3.isCp2InfoIncomplete_
            boolean r5 = r0.visitBoolean(r5, r6, r8, r9)
            r1.isCp2InfoIncomplete_ = r5
            int r5 = r1.bitField0_
            r5 = r5 & r7
            if (r5 != r7) goto L_0x0248
            r5 = 1
            goto L_0x0249
        L_0x0248:
            r5 = 0
        L_0x0249:
            boolean r6 = r1.isBlocked_
            int r8 = r3.bitField0_
            r8 = r8 & r7
            if (r8 != r7) goto L_0x0252
            r7 = 1
            goto L_0x0253
        L_0x0252:
            r7 = 0
        L_0x0253:
            boolean r8 = r3.isBlocked_
            boolean r5 = r0.visitBoolean(r5, r6, r7, r8)
            r1.isBlocked_ = r5
            int r5 = r1.bitField0_
            r4 = 512(0x200, float:7.175E-43)
            r5 = r5 & r4
            if (r5 != r4) goto L_0x0264
            r5 = 1
            goto L_0x0265
        L_0x0264:
            r5 = 0
        L_0x0265:
            boolean r6 = r1.isSpam_
            int r7 = r3.bitField0_
            r7 = r7 & r4
            if (r7 != r4) goto L_0x026e
            r4 = 1
            goto L_0x026f
        L_0x026e:
            r4 = 0
        L_0x026f:
            boolean r7 = r3.isSpam_
            boolean r4 = r0.visitBoolean(r5, r6, r4, r7)
            r1.isSpam_ = r4
            int r4 = r1.bitField0_
            r2 = 1024(0x400, float:1.435E-42)
            r4 = r4 & r2
            if (r4 != r2) goto L_0x0280
            r4 = 1
            goto L_0x0281
        L_0x0280:
            r4 = 0
        L_0x0281:
            int r5 = r1.contactSource_
            int r6 = r3.bitField0_
            r6 = r6 & r2
            if (r6 != r2) goto L_0x028a
            r2 = 1
            goto L_0x028b
        L_0x028a:
            r2 = 0
        L_0x028b:
            int r6 = r3.contactSource_
            int r2 = r0.visitInt(r4, r5, r2, r6)
            r1.contactSource_ = r2
            int r2 = r1.bitField0_
            r4 = 2048(0x800, float:2.87E-42)
            r2 = r2 & r4
            if (r2 != r4) goto L_0x029c
            r2 = 1
            goto L_0x029d
        L_0x029c:
            r2 = 0
        L_0x029d:
            boolean r5 = r1.canSupportCarrierVideoCall_
            int r6 = r3.bitField0_
            r6 = r6 & r4
            if (r6 != r4) goto L_0x02a6
            r4 = 1
            goto L_0x02a7
        L_0x02a6:
            r4 = 0
        L_0x02a7:
            boolean r6 = r3.canSupportCarrierVideoCall_
            boolean r2 = r0.visitBoolean(r2, r5, r4, r6)
            r1.canSupportCarrierVideoCall_ = r2
            int r2 = r1.bitField0_
            r4 = 4096(0x1000, float:5.74E-42)
            r2 = r2 & r4
            if (r2 != r4) goto L_0x02b8
            r2 = 1
            goto L_0x02b9
        L_0x02b8:
            r2 = 0
        L_0x02b9:
            java.lang.String r5 = r1.geolocation_
            int r6 = r3.bitField0_
            r6 = r6 & r4
            if (r6 != r4) goto L_0x02c2
            r4 = 1
            goto L_0x02c3
        L_0x02c2:
            r4 = 0
        L_0x02c3:
            java.lang.String r6 = r3.geolocation_
            java.lang.String r2 = r0.visitString(r2, r5, r4, r6)
            r1.geolocation_ = r2
            int r2 = r1.bitField0_
            r4 = 8192(0x2000, float:1.14794E-41)
            r2 = r2 & r4
            if (r2 != r4) goto L_0x02d4
            r2 = 1
            goto L_0x02d5
        L_0x02d4:
            r2 = 0
        L_0x02d5:
            boolean r5 = r1.isEmergencyNumber_
            int r6 = r3.bitField0_
            r6 = r6 & r4
            if (r6 != r4) goto L_0x02de
            r4 = 1
            goto L_0x02df
        L_0x02de:
            r4 = 0
        L_0x02df:
            boolean r6 = r3.isEmergencyNumber_
            boolean r2 = r0.visitBoolean(r2, r5, r4, r6)
            r1.isEmergencyNumber_ = r2
            com.google.protobuf.GeneratedMessageLite$MergeFromVisitor r2 = com.google.protobuf.GeneratedMessageLite.MergeFromVisitor.INSTANCE
            if (r0 != r2) goto L_0x02f2
            int r0 = r1.bitField0_
            int r2 = r3.bitField0_
            r0 = r0 | r2
            r1.bitField0_ = r0
        L_0x02f2:
            return r1
        L_0x02f3:
            com.android.dialer.NumberAttributes r0 = DEFAULT_INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.NumberAttributes.dynamicMethod(com.google.protobuf.GeneratedMessageLite$MethodToInvoke, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public boolean getCanReportAsInvalidNumber() {
        return this.canReportAsInvalidNumber_;
    }

    public boolean getCanSupportCarrierVideoCall() {
        return this.canSupportCarrierVideoCall_;
    }

    public ContactSource$Type getContactSource() {
        ContactSource$Type forNumber = ContactSource$Type.forNumber(this.contactSource_);
        return forNumber == null ? ContactSource$Type.UNKNOWN_SOURCE_TYPE : forNumber;
    }

    public String getGeolocation() {
        return this.geolocation_;
    }

    public boolean getIsBlocked() {
        return this.isBlocked_;
    }

    public boolean getIsBusiness() {
        return this.isBusiness_;
    }

    public boolean getIsCp2InfoIncomplete() {
        return this.isCp2InfoIncomplete_;
    }

    public boolean getIsEmergencyNumber() {
        return this.isEmergencyNumber_;
    }

    public boolean getIsSpam() {
        return this.isSpam_;
    }

    public String getLookupUri() {
        return this.lookupUri_;
    }

    public String getName() {
        return this.name_;
    }

    public String getNumberTypeLabel() {
        return this.numberTypeLabel_;
    }

    public long getPhotoId() {
        return this.photoId_;
    }

    public String getPhotoUri() {
        return this.photoUri_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, this.name_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, this.photoUri_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeInt64Size(3, this.photoId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeStringSize(4, this.lookupUri_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeStringSize(5, this.numberTypeLabel_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeBoolSize(6, this.isBusiness_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeBoolSize(7, this.canReportAsInvalidNumber_);
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeBoolSize(8, this.isCp2InfoIncomplete_);
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeBoolSize(9, this.isBlocked_);
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeBoolSize(10, this.isSpam_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            i2 += CodedOutputStream.computeEnumSize(11, this.contactSource_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            i2 += CodedOutputStream.computeBoolSize(12, this.canSupportCarrierVideoCall_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            i2 += CodedOutputStream.computeStringSize(13, this.geolocation_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            i2 += CodedOutputStream.computeBoolSize(14, this.isEmergencyNumber_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, this.name_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, this.photoUri_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeUInt64(3, this.photoId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(4, this.lookupUri_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeString(5, this.numberTypeLabel_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeBool(6, this.isBusiness_);
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeBool(7, this.canReportAsInvalidNumber_);
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeBool(8, this.isCp2InfoIncomplete_);
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeBool(9, this.isBlocked_);
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeBool(10, this.isSpam_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            codedOutputStream.writeInt32(11, this.contactSource_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            codedOutputStream.writeBool(12, this.canSupportCarrierVideoCall_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            codedOutputStream.writeString(13, this.geolocation_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            codedOutputStream.writeBool(14, this.isEmergencyNumber_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
