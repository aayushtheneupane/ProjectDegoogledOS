package p000;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: cfb */
/* compiled from: PG */
public final class cfb {

    /* renamed from: a */
    public final dgt f4223a;

    /* renamed from: b */
    public final ContentResolver f4224b;

    /* renamed from: c */
    private final iel f4225c;

    /* renamed from: d */
    private final dhv f4226d;

    public cfb(dgt dgt, ContentResolver contentResolver, iel iel, dhv dhv) {
        this.f4223a = dgt;
        this.f4224b = contentResolver;
        this.f4225c = iel;
        this.f4226d = dhv;
    }

    /* renamed from: a */
    public final ieh mo3090a(ceq ceq) {
        ieh ieh;
        String str = ceq.f4200b;
        new Object[1][0] = str;
        if ((ceq.f4199a & 1) == 0) {
            return ife.m12820a((Object) Optional.empty());
        }
        Uri parse = Uri.parse(str);
        Optional a = m4221a(parse);
        if ((ceq.f4199a & 8) != 0) {
            ieh = ife.m12820a((Object) Optional.m16285of(Uri.parse(ceq.f4204f).getAuthority()));
        } else if (a.isPresent()) {
            ieh = gte.m10770a(this.f4226d.mo4149a(((Long) a.get()).longValue()), cet.f4210a, (Executor) idh.f13918a);
        } else {
            ieh = ife.m12820a((Object) Optional.empty());
        }
        ieh a2 = this.f4225c.mo5933a(hmq.m11749a((Callable) new cer(this, ceq)));
        return gte.m10769a(a2, ieh).mo7613a((Callable) new ces(parse, a2, ieh), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public static Optional m4221a(Uri uri) {
        if (!"content".equals(uri.getScheme())) {
            return Optional.empty();
        }
        if (!"media".equals(uri.getAuthority())) {
            return Optional.empty();
        }
        try {
            return Optional.m16285of(Long.valueOf(ContentUris.parseId(uri)));
        } catch (NumberFormatException | UnsupportedOperationException e) {
            return Optional.empty();
        }
    }
}
