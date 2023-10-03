package p000;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.nio.ByteBuffer;
import java.util.List;

/* renamed from: bbq */
/* compiled from: PG */
public final class bbq implements arb {

    /* renamed from: a */
    private static final bbp f1998a = new bbp();

    /* renamed from: b */
    private final Context f1999b;

    /* renamed from: c */
    private final List f2000c;

    /* renamed from: d */
    private final bbp f2001d;

    /* renamed from: e */
    private final bbr f2002e;

    public bbq(Context context, List list, auk auk, aui aui) {
        bbp bbp = f1998a;
        this.f1999b = context.getApplicationContext();
        this.f2000c = list;
        this.f2002e = new bbr(auk, aui);
        this.f2001d = bbp;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ aua mo1507a(Object obj, int i, int i2, aqz aqz) {
        aqb aqb;
        Bitmap.Config config;
        boolean z;
        boolean z2;
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        aqc a = this.f2001d.mo1778a(byteBuffer);
        try {
            bfk.m2412a();
            if (a.f1427b != null) {
                bbv bbv = null;
                int i3 = 0;
                if (a.mo1483e()) {
                    aqb = a.f1428c;
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i4 = 0; i4 < 6; i4++) {
                        sb.append((char) a.mo1481c());
                    }
                    if (sb.toString().startsWith("GIF")) {
                        a.f1428c.f1418f = a.mo1482d();
                        a.f1428c.f1419g = a.mo1482d();
                        int c = a.mo1481c();
                        aqb aqb2 = a.f1428c;
                        if ((c & 128) != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        aqb2.f1420h = z2;
                        aqb2.f1421i = (int) Math.pow(2.0d, (double) ((c & 7) + 1));
                        a.f1428c.f1422j = a.mo1481c();
                        a.f1428c.f1423k = a.mo1481c();
                        if (a.f1428c.f1420h && !a.mo1483e()) {
                            aqb aqb3 = a.f1428c;
                            aqb3.f1413a = a.mo1479a(aqb3.f1421i);
                            aqb aqb4 = a.f1428c;
                            aqb4.f1424l = aqb4.f1413a[aqb4.f1422j];
                        }
                    } else {
                        a.f1428c.f1414b = 1;
                    }
                    if (!a.mo1483e()) {
                        boolean z3 = false;
                        while (true) {
                            if (!z3) {
                                if (a.mo1483e()) {
                                    break;
                                }
                                int c2 = a.mo1481c();
                                if (c2 == 33) {
                                    int c3 = a.mo1481c();
                                    if (c3 == 1) {
                                        a.mo1478a();
                                    } else if (c3 == 249) {
                                        a.f1428c.f1416d = new aqa();
                                        a.mo1481c();
                                        int c4 = a.mo1481c();
                                        aqa aqa = a.f1428c.f1416d;
                                        int i5 = (c4 & 28) >> 2;
                                        aqa.f1408g = i5;
                                        if (i5 == 0) {
                                            aqa.f1408g = 1;
                                        }
                                        aqa.f1407f = (c4 & 1) != 0;
                                        int d = a.mo1482d();
                                        if (d < 2) {
                                            d = 10;
                                        }
                                        aqa aqa2 = a.f1428c.f1416d;
                                        aqa2.f1410i = d * 10;
                                        aqa2.f1409h = a.mo1481c();
                                        a.mo1481c();
                                    } else if (c3 == 254) {
                                        a.mo1478a();
                                    } else if (c3 != 255) {
                                        a.mo1478a();
                                    } else {
                                        a.mo1480b();
                                        StringBuilder sb2 = new StringBuilder();
                                        for (int i6 = 0; i6 < 11; i6++) {
                                            sb2.append((char) a.f1426a[i6]);
                                        }
                                        if (sb2.toString().equals("NETSCAPE2.0")) {
                                            while (true) {
                                                a.mo1480b();
                                                byte[] bArr = a.f1426a;
                                                if (bArr[0] == 1) {
                                                    a.f1428c.f1425m = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
                                                }
                                                if (a.f1429d > 0) {
                                                    if (a.mo1483e()) {
                                                        break;
                                                    }
                                                } else {
                                                    break;
                                                }
                                            }
                                        } else {
                                            a.mo1478a();
                                        }
                                    }
                                } else if (c2 == 44) {
                                    aqb aqb5 = a.f1428c;
                                    if (aqb5.f1416d == null) {
                                        aqb5.f1416d = new aqa();
                                    }
                                    a.f1428c.f1416d.f1402a = a.mo1482d();
                                    a.f1428c.f1416d.f1403b = a.mo1482d();
                                    a.f1428c.f1416d.f1404c = a.mo1482d();
                                    a.f1428c.f1416d.f1405d = a.mo1482d();
                                    int c5 = a.mo1481c();
                                    int i7 = c5 & 128;
                                    int pow = (int) Math.pow(2.0d, (double) ((c5 & 7) + 1));
                                    aqa aqa3 = a.f1428c.f1416d;
                                    if ((c5 & 64) != 0) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    aqa3.f1406e = z;
                                    if (i7 != 0) {
                                        aqa3.f1412k = a.mo1479a(pow);
                                    } else {
                                        aqa3.f1412k = null;
                                    }
                                    a.f1428c.f1416d.f1411j = a.f1427b.position();
                                    a.mo1481c();
                                    a.mo1478a();
                                    if (!a.mo1483e()) {
                                        aqb aqb6 = a.f1428c;
                                        aqb6.f1415c++;
                                        aqb6.f1417e.add(aqb6.f1416d);
                                    }
                                } else if (c2 != 59) {
                                    a.f1428c.f1414b = 1;
                                } else {
                                    z3 = true;
                                }
                            } else {
                                break;
                            }
                        }
                        aqb aqb7 = a.f1428c;
                        if (aqb7.f1415c < 0) {
                            aqb7.f1414b = 1;
                        }
                    }
                    aqb = a.f1428c;
                }
                if (aqb.f1415c > 0 && aqb.f1414b == 0) {
                    if (aqz.mo1502a(bcc.f2042a) != aqj.PREFER_RGB_565) {
                        config = Bitmap.Config.ARGB_8888;
                    } else {
                        config = Bitmap.Config.RGB_565;
                    }
                    int min = Math.min(aqb.f1419g / i2, aqb.f1418f / i);
                    if (min != 0) {
                        i3 = Integer.highestOneBit(min);
                    }
                    aqd aqd = new aqd(this.f2002e, aqb, byteBuffer, Math.max(1, i3));
                    if (config != Bitmap.Config.ARGB_8888) {
                        if (config != Bitmap.Config.RGB_565) {
                            String valueOf = String.valueOf(config);
                            String valueOf2 = String.valueOf(Bitmap.Config.ARGB_8888);
                            String valueOf3 = String.valueOf(Bitmap.Config.RGB_565);
                            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 41 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
                            sb3.append("Unsupported format: ");
                            sb3.append(valueOf);
                            sb3.append(", must be one of ");
                            sb3.append(valueOf2);
                            sb3.append(" or ");
                            sb3.append(valueOf3);
                            throw new IllegalArgumentException(sb3.toString());
                        }
                    }
                    aqd.f1439j = config;
                    aqd.mo1476a();
                    Bitmap b = aqd.mo1477b();
                    if (b != null) {
                        bbv = new bbv(new bbt(this.f1999b, aqd, azg.f1899b, i, i2, b));
                    }
                }
                this.f2001d.mo1779a(a);
                return bbv;
            }
            throw new IllegalStateException("You must call setData() before parseHeader()");
        } catch (Throwable th) {
            this.f2001d.mo1779a(a);
            throw th;
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1508a(Object obj, aqz aqz) {
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        if (((Boolean) aqz.mo1502a(bcc.f2043b)).booleanValue()) {
            return false;
        }
        if ((byteBuffer != null ? C0652xy.m16060a(this.f2000c, (aqt) new aqo(byteBuffer)) : ImageHeaderParser$ImageType.UNKNOWN) == ImageHeaderParser$ImageType.GIF) {
            return true;
        }
        return false;
    }
}
