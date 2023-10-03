package com.android.dialer.historyitemactions;

import com.android.dialer.logging.ContactSource$Type;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class HistoryItemActionModuleInfo extends GeneratedMessageLite<HistoryItemActionModuleInfo, Builder> implements HistoryItemActionModuleInfoOrBuilder {
    /* access modifiers changed from: private */
    public static final HistoryItemActionModuleInfo DEFAULT_INSTANCE = new HistoryItemActionModuleInfo();
    private static volatile Parser<HistoryItemActionModuleInfo> PARSER;
    private int bitField0_;
    private int callType_ = 0;
    private boolean canReportAsInvalidNumber_ = false;
    private boolean canSupportAssistedDialing_ = false;
    private boolean canSupportCarrierVideoCall_ = false;
    private int contactSource_ = 0;
    private String countryIso_ = "";
    private int features_ = 0;
    private int host_ = 0;
    private boolean isBlocked_ = false;
    private boolean isEmergencyNumber_ = false;
    private boolean isSpam_ = false;
    private boolean isVoicemailCall_ = false;
    private String lookupUri_ = "";
    private String name_ = "";
    private String normalizedNumber_ = "";
    private String phoneAccountComponentName_ = "";

    /* renamed from: com.android.dialer.historyitemactions.HistoryItemActionModuleInfo$1 */
    static /* synthetic */ class C04861 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f26xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f26xa1df5c61 = r0
                int[] r0 = f26xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f26xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f26xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f26xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f26xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f26xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f26xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f26xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.historyitemactions.HistoryItemActionModuleInfo.C04861.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HistoryItemActionModuleInfo, Builder> implements HistoryItemActionModuleInfoOrBuilder {
        /* synthetic */ Builder(C04861 r1) {
            this();
        }

        public Builder setCallType(int i) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$1000((HistoryItemActionModuleInfo) this.instance, i);
            return this;
        }

        public Builder setCanReportAsInvalidNumber(boolean z) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$2000((HistoryItemActionModuleInfo) this.instance, z);
            return this;
        }

        public Builder setCanSupportAssistedDialing(boolean z) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$2200((HistoryItemActionModuleInfo) this.instance, z);
            return this;
        }

        public Builder setCanSupportCarrierVideoCall(boolean z) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$2400((HistoryItemActionModuleInfo) this.instance, z);
            return this;
        }

        public Builder setContactSource(ContactSource$Type contactSource$Type) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$3200((HistoryItemActionModuleInfo) this.instance, contactSource$Type);
            return this;
        }

        public Builder setCountryIso(String str) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$400((HistoryItemActionModuleInfo) this.instance, str);
            return this;
        }

        public Builder setFeatures(int i) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$1200((HistoryItemActionModuleInfo) this.instance, i);
            return this;
        }

        public Builder setHost(Host host) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$3400((HistoryItemActionModuleInfo) this.instance, host);
            return this;
        }

        public Builder setIsBlocked(boolean z) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$2600((HistoryItemActionModuleInfo) this.instance, z);
            return this;
        }

        public Builder setIsEmergencyNumber(boolean z) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$3600((HistoryItemActionModuleInfo) this.instance, z);
            return this;
        }

        public Builder setIsSpam(boolean z) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$2800((HistoryItemActionModuleInfo) this.instance, z);
            return this;
        }

        public Builder setIsVoicemailCall(boolean z) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$3000((HistoryItemActionModuleInfo) this.instance, z);
            return this;
        }

        public Builder setLookupUri(String str) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$1400((HistoryItemActionModuleInfo) this.instance, str);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$700((HistoryItemActionModuleInfo) this.instance, str);
            return this;
        }

        public Builder setNormalizedNumber(String str) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$100((HistoryItemActionModuleInfo) this.instance, str);
            return this;
        }

        public Builder setPhoneAccountComponentName(String str) {
            copyOnWrite();
            HistoryItemActionModuleInfo.access$1700((HistoryItemActionModuleInfo) this.instance, str);
            return this;
        }

        private Builder() {
            super(HistoryItemActionModuleInfo.DEFAULT_INSTANCE);
        }
    }

    public enum Host implements Internal.EnumLite {
        UNKNOWN(0),
        CALL_LOG(1),
        VOICEMAIL(2);
        
        private final int value;

        private Host(int i) {
            this.value = i;
        }

        public static Host forNumber(int i) {
            if (i == 0) {
                return UNKNOWN;
            }
            if (i == 1) {
                return CALL_LOG;
            }
            if (i != 2) {
                return null;
            }
            return VOICEMAIL;
        }

        public final int getNumber() {
            return this.value;
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private HistoryItemActionModuleInfo() {
    }

    static /* synthetic */ void access$100(HistoryItemActionModuleInfo historyItemActionModuleInfo, String str) {
        if (str != null) {
            historyItemActionModuleInfo.bitField0_ |= 1;
            historyItemActionModuleInfo.normalizedNumber_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1000(HistoryItemActionModuleInfo historyItemActionModuleInfo, int i) {
        historyItemActionModuleInfo.bitField0_ |= 8;
        historyItemActionModuleInfo.callType_ = i;
    }

    static /* synthetic */ void access$1200(HistoryItemActionModuleInfo historyItemActionModuleInfo, int i) {
        historyItemActionModuleInfo.bitField0_ |= 16;
        historyItemActionModuleInfo.features_ = i;
    }

    static /* synthetic */ void access$1400(HistoryItemActionModuleInfo historyItemActionModuleInfo, String str) {
        if (str != null) {
            historyItemActionModuleInfo.bitField0_ |= 32;
            historyItemActionModuleInfo.lookupUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1700(HistoryItemActionModuleInfo historyItemActionModuleInfo, String str) {
        if (str != null) {
            historyItemActionModuleInfo.bitField0_ |= 64;
            historyItemActionModuleInfo.phoneAccountComponentName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2000(HistoryItemActionModuleInfo historyItemActionModuleInfo, boolean z) {
        historyItemActionModuleInfo.bitField0_ |= 128;
        historyItemActionModuleInfo.canReportAsInvalidNumber_ = z;
    }

    static /* synthetic */ void access$2200(HistoryItemActionModuleInfo historyItemActionModuleInfo, boolean z) {
        historyItemActionModuleInfo.bitField0_ |= 256;
        historyItemActionModuleInfo.canSupportAssistedDialing_ = z;
    }

    static /* synthetic */ void access$2400(HistoryItemActionModuleInfo historyItemActionModuleInfo, boolean z) {
        historyItemActionModuleInfo.bitField0_ |= 512;
        historyItemActionModuleInfo.canSupportCarrierVideoCall_ = z;
    }

    static /* synthetic */ void access$2600(HistoryItemActionModuleInfo historyItemActionModuleInfo, boolean z) {
        historyItemActionModuleInfo.bitField0_ |= 1024;
        historyItemActionModuleInfo.isBlocked_ = z;
    }

    static /* synthetic */ void access$2800(HistoryItemActionModuleInfo historyItemActionModuleInfo, boolean z) {
        historyItemActionModuleInfo.bitField0_ |= 2048;
        historyItemActionModuleInfo.isSpam_ = z;
    }

    static /* synthetic */ void access$3000(HistoryItemActionModuleInfo historyItemActionModuleInfo, boolean z) {
        historyItemActionModuleInfo.bitField0_ |= 4096;
        historyItemActionModuleInfo.isVoicemailCall_ = z;
    }

    static /* synthetic */ void access$3200(HistoryItemActionModuleInfo historyItemActionModuleInfo, ContactSource$Type contactSource$Type) {
        if (contactSource$Type != null) {
            historyItemActionModuleInfo.bitField0_ |= 8192;
            historyItemActionModuleInfo.contactSource_ = contactSource$Type.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$3400(HistoryItemActionModuleInfo historyItemActionModuleInfo, Host host) {
        if (host != null) {
            historyItemActionModuleInfo.bitField0_ |= 16384;
            historyItemActionModuleInfo.host_ = host.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$3600(HistoryItemActionModuleInfo historyItemActionModuleInfo, boolean z) {
        historyItemActionModuleInfo.bitField0_ |= 32768;
        historyItemActionModuleInfo.isEmergencyNumber_ = z;
    }

    static /* synthetic */ void access$400(HistoryItemActionModuleInfo historyItemActionModuleInfo, String str) {
        if (str != null) {
            historyItemActionModuleInfo.bitField0_ |= 2;
            historyItemActionModuleInfo.countryIso_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$700(HistoryItemActionModuleInfo historyItemActionModuleInfo, String str) {
        if (str != null) {
            historyItemActionModuleInfo.bitField0_ |= 4;
            historyItemActionModuleInfo.name_ = str;
            return;
        }
        throw new NullPointerException();
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
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                HistoryItemActionModuleInfo historyItemActionModuleInfo = (HistoryItemActionModuleInfo) obj2;
                this.normalizedNumber_ = visitor.visitString((this.bitField0_ & 1) == 1, this.normalizedNumber_, (historyItemActionModuleInfo.bitField0_ & 1) == 1, historyItemActionModuleInfo.normalizedNumber_);
                this.countryIso_ = visitor.visitString((this.bitField0_ & 2) == 2, this.countryIso_, (historyItemActionModuleInfo.bitField0_ & 2) == 2, historyItemActionModuleInfo.countryIso_);
                this.name_ = visitor.visitString((this.bitField0_ & 4) == 4, this.name_, (historyItemActionModuleInfo.bitField0_ & 4) == 4, historyItemActionModuleInfo.name_);
                this.callType_ = visitor.visitInt((this.bitField0_ & 8) == 8, this.callType_, (historyItemActionModuleInfo.bitField0_ & 8) == 8, historyItemActionModuleInfo.callType_);
                this.features_ = visitor.visitInt((this.bitField0_ & 16) == 16, this.features_, (historyItemActionModuleInfo.bitField0_ & 16) == 16, historyItemActionModuleInfo.features_);
                this.lookupUri_ = visitor.visitString((this.bitField0_ & 32) == 32, this.lookupUri_, (historyItemActionModuleInfo.bitField0_ & 32) == 32, historyItemActionModuleInfo.lookupUri_);
                this.phoneAccountComponentName_ = visitor.visitString((this.bitField0_ & 64) == 64, this.phoneAccountComponentName_, (historyItemActionModuleInfo.bitField0_ & 64) == 64, historyItemActionModuleInfo.phoneAccountComponentName_);
                this.canReportAsInvalidNumber_ = visitor.visitBoolean((this.bitField0_ & 128) == 128, this.canReportAsInvalidNumber_, (historyItemActionModuleInfo.bitField0_ & 128) == 128, historyItemActionModuleInfo.canReportAsInvalidNumber_);
                this.canSupportAssistedDialing_ = visitor.visitBoolean((this.bitField0_ & 256) == 256, this.canSupportAssistedDialing_, (historyItemActionModuleInfo.bitField0_ & 256) == 256, historyItemActionModuleInfo.canSupportAssistedDialing_);
                this.canSupportCarrierVideoCall_ = visitor.visitBoolean((this.bitField0_ & 512) == 512, this.canSupportCarrierVideoCall_, (historyItemActionModuleInfo.bitField0_ & 512) == 512, historyItemActionModuleInfo.canSupportCarrierVideoCall_);
                this.isBlocked_ = visitor.visitBoolean((this.bitField0_ & 1024) == 1024, this.isBlocked_, (historyItemActionModuleInfo.bitField0_ & 1024) == 1024, historyItemActionModuleInfo.isBlocked_);
                this.isSpam_ = visitor.visitBoolean((this.bitField0_ & 2048) == 2048, this.isSpam_, (historyItemActionModuleInfo.bitField0_ & 2048) == 2048, historyItemActionModuleInfo.isSpam_);
                this.isVoicemailCall_ = visitor.visitBoolean((this.bitField0_ & 4096) == 4096, this.isVoicemailCall_, (historyItemActionModuleInfo.bitField0_ & 4096) == 4096, historyItemActionModuleInfo.isVoicemailCall_);
                this.contactSource_ = visitor.visitInt((this.bitField0_ & 8192) == 8192, this.contactSource_, (historyItemActionModuleInfo.bitField0_ & 8192) == 8192, historyItemActionModuleInfo.contactSource_);
                this.host_ = visitor.visitInt((this.bitField0_ & 16384) == 16384, this.host_, (historyItemActionModuleInfo.bitField0_ & 16384) == 16384, historyItemActionModuleInfo.host_);
                this.isEmergencyNumber_ = visitor.visitBoolean((this.bitField0_ & 32768) == 32768, this.isEmergencyNumber_, (historyItemActionModuleInfo.bitField0_ & 32768) == 32768, historyItemActionModuleInfo.isEmergencyNumber_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= historyItemActionModuleInfo.bitField0_;
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
                            case 10:
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 1;
                                this.normalizedNumber_ = readString;
                                break;
                            case 18:
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.countryIso_ = readString2;
                                break;
                            case 26:
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.name_ = readString3;
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.callType_ = codedInputStream.readRawVarint32();
                                break;
                            case 40:
                                this.bitField0_ |= 16;
                                this.features_ = codedInputStream.readRawVarint32();
                                break;
                            case 50:
                                String readString4 = codedInputStream.readString();
                                this.bitField0_ |= 32;
                                this.lookupUri_ = readString4;
                                break;
                            case 58:
                                String readString5 = codedInputStream.readString();
                                this.bitField0_ |= 64;
                                this.phoneAccountComponentName_ = readString5;
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.canReportAsInvalidNumber_ = codedInputStream.readBool();
                                break;
                            case 72:
                                this.bitField0_ |= 256;
                                this.canSupportAssistedDialing_ = codedInputStream.readBool();
                                break;
                            case 80:
                                this.bitField0_ |= 512;
                                this.canSupportCarrierVideoCall_ = codedInputStream.readBool();
                                break;
                            case 88:
                                this.bitField0_ |= 1024;
                                this.isBlocked_ = codedInputStream.readBool();
                                break;
                            case 96:
                                this.bitField0_ |= 2048;
                                this.isSpam_ = codedInputStream.readBool();
                                break;
                            case 104:
                                this.bitField0_ |= 4096;
                                this.isVoicemailCall_ = codedInputStream.readBool();
                                break;
                            case 112:
                                int readRawVarint32 = codedInputStream.readRawVarint32();
                                if (ContactSource$Type.forNumber(readRawVarint32) != null) {
                                    this.bitField0_ |= 8192;
                                    this.contactSource_ = readRawVarint32;
                                    break;
                                } else {
                                    super.mergeVarintField(14, readRawVarint32);
                                    break;
                                }
                            case 120:
                                int readRawVarint322 = codedInputStream.readRawVarint32();
                                if (Host.forNumber(readRawVarint322) != null) {
                                    this.bitField0_ |= 16384;
                                    this.host_ = readRawVarint322;
                                    break;
                                } else {
                                    super.mergeVarintField(15, readRawVarint322);
                                    break;
                                }
                            case 128:
                                this.bitField0_ |= 32768;
                                this.isEmergencyNumber_ = codedInputStream.readBool();
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
                return null;
            case 4:
                return new HistoryItemActionModuleInfo();
            case 5:
                return new Builder((C04861) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (HistoryItemActionModuleInfo.class) {
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

    public int getCallType() {
        return this.callType_;
    }

    public boolean getCanSupportAssistedDialing() {
        return this.canSupportAssistedDialing_;
    }

    public boolean getCanSupportCarrierVideoCall() {
        return this.canSupportCarrierVideoCall_;
    }

    public ContactSource$Type getContactSource() {
        ContactSource$Type forNumber = ContactSource$Type.forNumber(this.contactSource_);
        return forNumber == null ? ContactSource$Type.UNKNOWN_SOURCE_TYPE : forNumber;
    }

    public String getCountryIso() {
        return this.countryIso_;
    }

    public int getFeatures() {
        return this.features_;
    }

    public Host getHost() {
        Host forNumber = Host.forNumber(this.host_);
        return forNumber == null ? Host.UNKNOWN : forNumber;
    }

    public boolean getIsBlocked() {
        return this.isBlocked_;
    }

    public boolean getIsEmergencyNumber() {
        return this.isEmergencyNumber_;
    }

    public boolean getIsSpam() {
        return this.isSpam_;
    }

    public boolean getIsVoicemailCall() {
        return this.isVoicemailCall_;
    }

    public String getLookupUri() {
        return this.lookupUri_;
    }

    public String getName() {
        return this.name_;
    }

    public String getNormalizedNumber() {
        return this.normalizedNumber_;
    }

    public String getPhoneAccountComponentName() {
        return this.phoneAccountComponentName_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, this.normalizedNumber_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, this.countryIso_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(3, this.name_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeInt32Size(4, this.callType_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeInt32Size(5, this.features_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeStringSize(6, this.lookupUri_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeStringSize(7, this.phoneAccountComponentName_);
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeBoolSize(8, this.canReportAsInvalidNumber_);
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeBoolSize(9, this.canSupportAssistedDialing_);
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeBoolSize(10, this.canSupportCarrierVideoCall_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            i2 += CodedOutputStream.computeBoolSize(11, this.isBlocked_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            i2 += CodedOutputStream.computeBoolSize(12, this.isSpam_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            i2 += CodedOutputStream.computeBoolSize(13, this.isVoicemailCall_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            i2 += CodedOutputStream.computeEnumSize(14, this.contactSource_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            i2 += CodedOutputStream.computeEnumSize(15, this.host_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            i2 += CodedOutputStream.computeBoolSize(16, this.isEmergencyNumber_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, this.normalizedNumber_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, this.countryIso_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(3, this.name_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeInt32(4, this.callType_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeInt32(5, this.features_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeString(6, this.lookupUri_);
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeString(7, this.phoneAccountComponentName_);
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeBool(8, this.canReportAsInvalidNumber_);
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeBool(9, this.canSupportAssistedDialing_);
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeBool(10, this.canSupportCarrierVideoCall_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            codedOutputStream.writeBool(11, this.isBlocked_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            codedOutputStream.writeBool(12, this.isSpam_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            codedOutputStream.writeBool(13, this.isVoicemailCall_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            codedOutputStream.writeInt32(14, this.contactSource_);
        }
        if ((this.bitField0_ & 16384) == 16384) {
            codedOutputStream.writeInt32(15, this.host_);
        }
        if ((this.bitField0_ & 32768) == 32768) {
            codedOutputStream.writeBool(16, this.isEmergencyNumber_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
