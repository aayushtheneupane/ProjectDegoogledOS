package android.support.p002v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.R;

/* renamed from: android.support.v7.widget.ActionBarContainer */
/* compiled from: PG */
public class ActionBarContainer extends FrameLayout {

    /* renamed from: a */
    public Drawable f887a;

    /* renamed from: b */
    public Drawable f888b;

    /* renamed from: c */
    public Drawable f889c;

    /* renamed from: d */
    public boolean f890d;

    /* renamed from: e */
    private boolean f891e;

    /* renamed from: f */
    private View f892f;

    /* renamed from: g */
    private View f893g;

    /* renamed from: h */
    private int f894h;

    public ActionBarContainer(Context context) {
        this(context, (AttributeSet) null);
    }

    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C0340mj.m14694a((View) this, (Drawable) new C0499sg(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0435px.f15573a);
        boolean z = false;
        this.f887a = obtainStyledAttributes.getDrawable(0);
        this.f888b = obtainStyledAttributes.getDrawable(2);
        this.f894h = obtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == R.id.split_action_bar) {
            this.f890d = true;
            this.f889c = obtainStyledAttributes.getDrawable(1);
        }
        obtainStyledAttributes.recycle();
        if (this.f890d ? this.f889c == null : this.f887a == null && this.f888b == null) {
            z = true;
        }
        setWillNotDraw(z);
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f887a;
        if (drawable != null && drawable.isStateful()) {
            this.f887a.setState(getDrawableState());
        }
        Drawable drawable2 = this.f888b;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f888b.setState(getDrawableState());
        }
        Drawable drawable3 = this.f889c;
        if (drawable3 != null && drawable3.isStateful()) {
            this.f889c.setState(getDrawableState());
        }
    }

    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f887a;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f888b;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f889c;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f892f = findViewById(R.id.action_bar);
        this.f893g = findViewById(R.id.action_context_bar);
    }

    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f891e || super.onInterceptTouchEvent(motionEvent);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f890d) {
            Drawable drawable = this.f889c;
            if (drawable != null) {
                drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                return;
            }
        } else if (this.f887a == null) {
            return;
        } else {
            if (this.f892f.getVisibility() == 0) {
                this.f887a.setBounds(this.f892f.getLeft(), this.f892f.getTop(), this.f892f.getRight(), this.f892f.getBottom());
            } else {
                View view = this.f893g;
                if (view != null && view.getVisibility() == 0) {
                    this.f887a.setBounds(this.f893g.getLeft(), this.f893g.getTop(), this.f893g.getRight(), this.f893g.getBottom());
                } else {
                    this.f887a.setBounds(0, 0, 0, 0);
                }
            }
        }
        invalidate();
    }

    public final void onMeasure(int i, int i2) {
        int i3;
        if (this.f892f == null && View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE && (i3 = this.f894h) >= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(i3, View.MeasureSpec.getSize(i2)), RecyclerView.UNDEFINED_DURATION);
        }
        super.onMeasure(i, i2);
        if (this.f892f != null) {
            View.MeasureSpec.getMode(i2);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    /* renamed from: a */
    public final void mo781a(boolean z) {
        int i;
        this.f891e = z;
        if (!z) {
            i = 262144;
        } else {
            i = 393216;
        }
        setDescendantFocusability(i);
    }

    public final void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.f887a;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.f888b;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.f889c;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final boolean verifyDrawable(Drawable drawable) {
        if (drawable != this.f887a || this.f890d) {
            return (drawable == this.f889c && this.f890d) || super.verifyDrawable(drawable);
        }
        return true;
    }
}
