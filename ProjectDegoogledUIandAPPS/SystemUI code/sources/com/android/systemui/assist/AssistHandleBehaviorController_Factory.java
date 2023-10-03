package com.android.systemui.assist;

import android.content.Context;
import android.os.Handler;
import com.android.internal.app.AssistUtils;
import com.android.systemui.DumpController;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.assist.AssistHandleBehaviorController;
import com.android.systemui.statusbar.phone.NavigationModeController;
import dagger.internal.Factory;
import java.util.Map;
import javax.inject.Provider;

public final class AssistHandleBehaviorController_Factory implements Factory<AssistHandleBehaviorController> {
    private final Provider<AssistUtils> assistUtilsProvider;
    private final Provider<Map<AssistHandleBehavior, AssistHandleBehaviorController.BehaviorController>> behaviorMapProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceConfigHelper> deviceConfigHelperProvider;
    private final Provider<DumpController> dumpControllerProvider;
    private final Provider<Handler> handlerProvider;
    private final Provider<NavigationModeController> navigationModeControllerProvider;
    private final Provider<ScreenDecorations> screenDecorationsProvider;

    public AssistHandleBehaviorController_Factory(Provider<Context> provider, Provider<AssistUtils> provider2, Provider<Handler> provider3, Provider<ScreenDecorations> provider4, Provider<DeviceConfigHelper> provider5, Provider<Map<AssistHandleBehavior, AssistHandleBehaviorController.BehaviorController>> provider6, Provider<NavigationModeController> provider7, Provider<DumpController> provider8) {
        this.contextProvider = provider;
        this.assistUtilsProvider = provider2;
        this.handlerProvider = provider3;
        this.screenDecorationsProvider = provider4;
        this.deviceConfigHelperProvider = provider5;
        this.behaviorMapProvider = provider6;
        this.navigationModeControllerProvider = provider7;
        this.dumpControllerProvider = provider8;
    }

    public AssistHandleBehaviorController get() {
        return provideInstance(this.contextProvider, this.assistUtilsProvider, this.handlerProvider, this.screenDecorationsProvider, this.deviceConfigHelperProvider, this.behaviorMapProvider, this.navigationModeControllerProvider, this.dumpControllerProvider);
    }

    public static AssistHandleBehaviorController provideInstance(Provider<Context> provider, Provider<AssistUtils> provider2, Provider<Handler> provider3, Provider<ScreenDecorations> provider4, Provider<DeviceConfigHelper> provider5, Provider<Map<AssistHandleBehavior, AssistHandleBehaviorController.BehaviorController>> provider6, Provider<NavigationModeController> provider7, Provider<DumpController> provider8) {
        return new AssistHandleBehaviorController(provider.get(), provider2.get(), provider3.get(), provider4, provider5.get(), provider6.get(), provider7.get(), provider8.get());
    }

    public static AssistHandleBehaviorController_Factory create(Provider<Context> provider, Provider<AssistUtils> provider2, Provider<Handler> provider3, Provider<ScreenDecorations> provider4, Provider<DeviceConfigHelper> provider5, Provider<Map<AssistHandleBehavior, AssistHandleBehaviorController.BehaviorController>> provider6, Provider<NavigationModeController> provider7, Provider<DumpController> provider8) {
        return new AssistHandleBehaviorController_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }
}
