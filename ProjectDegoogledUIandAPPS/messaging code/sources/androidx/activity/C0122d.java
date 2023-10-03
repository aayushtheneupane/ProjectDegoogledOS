package androidx.activity;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: androidx.activity.d */
public abstract class C0122d {

    /* renamed from: Gl */
    private CopyOnWriteArrayList f126Gl = new CopyOnWriteArrayList();
    private boolean mEnabled;

    public C0122d(boolean z) {
        this.mEnabled = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo614a(C0119a aVar) {
        this.f126Gl.add(aVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo615b(C0119a aVar) {
        this.f126Gl.remove(aVar);
    }

    /* renamed from: ec */
    public abstract void mo616ec();

    public final boolean isEnabled() {
        return this.mEnabled;
    }

    public final void remove() {
        Iterator it = this.f126Gl.iterator();
        while (it.hasNext()) {
            ((C0119a) it.next()).cancel();
        }
    }

    public final void setEnabled(boolean z) {
        this.mEnabled = z;
    }
}
