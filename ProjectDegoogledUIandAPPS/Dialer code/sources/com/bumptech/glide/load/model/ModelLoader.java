package com.bumptech.glide.load.model;

import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import java.util.Collections;
import java.util.List;

public interface ModelLoader<Model, Data> {

    public static class LoadData<Data> {
        public final List<Key> alternateKeys;
        public final DataFetcher<Data> fetcher;
        public final Key sourceKey;

        public LoadData(Key key, DataFetcher<Data> dataFetcher) {
            List<Key> emptyList = Collections.emptyList();
            R$style.checkNotNull(key, "Argument must not be null");
            this.sourceKey = key;
            R$style.checkNotNull(emptyList, "Argument must not be null");
            this.alternateKeys = emptyList;
            R$style.checkNotNull(dataFetcher, "Argument must not be null");
            this.fetcher = dataFetcher;
        }
    }

    LoadData<Data> buildLoadData(Model model, int i, int i2, Options options);

    boolean handles(Model model);
}
