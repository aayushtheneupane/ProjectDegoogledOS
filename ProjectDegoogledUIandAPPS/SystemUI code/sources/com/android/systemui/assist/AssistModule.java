package com.android.systemui.assist;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.slice.Clock;
import com.android.internal.app.AssistUtils;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.assist.AssistHandleBehaviorController;
import java.util.EnumMap;
import java.util.Map;

public abstract class AssistModule {
    static Handler provideBackgroundHandler() {
        HandlerThread handlerThread = new HandlerThread("AssistHandleThread");
        handlerThread.start();
        return handlerThread.getThreadHandler();
    }

    static Map<AssistHandleBehavior, AssistHandleBehaviorController.BehaviorController> provideAssistHandleBehaviorControllerMap(AssistHandleOffBehavior assistHandleOffBehavior, AssistHandleLikeHomeBehavior assistHandleLikeHomeBehavior, AssistHandleReminderExpBehavior assistHandleReminderExpBehavior) {
        EnumMap enumMap = new EnumMap(AssistHandleBehavior.class);
        enumMap.put(AssistHandleBehavior.OFF, assistHandleOffBehavior);
        enumMap.put(AssistHandleBehavior.LIKE_HOME, assistHandleLikeHomeBehavior);
        enumMap.put(AssistHandleBehavior.REMINDER_EXP, assistHandleReminderExpBehavior);
        return enumMap;
    }

    static ScreenDecorations provideScreenDecorations(Context context) {
        return (ScreenDecorations) SysUiServiceProvider.getComponent(context, ScreenDecorations.class);
    }

    static AssistUtils provideAssistUtils(Context context) {
        return new AssistUtils(context);
    }

    static Clock provideSystemClock() {
        return $$Lambda$WyKlJnsW9STKD48w13qf39mFKI.INSTANCE;
    }
}
