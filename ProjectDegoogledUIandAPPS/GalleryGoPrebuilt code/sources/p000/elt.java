package p000;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.google.android.gms.common.api.GoogleApiActivity;

/* renamed from: elt */
/* compiled from: PG */
final class elt implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ elu f8524a;

    /* renamed from: b */
    private final elr f8525b;

    public elt(elu elu, elr elr) {
        this.f8524a = elu;
        this.f8525b = elr;
    }

    public final void run() {
        if (this.f8524a.f8526a) {
            ejq ejq = this.f8525b.f8521b;
            if (ejq.mo4894a()) {
                elu elu = this.f8524a;
                elu.f4997g.startActivityForResult(GoogleApiActivity.m4940a(elu.mo3520g(), ejq.f8444c, this.f8525b.f8520a, false), 1);
            } else if (ekh.m7672b(ejq.f8443b)) {
                elu elu2 = this.f8524a;
                ejw ejw = elu2.f8529d;
                Activity g = elu2.mo3520g();
                elu elu3 = this.f8524a;
                enw enw = elu3.f4997g;
                int i = ejq.f8443b;
                Dialog a = ejw.m7637a((Context) g, i, (epq) new epp(ejw.mo4917a(g, i, "d"), enw), (DialogInterface.OnCancelListener) elu3);
                if (a != null) {
                    ejw.m7638a(g, a, "GooglePlayServicesErrorDialog", (DialogInterface.OnCancelListener) elu3);
                }
            } else if (ejq.f8443b != 18) {
                this.f8524a.mo4985a(ejq, this.f8525b.f8520a);
            } else {
                Activity g2 = this.f8524a.mo3520g();
                elu elu4 = this.f8524a;
                ProgressBar progressBar = new ProgressBar(g2, (AttributeSet) null, 16842874);
                progressBar.setIndeterminate(true);
                progressBar.setVisibility(0);
                AlertDialog.Builder builder = new AlertDialog.Builder(g2);
                builder.setView(progressBar);
                builder.setMessage(epl.m8004c(g2, 18));
                builder.setPositiveButton("", (DialogInterface.OnClickListener) null);
                AlertDialog create = builder.create();
                ejw.m7638a(g2, (Dialog) create, "GooglePlayServicesUpdatingDialog", (DialogInterface.OnCancelListener) elu4);
                elu elu5 = this.f8524a;
                elu5.f8529d.mo4912a(elu5.mo3520g().getApplicationContext(), (enr) new els(this, create));
            }
        }
    }
}
