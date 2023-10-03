package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Map;

enum Maps$EntryFunction implements Function<Map.Entry<?, ?>, Object> {
    KEY {
        public Object apply(Object obj) {
            return ((Map.Entry) obj).getKey();
        }
    },
    VALUE {
        public Object apply(Object obj) {
            return ((Map.Entry) obj).getValue();
        }
    }
}
