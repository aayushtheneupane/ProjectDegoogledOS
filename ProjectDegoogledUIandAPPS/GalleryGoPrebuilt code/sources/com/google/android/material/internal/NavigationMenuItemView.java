package com.google.android.material.internal;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class NavigationMenuItemView extends gfx implements C0487rv {

    /* renamed from: b */
    private static final int[] f5219b = {16842912};

    /* renamed from: a */
    public boolean f5220a;

    /* renamed from: c */
    private int f5221c;

    /* renamed from: d */
    private final CheckedTextView f5222d;

    /* renamed from: e */
    private FrameLayout f5223e;

    /* renamed from: i */
    private C0475rj f5224i;

    /* renamed from: j */
    private final C0315ll f5225j;

    /* renamed from: a */
    public final C0475rj mo762a() {
        return this.f5224i;
    }

    /* renamed from: b */
    public final boolean mo764b() {
        return false;
    }

    public NavigationMenuItemView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f5225j = new gfy(this);
        mo10441c(0);
        LayoutInflater.from(context).inflate(R.layout.design_navigation_menu_item, this, true);
        this.f5221c = context.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size);
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R.id.design_menu_item_text);
        this.f5222d = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        C0340mj.m14698a((View) this.f5222d, this.f5225j);
    }

    /* renamed from: a */
    public final void mo763a(C0475rj rjVar) {
        StateListDrawable stateListDrawable;
        this.f5224i = rjVar;
        setVisibility(!rjVar.isVisible() ? 8 : 0);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(R.attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(f5219b, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            C0340mj.m14694a((View) this, (Drawable) stateListDrawable);
        }
        boolean isCheckable = rjVar.isCheckable();
        refreshDrawableState();
        if (this.f5220a != isCheckable) {
            this.f5220a = isCheckable;
            this.f5225j.mo9358a((View) this.f5222d, 2048);
        }
        boolean isChecked = rjVar.isChecked();
        refreshDrawableState();
        this.f5222d.setChecked(isChecked);
        setEnabled(rjVar.isEnabled());
        this.f5222d.setText(rjVar.f15783d);
        Drawable icon = rjVar.getIcon();
        if (icon != null) {
            int i = this.f5221c;
            icon.setBounds(0, 0, i, i);
        }
        dcm.m5902a(this.f5222d, icon, (Drawable) null, (Drawable) null, (Drawable) null);
        View actionView = rjVar.getActionView();
        if (actionView != null) {
            if (this.f5223e == null) {
                this.f5223e = (FrameLayout) ((ViewStub) findViewById(R.id.design_menu_item_action_area_stub)).inflate();
            }
            this.f5223e.removeAllViews();
            this.f5223e.addView(actionView);
        }
        setContentDescription(rjVar.f15791l);
        C0637xj.m15898a((View) this, rjVar.f15792m);
        C0475rj rjVar2 = this.f5224i;
        if (rjVar2.f15783d == null && rjVar2.getIcon() == null && this.f5224i.getActionView() != null) {
            this.f5222d.setVisibility(8);
            FrameLayout frameLayout = this.f5223e;
            if (frameLayout != null) {
                C0599vz vzVar = (C0599vz) frameLayout.getLayoutParams();
                vzVar.width = -1;
                this.f5223e.setLayoutParams(vzVar);
                return;
            }
            return;
        }
        this.f5222d.setVisibility(0);
        FrameLayout frameLayout2 = this.f5223e;
        if (frameLayout2 != null) {
            C0599vz vzVar2 = (C0599vz) frameLayout2.getLayoutParams();
            vzVar2.width = -2;
            this.f5223e.setLayoutParams(vzVar2);
        }
    }

    /* access modifiers changed from: protected */
    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        C0475rj rjVar = this.f5224i;
        if (rjVar != null && rjVar.isCheckable() && this.f5224i.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, f5219b);
        }
        return onCreateDrawableState;
    }
}
