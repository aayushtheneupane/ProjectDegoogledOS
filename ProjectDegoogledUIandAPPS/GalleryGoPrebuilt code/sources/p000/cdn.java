package p000;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.videotrimming.fragment.VideoThumbnailView;
import java.util.ArrayList;
import java.util.List;

/* renamed from: cdn */
/* compiled from: PG */
public final class cdn {

    /* renamed from: a */
    public final hbl f4121a;

    /* renamed from: b */
    public final VideoThumbnailView f4122b;

    /* renamed from: c */
    public final hlz f4123c;

    /* renamed from: d */
    public final int f4124d;

    /* renamed from: e */
    public final int f4125e;

    /* renamed from: f */
    public final int f4126f;

    /* renamed from: g */
    public final List f4127g = new ArrayList();

    /* renamed from: h */
    public int f4128h = 0;

    /* renamed from: i */
    public cdm f4129i;

    public cdn(hbl hbl, VideoThumbnailView videoThumbnailView, hlz hlz) {
        this.f4121a = hbl;
        this.f4122b = videoThumbnailView;
        this.f4123c = hlz;
        Resources resources = videoThumbnailView.getResources();
        this.f4124d = resources.getDimensionPixelSize(R.dimen.photosgo_videotrimming_trimview_thumbnail_height);
        this.f4125e = resources.getDimensionPixelSize(R.dimen.photosgo_videotrimming_trimview_thumbnail_margin);
        this.f4126f = resources.getColor(R.color.quantum_grey800);
        videoThumbnailView.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    /* renamed from: a */
    public final int mo3050a() {
        int i = 0;
        while (i < this.f4122b.getChildCount()) {
            View childAt = this.f4122b.getChildAt(i);
            boolean z = childAt instanceof C0533tn;
            String name = childAt.getClass().getName();
            if (z) {
                i++;
            } else {
                throw new IllegalStateException(ife.m12834a("unexpected child %s is class %s", Integer.valueOf(i), name));
            }
        }
        return this.f4122b.getChildCount();
    }

    /* renamed from: b */
    public final void mo3051b() {
        int i = 0;
        if (this.f4127g.isEmpty() || this.f4128h != this.f4127g.size()) {
            int a = mo3050a();
            this.f4122b.setBackground((Drawable) null);
            while (i < a) {
                ((C0533tn) this.f4122b.getChildAt(i)).setImageDrawable((Drawable) null);
                i++;
            }
            return;
        }
        ife.m12896d(this.f4128h == mo3050a());
        this.f4122b.setBackgroundResource(R.color.google_black);
        while (i < this.f4128h) {
            ((C0533tn) this.f4122b.getChildAt(i)).setImageBitmap((Bitmap) this.f4127g.get(i));
            i++;
        }
        this.f4122b.invalidate();
    }
}
