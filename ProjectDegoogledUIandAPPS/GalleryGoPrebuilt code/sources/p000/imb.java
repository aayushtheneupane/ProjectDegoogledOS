package p000;

/* renamed from: imb */
/* compiled from: PG */
public enum imb {
    DOUBLE(imc.DOUBLE, 1),
    FLOAT(imc.FLOAT, 5),
    INT64(imc.LONG, 0),
    UINT64(imc.LONG, 0),
    INT32(imc.INT, 0),
    FIXED64(imc.LONG, 1),
    FIXED32(imc.INT, 5),
    BOOL(imc.BOOLEAN, 0),
    STRING(imc.STRING, 2),
    GROUP(imc.MESSAGE, 3),
    MESSAGE(imc.MESSAGE, 2),
    BYTES(imc.BYTE_STRING, 2),
    UINT32(imc.INT, 0),
    ENUM(imc.ENUM, 0),
    SFIXED32(imc.INT, 5),
    SFIXED64(imc.LONG, 1),
    SINT32(imc.INT, 0),
    SINT64(imc.LONG, 0);
    

    /* renamed from: i */
    public final imc f14494i;

    /* renamed from: j */
    public final int f14495j;

    private imb(imc imc, int i) {
        this.f14494i = imc;
        this.f14495j = i;
    }
}
