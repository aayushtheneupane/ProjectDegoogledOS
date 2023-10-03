package p000;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: dup */
/* compiled from: PG */
public final class dup implements cqg {

    /* renamed from: a */
    public final hbl f7414a;

    /* renamed from: b */
    public final dum f7415b;

    /* renamed from: c */
    public final ViewGroup f7416c;

    /* renamed from: d */
    public final ImageView f7417d;

    /* renamed from: e */
    public final TextView f7418e;

    /* renamed from: f */
    public final hdt f7419f;

    /* renamed from: g */
    public final cnx f7420g;

    /* renamed from: h */
    public final hos f7421h;

    /* renamed from: i */
    public final cqh f7422i;

    /* renamed from: j */
    public final int f7423j;

    /* renamed from: k */
    public Optional f7424k = Optional.empty();

    /* renamed from: l */
    public Optional f7425l = Optional.empty();

    /* renamed from: m */
    public boolean f7426m = false;

    public dup(dum dum, hbl hbl, hdt hdt, cnx cnx, hos hos, cqh cqh) {
        this.f7414a = hbl;
        this.f7415b = dum;
        this.f7419f = hdt;
        this.f7420g = cnx;
        this.f7421h = hos;
        this.f7422i = cqh;
        View inflate = LayoutInflater.from(hbl).inflate(R.layout.people_grid_item_contents, dum, true);
        this.f7416c = (ViewGroup) inflate.findViewById(R.id.image_container);
        this.f7417d = (ImageView) inflate.findViewById(R.id.people_image);
        this.f7418e = (TextView) inflate.findViewById(R.id.footer_text);
        this.f7423j = hbl.getResources().getDimensionPixelSize(R.dimen.people_grid_item_rounded_corner_radius);
    }

    /* renamed from: a */
    public final void mo2621a() {
        if (this.f7424k.isPresent()) {
            this.f7426m = this.f7422i.mo3763c(ede.m7256a((cia) this.f7424k.get()));
            if (!this.f7425l.isPresent()) {
                this.f7425l = Optional.m16285of(((ViewStub) this.f7415b.findViewById(R.id.hide_container)).inflate());
            }
            ((View) this.f7425l.get()).setVisibility(!this.f7426m ? 8 : 0);
        }
    }
}
