package com.google.common.collect;

import com.google.common.base.C1547v;
import java.util.Map;

enum Maps$EntryFunction implements C1547v {
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
