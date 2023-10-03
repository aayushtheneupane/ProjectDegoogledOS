package com.android.dialer.rtt;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.util.List;

public final class RttTranscript extends GeneratedMessageLite<RttTranscript, Builder> implements RttTranscriptOrBuilder {
    /* access modifiers changed from: private */
    public static final RttTranscript DEFAULT_INSTANCE = new RttTranscript();
    private static volatile Parser<RttTranscript> PARSER;
    private int bitField0_;
    private String id_ = "";
    private Internal.ProtobufList<RttTranscriptMessage> messages_ = GeneratedMessageLite.emptyProtobufList();
    private String number_ = "";
    private long timestamp_ = 0;

    /* renamed from: com.android.dialer.rtt.RttTranscript$1 */
    static /* synthetic */ class C05551 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f35xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f35xa1df5c61 = r0
                int[] r0 = f35xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f35xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f35xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f35xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f35xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f35xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f35xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f35xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.rtt.RttTranscript.C05551.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RttTranscript, Builder> implements RttTranscriptOrBuilder {
        /* synthetic */ Builder(C05551 r1) {
            this();
        }

        public Builder addAllMessages(Iterable<? extends RttTranscriptMessage> iterable) {
            copyOnWrite();
            RttTranscript.access$1500((RttTranscript) this.instance, iterable);
            return this;
        }

        public Builder setId(String str) {
            copyOnWrite();
            RttTranscript.access$100((RttTranscript) this.instance, str);
            return this;
        }

        public Builder setNumber(String str) {
            copyOnWrite();
            RttTranscript.access$400((RttTranscript) this.instance, str);
            return this;
        }

        public Builder setTimestamp(long j) {
            copyOnWrite();
            RttTranscript.access$700((RttTranscript) this.instance, j);
            return this;
        }

        private Builder() {
            super(RttTranscript.DEFAULT_INSTANCE);
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private RttTranscript() {
    }

    static /* synthetic */ void access$100(RttTranscript rttTranscript, String str) {
        if (str != null) {
            rttTranscript.bitField0_ |= 1;
            rttTranscript.id_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1500(RttTranscript rttTranscript, Iterable iterable) {
        if (!rttTranscript.messages_.isModifiable()) {
            rttTranscript.messages_ = GeneratedMessageLite.mutableCopy(rttTranscript.messages_);
        }
        AbstractMessageLite.addAll(iterable, rttTranscript.messages_);
    }

    static /* synthetic */ void access$400(RttTranscript rttTranscript, String str) {
        if (str != null) {
            rttTranscript.bitField0_ |= 2;
            rttTranscript.number_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$700(RttTranscript rttTranscript, long j) {
        rttTranscript.bitField0_ |= 4;
        rttTranscript.timestamp_ = j;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static RttTranscript parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RttTranscript) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke.ordinal()) {
            case 0:
                return DEFAULT_INSTANCE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                RttTranscript rttTranscript = (RttTranscript) obj2;
                this.id_ = visitor.visitString((this.bitField0_ & 1) == 1, this.id_, (rttTranscript.bitField0_ & 1) == 1, rttTranscript.id_);
                this.number_ = visitor.visitString((this.bitField0_ & 2) == 2, this.number_, (rttTranscript.bitField0_ & 2) == 2, rttTranscript.number_);
                this.timestamp_ = visitor.visitLong((this.bitField0_ & 4) == 4, this.timestamp_, (rttTranscript.bitField0_ & 4) == 4, rttTranscript.timestamp_);
                this.messages_ = visitor.visitList(this.messages_, rttTranscript.messages_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= rttTranscript.bitField0_;
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
                                this.id_ = readString;
                            } else if (readTag == 18) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.number_ = readString2;
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.timestamp_ = codedInputStream.readRawVarint64();
                            } else if (readTag == 34) {
                                if (!this.messages_.isModifiable()) {
                                    this.messages_ = GeneratedMessageLite.mutableCopy(this.messages_);
                                }
                                this.messages_.add((RttTranscriptMessage) codedInputStream.readMessage(RttTranscriptMessage.parser(), extensionRegistryLite));
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
                this.messages_.makeImmutable();
                return null;
            case 4:
                return new RttTranscript();
            case 5:
                return new Builder((C05551) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (RttTranscript.class) {
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

    public String getId() {
        return this.id_;
    }

    public RttTranscriptMessage getMessages(int i) {
        return this.messages_.get(i);
    }

    public int getMessagesCount() {
        return this.messages_.size();
    }

    public List<RttTranscriptMessage> getMessagesList() {
        return this.messages_;
    }

    public String getNumber() {
        return this.number_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeStringSize(1, this.id_) + 0 : 0;
        if ((this.bitField0_ & 2) == 2) {
            computeStringSize += CodedOutputStream.computeStringSize(2, this.number_);
        }
        if ((this.bitField0_ & 4) == 4) {
            computeStringSize += CodedOutputStream.computeInt64Size(3, this.timestamp_);
        }
        for (int i2 = 0; i2 < this.messages_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(4, this.messages_.get(i2));
        }
        int serializedSize = this.unknownFields.getSerializedSize() + computeStringSize;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public long getTimestamp() {
        return this.timestamp_;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, this.id_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, this.number_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeUInt64(3, this.timestamp_);
        }
        for (int i = 0; i < this.messages_.size(); i++) {
            codedOutputStream.writeMessage(4, this.messages_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
