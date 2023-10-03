package p000;

import android.graphics.Paint;
import android.graphics.Path;

/* renamed from: ggl */
/* compiled from: PG */
public final class ggl {

    /* renamed from: g */
    public static final int[] f11257g = new int[3];

    /* renamed from: h */
    public static final float[] f11258h = {0.0f, 0.5f, 1.0f};

    /* renamed from: i */
    public static final int[] f11259i = new int[4];

    /* renamed from: j */
    public static final float[] f11260j = {0.0f, 0.0f, 0.5f, 1.0f};

    /* renamed from: a */
    public final Paint f11261a;

    /* renamed from: b */
    public final Paint f11262b;

    /* renamed from: c */
    public final Paint f11263c;

    /* renamed from: d */
    public int f11264d;

    /* renamed from: e */
    public int f11265e;

    /* renamed from: f */
    public int f11266f;

    /* renamed from: k */
    public final Path f11267k = new Path();

    /* renamed from: l */
    public final Paint f11268l = new Paint();

    public ggl() {
        mo6620a(-16777216);
        this.f11268l.setColor(0);
        Paint paint = new Paint(4);
        this.f11262b = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.f11261a = paint2;
        paint2.setColor(this.f11264d);
        this.f11263c = new Paint(this.f11262b);
    }

    /* renamed from: a */
    public final void mo6620a(int i) {
        this.f11264d = C0238ip.m14267b(i, 68);
        this.f11265e = C0238ip.m14267b(i, 20);
        this.f11266f = C0238ip.m14267b(i, 0);
    }
}
