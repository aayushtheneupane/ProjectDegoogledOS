package p000;

import android.content.Context;
import android.content.Intent;

@Deprecated
/* renamed from: fce */
/* compiled from: PG */
public final class fce implements fcd {
    /* renamed from: a */
    public final void mo5481a(Context context) {
        try {
            evx.m8266a(context);
        } catch (ekf e) {
            throw new eyd(e.f8467a, e);
        } catch (ekg e2) {
            int i = e2.f8468a;
            String message = e2.getMessage();
            new Intent(e2.f8475b);
            throw new eye(i, message, e2);
        }
    }
}
