package com.android.messaging.p041ui;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: com.android.messaging.ui.r */
public abstract class C1377r implements C1068V {
    protected Parcelable mSavedState;
    /* access modifiers changed from: protected */
    public View mView;

    private void restorePendingState() {
        Parcelable parcelable;
        View view = this.mView;
        if (view != null && (view instanceof C1072Z) && (parcelable = this.mSavedState) != null) {
            ((C1072Z) view).restoreState(parcelable);
        }
    }

    private void savePendingState() {
        View view = this.mView;
        if (view != null && (view instanceof C1072Z)) {
            this.mSavedState = ((C1072Z) view).saveState();
        }
    }

    /* access modifiers changed from: protected */
    public abstract View createView(ViewGroup viewGroup);

    public View destroyView() {
        savePendingState();
        View view = this.mView;
        this.mView = null;
        return view;
    }

    public View getView(ViewGroup viewGroup) {
        if (this.mView == null) {
            this.mView = createView(viewGroup);
            restorePendingState();
        }
        return this.mView;
    }

    public void resetState() {
        this.mSavedState = null;
        View view = this.mView;
        if (view != null && (view instanceof C1072Z)) {
            ((C1072Z) view).resetState();
        }
    }

    public void restoreState(Parcelable parcelable) {
        if (parcelable != null) {
            this.mSavedState = parcelable;
            restorePendingState();
        }
    }

    public Parcelable saveState() {
        savePendingState();
        return this.mSavedState;
    }
}
