package p000;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: ab */
/* compiled from: PG */
public class C0002ab extends C0600w {

    /* renamed from: a */
    public final C0112e f62a = new C0112e();

    /* renamed from: b */
    private C0573v f63b;

    /* renamed from: c */
    private final WeakReference f64c;

    /* renamed from: d */
    private int f65d = 0;

    /* renamed from: e */
    private boolean f66e = false;

    /* renamed from: f */
    private boolean f67f = false;

    /* renamed from: g */
    private final ArrayList f68g = new ArrayList();

    public C0002ab(C0681z zVar) {
        this.f64c = new WeakReference(zVar);
        this.f63b = C0573v.INITIALIZED;
    }

    /* renamed from: a */
    public final C0573v mo61a() {
        return this.f63b;
    }

    /* renamed from: a */
    public final void mo64a(C0654y yVar) {
        Object obj;
        C0681z zVar;
        boolean z;
        C0001aa aaVar = new C0001aa(yVar, this.f63b == C0573v.DESTROYED ? C0573v.DESTROYED : C0573v.INITIALIZED);
        C0112e eVar = this.f62a;
        C0195h a = eVar.mo4622a(yVar);
        if (a == null) {
            eVar.f7757a.put(yVar, eVar.mo9305a(yVar, aaVar));
            obj = null;
        } else {
            obj = a.f12389b;
        }
        if (((C0001aa) obj) == null && (zVar = (C0681z) this.f64c.get()) != null) {
            if (this.f65d != 0 || this.f66e) {
                z = true;
            } else {
                z = false;
            }
            C0573v c = m64c(yVar);
            this.f65d++;
            while (aaVar.f0a.compareTo(c) < 0 && this.f62a.mo4624c(yVar)) {
                m62b(aaVar.f0a);
                aaVar.mo2a(zVar, m63c(aaVar.f0a));
                m61b();
                c = m64c(yVar);
            }
            if (!z) {
                m65c();
            }
            this.f65d--;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: v} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final p000.C0573v m64c(p000.C0654y r4) {
        /*
            r3 = this;
            e r0 = r3.f62a
            boolean r1 = r0.mo4624c(r4)
            r2 = 0
            if (r1 == 0) goto L_0x0014
            java.util.HashMap r0 = r0.f7757a
            java.lang.Object r4 = r0.get(r4)
            h r4 = (p000.C0195h) r4
            h r4 = r4.f12391d
            goto L_0x0016
        L_0x0014:
            r4 = r2
        L_0x0016:
            if (r4 == 0) goto L_0x001f
            java.lang.Object r4 = r4.f12389b
            aa r4 = (p000.C0001aa) r4
            v r4 = r4.f0a
            goto L_0x0021
        L_0x001f:
            r4 = r2
        L_0x0021:
            java.util.ArrayList r0 = r3.f68g
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0039
            java.util.ArrayList r0 = r3.f68g
            int r1 = r0.size()
            int r1 = r1 + -1
            java.lang.Object r0 = r0.get(r1)
            r2 = r0
            v r2 = (p000.C0573v) r2
            goto L_0x003b
        L_0x0039:
        L_0x003b:
            v r0 = r3.f63b
            v r4 = m59a(r0, r4)
            v r4 = m59a(r4, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0002ab.m64c(y):v");
    }

    /* renamed from: b */
    static C0573v m60b(C0546u uVar) {
        C0573v vVar = C0573v.DESTROYED;
        C0546u uVar2 = C0546u.ON_CREATE;
        int ordinal = uVar.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    return C0573v.RESUMED;
                }
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        if (ordinal == 5) {
                            return C0573v.DESTROYED;
                        }
                        throw new IllegalArgumentException("Unexpected event value " + uVar);
                    }
                }
            }
            return C0573v.STARTED;
        }
        return C0573v.CREATED;
    }

    /* renamed from: a */
    public void mo62a(C0546u uVar) {
        mo63a(m60b(uVar));
    }

    /* renamed from: a */
    static C0573v m59a(C0573v vVar, C0573v vVar2) {
        return (vVar2 == null || vVar2.compareTo(vVar) >= 0) ? vVar : vVar2;
    }

    /* renamed from: a */
    public final void mo63a(C0573v vVar) {
        if (this.f63b != vVar) {
            this.f63b = vVar;
            if (this.f66e || this.f65d != 0) {
                this.f67f = true;
                return;
            }
            this.f66e = true;
            m65c();
            this.f66e = false;
        }
    }

    /* renamed from: b */
    private final void m61b() {
        ArrayList arrayList = this.f68g;
        arrayList.remove(arrayList.size() - 1);
    }

    /* renamed from: b */
    private final void m62b(C0573v vVar) {
        this.f68g.add(vVar);
    }

    /* renamed from: b */
    public final void mo65b(C0654y yVar) {
        this.f62a.mo4623b(yVar);
    }

    /* renamed from: c */
    private final void m65c() {
        C0573v vVar;
        C0573v vVar2;
        C0546u uVar;
        C0681z zVar = (C0681z) this.f64c.get();
        if (zVar != null) {
            while (true) {
                C0112e eVar = this.f62a;
                if (eVar.f15181e == 0 || ((vVar = ((C0001aa) eVar.f15178b.f12389b).f0a) == (vVar2 = ((C0001aa) eVar.f15179c.f12389b).f0a) && this.f63b == vVar2)) {
                    this.f67f = false;
                } else {
                    this.f67f = false;
                    if (this.f63b.compareTo(vVar) < 0) {
                        C0112e eVar2 = this.f62a;
                        C0166g gVar = new C0166g(eVar2.f15179c, eVar2.f15178b);
                        eVar2.f15180d.put(gVar, false);
                        while (gVar.hasNext() && !this.f67f) {
                            Map.Entry entry = (Map.Entry) gVar.next();
                            C0001aa aaVar = (C0001aa) entry.getValue();
                            while (aaVar.f0a.compareTo(this.f63b) > 0 && !this.f67f && this.f62a.mo4624c(entry.getKey())) {
                                C0573v vVar3 = aaVar.f0a;
                                C0573v vVar4 = C0573v.DESTROYED;
                                C0546u uVar2 = C0546u.ON_CREATE;
                                int ordinal = vVar3.ordinal();
                                if (ordinal == 0) {
                                    throw new IllegalArgumentException();
                                } else if (ordinal != 1) {
                                    if (ordinal == 2) {
                                        uVar = C0546u.ON_DESTROY;
                                    } else if (ordinal == 3) {
                                        uVar = C0546u.ON_STOP;
                                    } else if (ordinal == 4) {
                                        uVar = C0546u.ON_PAUSE;
                                    } else {
                                        throw new IllegalArgumentException("Unexpected state value " + vVar3);
                                    }
                                    m62b(m60b(uVar));
                                    aaVar.mo2a(zVar, uVar);
                                    m61b();
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                        }
                    }
                    C0195h hVar = this.f62a.f15179c;
                    if (!this.f67f && hVar != null && this.f63b.compareTo(((C0001aa) hVar.f12389b).f0a) > 0) {
                        C0222i a = this.f62a.mo9306a();
                        while (a.hasNext() && !this.f67f) {
                            C0195h hVar2 = (C0195h) a.next();
                            C0001aa aaVar2 = (C0001aa) hVar2.f12389b;
                            while (aaVar2.f0a.compareTo(this.f63b) < 0 && !this.f67f && this.f62a.mo4624c(hVar2.f12388a)) {
                                m62b(aaVar2.f0a);
                                aaVar2.mo2a(zVar, m63c(aaVar2.f0a));
                                m61b();
                            }
                        }
                    }
                }
            }
            this.f67f = false;
            return;
        }
        throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
    }

    /* renamed from: c */
    private static C0546u m63c(C0573v vVar) {
        C0573v vVar2 = C0573v.DESTROYED;
        C0546u uVar = C0546u.ON_CREATE;
        int ordinal = vVar.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return C0546u.ON_CREATE;
        }
        if (ordinal == 2) {
            return C0546u.ON_START;
        }
        if (ordinal == 3) {
            return C0546u.ON_RESUME;
        }
        if (ordinal != 4) {
            throw new IllegalArgumentException("Unexpected state value " + vVar);
        }
        throw new IllegalArgumentException();
    }
}
