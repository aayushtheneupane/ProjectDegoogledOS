package com.bumptech.glide.load.model;

import android.util.Base64;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class DataUrlLoader<Data> implements ModelLoader<String, Data> {
    private final DataDecoder<Data> dataDecoder;

    public interface DataDecoder<Data> {
    }

    private static final class DataUriFetcher<Data> implements DataFetcher<Data> {
        private Data data;
        private final String dataUri;
        private final DataDecoder<Data> reader;

        DataUriFetcher(String str, DataDecoder<Data> dataDecoder) {
            this.dataUri = str;
            this.reader = dataDecoder;
        }

        public void cancel() {
        }

        public void cleanup() {
            try {
                ((StreamFactory.C08211) this.reader).close(this.data);
            } catch (IOException unused) {
            }
        }

        public Class<Data> getDataClass() {
            return ((StreamFactory.C08211) this.reader).getDataClass();
        }

        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }

        public void loadData(Priority priority, DataFetcher.DataCallback<? super Data> dataCallback) {
            try {
                this.data = ((StreamFactory.C08211) this.reader).decode(this.dataUri);
                dataCallback.onDataReady(this.data);
            } catch (IllegalArgumentException e) {
                dataCallback.onLoadFailed(e);
            }
        }
    }

    public static final class StreamFactory implements ModelLoaderFactory<String, InputStream> {
        private final DataDecoder<InputStream> opener = new DataDecoder<InputStream>() {
            public void close(Object obj) throws IOException {
                ((InputStream) obj).close();
            }

            public Object decode(String str) throws IllegalArgumentException {
                if (str.startsWith("data:image")) {
                    int indexOf = str.indexOf(44);
                    if (indexOf == -1) {
                        throw new IllegalArgumentException("Missing comma in data URL.");
                    } else if (str.substring(0, indexOf).endsWith(";base64")) {
                        return new ByteArrayInputStream(Base64.decode(str.substring(indexOf + 1), 0));
                    } else {
                        throw new IllegalArgumentException("Not a base64 image data URL.");
                    }
                } else {
                    throw new IllegalArgumentException("Not a valid image data URL.");
                }
            }

            public Class<InputStream> getDataClass() {
                return InputStream.class;
            }
        };

        public ModelLoader<String, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new DataUrlLoader(this.opener);
        }
    }

    public DataUrlLoader(DataDecoder<Data> dataDecoder2) {
        this.dataDecoder = dataDecoder2;
    }

    public ModelLoader.LoadData buildLoadData(Object obj, int i, int i2, Options options) {
        String str = (String) obj;
        return new ModelLoader.LoadData(new ObjectKey(str), new DataUriFetcher(str, this.dataDecoder));
    }

    public boolean handles(Object obj) {
        return ((String) obj).startsWith("data:image");
    }
}
