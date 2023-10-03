package p000;

import android.view.View;
import com.google.android.apps.photosgo.home.HomeNavigationView;

/* renamed from: crk */
/* compiled from: PG */
final /* synthetic */ class crk implements View.OnClickListener {

    /* renamed from: a */
    private final HomeNavigationView f5491a;

    /* renamed from: b */
    private final View.OnClickListener f5492b;

    public crk(HomeNavigationView homeNavigationView, View.OnClickListener onClickListener) {
        this.f5491a = homeNavigationView;
        this.f5492b = onClickListener;
    }

    public final void onClick(View view) {
        HomeNavigationView homeNavigationView = this.f5491a;
        View.OnClickListener onClickListener = this.f5492b;
        homeNavigationView.mo3384b();
        onClickListener.onClick(view);
    }
}
