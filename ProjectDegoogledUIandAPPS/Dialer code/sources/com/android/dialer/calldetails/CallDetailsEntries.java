package com.android.dialer.calldetails;

import com.android.dialer.enrichedcall.historyquery.proto.HistoryResult;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.util.List;

public final class CallDetailsEntries extends GeneratedMessageLite<CallDetailsEntries, Builder> implements CallDetailsEntriesOrBuilder {
    /* access modifiers changed from: private */
    public static final CallDetailsEntries DEFAULT_INSTANCE = new CallDetailsEntries();
    private static volatile Parser<CallDetailsEntries> PARSER;
    private Internal.ProtobufList<CallDetailsEntry> entries_ = GeneratedMessageLite.emptyProtobufList();

    /* renamed from: com.android.dialer.calldetails.CallDetailsEntries$1 */
    static /* synthetic */ class C04161 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f14xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f14xa1df5c61 = r0
                int[] r0 = f14xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f14xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f14xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f14xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f14xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f14xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f14xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f14xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calldetails.CallDetailsEntries.C04161.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CallDetailsEntries, Builder> implements CallDetailsEntriesOrBuilder {
        /* synthetic */ Builder(C04161 r1) {
            this();
        }

        public Builder addEntries(CallDetailsEntry callDetailsEntry) {
            copyOnWrite();
            CallDetailsEntries.access$3300((CallDetailsEntries) this.instance, callDetailsEntry);
            return this;
        }

        private Builder() {
            super(CallDetailsEntries.DEFAULT_INSTANCE);
        }
    }

    public static final class CallDetailsEntry extends GeneratedMessageLite<CallDetailsEntry, Builder> implements CallDetailsEntryOrBuilder {
        /* access modifiers changed from: private */
        public static final CallDetailsEntry DEFAULT_INSTANCE = new CallDetailsEntry();
        private static volatile Parser<CallDetailsEntry> PARSER;
        private int bitField0_;
        private long callId_ = 0;
        private String callMappingId_ = "";
        private int callType_ = 0;
        private long dataUsage_ = 0;
        private long date_ = 0;
        private long duration_ = 0;
        private int features_ = 0;
        private boolean hasRttTranscript_ = false;
        private Internal.ProtobufList<HistoryResult> historyResults_ = GeneratedMessageLite.emptyProtobufList();
        private boolean isDuoCall_ = false;

        public static final class Builder extends GeneratedMessageLite.Builder<CallDetailsEntry, Builder> implements CallDetailsEntryOrBuilder {
            /* synthetic */ Builder(C04161 r1) {
                this();
            }

            public Builder setCallId(long j) {
                copyOnWrite();
                CallDetailsEntry.access$100((CallDetailsEntry) this.instance, j);
                return this;
            }

            public Builder setCallMappingId(String str) {
                copyOnWrite();
                CallDetailsEntry.access$2600((CallDetailsEntry) this.instance, str);
                return this;
            }

            public Builder setCallType(int i) {
                copyOnWrite();
                CallDetailsEntry.access$300((CallDetailsEntry) this.instance, i);
                return this;
            }

            public Builder setDataUsage(long j) {
                copyOnWrite();
                CallDetailsEntry.access$1100((CallDetailsEntry) this.instance, j);
                return this;
            }

            public Builder setDate(long j) {
                copyOnWrite();
                CallDetailsEntry.access$700((CallDetailsEntry) this.instance, j);
                return this;
            }

            public Builder setDuration(long j) {
                copyOnWrite();
                CallDetailsEntry.access$900((CallDetailsEntry) this.instance, j);
                return this;
            }

            public Builder setFeatures(int i) {
                copyOnWrite();
                CallDetailsEntry.access$500((CallDetailsEntry) this.instance, i);
                return this;
            }

            public Builder setHasRttTranscript(boolean z) {
                copyOnWrite();
                CallDetailsEntry.access$2400((CallDetailsEntry) this.instance, z);
                return this;
            }

            public Builder setIsDuoCall(boolean z) {
                copyOnWrite();
                CallDetailsEntry.access$2200((CallDetailsEntry) this.instance, z);
                return this;
            }

            private Builder() {
                super(CallDetailsEntry.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CallDetailsEntry() {
        }

        static /* synthetic */ void access$100(CallDetailsEntry callDetailsEntry, long j) {
            callDetailsEntry.bitField0_ |= 1;
            callDetailsEntry.callId_ = j;
        }

        static /* synthetic */ void access$1100(CallDetailsEntry callDetailsEntry, long j) {
            callDetailsEntry.bitField0_ |= 32;
            callDetailsEntry.dataUsage_ = j;
        }

        static /* synthetic */ void access$2200(CallDetailsEntry callDetailsEntry, boolean z) {
            callDetailsEntry.bitField0_ |= 64;
            callDetailsEntry.isDuoCall_ = z;
        }

        static /* synthetic */ void access$2400(CallDetailsEntry callDetailsEntry, boolean z) {
            callDetailsEntry.bitField0_ |= 128;
            callDetailsEntry.hasRttTranscript_ = z;
        }

        static /* synthetic */ void access$2600(CallDetailsEntry callDetailsEntry, String str) {
            if (str != null) {
                callDetailsEntry.bitField0_ |= 256;
                callDetailsEntry.callMappingId_ = str;
                return;
            }
            throw new NullPointerException();
        }

        static /* synthetic */ void access$300(CallDetailsEntry callDetailsEntry, int i) {
            callDetailsEntry.bitField0_ |= 2;
            callDetailsEntry.callType_ = i;
        }

        static /* synthetic */ void access$500(CallDetailsEntry callDetailsEntry, int i) {
            callDetailsEntry.bitField0_ |= 4;
            callDetailsEntry.features_ = i;
        }

        static /* synthetic */ void access$700(CallDetailsEntry callDetailsEntry, long j) {
            callDetailsEntry.bitField0_ |= 8;
            callDetailsEntry.date_ = j;
        }

        static /* synthetic */ void access$900(CallDetailsEntry callDetailsEntry, long j) {
            callDetailsEntry.bitField0_ |= 16;
            callDetailsEntry.duration_ = j;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Parser<CallDetailsEntry> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CallDetailsEntry callDetailsEntry = (CallDetailsEntry) obj2;
                    CallDetailsEntry callDetailsEntry2 = callDetailsEntry;
                    this.callId_ = visitor.visitLong((this.bitField0_ & 1) == 1, this.callId_, (callDetailsEntry.bitField0_ & 1) == 1, callDetailsEntry.callId_);
                    this.callType_ = visitor.visitInt((this.bitField0_ & 2) == 2, this.callType_, (callDetailsEntry2.bitField0_ & 2) == 2, callDetailsEntry2.callType_);
                    this.features_ = visitor.visitInt((this.bitField0_ & 4) == 4, this.features_, (callDetailsEntry2.bitField0_ & 4) == 4, callDetailsEntry2.features_);
                    this.date_ = visitor.visitLong((this.bitField0_ & 8) == 8, this.date_, (callDetailsEntry2.bitField0_ & 8) == 8, callDetailsEntry2.date_);
                    this.duration_ = visitor.visitLong((this.bitField0_ & 16) == 16, this.duration_, (callDetailsEntry2.bitField0_ & 16) == 16, callDetailsEntry2.duration_);
                    this.dataUsage_ = visitor.visitLong((this.bitField0_ & 32) == 32, this.dataUsage_, (callDetailsEntry2.bitField0_ & 32) == 32, callDetailsEntry2.dataUsage_);
                    this.historyResults_ = visitor.visitList(this.historyResults_, callDetailsEntry2.historyResults_);
                    this.isDuoCall_ = visitor.visitBoolean((this.bitField0_ & 64) == 64, this.isDuoCall_, (callDetailsEntry2.bitField0_ & 64) == 64, callDetailsEntry2.isDuoCall_);
                    this.hasRttTranscript_ = visitor.visitBoolean((this.bitField0_ & 128) == 128, this.hasRttTranscript_, (callDetailsEntry2.bitField0_ & 128) == 128, callDetailsEntry2.hasRttTranscript_);
                    this.callMappingId_ = visitor.visitString((this.bitField0_ & 256) == 256, this.callMappingId_, (callDetailsEntry2.bitField0_ & 256) == 256, callDetailsEntry2.callMappingId_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= callDetailsEntry2.bitField0_;
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
                                    this.callId_ = codedInputStream.readRawVarint64();
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.callType_ = codedInputStream.readRawVarint32();
                                    break;
                                case 24:
                                    this.bitField0_ |= 4;
                                    this.features_ = codedInputStream.readRawVarint32();
                                    break;
                                case 32:
                                    this.bitField0_ |= 8;
                                    this.date_ = codedInputStream.readRawVarint64();
                                    break;
                                case 40:
                                    this.bitField0_ |= 16;
                                    this.duration_ = codedInputStream.readRawVarint64();
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.dataUsage_ = codedInputStream.readRawVarint64();
                                    break;
                                case 58:
                                    if (!this.historyResults_.isModifiable()) {
                                        this.historyResults_ = GeneratedMessageLite.mutableCopy(this.historyResults_);
                                    }
                                    this.historyResults_.add((HistoryResult) codedInputStream.readMessage(HistoryResult.parser(), extensionRegistryLite));
                                    break;
                                case 64:
                                    this.bitField0_ |= 64;
                                    this.isDuoCall_ = codedInputStream.readBool();
                                    break;
                                case 72:
                                    this.bitField0_ |= 128;
                                    this.hasRttTranscript_ = codedInputStream.readBool();
                                    break;
                                case 82:
                                    String readString = codedInputStream.readString();
                                    this.bitField0_ |= 256;
                                    this.callMappingId_ = readString;
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
                    this.historyResults_.makeImmutable();
                    return null;
                case 4:
                    return new CallDetailsEntry();
                case 5:
                    return new Builder((C04161) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (CallDetailsEntry.class) {
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

        public long getCallId() {
            return this.callId_;
        }

        public String getCallMappingId() {
            return this.callMappingId_;
        }

        public int getCallType() {
            return this.callType_;
        }

        public long getDataUsage() {
            return this.dataUsage_;
        }

        public long getDate() {
            return this.date_;
        }

        public long getDuration() {
            return this.duration_;
        }

        public int getFeatures() {
            return this.features_;
        }

        public boolean getHasRttTranscript() {
            return this.hasRttTranscript_;
        }

        public HistoryResult getHistoryResults(int i) {
            return this.historyResults_.get(i);
        }

        public int getHistoryResultsCount() {
            return this.historyResults_.size();
        }

        public List<HistoryResult> getHistoryResultsList() {
            return this.historyResults_;
        }

        public boolean getIsDuoCall() {
            return this.isDuoCall_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeInt64Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt64Size(1, this.callId_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeInt64Size += CodedOutputStream.computeInt32Size(2, this.callType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeInt64Size += CodedOutputStream.computeInt32Size(3, this.features_);
            }
            if ((this.bitField0_ & 8) == 8) {
                computeInt64Size += CodedOutputStream.computeInt64Size(4, this.date_);
            }
            if ((this.bitField0_ & 16) == 16) {
                computeInt64Size += CodedOutputStream.computeInt64Size(5, this.duration_);
            }
            if ((this.bitField0_ & 32) == 32) {
                computeInt64Size += CodedOutputStream.computeInt64Size(6, this.dataUsage_);
            }
            for (int i2 = 0; i2 < this.historyResults_.size(); i2++) {
                computeInt64Size += CodedOutputStream.computeMessageSize(7, this.historyResults_.get(i2));
            }
            if ((this.bitField0_ & 64) == 64) {
                computeInt64Size += CodedOutputStream.computeBoolSize(8, this.isDuoCall_);
            }
            if ((this.bitField0_ & 128) == 128) {
                computeInt64Size += CodedOutputStream.computeBoolSize(9, this.hasRttTranscript_);
            }
            if ((this.bitField0_ & 256) == 256) {
                computeInt64Size += CodedOutputStream.computeStringSize(10, this.callMappingId_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + computeInt64Size;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeUInt64(1, this.callId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.callType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(3, this.features_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeUInt64(4, this.date_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeUInt64(5, this.duration_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeUInt64(6, this.dataUsage_);
            }
            for (int i = 0; i < this.historyResults_.size(); i++) {
                codedOutputStream.writeMessage(7, this.historyResults_.get(i));
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeBool(8, this.isDuoCall_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeBool(9, this.hasRttTranscript_);
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeString(10, this.callMappingId_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface CallDetailsEntryOrBuilder extends MessageLiteOrBuilder {
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private CallDetailsEntries() {
    }

    static /* synthetic */ void access$3300(CallDetailsEntries callDetailsEntries, CallDetailsEntry callDetailsEntry) {
        if (callDetailsEntry != null) {
            if (!callDetailsEntries.entries_.isModifiable()) {
                callDetailsEntries.entries_ = GeneratedMessageLite.mutableCopy(callDetailsEntries.entries_);
            }
            callDetailsEntries.entries_.add(callDetailsEntry);
            return;
        }
        throw new NullPointerException();
    }

    public static CallDetailsEntries getDefaultInstance() {
        return DEFAULT_INSTANCE;
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
                this.entries_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.entries_, ((CallDetailsEntries) obj2).entries_);
                return this;
            case 2:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!this.entries_.isModifiable()) {
                                    this.entries_ = GeneratedMessageLite.mutableCopy(this.entries_);
                                }
                                this.entries_.add((CallDetailsEntry) codedInputStream.readMessage(CallDetailsEntry.parser(), extensionRegistryLite));
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
                this.entries_.makeImmutable();
                return null;
            case 4:
                return new CallDetailsEntries();
            case 5:
                return new Builder((C04161) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (CallDetailsEntries.class) {
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

    public CallDetailsEntry getEntries(int i) {
        return this.entries_.get(i);
    }

    public int getEntriesCount() {
        return this.entries_.size();
    }

    public List<CallDetailsEntry> getEntriesList() {
        return this.entries_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.entries_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.entries_.get(i3));
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.entries_.size(); i++) {
            codedOutputStream.writeMessage(1, this.entries_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
