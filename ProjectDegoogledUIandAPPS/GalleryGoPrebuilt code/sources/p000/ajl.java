package p000;

import android.content.Context;
import java.util.Iterator;

/* renamed from: ajl */
/* compiled from: PG */
public final class ajl implements ajo {

    /* renamed from: a */
    public final ajk f645a;

    /* renamed from: b */
    public final Object f646b = new Object();

    /* renamed from: c */
    private final ajp[] f647c;

    static {
        iol.m14236b("WorkConstraintsTracker");
    }

    public ajl(Context context, amz amz, ajk ajk) {
        Context applicationContext = context.getApplicationContext();
        this.f645a = ajk;
        this.f647c = new ajp[]{new ajm(applicationContext, amz), new ajn(applicationContext, amz), new aju(applicationContext, amz), new ajq(applicationContext, amz), new ajt(applicationContext, amz), new ajs(applicationContext, amz), new ajr(applicationContext, amz)};
    }

    /* renamed from: a */
    public final boolean mo553a(String str) {
        synchronized (this.f646b) {
            ajp[] ajpArr = this.f647c;
            for (int i = 0; i < 7; i++) {
                ajp ajp = ajpArr[i];
                Object obj = ajp.f649b;
                if (obj != null) {
                    if (ajp.mo555b(obj) && ajp.f648a.contains(str)) {
                        iol.m14231a();
                        String.format("Work %s constrained by %s", new Object[]{str, ajp.getClass().getSimpleName()});
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /* renamed from: a */
    public final void mo552a(Iterable iterable) {
        synchronized (this.f646b) {
            ajp[] ajpArr = this.f647c;
            for (int i = 0; i < 7; i++) {
                ajpArr[i].mo557a((ajo) null);
            }
            ajp[] ajpArr2 = this.f647c;
            for (int i2 = 0; i2 < 7; i2++) {
                ajp ajp = ajpArr2[i2];
                ajp.f648a.clear();
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    alg alg = (alg) it.next();
                    if (ajp.mo554a(alg)) {
                        ajp.f648a.add(alg.f713b);
                    }
                }
                if (!ajp.f648a.isEmpty()) {
                    aka aka = ajp.f650c;
                    synchronized (aka.f659b) {
                        if (aka.f660c.add(ajp)) {
                            if (aka.f660c.size() == 1) {
                                aka.f661d = aka.mo560b();
                                iol.m14231a();
                                String.format("%s: initial state = %s", new Object[]{aka.getClass().getSimpleName(), aka.f661d});
                                aka.mo562c();
                            }
                            ajp.mo547a(aka.f661d);
                        }
                    }
                } else {
                    ajp.f650c.mo565a((aji) ajp);
                }
                ajp.mo556a();
            }
            ajp[] ajpArr3 = this.f647c;
            for (int i3 = 0; i3 < 7; i3++) {
                ajpArr3[i3].mo557a((ajo) this);
            }
        }
    }

    /* renamed from: a */
    public final void mo551a() {
        synchronized (this.f646b) {
            ajp[] ajpArr = this.f647c;
            for (int i = 0; i < 7; i++) {
                ajp ajp = ajpArr[i];
                if (!ajp.f648a.isEmpty()) {
                    ajp.f648a.clear();
                    ajp.f650c.mo565a((aji) ajp);
                }
            }
        }
    }
}
