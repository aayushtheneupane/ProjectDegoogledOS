package com.android.dialer.commandline;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

final class AutoValue_Arguments extends Arguments {
    private final ImmutableMap<String, String> flags;
    private final ImmutableList<String> positionals;

    AutoValue_Arguments(ImmutableMap<String, String> immutableMap, ImmutableList<String> immutableList) {
        if (immutableMap != null) {
            this.flags = immutableMap;
            if (immutableList != null) {
                this.positionals = immutableList;
                return;
            }
            throw new NullPointerException("Null positionals");
        }
        throw new NullPointerException("Null flags");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Arguments)) {
            return false;
        }
        Arguments arguments = (Arguments) obj;
        if (!this.flags.equals(((AutoValue_Arguments) arguments).flags) || !this.positionals.equals(((AutoValue_Arguments) arguments).positionals)) {
            return false;
        }
        return true;
    }

    public ImmutableMap<String, String> getFlags() {
        return this.flags;
    }

    public ImmutableList<String> getPositionals() {
        return this.positionals;
    }

    public int hashCode() {
        return this.positionals.hashCode() ^ ((this.flags.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Arguments{flags=");
        outline13.append(this.flags);
        outline13.append(", positionals=");
        return GeneratedOutlineSupport.outline11(outline13, this.positionals, "}");
    }
}
