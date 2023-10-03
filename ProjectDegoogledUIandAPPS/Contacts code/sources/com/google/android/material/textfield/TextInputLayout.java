package com.google.android.material.textfield;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.DrawableUtils;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.android.contacts.ContactPhotoManager;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class TextInputLayout extends LinearLayout {
    private static final int DEF_STYLE_RES = R$style.Widget_Design_TextInputLayout;
    private ValueAnimator animator;
    private MaterialShapeDrawable boxBackground;
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private final int boxCollapsedPaddingTopPx;
    private final int boxLabelCutoutPaddingPx;
    private int boxStrokeColor;
    private final int boxStrokeWidthDefaultPx;
    private final int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    private MaterialShapeDrawable boxUnderline;
    /* access modifiers changed from: private */
    public final TextWatcher clearTextEndIconTextWatcher;
    private final OnEditTextAttachedListener clearTextOnEditTextAttachedListener;
    final CollapsingTextHelper collapsingTextHelper;
    private final ShapeAppearanceModel cornerAdjustedShapeAppearanceModel;
    boolean counterEnabled;
    private int counterMaxLength;
    private int counterOverflowTextAppearance;
    private ColorStateList counterOverflowTextColor;
    private boolean counterOverflowed;
    private int counterTextAppearance;
    private ColorStateList counterTextColor;
    private TextView counterView;
    private int defaultFilledBackgroundColor;
    private ColorStateList defaultHintTextColor;
    private final int defaultStrokeColor;
    private final int disabledColor;
    private final int disabledFilledBackgroundColor;
    EditText editText;
    private final LinkedHashSet<OnEditTextAttachedListener> editTextAttachedListeners;
    private final LinkedHashSet<OnEndIconChangedListener> endIconChangedListeners;
    private Drawable endIconDummyDrawable;
    private int endIconMode;
    private ColorStateList endIconTintList;
    private PorterDuff.Mode endIconTintMode;
    /* access modifiers changed from: private */
    public final CheckableImageButton endIconView;
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean hasEndIconTintList;
    private boolean hasEndIconTintMode;
    private boolean hasStartIconTintList;
    private boolean hasStartIconTintMode;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;
    private final int hoveredFilledBackgroundColor;
    private final int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private Drawable originalEditTextEndDrawable;
    private CharSequence originalHint;
    private final OnEndIconChangedListener passwordToggleEndIconChangedListener;
    private final OnEditTextAttachedListener passwordToggleOnEditTextAttachedListener;
    /* access modifiers changed from: private */
    public boolean restoringSavedState;
    private final ShapeAppearanceModel shapeAppearanceModel;
    private Drawable startIconDummyDrawable;
    private ColorStateList startIconTintList;
    private PorterDuff.Mode startIconTintMode;
    private final CheckableImageButton startIconView;
    private final Rect tmpBoundsRect;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    public interface OnEditTextAttachedListener {
        void onEditTextAttached();
    }

    public interface OnEndIconChangedListener {
        void onEndIconChanged(int i);
    }

    public TextInputLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.textInputStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextInputLayout(android.content.Context r17, android.util.AttributeSet r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r7 = r18
            r8 = r19
            int r1 = DEF_STYLE_RES
            r2 = r17
            android.content.Context r1 = com.google.android.material.internal.ThemeEnforcement.createThemedContext(r2, r7, r8, r1)
            r0.<init>(r1, r7, r8)
            com.google.android.material.textfield.IndicatorViewController r1 = new com.google.android.material.textfield.IndicatorViewController
            r1.<init>(r0)
            r0.indicatorViewController = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.tmpRect = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            r0.tmpBoundsRect = r1
            android.graphics.RectF r1 = new android.graphics.RectF
            r1.<init>()
            r0.tmpRectF = r1
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            r0.editTextAttachedListeners = r1
            r9 = 0
            r0.endIconMode = r9
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            r0.endIconChangedListeners = r1
            com.google.android.material.textfield.TextInputLayout$1 r1 = new com.google.android.material.textfield.TextInputLayout$1
            r1.<init>()
            r0.passwordToggleOnEditTextAttachedListener = r1
            com.google.android.material.textfield.TextInputLayout$2 r1 = new com.google.android.material.textfield.TextInputLayout$2
            r1.<init>()
            r0.passwordToggleEndIconChangedListener = r1
            com.google.android.material.textfield.TextInputLayout$3 r1 = new com.google.android.material.textfield.TextInputLayout$3
            r1.<init>()
            r0.clearTextEndIconTextWatcher = r1
            com.google.android.material.textfield.TextInputLayout$4 r1 = new com.google.android.material.textfield.TextInputLayout$4
            r1.<init>()
            r0.clearTextOnEditTextAttachedListener = r1
            com.google.android.material.internal.CollapsingTextHelper r1 = new com.google.android.material.internal.CollapsingTextHelper
            r1.<init>(r0)
            r0.collapsingTextHelper = r1
            android.content.Context r10 = r16.getContext()
            r11 = 1
            r0.setOrientation(r11)
            r0.setWillNotDraw(r9)
            r0.setAddStatesFromChildren(r11)
            android.widget.FrameLayout r1 = new android.widget.FrameLayout
            r1.<init>(r10)
            r0.inputFrame = r1
            android.widget.FrameLayout r1 = r0.inputFrame
            r1.setAddStatesFromChildren(r11)
            android.widget.FrameLayout r1 = r0.inputFrame
            r0.addView(r1)
            com.google.android.material.internal.CollapsingTextHelper r1 = r0.collapsingTextHelper
            android.animation.TimeInterpolator r2 = com.google.android.material.animation.AnimationUtils.LINEAR_INTERPOLATOR
            r1.setTextSizeInterpolator(r2)
            com.google.android.material.internal.CollapsingTextHelper r1 = r0.collapsingTextHelper
            android.animation.TimeInterpolator r2 = com.google.android.material.animation.AnimationUtils.LINEAR_INTERPOLATOR
            r1.setPositionInterpolator(r2)
            com.google.android.material.internal.CollapsingTextHelper r1 = r0.collapsingTextHelper
            r2 = 8388659(0x800033, float:1.1755015E-38)
            r1.setCollapsedTextGravity(r2)
            int[] r3 = com.google.android.material.R$styleable.TextInputLayout
            int r5 = DEF_STYLE_RES
            r1 = 5
            int[] r6 = new int[r1]
            int r1 = com.google.android.material.R$styleable.TextInputLayout_counterTextAppearance
            r6[r9] = r1
            int r1 = com.google.android.material.R$styleable.TextInputLayout_counterOverflowTextAppearance
            r6[r11] = r1
            int r1 = com.google.android.material.R$styleable.TextInputLayout_errorTextAppearance
            r12 = 2
            r6[r12] = r1
            int r1 = com.google.android.material.R$styleable.TextInputLayout_helperTextTextAppearance
            r2 = 3
            r6[r2] = r1
            int r1 = com.google.android.material.R$styleable.TextInputLayout_hintTextAppearance
            r2 = 4
            r6[r2] = r1
            r1 = r10
            r2 = r18
            r4 = r19
            androidx.appcompat.widget.TintTypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(r1, r2, r3, r4, r5, r6)
            int r2 = com.google.android.material.R$styleable.TextInputLayout_hintEnabled
            boolean r2 = r1.getBoolean(r2, r11)
            r0.hintEnabled = r2
            int r2 = com.google.android.material.R$styleable.TextInputLayout_android_hint
            java.lang.CharSequence r2 = r1.getText(r2)
            r0.setHint(r2)
            int r2 = com.google.android.material.R$styleable.TextInputLayout_hintAnimationEnabled
            boolean r2 = r1.getBoolean(r2, r11)
            r0.hintAnimationEnabled = r2
            com.google.android.material.shape.ShapeAppearanceModel r2 = new com.google.android.material.shape.ShapeAppearanceModel
            int r3 = DEF_STYLE_RES
            r2.<init>(r10, r7, r8, r3)
            r0.shapeAppearanceModel = r2
            com.google.android.material.shape.ShapeAppearanceModel r2 = new com.google.android.material.shape.ShapeAppearanceModel
            com.google.android.material.shape.ShapeAppearanceModel r3 = r0.shapeAppearanceModel
            r2.<init>(r3)
            r0.cornerAdjustedShapeAppearanceModel = r2
            android.content.res.Resources r2 = r10.getResources()
            int r3 = com.google.android.material.R$dimen.mtrl_textinput_box_label_cutout_padding
            int r2 = r2.getDimensionPixelOffset(r3)
            r0.boxLabelCutoutPaddingPx = r2
            int r2 = com.google.android.material.R$styleable.TextInputLayout_boxCollapsedPaddingTop
            int r2 = r1.getDimensionPixelOffset(r2, r9)
            r0.boxCollapsedPaddingTopPx = r2
            android.content.res.Resources r2 = r10.getResources()
            int r3 = com.google.android.material.R$dimen.mtrl_textinput_box_stroke_width_default
            int r2 = r2.getDimensionPixelSize(r3)
            r0.boxStrokeWidthDefaultPx = r2
            android.content.res.Resources r2 = r10.getResources()
            int r3 = com.google.android.material.R$dimen.mtrl_textinput_box_stroke_width_focused
            int r2 = r2.getDimensionPixelSize(r3)
            r0.boxStrokeWidthFocusedPx = r2
            int r2 = r0.boxStrokeWidthDefaultPx
            r0.boxStrokeWidthPx = r2
            int r2 = com.google.android.material.R$styleable.TextInputLayout_boxCornerRadiusTopStart
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r2 = r1.getDimension(r2, r3)
            int r4 = com.google.android.material.R$styleable.TextInputLayout_boxCornerRadiusTopEnd
            float r4 = r1.getDimension(r4, r3)
            int r5 = com.google.android.material.R$styleable.TextInputLayout_boxCornerRadiusBottomEnd
            float r5 = r1.getDimension(r5, r3)
            int r6 = com.google.android.material.R$styleable.TextInputLayout_boxCornerRadiusBottomStart
            float r3 = r1.getDimension(r6, r3)
            r6 = 0
            int r7 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r7 < 0) goto L_0x0140
            com.google.android.material.shape.ShapeAppearanceModel r7 = r0.shapeAppearanceModel
            com.google.android.material.shape.CornerTreatment r7 = r7.getTopLeftCorner()
            r7.setCornerSize(r2)
        L_0x0140:
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 < 0) goto L_0x014d
            com.google.android.material.shape.ShapeAppearanceModel r2 = r0.shapeAppearanceModel
            com.google.android.material.shape.CornerTreatment r2 = r2.getTopRightCorner()
            r2.setCornerSize(r4)
        L_0x014d:
            int r2 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r2 < 0) goto L_0x015a
            com.google.android.material.shape.ShapeAppearanceModel r2 = r0.shapeAppearanceModel
            com.google.android.material.shape.CornerTreatment r2 = r2.getBottomRightCorner()
            r2.setCornerSize(r5)
        L_0x015a:
            int r2 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r2 < 0) goto L_0x0167
            com.google.android.material.shape.ShapeAppearanceModel r2 = r0.shapeAppearanceModel
            com.google.android.material.shape.CornerTreatment r2 = r2.getBottomLeftCorner()
            r2.setCornerSize(r3)
        L_0x0167:
            r16.adjustCornerSizeForStrokeWidth()
            int r2 = com.google.android.material.R$styleable.TextInputLayout_boxBackgroundColor
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r10, (androidx.appcompat.widget.TintTypedArray) r1, (int) r2)
            r3 = 16843623(0x1010367, float:2.3696E-38)
            r4 = -16842910(0xfffffffffefeff62, float:-1.6947497E38)
            r5 = -1
            if (r2 == 0) goto L_0x01b9
            int r6 = r2.getDefaultColor()
            r0.defaultFilledBackgroundColor = r6
            int r6 = r0.defaultFilledBackgroundColor
            r0.boxBackgroundColor = r6
            boolean r6 = r2.isStateful()
            if (r6 == 0) goto L_0x019e
            int[] r6 = new int[r11]
            r6[r9] = r4
            int r6 = r2.getColorForState(r6, r5)
            r0.disabledFilledBackgroundColor = r6
            int[] r6 = new int[r11]
            r6[r9] = r3
            int r2 = r2.getColorForState(r6, r5)
            r0.hoveredFilledBackgroundColor = r2
            goto L_0x01c1
        L_0x019e:
            int r2 = com.google.android.material.R$color.mtrl_filled_background_color
            android.content.res.ColorStateList r2 = androidx.appcompat.content.res.AppCompatResources.getColorStateList(r10, r2)
            int[] r6 = new int[r11]
            r6[r9] = r4
            int r6 = r2.getColorForState(r6, r5)
            r0.disabledFilledBackgroundColor = r6
            int[] r6 = new int[r11]
            r6[r9] = r3
            int r2 = r2.getColorForState(r6, r5)
            r0.hoveredFilledBackgroundColor = r2
            goto L_0x01c1
        L_0x01b9:
            r0.boxBackgroundColor = r9
            r0.defaultFilledBackgroundColor = r9
            r0.disabledFilledBackgroundColor = r9
            r0.hoveredFilledBackgroundColor = r9
        L_0x01c1:
            int r2 = com.google.android.material.R$styleable.TextInputLayout_android_textColorHint
            boolean r2 = r1.hasValue(r2)
            if (r2 == 0) goto L_0x01d3
            int r2 = com.google.android.material.R$styleable.TextInputLayout_android_textColorHint
            android.content.res.ColorStateList r2 = r1.getColorStateList(r2)
            r0.focusedTextColor = r2
            r0.defaultHintTextColor = r2
        L_0x01d3:
            int r2 = com.google.android.material.R$styleable.TextInputLayout_boxStrokeColor
            android.content.res.ColorStateList r2 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r10, (androidx.appcompat.widget.TintTypedArray) r1, (int) r2)
            if (r2 == 0) goto L_0x0209
            boolean r6 = r2.isStateful()
            if (r6 == 0) goto L_0x0209
            int r6 = r2.getDefaultColor()
            r0.defaultStrokeColor = r6
            int[] r6 = new int[r11]
            r6[r9] = r4
            int r4 = r2.getColorForState(r6, r5)
            r0.disabledColor = r4
            int[] r4 = new int[r11]
            r4[r9] = r3
            int r3 = r2.getColorForState(r4, r5)
            r0.hoveredStrokeColor = r3
            int[] r3 = new int[r11]
            r4 = 16842908(0x101009c, float:2.3693995E-38)
            r3[r9] = r4
            int r2 = r2.getColorForState(r3, r5)
            r0.focusedStrokeColor = r2
            goto L_0x0229
        L_0x0209:
            int r2 = com.google.android.material.R$styleable.TextInputLayout_boxStrokeColor
            int r2 = r1.getColor(r2, r9)
            r0.focusedStrokeColor = r2
            int r2 = com.google.android.material.R$color.mtrl_textinput_default_box_stroke_color
            int r2 = androidx.core.content.ContextCompat.getColor(r10, r2)
            r0.defaultStrokeColor = r2
            int r2 = com.google.android.material.R$color.mtrl_textinput_disabled_color
            int r2 = androidx.core.content.ContextCompat.getColor(r10, r2)
            r0.disabledColor = r2
            int r2 = com.google.android.material.R$color.mtrl_textinput_hovered_box_stroke_color
            int r2 = androidx.core.content.ContextCompat.getColor(r10, r2)
            r0.hoveredStrokeColor = r2
        L_0x0229:
            int r2 = com.google.android.material.R$styleable.TextInputLayout_hintTextAppearance
            int r2 = r1.getResourceId(r2, r5)
            if (r2 == r5) goto L_0x023a
            int r2 = com.google.android.material.R$styleable.TextInputLayout_hintTextAppearance
            int r2 = r1.getResourceId(r2, r9)
            r0.setHintTextAppearance(r2)
        L_0x023a:
            int r2 = com.google.android.material.R$styleable.TextInputLayout_errorTextAppearance
            int r2 = r1.getResourceId(r2, r9)
            int r3 = com.google.android.material.R$styleable.TextInputLayout_errorEnabled
            boolean r3 = r1.getBoolean(r3, r9)
            int r4 = com.google.android.material.R$styleable.TextInputLayout_helperTextTextAppearance
            int r4 = r1.getResourceId(r4, r9)
            int r6 = com.google.android.material.R$styleable.TextInputLayout_helperTextEnabled
            boolean r6 = r1.getBoolean(r6, r9)
            int r7 = com.google.android.material.R$styleable.TextInputLayout_helperText
            java.lang.CharSequence r7 = r1.getText(r7)
            int r8 = com.google.android.material.R$styleable.TextInputLayout_counterEnabled
            boolean r8 = r1.getBoolean(r8, r9)
            int r13 = com.google.android.material.R$styleable.TextInputLayout_counterMaxLength
            int r13 = r1.getInt(r13, r5)
            r0.setCounterMaxLength(r13)
            int r13 = com.google.android.material.R$styleable.TextInputLayout_counterTextAppearance
            int r13 = r1.getResourceId(r13, r9)
            r0.counterTextAppearance = r13
            int r13 = com.google.android.material.R$styleable.TextInputLayout_counterOverflowTextAppearance
            int r13 = r1.getResourceId(r13, r9)
            r0.counterOverflowTextAppearance = r13
            android.content.Context r13 = r16.getContext()
            android.view.LayoutInflater r13 = android.view.LayoutInflater.from(r13)
            int r14 = com.google.android.material.R$layout.design_text_input_start_icon
            android.widget.FrameLayout r15 = r0.inputFrame
            android.view.View r13 = r13.inflate(r14, r15, r9)
            com.google.android.material.internal.CheckableImageButton r13 = (com.google.android.material.internal.CheckableImageButton) r13
            r0.startIconView = r13
            android.widget.FrameLayout r13 = r0.inputFrame
            com.google.android.material.internal.CheckableImageButton r14 = r0.startIconView
            r13.addView(r14)
            com.google.android.material.internal.CheckableImageButton r13 = r0.startIconView
            r14 = 8
            r13.setVisibility(r14)
            r13 = 0
            r0.setStartIconOnClickListener(r13)
            int r15 = com.google.android.material.R$styleable.TextInputLayout_startIconDrawable
            boolean r15 = r1.hasValue(r15)
            if (r15 == 0) goto L_0x02bf
            int r15 = com.google.android.material.R$styleable.TextInputLayout_startIconDrawable
            android.graphics.drawable.Drawable r15 = r1.getDrawable(r15)
            r0.setStartIconDrawable(r15)
            int r15 = com.google.android.material.R$styleable.TextInputLayout_startIconContentDescription
            boolean r15 = r1.hasValue(r15)
            if (r15 == 0) goto L_0x02bf
            int r15 = com.google.android.material.R$styleable.TextInputLayout_startIconContentDescription
            java.lang.CharSequence r15 = r1.getText(r15)
            r0.setStartIconContentDescription(r15)
        L_0x02bf:
            int r15 = com.google.android.material.R$styleable.TextInputLayout_startIconTint
            boolean r15 = r1.hasValue(r15)
            if (r15 == 0) goto L_0x02d0
            int r15 = com.google.android.material.R$styleable.TextInputLayout_startIconTint
            android.content.res.ColorStateList r15 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r10, (androidx.appcompat.widget.TintTypedArray) r1, (int) r15)
            r0.setStartIconTintList(r15)
        L_0x02d0:
            int r15 = com.google.android.material.R$styleable.TextInputLayout_startIconTintMode
            boolean r15 = r1.hasValue(r15)
            if (r15 == 0) goto L_0x02e5
            int r15 = com.google.android.material.R$styleable.TextInputLayout_startIconTintMode
            int r15 = r1.getInt(r15, r5)
            android.graphics.PorterDuff$Mode r15 = com.google.android.material.internal.ViewUtils.parseTintMode(r15, r13)
            r0.setStartIconTintMode(r15)
        L_0x02e5:
            android.content.Context r15 = r16.getContext()
            android.view.LayoutInflater r15 = android.view.LayoutInflater.from(r15)
            int r12 = com.google.android.material.R$layout.design_text_input_end_icon
            android.widget.FrameLayout r13 = r0.inputFrame
            android.view.View r12 = r15.inflate(r12, r13, r9)
            com.google.android.material.internal.CheckableImageButton r12 = (com.google.android.material.internal.CheckableImageButton) r12
            r0.endIconView = r12
            android.widget.FrameLayout r12 = r0.inputFrame
            com.google.android.material.internal.CheckableImageButton r13 = r0.endIconView
            r12.addView(r13)
            com.google.android.material.internal.CheckableImageButton r12 = r0.endIconView
            r12.setVisibility(r14)
            int r12 = com.google.android.material.R$styleable.TextInputLayout_endIconMode
            boolean r12 = r1.hasValue(r12)
            if (r12 == 0) goto L_0x0339
            int r11 = com.google.android.material.R$styleable.TextInputLayout_endIconMode
            int r11 = r1.getInt(r11, r9)
            r0.setEndIconMode(r11)
            int r11 = com.google.android.material.R$styleable.TextInputLayout_endIconDrawable
            boolean r11 = r1.hasValue(r11)
            if (r11 == 0) goto L_0x0327
            int r11 = com.google.android.material.R$styleable.TextInputLayout_endIconDrawable
            android.graphics.drawable.Drawable r11 = r1.getDrawable(r11)
            r0.setEndIconDrawable(r11)
        L_0x0327:
            int r11 = com.google.android.material.R$styleable.TextInputLayout_endIconContentDescription
            boolean r11 = r1.hasValue(r11)
            if (r11 == 0) goto L_0x037d
            int r11 = com.google.android.material.R$styleable.TextInputLayout_endIconContentDescription
            java.lang.CharSequence r11 = r1.getText(r11)
            r0.setEndIconContentDescription(r11)
            goto L_0x037d
        L_0x0339:
            int r12 = com.google.android.material.R$styleable.TextInputLayout_passwordToggleEnabled
            boolean r12 = r1.hasValue(r12)
            if (r12 == 0) goto L_0x037d
            r0.setEndIconMode(r11)
            int r11 = com.google.android.material.R$styleable.TextInputLayout_passwordToggleDrawable
            android.graphics.drawable.Drawable r11 = r1.getDrawable(r11)
            r0.setEndIconDrawable(r11)
            int r11 = com.google.android.material.R$styleable.TextInputLayout_passwordToggleContentDescription
            java.lang.CharSequence r11 = r1.getText(r11)
            r0.setEndIconContentDescription(r11)
            int r11 = com.google.android.material.R$styleable.TextInputLayout_passwordToggleTint
            boolean r11 = r1.hasValue(r11)
            if (r11 == 0) goto L_0x0367
            int r11 = com.google.android.material.R$styleable.TextInputLayout_passwordToggleTint
            android.content.res.ColorStateList r11 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r10, (androidx.appcompat.widget.TintTypedArray) r1, (int) r11)
            r0.setEndIconTintList(r11)
        L_0x0367:
            int r11 = com.google.android.material.R$styleable.TextInputLayout_passwordToggleTintMode
            boolean r11 = r1.hasValue(r11)
            if (r11 == 0) goto L_0x037d
            int r11 = com.google.android.material.R$styleable.TextInputLayout_passwordToggleTintMode
            int r11 = r1.getInt(r11, r5)
            r12 = 0
            android.graphics.PorterDuff$Mode r11 = com.google.android.material.internal.ViewUtils.parseTintMode(r11, r12)
            r0.setEndIconTintMode(r11)
        L_0x037d:
            int r11 = com.google.android.material.R$styleable.TextInputLayout_passwordToggleEnabled
            boolean r11 = r1.hasValue(r11)
            if (r11 != 0) goto L_0x03ac
            int r11 = com.google.android.material.R$styleable.TextInputLayout_endIconTint
            boolean r11 = r1.hasValue(r11)
            if (r11 == 0) goto L_0x0396
            int r11 = com.google.android.material.R$styleable.TextInputLayout_endIconTint
            android.content.res.ColorStateList r10 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r10, (androidx.appcompat.widget.TintTypedArray) r1, (int) r11)
            r0.setEndIconTintList(r10)
        L_0x0396:
            int r10 = com.google.android.material.R$styleable.TextInputLayout_endIconTintMode
            boolean r10 = r1.hasValue(r10)
            if (r10 == 0) goto L_0x03ac
            int r10 = com.google.android.material.R$styleable.TextInputLayout_endIconTintMode
            int r5 = r1.getInt(r10, r5)
            r10 = 0
            android.graphics.PorterDuff$Mode r5 = com.google.android.material.internal.ViewUtils.parseTintMode(r5, r10)
            r0.setEndIconTintMode(r5)
        L_0x03ac:
            r0.setHelperTextEnabled(r6)
            r0.setHelperText(r7)
            r0.setHelperTextTextAppearance(r4)
            r0.setErrorEnabled(r3)
            r0.setErrorTextAppearance(r2)
            int r2 = r0.counterTextAppearance
            r0.setCounterTextAppearance(r2)
            int r2 = r0.counterOverflowTextAppearance
            r0.setCounterOverflowTextAppearance(r2)
            int r2 = com.google.android.material.R$styleable.TextInputLayout_errorTextColor
            boolean r2 = r1.hasValue(r2)
            if (r2 == 0) goto L_0x03d6
            int r2 = com.google.android.material.R$styleable.TextInputLayout_errorTextColor
            android.content.res.ColorStateList r2 = r1.getColorStateList(r2)
            r0.setErrorTextColor(r2)
        L_0x03d6:
            int r2 = com.google.android.material.R$styleable.TextInputLayout_helperTextTextColor
            boolean r2 = r1.hasValue(r2)
            if (r2 == 0) goto L_0x03e7
            int r2 = com.google.android.material.R$styleable.TextInputLayout_helperTextTextColor
            android.content.res.ColorStateList r2 = r1.getColorStateList(r2)
            r0.setHelperTextColor(r2)
        L_0x03e7:
            int r2 = com.google.android.material.R$styleable.TextInputLayout_hintTextColor
            boolean r2 = r1.hasValue(r2)
            if (r2 == 0) goto L_0x03f8
            int r2 = com.google.android.material.R$styleable.TextInputLayout_hintTextColor
            android.content.res.ColorStateList r2 = r1.getColorStateList(r2)
            r0.setHintTextColor(r2)
        L_0x03f8:
            int r2 = com.google.android.material.R$styleable.TextInputLayout_counterTextColor
            boolean r2 = r1.hasValue(r2)
            if (r2 == 0) goto L_0x0409
            int r2 = com.google.android.material.R$styleable.TextInputLayout_counterTextColor
            android.content.res.ColorStateList r2 = r1.getColorStateList(r2)
            r0.setCounterTextColor(r2)
        L_0x0409:
            int r2 = com.google.android.material.R$styleable.TextInputLayout_counterOverflowTextColor
            boolean r2 = r1.hasValue(r2)
            if (r2 == 0) goto L_0x041a
            int r2 = com.google.android.material.R$styleable.TextInputLayout_counterOverflowTextColor
            android.content.res.ColorStateList r2 = r1.getColorStateList(r2)
            r0.setCounterOverflowTextColor(r2)
        L_0x041a:
            r0.setCounterEnabled(r8)
            int r2 = com.google.android.material.R$styleable.TextInputLayout_boxBackgroundMode
            int r2 = r1.getInt(r2, r9)
            r0.setBoxBackgroundMode(r2)
            r1.recycle()
            r1 = 2
            androidx.core.view.ViewCompat.setImportantForAccessibility(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & -113) | 16;
            this.inputFrame.addView(view, layoutParams2);
            this.inputFrame.setLayoutParams(layoutParams);
            updateInputLayoutMargins();
            setEditText((EditText) view);
            return;
        }
        super.addView(view, i, layoutParams);
    }

    private Drawable getBoxBackground() {
        int i = this.boxBackgroundMode;
        if (i == 1 || i == 2) {
            return this.boxBackground;
        }
        throw new IllegalStateException();
    }

    public void setBoxBackgroundMode(int i) {
        if (i != this.boxBackgroundMode) {
            this.boxBackgroundMode = i;
            if (this.editText != null) {
                onApplyBoxBackgroundMode();
            }
        }
    }

    private void onApplyBoxBackgroundMode() {
        assignBoxBackgroundByMode();
        setEditTextBoxBackground();
        updateTextInputBoxState();
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
    }

    private void assignBoxBackgroundByMode() {
        int i = this.boxBackgroundMode;
        if (i == 0) {
            this.boxBackground = null;
            this.boxUnderline = null;
        } else if (i == 1) {
            this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.boxUnderline = new MaterialShapeDrawable();
        } else if (i == 2) {
            if (!this.hintEnabled || (this.boxBackground instanceof CutoutDrawable)) {
                this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
            } else {
                this.boxBackground = new CutoutDrawable(this.shapeAppearanceModel);
            }
            this.boxUnderline = null;
        } else {
            throw new IllegalArgumentException(this.boxBackgroundMode + " is illegal; only @BoxBackgroundMode constants are supported.");
        }
    }

    private void setEditTextBoxBackground() {
        if (shouldUseEditTextBackgroundForBoxBackground()) {
            ViewCompat.setBackground(this.editText, this.boxBackground);
        }
    }

    private boolean shouldUseEditTextBackgroundForBoxBackground() {
        EditText editText2 = this.editText;
        return (editText2 == null || this.boxBackground == null || editText2.getBackground() != null || this.boxBackgroundMode == 0) ? false : true;
    }

    private void adjustCornerSizeForStrokeWidth() {
        float f = this.boxBackgroundMode == 2 ? ((float) this.boxStrokeWidthPx) / 2.0f : ContactPhotoManager.OFFSET_DEFAULT;
        if (f > ContactPhotoManager.OFFSET_DEFAULT) {
            this.cornerAdjustedShapeAppearanceModel.getTopLeftCorner().setCornerSize(this.shapeAppearanceModel.getTopLeftCorner().getCornerSize() + f);
            this.cornerAdjustedShapeAppearanceModel.getTopRightCorner().setCornerSize(this.shapeAppearanceModel.getTopRightCorner().getCornerSize() + f);
            this.cornerAdjustedShapeAppearanceModel.getBottomRightCorner().setCornerSize(this.shapeAppearanceModel.getBottomRightCorner().getCornerSize() + f);
            this.cornerAdjustedShapeAppearanceModel.getBottomLeftCorner().setCornerSize(this.shapeAppearanceModel.getBottomLeftCorner().getCornerSize() + f);
            ensureCornerAdjustedShapeAppearanceModel();
        }
    }

    private void ensureCornerAdjustedShapeAppearanceModel() {
        if (this.boxBackgroundMode != 0 && (getBoxBackground() instanceof MaterialShapeDrawable)) {
            ((MaterialShapeDrawable) getBoxBackground()).setShapeAppearanceModel(this.cornerAdjustedShapeAppearanceModel);
        }
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        EditText editText2;
        if (this.originalHint == null || (editText2 = this.editText) == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        boolean z = this.isProvidingHint;
        this.isProvidingHint = false;
        CharSequence hint2 = editText2.getHint();
        this.editText.setHint(this.originalHint);
        try {
            super.dispatchProvideAutofillStructure(viewStructure, i);
        } finally {
            this.editText.setHint(hint2);
            this.isProvidingHint = z;
        }
    }

    private void setEditText(EditText editText2) {
        if (this.editText == null) {
            if (!(editText2 instanceof TextInputEditText)) {
                Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
            }
            this.editText = editText2;
            onApplyBoxBackgroundMode();
            setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
            this.collapsingTextHelper.setTypefaces(this.editText.getTypeface());
            this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
            int gravity = this.editText.getGravity();
            this.collapsingTextHelper.setCollapsedTextGravity((gravity & -113) | 48);
            this.collapsingTextHelper.setExpandedTextGravity(gravity);
            this.editText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    TextInputLayout textInputLayout = TextInputLayout.this;
                    textInputLayout.updateLabelState(!textInputLayout.restoringSavedState);
                    TextInputLayout textInputLayout2 = TextInputLayout.this;
                    if (textInputLayout2.counterEnabled) {
                        textInputLayout2.updateCounter(editable.length());
                    }
                }
            });
            if (this.defaultHintTextColor == null) {
                this.defaultHintTextColor = this.editText.getHintTextColors();
            }
            if (this.hintEnabled) {
                if (TextUtils.isEmpty(this.hint)) {
                    this.originalHint = this.editText.getHint();
                    setHint(this.originalHint);
                    this.editText.setHint((CharSequence) null);
                }
                this.isProvidingHint = true;
            }
            if (this.counterView != null) {
                updateCounter(this.editText.getText().length());
            }
            updateEditTextBackground();
            this.indicatorViewController.adjustIndicatorPadding();
            updateIconViewOnEditTextAttached(this.startIconView, R$dimen.mtrl_textinput_start_icon_padding_start, R$dimen.mtrl_textinput_start_icon_padding_end);
            updateIconViewOnEditTextAttached(this.endIconView, R$dimen.mtrl_textinput_end_icon_padding_start, R$dimen.mtrl_textinput_end_icon_padding_end);
            dispatchOnEditTextAttached();
            updateLabelState(false, true);
            return;
        }
        throw new IllegalArgumentException("We already have an EditText, can only have one");
    }

    private void updateInputLayoutMargins() {
        if (this.boxBackgroundMode != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
            int calculateLabelMarginTop = calculateLabelMarginTop();
            if (calculateLabelMarginTop != layoutParams.topMargin) {
                layoutParams.topMargin = calculateLabelMarginTop;
                this.inputFrame.requestLayout();
            }
        }
    }

    public int getBaseline() {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            return editText2.getBaseline() + getPaddingTop() + calculateLabelMarginTop();
        }
        return super.getBaseline();
    }

    /* access modifiers changed from: package-private */
    public void updateLabelState(boolean z) {
        updateLabelState(z, false);
    }

    private void updateLabelState(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        boolean isEnabled = isEnabled();
        EditText editText2 = this.editText;
        boolean z3 = true;
        boolean z4 = editText2 != null && !TextUtils.isEmpty(editText2.getText());
        EditText editText3 = this.editText;
        if (editText3 == null || !editText3.hasFocus()) {
            z3 = false;
        }
        boolean errorShouldBeShown = this.indicatorViewController.errorShouldBeShown();
        ColorStateList colorStateList2 = this.defaultHintTextColor;
        if (colorStateList2 != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList2);
            this.collapsingTextHelper.setExpandedTextColor(this.defaultHintTextColor);
        }
        if (!isEnabled) {
            this.collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(this.disabledColor));
            this.collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(this.disabledColor));
        } else if (errorShouldBeShown) {
            this.collapsingTextHelper.setCollapsedTextColor(this.indicatorViewController.getErrorViewTextColors());
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(textView.getTextColors());
        } else if (z3 && (colorStateList = this.focusedTextColor) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
        }
        if (z4 || (isEnabled() && (z3 || errorShouldBeShown))) {
            if (z2 || this.hintExpanded) {
                collapseHint(z);
            }
        } else if (z2 || !this.hintExpanded) {
            expandHint(z);
        }
    }

    public EditText getEditText() {
        return this.editText;
    }

    public void setHint(CharSequence charSequence) {
        if (this.hintEnabled) {
            setHintInternal(charSequence);
            sendAccessibilityEvent(2048);
        }
    }

    private void setHintInternal(CharSequence charSequence) {
        if (!TextUtils.equals(charSequence, this.hint)) {
            this.hint = charSequence;
            this.collapsingTextHelper.setText(charSequence);
            if (!this.hintExpanded) {
                openCutout();
            }
        }
    }

    public CharSequence getHint() {
        if (this.hintEnabled) {
            return this.hint;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isProvidingHint() {
        return this.isProvidingHint;
    }

    public void setHintTextAppearance(int i) {
        this.collapsingTextHelper.setCollapsedTextAppearance(i);
        this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
        if (this.editText != null) {
            updateLabelState(false);
            updateInputLayoutMargins();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList) {
        if (this.collapsingTextHelper.getCollapsedTextColor() != colorStateList) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
            this.focusedTextColor = colorStateList;
            if (this.editText != null) {
                updateLabelState(false);
            }
        }
    }

    public void setErrorEnabled(boolean z) {
        this.indicatorViewController.setErrorEnabled(z);
    }

    public void setErrorTextAppearance(int i) {
        this.indicatorViewController.setErrorTextAppearance(i);
    }

    public void setErrorTextColor(ColorStateList colorStateList) {
        this.indicatorViewController.setErrorViewTextColor(colorStateList);
    }

    public void setHelperTextTextAppearance(int i) {
        this.indicatorViewController.setHelperTextAppearance(i);
    }

    public void setHelperTextColor(ColorStateList colorStateList) {
        this.indicatorViewController.setHelperTextViewTextColor(colorStateList);
    }

    public void setHelperTextEnabled(boolean z) {
        this.indicatorViewController.setHelperTextEnabled(z);
    }

    public void setHelperText(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (!isHelperTextEnabled()) {
                setHelperTextEnabled(true);
            }
            this.indicatorViewController.showHelper(charSequence);
        } else if (isHelperTextEnabled()) {
            setHelperTextEnabled(false);
        }
    }

    public boolean isHelperTextEnabled() {
        return this.indicatorViewController.isHelperTextEnabled();
    }

    public void setError(CharSequence charSequence) {
        if (!this.indicatorViewController.isErrorEnabled()) {
            if (!TextUtils.isEmpty(charSequence)) {
                setErrorEnabled(true);
            } else {
                return;
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            this.indicatorViewController.showError(charSequence);
        } else {
            this.indicatorViewController.hideError();
        }
    }

    public void setCounterEnabled(boolean z) {
        if (this.counterEnabled != z) {
            if (z) {
                this.counterView = new AppCompatTextView(getContext());
                this.counterView.setId(R$id.textinput_counter);
                Typeface typeface2 = this.typeface;
                if (typeface2 != null) {
                    this.counterView.setTypeface(typeface2);
                }
                this.counterView.setMaxLines(1);
                this.indicatorViewController.addIndicator(this.counterView, 2);
                updateCounterTextAppearanceAndColor();
                updateCounter();
            } else {
                this.indicatorViewController.removeIndicator(this.counterView, 2);
                this.counterView = null;
            }
            this.counterEnabled = z;
        }
    }

    public void setCounterTextAppearance(int i) {
        if (this.counterTextAppearance != i) {
            this.counterTextAppearance = i;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList) {
        if (this.counterTextColor != colorStateList) {
            this.counterTextColor = colorStateList;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterOverflowTextAppearance(int i) {
        if (this.counterOverflowTextAppearance != i) {
            this.counterOverflowTextAppearance = i;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList) {
        if (this.counterOverflowTextColor != colorStateList) {
            this.counterOverflowTextColor = colorStateList;
            updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterMaxLength(int i) {
        if (this.counterMaxLength != i) {
            if (i > 0) {
                this.counterMaxLength = i;
            } else {
                this.counterMaxLength = -1;
            }
            if (this.counterEnabled) {
                updateCounter();
            }
        }
    }

    private void updateCounter() {
        if (this.counterView != null) {
            EditText editText2 = this.editText;
            updateCounter(editText2 == null ? 0 : editText2.getText().length());
        }
    }

    /* access modifiers changed from: package-private */
    public void updateCounter(int i) {
        boolean z = this.counterOverflowed;
        if (this.counterMaxLength == -1) {
            this.counterView.setText(String.valueOf(i));
            this.counterView.setContentDescription((CharSequence) null);
            this.counterOverflowed = false;
        } else {
            if (ViewCompat.getAccessibilityLiveRegion(this.counterView) == 1) {
                ViewCompat.setAccessibilityLiveRegion(this.counterView, 0);
            }
            this.counterOverflowed = i > this.counterMaxLength;
            updateCounterContentDescription(getContext(), this.counterView, i, this.counterMaxLength, this.counterOverflowed);
            if (z != this.counterOverflowed) {
                updateCounterTextAppearanceAndColor();
                if (this.counterOverflowed) {
                    ViewCompat.setAccessibilityLiveRegion(this.counterView, 1);
                }
            }
            this.counterView.setText(getContext().getString(R$string.character_counter_pattern, new Object[]{Integer.valueOf(i), Integer.valueOf(this.counterMaxLength)}));
        }
        if (this.editText != null && z != this.counterOverflowed) {
            updateLabelState(false);
            updateTextInputBoxState();
            updateEditTextBackground();
        }
    }

    private static void updateCounterContentDescription(Context context, TextView textView, int i, int i2, boolean z) {
        textView.setContentDescription(context.getString(z ? R$string.character_counter_overflowed_content_description : R$string.character_counter_content_description, new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    public void setEnabled(boolean z) {
        recursiveSetEnabled(this, z);
        super.setEnabled(z);
    }

    private static void recursiveSetEnabled(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) childAt, z);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public CharSequence getCounterOverflowDescription() {
        TextView textView;
        if (!this.counterEnabled || !this.counterOverflowed || (textView = this.counterView) == null) {
            return null;
        }
        return textView.getContentDescription();
    }

    private void updateCounterTextAppearanceAndColor() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.counterView;
        if (textView != null) {
            setTextAppearanceCompatWithErrorFallback(textView, this.counterOverflowed ? this.counterOverflowTextAppearance : this.counterTextAppearance);
            if (!this.counterOverflowed && (colorStateList2 = this.counterTextColor) != null) {
                this.counterView.setTextColor(colorStateList2);
            }
            if (this.counterOverflowed && (colorStateList = this.counterOverflowTextColor) != null) {
                this.counterView.setTextColor(colorStateList);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setTextAppearanceCompatWithErrorFallback(TextView textView, int i) {
        boolean z = true;
        try {
            TextViewCompat.setTextAppearance(textView, i);
            if (Build.VERSION.SDK_INT < 23 || textView.getTextColors().getDefaultColor() != -65281) {
                z = false;
            }
        } catch (Exception unused) {
        }
        if (z) {
            TextViewCompat.setTextAppearance(textView, R$style.TextAppearance_AppCompat_Caption);
            textView.setTextColor(ContextCompat.getColor(getContext(), R$color.design_error));
        }
    }

    private int calculateLabelMarginTop() {
        float collapsedTextHeight;
        if (!this.hintEnabled) {
            return 0;
        }
        int i = this.boxBackgroundMode;
        if (i == 0 || i == 1) {
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight();
        } else if (i != 2) {
            return 0;
        } else {
            collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f;
        }
        return (int) collapsedTextHeight;
    }

    private Rect calculateCollapsedTextBounds(Rect rect) {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            Rect rect2 = this.tmpBoundsRect;
            rect2.bottom = rect.bottom;
            int i = this.boxBackgroundMode;
            if (i == 1) {
                rect2.left = rect.left + editText2.getCompoundPaddingLeft();
                rect2.top = rect.top + this.boxCollapsedPaddingTopPx;
                rect2.right = rect.right - this.editText.getCompoundPaddingRight();
                return rect2;
            } else if (i != 2) {
                rect2.left = rect.left + editText2.getCompoundPaddingLeft();
                rect2.top = getPaddingTop();
                rect2.right = rect.right - this.editText.getCompoundPaddingRight();
                return rect2;
            } else {
                rect2.left = rect.left + editText2.getPaddingLeft();
                rect2.top = rect.top - calculateLabelMarginTop();
                rect2.right = rect.right - this.editText.getPaddingRight();
                return rect2;
            }
        } else {
            throw new IllegalStateException();
        }
    }

    private Rect calculateExpandedTextBounds(Rect rect) {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            Rect rect2 = this.tmpBoundsRect;
            rect2.left = rect.left + editText2.getCompoundPaddingLeft();
            rect2.top = rect.top + this.editText.getCompoundPaddingTop();
            rect2.right = rect.right - this.editText.getCompoundPaddingRight();
            rect2.bottom = rect.bottom - this.editText.getCompoundPaddingBottom();
            return rect2;
        }
        throw new IllegalStateException();
    }

    private int calculateBoxBackgroundColor() {
        return this.boxBackgroundMode == 1 ? MaterialColors.layer(MaterialColors.getColor((View) this, R$attr.colorSurface, 0), this.boxBackgroundColor) : this.boxBackgroundColor;
    }

    private void applyBoxAttributes() {
        if (this.boxBackground != null) {
            if (canDrawOutlineStroke()) {
                this.boxBackground.setStroke((float) this.boxStrokeWidthPx, this.boxStrokeColor);
            }
            this.boxBackground.setFillColor(ColorStateList.valueOf(calculateBoxBackgroundColor()));
            applyBoxUnderlineAttributes();
            invalidate();
        }
    }

    private void applyBoxUnderlineAttributes() {
        if (this.boxUnderline != null) {
            if (canDrawStroke()) {
                this.boxUnderline.setFillColor(ColorStateList.valueOf(this.boxStrokeColor));
            }
            invalidate();
        }
    }

    private boolean canDrawOutlineStroke() {
        return this.boxBackgroundMode == 2 && canDrawStroke();
    }

    private boolean canDrawStroke() {
        return this.boxStrokeWidthPx > -1 && this.boxStrokeColor != 0;
    }

    /* access modifiers changed from: package-private */
    public void updateEditTextBackground() {
        Drawable background;
        TextView textView;
        EditText editText2 = this.editText;
        if (editText2 != null && this.boxBackgroundMode == 0 && (background = editText2.getBackground()) != null) {
            if (DrawableUtils.canSafelyMutateDrawable(background)) {
                background = background.mutate();
            }
            if (this.indicatorViewController.errorShouldBeShown()) {
                background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.indicatorViewController.getErrorViewCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            } else if (!this.counterOverflowed || (textView = this.counterView) == null) {
                DrawableCompat.clearColorFilter(background);
                this.editText.refreshDrawableState();
            } else {
                background.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        CharSequence error;
        boolean isEndIconChecked;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.isEndIconChecked = parcel.readInt() != 1 ? false : true;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.error, parcel, i);
            parcel.writeInt(this.isEndIconChecked ? 1 : 0);
        }

        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.error + "}";
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.indicatorViewController.errorShouldBeShown()) {
            savedState.error = getError();
        }
        savedState.isEndIconChecked = hasEndIcon() && this.endIconView.isChecked();
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setError(savedState.error);
        if (savedState.isEndIconChecked) {
            this.endIconView.performClick();
            this.endIconView.jumpDrawablesToCurrentState();
        }
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.restoringSavedState = false;
    }

    public CharSequence getError() {
        if (this.indicatorViewController.isErrorEnabled()) {
            return this.indicatorViewController.getErrorText();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setEditTextHeightAndDummyDrawables();
    }

    private void setEditTextHeightAndDummyDrawables() {
        if (this.editText != null) {
            int max = Math.max(this.endIconView.getMeasuredHeight(), this.startIconView.getMeasuredHeight());
            if (this.editText.getMeasuredHeight() < max) {
                this.editText.setMinimumHeight(max);
                this.editText.post(new Runnable() {
                    public void run() {
                        TextInputLayout.this.editText.requestLayout();
                    }
                });
            }
            updateIconDummyDrawables();
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.startIconView.setImageDrawable(drawable);
        if (drawable != null) {
            setStartIconVisible(true);
            applyStartIconTint();
            return;
        }
        setStartIconVisible(false);
        setStartIconOnClickListener((View.OnClickListener) null);
        setStartIconContentDescription((CharSequence) null);
    }

    public Drawable getStartIconDrawable() {
        return this.startIconView.getDrawable();
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        setIconOnClickListener(this.startIconView, onClickListener);
    }

    public void setStartIconVisible(boolean z) {
        if (isStartIconVisible() != z) {
            this.startIconView.setVisibility(z ? 0 : 8);
            updateIconDummyDrawables();
        }
    }

    public boolean isStartIconVisible() {
        return this.startIconView.getVisibility() == 0;
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.startIconView.setContentDescription(charSequence);
        }
    }

    public CharSequence getStartIconContentDescription() {
        return this.startIconView.getContentDescription();
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.startIconTintList != colorStateList) {
            this.startIconTintList = colorStateList;
            this.hasStartIconTintList = true;
            applyStartIconTint();
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.startIconTintMode != mode) {
            this.startIconTintMode = mode;
            this.hasStartIconTintMode = true;
            applyStartIconTint();
        }
    }

    public void setEndIconMode(int i) {
        int i2 = this.endIconMode;
        this.endIconMode = i;
        setEndIconVisible(i != 0);
        if (i == -1) {
            setEndIconOnClickListener((View.OnClickListener) null);
        } else if (i == 1) {
            setEndIconPasswordToggleDefaults();
        } else if (i != 2) {
            setEndIconOnClickListener((View.OnClickListener) null);
            setEndIconDrawable((Drawable) null);
            setEndIconContentDescription((CharSequence) null);
        } else {
            setEndIconClearTextDefaults();
        }
        applyEndIconTint();
        dispatchOnEndIconChanged(i2);
    }

    public void setEndIconOnClickListener(View.OnClickListener onClickListener) {
        setIconOnClickListener(this.endIconView, onClickListener);
    }

    public void setEndIconVisible(boolean z) {
        if (isEndIconVisible() != z) {
            this.endIconView.setVisibility(z ? 0 : 4);
            updateIconDummyDrawables();
        }
    }

    public boolean isEndIconVisible() {
        return this.endIconView.getVisibility() == 0;
    }

    public void setEndIconDrawable(Drawable drawable) {
        this.endIconView.setImageDrawable(drawable);
    }

    public void setEndIconContentDescription(CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.endIconView.setContentDescription(charSequence);
        }
    }

    public CharSequence getEndIconContentDescription() {
        return this.endIconView.getContentDescription();
    }

    public void setEndIconTintList(ColorStateList colorStateList) {
        if (this.endIconTintList != colorStateList) {
            this.endIconTintList = colorStateList;
            this.hasEndIconTintList = true;
            applyEndIconTint();
        }
    }

    public void setEndIconTintMode(PorterDuff.Mode mode) {
        if (this.endIconTintMode != mode) {
            this.endIconTintMode = mode;
            this.hasEndIconTintMode = true;
            applyEndIconTint();
        }
    }

    public void addOnEndIconChangedListener(OnEndIconChangedListener onEndIconChangedListener) {
        this.endIconChangedListeners.add(onEndIconChangedListener);
    }

    public void addOnEditTextAttachedListener(OnEditTextAttachedListener onEditTextAttachedListener) {
        this.editTextAttachedListeners.add(onEditTextAttachedListener);
        if (this.editText != null) {
            onEditTextAttachedListener.onEditTextAttached();
        }
    }

    private void setEndIconPasswordToggleDefaults() {
        setEndIconDrawable(AppCompatResources.getDrawable(getContext(), R$drawable.design_password_eye));
        setEndIconContentDescription(getResources().getText(R$string.password_toggle_content_description));
        setEndIconOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText editText = TextInputLayout.this.editText;
                if (editText != null) {
                    int selectionEnd = editText.getSelectionEnd();
                    if (TextInputLayout.this.hasPasswordTransformation()) {
                        TextInputLayout.this.editText.setTransformationMethod((TransformationMethod) null);
                        TextInputLayout.this.endIconView.setChecked(true);
                    } else {
                        TextInputLayout.this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        TextInputLayout.this.endIconView.setChecked(false);
                    }
                    TextInputLayout.this.editText.setSelection(selectionEnd);
                }
            }
        });
        addOnEditTextAttachedListener(this.passwordToggleOnEditTextAttachedListener);
        addOnEndIconChangedListener(this.passwordToggleEndIconChangedListener);
    }

    private void setEndIconClearTextDefaults() {
        setEndIconDrawable(AppCompatResources.getDrawable(getContext(), R$drawable.mtrl_clear_text_button));
        setEndIconContentDescription(getResources().getText(R$string.clear_text_end_icon_content_description));
        setEndIconOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TextInputLayout.this.editText.setText((CharSequence) null);
            }
        });
        addOnEditTextAttachedListener(this.clearTextOnEditTextAttachedListener);
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate accessibilityDelegate) {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            ViewCompat.setAccessibilityDelegate(editText2, accessibilityDelegate);
        }
    }

    private void dispatchOnEditTextAttached() {
        Iterator it = this.editTextAttachedListeners.iterator();
        while (it.hasNext()) {
            ((OnEditTextAttachedListener) it.next()).onEditTextAttached();
        }
    }

    private boolean hasStartIcon() {
        return getStartIconDrawable() != null;
    }

    private void applyStartIconTint() {
        applyIconTint(this.startIconView, this.hasStartIconTintList, this.startIconTintList, this.hasStartIconTintMode, this.startIconTintMode);
    }

    private boolean hasEndIcon() {
        return this.endIconMode != 0;
    }

    private void dispatchOnEndIconChanged(int i) {
        Iterator it = this.endIconChangedListeners.iterator();
        while (it.hasNext()) {
            ((OnEndIconChangedListener) it.next()).onEndIconChanged(i);
        }
    }

    private void applyEndIconTint() {
        applyIconTint(this.endIconView, this.hasEndIconTintList, this.endIconTintList, this.hasEndIconTintMode, this.endIconTintMode);
    }

    private void updateIconDummyDrawables() {
        if (this.editText != null) {
            if (hasStartIcon() && isStartIconVisible()) {
                this.startIconDummyDrawable = new ColorDrawable();
                this.startIconDummyDrawable.setBounds(0, 0, this.startIconView.getMeasuredWidth() - this.startIconView.getPaddingRight(), 1);
                Drawable[] compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                TextViewCompat.setCompoundDrawablesRelative(this.editText, this.startIconDummyDrawable, compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
            } else if (this.startIconDummyDrawable != null) {
                Drawable[] compoundDrawablesRelative2 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                TextViewCompat.setCompoundDrawablesRelative(this.editText, (Drawable) null, compoundDrawablesRelative2[1], compoundDrawablesRelative2[2], compoundDrawablesRelative2[3]);
                this.startIconDummyDrawable = null;
            }
            if (hasEndIcon() && isEndIconVisible()) {
                if (this.endIconDummyDrawable == null) {
                    this.endIconDummyDrawable = new ColorDrawable();
                    this.endIconDummyDrawable.setBounds(0, 0, this.endIconView.getMeasuredWidth() - this.endIconView.getPaddingLeft(), 1);
                }
                Drawable[] compoundDrawablesRelative3 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                if (compoundDrawablesRelative3[2] != this.endIconDummyDrawable) {
                    this.originalEditTextEndDrawable = compoundDrawablesRelative3[2];
                }
                TextViewCompat.setCompoundDrawablesRelative(this.editText, compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], this.endIconDummyDrawable, compoundDrawablesRelative3[3]);
            } else if (this.endIconDummyDrawable != null) {
                Drawable[] compoundDrawablesRelative4 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
                if (compoundDrawablesRelative4[2] == this.endIconDummyDrawable) {
                    TextViewCompat.setCompoundDrawablesRelative(this.editText, compoundDrawablesRelative4[0], compoundDrawablesRelative4[1], this.originalEditTextEndDrawable, compoundDrawablesRelative4[3]);
                }
                this.endIconDummyDrawable = null;
            }
        }
    }

    private void applyIconTint(CheckableImageButton checkableImageButton, boolean z, ColorStateList colorStateList, boolean z2, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null && (z || z2)) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            if (z) {
                DrawableCompat.setTintList(drawable, colorStateList);
            }
            if (z2) {
                DrawableCompat.setTintMode(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    private void setIconOnClickListener(View view, View.OnClickListener onClickListener) {
        view.setOnClickListener(onClickListener);
        boolean z = true;
        view.setFocusable(onClickListener != null);
        if (onClickListener == null) {
            z = false;
        }
        view.setClickable(z);
    }

    private void updateIconViewOnEditTextAttached(View view, int i, int i2) {
        ViewCompat.setPaddingRelative(view, getResources().getDimensionPixelSize(i), this.editText.getPaddingTop(), getResources().getDimensionPixelSize(i2), this.editText.getPaddingBottom());
        view.bringToFront();
    }

    /* access modifiers changed from: private */
    public boolean hasPasswordTransformation() {
        EditText editText2 = this.editText;
        return editText2 != null && (editText2.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EditText editText2 = this.editText;
        if (editText2 != null) {
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, editText2, rect);
            updateBoxUnderlineBounds(rect);
            if (this.hintEnabled) {
                this.collapsingTextHelper.setCollapsedBounds(calculateCollapsedTextBounds(rect));
                this.collapsingTextHelper.setExpandedBounds(calculateExpandedTextBounds(rect));
                this.collapsingTextHelper.recalculate();
                if (cutoutEnabled() && !this.hintExpanded) {
                    openCutout();
                }
            }
        }
    }

    private void updateBoxUnderlineBounds(Rect rect) {
        MaterialShapeDrawable materialShapeDrawable = this.boxUnderline;
        if (materialShapeDrawable != null) {
            int i = rect.bottom;
            materialShapeDrawable.setBounds(rect.left, i - this.boxStrokeWidthFocusedPx, rect.right, i);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawHint(canvas);
        drawBoxUnderline(canvas);
    }

    private void drawHint(Canvas canvas) {
        if (this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas);
        }
    }

    private void drawBoxUnderline(Canvas canvas) {
        MaterialShapeDrawable materialShapeDrawable = this.boxUnderline;
        if (materialShapeDrawable != null) {
            Rect bounds = materialShapeDrawable.getBounds();
            bounds.top = bounds.bottom - this.boxStrokeWidthPx;
            this.boxUnderline.draw(canvas);
        }
    }

    private void collapseHint(boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (!z || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(1.0f);
        } else {
            animateToExpansionFraction(1.0f);
        }
        this.hintExpanded = false;
        if (cutoutEnabled()) {
            openCutout();
        }
    }

    private boolean cutoutEnabled() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable);
    }

    private void openCutout() {
        if (cutoutEnabled()) {
            RectF rectF = this.tmpRectF;
            this.collapsingTextHelper.getCollapsedTextActualBounds(rectF);
            applyCutoutPadding(rectF);
            rectF.offset((float) (-getPaddingLeft()), ContactPhotoManager.OFFSET_DEFAULT);
            ((CutoutDrawable) this.boxBackground).setCutout(rectF);
        }
    }

    private void closeCutout() {
        if (cutoutEnabled()) {
            ((CutoutDrawable) this.boxBackground).removeCutout();
        }
    }

    private void applyCutoutPadding(RectF rectF) {
        float f = rectF.left;
        int i = this.boxLabelCutoutPaddingPx;
        rectF.left = f - ((float) i);
        rectF.top -= (float) i;
        rectF.right += (float) i;
        rectF.bottom += (float) i;
    }

    /* access modifiers changed from: package-private */
    public boolean cutoutIsOpen() {
        return cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        if (!this.inDrawableStateChanged) {
            boolean z = true;
            this.inDrawableStateChanged = true;
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
            boolean state = collapsingTextHelper2 != null ? collapsingTextHelper2.setState(drawableState) | false : false;
            if (!ViewCompat.isLaidOut(this) || !isEnabled()) {
                z = false;
            }
            updateLabelState(z);
            updateEditTextBackground();
            updateTextInputBoxState();
            if (state) {
                invalidate();
            }
            this.inDrawableStateChanged = false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        r0 = r4.editText;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateTextInputBoxState() {
        /*
            r4 = this;
            com.google.android.material.shape.MaterialShapeDrawable r0 = r4.boxBackground
            if (r0 == 0) goto L_0x00a4
            int r0 = r4.boxBackgroundMode
            if (r0 != 0) goto L_0x000a
            goto L_0x00a4
        L_0x000a:
            boolean r0 = r4.isFocused()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x001f
            android.widget.EditText r0 = r4.editText
            if (r0 == 0) goto L_0x001d
            boolean r0 = r0.hasFocus()
            if (r0 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r0 = 0
            goto L_0x0020
        L_0x001f:
            r0 = 1
        L_0x0020:
            boolean r3 = r4.isHovered()
            if (r3 != 0) goto L_0x0030
            android.widget.EditText r3 = r4.editText
            if (r3 == 0) goto L_0x0031
            boolean r3 = r3.isHovered()
            if (r3 == 0) goto L_0x0031
        L_0x0030:
            r1 = 1
        L_0x0031:
            boolean r3 = r4.isEnabled()
            if (r3 != 0) goto L_0x003c
            int r3 = r4.disabledColor
            r4.boxStrokeColor = r3
            goto L_0x006e
        L_0x003c:
            com.google.android.material.textfield.IndicatorViewController r3 = r4.indicatorViewController
            boolean r3 = r3.errorShouldBeShown()
            if (r3 == 0) goto L_0x004d
            com.google.android.material.textfield.IndicatorViewController r3 = r4.indicatorViewController
            int r3 = r3.getErrorViewCurrentTextColor()
            r4.boxStrokeColor = r3
            goto L_0x006e
        L_0x004d:
            boolean r3 = r4.counterOverflowed
            if (r3 == 0) goto L_0x005c
            android.widget.TextView r3 = r4.counterView
            if (r3 == 0) goto L_0x005c
            int r3 = r3.getCurrentTextColor()
            r4.boxStrokeColor = r3
            goto L_0x006e
        L_0x005c:
            if (r0 == 0) goto L_0x0063
            int r3 = r4.focusedStrokeColor
            r4.boxStrokeColor = r3
            goto L_0x006e
        L_0x0063:
            if (r1 == 0) goto L_0x006a
            int r3 = r4.hoveredStrokeColor
            r4.boxStrokeColor = r3
            goto L_0x006e
        L_0x006a:
            int r3 = r4.defaultStrokeColor
            r4.boxStrokeColor = r3
        L_0x006e:
            if (r1 != 0) goto L_0x0072
            if (r0 == 0) goto L_0x0080
        L_0x0072:
            boolean r0 = r4.isEnabled()
            if (r0 == 0) goto L_0x0080
            int r0 = r4.boxStrokeWidthFocusedPx
            r4.boxStrokeWidthPx = r0
            r4.adjustCornerSizeForStrokeWidth()
            goto L_0x0087
        L_0x0080:
            int r0 = r4.boxStrokeWidthDefaultPx
            r4.boxStrokeWidthPx = r0
            r4.adjustCornerSizeForStrokeWidth()
        L_0x0087:
            int r0 = r4.boxBackgroundMode
            if (r0 != r2) goto L_0x00a1
            boolean r0 = r4.isEnabled()
            if (r0 != 0) goto L_0x0096
            int r0 = r4.disabledFilledBackgroundColor
            r4.boxBackgroundColor = r0
            goto L_0x00a1
        L_0x0096:
            if (r1 == 0) goto L_0x009d
            int r0 = r4.hoveredFilledBackgroundColor
            r4.boxBackgroundColor = r0
            goto L_0x00a1
        L_0x009d:
            int r0 = r4.defaultFilledBackgroundColor
            r4.boxBackgroundColor = r0
        L_0x00a1:
            r4.applyBoxAttributes()
        L_0x00a4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.updateTextInputBoxState():void");
    }

    private void expandHint(boolean z) {
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.animator.cancel();
        }
        if (!z || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(ContactPhotoManager.OFFSET_DEFAULT);
        } else {
            animateToExpansionFraction(ContactPhotoManager.OFFSET_DEFAULT);
        }
        if (cutoutEnabled() && ((CutoutDrawable) this.boxBackground).hasCutout()) {
            closeCutout();
        }
        this.hintExpanded = true;
    }

    /* access modifiers changed from: package-private */
    public void animateToExpansionFraction(float f) {
        if (this.collapsingTextHelper.getExpansionFraction() != f) {
            if (this.animator == null) {
                this.animator = new ValueAnimator();
                this.animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                this.animator.setDuration(167);
                this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        TextInputLayout.this.collapsingTextHelper.setExpansionFraction(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    }
                });
            }
            this.animator.setFloatValues(new float[]{this.collapsingTextHelper.getExpansionFraction(), f});
            this.animator.start();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean isHintExpanded() {
        return this.hintExpanded;
    }

    /* access modifiers changed from: package-private */
    public final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.helperTextIsDisplayed();
    }

    /* access modifiers changed from: package-private */
    public final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    /* access modifiers changed from: package-private */
    public final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    /* access modifiers changed from: package-private */
    public final int getErrorTextCurrentColor() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout textInputLayout) {
            this.layout = textInputLayout;
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            EditText editText = this.layout.getEditText();
            Editable text = editText != null ? editText.getText() : null;
            CharSequence hint = this.layout.getHint();
            CharSequence error = this.layout.getError();
            CharSequence counterOverflowDescription = this.layout.getCounterOverflowDescription();
            boolean z = !TextUtils.isEmpty(text);
            boolean z2 = !TextUtils.isEmpty(hint);
            boolean z3 = !TextUtils.isEmpty(error);
            boolean z4 = false;
            boolean z5 = z3 || !TextUtils.isEmpty(counterOverflowDescription);
            if (z) {
                accessibilityNodeInfoCompat.setText(text);
            } else if (z2) {
                accessibilityNodeInfoCompat.setText(hint);
            }
            if (z2) {
                accessibilityNodeInfoCompat.setHintText(hint);
                if (!z && z2) {
                    z4 = true;
                }
                accessibilityNodeInfoCompat.setShowingHintText(z4);
            }
            if (z5) {
                if (!z3) {
                    error = counterOverflowDescription;
                }
                accessibilityNodeInfoCompat.setError(error);
                accessibilityNodeInfoCompat.setContentInvalid(true);
            }
        }

        public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            EditText editText = this.layout.getEditText();
            CharSequence text = editText != null ? editText.getText() : null;
            if (TextUtils.isEmpty(text)) {
                text = this.layout.getHint();
            }
            if (!TextUtils.isEmpty(text)) {
                accessibilityEvent.getText().add(text);
            }
        }
    }
}
