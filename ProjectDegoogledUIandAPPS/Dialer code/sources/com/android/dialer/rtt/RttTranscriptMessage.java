package com.android.dialer.rtt;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class RttTranscriptMessage extends GeneratedMessageLite<RttTranscriptMessage, Builder> implements RttTranscriptMessageOrBuilder {
    /* access modifiers changed from: private */
    public static final RttTranscriptMessage DEFAULT_INSTANCE = new RttTranscriptMessage();
    private static volatile Parser<RttTranscriptMessage> PARSER;
    private int bitField0_;
    private String content_ = "";
    private boolean isFinished_ = false;
    private boolean isRemote_ = false;
    private long timestamp_ = 0;

    /* renamed from: com.android.dialer.rtt.RttTranscriptMessage$1 */
    static /* synthetic */ class C05561 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f36xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f36xa1df5c61 = r0
                int[] r0 = f36xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f36xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f36xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f36xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f36xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f36xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f36xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f36xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.rtt.RttTranscriptMessage.C05561.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RttTranscriptMessage, Builder> implements RttTranscriptMessageOrBuilder {
        /* synthetic */ Builder(C05561 r1) {
            this();
        }

        public Builder setContent(String str) {
            copyOnWrite();
            RttTranscriptMessage.access$100((RttTranscriptMessage) this.instance, str);
            return this;
        }

        public Builder setIsFinished(boolean z) {
            copyOnWrite();
            RttTranscriptMessage.access$800((RttTranscriptMessage) this.instance, z);
            return this;
        }

        public Builder setIsRemote(boolean z) {
            copyOnWrite();
            RttTranscriptMessage.access$600((RttTranscriptMessage) this.instance, z);
            return this;
        }

        public Builder setTimestamp(long j) {
            copyOnWrite();
            RttTranscriptMessage.access$400((RttTranscriptMessage) this.instance, j);
            return this;
        }

        private Builder() {
            super(RttTranscriptMessage.DEFAULT_INSTANCE);
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private RttTranscriptMessage() {
    }

    static /* synthetic */ void access$100(RttTranscriptMessage rttTranscriptMessage, String str) {
        if (str != null) {
            rttTranscriptMessage.bitField0_ |= 1;
            rttTranscriptMessage.content_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$400(RttTranscriptMessage rttTranscriptMessage, long j) {
        rttTranscriptMessage.bitField0_ |= 2;
        rttTranscriptMessage.timestamp_ = j;
    }

    static /* synthetic */ void access$600(RttTranscriptMessage rttTranscriptMessage, boolean z) {
        rttTranscriptMessage.bitField0_ |= 4;
        rttTranscriptMessage.isRemote_ = z;
    }

    static /* synthetic */ void access$800(RttTranscriptMessage rttTranscriptMessage, boolean z) {
        rttTranscriptMessage.bitField0_ |= 8;
        rttTranscriptMessage.isFinished_ = z;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Parser<RttTranscriptMessage> parser() {
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
                RttTranscriptMessage rttTranscriptMessage = (RttTranscriptMessage) obj2;
                this.content_ = visitor.visitString((this.bitField0_ & 1) == 1, this.content_, (rttTranscriptMessage.bitField0_ & 1) == 1, rttTranscriptMessage.content_);
                this.timestamp_ = visitor.visitLong((this.bitField0_ & 2) == 2, this.timestamp_, (rttTranscriptMessage.bitField0_ & 2) == 2, rttTranscriptMessage.timestamp_);
                this.isRemote_ = visitor.visitBoolean((this.bitField0_ & 4) == 4, this.isRemote_, (rttTranscriptMessage.bitField0_ & 4) == 4, rttTranscriptMessage.isRemote_);
                boolean z2 = (this.bitField0_ & 8) == 8;
                boolean z3 = this.isFinished_;
                if ((rttTranscriptMessage.bitField0_ & 8) == 8) {
                    z = true;
                }
                this.isFinished_ = visitor.visitBoolean(z2, z3, z, rttTranscriptMessage.isFinished_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= rttTranscriptMessage.bitField0_;
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
                                this.content_ = readString;
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.timestamp_ = codedInputStream.readRawVarint64();
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.isRemote_ = codedInputStream.readBool();
                            } else if (readTag == 32) {
                                this.bitField0_ |= 8;
                                this.isFinished_ = codedInputStream.readBool();
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
                return new RttTranscriptMessage();
            case 5:
                return new Builder((C05561) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (RttTranscriptMessage.class) {
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

    public String getContent() {
        return this.content_;
    }

    public boolean getIsFinished() {
        return this.isFinished_;
    }

    public boolean getIsRemote() {
        return this.isRemote_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, this.content_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeInt64Size(2, this.timestamp_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeBoolSize(3, this.isRemote_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeBoolSize(4, this.isFinished_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public long getTimestamp() {
        return this.timestamp_;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, this.content_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeUInt64(2, this.timestamp_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeBool(3, this.isRemote_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeBool(4, this.isFinished_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
