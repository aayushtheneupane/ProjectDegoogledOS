package p000;

import android.content.Intent;
import android.net.Uri;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* renamed from: btq */
/* compiled from: PG */
public final /* synthetic */ class btq implements hpr {

    /* renamed from: a */
    private final btr f3558a;

    public btq(btr btr) {
        this.f3558a = btr;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        btr btr = this.f3558a;
        if (((bto) obj).f3557b) {
            return Optional.empty();
        }
        btm btm = new btm((byte[]) null);
        btm.f3550a = Integer.valueOf(R.string.promo_header_text);
        if (btr.f3561c.getPackageManager().getLaunchIntentForPackage("com.google.android.apps.photos") != null) {
            return Optional.empty();
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=com.google.android.apps.photos"));
        btm.f3551b = Integer.valueOf(R.string.app_not_installed_promo_link_text);
        intent.addFlags(268435456);
        btm.f3552c = intent;
        btm.f3553d = 1;
        String str = btm.f3550a == null ? " promoText" : "";
        if (btm.f3551b == null) {
            str = str.concat(" linkText");
        }
        if (btm.f3553d == 0) {
            str = String.valueOf(str).concat(" type");
        }
        if (btm.f3552c == null) {
            str = String.valueOf(str).concat(" linkIntent");
        }
        if (str.isEmpty()) {
            return Optional.m16285of(new btj(btm.f3550a.intValue(), btm.f3551b.intValue(), btm.f3553d, btm.f3552c));
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
    }
}
