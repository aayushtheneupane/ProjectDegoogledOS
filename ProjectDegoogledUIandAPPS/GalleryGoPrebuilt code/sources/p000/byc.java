package p000;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.graphics.RectF;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: byc */
/* compiled from: PG */
public final class byc implements bxq, bxm {

    /* renamed from: a */
    public final Set f3886a = new HashSet();

    /* renamed from: b */
    public final List f3887b = new ArrayList();

    /* renamed from: c */
    public final PipelineParams f3888c = new PipelineParams();

    /* renamed from: d */
    public final Set f3889d;

    /* renamed from: e */
    public boolean f3890e;

    /* renamed from: f */
    private final Map f3891f = new HashMap();

    /* renamed from: g */
    private final Set f3892g = new HashSet();

    /* renamed from: h */
    private final PipelineParams f3893h;

    /* renamed from: i */
    private final PipelineParams f3894i;

    /* renamed from: j */
    private final PipelineParams f3895j;

    /* renamed from: k */
    private final Set f3896k;

    /* renamed from: l */
    private final Set f3897l;

    /* renamed from: m */
    private final bxc f3898m;

    /* renamed from: n */
    private float f3899n;

    /* renamed from: o */
    private boolean f3900o;

    public byc(bxc bxc) {
        new PipelineParams();
        new PipelineParams();
        this.f3893h = new PipelineParams();
        this.f3894i = new PipelineParams();
        this.f3895j = new PipelineParams();
        this.f3896k = new HashSet();
        this.f3889d = new HashSet();
        this.f3897l = new HashSet();
        this.f3900o = true;
        this.f3898m = bxc;
        hvr a = cam.f3969e.iterator();
        while (a.hasNext()) {
            bzy bzy = (bzy) a.next();
            this.f3891f.put(bzy, new byb(bzy));
        }
    }

    /* renamed from: a */
    public final void mo2886a(bxp bxp) {
        this.f3897l.add(bxp);
    }

    /* renamed from: a */
    public final void mo2885a(Animator.AnimatorListener animatorListener, TimeInterpolator timeInterpolator) {
        if (this.f3890e) {
            bzy bzy = byu.f3915a;
            this.f3899n = byr.m3800b(this.f3894i).floatValue();
            cam.m3950a(this.f3888c, this.f3894i);
            m3752a(this.f3894i, true);
            bxu bxu = new bxu(this.f3894i, this.f3896k, this, new bxw(this), new bxx(this));
            bxu.setDuration(300);
            bxu.setInterpolator(timeInterpolator);
            bxu.addListener(new bxz(this, bxu));
            if (animatorListener != null) {
                bxu.addListener(animatorListener);
            }
            this.f3889d.add(bxu);
            bxu.start();
            return;
        }
        this.f3887b.add(new bya(this.f3886a, new bxv(this, animatorListener, timeInterpolator)));
        this.f3886a.clear();
    }

    /* renamed from: a */
    public final void mo2884a() {
        if (!this.f3890e) {
            this.f3887b.add(new bya(this.f3886a, new bxy(this)));
            this.f3886a.clear();
        } else if (m3752a(this.f3888c, false)) {
            byu.f3917c.mo2915a(this.f3894i, byr.m3800b(this.f3888c));
            mo2902b();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0195  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0198  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0205  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0270 A[LOOP:2: B:84:0x026a->B:86:0x0270, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x003c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean m3752a(com.google.android.apps.photosgo.editor.parameters.PipelineParams r20, boolean r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            java.util.Set r2 = r0.f3896k
            r2.clear()
            java.util.Set r2 = r0.f3892g
            bzy r3 = p000.byu.f3919e
            boolean r2 = r2.contains(r3)
            r4 = 0
            if (r2 == 0) goto L_0x0031
            java.util.Set r2 = r0.f3892g
            bzy r5 = p000.byu.f3919e
            r2.remove(r5)
            bzy r2 = p000.byu.f3919e
            byb r2 = r0.m3751a((p000.bzy) r2)
            boolean r2 = r2.mo2899a((com.google.android.apps.photosgo.editor.parameters.PipelineParams) r1)
            if (r2 == 0) goto L_0x0030
            java.util.Set r2 = r0.f3896k
            bzy r5 = p000.byu.f3919e
            r2.add(r5)
            r2 = 1
            goto L_0x0032
        L_0x0030:
        L_0x0031:
            r2 = 0
        L_0x0032:
            java.util.Set r5 = r0.f3892g
            bzy r6 = p000.byu.f3917c
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x00ee
            java.lang.Float r5 = p000.byr.m3800b(r20)
            float r5 = r5.floatValue()
            bzy r6 = p000.byu.f3917c
            byb r6 = r0.m3751a((p000.bzy) r6)
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r7 = r0.f3895j
            r6.mo2899a((com.google.android.apps.photosgo.editor.parameters.PipelineParams) r7)
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r7 = r0.f3895j
            java.lang.Float r7 = p000.byr.m3800b(r7)
            float r7 = r7.floatValue()
            if (r21 == 0) goto L_0x0065
            float r8 = r0.f3899n
            boolean r8 = p000.ihg.m13042a((float) r7, (float) r8)
            if (r8 == 0) goto L_0x0065
            goto L_0x00ee
        L_0x0065:
            if (r21 == 0) goto L_0x0068
            goto L_0x0070
        L_0x0068:
            boolean r8 = p000.ihg.m13042a((float) r7, (float) r5)
            if (r8 == 0) goto L_0x0070
            goto L_0x00ee
        L_0x0070:
            java.util.Set r8 = r0.f3892g
            bzy r9 = p000.byu.f3917c
            r8.remove(r9)
            java.lang.Float r8 = p000.byx.m3823b(r20)
            float r8 = r8.floatValue()
            float r7 = r7 - r5
            float r5 = r7 - r8
            float r9 = java.lang.Math.abs(r5)
            double r9 = (double) r9
            r11 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 <= 0) goto L_0x009b
            float r9 = java.lang.Math.signum(r5)
            float r9 = r9 + r9
            r10 = 1078530011(0x40490fdb, float:3.1415927)
            float r9 = r9 * r10
            float r5 = r5 - r9
        L_0x009b:
            float r5 = r5 + r8
            java.lang.Boolean r8 = p000.byp.m3789b(r20)
            boolean r8 = r8.booleanValue()
            if (r8 != 0) goto L_0x00a7
            goto L_0x00c9
        L_0x00a7:
            bzy r8 = p000.bzl.f3936a
            java.lang.Float r9 = p000.bzi.m3861b()
            r8.mo2915a(r1, r9)
            bzy r8 = p000.bzl.f3937b
            bzy r9 = p000.bzl.f3937b
            bzj r9 = (p000.bzj) r9
            android.graphics.PointF r9 = r9.f3934a
            r8.mo2915a(r1, r9)
            java.util.Set r8 = r0.f3896k
            bzy r9 = p000.bzl.f3936a
            r8.add(r9)
            java.util.Set r8 = r0.f3896k
            bzy r9 = p000.bzl.f3937b
            r8.add(r9)
        L_0x00c9:
            java.util.Set r8 = r0.f3896k
            bzy r9 = p000.byu.f3917c
            r8.add(r9)
            java.util.Set r8 = r0.f3896k
            bzy r9 = p000.byy.f3922c
            r8.add(r9)
            if (r21 == 0) goto L_0x00e8
            bzy r7 = p000.byy.f3922c
            java.lang.Float r5 = java.lang.Float.valueOf(r5)
            r7.mo2915a(r1, r5)
            r6.mo2899a((com.google.android.apps.photosgo.editor.parameters.PipelineParams) r1)
            r5 = 1
            goto L_0x00ef
        L_0x00e8:
            r0.mo2900a((float) r7)
            r5 = 1
            goto L_0x00ef
        L_0x00ee:
            r5 = 0
        L_0x00ef:
            r2 = r2 | r5
            java.util.Set r5 = r0.f3892g
            bzy r6 = p000.cas.f3991a
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x013f
            bzy r5 = p000.cas.f3991a
            byb r5 = r0.m3751a((p000.bzy) r5)
            boolean r5 = r5.mo2899a((com.google.android.apps.photosgo.editor.parameters.PipelineParams) r1)
            if (r5 != 0) goto L_0x0107
            goto L_0x0140
        L_0x0107:
            car r5 = p000.cap.m3957b(r20)
            car r6 = p000.car.ORIGINAL
            if (r5 == r6) goto L_0x0117
            car r6 = p000.car.COLOR_POP
            if (r5 == r6) goto L_0x0115
            r5 = 1
            goto L_0x0118
        L_0x0115:
        L_0x0117:
            r5 = 0
        L_0x0118:
            hto r6 = p000.cam.f3967c
            p000.cam.m3952a((com.google.android.apps.photosgo.editor.parameters.PipelineParams) r1, (java.util.Set) r6)
            if (r5 == 0) goto L_0x0136
            bxc r5 = r0.f3898m
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r5 = r5.getAdjustmentsAutoParams(r1)
            if (r5 != 0) goto L_0x012c
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r5 = new com.google.android.apps.photosgo.editor.parameters.PipelineParams
            r5.<init>()
        L_0x012c:
            bzy r6 = p000.byu.f3919e
            byh r7 = r1.f4837b
            r6.mo2915a(r5, r7)
            p000.cam.m3950a((com.google.android.apps.photosgo.editor.parameters.PipelineParams) r5, (com.google.android.apps.photosgo.editor.parameters.PipelineParams) r1)
        L_0x0136:
            java.util.Set r5 = r0.f3896k
            hto r6 = p000.cam.f3967c
            r5.addAll(r6)
            r5 = 1
            goto L_0x0141
        L_0x013f:
        L_0x0140:
            r5 = 0
        L_0x0141:
            r2 = r2 | r5
            java.util.Set r5 = r0.f3892g
            java.util.Iterator r5 = r5.iterator()
            r6 = 0
        L_0x0149:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x016b
            java.lang.Object r7 = r5.next()
            bzy r7 = (p000.bzy) r7
            bzy r8 = p000.byu.f3915a
            if (r7 == r8) goto L_0x0149
            byb r8 = r0.m3751a((p000.bzy) r7)
            boolean r8 = r8.mo2899a((com.google.android.apps.photosgo.editor.parameters.PipelineParams) r1)
            if (r8 == 0) goto L_0x0149
            java.util.Set r6 = r0.f3896k
            r6.add(r7)
            r6 = 1
            goto L_0x0149
        L_0x016b:
            r2 = r2 | r6
            java.util.Set r5 = r0.f3892g
            bzy r6 = p000.bzt.f3939a
            boolean r5 = r5.contains(r6)
            if (r5 != 0) goto L_0x0198
            java.util.Set r5 = r0.f3892g
            bzy r6 = p000.byo.f3908a
            boolean r5 = r5.contains(r6)
            if (r5 != 0) goto L_0x0198
            java.util.Set r5 = r0.f3892g
            bzy r6 = p000.bzh.f3926a
            boolean r5 = r5.contains(r6)
            if (r5 != 0) goto L_0x0198
            java.util.Set r5 = r0.f3892g
            bzy r6 = p000.bzh.f3928c
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x0195
            goto L_0x0198
        L_0x0195:
            r5 = 0
            goto L_0x01fa
        L_0x0198:
            bxc r5 = r0.f3898m
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r5 = r5.getAdvancedParams(r1)
            if (r5 != 0) goto L_0x01a5
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r5 = new com.google.android.apps.photosgo.editor.parameters.PipelineParams
            r5.<init>()
        L_0x01a5:
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r6 = r0.f3893h
            hto r7 = p000.cam.f3966b
            hvr r7 = r7.iterator()
        L_0x01ad:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x01ed
            java.lang.Object r8 = r7.next()
            bzy r8 = (p000.bzy) r8
            r9 = -1082130432(0xffffffffbf800000, float:-1.0)
            r10 = 1065353216(0x3f800000, float:1.0)
            java.lang.Object r11 = r8.mo2914a(r1)
            java.lang.Float r11 = (java.lang.Float) r11
            float r11 = r11.floatValue()
            java.lang.Object r12 = r8.mo2914a(r6)
            java.lang.Float r12 = (java.lang.Float) r12
            float r12 = r12.floatValue()
            float r11 = r11 - r12
            java.lang.Object r12 = r8.mo2914a(r5)
            java.lang.Float r12 = (java.lang.Float) r12
            float r12 = r12.floatValue()
            float r11 = r11 + r12
            float r10 = java.lang.Math.min(r10, r11)
            float r9 = java.lang.Math.max(r9, r10)
            java.lang.Float r9 = java.lang.Float.valueOf(r9)
            r8.mo2915a(r1, r9)
            goto L_0x01ad
        L_0x01ed:
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r6 = r0.f3893h
            p000.cam.m3950a((com.google.android.apps.photosgo.editor.parameters.PipelineParams) r5, (com.google.android.apps.photosgo.editor.parameters.PipelineParams) r6)
            java.util.Set r5 = r0.f3896k
            hto r6 = p000.cam.f3966b
            r5.addAll(r6)
            r5 = 1
        L_0x01fa:
            r2 = r2 | r5
            java.util.Set r5 = r0.f3892g
            bzy r6 = p000.byu.f3915a
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x025f
            bzy r5 = p000.byu.f3915a
            byb r5 = r0.m3751a((p000.bzy) r5)
            boolean r5 = r5.mo2899a((com.google.android.apps.photosgo.editor.parameters.PipelineParams) r1)
            if (r5 == 0) goto L_0x025c
            java.lang.Boolean r4 = p000.byp.m3789b(r20)
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0226
            bxc r4 = r0.f3898m
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r5 = r0.f3895j
            boolean r4 = r4.getCroppedZoomParams(r5)
            r21 = r2
            goto L_0x024e
        L_0x0226:
            bxc r5 = r0.f3898m
            float r6 = r1.marginLeft
            float r7 = r1.marginTop
            float r8 = r1.marginRight
            float r9 = r1.marginBottom
            float r10 = r1.cropLeft
            float r11 = r1.cropTop
            float r12 = r1.cropRight
            float r13 = r1.cropBottom
            float r14 = r1.zoomCenterX
            float r15 = r1.zoomCenterY
            float r4 = r1.zoomScale
            float r3 = r1.rotateAngle
            r21 = r2
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r2 = r0.f3895j
            r16 = r4
            r17 = r3
            r18 = r2
            boolean r4 = r5.getUncroppedZoomParams(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
        L_0x024e:
            if (r4 == 0) goto L_0x0258
            com.google.android.apps.photosgo.editor.parameters.PipelineParams r2 = r0.f3895j
            hto r3 = p000.cam.f3965a
            p000.cam.m3951a(r2, r1, r3)
            goto L_0x025a
        L_0x0258:
        L_0x025a:
            r3 = 1
            goto L_0x0262
        L_0x025c:
            r21 = r2
            goto L_0x0261
        L_0x025f:
            r21 = r2
        L_0x0261:
            r3 = 0
        L_0x0262:
            r1 = r21 | r3
            java.util.Set r2 = r0.f3889d
            java.util.Iterator r2 = r2.iterator()
        L_0x026a:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x027e
            java.lang.Object r3 = r2.next()
            bxu r3 = (p000.bxu) r3
            java.util.Set r4 = r0.f3896k
            java.util.Set r3 = r3.f3869b
            r3.removeAll(r4)
            goto L_0x026a
        L_0x027e:
            java.util.Set r2 = r0.f3892g
            r2.clear()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.byc.m3752a(com.google.android.apps.photosgo.editor.parameters.PipelineParams, boolean):boolean");
    }

    /* renamed from: a */
    private final byb m3751a(bzy bzy) {
        return (byb) ife.m12898e((Object) (byb) this.f3891f.get(bzy));
    }

    /* renamed from: b */
    public final void mo2902b() {
        if (this.f3900o) {
            for (bxp a : this.f3897l) {
                a.mo2791a();
            }
        }
    }

    /* renamed from: a */
    public final void mo2900a(float f) {
        bzy bzy = byu.f3915a;
        float floatValue = byr.m3800b(this.f3888c).floatValue();
        RectF b = byq.m3795b(this.f3888c);
        byf.m3761a(f, b);
        byu.f3916b.mo2915a(this.f3888c, b);
        bzy bzy2 = byu.f3917c;
        PipelineParams pipelineParams = this.f3888c;
        Float valueOf = Float.valueOf(floatValue + f);
        bzy2.mo2915a(pipelineParams, valueOf);
        byu.f3917c.mo2915a(this.f3894i, valueOf);
        byy.f3922c.mo2915a(this.f3888c, byx.m3822b());
        PipelineParams zoomToFitRect = this.f3898m.zoomToFitRect(this.f3888c);
        if (zoomToFitRect != null) {
            bzl.f3937b.mo2915a(this.f3888c, bzj.m3868b(zoomToFitRect));
            bzl.f3936a.mo2915a(this.f3888c, bzi.m3862b(zoomToFitRect));
        }
        double d = (double) (f + f);
        Double.isNaN(d);
        if (Math.abs(Math.round(d / 3.141592653589793d) % 2) == 1) {
            byu.f3919e.mo2915a(this.f3888c, this.f3888c.f4837b.mo2906a());
        }
        for (bxu bxu : this.f3889d) {
            if (bxu.f3869b.contains(byu.f3916b)) {
                RectF b2 = byq.m3795b(bxu.f3868a);
                byf.m3761a(f, b2);
                byu.f3916b.mo2915a(bxu.f3868a, b2);
            }
        }
        if (!this.f3889d.isEmpty()) {
            mo2902b();
        }
    }

    /* renamed from: b */
    public final void mo2888b(bxp bxp) {
        this.f3897l.remove(bxp);
    }

    /* renamed from: a */
    public final void mo2887a(bzy bzy, Object obj) {
        if (!this.f3890e) {
            byb byb = new byb(bzy);
            byb.mo2898a(obj);
            this.f3886a.add(byb);
            return;
        }
        m3751a(bzy).mo2898a(obj);
        this.f3892g.add(bzy);
    }

    /* renamed from: a */
    public final void mo2901a(byb byb) {
        mo2887a(byb.f3884a, ife.m12898e(byb.f3885b));
    }
}
