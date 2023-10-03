package com.google.common.reflect;

import com.google.common.collect.C1624T;
import com.google.common.collect.C1628V;
import com.google.common.collect.C1644bb;
import com.google.common.collect.C1692rb;
import com.google.common.collect.ImmutableSet;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Logger;
import p026b.p027a.p030b.p031a.C0632a;

final class ClassPath$Scanner {

    /* renamed from: tO */
    private final C1628V f2566tO = new C1628V(C1644bb.m4561Bl());

    /* renamed from: uO */
    private final Set f2567uO = new HashSet();

    ClassPath$Scanner() {
    }

    static URI getClassPathEntry(File file, String str) {
        URI uri = new URI(str);
        if (uri.isAbsolute()) {
            return uri;
        }
        return new File(file.getParentFile(), str.replace('/', File.separatorChar)).toURI();
    }

    static ImmutableSet getClassPathFromManifest(File file, Manifest manifest) {
        if (manifest == null) {
            return ImmutableSet.m4235ql();
        }
        C1624T builder = ImmutableSet.builder();
        String value = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
        if (value != null) {
            for (String str : C1726c.f2568vO.split(value)) {
                try {
                    builder.add((Object) getClassPathEntry(file, str));
                } catch (URISyntaxException unused) {
                    Logger de = C1726c.logger;
                    de.warning("Invalid Class-Path entry: " + str);
                }
            }
        }
        return builder.build();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo9360a(URI uri, ClassLoader classLoader) {
        if (uri.getScheme().equals("file") && this.f2567uO.add(uri)) {
            scanFrom(new File(uri), classLoader);
        }
    }

    /* access modifiers changed from: package-private */
    public void scanFrom(File file, ClassLoader classLoader) {
        if (file.exists()) {
            if (file.isDirectory()) {
                m4654a(file, classLoader, "", ImmutableSet.m4235ql());
                return;
            }
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    C1692rb it = getClassPathFromManifest(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        mo9360a((URI) it.next(), classLoader);
                    }
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry nextElement = entries.nextElement();
                        if (!nextElement.isDirectory()) {
                            if (!nextElement.getName().equals("META-INF/MANIFEST.MF")) {
                                this.f2566tO.add((Object) C1725b.m4656a(nextElement.getName(), classLoader));
                            }
                        }
                    }
                } finally {
                    try {
                        jarFile.close();
                    } catch (IOException unused) {
                    }
                }
            } catch (IOException unused2) {
            }
        }
    }

    /* renamed from: a */
    private void m4654a(File file, ClassLoader classLoader, String str, ImmutableSet immutableSet) {
        Object obj;
        File canonicalFile = file.getCanonicalFile();
        if (!immutableSet.contains(canonicalFile)) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                C1726c.logger.warning("Cannot read directory " + file);
                return;
            }
            ImmutableSet build = ImmutableSet.builder().addAll(immutableSet).add((Object) canonicalFile).build();
            for (File file2 : listFiles) {
                String name = file2.getName();
                if (file2.isDirectory()) {
                    m4654a(file2, classLoader, C0632a.m1023d(str, name, "/"), build);
                } else {
                    String k = C0632a.m1025k(str, name);
                    if (!k.equals("META-INF/MANIFEST.MF")) {
                        C1628V v = this.f2566tO;
                        if (k.endsWith(".class")) {
                            obj = new C1724a(k, classLoader);
                        } else {
                            obj = new C1725b(k, classLoader);
                        }
                        v.add(obj);
                    }
                }
            }
        }
    }
}
