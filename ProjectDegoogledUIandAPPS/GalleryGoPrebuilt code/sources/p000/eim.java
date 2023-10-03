package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* renamed from: eim */
/* compiled from: PG */
public final class eim {

    /* renamed from: a */
    public int f8347a = Integer.MAX_VALUE;

    /* renamed from: b */
    public int f8348b = Integer.MAX_VALUE;

    /* renamed from: c */
    public int f8349c = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: d */
    public int f8350d = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: e */
    public int f8351e;

    /* renamed from: f */
    public int f8352f;

    /* renamed from: g */
    public int f8353g;

    /* renamed from: h */
    public int f8354h;

    /* renamed from: i */
    public int f8355i;

    /* renamed from: j */
    public float f8356j;

    /* renamed from: k */
    public float f8357k;

    /* renamed from: l */
    public int f8358l;

    /* renamed from: m */
    public int f8359m;

    /* renamed from: n */
    public final List f8360n = new ArrayList();

    /* renamed from: o */
    public int f8361o;

    /* renamed from: a */
    public final int mo4843a() {
        return this.f8354h - this.f8355i;
    }

    /* renamed from: a */
    public final void mo4844a(View view, int i, int i2, int i3, int i4) {
        eil eil = (eil) view.getLayoutParams();
        this.f8347a = Math.min(this.f8347a, (view.getLeft() - eil.mo4839m()) - i);
        this.f8348b = Math.min(this.f8348b, (view.getTop() - eil.mo4840n()) - i2);
        this.f8349c = Math.max(this.f8349c, view.getRight() + eil.mo4841o() + i3);
        this.f8350d = Math.max(this.f8350d, view.getBottom() + eil.mo4842p() + i4);
    }
}
