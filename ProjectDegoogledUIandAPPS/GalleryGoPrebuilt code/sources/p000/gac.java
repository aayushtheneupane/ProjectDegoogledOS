package p000;

import android.database.sqlite.SQLiteTransactionListener;

/* renamed from: gac */
/* compiled from: PG */
final class gac implements SQLiteTransactionListener {

    /* renamed from: a */
    private final /* synthetic */ gad f10775a;

    public gac(gad gad) {
        this.f10775a = gad;
    }

    public final void onBegin() {
    }

    public final void onCommit() {
    }

    public final void onRollback() {
        if (!this.f10775a.f10776a) {
            throw new gbj();
        }
    }
}
