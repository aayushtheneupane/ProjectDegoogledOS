package androidx.appcompat.p020b.p021a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;

/* renamed from: androidx.appcompat.b.a.k */
abstract class C0168k extends Drawable.ConstantState {
    boolean mAutoMirrored;
    boolean mCanConstantState;
    int mChangingConfigurations;
    boolean mCheckedConstantSize;
    boolean mCheckedConstantState;
    boolean mCheckedOpacity;
    boolean mCheckedPadding;
    boolean mCheckedStateful;
    int mChildrenChangingConfigurations;
    ColorFilter mColorFilter;
    int mConstantHeight;
    int mConstantMinimumHeight;
    int mConstantMinimumWidth;
    Rect mConstantPadding;
    boolean mConstantSize;
    int mConstantWidth;
    int mDensity = 160;
    boolean mDither;
    SparseArray mDrawableFutures;
    Drawable[] mDrawables;
    int mEnterFadeDuration;
    int mExitFadeDuration;
    boolean mHasColorFilter;
    boolean mHasTintList;
    boolean mHasTintMode;
    int mLayoutDirection;
    boolean mMutated;
    int mNumChildren;
    int mOpacity;
    final C0169l mOwner;
    Resources mSourceRes;
    boolean mStateful;
    ColorStateList mTintList;
    PorterDuff.Mode mTintMode;
    boolean mVariablePadding;

    C0168k(C0168k kVar, C0169l lVar, Resources resources) {
        Resources resources2;
        this.mVariablePadding = false;
        this.mConstantSize = false;
        this.mDither = true;
        this.mEnterFadeDuration = 0;
        this.mExitFadeDuration = 0;
        this.mOwner = lVar;
        if (resources != null) {
            resources2 = resources;
        } else {
            resources2 = kVar != null ? kVar.mSourceRes : null;
        }
        this.mSourceRes = resources2;
        this.mDensity = C0169l.resolveDensity(resources, kVar != null ? kVar.mDensity : 0);
        if (kVar != null) {
            this.mChangingConfigurations = kVar.mChangingConfigurations;
            this.mChildrenChangingConfigurations = kVar.mChildrenChangingConfigurations;
            this.mCheckedConstantState = true;
            this.mCanConstantState = true;
            this.mVariablePadding = kVar.mVariablePadding;
            this.mConstantSize = kVar.mConstantSize;
            this.mDither = kVar.mDither;
            this.mMutated = kVar.mMutated;
            this.mLayoutDirection = kVar.mLayoutDirection;
            this.mEnterFadeDuration = kVar.mEnterFadeDuration;
            this.mExitFadeDuration = kVar.mExitFadeDuration;
            this.mAutoMirrored = kVar.mAutoMirrored;
            this.mColorFilter = kVar.mColorFilter;
            this.mHasColorFilter = kVar.mHasColorFilter;
            this.mTintList = kVar.mTintList;
            this.mTintMode = kVar.mTintMode;
            this.mHasTintList = kVar.mHasTintList;
            this.mHasTintMode = kVar.mHasTintMode;
            if (kVar.mDensity == this.mDensity) {
                if (kVar.mCheckedPadding) {
                    this.mConstantPadding = new Rect(kVar.mConstantPadding);
                    this.mCheckedPadding = true;
                }
                if (kVar.mCheckedConstantSize) {
                    this.mConstantWidth = kVar.mConstantWidth;
                    this.mConstantHeight = kVar.mConstantHeight;
                    this.mConstantMinimumWidth = kVar.mConstantMinimumWidth;
                    this.mConstantMinimumHeight = kVar.mConstantMinimumHeight;
                    this.mCheckedConstantSize = true;
                }
            }
            if (kVar.mCheckedOpacity) {
                this.mOpacity = kVar.mOpacity;
                this.mCheckedOpacity = true;
            }
            if (kVar.mCheckedStateful) {
                this.mStateful = kVar.mStateful;
                this.mCheckedStateful = true;
            }
            Drawable[] drawableArr = kVar.mDrawables;
            this.mDrawables = new Drawable[drawableArr.length];
            this.mNumChildren = kVar.mNumChildren;
            SparseArray sparseArray = kVar.mDrawableFutures;
            if (sparseArray != null) {
                this.mDrawableFutures = sparseArray.clone();
            } else {
                this.mDrawableFutures = new SparseArray(this.mNumChildren);
            }
            int i = this.mNumChildren;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2] != null) {
                    Drawable.ConstantState constantState = drawableArr[i2].getConstantState();
                    if (constantState != null) {
                        this.mDrawableFutures.put(i2, constantState);
                    } else {
                        this.mDrawables[i2] = drawableArr[i2];
                    }
                }
            }
            return;
        }
        this.mDrawables = new Drawable[10];
        this.mNumChildren = 0;
    }

    /* renamed from: Hm */
    private void m146Hm() {
        SparseArray sparseArray = this.mDrawableFutures;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                int keyAt = this.mDrawableFutures.keyAt(i);
                Drawable[] drawableArr = this.mDrawables;
                Drawable newDrawable = ((Drawable.ConstantState) this.mDrawableFutures.valueAt(i)).newDrawable(this.mSourceRes);
                int i2 = Build.VERSION.SDK_INT;
                newDrawable.setLayoutDirection(this.mLayoutDirection);
                Drawable mutate = newDrawable.mutate();
                mutate.setCallback(this.mOwner);
                drawableArr[keyAt] = mutate;
            }
            this.mDrawableFutures = null;
        }
    }

    public final int addChild(Drawable drawable) {
        int i = this.mNumChildren;
        if (i >= this.mDrawables.length) {
            int i2 = i + 10;
            C0172o oVar = (C0172o) this;
            Drawable[] drawableArr = new Drawable[i2];
            System.arraycopy(oVar.mDrawables, 0, drawableArr, 0, i);
            oVar.mDrawables = drawableArr;
            int[][] iArr = new int[i2][];
            System.arraycopy(oVar.mStateSets, 0, iArr, 0, i);
            oVar.mStateSets = iArr;
        }
        drawable.mutate();
        drawable.setVisible(false, true);
        drawable.setCallback(this.mOwner);
        this.mDrawables[i] = drawable;
        this.mNumChildren++;
        this.mChildrenChangingConfigurations = drawable.getChangingConfigurations() | this.mChildrenChangingConfigurations;
        this.mCheckedOpacity = false;
        this.mCheckedStateful = false;
        this.mConstantPadding = null;
        this.mCheckedPadding = false;
        this.mCheckedConstantSize = false;
        this.mCheckedConstantState = false;
        return i;
    }

    /* access modifiers changed from: package-private */
    public final void applyTheme(Resources.Theme theme) {
        if (theme != null) {
            m146Hm();
            int i = this.mNumChildren;
            Drawable[] drawableArr = this.mDrawables;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2] != null && drawableArr[i2].canApplyTheme()) {
                    drawableArr[i2].applyTheme(theme);
                    this.mChildrenChangingConfigurations |= drawableArr[i2].getChangingConfigurations();
                }
            }
            updateDensity(theme.getResources());
        }
    }

    public boolean canApplyTheme() {
        int i = this.mNumChildren;
        Drawable[] drawableArr = this.mDrawables;
        for (int i2 = 0; i2 < i; i2++) {
            Drawable drawable = drawableArr[i2];
            if (drawable == null) {
                Drawable.ConstantState constantState = (Drawable.ConstantState) this.mDrawableFutures.get(i2);
                if (constantState != null && constantState.canApplyTheme()) {
                    return true;
                }
            } else if (drawable.canApplyTheme()) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean canConstantState() {
        if (this.mCheckedConstantState) {
            return this.mCanConstantState;
        }
        m146Hm();
        this.mCheckedConstantState = true;
        int i = this.mNumChildren;
        Drawable[] drawableArr = this.mDrawables;
        for (int i2 = 0; i2 < i; i2++) {
            if (drawableArr[i2].getConstantState() == null) {
                this.mCanConstantState = false;
                return false;
            }
        }
        this.mCanConstantState = true;
        return true;
    }

    /* access modifiers changed from: protected */
    public void computeConstantSize() {
        this.mCheckedConstantSize = true;
        m146Hm();
        int i = this.mNumChildren;
        Drawable[] drawableArr = this.mDrawables;
        this.mConstantHeight = -1;
        this.mConstantWidth = -1;
        this.mConstantMinimumHeight = 0;
        this.mConstantMinimumWidth = 0;
        for (int i2 = 0; i2 < i; i2++) {
            Drawable drawable = drawableArr[i2];
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (intrinsicWidth > this.mConstantWidth) {
                this.mConstantWidth = intrinsicWidth;
            }
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicHeight > this.mConstantHeight) {
                this.mConstantHeight = intrinsicHeight;
            }
            int minimumWidth = drawable.getMinimumWidth();
            if (minimumWidth > this.mConstantMinimumWidth) {
                this.mConstantMinimumWidth = minimumWidth;
            }
            int minimumHeight = drawable.getMinimumHeight();
            if (minimumHeight > this.mConstantMinimumHeight) {
                this.mConstantMinimumHeight = minimumHeight;
            }
        }
    }

    public int getChangingConfigurations() {
        return this.mChildrenChangingConfigurations | this.mChangingConfigurations;
    }

    public final Drawable getChild(int i) {
        int indexOfKey;
        Drawable drawable = this.mDrawables[i];
        if (drawable != null) {
            return drawable;
        }
        SparseArray sparseArray = this.mDrawableFutures;
        if (sparseArray == null || (indexOfKey = sparseArray.indexOfKey(i)) < 0) {
            return null;
        }
        Drawable newDrawable = ((Drawable.ConstantState) this.mDrawableFutures.valueAt(indexOfKey)).newDrawable(this.mSourceRes);
        int i2 = Build.VERSION.SDK_INT;
        newDrawable.setLayoutDirection(this.mLayoutDirection);
        Drawable mutate = newDrawable.mutate();
        mutate.setCallback(this.mOwner);
        this.mDrawables[i] = mutate;
        this.mDrawableFutures.removeAt(indexOfKey);
        if (this.mDrawableFutures.size() == 0) {
            this.mDrawableFutures = null;
        }
        return mutate;
    }

    public final Rect getConstantPadding() {
        if (this.mVariablePadding) {
            return null;
        }
        if (this.mConstantPadding != null || this.mCheckedPadding) {
            return this.mConstantPadding;
        }
        m146Hm();
        Rect rect = new Rect();
        int i = this.mNumChildren;
        Drawable[] drawableArr = this.mDrawables;
        Rect rect2 = null;
        for (int i2 = 0; i2 < i; i2++) {
            if (drawableArr[i2].getPadding(rect)) {
                if (rect2 == null) {
                    rect2 = new Rect(0, 0, 0, 0);
                }
                int i3 = rect.left;
                if (i3 > rect2.left) {
                    rect2.left = i3;
                }
                int i4 = rect.top;
                if (i4 > rect2.top) {
                    rect2.top = i4;
                }
                int i5 = rect.right;
                if (i5 > rect2.right) {
                    rect2.right = i5;
                }
                int i6 = rect.bottom;
                if (i6 > rect2.bottom) {
                    rect2.bottom = i6;
                }
            }
        }
        this.mCheckedPadding = true;
        this.mConstantPadding = rect2;
        return rect2;
    }

    public final int getOpacity() {
        if (this.mCheckedOpacity) {
            return this.mOpacity;
        }
        m146Hm();
        int i = this.mNumChildren;
        Drawable[] drawableArr = this.mDrawables;
        int opacity = i > 0 ? drawableArr[0].getOpacity() : -2;
        for (int i2 = 1; i2 < i; i2++) {
            opacity = Drawable.resolveOpacity(opacity, drawableArr[i2].getOpacity());
        }
        this.mOpacity = opacity;
        this.mCheckedOpacity = true;
        return opacity;
    }

    /* access modifiers changed from: package-private */
    public abstract void mutate();

    /* access modifiers changed from: package-private */
    public final void updateDensity(Resources resources) {
        if (resources != null) {
            this.mSourceRes = resources;
            int resolveDensity = C0169l.resolveDensity(resources, this.mDensity);
            int i = this.mDensity;
            this.mDensity = resolveDensity;
            if (i != resolveDensity) {
                this.mCheckedConstantSize = false;
                this.mCheckedPadding = false;
            }
        }
    }
}
