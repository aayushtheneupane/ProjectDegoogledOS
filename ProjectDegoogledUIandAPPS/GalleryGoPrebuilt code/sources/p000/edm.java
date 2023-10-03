package p000;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.sharing.SingleAppView;

/* renamed from: edm */
/* compiled from: PG */
final class edm extends gwe {

    /* renamed from: a */
    private final /* synthetic */ LayoutInflater f8045a;

    public edm(LayoutInflater layoutInflater) {
        this.f8045a = layoutInflater;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2643a(View view, Object obj) {
        eea eea = (eea) obj;
        edn c = ((SingleAppView) view).mo2635n();
        c.f8052g.setText(eea.mo4682a());
        c.f8051f.setImageDrawable(eea.mo4680a(c.f8048c));
        c.f8049d.f9364c.mo5563a(74893).mo5560a((View) c.f8046a);
        if (eea.mo4684b()) {
            c.f8047b.mo7632a((View) c.f8046a, (View.OnClickListener) new edk(c, eea));
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ View mo2641a(ViewGroup viewGroup) {
        return (SingleAppView) this.f8045a.inflate(R.layout.single_app, viewGroup, false);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2642a(View view) {
        edn c = ((SingleAppView) view).mo2635n();
        c.f8052g.setText((CharSequence) null);
        c.f8051f.setImageDrawable((Drawable) null);
        c.f8046a.setOnClickListener((View.OnClickListener) null);
        fee.m8692a(c.f8046a);
    }
}
