package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.android.internal.util.Preconditions;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1778R$integer;
import com.android.systemui.C1779R$layout;
import com.android.systemui.Dependency;
import com.android.systemui.statusbar.phone.NotificationPanelView;
import com.android.systemui.statusbar.phone.StatusBarWindowView;
import com.android.systemui.tuner.TunerService;
import java.util.function.Consumer;

public class BrightnessMirrorController implements CallbackController<BrightnessMirrorListener>, TunerService.Tunable {
    private boolean mAutoBrightnessEnabled;
    private View mBrightnessMirror;
    private final ArraySet<BrightnessMirrorListener> mBrightnessMirrorListeners = new ArraySet<>();
    private Context mContext;
    private ImageView mIcon;
    private final int[] mInt2Cache = new int[2];
    private ImageView mMaxBrightness;
    private ImageView mMinBrightness;
    private final NotificationPanelView mNotificationPanel;
    private final StatusBarWindowView mStatusBarWindow;
    private final Consumer<Boolean> mVisibilityCallback;

    public interface BrightnessMirrorListener {
        void onBrightnessMirrorReinflated(View view);
    }

    public BrightnessMirrorController(Context context, StatusBarWindowView statusBarWindowView, Consumer<Boolean> consumer) {
        this.mContext = context;
        this.mStatusBarWindow = statusBarWindowView;
        this.mBrightnessMirror = statusBarWindowView.findViewById(C1777R$id.brightness_mirror);
        this.mNotificationPanel = (NotificationPanelView) statusBarWindowView.findViewById(C1777R$id.notification_panel);
        this.mNotificationPanel.setPanelAlphaEndAction(new Runnable() {
            public final void run() {
                BrightnessMirrorController.this.lambda$new$0$BrightnessMirrorController();
            }
        });
        this.mVisibilityCallback = consumer;
        this.mIcon = (ImageView) this.mBrightnessMirror.findViewById(C1777R$id.brightness_icon);
        this.mMinBrightness = (ImageView) this.mBrightnessMirror.findViewById(C1777R$id.brightness_left);
        this.mMaxBrightness = (ImageView) this.mBrightnessMirror.findViewById(C1777R$id.brightness_right);
    }

    public /* synthetic */ void lambda$new$0$BrightnessMirrorController() {
        this.mBrightnessMirror.setVisibility(4);
    }

    public void showMirror() {
        TunerService tunerService = (TunerService) Dependency.get(TunerService.class);
        tunerService.addTunable(this, "qs_show_auto_brightness");
        tunerService.addTunable(this, "qs_show_brightness_buttons");
        updateIcon();
        this.mBrightnessMirror.setVisibility(0);
        this.mVisibilityCallback.accept(true);
        this.mNotificationPanel.setPanelAlpha(0, true);
    }

    public void hideMirror() {
        this.mVisibilityCallback.accept(false);
        this.mNotificationPanel.setPanelAlpha(255, true);
        ((TunerService) Dependency.get(TunerService.class)).removeTunable(this);
    }

    public void setLocation(View view) {
        view.getLocationInWindow(this.mInt2Cache);
        int width = this.mInt2Cache[0] + (view.getWidth() / 2);
        int height = this.mInt2Cache[1] + (view.getHeight() / 2);
        this.mBrightnessMirror.setTranslationX(0.0f);
        this.mBrightnessMirror.setTranslationY(0.0f);
        this.mBrightnessMirror.getLocationInWindow(this.mInt2Cache);
        int width2 = this.mInt2Cache[0] + (this.mBrightnessMirror.getWidth() / 2);
        int height2 = this.mInt2Cache[1] + (this.mBrightnessMirror.getHeight() / 2);
        this.mBrightnessMirror.setTranslationX((float) (width - width2));
        this.mBrightnessMirror.setTranslationY((float) (height - height2));
    }

    public View getMirror() {
        return this.mBrightnessMirror;
    }

    public void updateResources() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mBrightnessMirror.getLayoutParams();
        Resources resources = this.mBrightnessMirror.getResources();
        layoutParams.width = resources.getDimensionPixelSize(C1775R$dimen.qs_panel_width);
        layoutParams.height = resources.getDimensionPixelSize(C1775R$dimen.brightness_mirror_height);
        layoutParams.gravity = resources.getInteger(C1778R$integer.notification_panel_layout_gravity);
        this.mBrightnessMirror.setLayoutParams(layoutParams);
    }

    public void onOverlayChanged() {
        reinflate();
    }

    public void onDensityOrFontScaleChanged() {
        reinflate();
    }

    private void reinflate() {
        int indexOfChild = this.mStatusBarWindow.indexOfChild(this.mBrightnessMirror);
        this.mStatusBarWindow.removeView(this.mBrightnessMirror);
        this.mBrightnessMirror = LayoutInflater.from(this.mBrightnessMirror.getContext()).inflate(C1779R$layout.brightness_mirror, this.mStatusBarWindow, false);
        this.mStatusBarWindow.addView(this.mBrightnessMirror, indexOfChild);
        for (int i = 0; i < this.mBrightnessMirrorListeners.size(); i++) {
            this.mBrightnessMirrorListeners.valueAt(i).onBrightnessMirrorReinflated(this.mBrightnessMirror);
        }
    }

    public void addCallback(BrightnessMirrorListener brightnessMirrorListener) {
        Preconditions.checkNotNull(brightnessMirrorListener);
        this.mBrightnessMirrorListeners.add(brightnessMirrorListener);
    }

    public void removeCallback(BrightnessMirrorListener brightnessMirrorListener) {
        this.mBrightnessMirrorListeners.remove(brightnessMirrorListener);
    }

    public void onUiModeChanged() {
        reinflate();
    }

    private void updateIcon() {
        int i;
        boolean z = false;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "screen_brightness_mode", 0, -2) != 0) {
            z = true;
        }
        this.mIcon = (ImageView) this.mBrightnessMirror.findViewById(C1777R$id.brightness_icon);
        ImageView imageView = this.mIcon;
        if (imageView != null) {
            if (z) {
                i = C1776R$drawable.ic_qs_brightness_auto_on;
            } else {
                i = C1776R$drawable.ic_qs_brightness_auto_off;
            }
            imageView.setImageResource(i);
        }
    }

    public void onTuningChanged(String str, String str2) {
        if ("qs_show_auto_brightness".equals(str)) {
            this.mIcon = (ImageView) this.mBrightnessMirror.findViewById(C1777R$id.brightness_icon);
            this.mAutoBrightnessEnabled = str2 == null || Integer.parseInt(str2) != 0;
            updateAutoBrightnessVisibility();
        } else if ("qs_show_brightness_buttons".equals(str)) {
            this.mMinBrightness = (ImageView) this.mBrightnessMirror.findViewById(C1777R$id.brightness_left);
            this.mMaxBrightness = (ImageView) this.mBrightnessMirror.findViewById(C1777R$id.brightness_right);
            updateViewVisibilityForTuningValue(this.mMinBrightness, str2);
            updateViewVisibilityForTuningValue(this.mMaxBrightness, str2);
        }
    }

    private void updateAutoBrightnessVisibility() {
        this.mIcon.setVisibility(this.mAutoBrightnessEnabled ? 0 : 8);
    }

    private void updateViewVisibilityForTuningValue(View view, String str) {
        view.setVisibility((str == null || Integer.parseInt(str) != 0) ? 0 : 8);
    }
}
