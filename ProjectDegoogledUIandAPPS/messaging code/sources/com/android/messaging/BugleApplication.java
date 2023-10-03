package com.android.messaging;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.appcompat.mms.MmsManager;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.p041ui.C1037D;
import com.android.messaging.receiver.SmsReceiver;
import com.android.messaging.sms.C1006b;
import com.android.messaging.sms.C1012h;
import com.android.messaging.sms.C1014j;
import com.android.messaging.sms.C1015k;
import com.android.messaging.sms.C1024t;
import com.android.messaging.util.C1410N;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import java.io.File;
import java.lang.Thread;

public class BugleApplication extends Application implements Thread.UncaughtExceptionHandler {

    /* renamed from: Kb */
    private static boolean f976Kb = false;
    /* access modifiers changed from: private */

    /* renamed from: Jb */
    public Thread.UncaughtExceptionHandler f977Jb;

    /* renamed from: Za */
    public static boolean m1223Za() {
        return f976Kb;
    }

    /* renamed from: d */
    public static void m1225d(Context context) {
        SmsReceiver.m2322b(context);
    }

    protected static void setTestsRunning() {
        f976Kb = true;
    }

    /* renamed from: b */
    public void mo5856b(C0967f fVar) {
        Context applicationContext = fVar.getApplicationContext();
        C1449g Id = fVar.mo6646Id();
        fVar.mo6645Hd();
        C0947h dataModel = fVar.getDataModel();
        C1014j carrierConfigValuesLoader = fVar.getCarrierConfigValuesLoader();
        if (Log.isLoggable("MessagingAppProf", 3)) {
            File e = C1410N.m3551e("startup.trace", true);
            Debug.startMethodTracing(e.getAbsolutePath(), 167772160);
            new Handler(Looper.getMainLooper()).postDelayed(new C0759d(this, e), 30000);
        }
        m1225d(applicationContext);
        MmsManager.setApnSettingsLoader(new C1012h(applicationContext));
        MmsManager.setCarrierConfigValuesLoader(carrierConfigValuesLoader);
        MmsManager.setUserAgentInfoLoader(new C1015k(applicationContext));
        MmsManager.setUseWakeLock(true);
        Id.getBoolean("bugle_use_mms_api", true);
        MmsManager.setForceLegacyMms(false);
        new C0757b(Id);
        C1006b.m2346e(applicationContext);
        dataModel.mo6608ce();
        if (C1464na.m3760_j()) {
            applicationContext.registerReceiver(new C0756a(), new IntentFilter("android.telephony.action.CARRIER_CONFIG_CHANGED"));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C1037D.get().mo6941Wi();
    }

    public void onCreate() {
        super.onCreate();
        if (!f976Kb) {
            C0969h.m2192a(getApplicationContext(), this);
        } else {
            C1430e.m3622e("MessagingApp", "BugleApplication.onCreate: FactoryImpl.register skipped for test run");
        }
        this.f977Jb = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (Log.isLoggable("MessagingApp", 3)) {
            C1430e.m3620d("MessagingApp", "BugleApplication.onLowMemory");
        }
        C0967f.get().mo6656Sd();
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (getMainLooper().getThread() != thread) {
            C1430e.m3623e("MessagingApp", "Uncaught exception in background thread " + thread, th);
            new Handler(getMainLooper()).post(new C0758c(this, thread, th));
            return;
        }
        this.f977Jb.uncaughtException(thread, th);
    }

    /* renamed from: a */
    public void mo5855a(C0967f fVar) {
        int i = fVar.mo6645Hd().getInt("shared_preferences_version", -1);
        int parseInt = Integer.parseInt(getString(R.string.pref_version));
        if (parseInt > i) {
            C1430e.m3625i("MessagingApp", "Upgrading shared prefs from " + i + " to " + parseInt);
            try {
                fVar.mo6645Hd().mo8164F(i, parseInt);
                C1474sa.m3794a(new C0966e(this, fVar, i, parseInt));
                fVar.mo6645Hd().putInt("shared_preferences_version", parseInt);
            } catch (Exception e) {
                C1430e.m3623e("MessagingApp", "Failed to upgrade shared prefs", e);
            }
        } else if (parseInt < i) {
            C1430e.m3622e("MessagingApp", "Shared prefs downgrade requested and ignored. oldVersion = " + i + ", newVersion = " + parseInt);
        }
        C1024t.load();
    }
}
