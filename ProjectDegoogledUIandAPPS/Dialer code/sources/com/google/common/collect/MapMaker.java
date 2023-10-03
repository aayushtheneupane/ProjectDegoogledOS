package com.google.common.collect;

import android.support.p002v7.appcompat.R$style;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.collect.MapMakerInternalMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class MapMaker {
    int concurrencyLevel = -1;
    int initialCapacity = -1;
    Equivalence<Object> keyEquivalence;
    MapMakerInternalMap.Strength keyStrength;
    boolean useCustomMap;
    MapMakerInternalMap.Strength valueStrength;

    /* access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength getKeyStrength() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.keyStrength, MapMakerInternalMap.Strength.STRONG);
    }

    /* access modifiers changed from: package-private */
    public MapMakerInternalMap.Strength getValueStrength() {
        return (MapMakerInternalMap.Strength) MoreObjects.firstNonNull(this.valueStrength, MapMakerInternalMap.Strength.STRONG);
    }

    public <K, V> ConcurrentMap<K, V> makeMap() {
        if (this.useCustomMap) {
            return MapMakerInternalMap.create(this);
        }
        int i = this.initialCapacity;
        if (i == -1) {
            i = 16;
        }
        int i2 = this.concurrencyLevel;
        if (i2 == -1) {
            i2 = 4;
        }
        return new ConcurrentHashMap(i, 0.75f, i2);
    }

    /* access modifiers changed from: package-private */
    public MapMaker setKeyStrength(MapMakerInternalMap.Strength strength) {
        MoreObjects.checkState(this.keyStrength == null, "Key strength was already set to %s", (Object) this.keyStrength);
        if (strength != null) {
            this.keyStrength = strength;
            if (strength != MapMakerInternalMap.Strength.STRONG) {
                this.useCustomMap = true;
            }
            return this;
        }
        throw new NullPointerException();
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        int i = this.initialCapacity;
        if (i != -1) {
            stringHelper.add("initialCapacity", i);
        }
        int i2 = this.concurrencyLevel;
        if (i2 != -1) {
            stringHelper.add("concurrencyLevel", i2);
        }
        MapMakerInternalMap.Strength strength = this.keyStrength;
        if (strength != null) {
            stringHelper.add("keyStrength", (Object) R$style.toLowerCase(strength.toString()));
        }
        MapMakerInternalMap.Strength strength2 = this.valueStrength;
        if (strength2 != null) {
            stringHelper.add("valueStrength", (Object) R$style.toLowerCase(strength2.toString()));
        }
        if (this.keyEquivalence != null) {
            stringHelper.addValue("keyEquivalence");
        }
        return stringHelper.toString();
    }
}
