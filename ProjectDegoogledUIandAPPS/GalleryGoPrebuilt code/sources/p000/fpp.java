package p000;

import android.database.Cursor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: fpp */
/* compiled from: PG */
final /* synthetic */ class fpp implements fpu {

    /* renamed from: a */
    private final fpr f10224a;

    public fpp(fpr fpr) {
        this.f10224a = fpr;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final Object mo6020a() {
        Map map;
        fpr fpr = this.f10224a;
        Cursor query = fpr.f10228a.query(fpr.f10229b, fpr.f10226f, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                Map emptyMap = Collections.emptyMap();
                query.close();
                return emptyMap;
            }
            if (count <= 256) {
                map = new C0290kn(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            query.close();
            return map;
        } catch (Throwable th) {
            query.close();
            throw th;
        }
    }
}
