package p000;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/* renamed from: ghd */
/* compiled from: PG */
public final class ghd {

    /* renamed from: a */
    private final ghl[] f11353a = new ghl[4];

    /* renamed from: b */
    private final Matrix[] f11354b = new Matrix[4];

    /* renamed from: c */
    private final Matrix[] f11355c = new Matrix[4];

    /* renamed from: d */
    private final PointF f11356d = new PointF();

    /* renamed from: e */
    private final Path f11357e = new Path();

    /* renamed from: f */
    private final Path f11358f = new Path();

    /* renamed from: g */
    private final ghl f11359g = new ghl();

    /* renamed from: h */
    private final float[] f11360h = new float[2];

    /* renamed from: i */
    private final float[] f11361i = new float[2];

    /* renamed from: j */
    private boolean f11362j = true;

    public ghd() {
        for (int i = 0; i < 4; i++) {
            this.f11353a[i] = new ghl();
            this.f11354b[i] = new Matrix();
            this.f11355c[i] = new Matrix();
        }
    }

    /* renamed from: a */
    private static final float m10335a(int i) {
        return (float) ((i + 1) * 90);
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0218  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x022d A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo6673a(p000.gha r11, float r12, android.graphics.RectF r13, p000.ghb r14, android.graphics.Path r15) {
        /*
            r10 = this;
            r15.rewind()
            android.graphics.Path r0 = r10.f11357e
            r0.rewind()
            android.graphics.Path r0 = r10.f11358f
            r0.rewind()
            android.graphics.Path r0 = r10.f11358f
            android.graphics.Path$Direction r1 = android.graphics.Path.Direction.CW
            r0.addRect(r13, r1)
            ghc r0 = new ghc
            r2 = r0
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r7 = r15
            r2.<init>(r3, r4, r5, r6, r7)
            r11 = 0
            r12 = 0
        L_0x0021:
            r13 = 4
            r14 = 3
            r1 = 1
            if (r12 >= r13) goto L_0x00dc
            gha r13 = r0.f11348a
            r2 = 2
            if (r12 == r1) goto L_0x0038
            if (r12 == r2) goto L_0x0035
            if (r12 == r14) goto L_0x0032
            ggo r3 = r13.f11337b
            goto L_0x003a
        L_0x0032:
            ggo r3 = r13.f11336a
            goto L_0x003a
        L_0x0035:
            ggo r3 = r13.f11339d
            goto L_0x003a
        L_0x0038:
            ggo r3 = r13.f11338c
        L_0x003a:
            if (r12 == r1) goto L_0x0049
            if (r12 == r2) goto L_0x0046
            if (r12 == r14) goto L_0x0043
            gqb r13 = r13.f11345j
            goto L_0x004b
        L_0x0043:
            gqb r13 = r13.f11344i
            goto L_0x004b
        L_0x0046:
            gqb r13 = r13.f11347l
            goto L_0x004b
        L_0x0049:
            gqb r13 = r13.f11346k
        L_0x004b:
            ghl[] r4 = r10.f11353a
            r4 = r4[r12]
            float r5 = r0.f11352e
            android.graphics.RectF r6 = r0.f11350c
            float r3 = r3.mo6621a(r6)
            r13.mo6626a((p000.ghl) r4, (float) r5, (float) r3)
            float r13 = m10335a(r12)
            android.graphics.Matrix[] r3 = r10.f11354b
            r3 = r3[r12]
            r3.reset()
            android.graphics.RectF r3 = r0.f11350c
            android.graphics.PointF r4 = r10.f11356d
            if (r12 == r1) goto L_0x0087
            if (r12 == r2) goto L_0x007f
            if (r12 == r14) goto L_0x0077
            float r14 = r3.right
            float r2 = r3.top
            r4.set(r14, r2)
            goto L_0x008e
        L_0x0077:
            float r14 = r3.left
            float r2 = r3.top
            r4.set(r14, r2)
            goto L_0x008e
        L_0x007f:
            float r14 = r3.left
            float r2 = r3.bottom
            r4.set(r14, r2)
            goto L_0x008e
        L_0x0087:
            float r14 = r3.right
            float r2 = r3.bottom
            r4.set(r14, r2)
        L_0x008e:
            android.graphics.Matrix[] r14 = r10.f11354b
            r14 = r14[r12]
            android.graphics.PointF r2 = r10.f11356d
            float r2 = r2.x
            android.graphics.PointF r3 = r10.f11356d
            float r3 = r3.y
            r14.setTranslate(r2, r3)
            android.graphics.Matrix[] r14 = r10.f11354b
            r14 = r14[r12]
            r14.preRotate(r13)
            float[] r13 = r10.f11360h
            ghl[] r14 = r10.f11353a
            r14 = r14[r12]
            float r2 = r14.f11381b
            r13[r11] = r2
            float r14 = r14.f11382c
            r13[r1] = r14
            android.graphics.Matrix[] r14 = r10.f11354b
            r14 = r14[r12]
            r14.mapPoints(r13)
            android.graphics.Matrix[] r13 = r10.f11355c
            r13 = r13[r12]
            r13.reset()
            android.graphics.Matrix[] r13 = r10.f11355c
            r13 = r13[r12]
            float[] r14 = r10.f11360h
            r2 = r14[r11]
            r14 = r14[r1]
            r13.setTranslate(r2, r14)
            android.graphics.Matrix[] r13 = r10.f11355c
            r13 = r13[r12]
            float r14 = m10335a(r12)
            r13.preRotate(r14)
            int r12 = r12 + 1
            goto L_0x0021
        L_0x00dc:
            r12 = 0
        L_0x00dd:
            if (r12 >= r13) goto L_0x0230
            float[] r2 = r10.f11360h
            ghl[] r3 = r10.f11353a
            r3 = r3[r12]
            r4 = 0
            r2[r11] = r4
            float r3 = r3.f11380a
            r2[r1] = r3
            android.graphics.Matrix[] r3 = r10.f11354b
            r3 = r3[r12]
            r3.mapPoints(r2)
            if (r12 != 0) goto L_0x0101
            android.graphics.Path r2 = r0.f11349b
            float[] r3 = r10.f11360h
            r5 = r3[r11]
            r3 = r3[r1]
            r2.moveTo(r5, r3)
            goto L_0x010c
        L_0x0101:
            android.graphics.Path r2 = r0.f11349b
            float[] r3 = r10.f11360h
            r5 = r3[r11]
            r3 = r3[r1]
            r2.lineTo(r5, r3)
        L_0x010c:
            ghl[] r2 = r10.f11353a
            r2 = r2[r12]
            android.graphics.Matrix[] r3 = r10.f11354b
            r3 = r3[r12]
            android.graphics.Path r5 = r0.f11349b
            r2.mo6682a((android.graphics.Matrix) r3, (android.graphics.Path) r5)
            ghb r2 = r0.f11351d
            if (r2 == 0) goto L_0x0131
            ghl[] r3 = r10.f11353a
            r3 = r3[r12]
            android.graphics.Matrix[] r5 = r10.f11354b
            r5 = r5[r12]
            ggr r2 = (p000.ggr) r2
            ggu r2 = r2.f11272a
            ghk[] r2 = r2.f11294b
            ghk r3 = r3.mo6678a((android.graphics.Matrix) r5)
            r2[r12] = r3
        L_0x0131:
            int r2 = r12 + 1
            r3 = r2 & 3
            float[] r5 = r10.f11360h
            ghl[] r6 = r10.f11353a
            r6 = r6[r12]
            float r7 = r6.f11381b
            r5[r11] = r7
            float r6 = r6.f11382c
            r5[r1] = r6
            android.graphics.Matrix[] r6 = r10.f11354b
            r6 = r6[r12]
            r6.mapPoints(r5)
            float[] r5 = r10.f11361i
            ghl[] r6 = r10.f11353a
            r6 = r6[r3]
            r5[r11] = r4
            float r6 = r6.f11380a
            r5[r1] = r6
            android.graphics.Matrix[] r6 = r10.f11354b
            r6 = r6[r3]
            r6.mapPoints(r5)
            float[] r5 = r10.f11360h
            r6 = r5[r11]
            float[] r7 = r10.f11361i
            r8 = r7[r11]
            float r6 = r6 - r8
            double r8 = (double) r6
            r5 = r5[r1]
            r6 = r7[r1]
            float r5 = r5 - r6
            double r5 = (double) r5
            double r5 = java.lang.Math.hypot(r8, r5)
            float r5 = (float) r5
            r6 = -1165815185(0xffffffffba83126f, float:-0.001)
            float r5 = r5 + r6
            float r5 = java.lang.Math.max(r5, r4)
            android.graphics.RectF r6 = r0.f11350c
            float[] r7 = r10.f11360h
            ghl[] r8 = r10.f11353a
            r8 = r8[r12]
            float r9 = r8.f11381b
            r7[r11] = r9
            float r8 = r8.f11382c
            r7[r1] = r8
            android.graphics.Matrix[] r8 = r10.f11354b
            r8 = r8[r12]
            r8.mapPoints(r7)
            if (r12 == r1) goto L_0x01a2
            if (r12 == r14) goto L_0x01a2
            float r6 = r6.centerY()
            float[] r7 = r10.f11360h
            r7 = r7[r1]
            float r6 = r6 - r7
            java.lang.Math.abs(r6)
            goto L_0x01ae
        L_0x01a2:
            float r6 = r6.centerX()
            float[] r7 = r10.f11360h
            r7 = r7[r11]
            float r6 = r6 - r7
            java.lang.Math.abs(r6)
        L_0x01ae:
            ghl r6 = r10.f11359g
            r6.mo6679a()
            ghl r6 = r10.f11359g
            r6.mo6680a((float) r5, (float) r4)
            android.graphics.Path r5 = new android.graphics.Path
            r5.<init>()
            ghl r6 = r10.f11359g
            android.graphics.Matrix[] r7 = r10.f11355c
            r7 = r7[r12]
            r6.mo6682a((android.graphics.Matrix) r7, (android.graphics.Path) r5)
            boolean r6 = r10.f11362j
            if (r6 == 0) goto L_0x0209
            int r6 = android.os.Build.VERSION.SDK_INT
            boolean r6 = r10.m10336a(r5, r12)
            if (r6 == 0) goto L_0x01d3
            goto L_0x01da
        L_0x01d3:
            boolean r3 = r10.m10336a(r5, r3)
            if (r3 != 0) goto L_0x01da
            goto L_0x0209
        L_0x01da:
            android.graphics.Path r3 = r10.f11358f
            android.graphics.Path$Op r6 = android.graphics.Path.Op.DIFFERENCE
            r5.op(r5, r3, r6)
            float[] r3 = r10.f11360h
            r3[r11] = r4
            ghl r4 = r10.f11359g
            float r4 = r4.f11380a
            r3[r1] = r4
            android.graphics.Matrix[] r4 = r10.f11355c
            r4 = r4[r12]
            r4.mapPoints(r3)
            android.graphics.Path r3 = r10.f11357e
            float[] r4 = r10.f11360h
            r5 = r4[r11]
            r4 = r4[r1]
            r3.moveTo(r5, r4)
            ghl r3 = r10.f11359g
            android.graphics.Matrix[] r4 = r10.f11355c
            r4 = r4[r12]
            android.graphics.Path r5 = r10.f11357e
            r3.mo6682a((android.graphics.Matrix) r4, (android.graphics.Path) r5)
            goto L_0x0214
        L_0x0209:
            ghl r3 = r10.f11359g
            android.graphics.Matrix[] r4 = r10.f11355c
            r4 = r4[r12]
            android.graphics.Path r5 = r0.f11349b
            r3.mo6682a((android.graphics.Matrix) r4, (android.graphics.Path) r5)
        L_0x0214:
            ghb r3 = r0.f11351d
            if (r3 == 0) goto L_0x022c
            ghl r4 = r10.f11359g
            android.graphics.Matrix[] r5 = r10.f11355c
            r5 = r5[r12]
            ggr r3 = (p000.ggr) r3
            ggu r3 = r3.f11272a
            ghk[] r3 = r3.f11295c
            ghk r4 = r4.mo6678a((android.graphics.Matrix) r5)
            r3[r12] = r4
            goto L_0x022d
        L_0x022c:
        L_0x022d:
            r12 = r2
            goto L_0x00dd
        L_0x0230:
            r15.close()
            android.graphics.Path r11 = r10.f11357e
            r11.close()
            int r11 = android.os.Build.VERSION.SDK_INT
            android.graphics.Path r11 = r10.f11357e
            boolean r11 = r11.isEmpty()
            if (r11 != 0) goto L_0x0249
            android.graphics.Path r11 = r10.f11357e
            android.graphics.Path$Op r12 = android.graphics.Path.Op.UNION
            r15.op(r11, r12)
        L_0x0249:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ghd.mo6673a(gha, float, android.graphics.RectF, ghb, android.graphics.Path):void");
    }

    /* renamed from: a */
    private final boolean m10336a(Path path, int i) {
        Path path2 = new Path();
        this.f11353a[i].mo6682a(this.f11354b[i], path2);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        path2.computeBounds(rectF, true);
        path.op(path2, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (!rectF.isEmpty()) {
            return true;
        }
        if (rectF.width() <= 1.0f || rectF.height() <= 1.0f) {
            return false;
        }
        return true;
    }
}
