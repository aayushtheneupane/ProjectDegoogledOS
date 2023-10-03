package p000;

import android.os.Message;
import android.view.View;

/* renamed from: oa */
/* compiled from: PG */
final class C0385oa implements View.OnClickListener {

    /* renamed from: a */
    private final /* synthetic */ C0392oh f15340a;

    public C0385oa(C0392oh ohVar) {
        this.f15340a = ohVar;
    }

    public final void onClick(View view) {
        Message message;
        Message message2;
        C0392oh ohVar = this.f15340a;
        Message message3 = null;
        if (view == ohVar.f15395k && (message2 = ohVar.f15397m) != null) {
            message3 = Message.obtain(message2);
        } else if (view == ohVar.f15399o && (message = ohVar.f15401q) != null) {
            message3 = Message.obtain(message);
        } else if (view != ohVar.f15403s) {
        }
        if (message3 != null) {
            message3.sendToTarget();
        }
        C0392oh ohVar2 = this.f15340a;
        ohVar2.f15383J.obtainMessage(1, ohVar2.f15386b).sendToTarget();
    }
}
