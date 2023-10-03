package com.android.messaging.datamodel.action;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.util.C1429da;
import com.android.messaging.util.C1430e;
import com.android.vcard.VCardConfig;

public class ActionServiceImpl extends JobIntentService {
    protected static final String BUNDLE_ACTION = "bundle_action";
    protected static final String EXTRA_ACTION_BUNDLE = "datamodel_action_bundle";
    protected static final String EXTRA_OP_CODE = "op";
    protected static final String EXTRA_WORKER_EXCEPTION = "worker_exception";
    protected static final String EXTRA_WORKER_RESPONSE = "worker_response";
    protected static final String EXTRA_WORKER_UPDATE = "worker_update";
    protected static final int OP_RECEIVE_BACKGROUND_FAILURE = 202;
    protected static final int OP_RECEIVE_BACKGROUND_RESPONSE = 201;
    protected static final int OP_START_ACTION = 200;

    /* renamed from: Lb */
    private C0815e f1059Lb;

    public class PendingActionReceiver extends BroadcastReceiver {
        /* renamed from: m */
        public static Intent m1349m(int i) {
            Intent intent = new Intent(C0967f.get().getApplicationContext(), PendingActionReceiver.class);
            intent.setAction("com.android.messaging.datamodel.PENDING_ACTION");
            intent.putExtra(ActionServiceImpl.EXTRA_OP_CODE, i);
            return intent;
        }

        public void onReceive(Context context, Intent intent) {
            ActionServiceImpl.m1347b(intent);
        }
    }

    /* renamed from: a */
    protected static void m1343a(Action action) {
        Intent m = m1348m(OP_START_ACTION);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ACTION, action);
        m.putExtra(EXTRA_ACTION_BUNDLE, bundle);
        action.mo5941Be();
        m1347b(m);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m1347b(Intent intent) {
        Context applicationContext = C0967f.get().getApplicationContext();
        intent.getIntExtra(EXTRA_OP_CODE, 0);
        intent.setClass(applicationContext, ActionServiceImpl.class);
        JobIntentService.enqueueWork(applicationContext, ActionServiceImpl.class, 1000, intent);
    }

    /* renamed from: m */
    private static Intent m1348m(int i) {
        Intent intent = new Intent(C0967f.get().getApplicationContext(), ActionServiceImpl.class);
        intent.putExtra(EXTRA_OP_CODE, i);
        return intent;
    }

    public void onCreate() {
        super.onCreate();
        this.f1059Lb = C0947h.get().mo6604ae();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onHandleWork(Intent intent) {
        Action action;
        if (intent == null) {
            C1430e.m3630w("MessagingAppDataModel", "ActionService.onHandleIntent: Called with null intent");
            return;
        }
        int intExtra = intent.getIntExtra(EXTRA_OP_CODE, 0);
        Bundle bundleExtra = intent.getBundleExtra(EXTRA_ACTION_BUNDLE);
        bundleExtra.setClassLoader(getClassLoader());
        switch (intExtra) {
            case OP_START_ACTION /*200*/:
                action = (Action) bundleExtra.getParcelable(BUNDLE_ACTION);
                action.mo5940Ae();
                C1429da a = m1342a(action, "#executeAction");
                a.start();
                Object ve = action.mo5956ve();
                a.mo8056Pj();
                action.mo5953p(ve);
                break;
            case OP_RECEIVE_BACKGROUND_RESPONSE /*201*/:
                Action action2 = (Action) bundleExtra.getParcelable(BUNDLE_ACTION);
                Bundle bundleExtra2 = intent.getBundleExtra(EXTRA_WORKER_RESPONSE);
                C1429da a2 = m1342a(action2, "#processBackgroundResponse");
                a2.start();
                action2.mo5952j(bundleExtra2);
                a2.mo8056Pj();
                action = action2;
                break;
            case OP_RECEIVE_BACKGROUND_FAILURE /*202*/:
                action = (Action) bundleExtra.getParcelable(BUNDLE_ACTION);
                C1429da a3 = m1342a(action, "#processBackgroundFailure");
                a3.start();
                action.mo5943De();
                a3.mo8056Pj();
                break;
            default:
                throw new RuntimeException("Unrecognized opcode in ActionServiceImpl");
        }
        action.mo5948a(this.f1059Lb);
    }

    /* renamed from: a */
    protected static void m1344a(Action action, int i, long j) {
        Intent m = PendingActionReceiver.m1349m(OP_START_ACTION);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ACTION, action);
        m.putExtra(EXTRA_ACTION_BUNDLE, bundle);
        Context applicationContext = C0967f.get().getApplicationContext();
        PendingIntent broadcast = PendingIntent.getBroadcast(applicationContext, i, m, VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (j < Long.MAX_VALUE) {
            alarmManager.set(2, SystemClock.elapsedRealtime() + j, broadcast);
        } else {
            alarmManager.cancel(broadcast);
        }
    }

    /* renamed from: a */
    protected static void m1345a(Action action, Bundle bundle) {
        Intent m = m1348m(OP_RECEIVE_BACKGROUND_RESPONSE);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(BUNDLE_ACTION, action);
        m.putExtra(EXTRA_ACTION_BUNDLE, bundle2);
        m.putExtra(EXTRA_WORKER_RESPONSE, bundle);
        m1347b(m);
    }

    /* renamed from: a */
    protected static void m1346a(Action action, Exception exc) {
        Intent m = m1348m(OP_RECEIVE_BACKGROUND_FAILURE);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ACTION, action);
        m.putExtra(EXTRA_ACTION_BUNDLE, bundle);
        m.putExtra(EXTRA_WORKER_EXCEPTION, exc);
        m1347b(m);
    }

    /* renamed from: a */
    public static PendingIntent m1341a(Context context, Action action, int i, boolean z) {
        Intent m = PendingActionReceiver.m1349m(OP_START_ACTION);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ACTION, action);
        m.putExtra(EXTRA_ACTION_BUNDLE, bundle);
        if (z) {
            m.addFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
        }
        return PendingIntent.getBroadcast(context, i, m, VCardConfig.FLAG_CONVERT_PHONETIC_NAME_STRINGS);
    }

    /* renamed from: a */
    private static C1429da m1342a(Action action, String str) {
        return new C1429da("MessagingAppDataModel", action.getClass().getSimpleName() + str, 1000);
    }
}
