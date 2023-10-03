package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.collect.MapMaker;

@Deprecated
abstract class GenericMapMaker<K0, V0> {
    MapMaker.RemovalListener<K0, V0> removalListener;

    enum NullListener implements MapMaker.RemovalListener<Object, Object> {
        INSTANCE;

        public void onRemoval(MapMaker.RemovalNotification<Object, Object> removalNotification) {
        }
    }

    GenericMapMaker() {
    }

    /* access modifiers changed from: package-private */
    public <K extends K0, V extends V0> MapMaker.RemovalListener<K, V> getRemovalListener() {
        return (MapMaker.RemovalListener) MoreObjects.firstNonNull(this.removalListener, NullListener.INSTANCE);
    }
}
