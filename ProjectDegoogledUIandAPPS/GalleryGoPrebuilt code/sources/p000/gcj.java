package p000;

import android.view.View;
import com.google.android.material.appbar.AppBarLayout;

/* renamed from: gcj */
/* compiled from: PG */
public final class gcj implements C0329lz {

    /* renamed from: a */
    private final /* synthetic */ AppBarLayout f10941a;

    public gcj(AppBarLayout appBarLayout) {
        this.f10941a = appBarLayout;
    }

    /* renamed from: a */
    public final C0348mr mo79a(View view, C0348mr mrVar) {
        AppBarLayout appBarLayout = this.f10941a;
        C0348mr mrVar2 = !C0340mj.m14725p(appBarLayout) ? null : mrVar;
        if (!C0321lr.m14631a((Object) appBarLayout.f5125c, (Object) mrVar2)) {
            appBarLayout.f5125c = mrVar2;
            appBarLayout.requestLayout();
        }
        return mrVar;
    }
}
