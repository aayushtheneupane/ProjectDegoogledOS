package com.android.dialer.strictmode.impl;

import com.android.dialer.strictmode.impl.SystemDialerStrictMode;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Map;

final class AutoValue_SystemDialerStrictMode_StrictModeVmConfig extends SystemDialerStrictMode.StrictModeVmConfig {
    private final Map<Class<?>, Integer> maxInstanceLimits;

    static final class Builder extends SystemDialerStrictMode.StrictModeVmConfig.Builder {
        private Map<Class<?>, Integer> maxInstanceLimits;

        Builder() {
        }

        public SystemDialerStrictMode.StrictModeVmConfig build() {
            return new AutoValue_SystemDialerStrictMode_StrictModeVmConfig(this.maxInstanceLimits, (C05751) null);
        }
    }

    /* synthetic */ AutoValue_SystemDialerStrictMode_StrictModeVmConfig(Map map, C05751 r2) {
        this.maxInstanceLimits = map;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SystemDialerStrictMode.StrictModeVmConfig)) {
            return false;
        }
        Map<Class<?>, Integer> map = this.maxInstanceLimits;
        AutoValue_SystemDialerStrictMode_StrictModeVmConfig autoValue_SystemDialerStrictMode_StrictModeVmConfig = (AutoValue_SystemDialerStrictMode_StrictModeVmConfig) obj;
        if (map != null) {
            return map.equals(autoValue_SystemDialerStrictMode_StrictModeVmConfig.maxInstanceLimits);
        }
        if (autoValue_SystemDialerStrictMode_StrictModeVmConfig.maxInstanceLimits == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Map<Class<?>, Integer> map = this.maxInstanceLimits;
        return (map == null ? 0 : map.hashCode()) ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("StrictModeVmConfig{maxInstanceLimits="), this.maxInstanceLimits, "}");
    }
}
