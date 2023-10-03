package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$animator;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final Property<View, Float> CORNER_RADIUS = new Property<View, Float>(Float.class, "cornerRadius") {
        public void set(View view, Float f) {
            ((ExtendedFloatingActionButton) view).setCornerRadius(f.intValue());
        }

        public Float get(View view) {
            return Float.valueOf((float) ((ExtendedFloatingActionButton) view).getCornerRadius());
        }
    };
    private static final Property<View, Float> HEIGHT = new Property<View, Float>(Float.class, "height") {
        public void set(View view, Float f) {
            view.getLayoutParams().height = f.intValue();
            view.requestLayout();
        }

        public Float get(View view) {
            return Float.valueOf((float) view.getLayoutParams().height);
        }
    };
    private static final Property<View, Float> WIDTH = new Property<View, Float>(Float.class, "width") {
        public void set(View view, Float f) {
            view.getLayoutParams().width = f.intValue();
            view.requestLayout();
        }

        public Float get(View view) {
            return Float.valueOf((float) view.getLayoutParams().width);
        }
    };
    /* access modifiers changed from: private */
    public int animState;
    private final CoordinatorLayout.Behavior<ExtendedFloatingActionButton> behavior;
    /* access modifiers changed from: private */
    public Animator currentCollapseExpandAnimator;
    /* access modifiers changed from: private */
    public Animator currentShowHideAnimator;
    private MotionSpec defaultExtendMotionSpec;
    private MotionSpec defaultHideMotionSpec;
    private MotionSpec defaultShowMotionSpec;
    private MotionSpec defaultShrinkMotionSpec;
    private ArrayList<Animator.AnimatorListener> extendListeners;
    private MotionSpec extendMotionSpec;
    private ArrayList<Animator.AnimatorListener> hideListeners;
    private MotionSpec hideMotionSpec;
    private boolean isExtended;
    /* access modifiers changed from: private */
    public final Rect shadowPadding;
    private ArrayList<Animator.AnimatorListener> showListeners;
    private MotionSpec showMotionSpec;
    private ArrayList<Animator.AnimatorListener> shrinkListeners;
    private MotionSpec shrinkMotionSpec;
    private int userSetVisibility;

    public static abstract class OnChangedListener {
        public abstract void onExtended(ExtendedFloatingActionButton extendedFloatingActionButton);

        public abstract void onHidden(ExtendedFloatingActionButton extendedFloatingActionButton);

        public abstract void onShown(ExtendedFloatingActionButton extendedFloatingActionButton);

        public abstract void onShrunken(ExtendedFloatingActionButton extendedFloatingActionButton);
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.extendedFloatingActionButtonStyle);
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.shadowPadding = new Rect();
        this.animState = 0;
        this.isExtended = true;
        this.behavior = new ExtendedFloatingActionButtonBehavior(context, attributeSet);
        this.userSetVisibility = getVisibility();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R$styleable.ExtendedFloatingActionButton, i, R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon, new int[0]);
        this.showMotionSpec = MotionSpec.createFromAttribute(context, obtainStyledAttributes, R$styleable.ExtendedFloatingActionButton_showMotionSpec);
        this.hideMotionSpec = MotionSpec.createFromAttribute(context, obtainStyledAttributes, R$styleable.ExtendedFloatingActionButton_hideMotionSpec);
        this.extendMotionSpec = MotionSpec.createFromAttribute(context, obtainStyledAttributes, R$styleable.ExtendedFloatingActionButton_extendMotionSpec);
        this.shrinkMotionSpec = MotionSpec.createFromAttribute(context, obtainStyledAttributes, R$styleable.ExtendedFloatingActionButton_shrinkMotionSpec);
        obtainStyledAttributes.recycle();
        setHorizontallyScrolling(true);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.isExtended = false;
            shrinkNow();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setCornerRadius(getAdjustedRadius(getMeasuredHeight()));
    }

    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.behavior;
    }

    public void setVisibility(int i) {
        internalSetVisibility(i, true);
    }

    /* access modifiers changed from: private */
    public void internalSetVisibility(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.userSetVisibility = i;
        }
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }

    /* access modifiers changed from: private */
    public void hide(final boolean z, boolean z2, final OnChangedListener onChangedListener) {
        if (!isOrWillBeHidden()) {
            Animator animator = this.currentShowHideAnimator;
            if (animator != null) {
                animator.cancel();
            }
            if (!z2 || !shouldAnimateVisibilityChange()) {
                internalSetVisibility(z ? 8 : 4, z);
                if (onChangedListener != null) {
                    onChangedListener.onHidden(this);
                    return;
                }
                return;
            }
            AnimatorSet createAnimator = createAnimator(getCurrentHideMotionSpec());
            createAnimator.addListener(new AnimatorListenerAdapter() {
                private boolean cancelled;

                public void onAnimationStart(Animator animator) {
                    ExtendedFloatingActionButton.this.internalSetVisibility(0, z);
                    int unused = ExtendedFloatingActionButton.this.animState = 1;
                    Animator unused2 = ExtendedFloatingActionButton.this.currentShowHideAnimator = animator;
                    this.cancelled = false;
                }

                public void onAnimationCancel(Animator animator) {
                    this.cancelled = true;
                }

                public void onAnimationEnd(Animator animator) {
                    int unused = ExtendedFloatingActionButton.this.animState = 0;
                    Animator unused2 = ExtendedFloatingActionButton.this.currentShowHideAnimator = null;
                    if (!this.cancelled) {
                        ExtendedFloatingActionButton.this.internalSetVisibility(z ? 8 : 4, z);
                        OnChangedListener onChangedListener = onChangedListener;
                        if (onChangedListener != null) {
                            onChangedListener.onHidden(ExtendedFloatingActionButton.this);
                        }
                    }
                }
            });
            ArrayList<Animator.AnimatorListener> arrayList = this.hideListeners;
            if (arrayList != null) {
                Iterator<Animator.AnimatorListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    createAnimator.addListener(it.next());
                }
            }
            createAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public void show(final boolean z, boolean z2, final OnChangedListener onChangedListener) {
        if (!isOrWillBeShown()) {
            Animator animator = this.currentShowHideAnimator;
            if (animator != null) {
                animator.cancel();
            }
            if (!z2 || !shouldAnimateVisibilityChange()) {
                internalSetVisibility(0, z);
                setAlpha(1.0f);
                setScaleY(1.0f);
                setScaleX(1.0f);
                if (onChangedListener != null) {
                    onChangedListener.onShown(this);
                    return;
                }
                return;
            }
            AnimatorSet createAnimator = createAnimator(getCurrentShowMotionSpec());
            createAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    ExtendedFloatingActionButton.this.internalSetVisibility(0, z);
                    int unused = ExtendedFloatingActionButton.this.animState = 2;
                    Animator unused2 = ExtendedFloatingActionButton.this.currentShowHideAnimator = animator;
                }

                public void onAnimationEnd(Animator animator) {
                    int unused = ExtendedFloatingActionButton.this.animState = 0;
                    Animator unused2 = ExtendedFloatingActionButton.this.currentShowHideAnimator = null;
                    OnChangedListener onChangedListener = onChangedListener;
                    if (onChangedListener != null) {
                        onChangedListener.onShown(ExtendedFloatingActionButton.this);
                    }
                }
            });
            ArrayList<Animator.AnimatorListener> arrayList = this.showListeners;
            if (arrayList != null) {
                Iterator<Animator.AnimatorListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    createAnimator.addListener(it.next());
                }
            }
            createAnimator.start();
        }
    }

    public void extend(OnChangedListener onChangedListener) {
        setExtended(true, true, onChangedListener);
    }

    public void shrink(OnChangedListener onChangedListener) {
        setExtended(false, true, onChangedListener);
    }

    private void setExtended(final boolean z, boolean z2, final OnChangedListener onChangedListener) {
        if (z != this.isExtended && getIcon() != null && !TextUtils.isEmpty(getText())) {
            this.isExtended = z;
            Animator animator = this.currentCollapseExpandAnimator;
            if (animator != null) {
                animator.cancel();
            }
            if (z2 && shouldAnimateVisibilityChange()) {
                measure(0, 0);
                AnimatorSet createShrinkExtendAnimator = createShrinkExtendAnimator(this.isExtended ? getCurrentExtendMotionSpec() : getCurrentShrinkMotionSpec(), !this.isExtended);
                createShrinkExtendAnimator.addListener(new AnimatorListenerAdapter() {
                    private boolean cancelled;

                    public void onAnimationStart(Animator animator) {
                        Animator unused = ExtendedFloatingActionButton.this.currentCollapseExpandAnimator = animator;
                        this.cancelled = false;
                    }

                    public void onAnimationCancel(Animator animator) {
                        this.cancelled = true;
                    }

                    public void onAnimationEnd(Animator animator) {
                        OnChangedListener onChangedListener;
                        Animator unused = ExtendedFloatingActionButton.this.currentCollapseExpandAnimator = null;
                        if (!this.cancelled && (onChangedListener = onChangedListener) != null) {
                            if (z) {
                                onChangedListener.onExtended(ExtendedFloatingActionButton.this);
                            } else {
                                onChangedListener.onShrunken(ExtendedFloatingActionButton.this);
                            }
                        }
                    }
                });
                ArrayList<Animator.AnimatorListener> arrayList = z ? this.extendListeners : this.shrinkListeners;
                if (arrayList != null) {
                    Iterator<Animator.AnimatorListener> it = arrayList.iterator();
                    while (it.hasNext()) {
                        createShrinkExtendAnimator.addListener(it.next());
                    }
                }
                createShrinkExtendAnimator.start();
            } else if (z) {
                extendNow();
                if (onChangedListener != null) {
                    onChangedListener.onExtended(this);
                }
            } else {
                shrinkNow();
                if (onChangedListener != null) {
                    onChangedListener.onShrunken(this);
                }
            }
        }
    }

    private AnimatorSet createAnimator(MotionSpec motionSpec) {
        ArrayList arrayList = new ArrayList();
        if (motionSpec.hasPropertyValues("opacity")) {
            arrayList.add(motionSpec.getAnimator("opacity", this, View.ALPHA));
        }
        if (motionSpec.hasPropertyValues("scale")) {
            arrayList.add(motionSpec.getAnimator("scale", this, View.SCALE_Y));
            arrayList.add(motionSpec.getAnimator("scale", this, View.SCALE_X));
        }
        if (motionSpec.hasPropertyValues("width")) {
            arrayList.add(motionSpec.getAnimator("width", this, WIDTH));
        }
        if (motionSpec.hasPropertyValues("height")) {
            arrayList.add(motionSpec.getAnimator("height", this, HEIGHT));
        }
        if (motionSpec.hasPropertyValues("cornerRadius")) {
            arrayList.add(motionSpec.getAnimator("cornerRadius", this, CORNER_RADIUS));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    private AnimatorSet createShrinkExtendAnimator(MotionSpec motionSpec, boolean z) {
        int paddingStart = (ViewCompat.getPaddingStart(this) * 2) + getIconSize();
        if (motionSpec.hasPropertyValues("width")) {
            PropertyValuesHolder[] propertyValues = motionSpec.getPropertyValues("width");
            if (z) {
                propertyValues[0].setFloatValues(new float[]{(float) getMeasuredWidth(), (float) paddingStart});
            } else {
                propertyValues[0].setFloatValues(new float[]{(float) getWidth(), (float) getMeasuredWidth()});
            }
            motionSpec.setPropertyValues("width", propertyValues);
        }
        if (motionSpec.hasPropertyValues("height")) {
            PropertyValuesHolder[] propertyValues2 = motionSpec.getPropertyValues("height");
            if (z) {
                propertyValues2[0].setFloatValues(new float[]{(float) getMeasuredHeight(), (float) paddingStart});
            } else {
                propertyValues2[0].setFloatValues(new float[]{(float) getHeight(), (float) getMeasuredHeight()});
            }
            motionSpec.setPropertyValues("height", propertyValues2);
        }
        if (motionSpec.hasPropertyValues("cornerRadius")) {
            PropertyValuesHolder[] propertyValues3 = motionSpec.getPropertyValues("cornerRadius");
            if (z) {
                propertyValues3[0].setFloatValues(new float[]{(float) getCornerRadius(), (float) getAdjustedRadius(paddingStart)});
            } else {
                propertyValues3[0].setFloatValues(new float[]{(float) getCornerRadius(), (float) getAdjustedRadius(getHeight())});
            }
            motionSpec.setPropertyValues("cornerRadius", propertyValues3);
        }
        return createAnimator(motionSpec);
    }

    private boolean isOrWillBeShown() {
        if (getVisibility() != 0) {
            if (this.animState == 2) {
                return true;
            }
            return false;
        } else if (this.animState != 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isOrWillBeHidden() {
        if (getVisibility() == 0) {
            if (this.animState == 1) {
                return true;
            }
            return false;
        } else if (this.animState != 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut(this) && !isInEditMode();
    }

    private void shrinkNow() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            int paddingStart = (ViewCompat.getPaddingStart(this) * 2) + getIconSize();
            layoutParams.width = paddingStart;
            layoutParams.height = paddingStart;
            requestLayout();
        }
    }

    private void extendNow() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            measure(0, 0);
            layoutParams.width = getMeasuredWidth();
            layoutParams.height = getMeasuredHeight();
            requestLayout();
        }
    }

    private MotionSpec getCurrentShowMotionSpec() {
        MotionSpec motionSpec = this.showMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultShowMotionSpec == null) {
            this.defaultShowMotionSpec = MotionSpec.createFromResource(getContext(), R$animator.mtrl_extended_fab_show_motion_spec);
        }
        MotionSpec motionSpec2 = this.defaultShowMotionSpec;
        Preconditions.checkNotNull(motionSpec2);
        return motionSpec2;
    }

    private MotionSpec getCurrentHideMotionSpec() {
        MotionSpec motionSpec = this.hideMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultHideMotionSpec == null) {
            this.defaultHideMotionSpec = MotionSpec.createFromResource(getContext(), R$animator.mtrl_extended_fab_hide_motion_spec);
        }
        MotionSpec motionSpec2 = this.defaultHideMotionSpec;
        Preconditions.checkNotNull(motionSpec2);
        return motionSpec2;
    }

    private MotionSpec getCurrentExtendMotionSpec() {
        MotionSpec motionSpec = this.extendMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultExtendMotionSpec == null) {
            this.defaultExtendMotionSpec = MotionSpec.createFromResource(getContext(), R$animator.mtrl_extended_fab_extend_motion_spec);
        }
        MotionSpec motionSpec2 = this.defaultExtendMotionSpec;
        Preconditions.checkNotNull(motionSpec2);
        return motionSpec2;
    }

    private MotionSpec getCurrentShrinkMotionSpec() {
        MotionSpec motionSpec = this.shrinkMotionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultShrinkMotionSpec == null) {
            this.defaultShrinkMotionSpec = MotionSpec.createFromResource(getContext(), R$animator.mtrl_extended_fab_shrink_motion_spec);
        }
        MotionSpec motionSpec2 = this.defaultShrinkMotionSpec;
        Preconditions.checkNotNull(motionSpec2);
        return motionSpec2;
    }

    private int getAdjustedRadius(int i) {
        return (i - 1) / 2;
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        private OnChangedListener internalAutoHideListener;
        private OnChangedListener internalAutoShrinkListener;
        private Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = obtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = obtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            obtainStyledAttributes.recycle();
        }

        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            } else if (!isBottomSheet(view)) {
                return false;
            } else {
                updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton);
                return false;
            }
        }

        private static boolean isBottomSheet(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        public void setInternalAutoHideListener(OnChangedListener onChangedListener) {
            this.internalAutoHideListener = onChangedListener;
        }

        public void setInternalAutoShrinkListener(OnChangedListener onChangedListener) {
            this.internalAutoShrinkListener = onChangedListener;
        }

        private boolean shouldUpdateVisibility(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams();
            if ((this.autoHideEnabled || this.autoShrinkEnabled) && layoutParams.getAnchorId() == view.getId() && extendedFloatingActionButton.getUserSetVisibility() == 0) {
                return true;
            }
            return false;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                shrinkOrHide(extendedFloatingActionButton);
                return true;
            }
            extendOrShow(extendedFloatingActionButton);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams()).topMargin) {
                shrinkOrHide(extendedFloatingActionButton);
                return true;
            }
            extendOrShow(extendedFloatingActionButton);
            return true;
        }

        /* access modifiers changed from: protected */
        public void shrinkOrHide(ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (this.autoShrinkEnabled) {
                extendedFloatingActionButton.shrink(this.internalAutoShrinkListener);
            } else if (this.autoHideEnabled) {
                extendedFloatingActionButton.hide(false, true, this.internalAutoHideListener);
            }
        }

        /* access modifiers changed from: protected */
        public void extendOrShow(ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (this.autoShrinkEnabled) {
                extendedFloatingActionButton.extend(this.internalAutoShrinkListener);
            } else if (this.autoHideEnabled) {
                extendedFloatingActionButton.show(false, true, this.internalAutoHideListener);
            }
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
            List<View> dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = dependencies.get(i2);
                if (!(view instanceof AppBarLayout)) {
                    if (isBottomSheet(view) && updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i);
            offsetIfNeeded(coordinatorLayout, extendedFloatingActionButton);
            return true;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            Rect access$600 = extendedFloatingActionButton.shadowPadding;
            rect.set(extendedFloatingActionButton.getLeft() + access$600.left, extendedFloatingActionButton.getTop() + access$600.top, extendedFloatingActionButton.getRight() - access$600.right, extendedFloatingActionButton.getBottom() - access$600.bottom);
            return true;
        }

        private void offsetIfNeeded(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            int i;
            Rect access$600 = extendedFloatingActionButton.shadowPadding;
            if (access$600 != null && access$600.centerX() > 0 && access$600.centerY() > 0) {
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams();
                int i2 = 0;
                if (extendedFloatingActionButton.getRight() >= coordinatorLayout.getWidth() - layoutParams.rightMargin) {
                    i = access$600.right;
                } else {
                    i = extendedFloatingActionButton.getLeft() <= layoutParams.leftMargin ? -access$600.left : 0;
                }
                if (extendedFloatingActionButton.getBottom() >= coordinatorLayout.getHeight() - layoutParams.bottomMargin) {
                    i2 = access$600.bottom;
                } else if (extendedFloatingActionButton.getTop() <= layoutParams.topMargin) {
                    i2 = -access$600.top;
                }
                if (i2 != 0) {
                    ViewCompat.offsetTopAndBottom(extendedFloatingActionButton, i2);
                }
                if (i != 0) {
                    ViewCompat.offsetLeftAndRight(extendedFloatingActionButton, i);
                }
            }
        }
    }
}
