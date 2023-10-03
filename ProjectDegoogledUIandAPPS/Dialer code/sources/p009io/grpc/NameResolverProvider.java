package p009io.grpc;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import p009io.grpc.NameResolver;

/* renamed from: io.grpc.NameResolverProvider */
public abstract class NameResolverProvider extends NameResolver.Factory {
    private static final NameResolver.Factory factory = new NameResolverFactory(providers);
    private static final List<NameResolverProvider> providers;

    /* renamed from: io.grpc.NameResolverProvider$NameResolverFactory */
    private static class NameResolverFactory extends NameResolver.Factory {
        private final List<NameResolverProvider> providers;

        public NameResolverFactory(List<NameResolverProvider> list) {
            this.providers = list;
        }

        public String getDefaultScheme() {
            MoreObjects.checkState(!this.providers.isEmpty(), "No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration");
            return this.providers.get(0).getDefaultScheme();
        }

        public void newNameResolver(URI uri, Attributes attributes) {
            MoreObjects.checkState(!this.providers.isEmpty(), "No NameResolverProviders found via ServiceLoader, including for DNS. This is probably due to a broken build. If using ProGuard, check your configuration");
            for (NameResolverProvider newNameResolver : this.providers) {
                newNameResolver.newNameResolver(uri, attributes);
            }
        }
    }

    static {
        ClassLoader classLoader;
        if (isAndroid()) {
            classLoader = NameResolverProvider.class.getClassLoader();
        } else {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        providers = load(classLoader);
    }

    public static NameResolver.Factory asFactory() {
        return factory;
    }

    static NameResolverProvider create(Class<?> cls) {
        try {
            return (NameResolverProvider) cls.asSubclass(NameResolverProvider.class).newInstance();
        } catch (Throwable th) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Provider ");
            outline13.append(cls.getName());
            outline13.append(" could not be instantiated: ");
            outline13.append(th);
            throw new ServiceConfigurationError(outline13.toString(), th);
        }
    }

    public static Iterable<NameResolverProvider> getCandidatesViaHardCoded(ClassLoader classLoader) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(create(Class.forName("io.grpc.internal.DnsNameResolverProvider", true, classLoader)));
        } catch (ClassNotFoundException unused) {
        }
        return arrayList;
    }

    public static Iterable<NameResolverProvider> getCandidatesViaServiceLoader(ClassLoader classLoader) {
        return ServiceLoader.load(NameResolverProvider.class, classLoader);
    }

    private static boolean isAndroid() {
        try {
            Class.forName("android.app.Application", false, (ClassLoader) null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    static List<NameResolverProvider> load(ClassLoader classLoader) {
        Iterable<NameResolverProvider> iterable;
        boolean z = false;
        try {
            Class.forName("android.app.Application", false, (ClassLoader) null);
            z = true;
        } catch (Exception unused) {
        }
        if (z) {
            iterable = getCandidatesViaHardCoded(classLoader);
        } else {
            iterable = getCandidatesViaServiceLoader(classLoader);
        }
        ArrayList arrayList = new ArrayList();
        for (NameResolverProvider next : iterable) {
            if (next.isAvailable()) {
                arrayList.add(next);
            }
        }
        Collections.sort(arrayList, Collections.reverseOrder(new Comparator<NameResolverProvider>() {
            public int compare(Object obj, Object obj2) {
                return ((NameResolverProvider) obj).priority() - ((NameResolverProvider) obj2).priority();
            }
        }));
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: protected */
    public abstract boolean isAvailable();

    /* access modifiers changed from: protected */
    public abstract int priority();

    static NameResolver.Factory asFactory(List<NameResolverProvider> list) {
        return new NameResolverFactory(list);
    }
}
