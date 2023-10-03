package p000;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.feedback.ErrorReport;
import com.google.android.gms.googlehelp.GoogleHelp;
import java.io.File;

@Deprecated
/* renamed from: fbd */
/* compiled from: PG */
final class fbd implements fba {

    /* renamed from: a */
    private final GoogleHelp f9251a;

    /* renamed from: a */
    public final void mo5453a(int i, String str, Intent intent) {
        this.f9251a.f5081c.add(new eua(i, str, intent));
    }

    /* renamed from: a */
    public final Intent mo5452a() {
        return new Intent("com.google.android.gms.googlehelp.HELP").setPackage("com.google.android.gms").putExtra("EXTRA_GOOGLE_HELP", this.f9251a);
    }

    /* renamed from: a */
    public final void mo5455a(Uri uri) {
        this.f9251a.f5080b = uri;
    }

    /* renamed from: a */
    public final void mo5456a(fae fae, File file) {
        GoogleHelp googleHelp = this.f9251a;
        esi esi = ((far) fae).f9247a;
        if (esi != null) {
            googleHelp.f5086h = esi.f8940q;
        }
        googleHelp.f5082d = new ErrorReport(esi, file);
        googleHelp.f5082d.f5037a = "GoogleHelp";
    }

    /* renamed from: a */
    public final void mo5454a(Account account) {
        this.f9251a.f5079a = account;
    }

    public fbd(String str) {
        this.f9251a = new GoogleHelp(str);
    }
}
