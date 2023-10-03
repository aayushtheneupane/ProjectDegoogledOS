package com.bumptech.glide.load.data;

import android.support.p002v7.appcompat.R$style;
import com.bumptech.glide.load.data.DataRewinder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataRewinderRegistry {
    private static final DataRewinder.Factory<?> DEFAULT_FACTORY = new DataRewinder.Factory<Object>() {
        public DataRewinder<Object> build(Object obj) {
            return new DefaultRewinder(obj);
        }

        public Class<Object> getDataClass() {
            throw new UnsupportedOperationException("Not implemented");
        }
    };
    private final Map<Class<?>, DataRewinder.Factory<?>> rewinders = new HashMap();

    private static final class DefaultRewinder implements DataRewinder<Object> {
        private final Object data;

        DefaultRewinder(Object obj) {
            this.data = obj;
        }

        public void cleanup() {
        }

        public Object rewindAndGet() {
            return this.data;
        }
    }

    public synchronized <T> DataRewinder<T> build(T t) {
        DataRewinder.Factory<?> factory;
        R$style.checkNotNull(t, "Argument must not be null");
        factory = this.rewinders.get(t.getClass());
        if (factory == null) {
            Iterator<DataRewinder.Factory<?>> it = this.rewinders.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DataRewinder.Factory<?> next = it.next();
                if (next.getDataClass().isAssignableFrom(t.getClass())) {
                    factory = next;
                    break;
                }
            }
        }
        if (factory == null) {
            factory = DEFAULT_FACTORY;
        }
        return factory.build(t);
    }

    public synchronized void register(DataRewinder.Factory<?> factory) {
        this.rewinders.put(factory.getDataClass(), factory);
    }
}
