package p000;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: eot */
/* compiled from: PG */
public final class eot {

    /* renamed from: a */
    public static final Status f8727a = new Status(8, "The connection to Google Play services was lost");

    /* renamed from: b */
    public static final BasePendingResult[] f8728b = new BasePendingResult[0];

    /* renamed from: c */
    public final Set f8729c = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));

    /* renamed from: d */
    private final eos f8730d = new eor(this);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo5093a(BasePendingResult basePendingResult) {
        this.f8729c.add(basePendingResult);
        basePendingResult.mo3509a(this.f8730d);
    }
}
