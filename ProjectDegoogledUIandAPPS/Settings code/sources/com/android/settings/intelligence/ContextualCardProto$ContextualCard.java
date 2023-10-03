package com.android.settings.intelligence;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class ContextualCardProto$ContextualCard extends GeneratedMessageLite<ContextualCardProto$ContextualCard, Builder> implements ContextualCardProto$ContextualCardOrBuilder {
    /* access modifiers changed from: private */
    public static final ContextualCardProto$ContextualCard DEFAULT_INSTANCE = new ContextualCardProto$ContextualCard();
    private static volatile Parser<ContextualCardProto$ContextualCard> PARSER;
    private int bitField0_;
    private int cardCategory_ = 0;
    private String cardName_ = "";
    private double cardScore_ = 0.0d;
    private String sliceUri_ = "";

    private ContextualCardProto$ContextualCard() {
    }

    public enum Category implements Internal.EnumLite {
        DEFAULT(0),
        SUGGESTION(1),
        POSSIBLE(2),
        IMPORTANT(3),
        DEFERRED_SETUP(5);
        
        private static final Internal.EnumLiteMap<Category> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<Category>() {
            };
        }

        public final int getNumber() {
            return this.value;
        }

        public static Category forNumber(int i) {
            if (i == 0) {
                return DEFAULT;
            }
            if (i == 1) {
                return SUGGESTION;
            }
            if (i == 2) {
                return POSSIBLE;
            }
            if (i == 3) {
                return IMPORTANT;
            }
            if (i != 5) {
                return null;
            }
            return DEFERRED_SETUP;
        }

        private Category(int i) {
            this.value = i;
        }
    }

    public boolean hasSliceUri() {
        return (this.bitField0_ & 1) == 1;
    }

    public String getSliceUri() {
        return this.sliceUri_;
    }

    /* access modifiers changed from: private */
    public void setSliceUri(String str) {
        if (str != null) {
            this.bitField0_ |= 1;
            this.sliceUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasCardName() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getCardName() {
        return this.cardName_;
    }

    /* access modifiers changed from: private */
    public void setCardName(String str) {
        if (str != null) {
            this.bitField0_ |= 2;
            this.cardName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasCardCategory() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    public void setCardCategory(Category category) {
        if (category != null) {
            this.bitField0_ |= 4;
            this.cardCategory_ = category.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasCardScore() {
        return (this.bitField0_ & 8) == 8;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getSliceUri());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(3, getCardName());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeEnum(4, this.cardCategory_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeDouble(5, this.cardScore_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getSliceUri());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(3, getCardName());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeEnumSize(4, this.cardCategory_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeDoubleSize(5, this.cardScore_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ContextualCardProto$ContextualCard, Builder> implements ContextualCardProto$ContextualCardOrBuilder {
        /* synthetic */ Builder(ContextualCardProto$1 contextualCardProto$1) {
            this();
        }

        private Builder() {
            super(ContextualCardProto$ContextualCard.DEFAULT_INSTANCE);
        }

        public Builder setSliceUri(String str) {
            copyOnWrite();
            ((ContextualCardProto$ContextualCard) this.instance).setSliceUri(str);
            return this;
        }

        public Builder setCardName(String str) {
            copyOnWrite();
            ((ContextualCardProto$ContextualCard) this.instance).setCardName(str);
            return this;
        }

        public Builder setCardCategory(Category category) {
            copyOnWrite();
            ((ContextualCardProto$ContextualCard) this.instance).setCardCategory(category);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ContextualCardProto$1.f49xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new ContextualCardProto$ContextualCard();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                return null;
            case 4:
                return new Builder((ContextualCardProto$1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                ContextualCardProto$ContextualCard contextualCardProto$ContextualCard = (ContextualCardProto$ContextualCard) obj2;
                this.sliceUri_ = visitor.visitString(hasSliceUri(), this.sliceUri_, contextualCardProto$ContextualCard.hasSliceUri(), contextualCardProto$ContextualCard.sliceUri_);
                this.cardName_ = visitor.visitString(hasCardName(), this.cardName_, contextualCardProto$ContextualCard.hasCardName(), contextualCardProto$ContextualCard.cardName_);
                this.cardCategory_ = visitor.visitInt(hasCardCategory(), this.cardCategory_, contextualCardProto$ContextualCard.hasCardCategory(), contextualCardProto$ContextualCard.cardCategory_);
                this.cardScore_ = visitor.visitDouble(hasCardScore(), this.cardScore_, contextualCardProto$ContextualCard.hasCardScore(), contextualCardProto$ContextualCard.cardScore_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= contextualCardProto$ContextualCard.bitField0_;
                }
                return this;
            case 6:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ = 1 | this.bitField0_;
                                this.sliceUri_ = readString;
                            } else if (readTag == 26) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.cardName_ = readString2;
                            } else if (readTag == 32) {
                                int readEnum = codedInputStream.readEnum();
                                if (Category.forNumber(readEnum) == null) {
                                    super.mergeVarintField(4, readEnum);
                                } else {
                                    this.bitField0_ |= 4;
                                    this.cardCategory_ = readEnum;
                                }
                            } else if (readTag == 41) {
                                this.bitField0_ |= 8;
                                this.cardScore_ = codedInputStream.readDouble();
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
            case 7:
                break;
            case 8:
                if (PARSER == null) {
                    synchronized (ContextualCardProto$ContextualCard.class) {
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

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static Parser<ContextualCardProto$ContextualCard> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
