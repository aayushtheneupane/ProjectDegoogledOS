package p000;

import android.view.KeyEvent;
import android.widget.TextView;

/* renamed from: cln */
/* compiled from: PG */
final /* synthetic */ class cln implements TextView.OnEditorActionListener {

    /* renamed from: a */
    private final clr f4617a;

    public cln(clr clr) {
        this.f4617a = clr;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        clr clr = this.f4617a;
        if (i != 2) {
            return false;
        }
        clr.mo3248f();
        return true;
    }
}
