package com.android.dialer.callintent;

import com.android.dialer.logging.UiAction$Type;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.Internal$ListAdapter$Converter;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.util.Iterator;

public final class CallSpecificAppData extends GeneratedMessageLite<CallSpecificAppData, Builder> implements CallSpecificAppDataOrBuilder {
    /* access modifiers changed from: private */
    public static final CallSpecificAppData DEFAULT_INSTANCE = new CallSpecificAppData();
    private static volatile Parser<CallSpecificAppData> PARSER;
    private boolean allowAssistedDialing_ = false;
    private int bitField0_;
    private int callInitiationType_ = 0;
    private int charactersInSearchString_ = 0;
    private int lightbringerButtonAppearInCollapsedCallLogItemCount_ = 0;
    private int lightbringerButtonAppearInExpandedCallLogItemCount_ = 0;
    private int lightbringerButtonAppearInSearchCount_ = 0;
    private int positionOfSelectedSearchResult_ = 0;
    private int speedDialContactPosition_ = 0;
    private Internal.IntList speedDialContactType_ = GeneratedMessageLite.emptyIntList();
    private int startingTabIndex_ = 0;
    private long timeSinceAppLaunch_ = 0;
    private long timeSinceFirstClick_ = 0;
    private Internal.LongList uiActionTimestampsSinceAppLaunch_ = GeneratedMessageLite.emptyLongList();
    private Internal.IntList uiActionsSinceAppLaunch_ = GeneratedMessageLite.emptyIntList();

    /* renamed from: com.android.dialer.callintent.CallSpecificAppData$1 */
    class C04211 implements Internal$ListAdapter$Converter<Integer, SpeedDialContactType$Type> {
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CallSpecificAppData, Builder> implements CallSpecificAppDataOrBuilder {
        /* synthetic */ Builder(C04211 r1) {
            this();
        }

        public Builder addAllUiActionTimestampsSinceAppLaunch(Iterable<? extends Long> iterable) {
            copyOnWrite();
            CallSpecificAppData.access$2300((CallSpecificAppData) this.instance, iterable);
            return this;
        }

        public Builder addAllUiActionsSinceAppLaunch(Iterable<? extends UiAction$Type> iterable) {
            copyOnWrite();
            CallSpecificAppData.access$1900((CallSpecificAppData) this.instance, iterable);
            return this;
        }

        public Builder addSpeedDialContactType(SpeedDialContactType$Type speedDialContactType$Type) {
            copyOnWrite();
            CallSpecificAppData.access$800((CallSpecificAppData) this.instance, speedDialContactType$Type);
            return this;
        }

        public Builder setAllowAssistedDialing(boolean z) {
            copyOnWrite();
            CallSpecificAppData.access$3300((CallSpecificAppData) this.instance, z);
            return this;
        }

        public Builder setCallInitiationType(CallInitiationType$Type callInitiationType$Type) {
            copyOnWrite();
            CallSpecificAppData.access$100((CallSpecificAppData) this.instance, callInitiationType$Type);
            return this;
        }

        public Builder setCharactersInSearchString(int i) {
            copyOnWrite();
            CallSpecificAppData.access$500((CallSpecificAppData) this.instance, i);
            return this;
        }

        public Builder setLightbringerButtonAppearInCollapsedCallLogItemCount(int i) {
            copyOnWrite();
            CallSpecificAppData.access$2900((CallSpecificAppData) this.instance, i);
            return this;
        }

        public Builder setLightbringerButtonAppearInExpandedCallLogItemCount(int i) {
            copyOnWrite();
            CallSpecificAppData.access$2700((CallSpecificAppData) this.instance, i);
            return this;
        }

        public Builder setLightbringerButtonAppearInSearchCount(int i) {
            copyOnWrite();
            CallSpecificAppData.access$3100((CallSpecificAppData) this.instance, i);
            return this;
        }

        public Builder setPositionOfSelectedSearchResult(int i) {
            copyOnWrite();
            CallSpecificAppData.access$300((CallSpecificAppData) this.instance, i);
            return this;
        }

        public Builder setSpeedDialContactPosition(int i) {
            copyOnWrite();
            CallSpecificAppData.access$1100((CallSpecificAppData) this.instance, i);
            return this;
        }

        public Builder setStartingTabIndex(int i) {
            copyOnWrite();
            CallSpecificAppData.access$2500((CallSpecificAppData) this.instance, i);
            return this;
        }

        public Builder setTimeSinceAppLaunch(long j) {
            copyOnWrite();
            CallSpecificAppData.access$1300((CallSpecificAppData) this.instance, j);
            return this;
        }

        public Builder setTimeSinceFirstClick(long j) {
            copyOnWrite();
            CallSpecificAppData.access$1500((CallSpecificAppData) this.instance, j);
            return this;
        }

        private Builder() {
            super(CallSpecificAppData.DEFAULT_INSTANCE);
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private CallSpecificAppData() {
    }

    static /* synthetic */ void access$100(CallSpecificAppData callSpecificAppData, CallInitiationType$Type callInitiationType$Type) {
        if (callInitiationType$Type != null) {
            callSpecificAppData.bitField0_ |= 1;
            callSpecificAppData.callInitiationType_ = callInitiationType$Type.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1100(CallSpecificAppData callSpecificAppData, int i) {
        callSpecificAppData.bitField0_ |= 8;
        callSpecificAppData.speedDialContactPosition_ = i;
    }

    static /* synthetic */ void access$1300(CallSpecificAppData callSpecificAppData, long j) {
        callSpecificAppData.bitField0_ |= 16;
        callSpecificAppData.timeSinceAppLaunch_ = j;
    }

    static /* synthetic */ void access$1500(CallSpecificAppData callSpecificAppData, long j) {
        callSpecificAppData.bitField0_ |= 32;
        callSpecificAppData.timeSinceFirstClick_ = j;
    }

    static /* synthetic */ void access$1900(CallSpecificAppData callSpecificAppData, Iterable iterable) {
        if (!callSpecificAppData.uiActionsSinceAppLaunch_.isModifiable()) {
            callSpecificAppData.uiActionsSinceAppLaunch_ = GeneratedMessageLite.mutableCopy(callSpecificAppData.uiActionsSinceAppLaunch_);
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            callSpecificAppData.uiActionsSinceAppLaunch_.addInt(((UiAction$Type) it.next()).getNumber());
        }
    }

    static /* synthetic */ void access$2300(CallSpecificAppData callSpecificAppData, Iterable iterable) {
        if (!callSpecificAppData.uiActionTimestampsSinceAppLaunch_.isModifiable()) {
            callSpecificAppData.uiActionTimestampsSinceAppLaunch_ = GeneratedMessageLite.mutableCopy(callSpecificAppData.uiActionTimestampsSinceAppLaunch_);
        }
        AbstractMessageLite.addAll(iterable, callSpecificAppData.uiActionTimestampsSinceAppLaunch_);
    }

    static /* synthetic */ void access$2500(CallSpecificAppData callSpecificAppData, int i) {
        callSpecificAppData.bitField0_ |= 64;
        callSpecificAppData.startingTabIndex_ = i;
    }

    static /* synthetic */ void access$2700(CallSpecificAppData callSpecificAppData, int i) {
        callSpecificAppData.bitField0_ |= 128;
        callSpecificAppData.lightbringerButtonAppearInExpandedCallLogItemCount_ = i;
    }

    static /* synthetic */ void access$2900(CallSpecificAppData callSpecificAppData, int i) {
        callSpecificAppData.bitField0_ |= 256;
        callSpecificAppData.lightbringerButtonAppearInCollapsedCallLogItemCount_ = i;
    }

    static /* synthetic */ void access$300(CallSpecificAppData callSpecificAppData, int i) {
        callSpecificAppData.bitField0_ |= 2;
        callSpecificAppData.positionOfSelectedSearchResult_ = i;
    }

    static /* synthetic */ void access$3100(CallSpecificAppData callSpecificAppData, int i) {
        callSpecificAppData.bitField0_ |= 512;
        callSpecificAppData.lightbringerButtonAppearInSearchCount_ = i;
    }

    static /* synthetic */ void access$3300(CallSpecificAppData callSpecificAppData, boolean z) {
        callSpecificAppData.bitField0_ |= 1024;
        callSpecificAppData.allowAssistedDialing_ = z;
    }

    static /* synthetic */ void access$500(CallSpecificAppData callSpecificAppData, int i) {
        callSpecificAppData.bitField0_ |= 4;
        callSpecificAppData.charactersInSearchString_ = i;
    }

    static /* synthetic */ void access$800(CallSpecificAppData callSpecificAppData, SpeedDialContactType$Type speedDialContactType$Type) {
        if (speedDialContactType$Type != null) {
            if (!callSpecificAppData.speedDialContactType_.isModifiable()) {
                callSpecificAppData.speedDialContactType_ = GeneratedMessageLite.mutableCopy(callSpecificAppData.speedDialContactType_);
            }
            callSpecificAppData.speedDialContactType_.addInt(speedDialContactType$Type.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    public static CallSpecificAppData getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static CallSpecificAppData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CallSpecificAppData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        int i = 1024;
        int i2 = 512;
        boolean z = true;
        switch (methodToInvoke.ordinal()) {
            case 0:
                return DEFAULT_INSTANCE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                CallSpecificAppData callSpecificAppData = (CallSpecificAppData) obj2;
                this.callInitiationType_ = visitor.visitInt((this.bitField0_ & 1) == 1, this.callInitiationType_, (callSpecificAppData.bitField0_ & 1) == 1, callSpecificAppData.callInitiationType_);
                this.positionOfSelectedSearchResult_ = visitor.visitInt((this.bitField0_ & 2) == 2, this.positionOfSelectedSearchResult_, (callSpecificAppData.bitField0_ & 2) == 2, callSpecificAppData.positionOfSelectedSearchResult_);
                this.charactersInSearchString_ = visitor.visitInt((this.bitField0_ & 4) == 4, this.charactersInSearchString_, (callSpecificAppData.bitField0_ & 4) == 4, callSpecificAppData.charactersInSearchString_);
                this.speedDialContactType_ = visitor.visitIntList(this.speedDialContactType_, callSpecificAppData.speedDialContactType_);
                this.speedDialContactPosition_ = visitor.visitInt((this.bitField0_ & 8) == 8, this.speedDialContactPosition_, (callSpecificAppData.bitField0_ & 8) == 8, callSpecificAppData.speedDialContactPosition_);
                this.timeSinceAppLaunch_ = visitor.visitLong((this.bitField0_ & 16) == 16, this.timeSinceAppLaunch_, (callSpecificAppData.bitField0_ & 16) == 16, callSpecificAppData.timeSinceAppLaunch_);
                this.timeSinceFirstClick_ = visitor.visitLong((this.bitField0_ & 32) == 32, this.timeSinceFirstClick_, (callSpecificAppData.bitField0_ & 32) == 32, callSpecificAppData.timeSinceFirstClick_);
                this.uiActionsSinceAppLaunch_ = visitor.visitIntList(this.uiActionsSinceAppLaunch_, callSpecificAppData.uiActionsSinceAppLaunch_);
                this.uiActionTimestampsSinceAppLaunch_ = visitor.visitLongList(this.uiActionTimestampsSinceAppLaunch_, callSpecificAppData.uiActionTimestampsSinceAppLaunch_);
                this.startingTabIndex_ = visitor.visitInt((this.bitField0_ & 64) == 64, this.startingTabIndex_, (callSpecificAppData.bitField0_ & 64) == 64, callSpecificAppData.startingTabIndex_);
                this.lightbringerButtonAppearInExpandedCallLogItemCount_ = visitor.visitInt((this.bitField0_ & 128) == 128, this.lightbringerButtonAppearInExpandedCallLogItemCount_, (callSpecificAppData.bitField0_ & 128) == 128, callSpecificAppData.lightbringerButtonAppearInExpandedCallLogItemCount_);
                this.lightbringerButtonAppearInCollapsedCallLogItemCount_ = visitor.visitInt((this.bitField0_ & 256) == 256, this.lightbringerButtonAppearInCollapsedCallLogItemCount_, (callSpecificAppData.bitField0_ & 256) == 256, callSpecificAppData.lightbringerButtonAppearInCollapsedCallLogItemCount_);
                this.lightbringerButtonAppearInSearchCount_ = visitor.visitInt((this.bitField0_ & 512) == 512, this.lightbringerButtonAppearInSearchCount_, (callSpecificAppData.bitField0_ & 512) == 512, callSpecificAppData.lightbringerButtonAppearInSearchCount_);
                boolean z2 = (this.bitField0_ & 1024) == 1024;
                boolean z3 = this.allowAssistedDialing_;
                if ((callSpecificAppData.bitField0_ & 1024) != 1024) {
                    z = false;
                }
                this.allowAssistedDialing_ = visitor.visitBoolean(z2, z3, z, callSpecificAppData.allowAssistedDialing_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= callSpecificAppData.bitField0_;
                }
                return this;
            case 2:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z4 = false;
                while (!z4) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z4 = true;
                                break;
                            case 8:
                                int readRawVarint32 = codedInputStream.readRawVarint32();
                                if (CallInitiationType$Type.forNumber(readRawVarint32) != null) {
                                    this.bitField0_ |= 1;
                                    this.callInitiationType_ = readRawVarint32;
                                    break;
                                } else {
                                    super.mergeVarintField(1, readRawVarint32);
                                    break;
                                }
                            case 16:
                                this.bitField0_ |= 2;
                                this.positionOfSelectedSearchResult_ = codedInputStream.readRawVarint32();
                                break;
                            case 24:
                                this.bitField0_ |= 4;
                                this.charactersInSearchString_ = codedInputStream.readRawVarint32();
                                break;
                            case 32:
                                if (!this.speedDialContactType_.isModifiable()) {
                                    this.speedDialContactType_ = GeneratedMessageLite.mutableCopy(this.speedDialContactType_);
                                }
                                int readRawVarint322 = codedInputStream.readRawVarint32();
                                if (SpeedDialContactType$Type.forNumber(readRawVarint322) != null) {
                                    this.speedDialContactType_.addInt(readRawVarint322);
                                    break;
                                } else {
                                    super.mergeVarintField(4, readRawVarint322);
                                    break;
                                }
                            case 34:
                                if (!this.speedDialContactType_.isModifiable()) {
                                    this.speedDialContactType_ = GeneratedMessageLite.mutableCopy(this.speedDialContactType_);
                                }
                                int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    int readRawVarint323 = codedInputStream.readRawVarint32();
                                    if (SpeedDialContactType$Type.forNumber(readRawVarint323) == null) {
                                        super.mergeVarintField(4, readRawVarint323);
                                    } else {
                                        this.speedDialContactType_.addInt(readRawVarint323);
                                    }
                                }
                                codedInputStream.popLimit(pushLimit);
                                break;
                            case 40:
                                this.bitField0_ |= 8;
                                this.speedDialContactPosition_ = codedInputStream.readRawVarint32();
                                break;
                            case 48:
                                this.bitField0_ |= 16;
                                this.timeSinceAppLaunch_ = codedInputStream.readRawVarint64();
                                break;
                            case 56:
                                this.bitField0_ |= 32;
                                this.timeSinceFirstClick_ = codedInputStream.readRawVarint64();
                                break;
                            case 64:
                                if (!this.uiActionsSinceAppLaunch_.isModifiable()) {
                                    this.uiActionsSinceAppLaunch_ = GeneratedMessageLite.mutableCopy(this.uiActionsSinceAppLaunch_);
                                }
                                int readRawVarint324 = codedInputStream.readRawVarint32();
                                if (UiAction$Type.forNumber(readRawVarint324) != null) {
                                    this.uiActionsSinceAppLaunch_.addInt(readRawVarint324);
                                    break;
                                } else {
                                    super.mergeVarintField(8, readRawVarint324);
                                    break;
                                }
                            case 66:
                                if (!this.uiActionsSinceAppLaunch_.isModifiable()) {
                                    this.uiActionsSinceAppLaunch_ = GeneratedMessageLite.mutableCopy(this.uiActionsSinceAppLaunch_);
                                }
                                int pushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    int readRawVarint325 = codedInputStream.readRawVarint32();
                                    if (UiAction$Type.forNumber(readRawVarint325) == null) {
                                        super.mergeVarintField(8, readRawVarint325);
                                    } else {
                                        this.uiActionsSinceAppLaunch_.addInt(readRawVarint325);
                                    }
                                }
                                codedInputStream.popLimit(pushLimit2);
                                break;
                            case 72:
                                if (!this.uiActionTimestampsSinceAppLaunch_.isModifiable()) {
                                    this.uiActionTimestampsSinceAppLaunch_ = GeneratedMessageLite.mutableCopy(this.uiActionTimestampsSinceAppLaunch_);
                                }
                                this.uiActionTimestampsSinceAppLaunch_.addLong(codedInputStream.readRawVarint64());
                                break;
                            case 74:
                                int pushLimit3 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if (!this.uiActionTimestampsSinceAppLaunch_.isModifiable() && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.uiActionTimestampsSinceAppLaunch_ = GeneratedMessageLite.mutableCopy(this.uiActionTimestampsSinceAppLaunch_);
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.uiActionTimestampsSinceAppLaunch_.addLong(codedInputStream.readRawVarint64());
                                }
                                codedInputStream.popLimit(pushLimit3);
                                break;
                            case 80:
                                this.bitField0_ |= 64;
                                this.startingTabIndex_ = codedInputStream.readRawVarint32();
                                break;
                            case 88:
                                this.bitField0_ |= 128;
                                this.lightbringerButtonAppearInExpandedCallLogItemCount_ = codedInputStream.readRawVarint32();
                                break;
                            case 96:
                                this.bitField0_ |= 256;
                                this.lightbringerButtonAppearInCollapsedCallLogItemCount_ = codedInputStream.readRawVarint32();
                                break;
                            case 104:
                                this.bitField0_ |= i2;
                                this.lightbringerButtonAppearInSearchCount_ = codedInputStream.readRawVarint32();
                                break;
                            case 112:
                                this.bitField0_ |= i;
                                this.allowAssistedDialing_ = codedInputStream.readBool();
                                break;
                            default:
                                if (parseUnknownField(readTag, codedInputStream)) {
                                    break;
                                }
                                z4 = true;
                                break;
                        }
                        i = 1024;
                        i2 = 512;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case 3:
                this.speedDialContactType_.makeImmutable();
                this.uiActionsSinceAppLaunch_.makeImmutable();
                this.uiActionTimestampsSinceAppLaunch_.makeImmutable();
                return null;
            case 4:
                return new CallSpecificAppData();
            case 5:
                return new Builder((C04211) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (CallSpecificAppData.class) {
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

    public boolean getAllowAssistedDialing() {
        return this.allowAssistedDialing_;
    }

    public CallInitiationType$Type getCallInitiationType() {
        CallInitiationType$Type forNumber = CallInitiationType$Type.forNumber(this.callInitiationType_);
        return forNumber == null ? CallInitiationType$Type.UNKNOWN_INITIATION : forNumber;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeEnumSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeEnumSize(1, this.callInitiationType_) + 0 : 0;
        if ((this.bitField0_ & 2) == 2) {
            computeEnumSize += CodedOutputStream.computeInt32Size(2, this.positionOfSelectedSearchResult_);
        }
        if ((this.bitField0_ & 4) == 4) {
            computeEnumSize += CodedOutputStream.computeInt32Size(3, this.charactersInSearchString_);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.speedDialContactType_.size(); i3++) {
            i2 += CodedOutputStream.computeInt32SizeNoTag(this.speedDialContactType_.getInt(i3));
        }
        int size = (this.speedDialContactType_.size() * 1) + computeEnumSize + i2;
        if ((this.bitField0_ & 8) == 8) {
            size += CodedOutputStream.computeInt32Size(5, this.speedDialContactPosition_);
        }
        if ((this.bitField0_ & 16) == 16) {
            size += CodedOutputStream.computeInt64Size(6, this.timeSinceAppLaunch_);
        }
        if ((this.bitField0_ & 32) == 32) {
            size += CodedOutputStream.computeInt64Size(7, this.timeSinceFirstClick_);
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.uiActionsSinceAppLaunch_.size(); i5++) {
            i4 += CodedOutputStream.computeInt32SizeNoTag(this.uiActionsSinceAppLaunch_.getInt(i5));
        }
        int size2 = (this.uiActionsSinceAppLaunch_.size() * 1) + size + i4;
        int i6 = 0;
        for (int i7 = 0; i7 < this.uiActionTimestampsSinceAppLaunch_.size(); i7++) {
            i6 += CodedOutputStream.computeUInt64SizeNoTag(this.uiActionTimestampsSinceAppLaunch_.getLong(i7));
        }
        int size3 = (this.uiActionTimestampsSinceAppLaunch_.size() * 1) + size2 + i6;
        if ((this.bitField0_ & 64) == 64) {
            size3 += CodedOutputStream.computeInt32Size(10, this.startingTabIndex_);
        }
        if ((this.bitField0_ & 128) == 128) {
            size3 += CodedOutputStream.computeInt32Size(11, this.lightbringerButtonAppearInExpandedCallLogItemCount_);
        }
        if ((this.bitField0_ & 256) == 256) {
            size3 += CodedOutputStream.computeInt32Size(12, this.lightbringerButtonAppearInCollapsedCallLogItemCount_);
        }
        if ((this.bitField0_ & 512) == 512) {
            size3 += CodedOutputStream.computeInt32Size(13, this.lightbringerButtonAppearInSearchCount_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            size3 += CodedOutputStream.computeBoolSize(14, this.allowAssistedDialing_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + size3;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt32(1, this.callInitiationType_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.positionOfSelectedSearchResult_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt32(3, this.charactersInSearchString_);
        }
        for (int i = 0; i < this.speedDialContactType_.size(); i++) {
            codedOutputStream.writeInt32(4, this.speedDialContactType_.getInt(i));
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeInt32(5, this.speedDialContactPosition_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeUInt64(6, this.timeSinceAppLaunch_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeUInt64(7, this.timeSinceFirstClick_);
        }
        for (int i2 = 0; i2 < this.uiActionsSinceAppLaunch_.size(); i2++) {
            codedOutputStream.writeInt32(8, this.uiActionsSinceAppLaunch_.getInt(i2));
        }
        for (int i3 = 0; i3 < this.uiActionTimestampsSinceAppLaunch_.size(); i3++) {
            codedOutputStream.writeUInt64(9, this.uiActionTimestampsSinceAppLaunch_.getLong(i3));
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeInt32(10, this.startingTabIndex_);
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeInt32(11, this.lightbringerButtonAppearInExpandedCallLogItemCount_);
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeInt32(12, this.lightbringerButtonAppearInCollapsedCallLogItemCount_);
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeInt32(13, this.lightbringerButtonAppearInSearchCount_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            codedOutputStream.writeBool(14, this.allowAssistedDialing_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public static Builder newBuilder(CallSpecificAppData callSpecificAppData) {
        Builder builder = (Builder) DEFAULT_INSTANCE.toBuilder();
        builder.mergeFrom(callSpecificAppData);
        return builder;
    }
}
