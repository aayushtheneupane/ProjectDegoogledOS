package com.android.dialer.calllog.model;

import com.android.dialer.CoalescedIds;
import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.NumberAttributes;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class CoalescedRow extends GeneratedMessageLite<CoalescedRow, Builder> implements CoalescedRowOrBuilder {
    /* access modifiers changed from: private */
    public static final CoalescedRow DEFAULT_INSTANCE = new CoalescedRow();
    private static volatile Parser<CoalescedRow> PARSER;
    private int bitField0_;
    private int callType_ = 0;
    private CoalescedIds coalescedIds_;
    private int features_ = 0;
    private String formattedNumber_ = "";
    private String geocodedLocation_ = "";
    private long id_ = 0;
    private boolean isNew_ = false;
    private boolean isRead_ = false;
    private boolean isVoicemailCall_ = false;
    private NumberAttributes numberAttributes_;
    private int numberPresentation_ = 0;
    private DialerPhoneNumber number_;
    private String phoneAccountComponentName_ = "";
    private String phoneAccountId_ = "";
    private long timestamp_ = 0;
    private String voicemailCallTag_ = "";

    /* renamed from: com.android.dialer.calllog.model.CoalescedRow$1 */
    static /* synthetic */ class C04381 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f16xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f16xa1df5c61 = r0
                int[] r0 = f16xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f16xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f16xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f16xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f16xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f16xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f16xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f16xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calllog.model.CoalescedRow.C04381.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CoalescedRow, Builder> implements CoalescedRowOrBuilder {
        /* synthetic */ Builder(C04381 r1) {
            this();
        }

        public int getFeatures() {
            return ((CoalescedRow) this.instance).getFeatures();
        }

        public DialerPhoneNumber getNumber() {
            return ((CoalescedRow) this.instance).getNumber();
        }

        public int getNumberPresentation() {
            return ((CoalescedRow) this.instance).getNumberPresentation();
        }

        public String getPhoneAccountComponentName() {
            return ((CoalescedRow) this.instance).getPhoneAccountComponentName();
        }

        public String getPhoneAccountId() {
            return ((CoalescedRow) this.instance).getPhoneAccountId();
        }

        public Builder setCallType(int i) {
            copyOnWrite();
            CoalescedRow.access$2900((CoalescedRow) this.instance, i);
            return this;
        }

        public Builder setCoalescedIds(CoalescedIds coalescedIds) {
            copyOnWrite();
            CoalescedRow.access$4000((CoalescedRow) this.instance, coalescedIds);
            return this;
        }

        public Builder setFeatures(int i) {
            copyOnWrite();
            CoalescedRow.access$2700((CoalescedRow) this.instance, i);
            return this;
        }

        public Builder setFormattedNumber(String str) {
            copyOnWrite();
            CoalescedRow.access$900((CoalescedRow) this.instance, str);
            return this;
        }

        public Builder setGeocodedLocation(String str) {
            copyOnWrite();
            CoalescedRow.access$1800((CoalescedRow) this.instance, str);
            return this;
        }

        public Builder setId(long j) {
            copyOnWrite();
            CoalescedRow.access$100((CoalescedRow) this.instance, j);
            return this;
        }

        public Builder setIsNew(boolean z) {
            copyOnWrite();
            CoalescedRow.access$1600((CoalescedRow) this.instance, z);
            return this;
        }

        public Builder setIsRead(boolean z) {
            copyOnWrite();
            CoalescedRow.access$1400((CoalescedRow) this.instance, z);
            return this;
        }

        public Builder setIsVoicemailCall(boolean z) {
            copyOnWrite();
            CoalescedRow.access$3500((CoalescedRow) this.instance, z);
            return this;
        }

        public Builder setNumber(DialerPhoneNumber dialerPhoneNumber) {
            copyOnWrite();
            CoalescedRow.access$500((CoalescedRow) this.instance, dialerPhoneNumber);
            return this;
        }

        public Builder setNumberAttributes(NumberAttributes numberAttributes) {
            copyOnWrite();
            CoalescedRow.access$3100((CoalescedRow) this.instance, numberAttributes);
            return this;
        }

        public Builder setNumberPresentation(int i) {
            copyOnWrite();
            CoalescedRow.access$1200((CoalescedRow) this.instance, i);
            return this;
        }

        public Builder setPhoneAccountComponentName(String str) {
            copyOnWrite();
            CoalescedRow.access$2100((CoalescedRow) this.instance, str);
            return this;
        }

        public Builder setPhoneAccountId(String str) {
            copyOnWrite();
            CoalescedRow.access$2400((CoalescedRow) this.instance, str);
            return this;
        }

        public Builder setTimestamp(long j) {
            copyOnWrite();
            CoalescedRow.access$300((CoalescedRow) this.instance, j);
            return this;
        }

        public Builder setVoicemailCallTag(String str) {
            copyOnWrite();
            CoalescedRow.access$3700((CoalescedRow) this.instance, str);
            return this;
        }

        private Builder() {
            super(CoalescedRow.DEFAULT_INSTANCE);
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private CoalescedRow() {
    }

    static /* synthetic */ void access$100(CoalescedRow coalescedRow, long j) {
        coalescedRow.bitField0_ |= 1;
        coalescedRow.id_ = j;
    }

    static /* synthetic */ void access$1200(CoalescedRow coalescedRow, int i) {
        coalescedRow.bitField0_ |= 16;
        coalescedRow.numberPresentation_ = i;
    }

    static /* synthetic */ void access$1400(CoalescedRow coalescedRow, boolean z) {
        coalescedRow.bitField0_ |= 32;
        coalescedRow.isRead_ = z;
    }

    static /* synthetic */ void access$1600(CoalescedRow coalescedRow, boolean z) {
        coalescedRow.bitField0_ |= 64;
        coalescedRow.isNew_ = z;
    }

    static /* synthetic */ void access$1800(CoalescedRow coalescedRow, String str) {
        if (str != null) {
            coalescedRow.bitField0_ |= 128;
            coalescedRow.geocodedLocation_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2100(CoalescedRow coalescedRow, String str) {
        if (str != null) {
            coalescedRow.bitField0_ |= 256;
            coalescedRow.phoneAccountComponentName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2400(CoalescedRow coalescedRow, String str) {
        if (str != null) {
            coalescedRow.bitField0_ |= 512;
            coalescedRow.phoneAccountId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2700(CoalescedRow coalescedRow, int i) {
        coalescedRow.bitField0_ |= 1024;
        coalescedRow.features_ = i;
    }

    static /* synthetic */ void access$2900(CoalescedRow coalescedRow, int i) {
        coalescedRow.bitField0_ |= 2048;
        coalescedRow.callType_ = i;
    }

    static /* synthetic */ void access$300(CoalescedRow coalescedRow, long j) {
        coalescedRow.bitField0_ |= 2;
        coalescedRow.timestamp_ = j;
    }

    static /* synthetic */ void access$3100(CoalescedRow coalescedRow, NumberAttributes numberAttributes) {
        if (numberAttributes != null) {
            coalescedRow.numberAttributes_ = numberAttributes;
            coalescedRow.bitField0_ |= 4096;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$3500(CoalescedRow coalescedRow, boolean z) {
        coalescedRow.bitField0_ |= 8192;
        coalescedRow.isVoicemailCall_ = z;
    }

    static /* synthetic */ void access$3700(CoalescedRow coalescedRow, String str) {
        if (str != null) {
            coalescedRow.bitField0_ |= 16384;
            coalescedRow.voicemailCallTag_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$4000(CoalescedRow coalescedRow, CoalescedIds coalescedIds) {
        if (coalescedIds != null) {
            coalescedRow.coalescedIds_ = coalescedIds;
            coalescedRow.bitField0_ |= 32768;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$500(CoalescedRow coalescedRow, DialerPhoneNumber dialerPhoneNumber) {
        if (dialerPhoneNumber != null) {
            coalescedRow.number_ = dialerPhoneNumber;
            coalescedRow.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$900(CoalescedRow coalescedRow, String str) {
        if (str != null) {
            coalescedRow.bitField0_ |= 8;
            coalescedRow.formattedNumber_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return DEFAULT_INSTANCE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                CoalescedRow coalescedRow = (CoalescedRow) obj2;
                CoalescedRow coalescedRow2 = coalescedRow;
                this.id_ = visitor.visitLong((this.bitField0_ & 1) == 1, this.id_, (coalescedRow.bitField0_ & 1) == 1, coalescedRow.id_);
                this.timestamp_ = visitor.visitLong((this.bitField0_ & 2) == 2, this.timestamp_, (coalescedRow2.bitField0_ & 2) == 2, coalescedRow2.timestamp_);
                this.number_ = (DialerPhoneNumber) visitor.visitMessage(this.number_, coalescedRow2.number_);
                this.formattedNumber_ = visitor.visitString((this.bitField0_ & 8) == 8, this.formattedNumber_, (coalescedRow2.bitField0_ & 8) == 8, coalescedRow2.formattedNumber_);
                this.numberPresentation_ = visitor.visitInt((this.bitField0_ & 16) == 16, this.numberPresentation_, (coalescedRow2.bitField0_ & 16) == 16, coalescedRow2.numberPresentation_);
                this.isRead_ = visitor.visitBoolean((this.bitField0_ & 32) == 32, this.isRead_, (coalescedRow2.bitField0_ & 32) == 32, coalescedRow2.isRead_);
                this.isNew_ = visitor.visitBoolean((this.bitField0_ & 64) == 64, this.isNew_, (coalescedRow2.bitField0_ & 64) == 64, coalescedRow2.isNew_);
                this.geocodedLocation_ = visitor.visitString((this.bitField0_ & 128) == 128, this.geocodedLocation_, (coalescedRow2.bitField0_ & 128) == 128, coalescedRow2.geocodedLocation_);
                this.phoneAccountComponentName_ = visitor.visitString((this.bitField0_ & 256) == 256, this.phoneAccountComponentName_, (coalescedRow2.bitField0_ & 256) == 256, coalescedRow2.phoneAccountComponentName_);
                this.phoneAccountId_ = visitor.visitString((this.bitField0_ & 512) == 512, this.phoneAccountId_, (coalescedRow2.bitField0_ & 512) == 512, coalescedRow2.phoneAccountId_);
                this.features_ = visitor.visitInt((this.bitField0_ & 1024) == 1024, this.features_, (coalescedRow2.bitField0_ & 1024) == 1024, coalescedRow2.features_);
                this.callType_ = visitor.visitInt((this.bitField0_ & 2048) == 2048, this.callType_, (coalescedRow2.bitField0_ & 2048) == 2048, coalescedRow2.callType_);
                this.numberAttributes_ = (NumberAttributes) visitor.visitMessage(this.numberAttributes_, coalescedRow2.numberAttributes_);
                this.isVoicemailCall_ = visitor.visitBoolean((this.bitField0_ & 8192) == 8192, this.isVoicemailCall_, (coalescedRow2.bitField0_ & 8192) == 8192, coalescedRow2.isVoicemailCall_);
                this.voicemailCallTag_ = visitor.visitString((this.bitField0_ & 16384) == 16384, this.voicemailCallTag_, (coalescedRow2.bitField0_ & 16384) == 16384, coalescedRow2.voicemailCallTag_);
                this.coalescedIds_ = (CoalescedIds) visitor.visitMessage(this.coalescedIds_, coalescedRow2.coalescedIds_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= coalescedRow2.bitField0_;
                }
                return this;
            case 2:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.id_ = codedInputStream.readRawVarint64();
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.timestamp_ = codedInputStream.readRawVarint64();
                                break;
                            case 26:
                                DialerPhoneNumber.Builder builder = (this.bitField0_ & 4) == 4 ? (DialerPhoneNumber.Builder) this.number_.toBuilder() : null;
                                this.number_ = (DialerPhoneNumber) codedInputStream.readMessage(DialerPhoneNumber.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.number_);
                                    this.number_ = (DialerPhoneNumber) builder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                break;
                            case 34:
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 8;
                                this.formattedNumber_ = readString;
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.numberPresentation_ = codedInputStream.readRawVarint32();
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.isRead_ = codedInputStream.readBool();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.isNew_ = codedInputStream.readBool();
                                break;
                            case 66:
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 128;
                                this.geocodedLocation_ = readString2;
                                break;
                            case 74:
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 256;
                                this.phoneAccountComponentName_ = readString3;
                                break;
                            case 82:
                                String readString4 = codedInputStream.readString();
                                this.bitField0_ |= 512;
                                this.phoneAccountId_ = readString4;
                                break;
                            case 88:
                                this.bitField0_ |= 1024;
                                this.features_ = codedInputStream.readRawVarint32();
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.callType_ = codedInputStream.readRawVarint32();
                                break;
                            case 106:
                                NumberAttributes.Builder builder2 = (this.bitField0_ & 4096) == 4096 ? (NumberAttributes.Builder) this.numberAttributes_.toBuilder() : null;
                                this.numberAttributes_ = (NumberAttributes) codedInputStream.readMessage(NumberAttributes.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.numberAttributes_);
                                    this.numberAttributes_ = (NumberAttributes) builder2.buildPartial();
                                }
                                this.bitField0_ |= 4096;
                                break;
                            case 112:
                                this.bitField0_ |= 8192;
                                this.isVoicemailCall_ = codedInputStream.readBool();
                                break;
                            case 122:
                                String readString5 = codedInputStream.readString();
                                this.bitField0_ |= 16384;
                                this.voicemailCallTag_ = readString5;
                                break;
                            case 130:
                                CoalescedIds.Builder builder3 = (this.bitField0_ & 32768) == 32768 ? (CoalescedIds.Builder) this.coalescedIds_.toBuilder() : null;
                                this.coalescedIds_ = (CoalescedIds) codedInputStream.readMessage(CoalescedIds.parser(), extensionRegistryLite);
                                if (builder3 != null) {
                                    builder3.mergeFrom(this.coalescedIds_);
                                    this.coalescedIds_ = (CoalescedIds) builder3.buildPartial();
                                }
                                this.bitField0_ |= 32768;
                                break;
                            default:
                                if (parseUnknownField(readTag, codedInputStream)) {
                                    break;
                                }
                                z = true;
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 3:
                return null;
            case 4:
                return new CoalescedRow();
            case 5:
                return new Builder((C04381) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (CoalescedRow.class) {
                        if (PARSER == null) {
                            PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    public int getCallType() {
        return this.callType_;
    }

    public CoalescedIds getCoalescedIds() {
        CoalescedIds coalescedIds = this.coalescedIds_;
        return coalescedIds == null ? CoalescedIds.getDefaultInstance() : coalescedIds;
    }

    public int getFeatures() {
        return this.features_;
    }

    public String getFormattedNumber() {
        return this.formattedNumber_;
    }

    public String getGeocodedLocation() {
        return this.geocodedLocation_;
    }

    public long getId() {
        return this.id_;
    }

    public boolean getIsRead() {
        return this.isRead_;
    }

    public boolean getIsVoicemailCall() {
        return this.isVoicemailCall_;
    }

    public DialerPhoneNumber getNumber() {
        DialerPhoneNumber dialerPhoneNumber = this.number_;
        return dialerPhoneNumber == null ? DialerPhoneNumber.getDefaultInstance() : dialerPhoneNumber;
    }

    public NumberAttributes getNumberAttributes() {
        NumberAttributes numberAttributes = this.numberAttributes_;
        return numberAttributes == null ? NumberAttributes.getDefaultInstance() : numberAttributes;
    }

    public int getNumberPresentation() {
        return this.numberPresentation_;
    }

    public String getPhoneAccountComponentName() {
        return this.phoneAccountComponentName_;
    }

    public String getPhoneAccountId() {
        return this.phoneAccountId_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeInt64Size(2, this.timestamp_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeMessageSize(3, getNumber());
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeStringSize(4, this.formattedNumber_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeInt32Size(5, this.numberPresentation_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeBoolSize(6, this.isRead_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeBoolSize(7, this.isNew_);
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeStringSize(8, this.geocodedLocation_);
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeStringSize(9, this.phoneAccountComponentName_);
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeStringSize(10, this.phoneAccountId_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            i2 += CodedOutputStream.computeInt32Size(11, this.features_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            i2 += CodedOutputStream.computeInt32Size(12, this.callType_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            i2 += CodedOutputStream.computeMessageSize(13, getNumberAttributes());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            i2 += CodedOutputStream.computeBoolSize(14, this.isVoicemailCall_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            i2 += CodedOutputStream.computeStringSize(15, this.voicemailCallTag_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            i2 += CodedOutputStream.computeMessageSize(16, getCoalescedIds());
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public long getTimestamp() {
        return this.timestamp_;
    }

    public String getVoicemailCallTag() {
        return this.voicemailCallTag_;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeUInt64(1, this.id_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeUInt64(2, this.timestamp_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeMessage(3, getNumber());
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(4, this.formattedNumber_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeInt32(5, this.numberPresentation_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeBool(6, this.isRead_);
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeBool(7, this.isNew_);
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeString(8, this.geocodedLocation_);
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeString(9, this.phoneAccountComponentName_);
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeString(10, this.phoneAccountId_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            codedOutputStream.writeInt32(11, this.features_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            codedOutputStream.writeInt32(12, this.callType_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            codedOutputStream.writeMessage(13, getNumberAttributes());
        }
        if ((this.bitField0_ & 8192) == 8192) {
            codedOutputStream.writeBool(14, this.isVoicemailCall_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            codedOutputStream.writeString(15, this.voicemailCallTag_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            codedOutputStream.writeMessage(16, getCoalescedIds());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
