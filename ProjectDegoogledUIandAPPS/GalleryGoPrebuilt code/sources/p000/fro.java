package p000;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: fro */
/* compiled from: PG */
public final class fro implements fwt, fwd, fwg, fwq {

    /* renamed from: a */
    public final Activity f10326a;

    /* renamed from: b */
    public final Set f10327b = new HashSet();

    /* renamed from: c */
    public frq f10328c;

    public fro(Activity activity, fwc fwc) {
        this.f10326a = activity;
        fwc.mo6246a((fwt) this);
    }

    /* renamed from: a */
    public final void mo6071a(int i, int i2, Intent intent) {
        fri fri = new fri(i, i2, intent);
        boolean z = false;
        for (frn a : this.f10327b) {
            if (a.mo6069a(fri)) {
                z = true;
            }
        }
        if (!z) {
            frq frq = this.f10328c;
            Integer valueOf = Integer.valueOf(i);
            List list = (List) frq.f10329a.get(valueOf);
            if (list == null) {
                list = new ArrayList();
                frq.f10329a.put(valueOf, list);
            }
            list.add(fri);
        }
    }

    /* renamed from: a */
    public final void mo6072a(Bundle bundle) {
        if (bundle == null) {
            this.f10328c = new frq(fri.class);
        } else {
            this.f10328c = (frq) bundle.getParcelable("com.google.android.libraries.social.activityresult.ActivityResultManager.Results");
        }
    }

    /* renamed from: b */
    public final void mo6073b(Bundle bundle) {
        bundle.putParcelable("com.google.android.libraries.social.activityresult.ActivityResultManager.Results", this.f10328c);
    }
}
