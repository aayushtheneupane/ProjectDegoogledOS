package com.android.contacts.common.widget;

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

public final class SelectPhoneAccountDialogOptions extends GeneratedMessageLite<SelectPhoneAccountDialogOptions, Builder> implements SelectPhoneAccountDialogOptionsOrBuilder {
    /* access modifiers changed from: private */
    public static final SelectPhoneAccountDialogOptions DEFAULT_INSTANCE = new SelectPhoneAccountDialogOptions();
    private static volatile Parser<SelectPhoneAccountDialogOptions> PARSER;
    private int bitField0_;
    private String callId_ = "";
    private boolean canSetDefault_ = false;
    private Internal.ProtobufList<Entry> entries_ = GeneratedMessageLite.emptyProtobufList();
    private int setDefaultLabel_ = 0;
    private int title_ = 0;

    /* renamed from: com.android.contacts.common.widget.SelectPhoneAccountDialogOptions$1 */
    static /* synthetic */ class C02741 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f6xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f6xa1df5c61 = r0
                int[] r0 = f6xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f6xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f6xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f6xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f6xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f6xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f6xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f6xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.common.widget.SelectPhoneAccountDialogOptions.C02741.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SelectPhoneAccountDialogOptions, Builder> implements SelectPhoneAccountDialogOptionsOrBuilder {
        /* synthetic */ Builder(C02741 r1) {
            this();
        }

        public Builder addEntries(Entry.Builder builder) {
            copyOnWrite();
            SelectPhoneAccountDialogOptions.access$2700((SelectPhoneAccountDialogOptions) this.instance, builder);
            return this;
        }

        public Builder setCallId(String str) {
            copyOnWrite();
            SelectPhoneAccountDialogOptions.access$2000((SelectPhoneAccountDialogOptions) this.instance, str);
            return this;
        }

        public Builder setCanSetDefault(boolean z) {
            copyOnWrite();
            SelectPhoneAccountDialogOptions.access$1600((SelectPhoneAccountDialogOptions) this.instance, z);
            return this;
        }

        public Builder setSetDefaultLabel(int i) {
            copyOnWrite();
            SelectPhoneAccountDialogOptions.access$1800((SelectPhoneAccountDialogOptions) this.instance, i);
            return this;
        }

        public Builder setTitle(int i) {
            copyOnWrite();
            SelectPhoneAccountDialogOptions.access$1400((SelectPhoneAccountDialogOptions) this.instance, i);
            return this;
        }

        private Builder() {
            super(SelectPhoneAccountDialogOptions.DEFAULT_INSTANCE);
        }
    }

    public static final class Entry extends GeneratedMessageLite<Entry, Builder> implements EntryOrBuilder {
        /* access modifiers changed from: private */
        public static final Entry DEFAULT_INSTANCE = new Entry();
        private static volatile Parser<Entry> PARSER;
        private int bitField0_;
        private boolean enabled_ = true;
        private String hint_ = "";
        private String phoneAccountHandleComponentName_ = "";
        private String phoneAccountHandleId_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<Entry, Builder> implements EntryOrBuilder {
            /* synthetic */ Builder(C02741 r1) {
                this();
            }

            public Builder setEnabled(boolean z) {
                copyOnWrite();
                Entry.access$1000((Entry) this.instance, z);
                return this;
            }

            public Builder setHint(String str) {
                copyOnWrite();
                Entry.access$700((Entry) this.instance, str);
                return this;
            }

            public Builder setPhoneAccountHandleComponentName(String str) {
                copyOnWrite();
                Entry.access$100((Entry) this.instance, str);
                return this;
            }

            public Builder setPhoneAccountHandleId(String str) {
                copyOnWrite();
                Entry.access$400((Entry) this.instance, str);
                return this;
            }

            private Builder() {
                super(Entry.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Entry() {
        }

        static /* synthetic */ void access$100(Entry entry, String str) {
            if (str != null) {
                entry.bitField0_ |= 1;
                entry.phoneAccountHandleComponentName_ = str;
                return;
            }
            throw new NullPointerException();
        }

        static /* synthetic */ void access$1000(Entry entry, boolean z) {
            entry.bitField0_ |= 8;
            entry.enabled_ = z;
        }

        static /* synthetic */ void access$400(Entry entry, String str) {
            if (str != null) {
                entry.bitField0_ |= 2;
                entry.phoneAccountHandleId_ = str;
                return;
            }
            throw new NullPointerException();
        }

        static /* synthetic */ void access$700(Entry entry, String str) {
            if (str != null) {
                entry.bitField0_ |= 4;
                entry.hint_ = str;
                return;
            }
            throw new NullPointerException();
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Parser<Entry> parser() {
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
                    Entry entry = (Entry) obj2;
                    this.phoneAccountHandleComponentName_ = visitor.visitString((this.bitField0_ & 1) == 1, this.phoneAccountHandleComponentName_, (entry.bitField0_ & 1) == 1, entry.phoneAccountHandleComponentName_);
                    this.phoneAccountHandleId_ = visitor.visitString((this.bitField0_ & 2) == 2, this.phoneAccountHandleId_, (entry.bitField0_ & 2) == 2, entry.phoneAccountHandleId_);
                    this.hint_ = visitor.visitString((this.bitField0_ & 4) == 4, this.hint_, (entry.bitField0_ & 4) == 4, entry.hint_);
                    boolean z2 = (this.bitField0_ & 8) == 8;
                    boolean z3 = this.enabled_;
                    if ((entry.bitField0_ & 8) == 8) {
                        z = true;
                    }
                    this.enabled_ = visitor.visitBoolean(z2, z3, z, entry.enabled_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= entry.bitField0_;
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
                                    this.phoneAccountHandleComponentName_ = readString;
                                } else if (readTag == 18) {
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 2;
                                    this.phoneAccountHandleId_ = readString2;
                                } else if (readTag == 26) {
                                    String readString3 = codedInputStream.readString();
                                    this.bitField0_ |= 4;
                                    this.hint_ = readString3;
                                } else if (readTag == 32) {
                                    this.bitField0_ |= 8;
                                    this.enabled_ = codedInputStream.readBool();
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
                    return new Entry();
                case 5:
                    return new Builder((C02741) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (Entry.class) {
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

        public boolean getEnabled() {
            return this.enabled_;
        }

        public String getHint() {
            return this.hint_;
        }

        public String getPhoneAccountHandleComponentName() {
            return this.phoneAccountHandleComponentName_;
        }

        public String getPhoneAccountHandleId() {
            return this.phoneAccountHandleId_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, this.phoneAccountHandleComponentName_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeStringSize(2, this.phoneAccountHandleId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeStringSize(3, this.hint_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeBoolSize(4, this.enabled_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, this.phoneAccountHandleComponentName_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeString(2, this.phoneAccountHandleId_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeString(3, this.hint_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBool(4, this.enabled_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface EntryOrBuilder extends MessageLiteOrBuilder {
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private SelectPhoneAccountDialogOptions() {
    }

    static /* synthetic */ void access$1400(SelectPhoneAccountDialogOptions selectPhoneAccountDialogOptions, int i) {
        selectPhoneAccountDialogOptions.bitField0_ |= 1;
        selectPhoneAccountDialogOptions.title_ = i;
    }

    static /* synthetic */ void access$1600(SelectPhoneAccountDialogOptions selectPhoneAccountDialogOptions, boolean z) {
        selectPhoneAccountDialogOptions.bitField0_ |= 2;
        selectPhoneAccountDialogOptions.canSetDefault_ = z;
    }

    static /* synthetic */ void access$1800(SelectPhoneAccountDialogOptions selectPhoneAccountDialogOptions, int i) {
        selectPhoneAccountDialogOptions.bitField0_ |= 4;
        selectPhoneAccountDialogOptions.setDefaultLabel_ = i;
    }

    static /* synthetic */ void access$2000(SelectPhoneAccountDialogOptions selectPhoneAccountDialogOptions, String str) {
        if (str != null) {
            selectPhoneAccountDialogOptions.bitField0_ |= 8;
            selectPhoneAccountDialogOptions.callId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2700(SelectPhoneAccountDialogOptions selectPhoneAccountDialogOptions, Entry.Builder builder) {
        if (!selectPhoneAccountDialogOptions.entries_.isModifiable()) {
            selectPhoneAccountDialogOptions.entries_ = GeneratedMessageLite.mutableCopy(selectPhoneAccountDialogOptions.entries_);
        }
        selectPhoneAccountDialogOptions.entries_.add((Entry) builder.build());
    }

    public static SelectPhoneAccountDialogOptions getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = true;
        boolean z2 = false;
        switch (methodToInvoke.ordinal()) {
            case 0:
                return DEFAULT_INSTANCE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                SelectPhoneAccountDialogOptions selectPhoneAccountDialogOptions = (SelectPhoneAccountDialogOptions) obj2;
                this.title_ = visitor.visitInt(hasTitle(), this.title_, selectPhoneAccountDialogOptions.hasTitle(), selectPhoneAccountDialogOptions.title_);
                this.canSetDefault_ = visitor.visitBoolean((this.bitField0_ & 2) == 2, this.canSetDefault_, (selectPhoneAccountDialogOptions.bitField0_ & 2) == 2, selectPhoneAccountDialogOptions.canSetDefault_);
                this.setDefaultLabel_ = visitor.visitInt(hasSetDefaultLabel(), this.setDefaultLabel_, selectPhoneAccountDialogOptions.hasSetDefaultLabel(), selectPhoneAccountDialogOptions.setDefaultLabel_);
                boolean z3 = (this.bitField0_ & 8) == 8;
                String str = this.callId_;
                if ((selectPhoneAccountDialogOptions.bitField0_ & 8) != 8) {
                    z = false;
                }
                this.callId_ = visitor.visitString(z3, str, z, selectPhoneAccountDialogOptions.callId_);
                this.entries_ = visitor.visitList(this.entries_, selectPhoneAccountDialogOptions.entries_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= selectPhoneAccountDialogOptions.bitField0_;
                }
                return this;
            case 2:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z2) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.bitField0_ |= 1;
                                this.title_ = codedInputStream.readRawVarint32();
                            } else if (readTag == 16) {
                                this.bitField0_ |= 2;
                                this.canSetDefault_ = codedInputStream.readBool();
                            } else if (readTag == 24) {
                                this.bitField0_ |= 4;
                                this.setDefaultLabel_ = codedInputStream.readRawVarint32();
                            } else if (readTag == 34) {
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 8;
                                this.callId_ = readString;
                            } else if (readTag == 42) {
                                if (!this.entries_.isModifiable()) {
                                    this.entries_ = GeneratedMessageLite.mutableCopy(this.entries_);
                                }
                                this.entries_.add((Entry) codedInputStream.readMessage(Entry.parser(), extensionRegistryLite));
                            } else if (!parseUnknownField(readTag, codedInputStream)) {
                            }
                        }
                        z2 = true;
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
                return new SelectPhoneAccountDialogOptions();
            case 5:
                return new Builder((C02741) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (SelectPhoneAccountDialogOptions.class) {
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

    public String getCallId() {
        return this.callId_;
    }

    public boolean getCanSetDefault() {
        return this.canSetDefault_;
    }

    public Entry getEntries(int i) {
        return this.entries_.get(i);
    }

    public List<Entry> getEntriesList() {
        return this.entries_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.title_) + 0 : 0;
        if ((this.bitField0_ & 2) == 2) {
            computeInt32Size += CodedOutputStream.computeBoolSize(2, this.canSetDefault_);
        }
        if ((this.bitField0_ & 4) == 4) {
            computeInt32Size += CodedOutputStream.computeInt32Size(3, this.setDefaultLabel_);
        }
        if ((this.bitField0_ & 8) == 8) {
            computeInt32Size += CodedOutputStream.computeStringSize(4, this.callId_);
        }
        for (int i2 = 0; i2 < this.entries_.size(); i2++) {
            computeInt32Size += CodedOutputStream.computeMessageSize(5, this.entries_.get(i2));
        }
        int serializedSize = this.unknownFields.getSerializedSize() + computeInt32Size;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public int getSetDefaultLabel() {
        return this.setDefaultLabel_;
    }

    public int getTitle() {
        return this.title_;
    }

    public boolean hasSetDefaultLabel() {
        return (this.bitField0_ & 4) == 4;
    }

    public boolean hasTitle() {
        return (this.bitField0_ & 1) == 1;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeInt32(1, this.title_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeBool(2, this.canSetDefault_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeInt32(3, this.setDefaultLabel_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(4, this.callId_);
        }
        for (int i = 0; i < this.entries_.size(); i++) {
            codedOutputStream.writeMessage(5, this.entries_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
