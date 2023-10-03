package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;

/* renamed from: ftv */
/* compiled from: PG */
public final class ftv implements ioe {

    /* renamed from: a */
    private volatile Object f10587a;

    /* renamed from: b */
    private final Object f10588b = new Object();

    /* renamed from: c */
    private final View f10589c;

    public ftv(View view) {
        this.f10589c = view;
    }

    /* renamed from: a */
    private final Context m9618a(Class cls) {
        boolean z;
        Context a = m9617a(this.f10589c.getContext(), cls);
        if (a != m9617a(a.getApplicationContext(), ioe.class)) {
            z = true;
        } else {
            z = false;
        }
        ife.m12878b(z, "%s, Sting view cannot be created using the application context. Use an Sting Fragment or Activity context.", (Object) this.f10589c.getClass());
        return a;
    }

    /* renamed from: b */
    public final Object mo2453b() {
        Object obj;
        if (this.f10587a == null) {
            synchronized (this.f10588b) {
                if (this.f10587a == null) {
                    Context a = m9618a(fts.class);
                    if (a instanceof fts) {
                        fts fts = (fts) a;
                        ife.m12878b(fts.f10584b, "%s, @WithAccount Sting Views may only attach to @WithAccount @Sting Fragments.", (Object) this.f10589c.getClass());
                        if (fts.f10584b) {
                            obj = ((ftt) ((ioe) fts.f10583a).mo2453b()).mo2393O().mo2141a(this.f10589c).mo2140a();
                        } else {
                            obj = ((ftu) ((ioe) fts.f10583a).mo2453b()).mo2450b().mo2133a(this.f10589c).mo2132a();
                        }
                        this.f10587a = obj;
                    } else {
                        ife.m12878b(!(m9618a(ioe.class) instanceof ioe), "%s, @WithFragmentBindings Sting view must be attached to an @Sting Fragment.", (Object) this.f10589c.getClass());
                        throw new IllegalStateException(String.format("%s, Sting view must be attached to an @Sting Fragment or Activity.", new Object[]{this.f10589c.getClass()}));
                    }
                }
            }
        }
        return this.f10587a;
    }

    /* renamed from: a */
    private static Context m9617a(Context context, Class cls) {
        while ((context instanceof ContextWrapper) && !cls.isInstance(context)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return context;
    }
}
