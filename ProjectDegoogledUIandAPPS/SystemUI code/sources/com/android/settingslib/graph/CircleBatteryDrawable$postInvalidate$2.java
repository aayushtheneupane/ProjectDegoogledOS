package com.android.settingslib.graph;

/* compiled from: CircleBatteryDrawable.kt */
final class CircleBatteryDrawable$postInvalidate$2 implements Runnable {
    final /* synthetic */ CircleBatteryDrawable this$0;

    CircleBatteryDrawable$postInvalidate$2(CircleBatteryDrawable circleBatteryDrawable) {
        this.this$0 = circleBatteryDrawable;
    }

    public final void run() {
        this.this$0.invalidateSelf();
    }
}
