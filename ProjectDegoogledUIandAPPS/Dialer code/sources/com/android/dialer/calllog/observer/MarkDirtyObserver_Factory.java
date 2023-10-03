package com.android.dialer.calllog.observer;

import com.android.dialer.calllog.notifier.RefreshAnnotatedCallLogNotifier;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Provider;

public final class MarkDirtyObserver_Factory implements Factory<MarkDirtyObserver> {
    private final MembersInjector<MarkDirtyObserver> markDirtyObserverMembersInjector;
    private final Provider<RefreshAnnotatedCallLogNotifier> refreshAnnotatedCallLogNotifierProvider;

    public MarkDirtyObserver_Factory(MembersInjector<MarkDirtyObserver> membersInjector, Provider<RefreshAnnotatedCallLogNotifier> provider) {
        this.markDirtyObserverMembersInjector = membersInjector;
        this.refreshAnnotatedCallLogNotifierProvider = provider;
    }

    public Object get() {
        MembersInjector<MarkDirtyObserver> membersInjector = this.markDirtyObserverMembersInjector;
        MarkDirtyObserver markDirtyObserver = new MarkDirtyObserver(this.refreshAnnotatedCallLogNotifierProvider.get());
        MembersInjectors.injectMembers(membersInjector, markDirtyObserver);
        return markDirtyObserver;
    }
}
