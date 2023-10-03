package p000;

/* renamed from: cju */
/* compiled from: PG */
public final class cju {

    /* renamed from: a */
    private Integer f4522a;

    /* renamed from: b */
    private String f4523b;

    /* renamed from: c */
    private String f4524c;

    /* renamed from: d */
    private String f4525d;

    /* renamed from: e */
    private String f4526e;

    /* renamed from: f */
    private int f4527f;

    public cju(byte[] bArr) {
    }

    public /* synthetic */ cju(cjv cjv) {
        cjs cjs = (cjs) cjv;
        this.f4527f = cjs.f4520f;
        this.f4522a = Integer.valueOf(cjs.f4515a);
        this.f4523b = cjs.f4516b;
        this.f4524c = cjs.f4517c;
        this.f4525d = cjs.f4518d;
        this.f4526e = cjs.f4519e;
    }

    /* renamed from: a */
    public final cjv mo3190a() {
        String str = this.f4527f == 0 ? " type" : "";
        if (this.f4522a == null) {
            str = str.concat(" iconRes");
        }
        if (this.f4523b == null) {
            str = String.valueOf(str).concat(" key");
        }
        if (this.f4524c == null) {
            str = String.valueOf(str).concat(" path");
        }
        if (this.f4525d == null) {
            str = String.valueOf(str).concat(" name");
        }
        if (this.f4526e == null) {
            str = String.valueOf(str).concat(" summary");
        }
        if (str.isEmpty()) {
            return new cjs(this.f4527f, this.f4522a.intValue(), this.f4523b, this.f4524c, this.f4525d, this.f4526e);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }

    /* renamed from: a */
    public final void mo3191a(int i) {
        this.f4522a = Integer.valueOf(i);
    }

    /* renamed from: a */
    public final void mo3192a(String str) {
        if (str != null) {
            this.f4523b = str;
            return;
        }
        throw new NullPointerException("Null key");
    }

    /* renamed from: b */
    public final void mo3194b(String str) {
        if (str != null) {
            this.f4525d = str;
            return;
        }
        throw new NullPointerException("Null name");
    }

    /* renamed from: c */
    public final void mo3195c(String str) {
        if (str != null) {
            this.f4524c = str;
            return;
        }
        throw new NullPointerException("Null path");
    }

    /* renamed from: d */
    public final void mo3196d(String str) {
        if (str != null) {
            this.f4526e = str;
            return;
        }
        throw new NullPointerException("Null summary");
    }

    /* renamed from: b */
    public final void mo3193b() {
        this.f4527f = 1;
    }

    cju() {
    }
}
