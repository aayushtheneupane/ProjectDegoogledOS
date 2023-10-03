package p000;

/* renamed from: dxf */
/* compiled from: PG */
public final class dxf extends C0597vx {

    /* renamed from: a */
    public int f7554a;

    /* renamed from: b */
    private final /* synthetic */ dxg f7555b;

    public dxf(dxg dxg, int i) {
        this.f7555b = dxg;
        this.f7554a = i;
    }

    /* renamed from: a */
    public final int mo2711a(int i) {
        dxy dxy = dxy.DEFAULT_SORT_METHOD;
        dwu dwu = dwu.MEDIA;
        int ordinal = ((dwv) this.f7555b.f7560e.get(i)).mo4478b().ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return 1;
        }
        if (ordinal == 2 || ordinal == 3 || ordinal == 4 || ordinal == 5) {
            return this.f7554a;
        }
        String valueOf = String.valueOf(((dwv) this.f7555b.f7560e.get(i)).mo4478b());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 28);
        sb.append("Unknown PhotoGridItem kind: ");
        sb.append(valueOf);
        throw new IllegalArgumentException(sb.toString());
    }
}
