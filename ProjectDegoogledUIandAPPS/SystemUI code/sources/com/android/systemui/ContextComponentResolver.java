package com.android.systemui;

import java.util.Map;
import javax.inject.Provider;

public class ContextComponentResolver implements ContextComponentHelper {
    private final Map<Class<?>, Provider<Object>> mCreators;

    ContextComponentResolver(Map<Class<?>, Provider<Object>> map) {
        this.mCreators = map;
    }
}
