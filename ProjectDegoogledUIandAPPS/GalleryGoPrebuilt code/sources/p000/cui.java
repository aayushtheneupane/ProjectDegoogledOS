package p000;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: cui */
/* compiled from: PG */
public final class cui {

    /* renamed from: a */
    public final Map f5670a = new HashMap();

    /* renamed from: b */
    private final iel f5671b;

    public cui(iel iel) {
        this.f5671b = iel;
        for (cup put : cup.values()) {
            this.f5670a.put(put, new AtomicBoolean(false));
        }
        for (cuh put2 : cuh.values()) {
            this.f5670a.put(put2, new AtomicBoolean(false));
        }
    }

    /* renamed from: a */
    public final boolean mo3834a(Object obj) {
        return ((AtomicBoolean) this.f5670a.get(obj)).compareAndSet(false, true);
    }

    /* renamed from: b */
    public final void mo3835b(Object obj) {
        ((AtomicBoolean) this.f5670a.get(obj)).set(false);
    }

    /* renamed from: a */
    public final ieh mo3833a(cup cup, cue cue, ice ice) {
        if (!mo3834a(cup)) {
            return ife.m12820a((Object) null);
        }
        ice.getClass();
        return fxk.m9820a(cun.m5445a(cue, (ice) new cuf(ice), (Executor) this.f5671b), (Closeable) new cug(this, cup), (Executor) idh.f13918a);
    }
}
