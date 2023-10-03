package p000a.p008d.p009a;

import android.os.Bundle;
import androidx.lifecycle.C0443B;
import androidx.lifecycle.C0453j;
import androidx.loader.content.C0475f;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: a.d.a.b */
public abstract class C0036b {
    /* renamed from: h */
    public static C0036b m33h(C0453j jVar) {
        return new C0041g(jVar, ((C0443B) jVar).getViewModelStore());
    }

    /* renamed from: Cc */
    public abstract void mo223Cc();

    /* renamed from: a */
    public abstract C0475f mo224a(int i, Bundle bundle, C0035a aVar);

    /* renamed from: b */
    public abstract C0475f mo225b(int i, Bundle bundle, C0035a aVar);

    public abstract void destroyLoader(int i);

    @Deprecated
    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract C0475f getLoader(int i);
}
