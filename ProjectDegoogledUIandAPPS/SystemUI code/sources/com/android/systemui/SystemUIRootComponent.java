package com.android.systemui;

import com.android.systemui.Dependency;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.InjectionInflationController;
import com.android.systemui.util.leak.GarbageMonitor;

public interface SystemUIRootComponent {
    Dependency.DependencyInjector createDependency();

    FragmentService.FragmentCreator createFragmentCreator();

    GarbageMonitor createGarbageMonitor();

    InjectionInflationController.ViewCreator createViewCreator();

    ConfigurationController getConfigurationController();

    StatusBar.StatusBarInjector getStatusBarInjector();

    void inject(SystemUIAppComponentFactory systemUIAppComponentFactory);
}
