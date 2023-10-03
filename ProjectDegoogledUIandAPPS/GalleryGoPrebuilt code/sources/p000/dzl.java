package p000;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: dzl */
/* compiled from: PG */
public final class dzl {

    /* renamed from: a */
    public final Context f7719a;

    /* renamed from: b */
    public final iel f7720b;

    /* renamed from: c */
    public final cjr f7721c;

    /* renamed from: d */
    public final cjr f7722d;

    /* renamed from: e */
    public final grf f7723e;

    /* renamed from: f */
    public ServiceConnection f7724f = null;

    public dzl(Context context, iel iel, cjr cjr, cjr cjr2) {
        this.f7719a = context;
        this.f7720b = iel;
        this.f7721c = cjr;
        this.f7722d = cjr2;
        this.f7723e = new grf(new dzg(this), iel);
    }

    /* renamed from: a */
    public static Optional m6969a(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.google.android.apps.photos");
        if (launchIntentForPackage == null) {
            return Optional.empty();
        }
        launchIntentForPackage.addFlags(268435456);
        dze dze = new dze((byte[]) null);
        dze.f7705a = Integer.valueOf(R.string.photo_grid_promo_header_text);
        dze.f7706b = Integer.valueOf(R.string.photo_grid_promo_subheader_text);
        dze.f7707c = Integer.valueOf(R.string.photo_grid_promo_link_text);
        dze.f7708d = launchIntentForPackage;
        dze.f7709e = 1;
        String str = dze.f7705a == null ? " promoHeaderText" : "";
        if (dze.f7706b == null) {
            str = str.concat(" promoSubHeaderText");
        }
        if (dze.f7707c == null) {
            str = String.valueOf(str).concat(" linkText");
        }
        if (dze.f7709e == 0) {
            str = String.valueOf(str).concat(" type");
        }
        if (dze.f7708d == null) {
            str = String.valueOf(str).concat(" linkIntent");
        }
        if (str.isEmpty()) {
            return Optional.m16285of(new dzd(dze.f7705a.intValue(), dze.f7706b.intValue(), dze.f7707c.intValue(), dze.f7709e, dze.f7708d));
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }
}
