package p000;

import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.googlehelp.GoogleHelp;
import java.lang.ref.WeakReference;

/* renamed from: etp */
/* compiled from: PG */
public final class etp extends ets {

    /* renamed from: h */
    private final /* synthetic */ Intent f9009h;

    /* renamed from: i */
    private final /* synthetic */ WeakReference f9010i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public etp(ekv ekv, Intent intent, WeakReference weakReference) {
        super(ekv);
        this.f9009h = intent;
        this.f9010i = weakReference;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5237a(etz etz) {
        GoogleHelp googleHelp = (GoogleHelp) this.f9009h.getParcelableExtra("EXTRA_GOOGLE_HELP");
        esv esv = new esy(googleHelp).f8966a.f5086h;
        euc euc = new euc(googleHelp);
        etk etk = new etk(this, etz, this, esv, (byte[]) null);
        GoogleHelp googleHelp2 = euc.f9024a;
        try {
            etz etz2 = etk.f8996a;
            etp etp = etk.f8998c;
            ets ets = etk.f8997b;
            etz2.mo5241a(googleHelp2, new etq(etp.f9009h, etp.f9010i, ets, etk.f8999d, (byte[]) null));
        } catch (RemoteException e) {
            Log.e("gH_GoogleHelpApiImpl", "Starting help failed!", e);
            etk.f8998c.mo3513c(ett.f9015a);
        }
    }
}
