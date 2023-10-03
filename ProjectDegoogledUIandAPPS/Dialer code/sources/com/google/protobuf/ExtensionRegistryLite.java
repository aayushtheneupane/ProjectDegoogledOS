package com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;

public class ExtensionRegistryLite {
    private static final ExtensionRegistryLite EMPTY = new ExtensionRegistryLite(true);

    ExtensionRegistryLite() {
        new HashMap();
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        return EMPTY;
    }

    private ExtensionRegistryLite(boolean z) {
        Collections.emptyMap();
    }
}
