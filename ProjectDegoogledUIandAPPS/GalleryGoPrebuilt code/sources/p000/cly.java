package p000;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.foldermanagement.creation.SingleVolumeChooserView;
import p003j$.util.Optional;

/* renamed from: cly */
/* compiled from: PG */
final class cly extends gwe {

    /* renamed from: a */
    private final /* synthetic */ LayoutInflater f4655a;

    public cly(LayoutInflater layoutInflater) {
        this.f4655a = layoutInflater;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2643a(View view, Object obj) {
        cjv cjv = (cjv) obj;
        clz c = ((SingleVolumeChooserView) view).mo2635n();
        c.f4660e.f4670a.add(c);
        c.f4656a.setChecked(false);
        c.f4657b.setText(cjv.mo3179d());
        c.f4658c.setText(cjv.mo3180e());
        c.f4659d.setImageResource(cjv.mo3176a());
        c.f4661f = Optional.m16285of(cjv);
        c.mo3251a(c.f4660e.mo3255b());
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ View mo2641a(ViewGroup viewGroup) {
        return (SingleVolumeChooserView) this.f4655a.inflate(R.layout.single_volume_chooser_view, viewGroup, false);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2642a(View view) {
        clz c = ((SingleVolumeChooserView) view).mo2635n();
        c.f4660e.f4670a.remove(c);
        c.f4657b.setText((CharSequence) null);
        c.f4658c.setText((CharSequence) null);
        c.f4659d.setImageDrawable((Drawable) null);
        c.f4661f = Optional.empty();
    }
}
