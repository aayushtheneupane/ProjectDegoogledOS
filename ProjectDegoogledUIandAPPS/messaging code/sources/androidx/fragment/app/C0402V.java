package androidx.fragment.app;

import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import p000a.p005b.C0015b;

/* renamed from: androidx.fragment.app.V */
class C0402V {

    /* renamed from: vp */
    private static final int[] f388vp = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* renamed from: wp */
    private static final C0416ea f389wp = new C0408aa();

    /* renamed from: xp */
    private static final C0416ea f390xp;

    static {
        C0416ea eaVar;
        int i = Build.VERSION.SDK_INT;
        try {
            eaVar = (C0416ea) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            eaVar = null;
        }
        f390xp = eaVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:151:0x040b  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0423  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0461 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0224 A[ADDED_TO_REGION] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void m370a(androidx.fragment.app.C0389H r39, java.util.ArrayList r40, java.util.ArrayList r41, int r42, int r43, boolean r44) {
        /*
            r0 = r39
            r1 = r40
            r2 = r41
            r3 = r43
            r4 = r44
            int r5 = r0.mCurState
            r6 = 1
            if (r5 >= r6) goto L_0x0010
            return
        L_0x0010:
            android.util.SparseArray r5 = new android.util.SparseArray
            r5.<init>()
            r7 = r42
        L_0x0017:
            r8 = 0
            if (r7 >= r3) goto L_0x0068
            java.lang.Object r9 = r1.get(r7)
            androidx.fragment.app.a r9 = (androidx.fragment.app.C0407a) r9
            java.lang.Object r10 = r2.get(r7)
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x004e
            androidx.fragment.app.H r8 = r9.mManager
            androidx.fragment.app.l r8 = r8.mContainer
            boolean r8 = r8.onHasView()
            if (r8 != 0) goto L_0x0037
            goto L_0x0065
        L_0x0037:
            java.util.ArrayList r8 = r9.mOps
            int r8 = r8.size()
            int r8 = r8 - r6
        L_0x003e:
            if (r8 < 0) goto L_0x0065
            java.util.ArrayList r10 = r9.mOps
            java.lang.Object r10 = r10.get(r8)
            androidx.fragment.app.N r10 = (androidx.fragment.app.C0395N) r10
            m371a((androidx.fragment.app.C0407a) r9, (androidx.fragment.app.C0395N) r10, (android.util.SparseArray) r5, (boolean) r6, (boolean) r4)
            int r8 = r8 + -1
            goto L_0x003e
        L_0x004e:
            java.util.ArrayList r10 = r9.mOps
            int r10 = r10.size()
            r11 = r8
        L_0x0055:
            if (r11 >= r10) goto L_0x0065
            java.util.ArrayList r12 = r9.mOps
            java.lang.Object r12 = r12.get(r11)
            androidx.fragment.app.N r12 = (androidx.fragment.app.C0395N) r12
            m371a((androidx.fragment.app.C0407a) r9, (androidx.fragment.app.C0395N) r12, (android.util.SparseArray) r5, (boolean) r8, (boolean) r4)
            int r11 = r11 + 1
            goto L_0x0055
        L_0x0065:
            int r7 = r7 + 1
            goto L_0x0017
        L_0x0068:
            int r7 = r5.size()
            if (r7 == 0) goto L_0x0475
            android.view.View r7 = new android.view.View
            androidx.fragment.app.o r9 = r0.mHost
            android.content.Context r9 = r9.getContext()
            r7.<init>(r9)
            int r15 = r5.size()
            r14 = r8
        L_0x007e:
            if (r14 >= r15) goto L_0x0475
            int r9 = r5.keyAt(r14)
            a.b.b r13 = new a.b.b
            r13.<init>()
            int r10 = r3 + -1
            r12 = r42
        L_0x008d:
            if (r10 < r12) goto L_0x00f8
            java.lang.Object r11 = r1.get(r10)
            androidx.fragment.app.a r11 = (androidx.fragment.app.C0407a) r11
            boolean r16 = r11.interactsWith(r9)
            if (r16 != 0) goto L_0x009c
            goto L_0x00ed
        L_0x009c:
            java.lang.Object r16 = r2.get(r10)
            java.lang.Boolean r16 = (java.lang.Boolean) r16
            boolean r16 = r16.booleanValue()
            java.util.ArrayList r6 = r11.mSharedElementSourceNames
            if (r6 == 0) goto L_0x00ed
            int r6 = r6.size()
            if (r16 == 0) goto L_0x00b5
            java.util.ArrayList r8 = r11.mSharedElementSourceNames
            java.util.ArrayList r11 = r11.mSharedElementTargetNames
            goto L_0x00be
        L_0x00b5:
            java.util.ArrayList r8 = r11.mSharedElementSourceNames
            java.util.ArrayList r11 = r11.mSharedElementTargetNames
            r38 = r11
            r11 = r8
            r8 = r38
        L_0x00be:
            r1 = 0
        L_0x00bf:
            if (r1 >= r6) goto L_0x00ed
            java.lang.Object r16 = r11.get(r1)
            r2 = r16
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r16 = r8.get(r1)
            r3 = r16
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r16 = r13.remove(r3)
            r17 = r6
            r6 = r16
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L_0x00e1
            r13.put(r2, r6)
            goto L_0x00e4
        L_0x00e1:
            r13.put(r2, r3)
        L_0x00e4:
            int r1 = r1 + 1
            r2 = r41
            r3 = r43
            r6 = r17
            goto L_0x00bf
        L_0x00ed:
            int r10 = r10 + -1
            r1 = r40
            r2 = r41
            r3 = r43
            r6 = 1
            r8 = 0
            goto L_0x008d
        L_0x00f8:
            java.lang.Object r1 = r5.valueAt(r14)
            androidx.fragment.app.U r1 = (androidx.fragment.app.C0401U) r1
            if (r4 == 0) goto L_0x02f8
            androidx.fragment.app.l r3 = r0.mContainer
            boolean r3 = r3.onHasView()
            if (r3 == 0) goto L_0x0111
            androidx.fragment.app.l r3 = r0.mContainer
            android.view.View r3 = r3.onFindViewById(r9)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            goto L_0x0112
        L_0x0111:
            r3 = 0
        L_0x0112:
            if (r3 != 0) goto L_0x011c
        L_0x0114:
            r31 = r5
            r32 = r14
            r33 = r15
            goto L_0x02f1
        L_0x011c:
            androidx.fragment.app.j r6 = r1.lastIn
            androidx.fragment.app.j r8 = r1.firstOut
            androidx.fragment.app.ea r9 = m364a((androidx.fragment.app.C0424j) r8, (androidx.fragment.app.C0424j) r6)
            if (r9 != 0) goto L_0x0127
            goto L_0x0114
        L_0x0127:
            boolean r10 = r1.lastInIsPop
            boolean r11 = r1.firstOutIsPop
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r31 = r5
            java.lang.Object r5 = m366a((androidx.fragment.app.C0416ea) r9, (androidx.fragment.app.C0424j) r6, (boolean) r10)
            java.lang.Object r11 = m378b(r9, r8, r11)
            androidx.fragment.app.j r12 = r1.lastIn
            r32 = r14
            androidx.fragment.app.j r14 = r1.firstOut
            r33 = r15
            if (r12 == 0) goto L_0x0151
            android.view.View r15 = r12.requireView()
            r0 = 0
            r15.setVisibility(r0)
        L_0x0151:
            if (r12 == 0) goto L_0x01f1
            if (r14 != 0) goto L_0x0157
            goto L_0x01f1
        L_0x0157:
            boolean r0 = r1.lastInIsPop
            boolean r15 = r13.isEmpty()
            if (r15 == 0) goto L_0x0163
            r34 = r10
            r15 = 0
            goto L_0x0169
        L_0x0163:
            java.lang.Object r15 = m365a((androidx.fragment.app.C0416ea) r9, (androidx.fragment.app.C0424j) r12, (androidx.fragment.app.C0424j) r14, (boolean) r0)
            r34 = r10
        L_0x0169:
            a.b.b r10 = m377b(r9, r13, r15, r1)
            r35 = r6
            a.b.b r6 = m362a((androidx.fragment.app.C0416ea) r9, (p000a.p005b.C0015b) r13, (java.lang.Object) r15, (androidx.fragment.app.C0401U) r1)
            boolean r16 = r13.isEmpty()
            if (r16 == 0) goto L_0x0185
            if (r10 == 0) goto L_0x017e
            r10.clear()
        L_0x017e:
            if (r6 == 0) goto L_0x0183
            r6.clear()
        L_0x0183:
            r15 = 0
            goto L_0x0197
        L_0x0185:
            r16 = r15
            java.util.Set r15 = r13.keySet()
            m375a((java.util.ArrayList) r4, (p000a.p005b.C0015b) r10, (java.util.Collection) r15)
            java.util.Collection r15 = r13.values()
            m375a((java.util.ArrayList) r2, (p000a.p005b.C0015b) r6, (java.util.Collection) r15)
            r15 = r16
        L_0x0197:
            if (r5 != 0) goto L_0x01a0
            if (r11 != 0) goto L_0x01a0
            if (r15 != 0) goto L_0x01a0
            r37 = r2
            goto L_0x01f7
        L_0x01a0:
            r36 = r13
            r13 = 1
            m373a((androidx.fragment.app.C0424j) r12, (androidx.fragment.app.C0424j) r14, (boolean) r0, (p000a.p005b.C0015b) r10, (boolean) r13)
            if (r15 == 0) goto L_0x01d6
            r2.add(r7)
            r9.mo4221b((java.lang.Object) r15, (android.view.View) r7, (java.util.ArrayList) r4)
            boolean r13 = r1.firstOutIsPop
            r37 = r2
            androidx.fragment.app.a r2 = r1.firstOutTransaction
            r16 = r9
            r17 = r15
            r18 = r11
            r19 = r10
            r20 = r13
            r21 = r2
            m372a((androidx.fragment.app.C0416ea) r16, (java.lang.Object) r17, (java.lang.Object) r18, (p000a.p005b.C0015b) r19, (boolean) r20, (androidx.fragment.app.C0407a) r21)
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            android.view.View r1 = m363a((p000a.p005b.C0015b) r6, (androidx.fragment.app.C0401U) r1, (java.lang.Object) r5, (boolean) r0)
            if (r1 == 0) goto L_0x01d1
            r9.mo4213a((java.lang.Object) r5, (android.graphics.Rect) r2)
        L_0x01d1:
            r27 = r1
            r29 = r2
            goto L_0x01dc
        L_0x01d6:
            r37 = r2
            r27 = 0
            r29 = 0
        L_0x01dc:
            androidx.fragment.app.S r1 = new androidx.fragment.app.S
            r22 = r1
            r23 = r12
            r24 = r14
            r25 = r0
            r26 = r6
            r28 = r9
            r22.<init>(r23, r24, r25, r26, r27, r28, r29)
            androidx.core.view.OneShotPreDrawListener.add(r3, r1)
            goto L_0x01fa
        L_0x01f1:
            r37 = r2
            r35 = r6
            r34 = r10
        L_0x01f7:
            r36 = r13
            r15 = 0
        L_0x01fa:
            if (r5 != 0) goto L_0x0202
            if (r15 != 0) goto L_0x0202
            if (r11 != 0) goto L_0x0202
            goto L_0x02f1
        L_0x0202:
            java.util.ArrayList r0 = m369a((androidx.fragment.app.C0416ea) r9, (java.lang.Object) r11, (androidx.fragment.app.C0424j) r8, (java.util.ArrayList) r4, (android.view.View) r7)
            r1 = r35
            r2 = r37
            java.util.ArrayList r6 = m369a((androidx.fragment.app.C0416ea) r9, (java.lang.Object) r5, (androidx.fragment.app.C0424j) r1, (java.util.ArrayList) r2, (android.view.View) r7)
            r10 = 4
            m374a((java.util.ArrayList) r6, (int) r10)
            r16 = r9
            r17 = r5
            r18 = r11
            r19 = r15
            r20 = r1
            r21 = r34
            java.lang.Object r1 = m367a((androidx.fragment.app.C0416ea) r16, (java.lang.Object) r17, (java.lang.Object) r18, (java.lang.Object) r19, (androidx.fragment.app.C0424j) r20, (boolean) r21)
            if (r1 == 0) goto L_0x02f1
            if (r8 == 0) goto L_0x0249
            if (r11 == 0) goto L_0x0249
            boolean r10 = r8.mAdded
            if (r10 == 0) goto L_0x0249
            boolean r10 = r8.mHidden
            if (r10 == 0) goto L_0x0249
            boolean r10 = r8.mHiddenChanged
            if (r10 == 0) goto L_0x0249
            r10 = 1
            r8.setHideReplaced(r10)
            android.view.View r10 = r8.getView()
            r9.mo4215a((java.lang.Object) r11, (android.view.View) r10, (java.util.ArrayList) r0)
            android.view.ViewGroup r8 = r8.mContainer
            androidx.fragment.app.P r10 = new androidx.fragment.app.P
            r10.<init>(r0)
            androidx.core.view.OneShotPreDrawListener.add(r8, r10)
        L_0x0249:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            int r10 = r2.size()
            r12 = 0
        L_0x0253:
            if (r12 >= r10) goto L_0x026f
            java.lang.Object r13 = r2.get(r12)
            android.view.View r13 = (android.view.View) r13
            java.lang.String r14 = androidx.core.view.ViewCompat.getTransitionName(r13)
            r8.add(r14)
            int r14 = android.os.Build.VERSION.SDK_INT
            r16 = r10
            r10 = 0
            r13.setTransitionName(r10)
            int r12 = r12 + 1
            r10 = r16
            goto L_0x0253
        L_0x026f:
            r22 = r9
            r23 = r1
            r24 = r5
            r25 = r6
            r26 = r11
            r27 = r0
            r28 = r15
            r29 = r2
            r22.mo4216a(r23, r24, r25, r26, r27, r28, r29)
            r9.mo4212a((android.view.ViewGroup) r3, (java.lang.Object) r1)
            int r0 = r2.size()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5 = 0
        L_0x028f:
            if (r5 >= r0) goto L_0x02d3
            java.lang.Object r10 = r4.get(r5)
            android.view.View r10 = (android.view.View) r10
            java.lang.String r11 = androidx.core.view.ViewCompat.getTransitionName(r10)
            r1.add(r11)
            if (r11 != 0) goto L_0x02a3
            r13 = r36
            goto L_0x02ce
        L_0x02a3:
            int r12 = android.os.Build.VERSION.SDK_INT
            r14 = 0
            r10.setTransitionName(r14)
            r13 = r36
            java.lang.Object r10 = r13.get(r11)
            java.lang.String r10 = (java.lang.String) r10
            r12 = 0
        L_0x02b2:
            if (r12 >= r0) goto L_0x02ce
            java.lang.Object r14 = r8.get(r12)
            boolean r14 = r10.equals(r14)
            if (r14 == 0) goto L_0x02ca
            java.lang.Object r10 = r2.get(r12)
            android.view.View r10 = (android.view.View) r10
            int r12 = android.os.Build.VERSION.SDK_INT
            r10.setTransitionName(r11)
            goto L_0x02ce
        L_0x02ca:
            int r12 = r12 + 1
            r14 = 0
            goto L_0x02b2
        L_0x02ce:
            int r5 = r5 + 1
            r36 = r13
            goto L_0x028f
        L_0x02d3:
            androidx.fragment.app.ba r5 = new androidx.fragment.app.ba
            r22 = r5
            r23 = r9
            r24 = r0
            r25 = r2
            r26 = r8
            r27 = r4
            r28 = r1
            r22.<init>(r23, r24, r25, r26, r27, r28)
            androidx.core.view.OneShotPreDrawListener.add(r3, r5)
            r0 = 0
            m374a((java.util.ArrayList) r6, (int) r0)
            r9.mo4222b((java.lang.Object) r15, (java.util.ArrayList) r4, (java.util.ArrayList) r2)
            goto L_0x02f2
        L_0x02f1:
            r0 = 0
        L_0x02f2:
            r27 = r32
            r30 = r33
            goto L_0x0461
        L_0x02f8:
            r31 = r5
            r32 = r14
            r33 = r15
            r0 = 0
            r2 = r39
            androidx.fragment.app.l r3 = r2.mContainer
            boolean r3 = r3.onHasView()
            if (r3 == 0) goto L_0x0312
            androidx.fragment.app.l r3 = r2.mContainer
            android.view.View r3 = r3.onFindViewById(r9)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            goto L_0x0313
        L_0x0312:
            r3 = 0
        L_0x0313:
            if (r3 != 0) goto L_0x0316
            goto L_0x02f2
        L_0x0316:
            androidx.fragment.app.j r4 = r1.lastIn
            androidx.fragment.app.j r5 = r1.firstOut
            androidx.fragment.app.ea r6 = m364a((androidx.fragment.app.C0424j) r5, (androidx.fragment.app.C0424j) r4)
            if (r6 != 0) goto L_0x0321
            goto L_0x02f2
        L_0x0321:
            boolean r8 = r1.lastInIsPop
            boolean r9 = r1.firstOutIsPop
            java.lang.Object r8 = m366a((androidx.fragment.app.C0416ea) r6, (androidx.fragment.app.C0424j) r4, (boolean) r8)
            java.lang.Object r12 = m378b(r6, r5, r9)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            androidx.fragment.app.j r9 = r1.lastIn
            androidx.fragment.app.j r15 = r1.firstOut
            if (r9 == 0) goto L_0x03d9
            if (r15 != 0) goto L_0x0341
            goto L_0x03d9
        L_0x0341:
            boolean r14 = r1.lastInIsPop
            boolean r16 = r13.isEmpty()
            if (r16 == 0) goto L_0x034b
            r0 = 0
            goto L_0x0351
        L_0x034b:
            java.lang.Object r16 = m365a((androidx.fragment.app.C0416ea) r6, (androidx.fragment.app.C0424j) r9, (androidx.fragment.app.C0424j) r15, (boolean) r14)
            r0 = r16
        L_0x0351:
            a.b.b r2 = m377b(r6, r13, r0, r1)
            boolean r16 = r13.isEmpty()
            if (r16 == 0) goto L_0x035d
            r0 = 0
            goto L_0x0368
        L_0x035d:
            r16 = r0
            java.util.Collection r0 = r2.values()
            r11.addAll(r0)
            r0 = r16
        L_0x0368:
            if (r8 != 0) goto L_0x0370
            if (r12 != 0) goto L_0x0370
            if (r0 != 0) goto L_0x0370
            goto L_0x03d9
        L_0x0370:
            r22 = r4
            r4 = 1
            m373a((androidx.fragment.app.C0424j) r9, (androidx.fragment.app.C0424j) r15, (boolean) r14, (p000a.p005b.C0015b) r2, (boolean) r4)
            if (r0 == 0) goto L_0x039f
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r6.mo4221b((java.lang.Object) r0, (android.view.View) r7, (java.util.ArrayList) r11)
            r20 = r9
            boolean r9 = r1.firstOutIsPop
            r21 = r10
            androidx.fragment.app.a r10 = r1.firstOutTransaction
            r23 = r14
            r14 = r6
            r24 = r15
            r15 = r0
            r16 = r12
            r17 = r2
            r18 = r9
            r19 = r10
            m372a((androidx.fragment.app.C0416ea) r14, (java.lang.Object) r15, (java.lang.Object) r16, (p000a.p005b.C0015b) r17, (boolean) r18, (androidx.fragment.app.C0407a) r19)
            if (r8 == 0) goto L_0x03a8
            r6.mo4213a((java.lang.Object) r8, (android.graphics.Rect) r4)
            goto L_0x03a8
        L_0x039f:
            r20 = r9
            r21 = r10
            r23 = r14
            r24 = r15
            r4 = 0
        L_0x03a8:
            androidx.fragment.app.T r2 = new androidx.fragment.app.T
            r16 = r20
            r9 = r2
            r15 = r21
            r10 = r6
            r14 = r11
            r11 = r13
            r25 = r5
            r5 = r12
            r12 = r0
            r26 = r0
            r0 = r13
            r13 = r1
            r28 = r14
            r27 = r32
            r29 = 0
            r14 = r15
            r32 = r15
            r30 = r33
            r15 = r7
            r17 = r24
            r18 = r23
            r19 = r28
            r20 = r8
            r21 = r4
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            androidx.core.view.OneShotPreDrawListener.add(r3, r2)
            r20 = r26
            goto L_0x03eb
        L_0x03d9:
            r22 = r4
            r25 = r5
            r28 = r11
            r5 = r12
            r0 = r13
            r27 = r32
            r30 = r33
            r29 = 0
            r32 = r10
            r20 = r29
        L_0x03eb:
            if (r8 != 0) goto L_0x03f3
            if (r20 != 0) goto L_0x03f3
            if (r5 != 0) goto L_0x03f3
            goto L_0x0461
        L_0x03f3:
            r2 = r25
            r4 = r28
            java.util.ArrayList r2 = m369a((androidx.fragment.app.C0416ea) r6, (java.lang.Object) r5, (androidx.fragment.app.C0424j) r2, (java.util.ArrayList) r4, (android.view.View) r7)
            if (r2 == 0) goto L_0x0406
            boolean r4 = r2.isEmpty()
            if (r4 == 0) goto L_0x0404
            goto L_0x0406
        L_0x0404:
            r29 = r5
        L_0x0406:
            r4 = r6
            androidx.fragment.app.aa r4 = (androidx.fragment.app.C0408aa) r4
            if (r8 == 0) goto L_0x0411
            r4 = r8
            android.transition.Transition r4 = (android.transition.Transition) r4
            r4.addTarget(r7)
        L_0x0411:
            boolean r1 = r1.lastInIsPop
            r14 = r6
            r15 = r8
            r16 = r29
            r17 = r20
            r18 = r22
            r19 = r1
            java.lang.Object r1 = m367a((androidx.fragment.app.C0416ea) r14, (java.lang.Object) r15, (java.lang.Object) r16, (java.lang.Object) r17, (androidx.fragment.app.C0424j) r18, (boolean) r19)
            if (r1 == 0) goto L_0x0461
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r14 = r6
            r15 = r1
            r16 = r8
            r17 = r4
            r18 = r29
            r19 = r2
            r21 = r32
            r14.mo4216a(r15, r16, r17, r18, r19, r20, r21)
            androidx.fragment.app.Q r5 = new androidx.fragment.app.Q
            r9 = r5
            r10 = r8
            r11 = r6
            r12 = r7
            r13 = r22
            r14 = r32
            r15 = r4
            r16 = r2
            r17 = r29
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17)
            androidx.core.view.OneShotPreDrawListener.add(r3, r5)
            androidx.fragment.app.ca r2 = new androidx.fragment.app.ca
            r4 = r32
            r2.<init>(r6, r4, r0)
            androidx.core.view.OneShotPreDrawListener.add(r3, r2)
            r6.mo4212a((android.view.ViewGroup) r3, (java.lang.Object) r1)
            androidx.fragment.app.da r1 = new androidx.fragment.app.da
            r1.<init>(r6, r4, r0)
            androidx.core.view.OneShotPreDrawListener.add(r3, r1)
        L_0x0461:
            int r14 = r27 + 1
            r0 = r39
            r1 = r40
            r2 = r41
            r3 = r43
            r4 = r44
            r15 = r30
            r5 = r31
            r6 = 1
            r8 = 0
            goto L_0x007e
        L_0x0475:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0402V.m370a(androidx.fragment.app.H, java.util.ArrayList, java.util.ArrayList, int, int, boolean):void");
    }

    /* renamed from: b */
    private static Object m378b(C0416ea eaVar, C0424j jVar, boolean z) {
        Object obj;
        if (jVar == null) {
            return null;
        }
        if (z) {
            obj = jVar.getReturnTransition();
        } else {
            obj = jVar.getExitTransition();
        }
        return eaVar.mo4224j(obj);
    }

    /* renamed from: b */
    private static C0015b m377b(C0416ea eaVar, C0015b bVar, Object obj, C0401U u) {
        SharedElementCallback sharedElementCallback;
        ArrayList arrayList;
        if (bVar.isEmpty() || obj == null) {
            bVar.clear();
            return null;
        }
        C0424j jVar = u.firstOut;
        C0015b bVar2 = new C0015b();
        eaVar.mo4235a((Map) bVar2, jVar.requireView());
        C0407a aVar = u.firstOutTransaction;
        if (u.firstOutIsPop) {
            sharedElementCallback = jVar.getEnterTransitionCallback();
            arrayList = aVar.mSharedElementTargetNames;
        } else {
            sharedElementCallback = jVar.getExitTransitionCallback();
            arrayList = aVar.mSharedElementSourceNames;
        }
        bVar2.retainAll(arrayList);
        if (sharedElementCallback != null) {
            sharedElementCallback.onMapSharedElements(arrayList, bVar2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = (String) arrayList.get(size);
                View view = (View) bVar2.get(str);
                if (view == null) {
                    bVar.remove(str);
                } else if (!str.equals(ViewCompat.getTransitionName(view))) {
                    bVar.put(ViewCompat.getTransitionName(view), (String) bVar.remove(str));
                }
            }
        } else {
            bVar.retainAll(bVar2.keySet());
        }
        return bVar2;
    }

    /* renamed from: a */
    private static C0416ea m364a(C0424j jVar, C0424j jVar2) {
        ArrayList arrayList = new ArrayList();
        if (jVar != null) {
            Object exitTransition = jVar.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = jVar.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = jVar.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (jVar2 != null) {
            Object enterTransition = jVar2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = jVar2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = jVar2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        C0416ea eaVar = f389wp;
        if (eaVar != null && m376a(eaVar, (List) arrayList)) {
            return f389wp;
        }
        C0416ea eaVar2 = f390xp;
        if (eaVar2 != null && m376a(eaVar2, (List) arrayList)) {
            return f390xp;
        }
        if (f389wp == null && f390xp == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    /* renamed from: a */
    private static boolean m376a(C0416ea eaVar, List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!eaVar.mo4223i(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private static Object m365a(C0416ea eaVar, C0424j jVar, C0424j jVar2, boolean z) {
        Object obj;
        if (jVar == null || jVar2 == null) {
            return null;
        }
        if (z) {
            obj = jVar2.getSharedElementReturnTransition();
        } else {
            obj = jVar.getSharedElementEnterTransition();
        }
        Object j = eaVar.mo4224j(obj);
        C0408aa aaVar = (C0408aa) eaVar;
        if (j == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition((Transition) j);
        return transitionSet;
    }

    /* renamed from: a */
    private static Object m366a(C0416ea eaVar, C0424j jVar, boolean z) {
        Object obj;
        if (jVar == null) {
            return null;
        }
        if (z) {
            obj = jVar.getReenterTransition();
        } else {
            obj = jVar.getEnterTransition();
        }
        return eaVar.mo4224j(obj);
    }

    /* renamed from: a */
    private static void m375a(ArrayList arrayList, C0015b bVar, Collection collection) {
        for (int size = bVar.size() - 1; size >= 0; size--) {
            View view = (View) bVar.valueAt(size);
            if (collection.contains(ViewCompat.getTransitionName(view))) {
                arrayList.add(view);
            }
        }
    }

    /* renamed from: a */
    static C0015b m362a(C0416ea eaVar, C0015b bVar, Object obj, C0401U u) {
        SharedElementCallback sharedElementCallback;
        ArrayList arrayList;
        String a;
        C0424j jVar = u.lastIn;
        View view = jVar.getView();
        if (bVar.isEmpty() || obj == null || view == null) {
            bVar.clear();
            return null;
        }
        C0015b bVar2 = new C0015b();
        eaVar.mo4235a((Map) bVar2, view);
        C0407a aVar = u.lastInTransaction;
        if (u.lastInIsPop) {
            sharedElementCallback = jVar.getExitTransitionCallback();
            arrayList = aVar.mSharedElementSourceNames;
        } else {
            sharedElementCallback = jVar.getEnterTransitionCallback();
            arrayList = aVar.mSharedElementTargetNames;
        }
        if (arrayList != null) {
            bVar2.retainAll(arrayList);
            bVar2.retainAll(bVar.values());
        }
        if (sharedElementCallback == null) {
            int size = bVar.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else if (!bVar2.containsKey((String) bVar.valueAt(size))) {
                    bVar.removeAt(size);
                }
            }
        } else {
            sharedElementCallback.onMapSharedElements(arrayList, bVar2);
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                String str = (String) arrayList.get(size2);
                View view2 = (View) bVar2.get(str);
                if (view2 == null) {
                    String a2 = m368a(bVar, str);
                    if (a2 != null) {
                        bVar.remove(a2);
                    }
                } else if (!str.equals(ViewCompat.getTransitionName(view2)) && (a = m368a(bVar, str)) != null) {
                    bVar.put(a, ViewCompat.getTransitionName(view2));
                }
            }
        }
        return bVar2;
    }

    /* renamed from: a */
    private static String m368a(C0015b bVar, String str) {
        int size = bVar.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(bVar.valueAt(i))) {
                return (String) bVar.keyAt(i);
            }
        }
        return null;
    }

    /* renamed from: a */
    static View m363a(C0015b bVar, C0401U u, Object obj, boolean z) {
        ArrayList arrayList;
        String str;
        C0407a aVar = u.lastInTransaction;
        if (obj == null || bVar == null || (arrayList = aVar.mSharedElementSourceNames) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = (String) aVar.mSharedElementSourceNames.get(0);
        } else {
            str = (String) aVar.mSharedElementTargetNames.get(0);
        }
        return (View) bVar.get(str);
    }

    /* renamed from: a */
    private static void m372a(C0416ea eaVar, Object obj, Object obj2, C0015b bVar, boolean z, C0407a aVar) {
        String str;
        ArrayList arrayList = aVar.mSharedElementSourceNames;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (z) {
                str = (String) aVar.mSharedElementTargetNames.get(0);
            } else {
                str = (String) aVar.mSharedElementSourceNames.get(0);
            }
            View view = (View) bVar.get(str);
            eaVar.mo4220b(obj, view);
            if (obj2 != null) {
                eaVar.mo4220b(obj2, view);
            }
        }
    }

    /* renamed from: a */
    static void m373a(C0424j jVar, C0424j jVar2, boolean z, C0015b bVar, boolean z2) {
        SharedElementCallback sharedElementCallback;
        int i;
        if (z) {
            sharedElementCallback = jVar2.getEnterTransitionCallback();
        } else {
            sharedElementCallback = jVar.getEnterTransitionCallback();
        }
        if (sharedElementCallback != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (bVar == null) {
                i = 0;
            } else {
                i = bVar.size();
            }
            for (int i2 = 0; i2 < i; i2++) {
                arrayList2.add(bVar.keyAt(i2));
                arrayList.add(bVar.valueAt(i2));
            }
            if (z2) {
                sharedElementCallback.onSharedElementStart(arrayList2, arrayList, (List) null);
            } else {
                sharedElementCallback.onSharedElementEnd(arrayList2, arrayList, (List) null);
            }
        }
    }

    /* renamed from: a */
    static ArrayList m369a(C0416ea eaVar, Object obj, C0424j jVar, ArrayList arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        View view2 = jVar.getView();
        if (view2 != null) {
            eaVar.mo4234a(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        eaVar.mo4217a(obj, arrayList2);
        return arrayList2;
    }

    /* renamed from: a */
    static void m374a(ArrayList arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((View) arrayList.get(size)).setVisibility(i);
            }
        }
    }

    /* renamed from: a */
    private static Object m367a(C0416ea eaVar, Object obj, Object obj2, Object obj3, C0424j jVar, boolean z) {
        boolean z2;
        if (obj == null || obj2 == null || jVar == null) {
            z2 = true;
        } else {
            z2 = z ? jVar.getAllowReturnTransitionOverlap() : jVar.getAllowEnterTransitionOverlap();
        }
        if (z2) {
            return eaVar.mo4219b(obj2, obj, obj3);
        }
        return eaVar.mo4211a(obj2, obj, obj3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0041, code lost:
        if (r10.mAdded != false) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x007f, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0091, code lost:
        if (r10.mHidden == false) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0093, code lost:
        r1 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x00f5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:99:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m371a(androidx.fragment.app.C0407a r16, androidx.fragment.app.C0395N r17, android.util.SparseArray r18, boolean r19, boolean r20) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            androidx.fragment.app.j r10 = r1.f358Zo
            if (r10 != 0) goto L_0x000d
            return
        L_0x000d:
            int r11 = r10.mContainerId
            if (r11 != 0) goto L_0x0012
            return
        L_0x0012:
            if (r3 == 0) goto L_0x001b
            int[] r4 = f388vp
            int r1 = r1.mCmd
            r1 = r4[r1]
            goto L_0x001d
        L_0x001b:
            int r1 = r1.mCmd
        L_0x001d:
            r4 = 0
            r5 = 1
            if (r1 == r5) goto L_0x0086
            r6 = 3
            if (r1 == r6) goto L_0x005f
            r6 = 4
            if (r1 == r6) goto L_0x0047
            r6 = 5
            if (r1 == r6) goto L_0x0035
            r6 = 6
            if (r1 == r6) goto L_0x005f
            r6 = 7
            if (r1 == r6) goto L_0x0086
            r1 = r4
            r12 = r1
            r13 = r12
            goto L_0x009a
        L_0x0035:
            if (r20 == 0) goto L_0x0044
            boolean r1 = r10.mHiddenChanged
            if (r1 == 0) goto L_0x0095
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x0095
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x0095
            goto L_0x0093
        L_0x0044:
            boolean r1 = r10.mHidden
            goto L_0x0096
        L_0x0047:
            if (r20 == 0) goto L_0x0056
            boolean r1 = r10.mHiddenChanged
            if (r1 == 0) goto L_0x0081
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x0081
            boolean r1 = r10.mHidden
            if (r1 == 0) goto L_0x0081
            goto L_0x007f
        L_0x0056:
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x0081
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x0081
            goto L_0x007f
        L_0x005f:
            if (r20 == 0) goto L_0x0077
            boolean r1 = r10.mAdded
            if (r1 != 0) goto L_0x0081
            android.view.View r1 = r10.mView
            if (r1 == 0) goto L_0x0081
            int r1 = r1.getVisibility()
            if (r1 != 0) goto L_0x0081
            float r1 = r10.mPostponedAlpha
            r6 = 0
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x0081
            goto L_0x007f
        L_0x0077:
            boolean r1 = r10.mAdded
            if (r1 == 0) goto L_0x0081
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x0081
        L_0x007f:
            r1 = r5
            goto L_0x0082
        L_0x0081:
            r1 = r4
        L_0x0082:
            r13 = r1
            r1 = r4
            r12 = r5
            goto L_0x009a
        L_0x0086:
            if (r20 == 0) goto L_0x008b
            boolean r1 = r10.mIsNewlyAdded
            goto L_0x0096
        L_0x008b:
            boolean r1 = r10.mAdded
            if (r1 != 0) goto L_0x0095
            boolean r1 = r10.mHidden
            if (r1 != 0) goto L_0x0095
        L_0x0093:
            r1 = r5
            goto L_0x0096
        L_0x0095:
            r1 = r4
        L_0x0096:
            r12 = r4
            r13 = r12
            r4 = r1
            r1 = r5
        L_0x009a:
            java.lang.Object r6 = r2.get(r11)
            androidx.fragment.app.U r6 = (androidx.fragment.app.C0401U) r6
            if (r4 == 0) goto L_0x00b3
            if (r6 != 0) goto L_0x00ad
            androidx.fragment.app.U r4 = new androidx.fragment.app.U
            r4.<init>()
            r2.put(r11, r4)
            r6 = r4
        L_0x00ad:
            r6.lastIn = r10
            r6.lastInIsPop = r3
            r6.lastInTransaction = r0
        L_0x00b3:
            r14 = r6
            r15 = 0
            if (r20 != 0) goto L_0x00da
            if (r1 == 0) goto L_0x00da
            if (r14 == 0) goto L_0x00c1
            androidx.fragment.app.j r1 = r14.firstOut
            if (r1 != r10) goto L_0x00c1
            r14.firstOut = r15
        L_0x00c1:
            androidx.fragment.app.H r4 = r0.mManager
            int r1 = r10.mState
            if (r1 >= r5) goto L_0x00da
            int r1 = r4.mCurState
            if (r1 < r5) goto L_0x00da
            boolean r1 = r0.mReorderingAllowed
            if (r1 != 0) goto L_0x00da
            r4.mo4128g(r10)
            r6 = 1
            r7 = 0
            r8 = 0
            r9 = 0
            r5 = r10
            r4.mo4079a(r5, r6, r7, r8, r9)
        L_0x00da:
            if (r13 == 0) goto L_0x00f3
            if (r14 == 0) goto L_0x00e2
            androidx.fragment.app.j r1 = r14.firstOut
            if (r1 != 0) goto L_0x00f3
        L_0x00e2:
            if (r14 != 0) goto L_0x00ed
            androidx.fragment.app.U r1 = new androidx.fragment.app.U
            r1.<init>()
            r2.put(r11, r1)
            r14 = r1
        L_0x00ed:
            r14.firstOut = r10
            r14.firstOutIsPop = r3
            r14.firstOutTransaction = r0
        L_0x00f3:
            if (r20 != 0) goto L_0x00ff
            if (r12 == 0) goto L_0x00ff
            if (r14 == 0) goto L_0x00ff
            androidx.fragment.app.j r0 = r14.lastIn
            if (r0 != r10) goto L_0x00ff
            r14.lastIn = r15
        L_0x00ff:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0402V.m371a(androidx.fragment.app.a, androidx.fragment.app.N, android.util.SparseArray, boolean, boolean):void");
    }
}
