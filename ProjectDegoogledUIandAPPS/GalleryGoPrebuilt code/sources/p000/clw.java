package p000;

import android.view.View;
import com.google.android.apps.photosgo.foldermanagement.creation.SingleVolumeChooserView;

/* renamed from: clw */
/* compiled from: PG */
final /* synthetic */ class clw implements View.OnClickListener {

    /* renamed from: a */
    private final clz f4652a;

    /* renamed from: b */
    private final SingleVolumeChooserView f4653b;

    public clw(clz clz, SingleVolumeChooserView singleVolumeChooserView) {
        this.f4652a = clz;
        this.f4653b = singleVolumeChooserView;
    }

    public final void onClick(View view) {
        clz clz = this.f4652a;
        SingleVolumeChooserView singleVolumeChooserView = this.f4653b;
        if (clz.f4656a.isEnabled()) {
            clz.f4661f.ifPresent(new clx(singleVolumeChooserView));
        }
    }
}
