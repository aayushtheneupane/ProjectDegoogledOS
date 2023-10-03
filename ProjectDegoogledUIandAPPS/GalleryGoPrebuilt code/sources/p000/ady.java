package p000;

import android.util.SparseArray;
import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: ady */
/* compiled from: PG */
public final class ady extends C0667ym {

    /* renamed from: p */
    public boolean f247p;

    /* renamed from: q */
    public boolean f248q;

    /* renamed from: r */
    private final SparseArray f249r;

    public ady(View view) {
        super(view);
        SparseArray sparseArray = new SparseArray(4);
        this.f249r = sparseArray;
        sparseArray.put(16908310, view.findViewById(16908310));
        this.f249r.put(16908304, view.findViewById(16908304));
        this.f249r.put(16908294, view.findViewById(16908294));
        this.f249r.put(R.id.icon_frame, view.findViewById(R.id.icon_frame));
        this.f249r.put(16908350, view.findViewById(16908350));
    }

    /* renamed from: c */
    public final View mo235c(int i) {
        View view = (View) this.f249r.get(i);
        if (view == null && (view = this.f16382a.findViewById(i)) != null) {
            this.f249r.put(i, view);
        }
        return view;
    }
}
