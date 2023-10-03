package com.google.common.collect;

final class MapMaker$RemovalNotification extends ImmutableEntry {
    private static final long serialVersionUID = 0;
    private final MapMaker$RemovalCause cause;

    MapMaker$RemovalNotification(Object obj, Object obj2, MapMaker$RemovalCause mapMaker$RemovalCause) {
        super(obj, obj2);
        this.cause = mapMaker$RemovalCause;
    }
}
