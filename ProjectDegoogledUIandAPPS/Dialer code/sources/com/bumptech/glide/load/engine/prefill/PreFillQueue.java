package com.bumptech.glide.load.engine.prefill;

import java.util.ArrayList;
import java.util.Map;

final class PreFillQueue {
    private int bitmapsRemaining;

    public PreFillQueue(Map<PreFillType, Integer> map) {
        new ArrayList(map.keySet());
        for (Integer intValue : map.values()) {
            this.bitmapsRemaining = intValue.intValue() + this.bitmapsRemaining;
        }
    }
}
