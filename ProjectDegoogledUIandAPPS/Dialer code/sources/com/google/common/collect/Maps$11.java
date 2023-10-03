package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Map;

final class Maps$11 implements Function<Map.Entry<K, V1>, Map.Entry<K, V2>> {
    final /* synthetic */ Maps$EntryTransformer val$transformer;

    Maps$11(Maps$EntryTransformer maps$EntryTransformer) {
        this.val$transformer = maps$EntryTransformer;
    }

    public Object apply(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Maps$EntryTransformer maps$EntryTransformer = this.val$transformer;
        if (maps$EntryTransformer == null) {
            throw new NullPointerException();
        } else if (entry != null) {
            return new Maps$10(entry, maps$EntryTransformer);
        } else {
            throw new NullPointerException();
        }
    }
}
