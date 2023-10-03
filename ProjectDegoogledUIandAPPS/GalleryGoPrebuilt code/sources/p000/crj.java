package p000;

import android.view.View;
import com.google.android.apps.photosgo.home.HomeNavigationView;

/* renamed from: crj */
/* compiled from: PG */
final /* synthetic */ class crj implements View.OnClickListener {

    /* renamed from: a */
    private final HomeNavigationView f5489a;

    /* renamed from: b */
    private final View.OnClickListener f5490b;

    public crj(HomeNavigationView homeNavigationView, View.OnClickListener onClickListener) {
        this.f5489a = homeNavigationView;
        this.f5490b = onClickListener;
    }

    public final void onClick(View view) {
        HomeNavigationView homeNavigationView = this.f5489a;
        View.OnClickListener onClickListener = this.f5490b;
        homeNavigationView.mo3383a();
        onClickListener.onClick(view);
    }
}
