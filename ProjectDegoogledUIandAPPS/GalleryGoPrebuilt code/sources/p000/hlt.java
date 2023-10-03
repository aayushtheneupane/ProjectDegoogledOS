package p000;

import android.content.DialogInterface;

/* renamed from: hlt */
/* compiled from: PG */
final /* synthetic */ class hlt implements DialogInterface.OnClickListener {

    /* renamed from: a */
    private final hlz f12999a;

    /* renamed from: b */
    private final String f13000b;

    /* renamed from: c */
    private final DialogInterface.OnClickListener f13001c;

    public hlt(hlz hlz, String str, DialogInterface.OnClickListener onClickListener) {
        this.f12999a = hlz;
        this.f13000b = str;
        this.f13001c = onClickListener;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        hlz hlz = this.f12999a;
        String str = this.f13000b;
        DialogInterface.OnClickListener onClickListener = this.f13001c;
        hlp a = hlz.mo7577a(str);
        try {
            onClickListener.onClick(dialogInterface, i);
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
}
