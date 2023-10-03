package com.bumptech.glide.load.engine;

import android.util.Log;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.LogTime;
import java.util.Collections;
import java.util.List;

class SourceGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object>, DataFetcherGenerator.FetcherReadyCallback {

    /* renamed from: cb */
    private final DataFetcherGenerator.FetcherReadyCallback f63cb;
    private Object dataToCache;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int loadDataListIndex;
    private DataCacheKey originalKey;
    private DataCacheGenerator sourceCacheGenerator;

    SourceGenerator(DecodeHelper<?> decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.helper = decodeHelper;
        this.f63cb = fetcherReadyCallback;
    }

    public void cancel() {
        ModelLoader.LoadData<?> loadData2 = this.loadData;
        if (loadData2 != null) {
            loadData2.fetcher.cancel();
        }
    }

    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        this.f63cb.onDataFetcherFailed(key, exc, dataFetcher, this.loadData.fetcher.getDataSource());
    }

    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.f63cb.onDataFetcherReady(key, obj, dataFetcher, this.loadData.fetcher.getDataSource(), key);
    }

    public void onDataReady(Object obj) {
        DiskCacheStrategy diskCacheStrategy = this.helper.getDiskCacheStrategy();
        if (obj == null || !diskCacheStrategy.isDataCacheable(this.loadData.fetcher.getDataSource())) {
            this.f63cb.onDataFetcherReady(this.loadData.sourceKey, obj, this.loadData.fetcher, this.loadData.fetcher.getDataSource(), this.originalKey);
            return;
        }
        this.dataToCache = obj;
        this.f63cb.reschedule();
    }

    public void onLoadFailed(Exception exc) {
        this.f63cb.onDataFetcherFailed(this.originalKey, exc, this.loadData.fetcher, this.loadData.fetcher.getDataSource());
    }

    public void reschedule() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: finally extract failed */
    public boolean startNext() {
        Object obj = this.dataToCache;
        if (obj != null) {
            this.dataToCache = null;
            long logTime = LogTime.getLogTime();
            try {
                Encoder<X> sourceEncoder = this.helper.getSourceEncoder(obj);
                DataCacheWriter dataCacheWriter = new DataCacheWriter(sourceEncoder, obj, this.helper.getOptions());
                this.originalKey = new DataCacheKey(this.loadData.sourceKey, this.helper.getSignature());
                this.helper.getDiskCache().put(this.originalKey, dataCacheWriter);
                if (Log.isLoggable("SourceGenerator", 2)) {
                    String valueOf = String.valueOf(this.originalKey);
                    String valueOf2 = String.valueOf(obj);
                    String valueOf3 = String.valueOf(sourceEncoder);
                    double elapsedMillis = LogTime.getElapsedMillis(logTime);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 95 + valueOf2.length() + valueOf3.length());
                    sb.append("Finished encoding source to cache, key: ");
                    sb.append(valueOf);
                    sb.append(", data: ");
                    sb.append(valueOf2);
                    sb.append(", encoder: ");
                    sb.append(valueOf3);
                    sb.append(", duration: ");
                    sb.append(elapsedMillis);
                    Log.v("SourceGenerator", sb.toString());
                }
                this.loadData.fetcher.cleanup();
                this.sourceCacheGenerator = new DataCacheGenerator(Collections.singletonList(this.loadData.sourceKey), this.helper, this);
            } catch (Throwable th) {
                this.loadData.fetcher.cleanup();
                throw th;
            }
        }
        DataCacheGenerator dataCacheGenerator = this.sourceCacheGenerator;
        if (dataCacheGenerator != null && dataCacheGenerator.startNext()) {
            return true;
        }
        this.sourceCacheGenerator = null;
        this.loadData = null;
        boolean z = false;
        while (!z) {
            if (!(this.loadDataListIndex < this.helper.getLoadData().size())) {
                break;
            }
            List<ModelLoader.LoadData<?>> loadData2 = this.helper.getLoadData();
            int i = this.loadDataListIndex;
            this.loadDataListIndex = i + 1;
            this.loadData = loadData2.get(i);
            if (this.loadData != null && (this.helper.getDiskCacheStrategy().isDataCacheable(this.loadData.fetcher.getDataSource()) || this.helper.hasLoadPath(this.loadData.fetcher.getDataClass()))) {
                this.loadData.fetcher.loadData(this.helper.getPriority(), this);
                z = true;
            }
        }
        return z;
    }
}
