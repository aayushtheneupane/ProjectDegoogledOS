package p000;

import android.support.p002v7.widget.RecyclerView;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* renamed from: eu */
/* compiled from: PG */
public final class C0133eu extends C0182gn implements C0168gb {

    /* renamed from: a */
    public final C0171gd f9018a;

    /* renamed from: b */
    public int f9019b;

    /* renamed from: s */
    private boolean f9020s;

    public C0133eu(C0171gd gdVar) {
        gdVar.mo6455m();
        C0160fu fuVar = gdVar.f10991j;
        if (fuVar != null) {
            fuVar.f10593c.getClassLoader();
        }
        this.f9019b = -1;
        this.f9018a = gdVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5245a(int i) {
        if (this.f11650i) {
            if (C0171gd.m10054a(2)) {
                "Bump nesting in " + this + " by " + i;
            }
            int size = this.f11644c.size();
            for (int i2 = 0; i2 < size; i2++) {
                C0180gm gmVar = (C0180gm) this.f11644c.get(i2);
                C0147fh fhVar = gmVar.f11607b;
                if (fhVar != null) {
                    fhVar.f9603v += i;
                    if (C0171gd.m10054a(2)) {
                        "Bump nesting of " + gmVar.f11607b + " to " + gmVar.f11607b.f9603v;
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final void mo5251b() {
        m8165a(false);
    }

    /* renamed from: c */
    public final void mo5252c() {
        m8165a(true);
    }

    /* renamed from: a */
    private final void m8165a(boolean z) {
        if (!this.f9020s) {
            if (C0171gd.m10054a(2)) {
                "Commit: " + this;
                PrintWriter printWriter = new PrintWriter(new C0295ks());
                mo5247a("  ", printWriter);
                printWriter.close();
            }
            this.f9020s = true;
            if (!this.f11650i) {
                this.f9019b = -1;
            } else {
                this.f9019b = this.f9018a.f10987f.getAndIncrement();
            }
            this.f9018a.mo6425a((C0168gb) this, z);
            return;
        }
        throw new IllegalStateException("commit already called");
    }

    /* renamed from: a */
    public final void mo5244a() {
        mo6854d();
        this.f9018a.mo6436b(this, false);
    }

    /* renamed from: a */
    public final void mo5246a(int i, C0147fh fhVar, String str, int i2) {
        Class<?> cls = fhVar.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fhVar.f9564C;
            if (str2 == null || str.equals(str2)) {
                fhVar.f9564C = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fhVar + ": was " + fhVar.f9564C + " now " + str);
            }
        }
        if (i != 0) {
            if (i != -1) {
                int i3 = fhVar.f9562A;
                if (i3 == 0 || i3 == i) {
                    fhVar.f9562A = i;
                    fhVar.f9563B = i;
                } else {
                    throw new IllegalStateException("Can't change container ID of fragment " + fhVar + ": was " + fhVar.f9562A + " now " + i);
                }
            } else {
                throw new IllegalArgumentException("Can't add fragment " + fhVar + " with tag " + str + " to container view with no id");
            }
        }
        mo6852a(new C0180gm(i2, fhVar));
        fhVar.f9604w = this.f9018a;
    }

    /* renamed from: a */
    public final void mo5247a(String str, PrintWriter printWriter) {
        mo5248a(str, printWriter, true);
    }

    /* renamed from: a */
    public final void mo5248a(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f11652k);
            printWriter.print(" mIndex=");
            printWriter.print(this.f9019b);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f9020s);
            if (this.f11649h != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f11649h));
            }
            if (!(this.f11645d == 0 && this.f11646e == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f11645d));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f11646e));
            }
            if (!(this.f11647f == 0 && this.f11648g == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f11647f));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f11648g));
            }
            if (!(this.f11653l == 0 && this.f11654m == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f11653l));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f11654m);
            }
            if (!(this.f11655n == 0 && this.f11656o == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f11655n));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f11656o);
            }
        }
        if (!this.f11644c.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Operations:");
            int size = this.f11644c.size();
            for (int i = 0; i < size; i++) {
                C0180gm gmVar = (C0180gm) this.f11644c.get(i);
                switch (gmVar.f11606a) {
                    case 0:
                        str2 = "NULL";
                        break;
                    case 1:
                        str2 = "ADD";
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        str2 = "REPLACE";
                        break;
                    case 3:
                        str2 = "REMOVE";
                        break;
                    case 4:
                        str2 = "HIDE";
                        break;
                    case 5:
                        str2 = "SHOW";
                        break;
                    case 6:
                        str2 = "DETACH";
                        break;
                    case 7:
                        str2 = "ATTACH";
                        break;
                    case 8:
                        str2 = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        str2 = "UNSET_PRIMARY_NAV";
                        break;
                    case 10:
                        str2 = "OP_SET_MAX_LIFECYCLE";
                        break;
                    default:
                        str2 = "cmd=" + gmVar.f11606a;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str2);
                printWriter.print(" ");
                printWriter.println(gmVar.f11607b);
                if (z) {
                    if (!(gmVar.f11608c == 0 && gmVar.f11609d == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(gmVar.f11608c));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(gmVar.f11609d));
                    }
                    if (gmVar.f11610e != 0 || gmVar.f11611f != 0) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(gmVar.f11610e));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(gmVar.f11611f));
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public final boolean mo5249a(ArrayList arrayList, ArrayList arrayList2) {
        if (C0171gd.m10054a(2)) {
            "Run: " + this;
        }
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.f11650i) {
            return true;
        }
        C0171gd gdVar = this.f9018a;
        if (gdVar.f10983b == null) {
            gdVar.f10983b = new ArrayList();
        }
        gdVar.f10983b.add(this);
        return true;
    }

    /* renamed from: a */
    public final C0182gn mo5243a(C0147fh fhVar) {
        C0171gd gdVar = fhVar.f9604w;
        if (gdVar == null || gdVar == this.f9018a) {
            mo6852a(new C0180gm(3, fhVar));
            return this;
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fhVar.toString() + " is already attached to a FragmentManager.");
    }

    /* renamed from: b */
    public final C0182gn mo5250b(C0147fh fhVar) {
        C0171gd gdVar = fhVar.f9604w;
        if (gdVar == null || gdVar == this.f9018a) {
            mo6852a(new C0180gm(5, fhVar));
            return this;
        }
        throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment " + fhVar.toString() + " is already attached to a FragmentManager.");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f9019b >= 0) {
            sb.append(" #");
            sb.append(this.f9019b);
        }
        if (this.f11652k != null) {
            sb.append(" ");
            sb.append(this.f11652k);
        }
        sb.append("}");
        return sb.toString();
    }
}
