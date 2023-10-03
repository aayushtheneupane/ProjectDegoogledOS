package p000;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: dkm */
/* compiled from: PG */
public final class dkm {

    /* renamed from: a */
    public final dkk f6730a;

    /* renamed from: b */
    public final hbl f6731b;

    /* renamed from: c */
    public final String f6732c;

    /* renamed from: d */
    public final String f6733d;

    /* renamed from: e */
    public final String f6734e;

    /* renamed from: f */
    public final fee f6735f;

    /* renamed from: g */
    public final String f6736g;

    /* renamed from: h */
    public final Button f6737h;

    public dkm(dkk dkk, hbl hbl, hos hos, String str, String str2, String str3, fee fee, fdv fdv) {
        this.f6730a = dkk;
        this.f6731b = hbl;
        this.f6732c = str;
        this.f6733d = str2;
        this.f6734e = str3;
        this.f6735f = fee;
        LayoutInflater.from(hbl).inflate(R.layout.onboarding_view_content, dkk, true);
        this.f6736g = hbl.getString(R.string.app_name);
        ((TextView) dkk.findViewById(R.id.welcome)).setText(hbl.getString(R.string.onboarding_welcome, new Object[]{this.f6736g}));
        Button button = (Button) dkk.findViewById(R.id.continue_button);
        this.f6737h = button;
        hos.mo7632a((View) button, (View.OnClickListener) new dkl(fdv, dkk));
    }
}
