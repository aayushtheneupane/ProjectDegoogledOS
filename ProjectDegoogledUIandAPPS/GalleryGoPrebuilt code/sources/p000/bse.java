package p000;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.devicefolders.FolderView;
import com.google.android.apps.photosgo.devicefolders.NewFolderView;
import com.google.android.apps.photosgo.devicefolders.PromoView;

/* renamed from: bse */
/* compiled from: PG */
public final class bse {

    /* renamed from: a */
    public final FolderView f3481a;

    /* renamed from: b */
    public final NewFolderView f3482b;

    /* renamed from: c */
    public PromoView f3483c = null;

    /* renamed from: d */
    public int f3484d;

    /* renamed from: e */
    private final View f3485e;

    public bse(bsc bsc, hbl hbl) {
        LayoutInflater.from(hbl).inflate(R.layout.folders_grid_item_contents, bsc);
        this.f3481a = (FolderView) bsc.findViewById(R.id.folder_grid_item);
        this.f3482b = (NewFolderView) bsc.findViewById(R.id.new_folder_item);
        this.f3485e = bsc;
    }

    /* renamed from: a */
    public final void mo2718a() {
        if (this.f3483c == null) {
            this.f3483c = (PromoView) ((ViewStub) this.f3485e.findViewById(R.id.promo_stub)).inflate();
        }
    }
}
