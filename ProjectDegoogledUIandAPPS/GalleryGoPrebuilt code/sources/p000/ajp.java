package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: ajp */
/* compiled from: PG */
public abstract class ajp implements aji {

    /* renamed from: a */
    public final List f648a = new ArrayList();

    /* renamed from: b */
    public Object f649b;

    /* renamed from: c */
    public final aka f650c;

    /* renamed from: d */
    private ajo f651d;

    public ajp(aka aka) {
        this.f650c = aka;
    }

    /* renamed from: a */
    public abstract boolean mo554a(alg alg);

    /* renamed from: b */
    public abstract boolean mo555b(Object obj);

    /* renamed from: a */
    public final void mo547a(Object obj) {
        this.f649b = obj;
        mo556a();
    }

    /* renamed from: a */
    public final void mo557a(ajo ajo) {
        if (this.f651d != ajo) {
            this.f651d = ajo;
            mo556a();
        }
    }

    /* renamed from: a */
    public final void mo556a() {
        if (!this.f648a.isEmpty() && this.f651d != null) {
            Object obj = this.f649b;
            if (obj != null && !mo555b(obj)) {
                ajo ajo = this.f651d;
                List<String> list = this.f648a;
                synchronized (((ajl) ajo).f646b) {
                    ArrayList arrayList = new ArrayList();
                    for (String str : list) {
                        if (((ajl) ajo).mo553a(str)) {
                            iol.m14231a();
                            String.format("Constraints met for %s", new Object[]{str});
                            arrayList.add(str);
                        }
                    }
                    ajk ajk = ((ajl) ajo).f645a;
                    if (ajk != null) {
                        ajk.mo531a(arrayList);
                    }
                }
                return;
            }
            ajo ajo2 = this.f651d;
            List list2 = this.f648a;
            synchronized (((ajl) ajo2).f646b) {
                ajk ajk2 = ((ajl) ajo2).f645a;
                if (ajk2 != null) {
                    ajk2.mo532b(list2);
                }
            }
        }
    }
}
