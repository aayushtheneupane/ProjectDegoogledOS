package p000;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;

/* renamed from: crb */
/* compiled from: PG */
final /* synthetic */ class crb implements View.OnClickListener {

    /* renamed from: a */
    private final crd f5453a;

    public crb(crd crd) {
        this.f5453a = crd;
    }

    public final void onClick(View view) {
        cqt cqt = this.f5453a.f5460a;
        String[] strArr = crx.f5527a;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            C0160fu fuVar = cqt.f9605x;
            if (fuVar != null) {
                C0149fj fjVar = ((C0148fi) fuVar).f9696a;
                int i2 = Build.VERSION.SDK_INT;
                if (fjVar.shouldShowRequestPermissionRationale(str)) {
                    i++;
                }
            }
            C0149fj m = cqt.mo5653m();
            if (m != null) {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", m.getPackageName(), (String) null));
                C0160fu fuVar2 = cqt.f9605x;
                if (fuVar2 != null) {
                    fuVar2.mo5743a(cqt, intent, -1);
                    return;
                }
                throw new IllegalStateException("Fragment " + cqt + " not attached to Activity");
            }
            return;
        }
        cqt.mo5640a(crx.f5527a);
    }
}
