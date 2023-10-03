package p000;

import android.view.ActionMode;
import android.view.MenuItem;
import com.google.android.apps.photosgo.R;
import java.util.List;

/* renamed from: cpw */
/* compiled from: PG */
abstract class cpw implements ActionMode.Callback {

    /* renamed from: a */
    private C0147fh f5398a;

    /* renamed from: b */
    private cqh f5399b;

    /* renamed from: c */
    private hlz f5400c;

    /* renamed from: d */
    private cpi f5401d;

    public cpw(C0147fh fhVar, cqh cqh, hlz hlz, cpi cpi) {
        this.f5398a = fhVar;
        this.f5399b = cqh;
        this.f5400c = hlz;
        this.f5401d = cpi;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo3736a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo3737a(ActionMode actionMode, int i);

    /* renamed from: b */
    private final void m5238b() {
        ife.m12898e((Object) this.f5399b);
        this.f5399b.mo3766f();
        this.f5399b.mo3755a();
        mo3736a();
        C0147fh fhVar = (C0147fh) ife.m12898e((Object) this.f5398a);
        if (fhVar.f9573L != null) {
            ihg.m13041a((hoi) new cpq((cpi) ife.m12898e((Object) this.f5401d)), fhVar);
        }
        this.f5398a = null;
        this.f5399b = null;
        this.f5400c = null;
        this.f5401d = null;
    }

    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        hlp a = ((hlz) ife.m12898e((Object) this.f5400c)).mo7577a("ActionModeMenuItemClicked");
        try {
            int itemId = menuItem.getItemId();
            int[] iArr = {R.id.select_all, R.id.delete, R.id.share, R.id.move_to_folder, R.id.copy_to_folder, R.id.picker_done};
            int i = 0;
            while (i < 6) {
                int i2 = iArr[i];
                if (i2 == 0) {
                    throw null;
                } else if (i2 != itemId) {
                    i++;
                } else {
                    mo3737a(actionMode, i2);
                    if (a != null) {
                        a.close();
                    }
                    return true;
                }
            }
            StringBuilder sb = new StringBuilder(38);
            sb.append("Menu ID ");
            sb.append(itemId);
            sb.append(" is not implemented");
            throw new IllegalArgumentException(sb.toString());
        } catch (IllegalArgumentException e) {
            try {
                cwn.m5515b((Throwable) e, "ActionModeHelper: Unsupported action[%s]", menuItem);
                if (a != null) {
                    a.close();
                }
                return false;
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        }
        throw th;
    }

    public final void onDestroyActionMode(ActionMode actionMode) {
        hlp a;
        try {
            List list = hnb.f13076a;
            m5238b();
        } catch (IllegalStateException e) {
            a = ((hlz) ife.m12898e((Object) this.f5400c)).mo7577a("ActionModeCloseButtonClicked");
            m5238b();
            if (a != null) {
                a.close();
            }
        } catch (Throwable th) {
            if (a != null) {
                try {
                    a.close();
                } catch (Throwable th2) {
                    ifn.m12935a(th, th2);
                }
            }
            throw th;
        }
    }
}
