package p000;

import android.content.Context;
import com.google.android.apps.photosgo.R;

@Deprecated
/* renamed from: hoc */
/* compiled from: PG */
public final class hoc extends hoh {
    public hoc(Context context, hos hos, hpy hpy) {
        super(context, hos, hpy);
    }

    /* renamed from: a */
    public final void mo7625a(int i) {
        ife.m12876b(this.f13150a == -1, (Object) "Cannot set message multiple times.");
        this.f13150a = i;
    }

    /* renamed from: a */
    public final void mo7624a() {
        ife.m12876b(this.f13151b == -1, (Object) "Cannot set positive button multiple times.");
        this.f13151b = R.string.photosgo_videotrimming_cancel_trimming_video;
    }
}
