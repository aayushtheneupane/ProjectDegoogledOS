package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: ds */
/* compiled from: PG */
public final class C0104ds {

    /* renamed from: b */
    private static final int[] f7262b = {0, 4, 8};

    /* renamed from: c */
    private static final SparseIntArray f7263c;

    /* renamed from: a */
    public final HashMap f7264a = new HashMap();

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        f7263c = sparseIntArray;
        int[] iArr = C0105dt.f7309a;
        sparseIntArray.append(40, 25);
        f7263c.append(41, 26);
        f7263c.append(43, 29);
        f7263c.append(44, 30);
        f7263c.append(49, 36);
        f7263c.append(48, 35);
        f7263c.append(26, 4);
        f7263c.append(25, 3);
        f7263c.append(23, 1);
        f7263c.append(56, 6);
        f7263c.append(57, 7);
        f7263c.append(30, 17);
        f7263c.append(31, 18);
        f7263c.append(32, 19);
        f7263c.append(0, 27);
        f7263c.append(45, 32);
        f7263c.append(46, 33);
        f7263c.append(29, 10);
        f7263c.append(28, 9);
        f7263c.append(60, 13);
        f7263c.append(63, 16);
        f7263c.append(61, 14);
        f7263c.append(58, 11);
        f7263c.append(62, 15);
        f7263c.append(59, 12);
        f7263c.append(52, 40);
        f7263c.append(38, 39);
        f7263c.append(37, 41);
        f7263c.append(51, 42);
        f7263c.append(36, 20);
        f7263c.append(50, 37);
        f7263c.append(27, 5);
        f7263c.append(39, 60);
        f7263c.append(47, 60);
        f7263c.append(42, 60);
        f7263c.append(24, 60);
        f7263c.append(22, 60);
        f7263c.append(5, 24);
        f7263c.append(7, 28);
        f7263c.append(18, 31);
        f7263c.append(19, 8);
        f7263c.append(6, 34);
        f7263c.append(8, 2);
        f7263c.append(3, 23);
        f7263c.append(4, 21);
        f7263c.append(2, 22);
        f7263c.append(9, 43);
        f7263c.append(21, 44);
        f7263c.append(16, 45);
        f7263c.append(17, 46);
        f7263c.append(14, 47);
        f7263c.append(15, 48);
        f7263c.append(10, 49);
        f7263c.append(11, 50);
        f7263c.append(12, 51);
        f7263c.append(13, 52);
        f7263c.append(20, 53);
        f7263c.append(53, 54);
        f7263c.append(33, 55);
        f7263c.append(54, 56);
        f7263c.append(34, 57);
        f7263c.append(55, 58);
        f7263c.append(35, 59);
        f7263c.append(1, 38);
    }

    /* renamed from: a */
    public final void mo4383a(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                    C0103dr drVar = new C0103dr((byte[]) null);
                    TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, C0105dt.f7310b);
                    int indexCount = obtainStyledAttributes.getIndexCount();
                    for (int i2 = 0; i2 < indexCount; i2++) {
                        int index = obtainStyledAttributes.getIndex(i2);
                        int i3 = f7263c.get(index);
                        if (i3 != 60) {
                            switch (i3) {
                                case 1:
                                    drVar.f7190p = m6551a(obtainStyledAttributes, index, drVar.f7190p);
                                    break;
                                case RecyclerView.SCROLL_STATE_SETTLING:
                                    drVar.f7143D = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7143D);
                                    break;
                                case 3:
                                    drVar.f7189o = m6551a(obtainStyledAttributes, index, drVar.f7189o);
                                    break;
                                case 4:
                                    drVar.f7188n = m6551a(obtainStyledAttributes, index, drVar.f7188n);
                                    break;
                                case 5:
                                    drVar.f7197w = obtainStyledAttributes.getString(index);
                                    break;
                                case 6:
                                    drVar.f7198x = obtainStyledAttributes.getDimensionPixelOffset(index, drVar.f7198x);
                                    break;
                                case 7:
                                    drVar.f7199y = obtainStyledAttributes.getDimensionPixelOffset(index, drVar.f7199y);
                                    break;
                                case 8:
                                    drVar.f7144E = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7144E);
                                    break;
                                case 9:
                                    drVar.f7188n = m6551a(obtainStyledAttributes, index, drVar.f7194t);
                                    break;
                                case 10:
                                    drVar.f7193s = m6551a(obtainStyledAttributes, index, drVar.f7193s);
                                    break;
                                case 11:
                                    drVar.f7150K = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7150K);
                                    break;
                                case 12:
                                    drVar.f7151L = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7151L);
                                    break;
                                case 13:
                                    drVar.f7147H = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7147H);
                                    break;
                                case 14:
                                    drVar.f7149J = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7149J);
                                    break;
                                case 15:
                                    drVar.f7152M = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7152M);
                                    break;
                                case 16:
                                    drVar.f7148I = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7148I);
                                    break;
                                case 17:
                                    drVar.f7179e = obtainStyledAttributes.getDimensionPixelOffset(index, drVar.f7179e);
                                    break;
                                case 18:
                                    drVar.f7180f = obtainStyledAttributes.getDimensionPixelOffset(index, drVar.f7180f);
                                    break;
                                case 19:
                                    drVar.f7181g = obtainStyledAttributes.getFloat(index, drVar.f7181g);
                                    break;
                                case 20:
                                    drVar.f7195u = obtainStyledAttributes.getFloat(index, drVar.f7195u);
                                    break;
                                case 21:
                                    drVar.f7177c = obtainStyledAttributes.getLayoutDimension(index, drVar.f7177c);
                                    break;
                                case 22:
                                    drVar.f7146G = obtainStyledAttributes.getInt(index, drVar.f7146G);
                                    drVar.f7146G = f7262b[drVar.f7146G];
                                    break;
                                case 23:
                                    drVar.f7176b = obtainStyledAttributes.getLayoutDimension(index, drVar.f7176b);
                                    break;
                                case 24:
                                    drVar.f7140A = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7140A);
                                    break;
                                case 25:
                                    drVar.f7182h = m6551a(obtainStyledAttributes, index, drVar.f7182h);
                                    break;
                                case 26:
                                    drVar.f7183i = m6551a(obtainStyledAttributes, index, drVar.f7183i);
                                    break;
                                case 27:
                                    drVar.f7200z = obtainStyledAttributes.getInt(index, drVar.f7200z);
                                    break;
                                case 28:
                                    drVar.f7141B = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7141B);
                                    break;
                                case 29:
                                    drVar.f7184j = m6551a(obtainStyledAttributes, index, drVar.f7184j);
                                    break;
                                case 30:
                                    drVar.f7185k = m6551a(obtainStyledAttributes, index, drVar.f7185k);
                                    break;
                                case 31:
                                    drVar.f7145F = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7145F);
                                    break;
                                case 32:
                                    drVar.f7191q = m6551a(obtainStyledAttributes, index, drVar.f7191q);
                                    break;
                                case 33:
                                    drVar.f7192r = m6551a(obtainStyledAttributes, index, drVar.f7192r);
                                    break;
                                case 34:
                                    drVar.f7142C = obtainStyledAttributes.getDimensionPixelSize(index, drVar.f7142C);
                                    break;
                                case 35:
                                    drVar.f7187m = m6551a(obtainStyledAttributes, index, drVar.f7187m);
                                    break;
                                case 36:
                                    drVar.f7186l = m6551a(obtainStyledAttributes, index, drVar.f7186l);
                                    break;
                                case 37:
                                    drVar.f7196v = obtainStyledAttributes.getFloat(index, drVar.f7196v);
                                    break;
                                case 38:
                                    drVar.f7178d = obtainStyledAttributes.getResourceId(index, drVar.f7178d);
                                    break;
                                case 39:
                                    drVar.f7154O = obtainStyledAttributes.getFloat(index, drVar.f7154O);
                                    break;
                                case 40:
                                    drVar.f7153N = obtainStyledAttributes.getFloat(index, drVar.f7153N);
                                    break;
                                case 41:
                                    drVar.f7155P = obtainStyledAttributes.getInt(index, drVar.f7155P);
                                    break;
                                case 42:
                                    drVar.f7156Q = obtainStyledAttributes.getInt(index, drVar.f7156Q);
                                    break;
                                case 43:
                                    drVar.f7157R = obtainStyledAttributes.getFloat(index, drVar.f7157R);
                                    break;
                                case 44:
                                    drVar.f7158S = true;
                                    drVar.f7159T = obtainStyledAttributes.getFloat(index, drVar.f7159T);
                                    break;
                                case 45:
                                    drVar.f7160U = obtainStyledAttributes.getFloat(index, drVar.f7160U);
                                    break;
                                case 46:
                                    drVar.f7161V = obtainStyledAttributes.getFloat(index, drVar.f7161V);
                                    break;
                                case 47:
                                    drVar.f7162W = obtainStyledAttributes.getFloat(index, drVar.f7162W);
                                    break;
                                case 48:
                                    drVar.f7163X = obtainStyledAttributes.getFloat(index, drVar.f7163X);
                                    break;
                                case 49:
                                    drVar.f7164Y = obtainStyledAttributes.getFloat(index, drVar.f7164Y);
                                    break;
                                case 50:
                                    drVar.f7165Z = obtainStyledAttributes.getFloat(index, drVar.f7165Z);
                                    break;
                                case 51:
                                    drVar.f7167aa = obtainStyledAttributes.getFloat(index, drVar.f7167aa);
                                    break;
                                case 52:
                                    drVar.f7168ab = obtainStyledAttributes.getFloat(index, drVar.f7168ab);
                                    break;
                                case 53:
                                    drVar.f7169ac = obtainStyledAttributes.getFloat(index, drVar.f7169ac);
                                    break;
                                default:
                                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + f7263c.get(index));
                                    break;
                            }
                        } else {
                            Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + f7263c.get(index));
                        }
                    }
                    obtainStyledAttributes.recycle();
                    if (name.equalsIgnoreCase("Guideline")) {
                        drVar.f7166a = true;
                    }
                    this.f7264a.put(Integer.valueOf(drVar.f7178d), drVar);
                }
            }
        } catch (XmlPullParserException e) {
            ifn.m12932a(e);
        } catch (IOException e2) {
            ifn.m12932a(e2);
        }
    }

    /* renamed from: a */
    private static int m6551a(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }
}
