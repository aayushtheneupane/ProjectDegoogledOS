package p000;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* renamed from: ifi */
/* compiled from: PG */
final class ifi extends WeakReference {

    /* renamed from: a */
    private final int f13997a;

    public ifi(Throwable th, ReferenceQueue referenceQueue) {
        super(th, referenceQueue);
        if (th != null) {
            this.f13997a = System.identityHashCode(th);
            return;
        }
        throw new NullPointerException("The referent cannot be null");
    }

    public final int hashCode() {
        return this.f13997a;
    }

    public final boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ifi ifi = (ifi) obj;
        return this.f13997a == ifi.f13997a && get() == ifi.get();
    }
}
