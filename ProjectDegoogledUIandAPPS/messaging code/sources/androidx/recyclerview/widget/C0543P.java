package androidx.recyclerview.widget;

import android.os.Build;
import android.os.Trace;
import android.view.ViewGroup;

/* renamed from: androidx.recyclerview.widget.P */
public abstract class C0543P {

    /* renamed from: or */
    private final C0544Q f558or = new C0544Q();

    /* renamed from: pr */
    private boolean f559pr = false;

    /* renamed from: a */
    public final void mo4797a(C0586qa qaVar, int i) {
        qaVar.mPosition = i;
        if (this.f559pr) {
            qaVar.mItemId = getItemId(i);
        }
        qaVar.setFlags(1, 519);
        int i2 = Build.VERSION.SDK_INT;
        Trace.beginSection("RV OnBindView");
        qaVar.getUnmodifiedPayloads();
        mo4800b(qaVar, i);
        qaVar.clearPayload();
        ViewGroup.LayoutParams layoutParams = qaVar.itemView.getLayoutParams();
        if (layoutParams instanceof C0560da) {
            ((C0560da) layoutParams).mInsetsDirty = true;
        }
        int i3 = Build.VERSION.SDK_INT;
        Trace.endSection();
    }

    /* renamed from: b */
    public void mo4798b(RecyclerView recyclerView) {
    }

    /* renamed from: b */
    public void mo4799b(C0545S s) {
        this.f558or.unregisterObserver(s);
    }

    /* renamed from: b */
    public abstract void mo4800b(C0586qa qaVar, int i);

    public final C0586qa createViewHolder(ViewGroup viewGroup, int i) {
        try {
            int i2 = Build.VERSION.SDK_INT;
            Trace.beginSection("RV CreateView");
            C0586qa onCreateViewHolder = onCreateViewHolder(viewGroup, i);
            if (onCreateViewHolder.itemView.getParent() == null) {
                onCreateViewHolder.mItemViewType = i;
                return onCreateViewHolder;
            }
            throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
        } finally {
            int i3 = Build.VERSION.SDK_INT;
            Trace.endSection();
        }
    }

    /* renamed from: e */
    public boolean mo4802e(C0586qa qaVar) {
        return false;
    }

    /* renamed from: f */
    public void mo4803f(C0586qa qaVar) {
    }

    /* renamed from: g */
    public void mo4804g(C0586qa qaVar) {
    }

    public abstract int getItemCount();

    public abstract long getItemId(int i);

    public int getItemViewType(int i) {
        return 0;
    }

    /* renamed from: h */
    public void mo4808h(C0586qa qaVar) {
    }

    public final boolean hasStableIds() {
        return this.f559pr;
    }

    public final void notifyDataSetChanged() {
        this.f558or.notifyChanged();
    }

    public abstract C0586qa onCreateViewHolder(ViewGroup viewGroup, int i);

    public void setHasStableIds(boolean z) {
        if (!this.f558or.hasObservers()) {
            this.f559pr = z;
            return;
        }
        throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
    }

    /* renamed from: a */
    public void mo4796a(C0545S s) {
        this.f558or.registerObserver(s);
    }
}
