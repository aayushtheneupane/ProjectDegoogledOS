package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

/* renamed from: fts */
/* compiled from: PG */
public final class fts extends ContextWrapper {

    /* renamed from: a */
    public final C0147fh f10583a;

    /* renamed from: b */
    public final boolean f10584b;

    /* renamed from: c */
    private LayoutInflater f10585c;

    /* renamed from: d */
    private LayoutInflater f10586d;

    public fts(Context context, C0147fh fhVar, boolean z) {
        super((Context) ife.m12898e((Object) context));
        this.f10585c = null;
        this.f10583a = (C0147fh) ife.m12898e((Object) fhVar);
        this.f10584b = z;
    }

    public fts(LayoutInflater layoutInflater, C0147fh fhVar, boolean z) {
        super((Context) ife.m12898e((Object) ((LayoutInflater) ife.m12898e((Object) layoutInflater)).getContext()));
        this.f10585c = layoutInflater;
        this.f10583a = (C0147fh) ife.m12898e((Object) fhVar);
        this.f10584b = z;
    }

    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f10586d == null) {
            if (this.f10585c == null) {
                this.f10585c = (LayoutInflater) getBaseContext().getSystemService("layout_inflater");
            }
            this.f10586d = this.f10585c.cloneInContext(this);
        }
        return this.f10586d;
    }
}
