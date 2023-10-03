package android.support.p002v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: android.support.v7.widget.ActionBarContextView */
/* compiled from: PG */
public class ActionBarContextView extends C0498sf {

    /* renamed from: f */
    public CharSequence f895f;

    /* renamed from: g */
    public CharSequence f896g;

    /* renamed from: h */
    public View f897h;

    /* renamed from: i */
    public boolean f898i;

    /* renamed from: j */
    private View f899j;

    /* renamed from: k */
    private LinearLayout f900k;

    /* renamed from: l */
    private TextView f901l;

    /* renamed from: m */
    private TextView f902m;

    /* renamed from: n */
    private int f903n;

    /* renamed from: o */
    private int f904o;

    /* renamed from: p */
    private int f905p;

    public ActionBarContextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        C0684zc a = C0684zc.m16192a(context, attributeSet, C0435px.f15576d, i, 0);
        C0340mj.m14694a((View) this, a.mo10723a(0));
        this.f903n = a.mo10734f(5, 0);
        this.f904o = a.mo10734f(4, 0);
        this.f15862d = a.mo10732e(3, 0);
        this.f905p = a.mo10734f(2, R.layout.abc_action_mode_close_item_material);
        a.mo10724a();
    }

    /* access modifiers changed from: protected */
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    /* renamed from: a */
    public final void mo798a(C0443qe qeVar) {
        View view = this.f897h;
        if (view == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(this.f905p, this, false);
            this.f897h = inflate;
            addView(inflate);
        } else if (view.getParent() == null) {
            addView(this.f897h);
        }
        this.f897h.findViewById(R.id.action_mode_close_button).setOnClickListener(new C0500sh(qeVar));
        C0472rg rgVar = (C0472rg) qeVar.mo9647b();
        C0512st stVar = this.f15861c;
        if (stVar != null) {
            stVar.mo10073f();
        }
        this.f15861c = new C0512st(getContext());
        this.f15861c.mo10075h();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        rgVar.mo9834a((C0486ru) this.f15861c, this.f15859a);
        C0512st stVar2 = this.f15861c;
        C0488rw rwVar = stVar2.f15695f;
        if (stVar2.f15695f == null) {
            stVar2.f15695f = (C0488rw) stVar2.f15693d.inflate(R.layout.abc_action_menu_layout, this, false);
            stVar2.f15695f.mo774a(stVar2.f15692c);
            stVar2.mo9791b();
        }
        C0488rw rwVar2 = stVar2.f15695f;
        if (rwVar != rwVar2) {
            ((ActionMenuView) rwVar2).mo848a(stVar2);
        }
        this.f15860b = (ActionMenuView) rwVar2;
        C0340mj.m14694a((View) this.f15860b, (Drawable) null);
        addView(this.f15860b, layoutParams);
    }

    /* renamed from: c */
    private final void m861c() {
        int i;
        if (this.f900k == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f900k = linearLayout;
            this.f901l = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.f902m = (TextView) this.f900k.findViewById(R.id.action_bar_subtitle);
            if (this.f903n != 0) {
                this.f901l.setTextAppearance(getContext(), this.f903n);
            }
            if (this.f904o != 0) {
                this.f902m.setTextAppearance(getContext(), this.f904o);
            }
        }
        this.f901l.setText(this.f895f);
        this.f902m.setText(this.f896g);
        boolean z = !TextUtils.isEmpty(this.f895f);
        boolean isEmpty = TextUtils.isEmpty(this.f896g);
        boolean z2 = !isEmpty;
        TextView textView = this.f902m;
        int i2 = 8;
        if (!isEmpty) {
            i = 0;
        } else {
            i = 8;
        }
        textView.setVisibility(i);
        LinearLayout linearLayout2 = this.f900k;
        if (z || z2) {
            i2 = 0;
        }
        linearLayout2.setVisibility(i2);
        if (this.f900k.getParent() == null) {
            addView(this.f900k);
        }
    }

    /* renamed from: a */
    public final void mo794a() {
        removeAllViews();
        this.f899j = null;
        this.f15860b = null;
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0512st stVar = this.f15861c;
        if (stVar != null) {
            stVar.mo10071d();
            this.f15861c.mo10076i();
        }
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.f895f);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean a = C0703zv.m16280a(this);
        int paddingRight = a ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.f897h;
        if (!(view == null || view.getVisibility() == 8)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f897h.getLayoutParams();
            int i5 = a ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = a ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int a2 = m15369a(paddingRight, i5, a);
            paddingRight = m15369a(a2 + m15371a(this.f897h, a2, paddingTop, paddingTop2, a), i6, a);
        }
        LinearLayout linearLayout = this.f900k;
        if (!(linearLayout == null || this.f899j != null || linearLayout.getVisibility() == 8)) {
            paddingRight += m15371a(this.f900k, paddingRight, paddingTop, paddingTop2, a);
        }
        View view2 = this.f899j;
        if (view2 != null) {
            m15371a(view2, paddingRight, paddingTop, paddingTop2, a);
        }
        int paddingRight2 = !a ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        ActionMenuView actionMenuView = this.f15860b;
        if (actionMenuView != null) {
            m15371a(actionMenuView, paddingRight2, paddingTop, paddingTop2, !a);
        }
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5 = 1073741824;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (View.MeasureSpec.getMode(i2) != 0) {
            int size = View.MeasureSpec.getSize(i);
            int i6 = this.f15862d;
            if (i6 <= 0) {
                i6 = View.MeasureSpec.getSize(i2);
            }
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i7 = i6 - paddingTop;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, RecyclerView.UNDEFINED_DURATION);
            View view = this.f897h;
            if (view != null) {
                int a = m15370a(view, paddingLeft, makeMeasureSpec);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f897h.getLayoutParams();
                paddingLeft = a - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            }
            ActionMenuView actionMenuView = this.f15860b;
            if (actionMenuView != null && actionMenuView.getParent() == this) {
                paddingLeft = m15370a((View) this.f15860b, paddingLeft, makeMeasureSpec);
            }
            LinearLayout linearLayout = this.f900k;
            if (linearLayout != null && this.f899j == null) {
                if (this.f898i) {
                    this.f900k.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.f900k.getMeasuredWidth();
                    int i8 = measuredWidth <= paddingLeft ? paddingLeft - measuredWidth : paddingLeft;
                    LinearLayout linearLayout2 = this.f900k;
                    if (measuredWidth > paddingLeft) {
                        i4 = 8;
                    } else {
                        i4 = 0;
                    }
                    linearLayout2.setVisibility(i4);
                    paddingLeft = i8;
                } else {
                    paddingLeft = m15370a((View) linearLayout, paddingLeft, makeMeasureSpec);
                }
            }
            View view2 = this.f899j;
            if (view2 != null) {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                if (layoutParams.width != -2) {
                    i3 = 1073741824;
                } else {
                    i3 = RecyclerView.UNDEFINED_DURATION;
                }
                if (layoutParams.width >= 0) {
                    paddingLeft = Math.min(layoutParams.width, paddingLeft);
                }
                if (layoutParams.height == -2) {
                    i5 = RecyclerView.UNDEFINED_DURATION;
                }
                if (layoutParams.height >= 0) {
                    i7 = Math.min(layoutParams.height, i7);
                }
                this.f899j.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i3), View.MeasureSpec.makeMeasureSpec(i7, i5));
            }
            if (this.f15862d <= 0) {
                int childCount = getChildCount();
                int i9 = 0;
                for (int i10 = 0; i10 < childCount; i10++) {
                    int measuredHeight = getChildAt(i10).getMeasuredHeight() + paddingTop;
                    if (measuredHeight > i9) {
                        i9 = measuredHeight;
                    }
                }
                setMeasuredDimension(size, i9);
                return;
            }
            setMeasuredDimension(size, i6);
        } else {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
    }

    /* renamed from: a */
    public final void mo795a(int i) {
        this.f15862d = i;
    }

    /* renamed from: a */
    public final void mo796a(View view) {
        LinearLayout linearLayout;
        View view2 = this.f899j;
        if (view2 != null) {
            removeView(view2);
        }
        this.f899j = view;
        if (!(view == null || (linearLayout = this.f900k) == null)) {
            removeView(linearLayout);
            this.f900k = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    /* renamed from: b */
    public final void mo801b(CharSequence charSequence) {
        this.f896g = charSequence;
        m861c();
    }

    /* renamed from: a */
    public final void mo797a(CharSequence charSequence) {
        this.f895f = charSequence;
        m861c();
    }

    /* renamed from: a */
    public final void mo799a(boolean z) {
        if (z != this.f898i) {
            requestLayout();
        }
        this.f898i = z;
    }

    /* renamed from: b */
    public final void mo800b() {
        C0512st stVar = this.f15861c;
        if (stVar != null) {
            stVar.mo10070c();
        }
    }
}
