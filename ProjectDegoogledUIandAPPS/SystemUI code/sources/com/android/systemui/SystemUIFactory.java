package com.android.systemui;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.ViewMediatorCallback;
import com.android.systemui.DaggerSystemUIRootComponent;
import com.android.systemui.keyguard.DismissCallbackRegistry;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.ScrimView;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.KeyguardBouncer;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.LockIcon;
import com.android.systemui.statusbar.phone.LockscreenWallpaper;
import com.android.systemui.statusbar.phone.NotificationIconAreaController;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimState;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.UnlockMethodCache;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.volume.VolumeDialogComponent;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class SystemUIFactory {
    static SystemUIFactory mFactory;
    protected SystemUIRootComponent mRootComponent;

    public static <T extends SystemUIFactory> T getInstance() {
        return mFactory;
    }

    public static void createFromConfig(Context context) {
        String string = context.getString(C1784R$string.config_systemUIFactoryComponent);
        if (string == null || string.length() == 0) {
            throw new RuntimeException("No SystemUIFactory component configured");
        }
        try {
            mFactory = (SystemUIFactory) context.getClassLoader().loadClass(string).newInstance();
            mFactory.init(context);
        } catch (Throwable th) {
            Log.w("SystemUIFactory", "Error creating SystemUIFactory component: " + string, th);
            throw new RuntimeException(th);
        }
    }

    private void init(Context context) {
        this.mRootComponent = buildSystemUIRootComponent(context);
        Dependency.initDependencies(this.mRootComponent);
    }

    /* access modifiers changed from: protected */
    public SystemUIRootComponent buildSystemUIRootComponent(Context context) {
        DaggerSystemUIRootComponent.Builder builder = DaggerSystemUIRootComponent.builder();
        builder.dependencyProvider(new DependencyProvider());
        builder.contextHolder(new ContextHolder(context));
        return builder.build();
    }

    public SystemUIRootComponent getRootComponent() {
        return this.mRootComponent;
    }

    public StatusBarKeyguardViewManager createStatusBarKeyguardViewManager(Context context, ViewMediatorCallback viewMediatorCallback, LockPatternUtils lockPatternUtils) {
        return new StatusBarKeyguardViewManager(context, viewMediatorCallback, lockPatternUtils);
    }

    public ScreenshotNotificationSmartActionsProvider createScreenshotNotificationSmartActionsProvider(Context context, Executor executor, Handler handler) {
        return new ScreenshotNotificationSmartActionsProvider();
    }

    public KeyguardBouncer createKeyguardBouncer(Context context, ViewMediatorCallback viewMediatorCallback, LockPatternUtils lockPatternUtils, ViewGroup viewGroup, DismissCallbackRegistry dismissCallbackRegistry, KeyguardBouncer.BouncerExpansionCallback bouncerExpansionCallback, FalsingManager falsingManager, KeyguardBypassController keyguardBypassController) {
        return new KeyguardBouncer(context, viewMediatorCallback, lockPatternUtils, viewGroup, dismissCallbackRegistry, falsingManager, bouncerExpansionCallback, UnlockMethodCache.getInstance(context), KeyguardUpdateMonitor.getInstance(context), keyguardBypassController, new Handler(Looper.getMainLooper()));
    }

    public ScrimController createScrimController(ScrimView scrimView, ScrimView scrimView2, LockscreenWallpaper lockscreenWallpaper, TriConsumer<ScrimState, Float, ColorExtractor.GradientColors> triConsumer, Consumer<Integer> consumer, DozeParameters dozeParameters, AlarmManager alarmManager, KeyguardMonitor keyguardMonitor) {
        return new ScrimController(scrimView, scrimView2, triConsumer, consumer, dozeParameters, alarmManager, keyguardMonitor);
    }

    public NotificationIconAreaController createNotificationIconAreaController(Context context, StatusBar statusBar, NotificationWakeUpCoordinator notificationWakeUpCoordinator, KeyguardBypassController keyguardBypassController, StatusBarStateController statusBarStateController) {
        return new NotificationIconAreaController(context, statusBar, statusBarStateController, notificationWakeUpCoordinator, keyguardBypassController, (NotificationMediaManager) Dependency.get(NotificationMediaManager.class));
    }

    public KeyguardIndicationController createKeyguardIndicationController(Context context, ViewGroup viewGroup, LockIcon lockIcon) {
        return new KeyguardIndicationController(context, viewGroup, lockIcon);
    }

    public VolumeDialogComponent createVolumeDialogComponent(SystemUI systemUI, Context context) {
        return new VolumeDialogComponent(systemUI, context);
    }

    public static class ContextHolder {
        private Context mContext;

        public ContextHolder(Context context) {
            this.mContext = context;
        }

        public Context provideContext() {
            return this.mContext;
        }
    }
}
