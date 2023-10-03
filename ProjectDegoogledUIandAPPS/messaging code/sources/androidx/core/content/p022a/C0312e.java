package androidx.core.content.p022a;

/* renamed from: androidx.core.content.a.e */
public final class C0312e {

    /* renamed from: Ng */
    private final String f309Ng;

    /* renamed from: _n */
    private String f310_n;

    /* renamed from: ao */
    private int f311ao;
    private boolean mItalic;
    private int mTtcIndex;
    private int mWeight;

    public C0312e(String str, int i, boolean z, String str2, int i2, int i3) {
        this.f309Ng = str;
        this.mWeight = i;
        this.mItalic = z;
        this.f310_n = str2;
        this.mTtcIndex = i2;
        this.f311ao = i3;
    }

    public String getFileName() {
        return this.f309Ng;
    }

    public int getResourceId() {
        return this.f311ao;
    }

    public int getTtcIndex() {
        return this.mTtcIndex;
    }

    public String getVariationSettings() {
        return this.f310_n;
    }

    public int getWeight() {
        return this.mWeight;
    }

    public boolean isItalic() {
        return this.mItalic;
    }
}
