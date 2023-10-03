package com.android.dialer.phonelookup;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.AbstractMessageLite;
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

public final class PhoneLookupInfo extends GeneratedMessageLite<PhoneLookupInfo, Builder> implements PhoneLookupInfoOrBuilder {
    /* access modifiers changed from: private */
    public static final PhoneLookupInfo DEFAULT_INSTANCE = new PhoneLookupInfo();
    private static volatile Parser<PhoneLookupInfo> PARSER;
    private int bitField0_;
    private CequintInfo cequintInfo_;
    private CnapInfo cnapInfo_;
    private Cp2Info defaultCp2Info_;
    private EmergencyInfo emergencyInfo_;
    private Cp2Info extendedCp2Info_;
    private MigratedInfo migratedInfo_;
    private PeopleApiInfo peopleApiInfo_;
    private SpamInfo spamInfo_;
    private SystemBlockedNumberInfo systemBlockedNumberInfo_;

    /* renamed from: com.android.dialer.phonelookup.PhoneLookupInfo$1 */
    static /* synthetic */ class C05081 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f34xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f34xa1df5c61 = r0
                int[] r0 = f34xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f34xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f34xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f34xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f34xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f34xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f34xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f34xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonelookup.PhoneLookupInfo.C05081.<clinit>():void");
        }
    }

    public enum BlockedState implements Internal.EnumLite {
        UNKNOWN(0),
        BLOCKED(1),
        NOT_BLOCKED(2);
        
        private final int value;

        private BlockedState(int i) {
            this.value = i;
        }

        public static BlockedState forNumber(int i) {
            if (i == 0) {
                return UNKNOWN;
            }
            if (i == 1) {
                return BLOCKED;
            }
            if (i != 2) {
                return null;
            }
            return NOT_BLOCKED;
        }

        public final int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PhoneLookupInfo, Builder> implements PhoneLookupInfoOrBuilder {
        /* synthetic */ Builder(C05081 r1) {
            this();
        }

        public Builder setCequintInfo(CequintInfo cequintInfo) {
            copyOnWrite();
            PhoneLookupInfo.access$12600((PhoneLookupInfo) this.instance, cequintInfo);
            return this;
        }

        public Builder setCnapInfo(CnapInfo cnapInfo) {
            copyOnWrite();
            PhoneLookupInfo.access$12200((PhoneLookupInfo) this.instance, cnapInfo);
            return this;
        }

        public Builder setDefaultCp2Info(Cp2Info cp2Info) {
            copyOnWrite();
            PhoneLookupInfo.access$10200((PhoneLookupInfo) this.instance, cp2Info);
            return this;
        }

        public Builder setEmergencyInfo(EmergencyInfo emergencyInfo) {
            copyOnWrite();
            PhoneLookupInfo.access$13000((PhoneLookupInfo) this.instance, emergencyInfo);
            return this;
        }

        public Builder setExtendedCp2Info(Cp2Info cp2Info) {
            copyOnWrite();
            PhoneLookupInfo.access$10600((PhoneLookupInfo) this.instance, cp2Info);
            return this;
        }

        public Builder setSpamInfo(SpamInfo spamInfo) {
            copyOnWrite();
            PhoneLookupInfo.access$11000((PhoneLookupInfo) this.instance, spamInfo);
            return this;
        }

        public Builder setSystemBlockedNumberInfo(SystemBlockedNumberInfo systemBlockedNumberInfo) {
            copyOnWrite();
            PhoneLookupInfo.access$11800((PhoneLookupInfo) this.instance, systemBlockedNumberInfo);
            return this;
        }

        private Builder() {
            super(PhoneLookupInfo.DEFAULT_INSTANCE);
        }
    }

    public static final class CequintInfo extends GeneratedMessageLite<CequintInfo, Builder> implements CequintInfoOrBuilder {
        /* access modifiers changed from: private */
        public static final CequintInfo DEFAULT_INSTANCE = new CequintInfo();
        private static volatile Parser<CequintInfo> PARSER;
        private int bitField0_;
        private String geolocation_ = "";
        private String name_ = "";
        private String photoUri_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<CequintInfo, Builder> implements CequintInfoOrBuilder {
            /* synthetic */ Builder(C05081 r1) {
                this();
            }

            public Builder setGeolocation(String str) {
                copyOnWrite();
                CequintInfo.access$7500((CequintInfo) this.instance, str);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                CequintInfo.access$7200((CequintInfo) this.instance, str);
                return this;
            }

            public Builder setPhotoUri(String str) {
                copyOnWrite();
                CequintInfo.access$7800((CequintInfo) this.instance, str);
                return this;
            }

            private Builder() {
                super(CequintInfo.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CequintInfo() {
        }

        static /* synthetic */ void access$7200(CequintInfo cequintInfo, String str) {
            if (str != null) {
                cequintInfo.bitField0_ |= 1;
                cequintInfo.name_ = str;
                return;
            }
            throw new NullPointerException();
        }

        static /* synthetic */ void access$7500(CequintInfo cequintInfo, String str) {
            if (str != null) {
                cequintInfo.bitField0_ |= 2;
                cequintInfo.geolocation_ = str;
                return;
            }
            throw new NullPointerException();
        }

        static /* synthetic */ void access$7800(CequintInfo cequintInfo, String str) {
            if (str != null) {
                cequintInfo.bitField0_ |= 4;
                cequintInfo.photoUri_ = str;
                return;
            }
            throw new NullPointerException();
        }

        public static CequintInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Parser<CequintInfo> parser() {
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
                    CequintInfo cequintInfo = (CequintInfo) obj2;
                    this.name_ = visitor.visitString((this.bitField0_ & 1) == 1, this.name_, (cequintInfo.bitField0_ & 1) == 1, cequintInfo.name_);
                    this.geolocation_ = visitor.visitString((this.bitField0_ & 2) == 2, this.geolocation_, (cequintInfo.bitField0_ & 2) == 2, cequintInfo.geolocation_);
                    boolean z2 = (this.bitField0_ & 4) == 4;
                    String str = this.photoUri_;
                    if ((cequintInfo.bitField0_ & 4) == 4) {
                        z = true;
                    }
                    this.photoUri_ = visitor.visitString(z2, str, z, cequintInfo.photoUri_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= cequintInfo.bitField0_;
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
                                    this.name_ = readString;
                                } else if (readTag == 18) {
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 2;
                                    this.geolocation_ = readString2;
                                } else if (readTag == 26) {
                                    String readString3 = codedInputStream.readString();
                                    this.bitField0_ |= 4;
                                    this.photoUri_ = readString3;
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
                    return new CequintInfo();
                case 5:
                    return new Builder((C05081) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (CequintInfo.class) {
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

        public String getGeolocation() {
            return this.geolocation_;
        }

        public String getName() {
            return this.name_;
        }

        public String getPhotoUri() {
            return this.photoUri_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeStringSize(2, this.geolocation_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeStringSize(3, this.photoUri_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeString(2, this.geolocation_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeString(3, this.photoUri_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface CequintInfoOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class CnapInfo extends GeneratedMessageLite<CnapInfo, Builder> implements CnapInfoOrBuilder {
        /* access modifiers changed from: private */
        public static final CnapInfo DEFAULT_INSTANCE = new CnapInfo();
        private static volatile Parser<CnapInfo> PARSER;
        private int bitField0_;
        private String name_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<CnapInfo, Builder> implements CnapInfoOrBuilder {
            /* synthetic */ Builder(C05081 r1) {
                this();
            }

            public Builder setName(String str) {
                copyOnWrite();
                CnapInfo.access$6700((CnapInfo) this.instance, str);
                return this;
            }

            private Builder() {
                super(CnapInfo.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private CnapInfo() {
        }

        static /* synthetic */ void access$6700(CnapInfo cnapInfo, String str) {
            if (str != null) {
                cnapInfo.bitField0_ |= 1;
                cnapInfo.name_ = str;
                return;
            }
            throw new NullPointerException();
        }

        public static CnapInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Parser<CnapInfo> parser() {
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
                    CnapInfo cnapInfo = (CnapInfo) obj2;
                    boolean z2 = (this.bitField0_ & 1) == 1;
                    String str = this.name_;
                    if ((cnapInfo.bitField0_ & 1) == 1) {
                        z = true;
                    }
                    this.name_ = visitor.visitString(z2, str, z, cnapInfo.name_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= cnapInfo.bitField0_;
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
                                    this.name_ = readString;
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
                    return new CnapInfo();
                case 5:
                    return new Builder((C05081) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (CnapInfo.class) {
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

        public String getName() {
            return this.name_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, this.name_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, this.name_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface CnapInfoOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class Cp2Info extends GeneratedMessageLite<Cp2Info, Builder> implements Cp2InfoOrBuilder {
        /* access modifiers changed from: private */
        public static final Cp2Info DEFAULT_INSTANCE = new Cp2Info();
        private static volatile Parser<Cp2Info> PARSER;
        private int bitField0_;
        private Internal.ProtobufList<Cp2ContactInfo> cp2ContactInfo_ = GeneratedMessageLite.emptyProtobufList();
        private boolean isIncomplete_ = false;

        public static final class Builder extends GeneratedMessageLite.Builder<Cp2Info, Builder> implements Cp2InfoOrBuilder {
            /* synthetic */ Builder(C05081 r1) {
                this();
            }

            public Builder addAllCp2ContactInfo(Iterable<? extends Cp2ContactInfo> iterable) {
                copyOnWrite();
                Cp2Info.access$3000((Cp2Info) this.instance, iterable);
                return this;
            }

            public Builder addCp2ContactInfo(Cp2ContactInfo cp2ContactInfo) {
                copyOnWrite();
                Cp2Info.access$2600((Cp2Info) this.instance, cp2ContactInfo);
                return this;
            }

            public Builder setIsIncomplete(boolean z) {
                copyOnWrite();
                Cp2Info.access$3300((Cp2Info) this.instance, z);
                return this;
            }

            private Builder() {
                super(Cp2Info.DEFAULT_INSTANCE);
            }
        }

        public static final class Cp2ContactInfo extends GeneratedMessageLite<Cp2ContactInfo, Builder> implements Cp2ContactInfoOrBuilder {
            /* access modifiers changed from: private */
            public static final Cp2ContactInfo DEFAULT_INSTANCE = new Cp2ContactInfo();
            private static volatile Parser<Cp2ContactInfo> PARSER;
            private int bitField0_;
            private boolean canSupportCarrierVideoCall_ = false;
            private long contactId_ = 0;
            private String label_ = "";
            private String lookupUri_ = "";
            private String name_ = "";
            private long photoId_ = 0;
            private String photoThumbnailUri_ = "";
            private String photoUri_ = "";

            public static final class Builder extends GeneratedMessageLite.Builder<Cp2ContactInfo, Builder> implements Cp2ContactInfoOrBuilder {
                /* synthetic */ Builder(C05081 r1) {
                    this();
                }

                public Builder setCanSupportCarrierVideoCall(boolean z) {
                    copyOnWrite();
                    Cp2ContactInfo.access$2000((Cp2ContactInfo) this.instance, z);
                    return this;
                }

                public Builder setContactId(long j) {
                    copyOnWrite();
                    Cp2ContactInfo.access$1500((Cp2ContactInfo) this.instance, j);
                    return this;
                }

                public Builder setLabel(String str) {
                    copyOnWrite();
                    Cp2ContactInfo.access$1200((Cp2ContactInfo) this.instance, str);
                    return this;
                }

                public Builder setLookupUri(String str) {
                    copyOnWrite();
                    Cp2ContactInfo.access$1700((Cp2ContactInfo) this.instance, str);
                    return this;
                }

                public Builder setName(String str) {
                    copyOnWrite();
                    Cp2ContactInfo.access$100((Cp2ContactInfo) this.instance, str);
                    return this;
                }

                public Builder setPhotoId(long j) {
                    copyOnWrite();
                    Cp2ContactInfo.access$1000((Cp2ContactInfo) this.instance, j);
                    return this;
                }

                public Builder setPhotoThumbnailUri(String str) {
                    copyOnWrite();
                    Cp2ContactInfo.access$400((Cp2ContactInfo) this.instance, str);
                    return this;
                }

                public Builder setPhotoUri(String str) {
                    copyOnWrite();
                    Cp2ContactInfo.access$700((Cp2ContactInfo) this.instance, str);
                    return this;
                }

                private Builder() {
                    super(Cp2ContactInfo.DEFAULT_INSTANCE);
                }
            }

            static {
                DEFAULT_INSTANCE.makeImmutable();
            }

            private Cp2ContactInfo() {
            }

            static /* synthetic */ void access$100(Cp2ContactInfo cp2ContactInfo, String str) {
                if (str != null) {
                    cp2ContactInfo.bitField0_ |= 1;
                    cp2ContactInfo.name_ = str;
                    return;
                }
                throw new NullPointerException();
            }

            static /* synthetic */ void access$1000(Cp2ContactInfo cp2ContactInfo, long j) {
                cp2ContactInfo.bitField0_ |= 8;
                cp2ContactInfo.photoId_ = j;
            }

            static /* synthetic */ void access$1200(Cp2ContactInfo cp2ContactInfo, String str) {
                if (str != null) {
                    cp2ContactInfo.bitField0_ |= 16;
                    cp2ContactInfo.label_ = str;
                    return;
                }
                throw new NullPointerException();
            }

            static /* synthetic */ void access$1500(Cp2ContactInfo cp2ContactInfo, long j) {
                cp2ContactInfo.bitField0_ |= 32;
                cp2ContactInfo.contactId_ = j;
            }

            static /* synthetic */ void access$1700(Cp2ContactInfo cp2ContactInfo, String str) {
                if (str != null) {
                    cp2ContactInfo.bitField0_ |= 64;
                    cp2ContactInfo.lookupUri_ = str;
                    return;
                }
                throw new NullPointerException();
            }

            static /* synthetic */ void access$2000(Cp2ContactInfo cp2ContactInfo, boolean z) {
                cp2ContactInfo.bitField0_ |= 128;
                cp2ContactInfo.canSupportCarrierVideoCall_ = z;
            }

            static /* synthetic */ void access$400(Cp2ContactInfo cp2ContactInfo, String str) {
                if (str != null) {
                    cp2ContactInfo.bitField0_ |= 2;
                    cp2ContactInfo.photoThumbnailUri_ = str;
                    return;
                }
                throw new NullPointerException();
            }

            static /* synthetic */ void access$700(Cp2ContactInfo cp2ContactInfo, String str) {
                if (str != null) {
                    cp2ContactInfo.bitField0_ |= 4;
                    cp2ContactInfo.photoUri_ = str;
                    return;
                }
                throw new NullPointerException();
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.toBuilder();
            }

            public static Parser<Cp2ContactInfo> parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* access modifiers changed from: protected */
            public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
                boolean z = true;
                switch (methodToInvoke.ordinal()) {
                    case 0:
                        return DEFAULT_INSTANCE;
                    case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                        GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                        Cp2ContactInfo cp2ContactInfo = (Cp2ContactInfo) obj2;
                        this.name_ = visitor.visitString((this.bitField0_ & 1) == 1, this.name_, (cp2ContactInfo.bitField0_ & 1) == 1, cp2ContactInfo.name_);
                        this.photoThumbnailUri_ = visitor.visitString((this.bitField0_ & 2) == 2, this.photoThumbnailUri_, (cp2ContactInfo.bitField0_ & 2) == 2, cp2ContactInfo.photoThumbnailUri_);
                        this.photoUri_ = visitor.visitString((this.bitField0_ & 4) == 4, this.photoUri_, (cp2ContactInfo.bitField0_ & 4) == 4, cp2ContactInfo.photoUri_);
                        Cp2ContactInfo cp2ContactInfo2 = cp2ContactInfo;
                        this.photoId_ = visitor.visitLong((this.bitField0_ & 8) == 8, this.photoId_, (cp2ContactInfo.bitField0_ & 8) == 8, cp2ContactInfo.photoId_);
                        this.label_ = visitor.visitString((this.bitField0_ & 16) == 16, this.label_, (cp2ContactInfo2.bitField0_ & 16) == 16, cp2ContactInfo2.label_);
                        this.contactId_ = visitor.visitLong((this.bitField0_ & 32) == 32, this.contactId_, (cp2ContactInfo2.bitField0_ & 32) == 32, cp2ContactInfo2.contactId_);
                        this.lookupUri_ = visitor.visitString((this.bitField0_ & 64) == 64, this.lookupUri_, (cp2ContactInfo2.bitField0_ & 64) == 64, cp2ContactInfo2.lookupUri_);
                        boolean z2 = (this.bitField0_ & 128) == 128;
                        boolean z3 = this.canSupportCarrierVideoCall_;
                        if ((cp2ContactInfo2.bitField0_ & 128) != 128) {
                            z = false;
                        }
                        this.canSupportCarrierVideoCall_ = visitor.visitBoolean(z2, z3, z, cp2ContactInfo2.canSupportCarrierVideoCall_);
                        if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                            this.bitField0_ |= cp2ContactInfo2.bitField0_;
                        }
                        return this;
                    case 2:
                        CodedInputStream codedInputStream = (CodedInputStream) obj;
                        ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                        boolean z4 = false;
                        while (!z4) {
                            try {
                                int readTag = codedInputStream.readTag();
                                if (readTag != 0) {
                                    if (readTag == 10) {
                                        String readString = codedInputStream.readString();
                                        this.bitField0_ |= 1;
                                        this.name_ = readString;
                                    } else if (readTag == 18) {
                                        String readString2 = codedInputStream.readString();
                                        this.bitField0_ |= 2;
                                        this.photoThumbnailUri_ = readString2;
                                    } else if (readTag == 26) {
                                        String readString3 = codedInputStream.readString();
                                        this.bitField0_ |= 4;
                                        this.photoUri_ = readString3;
                                    } else if (readTag == 33) {
                                        this.bitField0_ |= 8;
                                        this.photoId_ = codedInputStream.readFixed64();
                                    } else if (readTag == 42) {
                                        String readString4 = codedInputStream.readString();
                                        this.bitField0_ |= 16;
                                        this.label_ = readString4;
                                    } else if (readTag == 49) {
                                        this.bitField0_ |= 32;
                                        this.contactId_ = codedInputStream.readFixed64();
                                    } else if (readTag == 58) {
                                        String readString5 = codedInputStream.readString();
                                        this.bitField0_ |= 64;
                                        this.lookupUri_ = readString5;
                                    } else if (readTag == 64) {
                                        this.bitField0_ |= 128;
                                        this.canSupportCarrierVideoCall_ = codedInputStream.readBool();
                                    } else if (!parseUnknownField(readTag, codedInputStream)) {
                                    }
                                }
                                z4 = true;
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
                        return new Cp2ContactInfo();
                    case 5:
                        return new Builder((C05081) null);
                    case 6:
                        break;
                    case 7:
                        if (PARSER == null) {
                            synchronized (Cp2ContactInfo.class) {
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

            public boolean getCanSupportCarrierVideoCall() {
                return this.canSupportCarrierVideoCall_;
            }

            public long getContactId() {
                return this.contactId_;
            }

            public String getLabel() {
                return this.label_;
            }

            public String getLookupUri() {
                return this.lookupUri_;
            }

            public String getName() {
                return this.name_;
            }

            public long getPhotoId() {
                return this.photoId_;
            }

            public String getPhotoThumbnailUri() {
                return this.photoThumbnailUri_;
            }

            public String getPhotoUri() {
                return this.photoUri_;
            }

            public int getSerializedSize() {
                int i = this.memoizedSerializedSize;
                if (i != -1) {
                    return i;
                }
                int i2 = 0;
                if ((this.bitField0_ & 1) == 1) {
                    i2 = 0 + CodedOutputStream.computeStringSize(1, this.name_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    i2 += CodedOutputStream.computeStringSize(2, this.photoThumbnailUri_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    i2 += CodedOutputStream.computeStringSize(3, this.photoUri_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    i2 += CodedOutputStream.computeFixed64Size(4, this.photoId_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    i2 += CodedOutputStream.computeStringSize(5, this.label_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    i2 += CodedOutputStream.computeFixed64Size(6, this.contactId_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    i2 += CodedOutputStream.computeStringSize(7, this.lookupUri_);
                }
                if ((this.bitField0_ & 128) == 128) {
                    i2 += CodedOutputStream.computeBoolSize(8, this.canSupportCarrierVideoCall_);
                }
                int serializedSize = this.unknownFields.getSerializedSize() + i2;
                this.memoizedSerializedSize = serializedSize;
                return serializedSize;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if ((this.bitField0_ & 1) == 1) {
                    codedOutputStream.writeString(1, this.name_);
                }
                if ((this.bitField0_ & 2) == 2) {
                    codedOutputStream.writeString(2, this.photoThumbnailUri_);
                }
                if ((this.bitField0_ & 4) == 4) {
                    codedOutputStream.writeString(3, this.photoUri_);
                }
                if ((this.bitField0_ & 8) == 8) {
                    codedOutputStream.writeFixed64(4, this.photoId_);
                }
                if ((this.bitField0_ & 16) == 16) {
                    codedOutputStream.writeString(5, this.label_);
                }
                if ((this.bitField0_ & 32) == 32) {
                    codedOutputStream.writeFixed64(6, this.contactId_);
                }
                if ((this.bitField0_ & 64) == 64) {
                    codedOutputStream.writeString(7, this.lookupUri_);
                }
                if ((this.bitField0_ & 128) == 128) {
                    codedOutputStream.writeBool(8, this.canSupportCarrierVideoCall_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }
        }

        public interface Cp2ContactInfoOrBuilder extends MessageLiteOrBuilder {
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private Cp2Info() {
        }

        static /* synthetic */ void access$2600(Cp2Info cp2Info, Cp2ContactInfo cp2ContactInfo) {
            if (cp2ContactInfo != null) {
                cp2Info.ensureCp2ContactInfoIsMutable();
                cp2Info.cp2ContactInfo_.add(cp2ContactInfo);
                return;
            }
            throw new NullPointerException();
        }

        static /* synthetic */ void access$3000(Cp2Info cp2Info, Iterable iterable) {
            cp2Info.ensureCp2ContactInfoIsMutable();
            AbstractMessageLite.addAll(iterable, cp2Info.cp2ContactInfo_);
        }

        static /* synthetic */ void access$3300(Cp2Info cp2Info, boolean z) {
            cp2Info.bitField0_ |= 1;
            cp2Info.isIncomplete_ = z;
        }

        private void ensureCp2ContactInfoIsMutable() {
            if (!this.cp2ContactInfo_.isModifiable()) {
                this.cp2ContactInfo_ = GeneratedMessageLite.mutableCopy(this.cp2ContactInfo_);
            }
        }

        public static Cp2Info getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Parser<Cp2Info> parser() {
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
                    Cp2Info cp2Info = (Cp2Info) obj2;
                    this.cp2ContactInfo_ = visitor.visitList(this.cp2ContactInfo_, cp2Info.cp2ContactInfo_);
                    boolean z2 = (this.bitField0_ & 1) == 1;
                    boolean z3 = this.isIncomplete_;
                    if ((cp2Info.bitField0_ & 1) == 1) {
                        z = true;
                    }
                    this.isIncomplete_ = visitor.visitBoolean(z2, z3, z, cp2Info.isIncomplete_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= cp2Info.bitField0_;
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
                                    if (!this.cp2ContactInfo_.isModifiable()) {
                                        this.cp2ContactInfo_ = GeneratedMessageLite.mutableCopy(this.cp2ContactInfo_);
                                    }
                                    this.cp2ContactInfo_.add((Cp2ContactInfo) codedInputStream.readMessage(Cp2ContactInfo.parser(), extensionRegistryLite));
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 1;
                                    this.isIncomplete_ = codedInputStream.readBool();
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
                    this.cp2ContactInfo_.makeImmutable();
                    return null;
                case 4:
                    return new Cp2Info();
                case 5:
                    return new Builder((C05081) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (Cp2Info.class) {
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

        public Cp2ContactInfo getCp2ContactInfo(int i) {
            return this.cp2ContactInfo_.get(i);
        }

        public int getCp2ContactInfoCount() {
            return this.cp2ContactInfo_.size();
        }

        public List<Cp2ContactInfo> getCp2ContactInfoList() {
            return this.cp2ContactInfo_;
        }

        public boolean getIsIncomplete() {
            return this.isIncomplete_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.cp2ContactInfo_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, this.cp2ContactInfo_.get(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                i2 += CodedOutputStream.computeBoolSize(2, this.isIncomplete_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.cp2ContactInfo_.size(); i++) {
                codedOutputStream.writeMessage(1, this.cp2ContactInfo_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBool(2, this.isIncomplete_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public static Builder newBuilder(Cp2Info cp2Info) {
            Builder builder = (Builder) DEFAULT_INSTANCE.toBuilder();
            builder.mergeFrom(cp2Info);
            return builder;
        }
    }

    public interface Cp2InfoOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class EmergencyInfo extends GeneratedMessageLite<EmergencyInfo, Builder> implements EmergencyInfoOrBuilder {
        /* access modifiers changed from: private */
        public static final EmergencyInfo DEFAULT_INSTANCE = new EmergencyInfo();
        private static volatile Parser<EmergencyInfo> PARSER;
        private int bitField0_;
        private boolean isEmergencyNumber_ = false;

        public static final class Builder extends GeneratedMessageLite.Builder<EmergencyInfo, Builder> implements EmergencyInfoOrBuilder {
            /* synthetic */ Builder(C05081 r1) {
                this();
            }

            public Builder setIsEmergencyNumber(boolean z) {
                copyOnWrite();
                EmergencyInfo.access$8300((EmergencyInfo) this.instance, z);
                return this;
            }

            private Builder() {
                super(EmergencyInfo.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private EmergencyInfo() {
        }

        static /* synthetic */ void access$8300(EmergencyInfo emergencyInfo, boolean z) {
            emergencyInfo.bitField0_ |= 1;
            emergencyInfo.isEmergencyNumber_ = z;
        }

        public static EmergencyInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Parser<EmergencyInfo> parser() {
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
                    EmergencyInfo emergencyInfo = (EmergencyInfo) obj2;
                    boolean z2 = (this.bitField0_ & 1) == 1;
                    boolean z3 = this.isEmergencyNumber_;
                    if ((emergencyInfo.bitField0_ & 1) == 1) {
                        z = true;
                    }
                    this.isEmergencyNumber_ = visitor.visitBoolean(z2, z3, z, emergencyInfo.isEmergencyNumber_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= emergencyInfo.bitField0_;
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
                                    this.bitField0_ |= 1;
                                    this.isEmergencyNumber_ = codedInputStream.readBool();
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
                    return new EmergencyInfo();
                case 5:
                    return new Builder((C05081) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (EmergencyInfo.class) {
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

        public boolean getIsEmergencyNumber() {
            return this.isEmergencyNumber_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, this.isEmergencyNumber_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBool(1, this.isEmergencyNumber_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface EmergencyInfoOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class MigratedInfo extends GeneratedMessageLite<MigratedInfo, Builder> implements MigratedInfoOrBuilder {
        /* access modifiers changed from: private */
        public static final MigratedInfo DEFAULT_INSTANCE = new MigratedInfo();
        private static volatile Parser<MigratedInfo> PARSER;
        private int bitField0_;
        private boolean isBusiness_ = false;
        private String label_ = "";
        private String name_ = "";
        private String photoUri_ = "";
        private int sourceType_ = 0;

        public static final class Builder extends GeneratedMessageLite.Builder<MigratedInfo, Builder> implements MigratedInfoOrBuilder {
            /* synthetic */ Builder(C05081 r1) {
                this();
            }

            private Builder() {
                super(MigratedInfo.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private MigratedInfo() {
        }

        public static MigratedInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MigratedInfo> parser() {
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
                    MigratedInfo migratedInfo = (MigratedInfo) obj2;
                    this.name_ = visitor.visitString((this.bitField0_ & 1) == 1, this.name_, (migratedInfo.bitField0_ & 1) == 1, migratedInfo.name_);
                    this.label_ = visitor.visitString((this.bitField0_ & 2) == 2, this.label_, (migratedInfo.bitField0_ & 2) == 2, migratedInfo.label_);
                    this.photoUri_ = visitor.visitString((this.bitField0_ & 4) == 4, this.photoUri_, (migratedInfo.bitField0_ & 4) == 4, migratedInfo.photoUri_);
                    this.isBusiness_ = visitor.visitBoolean((this.bitField0_ & 8) == 8, this.isBusiness_, (migratedInfo.bitField0_ & 8) == 8, migratedInfo.isBusiness_);
                    boolean z2 = (this.bitField0_ & 16) == 16;
                    int i = this.sourceType_;
                    if ((migratedInfo.bitField0_ & 16) == 16) {
                        z = true;
                    }
                    this.sourceType_ = visitor.visitInt(z2, i, z, migratedInfo.sourceType_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= migratedInfo.bitField0_;
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
                                    this.name_ = readString;
                                } else if (readTag == 18) {
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 2;
                                    this.label_ = readString2;
                                } else if (readTag == 26) {
                                    String readString3 = codedInputStream.readString();
                                    this.bitField0_ |= 4;
                                    this.photoUri_ = readString3;
                                } else if (readTag == 32) {
                                    this.bitField0_ |= 8;
                                    this.isBusiness_ = codedInputStream.readBool();
                                } else if (readTag == 40) {
                                    this.bitField0_ |= 16;
                                    this.sourceType_ = codedInputStream.readRawVarint32();
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
                    return new MigratedInfo();
                case 5:
                    return new Builder((C05081) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (MigratedInfo.class) {
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

        public boolean getIsBusiness() {
            return this.isBusiness_;
        }

        public String getLabel() {
            return this.label_;
        }

        public String getName() {
            return this.name_;
        }

        public String getPhotoUri() {
            return this.photoUri_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeStringSize(2, this.label_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeStringSize(3, this.photoUri_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeBoolSize(4, this.isBusiness_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeInt32Size(5, this.sourceType_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public int getSourceType() {
            return this.sourceType_;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeString(2, this.label_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeString(3, this.photoUri_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBool(4, this.isBusiness_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt32(5, this.sourceType_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface MigratedInfoOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class PeopleApiInfo extends GeneratedMessageLite<PeopleApiInfo, Builder> implements PeopleApiInfoOrBuilder {
        /* access modifiers changed from: private */
        public static final PeopleApiInfo DEFAULT_INSTANCE = new PeopleApiInfo();
        private static volatile Parser<PeopleApiInfo> PARSER;
        private int bitField0_;
        private String displayName_ = "";
        private String formattedNumberType_ = "";
        private String imageUrl_ = "";
        private int infoType_ = 0;
        private String lookupUri_ = "";
        private String numberType_ = "";
        private String personId_ = "";

        public static final class Builder extends GeneratedMessageLite.Builder<PeopleApiInfo, Builder> implements PeopleApiInfoOrBuilder {
            /* synthetic */ Builder(C05081 r1) {
                this();
            }

            private Builder() {
                super(PeopleApiInfo.DEFAULT_INSTANCE);
            }
        }

        public enum InfoType implements Internal.EnumLite {
            UNKNOWN(0),
            CONTACT(1),
            NEARBY_BUSINESS(2);
            
            private final int value;

            private InfoType(int i) {
                this.value = i;
            }

            public static InfoType forNumber(int i) {
                if (i == 0) {
                    return UNKNOWN;
                }
                if (i == 1) {
                    return CONTACT;
                }
                if (i != 2) {
                    return null;
                }
                return NEARBY_BUSINESS;
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private PeopleApiInfo() {
        }

        public static PeopleApiInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PeopleApiInfo> parser() {
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
                    PeopleApiInfo peopleApiInfo = (PeopleApiInfo) obj2;
                    this.displayName_ = visitor.visitString((this.bitField0_ & 1) == 1, this.displayName_, (peopleApiInfo.bitField0_ & 1) == 1, peopleApiInfo.displayName_);
                    this.numberType_ = visitor.visitString((this.bitField0_ & 2) == 2, this.numberType_, (peopleApiInfo.bitField0_ & 2) == 2, peopleApiInfo.numberType_);
                    this.formattedNumberType_ = visitor.visitString((this.bitField0_ & 4) == 4, this.formattedNumberType_, (peopleApiInfo.bitField0_ & 4) == 4, peopleApiInfo.formattedNumberType_);
                    this.imageUrl_ = visitor.visitString((this.bitField0_ & 8) == 8, this.imageUrl_, (peopleApiInfo.bitField0_ & 8) == 8, peopleApiInfo.imageUrl_);
                    this.personId_ = visitor.visitString((this.bitField0_ & 16) == 16, this.personId_, (peopleApiInfo.bitField0_ & 16) == 16, peopleApiInfo.personId_);
                    this.infoType_ = visitor.visitInt((this.bitField0_ & 32) == 32, this.infoType_, (peopleApiInfo.bitField0_ & 32) == 32, peopleApiInfo.infoType_);
                    boolean z2 = (this.bitField0_ & 64) == 64;
                    String str = this.lookupUri_;
                    if ((peopleApiInfo.bitField0_ & 64) == 64) {
                        z = true;
                    }
                    this.lookupUri_ = visitor.visitString(z2, str, z, peopleApiInfo.lookupUri_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= peopleApiInfo.bitField0_;
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
                                    this.displayName_ = readString;
                                } else if (readTag == 18) {
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 2;
                                    this.numberType_ = readString2;
                                } else if (readTag == 26) {
                                    String readString3 = codedInputStream.readString();
                                    this.bitField0_ |= 4;
                                    this.formattedNumberType_ = readString3;
                                } else if (readTag == 34) {
                                    String readString4 = codedInputStream.readString();
                                    this.bitField0_ |= 8;
                                    this.imageUrl_ = readString4;
                                } else if (readTag == 42) {
                                    String readString5 = codedInputStream.readString();
                                    this.bitField0_ |= 16;
                                    this.personId_ = readString5;
                                } else if (readTag == 48) {
                                    int readRawVarint32 = codedInputStream.readRawVarint32();
                                    if (InfoType.forNumber(readRawVarint32) == null) {
                                        super.mergeVarintField(6, readRawVarint32);
                                    } else {
                                        this.bitField0_ |= 32;
                                        this.infoType_ = readRawVarint32;
                                    }
                                } else if (readTag == 58) {
                                    String readString6 = codedInputStream.readString();
                                    this.bitField0_ |= 64;
                                    this.lookupUri_ = readString6;
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
                    return new PeopleApiInfo();
                case 5:
                    return new Builder((C05081) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (PeopleApiInfo.class) {
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

        public String getDisplayName() {
            return this.displayName_;
        }

        public InfoType getInfoType() {
            InfoType forNumber = InfoType.forNumber(this.infoType_);
            return forNumber == null ? InfoType.UNKNOWN : forNumber;
        }

        public String getLookupUri() {
            return this.lookupUri_;
        }

        public String getPersonId() {
            return this.personId_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, this.displayName_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeStringSize(2, this.numberType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeStringSize(3, this.formattedNumberType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeStringSize(4, this.imageUrl_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeStringSize(5, this.personId_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeEnumSize(6, this.infoType_);
            }
            if ((this.bitField0_ & 64) == 64) {
                i2 += CodedOutputStream.computeStringSize(7, this.lookupUri_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, this.displayName_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeString(2, this.numberType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeString(3, this.formattedNumberType_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeString(4, this.imageUrl_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeString(5, this.personId_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeInt32(6, this.infoType_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeString(7, this.lookupUri_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface PeopleApiInfoOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class SpamInfo extends GeneratedMessageLite<SpamInfo, Builder> implements SpamInfoOrBuilder {
        /* access modifiers changed from: private */
        public static final SpamInfo DEFAULT_INSTANCE = new SpamInfo();
        private static volatile Parser<SpamInfo> PARSER;
        private int bitField0_;
        private boolean isSpam_ = false;

        public static final class Builder extends GeneratedMessageLite.Builder<SpamInfo, Builder> implements SpamInfoOrBuilder {
            /* synthetic */ Builder(C05081 r1) {
                this();
            }

            public Builder setIsSpam(boolean z) {
                copyOnWrite();
                SpamInfo.access$3700((SpamInfo) this.instance, z);
                return this;
            }

            private Builder() {
                super(SpamInfo.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SpamInfo() {
        }

        static /* synthetic */ void access$3700(SpamInfo spamInfo, boolean z) {
            spamInfo.bitField0_ |= 1;
            spamInfo.isSpam_ = z;
        }

        public static SpamInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Parser<SpamInfo> parser() {
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
                    SpamInfo spamInfo = (SpamInfo) obj2;
                    boolean z2 = (this.bitField0_ & 1) == 1;
                    boolean z3 = this.isSpam_;
                    if ((spamInfo.bitField0_ & 1) == 1) {
                        z = true;
                    }
                    this.isSpam_ = visitor.visitBoolean(z2, z3, z, spamInfo.isSpam_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= spamInfo.bitField0_;
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
                                    this.bitField0_ |= 1;
                                    this.isSpam_ = codedInputStream.readBool();
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
                    return new SpamInfo();
                case 5:
                    return new Builder((C05081) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (SpamInfo.class) {
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

        public boolean getIsSpam() {
            return this.isSpam_;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, this.isSpam_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBool(1, this.isSpam_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface SpamInfoOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class SystemBlockedNumberInfo extends GeneratedMessageLite<SystemBlockedNumberInfo, Builder> implements SystemBlockedNumberInfoOrBuilder {
        /* access modifiers changed from: private */
        public static final SystemBlockedNumberInfo DEFAULT_INSTANCE = new SystemBlockedNumberInfo();
        private static volatile Parser<SystemBlockedNumberInfo> PARSER;
        private int bitField0_;
        private int blockedState_ = 0;

        public static final class Builder extends GeneratedMessageLite.Builder<SystemBlockedNumberInfo, Builder> implements SystemBlockedNumberInfoOrBuilder {
            /* synthetic */ Builder(C05081 r1) {
                this();
            }

            public Builder setBlockedState(BlockedState blockedState) {
                copyOnWrite();
                SystemBlockedNumberInfo.access$6300((SystemBlockedNumberInfo) this.instance, blockedState);
                return this;
            }

            private Builder() {
                super(SystemBlockedNumberInfo.DEFAULT_INSTANCE);
            }
        }

        static {
            DEFAULT_INSTANCE.makeImmutable();
        }

        private SystemBlockedNumberInfo() {
        }

        static /* synthetic */ void access$6300(SystemBlockedNumberInfo systemBlockedNumberInfo, BlockedState blockedState) {
            if (blockedState != null) {
                systemBlockedNumberInfo.bitField0_ |= 1;
                systemBlockedNumberInfo.blockedState_ = blockedState.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        public static SystemBlockedNumberInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Parser<SystemBlockedNumberInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke.ordinal()) {
                case 0:
                    return DEFAULT_INSTANCE;
                case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    SystemBlockedNumberInfo systemBlockedNumberInfo = (SystemBlockedNumberInfo) obj2;
                    this.blockedState_ = visitor.visitInt(hasBlockedState(), this.blockedState_, systemBlockedNumberInfo.hasBlockedState(), systemBlockedNumberInfo.blockedState_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= systemBlockedNumberInfo.bitField0_;
                    }
                    return this;
                case 2:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    int readRawVarint32 = codedInputStream.readRawVarint32();
                                    if (BlockedState.forNumber(readRawVarint32) == null) {
                                        super.mergeVarintField(1, readRawVarint32);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.blockedState_ = readRawVarint32;
                                    }
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
                    return new SystemBlockedNumberInfo();
                case 5:
                    return new Builder((C05081) null);
                case 6:
                    break;
                case 7:
                    if (PARSER == null) {
                        synchronized (SystemBlockedNumberInfo.class) {
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

        public BlockedState getBlockedState() {
            BlockedState forNumber = BlockedState.forNumber(this.blockedState_);
            return forNumber == null ? BlockedState.UNKNOWN : forNumber;
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.blockedState_);
            }
            int serializedSize = this.unknownFields.getSerializedSize() + i2;
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public boolean hasBlockedState() {
            return (this.bitField0_ & 1) == 1;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.blockedState_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface SystemBlockedNumberInfoOrBuilder extends MessageLiteOrBuilder {
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private PhoneLookupInfo() {
    }

    static /* synthetic */ void access$10200(PhoneLookupInfo phoneLookupInfo, Cp2Info cp2Info) {
        if (cp2Info != null) {
            phoneLookupInfo.defaultCp2Info_ = cp2Info;
            phoneLookupInfo.bitField0_ |= 1;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$10600(PhoneLookupInfo phoneLookupInfo, Cp2Info cp2Info) {
        if (cp2Info != null) {
            phoneLookupInfo.extendedCp2Info_ = cp2Info;
            phoneLookupInfo.bitField0_ |= 2;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$11000(PhoneLookupInfo phoneLookupInfo, SpamInfo spamInfo) {
        if (spamInfo != null) {
            phoneLookupInfo.spamInfo_ = spamInfo;
            phoneLookupInfo.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$11800(PhoneLookupInfo phoneLookupInfo, SystemBlockedNumberInfo systemBlockedNumberInfo) {
        if (systemBlockedNumberInfo != null) {
            phoneLookupInfo.systemBlockedNumberInfo_ = systemBlockedNumberInfo;
            phoneLookupInfo.bitField0_ |= 16;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$12200(PhoneLookupInfo phoneLookupInfo, CnapInfo cnapInfo) {
        if (cnapInfo != null) {
            phoneLookupInfo.cnapInfo_ = cnapInfo;
            phoneLookupInfo.bitField0_ |= 32;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$12600(PhoneLookupInfo phoneLookupInfo, CequintInfo cequintInfo) {
        if (cequintInfo != null) {
            phoneLookupInfo.cequintInfo_ = cequintInfo;
            phoneLookupInfo.bitField0_ |= 64;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$13000(PhoneLookupInfo phoneLookupInfo, EmergencyInfo emergencyInfo) {
        if (emergencyInfo != null) {
            phoneLookupInfo.emergencyInfo_ = emergencyInfo;
            phoneLookupInfo.bitField0_ |= 128;
            return;
        }
        throw new NullPointerException();
    }

    public static PhoneLookupInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static PhoneLookupInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (PhoneLookupInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke.ordinal()) {
            case 0:
                return DEFAULT_INSTANCE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                PhoneLookupInfo phoneLookupInfo = (PhoneLookupInfo) obj2;
                this.defaultCp2Info_ = (Cp2Info) visitor.visitMessage(this.defaultCp2Info_, phoneLookupInfo.defaultCp2Info_);
                this.extendedCp2Info_ = (Cp2Info) visitor.visitMessage(this.extendedCp2Info_, phoneLookupInfo.extendedCp2Info_);
                this.spamInfo_ = (SpamInfo) visitor.visitMessage(this.spamInfo_, phoneLookupInfo.spamInfo_);
                this.peopleApiInfo_ = (PeopleApiInfo) visitor.visitMessage(this.peopleApiInfo_, phoneLookupInfo.peopleApiInfo_);
                this.systemBlockedNumberInfo_ = (SystemBlockedNumberInfo) visitor.visitMessage(this.systemBlockedNumberInfo_, phoneLookupInfo.systemBlockedNumberInfo_);
                this.cnapInfo_ = (CnapInfo) visitor.visitMessage(this.cnapInfo_, phoneLookupInfo.cnapInfo_);
                this.cequintInfo_ = (CequintInfo) visitor.visitMessage(this.cequintInfo_, phoneLookupInfo.cequintInfo_);
                this.emergencyInfo_ = (EmergencyInfo) visitor.visitMessage(this.emergencyInfo_, phoneLookupInfo.emergencyInfo_);
                this.migratedInfo_ = (MigratedInfo) visitor.visitMessage(this.migratedInfo_, phoneLookupInfo.migratedInfo_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= phoneLookupInfo.bitField0_;
                }
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
                                Cp2Info.Builder builder = (this.bitField0_ & 1) == 1 ? (Cp2Info.Builder) this.defaultCp2Info_.toBuilder() : null;
                                this.defaultCp2Info_ = (Cp2Info) codedInputStream.readMessage(Cp2Info.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.defaultCp2Info_);
                                    this.defaultCp2Info_ = (Cp2Info) builder.buildPartial();
                                }
                                this.bitField0_ |= 1;
                            } else if (readTag == 18) {
                                SpamInfo.Builder builder2 = (this.bitField0_ & 4) == 4 ? (SpamInfo.Builder) this.spamInfo_.toBuilder() : null;
                                this.spamInfo_ = (SpamInfo) codedInputStream.readMessage(SpamInfo.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.spamInfo_);
                                    this.spamInfo_ = (SpamInfo) builder2.buildPartial();
                                }
                                this.bitField0_ |= 4;
                            } else if (readTag == 26) {
                                PeopleApiInfo.Builder builder3 = (this.bitField0_ & 8) == 8 ? (PeopleApiInfo.Builder) this.peopleApiInfo_.toBuilder() : null;
                                this.peopleApiInfo_ = (PeopleApiInfo) codedInputStream.readMessage(PeopleApiInfo.parser(), extensionRegistryLite);
                                if (builder3 != null) {
                                    builder3.mergeFrom(this.peopleApiInfo_);
                                    this.peopleApiInfo_ = (PeopleApiInfo) builder3.buildPartial();
                                }
                                this.bitField0_ |= 8;
                            } else if (readTag == 34) {
                                SystemBlockedNumberInfo.Builder builder4 = (this.bitField0_ & 16) == 16 ? (SystemBlockedNumberInfo.Builder) this.systemBlockedNumberInfo_.toBuilder() : null;
                                this.systemBlockedNumberInfo_ = (SystemBlockedNumberInfo) codedInputStream.readMessage(SystemBlockedNumberInfo.parser(), extensionRegistryLite);
                                if (builder4 != null) {
                                    builder4.mergeFrom(this.systemBlockedNumberInfo_);
                                    this.systemBlockedNumberInfo_ = (SystemBlockedNumberInfo) builder4.buildPartial();
                                }
                                this.bitField0_ |= 16;
                            } else if (readTag == 50) {
                                Cp2Info.Builder builder5 = (this.bitField0_ & 2) == 2 ? (Cp2Info.Builder) this.extendedCp2Info_.toBuilder() : null;
                                this.extendedCp2Info_ = (Cp2Info) codedInputStream.readMessage(Cp2Info.parser(), extensionRegistryLite);
                                if (builder5 != null) {
                                    builder5.mergeFrom(this.extendedCp2Info_);
                                    this.extendedCp2Info_ = (Cp2Info) builder5.buildPartial();
                                }
                                this.bitField0_ |= 2;
                            } else if (readTag == 58) {
                                CnapInfo.Builder builder6 = (this.bitField0_ & 32) == 32 ? (CnapInfo.Builder) this.cnapInfo_.toBuilder() : null;
                                this.cnapInfo_ = (CnapInfo) codedInputStream.readMessage(CnapInfo.parser(), extensionRegistryLite);
                                if (builder6 != null) {
                                    builder6.mergeFrom(this.cnapInfo_);
                                    this.cnapInfo_ = (CnapInfo) builder6.buildPartial();
                                }
                                this.bitField0_ |= 32;
                            } else if (readTag == 66) {
                                CequintInfo.Builder builder7 = (this.bitField0_ & 64) == 64 ? (CequintInfo.Builder) this.cequintInfo_.toBuilder() : null;
                                this.cequintInfo_ = (CequintInfo) codedInputStream.readMessage(CequintInfo.parser(), extensionRegistryLite);
                                if (builder7 != null) {
                                    builder7.mergeFrom(this.cequintInfo_);
                                    this.cequintInfo_ = (CequintInfo) builder7.buildPartial();
                                }
                                this.bitField0_ |= 64;
                            } else if (readTag == 74) {
                                EmergencyInfo.Builder builder8 = (this.bitField0_ & 128) == 128 ? (EmergencyInfo.Builder) this.emergencyInfo_.toBuilder() : null;
                                this.emergencyInfo_ = (EmergencyInfo) codedInputStream.readMessage(EmergencyInfo.parser(), extensionRegistryLite);
                                if (builder8 != null) {
                                    builder8.mergeFrom(this.emergencyInfo_);
                                    this.emergencyInfo_ = (EmergencyInfo) builder8.buildPartial();
                                }
                                this.bitField0_ |= 128;
                            } else if (readTag == 82) {
                                MigratedInfo.Builder builder9 = (this.bitField0_ & 256) == 256 ? (MigratedInfo.Builder) this.migratedInfo_.toBuilder() : null;
                                this.migratedInfo_ = (MigratedInfo) codedInputStream.readMessage(MigratedInfo.parser(), extensionRegistryLite);
                                if (builder9 != null) {
                                    builder9.mergeFrom(this.migratedInfo_);
                                    this.migratedInfo_ = (MigratedInfo) builder9.buildPartial();
                                }
                                this.bitField0_ |= 256;
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
                return new PhoneLookupInfo();
            case 5:
                return new Builder((C05081) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (PhoneLookupInfo.class) {
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

    public CequintInfo getCequintInfo() {
        CequintInfo cequintInfo = this.cequintInfo_;
        return cequintInfo == null ? CequintInfo.getDefaultInstance() : cequintInfo;
    }

    public CnapInfo getCnapInfo() {
        CnapInfo cnapInfo = this.cnapInfo_;
        return cnapInfo == null ? CnapInfo.getDefaultInstance() : cnapInfo;
    }

    public Cp2Info getDefaultCp2Info() {
        Cp2Info cp2Info = this.defaultCp2Info_;
        return cp2Info == null ? Cp2Info.getDefaultInstance() : cp2Info;
    }

    public EmergencyInfo getEmergencyInfo() {
        EmergencyInfo emergencyInfo = this.emergencyInfo_;
        return emergencyInfo == null ? EmergencyInfo.getDefaultInstance() : emergencyInfo;
    }

    public Cp2Info getExtendedCp2Info() {
        Cp2Info cp2Info = this.extendedCp2Info_;
        return cp2Info == null ? Cp2Info.getDefaultInstance() : cp2Info;
    }

    public MigratedInfo getMigratedInfo() {
        MigratedInfo migratedInfo = this.migratedInfo_;
        return migratedInfo == null ? MigratedInfo.getDefaultInstance() : migratedInfo;
    }

    public PeopleApiInfo getPeopleApiInfo() {
        PeopleApiInfo peopleApiInfo = this.peopleApiInfo_;
        return peopleApiInfo == null ? PeopleApiInfo.getDefaultInstance() : peopleApiInfo;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeMessageSize(1, getDefaultCp2Info());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeMessageSize(2, getSpamInfo());
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeMessageSize(3, getPeopleApiInfo());
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeMessageSize(4, getSystemBlockedNumberInfo());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeMessageSize(6, getExtendedCp2Info());
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeMessageSize(7, getCnapInfo());
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeMessageSize(8, getCequintInfo());
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeMessageSize(9, getEmergencyInfo());
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeMessageSize(10, getMigratedInfo());
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public SpamInfo getSpamInfo() {
        SpamInfo spamInfo = this.spamInfo_;
        return spamInfo == null ? SpamInfo.getDefaultInstance() : spamInfo;
    }

    public SystemBlockedNumberInfo getSystemBlockedNumberInfo() {
        SystemBlockedNumberInfo systemBlockedNumberInfo = this.systemBlockedNumberInfo_;
        return systemBlockedNumberInfo == null ? SystemBlockedNumberInfo.getDefaultInstance() : systemBlockedNumberInfo;
    }

    public boolean hasPeopleApiInfo() {
        return (this.bitField0_ & 8) == 8;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeMessage(1, getDefaultCp2Info());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeMessage(2, getSpamInfo());
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeMessage(3, getPeopleApiInfo());
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeMessage(4, getSystemBlockedNumberInfo());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeMessage(6, getExtendedCp2Info());
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeMessage(7, getCnapInfo());
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeMessage(8, getCequintInfo());
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeMessage(9, getEmergencyInfo());
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeMessage(10, getMigratedInfo());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
