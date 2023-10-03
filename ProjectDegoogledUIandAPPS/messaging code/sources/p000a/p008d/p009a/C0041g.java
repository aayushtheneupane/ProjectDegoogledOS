package p000a.p008d.p009a;

import android.os.Bundle;
import android.os.Looper;
import androidx.lifecycle.C0442A;
import androidx.lifecycle.C0453j;
import androidx.loader.content.C0475f;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* renamed from: a.d.a.g */
class C0041g extends C0036b {

    /* renamed from: Kp */
    private final C0453j f17Kp;

    /* renamed from: iq */
    private final C0040f f18iq;

    C0041g(C0453j jVar, C0442A a) {
        this.f17Kp = jVar;
        this.f18iq = C0040f.m47a(a);
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    private C0475f m55a(int i, Bundle bundle, C0035a aVar, C0475f fVar) {
        try {
            this.f18iq.mo250Ic();
            C0475f onCreateLoader = aVar.onCreateLoader(i, bundle);
            if (onCreateLoader != null) {
                if (onCreateLoader.getClass().isMemberClass()) {
                    if (!Modifier.isStatic(onCreateLoader.getClass().getModifiers())) {
                        throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + onCreateLoader);
                    }
                }
                C0037c cVar = new C0037c(i, bundle, onCreateLoader, fVar);
                this.f18iq.mo252a(i, cVar);
                this.f18iq.mo248Gc();
                return cVar.mo233a(this.f17Kp, aVar);
            }
            throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
        } catch (Throwable th) {
            this.f18iq.mo248Gc();
            throw th;
        }
    }

    /* renamed from: Cc */
    public void mo223Cc() {
        this.f18iq.mo246Cc();
    }

    /* renamed from: b */
    public C0475f mo225b(int i, Bundle bundle, C0035a aVar) {
        if (this.f18iq.mo249Hc()) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            C0037c loader = this.f18iq.getLoader(i);
            C0475f fVar = null;
            if (loader != null) {
                fVar = loader.mo230B(false);
            }
            return m55a(i, bundle, aVar, fVar);
        } else {
            throw new IllegalStateException("restartLoader must be called on the main thread");
        }
    }

    public void destroyLoader(int i) {
        if (this.f18iq.mo249Hc()) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            C0037c loader = this.f18iq.getLoader(i);
            if (loader != null) {
                loader.mo230B(true);
                this.f18iq.mo251O(i);
            }
        } else {
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.f18iq.dump(str, fileDescriptor, printWriter, strArr);
    }

    public C0475f getLoader(int i) {
        if (!this.f18iq.mo249Hc()) {
            C0037c loader = this.f18iq.getLoader(i);
            if (loader != null) {
                return loader.mo231Bc();
            }
            return null;
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Class<?> cls = this.f17Kp.getClass();
        sb.append(cls.getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(cls)));
        sb.append("}}");
        return sb.toString();
    }

    /* renamed from: a */
    public C0475f mo224a(int i, Bundle bundle, C0035a aVar) {
        if (this.f18iq.mo249Hc()) {
            throw new IllegalStateException("Called while creating a loader");
        } else if (Looper.getMainLooper() == Looper.myLooper()) {
            C0037c loader = this.f18iq.getLoader(i);
            if (loader == null) {
                return m55a(i, bundle, aVar, (C0475f) null);
            }
            return loader.mo233a(this.f17Kp, aVar);
        } else {
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
    }
}
