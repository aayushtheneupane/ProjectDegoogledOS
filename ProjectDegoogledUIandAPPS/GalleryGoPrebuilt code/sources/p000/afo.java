package p000;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;

/* renamed from: afo */
/* compiled from: PG */
final class afo implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* renamed from: a */
    public final ViewGroup f343a;

    /* renamed from: b */
    private final afl f344b;

    public afo(afl afl, ViewGroup viewGroup) {
        this.f344b = afl;
        this.f343a = viewGroup;
    }

    public final void onViewAttachedToWindow(View view) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: afu} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x023a  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01e8 A[EDGE_INSN: B:131:0x01e8->B:87:0x01e8 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x020e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onPreDraw() {
        /*
            r19 = this;
            r0 = r19
            r19.m379a()
            java.util.ArrayList r1 = p000.afp.f345a
            android.view.ViewGroup r2 = r0.f343a
            boolean r1 = r1.remove(r2)
            r2 = 1
            if (r1 == 0) goto L_0x02a9
            kn r1 = p000.afp.m380a()
            android.view.ViewGroup r3 = r0.f343a
            java.lang.Object r3 = r1.get(r3)
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            if (r3 != 0) goto L_0x002a
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            android.view.ViewGroup r5 = r0.f343a
            r1.put(r5, r3)
        L_0x0028:
            r5 = 0
            goto L_0x0038
        L_0x002a:
            int r5 = r3.size()
            if (r5 <= 0) goto L_0x0036
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r3)
            goto L_0x0038
        L_0x0036:
            goto L_0x0028
        L_0x0038:
            afl r6 = r0.f344b
            r3.add(r6)
            afl r3 = r0.f344b
            afn r6 = new afn
            r6.<init>(r0, r1)
            r3.mo302a((p000.afk) r6)
            afl r1 = r0.f344b
            android.view.ViewGroup r3 = r0.f343a
            r6 = 0
            r1.mo304a((android.view.ViewGroup) r3, (boolean) r6)
            if (r5 != 0) goto L_0x0052
            goto L_0x0067
        L_0x0052:
            int r1 = r5.size()
            r3 = 0
        L_0x0057:
            if (r3 >= r1) goto L_0x0067
            java.lang.Object r7 = r5.get(r3)
            afl r7 = (p000.afl) r7
            android.view.ViewGroup r8 = r0.f343a
            r7.mo316c((android.view.View) r8)
            int r3 = r3 + 1
            goto L_0x0057
        L_0x0067:
            afl r1 = r0.f344b
            android.view.ViewGroup r8 = r0.f343a
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r1.f330i = r3
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r1.f331j = r3
            afv r3 = r1.f326e
            afv r5 = r1.f327f
            kn r7 = new kn
            kn r9 = r3.f358a
            r7.<init>((p000.C0309lf) r9)
            kn r9 = new kn
            kn r10 = r5.f358a
            r9.<init>((p000.C0309lf) r10)
            r10 = 0
        L_0x008c:
            int[] r11 = r1.f329h
            int r12 = r11.length
            if (r10 >= r12) goto L_0x01e8
            r11 = r11[r10]
            if (r11 == r2) goto L_0x01a6
            r12 = 2
            if (r11 == r12) goto L_0x0158
            r12 = 3
            if (r11 == r12) goto L_0x0103
            r12 = 4
            if (r11 == r12) goto L_0x00a1
            r4 = r5
            goto L_0x01df
        L_0x00a1:
            kt r11 = r3.f360c
            kt r12 = r5.f360c
            int r13 = r11.mo9232b()
            r14 = 0
        L_0x00aa:
            if (r14 >= r13) goto L_0x00fd
            java.lang.Object r15 = r11.mo9233b(r14)
            android.view.View r15 = (android.view.View) r15
            if (r15 != 0) goto L_0x00b7
            r17 = r5
            goto L_0x00f6
        L_0x00b7:
            boolean r16 = r1.mo309a((android.view.View) r15)
            if (r16 == 0) goto L_0x00f4
            r17 = r5
            long r4 = r11.mo9228a((int) r14)
            java.lang.Object r4 = r12.mo9229a((long) r4)
            android.view.View r4 = (android.view.View) r4
            if (r4 == 0) goto L_0x00f6
            boolean r5 = r1.mo309a((android.view.View) r4)
            if (r5 == 0) goto L_0x00f6
            java.lang.Object r5 = r7.get(r15)
            afu r5 = (p000.afu) r5
            java.lang.Object r18 = r9.get(r4)
            r6 = r18
            afu r6 = (p000.afu) r6
            if (r5 == 0) goto L_0x00f6
            if (r6 == 0) goto L_0x00f6
            java.util.ArrayList r2 = r1.f330i
            r2.add(r5)
            java.util.ArrayList r2 = r1.f331j
            r2.add(r6)
            r7.remove(r15)
            r9.remove(r4)
            goto L_0x00f6
        L_0x00f4:
            r17 = r5
        L_0x00f6:
            int r14 = r14 + 1
            r5 = r17
            r2 = 1
            r6 = 0
            goto L_0x00aa
        L_0x00fd:
            r17 = r5
            r4 = r17
            goto L_0x01df
        L_0x0103:
            r17 = r5
            android.util.SparseArray r2 = r3.f359b
            r4 = r17
            android.util.SparseArray r5 = r4.f359b
            int r6 = r2.size()
            r11 = 0
        L_0x0110:
            if (r11 >= r6) goto L_0x01df
            java.lang.Object r12 = r2.valueAt(r11)
            android.view.View r12 = (android.view.View) r12
            if (r12 != 0) goto L_0x011b
            goto L_0x0153
        L_0x011b:
            boolean r13 = r1.mo309a((android.view.View) r12)
            if (r13 == 0) goto L_0x0153
            int r13 = r2.keyAt(r11)
            java.lang.Object r13 = r5.get(r13)
            android.view.View r13 = (android.view.View) r13
            if (r13 == 0) goto L_0x0153
            boolean r14 = r1.mo309a((android.view.View) r13)
            if (r14 == 0) goto L_0x0153
            java.lang.Object r14 = r7.get(r12)
            afu r14 = (p000.afu) r14
            java.lang.Object r15 = r9.get(r13)
            afu r15 = (p000.afu) r15
            if (r14 == 0) goto L_0x0153
            if (r15 == 0) goto L_0x0153
            java.util.ArrayList r0 = r1.f330i
            r0.add(r14)
            java.util.ArrayList r0 = r1.f331j
            r0.add(r15)
            r7.remove(r12)
            r9.remove(r13)
        L_0x0153:
            int r11 = r11 + 1
            r0 = r19
            goto L_0x0110
        L_0x0158:
            r4 = r5
            kn r0 = r3.f361d
            kn r2 = r4.f361d
            int r5 = r0.f15193b
            r6 = 0
        L_0x0160:
            if (r6 >= r5) goto L_0x01df
            java.lang.Object r11 = r0.mo9321c(r6)
            android.view.View r11 = (android.view.View) r11
            if (r11 != 0) goto L_0x016b
            goto L_0x01a3
        L_0x016b:
            boolean r12 = r1.mo309a((android.view.View) r11)
            if (r12 == 0) goto L_0x01a3
            java.lang.Object r12 = r0.mo9320b((int) r6)
            java.lang.Object r12 = r2.get(r12)
            android.view.View r12 = (android.view.View) r12
            if (r12 == 0) goto L_0x01a3
            boolean r13 = r1.mo309a((android.view.View) r12)
            if (r13 == 0) goto L_0x01a3
            java.lang.Object r13 = r7.get(r11)
            afu r13 = (p000.afu) r13
            java.lang.Object r14 = r9.get(r12)
            afu r14 = (p000.afu) r14
            if (r13 == 0) goto L_0x01a3
            if (r14 == 0) goto L_0x01a3
            java.util.ArrayList r15 = r1.f330i
            r15.add(r13)
            java.util.ArrayList r13 = r1.f331j
            r13.add(r14)
            r7.remove(r11)
            r9.remove(r12)
        L_0x01a3:
            int r6 = r6 + 1
            goto L_0x0160
        L_0x01a6:
            r4 = r5
            int r0 = r7.f15193b
            int r0 = r0 + -1
        L_0x01ab:
            if (r0 < 0) goto L_0x01df
            java.lang.Object r2 = r7.mo9320b((int) r0)
            android.view.View r2 = (android.view.View) r2
            if (r2 != 0) goto L_0x01b6
            goto L_0x01dc
        L_0x01b6:
            boolean r5 = r1.mo309a((android.view.View) r2)
            if (r5 == 0) goto L_0x01dc
            java.lang.Object r2 = r9.remove(r2)
            afu r2 = (p000.afu) r2
            if (r2 == 0) goto L_0x01dc
            android.view.View r5 = r2.f356b
            boolean r5 = r1.mo309a((android.view.View) r5)
            if (r5 == 0) goto L_0x01dc
            java.lang.Object r5 = r7.mo1935d(r0)
            afu r5 = (p000.afu) r5
            java.util.ArrayList r6 = r1.f330i
            r6.add(r5)
            java.util.ArrayList r5 = r1.f331j
            r5.add(r2)
        L_0x01dc:
            int r0 = r0 + -1
            goto L_0x01ab
        L_0x01df:
            int r10 = r10 + 1
            r0 = r19
            r5 = r4
            r2 = 1
            r6 = 0
            goto L_0x008c
        L_0x01e8:
            r0 = 0
        L_0x01e9:
            int r2 = r7.f15193b
            if (r0 >= r2) goto L_0x0209
            java.lang.Object r2 = r7.mo9321c(r0)
            afu r2 = (p000.afu) r2
            android.view.View r3 = r2.f356b
            boolean r3 = r1.mo309a((android.view.View) r3)
            if (r3 == 0) goto L_0x0206
            java.util.ArrayList r3 = r1.f330i
            r3.add(r2)
            java.util.ArrayList r2 = r1.f331j
            r3 = 0
            r2.add(r3)
        L_0x0206:
            int r0 = r0 + 1
            goto L_0x01e9
        L_0x0209:
            r6 = 0
        L_0x020a:
            int r0 = r9.f15193b
            if (r6 >= r0) goto L_0x022c
            java.lang.Object r0 = r9.mo9321c(r6)
            afu r0 = (p000.afu) r0
            android.view.View r2 = r0.f356b
            boolean r2 = r1.mo309a((android.view.View) r2)
            if (r2 == 0) goto L_0x0228
            java.util.ArrayList r2 = r1.f331j
            r2.add(r0)
            java.util.ArrayList r0 = r1.f330i
            r2 = 0
            r0.add(r2)
            goto L_0x0229
        L_0x0228:
            r2 = 0
        L_0x0229:
            int r6 = r6 + 1
            goto L_0x020a
        L_0x022c:
            kn r0 = p000.afl.m341b()
            int r2 = r0.f15193b
            agm r3 = p000.agb.m420a(r8)
            int r2 = r2 + -1
        L_0x0238:
            if (r2 < 0) goto L_0x0298
            java.lang.Object r4 = r0.mo9320b((int) r2)
            android.animation.Animator r4 = (android.animation.Animator) r4
            if (r4 == 0) goto L_0x0295
            java.lang.Object r5 = r0.get(r4)
            afj r5 = (p000.afj) r5
            if (r5 != 0) goto L_0x024b
        L_0x024a:
            goto L_0x0295
        L_0x024b:
            android.view.View r6 = r5.f314a
            if (r6 == 0) goto L_0x024a
            agm r6 = r5.f317d
            boolean r6 = r3.equals(r6)
            if (r6 == 0) goto L_0x0295
            afu r6 = r5.f316c
            android.view.View r7 = r5.f314a
            r9 = 1
            afu r10 = r1.mo299a((android.view.View) r7, (boolean) r9)
            afu r11 = r1.mo310b(r7, r9)
            if (r10 == 0) goto L_0x0267
        L_0x0266:
            goto L_0x0274
        L_0x0267:
            if (r11 != 0) goto L_0x0266
            afv r9 = r1.f327f
            kn r9 = r9.f358a
            java.lang.Object r7 = r9.get(r7)
            r11 = r7
            afu r11 = (p000.afu) r11
        L_0x0274:
            if (r10 == 0) goto L_0x0277
            goto L_0x0279
        L_0x0277:
            if (r11 == 0) goto L_0x0295
        L_0x0279:
            afl r5 = r5.f318e
            boolean r5 = r5.mo308a((p000.afu) r6, (p000.afu) r11)
            if (r5 == 0) goto L_0x0295
            boolean r5 = r4.isRunning()
            if (r5 != 0) goto L_0x0292
            boolean r5 = r4.isStarted()
            if (r5 == 0) goto L_0x028e
            goto L_0x0292
        L_0x028e:
            r0.remove(r4)
            goto L_0x0295
        L_0x0292:
            r4.cancel()
        L_0x0295:
            int r2 = r2 + -1
            goto L_0x0238
        L_0x0298:
            afv r9 = r1.f326e
            afv r10 = r1.f327f
            java.util.ArrayList r11 = r1.f330i
            java.util.ArrayList r12 = r1.f331j
            r7 = r1
            r7.mo303a(r8, r9, r10, r11, r12)
            r1.mo314c()
            r0 = 1
            return r0
        L_0x02a9:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.afo.onPreDraw():boolean");
    }

    public final void onViewDetachedFromWindow(View view) {
        m379a();
        afp.f345a.remove(this.f343a);
        ArrayList arrayList = (ArrayList) afp.m380a().get(this.f343a);
        if (arrayList != null && arrayList.size() > 0) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((afl) arrayList.get(i)).mo316c((View) this.f343a);
            }
        }
        this.f344b.mo307a(true);
    }

    /* renamed from: a */
    private final void m379a() {
        this.f343a.getViewTreeObserver().removeOnPreDrawListener(this);
        this.f343a.removeOnAttachStateChangeListener(this);
    }
}
