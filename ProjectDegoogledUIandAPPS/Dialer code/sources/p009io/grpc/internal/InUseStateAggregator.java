package p009io.grpc.internal;

import java.util.HashSet;

/* renamed from: io.grpc.internal.InUseStateAggregator */
abstract class InUseStateAggregator<T> {
    private final HashSet<T> inUseObjects = new HashSet<>();

    InUseStateAggregator() {
    }

    /* access modifiers changed from: package-private */
    public abstract Object getLock();

    /* access modifiers changed from: package-private */
    public final boolean isInUse() {
        boolean z;
        synchronized (getLock()) {
            z = !this.inUseObjects.isEmpty();
        }
        return z;
    }
}
