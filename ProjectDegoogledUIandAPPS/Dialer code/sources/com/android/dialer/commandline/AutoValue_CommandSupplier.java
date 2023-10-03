package com.android.dialer.commandline;

import com.android.dialer.commandline.CommandSupplier;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableMap;

final class AutoValue_CommandSupplier extends CommandSupplier {
    private final ImmutableMap<String, Command> commands;

    static final class Builder extends CommandSupplier.Builder {
        private ImmutableMap<String, Command> commands;
        private ImmutableMap.Builder<String, Command> commandsBuilder$;

        Builder() {
        }

        public CommandSupplier build() {
            ImmutableMap.Builder<String, Command> builder = this.commandsBuilder$;
            if (builder != null) {
                this.commands = builder.build();
            } else if (this.commands == null) {
                this.commands = ImmutableMap.m82of();
            }
            return new AutoValue_CommandSupplier(this.commands, (C04441) null);
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap.Builder<String, Command> commandsBuilder() {
            if (this.commandsBuilder$ == null) {
                this.commandsBuilder$ = ImmutableMap.builder();
            }
            return this.commandsBuilder$;
        }
    }

    /* synthetic */ AutoValue_CommandSupplier(ImmutableMap immutableMap, C04441 r2) {
        this.commands = immutableMap;
    }

    public ImmutableMap<String, Command> commands() {
        return this.commands;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CommandSupplier) {
            return this.commands.equals(((CommandSupplier) obj).commands());
        }
        return false;
    }

    public int hashCode() {
        return this.commands.hashCode() ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("CommandSupplier{commands="), this.commands, "}");
    }
}
