package p000;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.devicefolders.FolderView;

/* renamed from: bry */
/* compiled from: PG */
public final class bry {

    /* renamed from: a */
    public final FolderView f3466a;

    /* renamed from: b */
    public final hdt f3467b;

    /* renamed from: c */
    public final cnx f3468c;

    /* renamed from: d */
    public final hos f3469d;

    /* renamed from: e */
    public final ImageView f3470e;

    /* renamed from: f */
    public final ImageView f3471f;

    /* renamed from: g */
    public final View f3472g;

    /* renamed from: h */
    public final TextView f3473h;

    /* renamed from: i */
    public final TextView f3474i;

    /* renamed from: j */
    public final int f3475j;

    /* renamed from: k */
    private final hbl f3476k;

    public bry(FolderView folderView, hbl hbl, hdt hdt, cnx cnx, hos hos) {
        this.f3466a = folderView;
        this.f3476k = hbl;
        this.f3467b = hdt;
        this.f3468c = cnx;
        this.f3469d = hos;
        LayoutInflater.from(hbl).inflate(R.layout.folder_contents, folderView);
        this.f3470e = (ImageView) folderView.findViewById(R.id.image1);
        this.f3471f = (ImageView) folderView.findViewById(R.id.sd_card);
        this.f3472g = folderView.findViewById(R.id.sd_card_gradient);
        this.f3473h = (TextView) folderView.findViewById(R.id.folder_name);
        this.f3474i = (TextView) folderView.findViewById(R.id.folder_summary);
        this.f3475j = hbl.getResources().getDimensionPixelSize(R.dimen.folder_grid_item_view_image_rounding_radius);
    }

    /* renamed from: a */
    public final String mo2713a(btl btl) {
        return C0643xp.m15940a(this.f3476k, R.string.folder_summary_item_count_icu, "PHOTO_COUNT", Integer.valueOf(btl.mo2738b().size()));
    }
}
