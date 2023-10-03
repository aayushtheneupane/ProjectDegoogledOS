package com.android.dialer.buildtype;

import com.android.dialer.common.LogUtil;

public class BuildType {
    private static int cachedBuildType;
    private static boolean didInitializeBuildType;

    public static synchronized int get() {
        int i;
        synchronized (BuildType.class) {
            if (!didInitializeBuildType) {
                didInitializeBuildType = true;
                try {
                    cachedBuildType = ((BuildTypeAccessor) Class.forName(BuildTypeAccessor.class.getName() + "Impl").getConstructor(new Class[0]).newInstance(new Object[0])).getBuildType();
                } catch (ReflectiveOperationException e) {
                    LogUtil.m7e("BuildType.get", "error creating BuildTypeAccessorImpl", (Throwable) e);
                    throw new AssertionError("Unable to get build type. To fix this error include one of the build type modules (bugfood, etc...) in your target.");
                }
            }
            i = cachedBuildType;
        }
        return i;
    }
}
