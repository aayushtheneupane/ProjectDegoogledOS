package p000;

import android.text.Layout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.libraries.social.licenses.LicenseActivity;

/* renamed from: fss */
/* compiled from: PG */
public final /* synthetic */ class fss implements Runnable {

    /* renamed from: a */
    private final LicenseActivity f10544a;

    /* renamed from: b */
    private final int f10545b;

    /* renamed from: c */
    private final ScrollView f10546c;

    public fss(LicenseActivity licenseActivity, int i, ScrollView scrollView) {
        this.f10544a = licenseActivity;
        this.f10545b = i;
        this.f10546c = scrollView;
    }

    public final void run() {
        LicenseActivity licenseActivity = this.f10544a;
        int i = this.f10545b;
        ScrollView scrollView = this.f10546c;
        Layout layout = ((TextView) licenseActivity.findViewById(R.id.license_activity_textview)).getLayout();
        if (layout != null) {
            scrollView.scrollTo(0, layout.getLineTop(layout.getLineForOffset(i)));
        }
    }
}
