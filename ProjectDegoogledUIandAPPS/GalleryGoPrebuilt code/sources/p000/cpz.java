package p000;

import android.util.ArraySet;
import android.view.ActionMode;
import android.view.Menu;
import com.google.android.apps.photosgo.R;
import java.util.Collection;
import java.util.Set;

/* renamed from: cpz */
/* compiled from: PG */
final class cpz extends cpw {

    /* renamed from: a */
    private C0147fh f5405a;

    /* renamed from: b */
    private cqh f5406b;

    public cpz(C0147fh fhVar, cqh cqh, hlz hlz, cpi cpi) {
        super(fhVar, cqh, hlz, cpi);
        this.f5405a = fhVar;
        this.f5406b = cqh;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo3737a(ActionMode actionMode, int i) {
        ife.m12898e((Object) this.f5406b);
        C0147fh fhVar = (C0147fh) ife.m12898e((Object) this.f5405a);
        if (i == 0) {
            throw null;
        } else if (i == R.id.picker_done) {
            Set<cpt> set = this.f5406b.f5420a;
            ArraySet arraySet = new ArraySet();
            for (cpt cpt : set) {
                if (cpt instanceof dul) {
                    dul dul = (dul) cpt;
                    if (dul.mo4432a() == 1) {
                        arraySet.add(dul.mo4437c());
                    }
                }
            }
            ihg.m13041a((hoi) new dub(hto.m12125a((Collection) arraySet)), fhVar);
            actionMode.finish();
        } else {
            String b = cun.m5449b(i);
            StringBuilder sb = new StringBuilder(b.length() + 17);
            sb.append("Unsupported item ");
            sb.append(b);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.picker_multiselect_menu, menu);
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo3736a() {
        this.f5405a = null;
        this.f5406b = null;
    }

    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        actionMode.setTitle(R.string.hide_and_show);
        return true;
    }
}
