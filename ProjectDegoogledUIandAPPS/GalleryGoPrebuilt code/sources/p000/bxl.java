package p000;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.ScaleGestureDetector;
import android.view.View;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import p003j$.util.Optional;

/* renamed from: bxl */
/* compiled from: PG */
public final class bxl implements View.OnTouchListener, C0438q {

    /* renamed from: a */
    public static final TimeInterpolator f3832a = new fry();

    /* renamed from: A */
    private Optional f3833A = Optional.empty();

    /* renamed from: b */
    public final PointF f3834b = new PointF();

    /* renamed from: c */
    public final PointF f3835c = new PointF();

    /* renamed from: d */
    public final RectF f3836d = new RectF();

    /* renamed from: e */
    public final RectF f3837e = new RectF(((byq) byu.f3916b).f3914a);

    /* renamed from: f */
    public final PipelineParams f3838f = new PipelineParams();

    /* renamed from: g */
    public final bxq f3839g;

    /* renamed from: h */
    public final Animator.AnimatorListener f3840h = new bxk(this);

    /* renamed from: i */
    public final bxm f3841i;

    /* renamed from: j */
    public final bxc f3842j;

    /* renamed from: k */
    public final bxo f3843k;

    /* renamed from: l */
    public final bxg f3844l;

    /* renamed from: m */
    public View f3845m;

    /* renamed from: n */
    public int f3846n = -1;

    /* renamed from: o */
    public byh f3847o;

    /* renamed from: p */
    public boolean f3848p;

    /* renamed from: q */
    private final RectF f3849q = new RectF();

    /* renamed from: r */
    private final RectF f3850r = new RectF();

    /* renamed from: s */
    private final RectF f3851s = new RectF();

    /* renamed from: t */
    private final RectF f3852t = new RectF();

    /* renamed from: u */
    private final PointF f3853u = new PointF();

    /* renamed from: v */
    private final ScaleGestureDetector.OnScaleGestureListener f3854v = new bxj(this);

    /* renamed from: w */
    private final ScaleGestureDetector f3855w;

    /* renamed from: x */
    private final int f3856x;

    /* renamed from: y */
    private int f3857y = 0;

    /* renamed from: z */
    private final int f3858z;

    /* renamed from: d */
    private final boolean m3730d() {
        return this.f3857y != 0;
    }

    /* renamed from: a */
    public final void mo2556a() {
    }

    /* renamed from: b */
    public final void mo2558b() {
    }

    /* renamed from: c */
    public final void mo2560c() {
    }

    /* renamed from: c */
    public final void mo2561c(C0681z zVar) {
    }

    public bxl(Context context, C0147fh fhVar, bxc bxc, bxq bxq, bxm bxm, bxo bxo, bxg bxg) {
        this.f3842j = bxc;
        this.f3839g = bxq;
        this.f3841i = bxm;
        this.f3843k = bxo;
        this.f3855w = new ScaleGestureDetector(context, this.f3854v);
        Resources resources = context.getResources();
        this.f3856x = resources.getDimensionPixelSize(R.dimen.editor_crop_touch_handle_size);
        this.f3858z = resources.getDimensionPixelSize(R.dimen.editor_crop_min_size);
        this.f3844l = bxg;
        fhVar.mo5ad().mo64a(this);
    }

    /* renamed from: a */
    public final void mo2881a(PipelineParams pipelineParams) {
        ife.m12898e((Object) this.f3845m);
        byu.f3916b.mo2916b(pipelineParams, this.f3851s);
        ihg.m13034a(this.f3836d, this.f3845m.getWidth(), this.f3845m.getHeight(), this.f3851s, pipelineParams, this.f3842j);
        this.f3839g.mo2887a(byu.f3916b, (Object) byq.m3795b(pipelineParams));
        this.f3839g.mo2887a(bzl.f3936a, (Object) bzi.m3862b(pipelineParams));
        this.f3839g.mo2887a(bzl.f3937b, (Object) bzj.m3868b(pipelineParams));
    }

    /* renamed from: a */
    public final void mo2557a(C0681z zVar) {
        Optional of = Optional.m16285of(new bxh(this));
        this.f3833A = of;
        this.f3839g.mo2886a((bxp) of.get());
    }

    /* renamed from: b */
    public final void mo2559b(C0681z zVar) {
        this.f3833A.ifPresent(new bxi(this));
        this.f3833A = Optional.empty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        if (r2 != 3) goto L_0x03c6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouch(android.view.View r27, android.view.MotionEvent r28) {
        /*
            r26 = this;
            r0 = r26
            r1 = r28
            android.view.View r2 = r0.f3845m
            r3 = 0
            if (r2 == 0) goto L_0x03c8
            int r2 = r0.f3846n
            r4 = -1
            if (r2 != r4) goto L_0x001b
            android.graphics.PointF r2 = r0.f3835c
            float r5 = r28.getX()
            float r6 = r28.getY()
            r2.set(r5, r6)
        L_0x001b:
            boolean r2 = r26.m3730d()
            if (r2 != 0) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            android.view.ScaleGestureDetector r2 = r0.f3855w
            r2.onTouchEvent(r1)
        L_0x0027:
            int r2 = r0.f3846n
            r5 = -2
            r6 = 1
            if (r2 == r5) goto L_0x03c7
            int r2 = r28.getActionMasked()
            if (r2 == 0) goto L_0x0369
            r5 = 0
            if (r2 == r6) goto L_0x02f4
            r7 = 2
            if (r2 == r7) goto L_0x003e
            r1 = 3
            if (r2 == r1) goto L_0x02f4
            goto L_0x03c6
        L_0x003e:
            boolean r2 = r26.m3730d()
            if (r2 == 0) goto L_0x03c6
            int r2 = r0.f3846n
            int r3 = r1.getPointerId(r3)
            if (r2 != r3) goto L_0x03c6
            float r2 = r28.getX()
            float r1 = r28.getY()
            android.view.View r3 = r0.f3845m
            if (r3 == 0) goto L_0x03c6
            int r3 = r0.f3857y
            if (r3 == 0) goto L_0x03c6
            android.graphics.PointF r3 = r0.f3835c
            float r3 = r3.x
            float r3 = r2 - r3
            android.graphics.PointF r4 = r0.f3835c
            float r4 = r4.y
            float r4 = r1 - r4
            int r8 = r0.f3857y
            android.graphics.RectF r9 = r0.f3850r
            int r10 = r0.f3858z
            r11 = 8
            r12 = 15
            if (r8 != r12) goto L_0x0089
            float r8 = r9.left
            float r8 = r8 - r3
            r9.left = r8
            float r8 = r9.top
            float r8 = r8 - r4
            r9.top = r8
            float r8 = r9.right
            float r8 = r8 - r3
            r9.right = r8
            float r3 = r9.bottom
            float r3 = r3 - r4
            r9.bottom = r3
            goto L_0x00ed
        L_0x0089:
            r13 = r8 & 1
            if (r13 != 0) goto L_0x00a5
            r13 = r8 & 4
            if (r13 == 0) goto L_0x00b9
            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r13 >= 0) goto L_0x009f
            float r13 = (float) r10
            float r14 = r9.width()
            float r13 = r13 - r14
            float r3 = java.lang.Math.max(r3, r13)
        L_0x009f:
            float r13 = r9.right
            float r13 = r13 + r3
            r9.right = r13
            goto L_0x00b9
        L_0x00a5:
            int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r13 <= 0) goto L_0x00b4
            float r13 = r9.width()
            float r14 = (float) r10
            float r13 = r13 - r14
            float r3 = java.lang.Math.min(r3, r13)
        L_0x00b4:
            float r13 = r9.left
            float r13 = r13 + r3
            r9.left = r13
        L_0x00b9:
            r3 = r8 & 2
            if (r3 != 0) goto L_0x00d7
            r3 = r8 & 8
            if (r3 == 0) goto L_0x00ed
            int r3 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x00d0
            float r3 = (float) r10
            float r8 = r9.height()
            float r3 = r3 - r8
            float r4 = java.lang.Math.max(r4, r3)
            goto L_0x00d1
        L_0x00d0:
        L_0x00d1:
            float r3 = r9.bottom
            float r3 = r3 + r4
            r9.bottom = r3
            goto L_0x00ed
        L_0x00d7:
            int r3 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x00e7
            float r3 = r9.height()
            float r8 = (float) r10
            float r3 = r3 - r8
            float r4 = java.lang.Math.min(r4, r3)
            goto L_0x00e8
        L_0x00e7:
        L_0x00e8:
            float r3 = r9.top
            float r3 = r3 + r4
            r9.top = r3
        L_0x00ed:
            android.graphics.PointF r3 = r0.f3835c
            r3.set(r2, r1)
            android.graphics.RectF r1 = r0.f3850r
            android.graphics.RectF r2 = r0.f3852t
            android.graphics.RectF r3 = r0.f3836d
            p000.ihg.m13035a((android.graphics.RectF) r1, (android.graphics.RectF) r2, (android.graphics.RectF) r3)
            bzy r1 = p000.byu.f3915a
            bxm r1 = r0.f3841i
            byc r1 = (p000.byc) r1
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r1 = r1.f3888c
            byh r1 = r1.f4837b
            float r1 = r1.mo2907b()
            bxm r2 = r0.f3841i
            byc r2 = (p000.byc) r2
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r2 = r2.f3888c
            java.lang.Float r2 = p000.byr.m3800b(r2)
            float r2 = r2.floatValue()
            boolean r3 = p000.ihg.m13042a((float) r1, (float) r5)
            if (r3 != 0) goto L_0x0130
            boolean r3 = p000.ihg.m13042a((float) r2, (float) r5)
            if (r3 != 0) goto L_0x0130
            r3 = 1078530011(0x40490fdb, float:3.1415927)
            boolean r2 = p000.ihg.m13042a((float) r2, (float) r3)
            if (r2 != 0) goto L_0x0130
            r2 = 1065353216(0x3f800000, float:1.0)
            float r1 = r2 / r1
        L_0x0130:
            boolean r2 = p000.ihg.m13042a((float) r1, (float) r5)
            if (r2 == 0) goto L_0x0138
            goto L_0x019a
        L_0x0138:
            int r2 = r0.f3857y
            if (r2 != r6) goto L_0x013d
            goto L_0x0144
        L_0x013d:
            if (r2 == r7) goto L_0x0144
            r3 = 4
            if (r2 == r3) goto L_0x0144
            if (r2 != r11) goto L_0x019a
        L_0x0144:
            bxc r13 = r0.f3842j
            bxm r2 = r0.f3841i
            byc r2 = (p000.byc) r2
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r2 = r2.f3888c
            java.lang.Float r2 = p000.byr.m3800b(r2)
            float r15 = r2.floatValue()
            bxm r2 = r0.f3841i
            byc r2 = (p000.byc) r2
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r2 = r2.f3888c
            java.lang.Float r2 = p000.bys.m3805b(r2)
            float r16 = r2.floatValue()
            android.graphics.RectF r2 = r0.f3849q
            float r2 = r2.left
            android.graphics.RectF r3 = r0.f3849q
            float r3 = r3.top
            android.graphics.RectF r4 = r0.f3849q
            float r4 = r4.right
            android.graphics.RectF r5 = r0.f3849q
            float r5 = r5.bottom
            android.graphics.RectF r7 = r0.f3852t
            float r7 = r7.left
            android.graphics.RectF r8 = r0.f3852t
            float r8 = r8.top
            android.graphics.RectF r9 = r0.f3852t
            float r9 = r9.right
            android.graphics.RectF r10 = r0.f3852t
            float r10 = r10.bottom
            android.graphics.RectF r11 = r0.f3852t
            r14 = r1
            r17 = r2
            r18 = r3
            r19 = r4
            r20 = r5
            r21 = r7
            r22 = r8
            r23 = r9
            r24 = r10
            r25 = r11
            r13.resizeCropRectWithForcedAspectRatio(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
        L_0x019a:
            bxc r13 = r0.f3842j
            bxm r2 = r0.f3841i
            byc r2 = (p000.byc) r2
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r14 = r2.f3888c
            int r15 = r0.f3857y
            android.graphics.RectF r2 = r0.f3852t
            float r2 = r2.left
            android.graphics.RectF r3 = r0.f3852t
            float r3 = r3.top
            android.graphics.RectF r4 = r0.f3852t
            float r4 = r4.right
            android.graphics.RectF r5 = r0.f3852t
            float r5 = r5.bottom
            r16 = r1
            r17 = r2
            r18 = r3
            r19 = r4
            r20 = r5
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r1 = r13.magicMove(r14, r15, r16, r17, r18, r19, r20)
            if (r1 != 0) goto L_0x01c9
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r1 = new com.google.android.apps.photosgo.editor.parameters.PipelineParams
            r1.<init>()
        L_0x01c9:
            bzy r2 = p000.byu.f3916b
            android.graphics.RectF r3 = r0.f3852t
            r2.mo2916b(r1, r3)
            int r2 = r0.f3857y
            if (r2 == r12) goto L_0x0209
            android.graphics.RectF r2 = r0.f3852t
            android.graphics.RectF r3 = r0.f3851s
            float r3 = r3.left
            android.graphics.RectF r4 = r0.f3852t
            float r4 = r4.left
            float r3 = java.lang.Math.min(r3, r4)
            android.graphics.RectF r4 = r0.f3851s
            float r4 = r4.top
            android.graphics.RectF r5 = r0.f3852t
            float r5 = r5.top
            float r4 = java.lang.Math.min(r4, r5)
            android.graphics.RectF r5 = r0.f3851s
            float r5 = r5.right
            android.graphics.RectF r7 = r0.f3852t
            float r7 = r7.right
            float r5 = java.lang.Math.max(r5, r7)
            android.graphics.RectF r7 = r0.f3851s
            float r7 = r7.bottom
            android.graphics.RectF r8 = r0.f3852t
            float r8 = r8.bottom
            float r7 = java.lang.Math.max(r7, r8)
            r2.set(r3, r4, r5, r7)
        L_0x0209:
            android.graphics.RectF r2 = r0.f3852t
            float r2 = r2.left
            android.graphics.RectF r3 = r0.f3851s
            float r3 = r3.left
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x0216
        L_0x0215:
            goto L_0x023e
        L_0x0216:
            android.graphics.RectF r2 = r0.f3852t
            float r2 = r2.top
            android.graphics.RectF r3 = r0.f3851s
            float r3 = r3.top
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 < 0) goto L_0x0215
            android.graphics.RectF r2 = r0.f3852t
            float r2 = r2.right
            android.graphics.RectF r3 = r0.f3851s
            float r3 = r3.right
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0215
            android.graphics.RectF r2 = r0.f3852t
            float r2 = r2.bottom
            android.graphics.RectF r3 = r0.f3851s
            float r3 = r3.bottom
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0215
            int r2 = r0.f3857y
            if (r2 != r12) goto L_0x0260
        L_0x023e:
            android.graphics.RectF r2 = r0.f3851s
            android.graphics.RectF r3 = r0.f3852t
            r2.set(r3)
            android.graphics.RectF r13 = r0.f3836d
            android.view.View r2 = r0.f3845m
            int r14 = r2.getWidth()
            android.view.View r2 = r0.f3845m
            int r15 = r2.getHeight()
            android.graphics.RectF r2 = r0.f3851s
            bxc r3 = r0.f3842j
            r16 = r2
            r17 = r1
            r18 = r3
            p000.ihg.m13034a((android.graphics.RectF) r13, (int) r14, (int) r15, (android.graphics.RectF) r16, (com.google.android.apps.photosgo.editor.parameters.PipelineParams) r17, (p000.bxc) r18)
        L_0x0260:
            android.graphics.RectF r2 = r0.f3850r
            android.graphics.RectF r3 = r0.f3852t
            android.graphics.RectF r4 = r0.f3836d
            p000.ihg.m13035a((android.graphics.RectF) r2, (android.graphics.RectF) r3, (android.graphics.RectF) r4)
            android.graphics.PointF r2 = r0.f3853u
            android.graphics.PointF r3 = r0.f3835c
            float r3 = r3.x
            android.graphics.RectF r4 = r0.f3836d
            float r3 = p000.ihg.m13012a((float) r3, (android.graphics.RectF) r4)
            android.graphics.PointF r4 = r0.f3835c
            float r4 = r4.y
            android.graphics.RectF r5 = r0.f3836d
            float r4 = p000.ihg.m13043b((float) r4, (android.graphics.RectF) r5)
            r2.set(r3, r4)
            bxq r2 = r0.f3839g
            bzy r3 = p000.byu.f3916b
            android.graphics.RectF r4 = p000.byq.m3795b(r1)
            r2.mo2887a((p000.bzy) r3, (java.lang.Object) r4)
            bxq r2 = r0.f3839g
            bzy r3 = p000.bzl.f3936a
            java.lang.Float r4 = p000.bzi.m3862b(r1)
            r2.mo2887a((p000.bzy) r3, (java.lang.Object) r4)
            bxq r2 = r0.f3839g
            bzy r3 = p000.bzl.f3937b
            android.graphics.PointF r1 = p000.bzj.m3868b(r1)
            r2.mo2887a((p000.bzy) r3, (java.lang.Object) r1)
            bxq r1 = r0.f3839g
            r1.mo2884a()
            android.graphics.RectF r1 = r0.f3850r
            android.graphics.RectF r2 = r0.f3852t
            float r2 = r2.left
            android.graphics.RectF r3 = r0.f3836d
            float r2 = p000.ihg.m13049c((float) r2, (android.graphics.RectF) r3)
            android.graphics.RectF r3 = r0.f3852t
            float r3 = r3.top
            android.graphics.RectF r4 = r0.f3836d
            float r3 = p000.ihg.m13052d((float) r3, (android.graphics.RectF) r4)
            android.graphics.RectF r4 = r0.f3852t
            float r4 = r4.right
            android.graphics.RectF r5 = r0.f3836d
            float r4 = p000.ihg.m13049c((float) r4, (android.graphics.RectF) r5)
            android.graphics.RectF r5 = r0.f3852t
            float r5 = r5.bottom
            android.graphics.RectF r7 = r0.f3836d
            float r5 = p000.ihg.m13052d((float) r5, (android.graphics.RectF) r7)
            r1.set(r2, r3, r4, r5)
            int r1 = r0.f3857y
            if (r1 == r12) goto L_0x03c6
            android.graphics.PointF r1 = r0.f3835c
            android.graphics.PointF r2 = r0.f3853u
            float r2 = r2.x
            android.graphics.RectF r3 = r0.f3836d
            float r2 = p000.ihg.m13049c((float) r2, (android.graphics.RectF) r3)
            android.graphics.PointF r3 = r0.f3853u
            float r3 = r3.y
            android.graphics.RectF r4 = r0.f3836d
            float r3 = p000.ihg.m13052d((float) r3, (android.graphics.RectF) r4)
            r1.set(r2, r3)
            goto L_0x03c6
        L_0x02f4:
            r0.f3846n = r4
            android.view.View r1 = r0.f3845m
            if (r1 == 0) goto L_0x03c6
            boolean r1 = r26.m3730d()
            if (r1 == 0) goto L_0x03c6
            bzy r1 = p000.byu.f3916b
            bxm r2 = r0.f3841i
            byc r2 = (p000.byc) r2
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r2 = r2.f3888c
            android.graphics.RectF r4 = r0.f3851s
            r1.mo2916b(r2, r4)
            bxm r1 = r0.f3841i
            byc r1 = (p000.byc) r1
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r1 = r1.f3888c
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r1 = com.google.android.apps.photosgo.editor.parameters.PipelineParams.m4770a(r1)
            android.graphics.RectF r7 = r0.f3836d
            android.view.View r2 = r0.f3845m
            int r8 = r2.getWidth()
            android.view.View r2 = r0.f3845m
            int r9 = r2.getHeight()
            android.graphics.RectF r10 = r0.f3851s
            bxc r12 = r0.f3842j
            r11 = r1
            p000.ihg.m13034a((android.graphics.RectF) r7, (int) r8, (int) r9, (android.graphics.RectF) r10, (com.google.android.apps.photosgo.editor.parameters.PipelineParams) r11, (p000.bxc) r12)
            bxq r2 = r0.f3839g
            bzy r4 = p000.byy.f3920a
            java.lang.Float r5 = java.lang.Float.valueOf(r5)
            r2.mo2887a((p000.bzy) r4, (java.lang.Object) r5)
            bxq r2 = r0.f3839g
            bzy r4 = p000.bzl.f3937b
            android.graphics.PointF r5 = p000.bzj.m3868b(r1)
            r2.mo2887a((p000.bzy) r4, (java.lang.Object) r5)
            bxq r2 = r0.f3839g
            bzy r4 = p000.bzl.f3936a
            java.lang.Float r1 = p000.bzi.m3862b(r1)
            r2.mo2887a((p000.bzy) r4, (java.lang.Object) r1)
            bxo r1 = r0.f3843k
            android.animation.TimeInterpolator r2 = f3832a
            r1.f3860a = r2
            android.animation.Animator$AnimatorListener r2 = r0.f3840h
            r1.f3861b = r2
            r1.mo2883a()
            r0.f3857y = r3
            android.graphics.RectF r1 = r0.f3850r
            r1.setEmpty()
            android.graphics.RectF r1 = r0.f3849q
            r1.setEmpty()
            goto L_0x03c6
        L_0x0369:
            bxg r2 = r0.f3844l
            android.graphics.RectF r2 = r2.mo2874a()
            int r4 = r0.f3856x
            float r5 = r28.getX()
            float r7 = r28.getY()
            int r2 = p000.ihg.m13018a((android.graphics.RectF) r2, (int) r4, (float) r5, (float) r7)
            int r3 = r1.getPointerId(r3)
            float r4 = r28.getX()
            float r1 = r28.getY()
            if (r2 == 0) goto L_0x03c6
            r0.f3857y = r2
            android.graphics.PointF r2 = r0.f3835c
            r2.set(r4, r1)
            r0.f3846n = r3
            android.graphics.RectF r1 = r0.f3850r
            bxg r2 = r0.f3844l
            android.graphics.RectF r2 = r2.mo2874a()
            r1.set(r2)
            bzy r1 = p000.byu.f3916b
            bxm r2 = r0.f3841i
            byc r2 = (p000.byc) r2
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r2 = r2.f3888c
            android.graphics.RectF r3 = r0.f3849q
            r1.mo2916b(r2, r3)
            android.graphics.RectF r1 = r0.f3851s
            android.graphics.RectF r2 = r0.f3849q
            r1.set(r2)
            bxq r1 = r0.f3839g
            bzy r2 = p000.byy.f3920a
            r3 = 1060320051(0x3f333333, float:0.7)
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            r1.mo2887a((p000.bzy) r2, (java.lang.Object) r3)
            bxo r1 = r0.f3843k
            r1.mo2883a()
        L_0x03c6:
            return r6
        L_0x03c7:
            return r6
        L_0x03c8:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bxl.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
