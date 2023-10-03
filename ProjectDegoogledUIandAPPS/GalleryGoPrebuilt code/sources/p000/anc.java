package p000;

import android.content.Context;
import android.text.TextUtils;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import java.util.Collections;

/* renamed from: anc */
/* compiled from: PG */
public final class anc implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ConstraintTrackingWorker f812a;

    public anc(ConstraintTrackingWorker constraintTrackingWorker) {
        this.f812a = constraintTrackingWorker;
    }

    public final void run() {
        String str;
        ConstraintTrackingWorker constraintTrackingWorker = this.f812a;
        Object obj = constraintTrackingWorker.mo1220b().f489b.get("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
        if (obj instanceof String) {
            str = (String) obj;
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            constraintTrackingWorker.f1192h = constraintTrackingWorker.f1161b.f1169e.mo502b(constraintTrackingWorker.f1160a, str, constraintTrackingWorker.f1189e);
            if (constraintTrackingWorker.f1192h != null) {
                alg b = aip.m549a(constraintTrackingWorker.f1160a).f554c.mo1226j().mo608b(constraintTrackingWorker.mo1219a().toString());
                if (b != null) {
                    Context context = constraintTrackingWorker.f1160a;
                    ajl ajl = new ajl(context, aip.m549a(context).f555d, constraintTrackingWorker);
                    ajl.mo552a((Iterable) Collections.singletonList(b));
                    if (ajl.mo553a(constraintTrackingWorker.mo1219a().toString())) {
                        iol.m14231a();
                        String.format("Constraints met for delegate %s", new Object[]{str});
                        try {
                            ieh c = constraintTrackingWorker.f1192h.mo1221c();
                            c.mo53a(new and(constraintTrackingWorker, c), constraintTrackingWorker.mo1224f());
                        } catch (Throwable th) {
                            iol.m14231a();
                            String.format("Delegated worker %s threw exception in startWork.", new Object[]{str});
                            new Throwable[1][0] = th;
                            synchronized (constraintTrackingWorker.f1190f) {
                                if (constraintTrackingWorker.f1191g) {
                                    iol.m14231a();
                                    constraintTrackingWorker.mo1245h();
                                } else {
                                    constraintTrackingWorker.mo1244g();
                                }
                            }
                        }
                    } else {
                        iol.m14231a();
                        String.format("Constraints not met for delegate %s. Requesting retry.", new Object[]{str});
                        constraintTrackingWorker.mo1245h();
                    }
                } else {
                    constraintTrackingWorker.mo1244g();
                }
            } else {
                iol.m14231a();
                constraintTrackingWorker.mo1244g();
            }
        } else {
            iol.m14231a();
            iol.m14234a(ConstraintTrackingWorker.f1188d, "No worker to delegate to.", new Throwable[0]);
            constraintTrackingWorker.mo1244g();
        }
    }
}
