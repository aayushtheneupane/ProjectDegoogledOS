package p000;

/* renamed from: edv */
/* compiled from: PG */
public final class edv {

    /* renamed from: a */
    private String f8062a;

    /* renamed from: b */
    private String f8063b;

    /* renamed from: c */
    private String f8064c;

    /* renamed from: d */
    private Integer f8065d;

    /* renamed from: e */
    private Long f8066e;

    /* renamed from: f */
    private Boolean f8067f;

    /* renamed from: g */
    private Boolean f8068g;

    edv() {
    }

    public edv(byte[] bArr) {
    }

    public /* synthetic */ edv(edw edw) {
        edy edy = (edy) edw;
        this.f8062a = edy.f8071a;
        this.f8063b = edy.f8072b;
        this.f8064c = edy.f8073c;
        this.f8065d = Integer.valueOf(edy.f8074d);
        this.f8066e = Long.valueOf(edy.f8075e);
        this.f8067f = Boolean.valueOf(edy.f8076f);
        this.f8068g = Boolean.valueOf(edy.f8077g);
    }

    /* renamed from: a */
    public final edw mo4721a() {
        String str = this.f8062a == null ? " activityClassName" : "";
        if (this.f8063b == null) {
            str = str.concat(" packageName");
        }
        if (this.f8064c == null) {
            str = String.valueOf(str).concat(" label");
        }
        if (this.f8065d == null) {
            str = String.valueOf(str).concat(" numShares");
        }
        if (this.f8066e == null) {
            str = String.valueOf(str).concat(" lastShare");
        }
        if (this.f8067f == null) {
            str = String.valueOf(str).concat(" supportsMultiple");
        }
        if (this.f8068g == null) {
            str = String.valueOf(str).concat(" supportsVideo");
        }
        if (str.isEmpty()) {
            return new edy(this.f8062a, this.f8063b, this.f8064c, this.f8065d.intValue(), this.f8066e.longValue(), this.f8067f.booleanValue(), this.f8068g.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo4724a(String str) {
        if (str != null) {
            this.f8062a = str;
            return;
        }
        throw new NullPointerException("Null activityClassName");
    }

    /* renamed from: c */
    public final void mo4728c(String str) {
        if (str != null) {
            this.f8064c = str;
            return;
        }
        throw new NullPointerException("Null label");
    }

    /* renamed from: a */
    public final void mo4723a(long j) {
        this.f8066e = Long.valueOf(j);
    }

    /* renamed from: a */
    public final void mo4722a(int i) {
        this.f8065d = Integer.valueOf(i);
    }

    /* renamed from: b */
    public final void mo4726b(String str) {
        if (str != null) {
            this.f8063b = str;
            return;
        }
        throw new NullPointerException("Null packageName");
    }

    /* renamed from: a */
    public final void mo4725a(boolean z) {
        this.f8067f = Boolean.valueOf(z);
    }

    /* renamed from: b */
    public final void mo4727b(boolean z) {
        this.f8068g = Boolean.valueOf(z);
    }
}
