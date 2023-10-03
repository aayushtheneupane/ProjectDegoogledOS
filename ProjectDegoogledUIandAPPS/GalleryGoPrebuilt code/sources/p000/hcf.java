package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

/* renamed from: hcf */
/* compiled from: PG */
public final class hcf extends ContextWrapper implements hbs {

    /* renamed from: a */
    private final hqk f12471a;

    public hcf(Context context) {
        super((Context) ife.m12898e((Object) context));
        this.f12471a = ife.m12811a((hqk) new hcd(this));
    }

    public hcf(LayoutInflater layoutInflater) {
        super((Context) ife.m12898e((Object) ((LayoutInflater) ife.m12898e((Object) layoutInflater)).getContext()));
        this.f12471a = ife.m12811a((hqk) new hce(this, layoutInflater));
    }

    public final Object getSystemService(String str) {
        if ("layout_inflater".equals(str)) {
            return this.f12471a.mo2652a();
        }
        return getBaseContext().getSystemService(str);
    }
}
