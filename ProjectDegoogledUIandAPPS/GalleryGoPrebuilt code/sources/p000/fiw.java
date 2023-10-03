package p000;

import android.app.Activity;
import android.text.TextUtils;

/* renamed from: fiw */
/* compiled from: PG */
final class fiw implements fix {

    /* renamed from: a */
    public final /* synthetic */ fiz f9760a;

    public fiw(fiz fiz) {
        this.f9760a = fiz;
    }

    /* renamed from: a */
    public final void mo5740a(Activity activity) {
        fjy fjy;
        fiz fiz = this.f9760a;
        Class<?> cls = activity.getClass();
        if (!TextUtils.isEmpty((CharSequence) null)) {
            String valueOf = String.valueOf((Object) null);
            String valueOf2 = String.valueOf(cls.getSimpleName());
            fjy = new fjy(valueOf2.length() == 0 ? new String(valueOf) : valueOf.concat(valueOf2));
        } else {
            fjy = new fjy(cls.getSimpleName());
        }
        fiz.mo5846a(fjy);
    }

    /* renamed from: b */
    public final void mo5742b(Activity activity) {
        this.f9760a.mo5846a((fjy) null);
        if (this.f9760a.f9767h.get()) {
            this.f9760a.mo5732c().mo5931a((Runnable) new fiv(this));
        }
    }
}
