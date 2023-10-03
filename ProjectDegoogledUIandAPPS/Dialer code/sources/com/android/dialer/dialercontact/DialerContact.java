package com.android.dialer.dialercontact;

import com.android.dialer.dialercontact.SimDetails;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class DialerContact extends GeneratedMessageLite<DialerContact, Builder> implements DialerContactOrBuilder {
    /* access modifiers changed from: private */
    public static final DialerContact DEFAULT_INSTANCE = new DialerContact();
    private static volatile Parser<DialerContact> PARSER;
    private int bitField0_;
    private int contactType_ = 0;
    private String contactUri_ = "";
    private String displayNumber_ = "";
    private String nameOrNumber_ = "";
    private String numberLabel_ = "";
    private String number_ = "";
    private long photoId_ = 0;
    private String photoUri_ = "";
    private String postDialDigits_ = "";
    private SimDetails simDetails_;

    /* renamed from: com.android.dialer.dialercontact.DialerContact$1 */
    static /* synthetic */ class C04711 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f21xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f21xa1df5c61 = r0
                int[] r0 = f21xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f21xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f21xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f21xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f21xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f21xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f21xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f21xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.dialercontact.DialerContact.C04711.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DialerContact, Builder> implements DialerContactOrBuilder {
        /* synthetic */ Builder(C04711 r1) {
            this();
        }

        public Builder setContactType(int i) {
            copyOnWrite();
            DialerContact.access$2100((DialerContact) this.instance, i);
            return this;
        }

        public Builder setContactUri(String str) {
            copyOnWrite();
            DialerContact.access$600((DialerContact) this.instance, str);
            return this;
        }

        public Builder setDisplayNumber(String str) {
            copyOnWrite();
            DialerContact.access$1500((DialerContact) this.instance, str);
            return this;
        }

        public Builder setNameOrNumber(String str) {
            copyOnWrite();
            DialerContact.access$900((DialerContact) this.instance, str);
            return this;
        }

        public Builder setNumber(String str) {
            copyOnWrite();
            DialerContact.access$1200((DialerContact) this.instance, str);
            return this;
        }

        public Builder setNumberLabel(String str) {
            copyOnWrite();
            DialerContact.access$1800((DialerContact) this.instance, str);
            return this;
        }

        public Builder setPhotoId(long j) {
            copyOnWrite();
            DialerContact.access$100((DialerContact) this.instance, j);
            return this;
        }

        public Builder setPhotoUri(String str) {
            copyOnWrite();
            DialerContact.access$300((DialerContact) this.instance, str);
            return this;
        }

        public Builder setPostDialDigits(String str) {
            copyOnWrite();
            DialerContact.access$2700((DialerContact) this.instance, str);
            return this;
        }

        public Builder setSimDetails(SimDetails simDetails) {
            copyOnWrite();
            DialerContact.access$2300((DialerContact) this.instance, simDetails);
            return this;
        }

        private Builder() {
            super(DialerContact.DEFAULT_INSTANCE);
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private DialerContact() {
    }

    static /* synthetic */ void access$100(DialerContact dialerContact, long j) {
        dialerContact.bitField0_ |= 1;
        dialerContact.photoId_ = j;
    }

    static /* synthetic */ void access$1200(DialerContact dialerContact, String str) {
        if (str != null) {
            dialerContact.bitField0_ |= 16;
            dialerContact.number_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1500(DialerContact dialerContact, String str) {
        if (str != null) {
            dialerContact.bitField0_ |= 32;
            dialerContact.displayNumber_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1800(DialerContact dialerContact, String str) {
        if (str != null) {
            dialerContact.bitField0_ |= 64;
            dialerContact.numberLabel_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2100(DialerContact dialerContact, int i) {
        dialerContact.bitField0_ |= 128;
        dialerContact.contactType_ = i;
    }

    static /* synthetic */ void access$2300(DialerContact dialerContact, SimDetails simDetails) {
        if (simDetails != null) {
            dialerContact.simDetails_ = simDetails;
            dialerContact.bitField0_ |= 256;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2700(DialerContact dialerContact, String str) {
        if (str != null) {
            dialerContact.bitField0_ |= 512;
            dialerContact.postDialDigits_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$300(DialerContact dialerContact, String str) {
        if (str != null) {
            dialerContact.bitField0_ |= 2;
            dialerContact.photoUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$600(DialerContact dialerContact, String str) {
        if (str != null) {
            dialerContact.bitField0_ |= 4;
            dialerContact.contactUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$900(DialerContact dialerContact, String str) {
        if (str != null) {
            dialerContact.bitField0_ |= 8;
            dialerContact.nameOrNumber_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public static DialerContact getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static DialerContact parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DialerContact) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return DEFAULT_INSTANCE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                DialerContact dialerContact = (DialerContact) obj2;
                DialerContact dialerContact2 = dialerContact;
                this.photoId_ = visitor.visitLong((this.bitField0_ & 1) == 1, this.photoId_, (dialerContact.bitField0_ & 1) == 1, dialerContact.photoId_);
                DialerContact dialerContact3 = dialerContact2;
                this.photoUri_ = visitor.visitString(hasPhotoUri(), this.photoUri_, dialerContact2.hasPhotoUri(), dialerContact3.photoUri_);
                this.contactUri_ = visitor.visitString(hasContactUri(), this.contactUri_, dialerContact3.hasContactUri(), dialerContact3.contactUri_);
                this.nameOrNumber_ = visitor.visitString((this.bitField0_ & 8) == 8, this.nameOrNumber_, (dialerContact3.bitField0_ & 8) == 8, dialerContact3.nameOrNumber_);
                this.number_ = visitor.visitString((this.bitField0_ & 16) == 16, this.number_, (dialerContact3.bitField0_ & 16) == 16, dialerContact3.number_);
                this.displayNumber_ = visitor.visitString((this.bitField0_ & 32) == 32, this.displayNumber_, (dialerContact3.bitField0_ & 32) == 32, dialerContact3.displayNumber_);
                this.numberLabel_ = visitor.visitString((this.bitField0_ & 64) == 64, this.numberLabel_, (dialerContact3.bitField0_ & 64) == 64, dialerContact3.numberLabel_);
                this.contactType_ = visitor.visitInt((this.bitField0_ & 128) == 128, this.contactType_, (dialerContact3.bitField0_ & 128) == 128, dialerContact3.contactType_);
                this.simDetails_ = (SimDetails) visitor.visitMessage(this.simDetails_, dialerContact3.simDetails_);
                this.postDialDigits_ = visitor.visitString((this.bitField0_ & 512) == 512, this.postDialDigits_, (dialerContact3.bitField0_ & 512) == 512, dialerContact3.postDialDigits_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= dialerContact3.bitField0_;
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
                            case 9:
                                this.bitField0_ |= 1;
                                this.photoId_ = codedInputStream.readFixed64();
                                break;
                            case 18:
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.photoUri_ = readString;
                                break;
                            case 26:
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.contactUri_ = readString2;
                                break;
                            case 34:
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 8;
                                this.nameOrNumber_ = readString3;
                                break;
                            case 50:
                                String readString4 = codedInputStream.readString();
                                this.bitField0_ |= 16;
                                this.number_ = readString4;
                                break;
                            case 58:
                                String readString5 = codedInputStream.readString();
                                this.bitField0_ |= 32;
                                this.displayNumber_ = readString5;
                                break;
                            case 66:
                                String readString6 = codedInputStream.readString();
                                this.bitField0_ |= 64;
                                this.numberLabel_ = readString6;
                                break;
                            case 72:
                                this.bitField0_ |= 128;
                                this.contactType_ = codedInputStream.readRawVarint32();
                                break;
                            case 82:
                                SimDetails.Builder builder = (this.bitField0_ & 256) == 256 ? (SimDetails.Builder) this.simDetails_.toBuilder() : null;
                                this.simDetails_ = (SimDetails) codedInputStream.readMessage(SimDetails.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.simDetails_);
                                    this.simDetails_ = (SimDetails) builder.buildPartial();
                                }
                                this.bitField0_ |= 256;
                                break;
                            case 90:
                                String readString7 = codedInputStream.readString();
                                this.bitField0_ |= 512;
                                this.postDialDigits_ = readString7;
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
                return new DialerContact();
            case 5:
                return new Builder((C04711) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (DialerContact.class) {
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

    public int getContactType() {
        return this.contactType_;
    }

    public String getContactUri() {
        return this.contactUri_;
    }

    public String getDisplayNumber() {
        return this.displayNumber_;
    }

    public String getNameOrNumber() {
        return this.nameOrNumber_;
    }

    public String getNumber() {
        return this.number_;
    }

    public String getNumberLabel() {
        return this.numberLabel_;
    }

    public long getPhotoId() {
        return this.photoId_;
    }

    public String getPhotoUri() {
        return this.photoUri_;
    }

    public String getPostDialDigits() {
        return this.postDialDigits_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeFixed64Size(1, this.photoId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, this.photoUri_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(3, this.contactUri_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeStringSize(4, this.nameOrNumber_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeStringSize(6, this.number_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeStringSize(7, this.displayNumber_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeStringSize(8, this.numberLabel_);
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeInt32Size(9, this.contactType_);
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeMessageSize(10, getSimDetails());
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeStringSize(11, this.postDialDigits_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public SimDetails getSimDetails() {
        SimDetails simDetails = this.simDetails_;
        return simDetails == null ? SimDetails.getDefaultInstance() : simDetails;
    }

    public boolean hasContactUri() {
        return (this.bitField0_ & 4) == 4;
    }

    public boolean hasPhotoUri() {
        return (this.bitField0_ & 2) == 2;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeFixed64(1, this.photoId_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, this.photoUri_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(3, this.contactUri_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(4, this.nameOrNumber_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeString(6, this.number_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeString(7, this.displayNumber_);
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeString(8, this.numberLabel_);
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeInt32(9, this.contactType_);
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeMessage(10, getSimDetails());
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeString(11, this.postDialDigits_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
