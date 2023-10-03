package p000;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: emg */
/* compiled from: PG */
public final class emg {

    /* renamed from: a */
    public final Map f8552a = Collections.synchronizedMap(new WeakHashMap());

    /* renamed from: b */
    public final Map f8553b = Collections.synchronizedMap(new WeakHashMap());

    /* renamed from: a */
    public final void mo5006a(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.f8552a) {
            hashMap = new HashMap(this.f8552a);
        }
        synchronized (this.f8553b) {
            hashMap2 = new HashMap(this.f8553b);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).mo3513c(status);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((exe) entry2.getKey()).mo5366b(new eko(status));
            }
        }
    }
}
