package p000;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.gms.googlehelp.internal.common.TogglingData;
import java.lang.ref.WeakReference;

/* renamed from: etq */
/* compiled from: PG */
final class etq extends etw {

    /* renamed from: a */
    private final /* synthetic */ Intent f9011a;

    /* renamed from: b */
    private final /* synthetic */ WeakReference f9012b;

    /* renamed from: c */
    private final /* synthetic */ ets f9013c;

    /* renamed from: d */
    private final /* synthetic */ esv f9014d;

    public etq(Intent intent, WeakReference weakReference, ets ets, esv esv, byte[] bArr) {
        this.f9011a = intent;
        this.f9012b = weakReference;
        this.f9013c = ets;
        this.f9014d = esv;
    }

    /* renamed from: a */
    public final void mo5239a(GoogleHelp googleHelp) {
        eqx eqx;
        ViewGroup viewGroup;
        long nanoTime = System.nanoTime();
        this.f9011a.putExtra("EXTRA_START_TICK", nanoTime);
        Activity activity = (Activity) this.f9012b.get();
        if (activity == null) {
            this.f9013c.mo3513c(ett.f9015a);
            return;
        }
        if (this.f9014d != null) {
            euc euc = new euc(googleHelp);
            Context applicationContext = activity.getApplicationContext();
            esv esv = this.f9014d;
            esy esy = new esy(euc.f9024a);
            if (esv != null) {
                esy.f8966a.f5085g = true;
                Context context = applicationContext;
                esv esv2 = esv;
                long j = nanoTime;
                euc.m8176a(new eti(context, euc.f9024a, esv2, j, (byte[]) null));
                euc.m8176a(new etj(context, euc.f9024a, esv2, j, (byte[]) null));
            }
        }
        esy esy2 = new esy(googleHelp);
        esy2.f8966a.f5084f = ejw.f8455b;
        if (esy2.mo5225a() != null) {
            TogglingData a = esy2.mo5225a();
            String charSequence = activity.getTitle().toString();
            int identifier = activity.getResources().getIdentifier("action_bar", "id", activity.getPackageName());
            if (identifier != 0 && (viewGroup = (ViewGroup) activity.findViewById(identifier)) != null) {
                int i = 0;
                while (true) {
                    if (i >= viewGroup.getChildCount()) {
                        break;
                    }
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt instanceof TextView) {
                        charSequence = ((TextView) childAt).getText().toString();
                        break;
                    }
                    i++;
                }
            }
            a.f5105a = charSequence;
        }
        ets ets = this.f9013c;
        Intent intent = this.f9011a;
        if (intent.hasExtra("EXTRA_GOOGLE_HELP")) {
            intent.putExtra("EXTRA_GOOGLE_HELP", googleHelp);
        } else if (intent.hasExtra("EXTRA_IN_PRODUCT_HELP")) {
            Parcelable.Creator creator = ete.CREATOR;
            byte[] byteArrayExtra = intent.getByteArrayExtra("EXTRA_IN_PRODUCT_HELP");
            if (byteArrayExtra != null) {
                abj.m85a((Object) creator);
                int length = byteArrayExtra.length;
                Parcel obtain = Parcel.obtain();
                obtain.unmarshall(byteArrayExtra, 0, length);
                obtain.setDataPosition(0);
                eqx = (eqx) creator.createFromParcel(obtain);
                obtain.recycle();
            } else {
                eqx = null;
            }
            ete ete = (ete) eqx;
            ete.f8978a = googleHelp;
            Parcel obtain2 = Parcel.obtain();
            etf.m8141a(ete, obtain2, 0);
            byte[] marshall = obtain2.marshall();
            obtain2.recycle();
            intent.putExtra("EXTRA_IN_PRODUCT_HELP", marshall);
        }
        activity.startActivityForResult(intent, 123);
        ets.mo3507a((ela) Status.f4972a);
    }
}
