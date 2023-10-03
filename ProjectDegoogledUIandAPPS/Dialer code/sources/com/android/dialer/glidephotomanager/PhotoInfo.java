package com.android.dialer.glidephotomanager;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;

public final class PhotoInfo extends GeneratedMessageLite<PhotoInfo, Builder> implements PhotoInfoOrBuilder {
    /* access modifiers changed from: private */
    public static final PhotoInfo DEFAULT_INSTANCE = new PhotoInfo();
    private static volatile Parser<PhotoInfo> PARSER;
    private int bitField0_;
    private String formattedNumber_ = "";
    private boolean isBlocked_ = false;
    private boolean isBusiness_ = false;
    private boolean isConference_ = false;
    private boolean isRtt_ = false;
    private boolean isSpam_ = false;
    private boolean isVideo_ = false;
    private boolean isVoicemail_ = false;
    private String lookupUri_ = "";
    private String name_ = "";
    private long photoId_ = 0;
    private String photoUri_ = "";

    /* renamed from: com.android.dialer.glidephotomanager.PhotoInfo$1 */
    static /* synthetic */ class C04831 {

        /* renamed from: $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke */
        static final /* synthetic */ int[] f25xa1df5c61 = new int[GeneratedMessageLite.MethodToInvoke.values().length];

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
                f25xa1df5c61 = r0
                int[] r0 = f25xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f25xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f25xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f25xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f25xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f25xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f25xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f25xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.glidephotomanager.PhotoInfo.C04831.<clinit>():void");
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PhotoInfo, Builder> implements PhotoInfoOrBuilder {
        /* synthetic */ Builder(C04831 r1) {
            this();
        }

        public Builder setFormattedNumber(String str) {
            copyOnWrite();
            PhotoInfo.access$400((PhotoInfo) this.instance, str);
            return this;
        }

        public Builder setIsBlocked(boolean z) {
            copyOnWrite();
            PhotoInfo.access$1900((PhotoInfo) this.instance, z);
            return this;
        }

        public Builder setIsBusiness(boolean z) {
            copyOnWrite();
            PhotoInfo.access$1500((PhotoInfo) this.instance, z);
            return this;
        }

        public Builder setIsConference(boolean z) {
            copyOnWrite();
            PhotoInfo.access$2700((PhotoInfo) this.instance, z);
            return this;
        }

        public Builder setIsRtt(boolean z) {
            copyOnWrite();
            PhotoInfo.access$2500((PhotoInfo) this.instance, z);
            return this;
        }

        public Builder setIsSpam(boolean z) {
            copyOnWrite();
            PhotoInfo.access$2100((PhotoInfo) this.instance, z);
            return this;
        }

        public Builder setIsVideo(boolean z) {
            copyOnWrite();
            PhotoInfo.access$2300((PhotoInfo) this.instance, z);
            return this;
        }

        public Builder setIsVoicemail(boolean z) {
            copyOnWrite();
            PhotoInfo.access$1700((PhotoInfo) this.instance, z);
            return this;
        }

        public Builder setLookupUri(String str) {
            copyOnWrite();
            PhotoInfo.access$1200((PhotoInfo) this.instance, str);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            PhotoInfo.access$100((PhotoInfo) this.instance, str);
            return this;
        }

        public Builder setPhotoId(long j) {
            copyOnWrite();
            PhotoInfo.access$1000((PhotoInfo) this.instance, j);
            return this;
        }

        public Builder setPhotoUri(String str) {
            copyOnWrite();
            PhotoInfo.access$700((PhotoInfo) this.instance, str);
            return this;
        }

        private Builder() {
            super(PhotoInfo.DEFAULT_INSTANCE);
        }
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    private PhotoInfo() {
    }

    static /* synthetic */ void access$100(PhotoInfo photoInfo, String str) {
        if (str != null) {
            photoInfo.bitField0_ |= 1;
            photoInfo.name_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1000(PhotoInfo photoInfo, long j) {
        photoInfo.bitField0_ |= 8;
        photoInfo.photoId_ = j;
    }

    static /* synthetic */ void access$1200(PhotoInfo photoInfo, String str) {
        if (str != null) {
            photoInfo.bitField0_ |= 16;
            photoInfo.lookupUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$1500(PhotoInfo photoInfo, boolean z) {
        photoInfo.bitField0_ |= 32;
        photoInfo.isBusiness_ = z;
    }

    static /* synthetic */ void access$1700(PhotoInfo photoInfo, boolean z) {
        photoInfo.bitField0_ |= 64;
        photoInfo.isVoicemail_ = z;
    }

    static /* synthetic */ void access$1900(PhotoInfo photoInfo, boolean z) {
        photoInfo.bitField0_ |= 128;
        photoInfo.isBlocked_ = z;
    }

    static /* synthetic */ void access$2100(PhotoInfo photoInfo, boolean z) {
        photoInfo.bitField0_ |= 256;
        photoInfo.isSpam_ = z;
    }

    static /* synthetic */ void access$2300(PhotoInfo photoInfo, boolean z) {
        photoInfo.bitField0_ |= 512;
        photoInfo.isVideo_ = z;
    }

    static /* synthetic */ void access$2500(PhotoInfo photoInfo, boolean z) {
        photoInfo.bitField0_ |= 1024;
        photoInfo.isRtt_ = z;
    }

    static /* synthetic */ void access$2700(PhotoInfo photoInfo, boolean z) {
        photoInfo.bitField0_ |= 2048;
        photoInfo.isConference_ = z;
    }

    static /* synthetic */ void access$400(PhotoInfo photoInfo, String str) {
        if (str != null) {
            photoInfo.bitField0_ |= 2;
            photoInfo.formattedNumber_ = str;
            return;
        }
        throw new NullPointerException();
    }

    static /* synthetic */ void access$700(PhotoInfo photoInfo, String str) {
        if (str != null) {
            photoInfo.bitField0_ |= 4;
            photoInfo.photoUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    public static PhotoInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Parser<PhotoInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        int i = 2048;
        int i2 = 1024;
        int i3 = 512;
        switch (methodToInvoke.ordinal()) {
            case 0:
                return DEFAULT_INSTANCE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                PhotoInfo photoInfo = (PhotoInfo) obj2;
                this.name_ = visitor.visitString((this.bitField0_ & 1) == 1, this.name_, (photoInfo.bitField0_ & 1) == 1, photoInfo.name_);
                this.formattedNumber_ = visitor.visitString((this.bitField0_ & 2) == 2, this.formattedNumber_, (photoInfo.bitField0_ & 2) == 2, photoInfo.formattedNumber_);
                this.photoUri_ = visitor.visitString((this.bitField0_ & 4) == 4, this.photoUri_, (photoInfo.bitField0_ & 4) == 4, photoInfo.photoUri_);
                this.photoId_ = visitor.visitLong((this.bitField0_ & 8) == 8, this.photoId_, (photoInfo.bitField0_ & 8) == 8, photoInfo.photoId_);
                this.lookupUri_ = visitor.visitString((this.bitField0_ & 16) == 16, this.lookupUri_, (photoInfo.bitField0_ & 16) == 16, photoInfo.lookupUri_);
                this.isBusiness_ = visitor.visitBoolean((this.bitField0_ & 32) == 32, this.isBusiness_, (photoInfo.bitField0_ & 32) == 32, photoInfo.isBusiness_);
                this.isVoicemail_ = visitor.visitBoolean((this.bitField0_ & 64) == 64, this.isVoicemail_, (photoInfo.bitField0_ & 64) == 64, photoInfo.isVoicemail_);
                this.isBlocked_ = visitor.visitBoolean((this.bitField0_ & 128) == 128, this.isBlocked_, (photoInfo.bitField0_ & 128) == 128, photoInfo.isBlocked_);
                this.isSpam_ = visitor.visitBoolean((this.bitField0_ & 256) == 256, this.isSpam_, (photoInfo.bitField0_ & 256) == 256, photoInfo.isSpam_);
                this.isVideo_ = visitor.visitBoolean((this.bitField0_ & 512) == 512, this.isVideo_, (photoInfo.bitField0_ & 512) == 512, photoInfo.isVideo_);
                this.isRtt_ = visitor.visitBoolean((this.bitField0_ & 1024) == 1024, this.isRtt_, (photoInfo.bitField0_ & 1024) == 1024, photoInfo.isRtt_);
                this.isConference_ = visitor.visitBoolean((this.bitField0_ & 2048) == 2048, this.isConference_, (photoInfo.bitField0_ & 2048) == 2048, photoInfo.isConference_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= photoInfo.bitField0_;
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
                                this.name_ = readString;
                                break;
                            case 18:
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.formattedNumber_ = readString2;
                                break;
                            case 26:
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 4;
                                this.photoUri_ = readString3;
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.photoId_ = codedInputStream.readRawVarint64();
                                break;
                            case 42:
                                String readString4 = codedInputStream.readString();
                                this.bitField0_ |= 16;
                                this.lookupUri_ = readString4;
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.isBusiness_ = codedInputStream.readBool();
                                break;
                            case 56:
                                this.bitField0_ |= 64;
                                this.isVoicemail_ = codedInputStream.readBool();
                                break;
                            case 64:
                                this.bitField0_ |= 128;
                                this.isBlocked_ = codedInputStream.readBool();
                                break;
                            case 72:
                                this.bitField0_ |= 256;
                                this.isSpam_ = codedInputStream.readBool();
                                break;
                            case 80:
                                this.bitField0_ |= i3;
                                this.isVideo_ = codedInputStream.readBool();
                                break;
                            case 88:
                                this.bitField0_ |= i2;
                                this.isRtt_ = codedInputStream.readBool();
                                break;
                            case 96:
                                this.bitField0_ |= i;
                                this.isConference_ = codedInputStream.readBool();
                                break;
                            default:
                                if (parseUnknownField(readTag, codedInputStream)) {
                                    break;
                                }
                                z = true;
                                break;
                        }
                        i = 2048;
                        i2 = 1024;
                        i3 = 512;
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
                return new PhotoInfo();
            case 5:
                return new Builder((C04831) null);
            case 6:
                break;
            case 7:
                if (PARSER == null) {
                    synchronized (PhotoInfo.class) {
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

    public String getFormattedNumber() {
        return this.formattedNumber_;
    }

    public boolean getIsBlocked() {
        return this.isBlocked_;
    }

    public boolean getIsBusiness() {
        return this.isBusiness_;
    }

    public boolean getIsConference() {
        return this.isConference_;
    }

    public boolean getIsRtt() {
        return this.isRtt_;
    }

    public boolean getIsSpam() {
        return this.isSpam_;
    }

    public boolean getIsVideo() {
        return this.isVideo_;
    }

    public boolean getIsVoicemail() {
        return this.isVoicemail_;
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
            i2 += CodedOutputStream.computeStringSize(2, this.formattedNumber_);
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeStringSize(3, this.photoUri_);
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeInt64Size(4, this.photoId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            i2 += CodedOutputStream.computeStringSize(5, this.lookupUri_);
        }
        if ((this.bitField0_ & 32) == 32) {
            i2 += CodedOutputStream.computeBoolSize(6, this.isBusiness_);
        }
        if ((this.bitField0_ & 64) == 64) {
            i2 += CodedOutputStream.computeBoolSize(7, this.isVoicemail_);
        }
        if ((this.bitField0_ & 128) == 128) {
            i2 += CodedOutputStream.computeBoolSize(8, this.isBlocked_);
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeBoolSize(9, this.isSpam_);
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeBoolSize(10, this.isVideo_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            i2 += CodedOutputStream.computeBoolSize(11, this.isRtt_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            i2 += CodedOutputStream.computeBoolSize(12, this.isConference_);
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
            codedOutputStream.writeString(2, this.formattedNumber_);
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeString(3, this.photoUri_);
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeUInt64(4, this.photoId_);
        }
        if ((this.bitField0_ & 16) == 16) {
            codedOutputStream.writeString(5, this.lookupUri_);
        }
        if ((this.bitField0_ & 32) == 32) {
            codedOutputStream.writeBool(6, this.isBusiness_);
        }
        if ((this.bitField0_ & 64) == 64) {
            codedOutputStream.writeBool(7, this.isVoicemail_);
        }
        if ((this.bitField0_ & 128) == 128) {
            codedOutputStream.writeBool(8, this.isBlocked_);
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeBool(9, this.isSpam_);
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeBool(10, this.isVideo_);
        }
        if ((this.bitField0_ & 1024) == 1024) {
            codedOutputStream.writeBool(11, this.isRtt_);
        }
        if ((this.bitField0_ & 2048) == 2048) {
            codedOutputStream.writeBool(12, this.isConference_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }
}
