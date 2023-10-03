package p000;

import android.database.sqlite.SQLiteProgram;

/* renamed from: bo */
/* compiled from: PG */
public class C0044bo implements C0035bf {

    /* renamed from: a */
    public final SQLiteProgram f3236a;

    public C0044bo(SQLiteProgram sQLiteProgram) {
        this.f3236a = sQLiteProgram;
    }

    /* renamed from: a */
    public final void mo1923a(int i, byte[] bArr) {
        this.f3236a.bindBlob(i, bArr);
    }

    /* renamed from: a */
    public final void mo1921a(int i, long j) {
        this.f3236a.bindLong(i, j);
    }

    /* renamed from: a */
    public final void mo1920a(int i) {
        this.f3236a.bindNull(i);
    }

    /* renamed from: a */
    public final void mo1922a(int i, String str) {
        this.f3236a.bindString(i, str);
    }

    public final void close() {
        this.f3236a.close();
    }
}
