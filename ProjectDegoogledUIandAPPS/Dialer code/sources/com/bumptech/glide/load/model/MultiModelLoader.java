package com.bumptech.glide.load.model;

import android.support.p000v4.util.Pools$Pool;
import android.support.p002v7.appcompat.R$style;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.ModelLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MultiModelLoader<Model, Data> implements ModelLoader<Model, Data> {
    private final Pools$Pool<List<Throwable>> exceptionListPool;
    private final List<ModelLoader<Model, Data>> modelLoaders;

    static class MultiFetcher<Data> implements DataFetcher<Data>, DataFetcher.DataCallback<Data> {
        private DataFetcher.DataCallback<? super Data> callback;
        private int currentIndex;
        private List<Throwable> exceptions;
        private final List<DataFetcher<Data>> fetchers;
        private Priority priority;
        private final Pools$Pool<List<Throwable>> throwableListPool;

        MultiFetcher(List<DataFetcher<Data>> list, Pools$Pool<List<Throwable>> pools$Pool) {
            this.throwableListPool = pools$Pool;
            if (!list.isEmpty()) {
                this.fetchers = list;
                this.currentIndex = 0;
                return;
            }
            throw new IllegalArgumentException("Must not be empty.");
        }

        private void startNextOrFail() {
            if (this.currentIndex < this.fetchers.size() - 1) {
                this.currentIndex++;
                Priority priority2 = this.priority;
                DataFetcher.DataCallback<? super Data> dataCallback = this.callback;
                this.priority = priority2;
                this.callback = dataCallback;
                this.exceptions = this.throwableListPool.acquire();
                this.fetchers.get(this.currentIndex).loadData(priority2, this);
                return;
            }
            R$style.checkNotNull(this.exceptions, "Argument must not be null");
            this.callback.onLoadFailed(new GlideException("Fetch failed", (List<Throwable>) new ArrayList(this.exceptions)));
        }

        public void cancel() {
            for (DataFetcher<Data> cancel : this.fetchers) {
                cancel.cancel();
            }
        }

        public void cleanup() {
            List<Throwable> list = this.exceptions;
            if (list != null) {
                this.throwableListPool.release(list);
            }
            this.exceptions = null;
            for (DataFetcher<Data> cleanup : this.fetchers) {
                cleanup.cleanup();
            }
        }

        public Class<Data> getDataClass() {
            return this.fetchers.get(0).getDataClass();
        }

        public DataSource getDataSource() {
            return this.fetchers.get(0).getDataSource();
        }

        public void loadData(Priority priority2, DataFetcher.DataCallback<? super Data> dataCallback) {
            this.priority = priority2;
            this.callback = dataCallback;
            this.exceptions = this.throwableListPool.acquire();
            this.fetchers.get(this.currentIndex).loadData(priority2, this);
        }

        public void onDataReady(Data data) {
            if (data != null) {
                this.callback.onDataReady(data);
            } else {
                startNextOrFail();
            }
        }

        public void onLoadFailed(Exception exc) {
            List<Throwable> list = this.exceptions;
            R$style.checkNotNull(list, "Argument must not be null");
            list.add(exc);
            startNextOrFail();
        }
    }

    MultiModelLoader(List<ModelLoader<Model, Data>> list, Pools$Pool<List<Throwable>> pools$Pool) {
        this.modelLoaders = list;
        this.exceptionListPool = pools$Pool;
    }

    public ModelLoader.LoadData<Data> buildLoadData(Model model, int i, int i2, Options options) {
        ModelLoader.LoadData buildLoadData;
        int size = this.modelLoaders.size();
        ArrayList arrayList = new ArrayList(size);
        Key key = null;
        for (int i3 = 0; i3 < size; i3++) {
            ModelLoader modelLoader = this.modelLoaders.get(i3);
            if (modelLoader.handles(model) && (buildLoadData = modelLoader.buildLoadData(model, i, i2, options)) != null) {
                key = buildLoadData.sourceKey;
                arrayList.add(buildLoadData.fetcher);
            }
        }
        if (!arrayList.isEmpty()) {
            return new ModelLoader.LoadData<>(key, new MultiFetcher(arrayList, this.exceptionListPool));
        }
        return null;
    }

    public boolean handles(Model model) {
        for (ModelLoader<Model, Data> handles : this.modelLoaders) {
            if (handles.handles(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String arrays = Arrays.toString(this.modelLoaders.toArray());
        StringBuilder sb = new StringBuilder(GeneratedOutlineSupport.outline1(arrays, 31));
        sb.append("MultiModelLoader{modelLoaders=");
        sb.append(arrays);
        sb.append('}');
        return sb.toString();
    }
}
