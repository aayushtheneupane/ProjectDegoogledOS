package p000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: ige */
/* compiled from: PG */
public enum ige {
    MOTO("(MV)?IMG_(\\d+_\\d+)_BURST(\\d+)(_COVER)?(?:_[a-zA-Z0-9]+)?\\.(?:JPG|jpg|JPEG|jpeg)", (int) null, 2, 3, 4, (Integer) null),
    SAMSUNG("(\\d+_\\d+)_(\\d+)\\.(?:JPG|jpg|JPEG|jpeg)", "Samsung|SAMSUNG|samsung", 1, 2, (Integer) null, (Integer) null),
    LG("(\\d+_\\d+(?:\\(\\d+\\))?)_Burst(\\d+)\\.(?:JPG|jpg|JPEG|jpeg)", (int) null, 1, 2, (Integer) null, (Integer) null),
    HUAWEI("IMG_(\\d+_\\d+)_BURST(\\d+)(_COVER)?\\.(?:JPG|jpg|JPEG|jpeg)", (int) null, 1, 2, 3, (Integer) null),
    ONEPLUS("IMG_(\\d+_\\d+)_(\\d+)\\.(?:JPG|jpg|JPEG|jpeg)", "OnePlus|Oneplus|ONEPLUS|oneplus", 1, 2, (Integer) null, (Integer) null),
    PIXEL_NEW("(PXL|PIXEL)_([^\\.\\\\\\/]*)\\.(?:[^\\\\\\/\\d\\-]*)[\\-0](\\d+)(?:\\.([^\\\\\\/]*COVER)|(?:[^\\\\\\/]+))?\\.(?:JPG|jpg|JPEG|jpeg)$", "Google|google|GOOGLE", 2, 3, 4, (Integer) null),
    PIXEL("[0-9]+(XTR|IMG)_(\\d+)_BURST(\\d+)(_COVER)?\\.(?:JPG|jpg|JPEG|jpeg)", "Google|google|GOOGLE", 3, 2, 4),
    GOOGLE("[a-zA-Z0-9]+_(\\d+)_BURST(\\d+)(_COVER)?\\.(?:JPG|jpg|JPEG|jpeg)", (int) null, 2, 1, 3, (Integer) null),
    HTC("IMAG(\\d+)(?:\\(\\d+\\))?_BURST(\\d+)(_COVER)?\\.(?:JPG|jpg|JPEG|jpeg)", (int) null, 1, 2, 3, (Integer) null),
    LG_VERIZON_USA("(\\d+[a-zA-Z]?)_Burst(\\d+)\\.(?:JPG|jpg|JPEG|jpeg)", (int) null, 1, 2, (Integer) null, (Integer) null),
    LG_DOCOMO_JAPAN("IMG(\\d+)_Burst(\\d+)\\.(?:JPG|jpg|JPEG|jpeg)", (int) null, 1, 2, (Integer) null, (Integer) null);
    

    /* renamed from: f */
    public static final List f14060f = null;

    /* renamed from: a */
    public final String f14073a;

    /* renamed from: b */
    public final String f14074b;

    /* renamed from: c */
    public final Integer f14075c;

    /* renamed from: d */
    public final Integer f14076d;

    /* renamed from: e */
    public final Integer f14077e;

    static {
        f14060f = Collections.unmodifiableList(Arrays.asList(values()));
        m12968a(true);
        m12968a(false);
    }

    private ige(String str, String str2, Integer num, Integer num2, Integer num3, byte[] bArr) {
        this(r1, r2, str, str2, num, num2, num3);
    }

    private ige(String str, String str2, Integer num, Integer num2, Integer num3) {
        this.f14073a = str;
        this.f14074b = str2;
        this.f14075c = num;
        this.f14076d = num2;
        this.f14077e = num3;
    }

    /* renamed from: a */
    private static void m12968a(boolean z) {
        ArrayList arrayList = new ArrayList();
        for (ige ige : f14060f) {
            String str = ige.f14074b;
            if ((str != null && z) || (str == null && !z)) {
                arrayList.add(ige);
            }
        }
        Collections.unmodifiableList(arrayList);
    }
}
