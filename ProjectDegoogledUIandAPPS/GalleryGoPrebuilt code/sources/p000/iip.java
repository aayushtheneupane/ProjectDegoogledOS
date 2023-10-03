package p000;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: iip */
/* compiled from: PG */
abstract class iip {

    /* renamed from: a */
    private static final Logger f14314a = Logger.getLogger(iie.class.getName());

    /* renamed from: b */
    private static final String f14315b = "ihi";

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract iij mo8740a();

    /* renamed from: a */
    static iij m13531a(Class cls) {
        String str;
        Class<iip> cls2 = iip.class;
        ClassLoader classLoader = cls2.getClassLoader();
        if (cls.equals(iij.class)) {
            str = f14315b;
        } else if (cls.getPackage().equals(cls2.getPackage())) {
            str = String.format("%s.BlazeGenerated%sLoader", new Object[]{cls.getPackage().getName(), cls.getSimpleName()});
        } else {
            throw new IllegalArgumentException(cls.getName());
        }
        try {
            return (iij) cls.cast(((iip) Class.forName(str, true, classLoader).getConstructor(new Class[0]).newInstance(new Object[0])).mo8740a());
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        } catch (IllegalAccessException e3) {
            throw new IllegalStateException(e3);
        } catch (InvocationTargetException e4) {
            throw new IllegalStateException(e4);
        } catch (ClassNotFoundException e5) {
            Iterator<S> it = ServiceLoader.load(cls2, classLoader).iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                try {
                    arrayList.add(cls.cast(((iip) it.next()).mo8740a()));
                } catch (ServiceConfigurationError e6) {
                    ServiceConfigurationError serviceConfigurationError = e6;
                    Logger logger = f14314a;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(cls.getSimpleName());
                    logger.logp(level, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", valueOf.length() == 0 ? new String("Unable to load ") : "Unable to load ".concat(valueOf), serviceConfigurationError);
                }
            }
            if (arrayList.size() == 1) {
                return (iij) arrayList.get(0);
            }
            if (arrayList.size() == 0) {
                return null;
            }
            try {
                return (iij) cls.getMethod("combine", new Class[]{Collection.class}).invoke((Object) null, new Object[]{arrayList});
            } catch (NoSuchMethodException e7) {
                throw new IllegalStateException(e7);
            } catch (IllegalAccessException e8) {
                throw new IllegalStateException(e8);
            } catch (InvocationTargetException e9) {
                throw new IllegalStateException(e9);
            }
        }
    }
}
