package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.apps.photosgo.R;
import com.google.android.material.textfield.TextInputEditText;
import java.util.List;

/* renamed from: clp */
/* compiled from: PG */
final class clp implements gvc {

    /* renamed from: a */
    private boolean f4619a = true;

    /* renamed from: b */
    private final /* synthetic */ clr f4620b;

    public clp(clr clr) {
        this.f4620b = clr;
    }

    /* renamed from: a */
    public final void mo2679a() {
    }

    /* renamed from: a */
    public final void mo2681a(Throwable th) {
        cwn.m5515b(th, "FolderCreationFragmentPeer: Error while getting the volume list.", new Object[0]);
        this.f4620b.f4629e.mo2572a((int) R.string.folder_creation_no_volumes);
        this.f4620b.f4627c.mo3274e();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo2680a(Object obj) {
        int i;
        List<cjv> list = (List) obj;
        ((gwd) ife.m12885c((Object) this.f4620b.f4644t)).mo7129a((List) list);
        hsq g = hsu.m12070g();
        for (cjv cjv : list) {
            g.mo7932a(cjv.mo3177b(), cjv.mo3178c());
        }
        cmg cmg = this.f4620b.f4630f;
        cmg.f4672c = g.mo7930a();
        cmg.mo3253a();
        View view = this.f4620b.f4626b.f9573L;
        if (view != null) {
            RecyclerView recyclerView = (RecyclerView) ife.m12898e((Object) (RecyclerView) view.findViewById(R.id.folder_creation_volume_list));
            if (list.size() > 1) {
                i = 0;
            } else {
                i = 8;
            }
            recyclerView.setVisibility(i);
            clr clr = this.f4620b;
            clr.f4647w = true;
            clr.mo3247e();
            if (this.f4619a) {
                ((TextInputEditText) ife.m12898e((Object) this.f4620b.f4642r)).requestFocus();
                ((InputMethodManager) this.f4620b.f4626b.mo2634k().getSystemService("input_method")).toggleSoftInput(2, 1);
                this.f4619a = false;
            }
        }
    }
}
