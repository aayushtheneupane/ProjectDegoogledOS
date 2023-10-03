package p000;

/* renamed from: iin */
/* compiled from: PG */
public enum iin {
    DOUBLE(0, 1, iji.DOUBLE),
    FLOAT(1, 1, iji.FLOAT),
    INT64(2, 1, iji.LONG),
    UINT64(3, 1, iji.LONG),
    INT32(4, 1, iji.INT),
    FIXED64(5, 1, iji.LONG),
    FIXED32(6, 1, iji.INT),
    BOOL(7, 1, iji.BOOLEAN),
    STRING(8, 1, iji.STRING),
    MESSAGE(9, 1, iji.MESSAGE),
    BYTES(10, 1, iji.BYTE_STRING),
    UINT32(11, 1, iji.INT),
    ENUM(12, 1, iji.ENUM),
    SFIXED32(13, 1, iji.INT),
    SFIXED64(14, 1, iji.LONG),
    SINT32(15, 1, iji.INT),
    SINT64(16, 1, iji.LONG),
    GROUP(17, 1, iji.MESSAGE),
    DOUBLE_LIST(18, 2, iji.DOUBLE),
    FLOAT_LIST(19, 2, iji.FLOAT),
    INT64_LIST(20, 2, iji.LONG),
    UINT64_LIST(21, 2, iji.LONG),
    INT32_LIST(22, 2, iji.INT),
    FIXED64_LIST(23, 2, iji.LONG),
    FIXED32_LIST(24, 2, iji.INT),
    BOOL_LIST(25, 2, iji.BOOLEAN),
    STRING_LIST(26, 2, iji.STRING),
    MESSAGE_LIST(27, 2, iji.MESSAGE),
    BYTES_LIST(28, 2, iji.BYTE_STRING),
    UINT32_LIST(29, 2, iji.INT),
    ENUM_LIST(30, 2, iji.ENUM),
    SFIXED32_LIST(31, 2, iji.INT),
    SFIXED64_LIST(32, 2, iji.LONG),
    SINT32_LIST(33, 2, iji.INT),
    SINT64_LIST(34, 2, iji.LONG),
    DOUBLE_LIST_PACKED(35, 3, iji.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, iji.FLOAT),
    INT64_LIST_PACKED(37, 3, iji.LONG),
    UINT64_LIST_PACKED(38, 3, iji.LONG),
    INT32_LIST_PACKED(39, 3, iji.INT),
    FIXED64_LIST_PACKED(40, 3, iji.LONG),
    FIXED32_LIST_PACKED(41, 3, iji.INT),
    BOOL_LIST_PACKED(42, 3, iji.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, iji.INT),
    ENUM_LIST_PACKED(44, 3, iji.ENUM),
    SFIXED32_LIST_PACKED(45, 3, iji.INT),
    SFIXED64_LIST_PACKED(46, 3, iji.LONG),
    SINT32_LIST_PACKED(47, 3, iji.INT),
    SINT64_LIST_PACKED(48, 3, iji.LONG),
    GROUP_LIST(49, 2, iji.MESSAGE),
    MAP(50, 4, iji.VOID);
    

    /* renamed from: aa */
    private static final iin[] f14285aa = null;

    /* renamed from: c */
    public final int f14311c;

    static {
        int i;
        f14285aa = new iin[r1];
        for (iin iin : values()) {
            f14285aa[iin.f14311c] = iin;
        }
    }

    private iin(int i, int i2, iji iji) {
        this.f14311c = i;
        iji iji2 = iji.VOID;
        if (i2 == 1) {
            iji.ordinal();
        }
    }
}
