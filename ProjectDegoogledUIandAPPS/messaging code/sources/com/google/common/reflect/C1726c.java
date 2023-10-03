package com.google.common.reflect;

import com.google.common.base.C1516M;
import com.google.common.collect.C1633Xa;
import com.google.common.collect.ImmutableMap;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

/* renamed from: com.google.common.reflect.c */
public final class C1726c {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(C1726c.class.getName());
    /* access modifiers changed from: private */

    /* renamed from: vO */
    public static final C1516M f2568vO = C1516M.m3974Ua(" ").mo8531Wk();

    static String getClassName(String str) {
        return str.substring(0, str.length() - 6).replace('/', '.');
    }

    static ImmutableMap getClassPathEntries(ClassLoader classLoader) {
        LinkedHashMap yl = C1633Xa.m4549yl();
        ClassLoader parent = classLoader.getParent();
        if (parent != null) {
            yl.putAll(getClassPathEntries(parent));
        }
        if (classLoader instanceof URLClassLoader) {
            URL[] uRLs = ((URLClassLoader) classLoader).getURLs();
            int length = uRLs.length;
            int i = 0;
            while (i < length) {
                try {
                    URI uri = uRLs[i].toURI();
                    if (!yl.containsKey(uri)) {
                        yl.put(uri, classLoader);
                    }
                    i++;
                } catch (URISyntaxException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return ImmutableMap.m4212c(yl);
    }
}
