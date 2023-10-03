package p000;

/* renamed from: ibl */
/* compiled from: PG */
final class ibl extends ibe {
    private ibl() {
    }

    public /* synthetic */ ibl(byte[] bArr) {
    }

    /* renamed from: a */
    public final boolean mo8338a(ibr ibr, ibi ibi, ibi ibi2) {
        synchronized (ibr) {
            if (ibr.listeners != ibi) {
                return false;
            }
            ibr.listeners = ibi2;
            return true;
        }
    }

    /* renamed from: a */
    public final boolean mo8340a(ibr ibr, Object obj, Object obj2) {
        synchronized (ibr) {
            boolean z = ibr.f13852b;
            if (ibr.value != obj) {
                return false;
            }
            ibr.value = obj2;
            return true;
        }
    }

    /* renamed from: a */
    public final boolean mo8339a(ibr ibr, ibq ibq, ibq ibq2) {
        synchronized (ibr) {
            if (ibr.waiters != ibq) {
                return false;
            }
            ibr.waiters = ibq2;
            return true;
        }
    }

    /* renamed from: a */
    public final void mo8336a(ibq ibq, ibq ibq2) {
        ibq.next = ibq2;
    }

    /* renamed from: a */
    public final void mo8337a(ibq ibq, Thread thread) {
        ibq.thread = thread;
    }
}
