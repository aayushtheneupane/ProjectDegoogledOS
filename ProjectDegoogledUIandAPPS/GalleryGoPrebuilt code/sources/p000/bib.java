package p000;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@Deprecated
/* renamed from: bib */
/* compiled from: PG */
public abstract class bib extends Service {

    /* renamed from: a */
    public static final Handler f2434a = new Handler(Looper.getMainLooper());

    /* renamed from: b */
    public final ExecutorService f2435b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new bhy(this));

    /* renamed from: c */
    public final C0309lf f2436c = new C0309lf(1);

    /* renamed from: d */
    private final bhp f2437d = new bhp(this);

    /* renamed from: a */
    public abstract void mo2067a(bhx bhx);

    /* renamed from: b */
    public abstract void mo2069b(bhx bhx);

    public final IBinder onBind(Intent intent) {
        return this.f2437d;
    }

    public final void onStart(Intent intent, int i) {
    }

    /* access modifiers changed from: protected */
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        synchronized (this.f2436c) {
            if (!this.f2436c.isEmpty()) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                printWriter.println("Running jobs:");
                int i = 0;
                while (true) {
                    C0309lf lfVar = this.f2436c;
                    if (i < lfVar.f15193b) {
                        bhz bhz = (bhz) lfVar.get(lfVar.mo9320b(i));
                        String quote = JSONObject.quote(((bhw) bhz.f2423a).f2412a);
                        String formatElapsedTime = DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(elapsedRealtime - bhz.f2424b));
                        StringBuilder sb = new StringBuilder(String.valueOf(quote).length() + 28 + String.valueOf(formatElapsedTime).length());
                        sb.append("    * ");
                        sb.append(quote);
                        sb.append(" has been running for ");
                        sb.append(formatElapsedTime);
                        printWriter.println(sb.toString());
                        i++;
                    } else {
                        return;
                    }
                }
            } else {
                printWriter.println("No running jobs");
            }
        }
    }

    /* renamed from: a */
    public final void mo2068a(bhx bhx, boolean z) {
        if (bhx != null) {
            this.f2435b.execute(new bia(7, this, bhx, (bhn) null, (bhz) null, false, z ? 1 : 0));
        } else {
            Log.e("FJD.JobService", "jobFinished called with a null JobParameters");
        }
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        stopSelf(i2);
        return 2;
    }

    public final boolean onUnbind(Intent intent) {
        this.f2435b.execute(new bia(3, this, (bhx) null, (bhn) null, (bhz) null, false, 0));
        return super.onUnbind(intent);
    }
}
