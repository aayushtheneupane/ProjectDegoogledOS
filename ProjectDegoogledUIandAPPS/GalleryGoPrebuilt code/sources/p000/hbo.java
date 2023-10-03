package p000;

import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: hbo */
/* compiled from: PG */
public final class hbo {

    /* renamed from: a */
    public static final hqa f12459a = hbm.f12457a;

    /* renamed from: b */
    public static final hqa f12460b = hbn.f12458a;

    /* renamed from: a */
    public static hbl m11142a(Context context, hqa hqa) {
        Context context2 = context;
        while (!hqa.mo4768a(context2)) {
            if (context2 instanceof ContextWrapper) {
                context2 = ((ContextWrapper) context2).getBaseContext();
            } else {
                String valueOf = String.valueOf(context.getClass());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 49);
                sb.append("Cannot use base context of type ");
                sb.append(valueOf);
                sb.append(" for ViewContext.");
                throw new IllegalStateException(sb.toString());
            }
        }
        return new hbl(context);
    }
}
