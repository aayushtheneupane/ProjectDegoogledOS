package com.android.systemui.p006qs;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.havoc.header.StatusBarHeaderMachine;
import com.android.systemui.p006qs.customize.QSCustomizer;

/* renamed from: com.android.systemui.qs.QSContainerImpl */
public class QSContainerImpl extends FrameLayout implements StatusBarHeaderMachine.IStatusBarHeaderMachineObserver {
    private View mBackground;
    private View mBackgroundGradient;
    /* access modifiers changed from: private */
    public ImageView mBackgroundImage;
    /* access modifiers changed from: private */
    public Drawable mCurrentBackground;
    private boolean mForceHideQsStatusBar;
    private QuickStatusBarHeader mHeader;
    /* access modifiers changed from: private */
    public boolean mHeaderImageEnabled;
    private int mHeightOverride = -1;
    private boolean mHideQSBlackGradient;
    private boolean mLandscape;
    private QSCustomizer mQSCustomizer;
    private View mQSDetail;
    private View mQSFooter;
    private QSPanel mQSPanel;
    private boolean mQsBackgroundAlpha;
    private boolean mQsDisabled;
    private float mQsExpansion;
    private int mSideMargins;
    private final Point mSizePoint = new Point();
    private View mStatusBarBackground;
    private StatusBarHeaderMachine mStatusBarHeaderMachine;

    public boolean performClick() {
        return true;
    }

    public QSContainerImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new SettingsObserver(new Handler()).observe();
        this.mStatusBarHeaderMachine = new StatusBarHeaderMachine(context);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mQSPanel = (QSPanel) findViewById(C1777R$id.quick_settings_panel);
        this.mQSDetail = findViewById(C1777R$id.qs_detail);
        this.mHeader = (QuickStatusBarHeader) findViewById(C1777R$id.header);
        this.mQSCustomizer = (QSCustomizer) findViewById(C1777R$id.qs_customize);
        this.mQSFooter = findViewById(C1777R$id.qs_footer);
        this.mBackground = findViewById(C1777R$id.quick_settings_background);
        this.mStatusBarBackground = findViewById(C1777R$id.quick_settings_status_bar_background);
        this.mBackgroundGradient = findViewById(C1777R$id.quick_settings_gradient_view);
        this.mSideMargins = getResources().getDimensionPixelSize(C1775R$dimen.notification_side_paddings);
        this.mBackgroundImage = (ImageView) findViewById(C1777R$id.qs_header_image_view);
        this.mBackgroundImage.setClipToOutline(true);
        this.mForceHideQsStatusBar = this.mContext.getResources().getBoolean(C1773R$bool.qs_status_bar_hidden);
        setImportantForAccessibility(2);
        setMargins();
        updateSettings();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mStatusBarHeaderMachine.addObserver(this);
        this.mStatusBarHeaderMachine.updateEnablement();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mStatusBarHeaderMachine.removeObserver(this);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setBackgroundGradientVisibility(configuration);
        this.mLandscape = configuration.orientation == 2;
        updateResources();
        this.mSizePoint.set(0, 0);
    }

    /* renamed from: com.android.systemui.qs.QSContainerImpl$SettingsObserver */
    private class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = QSContainerImpl.this.getContext().getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_panel_bg_alpha"), false, this, -1);
            contentResolver.registerContentObserver(Settings.System.getUriFor("qs_header_background"), false, this, -1);
        }

        public void onChange(boolean z) {
            QSContainerImpl.this.updateSettings();
        }
    }

    /* access modifiers changed from: private */
    public void updateSettings() {
        ContentResolver contentResolver = getContext().getContentResolver();
        int intForUser = Settings.System.getIntForUser(contentResolver, "qs_panel_bg_alpha", 255, -2);
        this.mHideQSBlackGradient = Settings.System.getIntForUser(contentResolver, "qs_header_background", 0, -2) == 1;
        Drawable background = this.mBackground.getBackground();
        if (intForUser < 255) {
            this.mQsBackgroundAlpha = true;
            background.setAlpha(intForUser);
            this.mBackground.setBackground(background);
        } else {
            this.mQsBackgroundAlpha = false;
            background.setAlpha(255);
            this.mBackground.setBackground(background);
        }
        updateResources();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Configuration configuration = getResources().getConfiguration();
        boolean z = configuration.smallestScreenWidthDp >= 600 || configuration.orientation != 2;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mQSPanel.getLayoutParams();
        int displayHeight = ((getDisplayHeight() - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin) - getPaddingBottom();
        if (z) {
            displayHeight -= getResources().getDimensionPixelSize(C1775R$dimen.navigation_bar_height);
        }
        this.mQSPanel.measure(i, View.MeasureSpec.makeMeasureSpec(displayHeight, 1073741824));
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.mQSPanel.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + this.mQSPanel.getMeasuredHeight() + getPaddingBottom(), 1073741824));
        this.mQSCustomizer.measure(i, View.MeasureSpec.makeMeasureSpec(getDisplayHeight(), 1073741824));
    }

    /* access modifiers changed from: protected */
    public void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        if (view != this.mQSPanel) {
            super.measureChildWithMargins(view, i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        updateExpansion();
    }

    public void disable(int i, int i2, boolean z) {
        boolean z2 = true;
        int i3 = 0;
        if ((i2 & 1) == 0) {
            z2 = false;
        }
        if (z2 != this.mQsDisabled) {
            this.mQsDisabled = z2;
            setBackgroundGradientVisibility(getResources().getConfiguration());
            View view = this.mBackground;
            if (this.mQsDisabled) {
                i3 = 8;
            }
            view.setVisibility(i3);
        }
    }

    /* access modifiers changed from: private */
    public void updateResources() {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(17105395) + (this.mHeaderImageEnabled ? this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_header_image_offset) : 0);
        int dimensionPixelSize2 = this.mHeaderImageEnabled ? this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.qs_header_image_side_margin) : 0;
        int dimensionPixelSize3 = !this.mHeaderImageEnabled ? this.mContext.getResources().getDimensionPixelSize(17105395) : 0;
        ((FrameLayout.LayoutParams) this.mQSPanel.getLayoutParams()).topMargin = dimensionPixelSize;
        QSPanel qSPanel = this.mQSPanel;
        qSPanel.setLayoutParams(qSPanel.getLayoutParams());
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mStatusBarBackground.getLayoutParams();
        marginLayoutParams.height = dimensionPixelSize;
        marginLayoutParams.setMargins(dimensionPixelSize2, 0, dimensionPixelSize2, 0);
        this.mStatusBarBackground.setLayoutParams(marginLayoutParams);
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mBackgroundGradient.getLayoutParams();
        marginLayoutParams2.setMargins(0, dimensionPixelSize3, 0, 0);
        this.mBackgroundGradient.setLayoutParams(marginLayoutParams2);
        if (this.mHeaderImageEnabled) {
            this.mStatusBarBackground.setBackgroundColor(0);
        } else {
            this.mStatusBarBackground.setBackgroundColor(-16777216);
        }
        setBackgroundGradientVisibility(getResources().getConfiguration());
    }

    public void setHeightOverride(int i) {
        this.mHeightOverride = i;
        updateExpansion();
    }

    public void updateExpansion() {
        int calculateContainerHeight = calculateContainerHeight();
        setBottom(getTop() + calculateContainerHeight);
        this.mQSDetail.setBottom(getTop() + calculateContainerHeight);
        View view = this.mQSFooter;
        view.setTranslationY((float) (calculateContainerHeight - view.getHeight()));
        this.mBackground.setTop(this.mQSPanel.getTop());
        this.mBackground.setBottom(calculateContainerHeight);
    }

    /* access modifiers changed from: protected */
    public int calculateContainerHeight() {
        int i = this.mHeightOverride;
        if (i == -1) {
            i = getMeasuredHeight();
        }
        if (this.mQSCustomizer.isCustomizing()) {
            return this.mQSCustomizer.getHeight();
        }
        return this.mHeader.getHeight() + Math.round(this.mQsExpansion * ((float) (i - this.mHeader.getHeight())));
    }

    private void setBackgroundGradientVisibility(Configuration configuration) {
        int i = 0;
        boolean z = (this.mLandscape || this.mForceHideQsStatusBar || this.mHideQSBlackGradient) && !this.mHeaderImageEnabled;
        if (this.mLandscape || this.mForceHideQsStatusBar || this.mHideQSBlackGradient) {
            this.mBackgroundGradient.setVisibility(4);
        } else {
            this.mBackgroundGradient.setVisibility((this.mQsDisabled || this.mQsBackgroundAlpha) ? 4 : 0);
        }
        View view = this.mStatusBarBackground;
        if (z) {
            i = 4;
        }
        view.setVisibility(i);
    }

    public void setExpansion(float f) {
        this.mQsExpansion = f;
        updateExpansion();
    }

    private void setMargins() {
        setMargins(this.mQSDetail);
        setMargins(this.mBackground);
        setMargins(this.mQSFooter);
        this.mQSPanel.setMargins(this.mSideMargins);
        this.mHeader.setMargins(this.mSideMargins);
    }

    private void setMargins(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        int i = this.mSideMargins;
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
    }

    private int getDisplayHeight() {
        if (this.mSizePoint.y == 0) {
            getDisplay().getRealSize(this.mSizePoint);
        }
        return this.mSizePoint.y;
    }

    public void updateHeader(final Drawable drawable, final boolean z) {
        post(new Runnable() {
            public void run() {
                QSContainerImpl.this.doUpdateStatusBarCustomHeader(drawable, z);
            }
        });
    }

    public void disableHeader() {
        post(new Runnable() {
            public void run() {
                Drawable unused = QSContainerImpl.this.mCurrentBackground = null;
                QSContainerImpl.this.mBackgroundImage.setVisibility(8);
                boolean unused2 = QSContainerImpl.this.mHeaderImageEnabled = false;
                QSContainerImpl.this.updateResources();
            }
        });
    }

    public void refreshHeader() {
        post(new Runnable() {
            public void run() {
                QSContainerImpl qSContainerImpl = QSContainerImpl.this;
                qSContainerImpl.doUpdateStatusBarCustomHeader(qSContainerImpl.mCurrentBackground, true);
            }
        });
    }

    /* access modifiers changed from: private */
    public void doUpdateStatusBarCustomHeader(Drawable drawable, boolean z) {
        if (drawable != null) {
            this.mBackgroundImage.setVisibility(0);
            this.mCurrentBackground = drawable;
            setNotificationPanelHeaderBackground(drawable, z);
            this.mHeaderImageEnabled = true;
            updateResources();
            return;
        }
        this.mCurrentBackground = null;
        this.mBackgroundImage.setVisibility(8);
        this.mHeaderImageEnabled = false;
        updateResources();
    }

    private void setNotificationPanelHeaderBackground(Drawable drawable, boolean z) {
        if (this.mBackgroundImage.getDrawable() == null || z) {
            this.mBackgroundImage.setImageDrawable(drawable);
        } else {
            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{this.mBackgroundImage.getDrawable(), drawable});
            transitionDrawable.setCrossFadeEnabled(true);
            this.mBackgroundImage.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(1000);
        }
        applyHeaderBackgroundShadow();
    }

    private void applyHeaderBackgroundShadow() {
        int intForUser = Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_custom_header_shadow", 0, -2);
        Drawable drawable = this.mCurrentBackground;
        if (drawable == null) {
            return;
        }
        if (intForUser != 0) {
            this.mCurrentBackground.setColorFilter(Color.argb(intForUser, 0, 0, 0), PorterDuff.Mode.SRC_ATOP);
            return;
        }
        drawable.setColorFilter((ColorFilter) null);
    }
}
