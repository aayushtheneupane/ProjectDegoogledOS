package p000;

import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: ajb */
/* compiled from: PG */
final class ajb implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ajf f619a;

    public ajb(ajf ajf) {
        this.f619a = ajf;
    }

    public final void run() {
        ajf ajf;
        aje aje;
        WorkDatabase workDatabase;
        synchronized (this.f619a.f631h) {
            ajf ajf2 = this.f619a;
            ajf2.f632i = (Intent) ajf2.f631h.get(0);
        }
        Intent intent = this.f619a.f632i;
        if (intent != null) {
            String action = intent.getAction();
            int intExtra = this.f619a.f632i.getIntExtra("KEY_START_ID", 0);
            iol.m14231a();
            Integer valueOf = Integer.valueOf(intExtra);
            String.format("Processing command %s, %s", new Object[]{this.f619a.f632i, valueOf});
            PowerManager.WakeLock a = ami.m762a(this.f619a.f625b, String.format("%s (%s)", new Object[]{action, valueOf}));
            try {
                iol.m14231a();
                String.format("Acquiring operation wake lock (%s) %s", new Object[]{action, a});
                a.acquire();
                ajf ajf3 = this.f619a;
                aiw aiw = ajf3.f630g;
                Intent intent2 = ajf3.f632i;
                String action2 = intent2.getAction();
                if ("ACTION_CONSTRAINTS_CHANGED".equals(action2)) {
                    iol.m14231a();
                    String.format("Handling constraints changed %s", new Object[]{intent2});
                    aiz aiz = new aiz(aiw.f600b, intExtra, ajf3);
                    List<alg> b = aiz.f608c.f629f.f554c.mo1226j().mo609b();
                    aix.m581a(aiz.f606a, b);
                    aiz.f609d.mo552a((Iterable) b);
                    ArrayList<alg> arrayList = new ArrayList<>(b.size());
                    long currentTimeMillis = System.currentTimeMillis();
                    for (alg alg : b) {
                        String str = alg.f713b;
                        if (currentTimeMillis >= alg.mo597c() && (!alg.mo598d() || aiz.f609d.mo553a(str))) {
                            arrayList.add(alg);
                        }
                    }
                    for (alg alg2 : arrayList) {
                        String str2 = alg2.f713b;
                        Intent b2 = aiw.m578b(aiz.f606a, str2);
                        iol.m14231a();
                        String.format("Creating a delay_met command for workSpec with id (%s)", new Object[]{str2});
                        ajf ajf4 = aiz.f608c;
                        ajf4.mo544a(new ajc(ajf4, b2, aiz.f607b));
                    }
                    aiz.f609d.mo551a();
                } else if ("ACTION_RESCHEDULE".equals(action2)) {
                    iol.m14231a();
                    String.format("Handling reschedule %s, %s", new Object[]{intent2, valueOf});
                    ajf3.f629f.mo522a();
                } else {
                    Bundle extras = intent2.getExtras();
                    String[] strArr = {"KEY_WORKSPEC_ID"};
                    if (extras == null || extras.isEmpty() || extras.get(strArr[0]) == null) {
                        iol.m14231a();
                        iol.m14234a(aiw.f599a, String.format("Invalid request for %s, requires %s.", new Object[]{action2, "KEY_WORKSPEC_ID"}), new Throwable[0]);
                    } else if ("ACTION_SCHEDULE_WORK".equals(action2)) {
                        String string = intent2.getExtras().getString("KEY_WORKSPEC_ID");
                        iol.m14231a();
                        String.format("Handling schedule work for %s", new Object[]{string});
                        workDatabase = ajf3.f629f.f554c;
                        workDatabase.mo2845e();
                        alg b3 = workDatabase.mo1226j().mo608b(string);
                        if (b3 == null) {
                            iol.m14231a();
                            String str3 = aiw.f599a;
                            Log.w(str3, "Skipping scheduling " + string + " because it's no longer in the DB");
                        } else if (!gbz.m9998e(b3.f728q)) {
                            long c = b3.mo597c();
                            if (b3.mo598d()) {
                                iol.m14231a();
                                String.format("Opportunistically setting an alarm for %s at %s", new Object[]{string, Long.valueOf(c)});
                                aiv.m572a(aiw.f600b, ajf3.f629f, string, c);
                                ajf3.mo544a(new ajc(ajf3, aiw.m575a(aiw.f600b), intExtra));
                            } else {
                                iol.m14231a();
                                String.format("Setting up Alarms for %s at %s", new Object[]{string, Long.valueOf(c)});
                                aiv.m572a(aiw.f600b, ajf3.f629f, string, c);
                            }
                            workDatabase.mo2847g();
                        } else {
                            iol.m14231a();
                            String str4 = aiw.f599a;
                            Log.w(str4, "Skipping scheduling " + string + "because it is finished.");
                        }
                        workDatabase.mo2846f();
                    } else if ("ACTION_DELAY_MET".equals(action2)) {
                        Bundle extras2 = intent2.getExtras();
                        synchronized (aiw.f602d) {
                            String string2 = extras2.getString("KEY_WORKSPEC_ID");
                            iol.m14231a();
                            String.format("Handing delay met for %s", new Object[]{string2});
                            if (!aiw.f601c.containsKey(string2)) {
                                aja aja = new aja(aiw.f600b, intExtra, string2, ajf3);
                                aiw.f601c.put(string2, aja);
                                aja.f615f = ami.m762a(aja.f610a, String.format("%s (%s)", new Object[]{aja.f612c, Integer.valueOf(aja.f611b)}));
                                iol.m14231a();
                                String.format("Acquiring wakelock %s for WorkSpec %s", new Object[]{aja.f615f, aja.f612c});
                                aja.f615f.acquire();
                                alg b4 = aja.f613d.f629f.f554c.mo1226j().mo608b(aja.f612c);
                                if (b4 != null) {
                                    boolean d = b4.mo598d();
                                    aja.f616g = d;
                                    if (!d) {
                                        iol.m14231a();
                                        String.format("No constraints for %s", new Object[]{aja.f612c});
                                        aja.mo531a(Collections.singletonList(aja.f612c));
                                    } else {
                                        aja.f614e.mo552a((Iterable) Collections.singletonList(b4));
                                    }
                                } else {
                                    aja.mo536a();
                                }
                            } else {
                                iol.m14231a();
                                String.format("WorkSpec %s is already being handled for ACTION_DELAY_MET", new Object[]{string2});
                            }
                        }
                    } else if ("ACTION_STOP_WORK".equals(action2)) {
                        String string3 = intent2.getExtras().getString("KEY_WORKSPEC_ID");
                        iol.m14231a();
                        String.format("Handing stopWork work for %s", new Object[]{string3});
                        ajf3.f629f.mo525b(string3);
                        aiv.m571a(aiw.f600b, ajf3.f629f, string3);
                        ajf3.mo503a(string3, false);
                    } else if ("ACTION_EXECUTION_COMPLETED".equals(action2)) {
                        Bundle extras3 = intent2.getExtras();
                        String string4 = extras3.getString("KEY_WORKSPEC_ID");
                        boolean z = extras3.getBoolean("KEY_NEEDS_RESCHEDULE");
                        iol.m14231a();
                        String.format("Handling onExecutionCompleted %s, %s", new Object[]{intent2, valueOf});
                        aiw.mo503a(string4, z);
                    } else {
                        iol.m14231a();
                        Log.w(aiw.f599a, String.format("Ignoring intent %s", new Object[]{intent2}));
                    }
                }
                iol.m14231a();
                String.format("Releasing operation wake lock (%s) %s", new Object[]{action, a});
                a.release();
                ajf = this.f619a;
                aje = new aje(ajf);
            } catch (Throwable th) {
                try {
                    iol.m14231a();
                    iol.m14234a(ajf.f624a, "Unexpected error in onHandleIntent", th);
                    iol.m14231a();
                    String.format("Releasing operation wake lock (%s) %s", new Object[]{action, a});
                    a.release();
                    ajf = this.f619a;
                    aje = new aje(ajf);
                } catch (Throwable th2) {
                    iol.m14231a();
                    String.format("Releasing operation wake lock (%s) %s", new Object[]{action, a});
                    a.release();
                    ajf ajf5 = this.f619a;
                    ajf5.mo544a(new aje(ajf5));
                    throw th2;
                }
            }
            ajf.mo544a(aje);
        }
    }
}
