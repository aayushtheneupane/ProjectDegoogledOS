package p003j$.util.stream;

import java.util.EnumMap;
import java.util.Map;
import p003j$.util.Map$$Dispatch;
import p003j$.util.Spliterator;

/* renamed from: j$.util.stream.StreamOpFlag */
enum StreamOpFlag {
    DISTINCT(0, r1),
    SORTED(1, r1),
    ORDERED(2, r1),
    SIZED(3, r1),
    SHORT_CIRCUIT(12, r1);
    
    private static final int FLAG_MASK = 0;
    private static final int FLAG_MASK_IS = 0;
    private static final int FLAG_MASK_NOT = 0;
    static final int INITIAL_OPS_VALUE = 0;
    static final int IS_SHORT_CIRCUIT = 0;
    static final int IS_SIZED = 0;
    static final int NOT_ORDERED = 0;
    static final int NOT_SIZED = 0;
    static final int OP_MASK = 0;
    static final int SPLITERATOR_CHARACTERISTICS_MASK = 0;
    static final int STREAM_MASK = 0;
    private final int bitPosition;
    private final int clear;
    private final Map maskTable;
    private final int preserve;
    private final int set;

    /* renamed from: j$.util.stream.StreamOpFlag$Type */
    enum Type {
        SPLITERATOR,
        STREAM,
        OP,
        TERMINAL_OP,
        UPSTREAM_TERMINAL_OP
    }

    static {
        SPLITERATOR_CHARACTERISTICS_MASK = createMask(Type.SPLITERATOR);
        STREAM_MASK = createMask(Type.STREAM);
        OP_MASK = createMask(Type.OP);
        createMask(Type.TERMINAL_OP);
        createMask(Type.UPSTREAM_TERMINAL_OP);
        FLAG_MASK = createFlagMask();
        int i = STREAM_MASK;
        FLAG_MASK_IS = i;
        int i2 = i << 1;
        FLAG_MASK_NOT = i2;
        INITIAL_OPS_VALUE = i | i2;
        StreamOpFlag streamOpFlag = DISTINCT;
        int i3 = streamOpFlag.set;
        int i4 = streamOpFlag.clear;
        StreamOpFlag streamOpFlag2 = SORTED;
        int i5 = streamOpFlag2.set;
        int i6 = streamOpFlag2.clear;
        StreamOpFlag streamOpFlag3 = ORDERED;
        int i7 = streamOpFlag3.set;
        NOT_ORDERED = streamOpFlag3.clear;
        StreamOpFlag streamOpFlag4 = SIZED;
        IS_SIZED = streamOpFlag4.set;
        NOT_SIZED = streamOpFlag4.clear;
        IS_SHORT_CIRCUIT = SHORT_CIRCUIT.set;
    }

    private static MaskBuilder set(Type type) {
        MaskBuilder maskBuilder = new MaskBuilder(new EnumMap(Type.class));
        maskBuilder.set(type);
        return maskBuilder;
    }

    /* renamed from: j$.util.stream.StreamOpFlag$MaskBuilder */
    class MaskBuilder {
        final Map map;

        MaskBuilder(Map map2) {
            this.map = map2;
        }

        /* access modifiers changed from: package-private */
        public MaskBuilder mask(Type type, Integer num) {
            this.map.put(type, num);
            return this;
        }

        /* access modifiers changed from: package-private */
        public MaskBuilder set(Type type) {
            mask(type, 1);
            return this;
        }

        /* access modifiers changed from: package-private */
        public MaskBuilder clear(Type type) {
            mask(type, 2);
            return this;
        }

        /* access modifiers changed from: package-private */
        public MaskBuilder setAndClear(Type type) {
            mask(type, 3);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Map build() {
            for (Type putIfAbsent : Type.values()) {
                Map$$Dispatch.putIfAbsent(this.map, putIfAbsent, 0);
            }
            return this.map;
        }
    }

    private StreamOpFlag(int i, MaskBuilder maskBuilder) {
        this.maskTable = maskBuilder.build();
        int i2 = i * 2;
        this.bitPosition = i2;
        this.set = 1 << i2;
        this.clear = 2 << i2;
        this.preserve = 3 << i2;
    }

    /* access modifiers changed from: package-private */
    public boolean isKnown(int i) {
        return (i & this.preserve) == this.set;
    }

    private static int createMask(Type type) {
        int i = 0;
        for (StreamOpFlag streamOpFlag : values()) {
            i |= ((Integer) streamOpFlag.maskTable.get(type)).intValue() << streamOpFlag.bitPosition;
        }
        return i;
    }

    private static int createFlagMask() {
        int i = 0;
        for (StreamOpFlag streamOpFlag : values()) {
            i |= streamOpFlag.preserve;
        }
        return i;
    }

    private static int getMask(int i) {
        if (i == 0) {
            return FLAG_MASK;
        }
        return (((i & FLAG_MASK_NOT) >> 1) | (((FLAG_MASK_IS & i) << 1) | i)) ^ -1;
    }

    static int combineOpFlags(int i, int i2) {
        return i | (i2 & getMask(i));
    }

    static int fromCharacteristics(Spliterator spliterator) {
        int characteristics = spliterator.characteristics();
        if ((characteristics & 4) == 0 || spliterator.getComparator() == null) {
            return SPLITERATOR_CHARACTERISTICS_MASK & characteristics;
        }
        return SPLITERATOR_CHARACTERISTICS_MASK & characteristics & -5;
    }
}
