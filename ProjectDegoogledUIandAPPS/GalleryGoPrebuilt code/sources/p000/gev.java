package p000;

import android.widget.BaseAdapter;

/* renamed from: gev */
/* compiled from: PG */
public final class gev extends BaseAdapter {

    /* renamed from: a */
    public static final int f11135a = ggf.m10260e().getMaximum(4);

    /* renamed from: b */
    public final geu f11136b;

    /* renamed from: c */
    public final ged f11137c;

    /* renamed from: d */
    public gec f11138d;

    /* renamed from: e */
    private final gea f11139e;

    public final boolean hasStableIds() {
        return true;
    }

    public gev(geu geu, ged ged, gea gea) {
        this.f11136b = geu;
        this.f11137c = ged;
        this.f11139e = gea;
    }

    /* renamed from: a */
    public final int mo6533a() {
        return this.f11136b.mo6523a();
    }

    public final int getCount() {
        return this.f11136b.f11134f + mo6533a();
    }

    /* renamed from: a */
    public final Long getItem(int i) {
        if (i < this.f11136b.mo6523a() || i > mo6535b()) {
            return null;
        }
        geu geu = this.f11136b;
        return Long.valueOf(geu.mo6525a((i - geu.mo6523a()) + 1));
    }

    public final long getItemId(int i) {
        return (long) (i / this.f11136b.f11133e);
    }

    /* JADX WARNING: type inference failed for: r8v23, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* bridge */ /* synthetic */ android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            r6 = this;
            android.content.Context r0 = r9.getContext()
            gec r1 = r6.f11138d
            if (r1 != 0) goto L_0x000f
            gec r1 = new gec
            r1.<init>(r0)
            r6.f11138d = r1
        L_0x000f:
            r0 = r8
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r8 != 0) goto L_0x0028
            android.content.Context r8 = r9.getContext()
            android.view.LayoutInflater r8 = android.view.LayoutInflater.from(r8)
            r0 = 2131558494(0x7f0d005e, float:1.8742305E38)
            android.view.View r8 = r8.inflate(r0, r9, r1)
            r0 = r8
            android.widget.TextView r0 = (android.widget.TextView) r0
            goto L_0x0029
        L_0x0028:
        L_0x0029:
            int r8 = r6.mo6533a()
            int r8 = r7 - r8
            r9 = 1
            if (r8 < 0) goto L_0x0094
            geu r2 = r6.f11136b
            int r3 = r2.f11134f
            if (r8 < r3) goto L_0x0039
            goto L_0x0094
        L_0x0039:
            int r8 = r8 + r9
            r0.setTag(r2)
            java.lang.String r2 = java.lang.String.valueOf(r8)
            r0.setText(r2)
            geu r2 = r6.f11136b
            long r2 = r2.mo6525a((int) r8)
            geu r8 = r6.f11136b
            int r8 = r8.f11132d
            geu r4 = new geu
            java.util.Calendar r5 = p000.ggf.m10259d()
            r4.<init>(r5)
            int r4 = r4.f11132d
            if (r8 == r4) goto L_0x0074
            java.util.Locale r8 = java.util.Locale.getDefault()
            int r4 = android.os.Build.VERSION.SDK_INT
            java.lang.String r4 = "yMMMEd"
            android.icu.text.DateFormat r8 = p000.ggf.m10249a((java.lang.String) r4, (java.util.Locale) r8)
            java.util.Date r4 = new java.util.Date
            r4.<init>(r2)
            java.lang.String r8 = r8.format(r4)
            r0.setContentDescription(r8)
            goto L_0x008c
        L_0x0074:
            java.util.Locale r8 = java.util.Locale.getDefault()
            int r4 = android.os.Build.VERSION.SDK_INT
            java.lang.String r4 = "MMMEd"
            android.icu.text.DateFormat r8 = p000.ggf.m10249a((java.lang.String) r4, (java.util.Locale) r8)
            java.util.Date r4 = new java.util.Date
            r4.<init>(r2)
            java.lang.String r8 = r8.format(r4)
            r0.setContentDescription(r8)
        L_0x008c:
            r0.setVisibility(r1)
            r0.setEnabled(r9)
            goto L_0x009c
        L_0x0094:
            r8 = 8
            r0.setVisibility(r8)
            r0.setEnabled(r1)
        L_0x009c:
            java.lang.Long r7 = r6.getItem(r7)
            if (r7 == 0) goto L_0x010f
            gea r8 = r6.f11139e
            gdz r8 = r8.f11084d
            r7.longValue()
            boolean r8 = r8.mo6496a()
            if (r8 == 0) goto L_0x0104
            r0.setEnabled(r9)
            ged r8 = r6.f11137c
            java.util.Collection r8 = r8.mo6504a()
            java.util.Iterator r8 = r8.iterator()
        L_0x00bc:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x00e4
            java.lang.Object r9 = r8.next()
            java.lang.Long r9 = (java.lang.Long) r9
            long r1 = r9.longValue()
            long r3 = r7.longValue()
            long r3 = p000.ggf.m10247a((long) r3)
            long r1 = p000.ggf.m10247a((long) r1)
            int r9 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r9 != 0) goto L_0x00bc
            gec r7 = r6.f11138d
            geb r7 = r7.f11094b
            r7.mo6503a(r0)
            goto L_0x010f
        L_0x00e4:
            java.util.Calendar r8 = p000.ggf.m10259d()
            long r8 = r8.getTimeInMillis()
            long r1 = r7.longValue()
            int r7 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r7 != 0) goto L_0x00fc
            gec r7 = r6.f11138d
            geb r7 = r7.f11095c
            r7.mo6503a(r0)
            goto L_0x010f
        L_0x00fc:
            gec r7 = r6.f11138d
            geb r7 = r7.f11093a
            r7.mo6503a(r0)
            goto L_0x010f
        L_0x0104:
            r0.setEnabled(r1)
            gec r7 = r6.f11138d
            geb r7 = r7.f11099g
            r7.mo6503a(r0)
        L_0x010f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gev.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    /* renamed from: b */
    public final int mo6535b() {
        return (this.f11136b.mo6523a() + this.f11136b.f11134f) - 1;
    }
}
