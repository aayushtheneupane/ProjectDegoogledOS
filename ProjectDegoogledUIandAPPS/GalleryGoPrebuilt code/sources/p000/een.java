package p000;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: een */
/* compiled from: PG */
public final class een {

    /* renamed from: a */
    public final iqk f8106a;

    /* renamed from: b */
    public final bpt f8107b;

    /* renamed from: c */
    public final iel f8108c;

    /* renamed from: d */
    public final PackageManager f8109d;

    /* renamed from: e */
    public final inw f8110e;

    public een(iqk iqk, bpt bpt, iel iel, Context context, inw inw) {
        this.f8106a = iqk;
        this.f8107b = bpt;
        this.f8108c = iel;
        this.f8109d = context.getPackageManager();
        this.f8110e = inw;
    }

    /* renamed from: a */
    public static List m7335a(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                edv j = edw.m7289j();
                j.mo4724a(fxk.m9835c(cursor, "activity_class_name"));
                j.mo4726b(fxk.m9835c(cursor, "package_name"));
                j.mo4728c(fxk.m9835c(cursor, "a"));
                j.mo4722a(fxk.m9818a(cursor, "b"));
                j.mo4723a(fxk.m9829b(cursor, "c"));
                j.mo4725a(fxk.m9840e(cursor, "d"));
                j.mo4727b(fxk.m9840e(cursor, "e"));
                arrayList.add(j.mo4721a());
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    /* renamed from: b */
    public final ieh mo4753b(List list) {
        return this.f8107b.mo2656a(new eel(list));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final ieh mo4749a() {
        bpt bpt = this.f8107b;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT * FROM at ORDER BY b DESC");
        return bpt.mo2655a(hgn.mo7407a(), eeg.f8097a).mo6899b();
    }

    /* renamed from: a */
    public final ieh mo4751a(List list) {
        return this.f8107b.mo2656a(new eek(list));
    }

    /* renamed from: a */
    public final boolean mo4752a(Intent intent) {
        return this.f8109d.resolveActivity(intent, 0) != null;
    }

    /* renamed from: c */
    public final ieh mo4754c(List list) {
        return this.f8107b.mo2656a(new eem(list));
    }

    /* renamed from: a */
    public final ieh mo4750a(String str, String str2) {
        bpt bpt = this.f8107b;
        hgn hgn = new hgn();
        hgn.mo7409a("SELECT * FROM at WHERE activity_class_name = ?");
        hgn.mo7411b(str);
        return gte.m10771a(bpt.mo2655a(hgn.mo7407a(), eeb.f8092a).mo6899b(), (icf) new eeh(this, str, str2), (Executor) this.f8108c);
    }
}
