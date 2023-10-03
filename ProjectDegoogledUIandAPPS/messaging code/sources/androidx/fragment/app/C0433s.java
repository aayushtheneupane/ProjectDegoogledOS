package androidx.fragment.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* renamed from: androidx.fragment.app.s */
public abstract class C0433s {

    /* renamed from: Qo */
    static final C0428n f417Qo = new C0428n();

    /* renamed from: Po */
    private C0428n f418Po = null;

    /* renamed from: a */
    public void mo4429a(C0428n nVar) {
        this.f418Po = nVar;
    }

    public abstract C0396O beginTransaction();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean executePendingTransactions();

    public abstract C0424j findFragmentByTag(String str);

    public abstract List getFragments();

    public abstract boolean isDestroyed();

    public abstract void popBackStack(int i, int i2);

    public abstract boolean popBackStackImmediate();

    /* renamed from: vc */
    public C0428n mo4152vc() {
        if (this.f418Po == null) {
            this.f418Po = f417Qo;
        }
        return this.f418Po;
    }
}
