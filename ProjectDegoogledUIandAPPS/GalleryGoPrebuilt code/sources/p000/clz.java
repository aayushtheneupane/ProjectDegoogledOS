package p000;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.foldermanagement.creation.SingleVolumeChooserView;
import p003j$.util.Optional;

/* renamed from: clz */
/* compiled from: PG */
public final class clz implements cmf {

    /* renamed from: a */
    public final RadioButton f4656a;

    /* renamed from: b */
    public final TextView f4657b;

    /* renamed from: c */
    public final TextView f4658c;

    /* renamed from: d */
    public final ImageView f4659d;

    /* renamed from: e */
    public final cmg f4660e;

    /* renamed from: f */
    public Optional f4661f = Optional.empty();

    public clz(SingleVolumeChooserView singleVolumeChooserView, cmg cmg, hlz hlz) {
        this.f4656a = (RadioButton) singleVolumeChooserView.findViewById(R.id.folder_creation_volume_check);
        this.f4657b = (TextView) singleVolumeChooserView.findViewById(R.id.folder_creation_volume_name);
        this.f4658c = (TextView) singleVolumeChooserView.findViewById(R.id.folder_creation_volume_summary);
        this.f4659d = (ImageView) singleVolumeChooserView.findViewById(R.id.folder_creation_volume_icon);
        this.f4660e = cmg;
        singleVolumeChooserView.setOnClickListener(hlz.mo7575a((View.OnClickListener) new clw(this, singleVolumeChooserView), "volume clicked"));
    }

    /* renamed from: a */
    public final void mo3251a(String str) {
        if (this.f4661f.isPresent()) {
            this.f4656a.setChecked(((cjv) this.f4661f.get()).mo3177b().equals(str));
        }
    }

    /* renamed from: a */
    public final void mo3227a(boolean z) {
        this.f4656a.setEnabled(z);
    }
}
