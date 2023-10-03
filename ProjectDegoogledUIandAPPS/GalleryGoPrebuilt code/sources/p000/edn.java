package p000;

import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.sharing.SingleAppView;

/* renamed from: edn */
/* compiled from: PG */
public final class edn {

    /* renamed from: a */
    public final SingleAppView f8046a;

    /* renamed from: b */
    public final hos f8047b;

    /* renamed from: c */
    public final hbl f8048c;

    /* renamed from: d */
    public final fee f8049d;

    /* renamed from: e */
    public final fdv f8050e;

    /* renamed from: f */
    public final ImageView f8051f;

    /* renamed from: g */
    public final TextView f8052g;

    public edn(SingleAppView singleAppView, hos hos, hbl hbl, fee fee, fdv fdv) {
        this.f8046a = singleAppView;
        this.f8047b = hos;
        this.f8048c = hbl;
        this.f8049d = fee;
        this.f8050e = fdv;
        this.f8051f = (ImageView) singleAppView.findViewById(R.id.app_icon);
        this.f8052g = (TextView) singleAppView.findViewById(R.id.app_icon_label);
    }
}
