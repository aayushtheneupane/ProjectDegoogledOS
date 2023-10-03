package com.android.dialer.voicemail.model;

import com.android.dialer.DialerPhoneNumber;
import com.android.dialer.NumberAttributes;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class VoicemailEntry extends GeneratedMessageLite<VoicemailEntry, Builder> implements VoicemailEntryOrBuilder {
    /* access modifiers changed from: private */
    public static final VoicemailEntry DEFAULT_INSTANCE = new VoicemailEntry();
    private static volatile Parser<VoicemailEntry> PARSER;
    private int bitField0_;
    private int callType_ = 0;
    private long duration_ = 0;
    private String formattedNumber_ = "";
    private String geocodedLocation_ = "";
    private long id_ = 0;
    private int isRead_ = 0;
    private NumberAttributes numberAttributes_;
    private DialerPhoneNumber number_;
    private String phoneAccountComponentName_ = "";
    private String phoneAccountId_ = "";
    private long timestamp_ = 0;
    private int transcriptionState_ = 0;
    private String transcription_ = "";
    private String voicemailUri_ = "";

    /* renamed from: com.android.dialer.voicemail.model.VoicemailEntry$1 */
    static /* synthetic */ class C06081 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f39xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f39xa1df5c61 = r0
                int[] r0 = f39xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f39xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f39xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f39xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f39xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f39xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f39xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f39xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.voicemail.model.VoicemailEntry.C06081.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<VoicemailEntry, Builder> implements VoicemailEntryOrBuilder {
        /* synthetic */ Builder(C06081 r1) {
            this();
        }

        public Builder setCallType(int i) {
            copyOnWrite();
            VoicemailEntry.access$2300((VoicemailEntry) this.instance, i);
            return this;
        }

        public Builder setDuration(long j) {
            copyOnWrite();
            VoicemailEntry.access$1500((VoicemailEntry) this.instance, j);
            return this;
        }

        public Builder setFormattedNumber(String str) {
            copyOnWrite();
            VoicemailEntry.access$900((VoicemailEntry) this.instance, str);
            return this;
        }

        public Builder setGeocodedLocation(String str) {
            copyOnWrite();
            VoicemailEntry.access$1200((VoicemailEntry) this.instance, str);
            return this;
        }

        public Builder setId(long j) {
            copyOnWrite();
            VoicemailEntry.access$100((VoicemailEntry) this.instance, j);
            return this;
        }

        public Builder setIsRead(int i) {
            copyOnWrite();
            VoicemailEntry.access$2500((VoicemailEntry) this.instance, i);
            return this;
        }

        public Builder setNumber(DialerPhoneNumber dialerPhoneNumber) {
            copyOnWrite();
            VoicemailEntry.access$500((VoicemailEntry) this.instance, dialerPhoneNumber);
            return this;
        }

        public Builder setNumberAttributes(NumberAttributes numberAttributes) {
            copyOnWrite();
            VoicemailEntry.access$2700((VoicemailEntry) this.instance, numberAttributes);
            return this;
        }

        public Builder setPhoneAccountComponentName(String str) {
            copyOnWrite();
            VoicemailEntry.access$3300((VoicemailEntry) this.instance, str);
            return this;
        }

        public Builder setPhoneAccountId(String str) {
            copyOnWrite();
            VoicemailEntry.access$3600((VoicemailEntry) this.instance, str);
            return this;
        }

        public Builder setTimestamp(long j) {
            copyOnWrite();
            VoicemailEntry.access$300((VoicemailEntry) this.instance, j);
            return this;
        }

        public Builder setTranscription(String str) {
            copyOnWrite();
            VoicemailEntry.access$1700((VoicemailEntry) this.instance, str);
            return this;
        }

        public Builder setTranscriptionState(int i) {
            copyOnWrite();
            VoicemailEntry.access$3100((VoicemailEntry) this.instance, i);
            return this;
        }

        public Builder setVoicemailUri(String str) {
            copyOnWrite();
            VoicemailEntry.access$2000((VoicemailEntry) this.instance, str);
            return this;
        }

        private Builder() {
            super(VoicemailEntry.DEFAULT_INSTANCE);
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private VoicemailEntry() {
    }

    static /* synthetic */ void access$100(VoicemailEntry voicemailEntry, long j) {
        voicemailEntry.bitField0_ |= 1;
        voicemailEntry.id_ = j;
    }

    static /* synthetic */ void access$1200(VoicemailEntry voicemailEntry, String str) {
        if (str != null) {
            voicemailEntry.bitField0_ |= 16;
            voicemailEntry.geocodedLocation_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1500(VoicemailEntry voicemailEntry, long j) {
        voicemailEntry.bitField0_ |= 32;
        voicemailEntry.duration_ = j;
    }

    static /* synthetic */ void access$1700(VoicemailEntry voicemailEntry, String str) {
        if (str != null) {
            voicemailEntry.bitField0_ |= 64;
            voicemailEntry.transcription_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2000(VoicemailEntry voicemailEntry, String str) {
        if (str != null) {
            voicemailEntry.bitField0_ |= 128;
            voicemailEntry.voicemailUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$2300(VoicemailEntry voicemailEntry, int i) {
        voicemailEntry.bitField0_ |= 256;
        voicemailEntry.callType_ = i;
    }

    static /* synthetic */ void access$2500(VoicemailEntry voicemailEntry, int i) {
        voicemailEntry.bitField0_ |= 512;
        voicemailEntry.isRead_ = i;
    }

    static /* synthetic */ void access$2700(VoicemailEntry voicemailEntry, NumberAttributes numberAttributes) {
        if (numberAttributes != null) {
            voicemailEntry.numberAttributes_ = numberAttributes;
            voicemailEntry.bitField0_ |= 1024;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$300(VoicemailEntry voicemailEntry, long j) {
        voicemailEntry.bitField0_ |= 2;
        voicemailEntry.timestamp_ = j;
    }

    static /* synthetic */ void access$3100(VoicemailEntry voicemailEntry, int i) {
        voicemailEntry.bitField0_ |= 2048;
        voicemailEntry.transcriptionState_ = i;
    }

    static /* synthetic */ void access$3300(VoicemailEntry voicemailEntry, String str) {
        if (str != null) {
            voicemailEntry.bitField0_ |= 4096;
            voicemailEntry.phoneAccountComponentName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$3600(VoicemailEntry voicemailEntry, String str) {
        if (str != null) {
            voicemailEntry.bitField0_ |= 8192;
            voicemailEntry.phoneAccountId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$500(VoicemailEntry voicemailEntry, DialerPhoneNumber dialerPhoneNumber) {
        if (dialerPhoneNumber != null) {
            voicemailEntry.number_ = dialerPhoneNumber;
            voicemailEntry.bitField0_ |= 4;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$900(VoicemailEntry voicemailEntry, String str) {
        if (str != null) {
            voicemailEntry.bitField0_ |= 8;
            voicemailEntry.formattedNumber_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0164, code lost:
        if (parseUnknownField(r15, r0) == false) goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0166, code lost:
        r2 = 8192;
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x016d, code lost:
        r2 = 8192;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object dynamicMethod(com.google.protobuf.GeneratedMessageLite.MethodToInvoke r27, java.lang.Object r28, java.lang.Object r29) {
        /*
            r26 = this;
            r1 = r26
            int r0 = r27.ordinal()
            r2 = 8192(0x2000, float:1.14794E-41)
            r3 = 4096(0x1000, float:5.74E-42)
            r4 = 2048(0x800, float:2.87E-42)
            r5 = 512(0x200, float:7.175E-43)
            r6 = 256(0x100, float:3.59E-43)
            r7 = 128(0x80, float:1.794E-43)
            r8 = 64
            r9 = 32
            r10 = 16
            r11 = 8
            r12 = 2
            r15 = 0
            switch(r0) {
                case 0: goto L_0x0328;
                case 1: goto L_0x0197;
                case 2: goto L_0x004e;
                case 3: goto L_0x004d;
                case 4: goto L_0x0047;
                case 5: goto L_0x0041;
                case 6: goto L_0x0194;
                case 7: goto L_0x0025;
                default: goto L_0x001f;
            }
        L_0x001f:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            throw r0
        L_0x0025:
            com.google.protobuf.Parser<com.android.dialer.voicemail.model.VoicemailEntry> r0 = PARSER
            if (r0 != 0) goto L_0x003e
            java.lang.Class<com.android.dialer.voicemail.model.VoicemailEntry> r1 = com.android.dialer.voicemail.model.VoicemailEntry.class
            monitor-enter(r1)
            com.google.protobuf.Parser<com.android.dialer.voicemail.model.VoicemailEntry> r0 = PARSER     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x0039
            com.google.protobuf.GeneratedMessageLite$DefaultInstanceBasedParser r0 = new com.google.protobuf.GeneratedMessageLite$DefaultInstanceBasedParser     // Catch:{ all -> 0x003b }
            com.android.dialer.voicemail.model.VoicemailEntry r2 = DEFAULT_INSTANCE     // Catch:{ all -> 0x003b }
            r0.<init>(r2)     // Catch:{ all -> 0x003b }
            PARSER = r0     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r1)     // Catch:{ all -> 0x003b }
            goto L_0x003e
        L_0x003b:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x003b }
            throw r0
        L_0x003e:
            com.google.protobuf.Parser<com.android.dialer.voicemail.model.VoicemailEntry> r0 = PARSER
            return r0
        L_0x0041:
            com.android.dialer.voicemail.model.VoicemailEntry$Builder r0 = new com.android.dialer.voicemail.model.VoicemailEntry$Builder
            r0.<init>(r15)
            return r0
        L_0x0047:
            com.android.dialer.voicemail.model.VoicemailEntry r0 = new com.android.dialer.voicemail.model.VoicemailEntry
            r0.<init>()
            return r0
        L_0x004d:
            return r15
        L_0x004e:
            r0 = r28
            com.google.protobuf.CodedInputStream r0 = (com.google.protobuf.CodedInputStream) r0
            r13 = r29
            com.google.protobuf.ExtensionRegistryLite r13 = (com.google.protobuf.ExtensionRegistryLite) r13
            r16 = 0
        L_0x0058:
            if (r16 != 0) goto L_0x0194
            int r15 = r0.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            switch(r15) {
                case 0: goto L_0x0166;
                case 8: goto L_0x0157;
                case 16: goto L_0x014b;
                case 26: goto L_0x0119;
                case 34: goto L_0x010d;
                case 42: goto L_0x0101;
                case 48: goto L_0x00f5;
                case 58: goto L_0x00e8;
                case 66: goto L_0x00db;
                case 72: goto L_0x00ce;
                case 80: goto L_0x00c1;
                case 90: goto L_0x008e;
                case 96: goto L_0x0081;
                case 106: goto L_0x0074;
                case 114: goto L_0x0067;
                default: goto L_0x0061;
            }     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
        L_0x0061:
            boolean r2 = r1.parseUnknownField(r15, r0)     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x0164
        L_0x0067:
            java.lang.String r15 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r14 = r14 | r2
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.phoneAccountId_ = r15     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x0074:
            java.lang.String r14 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            int r15 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r15 = r15 | r3
            r1.bitField0_ = r15     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.phoneAccountComponentName_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x0081:
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r14 = r14 | r4
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            int r14 = r0.readRawVarint32()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.transcriptionState_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x008e:
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r15 = 1024(0x400, float:1.435E-42)
            r14 = r14 & r15
            if (r14 != r15) goto L_0x009e
            com.android.dialer.NumberAttributes r14 = r1.numberAttributes_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.google.protobuf.GeneratedMessageLite$Builder r14 = r14.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.android.dialer.NumberAttributes$Builder r14 = (com.android.dialer.NumberAttributes.Builder) r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x009f
        L_0x009e:
            r14 = 0
        L_0x009f:
            com.google.protobuf.Parser r2 = com.android.dialer.NumberAttributes.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.google.protobuf.MessageLite r2 = r0.readMessage(r2, r13)     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.android.dialer.NumberAttributes r2 = (com.android.dialer.NumberAttributes) r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.numberAttributes_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            if (r14 == 0) goto L_0x00ba
            com.android.dialer.NumberAttributes r2 = r1.numberAttributes_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r14.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.google.protobuf.GeneratedMessageLite r2 = r14.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.android.dialer.NumberAttributes r2 = (com.android.dialer.NumberAttributes) r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.numberAttributes_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
        L_0x00ba:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r2 = r2 | r15
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x00c1:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r2 = r2 | r5
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            int r2 = r0.readRawVarint32()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.isRead_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x00ce:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r2 = r2 | r6
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            int r2 = r0.readRawVarint32()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.callType_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x00db:
            java.lang.String r2 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r14 = r14 | r7
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.voicemailUri_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x00e8:
            java.lang.String r2 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r14 = r14 | r8
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.transcription_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x00f5:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r2 = r2 | r9
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            long r14 = r0.readRawVarint64()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.duration_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x0101:
            java.lang.String r2 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r14 = r14 | r10
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.geocodedLocation_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x010d:
            java.lang.String r2 = r0.readString()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            int r14 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r14 = r14 | r11
            r1.bitField0_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.formattedNumber_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x0119:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r14 = 4
            r2 = r2 & r14
            if (r2 != r14) goto L_0x0129
            com.android.dialer.DialerPhoneNumber r2 = r1.number_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.google.protobuf.GeneratedMessageLite$Builder r2 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r15 = r2
            com.android.dialer.DialerPhoneNumber$Builder r15 = (com.android.dialer.DialerPhoneNumber.Builder) r15     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x012a
        L_0x0129:
            r15 = 0
        L_0x012a:
            com.google.protobuf.Parser r2 = com.android.dialer.DialerPhoneNumber.parser()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.google.protobuf.MessageLite r2 = r0.readMessage(r2, r13)     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.android.dialer.DialerPhoneNumber r2 = (com.android.dialer.DialerPhoneNumber) r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.number_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            if (r15 == 0) goto L_0x0145
            com.android.dialer.DialerPhoneNumber r2 = r1.number_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r15.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.google.protobuf.GeneratedMessageLite r2 = r15.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            com.android.dialer.DialerPhoneNumber r2 = (com.android.dialer.DialerPhoneNumber) r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.number_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
        L_0x0145:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r2 = r2 | r14
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x014b:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r2 = r2 | r12
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            long r14 = r0.readRawVarint64()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.timestamp_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x0157:
            int r2 = r1.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r14 = 1
            r2 = r2 | r14
            r1.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            long r14 = r0.readRawVarint64()     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            r1.id_ = r14     // Catch:{ InvalidProtocolBufferException -> 0x0188, IOException -> 0x0174 }
            goto L_0x016d
        L_0x0164:
            if (r2 != 0) goto L_0x016d
        L_0x0166:
            r2 = 8192(0x2000, float:1.14794E-41)
            r15 = 0
            r16 = 1
            goto L_0x0058
        L_0x016d:
            r2 = 8192(0x2000, float:1.14794E-41)
            r15 = 0
            goto L_0x0058
        L_0x0172:
            r0 = move-exception
            goto L_0x0193
        L_0x0174:
            r0 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0172 }
            com.google.protobuf.InvalidProtocolBufferException r3 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x0172 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0172 }
            r3.<init>(r0)     // Catch:{ all -> 0x0172 }
            com.google.protobuf.InvalidProtocolBufferException r0 = r3.setUnfinishedMessage(r1)     // Catch:{ all -> 0x0172 }
            r2.<init>(r0)     // Catch:{ all -> 0x0172 }
            throw r2     // Catch:{ all -> 0x0172 }
        L_0x0188:
            r0 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0172 }
            com.google.protobuf.InvalidProtocolBufferException r0 = r0.setUnfinishedMessage(r1)     // Catch:{ all -> 0x0172 }
            r2.<init>(r0)     // Catch:{ all -> 0x0172 }
            throw r2     // Catch:{ all -> 0x0172 }
        L_0x0193:
            throw r0
        L_0x0194:
            com.android.dialer.voicemail.model.VoicemailEntry r0 = DEFAULT_INSTANCE
            return r0
        L_0x0197:
            r0 = r28
            com.google.protobuf.GeneratedMessageLite$Visitor r0 = (com.google.protobuf.GeneratedMessageLite.Visitor) r0
            r2 = r29
            com.android.dialer.voicemail.model.VoicemailEntry r2 = (com.android.dialer.voicemail.model.VoicemailEntry) r2
            int r13 = r1.bitField0_
            r14 = 1
            r13 = r13 & r14
            if (r13 != r14) goto L_0x01a8
            r20 = r14
            goto L_0x01aa
        L_0x01a8:
            r20 = 0
        L_0x01aa:
            long r3 = r1.id_
            int r13 = r2.bitField0_
            r13 = r13 & r14
            if (r13 != r14) goto L_0x01b4
            r23 = r14
            goto L_0x01b6
        L_0x01b4:
            r23 = 0
        L_0x01b6:
            long r14 = r2.id_
            r19 = r0
            r21 = r3
            r24 = r14
            long r3 = r19.visitLong(r20, r21, r23, r24)
            r1.id_ = r3
            int r3 = r1.bitField0_
            r3 = r3 & r12
            if (r3 != r12) goto L_0x01cb
            r13 = 1
            goto L_0x01cc
        L_0x01cb:
            r13 = 0
        L_0x01cc:
            long r14 = r1.timestamp_
            int r3 = r2.bitField0_
            r3 = r3 & r12
            if (r3 != r12) goto L_0x01d6
            r16 = 1
            goto L_0x01d8
        L_0x01d6:
            r16 = 0
        L_0x01d8:
            long r3 = r2.timestamp_
            r12 = r0
            r19 = 1
            r17 = r3
            long r3 = r12.visitLong(r13, r14, r16, r17)
            r1.timestamp_ = r3
            com.android.dialer.DialerPhoneNumber r3 = r1.number_
            com.android.dialer.DialerPhoneNumber r4 = r2.number_
            com.google.protobuf.MessageLite r3 = r0.visitMessage(r3, r4)
            com.android.dialer.DialerPhoneNumber r3 = (com.android.dialer.DialerPhoneNumber) r3
            r1.number_ = r3
            int r3 = r1.bitField0_
            r3 = r3 & r11
            if (r3 != r11) goto L_0x01f9
            r3 = r19
            goto L_0x01fa
        L_0x01f9:
            r3 = 0
        L_0x01fa:
            java.lang.String r4 = r1.formattedNumber_
            int r12 = r2.bitField0_
            r12 = r12 & r11
            if (r12 != r11) goto L_0x0204
            r11 = r19
            goto L_0x0205
        L_0x0204:
            r11 = 0
        L_0x0205:
            java.lang.String r12 = r2.formattedNumber_
            java.lang.String r3 = r0.visitString(r3, r4, r11, r12)
            r1.formattedNumber_ = r3
            int r3 = r1.bitField0_
            r3 = r3 & r10
            if (r3 != r10) goto L_0x0215
            r3 = r19
            goto L_0x0216
        L_0x0215:
            r3 = 0
        L_0x0216:
            java.lang.String r4 = r1.geocodedLocation_
            int r11 = r2.bitField0_
            r11 = r11 & r10
            if (r11 != r10) goto L_0x0220
            r10 = r19
            goto L_0x0221
        L_0x0220:
            r10 = 0
        L_0x0221:
            java.lang.String r11 = r2.geocodedLocation_
            java.lang.String r3 = r0.visitString(r3, r4, r10, r11)
            r1.geocodedLocation_ = r3
            int r3 = r1.bitField0_
            r3 = r3 & r9
            if (r3 != r9) goto L_0x0231
            r10 = r19
            goto L_0x0232
        L_0x0231:
            r10 = 0
        L_0x0232:
            long r11 = r1.duration_
            int r3 = r2.bitField0_
            r3 = r3 & r9
            if (r3 != r9) goto L_0x023c
            r13 = r19
            goto L_0x023d
        L_0x023c:
            r13 = 0
        L_0x023d:
            long r14 = r2.duration_
            r9 = r0
            long r3 = r9.visitLong(r10, r11, r13, r14)
            r1.duration_ = r3
            int r3 = r1.bitField0_
            r3 = r3 & r8
            if (r3 != r8) goto L_0x024e
            r3 = r19
            goto L_0x024f
        L_0x024e:
            r3 = 0
        L_0x024f:
            java.lang.String r4 = r1.transcription_
            int r9 = r2.bitField0_
            r9 = r9 & r8
            if (r9 != r8) goto L_0x0259
            r8 = r19
            goto L_0x025a
        L_0x0259:
            r8 = 0
        L_0x025a:
            java.lang.String r9 = r2.transcription_
            java.lang.String r3 = r0.visitString(r3, r4, r8, r9)
            r1.transcription_ = r3
            int r3 = r1.bitField0_
            r3 = r3 & r7
            if (r3 != r7) goto L_0x026a
            r3 = r19
            goto L_0x026b
        L_0x026a:
            r3 = 0
        L_0x026b:
            java.lang.String r4 = r1.voicemailUri_
            int r8 = r2.bitField0_
            r8 = r8 & r7
            if (r8 != r7) goto L_0x0275
            r7 = r19
            goto L_0x0276
        L_0x0275:
            r7 = 0
        L_0x0276:
            java.lang.String r8 = r2.voicemailUri_
            java.lang.String r3 = r0.visitString(r3, r4, r7, r8)
            r1.voicemailUri_ = r3
            int r3 = r1.bitField0_
            r3 = r3 & r6
            if (r3 != r6) goto L_0x0286
            r3 = r19
            goto L_0x0287
        L_0x0286:
            r3 = 0
        L_0x0287:
            int r4 = r1.callType_
            int r7 = r2.bitField0_
            r7 = r7 & r6
            if (r7 != r6) goto L_0x0291
            r6 = r19
            goto L_0x0292
        L_0x0291:
            r6 = 0
        L_0x0292:
            int r7 = r2.callType_
            int r3 = r0.visitInt(r3, r4, r6, r7)
            r1.callType_ = r3
            int r3 = r1.bitField0_
            r3 = r3 & r5
            if (r3 != r5) goto L_0x02a2
            r3 = r19
            goto L_0x02a3
        L_0x02a2:
            r3 = 0
        L_0x02a3:
            int r4 = r1.isRead_
            int r6 = r2.bitField0_
            r6 = r6 & r5
            if (r6 != r5) goto L_0x02ad
            r5 = r19
            goto L_0x02ae
        L_0x02ad:
            r5 = 0
        L_0x02ae:
            int r6 = r2.isRead_
            int r3 = r0.visitInt(r3, r4, r5, r6)
            r1.isRead_ = r3
            com.android.dialer.NumberAttributes r3 = r1.numberAttributes_
            com.android.dialer.NumberAttributes r4 = r2.numberAttributes_
            com.google.protobuf.MessageLite r3 = r0.visitMessage(r3, r4)
            com.android.dialer.NumberAttributes r3 = (com.android.dialer.NumberAttributes) r3
            r1.numberAttributes_ = r3
            int r3 = r1.bitField0_
            r4 = 2048(0x800, float:2.87E-42)
            r3 = r3 & r4
            if (r3 != r4) goto L_0x02cc
            r3 = r19
            goto L_0x02cd
        L_0x02cc:
            r3 = 0
        L_0x02cd:
            int r5 = r1.transcriptionState_
            int r6 = r2.bitField0_
            r6 = r6 & r4
            if (r6 != r4) goto L_0x02d7
            r4 = r19
            goto L_0x02d8
        L_0x02d7:
            r4 = 0
        L_0x02d8:
            int r6 = r2.transcriptionState_
            int r3 = r0.visitInt(r3, r5, r4, r6)
            r1.transcriptionState_ = r3
            int r3 = r1.bitField0_
            r4 = 4096(0x1000, float:5.74E-42)
            r3 = r3 & r4
            if (r3 != r4) goto L_0x02ea
            r3 = r19
            goto L_0x02eb
        L_0x02ea:
            r3 = 0
        L_0x02eb:
            java.lang.String r5 = r1.phoneAccountComponentName_
            int r6 = r2.bitField0_
            r6 = r6 & r4
            if (r6 != r4) goto L_0x02f5
            r4 = r19
            goto L_0x02f6
        L_0x02f5:
            r4 = 0
        L_0x02f6:
            java.lang.String r6 = r2.phoneAccountComponentName_
            java.lang.String r3 = r0.visitString(r3, r5, r4, r6)
            r1.phoneAccountComponentName_ = r3
            int r3 = r1.bitField0_
            r4 = 8192(0x2000, float:1.14794E-41)
            r3 = r3 & r4
            if (r3 != r4) goto L_0x0308
            r3 = r19
            goto L_0x0309
        L_0x0308:
            r3 = 0
        L_0x0309:
            java.lang.String r5 = r1.phoneAccountId_
            int r6 = r2.bitField0_
            r6 = r6 & r4
            if (r6 != r4) goto L_0x0313
            r4 = r19
            goto L_0x0314
        L_0x0313:
            r4 = 0
        L_0x0314:
            java.lang.String r6 = r2.phoneAccountId_
            java.lang.String r3 = r0.visitString(r3, r5, r4, r6)
            r1.phoneAccountId_ = r3
            com.google.protobuf.GeneratedMessageLite$MergeFromVisitor r3 = com.google.protobuf.GeneratedMessageLite.MergeFromVisitor.INSTANCE
            if (r0 != r3) goto L_0x0327
            int r0 = r1.bitField0_
            int r2 = r2.bitField0_
            r0 = r0 | r2
            r1.bitField0_ = r0
        L_0x0327:
            return r1
        L_0x0328:
            com.android.dialer.voicemail.model.VoicemailEntry r0 = DEFAULT_INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.voicemail.model.VoicemailEntry.dynamicMethod(com.google.protobuf.GeneratedMessageLite$MethodToInvoke, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public int getCallType() {
        return this.callType_;
    }

    public long getDuration() {
        return this.duration_;
    }

    public String getFormattedNumber() {
        return this.formattedNumber_;
    }

    public String getGeocodedLocation() {
        return this.geocodedLocation_;
    }

    public long getId() {
        return this.id_;
    }

    public int getIsRead() {
        return this.isRead_;
    }

    public DialerPhoneNumber getNumber() {
        DialerPhoneNumber dialerPhoneNumber = this.number_;
        return dialerPhoneNumber == null ? DialerPhoneNumber.getDefaultInstance() : dialerPhoneNumber;
    }

    public NumberAttributes getNumberAttributes() {
        NumberAttributes numberAttributes = this.numberAttributes_;
        return numberAttributes == null ? NumberAttributes.getDefaultInstance() : numberAttributes;
    }

    public String getPhoneAccountComponentName() {
        return this.phoneAccountComponentName_;
    }

    public String getPhoneAccountId() {
        return this.phoneAccountId_;
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if ((this.bitField0_ & 1) == 1) {
            i2 = 0 + CodedOutputStream.computeInt64Size(1, this.id_);
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeInt64Size(2, this.timestamp_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeMessageSize(3, getNumber());
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeStringSize(4, this.formattedNumber_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeStringSize(5, this.geocodedLocation_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeInt64Size(6, this.duration_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeStringSize(7, this.transcription_);
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeStringSize(8, this.voicemailUri_);
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeInt32Size(9, this.callType_);
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeInt32Size(10, this.isRead_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            i2 += CodedOutputStream.computeMessageSize(11, getNumberAttributes());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            i2 += CodedOutputStream.computeInt32Size(12, this.transcriptionState_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            i2 += CodedOutputStream.computeStringSize(13, this.phoneAccountComponentName_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            i2 += CodedOutputStream.computeStringSize(14, this.phoneAccountId_);
        }
        int serializedSize = this.unknownFields.getSerializedSize() + i2;
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public long getTimestamp() {
        return this.timestamp_;
    }

    public String getTranscription() {
        return this.transcription_;
    }

    public int getTranscriptionState() {
        return this.transcriptionState_;
    }

    public String getVoicemailUri() {
        return this.voicemailUri_;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeUInt64(1, this.id_);
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeUInt64(2, this.timestamp_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeMessage(3, getNumber());
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeString(4, this.formattedNumber_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeString(5, this.geocodedLocation_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeUInt64(6, this.duration_);
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeString(7, this.transcription_);
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeString(8, this.voicemailUri_);
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeInt32(9, this.callType_);
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeInt32(10, this.isRead_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            codedOutputStream.writeMessage(11, getNumberAttributes());
        }
        if ((this.bitField0_ & 2048) == 2048) {
            codedOutputStream.writeInt32(12, this.transcriptionState_);
        }
        if ((this.bitField0_ & 4096) == 4096) {
            codedOutputStream.writeString(13, this.phoneAccountComponentName_);
        }
        if ((this.bitField0_ & 8192) == 8192) {
            codedOutputStream.writeString(14, this.phoneAccountId_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
