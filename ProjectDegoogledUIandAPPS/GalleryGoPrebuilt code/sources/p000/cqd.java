package p000;

import android.content.Context;
import android.content.res.Configuration;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.R;

/* renamed from: cqd */
/* compiled from: PG */
public final class cqd extends FrameLayout {

    /* renamed from: a */
    public final RecyclerView f5411a = ((RecyclerView) findViewById(R.id.recycler_view));

    /* renamed from: b */
    private int f5412b;

    /* renamed from: c */
    private View f5413c;

    public cqd(Context context) {
        super(context);
        inflate(context, R.layout.emptyable_recycler_view_switcher, this);
    }

    /* renamed from: a */
    public final void mo3753a(int i) {
        boolean z = true;
        ife.m12876b(this.f5412b == 0, (Object) "Can only call initializeEmptyViewLayout once.");
        if (i == 0) {
            z = false;
        }
        ife.m12845a(z, (Object) "Must set a valid empty view layout resource.");
        ((ViewStub) findViewById(R.id.empty_state)).setLayoutResource(i);
        this.f5412b = i;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        View view = this.f5413c;
        if (view != null) {
            removeView(view);
            ViewGroup.LayoutParams layoutParams = this.f5413c.getLayoutParams();
            View inflate = LayoutInflater.from(getContext()).inflate(this.f5412b, this, false);
            this.f5413c = inflate;
            inflate.setLayoutParams(layoutParams);
            addView(this.f5413c);
        }
    }

    /* renamed from: a */
    public final void mo3752a() {
        C0634xg adapter = this.f5411a.getAdapter();
        boolean z = true;
        int i = 0;
        if (!(adapter == null || adapter.mo220a() == 0)) {
            z = false;
        }
        if (z && this.f5413c == null) {
            if (this.f5412b != 0) {
                this.f5413c = ((ViewStub) findViewById(R.id.empty_state)).inflate();
            } else {
                return;
            }
        }
        View view = this.f5413c;
        if (view != null) {
            view.setVisibility(!z ? 8 : 0);
        }
        RecyclerView recyclerView = this.f5411a;
        if (recyclerView != null) {
            if (z) {
                i = 8;
            }
            recyclerView.setVisibility(i);
        }
    }
}
