package com.android.systemui.settings;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.Dependency;
import com.android.systemui.tuner.TunerService;

public class BrightnessDialog extends Activity implements TunerService.Tunable {
    private ImageView mAdaptiveBrightness;
    private boolean mAutoBrightnessEnabled;
    private BrightnessController mBrightnessController;
    private ImageView mMaxBrightness;
    private ImageView mMinBrightness;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        final ContentResolver contentResolver = getContentResolver();
        Window window = getWindow();
        final Vibrator vibrator = (Vibrator) getSystemService("vibrator");
        window.setGravity(48);
        window.clearFlags(2);
        window.requestFeature(1);
        View inflate = LayoutInflater.from(this).inflate(C1779R$layout.quick_settings_brightness_dialog, (ViewGroup) null);
        setContentView(inflate);
        this.mAdaptiveBrightness = (ImageView) findViewById(C1777R$id.brightness_icon);
        this.mBrightnessController = new BrightnessController(this, this.mAdaptiveBrightness, (ToggleSliderView) findViewById(C1777R$id.brightness_slider));
        this.mMinBrightness = (ImageView) inflate.findViewById(C1777R$id.brightness_left);
        this.mMinBrightness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int intForUser = Settings.System.getIntForUser(contentResolver, "screen_brightness", 0, -2);
                int i = intForUser - 2;
                if (intForUser != 0) {
                    Settings.System.putIntForUser(contentResolver, "screen_brightness", Math.max(0, i), -2);
                }
            }
        });
        this.mMinBrightness.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                BrightnessDialog.this.setBrightnessMinMax(true);
                vibrator.vibrate(VibrationEffect.createOneShot(50, -1));
                return true;
            }
        });
        this.mMaxBrightness = (ImageView) inflate.findViewById(C1777R$id.brightness_right);
        this.mMaxBrightness.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int intForUser = Settings.System.getIntForUser(contentResolver, "screen_brightness", 0, -2);
                int i = intForUser + 2;
                if (intForUser != 255) {
                    Settings.System.putIntForUser(contentResolver, "screen_brightness", Math.min(255, i), -2);
                }
            }
        });
        this.mMaxBrightness.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                BrightnessDialog.this.setBrightnessMinMax(false);
                vibrator.vibrate(VibrationEffect.createOneShot(50, -1));
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public void setBrightnessMinMax(boolean z) {
        this.mBrightnessController.setBrightnessFromSliderButtons(z ? 0 : 1023);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mBrightnessController.registerCallbacks();
        MetricsLogger.visible(this, 220);
        TunerService tunerService = (TunerService) Dependency.get(TunerService.class);
        tunerService.addTunable(this, "qs_show_auto_brightness");
        tunerService.addTunable(this, "qs_show_brightness_buttons");
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        MetricsLogger.hidden(this, 220);
        this.mBrightnessController.unregisterCallbacks();
        ((TunerService) Dependency.get(TunerService.class)).removeTunable(this);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 25 || i == 24 || i == 164) {
            finish();
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z) {
            finish();
        }
    }

    public void onTuningChanged(String str, String str2) {
        if ("qs_show_auto_brightness".equals(str)) {
            this.mAutoBrightnessEnabled = str2 == null || Integer.parseInt(str2) != 0;
            updateAutoBrightnessVisibility();
        } else if ("qs_show_brightness_buttons".equals(str)) {
            updateViewVisibilityForTuningValue(this.mMinBrightness, str2);
            updateViewVisibilityForTuningValue(this.mMaxBrightness, str2);
        }
    }

    private void updateAutoBrightnessVisibility() {
        this.mAdaptiveBrightness.setVisibility(this.mAutoBrightnessEnabled ? 0 : 8);
    }

    private void updateViewVisibilityForTuningValue(View view, String str) {
        view.setVisibility((str == null || Integer.parseInt(str) != 0) ? 0 : 8);
    }
}
