package p000;

import android.content.Context;
import android.widget.VideoView;
import com.google.android.apps.photosgo.environment.BuildType;
import com.google.android.apps.photosgo.oneup.photo.PhotoView;

/* renamed from: dtm */
/* compiled from: PG */
public final class dtm {

    /* renamed from: a */
    private final iqk f7347a;

    /* renamed from: b */
    private final iqk f7348b;

    /* renamed from: c */
    private final iqk f7349c;

    /* renamed from: d */
    private final iqk f7350d;

    /* renamed from: e */
    private final iqk f7351e;

    /* renamed from: f */
    private final iqk f7352f;

    public dtm(iqk iqk, iqk iqk2, iqk iqk3, iqk iqk4, iqk iqk5, iqk iqk6) {
        this.f7347a = (iqk) m6648a(iqk, 1);
        this.f7348b = (iqk) m6648a(iqk2, 2);
        this.f7349c = (iqk) m6648a(iqk3, 3);
        this.f7350d = (iqk) m6648a(iqk4, 4);
        this.f7351e = (iqk) m6648a(iqk5, 5);
        this.f7352f = (iqk) m6648a(iqk6, 6);
    }

    /* renamed from: a */
    private static Object m6648a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(93);
        sb.append("@AutoFactory method argument is null but is not marked @Nullable. Argument index: ");
        sb.append(i);
        throw new NullPointerException(sb.toString());
    }

    /* renamed from: a */
    public final dtl mo4423a(VideoView videoView, dsw dsw, PhotoView photoView, hdt hdt) {
        VideoView videoView2 = videoView;
        return new dtl((VideoView) m6648a(videoView, 1), dsw, photoView, hdt, (Context) m6648a((Context) this.f7347a.mo2097a(), 5), (C0147fh) m6648a((C0147fh) this.f7348b.mo2097a(), 6), (cny) m6648a((cny) this.f7349c.mo2097a(), 7), (iel) m6648a((iel) this.f7350d.mo2097a(), 8), (exm) m6648a((exm) this.f7351e.mo2097a(), 9), (BuildType) m6648a((BuildType) this.f7352f.mo2097a(), 10));
    }
}
