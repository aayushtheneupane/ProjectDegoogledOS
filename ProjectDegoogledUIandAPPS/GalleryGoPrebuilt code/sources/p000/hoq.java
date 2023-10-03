package p000;

import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import java.util.Set;

/* renamed from: hoq */
/* compiled from: PG */
public final /* synthetic */ class hoq implements DialogInterface.OnClickListener {

    /* renamed from: a */
    private final hos f13164a;

    /* renamed from: b */
    private final hoi f13165b;

    public hoq(hos hos, hoi hoi) {
        this.f13164a = hos;
        this.f13165b = hoi;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        String str;
        hos hos = this.f13164a;
        hoi hoi = this.f13165b;
        Button a = ((C0394oj) dialogInterface).mo9526a(i);
        if (hos.m11846a(a.getContext())) {
            if (i != -3) {
                str = i != -2 ? i != -1 ? "Clicked dialog button" : "Clicked positive dialog button" : "Clicked negative dialog button";
            } else {
                str = "Clicked neutral dialog button";
            }
            hlp a2 = hos.f13168a.mo7579a(str, hnf.f13084a);
            try {
                ihg.m13039a(hoi, (View) a);
                for (View.OnClickListener onClick : (Set) ((ioi) hos.f13169b).f14599a) {
                    onClick.onClick(a);
                }
                if (a2 != null) {
                    a2.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }
}
