package p000;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* renamed from: yq */
/* compiled from: PG */
public final class C0671yq {

    /* renamed from: a */
    private static final PorterDuff.Mode f16404a = PorterDuff.Mode.SRC_IN;

    /* renamed from: b */
    private static C0671yq f16405b;

    /* renamed from: h */
    private static final C0297ku f16406h = new C0297ku();

    /* renamed from: c */
    private WeakHashMap f16407c;

    /* renamed from: d */
    private final WeakHashMap f16408d = new WeakHashMap(0);

    /* renamed from: e */
    private TypedValue f16409e;

    /* renamed from: f */
    private boolean f16410f;

    /* renamed from: g */
    private C0670yp f16411g;

    /* renamed from: a */
    private final synchronized void m16161a(Context context, long j, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            C0296kt ktVar = (C0296kt) this.f16408d.get(context);
            if (ktVar == null) {
                ktVar = new C0296kt();
                this.f16408d.put(context, ktVar);
            }
            ktVar.mo9231a(j, new WeakReference(constantState));
        }
    }

    /* renamed from: a */
    public static synchronized C0671yq m16160a() {
        C0671yq yqVar;
        synchronized (C0671yq.class) {
            if (f16405b == null) {
                f16405b = new C0671yq();
                int i = Build.VERSION.SDK_INT;
            }
            yqVar = f16405b;
        }
        return yqVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        return null;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized android.graphics.drawable.Drawable m16159a(android.content.Context r4, long r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.WeakHashMap r0 = r3.f16408d     // Catch:{ all -> 0x0046 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0046 }
            kt r0 = (p000.C0296kt) r0     // Catch:{ all -> 0x0046 }
            r1 = 0
            if (r0 == 0) goto L_0x0044
            java.lang.Object r2 = r0.mo9229a((long) r5)     // Catch:{ all -> 0x0046 }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x0046 }
            if (r2 == 0) goto L_0x0042
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0046 }
            android.graphics.drawable.Drawable$ConstantState r2 = (android.graphics.drawable.Drawable.ConstantState) r2     // Catch:{ all -> 0x0046 }
            if (r2 != 0) goto L_0x0038
            long[] r4 = r0.f15155c     // Catch:{ all -> 0x0046 }
            int r2 = r0.f15157e     // Catch:{ all -> 0x0046 }
            int r4 = p000.C0294kr.m14538a((long[]) r4, (int) r2, (long) r5)     // Catch:{ all -> 0x0046 }
            if (r4 < 0) goto L_0x0042
            java.lang.Object[] r5 = r0.f15156d     // Catch:{ all -> 0x0046 }
            r5 = r5[r4]     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = p000.C0296kt.f15153a     // Catch:{ all -> 0x0046 }
            if (r5 == r6) goto L_0x0042
            java.lang.Object[] r5 = r0.f15156d     // Catch:{ all -> 0x0046 }
            java.lang.Object r6 = p000.C0296kt.f15153a     // Catch:{ all -> 0x0046 }
            r5[r4] = r6     // Catch:{ all -> 0x0046 }
            r4 = 1
            r0.f15154b = r4     // Catch:{ all -> 0x0046 }
            goto L_0x0042
        L_0x0038:
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ all -> 0x0046 }
            android.graphics.drawable.Drawable r4 = r2.newDrawable(r4)     // Catch:{ all -> 0x0046 }
            monitor-exit(r3)
            return r4
        L_0x0042:
            monitor-exit(r3)
            return r1
        L_0x0044:
            monitor-exit(r3)
            return r1
        L_0x0046:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0671yq.m16159a(android.content.Context, long):android.graphics.drawable.Drawable");
    }

    /* renamed from: a */
    public final synchronized Drawable mo10663a(Context context, int i) {
        return mo10664a(context, i, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: android.graphics.PorterDuff$Mode} */
    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v1, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: type inference failed for: r6v2 */
    /* JADX WARNING: type inference failed for: r6v5 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01be  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.graphics.drawable.Drawable mo10664a(android.content.Context r12, int r13, boolean r14) {
        /*
            r11 = this;
            monitor-enter(r11)
            boolean r0 = r11.f16410f     // Catch:{ all -> 0x01c3 }
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0032
            r11.f16410f = r2     // Catch:{ all -> 0x01c3 }
            r0 = 2131230816(0x7f080060, float:1.8077695E38)
            android.graphics.drawable.Drawable r0 = r11.mo10663a((android.content.Context) r12, (int) r0)     // Catch:{ all -> 0x01c3 }
            if (r0 == 0) goto L_0x0027
            boolean r3 = r0 instanceof p000.agw     // Catch:{ all -> 0x01c3 }
            if (r3 != 0) goto L_0x0032
            java.lang.String r3 = "android.graphics.drawable.VectorDrawable"
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x01c3 }
            java.lang.String r0 = r0.getName()     // Catch:{ all -> 0x01c3 }
            boolean r0 = r3.equals(r0)     // Catch:{ all -> 0x01c3 }
            if (r0 == 0) goto L_0x0027
            goto L_0x0032
        L_0x0027:
            r11.f16410f = r1     // Catch:{ all -> 0x01c3 }
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ all -> 0x01c3 }
            java.lang.String r13 = "This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat."
            r12.<init>(r13)     // Catch:{ all -> 0x01c3 }
            throw r12     // Catch:{ all -> 0x01c3 }
        L_0x0032:
            android.util.TypedValue r0 = r11.f16409e     // Catch:{ all -> 0x01c3 }
            if (r0 == 0) goto L_0x0037
            goto L_0x003e
        L_0x0037:
            android.util.TypedValue r0 = new android.util.TypedValue     // Catch:{ all -> 0x01c3 }
            r0.<init>()     // Catch:{ all -> 0x01c3 }
            r11.f16409e = r0     // Catch:{ all -> 0x01c3 }
        L_0x003e:
            android.util.TypedValue r0 = r11.f16409e     // Catch:{ all -> 0x01c3 }
            android.content.res.Resources r3 = r12.getResources()     // Catch:{ all -> 0x01c3 }
            r3.getValue(r13, r0, r2)     // Catch:{ all -> 0x01c3 }
            int r3 = r0.assetCookie     // Catch:{ all -> 0x01c3 }
            long r3 = (long) r3     // Catch:{ all -> 0x01c3 }
            r5 = 32
            long r3 = r3 << r5
            int r5 = r0.data     // Catch:{ all -> 0x01c3 }
            long r5 = (long) r5     // Catch:{ all -> 0x01c3 }
            long r3 = r3 | r5
            android.graphics.drawable.Drawable r5 = r11.m16159a((android.content.Context) r12, (long) r3)     // Catch:{ all -> 0x01c3 }
            r6 = 0
            if (r5 != 0) goto L_0x0088
            yp r5 = r11.f16411g     // Catch:{ all -> 0x01c3 }
            if (r5 == 0) goto L_0x007d
            r5 = 2131230747(0x7f08001b, float:1.8077556E38)
            if (r13 != r5) goto L_0x007c
            android.graphics.drawable.LayerDrawable r5 = new android.graphics.drawable.LayerDrawable     // Catch:{ all -> 0x01c3 }
            r7 = 2
            android.graphics.drawable.Drawable[] r7 = new android.graphics.drawable.Drawable[r7]     // Catch:{ all -> 0x01c3 }
            r8 = 2131230746(0x7f08001a, float:1.8077553E38)
            android.graphics.drawable.Drawable r8 = r11.mo10663a((android.content.Context) r12, (int) r8)     // Catch:{ all -> 0x01c3 }
            r7[r1] = r8     // Catch:{ all -> 0x01c3 }
            r8 = 2131230748(0x7f08001c, float:1.8077558E38)
            android.graphics.drawable.Drawable r8 = r11.mo10663a((android.content.Context) r12, (int) r8)     // Catch:{ all -> 0x01c3 }
            r7[r2] = r8     // Catch:{ all -> 0x01c3 }
            r5.<init>(r7)     // Catch:{ all -> 0x01c3 }
            goto L_0x007e
        L_0x007c:
        L_0x007d:
            r5 = r6
        L_0x007e:
            if (r5 == 0) goto L_0x0088
            int r0 = r0.changingConfigurations     // Catch:{ all -> 0x01c3 }
            r5.setChangingConfigurations(r0)     // Catch:{ all -> 0x01c3 }
            r11.m16161a((android.content.Context) r12, (long) r3, (android.graphics.drawable.Drawable) r5)     // Catch:{ all -> 0x01c3 }
        L_0x0088:
            if (r5 != 0) goto L_0x008e
            android.graphics.drawable.Drawable r5 = p000.C0071co.m4660a((android.content.Context) r12, (int) r13)     // Catch:{ all -> 0x01c3 }
        L_0x008e:
            if (r5 == 0) goto L_0x01ba
            android.content.res.ColorStateList r0 = r11.mo10667b(r12, r13)     // Catch:{ all -> 0x01c3 }
            if (r0 != 0) goto L_0x0194
            yp r0 = r11.f16411g     // Catch:{ all -> 0x01c3 }
            r3 = 2130968750(0x7f0400ae, float:1.7546162E38)
            r4 = 2130968752(0x7f0400b0, float:1.7546167E38)
            if (r0 == 0) goto L_0x0116
            r7 = 2131230797(0x7f08004d, float:1.8077657E38)
            r8 = 16908301(0x102000d, float:2.3877265E-38)
            r9 = 16908303(0x102000f, float:2.387727E-38)
            r10 = 16908288(0x1020000, float:2.387723E-38)
            if (r13 != r7) goto L_0x00d9
            r13 = r5
            android.graphics.drawable.LayerDrawable r13 = (android.graphics.drawable.LayerDrawable) r13     // Catch:{ all -> 0x01c3 }
            android.graphics.drawable.Drawable r14 = r13.findDrawableByLayerId(r10)     // Catch:{ all -> 0x01c3 }
            int r0 = p000.C0678yx.m16183a(r12, r4)     // Catch:{ all -> 0x01c3 }
            android.graphics.PorterDuff$Mode r1 = p000.C0529tj.f15934a     // Catch:{ all -> 0x01c3 }
            p000.C0528ti.m15435a(r14, r0, r1)     // Catch:{ all -> 0x01c3 }
            android.graphics.drawable.Drawable r14 = r13.findDrawableByLayerId(r9)     // Catch:{ all -> 0x01c3 }
            int r0 = p000.C0678yx.m16183a(r12, r4)     // Catch:{ all -> 0x01c3 }
            android.graphics.PorterDuff$Mode r1 = p000.C0529tj.f15934a     // Catch:{ all -> 0x01c3 }
            p000.C0528ti.m15435a(r14, r0, r1)     // Catch:{ all -> 0x01c3 }
            android.graphics.drawable.Drawable r13 = r13.findDrawableByLayerId(r8)     // Catch:{ all -> 0x01c3 }
            int r12 = p000.C0678yx.m16183a(r12, r3)     // Catch:{ all -> 0x01c3 }
            android.graphics.PorterDuff$Mode r14 = p000.C0529tj.f15934a     // Catch:{ all -> 0x01c3 }
            p000.C0528ti.m15435a(r13, r12, r14)     // Catch:{ all -> 0x01c3 }
            goto L_0x01ba
        L_0x00d9:
            r7 = 2131230788(0x7f080044, float:1.8077639E38)
            if (r13 == r7) goto L_0x00e9
            r7 = 2131230787(0x7f080043, float:1.8077637E38)
            if (r13 == r7) goto L_0x00e9
            r7 = 2131230789(0x7f080045, float:1.807764E38)
            if (r13 == r7) goto L_0x00e9
            goto L_0x0116
        L_0x00e9:
            r13 = r5
            android.graphics.drawable.LayerDrawable r13 = (android.graphics.drawable.LayerDrawable) r13     // Catch:{ all -> 0x01c3 }
            android.graphics.drawable.Drawable r14 = r13.findDrawableByLayerId(r10)     // Catch:{ all -> 0x01c3 }
            int r0 = p000.C0678yx.m16185c(r12, r4)     // Catch:{ all -> 0x01c3 }
            android.graphics.PorterDuff$Mode r1 = p000.C0529tj.f15934a     // Catch:{ all -> 0x01c3 }
            p000.C0528ti.m15435a(r14, r0, r1)     // Catch:{ all -> 0x01c3 }
            android.graphics.drawable.Drawable r14 = r13.findDrawableByLayerId(r9)     // Catch:{ all -> 0x01c3 }
            int r0 = p000.C0678yx.m16183a(r12, r3)     // Catch:{ all -> 0x01c3 }
            android.graphics.PorterDuff$Mode r1 = p000.C0529tj.f15934a     // Catch:{ all -> 0x01c3 }
            p000.C0528ti.m15435a(r14, r0, r1)     // Catch:{ all -> 0x01c3 }
            android.graphics.drawable.Drawable r13 = r13.findDrawableByLayerId(r8)     // Catch:{ all -> 0x01c3 }
            int r12 = p000.C0678yx.m16183a(r12, r3)     // Catch:{ all -> 0x01c3 }
            android.graphics.PorterDuff$Mode r14 = p000.C0529tj.f15934a     // Catch:{ all -> 0x01c3 }
            p000.C0528ti.m15435a(r13, r12, r14)     // Catch:{ all -> 0x01c3 }
            goto L_0x01bb
        L_0x0116:
            if (r0 == 0) goto L_0x0190
            android.graphics.PorterDuff$Mode r7 = p000.C0529tj.f15934a     // Catch:{ all -> 0x01c3 }
            r8 = r0
            ti r8 = (p000.C0528ti) r8     // Catch:{ all -> 0x01c3 }
            int[] r8 = r8.f15928a     // Catch:{ all -> 0x01c3 }
            boolean r8 = p000.C0528ti.m15436a((int[]) r8, (int) r13)     // Catch:{ all -> 0x01c3 }
            r9 = 16842801(0x1010031, float:2.3693695E-38)
            r10 = -1
            if (r8 == 0) goto L_0x012f
            r13 = -1
            r1 = 1
            r9 = 2130968752(0x7f0400b0, float:1.7546167E38)
            goto L_0x016f
        L_0x012f:
            r4 = r0
            ti r4 = (p000.C0528ti) r4     // Catch:{ all -> 0x01c3 }
            int[] r4 = r4.f15930c     // Catch:{ all -> 0x01c3 }
            boolean r4 = p000.C0528ti.m15436a((int[]) r4, (int) r13)     // Catch:{ all -> 0x01c3 }
            if (r4 != 0) goto L_0x016a
            ti r0 = (p000.C0528ti) r0     // Catch:{ all -> 0x01c3 }
            int[] r0 = r0.f15931d     // Catch:{ all -> 0x01c3 }
            boolean r0 = p000.C0528ti.m15436a((int[]) r0, (int) r13)     // Catch:{ all -> 0x01c3 }
            if (r0 == 0) goto L_0x014a
            android.graphics.PorterDuff$Mode r7 = android.graphics.PorterDuff.Mode.MULTIPLY     // Catch:{ all -> 0x01c3 }
            r13 = -1
            r1 = 1
            goto L_0x016f
        L_0x014a:
            r0 = 2131230774(0x7f080036, float:1.807761E38)
            if (r13 != r0) goto L_0x015f
            r13 = 1109603123(0x42233333, float:40.8)
            int r13 = java.lang.Math.round(r13)     // Catch:{ all -> 0x01c3 }
            r1 = 16842800(0x1010030, float:2.3693693E-38)
            r1 = 1
            r9 = 16842800(0x1010030, float:2.3693693E-38)
            goto L_0x016f
        L_0x015f:
            r0 = 2131230750(0x7f08001e, float:1.8077562E38)
            if (r13 != r0) goto L_0x0167
            r13 = -1
            r1 = 1
            goto L_0x016f
        L_0x0167:
            r13 = -1
            r9 = 0
            goto L_0x016f
        L_0x016a:
            r13 = -1
            r1 = 1
            r9 = 2130968750(0x7f0400ae, float:1.7546162E38)
        L_0x016f:
            if (r1 != 0) goto L_0x0172
            goto L_0x0190
        L_0x0172:
            boolean r14 = p000.C0579vf.m15606b(r5)     // Catch:{ all -> 0x01c3 }
            if (r14 == 0) goto L_0x017d
            android.graphics.drawable.Drawable r14 = r5.mutate()     // Catch:{ all -> 0x01c3 }
            goto L_0x017e
        L_0x017d:
            r14 = r5
        L_0x017e:
            int r12 = p000.C0678yx.m16183a(r12, r9)     // Catch:{ all -> 0x01c3 }
            android.graphics.PorterDuffColorFilter r12 = p000.C0529tj.m15437a((int) r12, (android.graphics.PorterDuff.Mode) r7)     // Catch:{ all -> 0x01c3 }
            r14.setColorFilter(r12)     // Catch:{ all -> 0x01c3 }
            if (r13 == r10) goto L_0x01ba
            r14.setAlpha(r13)     // Catch:{ all -> 0x01c3 }
            goto L_0x01bb
        L_0x0190:
            if (r14 != 0) goto L_0x0193
            goto L_0x01ba
        L_0x0193:
            goto L_0x01bc
        L_0x0194:
            boolean r12 = p000.C0579vf.m15606b(r5)     // Catch:{ all -> 0x01c3 }
            if (r12 == 0) goto L_0x019f
            android.graphics.drawable.Drawable r5 = r5.mutate()     // Catch:{ all -> 0x01c3 }
            goto L_0x01a0
        L_0x019f:
        L_0x01a0:
            int r12 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01c3 }
            p000.C0257jh.m14475a((android.graphics.drawable.Drawable) r5, (android.content.res.ColorStateList) r0)     // Catch:{ all -> 0x01c3 }
            yp r12 = r11.f16411g     // Catch:{ all -> 0x01c3 }
            if (r12 == 0) goto L_0x01b2
            r12 = 2131230800(0x7f080050, float:1.8077663E38)
            if (r13 != r12) goto L_0x01b1
            android.graphics.PorterDuff$Mode r6 = android.graphics.PorterDuff.Mode.MULTIPLY     // Catch:{ all -> 0x01c3 }
            goto L_0x01b2
        L_0x01b1:
        L_0x01b2:
            if (r6 != 0) goto L_0x01b5
            goto L_0x01ba
        L_0x01b5:
            p000.C0257jh.m14476a((android.graphics.drawable.Drawable) r5, (android.graphics.PorterDuff.Mode) r6)     // Catch:{ all -> 0x01c3 }
            goto L_0x01bb
        L_0x01ba:
        L_0x01bb:
            r6 = r5
        L_0x01bc:
            if (r6 == 0) goto L_0x01c1
            p000.C0579vf.m15605a()     // Catch:{ all -> 0x01c3 }
        L_0x01c1:
            monitor-exit(r11)
            return r6
        L_0x01c3:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0671yq.mo10664a(android.content.Context, int, boolean):android.graphics.drawable.Drawable");
    }

    /* renamed from: a */
    public static synchronized PorterDuffColorFilter m16158a(int i, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        synchronized (C0671yq.class) {
            porterDuffColorFilter = (PorterDuffColorFilter) f16406h.mo9237a((Object) Integer.valueOf(C0297ku.m14551a(i, mode)));
            if (porterDuffColorFilter == null) {
                porterDuffColorFilter = new PorterDuffColorFilter(i, mode);
                PorterDuffColorFilter porterDuffColorFilter2 = (PorterDuffColorFilter) f16406h.mo9238a((Object) Integer.valueOf(C0297ku.m14551a(i, mode)), (Object) porterDuffColorFilter);
            }
        }
        return porterDuffColorFilter;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0195, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x019a, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.content.res.ColorStateList mo10667b(android.content.Context r10, int r11) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.util.WeakHashMap r0 = r9.f16407c     // Catch:{ all -> 0x019b }
            r1 = 0
            if (r0 != 0) goto L_0x0008
        L_0x0006:
            r0 = r1
            goto L_0x0018
        L_0x0008:
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x019b }
            lg r0 = (p000.C0310lg) r0     // Catch:{ all -> 0x019b }
            if (r0 == 0) goto L_0x0017
            java.lang.Object r0 = r0.mo9335a(r11)     // Catch:{ all -> 0x019b }
            android.content.res.ColorStateList r0 = (android.content.res.ColorStateList) r0     // Catch:{ all -> 0x019b }
            goto L_0x0018
        L_0x0017:
            goto L_0x0006
        L_0x0018:
            if (r0 != 0) goto L_0x0198
            yp r0 = r9.f16411g     // Catch:{ all -> 0x019b }
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0126
            r4 = 2131230751(0x7f08001f, float:1.8077564E38)
            if (r11 != r4) goto L_0x002e
            r0 = 2131099668(0x7f060014, float:1.7811696E38)
            android.content.res.ColorStateList r1 = p000.C0436py.m15104a(r10, r0)     // Catch:{ all -> 0x019b }
            goto L_0x0127
        L_0x002e:
            r4 = 2131230801(0x7f080051, float:1.8077665E38)
            if (r11 != r4) goto L_0x003c
            r0 = 2131099671(0x7f060017, float:1.7811702E38)
            android.content.res.ColorStateList r1 = p000.C0436py.m15104a(r10, r0)     // Catch:{ all -> 0x019b }
            goto L_0x0128
        L_0x003c:
            r4 = 2131230800(0x7f080050, float:1.8077663E38)
            if (r11 != r4) goto L_0x009f
            r0 = 3
            int[][] r1 = new int[r0][]     // Catch:{ all -> 0x019b }
            int[] r0 = new int[r0]     // Catch:{ all -> 0x019b }
            r4 = 2130968785(0x7f0400d1, float:1.7546233E38)
            android.content.res.ColorStateList r5 = p000.C0678yx.m16184b(r10, r4)     // Catch:{ all -> 0x019b }
            r6 = 2130968750(0x7f0400ae, float:1.7546162E38)
            r7 = 2
            if (r5 == 0) goto L_0x0078
            boolean r8 = r5.isStateful()     // Catch:{ all -> 0x019b }
            if (r8 == 0) goto L_0x0078
            int[] r4 = p000.C0678yx.f16424a     // Catch:{ all -> 0x019b }
            r1[r3] = r4     // Catch:{ all -> 0x019b }
            int r4 = r5.getColorForState(r4, r3)     // Catch:{ all -> 0x019b }
            r0[r3] = r4     // Catch:{ all -> 0x019b }
            int[] r4 = p000.C0678yx.f16427d     // Catch:{ all -> 0x019b }
            r1[r2] = r4     // Catch:{ all -> 0x019b }
            int r4 = p000.C0678yx.m16183a(r10, r6)     // Catch:{ all -> 0x019b }
            r0[r2] = r4     // Catch:{ all -> 0x019b }
            int[] r4 = p000.C0678yx.f16428e     // Catch:{ all -> 0x019b }
            r1[r7] = r4     // Catch:{ all -> 0x019b }
            int r4 = r5.getDefaultColor()     // Catch:{ all -> 0x019b }
            r0[r7] = r4     // Catch:{ all -> 0x019b }
            goto L_0x0096
        L_0x0078:
            int[] r5 = p000.C0678yx.f16424a     // Catch:{ all -> 0x019b }
            r1[r3] = r5     // Catch:{ all -> 0x019b }
            int r5 = p000.C0678yx.m16185c(r10, r4)     // Catch:{ all -> 0x019b }
            r0[r3] = r5     // Catch:{ all -> 0x019b }
            int[] r5 = p000.C0678yx.f16427d     // Catch:{ all -> 0x019b }
            r1[r2] = r5     // Catch:{ all -> 0x019b }
            int r5 = p000.C0678yx.m16183a(r10, r6)     // Catch:{ all -> 0x019b }
            r0[r2] = r5     // Catch:{ all -> 0x019b }
            int[] r5 = p000.C0678yx.f16428e     // Catch:{ all -> 0x019b }
            r1[r7] = r5     // Catch:{ all -> 0x019b }
            int r4 = p000.C0678yx.m16183a(r10, r4)     // Catch:{ all -> 0x019b }
            r0[r7] = r4     // Catch:{ all -> 0x019b }
        L_0x0096:
            android.content.res.ColorStateList r4 = new android.content.res.ColorStateList     // Catch:{ all -> 0x019b }
            r4.<init>(r1, r0)     // Catch:{ all -> 0x019b }
            r1 = r4
            goto L_0x0128
        L_0x009f:
            r4 = 2131230739(0x7f080013, float:1.807754E38)
            if (r11 != r4) goto L_0x00b1
            r0 = 2130968749(0x7f0400ad, float:1.754616E38)
            int r0 = p000.C0678yx.m16183a(r10, r0)     // Catch:{ all -> 0x019b }
            android.content.res.ColorStateList r1 = p000.C0528ti.m15434a((android.content.Context) r10, (int) r0)     // Catch:{ all -> 0x019b }
            goto L_0x0128
        L_0x00b1:
            r4 = 2131230733(0x7f08000d, float:1.8077527E38)
            if (r11 != r4) goto L_0x00bc
            android.content.res.ColorStateList r1 = p000.C0528ti.m15434a((android.content.Context) r10, (int) r3)     // Catch:{ all -> 0x019b }
            goto L_0x0128
        L_0x00bc:
            r4 = 2131230738(0x7f080012, float:1.8077537E38)
            if (r11 != r4) goto L_0x00cd
            r0 = 2130968747(0x7f0400ab, float:1.7546156E38)
            int r0 = p000.C0678yx.m16183a(r10, r0)     // Catch:{ all -> 0x019b }
            android.content.res.ColorStateList r1 = p000.C0528ti.m15434a((android.content.Context) r10, (int) r0)     // Catch:{ all -> 0x019b }
            goto L_0x0128
        L_0x00cd:
            r4 = 2131230798(0x7f08004e, float:1.8077659E38)
            if (r11 != r4) goto L_0x00d3
            goto L_0x011e
        L_0x00d3:
            r4 = 2131230799(0x7f08004f, float:1.807766E38)
            if (r11 == r4) goto L_0x011e
            r4 = r0
            ti r4 = (p000.C0528ti) r4     // Catch:{ all -> 0x019b }
            int[] r4 = r4.f15929b     // Catch:{ all -> 0x019b }
            boolean r4 = p000.C0528ti.m15436a((int[]) r4, (int) r11)     // Catch:{ all -> 0x019b }
            if (r4 == 0) goto L_0x00eb
            r0 = 2130968752(0x7f0400b0, float:1.7546167E38)
            android.content.res.ColorStateList r1 = p000.C0678yx.m16184b(r10, r0)     // Catch:{ all -> 0x019b }
            goto L_0x0128
        L_0x00eb:
            r4 = r0
            ti r4 = (p000.C0528ti) r4     // Catch:{ all -> 0x019b }
            int[] r4 = r4.f15932e     // Catch:{ all -> 0x019b }
            boolean r4 = p000.C0528ti.m15436a((int[]) r4, (int) r11)     // Catch:{ all -> 0x019b }
            if (r4 == 0) goto L_0x00fe
            r0 = 2131099667(0x7f060013, float:1.7811694E38)
            android.content.res.ColorStateList r1 = p000.C0436py.m15104a(r10, r0)     // Catch:{ all -> 0x019b }
            goto L_0x0128
        L_0x00fe:
            ti r0 = (p000.C0528ti) r0     // Catch:{ all -> 0x019b }
            int[] r0 = r0.f15933f     // Catch:{ all -> 0x019b }
            boolean r0 = p000.C0528ti.m15436a((int[]) r0, (int) r11)     // Catch:{ all -> 0x019b }
            if (r0 == 0) goto L_0x0110
            r0 = 2131099666(0x7f060012, float:1.7811692E38)
            android.content.res.ColorStateList r1 = p000.C0436py.m15104a(r10, r0)     // Catch:{ all -> 0x019b }
            goto L_0x0128
        L_0x0110:
            r0 = 2131230795(0x7f08004b, float:1.8077653E38)
            if (r11 == r0) goto L_0x0116
            goto L_0x0127
        L_0x0116:
            r0 = 2131099669(0x7f060015, float:1.7811698E38)
            android.content.res.ColorStateList r1 = p000.C0436py.m15104a(r10, r0)     // Catch:{ all -> 0x019b }
            goto L_0x0128
        L_0x011e:
            r0 = 2131099670(0x7f060016, float:1.78117E38)
            android.content.res.ColorStateList r1 = p000.C0436py.m15104a(r10, r0)     // Catch:{ all -> 0x019b }
            goto L_0x0128
        L_0x0126:
        L_0x0127:
        L_0x0128:
            if (r1 == 0) goto L_0x0196
            java.util.WeakHashMap r0 = r9.f16407c     // Catch:{ all -> 0x019b }
            if (r0 == 0) goto L_0x012f
            goto L_0x0136
        L_0x012f:
            java.util.WeakHashMap r0 = new java.util.WeakHashMap     // Catch:{ all -> 0x019b }
            r0.<init>()     // Catch:{ all -> 0x019b }
            r9.f16407c = r0     // Catch:{ all -> 0x019b }
        L_0x0136:
            java.util.WeakHashMap r0 = r9.f16407c     // Catch:{ all -> 0x019b }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x019b }
            lg r0 = (p000.C0310lg) r0     // Catch:{ all -> 0x019b }
            if (r0 != 0) goto L_0x014a
            lg r0 = new lg     // Catch:{ all -> 0x019b }
            r0.<init>()     // Catch:{ all -> 0x019b }
            java.util.WeakHashMap r4 = r9.f16407c     // Catch:{ all -> 0x019b }
            r4.put(r10, r0)     // Catch:{ all -> 0x019b }
        L_0x014a:
            int r10 = r0.f15199d     // Catch:{ all -> 0x019b }
            if (r10 == 0) goto L_0x015b
            int[] r4 = r0.f15197b     // Catch:{ all -> 0x019b }
            int r5 = r10 + -1
            r4 = r4[r5]     // Catch:{ all -> 0x019b }
            if (r11 <= r4) goto L_0x0157
            goto L_0x015b
        L_0x0157:
            r0.mo9337a(r11, r1)     // Catch:{ all -> 0x019b }
            goto L_0x0194
        L_0x015b:
            boolean r4 = r0.f15196a     // Catch:{ all -> 0x019b }
            if (r4 != 0) goto L_0x0160
            goto L_0x0168
        L_0x0160:
            int[] r4 = r0.f15197b     // Catch:{ all -> 0x019b }
            int r4 = r4.length     // Catch:{ all -> 0x019b }
            if (r10 < r4) goto L_0x0168
            r0.mo9336a()     // Catch:{ all -> 0x019b }
        L_0x0168:
            int r10 = r0.f15199d     // Catch:{ all -> 0x019b }
            int[] r4 = r0.f15197b     // Catch:{ all -> 0x019b }
            int r4 = r4.length     // Catch:{ all -> 0x019b }
            if (r10 < r4) goto L_0x0189
            int r4 = r10 + 1
            int r4 = p000.C0294kr.m14536a(r4)     // Catch:{ all -> 0x019b }
            int[] r5 = new int[r4]     // Catch:{ all -> 0x019b }
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x019b }
            int[] r6 = r0.f15197b     // Catch:{ all -> 0x019b }
            int r7 = r6.length     // Catch:{ all -> 0x019b }
            java.lang.System.arraycopy(r6, r3, r5, r3, r7)     // Catch:{ all -> 0x019b }
            java.lang.Object[] r6 = r0.f15198c     // Catch:{ all -> 0x019b }
            int r7 = r6.length     // Catch:{ all -> 0x019b }
            java.lang.System.arraycopy(r6, r3, r4, r3, r7)     // Catch:{ all -> 0x019b }
            r0.f15197b = r5     // Catch:{ all -> 0x019b }
            r0.f15198c = r4     // Catch:{ all -> 0x019b }
        L_0x0189:
            int[] r3 = r0.f15197b     // Catch:{ all -> 0x019b }
            r3[r10] = r11     // Catch:{ all -> 0x019b }
            java.lang.Object[] r11 = r0.f15198c     // Catch:{ all -> 0x019b }
            r11[r10] = r1     // Catch:{ all -> 0x019b }
            int r10 = r10 + r2
            r0.f15199d = r10     // Catch:{ all -> 0x019b }
        L_0x0194:
            monitor-exit(r9)
            return r1
        L_0x0196:
            r0 = r1
            goto L_0x0199
        L_0x0198:
        L_0x0199:
            monitor-exit(r9)
            return r0
        L_0x019b:
            r10 = move-exception
            monitor-exit(r9)
            goto L_0x019f
        L_0x019e:
            throw r10
        L_0x019f:
            goto L_0x019e
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0671yq.mo10667b(android.content.Context, int):android.content.res.ColorStateList");
    }

    /* renamed from: a */
    public final synchronized void mo10665a(Context context) {
        C0296kt ktVar = (C0296kt) this.f16408d.get(context);
        if (ktVar != null) {
            ktVar.mo9234c();
        }
    }

    /* renamed from: a */
    public final synchronized void mo10666a(C0670yp ypVar) {
        this.f16411g = ypVar;
    }

    /* renamed from: a */
    static void m16162a(Drawable drawable, C0682za zaVar, int[] iArr) {
        ColorStateList colorStateList;
        if (!C0579vf.m15606b(drawable) || drawable.mutate() == drawable) {
            PorterDuffColorFilter porterDuffColorFilter = null;
            if (zaVar.f16434d) {
                colorStateList = zaVar.f16431a;
            } else if (!zaVar.f16433c) {
                drawable.clearColorFilter();
                int i = Build.VERSION.SDK_INT;
            } else {
                colorStateList = null;
            }
            PorterDuff.Mode mode = zaVar.f16433c ? zaVar.f16432b : f16404a;
            if (!(colorStateList == null || mode == null)) {
                porterDuffColorFilter = m16158a(colorStateList.getColorForState(iArr, 0), mode);
            }
            drawable.setColorFilter(porterDuffColorFilter);
            int i2 = Build.VERSION.SDK_INT;
        }
    }
}
