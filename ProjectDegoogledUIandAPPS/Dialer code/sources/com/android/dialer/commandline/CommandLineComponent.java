package com.android.dialer.commandline;

import com.android.dialer.function.Supplier;
import com.google.common.collect.ImmutableMap;

public abstract class CommandLineComponent {

    public interface HasComponent {
    }

    public abstract Supplier<ImmutableMap<String, Command>> commandSupplier();
}
