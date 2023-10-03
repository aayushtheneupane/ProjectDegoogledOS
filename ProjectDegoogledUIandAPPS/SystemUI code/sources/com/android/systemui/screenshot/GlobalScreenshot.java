package com.android.systemui.screenshot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Slog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.havoc.ActionUtils;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.SystemUI;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.screenshot.GlobalScreenshot;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import com.android.systemui.screenshot.ScreenshotSelectorView;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.util.NotificationChannels;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

class GlobalScreenshot {
    /* access modifiers changed from: private */
    public AudioManager mAudioManager;
    /* access modifiers changed from: private */
    public ImageView mBackgroundView = ((ImageView) this.mScreenshotLayout.findViewById(C1777R$id.global_screenshot_background));
    private float mBgPadding;
    /* access modifiers changed from: private */
    public float mBgPaddingScale;
    /* access modifiers changed from: private */
    public MediaActionSound mCameraSound;
    private View mCancelButton = this.mScreenshotLayout.findViewById(C1777R$id.global_screenshot_selector_cancel);
    /* access modifiers changed from: private */
    public View mCaptureButton = this.mScreenshotLayout.findViewById(C1777R$id.global_screenshot_selector_capture);
    /* access modifiers changed from: private */
    public Context mContext;
    private Display mDisplay;
    private DisplayMetrics mDisplayMetrics;
    private int mNotificationIconSize;
    private NotificationManager mNotificationManager;
    private final int mPreviewHeight;
    private final int mPreviewWidth;
    private AsyncTask<Void, Void, Void> mSaveInBgTask;
    /* access modifiers changed from: private */
    public Bitmap mScreenBitmap;
    /* access modifiers changed from: private */
    public AnimatorSet mScreenshotAnimation;
    /* access modifiers changed from: private */
    public LinearLayout mScreenshotButtonsLayout = ((LinearLayout) this.mScreenshotLayout.findViewById(C1777R$id.global_screenshot_buttons));
    /* access modifiers changed from: private */
    public ImageView mScreenshotFlash = ((ImageView) this.mScreenshotLayout.findViewById(C1777R$id.global_screenshot_flash));
    /* access modifiers changed from: private */
    public View mScreenshotLayout;
    /* access modifiers changed from: private */
    public ScreenshotSelectorView mScreenshotSelectorView = ((ScreenshotSelectorView) this.mScreenshotLayout.findViewById(C1777R$id.global_screenshot_selector));
    /* access modifiers changed from: private */
    public ImageView mScreenshotView = ((ImageView) this.mScreenshotLayout.findViewById(C1777R$id.global_screenshot));
    /* access modifiers changed from: private */
    public Vibrator mVibrator;
    private WindowManager.LayoutParams mWindowLayoutParams;
    /* access modifiers changed from: private */
    public WindowManager mWindowManager;

    public GlobalScreenshot(Context context) {
        int i;
        Resources resources = context.getResources();
        this.mContext = context;
        this.mScreenshotLayout = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(C1779R$layout.global_screenshot, (ViewGroup) null);
        this.mScreenshotLayout.setFocusable(true);
        this.mScreenshotSelectorView.setFocusable(true);
        this.mScreenshotSelectorView.setFocusableInTouchMode(true);
        this.mScreenshotLayout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        this.mWindowLayoutParams = new WindowManager.LayoutParams(-1, -1, 0, 0, 2036, 525568, -3);
        this.mWindowLayoutParams.setTitle("ScreenshotAnimation");
        this.mWindowLayoutParams.layoutInDisplayCutoutMode = 1;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
        this.mDisplay = this.mWindowManager.getDefaultDisplay();
        this.mDisplayMetrics = new DisplayMetrics();
        this.mDisplay.getRealMetrics(this.mDisplayMetrics);
        this.mNotificationIconSize = resources.getDimensionPixelSize(17104902);
        this.mBgPadding = (float) resources.getDimensionPixelSize(C1775R$dimen.global_screenshot_bg_padding);
        this.mBgPaddingScale = this.mBgPadding / ((float) this.mDisplayMetrics.widthPixels);
        try {
            i = resources.getDimensionPixelSize(C1775R$dimen.notification_panel_width);
        } catch (Resources.NotFoundException unused) {
            i = 0;
        }
        this.mPreviewWidth = i <= 0 ? this.mDisplayMetrics.widthPixels : i;
        this.mPreviewHeight = resources.getDimensionPixelSize(C1775R$dimen.notification_max_height);
        this.mCameraSound = new MediaActionSound();
        this.mCameraSound.load(0);
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mVibrator = (Vibrator) this.mContext.getSystemService("vibrator");
    }

    /* access modifiers changed from: private */
    public void saveScreenshotInWorkerThread(Consumer<Uri> consumer) {
        SaveImageInBackgroundData saveImageInBackgroundData = new SaveImageInBackgroundData();
        saveImageInBackgroundData.context = this.mContext;
        saveImageInBackgroundData.image = this.mScreenBitmap;
        saveImageInBackgroundData.iconSize = this.mNotificationIconSize;
        saveImageInBackgroundData.finisher = consumer;
        saveImageInBackgroundData.previewWidth = this.mPreviewWidth;
        saveImageInBackgroundData.previewheight = this.mPreviewHeight;
        AsyncTask<Void, Void, Void> asyncTask = this.mSaveInBgTask;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        this.mSaveInBgTask = new SaveImageInBackgroundTask(this.mContext, saveImageInBackgroundData, this.mNotificationManager).execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void takeScreenshot(Consumer<Uri> consumer, boolean z, boolean z2, Rect rect) {
        this.mScreenBitmap = SurfaceControl.screenshot(rect, rect.width(), rect.height(), this.mDisplay.getRotation());
        Bitmap bitmap = this.mScreenBitmap;
        if (bitmap == null) {
            notifyScreenshotError(this.mContext, this.mNotificationManager, C1784R$string.screenshot_failed_to_capture_text);
            consumer.accept((Object) null);
            return;
        }
        bitmap.setHasAlpha(false);
        this.mScreenBitmap.prepareToDraw();
        DisplayMetrics displayMetrics = this.mDisplayMetrics;
        startAnimation(consumer, displayMetrics.widthPixels, displayMetrics.heightPixels, z, z2);
    }

    /* access modifiers changed from: package-private */
    public void takeScreenshot(Consumer<Uri> consumer, boolean z, boolean z2) {
        if (this.mScreenshotLayout.getParent() != null) {
            consumer.accept((Object) null);
            return;
        }
        this.mDisplay.getRealMetrics(this.mDisplayMetrics);
        DisplayMetrics displayMetrics = this.mDisplayMetrics;
        takeScreenshot(consumer, z, z2, new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels));
    }

    /* access modifiers changed from: package-private */
    public void takeScreenshotPartial(final Consumer<Uri> consumer, final boolean z, final boolean z2) {
        if (this.mScreenshotLayout.getParent() != null) {
            consumer.accept((Object) null);
            return;
        }
        this.mWindowManager.addView(this.mScreenshotLayout, this.mWindowLayoutParams);
        ActionUtils.setPartialScreenshot(true);
        this.mScreenshotSelectorView.setSelectionListener(new ScreenshotSelectorView.OnSelectionListener() {
            public void onSelectionChanged(Rect rect, boolean z) {
                if (z) {
                    GlobalScreenshot.this.mScreenshotLayout.post(new Runnable() {
                        public final void run() {
                            GlobalScreenshot.C10002.this.lambda$onSelectionChanged$0$GlobalScreenshot$2();
                        }
                    });
                }
            }

            public /* synthetic */ void lambda$onSelectionChanged$0$GlobalScreenshot$2() {
                GlobalScreenshot.this.mCaptureButton.setVisibility(0);
            }
        });
        this.mCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GlobalScreenshot.this.mScreenshotLayout.post(new Runnable(consumer) {
                    private final /* synthetic */ Consumer f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        GlobalScreenshot.C10013.this.lambda$onClick$0$GlobalScreenshot$3(this.f$1);
                    }
                });
            }

            public /* synthetic */ void lambda$onClick$0$GlobalScreenshot$3(Consumer consumer) {
                consumer.accept((Object) null);
                GlobalScreenshot.this.hideScreenshotSelector();
            }
        });
        this.mCaptureButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final Rect selectionRect = GlobalScreenshot.this.mScreenshotSelectorView.getSelectionRect();
                GlobalScreenshot.this.mScreenshotButtonsLayout.getLayoutTransition().addTransitionListener(new LayoutTransition.TransitionListener() {
                    public void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
                    }

                    public void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i) {
                        C10024 r4 = C10024.this;
                        GlobalScreenshot.this.takeScreenshot(consumer, z, z2, selectionRect);
                        layoutTransition.removeTransitionListener(this);
                    }
                });
                GlobalScreenshot.this.mScreenshotLayout.post(new Runnable() {
                    public final void run() {
                        GlobalScreenshot.C10024.this.lambda$onClick$0$GlobalScreenshot$4();
                    }
                });
            }

            public /* synthetic */ void lambda$onClick$0$GlobalScreenshot$4() {
                GlobalScreenshot.this.hideScreenshotSelector();
            }
        });
        this.mScreenshotLayout.post(new Runnable() {
            public final void run() {
                GlobalScreenshot.this.lambda$takeScreenshotPartial$0$GlobalScreenshot();
            }
        });
    }

    public /* synthetic */ void lambda$takeScreenshotPartial$0$GlobalScreenshot() {
        this.mScreenshotSelectorView.setVisibility(0);
        this.mScreenshotSelectorView.requestFocus();
    }

    /* access modifiers changed from: package-private */
    public void hideScreenshotSelector() {
        ActionUtils.setPartialScreenshot(false);
        this.mWindowManager.removeView(this.mScreenshotLayout);
        this.mScreenshotSelectorView.stopSelection();
        this.mScreenshotSelectorView.setVisibility(8);
        this.mCaptureButton.setVisibility(8);
    }

    /* access modifiers changed from: package-private */
    public void stopScreenshot() {
        if (this.mScreenshotSelectorView.getSelectionRect() != null) {
            try {
                this.mWindowManager.removeView(this.mScreenshotLayout);
                this.mScreenshotSelectorView.stopSelection();
            } catch (IllegalArgumentException unused) {
            }
        }
        ActionUtils.setPartialScreenshot(false);
    }

    private void startAnimation(final Consumer<Uri> consumer, int i, int i2, boolean z, boolean z2) {
        if (((PowerManager) this.mContext.getSystemService("power")).isPowerSaveMode()) {
            Toast.makeText(this.mContext, C1784R$string.screenshot_saved_title, 0).show();
        }
        this.mScreenshotView.setImageBitmap(this.mScreenBitmap);
        this.mScreenshotLayout.requestFocus();
        AnimatorSet animatorSet = this.mScreenshotAnimation;
        if (animatorSet != null) {
            if (animatorSet.isStarted()) {
                this.mScreenshotAnimation.end();
            }
            this.mScreenshotAnimation.removeAllListeners();
        }
        this.mWindowManager.addView(this.mScreenshotLayout, this.mWindowLayoutParams);
        ValueAnimator createScreenshotDropInAnimation = createScreenshotDropInAnimation();
        ValueAnimator createScreenshotDropOutAnimation = createScreenshotDropOutAnimation(i, i2, z, z2);
        this.mScreenshotAnimation = new AnimatorSet();
        this.mScreenshotAnimation.playSequentially(new Animator[]{createScreenshotDropInAnimation, createScreenshotDropOutAnimation});
        this.mScreenshotAnimation.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                GlobalScreenshot.this.saveScreenshotInWorkerThread(consumer);
                GlobalScreenshot.this.mWindowManager.removeView(GlobalScreenshot.this.mScreenshotLayout);
                Bitmap unused = GlobalScreenshot.this.mScreenBitmap = null;
                GlobalScreenshot.this.mScreenshotView.setImageBitmap((Bitmap) null);
            }
        });
        this.mScreenshotLayout.post(new Runnable() {
            public void run() {
                int ringerMode = GlobalScreenshot.this.mAudioManager.getRingerMode();
                if (ringerMode != 0) {
                    if (ringerMode != 1) {
                        if (ringerMode == 2 && Settings.System.getInt(GlobalScreenshot.this.mContext.getContentResolver(), "screenshot_sound", 1) == 1) {
                            GlobalScreenshot.this.mCameraSound.play(0);
                        }
                    } else if (GlobalScreenshot.this.mVibrator != null && GlobalScreenshot.this.mVibrator.hasVibrator()) {
                        GlobalScreenshot.this.mVibrator.vibrate(VibrationEffect.createOneShot(50, -1));
                    }
                }
                GlobalScreenshot.this.mScreenshotView.setLayerType(2, (Paint) null);
                GlobalScreenshot.this.mScreenshotView.buildLayer();
                GlobalScreenshot.this.mScreenshotAnimation.start();
            }
        });
    }

    private ValueAnimator createScreenshotDropInAnimation() {
        final C10067 r0 = new Interpolator() {
            public float getInterpolation(float f) {
                if (f <= 0.60465115f) {
                    return (float) Math.sin(((double) (f / 0.60465115f)) * 3.141592653589793d);
                }
                return 0.0f;
            }
        };
        final C10078 r1 = new Interpolator() {
            public float getInterpolation(float f) {
                if (f < 0.30232558f) {
                    return 0.0f;
                }
                return (f - 0.60465115f) / 0.39534885f;
            }
        };
        final boolean z = (this.mContext.getResources().getConfiguration().uiMode & 48) == 32;
        if (z) {
            this.mScreenshotView.getBackground().setTint(-16777216);
        } else {
            this.mScreenshotView.getBackground().setTintList((ColorStateList) null);
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(430);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                GlobalScreenshot.this.mBackgroundView.setAlpha(0.0f);
                GlobalScreenshot.this.mBackgroundView.setVisibility(0);
                GlobalScreenshot.this.mScreenshotView.setAlpha(0.0f);
                GlobalScreenshot.this.mScreenshotView.setTranslationX(0.0f);
                GlobalScreenshot.this.mScreenshotView.setTranslationY(0.0f);
                GlobalScreenshot.this.mScreenshotView.setScaleX(GlobalScreenshot.this.mBgPaddingScale + 1.0f);
                GlobalScreenshot.this.mScreenshotView.setScaleY(GlobalScreenshot.this.mBgPaddingScale + 1.0f);
                GlobalScreenshot.this.mScreenshotView.setVisibility(0);
                if (!z) {
                    GlobalScreenshot.this.mScreenshotFlash.setAlpha(0.0f);
                    GlobalScreenshot.this.mScreenshotFlash.setVisibility(0);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (!z) {
                    GlobalScreenshot.this.mScreenshotFlash.setVisibility(8);
                }
            }
        });
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                float access$1500 = (GlobalScreenshot.this.mBgPaddingScale + 1.0f) - (r1.getInterpolation(floatValue) * 0.27499998f);
                GlobalScreenshot.this.mBackgroundView.setAlpha(r1.getInterpolation(floatValue) * 0.5f);
                GlobalScreenshot.this.mScreenshotView.setAlpha(floatValue);
                GlobalScreenshot.this.mScreenshotView.setScaleX(access$1500);
                GlobalScreenshot.this.mScreenshotView.setScaleY(access$1500);
                if (!z) {
                    GlobalScreenshot.this.mScreenshotFlash.setAlpha(r0.getInterpolation(floatValue));
                }
            }
        });
        return ofFloat;
    }

    private ValueAnimator createScreenshotDropOutAnimation(int i, int i2, boolean z, boolean z2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setStartDelay(500);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                GlobalScreenshot.this.mBackgroundView.setVisibility(8);
                GlobalScreenshot.this.mScreenshotView.setVisibility(8);
                GlobalScreenshot.this.mScreenshotView.setLayerType(0, (Paint) null);
            }
        });
        if (!z || !z2) {
            ofFloat.setDuration(320);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float access$1500 = (GlobalScreenshot.this.mBgPaddingScale + 0.725f) - (0.125f * floatValue);
                    float f = 1.0f - floatValue;
                    GlobalScreenshot.this.mBackgroundView.setAlpha(0.5f * f);
                    GlobalScreenshot.this.mScreenshotView.setAlpha(f);
                    GlobalScreenshot.this.mScreenshotView.setScaleX(access$1500);
                    GlobalScreenshot.this.mScreenshotView.setScaleY(access$1500);
                }
            });
        } else {
            final C099813 r6 = new Interpolator() {
                public float getInterpolation(float f) {
                    if (f < 0.8604651f) {
                        return (float) (1.0d - Math.pow((double) (1.0f - (f / 0.8604651f)), 2.0d));
                    }
                    return 1.0f;
                }
            };
            float f = this.mBgPadding;
            float f2 = (((float) i) - (f * 2.0f)) / 2.0f;
            float f3 = (((float) i2) - (f * 2.0f)) / 2.0f;
            final PointF pointF = new PointF((-f2) + (f2 * 0.45f), (-f3) + (f3 * 0.45f));
            ofFloat.setDuration(430);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    float access$1500 = (GlobalScreenshot.this.mBgPaddingScale + 0.725f) - (r6.getInterpolation(floatValue) * 0.27500004f);
                    GlobalScreenshot.this.mBackgroundView.setAlpha((1.0f - floatValue) * 0.5f);
                    GlobalScreenshot.this.mScreenshotView.setAlpha(1.0f - r6.getInterpolation(floatValue));
                    GlobalScreenshot.this.mScreenshotView.setScaleX(access$1500);
                    GlobalScreenshot.this.mScreenshotView.setScaleY(access$1500);
                    GlobalScreenshot.this.mScreenshotView.setTranslationX(pointF.x * floatValue);
                    GlobalScreenshot.this.mScreenshotView.setTranslationY(floatValue * pointF.y);
                }
            });
        }
        return ofFloat;
    }

    static void notifyScreenshotError(Context context, NotificationManager notificationManager, int i) {
        Resources resources = context.getResources();
        String string = resources.getString(i);
        Notification.Builder color = new Notification.Builder(context, NotificationChannels.ALERTS).setTicker(resources.getString(C1784R$string.screenshot_failed_title)).setContentTitle(resources.getString(C1784R$string.screenshot_failed_title)).setContentText(string).setSmallIcon(C1776R$drawable.stat_notify_image_error).setWhen(System.currentTimeMillis()).setVisibility(1).setCategory("err").setAutoCancel(true).setColor(context.getColor(17170460));
        Intent createAdminSupportIntent = ((DevicePolicyManager) context.getSystemService("device_policy")).createAdminSupportIntent("policy_disable_screen_capture");
        if (createAdminSupportIntent != null) {
            color.setContentIntent(PendingIntent.getActivityAsUser(context, 0, createAdminSupportIntent, 0, (Bundle) null, UserHandle.CURRENT));
        }
        SystemUI.overrideNotificationAppName(context, color, true);
        notificationManager.notify(1, new Notification.BigTextStyle(color).bigText(string).build());
    }

    @VisibleForTesting
    static CompletableFuture<List<Notification.Action>> getSmartActionsFuture(String str, Bitmap bitmap, ScreenshotNotificationSmartActionsProvider screenshotNotificationSmartActionsProvider, boolean z, boolean z2) {
        ComponentName componentName;
        if (!z) {
            Slog.i("GlobalScreenshot", "Screenshot Intelligence not enabled, returning empty list.");
            return CompletableFuture.completedFuture(Collections.emptyList());
        } else if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
            Slog.w("GlobalScreenshot", String.format("Bitmap expected: Hardware, Bitmap found: %s. Returning empty list.", new Object[]{bitmap.getConfig()}));
            return CompletableFuture.completedFuture(Collections.emptyList());
        } else {
            Slog.d("GlobalScreenshot", "Screenshot from a managed profile: " + z2);
            long uptimeMillis = SystemClock.uptimeMillis();
            try {
                ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
                if (runningTask == null || runningTask.topActivity == null) {
                    componentName = new ComponentName("", "");
                } else {
                    componentName = runningTask.topActivity;
                }
                return screenshotNotificationSmartActionsProvider.getActions(str, bitmap, componentName, z2);
            } catch (Throwable th) {
                long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
                CompletableFuture<List<Notification.Action>> completedFuture = CompletableFuture.completedFuture(Collections.emptyList());
                Slog.e("GlobalScreenshot", "Failed to get future for screenshot notification smart actions.", th);
                notifyScreenshotOp(str, screenshotNotificationSmartActionsProvider, ScreenshotNotificationSmartActionsProvider.ScreenshotOp.REQUEST_SMART_ACTIONS, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.ERROR, uptimeMillis2);
                return completedFuture;
            }
        }
    }

    @VisibleForTesting
    static List<Notification.Action> getSmartActions(String str, CompletableFuture<List<Notification.Action>> completableFuture, int i, ScreenshotNotificationSmartActionsProvider screenshotNotificationSmartActionsProvider) {
        ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus screenshotOpStatus;
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            List<Notification.Action> list = completableFuture.get((long) i, TimeUnit.MILLISECONDS);
            long uptimeMillis2 = SystemClock.uptimeMillis() - uptimeMillis;
            Slog.d("GlobalScreenshot", String.format("Got %d smart actions. Wait time: %d ms", new Object[]{Integer.valueOf(list.size()), Long.valueOf(uptimeMillis2)}));
            notifyScreenshotOp(str, screenshotNotificationSmartActionsProvider, ScreenshotNotificationSmartActionsProvider.ScreenshotOp.WAIT_FOR_SMART_ACTIONS, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.SUCCESS, uptimeMillis2);
            return list;
        } catch (Throwable th) {
            long uptimeMillis3 = SystemClock.uptimeMillis() - uptimeMillis;
            Slog.e("GlobalScreenshot", String.format("Error getting smart actions. Wait time: %d ms", new Object[]{Long.valueOf(uptimeMillis3)}), th);
            if (th instanceof TimeoutException) {
                screenshotOpStatus = ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.TIMEOUT;
            } else {
                screenshotOpStatus = ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.ERROR;
            }
            String str2 = str;
            ScreenshotNotificationSmartActionsProvider screenshotNotificationSmartActionsProvider2 = screenshotNotificationSmartActionsProvider;
            notifyScreenshotOp(str2, screenshotNotificationSmartActionsProvider2, ScreenshotNotificationSmartActionsProvider.ScreenshotOp.WAIT_FOR_SMART_ACTIONS, screenshotOpStatus, uptimeMillis3);
            return Collections.emptyList();
        }
    }

    static void notifyScreenshotOp(String str, ScreenshotNotificationSmartActionsProvider screenshotNotificationSmartActionsProvider, ScreenshotNotificationSmartActionsProvider.ScreenshotOp screenshotOp, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus screenshotOpStatus, long j) {
        try {
            screenshotNotificationSmartActionsProvider.notifyOp(str, screenshotOp, screenshotOpStatus, j);
        } catch (Throwable th) {
            Slog.e("GlobalScreenshot", "Error in notifyScreenshotOp: ", th);
        }
    }

    static void notifyScreenshotAction(Context context, String str, String str2, boolean z) {
        try {
            SystemUIFactory.getInstance().createScreenshotNotificationSmartActionsProvider(context, AsyncTask.THREAD_POOL_EXECUTOR, new Handler()).notifyAction(str, str2, z);
        } catch (Throwable th) {
            Slog.e("GlobalScreenshot", "Error in notifyScreenshotAction: ", th);
        }
    }

    public static class ActionProxyReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Intent intent2 = (Intent) intent.getParcelableExtra("android:screenshot_action_intent");
            ((StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class)).executeRunnableDismissingKeyguard(new Runnable(intent, context, intent2) {
                private final /* synthetic */ Intent f$0;
                private final /* synthetic */ Context f$1;
                private final /* synthetic */ Intent f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    GlobalScreenshot.ActionProxyReceiver.lambda$onReceive$0(this.f$0, this.f$1, this.f$2);
                }
            }, (Runnable) null, true, true, true);
            if (intent.getBooleanExtra("android:smart_actions_enabled", false)) {
                GlobalScreenshot.notifyScreenshotAction(context, intent.getStringExtra("android:screenshot_id"), "android.intent.action.EDIT".equals(intent2.getAction()) ? "Edit" : "Share", false);
            }
        }

        static /* synthetic */ void lambda$onReceive$0(Intent intent, Context context, Intent intent2) {
            try {
                ActivityManagerWrapper.getInstance().closeSystemWindows(StatusBar.SYSTEM_DIALOG_REASON_SCREENSHOT).get(3000, TimeUnit.MILLISECONDS);
                if (intent.getBooleanExtra("android:screenshot_cancel_notification", false)) {
                    GlobalScreenshot.cancelScreenshotNotification(context);
                }
                ActivityOptions makeBasic = ActivityOptions.makeBasic();
                makeBasic.setDisallowEnterPictureInPictureWhileLaunching(intent.getBooleanExtra("android:screenshot_disallow_enter_pip", false));
                context.startActivityAsUser(intent2, makeBasic.toBundle(), UserHandle.CURRENT);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Slog.e("GlobalScreenshot", "Unable to share screenshot", e);
            }
        }
    }

    public static class TargetChosenReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            GlobalScreenshot.cancelScreenshotNotification(context);
        }
    }

    public static class DeleteScreenshotReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("android:screenshot_uri_id")) {
                GlobalScreenshot.cancelScreenshotNotification(context);
                Toast.makeText(context, C1784R$string.delete_screenshot_toast, 0).show();
                Uri parse = Uri.parse(intent.getStringExtra("android:screenshot_uri_id"));
                new DeleteImageInBackgroundTask(context).execute(new Uri[]{parse});
                if (intent.getBooleanExtra("android:smart_actions_enabled", false)) {
                    GlobalScreenshot.notifyScreenshotAction(context, intent.getStringExtra("android:screenshot_id"), "Delete", false);
                }
            }
        }
    }

    public static class SmartActionsReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            Intent intent2 = ((PendingIntent) intent.getParcelableExtra("android:screenshot_action_intent")).getIntent();
            String stringExtra = intent.getStringExtra("android:screenshot_action_type");
            Slog.d("GlobalScreenshot", "Executing smart action [" + stringExtra + "]:" + intent2);
            context.startActivityAsUser(intent2, ActivityOptions.makeBasic().toBundle(), UserHandle.CURRENT);
            GlobalScreenshot.notifyScreenshotAction(context, intent.getStringExtra("android:screenshot_id"), stringExtra, true);
        }
    }

    /* access modifiers changed from: private */
    public static void cancelScreenshotNotification(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancel(1);
    }
}
