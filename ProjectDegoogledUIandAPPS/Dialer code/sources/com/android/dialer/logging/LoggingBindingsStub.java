package com.android.dialer.logging;

import android.app.Activity;
import android.widget.QuickContactBadge;
import com.android.dialer.logging.LoggingBindings;
import java.util.Collection;

public class LoggingBindingsStub implements LoggingBindings {
    public void logAnnotatedCallLogMetrics(int i) {
    }

    public void logAnnotatedCallLogMetrics(int i, int i2) {
    }

    public void logCallImpression(DialerImpression$Type dialerImpression$Type, String str, long j) {
    }

    public void logContactsProviderMetrics(Collection<LoggingBindings.ContactsProviderMatchInfo> collection) {
    }

    public void logImpression(DialerImpression$Type dialerImpression$Type) {
    }

    public void logInteraction(InteractionEvent$Type interactionEvent$Type) {
    }

    public void logQuickContactOnTouch(QuickContactBadge quickContactBadge, InteractionEvent$Type interactionEvent$Type, boolean z) {
    }

    public void logScreenView(ScreenEvent$Type screenEvent$Type, Activity activity) {
    }

    public void logSpeedDialContactComposition(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
    }
}
