package com.google.common.reflect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Logger;

public final class ClassPath {
    /* access modifiers changed from: private */
    public static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.m62on(" ").omitEmptyStrings();
    private static final Predicate<ClassInfo> IS_TOP_LEVEL = new Predicate<ClassInfo>() {
        public boolean apply(ClassInfo classInfo) {
            return classInfo.className.indexOf(36) == -1;
        }
    };
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ClassPath.class.getName());

    public static class ResourceInfo {
        final ClassLoader loader;
        private final String resourceName;

        /* renamed from: of */
        static ResourceInfo m72of(String str, ClassLoader classLoader) {
            if (str.endsWith(".class")) {
                return new ClassInfo(str, classLoader);
            }
            return new ResourceInfo(str, classLoader);
        }

        ResourceInfo(String str, ClassLoader classLoader) {
            Preconditions.checkNotNull(str);
            this.resourceName = str;
            Preconditions.checkNotNull(classLoader);
            this.loader = classLoader;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

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

        public String toString() {
            return this.resourceName;
        }
    }

    public static final class ClassInfo extends ResourceInfo {
        /* access modifiers changed from: private */
        public final String className;

        ClassInfo(String str, ClassLoader classLoader) {
            super(str, classLoader);
            this.className = ClassPath.getClassName(str);
        }

        public String toString() {
            return this.className;
        }
    }

    static ImmutableMap<URI, ClassLoader> getClassPathEntries(ClassLoader classLoader) {
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        ClassLoader parent = classLoader.getParent();
        if (parent != null) {
            newLinkedHashMap.putAll(getClassPathEntries(parent));
        }
        if (classLoader instanceof URLClassLoader) {
            URL[] uRLs = ((URLClassLoader) classLoader).getURLs();
            int length = uRLs.length;
            int i = 0;
            while (i < length) {
                try {
                    URI uri = uRLs[i].toURI();
                    if (!newLinkedHashMap.containsKey(uri)) {
                        newLinkedHashMap.put(uri, classLoader);
                    }
                    i++;
                } catch (URISyntaxException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return ImmutableMap.copyOf(newLinkedHashMap);
    }

    static final class Scanner {
        private final ImmutableSortedSet.Builder<ResourceInfo> resources = new ImmutableSortedSet.Builder<>(Ordering.usingToString());
        private final Set<URI> scannedUris = Sets.newHashSet();

        Scanner() {
        }

        /* access modifiers changed from: package-private */
        public void scan(URI uri, ClassLoader classLoader) throws IOException {
            if (uri.getScheme().equals("file") && this.scannedUris.add(uri)) {
                scanFrom(new File(uri), classLoader);
            }
        }

        /* access modifiers changed from: package-private */
        public void scanFrom(File file, ClassLoader classLoader) throws IOException {
            if (file.exists()) {
                if (file.isDirectory()) {
                    scanDirectory(file, classLoader);
                } else {
                    scanJar(file, classLoader);
                }
            }
        }

        private void scanDirectory(File file, ClassLoader classLoader) throws IOException {
            scanDirectory(file, classLoader, "", ImmutableSet.m69of());
        }

        private void scanDirectory(File file, ClassLoader classLoader, String str, ImmutableSet<File> immutableSet) throws IOException {
            File canonicalFile = file.getCanonicalFile();
            if (!immutableSet.contains(canonicalFile)) {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    ClassPath.logger.warning("Cannot read directory " + file);
                    return;
                }
                ImmutableSet build = ImmutableSet.builder().addAll(immutableSet).add((Object) canonicalFile).build();
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (file2.isDirectory()) {
                        scanDirectory(file2, classLoader, str + name + "/", build);
                    } else {
                        String str2 = str + name;
                        if (!str2.equals("META-INF/MANIFEST.MF")) {
                            this.resources.add((Object) ResourceInfo.m72of(str2, classLoader));
                        }
                    }
                }
            }
        }

        private void scanJar(File file, ClassLoader classLoader) throws IOException {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    UnmodifiableIterator<URI> it = getClassPathFromManifest(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        scan(it.next(), classLoader);
                    }
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry nextElement = entries.nextElement();
                        if (!nextElement.isDirectory()) {
                            if (!nextElement.getName().equals("META-INF/MANIFEST.MF")) {
                                this.resources.add((Object) ResourceInfo.m72of(nextElement.getName(), classLoader));
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

        static ImmutableSet<URI> getClassPathFromManifest(File file, Manifest manifest) {
            if (manifest == null) {
                return ImmutableSet.m69of();
            }
            ImmutableSet.Builder builder = ImmutableSet.builder();
            String value = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
            if (value != null) {
                for (String next : ClassPath.CLASS_PATH_ATTRIBUTE_SEPARATOR.split(value)) {
                    try {
                        builder.add((Object) getClassPathEntry(file, next));
                    } catch (URISyntaxException unused) {
                        Logger access$100 = ClassPath.logger;
                        access$100.warning("Invalid Class-Path entry: " + next);
                    }
                }
            }
            return builder.build();
        }

        static URI getClassPathEntry(File file, String str) throws URISyntaxException {
            URI uri = new URI(str);
            if (uri.isAbsolute()) {
                return uri;
            }
            return new File(file.getParentFile(), str.replace('/', File.separatorChar)).toURI();
        }
    }

    static String getClassName(String str) {
        return str.substring(0, str.length() - 6).replace('/', '.');
    }
}
