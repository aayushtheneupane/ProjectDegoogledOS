package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.recyclerview.widget.ua */
public abstract class C0594ua {

    /* renamed from: ft */
    private ArrayList f670ft = new ArrayList();

    /* renamed from: gt */
    private long f671gt = 120;

    /* renamed from: ht */
    private long f672ht = 120;

    /* renamed from: it */
    private long f673it = 250;

    /* renamed from: jt */
    private long f674jt = 250;
    private C0549W mListener = null;
    boolean mSupportsChangeAnimations = true;

    /* renamed from: p */
    static int m915p(C0586qa qaVar) {
        int i = qaVar.mFlags & 14;
        if (qaVar.isInvalid()) {
            return 4;
        }
        if ((i & 4) != 0) {
            return i;
        }
        int oldPosition = qaVar.getOldPosition();
        int adapterPosition = qaVar.getAdapterPosition();
        return (oldPosition == -1 || adapterPosition == -1 || oldPosition == adapterPosition) ? i : i | 2048;
    }

    /* renamed from: a */
    public abstract boolean mo5168a(C0586qa qaVar, int i, int i2, int i3, int i4);

    /* renamed from: a */
    public boolean mo5238a(C0586qa qaVar, C0548V v, C0548V v2) {
        if (v == null || (v.left == v2.left && v.top == v2.top)) {
            return mo5176n(qaVar);
        }
        return mo5168a(qaVar, v.left, v.top, v2.left, v2.top);
    }

    /* renamed from: a */
    public abstract boolean mo5169a(C0586qa qaVar, C0586qa qaVar2, int i, int i2, int i3, int i4);

    /* renamed from: a */
    public abstract boolean mo5170a(C0586qa qaVar, List list);

    /* renamed from: b */
    public boolean mo5241b(C0586qa qaVar, C0548V v, C0548V v2) {
        int i = v.left;
        int i2 = v.top;
        View view = qaVar.itemView;
        int left = v2 == null ? view.getLeft() : v2.left;
        int top = v2 == null ? view.getTop() : v2.top;
        if (qaVar.isRemoved() || (i == left && i2 == top)) {
            mo5177o(qaVar);
            return true;
        }
        view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
        return mo5168a(qaVar, i, i2, left, top);
    }

    /* renamed from: c */
    public final void mo5242c(C0586qa qaVar, boolean z) {
    }

    /* renamed from: d */
    public boolean mo5243d(C0586qa qaVar, C0548V v, C0548V v2) {
        if (v.left == v2.left && v.top == v2.top) {
            C0549W w = this.mListener;
            if (w == null) {
                return false;
            }
            w.mo4984j(qaVar);
            return false;
        }
        return mo5168a(qaVar, v.left, v.top, v2.left, v2.top);
    }

    public final void dispatchAnimationsFinished() {
        int size = this.f670ft.size();
        for (int i = 0; i < size; i++) {
            ((C0547U) this.f670ft.get(i)).onAnimationsFinished();
        }
        this.f670ft.clear();
    }

    public abstract void endAnimations();

    public long getAddDuration() {
        return this.f671gt;
    }

    public long getChangeDuration() {
        return this.f674jt;
    }

    public long getMoveDuration() {
        return this.f673it;
    }

    public long getRemoveDuration() {
        return this.f672ht;
    }

    public abstract boolean isRunning();

    /* renamed from: n */
    public abstract boolean mo5176n(C0586qa qaVar);

    /* renamed from: o */
    public abstract boolean mo5177o(C0586qa qaVar);

    public C0548V obtainHolderInfo() {
        return new C0548V();
    }

    /* renamed from: q */
    public final void mo5250q(C0586qa qaVar) {
        C0549W w = this.mListener;
        if (w != null) {
            w.mo4984j(qaVar);
        }
    }

    /* renamed from: r */
    public final void mo5251r(C0586qa qaVar) {
    }

    public abstract void runPendingAnimations();

    /* renamed from: s */
    public final void mo5252s(C0586qa qaVar) {
        C0549W w = this.mListener;
        if (w != null) {
            w.mo4984j(qaVar);
        }
    }

    /* renamed from: t */
    public final void mo5253t(C0586qa qaVar) {
    }

    /* renamed from: u */
    public final void mo5254u(C0586qa qaVar) {
        C0549W w = this.mListener;
        if (w != null) {
            w.mo4984j(qaVar);
        }
    }

    /* renamed from: v */
    public final void mo5255v(C0586qa qaVar) {
    }

    /* renamed from: w */
    public abstract void mo5179w(C0586qa qaVar);

    /* renamed from: a */
    public boolean mo5239a(C0586qa qaVar, C0586qa qaVar2, C0548V v, C0548V v2) {
        int i;
        int i2;
        int i3 = v.left;
        int i4 = v.top;
        if (qaVar2.shouldIgnore()) {
            int i5 = v.left;
            i = v.top;
            i2 = i5;
        } else {
            i2 = v2.left;
            i = v2.top;
        }
        return mo5169a(qaVar, qaVar2, i3, i4, i2, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5237a(C0549W w) {
        this.mListener = w;
    }

    /* renamed from: b */
    public final void mo5240b(C0586qa qaVar, boolean z) {
        C0549W w = this.mListener;
        if (w != null) {
            w.mo4984j(qaVar);
        }
    }

    /* renamed from: a */
    public C0548V mo5236a(C0582oa oaVar, C0586qa qaVar, int i, List list) {
        C0548V obtainHolderInfo = obtainHolderInfo();
        obtainHolderInfo.mo4983i(qaVar);
        return obtainHolderInfo;
    }
}
