package p000;

import android.content.ContentValues;

/* renamed from: hfz */
/* compiled from: PG */
public final class hfz {

    /* renamed from: a */
    private final gbr f12687a;

    public hfz(gbr gbr) {
        this.f12687a = gbr;
    }

    /* renamed from: a */
    public final void mo7387a(hgk hgk) {
        gbr gbr = this.f12687a;
        gbk gbk = hgk.f12697a;
        gbr.m9983a();
        String str = gbk.f10835a;
        String str2 = gbk.f10836b;
        StringBuilder sb = new StringBuilder(str.length() + 19 + String.valueOf(str2).length());
        sb.append("DELETE FROM ");
        sb.append(str);
        sb.append(" WHERE ");
        sb.append(str2);
        hlj a = hnb.m11766a(sb.toString(), hnf.f13084a);
        try {
            gbr.f10848b.delete(gbk.f10835a, gbk.f10836b, gbk.f10837c);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo7388a(hgm hgm) {
        this.f12687a.mo6392a(hgm.f12699a);
    }

    /* renamed from: a */
    public final void mo7389a(String str) {
        gbr gbr = this.f12687a;
        gbr.m9983a();
        hlj a = hnb.m11766a(str.length() == 0 ? new String("execSQL: ") : "execSQL: ".concat(str), hnf.f13084a);
        try {
            gbr.f10848b.execSQL(str);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo7390a(String str, ContentValues contentValues) {
        gbr gbr = this.f12687a;
        gbr.m9983a();
        hlj a = hnb.m11766a(str.length() == 0 ? new String("INSERT WITH ON CONFLICT ") : "INSERT WITH ON CONFLICT ".concat(str), hnf.f13084a);
        try {
            gbr.f10848b.insertWithOnConflict(str, (String) null, contentValues, 4);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    @SafeVarargs
    /* renamed from: a */
    public final int mo7386a(String str, ContentValues contentValues, String str2, String... strArr) {
        gbr gbr = this.f12687a;
        gbr.m9983a();
        hlj a = hnb.m11766a(str2.length() == 0 ? new String("UPDATE WHERE ") : "UPDATE WHERE ".concat(str2), hnf.f13084a);
        try {
            int update = gbr.f10848b.update(str, contentValues, str2, strArr);
            if (a != null) {
                a.close();
            }
            return update;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
