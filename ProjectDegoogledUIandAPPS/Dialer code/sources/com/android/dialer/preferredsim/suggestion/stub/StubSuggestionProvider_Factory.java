package com.android.dialer.preferredsim.suggestion.stub;

import dagger.internal.Factory;

public enum StubSuggestionProvider_Factory implements Factory<StubSuggestionProvider> {
    INSTANCE;

    public Object get() {
        return new StubSuggestionProvider();
    }
}
