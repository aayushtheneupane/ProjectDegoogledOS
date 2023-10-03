package p000;

import android.view.LayoutInflater;
import android.view.ViewStub;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.photo.OneUpPhotoView;
import com.google.android.apps.photosgo.oneup.video.OneUpVideoView;
import p003j$.util.Optional;

/* renamed from: dlw */
/* compiled from: PG */
public final class dlw {

    /* renamed from: a */
    public final dln f6805a;

    /* renamed from: b */
    public final OneUpPhotoView f6806b;

    /* renamed from: c */
    public final bkt f6807c;

    /* renamed from: d */
    public final dll f6808d;

    /* renamed from: e */
    public final doj f6809e;

    /* renamed from: f */
    public OneUpVideoView f6810f;

    /* renamed from: g */
    public Optional f6811g = Optional.empty();

    public dlw(dln dln, hbl hbl, bkt bkt, dlm dlm) {
        this.f6807c = bkt;
        LayoutInflater.from(hbl).inflate(R.layout.media_view_contents, dln);
        this.f6805a = dln;
        this.f6806b = (OneUpPhotoView) dln.findViewById(R.id.photo);
        this.f6808d = new dll((hdt) dlm.m6305a((hdt) dlm.f6788a.mo2097a(), 1), (cny) dlm.m6305a((cny) dlm.f6789b.mo2097a(), 2), (hbl) dlm.m6305a((hbl) dlm.f6790c.mo2097a(), 3), (fee) dlm.m6305a((fee) dlm.f6791d.mo2097a(), 4), (ViewStub) dlm.m6305a((ViewStub) dln.findViewById(R.id.photo_view_launch_overlay_stub), 5));
        ViewStub viewStub = (ViewStub) dln.findViewById(R.id.photo_view_processing_overlay_stub);
        if (viewStub != null) {
            this.f6809e = new doj(viewStub);
            return;
        }
        StringBuilder sb = new StringBuilder(93);
        sb.append("@AutoFactory method argument is null but is not marked @Nullable. Argument index: 1");
        throw new NullPointerException(sb.toString());
    }
}
