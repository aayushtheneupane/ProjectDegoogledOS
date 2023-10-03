package p000;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import java.util.concurrent.TimeUnit;

/* renamed from: fkd */
/* compiled from: PG */
final class fkd extends fhn implements fmc, fhz {

    /* renamed from: d */
    public static final long f9870d = TimeUnit.HOURS.toMillis(12);

    /* renamed from: e */
    public final SharedPreferences f9871e;

    /* renamed from: f */
    public final boolean f9872f;

    /* renamed from: g */
    public final int f9873g;

    /* renamed from: h */
    public final hso f9874h;

    /* renamed from: i */
    private final fid f9875i;

    /* renamed from: f */
    public final void mo5834f() {
    }

    private fkd(iqk iqk, Application application, hqk hqk, hqk hqk2, SharedPreferences sharedPreferences) {
        this(iqk, application, hqk, hqk2, sharedPreferences, false, -1, hso.m12047f());
    }

    private fkd(iqk iqk, Application application, hqk hqk, hqk hqk2, SharedPreferences sharedPreferences, boolean z, int i, hso hso) {
        super(iqk, application, hqk, hqk2, 1);
        this.f9871e = sharedPreferences;
        this.f9872f = z;
        this.f9873g = i;
        this.f9874h = hso;
        this.f9875i = fid.m8938a(application);
    }

    /* renamed from: a */
    static fkd m9064a(iqk iqk, Application application, hqk hqk, hqk hqk2, SharedPreferences sharedPreferences, hpy hpy) {
        if (!hpy.mo7646a()) {
            return new fkd(iqk, application, hqk, hqk2, sharedPreferences);
        }
        return new fkd(iqk, application, hqk, hqk2, sharedPreferences, ((flh) hpy.mo7647b()).mo5772a(), ((flh) hpy.mo7647b()).mo5773b(), ((flh) hpy.mo7647b()).mo5774c());
    }

    /* renamed from: b */
    public final void mo5742b(Activity activity) {
        this.f9875i.mo5748b(this);
        mo5732c().mo5931a((Runnable) new fkc(this));
    }

    /* renamed from: e */
    public final void mo5833e() {
        this.f9875i.mo5747a((fic) this);
    }

    /* renamed from: d */
    public final void mo5733d() {
        this.f9875i.mo5748b(this);
    }
}
