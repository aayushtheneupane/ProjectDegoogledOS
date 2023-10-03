package p000;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: atu */
/* compiled from: PG */
public final class atu extends Exception {

    /* renamed from: a */
    private static final StackTraceElement[] f1668a = new StackTraceElement[0];
    public static final long serialVersionUID = 1;

    /* renamed from: b */
    private final List f1669b;

    /* renamed from: c */
    private aqu f1670c;

    /* renamed from: d */
    private Class f1671d;

    /* renamed from: e */
    private String f1672e;

    /* renamed from: f */
    private int f1673f;

    public final Throwable fillInStackTrace() {
        return this;
    }

    public atu(String str) {
        this(str, Collections.emptyList());
    }

    public atu(String str, Throwable th) {
        this(str, Collections.singletonList(th));
    }

    public atu(String str, List list) {
        this.f1672e = str;
        setStackTrace(f1668a);
        this.f1669b = list;
    }

    /* renamed from: a */
    private final void m1653a(Throwable th, List list) {
        if (th instanceof atu) {
            for (Throwable a : ((atu) th).f1669b) {
                m1653a(a, list);
            }
            return;
        }
        list.add(th);
    }

    /* renamed from: a */
    private static void m1652a(Throwable th, Appendable appendable) {
        try {
            appendable.append(th.getClass().toString()).append(": ").append(th.getMessage()).append(10);
        } catch (IOException e) {
            throw new RuntimeException(th);
        }
    }

    public final String getMessage() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder(71);
        sb.append(this.f1672e);
        Class cls = this.f1671d;
        String str3 = "";
        if (cls != null) {
            String valueOf = String.valueOf(cls);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 2);
            sb2.append(", ");
            sb2.append(valueOf);
            str = sb2.toString();
        } else {
            str = str3;
        }
        sb.append(str);
        int i = this.f1673f;
        if (i == 0) {
            str2 = str3;
        } else {
            String a = C0652xy.m16064a(i);
            StringBuilder sb3 = new StringBuilder(a.length() + 2);
            sb3.append(", ");
            sb3.append(a);
            str2 = sb3.toString();
        }
        sb.append(str2);
        aqu aqu = this.f1670c;
        if (aqu != null) {
            String valueOf2 = String.valueOf(aqu);
            StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf2).length() + 2);
            sb4.append(", ");
            sb4.append(valueOf2);
            str3 = sb4.toString();
        }
        sb.append(str3);
        List a2 = mo1614a();
        if (a2.isEmpty()) {
            return sb.toString();
        }
        if (a2.size() == 1) {
            sb.append("\nThere was 1 cause:");
        } else {
            sb.append("\nThere were ");
            sb.append(a2.size());
            sb.append(" causes:");
        }
        int size = a2.size();
        for (int i2 = 0; i2 < size; i2++) {
            Throwable th = (Throwable) a2.get(i2);
            sb.append(10);
            sb.append(th.getClass().getName());
            sb.append('(');
            sb.append(th.getMessage());
            sb.append(')');
        }
        sb.append("\n call GlideException#logRootCauses(String) for more detail");
        return sb.toString();
    }

    /* renamed from: a */
    public final List mo1614a() {
        ArrayList arrayList = new ArrayList();
        m1653a((Throwable) this, (List) arrayList);
        return arrayList;
    }

    public final void printStackTrace() {
        ifn.m12933a((Throwable) this, System.err);
    }

    public final void printStackTrace(PrintStream printStream) {
        m1651a(printStream);
    }

    public final void printStackTrace(PrintWriter printWriter) {
        m1651a(printWriter);
    }

    /* renamed from: a */
    private final void m1651a(Appendable appendable) {
        m1652a((Throwable) this, appendable);
        List list = this.f1669b;
        att att = new att(appendable);
        try {
            int size = list.size();
            int i = 0;
            while (i < size) {
                att.append("Cause (");
                int i2 = i + 1;
                att.append(String.valueOf(i2));
                att.append(" of ");
                att.append(String.valueOf(size));
                att.append("): ");
                Throwable th = (Throwable) list.get(i);
                if (th instanceof atu) {
                    ((atu) th).m1651a(att);
                } else {
                    m1652a(th, (Appendable) att);
                }
                i = i2;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo1615a(aqu aqu, int i, Class cls) {
        this.f1670c = aqu;
        this.f1673f = i;
        this.f1671d = cls;
    }
}
