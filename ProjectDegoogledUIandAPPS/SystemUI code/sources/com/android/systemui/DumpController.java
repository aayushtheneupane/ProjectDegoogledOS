package com.android.systemui;

import com.android.internal.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DumpController.kt */
public final class DumpController implements Dumpable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<WeakReference<Dumpable>> listeners = new ArrayList();

    /* compiled from: DumpController.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void addListener(Dumpable dumpable) {
        Intrinsics.checkParameterIsNotNull(dumpable, "listener");
        boolean z = false;
        Preconditions.checkNotNull(dumpable, "The listener to be added cannot be null", new Object[0]);
        synchronized (this.listeners) {
            List<WeakReference<Dumpable>> list = this.listeners;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (Intrinsics.areEqual((Object) (Dumpable) ((WeakReference) it.next()).get(), (Object) dumpable)) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (!z) {
                this.listeners.add(new WeakReference(dumpable));
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void removeListener(Dumpable dumpable) {
        Intrinsics.checkParameterIsNotNull(dumpable, "listener");
        synchronized (this.listeners) {
            boolean unused = CollectionsKt__MutableCollectionsKt.removeAll(this.listeners, new DumpController$removeListener$$inlined$synchronized$lambda$1(this, dumpable));
        }
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        Intrinsics.checkParameterIsNotNull(printWriter, "pw");
        printWriter.println("DumpController state:");
        synchronized (this.listeners) {
            for (WeakReference weakReference : this.listeners) {
                Dumpable dumpable = (Dumpable) weakReference.get();
                if (dumpable != null) {
                    dumpable.dump(fileDescriptor, printWriter, strArr);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
