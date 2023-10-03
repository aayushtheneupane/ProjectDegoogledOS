package p000;

import android.animation.Animator;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

/* renamed from: afl */
/* compiled from: PG */
public abstract class afl implements Cloneable {

    /* renamed from: n */
    private static final int[] f319n = {2, 1, 3, 4};

    /* renamed from: p */
    private static final ThreadLocal f320p = new ThreadLocal();

    /* renamed from: v */
    private static final flw f321v = new flw((byte[]) null);

    /* renamed from: a */
    public long f322a = -1;

    /* renamed from: b */
    public long f323b = -1;

    /* renamed from: c */
    public final ArrayList f324c = new ArrayList();

    /* renamed from: d */
    public final ArrayList f325d = new ArrayList();

    /* renamed from: e */
    public afv f326e = new afv();

    /* renamed from: f */
    public afv f327f = new afv();

    /* renamed from: g */
    public afs f328g = null;

    /* renamed from: h */
    public final int[] f329h = f319n;

    /* renamed from: i */
    public ArrayList f330i;

    /* renamed from: j */
    public ArrayList f331j;

    /* renamed from: k */
    public final ArrayList f332k = new ArrayList();

    /* renamed from: l */
    public fxk f333l;

    /* renamed from: m */
    public flw f334m = f321v;

    /* renamed from: o */
    private final String f335o = getClass().getName();

    /* renamed from: q */
    private int f336q = 0;

    /* renamed from: r */
    private boolean f337r = false;

    /* renamed from: s */
    private boolean f338s = false;

    /* renamed from: t */
    private ArrayList f339t = null;

    /* renamed from: u */
    private ArrayList f340u = new ArrayList();

    /* renamed from: a */
    public Animator mo269a(ViewGroup viewGroup, afu afu, afu afu2) {
        return null;
    }

    /* renamed from: a */
    public abstract void mo270a(afu afu);

    /* renamed from: a */
    public String[] mo271a() {
        return null;
    }

    /* renamed from: b */
    public abstract void mo272b(afu afu);

    /* renamed from: c */
    public void mo315c(afu afu) {
    }

    /* renamed from: h */
    public void mo324h() {
    }

    /* renamed from: i */
    public void mo325i() {
    }

    /* renamed from: a */
    public void mo302a(afk afk) {
        if (this.f339t == null) {
            this.f339t = new ArrayList();
        }
        this.f339t.add(afk);
    }

    /* renamed from: d */
    public void mo319d(View view) {
        this.f325d.add(view);
    }

    /* renamed from: a */
    private static void m339a(afv afv, View view, afu afu) {
        afv.f358a.put(view, afu);
        int id = view.getId();
        if (id >= 0) {
            if (afv.f359b.indexOfKey(id) >= 0) {
                afv.f359b.put(id, (Object) null);
            } else {
                afv.f359b.put(id, view);
            }
        }
        String m = C0340mj.m14722m(view);
        if (m != null) {
            if (afv.f361d.containsKey(m)) {
                afv.f361d.put(m, (Object) null);
            } else {
                afv.f361d.put(m, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                C0296kt ktVar = afv.f360c;
                if (ktVar.f15154b) {
                    ktVar.mo9230a();
                }
                if (C0294kr.m14538a(ktVar.f15155c, ktVar.f15157e, itemIdAtPosition) >= 0) {
                    View view2 = (View) afv.f360c.mo9229a(itemIdAtPosition);
                    if (view2 != null) {
                        C0340mj.m14702a(view2, false);
                        afv.f360c.mo9231a(itemIdAtPosition, (Object) null);
                        return;
                    }
                    return;
                }
                C0340mj.m14702a(view, true);
                afv.f360c.mo9231a(itemIdAtPosition, view);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo322f() {
        for (int size = this.f332k.size() - 1; size >= 0; size--) {
            ((Animator) this.f332k.get(size)).cancel();
        }
        ArrayList arrayList = this.f339t;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.f339t.clone();
            int size2 = arrayList2.size();
            for (int i = 0; i < size2; i++) {
                ((afk) arrayList2.get(i)).mo264a();
            }
        }
    }

    /* renamed from: c */
    private final void m342c(View view, boolean z) {
        if (view != null) {
            view.getId();
            if (view.getParent() instanceof ViewGroup) {
                afu afu = new afu(view);
                if (z) {
                    mo270a(afu);
                } else {
                    mo272b(afu);
                }
                afu.f357c.add(this);
                mo315c(afu);
                if (z) {
                    m339a(this.f326e, view, afu);
                } else {
                    m339a(this.f327f, view, afu);
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    m342c(viewGroup.getChildAt(i), z);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo304a(ViewGroup viewGroup, boolean z) {
        mo307a(z);
        if (this.f324c.size() > 0 || this.f325d.size() > 0) {
            for (int i = 0; i < this.f324c.size(); i++) {
                View findViewById = viewGroup.findViewById(((Integer) this.f324c.get(i)).intValue());
                if (findViewById != null) {
                    afu afu = new afu(findViewById);
                    if (z) {
                        mo270a(afu);
                    } else {
                        mo272b(afu);
                    }
                    afu.f357c.add(this);
                    mo315c(afu);
                    if (z) {
                        m339a(this.f326e, findViewById, afu);
                    } else {
                        m339a(this.f327f, findViewById, afu);
                    }
                }
            }
            for (int i2 = 0; i2 < this.f325d.size(); i2++) {
                View view = (View) this.f325d.get(i2);
                afu afu2 = new afu(view);
                if (z) {
                    mo270a(afu2);
                } else {
                    mo272b(afu2);
                }
                afu2.f357c.add(this);
                mo315c(afu2);
                if (z) {
                    m339a(this.f326e, view, afu2);
                } else {
                    m339a(this.f327f, view, afu2);
                }
            }
            return;
        }
        m342c(viewGroup, z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo307a(boolean z) {
        if (z) {
            this.f326e.f358a.clear();
            this.f326e.f359b.clear();
            this.f326e.f360c.mo9234c();
            return;
        }
        this.f327f.f358a.clear();
        this.f327f.f359b.clear();
        this.f327f.f360c.mo9234c();
    }

    /* renamed from: g */
    public afl clone() {
        try {
            afl afl = (afl) super.clone();
            afl.f340u = new ArrayList();
            afl.f326e = new afv();
            afl.f327f = new afv();
            afl.f330i = null;
            afl.f331j = null;
            return afl;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x010b  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo303a(android.view.ViewGroup r19, p000.afv r20, p000.afv r21, java.util.ArrayList r22, java.util.ArrayList r23) {
        /*
            r18 = this;
            r6 = r18
            kn r7 = m341b()
            android.util.SparseIntArray r8 = new android.util.SparseIntArray
            r8.<init>()
            int r9 = r22.size()
            r11 = 0
        L_0x0010:
            if (r11 >= r9) goto L_0x0118
            r12 = r22
            java.lang.Object r0 = r12.get(r11)
            afu r0 = (p000.afu) r0
            r13 = r23
            java.lang.Object r1 = r13.get(r11)
            afu r1 = (p000.afu) r1
            if (r0 == 0) goto L_0x002f
            java.util.ArrayList r3 = r0.f357c
            boolean r3 = r3.contains(r6)
            if (r3 == 0) goto L_0x002d
            goto L_0x002f
        L_0x002d:
            r0 = 0
        L_0x002f:
            if (r1 == 0) goto L_0x003c
            java.util.ArrayList r3 = r1.f357c
            boolean r3 = r3.contains(r6)
            if (r3 == 0) goto L_0x003a
            goto L_0x003c
        L_0x003a:
            r1 = 0
        L_0x003c:
            if (r0 != 0) goto L_0x0048
            if (r1 != 0) goto L_0x0048
            r14 = r19
            r15 = r21
            r20 = r9
            goto L_0x0112
        L_0x0048:
            if (r0 == 0) goto L_0x005b
            if (r1 != 0) goto L_0x004d
            goto L_0x005b
        L_0x004d:
            boolean r3 = r6.mo308a((p000.afu) r0, (p000.afu) r1)
            if (r3 != 0) goto L_0x005b
            r14 = r19
            r15 = r21
            r20 = r9
            goto L_0x0112
        L_0x005b:
            r14 = r19
            android.animation.Animator r3 = r6.mo269a((android.view.ViewGroup) r14, (p000.afu) r0, (p000.afu) r1)
            if (r3 == 0) goto L_0x010e
            if (r1 != 0) goto L_0x006e
            android.view.View r0 = r0.f356b
            r15 = r21
            r1 = r0
            r10 = r3
        L_0x006b:
            r5 = 0
            goto L_0x00ed
        L_0x006e:
            android.view.View r0 = r1.f356b
            java.lang.String[] r1 = r18.mo271a()
            if (r1 == 0) goto L_0x00e4
            int r4 = r1.length
            if (r4 <= 0) goto L_0x00e4
            afu r4 = new afu
            r4.<init>(r0)
            r15 = r21
            kn r5 = r15.f358a
            java.lang.Object r5 = r5.get(r0)
            afu r5 = (p000.afu) r5
            if (r5 != 0) goto L_0x008d
            r16 = r3
            goto L_0x00ac
        L_0x008d:
            r2 = 0
        L_0x008f:
            int r10 = r1.length
            if (r2 >= r10) goto L_0x00aa
            java.util.Map r10 = r4.f355a
            r16 = r3
            r3 = r1[r2]
            r17 = r1
            java.util.Map r1 = r5.f355a
            java.lang.Object r1 = r1.get(r3)
            r10.put(r3, r1)
            int r2 = r2 + 1
            r3 = r16
            r1 = r17
            goto L_0x008f
        L_0x00aa:
            r16 = r3
        L_0x00ac:
            int r1 = r7.f15193b
            r2 = 0
        L_0x00af:
            if (r2 >= r1) goto L_0x00df
            java.lang.Object r3 = r7.mo9320b((int) r2)
            android.animation.Animator r3 = (android.animation.Animator) r3
            java.lang.Object r3 = r7.get(r3)
            afj r3 = (p000.afj) r3
            afu r5 = r3.f316c
            if (r5 != 0) goto L_0x00c2
        L_0x00c1:
            goto L_0x00dc
        L_0x00c2:
            android.view.View r5 = r3.f314a
            if (r5 != r0) goto L_0x00c1
            java.lang.String r5 = r3.f315b
            java.lang.String r10 = r6.f335o
            boolean r5 = r5.equals(r10)
            if (r5 == 0) goto L_0x00dc
            afu r3 = r3.f316c
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00dc
            r1 = r0
            r5 = r4
            r10 = 0
            goto L_0x00ed
        L_0x00dc:
            int r2 = r2 + 1
            goto L_0x00af
        L_0x00df:
            r1 = r0
            r5 = r4
            r10 = r16
            goto L_0x00ed
        L_0x00e4:
            r15 = r21
            r16 = r3
            r1 = r0
            r10 = r16
            goto L_0x006b
        L_0x00ed:
            if (r10 == 0) goto L_0x010b
            afj r4 = new afj
            java.lang.String r2 = r6.f335o
            agm r16 = p000.agb.m420a(r19)
            r0 = r4
            r3 = r18
            r20 = r9
            r9 = r4
            r4 = r16
            r0.<init>(r1, r2, r3, r4, r5)
            r7.put(r10, r9)
            java.util.ArrayList r0 = r6.f340u
            r0.add(r10)
            goto L_0x0112
        L_0x010b:
            r20 = r9
            goto L_0x0112
        L_0x010e:
            r15 = r21
            r20 = r9
        L_0x0112:
            int r11 = r11 + 1
            r9 = r20
            goto L_0x0010
        L_0x0118:
            int r0 = r8.size()
            if (r0 == 0) goto L_0x0147
            r10 = 0
        L_0x011f:
            int r0 = r8.size()
            if (r10 >= r0) goto L_0x0147
            int r0 = r8.keyAt(r10)
            java.util.ArrayList r1 = r6.f340u
            java.lang.Object r0 = r1.get(r0)
            android.animation.Animator r0 = (android.animation.Animator) r0
            int r1 = r8.valueAt(r10)
            long r1 = (long) r1
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r1 = r1 + r3
            long r3 = r0.getStartDelay()
            long r1 = r1 + r3
            r0.setStartDelay(r1)
            int r10 = r10 + 1
            goto L_0x011f
        L_0x0147:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.afl.mo303a(android.view.ViewGroup, afv, afv, java.util.ArrayList, java.util.ArrayList):void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public final void mo320e() {
        int i = this.f336q - 1;
        this.f336q = i;
        if (i == 0) {
            ArrayList arrayList = this.f339t;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.f339t.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((afk) arrayList2.get(i2)).mo265a(this);
                }
            }
            for (int i3 = 0; i3 < this.f326e.f360c.mo9232b(); i3++) {
                View view = (View) this.f326e.f360c.mo9233b(i3);
                if (view != null) {
                    C0340mj.m14702a(view, false);
                }
            }
            for (int i4 = 0; i4 < this.f327f.f360c.mo9232b(); i4++) {
                View view2 = (View) this.f327f.f360c.mo9233b(i4);
                if (view2 != null) {
                    C0340mj.m14702a(view2, false);
                }
            }
            this.f338s = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final afu mo310b(View view, boolean z) {
        ArrayList arrayList;
        afs afs = this.f328g;
        if (afs != null) {
            return afs.mo310b(view, z);
        }
        ArrayList arrayList2 = z ? this.f330i : this.f331j;
        if (arrayList2 == null) {
            return null;
        }
        int size = arrayList2.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            }
            afu afu = (afu) arrayList2.get(i);
            if (afu != null) {
                if (afu.f356b == view) {
                    break;
                }
                i++;
            } else {
                return null;
            }
        }
        if (i < 0) {
            return null;
        }
        if (!z) {
            arrayList = this.f330i;
        } else {
            arrayList = this.f331j;
        }
        return (afu) arrayList.get(i);
    }

    /* renamed from: b */
    public static C0290kn m341b() {
        C0290kn knVar = (C0290kn) f320p.get();
        if (knVar != null) {
            return knVar;
        }
        C0290kn knVar2 = new C0290kn();
        f320p.set(knVar2);
        return knVar2;
    }

    /* renamed from: a */
    public final afu mo299a(View view, boolean z) {
        afv afv;
        afs afs = this.f328g;
        if (afs != null) {
            return afs.mo299a(view, z);
        }
        if (!z) {
            afv = this.f327f;
        } else {
            afv = this.f326e;
        }
        return (afu) afv.f358a.get(view);
    }

    /* renamed from: a */
    public boolean mo308a(afu afu, afu afu2) {
        if (!(afu == null || afu2 == null)) {
            String[] a = mo271a();
            if (a == null) {
                for (String a2 : afu.f355a.keySet()) {
                    if (m340a(afu, afu2, a2)) {
                        return true;
                    }
                }
                return false;
            }
            for (String a3 : a) {
                if (m340a(afu, afu2, a3)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo309a(View view) {
        int id = view.getId();
        if ((this.f324c.size() == 0 && this.f325d.size() == 0) || this.f324c.contains(Integer.valueOf(id)) || this.f325d.contains(view)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m340a(afu afu, afu afu2, String str) {
        Object obj = afu.f355a.get(str);
        Object obj2 = afu2.f355a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    /* renamed from: b */
    public void mo313b(View view) {
        if (!this.f338s) {
            C0290kn b = m341b();
            int i = b.f15193b;
            agm a = agb.m420a(view);
            for (int i2 = i - 1; i2 >= 0; i2--) {
                afj afj = (afj) b.mo9321c(i2);
                if (afj.f314a != null && a.equals(afj.f317d)) {
                    int i3 = Build.VERSION.SDK_INT;
                    ((Animator) b.mo9320b(i2)).pause();
                }
            }
            ArrayList arrayList = this.f339t;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.f339t.clone();
                int size = arrayList2.size();
                for (int i4 = 0; i4 < size; i4++) {
                    ((afk) arrayList2.get(i4)).mo266b();
                }
            }
            this.f337r = true;
        }
    }

    /* renamed from: b */
    public void mo312b(afk afk) {
        ArrayList arrayList = this.f339t;
        if (arrayList != null) {
            arrayList.remove(afk);
            if (this.f339t.size() == 0) {
                this.f339t = null;
            }
        }
    }

    /* renamed from: e */
    public void mo321e(View view) {
        this.f325d.remove(view);
    }

    /* renamed from: c */
    public void mo316c(View view) {
        if (this.f337r) {
            if (!this.f338s) {
                C0290kn b = m341b();
                int i = b.f15193b;
                agm a = agb.m420a(view);
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    afj afj = (afj) b.mo9321c(i2);
                    if (afj.f314a != null && a.equals(afj.f317d)) {
                        int i3 = Build.VERSION.SDK_INT;
                        ((Animator) b.mo9320b(i2)).resume();
                    }
                }
                ArrayList arrayList = this.f339t;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.f339t.clone();
                    int size = arrayList2.size();
                    for (int i4 = 0; i4 < size; i4++) {
                        ((afk) arrayList2.get(i4)).mo267c();
                    }
                }
            }
            this.f337r = false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo314c() {
        mo318d();
        C0290kn b = m341b();
        ArrayList arrayList = this.f340u;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Animator animator = (Animator) arrayList.get(i);
            if (b.containsKey(animator)) {
                mo318d();
                if (animator != null) {
                    animator.addListener(new afh(this, b));
                    long j = this.f323b;
                    if (j >= 0) {
                        animator.setDuration(j);
                    }
                    long j2 = this.f322a;
                    if (j2 >= 0) {
                        animator.setStartDelay(j2 + animator.getStartDelay());
                    }
                    animator.addListener(new afi(this));
                    animator.start();
                }
            }
        }
        this.f340u.clear();
        mo320e();
    }

    /* renamed from: a */
    public void mo301a(long j) {
        this.f323b = j;
    }

    /* renamed from: a */
    public void mo306a(fxk fxk) {
        this.f333l = fxk;
    }

    /* renamed from: a */
    public void mo305a(flw flw) {
        if (flw == null) {
            this.f334m = f321v;
        } else {
            this.f334m = flw;
        }
    }

    /* renamed from: b */
    public void mo311b(long j) {
        this.f322a = j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final void mo318d() {
        if (this.f336q == 0) {
            ArrayList arrayList = this.f339t;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.f339t.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((afk) arrayList2.get(i)).mo278b(this);
                }
            }
            this.f338s = false;
        }
        this.f336q++;
    }

    public final String toString() {
        return mo300a("");
    }

    /* renamed from: a */
    public String mo300a(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.f323b != -1) {
            str2 = str2 + "dur(" + this.f323b + ") ";
        }
        if (this.f322a != -1) {
            str2 = str2 + "dly(" + this.f322a + ") ";
        }
        if (this.f324c.size() <= 0 && this.f325d.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.f324c.size() > 0) {
            for (int i = 0; i < this.f324c.size(); i++) {
                if (i > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f324c.get(i);
            }
        }
        if (this.f325d.size() > 0) {
            for (int i2 = 0; i2 < this.f325d.size(); i2++) {
                if (i2 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f325d.get(i2);
            }
        }
        return str3 + ")";
    }
}
