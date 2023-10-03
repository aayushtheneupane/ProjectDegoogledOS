package com.android.settings.intelligence;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.util.List;

public final class ContextualCardProto$ContextualCardList extends GeneratedMessageLite<ContextualCardProto$ContextualCardList, Builder> implements ContextualCardProto$ContextualCardListOrBuilder {
    /* access modifiers changed from: private */
    public static final ContextualCardProto$ContextualCardList DEFAULT_INSTANCE = new ContextualCardProto$ContextualCardList();
    private static volatile Parser<ContextualCardProto$ContextualCardList> PARSER;
    private Internal.ProtobufList<ContextualCardProto$ContextualCard> card_ = GeneratedMessageLite.emptyProtobufList();

    private ContextualCardProto$ContextualCardList() {
    }

    public List<ContextualCardProto$ContextualCard> getCardList() {
        return this.card_;
    }

    private void ensureCardIsMutable() {
        if (!this.card_.isModifiable()) {
            this.card_ = GeneratedMessageLite.mutableCopy(this.card_);
        }
    }

    /* access modifiers changed from: private */
    public void addCard(ContextualCardProto$ContextualCard contextualCardProto$ContextualCard) {
        if (contextualCardProto$ContextualCard != null) {
            ensureCardIsMutable();
            this.card_.add(contextualCardProto$ContextualCard);
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.card_.size(); i++) {
            codedOutputStream.writeMessage(1, this.card_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.card_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, this.card_.get(i3));
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ContextualCardProto$ContextualCardList, Builder> implements ContextualCardProto$ContextualCardListOrBuilder {
        /* synthetic */ Builder(ContextualCardProto$1 contextualCardProto$1) {
            this();
        }

        private Builder() {
            super(ContextualCardProto$ContextualCardList.DEFAULT_INSTANCE);
        }

        public Builder addCard(ContextualCardProto$ContextualCard contextualCardProto$ContextualCard) {
            copyOnWrite();
            ((ContextualCardProto$ContextualCardList) this.instance).addCard(contextualCardProto$ContextualCard);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ContextualCardProto$1.f49xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new ContextualCardProto$ContextualCardList();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.card_.makeImmutable();
                return null;
            case 4:
                return new Builder((ContextualCardProto$1) null);
            case 5:
                this.card_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.card_, ((ContextualCardProto$ContextualCardList) obj2).card_);
                GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
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
                                if (!this.card_.isModifiable()) {
                                    this.card_ = GeneratedMessageLite.mutableCopy(this.card_);
                                }
                                this.card_.add((ContextualCardProto$ContextualCard) codedInputStream.readMessage(ContextualCardProto$ContextualCard.parser(), extensionRegistryLite));
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
                    synchronized (ContextualCardProto$ContextualCardList.class) {
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
}
