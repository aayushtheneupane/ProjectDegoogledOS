package p000;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: duo */
/* compiled from: PG */
final class duo extends gwe {

    /* renamed from: a */
    private final hbl f7413a;

    public duo(hbl hbl) {
        this.f7413a = hbl;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2643a(View view, Object obj) {
        dul dul = (dul) obj;
        dup a = ((dum) view).mo2635n();
        int a2 = dul.mo4432a();
        int i = a2 - 1;
        if (a2 == 0) {
            throw null;
        } else if (i == 0) {
            a.f7424k = Optional.m16285of(dul.mo4437c());
            a.f7422i.mo3758a((cqg) a.f7415b.mo2635n());
            a.f7421h.mo7632a((View) a.f7417d, (View.OnClickListener) new dun(a));
            cnx cnx = a.f7420g;
            ((apj) a.f7419f.mo7309a((Object) new cob(dul.mo4437c().mo3107a().get(), dul.mo4437c().mo3110d(), (ihw) null)).mo1854a((aqu) new bfa(dul.mo4437c().mo3107a()))).mo1426b(cnx.f4748a.f4753b.mo1866f().mo1851a(cnx.f4750c).mo1857a(atc.f1597a).mo1856a((ard) new bas(a.f7423j)).mo1872k()).mo1422a(a.f7417d);
            a.f7416c.setVisibility(0);
            if (((cia) a.f7424k.get()).mo3113f()) {
                a.f7422i.mo3756a((cpt) ede.m7256a((cia) a.f7424k.get()));
            }
            if (a.f7422i.mo3764d()) {
                a.mo2621a();
            }
            a.f7418e.setVisibility(8);
        } else if (i == 1) {
            TextView textView = a.f7418e;
            dul.mo4433b();
            textView.setText(R.string.people_fragment_description);
            a.f7416c.setVisibility(8);
            a.f7418e.setVisibility(0);
        } else if (i == 2) {
            a.f7416c.setVisibility(0);
            a.f7417d.setImageDrawable(a.f7414a.getDrawable(R.drawable.people_grid_placeholder));
            a.f7418e.setVisibility(8);
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ View mo2641a(ViewGroup viewGroup) {
        return new dum(this.f7413a);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2642a(View view) {
        dup a = ((dum) view).mo2635n();
        a.f7419f.mo7311a(a.f7417d);
        a.f7422i.mo3761b((cqg) a.f7415b.mo2635n());
        if (a.f7425l.isPresent()) {
            ((View) a.f7425l.get()).setVisibility(8);
        }
        a.f7424k = Optional.empty();
    }
}
