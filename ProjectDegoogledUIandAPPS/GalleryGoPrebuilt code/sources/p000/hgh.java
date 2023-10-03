package p000;

import android.content.Context;
import java.util.ArrayList;
import java.util.Random;

/* renamed from: hgh */
/* compiled from: PG */
public final /* synthetic */ class hgh {
    public hgh() {
        new Random();
        new ArrayList(2);
    }

    /* renamed from: a */
    public static int m11441a(int i) {
        if (i == 0) {
            return 1;
        }
        if (i != 1) {
            return i != 2 ? 0 : 3;
        }
        return 2;
    }

    /* renamed from: a */
    public static Object m11442a(Context context, Class cls) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof ioe) {
            try {
                return cls.cast(((ioe) applicationContext).mo2453b());
            } catch (ClassCastException e) {
                throw new IllegalStateException("Failed to get an entry point. Did you mark your interface with @SingletonEntryPoint?", e);
            }
        } else {
            String valueOf = String.valueOf(applicationContext.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 68);
            sb.append("Given application context does not implement CloakComponentManager: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* renamed from: a */
    public static void m11443a(hgi hgi, gbr gbr) {
        hgi.mo4001a(new hfz(gbr));
    }
}
