package p000;

import android.content.ContentResolver;
import android.os.Build;
import android.os.Environment;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import p003j$.util.Optional;

/* renamed from: dbm */
/* compiled from: PG */
public final class dbm extends iox {

    /* renamed from: b */
    private final ioq f6197b;

    /* renamed from: c */
    private final ioq f6198c;

    /* renamed from: d */
    private final ioq f6199d;

    /* renamed from: e */
    private final ioq f6200e;

    /* renamed from: f */
    private final ioq f6201f;

    /* renamed from: g */
    private final ioq f6202g;

    public dbm(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4, ioq ioq5, ioq ioq6) {
        super(iqk2, iph.m14288a(dbm.class), iqk);
        this.f6197b = ipd.m14274a(ioq);
        this.f6198c = ipd.m14274a(ioq2);
        this.f6199d = ipd.m14274a(ioq3);
        this.f6200e = ipd.m14274a(ioq4);
        this.f6201f = ipd.m14274a(ioq5);
        this.f6202g = ipd.m14274a(ioq6);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        dbo dbo = (dbo) list.get(0);
        Optional optional = (Optional) list.get(1);
        Optional optional2 = (Optional) list.get(2);
        boolean booleanValue = ((Boolean) list.get(3)).booleanValue();
        ContentResolver contentResolver = (ContentResolver) list.get(4);
        iek iek = (iek) list.get(5);
        if (Build.VERSION.SDK_INT < 29 || !optional.isPresent()) {
            return ife.m12820a((Object) Optional.empty());
        }
        Matcher matcher = cjn.f4503a.matcher((String) optional.get());
        ife.m12845a(matcher.matches(), (Object) "absolutePath must be in /storage/.");
        cjn cjn = new cjn(matcher.group(1), matcher.group(2), matcher.group(3));
        String str = cjn.f4505c;
        if (cjn.m4401a(str, Environment.DIRECTORY_DCIM) || cjn.m4401a(str, Environment.DIRECTORY_MOVIES) || cjn.m4401a(str, Environment.DIRECTORY_PICTURES)) {
            return iek.mo5933a(hmq.m11749a((Callable) new dbb(cjn, booleanValue, contentResolver, dbo, optional2)));
        }
        return ife.m12820a((Object) Optional.empty());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6197b.mo9044b(), this.f6198c.mo9044b(), this.f6199d.mo9044b(), this.f6200e.mo9044b(), this.f6201f.mo9044b(), this.f6202g.mo9044b());
    }
}
