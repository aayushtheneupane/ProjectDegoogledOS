package com.google.common.collect;

import java.lang.reflect.Array;

final class Platform {
    static <T> T[] newArray(T[] tArr, int i) {
        return (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i);
    }

    static MapMaker tryWeakKeys(MapMaker mapMaker) {
        mapMaker.weakKeys();
        return mapMaker;
    }
}
