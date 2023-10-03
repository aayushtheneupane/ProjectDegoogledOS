package com.bumptech.glide.load.model;

import android.support.p000v4.util.Pools$Pool;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelLoaderRegistry {
    private final ModelLoaderCache cache = new ModelLoaderCache();
    private final MultiModelLoaderFactory multiModelLoaderFactory;

    private static class ModelLoaderCache {
        private final Map<Class<?>, Entry<?>> cachedModelLoaders = new HashMap();

        private static class Entry<Model> {
            final List<ModelLoader<Model, ?>> loaders;

            public Entry(List<ModelLoader<Model, ?>> list) {
                this.loaders = list;
            }
        }

        ModelLoaderCache() {
        }

        public void clear() {
            this.cachedModelLoaders.clear();
        }

        public <Model> List<ModelLoader<Model, ?>> get(Class<Model> cls) {
            Entry entry = this.cachedModelLoaders.get(cls);
            if (entry == null) {
                return null;
            }
            return entry.loaders;
        }

        public <Model> void put(Class<Model> cls, List<ModelLoader<Model, ?>> list) {
            if (this.cachedModelLoaders.put(cls, new Entry(list)) != null) {
                String valueOf = String.valueOf(cls);
                throw new IllegalStateException(GeneratedOutlineSupport.outline4(valueOf.length() + 34, "Already cached loaders for model: ", valueOf));
            }
        }
    }

    public ModelLoaderRegistry(Pools$Pool<List<Throwable>> pools$Pool) {
        MultiModelLoaderFactory multiModelLoaderFactory2 = new MultiModelLoaderFactory(pools$Pool);
        this.multiModelLoaderFactory = multiModelLoaderFactory2;
    }

    public synchronized <Model, Data> void append(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        this.multiModelLoaderFactory.append(cls, cls2, modelLoaderFactory);
        this.cache.clear();
    }

    public synchronized List<Class<?>> getDataClasses(Class<?> cls) {
        return this.multiModelLoaderFactory.getDataClasses(cls);
    }

    public synchronized <A> List<ModelLoader<A, ?>> getModelLoaders(A a) {
        ArrayList arrayList;
        Class<?> cls = a.getClass();
        List<ModelLoader<Model, ?>> list = this.cache.get(cls);
        if (list == null) {
            list = Collections.unmodifiableList(this.multiModelLoaderFactory.build(cls));
            this.cache.put(cls, list);
        }
        int size = list.size();
        arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            ModelLoader modelLoader = list.get(i);
            if (modelLoader.handles(a)) {
                arrayList.add(modelLoader);
            }
        }
        return arrayList;
    }
}
