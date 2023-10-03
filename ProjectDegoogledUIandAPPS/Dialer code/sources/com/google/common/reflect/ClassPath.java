package com.google.common.reflect;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Logger;

public final class ClassPath {
    /* access modifiers changed from: private */
    public static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.m71on(" ").omitEmptyStrings();
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ClassPath.class.getName());

    public static final class ClassInfo extends ResourceInfo {
        /* access modifiers changed from: private */
        public final String className;

        public String toString() {
            return this.className;
        }
    }

    public static class ResourceInfo {
        final ClassLoader loader;
        private final String resourceName;

        public boolean equals(Object obj) {
            if (!(obj instanceof ResourceInfo)) {
                return false;
            }
            ResourceInfo resourceInfo = (ResourceInfo) obj;
            if (!this.resourceName.equals(resourceInfo.resourceName) || this.loader != resourceInfo.loader) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public String toString() {
            return this.resourceName;
        }
    }

    static abstract class Scanner {
        private final Set<File> scannedUris = new HashSet();

        Scanner() {
        }

        static ImmutableMap<File, ClassLoader> getClassPathEntries(ClassLoader classLoader) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            ClassLoader parent = classLoader.getParent();
            if (parent != null) {
                linkedHashMap.putAll(getClassPathEntries(parent));
            }
            if (classLoader instanceof URLClassLoader) {
                for (URL url : ((URLClassLoader) classLoader).getURLs()) {
                    if (url.getProtocol().equals("file")) {
                        File file = ClassPath.toFile(url);
                        if (!linkedHashMap.containsKey(file)) {
                            linkedHashMap.put(file, classLoader);
                        }
                    }
                }
            }
            return ImmutableMap.copyOf(linkedHashMap);
        }

        static URL getClassPathEntry(File file, String str) throws MalformedURLException {
            return new URL(file.toURI().toURL(), str);
        }

        static ImmutableSet<File> getClassPathFromManifest(File file, Manifest manifest) {
            if (manifest == null) {
                return ImmutableSet.m86of();
            }
            ImmutableSet.Builder builder = ImmutableSet.builder();
            String value = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
            if (value != null) {
                for (String next : ClassPath.CLASS_PATH_ATTRIBUTE_SEPARATOR.split(value)) {
                    try {
                        URL classPathEntry = getClassPathEntry(file, next);
                        if (classPathEntry.getProtocol().equals("file")) {
                            builder.add((Object) ClassPath.toFile(classPathEntry));
                        }
                    } catch (MalformedURLException unused) {
                        Logger access$100 = ClassPath.logger;
                        access$100.warning("Invalid Class-Path entry: " + next);
                    }
                }
            }
            return builder.build();
        }

        /* access modifiers changed from: package-private */
        public final void scan(File file, ClassLoader classLoader) throws IOException {
            if (this.scannedUris.add(file.getCanonicalFile())) {
                try {
                    if (file.exists()) {
                        if (file.isDirectory()) {
                            scanDirectory(classLoader, file);
                            return;
                        }
                        try {
                            JarFile jarFile = new JarFile(file);
                            try {
                                UnmodifiableIterator<File> it = getClassPathFromManifest(file, jarFile.getManifest()).iterator();
                                while (it.hasNext()) {
                                    scan(it.next(), classLoader);
                                }
                                scanJarFile(classLoader, jarFile);
                            } finally {
                                try {
                                    jarFile.close();
                                } catch (IOException unused) {
                                }
                            }
                        } catch (IOException unused2) {
                        }
                    }
                } catch (SecurityException e) {
                    Logger access$100 = ClassPath.logger;
                    access$100.warning("Cannot access " + file + ": " + e);
                }
            }
        }

        /* access modifiers changed from: protected */
        public abstract void scanDirectory(ClassLoader classLoader, File file) throws IOException;

        /* access modifiers changed from: protected */
        public abstract void scanJarFile(ClassLoader classLoader, JarFile jarFile) throws IOException;
    }

    static {
        new Predicate<ClassInfo>() {
            public boolean apply(Object obj) {
                return ((ClassInfo) obj).className.indexOf(36) == -1;
            }
        };
    }

    static String getClassName(String str) {
        return str.substring(0, str.length() - 6).replace('/', '.');
    }

    static File toFile(URL url) {
        MoreObjects.checkArgument(url.getProtocol().equals("file"));
        try {
            return new File(url.toURI());
        } catch (URISyntaxException unused) {
            return new File(url.getPath());
        }
    }

    static final class DefaultScanner extends Scanner {
        private final SetMultimap<ClassLoader, String> resources = MultimapBuilder.hashKeys().linkedHashSetValues().build();

        DefaultScanner() {
        }

        /* access modifiers changed from: protected */
        public void scanDirectory(ClassLoader classLoader, File file) throws IOException {
            HashSet hashSet = new HashSet();
            hashSet.add(file.getCanonicalFile());
            scanDirectory(file, classLoader, "", hashSet);
        }

        /* access modifiers changed from: protected */
        public void scanJarFile(ClassLoader classLoader, JarFile jarFile) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                if (!nextElement.isDirectory() && !nextElement.getName().equals("META-INF/MANIFEST.MF")) {
                    this.resources.get(classLoader).add(nextElement.getName());
                }
            }
        }

        private void scanDirectory(File file, ClassLoader classLoader, String str, Set<File> set) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                ClassPath.logger.warning("Cannot read directory " + file);
                return;
            }
            for (File file2 : listFiles) {
                String name = file2.getName();
                if (file2.isDirectory()) {
                    File canonicalFile = file2.getCanonicalFile();
                    if (set.add(canonicalFile)) {
                        scanDirectory(canonicalFile, classLoader, GeneratedOutlineSupport.outline9(str, name, "/"), set);
                        set.remove(canonicalFile);
                    }
                } else {
                    String outline8 = GeneratedOutlineSupport.outline8(str, name);
                    if (!outline8.equals("META-INF/MANIFEST.MF")) {
                        this.resources.get(classLoader).add(outline8);
                    }
                }
            }
        }
    }
}
