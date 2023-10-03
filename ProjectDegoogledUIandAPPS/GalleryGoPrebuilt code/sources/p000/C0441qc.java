package p000;

import android.content.Context;
import android.content.res.Configuration;
import com.google.android.apps.photosgo.R;

/* renamed from: qc */
/* compiled from: PG */
public final class C0441qc {

    /* renamed from: a */
    public final Context f15602a;

    private C0441qc(Context context) {
        this.f15602a = context;
    }

    /* renamed from: a */
    public static C0441qc m15109a(Context context) {
        return new C0441qc(context);
    }

    /* renamed from: a */
    public final int mo9692a() {
        Configuration configuration = this.f15602a.getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i > 600) {
            return 5;
        }
        if (i >= 500) {
            return 4;
        }
        if (i <= 480 || i2 <= 640) {
            return i >= 360 ? 3 : 2;
        }
        return 4;
    }

    /* renamed from: b */
    public final boolean mo9693b() {
        return this.f15602a.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs);
    }
}
