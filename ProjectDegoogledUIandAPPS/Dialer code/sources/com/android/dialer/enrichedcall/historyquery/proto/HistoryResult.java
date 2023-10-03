package com.android.dialer.enrichedcall.historyquery.proto;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class HistoryResult extends GeneratedMessageLite<HistoryResult, Builder> implements HistoryResultOrBuilder {
    /* access modifiers changed from: private */
    public static final HistoryResult DEFAULT_INSTANCE = new HistoryResult();
    private static volatile Parser<HistoryResult> PARSER;
    private int bitField0_;
    private String imageContentType_ = "";
    private String imageUri_ = "";
    private String text_ = "";
    private long timestamp_ = 0;
    private int type_ = 1;

    /* renamed from: com.android.dialer.enrichedcall.historyquery.proto.HistoryResult$1 */
    static /* synthetic */ class C04821 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f24xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f24xa1df5c61 = r0
                int[] r0 = f24xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f24xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f24xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f24xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f24xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f24xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f24xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f24xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.enrichedcall.historyquery.proto.HistoryResult.C04821.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HistoryResult, Builder> implements HistoryResultOrBuilder {
        /* synthetic */ Builder(C04821 r1) {
            this();
        }

        private Builder() {
            super(HistoryResult.DEFAULT_INSTANCE);
        }
    }

    public enum Type implements Internal.EnumLite {
        INCOMING_CALL_COMPOSER(1),
        OUTGOING_CALL_COMPOSER(2),
        INCOMING_POST_CALL(3),
        OUTGOING_POST_CALL(4);
        
        private final int value;

        private Type(int i) {
            this.value = i;
        }

        public static Type forNumber(int i) {
            if (i == 1) {
                return INCOMING_CALL_COMPOSER;
            }
            if (i == 2) {
                return OUTGOING_CALL_COMPOSER;
            }
            if (i == 3) {
                return INCOMING_POST_CALL;
            }
            if (i != 4) {
                return null;
            }
            return OUTGOING_POST_CALL;
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private HistoryResult() {
    }

    public static Parser<HistoryResult> parser() {
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
                HistoryResult historyResult = (HistoryResult) obj2;
                this.type_ = visitor.visitInt((this.bitField0_ & 1) == 1, this.type_, (historyResult.bitField0_ & 1) == 1, historyResult.type_);
                this.text_ = visitor.visitString((this.bitField0_ & 2) == 2, this.text_, (historyResult.bitField0_ & 2) == 2, historyResult.text_);
                this.imageUri_ = visitor.visitString((this.bitField0_ & 4) == 4, this.imageUri_, (historyResult.bitField0_ & 4) == 4, historyResult.imageUri_);
                this.imageContentType_ = visitor.visitString((this.bitField0_ & 8) == 8, this.imageContentType_, (historyResult.bitField0_ & 8) == 8, historyResult.imageContentType_);
                boolean z2 = (this.bitField0_ & 16) == 16;
                long j = this.timestamp_;
                if ((historyResult.bitField0_ & 16) == 16) {
                    z = true;
                }
                this.timestamp_ = visitor.visitLong(z2, j, z, historyResult.timestamp_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= historyResult.bitField0_;
                }
                return this;
            case 2:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                int readRawVarint32 = codedInputStream.readRawVarint32();
                                if (Type.forNumber(readRawVarint32) == null) {
                                    super.mergeVarintField(1, readRawVarint32);
                                } else {
                                    this.bitField0_ |= 1;
                                    this.type_ = readRawVarint32;
                                }
                            } else if (readTag == 18) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.text_ = readString;
                            } else if (readTag == 34) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.imageUri_ = readString2;
                            } else if (readTag == 42) {
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 8;
                                this.imageContentType_ = readString3;
                            } else if (readTag == 56) {
                                this.bitField0_ |= 16;
                                this.timestamp_ = codedInputStream.readRawVarint64();
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
                return new HistoryResult();
            case 5:
                return new Builder((C04821) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (HistoryResult.class) {
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

    public String getImageUri() {
        return this.imageUri_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, this.text_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(4, this.imageUri_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeStringSize(5, this.imageContentType_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeInt64Size(7, this.timestamp_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public String getText() {
        return this.text_;
    }

    public Type getType() {
        Type forNumber = Type.forNumber(this.type_);
        return forNumber == null ? Type.INCOMING_CALL_COMPOSER : forNumber;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt32(1, this.type_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, this.text_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(4, this.imageUri_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(5, this.imageContentType_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeUInt64(7, this.timestamp_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
