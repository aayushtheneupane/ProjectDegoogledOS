package p000a.p008d.p009a;

import android.os.Bundle;
import android.os.Looper;
import androidx.lifecycle.C0453j;
import androidx.lifecycle.C0462s;
import androidx.lifecycle.C0463t;
import androidx.loader.content.C0474e;
import androidx.loader.content.C0475f;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: a.d.a.c */
public class C0037c extends C0462s implements C0474e {

    /* renamed from: Kp */
    private C0453j f14Kp;

    /* renamed from: Zp */
    private C0475f f15Zp;
    private final Bundle mArgs;
    private final int mId;
    private final C0475f mLoader;
    private C0038d mObserver;

    C0037c(int i, Bundle bundle, C0475f fVar, C0475f fVar2) {
        this.mId = i;
        this.mArgs = bundle;
        this.mLoader = fVar;
        this.f15Zp = fVar2;
        this.mLoader.mo4504a(i, this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ac */
    public void mo229Ac() {
        this.mLoader.stopLoading();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: B */
    public C0475f mo230B(boolean z) {
        this.mLoader.cancelLoad();
        this.mLoader.abandon();
        C0038d dVar = this.mObserver;
        if (dVar != null) {
            super.mo234a((C0463t) dVar);
            this.f14Kp = null;
            this.mObserver = null;
            if (z) {
                dVar.reset();
            }
        }
        this.mLoader.mo4505a(this);
        if ((dVar == null || dVar.mo240Jc()) && !z) {
            return this.mLoader;
        }
        this.mLoader.reset();
        return this.f15Zp;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Bc */
    public C0475f mo231Bc() {
        return this.mLoader;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Cc */
    public void mo232Cc() {
        C0453j jVar = this.f14Kp;
        C0038d dVar = this.mObserver;
        if (jVar != null && dVar != null) {
            super.mo234a((C0463t) dVar);
            mo4468a(jVar, dVar);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C0475f mo233a(C0453j jVar, C0035a aVar) {
        C0038d dVar = new C0038d(this.mLoader, aVar);
        mo4468a(jVar, dVar);
        C0038d dVar2 = this.mObserver;
        if (dVar2 != null) {
            super.mo234a((C0463t) dVar2);
            this.f14Kp = null;
            this.mObserver = null;
        }
        this.f14Kp = jVar;
        this.mObserver = dVar;
        return this.mLoader;
    }

    /* renamed from: b */
    public void mo235b(C0475f fVar, Object obj) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.setValue(obj);
            C0475f fVar2 = this.f15Zp;
            if (fVar2 != null) {
                fVar2.reset();
                this.f15Zp = null;
                return;
            }
            return;
        }
        mo4471l(obj);
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mId=");
        printWriter.print(this.mId);
        printWriter.print(" mArgs=");
        printWriter.println(this.mArgs);
        printWriter.print(str);
        printWriter.print("mLoader=");
        printWriter.println(this.mLoader);
        this.mLoader.dump(C0632a.m1025k(str, "  "), fileDescriptor, printWriter, strArr);
        if (this.mObserver != null) {
            printWriter.print(str);
            printWriter.print("mCallbacks=");
            printWriter.println(this.mObserver);
            this.mObserver.dump(C0632a.m1025k(str, "  "), printWriter);
        }
        printWriter.print(str);
        printWriter.print("mData=");
        printWriter.println(mo231Bc().dataToString(getValue()));
        printWriter.print(str);
        printWriter.print("mStarted=");
        printWriter.println(mo4472zc());
    }

    /* access modifiers changed from: protected */
    public void onActive() {
        this.mLoader.startLoading();
    }

    public void setValue(Object obj) {
        super.setValue(obj);
        C0475f fVar = this.f15Zp;
        if (fVar != null) {
            fVar.reset();
            this.f15Zp = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("LoaderInfo{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" #");
        sb.append(this.mId);
        sb.append(" : ");
        Class<?> cls = this.mLoader.getClass();
        sb.append(cls.getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(cls)));
        sb.append("}}");
        return sb.toString();
    }

    /* renamed from: a */
    public void mo234a(C0463t tVar) {
        super.mo234a(tVar);
        this.f14Kp = null;
        this.mObserver = null;
    }
}
