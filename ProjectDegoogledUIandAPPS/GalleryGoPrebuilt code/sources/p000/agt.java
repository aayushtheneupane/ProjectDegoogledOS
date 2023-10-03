package p000;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Shader;

/* renamed from: agt */
/* compiled from: PG */
final class agt {

    /* renamed from: a */
    public static final Matrix f422a = new Matrix();

    /* renamed from: b */
    public Paint f423b;

    /* renamed from: c */
    public Paint f424c;

    /* renamed from: d */
    public final agr f425d;

    /* renamed from: e */
    public float f426e;

    /* renamed from: f */
    public float f427f;

    /* renamed from: g */
    public float f428g;

    /* renamed from: h */
    public float f429h;

    /* renamed from: i */
    public String f430i;

    /* renamed from: j */
    public Boolean f431j;

    /* renamed from: k */
    public final C0290kn f432k;

    /* renamed from: l */
    private final Path f433l;

    /* renamed from: m */
    private final Path f434m;

    /* renamed from: n */
    private final Matrix f435n;

    /* renamed from: o */
    private PathMeasure f436o;

    /* renamed from: p */
    private int f437p;

    public int getRootAlpha() {
        return this.f437p;
    }

    public agt() {
        this.f435n = new Matrix();
        this.f426e = 0.0f;
        this.f427f = 0.0f;
        this.f428g = 0.0f;
        this.f429h = 0.0f;
        this.f437p = 255;
        this.f430i = null;
        this.f431j = null;
        this.f432k = new C0290kn();
        this.f425d = new agr();
        this.f433l = new Path();
        this.f434m = new Path();
    }

    public agt(agt agt) {
        this.f435n = new Matrix();
        this.f426e = 0.0f;
        this.f427f = 0.0f;
        this.f428g = 0.0f;
        this.f429h = 0.0f;
        this.f437p = 255;
        this.f430i = null;
        this.f431j = null;
        this.f432k = new C0290kn();
        this.f425d = new agr(agt.f425d, this.f432k);
        this.f433l = new Path(agt.f433l);
        this.f434m = new Path(agt.f434m);
        this.f426e = agt.f426e;
        this.f427f = agt.f427f;
        this.f428g = agt.f428g;
        this.f429h = agt.f429h;
        this.f437p = agt.f437p;
        this.f430i = agt.f430i;
        String str = agt.f430i;
        if (str != null) {
            this.f432k.put(str, this);
        }
        this.f431j = agt.f431j;
    }

    /* renamed from: a */
    public final void mo415a(agr agr, Matrix matrix, Canvas canvas, int i, int i2) {
        int i3;
        float f;
        float f2;
        float f3;
        float f4;
        int i4;
        float[] fArr;
        int i5;
        C0240ir[] irVarArr;
        char c;
        float f5;
        float f6;
        float f7;
        float f8;
        agr agr2 = agr;
        Canvas canvas2 = canvas;
        agr2.f408a.set(matrix);
        agr2.f408a.preConcat(agr2.f417j);
        canvas.save();
        char c2 = 0;
        int i6 = 0;
        while (i6 < agr2.f409b.size()) {
            ggf ggf = (ggf) agr2.f409b.get(i6);
            if (ggf instanceof agr) {
                mo415a((agr) ggf, agr2.f408a, canvas, i, i2);
                i3 = i6;
            } else if (ggf instanceof ags) {
                ags ags = (ags) ggf;
                float f9 = ((float) i) / this.f428g;
                float f10 = ((float) i2) / this.f429h;
                float min = Math.min(f9, f10);
                Matrix matrix2 = agr2.f408a;
                this.f435n.set(matrix2);
                this.f435n.postScale(f9, f10);
                float[] fArr2 = {0.0f, 1.0f, 1.0f, 0.0f};
                matrix2.mapVectors(fArr2);
                double hypot = Math.hypot((double) fArr2[c2], (double) fArr2[1]);
                double hypot2 = Math.hypot((double) fArr2[2], (double) fArr2[3]);
                float f11 = (fArr2[c2] * fArr2[3]) - (fArr2[1] * fArr2[2]);
                float max = Math.max((float) hypot, (float) hypot2);
                if (max > 0.0f) {
                    f = Math.abs(f11) / max;
                } else {
                    f = 0.0f;
                }
                if (f != 0.0f) {
                    Path path = this.f433l;
                    path.reset();
                    C0240ir[] irVarArr2 = ags.f419l;
                    if (irVarArr2 == null) {
                        f3 = f;
                        f2 = min;
                        i3 = i6;
                    } else {
                        float[] fArr3 = new float[6];
                        int i7 = 0;
                        char c3 = 'm';
                        while (i7 < irVarArr2.length) {
                            C0240ir irVar = irVarArr2[i7];
                            char c4 = irVar.f14812a;
                            float[] fArr4 = irVar.f14813b;
                            float f12 = fArr3[c2];
                            float f13 = fArr3[1];
                            float f14 = fArr3[2];
                            float f15 = fArr3[3];
                            float f16 = fArr3[4];
                            float f17 = fArr3[5];
                            switch (c4) {
                                case 'A':
                                case 'a':
                                    i4 = 7;
                                    break;
                                case 'C':
                                case 'c':
                                    i4 = 6;
                                    break;
                                case 'H':
                                case 'V':
                                case 'h':
                                case 'v':
                                    i4 = 1;
                                    break;
                                case 'L':
                                case 'M':
                                case 'T':
                                case 'l':
                                case 'm':
                                case 't':
                                    break;
                                case 'Q':
                                case 'S':
                                case 'q':
                                case 's':
                                    i4 = 4;
                                    break;
                                case 'Z':
                                case 'z':
                                    path.close();
                                    path.moveTo(f16, f17);
                                    f13 = f17;
                                    f15 = f13;
                                    f12 = f16;
                                    f14 = f12;
                                    break;
                                default:
                                    i4 = 2;
                                    break;
                            }
                            i4 = 2;
                            float f18 = f;
                            int i8 = i6;
                            char c5 = c3;
                            float f19 = f12;
                            float f20 = f13;
                            int i9 = 0;
                            while (true) {
                                float f21 = min;
                                if (i9 >= fArr4.length) {
                                    fArr3[0] = f19;
                                    fArr3[1] = f20;
                                    fArr3[2] = f14;
                                    fArr3[3] = f15;
                                    fArr3[4] = f16;
                                    fArr3[5] = f17;
                                    i7++;
                                    agr agr3 = agr;
                                    c3 = irVarArr2[i7].f14812a;
                                    i6 = i8;
                                    f = f18;
                                    min = f21;
                                    c2 = 0;
                                    int i10 = i;
                                } else {
                                    if (c4 == 'A') {
                                        c = c4;
                                        float f22 = f17;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        int i11 = i9 + 5;
                                        int i12 = i9 + 6;
                                        C0240ir.m14342a(path, f19, f20, fArr4[i11], fArr4[i12], fArr4[i9], fArr4[i9 + 1], fArr4[i9 + 2], fArr4[i9 + 3] != 0.0f, fArr4[i9 + 4] != 0.0f);
                                        f19 = fArr4[i11];
                                        f20 = fArr4[i12];
                                        f14 = f19;
                                        f15 = f20;
                                        f17 = f22;
                                    } else if (c4 == 'C') {
                                        c = c4;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        int i13 = i9 + 2;
                                        int i14 = i9 + 3;
                                        int i15 = i9 + 4;
                                        int i16 = i9 + 5;
                                        path.cubicTo(fArr4[i9], fArr4[i9 + 1], fArr4[i13], fArr4[i14], fArr4[i15], fArr4[i16]);
                                        float f23 = fArr4[i15];
                                        f20 = fArr4[i16];
                                        f14 = fArr4[i13];
                                        f15 = fArr4[i14];
                                        f19 = f23;
                                        f17 = f17;
                                    } else if (c4 == 'H') {
                                        c = c4;
                                        float f24 = f17;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        path.lineTo(fArr4[i9], f20);
                                        f19 = fArr4[i9];
                                    } else if (c4 == 'Q') {
                                        c = c4;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        int i17 = i9 + 1;
                                        int i18 = i9 + 2;
                                        int i19 = i9 + 3;
                                        path.quadTo(fArr4[i9], fArr4[i17], fArr4[i18], fArr4[i19]);
                                        float f25 = fArr4[i9];
                                        float f26 = fArr4[i17];
                                        float f27 = fArr4[i18];
                                        f20 = fArr4[i19];
                                        f15 = f26;
                                        f14 = f25;
                                        f17 = f17;
                                        f19 = f27;
                                    } else if (c4 == 'V') {
                                        c = c4;
                                        float f28 = f17;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        path.lineTo(f19, fArr4[i9]);
                                        f20 = fArr4[i9];
                                    } else if (c4 == 'a') {
                                        c = c4;
                                        float f29 = f17;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        int i20 = i9 + 5;
                                        int i21 = i9 + 6;
                                        C0240ir.m14342a(path, f19, f20, fArr4[i20] + f19, fArr4[i21] + f20, fArr4[i9], fArr4[i9 + 1], fArr4[i9 + 2], fArr4[i9 + 3] != 0.0f, fArr4[i9 + 4] != 0.0f);
                                        f19 += fArr4[i20];
                                        f20 += fArr4[i21];
                                        f14 = f19;
                                        f15 = f20;
                                        f17 = f29;
                                    } else if (c4 == 'c') {
                                        c = c4;
                                        float f30 = f17;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        int i22 = i9 + 2;
                                        int i23 = i9 + 3;
                                        int i24 = i9 + 4;
                                        int i25 = i9 + 5;
                                        path.rCubicTo(fArr4[i9], fArr4[i9 + 1], fArr4[i22], fArr4[i23], fArr4[i24], fArr4[i25]);
                                        float f31 = fArr4[i22] + f19;
                                        float f32 = fArr4[i23] + f20;
                                        f19 += fArr4[i24];
                                        f20 += fArr4[i25];
                                        f14 = f31;
                                        f15 = f32;
                                        f17 = f30;
                                    } else if (c4 == 'h') {
                                        c = c4;
                                        float f33 = f17;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        path.rLineTo(fArr4[i9], 0.0f);
                                        f19 += fArr4[i9];
                                    } else if (c4 == 'q') {
                                        c = c4;
                                        float f34 = f17;
                                        int i26 = i9 + 1;
                                        int i27 = i9 + 2;
                                        int i28 = i9 + 3;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        path.rQuadTo(fArr4[i9], fArr4[i26], fArr4[i27], fArr4[i28]);
                                        float f35 = fArr4[i9] + f19;
                                        float f36 = fArr4[i26] + f20;
                                        f19 += fArr4[i27];
                                        f20 += fArr4[i28];
                                        f15 = f36;
                                        f14 = f35;
                                        f17 = f34;
                                    } else if (c4 == 'v') {
                                        c = c4;
                                        float f37 = f17;
                                        path.rLineTo(0.0f, fArr4[i9]);
                                        f20 += fArr4[i9];
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                    } else if (c4 == 'L') {
                                        c = c4;
                                        float f38 = f17;
                                        int i29 = i9 + 1;
                                        path.lineTo(fArr4[i9], fArr4[i29]);
                                        f19 = fArr4[i9];
                                        f20 = fArr4[i29];
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                    } else if (c4 == 'M') {
                                        c = c4;
                                        float f39 = f17;
                                        f19 = fArr4[i9];
                                        f20 = fArr4[i9 + 1];
                                        if (i9 > 0) {
                                            path.lineTo(f19, f20);
                                            irVarArr = irVarArr2;
                                            i5 = i7;
                                            fArr = fArr3;
                                            f17 = f39;
                                        } else {
                                            path.moveTo(f19, f20);
                                            f16 = f19;
                                            f17 = f20;
                                            irVarArr = irVarArr2;
                                            i5 = i7;
                                            fArr = fArr3;
                                        }
                                    } else if (c4 == 'S') {
                                        c = c4;
                                        float f40 = f17;
                                        if (c5 == 'c' || c5 == 's' || c5 == 'C' || c5 == 'S') {
                                            f19 = (f19 + f19) - f14;
                                            f20 = (f20 + f20) - f15;
                                        }
                                        int i30 = i9 + 1;
                                        int i31 = i9 + 2;
                                        int i32 = i9 + 3;
                                        path.cubicTo(f19, f20, fArr4[i9], fArr4[i30], fArr4[i31], fArr4[i32]);
                                        float f41 = fArr4[i9];
                                        float f42 = fArr4[i30];
                                        float f43 = fArr4[i31];
                                        f20 = fArr4[i32];
                                        f15 = f42;
                                        f14 = f41;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        f17 = f40;
                                        f19 = f43;
                                    } else if (c4 == 'T') {
                                        c = c4;
                                        float f44 = f17;
                                        if (c5 == 'q' || c5 == 't' || c5 == 'Q' || c5 == 'T') {
                                            f19 = (f19 + f19) - f14;
                                            f20 = (f20 + f20) - f15;
                                        }
                                        int i33 = i9 + 1;
                                        path.quadTo(f19, f20, fArr4[i9], fArr4[i33]);
                                        float f45 = fArr4[i9];
                                        f14 = f19;
                                        f15 = f20;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        f17 = f44;
                                        f20 = fArr4[i33];
                                        f19 = f45;
                                    } else if (c4 == 'l') {
                                        c = c4;
                                        float f46 = f17;
                                        int i34 = i9 + 1;
                                        path.rLineTo(fArr4[i9], fArr4[i34]);
                                        f19 += fArr4[i9];
                                        f20 += fArr4[i34];
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                    } else if (c4 == 'm') {
                                        c = c4;
                                        float f47 = f17;
                                        float f48 = fArr4[i9];
                                        f19 += f48;
                                        float f49 = fArr4[i9 + 1];
                                        f20 += f49;
                                        if (i9 > 0) {
                                            path.rLineTo(f48, f49);
                                            irVarArr = irVarArr2;
                                            i5 = i7;
                                            fArr = fArr3;
                                            f17 = f47;
                                        } else {
                                            path.rMoveTo(f48, f49);
                                            f16 = f19;
                                            f17 = f20;
                                            irVarArr = irVarArr2;
                                            i5 = i7;
                                            fArr = fArr3;
                                        }
                                    } else if (c4 == 's') {
                                        c = c4;
                                        float f50 = f17;
                                        if (c5 == 'c' || c5 == 's' || c5 == 'C' || c5 == 'S') {
                                            f6 = f19 - f14;
                                            f5 = f20 - f15;
                                        } else {
                                            f6 = 0.0f;
                                            f5 = 0.0f;
                                        }
                                        int i35 = i9 + 1;
                                        int i36 = i9 + 2;
                                        int i37 = i9 + 3;
                                        path.rCubicTo(f6, f5, fArr4[i9], fArr4[i35], fArr4[i36], fArr4[i37]);
                                        float f51 = fArr4[i9] + f19;
                                        float f52 = fArr4[i35] + f20;
                                        f19 += fArr4[i36];
                                        f20 += fArr4[i37];
                                        f15 = f52;
                                        f14 = f51;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        f17 = f50;
                                    } else if (c4 != 't') {
                                        c = c4;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                    } else {
                                        if (c5 == 'q' || c5 == 't' || c5 == 'Q' || c5 == 'T') {
                                            f8 = f19 - f14;
                                            f7 = f20 - f15;
                                        } else {
                                            f8 = 0.0f;
                                            f7 = 0.0f;
                                        }
                                        int i38 = i9 + 1;
                                        c = c4;
                                        path.rQuadTo(f8, f7, fArr4[i9], fArr4[i38]);
                                        float f53 = f8 + f19;
                                        float f54 = f7 + f20;
                                        f19 += fArr4[i9];
                                        f20 += fArr4[i38];
                                        f14 = f53;
                                        f15 = f54;
                                        irVarArr = irVarArr2;
                                        i5 = i7;
                                        fArr = fArr3;
                                        f17 = f17;
                                    }
                                    i9 += i4;
                                    min = f21;
                                    c5 = c;
                                    c4 = c5;
                                    irVarArr2 = irVarArr;
                                    i7 = i5;
                                    fArr3 = fArr;
                                }
                            }
                        }
                        f3 = f;
                        f2 = min;
                        i3 = i6;
                    }
                    Path path2 = this.f433l;
                    this.f434m.reset();
                    if (ags.mo376a()) {
                        this.f434m.setFillType(ags.f421n == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                        this.f434m.addPath(path2, this.f435n);
                        canvas2.clipPath(this.f434m);
                    } else {
                        agq agq = (agq) ags;
                        float f55 = agq.f402f;
                        if (f55 != 0.0f || agq.f403g != 1.0f) {
                            float f56 = agq.f404h;
                            float f57 = (f55 + f56) % 1.0f;
                            float f58 = (agq.f403g + f56) % 1.0f;
                            if (this.f436o == null) {
                                this.f436o = new PathMeasure();
                            }
                            this.f436o.setPath(this.f433l, false);
                            float length = this.f436o.getLength();
                            float f59 = f57 * length;
                            float f60 = f58 * length;
                            path2.reset();
                            if (f59 > f60) {
                                this.f436o.getSegment(f59, length, path2, true);
                                f4 = 0.0f;
                                this.f436o.getSegment(0.0f, f60, path2, true);
                            } else {
                                f4 = 0.0f;
                                this.f436o.getSegment(f59, f60, path2, true);
                            }
                            path2.rLineTo(f4, f4);
                        }
                        this.f434m.addPath(path2, this.f435n);
                        if (agq.f399c.mo8497c()) {
                            C0229ig igVar = agq.f399c;
                            if (this.f424c == null) {
                                Paint paint = new Paint(1);
                                this.f424c = paint;
                                paint.setStyle(Paint.Style.FILL);
                            }
                            Paint paint2 = this.f424c;
                            if (igVar.mo8494a()) {
                                Shader shader = igVar.f14045a;
                                shader.setLocalMatrix(this.f435n);
                                paint2.setShader(shader);
                                paint2.setAlpha(Math.round(agq.f401e * 255.0f));
                            } else {
                                paint2.setShader((Shader) null);
                                paint2.setAlpha(255);
                                paint2.setColor(agw.m466a(igVar.f14046b, agq.f401e));
                            }
                            paint2.setColorFilter((ColorFilter) null);
                            this.f434m.setFillType(agq.f421n == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                            canvas2.drawPath(this.f434m, paint2);
                        }
                        if (agq.f397a.mo8497c()) {
                            C0229ig igVar2 = agq.f397a;
                            if (this.f423b == null) {
                                Paint paint3 = new Paint(1);
                                this.f423b = paint3;
                                paint3.setStyle(Paint.Style.STROKE);
                            }
                            Paint paint4 = this.f423b;
                            Paint.Join join = agq.f406j;
                            if (join != null) {
                                paint4.setStrokeJoin(join);
                            }
                            Paint.Cap cap = agq.f405i;
                            if (cap != null) {
                                paint4.setStrokeCap(cap);
                            }
                            paint4.setStrokeMiter(agq.f407k);
                            if (igVar2.mo8494a()) {
                                Shader shader2 = igVar2.f14045a;
                                shader2.setLocalMatrix(this.f435n);
                                paint4.setShader(shader2);
                                paint4.setAlpha(Math.round(agq.f400d * 255.0f));
                            } else {
                                paint4.setShader((Shader) null);
                                paint4.setAlpha(255);
                                paint4.setColor(agw.m466a(igVar2.f14046b, agq.f400d));
                            }
                            paint4.setColorFilter((ColorFilter) null);
                            paint4.setStrokeWidth(agq.f398b * f2 * f3);
                            canvas2.drawPath(this.f434m, paint4);
                        }
                    }
                } else {
                    i3 = i6;
                }
            } else {
                i3 = i6;
            }
            i6 = i3 + 1;
            agr2 = agr;
            c2 = 0;
        }
        canvas.restore();
    }

    public float getAlpha() {
        return ((float) getRootAlpha()) / 255.0f;
    }

    public void setAlpha(float f) {
        setRootAlpha((int) (f * 255.0f));
    }

    public void setRootAlpha(int i) {
        this.f437p = i;
    }
}
