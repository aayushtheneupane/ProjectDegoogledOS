package com.bumptech.glide.module;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public final class ManifestParser {
    private final Context context;

    public ManifestParser(Context context2) {
        this.context = context2;
    }

    private static GlideModule parseModule(String str) {
        try {
            Class<?> cls = Class.forName(str);
            try {
                Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                if (newInstance instanceof GlideModule) {
                    return (GlideModule) newInstance;
                }
                String valueOf = String.valueOf(newInstance);
                throw new RuntimeException(GeneratedOutlineSupport.outline4(valueOf.length() + 44, "Expected instanceof GlideModule, but found: ", valueOf));
            } catch (InstantiationException e) {
                throwInstantiateGlideModuleException(cls, e);
                throw null;
            } catch (IllegalAccessException e2) {
                throwInstantiateGlideModuleException(cls, e2);
                throw null;
            } catch (NoSuchMethodException e3) {
                throwInstantiateGlideModuleException(cls, e3);
                throw null;
            } catch (InvocationTargetException e4) {
                throwInstantiateGlideModuleException(cls, e4);
                throw null;
            }
        } catch (ClassNotFoundException e5) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
        }
    }

    private static void throwInstantiateGlideModuleException(Class<?> cls, Exception exc) {
        String valueOf = String.valueOf(cls);
        throw new RuntimeException(GeneratedOutlineSupport.outline4(valueOf.length() + 53, "Unable to instantiate GlideModule implementation for ", valueOf), exc);
    }

    public List<GlideModule> parse() {
        if (Log.isLoggable("ManifestParser", 3)) {
            Log.d("ManifestParser", "Loading Glide modules");
        }
        ArrayList arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                if (Log.isLoggable("ManifestParser", 3)) {
                    Log.d("ManifestParser", "Got null app info metadata");
                }
                return arrayList;
            }
            if (Log.isLoggable("ManifestParser", 2)) {
                String valueOf = String.valueOf(applicationInfo.metaData);
                StringBuilder sb = new StringBuilder(valueOf.length() + 23);
                sb.append("Got app info metadata: ");
                sb.append(valueOf);
                Log.v("ManifestParser", sb.toString());
            }
            for (String str : applicationInfo.metaData.keySet()) {
                if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                    arrayList.add(parseModule(str));
                    if (Log.isLoggable("ManifestParser", 3)) {
                        String valueOf2 = String.valueOf(str);
                        Log.d("ManifestParser", valueOf2.length() != 0 ? "Loaded Glide module: ".concat(valueOf2) : new String("Loaded Glide module: "));
                    }
                }
            }
            if (Log.isLoggable("ManifestParser", 3)) {
                Log.d("ManifestParser", "Finished loading Glide modules");
            }
            return arrayList;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
    }
}
