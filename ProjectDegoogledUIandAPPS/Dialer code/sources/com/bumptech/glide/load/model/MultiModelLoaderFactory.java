package com.bumptech.glide.load.model;

import android.support.p000v4.util.Pools$Pool;
import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultiModelLoaderFactory {
    private static final Factory DEFAULT_FACTORY = new Factory();
    private static final ModelLoader<Object, Object> EMPTY_MODEL_LOADER = new EmptyModelLoader();
    private final Set<Entry<?, ?>> alreadyUsedEntries;
    private final List<Entry<?, ?>> entries;
    private final Factory factory;
    private final Pools$Pool<List<Throwable>> throwableListPool;

    private static class EmptyModelLoader implements ModelLoader<Object, Object> {
        EmptyModelLoader() {
        }

        public ModelLoader.LoadData<Object> buildLoadData(Object obj, int i, int i2, Options options) {
            return null;
        }

        public boolean handles(Object obj) {
            return false;
        }
    }

    private static class Entry<Model, Data> {
        final Class<Data> dataClass;
        final ModelLoaderFactory<? extends Model, ? extends Data> factory;
        private final Class<Model> modelClass;

        public Entry(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
            this.modelClass = cls;
            this.dataClass = cls2;
            this.factory = modelLoaderFactory;
        }

        public boolean handles(Class<?> cls) {
            return this.modelClass.isAssignableFrom(cls);
        }

        public boolean handles(Class<?> cls, Class<?> cls2) {
            return this.modelClass.isAssignableFrom(cls) && this.dataClass.isAssignableFrom(cls2);
        }
    }

    static class Factory {
        Factory() {
        }

        public <Model, Data> MultiModelLoader<Model, Data> build(List<ModelLoader<Model, Data>> list, Pools$Pool<List<Throwable>> pools$Pool) {
            return new MultiModelLoader<>(list, pools$Pool);
        }
    }

    public MultiModelLoaderFactory(Pools$Pool<List<Throwable>> pools$Pool) {
        this(pools$Pool, DEFAULT_FACTORY);
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model, Data> void append(Class<Model> cls, Class<Data> cls2, ModelLoaderFactory<? extends Model, ? extends Data> modelLoaderFactory) {
        Entry entry = new Entry(cls, cls2, modelLoaderFactory);
        List<Entry<?, ?>> list = this.entries;
        list.add(list.size(), entry);
    }

    /* access modifiers changed from: package-private */
    public synchronized <Model> List<ModelLoader<Model, ?>> build(Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (Entry next : this.entries) {
                if (!this.alreadyUsedEntries.contains(next)) {
                    if (next.handles(cls)) {
                        this.alreadyUsedEntries.add(next);
                        ModelLoader<? extends Model, ? extends Data> build = next.factory.build(this);
                        R$style.checkNotNull(build, "Argument must not be null");
                        arrayList.add(build);
                        this.alreadyUsedEntries.remove(next);
                    }
                }
            }
        } catch (Throwable th) {
            this.alreadyUsedEntries.clear();
            throw th;
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public synchronized List<Class<?>> getDataClasses(Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (Entry next : this.entries) {
            if (!arrayList.contains(next.dataClass) && next.handles(cls)) {
                arrayList.add(next.dataClass);
            }
        }
        return arrayList;
    }

    MultiModelLoaderFactory(Pools$Pool<List<Throwable>> pools$Pool, Factory factory2) {
        this.entries = new ArrayList();
        this.alreadyUsedEntries = new HashSet();
        this.throwableListPool = pools$Pool;
        this.factory = factory2;
    }

    public synchronized <Model, Data> ModelLoader<Model, Data> build(Class<Model> cls, Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (Entry next : this.entries) {
                if (this.alreadyUsedEntries.contains(next)) {
                    z = true;
                } else if (next.handles(cls, cls2)) {
                    this.alreadyUsedEntries.add(next);
                    ModelLoader<? extends Model, ? extends Data> build = next.factory.build(this);
                    R$style.checkNotNull(build, "Argument must not be null");
                    arrayList.add(build);
                    this.alreadyUsedEntries.remove(next);
                }
            }
            if (arrayList.size() > 1) {
                return this.factory.build(arrayList, this.throwableListPool);
            } else if (arrayList.size() == 1) {
                return (ModelLoader) arrayList.get(0);
            } else if (z) {
                return EMPTY_MODEL_LOADER;
            } else {
                throw new Registry.NoModelLoaderAvailableException(cls, cls2);
            }
        } catch (Throwable th) {
            this.alreadyUsedEntries.clear();
            throw th;
        }
    }
}
