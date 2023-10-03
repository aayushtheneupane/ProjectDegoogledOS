package com.android.messaging.datamodel.action;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.JobIntentService;
import androidx.core.view.PointerIconCompat;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.DataModelException;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1429da;
import com.android.messaging.util.C1430e;
import java.util.Iterator;
import java.util.List;

public class BackgroundWorkerService extends JobIntentService {
    protected static final String EXTRA_ACTION = "action";
    protected static final String EXTRA_ATTEMPT = "retry_attempt";
    protected static final String EXTRA_OP_CODE = "op";
    protected static final int OP_PROCESS_REQUEST = 400;
    private final C0814d mHost = C0947h.get().mo6594_d();

    /* renamed from: b */
    public static void m1350b(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_ACTION, (Action) it.next());
            intent.putExtra(EXTRA_ATTEMPT, 0);
            Context applicationContext = C0967f.get().getApplicationContext();
            intent.setClass(applicationContext, BackgroundWorkerService.class);
            intent.putExtra(EXTRA_OP_CODE, OP_PROCESS_REQUEST);
            JobIntentService.enqueueWork(applicationContext, BackgroundWorkerService.class, (int) PointerIconCompat.TYPE_CONTEXT_MENU, intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(Intent intent) {
        if (intent == null) {
            C1430e.m3630w("MessagingAppDataModel", "BackgroundWorkerService.onHandleIntent: Called with null intent");
            return;
        }
        int intExtra = intent.getIntExtra(EXTRA_OP_CODE, 0);
        if (intExtra == OP_PROCESS_REQUEST) {
            Action action = (Action) intent.getParcelableExtra(EXTRA_ACTION);
            intent.getIntExtra(EXTRA_ATTEMPT, -1);
            action.mo5959ze();
            try {
                C1429da daVar = new C1429da("MessagingAppDataModel", action.getClass().getSimpleName() + "#doBackgroundWork", -1);
                daVar.start();
                Bundle ue = action.mo5955ue();
                daVar.mo8056Pj();
                action.mo5958ye();
                this.mHost.mo6041a(action, ue);
            } catch (Exception e) {
                C1430e.m3623e("MessagingAppDataModel", "Error in background worker", e);
                if (!(e instanceof DataModelException)) {
                    C1424b.fail("Unexpected error in background worker - abort");
                }
                action.mo5958ye();
                this.mHost.mo6042a(action, e);
            }
        } else {
            C1430e.m3630w("MessagingAppDataModel", "Unrecognized opcode in BackgroundWorkerService " + intExtra);
            throw new RuntimeException("Unrecognized opcode in BackgroundWorkerService");
        }
    }
}
