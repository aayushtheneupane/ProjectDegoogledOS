package p000;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.google.android.apps.photosgo.R;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.logging.Level;

/* renamed from: cwd */
/* compiled from: PG */
public final class cwd implements cvy {

    /* renamed from: f */
    private static final hso f5779f = hso.m12037a(cup.IMAGE_LABELING_JOB, cup.FACE_DETECTION_JOB, cup.FACE_EMBEDDING_JOB, cup.FACE_CLUSTERING_JOB, cup.FACE_THUMBNAILING_JOB);

    /* renamed from: a */
    public final cui f5780a;

    /* renamed from: b */
    public final Executor f5781b;

    /* renamed from: c */
    public final inw f5782c;

    /* renamed from: d */
    public final inw f5783d;

    /* renamed from: e */
    public final inw f5784e;

    /* renamed from: g */
    private final Map f5785g;

    /* renamed from: h */
    private final dgw f5786h;

    /* renamed from: i */
    private final bpt f5787i;

    /* renamed from: j */
    private final cjh f5788j;

    /* renamed from: k */
    private final cie f5789k;

    public cwd(Map map, dgw dgw, cui cui, Executor executor, bpt bpt, cjh cjh, cie cie, inw inw, inw inw2, inw inw3) {
        this.f5785g = map;
        this.f5786h = dgw;
        this.f5780a = cui;
        this.f5781b = executor;
        this.f5787i = bpt;
        this.f5788j = cjh;
        this.f5789k = cie;
        this.f5782c = inw;
        this.f5783d = inw2;
        this.f5784e = inw3;
    }

    /* renamed from: a */
    public final void mo3851a() {
        if (this.f5780a.mo3834a(cuh.DOGFOOD_JOB_LAUNCHER)) {
            ArrayList arrayList = new ArrayList();
            hvs i = f5779f.listIterator();
            while (i.hasNext()) {
                cut cut = (cut) this.f5785g.get((cup) i.next());
                if (cut != null) {
                    arrayList.add(cut);
                }
            }
            hlj a = hnb.m11765a("DogfoodJobLauncher");
            try {
                dgw dgw = this.f5786h;
                ieh a2 = gte.m10769a(gte.m10769a(this.f5789k.mo3154a(), this.f5788j.mo3171b(), this.f5787i.mo2656a(cwb.f5777a)).mo7613a((Callable) new cwc(this), this.f5781b)).mo7611a((ice) new cvz(this, arrayList), this.f5781b);
                dgu dgu = dgu.DOGFOOD_JOB;
                String str = (String) dgw.f6523b.get(dgu);
                if (str == null) {
                    cwn.m5514b("ServiceNotificationLauncher: Notification channel[%s] was not bound. Unable to bind future as service.", dgu);
                    a2 = ife.m12822a((Throwable) new dgv("Notification channel unbound!"));
                } else {
                    Context context = dgw.f6524c;
                    PendingIntent activity = PendingIntent.getActivity(context, 0, new Intent(context, dgw.f6525d), 0);
                    goo goo = dgw.f6522a;
                    Notification build = new Notification.Builder(dgw.f6524c, str).setContentTitle("Force-processing images for clustering.").setContentText("Running image labelling and person organisation analysis...").setContentIntent(activity).setSmallIcon(R.drawable.product_logo_gallery_go_color_24).setTicker("Running image labelling and person organisation analysis...").build();
                    ife.m12869b((Object) build, (Object) "A notification is required to use a foreground service");
                    if (!a2.isDone()) {
                        int i2 = Build.VERSION.SDK_INT;
                        if (!goo.f11752e.areNotificationsEnabled()) {
                            ((hvv) goo.f11748a.mo8179a(Level.WARNING).mo8201a("com/google/apps/tiktok/concurrent/AndroidFutures", "attachForegroundService", 229, "AndroidFutures.java")).mo8203a("User disabled notifications for app");
                        }
                        int i3 = Build.VERSION.SDK_INT;
                        NotificationChannel notificationChannel = goo.f11752e.getNotificationChannel(build.getChannelId());
                        int importance = notificationChannel.getImportance();
                        if (notificationChannel.getImportance() < 2) {
                            ((hvv) goo.f11748a.mo8179a(Level.WARNING).mo8201a("com/google/apps/tiktok/concurrent/AndroidFutures", "attachForegroundService", 237, "AndroidFutures.java")).mo8203a("User blocked notification channel");
                        }
                        int i4 = Build.VERSION.SDK_INT;
                        build.category = "service";
                        int i5 = Build.VERSION.SDK_INT;
                        build.flags |= 256;
                        build.flags |= 34;
                        gqw gqw = goo.f11751d;
                        gqy gqy = new gqy(build, importance);
                        synchronized (gqw.f11858d) {
                            gqy gqy2 = (gqy) gqw.f11859e.get(a2);
                            if (gqy2 == null) {
                                a2.mo53a(new gqt(gqw, a2), gqw.f11856b);
                            } else if (gqy2.f11866b >= gqy.f11866b) {
                            }
                            gqw.f11859e.put(a2, gqy);
                            got got = gqw.f11857c;
                            Runnable runnable = gqw.f11855a;
                            synchronized (got.f11767a) {
                                got.f11768b.add(runnable);
                            }
                            if (!gqw.f11857c.mo6891a()) {
                                gqv gqv = gqv.STOPPED;
                                int ordinal = gqw.f11861g.ordinal();
                                if (ordinal == 0) {
                                    gqw.mo6940a(gqy.f11865a);
                                } else if (ordinal == 2) {
                                    gqw.mo6941a(gqw.f11863i);
                                }
                            }
                        }
                    }
                }
                a.mo7548a(a2).mo53a(new cwa(this), idh.f13918a);
                if (a != null) {
                    a.close();
                }
            } catch (Throwable th) {
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable th2) {
                        ifn.m12935a(th, th2);
                    }
                }
                throw th;
            }
        }
    }
}
