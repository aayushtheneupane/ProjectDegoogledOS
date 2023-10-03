package com.android.dialer.simulator.impl;

import com.android.dialer.simulator.impl.AutoValue_SimulatorPortalEntryGroup;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.Map;

@AutoValue
public abstract class SimulatorPortalEntryGroup {

    static abstract class Builder {
        Builder() {
        }

        /* access modifiers changed from: package-private */
        public abstract SimulatorPortalEntryGroup build();

        /* access modifiers changed from: package-private */
        public abstract Builder setMethods(Map<String, Runnable> map);

        /* access modifiers changed from: package-private */
        public abstract Builder setSubPortals(Map<String, SimulatorPortalEntryGroup> map);
    }

    static Builder builder() {
        AutoValue_SimulatorPortalEntryGroup.Builder builder = new AutoValue_SimulatorPortalEntryGroup.Builder();
        builder.setMethods(Collections.emptyMap());
        builder.setSubPortals(Collections.emptyMap());
        return builder;
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableMap<String, Runnable> methods();

    /* access modifiers changed from: package-private */
    public abstract ImmutableMap<String, SimulatorPortalEntryGroup> subPortals();
}
