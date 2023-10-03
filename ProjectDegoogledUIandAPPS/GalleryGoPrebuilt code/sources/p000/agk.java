package p000;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: agk */
/* compiled from: PG */
public class agk extends afl {

    /* renamed from: o */
    private static final String[] f389o = {"android:visibility:visibility", "android:visibility:parent"};

    /* renamed from: n */
    public int f390n = 3;

    /* renamed from: a */
    public Animator mo276a(View view, afu afu) {
        throw null;
    }

    /* renamed from: a */
    public void mo270a(afu afu) {
        throw null;
    }

    /* renamed from: a */
    public final String[] mo271a() {
        return f389o;
    }

    /* renamed from: b */
    public Animator mo277b(View view, afu afu) {
        throw null;
    }

    /* renamed from: b */
    public final void mo272b(afu afu) {
        m448d(afu);
    }

    /* renamed from: d */
    public static final void m448d(afu afu) {
        afu.f355a.put("android:visibility:visibility", Integer.valueOf(afu.f356b.getVisibility()));
        afu.f355a.put("android:visibility:parent", afu.f356b.getParent());
        int[] iArr = new int[2];
        afu.f356b.getLocationOnScreen(iArr);
        afu.f355a.put("android:visibility:screenLocation", iArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x013a, code lost:
        if (m447b(mo310b(r13, false), mo299a(r13, false)).f383a == false) goto L_0x013c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005d  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.animation.Animator mo269a(android.view.ViewGroup r13, p000.afu r14, p000.afu r15) {
        /*
            r12 = this;
            agj r0 = m447b((p000.afu) r14, (p000.afu) r15)
            boolean r1 = r0.f383a
            r2 = 0
            if (r1 == 0) goto L_0x0143
            android.view.ViewGroup r1 = r0.f387e
            if (r1 != 0) goto L_0x0011
            android.view.ViewGroup r1 = r0.f388f
            if (r1 == 0) goto L_0x0143
        L_0x0011:
            boolean r1 = r0.f384b
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L_0x011b
            int r0 = r0.f386d
            int r1 = r12.f390n
            r5 = 2
            r1 = r1 & r5
            if (r1 == r5) goto L_0x0021
        L_0x001f:
            goto L_0x011a
        L_0x0021:
            if (r14 == 0) goto L_0x001f
            android.view.View r1 = r14.f356b
            if (r15 == 0) goto L_0x002a
            android.view.View r15 = r15.f356b
            goto L_0x002c
        L_0x002a:
            r15 = r2
        L_0x002c:
            r6 = 2131362204(0x7f0a019c, float:1.8344182E38)
            java.lang.Object r7 = r1.getTag(r6)
            android.view.View r7 = (android.view.View) r7
            if (r7 == 0) goto L_0x003b
            r15 = r2
            r8 = 1
            goto L_0x00a0
        L_0x003b:
            if (r15 != 0) goto L_0x003e
            goto L_0x004d
        L_0x003e:
            android.view.ViewParent r7 = r15.getParent()
            if (r7 == 0) goto L_0x004d
            r7 = 4
            if (r0 != r7) goto L_0x004a
        L_0x0047:
            r7 = r15
            r15 = r2
            goto L_0x0050
        L_0x004a:
            if (r1 != r15) goto L_0x0053
            goto L_0x0047
        L_0x004d:
            if (r15 == 0) goto L_0x0052
            r7 = r2
        L_0x0050:
            r8 = 0
            goto L_0x0056
        L_0x0052:
        L_0x0053:
            r15 = r2
            r7 = r15
            r8 = 1
        L_0x0056:
            if (r8 != 0) goto L_0x005d
        L_0x0058:
            r8 = 0
            r11 = r7
            r7 = r15
            r15 = r11
            goto L_0x00a0
        L_0x005d:
            android.view.ViewParent r8 = r1.getParent()
            if (r8 == 0) goto L_0x009d
            android.view.ViewParent r8 = r1.getParent()
            boolean r8 = r8 instanceof android.view.View
            if (r8 == 0) goto L_0x0058
            android.view.ViewParent r8 = r1.getParent()
            android.view.View r8 = (android.view.View) r8
            afu r9 = r12.mo299a((android.view.View) r8, (boolean) r3)
            afu r10 = r12.mo310b(r8, r3)
            agj r9 = m447b((p000.afu) r9, (p000.afu) r10)
            boolean r9 = r9.f383a
            if (r9 != 0) goto L_0x008b
            android.view.View r15 = p000.aft.m408a(r13, r1, r8)
            r8 = 0
            r11 = r7
            r7 = r15
            r15 = r11
            goto L_0x00a0
        L_0x008b:
            int r9 = r8.getId()
            android.view.ViewParent r8 = r8.getParent()
            if (r8 != 0) goto L_0x0058
            r8 = -1
            if (r9 == r8) goto L_0x0058
            android.view.View r8 = r13.findViewById(r9)
            goto L_0x0058
        L_0x009d:
            r15 = r7
            r8 = 0
            r7 = r1
        L_0x00a0:
            if (r7 != 0) goto L_0x00ca
            if (r15 != 0) goto L_0x00a6
            goto L_0x001f
        L_0x00a6:
            int r13 = r15.getVisibility()
            p000.agb.m422a((android.view.View) r15, (int) r4)
            android.animation.Animator r2 = r12.mo277b((android.view.View) r15, (p000.afu) r14)
            if (r2 == 0) goto L_0x00c5
            agi r13 = new agi
            r13.<init>(r15, r0)
            r2.addListener(r13)
            int r14 = android.os.Build.VERSION.SDK_INT
            r2.addPauseListener(r13)
            r12.mo302a((p000.afk) r13)
            goto L_0x011a
        L_0x00c5:
            p000.agb.m422a((android.view.View) r15, (int) r13)
            goto L_0x011a
        L_0x00ca:
            if (r8 != 0) goto L_0x00fc
            java.util.Map r15 = r14.f355a
            java.lang.String r0 = "android:visibility:screenLocation"
            java.lang.Object r15 = r15.get(r0)
            int[] r15 = (int[]) r15
            r0 = r15[r4]
            r15 = r15[r3]
            int[] r2 = new int[r5]
            r13.getLocationOnScreen(r2)
            r4 = r2[r4]
            int r0 = r0 - r4
            int r4 = r7.getLeft()
            int r0 = r0 - r4
            r7.offsetLeftAndRight(r0)
            r0 = r2[r3]
            int r15 = r15 - r0
            int r0 = r7.getTop()
            int r15 = r15 - r0
            r7.offsetTopAndBottom(r15)
            afx r15 = p000.afy.m413a(r13)
            r15.mo336a(r7)
        L_0x00fc:
            android.animation.Animator r2 = r12.mo277b((android.view.View) r7, (p000.afu) r14)
            if (r8 != 0) goto L_0x001f
            if (r2 == 0) goto L_0x0111
            r1.setTag(r6, r7)
            agh r14 = new agh
            r14.<init>(r12, r13, r7, r1)
            r12.mo302a((p000.afk) r14)
            goto L_0x011a
        L_0x0111:
            afx r13 = p000.afy.m413a(r13)
            r13.mo337b(r7)
        L_0x011a:
            return r2
        L_0x011b:
            int r13 = r12.f390n
            r13 = r13 & r3
            if (r13 != r3) goto L_0x0143
            if (r15 == 0) goto L_0x0143
            if (r14 != 0) goto L_0x013c
            android.view.View r13 = r15.f356b
            android.view.ViewParent r13 = r13.getParent()
            android.view.View r13 = (android.view.View) r13
            afu r0 = r12.mo310b(r13, r4)
            afu r13 = r12.mo299a((android.view.View) r13, (boolean) r4)
            agj r13 = m447b((p000.afu) r0, (p000.afu) r13)
            boolean r13 = r13.f383a
            if (r13 != 0) goto L_0x0143
        L_0x013c:
            android.view.View r13 = r15.f356b
            android.animation.Animator r13 = r12.mo276a((android.view.View) r13, (p000.afu) r14)
            return r13
        L_0x0143:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.agk.mo269a(android.view.ViewGroup, afu, afu):android.animation.Animator");
    }

    /* renamed from: b */
    private static final agj m447b(afu afu, afu afu2) {
        agj agj = new agj();
        agj.f383a = false;
        agj.f384b = false;
        if (afu == null || !afu.f355a.containsKey("android:visibility:visibility")) {
            agj.f385c = -1;
            agj.f387e = null;
        } else {
            agj.f385c = ((Integer) afu.f355a.get("android:visibility:visibility")).intValue();
            agj.f387e = (ViewGroup) afu.f355a.get("android:visibility:parent");
        }
        if (afu2 == null || !afu2.f355a.containsKey("android:visibility:visibility")) {
            agj.f386d = -1;
            agj.f388f = null;
        } else {
            agj.f386d = ((Integer) afu2.f355a.get("android:visibility:visibility")).intValue();
            agj.f388f = (ViewGroup) afu2.f355a.get("android:visibility:parent");
        }
        if (afu != null && afu2 != null) {
            int i = agj.f385c;
            int i2 = agj.f386d;
            if (i == i2 && agj.f387e == agj.f388f) {
                return agj;
            }
            if (i == i2) {
                if (agj.f388f == null) {
                    agj.f384b = false;
                    agj.f383a = true;
                } else if (agj.f387e == null) {
                    agj.f384b = true;
                    agj.f383a = true;
                }
            } else if (i == 0) {
                agj.f384b = false;
                agj.f383a = true;
            } else if (i2 == 0) {
                agj.f384b = true;
                agj.f383a = true;
            }
        } else if (afu == null && agj.f386d == 0) {
            agj.f384b = true;
            agj.f383a = true;
        } else if (afu2 == null && agj.f385c == 0) {
            agj.f384b = false;
            agj.f383a = true;
        }
        return agj;
    }

    /* renamed from: a */
    public final boolean mo308a(afu afu, afu afu2) {
        if (afu == null && afu2 == null) {
            return false;
        }
        if (afu != null && afu2 != null && afu2.f355a.containsKey("android:visibility:visibility") != afu.f355a.containsKey("android:visibility:visibility")) {
            return false;
        }
        agj b = m447b(afu, afu2);
        if (!b.f383a) {
            return false;
        }
        if (b.f385c == 0 || b.f386d == 0) {
            return true;
        }
        return false;
    }
}
