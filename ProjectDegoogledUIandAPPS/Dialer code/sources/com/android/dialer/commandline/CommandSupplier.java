package com.android.dialer.commandline;

import com.android.dialer.function.Supplier;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;

@AutoValue
public abstract class CommandSupplier implements Supplier<ImmutableMap<String, Command>> {

    public static abstract class Builder {
        public abstract CommandSupplier build();

        /* access modifiers changed from: package-private */
        public abstract ImmutableMap.Builder<String, Command> commandsBuilder();
    }

    public abstract ImmutableMap<String, Command> commands();

    public Object get() {
        return commands();
    }
}
