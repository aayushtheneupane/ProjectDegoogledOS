package p000;

import android.view.MenuItem;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: cda */
/* compiled from: PG */
final /* synthetic */ class cda implements C0690zi {

    /* renamed from: a */
    private final cdh f4094a;

    public cda(cdh cdh) {
        this.f4094a = cdh;
    }

    /* renamed from: a */
    public final boolean mo2636a(MenuItem menuItem) {
        cdh cdh = this.f4094a;
        if (menuItem.getItemId() != R.id.editor_top_bar_save) {
            return false;
        }
        if (cdh.f4116o != null) {
            cef d = cdh.mo3049d();
            ccb h = ccc.m4033h();
            h.mo3021b((long) d.mo3073c());
            h.mo3018a((long) d.mo3075d());
            h.mo3019a((cxi) ife.m12898e((Object) cdh.f4116o));
            if (!cdh.f4104c.f4036e.isEmpty()) {
                h.f4041a = Optional.m16285of(cdh.f4104c.f4036e);
            }
            cdz cdz = cdh.f4108g;
            ccc a = h.mo3017a();
            cbv cbv = (cbv) a;
            Object[] objArr = {a.mo3022g(), Long.valueOf(cbv.f4026c), Long.valueOf(cbv.f4027d)};
            if (cdz.m4160a(cdz.f4143a) == null) {
                cdr cdr = new cdr();
                ftr.m9611b(cdr);
                ftr.m9610a((C0147fh) cdr);
                cdr.mo5429b(cdz.f4143a.mo5659r(), "dialog");
                cdz.f4144b.mo6987a(grw.m10688c(cdz.f4145c.mo3023a(a)), cdz.f4146d);
                return true;
            }
        }
        return true;
    }
}
