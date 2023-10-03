package com.android.systemui.bubbles;

import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.ActivityView;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StatsLog;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.R$styleable;
import com.android.systemui.bubbles.BubbleExpandedView;
import com.android.systemui.recents.TriangleShape;
import com.android.systemui.statusbar.AlphaOptimizedButton;

public class BubbleExpandedView extends LinearLayout implements View.OnClickListener {
    /* access modifiers changed from: private */
    public ActivityView mActivityView;
    /* access modifiers changed from: private */
    public ActivityViewStatus mActivityViewStatus;
    private Drawable mAppIcon;
    private String mAppName;
    /* access modifiers changed from: private */
    public Bubble mBubble;
    /* access modifiers changed from: private */
    public BubbleController mBubbleController;
    /* access modifiers changed from: private */
    public PendingIntent mBubbleIntent;
    private Point mDisplaySize;
    private int mExpandedViewTouchSlop;
    private boolean mKeyboardVisible;
    private int mMinHeight;
    private boolean mNeedsNewHeight;
    private PackageManager mPm;
    private ShapeDrawable mPointerDrawable;
    private int mPointerHeight;
    private int mPointerMargin;
    private View mPointerView;
    private int mPointerWidth;
    private AlphaOptimizedButton mSettingsIcon;
    private int mSettingsIconHeight;
    private BubbleStackView mStackView;
    private ActivityView.StateCallback mStateCallback;
    /* access modifiers changed from: private */
    public int mTaskId;
    private int[] mTempLoc;
    private Rect mTempRect;
    private WindowManager mWindowManager;

    private enum ActivityViewStatus {
        INITIALIZING,
        INITIALIZED,
        ACTIVITY_STARTED,
        RELEASED
    }

    /* renamed from: com.android.systemui.bubbles.BubbleExpandedView$2 */
    static /* synthetic */ class C06852 {

        /* renamed from: $SwitchMap$com$android$systemui$bubbles$BubbleExpandedView$ActivityViewStatus */
        static final /* synthetic */ int[] f34x9ad3957a = new int[ActivityViewStatus.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.android.systemui.bubbles.BubbleExpandedView$ActivityViewStatus[] r0 = com.android.systemui.bubbles.BubbleExpandedView.ActivityViewStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f34x9ad3957a = r0
                int[] r0 = f34x9ad3957a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.android.systemui.bubbles.BubbleExpandedView$ActivityViewStatus r1 = com.android.systemui.bubbles.BubbleExpandedView.ActivityViewStatus.INITIALIZING     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f34x9ad3957a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.android.systemui.bubbles.BubbleExpandedView$ActivityViewStatus r1 = com.android.systemui.bubbles.BubbleExpandedView.ActivityViewStatus.INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f34x9ad3957a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.android.systemui.bubbles.BubbleExpandedView$ActivityViewStatus r1 = com.android.systemui.bubbles.BubbleExpandedView.ActivityViewStatus.ACTIVITY_STARTED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bubbles.BubbleExpandedView.C06852.<clinit>():void");
        }
    }

    public BubbleExpandedView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BubbleExpandedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BubbleExpandedView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BubbleExpandedView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mActivityViewStatus = ActivityViewStatus.INITIALIZING;
        this.mTaskId = -1;
        this.mTempRect = new Rect();
        this.mTempLoc = new int[2];
        this.mBubbleController = (BubbleController) Dependency.get(BubbleController.class);
        this.mStateCallback = new ActivityView.StateCallback() {
            public void onActivityViewReady(ActivityView activityView) {
                int i = C06852.f34x9ad3957a[BubbleExpandedView.this.mActivityViewStatus.ordinal()];
                if (i == 1 || i == 2) {
                    BubbleExpandedView.this.post(new Runnable(ActivityOptions.makeCustomAnimation(BubbleExpandedView.this.getContext(), 0, 0)) {
                        private final /* synthetic */ ActivityOptions f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            BubbleExpandedView.C06841.this.lambda$onActivityViewReady$0$BubbleExpandedView$1(this.f$1);
                        }
                    });
                    ActivityViewStatus unused = BubbleExpandedView.this.mActivityViewStatus = ActivityViewStatus.ACTIVITY_STARTED;
                }
            }

            public /* synthetic */ void lambda$onActivityViewReady$0$BubbleExpandedView$1(ActivityOptions activityOptions) {
                try {
                    BubbleExpandedView.this.mActivityView.startActivity(BubbleExpandedView.this.mBubbleIntent, activityOptions);
                } catch (RuntimeException e) {
                    Log.w("Bubbles", "Exception while displaying bubble: " + BubbleExpandedView.this.getBubbleKey() + ", " + e.getMessage() + "; removing bubble");
                    BubbleExpandedView.this.mBubbleController.removeBubble(BubbleExpandedView.this.mBubble.getKey(), 10);
                }
            }

            public void onActivityViewDestroyed(ActivityView activityView) {
                ActivityViewStatus unused = BubbleExpandedView.this.mActivityViewStatus = ActivityViewStatus.RELEASED;
            }

            public void onTaskCreated(int i, ComponentName componentName) {
                int unused = BubbleExpandedView.this.mTaskId = i;
            }

            public void onTaskRemovalStarted(int i) {
                if (BubbleExpandedView.this.mBubble != null) {
                    BubbleExpandedView.this.post(new Runnable() {
                        public final void run() {
                            BubbleExpandedView.C06841.this.lambda$onTaskRemovalStarted$1$BubbleExpandedView$1();
                        }
                    });
                }
            }

            public /* synthetic */ void lambda$onTaskRemovalStarted$1$BubbleExpandedView$1() {
                BubbleExpandedView.this.mBubbleController.removeBubble(BubbleExpandedView.this.mBubble.getKey(), 3);
            }
        };
        this.mPm = context.getPackageManager();
        this.mDisplaySize = new Point();
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mWindowManager.getDefaultDisplay().getRealSize(this.mDisplaySize);
        Resources resources = getResources();
        this.mMinHeight = resources.getDimensionPixelSize(C1775R$dimen.bubble_expanded_default_height);
        this.mPointerMargin = resources.getDimensionPixelSize(C1775R$dimen.bubble_pointer_margin);
        this.mExpandedViewTouchSlop = resources.getDimensionPixelSize(C1775R$dimen.bubble_expanded_view_slop);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        Resources resources = getResources();
        this.mPointerView = findViewById(C1777R$id.pointer_view);
        this.mPointerWidth = resources.getDimensionPixelSize(C1775R$dimen.bubble_pointer_width);
        this.mPointerHeight = resources.getDimensionPixelSize(C1775R$dimen.bubble_pointer_height);
        this.mPointerDrawable = new ShapeDrawable(TriangleShape.create((float) this.mPointerWidth, (float) this.mPointerHeight, true));
        this.mPointerView.setBackground(this.mPointerDrawable);
        this.mPointerView.setVisibility(4);
        this.mSettingsIconHeight = getContext().getResources().getDimensionPixelSize(C1775R$dimen.bubble_settings_size);
        this.mSettingsIcon = (AlphaOptimizedButton) findViewById(C1777R$id.settings_button);
        this.mSettingsIcon.setOnClickListener(this);
        this.mActivityView = new ActivityView(this.mContext, (AttributeSet) null, 0, true);
        setContentVisibility(false);
        addView(this.mActivityView);
        bringChildToFront(this.mActivityView);
        bringChildToFront(this.mSettingsIcon);
        applyThemeAttrs();
        setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                return BubbleExpandedView.this.lambda$onFinishInflate$0$BubbleExpandedView(view, windowInsets);
            }
        });
    }

    public /* synthetic */ WindowInsets lambda$onFinishInflate$0$BubbleExpandedView(View view, WindowInsets windowInsets) {
        this.mKeyboardVisible = windowInsets.getSystemWindowInsetBottom() - windowInsets.getStableInsetBottom() != 0;
        if (!this.mKeyboardVisible && this.mNeedsNewHeight) {
            updateHeight();
        }
        return view.onApplyWindowInsets(windowInsets);
    }

    /* access modifiers changed from: private */
    public String getBubbleKey() {
        Bubble bubble = this.mBubble;
        return bubble != null ? bubble.getKey() : "null";
    }

    /* access modifiers changed from: package-private */
    public void applyThemeAttrs() {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(R$styleable.BubbleExpandedView);
        int color = obtainStyledAttributes.getColor(R$styleable.BubbleExpandedView_android_colorBackgroundFloating, -1);
        float dimension = obtainStyledAttributes.getDimension(R$styleable.BubbleExpandedView_android_dialogCornerRadius, 0.0f);
        obtainStyledAttributes.recycle();
        this.mPointerDrawable.setTint(color);
        if (ScreenDecorationsUtils.supportsRoundedCornersOnWindows(this.mContext.getResources())) {
            this.mActivityView.setCornerRadius(dimension);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mKeyboardVisible = false;
        this.mNeedsNewHeight = false;
        ActivityView activityView = this.mActivityView;
        if (activityView != null) {
            activityView.setForwardedInsets(Insets.of(0, 0, 0, 0));
        }
    }

    /* access modifiers changed from: package-private */
    public void setContentVisibility(boolean z) {
        float f = z ? 1.0f : 0.0f;
        this.mPointerView.setAlpha(f);
        ActivityView activityView = this.mActivityView;
        if (activityView != null) {
            activityView.setAlpha(f);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateInsets(WindowInsets windowInsets) {
        if (usingActivityView()) {
            this.mActivityView.setForwardedInsets(Insets.of(0, 0, 0, Math.max((this.mActivityView.getLocationOnScreen()[1] + this.mActivityView.getHeight()) - (this.mDisplaySize.y - Math.max(windowInsets.getSystemWindowInsetBottom(), windowInsets.getDisplayCutout() != null ? windowInsets.getDisplayCutout().getSafeInsetBottom() : 0)), 0)));
        }
    }

    public void setBubble(Bubble bubble, BubbleStackView bubbleStackView, String str) {
        this.mStackView = bubbleStackView;
        this.mBubble = bubble;
        this.mAppName = str;
        try {
            this.mAppIcon = this.mPm.getApplicationIcon(this.mPm.getApplicationInfo(bubble.getPackageName(), 795136));
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (this.mAppIcon == null) {
            this.mAppIcon = this.mPm.getDefaultActivityIcon();
        }
        applyThemeAttrs();
        showSettingsIcon();
        updateExpandedView();
    }

    public void populateExpandedView() {
        if (usingActivityView()) {
            this.mActivityView.setCallback(this.mStateCallback);
        } else {
            Log.e("Bubbles", "Cannot populate expanded view.");
        }
    }

    public void update(Bubble bubble) {
        if (bubble.getKey().equals(this.mBubble.getKey())) {
            this.mBubble = bubble;
            updateSettingsContentDescription();
            updateHeight();
            return;
        }
        Log.w("Bubbles", "Trying to update entry with different key, new bubble: " + bubble.getKey() + " old bubble: " + bubble.getKey());
    }

    private void updateExpandedView() {
        this.mBubbleIntent = this.mBubble.getBubbleIntent(this.mContext);
        if (this.mBubbleIntent != null) {
            setContentVisibility(false);
            this.mActivityView.setVisibility(0);
        }
        updateView();
    }

    /* access modifiers changed from: package-private */
    public boolean performBackPressIfNeeded() {
        if (!usingActivityView()) {
            return false;
        }
        this.mActivityView.performBackPress();
        return true;
    }

    /* access modifiers changed from: package-private */
    public void updateHeight() {
        if (usingActivityView()) {
            float max = Math.max(Math.min(Math.max(this.mBubble.getDesiredHeight(this.mContext), (float) this.mMinHeight), (float) getMaxExpandedHeight()), (float) this.mMinHeight);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mActivityView.getLayoutParams();
            this.mNeedsNewHeight = ((float) layoutParams.height) != max;
            if (!this.mKeyboardVisible) {
                layoutParams.height = (int) max;
                this.mActivityView.setLayoutParams(layoutParams);
                this.mNeedsNewHeight = false;
            }
        }
    }

    private int getMaxExpandedHeight() {
        this.mWindowManager.getDefaultDisplay().getRealSize(this.mDisplaySize);
        return ((((this.mDisplaySize.y - this.mActivityView.getLocationOnScreen()[1]) - this.mSettingsIconHeight) - this.mPointerHeight) - this.mPointerMargin) - (getRootWindowInsets() != null ? getRootWindowInsets().getStableInsetBottom() : 0);
    }

    /* access modifiers changed from: package-private */
    public boolean intersectingTouchableContent(int i, int i2) {
        this.mTempRect.setEmpty();
        ActivityView activityView = this.mActivityView;
        if (activityView != null) {
            this.mTempLoc = activityView.getLocationOnScreen();
            Rect rect = this.mTempRect;
            int[] iArr = this.mTempLoc;
            int i3 = iArr[0];
            int i4 = this.mExpandedViewTouchSlop;
            rect.set(i3 - i4, iArr[1] - i4, iArr[0] + this.mActivityView.getWidth() + this.mExpandedViewTouchSlop, this.mTempLoc[1] + this.mActivityView.getHeight() + this.mExpandedViewTouchSlop);
        }
        if (this.mTempRect.contains(i, i2)) {
            return true;
        }
        this.mTempLoc = this.mSettingsIcon.getLocationOnScreen();
        Rect rect2 = this.mTempRect;
        int[] iArr2 = this.mTempLoc;
        rect2.set(iArr2[0], iArr2[1], iArr2[0] + this.mSettingsIcon.getWidth(), this.mTempLoc[1] + this.mSettingsIcon.getHeight());
        return this.mTempRect.contains(i, i2);
    }

    public void onClick(View view) {
        if (this.mBubble != null && view.getId() == C1777R$id.settings_button) {
            this.mStackView.collapseStack(new Runnable(this.mBubble.getSettingsIntent()) {
                private final /* synthetic */ Intent f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BubbleExpandedView.this.lambda$onClick$1$BubbleExpandedView(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onClick$1$BubbleExpandedView(Intent intent) {
        this.mContext.startActivityAsUser(intent, this.mBubble.getEntry().notification.getUser());
        logBubbleClickEvent(this.mBubble, 9);
    }

    private void updateSettingsContentDescription() {
        this.mSettingsIcon.setContentDescription(getResources().getString(C1784R$string.bubbles_settings_button_description, new Object[]{this.mAppName}));
    }

    /* access modifiers changed from: package-private */
    public void showSettingsIcon() {
        updateSettingsContentDescription();
        this.mSettingsIcon.setVisibility(0);
    }

    public void updateView() {
        if (usingActivityView() && this.mActivityView.getVisibility() == 0 && this.mActivityView.isAttachedToWindow()) {
            this.mActivityView.onLocationChanged();
        }
        updateHeight();
    }

    public void setPointerPosition(float f) {
        this.mPointerView.setTranslationX(f - (((float) this.mPointerWidth) / 2.0f));
        this.mPointerView.setVisibility(0);
    }

    public void cleanUpExpandedState() {
        if (this.mActivityView != null) {
            int i = C06852.f34x9ad3957a[this.mActivityViewStatus.ordinal()];
            if (i == 2 || i == 3) {
                this.mActivityView.release();
            }
            if (this.mTaskId != -1) {
                try {
                    ActivityTaskManager.getService().removeTask(this.mTaskId);
                } catch (RemoteException unused) {
                    Log.w("Bubbles", "Failed to remove taskId " + this.mTaskId);
                }
                this.mTaskId = -1;
            }
            removeView(this.mActivityView);
            this.mActivityView = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyDisplayEmpty() {
        if (this.mActivityViewStatus == ActivityViewStatus.ACTIVITY_STARTED) {
            this.mActivityViewStatus = ActivityViewStatus.INITIALIZED;
        }
    }

    private boolean usingActivityView() {
        return (this.mBubbleIntent == null || this.mActivityView == null) ? false : true;
    }

    public int getVirtualDisplayId() {
        if (usingActivityView()) {
            return this.mActivityView.getVirtualDisplayId();
        }
        return -1;
    }

    private void logBubbleClickEvent(Bubble bubble, int i) {
        StatusBarNotification statusBarNotification = bubble.getEntry().notification;
        String packageName = statusBarNotification.getPackageName();
        String channelId = statusBarNotification.getNotification().getChannelId();
        int id = statusBarNotification.getId();
        BubbleStackView bubbleStackView = this.mStackView;
        StatsLog.write(149, packageName, channelId, id, bubbleStackView.getBubbleIndex(bubbleStackView.getExpandedBubble()), this.mStackView.getBubbleCount(), i, this.mStackView.getNormalizedXPosition(), this.mStackView.getNormalizedYPosition(), bubble.showInShadeWhenBubble(), bubble.isOngoing(), false);
    }
}
