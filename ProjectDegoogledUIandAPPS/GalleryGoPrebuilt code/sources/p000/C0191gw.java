package p000;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* renamed from: gw */
/* compiled from: PG */
final class C0191gw {

    /* renamed from: a */
    public static /* synthetic */ int f12166a;

    /* renamed from: b */
    private static final int[] f12167b = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};

    /* renamed from: c */
    private static final C0202hg f12168c = new C0198hc();

    /* renamed from: d */
    private static final C0202hg f12169d;

    static {
        C0202hg hgVar;
        int i = Build.VERSION.SDK_INT;
        try {
            hgVar = (C0202hg) Class.forName("afg").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            hgVar = null;
        }
        f12169d = hgVar;
    }

    /* renamed from: a */
    private static void m10922a(ArrayList arrayList, C0290kn knVar, Collection collection) {
        for (int i = knVar.f15193b - 1; i >= 0; i--) {
            View view = (View) knVar.mo9321c(i);
            if (collection.contains(C0340mj.m14722m(view))) {
                arrayList.add(view);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003b, code lost:
        if (r0.f9597p != false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0075, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0088, code lost:
        if (r0.f9565D == false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x008a, code lost:
        r9 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00e0 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:92:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m10918a(p000.C0133eu r8, p000.C0180gm r9, android.util.SparseArray r10, boolean r11, boolean r12) {
        /*
            fh r0 = r9.f11607b
            if (r0 == 0) goto L_0x00ec
            int r1 = r0.f9563B
            if (r1 == 0) goto L_0x00ec
            if (r11 == 0) goto L_0x0011
            int[] r2 = f12167b
            int r9 = r9.f11606a
            r9 = r2[r9]
            goto L_0x0013
        L_0x0011:
            int r9 = r9.f11606a
        L_0x0013:
            r2 = 1
            r3 = 0
            if (r9 == r2) goto L_0x007d
            r4 = 3
            if (r9 == r4) goto L_0x0056
            r4 = 4
            if (r9 == r4) goto L_0x003e
            r4 = 5
            if (r9 == r4) goto L_0x002b
            r4 = 6
            if (r9 == r4) goto L_0x0056
            r4 = 7
            if (r9 == r4) goto L_0x007d
            r9 = 0
            r4 = 0
            r5 = 0
            goto L_0x0092
        L_0x002b:
            if (r12 != 0) goto L_0x0031
            boolean r9 = r0.f9565D
            goto L_0x008d
        L_0x0031:
            boolean r9 = r0.f9578Q
            if (r9 == 0) goto L_0x008c
            boolean r9 = r0.f9565D
            if (r9 != 0) goto L_0x008c
            boolean r9 = r0.f9597p
            if (r9 == 0) goto L_0x008c
            goto L_0x008a
        L_0x003e:
            if (r12 == 0) goto L_0x004d
            boolean r9 = r0.f9578Q
            if (r9 == 0) goto L_0x0077
            boolean r9 = r0.f9597p
            if (r9 == 0) goto L_0x0077
            boolean r9 = r0.f9565D
            if (r9 == 0) goto L_0x0077
            goto L_0x0055
        L_0x004d:
            boolean r9 = r0.f9597p
            if (r9 == 0) goto L_0x0077
            boolean r9 = r0.f9565D
            if (r9 != 0) goto L_0x0077
        L_0x0055:
            goto L_0x0075
        L_0x0056:
            boolean r9 = r0.f9597p
            if (r12 == 0) goto L_0x006f
            if (r9 != 0) goto L_0x0077
            android.view.View r9 = r0.f9573L
            if (r9 != 0) goto L_0x0061
            goto L_0x0077
        L_0x0061:
            int r9 = r9.getVisibility()
            if (r9 != 0) goto L_0x0077
            float r9 = r0.f9579R
            r4 = 0
            int r9 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r9 < 0) goto L_0x0077
            goto L_0x0075
        L_0x006f:
            if (r9 == 0) goto L_0x0077
            boolean r9 = r0.f9565D
            if (r9 != 0) goto L_0x0077
        L_0x0075:
            r9 = 1
            goto L_0x0078
        L_0x0077:
            r9 = 0
        L_0x0078:
            r5 = r9
            r9 = 0
            r4 = 1
            goto L_0x0092
        L_0x007d:
            if (r12 == 0) goto L_0x0082
            boolean r9 = r0.f9577P
            goto L_0x008d
        L_0x0082:
            boolean r9 = r0.f9597p
            if (r9 != 0) goto L_0x008c
            boolean r9 = r0.f9565D
            if (r9 != 0) goto L_0x008c
        L_0x008a:
            r9 = 1
            goto L_0x008d
        L_0x008c:
            r9 = 0
        L_0x008d:
            r3 = r9
            r9 = 1
            r4 = 0
            r5 = 0
        L_0x0092:
            java.lang.Object r6 = r10.get(r1)
            gv r6 = (p000.C0190gv) r6
            if (r3 == 0) goto L_0x00a5
            gv r6 = m10913a((p000.C0190gv) r6, (android.util.SparseArray) r10, (int) r1)
            r6.f12114a = r0
            r6.f12115b = r11
            r6.f12116c = r8
            goto L_0x00a6
        L_0x00a5:
        L_0x00a6:
            r3 = 0
            if (r12 != 0) goto L_0x00c9
            if (r9 == 0) goto L_0x00c9
            if (r6 == 0) goto L_0x00b5
            fh r9 = r6.f12117d
            if (r9 == r0) goto L_0x00b2
            goto L_0x00b5
        L_0x00b2:
            r6.f12117d = r3
        L_0x00b5:
            gd r9 = r8.f9018a
            int r7 = r0.f9587f
            if (r7 > 0) goto L_0x00c9
            int r7 = r9.f10990i
            if (r7 <= 0) goto L_0x00c9
            boolean r7 = r8.f11659r
            if (r7 != 0) goto L_0x00c9
            r9.mo6445d((p000.C0147fh) r0)
            r9.mo6423a((p000.C0147fh) r0, (int) r2)
        L_0x00c9:
            if (r5 != 0) goto L_0x00cc
        L_0x00cb:
            goto L_0x00de
        L_0x00cc:
            if (r6 == 0) goto L_0x00d2
            fh r9 = r6.f12117d
            if (r9 != 0) goto L_0x00cb
        L_0x00d2:
            gv r6 = m10913a((p000.C0190gv) r6, (android.util.SparseArray) r10, (int) r1)
            r6.f12117d = r0
            r6.f12118e = r11
            r6.f12119f = r8
        L_0x00de:
            if (r12 != 0) goto L_0x00eb
            if (r4 == 0) goto L_0x00eb
            if (r6 != 0) goto L_0x00e5
            goto L_0x00eb
        L_0x00e5:
            fh r8 = r6.f12114a
            if (r8 != r0) goto L_0x00eb
            r6.f12114a = r3
        L_0x00eb:
            return
        L_0x00ec:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0191gw.m10918a(eu, gm, android.util.SparseArray, boolean, boolean):void");
    }

    /* renamed from: a */
    private static boolean m10923a(C0202hg hgVar, List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!hgVar.mo289a(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    static C0290kn m10917a(C0202hg hgVar, C0290kn knVar, Object obj, C0190gv gvVar) {
        ArrayList arrayList;
        C0147fh fhVar = gvVar.f12114a;
        View view = fhVar.f9573L;
        if (knVar.isEmpty() || obj == null || view == null) {
            knVar.clear();
            return null;
        }
        C0290kn knVar2 = new C0290kn();
        hgVar.mo7392a((Map) knVar2, view);
        C0133eu euVar = gvVar.f12116c;
        if (gvVar.f12115b) {
            fhVar.mo5632M();
            arrayList = euVar.f11657p;
        } else {
            fhVar.mo5631L();
            arrayList = euVar.f11658q;
        }
        if (arrayList != null) {
            C0304la.m14561a((Map) knVar2, (Collection) arrayList);
            C0304la.m14561a((Map) knVar2, knVar.values());
        }
        for (int i = knVar.f15193b - 1; i >= 0; i--) {
            if (!knVar2.containsKey((String) knVar.mo9321c(i))) {
                knVar.mo1935d(i);
            }
        }
        return knVar2;
    }

    /* renamed from: b */
    private static C0290kn m10925b(C0202hg hgVar, C0290kn knVar, Object obj, C0190gv gvVar) {
        ArrayList arrayList;
        if (!knVar.isEmpty() && obj != null) {
            C0147fh fhVar = gvVar.f12117d;
            C0290kn knVar2 = new C0290kn();
            hgVar.mo7392a((Map) knVar2, fhVar.mo5663u());
            C0133eu euVar = gvVar.f12119f;
            if (gvVar.f12118e) {
                fhVar.mo5631L();
                arrayList = euVar.f11658q;
            } else {
                fhVar.mo5632M();
                arrayList = euVar.f11657p;
            }
            if (arrayList != null) {
                C0304la.m14561a((Map) knVar2, (Collection) arrayList);
            }
            C0304la.m14561a((Map) knVar, (Collection) knVar2.keySet());
            return knVar2;
        }
        knVar.clear();
        return null;
    }

    /* renamed from: a */
    private static C0202hg m10914a(C0147fh fhVar, C0147fh fhVar2) {
        Object z;
        ArrayList arrayList = new ArrayList();
        if (fhVar != null) {
            Object y = fhVar.mo5664y();
            if (y != null) {
                arrayList.add(y);
            }
            Object A = fhVar.mo5620A();
            if (A != null) {
                arrayList.add(A);
            }
        }
        if (!(fhVar2 == null || (z = fhVar2.mo5665z()) == null)) {
            arrayList.add(z);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        C0202hg hgVar = f12168c;
        if (hgVar != null && m10923a(hgVar, (List) arrayList)) {
            return f12168c;
        }
        C0202hg hgVar2 = f12169d;
        if (hgVar2 != null && m10923a(hgVar2, (List) arrayList)) {
            return f12169d;
        }
        if (f12168c == null && f12169d == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    /* renamed from: a */
    static ArrayList m10916a(C0202hg hgVar, Object obj, C0147fh fhVar, ArrayList arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        View view2 = fhVar.f9573L;
        if (view2 != null) {
            hgVar.mo7391a(arrayList2, view2);
        }
        arrayList2.removeAll(arrayList);
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        hgVar.mo286a(obj, arrayList2);
        return arrayList2;
    }

    /* renamed from: a */
    private static C0190gv m10913a(C0190gv gvVar, SparseArray sparseArray, int i) {
        if (gvVar != null) {
            return gvVar;
        }
        C0190gv gvVar2 = new C0190gv();
        sparseArray.put(i, gvVar2);
        return gvVar2;
    }

    /* renamed from: a */
    private static Object m10915a(C0202hg hgVar, C0147fh fhVar, boolean z) {
        Object obj = null;
        if (fhVar == null) {
            return null;
        }
        if (z) {
            obj = fhVar.mo5665z();
        }
        return hgVar.mo290b(obj);
    }

    /* renamed from: b */
    private static Object m10924b(C0202hg hgVar, C0147fh fhVar, boolean z) {
        Object obj = null;
        if (fhVar == null) {
            return null;
        }
        if (z) {
            obj = fhVar.mo5664y();
        }
        return hgVar.mo290b(obj);
    }

    /* renamed from: a */
    static View m10912a(C0290kn knVar, C0190gv gvVar, Object obj, boolean z) {
        ArrayList arrayList;
        String str;
        C0133eu euVar = gvVar.f12116c;
        if (obj == null || knVar == null || (arrayList = euVar.f11657p) == null || arrayList.isEmpty()) {
            return null;
        }
        if (!z) {
            str = (String) euVar.f11658q.get(0);
        } else {
            str = (String) euVar.f11657p.get(0);
        }
        return (View) knVar.get(str);
    }

    /* renamed from: c */
    private static Object m10926c(C0202hg hgVar, C0147fh fhVar, boolean z) {
        Object obj;
        if (z) {
            obj = fhVar.mo5620A();
        } else {
            obj = null;
        }
        return hgVar.mo294c(hgVar.mo290b(obj));
    }

    /* renamed from: a */
    private static void m10920a(C0202hg hgVar, Object obj, Object obj2, C0290kn knVar, boolean z, C0133eu euVar) {
        String str;
        ArrayList arrayList = euVar.f11657p;
        if (arrayList != null && !arrayList.isEmpty()) {
            if (!z) {
                str = (String) euVar.f11657p.get(0);
            } else {
                str = (String) euVar.f11658q.get(0);
            }
            View view = (View) knVar.get(str);
            hgVar.mo283a(obj, view);
            if (obj2 != null) {
                hgVar.mo283a(obj2, view);
            }
        }
    }

    /* renamed from: a */
    static void m10921a(ArrayList arrayList, int i) {
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((View) arrayList.get(size)).setVisibility(i);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r3v8, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x024b  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x027a A[LOOP:7: B:111:0x0278->B:112:0x027a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0200 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x021f A[ADDED_TO_REGION] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void m10919a(p000.C0171gd r36, java.util.ArrayList r37, java.util.ArrayList r38, int r39, int r40, boolean r41, p000.C0189gu r42) {
        /*
            r0 = r36
            r1 = r37
            r2 = r38
            r3 = r40
            r4 = r41
            r5 = r42
            int r6 = r0.f10990i
            if (r6 <= 0) goto L_0x04c8
            android.util.SparseArray r6 = new android.util.SparseArray
            r6.<init>()
            r7 = r39
        L_0x0017:
            r8 = 1
            r9 = 0
            if (r7 >= r3) goto L_0x0069
            java.lang.Object r10 = r1.get(r7)
            eu r10 = (p000.C0133eu) r10
            java.lang.Object r11 = r2.get(r7)
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 != 0) goto L_0x0044
            java.util.ArrayList r8 = r10.f11644c
            int r8 = r8.size()
            r11 = 0
        L_0x0034:
            if (r11 >= r8) goto L_0x0066
            java.util.ArrayList r12 = r10.f11644c
            java.lang.Object r12 = r12.get(r11)
            gm r12 = (p000.C0180gm) r12
            m10918a((p000.C0133eu) r10, (p000.C0180gm) r12, (android.util.SparseArray) r6, (boolean) r9, (boolean) r4)
            int r11 = r11 + 1
            goto L_0x0034
        L_0x0044:
            gd r9 = r10.f9018a
            fq r9 = r9.f10992k
            boolean r9 = r9.mo5558Z()
            if (r9 == 0) goto L_0x0066
            java.util.ArrayList r9 = r10.f11644c
            int r9 = r9.size()
            int r9 = r9 + -1
        L_0x0056:
            if (r9 < 0) goto L_0x0066
            java.util.ArrayList r11 = r10.f11644c
            java.lang.Object r11 = r11.get(r9)
            gm r11 = (p000.C0180gm) r11
            m10918a((p000.C0133eu) r10, (p000.C0180gm) r11, (android.util.SparseArray) r6, (boolean) r8, (boolean) r4)
            int r9 = r9 + -1
            goto L_0x0056
        L_0x0066:
            int r7 = r7 + 1
            goto L_0x0017
        L_0x0069:
            int r7 = r6.size()
            if (r7 == 0) goto L_0x04c8
            android.view.View r7 = new android.view.View
            fu r10 = r0.f10991j
            android.content.Context r10 = r10.f10593c
            r7.<init>(r10)
            int r15 = r6.size()
            r14 = 0
        L_0x007d:
            if (r14 >= r15) goto L_0x04c8
            int r10 = r6.keyAt(r14)
            kn r13 = new kn
            r13.<init>()
            int r11 = r3 + -1
        L_0x008a:
            r12 = r39
            if (r11 < r12) goto L_0x011c
            java.lang.Object r16 = r1.get(r11)
            r8 = r16
            eu r8 = (p000.C0133eu) r8
            java.util.ArrayList r9 = r8.f11644c
            int r9 = r9.size()
            r1 = 0
        L_0x009d:
            if (r1 >= r9) goto L_0x0110
            java.util.ArrayList r3 = r8.f11644c
            java.lang.Object r3 = r3.get(r1)
            gm r3 = (p000.C0180gm) r3
            fh r3 = r3.f11607b
            if (r3 == 0) goto L_0x00ae
            int r3 = r3.f9563B
            goto L_0x00b0
        L_0x00ae:
            r3 = 0
        L_0x00b0:
            if (r3 != 0) goto L_0x00b3
        L_0x00b2:
            goto L_0x0109
        L_0x00b3:
            if (r3 != r10) goto L_0x00b2
            java.lang.Object r1 = r2.get(r11)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            java.util.ArrayList r3 = r8.f11657p
            if (r3 != 0) goto L_0x00c4
            goto L_0x0110
        L_0x00c4:
            int r3 = r3.size()
            if (r1 == 0) goto L_0x00cf
            java.util.ArrayList r1 = r8.f11657p
            java.util.ArrayList r8 = r8.f11658q
            goto L_0x00d8
        L_0x00cf:
            java.util.ArrayList r1 = r8.f11657p
            java.util.ArrayList r8 = r8.f11658q
            r35 = r8
            r8 = r1
            r1 = r35
        L_0x00d8:
            r9 = 0
        L_0x00d9:
            if (r9 >= r3) goto L_0x0110
            java.lang.Object r16 = r8.get(r9)
            r2 = r16
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r16 = r1.get(r9)
            r17 = r1
            r1 = r16
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r16 = r13.remove(r1)
            r18 = r3
            r3 = r16
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto L_0x00fd
            r13.put(r2, r3)
            goto L_0x0100
        L_0x00fd:
            r13.put(r2, r1)
        L_0x0100:
            int r9 = r9 + 1
            r2 = r38
            r1 = r17
            r3 = r18
            goto L_0x00d9
        L_0x0109:
            int r1 = r1 + 1
            r2 = r38
            r3 = r40
            goto L_0x009d
        L_0x0110:
            int r11 = r11 + -1
            r1 = r37
            r2 = r38
            r3 = r40
            r8 = 1
            r9 = 0
            goto L_0x008a
        L_0x011c:
            java.lang.Object r1 = r6.valueAt(r14)
            gv r1 = (p000.C0190gv) r1
            if (r4 == 0) goto L_0x0339
            fq r3 = r0.f10992k
            boolean r3 = r3.mo5558Z()
            if (r3 == 0) goto L_0x0135
            fq r3 = r0.f10992k
            android.view.View r3 = r3.mo5559a(r10)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            goto L_0x0137
        L_0x0135:
            r3 = 0
        L_0x0137:
            if (r3 == 0) goto L_0x032a
            fh r8 = r1.f12114a
            fh r9 = r1.f12117d
            hg r10 = m10914a((p000.C0147fh) r9, (p000.C0147fh) r8)
            if (r10 == 0) goto L_0x031b
            boolean r11 = r1.f12115b
            boolean r2 = r1.f12118e
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r31 = r6
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.lang.Object r11 = m10915a((p000.C0202hg) r10, (p000.C0147fh) r8, (boolean) r11)
            java.lang.Object r2 = m10924b(r10, r9, r2)
            fh r12 = r1.f12114a
            r16 = r14
            fh r14 = r1.f12117d
            if (r12 == 0) goto L_0x016e
            r17 = r15
            android.view.View r15 = r12.mo5663u()
            r0 = 0
            r15.setVisibility(r0)
            goto L_0x0170
        L_0x016e:
            r17 = r15
        L_0x0170:
            if (r12 != 0) goto L_0x0177
        L_0x0172:
            r18 = r13
        L_0x0174:
            r12 = 0
            goto L_0x01fe
        L_0x0177:
            if (r14 == 0) goto L_0x01fa
            boolean r0 = r1.f12115b
            boolean r12 = r13.isEmpty()
            if (r12 != 0) goto L_0x0186
            java.lang.Object r12 = m10926c(r10, r14, r0)
            goto L_0x0188
        L_0x0186:
            r12 = 0
        L_0x0188:
            kn r14 = m10925b(r10, r13, r12, r1)
            kn r15 = m10917a((p000.C0202hg) r10, (p000.C0290kn) r13, (java.lang.Object) r12, (p000.C0190gv) r1)
            boolean r18 = r13.isEmpty()
            if (r18 != 0) goto L_0x01a9
            r18 = r12
            java.util.Set r12 = r13.keySet()
            m10922a((java.util.ArrayList) r6, (p000.C0290kn) r14, (java.util.Collection) r12)
            java.util.Collection r12 = r13.values()
            m10922a((java.util.ArrayList) r4, (p000.C0290kn) r15, (java.util.Collection) r12)
            r12 = r18
            goto L_0x01b8
        L_0x01a9:
            if (r14 == 0) goto L_0x01ae
            r14.clear()
        L_0x01ae:
            if (r15 == 0) goto L_0x01b6
            r15.clear()
            r12 = 0
            goto L_0x01b8
        L_0x01b6:
            r12 = 0
        L_0x01b8:
            if (r11 != 0) goto L_0x01c0
            if (r2 != 0) goto L_0x01c0
            if (r12 == 0) goto L_0x01bf
            goto L_0x01c0
        L_0x01bf:
            goto L_0x0172
        L_0x01c0:
            if (r12 == 0) goto L_0x01ed
            r4.add(r7)
            r10.mo284a((java.lang.Object) r12, (android.view.View) r7, (java.util.ArrayList) r6)
            r18 = r13
            boolean r13 = r1.f12118e
            eu r5 = r1.f12119f
            r22 = r10
            r23 = r12
            r24 = r2
            r25 = r14
            r26 = r13
            r27 = r5
            m10920a(r22, r23, r24, r25, r26, r27)
            android.graphics.Rect r5 = new android.graphics.Rect
            r5.<init>()
            android.view.View r0 = m10912a((p000.C0290kn) r15, (p000.C0190gv) r1, (java.lang.Object) r11, (boolean) r0)
            if (r0 != 0) goto L_0x01e9
            goto L_0x01f1
        L_0x01e9:
            r10.mo282a((java.lang.Object) r11, (android.graphics.Rect) r5)
            goto L_0x01f1
        L_0x01ed:
            r18 = r13
            r0 = 0
            r5 = 0
        L_0x01f1:
            gs r1 = new gs
            r1.<init>(r0, r5)
            p000.C0331ma.m14662a(r3, r1)
            goto L_0x01fe
        L_0x01fa:
            r18 = r13
            goto L_0x0174
        L_0x01fe:
            if (r11 != 0) goto L_0x020d
            if (r12 != 0) goto L_0x020d
            if (r2 == 0) goto L_0x0205
            goto L_0x020d
        L_0x0205:
            r1 = r42
            r9 = r16
            r30 = r17
            goto L_0x04b3
        L_0x020d:
            java.util.ArrayList r0 = m10916a((p000.C0202hg) r10, (java.lang.Object) r2, (p000.C0147fh) r9, (java.util.ArrayList) r6, (android.view.View) r7)
            java.util.ArrayList r1 = m10916a((p000.C0202hg) r10, (java.lang.Object) r11, (p000.C0147fh) r8, (java.util.ArrayList) r4, (android.view.View) r7)
            r5 = 4
            m10921a((java.util.ArrayList) r1, (int) r5)
            java.lang.Object r5 = r10.mo280a((java.lang.Object) r2, (java.lang.Object) r11, (java.lang.Object) r12)
            if (r9 == 0) goto L_0x0245
            if (r0 == 0) goto L_0x0245
            int r8 = r0.size()
            if (r8 <= 0) goto L_0x0228
        L_0x0227:
            goto L_0x022f
        L_0x0228:
            int r8 = r6.size()
            if (r8 <= 0) goto L_0x0242
            goto L_0x0227
        L_0x022f:
            jj r8 = new jj
            r8.<init>()
            r15 = r42
            r15.mo6296a(r9, r8)
            go r13 = new go
            r13.<init>(r15, r9, r8)
            r10.mo288a((java.lang.Object) r5, (p000.C0259jj) r8, (java.lang.Runnable) r13)
            goto L_0x0247
        L_0x0242:
            r15 = r42
            goto L_0x0247
        L_0x0245:
            r15 = r42
        L_0x0247:
            if (r9 != 0) goto L_0x024b
            r8 = 1
            goto L_0x026e
        L_0x024b:
            if (r2 == 0) goto L_0x026d
            boolean r8 = r9.f9597p
            if (r8 == 0) goto L_0x026d
            boolean r8 = r9.f9565D
            if (r8 == 0) goto L_0x026d
            boolean r8 = r9.f9578Q
            if (r8 == 0) goto L_0x026d
            r8 = 1
            r9.mo5639a((boolean) r8)
            android.view.View r13 = r9.f9573L
            r10.mo292b((java.lang.Object) r2, (android.view.View) r13, (java.util.ArrayList) r0)
            android.view.ViewGroup r9 = r9.f9572K
            gp r13 = new gp
            r13.<init>(r0)
            p000.C0331ma.m14662a(r9, r13)
            goto L_0x026e
        L_0x026d:
            r8 = 1
        L_0x026e:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            int r13 = r4.size()
            r14 = 0
        L_0x0278:
            if (r14 >= r13) goto L_0x0295
            java.lang.Object r19 = r4.get(r14)
            r8 = r19
            android.view.View r8 = (android.view.View) r8
            r19 = r13
            java.lang.String r13 = p000.C0340mj.m14722m(r8)
            r9.add(r13)
            r13 = 0
            p000.C0340mj.m14697a((android.view.View) r8, (java.lang.String) r13)
            int r14 = r14 + 1
            r13 = r19
            r8 = 1
            goto L_0x0278
        L_0x0295:
            r22 = r10
            r23 = r5
            r24 = r11
            r25 = r1
            r26 = r2
            r27 = r0
            r28 = r12
            r29 = r4
            r22.mo285a(r23, r24, r25, r26, r27, r28, r29)
            r10.mo281a((android.view.ViewGroup) r3, (java.lang.Object) r5)
            int r0 = r4.size()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r5 = 0
        L_0x02b5:
            if (r5 >= r0) goto L_0x02f8
            java.lang.Object r8 = r6.get(r5)
            android.view.View r8 = (android.view.View) r8
            java.lang.String r11 = p000.C0340mj.m14722m(r8)
            r2.add(r11)
            if (r11 == 0) goto L_0x02ef
            r14 = 0
            p000.C0340mj.m14697a((android.view.View) r8, (java.lang.String) r14)
            r8 = r18
            java.lang.Object r13 = r8.get(r11)
            java.lang.String r13 = (java.lang.String) r13
            r14 = 0
        L_0x02d3:
            if (r14 < r0) goto L_0x02d6
            goto L_0x02f1
        L_0x02d6:
            java.lang.Object r15 = r9.get(r14)
            boolean r15 = r13.equals(r15)
            if (r15 != 0) goto L_0x02e5
            int r14 = r14 + 1
            r15 = r42
            goto L_0x02d3
        L_0x02e5:
            java.lang.Object r13 = r4.get(r14)
            android.view.View r13 = (android.view.View) r13
            p000.C0340mj.m14697a((android.view.View) r13, (java.lang.String) r11)
            goto L_0x02f1
        L_0x02ef:
            r8 = r18
        L_0x02f1:
            int r5 = r5 + 1
            r15 = r42
            r18 = r8
            goto L_0x02b5
        L_0x02f8:
            hd r5 = new hd
            r22 = r5
            r23 = r0
            r24 = r4
            r25 = r9
            r26 = r6
            r27 = r2
            r22.<init>(r23, r24, r25, r26, r27)
            p000.C0331ma.m14662a(r3, r5)
            r0 = 0
            m10921a((java.util.ArrayList) r1, (int) r0)
            r10.mo287a((java.lang.Object) r12, (java.util.ArrayList) r6, (java.util.ArrayList) r4)
            r1 = r42
            r9 = r16
            r30 = r17
            goto L_0x04b3
        L_0x031b:
            r31 = r6
            r16 = r14
            r17 = r15
            r0 = 0
            r1 = r42
            r9 = r16
            r30 = r17
            goto L_0x04b3
        L_0x032a:
            r31 = r6
            r16 = r14
            r17 = r15
            r0 = 0
            r1 = r42
            r9 = r16
            r30 = r17
            goto L_0x04b3
        L_0x0339:
            r31 = r6
            r8 = r13
            r16 = r14
            r17 = r15
            r0 = 0
            r2 = r36
            fq r3 = r2.f10992k
            boolean r3 = r3.mo5558Z()
            if (r3 == 0) goto L_0x0356
            fq r3 = r2.f10992k
            android.view.View r3 = r3.mo5559a(r10)
            r13 = r3
            android.view.ViewGroup r13 = (android.view.ViewGroup) r13
            r3 = r13
            goto L_0x0358
        L_0x0356:
            r3 = 0
        L_0x0358:
            if (r3 == 0) goto L_0x04ad
            fh r4 = r1.f12114a
            fh r5 = r1.f12117d
            hg r6 = m10914a((p000.C0147fh) r5, (p000.C0147fh) r4)
            if (r6 == 0) goto L_0x04a6
            boolean r9 = r1.f12115b
            boolean r10 = r1.f12118e
            java.lang.Object r15 = m10915a((p000.C0202hg) r6, (p000.C0147fh) r4, (boolean) r9)
            java.lang.Object r14 = m10924b(r6, r5, r10)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            fh r9 = r1.f12114a
            fh r10 = r1.f12117d
            if (r9 != 0) goto L_0x0394
        L_0x0380:
            r1 = r42
            r32 = r4
            r4 = r8
            r33 = r12
            r22 = r13
            r0 = r14
            r34 = r15
            r9 = r16
            r30 = r17
            r2 = 0
            r8 = 0
            goto L_0x0421
        L_0x0394:
            if (r10 != 0) goto L_0x0397
            goto L_0x0380
        L_0x0397:
            boolean r11 = r1.f12115b
            boolean r9 = r8.isEmpty()
            if (r9 != 0) goto L_0x03a4
            java.lang.Object r9 = m10926c(r6, r10, r11)
            goto L_0x03a6
        L_0x03a4:
            r9 = 0
        L_0x03a6:
            kn r18 = m10925b(r6, r8, r9, r1)
            boolean r10 = r8.isEmpty()
            if (r10 != 0) goto L_0x03b9
            java.util.Collection r10 = r18.values()
            r13.addAll(r10)
            r10 = r9
            goto L_0x03bb
        L_0x03b9:
            r10 = 0
        L_0x03bb:
            if (r15 != 0) goto L_0x03c3
            if (r14 != 0) goto L_0x03c3
            if (r10 == 0) goto L_0x03c2
            goto L_0x03c3
        L_0x03c2:
            goto L_0x0380
        L_0x03c3:
            if (r10 == 0) goto L_0x03f0
            android.graphics.Rect r9 = new android.graphics.Rect
            r9.<init>()
            r6.mo284a((java.lang.Object) r10, (android.view.View) r7, (java.util.ArrayList) r13)
            boolean r0 = r1.f12118e
            eu r2 = r1.f12119f
            r32 = r4
            r4 = r9
            r9 = r6
            r21 = r10
            r19 = r11
            r11 = r14
            r33 = r12
            r12 = r18
            r22 = r13
            r13 = r0
            r0 = r14
            r14 = r2
            m10920a(r9, r10, r11, r12, r13, r14)
            if (r15 == 0) goto L_0x03ec
            r6.mo282a((java.lang.Object) r15, (android.graphics.Rect) r4)
            goto L_0x03ed
        L_0x03ec:
        L_0x03ed:
            r20 = r4
            goto L_0x03fd
        L_0x03f0:
            r32 = r4
            r21 = r10
            r19 = r11
            r33 = r12
            r22 = r13
            r0 = r14
            r20 = 0
        L_0x03fd:
            gt r2 = new gt
            r10 = r2
            r11 = r6
            r12 = r8
            r4 = r8
            r13 = r21
            r9 = r16
            r8 = 0
            r14 = r1
            r1 = r42
            r34 = r15
            r30 = r17
            r15 = r33
            r16 = r7
            r17 = r19
            r18 = r22
            r19 = r34
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            p000.C0331ma.m14662a(r3, r2)
            r2 = r21
        L_0x0421:
            r11 = r34
            if (r11 == 0) goto L_0x0426
            goto L_0x042c
        L_0x0426:
            if (r2 != 0) goto L_0x042c
            if (r0 != 0) goto L_0x042c
            goto L_0x04b3
        L_0x042c:
            r10 = r22
            java.util.ArrayList r17 = m10916a((p000.C0202hg) r6, (java.lang.Object) r0, (p000.C0147fh) r5, (java.util.ArrayList) r10, (android.view.View) r7)
            if (r17 == 0) goto L_0x043d
            boolean r12 = r17.isEmpty()
            if (r12 == 0) goto L_0x043b
            goto L_0x043d
        L_0x043b:
            r8 = r0
            goto L_0x043e
        L_0x043d:
        L_0x043e:
            r6.mo291b(r11, r7)
            java.lang.Object r0 = r6.mo280a((java.lang.Object) r8, (java.lang.Object) r11, (java.lang.Object) r2)
            if (r5 == 0) goto L_0x0467
            if (r17 == 0) goto L_0x0467
            int r12 = r17.size()
            if (r12 <= 0) goto L_0x0450
        L_0x044f:
            goto L_0x0457
        L_0x0450:
            int r10 = r10.size()
            if (r10 <= 0) goto L_0x0467
            goto L_0x044f
        L_0x0457:
            jj r10 = new jj
            r10.<init>()
            r1.mo6296a(r5, r10)
            gq r12 = new gq
            r12.<init>(r1, r5, r10)
            r6.mo288a((java.lang.Object) r0, (p000.C0259jj) r10, (java.lang.Runnable) r12)
        L_0x0467:
            java.util.ArrayList r16 = new java.util.ArrayList
            r16.<init>()
            r21 = r6
            r22 = r0
            r23 = r11
            r24 = r16
            r25 = r8
            r26 = r17
            r27 = r2
            r28 = r33
            r21.mo285a(r22, r23, r24, r25, r26, r27, r28)
            gr r2 = new gr
            r10 = r2
            r12 = r6
            r13 = r7
            r14 = r32
            r15 = r33
            r18 = r8
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)
            p000.C0331ma.m14662a(r3, r2)
            he r2 = new he
            r5 = r33
            r2.<init>(r5, r4)
            p000.C0331ma.m14662a(r3, r2)
            r6.mo281a((android.view.ViewGroup) r3, (java.lang.Object) r0)
            hf r0 = new hf
            r0.<init>(r5, r4)
            p000.C0331ma.m14662a(r3, r0)
            goto L_0x04b3
        L_0x04a6:
            r1 = r42
            r9 = r16
            r30 = r17
            goto L_0x04b3
        L_0x04ad:
            r1 = r42
            r9 = r16
            r30 = r17
        L_0x04b3:
            int r14 = r9 + 1
            r0 = r36
            r2 = r38
            r3 = r40
            r4 = r41
            r5 = r1
            r15 = r30
            r6 = r31
            r8 = 1
            r9 = 0
            r1 = r37
            goto L_0x007d
        L_0x04c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0191gw.m10919a(gd, java.util.ArrayList, java.util.ArrayList, int, int, boolean, gu):void");
    }
}
