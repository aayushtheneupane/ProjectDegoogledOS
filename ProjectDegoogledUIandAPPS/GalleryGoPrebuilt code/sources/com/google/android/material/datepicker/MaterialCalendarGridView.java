package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class MaterialCalendarGridView extends GridView {
    public MaterialCalendarGridView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ggf.m10260e();
        if (ges.m10170b(getContext())) {
            setNextFocusLeftId(R.id.cancel_button);
            setNextFocusRightId(R.id.confirm_button);
        }
        C0340mj.m14698a((View) this, (C0315ll) new ger());
    }

    /* renamed from: a */
    public final gev getAdapter() {
        return (gev) super.getAdapter();
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        gev a = getAdapter();
        ged ged = a.f11137c;
        gec gec = a.f11138d;
        a.getItem(a.mo6533a());
        a.getItem(a.mo6535b());
        for (C0305lb lbVar : ged.mo6505b()) {
        }
    }

    /* access modifiers changed from: protected */
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        if (!z) {
            super.onFocusChanged(false, i, rect);
        } else if (i == 33) {
            setSelection(getAdapter().mo6535b());
        } else if (i == 130) {
            setSelection(getAdapter().mo6533a());
        } else {
            super.onFocusChanged(true, i, rect);
        }
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().mo6533a()) {
            return true;
        }
        if (i != 19) {
            return false;
        }
        setSelection(getAdapter().mo6533a());
        return true;
    }

    public final void setAdapter(ListAdapter listAdapter) {
        if (listAdapter instanceof gev) {
            super.setAdapter(listAdapter);
        } else {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", new Object[]{MaterialCalendarGridView.class.getCanonicalName(), gev.class.getCanonicalName()}));
        }
    }

    public final void setSelection(int i) {
        if (i < getAdapter().mo6533a()) {
            super.setSelection(getAdapter().mo6533a());
        } else {
            super.setSelection(i);
        }
    }
}
