package p000;

/* renamed from: aax */
/* compiled from: PG */
final class aax extends aaq {
    /* renamed from: a */
    public final boolean mo46a(aaz aaz, aau aau, aau aau2) {
        synchronized (aaz) {
            if (aaz.listeners != aau) {
                return false;
            }
            aaz.listeners = aau2;
            return true;
        }
    }

    /* renamed from: a */
    public final boolean mo48a(aaz aaz, Object obj, Object obj2) {
        synchronized (aaz) {
            if (aaz.value != obj) {
                return false;
            }
            aaz.value = obj2;
            return true;
        }
    }

    /* renamed from: a */
    public final boolean mo47a(aaz aaz, aay aay, aay aay2) {
        synchronized (aaz) {
            if (aaz.waiters != aay) {
                return false;
            }
            aaz.waiters = aay2;
            return true;
        }
    }

    /* renamed from: a */
    public final void mo44a(aay aay, aay aay2) {
        aay.next = aay2;
    }

    /* renamed from: a */
    public final void mo45a(aay aay, Thread thread) {
        aay.thread = thread;
    }
}
