package com.android.dialer;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class DialerPhoneNumber extends GeneratedMessageLite<DialerPhoneNumber, Builder> implements DialerPhoneNumberOrBuilder {
    /* access modifiers changed from: private */
    public static final DialerPhoneNumber DEFAULT_INSTANCE = new DialerPhoneNumber();
    private static volatile Parser<DialerPhoneNumber> PARSER;
    private int bitField0_;
    private String countryIso_ = "";
    private boolean isValid_ = false;
    private String normalizedNumber_ = "";
    private String postDialPortion_ = "";

    /* renamed from: com.android.dialer.DialerPhoneNumber$1 */
    static /* synthetic */ class C02771 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f9xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f9xa1df5c61 = r0
                int[] r0 = f9xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f9xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f9xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f9xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f9xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f9xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f9xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f9xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.DialerPhoneNumber.C02771.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DialerPhoneNumber, Builder> implements DialerPhoneNumberOrBuilder {
        /* synthetic */ Builder(C02771 r1) {
            this();
        }

        public Builder setCountryIso(String str) {
            copyOnWrite();
            DialerPhoneNumber.access$400((DialerPhoneNumber) this.instance, str);
            return this;
        }

        public Builder setIsValid(boolean z) {
            copyOnWrite();
            DialerPhoneNumber.access$700((DialerPhoneNumber) this.instance, z);
            return this;
        }

        public Builder setNormalizedNumber(String str) {
            copyOnWrite();
            DialerPhoneNumber.access$100((DialerPhoneNumber) this.instance, str);
            return this;
        }

        public Builder setPostDialPortion(String str) {
            copyOnWrite();
            DialerPhoneNumber.access$900((DialerPhoneNumber) this.instance, str);
            return this;
        }

        private Builder() {
            super(DialerPhoneNumber.DEFAULT_INSTANCE);
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private DialerPhoneNumber() {
    }

    static /* synthetic */ void access$100(DialerPhoneNumber dialerPhoneNumber, String str) {
        if (str != null) {
            dialerPhoneNumber.bitField0_ |= 1;
            dialerPhoneNumber.normalizedNumber_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$400(DialerPhoneNumber dialerPhoneNumber, String str) {
        if (str != null) {
            dialerPhoneNumber.bitField0_ |= 2;
            dialerPhoneNumber.countryIso_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$700(DialerPhoneNumber dialerPhoneNumber, boolean z) {
        dialerPhoneNumber.bitField0_ |= 4;
        dialerPhoneNumber.isValid_ = z;
    }

    static /* synthetic */ void access$900(DialerPhoneNumber dialerPhoneNumber, String str) {
        if (str != null) {
            dialerPhoneNumber.bitField0_ |= 8;
            dialerPhoneNumber.postDialPortion_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public static DialerPhoneNumber getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static DialerPhoneNumber parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DialerPhoneNumber) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Parser<DialerPhoneNumber> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke.ordinal()) {
            case 0:
                return DEFAULT_INSTANCE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                DialerPhoneNumber dialerPhoneNumber = (DialerPhoneNumber) obj2;
                this.normalizedNumber_ = visitor.visitString((this.bitField0_ & 1) == 1, this.normalizedNumber_, (dialerPhoneNumber.bitField0_ & 1) == 1, dialerPhoneNumber.normalizedNumber_);
                this.countryIso_ = visitor.visitString((this.bitField0_ & 2) == 2, this.countryIso_, (dialerPhoneNumber.bitField0_ & 2) == 2, dialerPhoneNumber.countryIso_);
                this.isValid_ = visitor.visitBoolean((this.bitField0_ & 4) == 4, this.isValid_, (dialerPhoneNumber.bitField0_ & 4) == 4, dialerPhoneNumber.isValid_);
                boolean z2 = (this.bitField0_ & 8) == 8;
                String str = this.postDialPortion_;
                if ((dialerPhoneNumber.bitField0_ & 8) == 8) {
                    z = true;
                }
                this.postDialPortion_ = visitor.visitString(z2, str, z, dialerPhoneNumber.postDialPortion_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= dialerPhoneNumber.bitField0_;
                }
                return this;
            case 2:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 1;
                                this.normalizedNumber_ = readString;
                            } else if (readTag == 18) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.countryIso_ = readString2;
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.isValid_ = codedInputStream.readBool();
                            } else if (readTag == 34) {
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 8;
                                this.postDialPortion_ = readString3;
                            } else if (!parseUnknownField(readTag, codedInputStream)) {
                            }
                        }
                        z = true;
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
                return new DialerPhoneNumber();
            case 5:
                return new Builder((C02771) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (DialerPhoneNumber.class) {
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

    public String getCountryIso() {
        return this.countryIso_;
    }

    public boolean getIsValid() {
        return this.isValid_;
    }

    public String getNormalizedNumber() {
        return this.normalizedNumber_;
    }

    public String getPostDialPortion() {
        return this.postDialPortion_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, this.normalizedNumber_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, this.countryIso_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBoolSize(3, this.isValid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeStringSize(4, this.postDialPortion_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, this.normalizedNumber_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, this.countryIso_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBool(3, this.isValid_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(4, this.postDialPortion_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
