package p000;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.category.CategoryListView;
import com.google.android.apps.photosgo.photogrid.DateHeaderView;
import com.google.android.apps.photosgo.photogrid.PhotosPromoView;
import com.google.android.apps.photosgo.photogrid.SinglePhotoView;

/* renamed from: dwa */
/* compiled from: PG */
public final class dwa {

    /* renamed from: a */
    public dwu f7472a;

    /* renamed from: b */
    public PhotosPromoView f7473b;

    /* renamed from: c */
    public CategoryListView f7474c;

    /* renamed from: d */
    public final SinglePhotoView f7475d;

    /* renamed from: e */
    public final DateHeaderView f7476e;

    /* renamed from: f */
    public final ImageView f7477f;

    /* renamed from: g */
    public final View f7478g;

    public dwa(dvy dvy, hbl hbl) {
        LayoutInflater.from(hbl).inflate(R.layout.grid_item_contents, dvy);
        this.f7475d = (SinglePhotoView) dvy.findViewById(R.id.single_photo);
        this.f7476e = (DateHeaderView) dvy.findViewById(R.id.header);
        this.f7477f = (ImageView) dvy.findViewById(R.id.header_placeholder);
        this.f7478g = dvy;
    }

    /* renamed from: a */
    public final void mo4518a(int i) {
        dxu a = this.f7475d.mo2635n();
        a.f7596k = i;
        float f = (float) i;
        float f2 = a.f7590e;
        a.f7602q = (f - (f2 + f2)) / f;
    }
}
