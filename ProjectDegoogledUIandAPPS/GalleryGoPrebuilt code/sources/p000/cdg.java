package p000;

import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: cdg */
/* compiled from: PG */
final class cdg implements dtj {

    /* renamed from: a */
    private final /* synthetic */ cdh f4101a;

    public /* synthetic */ cdg(cdh cdh) {
        this.f4101a = cdh;
    }

    /* renamed from: a */
    public final void mo3044a(int i, int i2, boolean z) {
    }

    /* renamed from: b */
    public final void mo3045b() {
        View view = this.f4101a.f4103b.f9573L;
        if (view != null) {
            ((View) ife.m12898e((Object) view.findViewById(R.id.play_button))).setVisibility(0);
            ((View) ife.m12898e((Object) view.findViewById(R.id.pause_button))).setVisibility(8);
        }
    }

    /* renamed from: a */
    public final void mo3043a() {
        View view = this.f4101a.f4103b.f9573L;
        if (view != null) {
            ((View) ife.m12898e((Object) view.findViewById(R.id.play_button))).setVisibility(8);
            ((View) ife.m12898e((Object) view.findViewById(R.id.pause_button))).setVisibility(0);
        }
    }
}
