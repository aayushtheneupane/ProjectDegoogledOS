package p000;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* renamed from: bj */
/* compiled from: PG */
public final class C0039bj implements C0028az {

    /* renamed from: b */
    private static final String[] f2517b = new String[0];

    /* renamed from: a */
    public final SQLiteDatabase f2518a;

    public C0039bj(SQLiteDatabase sQLiteDatabase) {
        this.f2518a = sQLiteDatabase;
    }

    /* renamed from: a */
    public final void mo1731a() {
        this.f2518a.beginTransaction();
    }

    public final void close() {
        this.f2518a.close();
    }

    /* renamed from: a */
    public final C0037bh mo1730a(String str) {
        return new C0045bp(this.f2518a.compileStatement(str));
    }

    /* renamed from: b */
    public final void mo1734b() {
        this.f2518a.endTransaction();
    }

    /* renamed from: c */
    public final void mo1736c(String str) {
        this.f2518a.execSQL(str);
    }

    /* renamed from: a */
    public final void mo1732a(String str, Object[] objArr) {
        this.f2518a.execSQL(str, objArr);
    }

    /* renamed from: f */
    public final String mo1739f() {
        return this.f2518a.getPath();
    }

    /* renamed from: d */
    public final boolean mo1737d() {
        return this.f2518a.inTransaction();
    }

    /* renamed from: e */
    public final boolean mo1738e() {
        return this.f2518a.isOpen();
    }

    /* renamed from: a */
    public final Cursor mo1729a(C0036bg bgVar) {
        return this.f2518a.rawQueryWithFactory(new C0038bi(bgVar), bgVar.mo1726a(), f2517b, (String) null);
    }

    /* renamed from: b */
    public final Cursor mo1733b(String str) {
        return mo1729a((C0036bg) new C0027ay(str));
    }

    /* renamed from: c */
    public final void mo1735c() {
        this.f2518a.setTransactionSuccessful();
    }
}
