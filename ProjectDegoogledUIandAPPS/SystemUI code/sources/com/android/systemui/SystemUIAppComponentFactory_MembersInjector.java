package com.android.systemui;

import dagger.MembersInjector;

public final class SystemUIAppComponentFactory_MembersInjector implements MembersInjector<SystemUIAppComponentFactory> {
    public static void injectMComponentHelper(SystemUIAppComponentFactory systemUIAppComponentFactory, ContextComponentHelper contextComponentHelper) {
        systemUIAppComponentFactory.mComponentHelper = contextComponentHelper;
    }
}
