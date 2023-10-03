package androidx.lifecycle;

import java.util.Map;
import p000a.p001a.p002a.p003a.C0002c;
import p000a.p001a.p002a.p004b.C0013h;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.lifecycle.q */
public abstract class C0460q {
    static final Object NOT_SET = new Object();

    /* renamed from: Up */
    final Object f448Up = new Object();

    /* renamed from: Vp */
    volatile Object f449Vp = NOT_SET;

    /* renamed from: Wp */
    private boolean f450Wp;

    /* renamed from: Xp */
    private boolean f451Xp;

    /* renamed from: Yp */
    private final Runnable f452Yp = new C0458o(this);
    int mActiveCount = 0;
    private volatile Object mData = NOT_SET;
    private C0013h mObservers = new C0013h();
    private int mVersion = -1;

    /* renamed from: b */
    private void m478b(C0459p pVar) {
        if (pVar.mActive) {
            if (!pVar.mo4446yc()) {
                pVar.mo4467A(false);
                return;
            }
            int i = pVar.f447Tp;
            int i2 = this.mVersion;
            if (i < i2) {
                pVar.f447Tp = i2;
                pVar.mObserver.mo241a(this.mData);
            }
        }
    }

    /* renamed from: z */
    static void m479z(String str) {
        if (!C0002c.getInstance().mo5oc()) {
            throw new IllegalStateException(C0632a.m1023d("Cannot invoke ", str, " on a background thread"));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ac */
    public void mo229Ac() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4469a(C0459p pVar) {
        if (this.f450Wp) {
            this.f451Xp = true;
            return;
        }
        this.f450Wp = true;
        do {
            this.f451Xp = false;
            if (pVar == null) {
                C0010e pc = this.mObservers.mo30pc();
                while (pc.hasNext()) {
                    m478b((C0459p) ((Map.Entry) pc.next()).getValue());
                    if (this.f451Xp) {
                        break;
                    }
                }
            } else {
                m478b(pVar);
                pVar = null;
            }
        } while (this.f451Xp);
        this.f450Wp = false;
    }

    public Object getValue() {
        Object obj = this.mData;
        if (obj != NOT_SET) {
            return obj;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public void mo4471l(Object obj) {
        boolean z;
        synchronized (this.f448Up) {
            z = this.f449Vp == NOT_SET;
            this.f449Vp = obj;
        }
        if (z) {
            C0002c.getInstance().mo4f(this.f452Yp);
        }
    }

    /* access modifiers changed from: protected */
    public void onActive() {
    }

    /* access modifiers changed from: protected */
    public void setValue(Object obj) {
        m479z("setValue");
        this.mVersion++;
        this.mData = obj;
        mo4469a((C0459p) null);
    }

    /* renamed from: zc */
    public boolean mo4472zc() {
        return this.mActiveCount > 0;
    }

    /* renamed from: a */
    public void mo4468a(C0453j jVar, C0463t tVar) {
        m479z("observe");
        if (jVar.getLifecycle().getCurrentState() != Lifecycle$State.DESTROYED) {
            LiveData$LifecycleBoundObserver liveData$LifecycleBoundObserver = new LiveData$LifecycleBoundObserver(this, jVar, tVar);
            C0459p pVar = (C0459p) this.mObservers.putIfAbsent(tVar, liveData$LifecycleBoundObserver);
            if (pVar != null && !pVar.mo4444g(jVar)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            } else if (pVar == null) {
                jVar.getLifecycle().mo4460a(liveData$LifecycleBoundObserver);
            }
        }
    }

    /* renamed from: a */
    public void mo234a(C0463t tVar) {
        m479z("removeObserver");
        C0459p pVar = (C0459p) this.mObservers.remove(tVar);
        if (pVar != null) {
            pVar.mo4445xc();
            pVar.mo4467A(false);
        }
    }
}
