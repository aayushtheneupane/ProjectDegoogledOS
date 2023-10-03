package p000a.p008d.p009a;

import androidx.lifecycle.C0463t;
import androidx.loader.content.C0475f;
import java.io.PrintWriter;

/* renamed from: a.d.a.d */
class C0038d implements C0463t {
    private final C0035a mCallback;
    private boolean mDeliveredData = false;
    private final C0475f mLoader;

    C0038d(C0475f fVar, C0035a aVar) {
        this.mLoader = fVar;
        this.mCallback = aVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Jc */
    public boolean mo240Jc() {
        return this.mDeliveredData;
    }

    /* renamed from: a */
    public void mo241a(Object obj) {
        this.mDeliveredData = true;
        this.mCallback.mo221a(this.mLoader, obj);
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.print("mDeliveredData=");
        printWriter.println(this.mDeliveredData);
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        if (this.mDeliveredData) {
            this.mCallback.mo220a(this.mLoader);
        }
    }

    public String toString() {
        return this.mCallback.toString();
    }
}
