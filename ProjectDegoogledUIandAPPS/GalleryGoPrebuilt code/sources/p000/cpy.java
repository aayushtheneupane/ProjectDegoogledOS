package p000;

import android.view.ActionMode;
import android.view.Menu;
import android.view.View;
import com.google.android.apps.photosgo.R;
import java.util.Collection;

/* renamed from: cpy */
/* compiled from: PG */
final class cpy extends cpw {

    /* renamed from: a */
    private C0147fh f5402a;

    /* renamed from: b */
    private cqh f5403b;

    /* renamed from: c */
    private cnh f5404c;

    public cpy(C0147fh fhVar, cqh cqh, hlz hlz, cpi cpi, cnh cnh) {
        super(fhVar, cqh, hlz, cpi);
        this.f5402a = fhVar;
        this.f5403b = cqh;
        this.f5404c = cnh;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo3737a(ActionMode actionMode, int i) {
        if (i == 0) {
            throw null;
        } else if (i == R.id.picker_done) {
            C0149fj m = ((C0147fh) ife.m12898e((Object) this.f5402a)).mo5653m();
            if (m != null) {
                dvo dvo = new dvo(hto.m12125a((Collection) dwv.m6830a(((cqh) ife.m12898e((Object) this.f5403b)).f5420a)));
                View findViewById = m.findViewById(16908290);
                ife.m12869b((Object) findViewById, (Object) "Activity must have content view to send an event!");
                ihg.m13032a((int) R.id.tiktok_event_activity_listeners, (hoi) dvo, findViewById);
            }
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
        C0573v a = ((C0147fh) ife.m12898e((Object) this.f5402a)).mo5ad().mo61a();
        if (a == C0573v.RESUMED || a == C0573v.STARTED) {
            ((cnh) ife.m12898e((Object) this.f5404c)).mo3274e();
        }
        this.f5402a = null;
        this.f5403b = null;
        this.f5404c = null;
    }

    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        ife.m12898e((Object) this.f5403b);
        if (this.f5403b.f5420a.isEmpty()) {
            actionMode.setTitle(R.string.select_items_text);
            menu.setGroupEnabled(R.id.picker_group_done, false);
        } else {
            actionMode.setTitle(String.valueOf(this.f5403b.f5420a.size()));
            menu.setGroupEnabled(R.id.picker_group_done, true);
        }
        return true;
    }
}
