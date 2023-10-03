package p000;

import android.graphics.drawable.Drawable;
import com.google.android.apps.photosgo.oneup.video.VideoControlsView;

/* renamed from: dti */
/* compiled from: PG */
final class dti implements bee {

    /* renamed from: a */
    private final /* synthetic */ dsw f7320a;

    public dti(dsw dsw) {
        this.f7320a = dsw;
    }

    /* renamed from: a */
    public final boolean mo1486a(atu atu) {
        dsw dsw = this.f7320a;
        if (dsw == null) {
            return true;
        }
        dsw.mo4395g();
        return true;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1487a(Object obj) {
        int i;
        Drawable drawable = (Drawable) obj;
        dsw dsw = this.f7320a;
        if (dsw != null) {
            VideoControlsView videoControlsView = dsw.f7295a;
            if (!dsw.f7297c.f7121b) {
                i = 0;
            } else {
                i = 8;
            }
            videoControlsView.setVisibility(i);
        }
        return false;
    }
}
