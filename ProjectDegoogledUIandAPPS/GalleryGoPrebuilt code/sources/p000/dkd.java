package p000;

import android.content.DialogInterface;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: dkd */
/* compiled from: PG */
final /* synthetic */ class dkd implements DialogInterface.OnShowListener {

    /* renamed from: a */
    private final dke f6711a;

    public dkd(dke dke) {
        this.f6711a = dke;
    }

    public final void onShow(DialogInterface dialogInterface) {
        int i;
        dke dke = this.f6711a;
        ((fdi) ((fdi) dke.f6720i.mo5579a(77459).mo5513a(fej.m8698a())).mo5513a(ffh.f9451a)).mo5510a();
        fhg.m8904b(dke.f6713b);
        dkm c = dke.f6719h.mo2635n();
        dkj dkj = dke.f6712a;
        ((fea) c.f6735f.f9364c.mo5563a(77458).mo5513a(ffh.f9451a)).mo5560a((View) c.f6737h);
        TextView textView = (TextView) c.f6730a.findViewById(R.id.disclaimer);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        hbl hbl = c.f6731b;
        int a = C0637xj.m15889a(dkj.f6726b);
        if (a == 0) {
            a = 2;
        }
        if (a - 1 != 2) {
            i = R.string.onboarding_disclaimer_location_not_confirmed;
        } else {
            i = R.string.onboarding_disclaimer;
        }
        textView.setText(Html.fromHtml(hbl.getString(i, new Object[]{c.f6732c, c.f6733d, c.f6736g, c.f6734e}), 63));
    }
}
