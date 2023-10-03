package com.android.dialer.simulator.impl;

import com.android.dialer.simulator.impl.SimulatorPortalEntryGroup;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

final class AutoValue_SimulatorPortalEntryGroup extends SimulatorPortalEntryGroup {
    private final ImmutableMap<String, Runnable> methods;
    private final ImmutableMap<String, SimulatorPortalEntryGroup> subPortals;

    static final class Builder extends SimulatorPortalEntryGroup.Builder {
        private ImmutableMap<String, Runnable> methods;
        private ImmutableMap<String, SimulatorPortalEntryGroup> subPortals;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public SimulatorPortalEntryGroup build() {
            String str = "";
            if (this.methods == null) {
                str = GeneratedOutlineSupport.outline8(str, " methods");
            }
            if (this.subPortals == null) {
                str = GeneratedOutlineSupport.outline8(str, " subPortals");
            }
            if (str.isEmpty()) {
                return new AutoValue_SimulatorPortalEntryGroup(this.methods, this.subPortals, (C05591) null);
            }
            throw new IllegalStateException(GeneratedOutlineSupport.outline8("Missing required properties:", str));
        }

        /* access modifiers changed from: package-private */
        public SimulatorPortalEntryGroup.Builder setMethods(Map<String, Runnable> map) {
            if (map != null) {
                this.methods = ImmutableMap.copyOf(map);
                return this;
            }
            throw new NullPointerException("Null methods");
        }

        /* access modifiers changed from: package-private */
        public SimulatorPortalEntryGroup.Builder setSubPortals(Map<String, SimulatorPortalEntryGroup> map) {
            if (map != null) {
                this.subPortals = ImmutableMap.copyOf(map);
                return this;
            }
            throw new NullPointerException("Null subPortals");
        }
    }

    /* synthetic */ AutoValue_SimulatorPortalEntryGroup(ImmutableMap immutableMap, ImmutableMap immutableMap2, C05591 r3) {
        this.methods = immutableMap;
        this.subPortals = immutableMap2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SimulatorPortalEntryGroup)) {
            return false;
        }
        SimulatorPortalEntryGroup simulatorPortalEntryGroup = (SimulatorPortalEntryGroup) obj;
        if (!this.methods.equals(((AutoValue_SimulatorPortalEntryGroup) simulatorPortalEntryGroup).methods) || !this.subPortals.equals(((AutoValue_SimulatorPortalEntryGroup) simulatorPortalEntryGroup).subPortals)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.subPortals.hashCode() ^ ((this.methods.hashCode() ^ 1000003) * 1000003);
    }

    /* access modifiers changed from: package-private */
    public ImmutableMap<String, Runnable> methods() {
        return this.methods;
    }

    /* access modifiers changed from: package-private */
    public ImmutableMap<String, SimulatorPortalEntryGroup> subPortals() {
        return this.subPortals;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SimulatorPortalEntryGroup{methods=");
        outline13.append(this.methods);
        outline13.append(", subPortals=");
        return GeneratedOutlineSupport.outline11(outline13, this.subPortals, "}");
    }
}
