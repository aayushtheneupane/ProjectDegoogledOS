package androidx.lifecycle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Map;
import p000a.p001a.p002a.p004b.C0006a;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.lifecycle.l */
public class C0455l extends C0450g {

    /* renamed from: Jp */
    private C0006a f439Jp = new C0006a();

    /* renamed from: Kp */
    private final WeakReference f440Kp;

    /* renamed from: Lp */
    private int f441Lp = 0;

    /* renamed from: Mp */
    private boolean f442Mp = false;

    /* renamed from: Np */
    private boolean f443Np = false;

    /* renamed from: Op */
    private ArrayList f444Op = new ArrayList();
    private Lifecycle$State mState;

    public C0455l(C0453j jVar) {
        this.f440Kp = new WeakReference(jVar);
        this.mState = Lifecycle$State.INITIALIZED;
    }

    /* renamed from: Hn */
    private void m459Hn() {
        ArrayList arrayList = this.f444Op;
        arrayList.remove(arrayList.size() - 1);
    }

    /* renamed from: b */
    private void m462b(Lifecycle$State lifecycle$State) {
        if (this.mState != lifecycle$State) {
            this.mState = lifecycle$State;
            if (this.f442Mp || this.f441Lp != 0) {
                this.f443Np = true;
                return;
            }
            this.f442Mp = true;
            sync();
            this.f442Mp = false;
        }
    }

    /* renamed from: c */
    private Lifecycle$State m464c(C0452i iVar) {
        Map.Entry f = this.f439Jp.mo8f(iVar);
        Lifecycle$State lifecycle$State = null;
        Lifecycle$State lifecycle$State2 = f != null ? ((C0454k) f.getValue()).mState : null;
        if (!this.f444Op.isEmpty()) {
            ArrayList arrayList = this.f444Op;
            lifecycle$State = (Lifecycle$State) arrayList.get(arrayList.size() - 1);
        }
        return m460a(m460a(this.mState, lifecycle$State2), lifecycle$State);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0015, code lost:
        r1 = ((androidx.lifecycle.C0454k) r8.f439Jp.eldest().getValue()).mState;
        r4 = ((androidx.lifecycle.C0454k) r8.f439Jp.mo32qc().getValue()).mState;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sync() {
        /*
            r8 = this;
            java.lang.ref.WeakReference r0 = r8.f440Kp
            java.lang.Object r0 = r0.get()
            androidx.lifecycle.j r0 = (androidx.lifecycle.C0453j) r0
            if (r0 == 0) goto L_0x0136
        L_0x000a:
            a.a.a.b.a r1 = r8.f439Jp
            int r1 = r1.size()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0015
            goto L_0x0037
        L_0x0015:
            a.a.a.b.a r1 = r8.f439Jp
            java.util.Map$Entry r1 = r1.eldest()
            java.lang.Object r1 = r1.getValue()
            androidx.lifecycle.k r1 = (androidx.lifecycle.C0454k) r1
            androidx.lifecycle.Lifecycle$State r1 = r1.mState
            a.a.a.b.a r4 = r8.f439Jp
            java.util.Map$Entry r4 = r4.mo32qc()
            java.lang.Object r4 = r4.getValue()
            androidx.lifecycle.k r4 = (androidx.lifecycle.C0454k) r4
            androidx.lifecycle.Lifecycle$State r4 = r4.mState
            if (r1 != r4) goto L_0x0039
            androidx.lifecycle.Lifecycle$State r1 = r8.mState
            if (r1 != r4) goto L_0x0039
        L_0x0037:
            r1 = r2
            goto L_0x003a
        L_0x0039:
            r1 = r3
        L_0x003a:
            if (r1 != 0) goto L_0x0133
            r8.f443Np = r3
            androidx.lifecycle.Lifecycle$State r1 = r8.mState
            a.a.a.b.a r3 = r8.f439Jp
            java.util.Map$Entry r3 = r3.eldest()
            java.lang.Object r3 = r3.getValue()
            androidx.lifecycle.k r3 = (androidx.lifecycle.C0454k) r3
            androidx.lifecycle.Lifecycle$State r3 = r3.mState
            int r1 = r1.compareTo(r3)
            if (r1 >= 0) goto L_0x00cd
            a.a.a.b.a r1 = r8.f439Jp
            java.util.Iterator r1 = r1.descendingIterator()
        L_0x005a:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00cd
            boolean r3 = r8.f443Np
            if (r3 != 0) goto L_0x00cd
            java.lang.Object r3 = r1.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getValue()
            androidx.lifecycle.k r4 = (androidx.lifecycle.C0454k) r4
        L_0x0070:
            androidx.lifecycle.Lifecycle$State r5 = r4.mState
            androidx.lifecycle.Lifecycle$State r6 = r8.mState
            int r5 = r5.compareTo(r6)
            if (r5 <= 0) goto L_0x005a
            boolean r5 = r8.f443Np
            if (r5 != 0) goto L_0x005a
            a.a.a.b.a r5 = r8.f439Jp
            java.lang.Object r6 = r3.getKey()
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x005a
            androidx.lifecycle.Lifecycle$State r5 = r4.mState
            int r6 = r5.ordinal()
            if (r6 == 0) goto L_0x00c7
            if (r6 == r2) goto L_0x00c1
            r7 = 2
            if (r6 == r7) goto L_0x00af
            r7 = 3
            if (r6 == r7) goto L_0x00ac
            r7 = 4
            if (r6 != r7) goto L_0x00a0
            androidx.lifecycle.Lifecycle$Event r5 = androidx.lifecycle.Lifecycle$Event.ON_PAUSE
            goto L_0x00b1
        L_0x00a0:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Unexpected state value "
            java.lang.String r0 = p026b.p027a.p030b.p031a.C0632a.m1014a(r0, r5)
            r8.<init>(r0)
            throw r8
        L_0x00ac:
            androidx.lifecycle.Lifecycle$Event r5 = androidx.lifecycle.Lifecycle$Event.ON_STOP
            goto L_0x00b1
        L_0x00af:
            androidx.lifecycle.Lifecycle$Event r5 = androidx.lifecycle.Lifecycle$Event.ON_DESTROY
        L_0x00b1:
            androidx.lifecycle.Lifecycle$State r6 = m461b((androidx.lifecycle.Lifecycle$Event) r5)
            java.util.ArrayList r7 = r8.f444Op
            r7.add(r6)
            r4.mo4463b(r0, r5)
            r8.m459Hn()
            goto L_0x0070
        L_0x00c1:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>()
            throw r8
        L_0x00c7:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>()
            throw r8
        L_0x00cd:
            a.a.a.b.a r1 = r8.f439Jp
            java.util.Map$Entry r1 = r1.mo32qc()
            boolean r2 = r8.f443Np
            if (r2 != 0) goto L_0x000a
            if (r1 == 0) goto L_0x000a
            androidx.lifecycle.Lifecycle$State r2 = r8.mState
            java.lang.Object r1 = r1.getValue()
            androidx.lifecycle.k r1 = (androidx.lifecycle.C0454k) r1
            androidx.lifecycle.Lifecycle$State r1 = r1.mState
            int r1 = r2.compareTo(r1)
            if (r1 <= 0) goto L_0x000a
            a.a.a.b.a r1 = r8.f439Jp
            a.a.a.b.e r1 = r1.mo30pc()
        L_0x00ef:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x000a
            boolean r2 = r8.f443Np
            if (r2 != 0) goto L_0x000a
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getValue()
            androidx.lifecycle.k r3 = (androidx.lifecycle.C0454k) r3
        L_0x0105:
            androidx.lifecycle.Lifecycle$State r4 = r3.mState
            androidx.lifecycle.Lifecycle$State r5 = r8.mState
            int r4 = r4.compareTo(r5)
            if (r4 >= 0) goto L_0x00ef
            boolean r4 = r8.f443Np
            if (r4 != 0) goto L_0x00ef
            a.a.a.b.a r4 = r8.f439Jp
            java.lang.Object r5 = r2.getKey()
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x00ef
            androidx.lifecycle.Lifecycle$State r4 = r3.mState
            java.util.ArrayList r5 = r8.f444Op
            r5.add(r4)
            androidx.lifecycle.Lifecycle$State r4 = r3.mState
            androidx.lifecycle.Lifecycle$Event r4 = m463c((androidx.lifecycle.Lifecycle$State) r4)
            r3.mo4463b(r0, r4)
            r8.m459Hn()
            goto L_0x0105
        L_0x0133:
            r8.f443Np = r3
            return
        L_0x0136:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state."
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.C0455l.sync():void");
    }

    @Deprecated
    /* renamed from: a */
    public void mo4465a(Lifecycle$State lifecycle$State) {
        m462b(lifecycle$State);
    }

    public Lifecycle$State getCurrentState() {
        return this.mState;
    }

    /* renamed from: a */
    public void mo4464a(Lifecycle$Event lifecycle$Event) {
        m462b(m461b(lifecycle$Event));
    }

    /* renamed from: a */
    public void mo4460a(C0452i iVar) {
        C0453j jVar;
        Lifecycle$State lifecycle$State = this.mState;
        Lifecycle$State lifecycle$State2 = Lifecycle$State.DESTROYED;
        if (lifecycle$State != lifecycle$State2) {
            lifecycle$State2 = Lifecycle$State.INITIALIZED;
        }
        C0454k kVar = new C0454k(iVar, lifecycle$State2);
        if (((C0454k) this.f439Jp.putIfAbsent(iVar, kVar)) == null && (jVar = (C0453j) this.f440Kp.get()) != null) {
            boolean z = this.f441Lp != 0 || this.f442Mp;
            Lifecycle$State c = m464c(iVar);
            this.f441Lp++;
            while (kVar.mState.compareTo(c) < 0 && this.f439Jp.contains(iVar)) {
                this.f444Op.add(kVar.mState);
                kVar.mo4463b(jVar, m463c(kVar.mState));
                m459Hn();
                c = m464c(iVar);
            }
            if (!z) {
                sync();
            }
            this.f441Lp--;
        }
    }

    /* renamed from: c */
    private static Lifecycle$Event m463c(Lifecycle$State lifecycle$State) {
        int ordinal = lifecycle$State.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return Lifecycle$Event.ON_CREATE;
        }
        if (ordinal == 2) {
            return Lifecycle$Event.ON_START;
        }
        if (ordinal == 3) {
            return Lifecycle$Event.ON_RESUME;
        }
        if (ordinal != 4) {
            throw new IllegalArgumentException(C0632a.m1014a("Unexpected state value ", lifecycle$State));
        }
        throw new IllegalArgumentException();
    }

    /* renamed from: b */
    public void mo4461b(C0452i iVar) {
        this.f439Jp.remove(iVar);
    }

    /* renamed from: b */
    static Lifecycle$State m461b(Lifecycle$Event lifecycle$Event) {
        int ordinal = lifecycle$Event.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    return Lifecycle$State.RESUMED;
                }
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        if (ordinal == 5) {
                            return Lifecycle$State.DESTROYED;
                        }
                        throw new IllegalArgumentException(C0632a.m1014a("Unexpected event value ", lifecycle$Event));
                    }
                }
            }
            return Lifecycle$State.STARTED;
        }
        return Lifecycle$State.CREATED;
    }

    /* renamed from: a */
    static Lifecycle$State m460a(Lifecycle$State lifecycle$State, Lifecycle$State lifecycle$State2) {
        return (lifecycle$State2 == null || lifecycle$State2.compareTo(lifecycle$State) >= 0) ? lifecycle$State : lifecycle$State2;
    }
}
