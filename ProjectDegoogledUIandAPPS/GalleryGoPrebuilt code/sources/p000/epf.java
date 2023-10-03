package p000;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;

/* renamed from: epf */
/* compiled from: PG */
public final class epf extends eox {

    /* renamed from: c */
    private final IBinder f8743c;

    /* renamed from: d */
    private final /* synthetic */ epi f8744d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public epf(epi epi, int i, IBinder iBinder, Bundle bundle) {
        super(epi, i, bundle);
        this.f8744d = epi;
        this.f8743c = iBinder;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5098a(ejq ejq) {
        eoz eoz = this.f8744d.f8758l;
        if (eoz != null) {
            eoz.mo5102a(ejq);
        }
        this.f8744d.mo5111a(ejq);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo5099a() {
        try {
            String interfaceDescriptor = this.f8743c.getInterfaceDescriptor();
            if (!this.f8744d.mo4884b().equals(interfaceDescriptor)) {
                String b = this.f8744d.mo4884b();
                StringBuilder sb = new StringBuilder(String.valueOf(b).length() + 34 + String.valueOf(interfaceDescriptor).length());
                sb.append("service descriptor mismatch: ");
                sb.append(b);
                sb.append(" vs. ");
                sb.append(interfaceDescriptor);
                Log.e("GmsClient", sb.toString());
                return false;
            }
            IInterface a = this.f8744d.mo4882a(this.f8743c);
            if (a == null || (!this.f8744d.mo5116a(2, 4, a) && !this.f8744d.mo5116a(3, 4, a))) {
                return false;
            }
            epi epi = this.f8744d;
            epi.f8759m = null;
            eoy eoy = epi.f8757k;
            if (eoy == null) {
                return true;
            }
            ((ept) eoy).f8804a.mo4993a((Bundle) null);
            return true;
        } catch (RemoteException e) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }
}
