package p000;

import p003j$.util.Optional;

/* renamed from: cyf */
/* compiled from: PG */
public final class cyf {

    /* renamed from: A */
    private Boolean f5997A;

    /* renamed from: B */
    private Boolean f5998B;

    /* renamed from: C */
    private byte[] f5999C;

    /* renamed from: D */
    private byte[] f6000D;

    /* renamed from: E */
    private Optional f6001E;

    /* renamed from: F */
    private Optional f6002F;

    /* renamed from: G */
    private Integer f6003G;

    /* renamed from: H */
    private Boolean f6004H;

    /* renamed from: I */
    private Boolean f6005I;

    /* renamed from: J */
    private Integer f6006J;

    /* renamed from: K */
    private Boolean f6007K;

    /* renamed from: L */
    private Boolean f6008L;

    /* renamed from: a */
    public Optional f6009a;

    /* renamed from: b */
    public Optional f6010b;

    /* renamed from: c */
    public Optional f6011c;

    /* renamed from: d */
    public Optional f6012d;

    /* renamed from: e */
    public Optional f6013e;

    /* renamed from: f */
    private Long f6014f;

    /* renamed from: g */
    private Integer f6015g;

    /* renamed from: h */
    private String f6016h;

    /* renamed from: i */
    private Integer f6017i;

    /* renamed from: j */
    private Integer f6018j;

    /* renamed from: k */
    private Integer f6019k;

    /* renamed from: l */
    private Long f6020l;

    /* renamed from: m */
    private Optional f6021m;

    /* renamed from: n */
    private Long f6022n;

    /* renamed from: o */
    private Long f6023o;

    /* renamed from: p */
    private Long f6024p;

    /* renamed from: q */
    private String f6025q;

    /* renamed from: r */
    private String f6026r;

    /* renamed from: s */
    private String f6027s;

    /* renamed from: t */
    private Optional f6028t;

    /* renamed from: u */
    private Integer f6029u;

    /* renamed from: v */
    private Integer f6030v;

    /* renamed from: w */
    private Boolean f6031w;

    /* renamed from: x */
    private Boolean f6032x;

    /* renamed from: y */
    private Boolean f6033y;

    /* renamed from: z */
    private Boolean f6034z;

    public cyf(byte[] bArr) {
        this.f6009a = Optional.empty();
        this.f6021m = Optional.empty();
        this.f6028t = Optional.empty();
        this.f6010b = Optional.empty();
        this.f6011c = Optional.empty();
        this.f6001E = Optional.empty();
        this.f6002F = Optional.empty();
        this.f6012d = Optional.empty();
        this.f6013e = Optional.empty();
    }

    public /* synthetic */ cyf(cyg cyg) {
        this.f6009a = Optional.empty();
        this.f6021m = Optional.empty();
        this.f6028t = Optional.empty();
        this.f6010b = Optional.empty();
        this.f6011c = Optional.empty();
        this.f6001E = Optional.empty();
        this.f6002F = Optional.empty();
        this.f6012d = Optional.empty();
        this.f6013e = Optional.empty();
        cwy cwy = (cwy) cyg;
        this.f6009a = cwy.f5853a;
        this.f6014f = Long.valueOf(cwy.f5854b);
        this.f6015g = Integer.valueOf(cwy.f5855c);
        this.f6016h = cwy.f5856d;
        this.f6017i = Integer.valueOf(cwy.f5857e);
        this.f6018j = Integer.valueOf(cwy.f5858f);
        this.f6019k = Integer.valueOf(cwy.f5859g);
        this.f6020l = Long.valueOf(cwy.f5860h);
        this.f6021m = cwy.f5861i;
        this.f6022n = Long.valueOf(cwy.f5862j);
        this.f6023o = Long.valueOf(cwy.f5863k);
        this.f6024p = Long.valueOf(cwy.f5864l);
        this.f6025q = cwy.f5865m;
        this.f6026r = cwy.f5866n;
        this.f6027s = cwy.f5867o;
        this.f6028t = cwy.f5868p;
        this.f6010b = cwy.f5869q;
        this.f6029u = Integer.valueOf(cwy.f5870r);
        this.f6011c = cwy.f5871s;
        this.f6030v = Integer.valueOf(cwy.f5872t);
        this.f6031w = Boolean.valueOf(cwy.f5873u);
        this.f6032x = Boolean.valueOf(cwy.f5874v);
        this.f6033y = Boolean.valueOf(cwy.f5875w);
        this.f6034z = Boolean.valueOf(cwy.f5876x);
        this.f5997A = Boolean.valueOf(cwy.f5877y);
        this.f5998B = Boolean.valueOf(cwy.f5878z);
        this.f5999C = cwy.f5841A;
        this.f6000D = cwy.f5842B;
        this.f6001E = cwy.f5843C;
        this.f6002F = cwy.f5844D;
        this.f6012d = cwy.f5845E;
        this.f6003G = Integer.valueOf(cwy.f5846F);
        this.f6013e = cwy.f5847G;
        this.f6004H = Boolean.valueOf(cwy.f5848H);
        this.f6005I = Boolean.valueOf(cwy.f5849I);
        this.f6006J = Integer.valueOf(cwy.f5850J);
        this.f6007K = Boolean.valueOf(cwy.f5851K);
        this.f6008L = Boolean.valueOf(cwy.f5852L);
    }

    /* renamed from: c */
    public final cyg mo3966c() {
        String str = this.f6014f == null ? " mediaStoreId" : "";
        if (this.f6015g == null) {
            str = str.concat(" mediaType");
        }
        if (this.f6016h == null) {
            str = String.valueOf(str).concat(" mimeType");
        }
        if (this.f6017i == null) {
            str = String.valueOf(str).concat(" heightPx");
        }
        if (this.f6018j == null) {
            str = String.valueOf(str).concat(" widthPx");
        }
        if (this.f6019k == null) {
            str = String.valueOf(str).concat(" orientation");
        }
        if (this.f6020l == null) {
            str = String.valueOf(str).concat(" fileSize");
        }
        if (this.f6022n == null) {
            str = String.valueOf(str).concat(" captureUtcTimestampMs");
        }
        if (this.f6023o == null) {
            str = String.valueOf(str).concat(" captureUtcOffsetMs");
        }
        if (this.f6024p == null) {
            str = String.valueOf(str).concat(" timeModifiedMs");
        }
        if (this.f6025q == null) {
            str = String.valueOf(str).concat(" bucketId");
        }
        if (this.f6026r == null) {
            str = String.valueOf(str).concat(" folderName");
        }
        if (this.f6027s == null) {
            str = String.valueOf(str).concat(" fileName");
        }
        if (this.f6029u == null) {
            str = String.valueOf(str).concat(" compressionAttempts");
        }
        if (this.f6030v == null) {
            str = String.valueOf(str).concat(" burstPrimaryState");
        }
        if (this.f6031w == null) {
            str = String.valueOf(str).concat(" animal");
        }
        if (this.f6032x == null) {
            str = String.valueOf(str).concat(" document");
        }
        if (this.f6033y == null) {
            str = String.valueOf(str).concat(" nature");
        }
        if (this.f6034z == null) {
            str = String.valueOf(str).concat(" food");
        }
        if (this.f5997A == null) {
            str = String.valueOf(str).concat(" screenshot");
        }
        if (this.f5998B == null) {
            str = String.valueOf(str).concat(" selfie");
        }
        if (this.f5999C == null) {
            str = String.valueOf(str).concat(" labelsMetadata");
        }
        if (this.f6000D == null) {
            str = String.valueOf(str).concat(" jobsMetadata");
        }
        if (this.f6003G == null) {
            str = String.valueOf(str).concat(" trashStatus");
        }
        if (this.f6004H == null) {
            str = String.valueOf(str).concat(" faceDetectionRun");
        }
        if (this.f6005I == null) {
            str = String.valueOf(str).concat(" thumbnailingRun");
        }
        if (this.f6006J == null) {
            str = String.valueOf(str).concat(" imageLabelingModelVersion");
        }
        if (this.f6007K == null) {
            str = String.valueOf(str).concat(" shouldWipeGeneratedData");
        }
        if (this.f6008L == null) {
            str = String.valueOf(str).concat(" pendingDuringSync");
        }
        if (str.isEmpty()) {
            return new cwy(this.f6009a, this.f6014f.longValue(), this.f6015g.intValue(), this.f6016h, this.f6017i.intValue(), this.f6018j.intValue(), this.f6019k.intValue(), this.f6020l.longValue(), this.f6021m, this.f6022n.longValue(), this.f6023o.longValue(), this.f6024p.longValue(), this.f6025q, this.f6026r, this.f6027s, this.f6028t, this.f6010b, this.f6029u.intValue(), this.f6011c, this.f6030v.intValue(), this.f6031w.booleanValue(), this.f6032x.booleanValue(), this.f6033y.booleanValue(), this.f6034z.booleanValue(), this.f5997A.booleanValue(), this.f5998B.booleanValue(), this.f5999C, this.f6000D, this.f6001E, this.f6002F, this.f6012d, this.f6003G.intValue(), this.f6013e, this.f6004H.booleanValue(), this.f6005I.booleanValue(), this.f6006J.intValue(), this.f6007K.booleanValue(), this.f6008L.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: b */
    public final byte[] mo3965b() {
        byte[] bArr = this.f6000D;
        if (bArr != null) {
            return bArr;
        }
        throw new IllegalStateException("Property \"jobsMetadata\" has not been set");
    }

    /* renamed from: c */
    public final void mo3970c(boolean z) {
        this.f6031w = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public final void mo3957a(String str) {
        if (str != null) {
            this.f6025q = str;
            return;
        }
        throw new NullPointerException("Null bucketId");
    }

    /* renamed from: d */
    public final void mo3973d(String str) {
        this.f6028t = Optional.ofNullable(str);
    }

    /* renamed from: e */
    public final void mo3975e(int i) {
        this.f6030v = Integer.valueOf(i);
    }

    /* renamed from: b */
    public final void mo3961b(long j) {
        this.f6023o = Long.valueOf(j);
    }

    /* renamed from: c */
    public final void mo3968c(long j) {
        this.f6022n = Long.valueOf(j);
    }

    /* renamed from: f */
    public final void mo3979f(int i) {
        this.f6029u = Integer.valueOf(i);
    }

    /* renamed from: d */
    public final void mo3974d(boolean z) {
        this.f6032x = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public final void mo3956a(long j) {
        this.f6021m = Optional.m16285of(Long.valueOf(j));
    }

    /* renamed from: e */
    public final void mo3978e(boolean z) {
        this.f6004H = Boolean.valueOf(z);
    }

    /* renamed from: b */
    public final void mo3962b(String str) {
        if (str != null) {
            this.f6027s = str;
            return;
        }
        throw new NullPointerException("Null fileName");
    }

    /* renamed from: d */
    public final void mo3972d(long j) {
        this.f6020l = Long.valueOf(j);
    }

    /* renamed from: c */
    public final void mo3969c(String str) {
        if (str != null) {
            this.f6026r = str;
            return;
        }
        throw new NullPointerException("Null folderName");
    }

    /* renamed from: f */
    public final void mo3982f(boolean z) {
        this.f6034z = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public final void mo3955a(int i) {
        this.f6017i = Integer.valueOf(i);
    }

    /* renamed from: g */
    public final void mo3983g(int i) {
        this.f6006J = Integer.valueOf(i);
    }

    /* renamed from: a */
    public final void mo3959a(byte[] bArr) {
        if (bArr != null) {
            this.f6000D = bArr;
            return;
        }
        throw new NullPointerException("Null jobsMetadata");
    }

    /* renamed from: b */
    public final void mo3964b(byte[] bArr) {
        if (bArr != null) {
            this.f5999C = bArr;
            return;
        }
        throw new NullPointerException("Null labelsMetadata");
    }

    /* renamed from: e */
    public final void mo3976e(long j) {
        this.f6014f = Long.valueOf(j);
    }

    /* renamed from: h */
    public final void mo3986h(int i) {
        this.f6015g = Integer.valueOf(i);
    }

    /* renamed from: e */
    public final void mo3977e(String str) {
        if (str != null) {
            this.f6016h = str;
            return;
        }
        throw new NullPointerException("Null mimeType");
    }

    /* renamed from: g */
    public final void mo3985g(boolean z) {
        this.f6033y = Boolean.valueOf(z);
    }

    /* renamed from: b */
    public final void mo3960b(int i) {
        this.f6019k = Integer.valueOf(i);
    }

    /* renamed from: a */
    public final void mo3958a(boolean z) {
        this.f6008L = Boolean.valueOf(z);
    }

    /* renamed from: f */
    public final void mo3981f(String str) {
        this.f6002F = Optional.ofNullable(str);
    }

    /* renamed from: h */
    public final void mo3987h(boolean z) {
        this.f5997A = Boolean.valueOf(z);
    }

    /* renamed from: i */
    public final void mo3988i(boolean z) {
        this.f5998B = Boolean.valueOf(z);
    }

    /* renamed from: b */
    public final void mo3963b(boolean z) {
        this.f6007K = Boolean.valueOf(z);
    }

    /* renamed from: g */
    public final void mo3984g(String str) {
        this.f6001E = Optional.m16285of(str);
    }

    /* renamed from: j */
    public final void mo3989j(boolean z) {
        this.f6005I = Boolean.valueOf(z);
    }

    /* renamed from: f */
    public final void mo3980f(long j) {
        this.f6024p = Long.valueOf(j);
    }

    /* renamed from: c */
    public final void mo3967c(int i) {
        this.f6003G = Integer.valueOf(i);
    }

    /* renamed from: d */
    public final void mo3971d(int i) {
        this.f6018j = Integer.valueOf(i);
    }

    cyf() {
    }

    /* renamed from: a */
    public final cyf mo3954a() {
        mo3975e(0);
        return this;
    }
}
