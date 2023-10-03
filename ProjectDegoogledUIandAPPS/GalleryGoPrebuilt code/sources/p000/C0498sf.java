package p000;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.p002v7.widget.ActionMenuView;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* renamed from: sf */
/* compiled from: PG */
public abstract class C0498sf extends ViewGroup {

    /* renamed from: a */
    public final Context f15859a;

    /* renamed from: b */
    public ActionMenuView f15860b;

    /* renamed from: c */
    public C0512st f15861c;

    /* renamed from: d */
    public int f15862d;

    /* renamed from: e */
    public C0344mn f15863e;

    /* renamed from: f */
    private final C0497se f15864f;

    /* renamed from: g */
    private boolean f15865g;

    /* renamed from: h */
    private boolean f15866h;

    C0498sf(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: a */
    protected static int m15369a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    /* renamed from: a */
    public void mo795a(int i) {
        throw null;
    }

    public C0498sf(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C0498sf(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f15864f = new C0497se(this);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f15859a = context;
        } else {
            this.f15859a = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }

    /* renamed from: a */
    protected static final int m15370a(View view, int i, int i2) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, RecyclerView.UNDEFINED_DURATION), i2);
        return Math.max(0, i - view.getMeasuredWidth());
    }

    /* access modifiers changed from: protected */
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes((AttributeSet) null, C0435px.f15573a, R.attr.actionBarStyle, 0);
        mo795a(obtainStyledAttributes.getLayoutDimension(13, 0));
        obtainStyledAttributes.recycle();
        C0512st stVar = this.f15861c;
        if (stVar != null) {
            stVar.f15880h = C0441qc.m15109a(stVar.f15691b).mo9692a();
            C0472rg rgVar = stVar.f15692c;
            if (rgVar != null) {
                rgVar.mo9851b(true);
            }
        }
    }

    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f15866h = false;
        }
        if (!this.f15866h) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.f15866h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f15866h = false;
        }
        return true;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f15865g = false;
        }
        if (!this.f15865g) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.f15865g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f15865g = false;
        }
        return true;
    }

    /* renamed from: a */
    protected static final int m15371a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
            return -measuredWidth;
        }
        view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        return measuredWidth;
    }

    public final void setVisibility(int i) {
        if (i != getVisibility()) {
            C0344mn mnVar = this.f15863e;
            if (mnVar != null) {
                mnVar.mo9399a();
            }
            super.setVisibility(i);
        }
    }

    /* renamed from: a */
    public final C0344mn mo10050a(int i, long j) {
        C0344mn mnVar = this.f15863e;
        if (mnVar != null) {
            mnVar.mo9399a();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            C0344mn k = C0340mj.m14720k(this);
            k.mo9400a(1.0f);
            k.mo9401a(j);
            C0497se seVar = this.f15864f;
            seVar.mo10049a(k, 0);
            k.mo9402a((C0345mo) seVar);
            return k;
        }
        C0344mn k2 = C0340mj.m14720k(this);
        k2.mo9400a(0.0f);
        k2.mo9401a(j);
        C0497se seVar2 = this.f15864f;
        seVar2.mo10049a(k2, i);
        k2.mo9402a((C0345mo) seVar2);
        return k2;
    }
}
