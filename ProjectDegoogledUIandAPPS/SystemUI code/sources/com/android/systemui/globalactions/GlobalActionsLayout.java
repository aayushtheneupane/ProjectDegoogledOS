package com.android.systemui.globalactions;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.annotations.VisibleForTesting;
import com.android.systemui.C1774R$color;
import com.android.systemui.C1777R$id;
import com.android.systemui.HardwareBgDrawable;
import com.android.systemui.MultiListLayout;
import com.android.systemui.util.leak.RotationUtils;
import java.util.Locale;

public abstract class GlobalActionsLayout extends MultiListLayout {
    boolean mBackgroundsSet;

    /* access modifiers changed from: protected */
    public abstract boolean shouldReverseListItems();

    public GlobalActionsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setBackgrounds() {
        int color = getResources().getColor(C1774R$color.global_actions_grid_background, (Resources.Theme) null);
        int color2 = getResources().getColor(C1774R$color.global_actions_separated_background, (Resources.Theme) null);
        HardwareBgDrawable hardwareBgDrawable = new HardwareBgDrawable(true, true, getContext());
        HardwareBgDrawable hardwareBgDrawable2 = new HardwareBgDrawable(true, true, getContext());
        hardwareBgDrawable.setTint(color);
        hardwareBgDrawable2.setTint(color2);
        getListView().setBackground(hardwareBgDrawable);
        getSeparatedView().setBackground(hardwareBgDrawable2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getListView() != null && !this.mBackgroundsSet) {
            setBackgrounds();
            this.mBackgroundsSet = true;
        }
    }

    /* access modifiers changed from: protected */
    public void addToListView(View view, boolean z) {
        if (z) {
            getListView().addView(view, 0);
        } else {
            getListView().addView(view);
        }
    }

    /* access modifiers changed from: protected */
    public void addToSeparatedView(View view, boolean z) {
        if (z) {
            getSeparatedView().addView(view, 0);
        } else {
            getSeparatedView().addView(view);
        }
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public int getCurrentLayoutDirection() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public int getCurrentRotation() {
        return RotationUtils.getRotation(this.mContext);
    }

    public void onUpdateList() {
        View view;
        super.onUpdateList();
        ViewGroup separatedView = getSeparatedView();
        ViewGroup listView = getListView();
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            boolean shouldBeSeparated = this.mAdapter.shouldBeSeparated(i);
            if (shouldBeSeparated) {
                view = this.mAdapter.getView(i, (View) null, separatedView);
            } else {
                view = this.mAdapter.getView(i, (View) null, listView);
            }
            if (shouldBeSeparated) {
                addToSeparatedView(view, false);
            } else {
                addToListView(view, shouldReverseListItems());
            }
        }
    }

    /* access modifiers changed from: protected */
    public ViewGroup getSeparatedView() {
        return (ViewGroup) findViewById(C1777R$id.separated_button);
    }

    /* access modifiers changed from: protected */
    public ViewGroup getListView() {
        return (ViewGroup) findViewById(16908298);
    }

    /* access modifiers changed from: protected */
    public View getWrapper() {
        return getChildAt(0);
    }
}
