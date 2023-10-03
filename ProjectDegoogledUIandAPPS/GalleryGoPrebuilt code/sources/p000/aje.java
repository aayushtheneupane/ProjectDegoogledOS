package p000;

import android.content.Intent;

/* renamed from: aje */
/* compiled from: PG */
final class aje implements Runnable {

    /* renamed from: a */
    private final ajf f623a;

    public aje(ajf ajf) {
        this.f623a = ajf;
    }

    public final void run() {
        boolean z;
        boolean z2;
        ajf ajf = this.f623a;
        iol.m14231a();
        ajf.mo546c();
        synchronized (ajf.f631h) {
            if (ajf.f632i != null) {
                iol.m14231a();
                String.format("Removing command %s", new Object[]{ajf.f632i});
                if (((Intent) ajf.f631h.remove(0)).equals(ajf.f632i)) {
                    ajf.f632i = null;
                } else {
                    throw new IllegalStateException("Dequeue-d command is not the first.");
                }
            }
            amf amf = ((anb) ajf.f626c).f809a;
            aiw aiw = ajf.f630g;
            synchronized (aiw.f602d) {
                z = !aiw.f601c.isEmpty();
            }
            if (!z) {
                if (ajf.f631h.isEmpty()) {
                    synchronized (amf.f759b) {
                        z2 = !amf.f758a.isEmpty();
                    }
                    if (!z2) {
                        iol.m14231a();
                        ajd ajd = ajf.f633j;
                        if (ajd != null) {
                            ajd.mo540a();
                        }
                    }
                }
            }
            if (!ajf.f631h.isEmpty()) {
                ajf.mo545b();
            }
        }
    }
}
