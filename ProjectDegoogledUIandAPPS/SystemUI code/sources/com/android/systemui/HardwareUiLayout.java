package com.android.systemui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.leak.RotationUtils;

public class HardwareUiLayout extends MultiListLayout implements TunerService.Tunable {
    private Animator mAnimator;
    private boolean mCollapse;
    private View mDivision;
    private boolean mEdgeBleed;
    private int mEndPoint;
    private final ViewTreeObserver.OnComputeInternalInsetsListener mInsetsListener = new ViewTreeObserver.OnComputeInternalInsetsListener() {
        public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
            HardwareUiLayout.this.lambda$new$5$HardwareUiLayout(internalInsetsInfo);
        }
    };
    private ViewGroup mList;
    private HardwareBgDrawable mListBackground;
    private int mOldHeight;
    private boolean mRotatedBackground;
    private boolean mRoundedDivider;
    private ViewGroup mSeparatedView;
    private HardwareBgDrawable mSeparatedViewBackground;
    private boolean mSwapOrientation = true;
    private final int[] mTmp2 = new int[2];

    private void animateChild(int i, int i2) {
    }

    public HardwareUiLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotation = 0;
        updateSettings();
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
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateSettings();
        ((TunerService) Dependency.get(TunerService.class)).addTunable(this, "sysui_hwui_edge_bleed", "sysui_hwui_rounded_divider");
        getViewTreeObserver().addOnComputeInternalInsetsListener(this.mInsetsListener);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnComputeInternalInsetsListener(this.mInsetsListener);
        ((TunerService) Dependency.get(TunerService.class)).removeTunable(this);
    }

    public void onTuningChanged(String str, String str2) {
        updateSettings();
    }

    private void updateSettings() {
        int i = 0;
        this.mEdgeBleed = Settings.Secure.getInt(getContext().getContentResolver(), "sysui_hwui_edge_bleed", 0) != 0;
        this.mRoundedDivider = Settings.Secure.getInt(getContext().getContentResolver(), "sysui_hwui_rounded_divider", 0) != 0;
        if (!this.mEdgeBleed) {
            i = getEdgePadding();
        }
        updateEdgeMargin(i);
        this.mListBackground = new HardwareBgDrawable(this.mRoundedDivider, !this.mEdgeBleed, getContext());
        this.mSeparatedViewBackground = new HardwareBgDrawable(this.mRoundedDivider, true ^ this.mEdgeBleed, getContext());
        ViewGroup viewGroup = this.mList;
        if (viewGroup != null) {
            viewGroup.setBackground(this.mListBackground);
            this.mSeparatedView.setBackground(this.mSeparatedViewBackground);
            requestLayout();
        }
    }

    private void updateEdgeMargin(int i) {
        ViewGroup viewGroup = this.mList;
        if (viewGroup != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewGroup.getLayoutParams();
            int i2 = this.mRotation;
            if (i2 == 1) {
                marginLayoutParams.topMargin = i;
            } else if (i2 == 2) {
                marginLayoutParams.bottomMargin = i;
            } else {
                marginLayoutParams.rightMargin = i;
                marginLayoutParams.leftMargin = i;
            }
            this.mList.setLayoutParams(marginLayoutParams);
        }
        ViewGroup viewGroup2 = this.mSeparatedView;
        if (viewGroup2 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) viewGroup2.getLayoutParams();
            int i3 = this.mRotation;
            if (i3 == 1) {
                marginLayoutParams2.topMargin = i;
            } else if (i3 == 2) {
                marginLayoutParams2.bottomMargin = i;
            } else {
                marginLayoutParams2.rightMargin = i;
            }
            this.mSeparatedView.setLayoutParams(marginLayoutParams2);
        }
    }

    private int getEdgePadding() {
        return getContext().getResources().getDimensionPixelSize(C1775R$dimen.edge_margin);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mList == null) {
            if (getChildCount() != 0) {
                this.mList = getListView();
                this.mList.setBackground(this.mListBackground);
                this.mSeparatedView = getSeparatedView();
                this.mSeparatedView.setBackground(this.mSeparatedViewBackground);
                updateEdgeMargin(this.mEdgeBleed ? 0 : getEdgePadding());
                this.mOldHeight = this.mList.getMeasuredHeight();
                updateRotation();
            } else {
                return;
            }
        }
        int measuredHeight = this.mList.getMeasuredHeight();
        int i3 = this.mOldHeight;
        if (measuredHeight != i3) {
            animateChild(i3, measuredHeight);
        }
        post(new Runnable() {
            public final void run() {
                HardwareUiLayout.this.lambda$onMeasure$0$HardwareUiLayout();
            }
        });
        post(new Runnable() {
            public final void run() {
                HardwareUiLayout.this.lambda$onMeasure$1$HardwareUiLayout();
            }
        });
    }

    private void updateRotation() {
        int rotation = RotationUtils.getRotation(getContext());
        int i = this.mRotation;
        if (rotation != i) {
            rotate(i, rotation);
            this.mRotation = rotation;
        }
    }

    /* access modifiers changed from: protected */
    public void rotate(int i, int i2) {
        super.rotate(i, i2);
        if (i == 0 || i2 == 0) {
            if (i == 1 || i2 == 2) {
                rotateRight();
            } else {
                rotateLeft();
            }
            if (this.mAdapter.hasSeparatedItems()) {
                if (i == 2 || i2 == 2) {
                    swapLeftAndTop(this.mSeparatedView);
                } else if (i == 1) {
                    rotateRight(this.mSeparatedView);
                } else {
                    rotateLeft(this.mSeparatedView);
                }
            }
            if (i2 != 0) {
                if (this.mList instanceof LinearLayout) {
                    this.mRotatedBackground = true;
                    this.mListBackground.setRotatedBackground(true);
                    this.mSeparatedViewBackground.setRotatedBackground(true);
                    LinearLayout linearLayout = (LinearLayout) this.mList;
                    if (this.mSwapOrientation) {
                        linearLayout.setOrientation(0);
                        setOrientation(0);
                    }
                    swapDimens(this.mList);
                    swapDimens(this.mSeparatedView);
                }
            } else if (this.mList instanceof LinearLayout) {
                this.mRotatedBackground = false;
                this.mListBackground.setRotatedBackground(false);
                this.mSeparatedViewBackground.setRotatedBackground(false);
                LinearLayout linearLayout2 = (LinearLayout) this.mList;
                if (this.mSwapOrientation) {
                    linearLayout2.setOrientation(1);
                    setOrientation(1);
                }
                swapDimens(this.mList);
                swapDimens(this.mSeparatedView);
            }
        } else {
            rotate(i, 0);
            rotate(0, i2);
        }
    }

    public void onUpdateList() {
        ViewGroup viewGroup;
        super.onUpdateList();
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            if (this.mAdapter.shouldBeSeparated(i)) {
                viewGroup = getSeparatedView();
            } else {
                viewGroup = getListView();
            }
            viewGroup.addView(this.mAdapter.getView(i, (View) null, viewGroup));
        }
    }

    private void rotateRight() {
        rotateRight(this);
        rotateRight(this.mList);
        swapDimens(this);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mList.getLayoutParams();
        layoutParams.gravity = rotateGravityRight(layoutParams.gravity);
        this.mList.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mSeparatedView.getLayoutParams();
        layoutParams2.gravity = rotateGravityRight(layoutParams2.gravity);
        this.mSeparatedView.setLayoutParams(layoutParams2);
        setGravity(rotateGravityRight(getGravity()));
    }

    private void swapDimens(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int i = layoutParams.width;
        layoutParams.width = layoutParams.height;
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }

    private int rotateGravityRight(int i) {
        int absoluteGravity = Gravity.getAbsoluteGravity(i, getLayoutDirection());
        int i2 = i & 112;
        int i3 = absoluteGravity & 7;
        int i4 = i3 != 1 ? i3 != 5 ? 48 : 80 : 16;
        if (i2 != 16) {
            return i2 != 80 ? i4 | 5 : i4 | 3;
        }
        return i4 | 1;
    }

    private void rotateLeft() {
        rotateLeft(this);
        rotateLeft(this.mList);
        swapDimens(this);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mList.getLayoutParams();
        layoutParams.gravity = rotateGravityLeft(layoutParams.gravity);
        this.mList.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mSeparatedView.getLayoutParams();
        layoutParams2.gravity = rotateGravityLeft(layoutParams2.gravity);
        this.mSeparatedView.setLayoutParams(layoutParams2);
        setGravity(rotateGravityLeft(getGravity()));
    }

    private int rotateGravityLeft(int i) {
        if (i == -1) {
            i = 8388659;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i, getLayoutDirection());
        int i2 = i & 112;
        int i3 = absoluteGravity & 7;
        int i4 = i3 != 1 ? i3 != 5 ? 80 : 48 : 16;
        if (i2 != 16) {
            return i2 != 80 ? i4 | 3 : i4 | 5;
        }
        return i4 | 1;
    }

    private void rotateLeft(View view) {
        view.setPadding(view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom(), view.getPaddingLeft());
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMargins(marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin, marginLayoutParams.leftMargin);
        view.setLayoutParams(marginLayoutParams);
    }

    private void rotateRight(View view) {
        view.setPadding(view.getPaddingBottom(), view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight());
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMargins(marginLayoutParams.bottomMargin, marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin);
        view.setLayoutParams(marginLayoutParams);
    }

    private void swapLeftAndTop(View view) {
        view.setPadding(view.getPaddingTop(), view.getPaddingLeft(), view.getPaddingBottom(), view.getPaddingRight());
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMargins(marginLayoutParams.topMargin, marginLayoutParams.leftMargin, marginLayoutParams.bottomMargin, marginLayoutParams.rightMargin);
        view.setLayoutParams(marginLayoutParams);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        post(new Runnable() {
            public final void run() {
                HardwareUiLayout.this.lambda$onLayout$2$HardwareUiLayout();
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: updatePosition */
    public void lambda$onMeasure$1$HardwareUiLayout() {
        float f;
        if (this.mList != null) {
            boolean hasSeparatedItems = this.mAdapter.hasSeparatedItems();
            this.mListBackground.setRotatedBackground(hasSeparatedItems);
            this.mSeparatedViewBackground.setRotatedBackground(hasSeparatedItems);
            View view = this.mDivision;
            if (view == null || view.getVisibility() != 0) {
                setCutPoint(this.mList.getMeasuredHeight());
                return;
            }
            char c = !this.mRotatedBackground;
            this.mDivision.getLocationOnScreen(this.mTmp2);
            if (this.mRotatedBackground) {
                f = this.mDivision.getTranslationX();
            } else {
                f = this.mDivision.getTranslationY();
            }
            int[] iArr = this.mTmp2;
            int i = (int) (((float) iArr[c]) + f);
            this.mList.getLocationOnScreen(iArr);
            setCutPoint(i - this.mTmp2[c]);
        }
    }

    private void setCutPoint(int i) {
        int cutPoint = this.mListBackground.getCutPoint();
        if (cutPoint != i) {
            if (getAlpha() == 0.0f || cutPoint == 0) {
                this.mListBackground.setCutPoint(i);
                return;
            }
            Animator animator = this.mAnimator;
            if (animator != null) {
                if (this.mEndPoint != i) {
                    animator.cancel();
                } else {
                    return;
                }
            }
            this.mEndPoint = i;
            this.mAnimator = ObjectAnimator.ofInt(this.mListBackground, "cutPoint", new int[]{cutPoint, i});
            if (this.mCollapse) {
                this.mAnimator.setStartDelay(300);
                this.mCollapse = false;
            }
            this.mAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updatePaddingAndGravityIfTooTall */
    public void lambda$onMeasure$0$HardwareUiLayout() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean hasSeparatedItems = this.mAdapter.hasSeparatedItems();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mSeparatedView.getLayoutParams();
        int rotation = RotationUtils.getRotation(getContext());
        if (rotation == 1) {
            i3 = getPaddingLeft();
            i2 = this.mList.getMeasuredWidth() + this.mSeparatedView.getMeasuredWidth();
            i5 = hasSeparatedItems ? marginLayoutParams.leftMargin : 0;
            i4 = getMeasuredWidth();
            i = 49;
        } else if (rotation != 2) {
            i3 = getPaddingTop();
            i2 = this.mList.getMeasuredHeight() + this.mSeparatedView.getMeasuredHeight();
            i5 = hasSeparatedItems ? marginLayoutParams.topMargin : 0;
            i4 = getMeasuredHeight();
            i = 21;
        } else {
            i3 = getPaddingRight();
            i2 = this.mList.getMeasuredWidth() + this.mSeparatedView.getMeasuredWidth();
            i5 = hasSeparatedItems ? marginLayoutParams.leftMargin : 0;
            i4 = getMeasuredWidth();
            i = 81;
        }
        if (i3 + i2 + i5 >= i4) {
            setPadding(0, 0, 0, 0);
            setGravity(i);
        }
    }

    public ViewOutlineProvider getOutlineProvider() {
        return super.getOutlineProvider();
    }

    public /* synthetic */ void lambda$new$5$HardwareUiLayout(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        if (this.mHasOutsideTouch || this.mList == null) {
            internalInsetsInfo.setTouchableInsets(0);
            return;
        }
        internalInsetsInfo.setTouchableInsets(1);
        internalInsetsInfo.contentInsets.set(this.mList.getLeft(), this.mList.getTop(), 0, getBottom() - this.mList.getBottom());
    }

    private float getAnimationDistance() {
        return getContext().getResources().getDimension(C1775R$dimen.global_actions_panel_width) / 2.0f;
    }

    public float getAnimationOffsetX() {
        if (RotationUtils.getRotation(this.mContext) == 0) {
            return getAnimationDistance();
        }
        return 0.0f;
    }

    public float getAnimationOffsetY() {
        int rotation = RotationUtils.getRotation(getContext());
        if (rotation == 1) {
            return -getAnimationDistance();
        }
        if (rotation != 2) {
            return 0.0f;
        }
        return getAnimationDistance();
    }
}
