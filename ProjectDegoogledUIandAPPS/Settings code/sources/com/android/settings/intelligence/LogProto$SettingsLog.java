package com.android.settings.intelligence;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class LogProto$SettingsLog extends GeneratedMessageLite<LogProto$SettingsLog, Builder> implements LogProto$SettingsLogOrBuilder {
    /* access modifiers changed from: private */
    public static final LogProto$SettingsLog DEFAULT_INSTANCE = new LogProto$SettingsLog();
    private static volatile Parser<LogProto$SettingsLog> PARSER;
    private int action_ = 0;
    private int attribution_ = 0;
    private int bitField0_;
    private int changedPreferenceIntValue_ = 0;
    private String changedPreferenceKey_ = "";
    private int pageId_ = 0;
    private String timestamp_ = "";

    private LogProto$SettingsLog() {
    }

    public boolean hasAttribution() {
        return (this.bitField0_ & 1) == 1;
    }

    /* access modifiers changed from: private */
    public void setAttribution(int i) {
        this.bitField0_ |= 1;
        this.attribution_ = i;
    }

    public boolean hasAction() {
        return (this.bitField0_ & 2) == 2;
    }

    /* access modifiers changed from: private */
    public void setAction(int i) {
        this.bitField0_ |= 2;
        this.action_ = i;
    }

    public boolean hasPageId() {
        return (this.bitField0_ & 4) == 4;
    }

    /* access modifiers changed from: private */
    public void setPageId(int i) {
        this.bitField0_ |= 4;
        this.pageId_ = i;
    }

    public boolean hasChangedPreferenceKey() {
        return (this.bitField0_ & 8) == 8;
    }

    public String getChangedPreferenceKey() {
        return this.changedPreferenceKey_;
    }

    /* access modifiers changed from: private */
    public void setChangedPreferenceKey(String str) {
        if (str != null) {
            this.bitField0_ |= 8;
            this.changedPreferenceKey_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasChangedPreferenceIntValue() {
        return (this.bitField0_ & 16) == 16;
    }

    /* access modifiers changed from: private */
    public void setChangedPreferenceIntValue(int i) {
        this.bitField0_ |= 16;
        this.changedPreferenceIntValue_ = i;
    }

    public boolean hasTimestamp() {
        return (this.bitField0_ & 32) == 32;
    }

    public String getTimestamp() {
        return this.timestamp_;
    }

    /* access modifiers changed from: private */
    public void setTimestamp(String str) {
        if (str != null) {
            this.bitField0_ |= 32;
            this.timestamp_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt32(1, this.attribution_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeInt32(2, this.action_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt32(3, this.pageId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(4, getChangedPreferenceKey());
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeInt32(5, this.changedPreferenceIntValue_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeString(6, getTimestamp());
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
            i2 = 0 + CodedOutputStream.computeInt32Size(1, this.attribution_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeInt32Size(2, this.action_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeInt32Size(3, this.pageId_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeStringSize(4, getChangedPreferenceKey());
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeInt32Size(5, this.changedPreferenceIntValue_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeStringSize(6, getTimestamp());
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LogProto$SettingsLog, Builder> implements LogProto$SettingsLogOrBuilder {
        /* synthetic */ Builder(LogProto$1 logProto$1) {
            this();
        }

        private Builder() {
            super(LogProto$SettingsLog.DEFAULT_INSTANCE);
        }

        public Builder setAttribution(int i) {
            copyOnWrite();
            ((LogProto$SettingsLog) this.instance).setAttribution(i);
            return this;
        }

        public Builder setAction(int i) {
            copyOnWrite();
            ((LogProto$SettingsLog) this.instance).setAction(i);
            return this;
        }

        public Builder setPageId(int i) {
            copyOnWrite();
            ((LogProto$SettingsLog) this.instance).setPageId(i);
            return this;
        }

        public Builder setChangedPreferenceKey(String str) {
            copyOnWrite();
            ((LogProto$SettingsLog) this.instance).setChangedPreferenceKey(str);
            return this;
        }

        public Builder setChangedPreferenceIntValue(int i) {
            copyOnWrite();
            ((LogProto$SettingsLog) this.instance).setChangedPreferenceIntValue(i);
            return this;
        }

        public Builder setTimestamp(String str) {
            copyOnWrite();
            ((LogProto$SettingsLog) this.instance).setTimestamp(str);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (LogProto$1.f50xa1df5c61[methodToInvoke.ordinal()]) {
            case 1:
                return new LogProto$SettingsLog();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                return null;
            case 4:
                return new Builder((LogProto$1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                LogProto$SettingsLog logProto$SettingsLog = (LogProto$SettingsLog) obj2;
                this.attribution_ = visitor.visitInt(hasAttribution(), this.attribution_, logProto$SettingsLog.hasAttribution(), logProto$SettingsLog.attribution_);
                this.action_ = visitor.visitInt(hasAction(), this.action_, logProto$SettingsLog.hasAction(), logProto$SettingsLog.action_);
                this.pageId_ = visitor.visitInt(hasPageId(), this.pageId_, logProto$SettingsLog.hasPageId(), logProto$SettingsLog.pageId_);
                this.changedPreferenceKey_ = visitor.visitString(hasChangedPreferenceKey(), this.changedPreferenceKey_, logProto$SettingsLog.hasChangedPreferenceKey(), logProto$SettingsLog.changedPreferenceKey_);
                this.changedPreferenceIntValue_ = visitor.visitInt(hasChangedPreferenceIntValue(), this.changedPreferenceIntValue_, logProto$SettingsLog.hasChangedPreferenceIntValue(), logProto$SettingsLog.changedPreferenceIntValue_);
                this.timestamp_ = visitor.visitString(hasTimestamp(), this.timestamp_, logProto$SettingsLog.hasTimestamp(), logProto$SettingsLog.timestamp_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= logProto$SettingsLog.bitField0_;
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
                            if (readTag == 8) {
                                this.bitField0_ |= 1;
                                this.attribution_ = codedInputStream.readInt32();
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.action_ = codedInputStream.readInt32();
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.pageId_ = codedInputStream.readInt32();
                            } else if (readTag == 34) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 8;
                                this.changedPreferenceKey_ = readString;
                            } else if (readTag == 40) {
                                this.bitField0_ |= 16;
                                this.changedPreferenceIntValue_ = codedInputStream.readInt32();
                            } else if (readTag == 50) {
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 32;
                                this.timestamp_ = readString2;
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
                    synchronized (LogProto$SettingsLog.class) {
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
