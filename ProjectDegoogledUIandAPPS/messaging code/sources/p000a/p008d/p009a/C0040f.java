package p000a.p008d.p009a;

import androidx.lifecycle.C0442A;
import androidx.lifecycle.C0466w;
import androidx.lifecycle.C0467x;
import androidx.lifecycle.C0469z;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import p000a.p005b.C0028o;

/* renamed from: a.d.a.f */
class C0040f extends C0466w {

    /* renamed from: hq */
    private static final C0467x f16hq = new C0039e();
    private boolean mCreatingLoader = false;
    private C0028o mLoaders = new C0028o(10);

    C0040f() {
    }

    /* renamed from: a */
    static C0040f m47a(C0442A a) {
        return (C0040f) new C0469z(a, f16hq).mo4482d(C0040f.class);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Cc */
    public void mo246Cc() {
        int size = this.mLoaders.size();
        for (int i = 0; i < size; i++) {
            ((C0037c) this.mLoaders.valueAt(i)).mo232Cc();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: Dc */
    public void mo247Dc() {
        int size = this.mLoaders.size();
        for (int i = 0; i < size; i++) {
            ((C0037c) this.mLoaders.valueAt(i)).mo230B(true);
        }
        this.mLoaders.clear();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Gc */
    public void mo248Gc() {
        this.mCreatingLoader = false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Hc */
    public boolean mo249Hc() {
        return this.mCreatingLoader;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ic */
    public void mo250Ic() {
        this.mCreatingLoader = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: O */
    public void mo251O(int i) {
        this.mLoaders.remove(i);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (this.mLoaders.size() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            String str2 = str + "    ";
            for (int i = 0; i < this.mLoaders.size(); i++) {
                C0037c cVar = (C0037c) this.mLoaders.valueAt(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(this.mLoaders.keyAt(i));
                printWriter.print(": ");
                printWriter.println(cVar.toString());
                cVar.dump(str2, fileDescriptor, printWriter, strArr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public C0037c getLoader(int i) {
        return (C0037c) this.mLoaders.get(i, (Object) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo252a(int i, C0037c cVar) {
        this.mLoaders.put(i, cVar);
    }
}
