package p000;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: fpr */
/* compiled from: PG */
public final class fpr implements fpv {

    /* renamed from: f */
    public static final String[] f10226f = {"key", "value"};

    /* renamed from: g */
    private static final Map f10227g = new C0290kn();

    /* renamed from: a */
    public final ContentResolver f10228a;

    /* renamed from: b */
    public final Uri f10229b;

    /* renamed from: c */
    public final Object f10230c = new Object();

    /* renamed from: d */
    public volatile Map f10231d;

    /* renamed from: e */
    public final List f10232e = new ArrayList();

    /* renamed from: h */
    private final ContentObserver f10233h = new fpq(this);

    private fpr(ContentResolver contentResolver, Uri uri) {
        this.f10228a = contentResolver;
        this.f10229b = uri;
        contentResolver.registerContentObserver(uri, false, this.f10233h);
    }

    /* renamed from: a */
    public static synchronized void m9370a() {
        synchronized (fpr.class) {
            for (fpr fpr : f10227g.values()) {
                fpr.f10228a.unregisterContentObserver(fpr.f10233h);
            }
            f10227g.clear();
        }
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo6022a(String str) {
        Map map;
        Map map2 = this.f10231d;
        if (map2 == null) {
            synchronized (this.f10230c) {
                map2 = this.f10231d;
                if (map2 == null) {
                    StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                    try {
                        map = (Map) fpt.m9378a((fpu) new fpp(this));
                        StrictMode.setThreadPolicy(allowThreadDiskReads);
                    } catch (SQLiteException | IllegalStateException | SecurityException e) {
                        try {
                            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            map = null;
                        } catch (Throwable th) {
                            StrictMode.setThreadPolicy(allowThreadDiskReads);
                            throw th;
                        }
                    }
                    this.f10231d = map;
                    map2 = map;
                }
            }
        }
        if (map2 == null) {
            map2 = Collections.emptyMap();
        }
        return (String) map2.get(str);
    }

    /* renamed from: a */
    public static fpr m9369a(ContentResolver contentResolver, Uri uri) {
        fpr fpr;
        synchronized (fpr.class) {
            fpr = (fpr) f10227g.get(uri);
            if (fpr == null) {
                try {
                    fpr fpr2 = new fpr(contentResolver, uri);
                    try {
                        f10227g.put(uri, fpr2);
                    } catch (SecurityException e) {
                    }
                    fpr = fpr2;
                } catch (SecurityException e2) {
                }
            }
        }
        return fpr;
    }
}
