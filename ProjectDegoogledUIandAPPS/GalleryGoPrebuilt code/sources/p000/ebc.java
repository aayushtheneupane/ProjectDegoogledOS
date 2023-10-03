package p000;

import android.content.Context;
import android.content.res.Configuration;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.R;

/* renamed from: ebc */
/* compiled from: PG */
public class ebc extends FrameLayout {

    /* renamed from: a */
    public final C0607wg f7825a;

    /* renamed from: b */
    public final RecyclerView f7826b;

    /* renamed from: c */
    public int f7827c;

    /* renamed from: d */
    public C0643xp f7828d;

    /* renamed from: e */
    public eav f7829e;

    /* renamed from: f */
    public final eaz f7830f;

    /* renamed from: g */
    private final C0652xy f7831g;

    public ebc(Context context) {
        this(context, (AttributeSet) null);
    }

    public ebc(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ebc(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f7827c = 1;
        this.f7828d = null;
        this.f7830f = new eaz((byte[]) null);
        this.f7831g = new eaw(this);
        inflate(context, R.layout.recycler_view_pager, this);
        this.f7826b = (RecyclerView) findViewById(R.id.recycler_view);
        this.f7825a = new eax(this);
        this.f7826b.addOnScrollListener(this.f7831g);
        this.f7826b.setLayoutManager(this.f7825a);
        this.f7826b.setHasFixedSize(true);
        this.f7826b.setItemAnimator(this.f7830f);
        this.f7826b.setScrollingTouchSlop(1);
        new ebb().mo4650a(this.f7826b);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        this.f7826b.scrollToPosition(this.f7829e.f7804d);
    }
}
