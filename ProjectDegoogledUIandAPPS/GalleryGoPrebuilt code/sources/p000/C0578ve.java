package p000;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.List;

/* renamed from: ve */
/* compiled from: PG */
public class C0578ve extends C0674yt {

    /* renamed from: i */
    private static TimeInterpolator f16075i;

    /* renamed from: a */
    public final ArrayList f16076a = new ArrayList();

    /* renamed from: b */
    public final ArrayList f16077b = new ArrayList();

    /* renamed from: c */
    public final ArrayList f16078c = new ArrayList();

    /* renamed from: d */
    public final ArrayList f16079d = new ArrayList();

    /* renamed from: e */
    public final ArrayList f16080e = new ArrayList();

    /* renamed from: f */
    public final ArrayList f16081f = new ArrayList();

    /* renamed from: g */
    public final ArrayList f16082g = new ArrayList();

    /* renamed from: j */
    private final ArrayList f16083j = new ArrayList();

    /* renamed from: k */
    private final ArrayList f16084k = new ArrayList();

    /* renamed from: l */
    private final ArrayList f16085l = new ArrayList();

    /* renamed from: m */
    private final ArrayList f16086m = new ArrayList();

    /* renamed from: b */
    public final boolean mo10371b(C0667ym ymVar) {
        m15592h(ymVar);
        ymVar.f16382a.setAlpha(0.0f);
        this.f16084k.add(ymVar);
        return true;
    }

    /* renamed from: a */
    public final boolean mo10369a(C0667ym ymVar, C0667ym ymVar2, int i, int i2, int i3, int i4) {
        if (ymVar == ymVar2) {
            return mo10367a(ymVar, i, i2, i3, i4);
        }
        float translationX = ymVar.f16382a.getTranslationX();
        float translationY = ymVar.f16382a.getTranslationY();
        float alpha = ymVar.f16382a.getAlpha();
        m15592h(ymVar);
        int i5 = (int) (((float) (i3 - i)) - translationX);
        int i6 = (int) (((float) (i4 - i2)) - translationY);
        ymVar.f16382a.setTranslationX(translationX);
        ymVar.f16382a.setTranslationY(translationY);
        ymVar.f16382a.setAlpha(alpha);
        if (ymVar2 != null) {
            m15592h(ymVar2);
            ymVar2.f16382a.setTranslationX((float) (-i5));
            ymVar2.f16382a.setTranslationY((float) (-i6));
            ymVar2.f16382a.setAlpha(0.0f);
        }
        this.f16086m.add(new C0576vc(ymVar, ymVar2, i, i2, i3, i4));
        return true;
    }

    /* renamed from: a */
    public final boolean mo10367a(C0667ym ymVar, int i, int i2, int i3, int i4) {
        View view = ymVar.f16382a;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) ymVar.f16382a.getTranslationY());
        m15592h(ymVar);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            mo10556d(ymVar);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX((float) (-i5));
        }
        if (i6 != 0) {
            view.setTranslationY((float) (-i6));
        }
        this.f16085l.add(new C0577vd(ymVar, translationX, translationY, i3, i4));
        return true;
    }

    /* renamed from: a */
    public final boolean mo10366a(C0667ym ymVar) {
        m15592h(ymVar);
        this.f16083j.add(ymVar);
        return true;
    }

    /* renamed from: a */
    public final boolean mo10368a(C0667ym ymVar, List list) {
        return !list.isEmpty() || mo10557e(ymVar);
    }

    /* renamed from: a */
    private static final void m15588a(List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ((C0667ym) list.get(size)).f16382a.animate().cancel();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo10372c() {
        if (!mo10370b()) {
            mo10558f();
        }
    }

    /* renamed from: c */
    public final void mo10373c(C0667ym ymVar) {
        View view = ymVar.f16382a;
        view.animate().cancel();
        int size = this.f16085l.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (((C0577vd) this.f16085l.get(size)).f16070a == ymVar) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                mo10556d(ymVar);
                this.f16085l.remove(size);
            }
        }
        m15589a((List) this.f16086m, ymVar);
        if (this.f16083j.remove(ymVar)) {
            view.setAlpha(1.0f);
            mo10556d(ymVar);
        }
        if (this.f16084k.remove(ymVar)) {
            view.setAlpha(1.0f);
            mo10556d(ymVar);
        }
        for (int size2 = this.f16078c.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.f16078c.get(size2);
            m15589a((List) arrayList, ymVar);
            if (arrayList.isEmpty()) {
                this.f16078c.remove(size2);
            }
        }
        for (int size3 = this.f16077b.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.f16077b.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                } else if (((C0577vd) arrayList2.get(size4)).f16070a != ymVar) {
                    size4--;
                } else {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    mo10556d(ymVar);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.f16077b.remove(size3);
                    }
                }
            }
        }
        for (int size5 = this.f16076a.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.f16076a.get(size5);
            if (arrayList3.remove(ymVar)) {
                view.setAlpha(1.0f);
                mo10556d(ymVar);
                if (arrayList3.isEmpty()) {
                    this.f16076a.remove(size5);
                }
            }
        }
        this.f16081f.remove(ymVar);
        this.f16079d.remove(ymVar);
        this.f16082g.remove(ymVar);
        this.f16080e.remove(ymVar);
        mo10372c();
    }

    /* renamed from: d */
    public final void mo10374d() {
        int size = this.f16085l.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            C0577vd vdVar = (C0577vd) this.f16085l.get(size);
            View view = vdVar.f16070a.f16382a;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            mo10556d(vdVar.f16070a);
            this.f16085l.remove(size);
        }
        for (int size2 = this.f16083j.size() - 1; size2 >= 0; size2--) {
            mo10556d((C0667ym) this.f16083j.get(size2));
            this.f16083j.remove(size2);
        }
        int size3 = this.f16084k.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            C0667ym ymVar = (C0667ym) this.f16084k.get(size3);
            ymVar.f16382a.setAlpha(1.0f);
            mo10556d(ymVar);
            this.f16084k.remove(size3);
        }
        for (int size4 = this.f16086m.size() - 1; size4 >= 0; size4--) {
            m15590a((C0576vc) this.f16086m.get(size4));
        }
        this.f16086m.clear();
        if (mo10370b()) {
            for (int size5 = this.f16077b.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList = (ArrayList) this.f16077b.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    C0577vd vdVar2 = (C0577vd) arrayList.get(size6);
                    View view2 = vdVar2.f16070a.f16382a;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    mo10556d(vdVar2.f16070a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.f16077b.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.f16076a.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList2 = (ArrayList) this.f16076a.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    C0667ym ymVar2 = (C0667ym) arrayList2.get(size8);
                    ymVar2.f16382a.setAlpha(1.0f);
                    mo10556d(ymVar2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.f16076a.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.f16078c.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList3 = (ArrayList) this.f16078c.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    m15590a((C0576vc) arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.f16078c.remove(arrayList3);
                    }
                }
            }
            m15588a((List) this.f16081f);
            m15588a((List) this.f16080e);
            m15588a((List) this.f16079d);
            m15588a((List) this.f16082g);
            mo10558f();
        }
    }

    /* renamed from: a */
    private final void m15589a(List list, C0667ym ymVar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            C0576vc vcVar = (C0576vc) list.get(size);
            if (m15591a(vcVar, ymVar) && vcVar.f16064a == null && vcVar.f16065b == null) {
                list.remove(vcVar);
            }
        }
    }

    /* renamed from: a */
    private final void m15590a(C0576vc vcVar) {
        C0667ym ymVar = vcVar.f16064a;
        if (ymVar != null) {
            m15591a(vcVar, ymVar);
        }
        C0667ym ymVar2 = vcVar.f16065b;
        if (ymVar2 != null) {
            m15591a(vcVar, ymVar2);
        }
    }

    /* renamed from: a */
    private final boolean m15591a(C0576vc vcVar, C0667ym ymVar) {
        if (vcVar.f16065b == ymVar) {
            vcVar.f16065b = null;
        } else if (vcVar.f16064a != ymVar) {
            return false;
        } else {
            vcVar.f16064a = null;
        }
        ymVar.f16382a.setAlpha(1.0f);
        ymVar.f16382a.setTranslationX(0.0f);
        ymVar.f16382a.setTranslationY(0.0f);
        mo10556d(ymVar);
        return true;
    }

    /* renamed from: b */
    public final boolean mo10370b() {
        return !this.f16084k.isEmpty() || !this.f16086m.isEmpty() || !this.f16085l.isEmpty() || !this.f16083j.isEmpty() || !this.f16080e.isEmpty() || !this.f16081f.isEmpty() || !this.f16079d.isEmpty() || !this.f16082g.isEmpty() || !this.f16077b.isEmpty() || !this.f16076a.isEmpty() || !this.f16078c.isEmpty();
    }

    /* renamed from: h */
    private final void m15592h(C0667ym ymVar) {
        if (f16075i == null) {
            f16075i = new ValueAnimator().getInterpolator();
        }
        ymVar.f16382a.animate().setInterpolator(f16075i);
        mo10373c(ymVar);
    }

    /* renamed from: a */
    public final void mo10365a() {
        long j;
        boolean isEmpty = this.f16083j.isEmpty();
        boolean z = !isEmpty;
        boolean isEmpty2 = this.f16085l.isEmpty();
        boolean z2 = !isEmpty2;
        boolean isEmpty3 = this.f16086m.isEmpty();
        boolean z3 = !isEmpty3;
        boolean z4 = !this.f16084k.isEmpty();
        if (z || z2 || z4 || z3) {
            ArrayList arrayList = this.f16083j;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                C0667ym ymVar = (C0667ym) arrayList.get(i);
                View view = ymVar.f16382a;
                ViewPropertyAnimator animate = view.animate();
                this.f16081f.add(ymVar);
                animate.setDuration(120).alpha(0.0f).setListener(new C0570ux(this, ymVar, animate, view)).start();
            }
            this.f16083j.clear();
            if (z2) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.f16085l);
                this.f16077b.add(arrayList2);
                this.f16085l.clear();
                C0567uu uuVar = new C0567uu(this, arrayList2);
                if (z) {
                    C0340mj.m14696a(((C0577vd) arrayList2.get(0)).f16070a.f16382a, (Runnable) uuVar, 120);
                } else {
                    uuVar.run();
                }
            }
            if (z3) {
                ArrayList arrayList3 = new ArrayList();
                arrayList3.addAll(this.f16086m);
                this.f16078c.add(arrayList3);
                this.f16086m.clear();
                C0568uv uvVar = new C0568uv(this, arrayList3);
                if (z) {
                    j = 120;
                    C0340mj.m14696a(((C0576vc) arrayList3.get(0)).f16064a.f16382a, (Runnable) uvVar, 120);
                } else {
                    j = 120;
                    uvVar.run();
                }
            } else {
                j = 120;
            }
            if (z4) {
                ArrayList arrayList4 = new ArrayList();
                arrayList4.addAll(this.f16084k);
                this.f16076a.add(arrayList4);
                this.f16084k.clear();
                C0569uw uwVar = new C0569uw(this, arrayList4);
                if (!z && !z2 && !z3) {
                    uwVar.run();
                    return;
                }
                long j2 = 0;
                if (isEmpty) {
                    j = 0;
                }
                long j3 = !isEmpty2 ? 250 : 0;
                if (!isEmpty3) {
                    j2 = 250;
                }
                C0340mj.m14696a(((C0667ym) arrayList4.get(0)).f16382a, (Runnable) uwVar, j + Math.max(j3, j2));
            }
        }
    }
}
