package p000;

import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.p002v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.apps.photosgo.media.Filter$Category;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dcm */
/* compiled from: PG */
public class dcm {
    /* renamed from: a */
    public static void m5900a(PopupWindow popupWindow, boolean z) {
        int i = Build.VERSION.SDK_INT;
        popupWindow.setOverlapAnchor(z);
    }

    /* renamed from: a */
    public void mo4054a() {
    }

    /* renamed from: a */
    public void mo4055a(int i, int i2) {
    }

    /* renamed from: a */
    public void mo4056a(int i, int i2, Object obj) {
        throw null;
    }

    /* renamed from: b */
    public void mo4057b(int i, int i2) {
    }

    /* renamed from: c */
    public void mo4058c(int i, int i2) {
    }

    /* renamed from: a */
    public static void m5899a(PopupWindow popupWindow, int i) {
        int i2 = Build.VERSION.SDK_INT;
        popupWindow.setWindowLayoutType(i);
    }

    /* renamed from: a */
    public static Drawable[] m5903a(TextView textView) {
        int i = Build.VERSION.SDK_INT;
        return textView.getCompoundDrawablesRelative();
    }

    /* renamed from: a */
    public static void m5902a(TextView textView, Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        int i = Build.VERSION.SDK_INT;
        textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    /* renamed from: b */
    public static void m5905b(TextView textView, int i) {
        int i2;
        C0321lr.m14626a(i);
        if (Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i3 = Build.VERSION.SDK_INT;
        if (textView.getIncludeFontPadding()) {
            i2 = fontMetricsInt.top;
        } else {
            i2 = fontMetricsInt.ascent;
        }
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), i + i2, textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    /* renamed from: c */
    public static void m5906c(TextView textView, int i) {
        int i2;
        C0321lr.m14626a(i);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i3 = Build.VERSION.SDK_INT;
        if (textView.getIncludeFontPadding()) {
            i2 = fontMetricsInt.bottom;
        } else {
            i2 = fontMetricsInt.descent;
        }
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
        }
    }

    /* renamed from: d */
    public static void m5907d(TextView textView, int i) {
        C0321lr.m14626a(i);
        int fontMetricsInt = textView.getPaint().getFontMetricsInt((Paint.FontMetricsInt) null);
        if (i != fontMetricsInt) {
            textView.setLineSpacing((float) (i - fontMetricsInt), 1.0f);
        }
    }

    /* renamed from: a */
    public static void m5901a(TextView textView, int i) {
        int i2 = Build.VERSION.SDK_INT;
        textView.setTextAppearance(i);
    }

    /* renamed from: a */
    public static ActionMode.Callback m5891a(TextView textView, ActionMode.Callback callback) {
        int i = Build.VERSION.SDK_INT;
        return (Build.VERSION.SDK_INT > 27 || (callback instanceof C0376ns)) ? callback : new C0376ns(callback, textView);
    }

    /* renamed from: a */
    public static InputConnection m5892a(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        if (inputConnection != null && editorInfo.hintText == null) {
            ViewParent parent = view.getParent();
            while (true) {
                if (parent instanceof View) {
                    if (parent instanceof C0704zw) {
                        editorInfo.hintText = ((C0704zw) parent).mo10760a();
                        break;
                    }
                    parent = parent.getParent();
                } else {
                    break;
                }
            }
        }
        return inputConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0045 A[Catch:{ AssertionError | Exception -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x001e A[EDGE_INSN: B:36:0x001e->B:34:0x001e ?: BREAK  , SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.cye m5893a(p000.ebi r12, android.hardware.camera2.CameraManager r13) {
        /*
            r0 = 0
            cye r1 = new cye     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            r2.<init>()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            r3.<init>()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            java.lang.String[] r4 = r13.getCameraIdList()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r5 = r4.length     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            r6 = 0
        L_0x0013:
            if (r6 < r5) goto L_0x0086
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            r13.<init>()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            java.util.Iterator r4 = r3.iterator()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
        L_0x001e:
            boolean r5 = r4.hasNext()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            if (r5 == 0) goto L_0x0073
            java.lang.Object r5 = r4.next()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            android.graphics.Rect r5 = (android.graphics.Rect) r5     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r6 = r5.width()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            float r6 = (float) r6     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            r7 = 1028443341(0x3d4ccccd, float:0.05)
            float r6 = r6 * r7
            int r8 = r5.height()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            float r8 = (float) r8     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            float r8 = r8 * r7
            java.util.Iterator r7 = r2.iterator()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
        L_0x003f:
            boolean r9 = r7.hasNext()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            if (r9 == 0) goto L_0x001e
            java.lang.Object r9 = r7.next()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            android.graphics.Rect r9 = (android.graphics.Rect) r9     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r10 = r5.width()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r11 = r9.width()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r10 = r10 - r11
            int r10 = java.lang.Math.abs(r10)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            float r10 = (float) r10     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r10 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x006f
            int r10 = r5.height()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r9 = r9.height()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r10 = r10 - r9
            int r9 = java.lang.Math.abs(r10)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            float r9 = (float) r9     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r9 = (r9 > r8 ? 1 : (r9 == r8 ? 0 : -1))
            if (r9 > 0) goto L_0x003f
        L_0x006f:
            r13.add(r5)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            goto L_0x001e
        L_0x0073:
            r3.removeAll(r13)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            int r13 = r3.size()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            android.graphics.Rect[] r13 = new android.graphics.Rect[r13]     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            java.lang.Object[] r13 = r3.toArray(r13)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            android.graphics.Rect[] r13 = (android.graphics.Rect[]) r13     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            r1.<init>(r12, r13)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            return r1
        L_0x0086:
            r7 = r4[r6]     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            android.hardware.camera2.CameraCharacteristics r7 = r13.getCameraCharacteristics(r7)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            android.hardware.camera2.CameraCharacteristics$Key r8 = android.hardware.camera2.CameraCharacteristics.LENS_FACING     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            java.lang.Object r8 = r7.get(r8)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            android.hardware.camera2.CameraCharacteristics$Key r9 = android.hardware.camera2.CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            java.lang.Object r7 = r7.get(r9)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            android.graphics.Rect r7 = (android.graphics.Rect) r7     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            if (r7 != 0) goto L_0x009f
            goto L_0x00af
        L_0x009f:
            if (r8 == 0) goto L_0x00ac
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            if (r8 == 0) goto L_0x00a8
            goto L_0x00ac
        L_0x00a8:
            r3.add(r7)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
            goto L_0x00af
        L_0x00ac:
            r2.add(r7)     // Catch:{ Exception -> 0x00b5, AssertionError -> 0x00b3 }
        L_0x00af:
            int r6 = r6 + 1
            goto L_0x0013
        L_0x00b3:
            r13 = move-exception
            goto L_0x00b6
        L_0x00b5:
            r13 = move-exception
        L_0x00b6:
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "Error analysing camera dimensions. Selfie heuristics disabled."
            p000.cwn.m5515b((java.lang.Throwable) r13, (java.lang.String) r2, (java.lang.Object[]) r1)
            cye r13 = new cye
            android.graphics.Rect[] r0 = new android.graphics.Rect[r0]
            r13.<init>(r12, r0)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dcm.m5893a(ebi, android.hardware.camera2.CameraManager):cye");
    }

    /* renamed from: a */
    public static List m5898a(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (!cursor.moveToFirst()) {
            return arrayList;
        }
        cyx cyx = new cyx(cursor);
        do {
            iir g = cxi.f5908x.mo8793g();
            long j = cursor.getLong(cyx.f6054a);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi = (cxi) g.f14318b;
            cxi.f5909a |= 64;
            cxi.f5916h = j;
            long j2 = cursor.getLong(cyx.f6055b);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi2 = (cxi) g.f14318b;
            int i = 2;
            int i2 = cxi2.f5909a | 2;
            cxi2.f5909a = i2;
            cxi2.f5911c = j2;
            cxi2.f5909a = i2 | 4;
            cxi2.f5912d = false;
            cxh a = cyc.m5643a(cursor.getInt(cyx.f6056c));
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi3 = (cxi) g.f14318b;
            cxi3.f5913e = a.f5906d;
            cxi3.f5909a |= 8;
            String string = cursor.getString(cyx.f6057d);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi4 = (cxi) g.f14318b;
            string.getClass();
            cxi4.f5909a |= 16;
            cxi4.f5914f = string;
            String uri = cyc.m5647b(a, j2).toString();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi5 = (cxi) g.f14318b;
            uri.getClass();
            cxi5.f5909a |= 1;
            cxi5.f5910b = uri;
            if (!cursor.isNull(cyx.f6058e)) {
                long j3 = (long) cursor.getInt(cyx.f6058e);
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                cxi cxi6 = (cxi) g.f14318b;
                cxi6.f5909a |= 32;
                cxi6.f5915g = j3;
            }
            long j4 = cursor.getLong(cyx.f6059f);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi7 = (cxi) g.f14318b;
            cxi7.f5909a |= 256;
            cxi7.f5918j = j4;
            long j5 = cursor.getLong(cyx.f6060g);
            long j6 = cursor.getLong(cyx.f6061h);
            iir g2 = ehf.f8283d.mo8793g();
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            ehf ehf = (ehf) g2.f14318b;
            int i3 = ehf.f8285a | 1;
            ehf.f8285a = i3;
            ehf.f8286b = j5;
            ehf.f8285a = i3 | 2;
            ehf.f8287c = j6;
            ehf ehf2 = (ehf) g2.mo8770g();
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi8 = (cxi) g.f14318b;
            ehf2.getClass();
            cxi8.f5917i = ehf2;
            cxi8.f5909a |= 128;
            String string2 = cursor.getString(cyx.f6062i);
            String string3 = cursor.getString(cyx.f6063j);
            iir g3 = cxe.f5893f.mo8793g();
            if (g3.f14319c) {
                g3.mo8751b();
                g3.f14319c = false;
            }
            cxe cxe = (cxe) g3.f14318b;
            string3.getClass();
            cxe.f5895a |= 1;
            cxe.f5896b = string3;
            String string4 = cursor.getString(cyx.f6064k);
            if (((String) cyc.f5983a.mo2652a()).equals(string3)) {
                string4 = cyc.f5984b;
            }
            if (g3.f14319c) {
                g3.mo8751b();
                g3.f14319c = false;
            }
            cxe cxe2 = (cxe) g3.f14318b;
            string4.getClass();
            cxe2.f5895a |= 2;
            cxe2.f5897c = string4;
            String b = hpz.m11900b(new File(string2).getParent());
            if (g3.f14319c) {
                g3.mo8751b();
                g3.f14319c = false;
            }
            cxe cxe3 = (cxe) g3.f14318b;
            b.getClass();
            cxe3.f5895a |= 4;
            cxe3.f5898d = b;
            boolean contains = string2.contains("/DCIM/Camera/");
            if (g3.f14319c) {
                g3.mo8751b();
                g3.f14319c = false;
            }
            cxe cxe4 = (cxe) g3.f14318b;
            cxe4.f5895a |= 8;
            cxe4.f5899e = contains;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi9 = (cxi) g.f14318b;
            cxe cxe5 = (cxe) g3.mo8770g();
            cxe5.getClass();
            cxi9.f5919k = cxe5;
            cxi9.f5909a |= 512;
            long j7 = cursor.getLong(cyx.f6065l);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi10 = (cxi) g.f14318b;
            int i4 = cxi10.f5909a | 1024;
            cxi10.f5909a = i4;
            cxi10.f5920l = j7;
            string2.getClass();
            cxi10.f5909a = i4 | 2048;
            cxi10.f5921m = string2;
            int i5 = cursor.getInt(cyx.f6067n);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi11 = (cxi) g.f14318b;
            cxi11.f5909a |= 8192;
            cxi11.f5923o = i5;
            int i6 = cursor.getInt(cyx.f6068o);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi12 = (cxi) g.f14318b;
            cxi12.f5909a |= 16384;
            cxi12.f5924p = i6;
            int i7 = cursor.getInt(cyx.f6069p);
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi13 = (cxi) g.f14318b;
            cxi13.f5909a |= 32768;
            cxi13.f5925q = i7;
            if (!cursor.isNull(cyx.f6070q)) {
                String string5 = cursor.getString(cyx.f6070q);
                if (!"_".equals(string5)) {
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    cxi cxi14 = (cxi) g.f14318b;
                    string5.getClass();
                    cxi14.f5909a |= 65536;
                    cxi14.f5926r = string5;
                }
            }
            if (!cursor.isNull(cyx.f6071r)) {
                String string6 = cursor.getString(cyx.f6071r);
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                cxi cxi15 = (cxi) g.f14318b;
                string6.getClass();
                cxi15.f5909a |= 131072;
                cxi15.f5927s = string6;
            }
            int i8 = cursor.getInt(cursor.getColumnIndexOrThrow("aj"));
            if (i8 != 0) {
                i = i8 != 1 ? i8 != 2 ? i8 != 3 ? 1 : 5 : 4 : 3;
            }
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            cxi cxi16 = (cxi) g.f14318b;
            cxi16.f5928t = i - 1;
            cxi16.f5909a |= 262144;
            if (!cursor.isNull(cyx.f6066m)) {
                ihw a2 = ihw.m13162a(cursor.getBlob(cyx.f6066m));
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                cxi cxi17 = (cxi) g.f14318b;
                a2.getClass();
                cxi17.f5909a |= 4096;
                cxi17.f5922n = a2;
            }
            if (!cursor.isNull(cyx.f6072s)) {
                String string7 = cursor.getString(cyx.f6072s);
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                cxi cxi18 = (cxi) g.f14318b;
                string7.getClass();
                cxi18.f5909a |= 1048576;
                cxi18.f5930v = string7;
            }
            if (!cursor.isNull(cyx.f6073t)) {
                long j8 = cursor.getLong(cyx.f6073t);
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                cxi cxi19 = (cxi) g.f14318b;
                cxi19.f5909a |= 2097152;
                cxi19.f5931w = j8;
            }
            arrayList.add((cxi) g.mo8770g());
        } while (cursor.moveToNext());
        return arrayList;
    }

    /* renamed from: b */
    public static List m5904b(Cursor cursor) {
        int i;
        int i2;
        Cursor cursor2 = cursor;
        ArrayList arrayList = new ArrayList();
        if (!cursor.moveToFirst()) {
            return arrayList;
        }
        int columnIndexOrThrow = cursor2.getColumnIndexOrThrow("a");
        int columnIndexOrThrow2 = cursor2.getColumnIndexOrThrow("b");
        int columnIndexOrThrow3 = cursor2.getColumnIndexOrThrow("c");
        int columnIndexOrThrow4 = cursor2.getColumnIndexOrThrow("d");
        int columnIndexOrThrow5 = cursor2.getColumnIndexOrThrow("e");
        int columnIndexOrThrow6 = cursor2.getColumnIndexOrThrow("f");
        int columnIndexOrThrow7 = cursor2.getColumnIndexOrThrow("ah");
        int columnIndexOrThrow8 = cursor2.getColumnIndexOrThrow("g");
        int columnIndexOrThrow9 = cursor2.getColumnIndexOrThrow("i");
        int columnIndexOrThrow10 = cursor2.getColumnIndexOrThrow("j");
        int columnIndexOrThrow11 = cursor2.getColumnIndexOrThrow("k");
        int columnIndexOrThrow12 = cursor2.getColumnIndexOrThrow("l");
        int columnIndexOrThrow13 = cursor2.getColumnIndexOrThrow("m");
        int columnIndexOrThrow14 = cursor2.getColumnIndexOrThrow("n");
        ArrayList arrayList2 = arrayList;
        int columnIndexOrThrow15 = cursor2.getColumnIndexOrThrow("ai");
        int columnIndexOrThrow16 = cursor2.getColumnIndexOrThrow("aj");
        int columnIndexOrThrow17 = cursor2.getColumnIndexOrThrow("y");
        int columnIndexOrThrow18 = cursor2.getColumnIndexOrThrow("x");
        int columnIndexOrThrow19 = cursor2.getColumnIndexOrThrow("w");
        int columnIndexOrThrow20 = cursor2.getColumnIndexOrThrow("h");
        int columnIndexOrThrow21 = cursor2.getColumnIndexOrThrow("aa");
        int columnIndexOrThrow22 = cursor2.getColumnIndexOrThrow("ab");
        int columnIndexOrThrow23 = cursor2.getColumnIndexOrThrow("ac");
        int columnIndexOrThrow24 = cursor2.getColumnIndexOrThrow("af");
        int columnIndexOrThrow25 = cursor2.getColumnIndexOrThrow("ae");
        int columnIndexOrThrow26 = cursor2.getColumnIndexOrThrow("ad");
        int columnIndex = cursor2.getColumnIndex("v");
        int columnIndex2 = cursor2.getColumnIndex("ag");
        int columnIndexOrThrow27 = cursor2.getColumnIndexOrThrow("ak");
        int columnIndexOrThrow28 = cursor2.getColumnIndexOrThrow("ao");
        int columnIndexOrThrow29 = cursor2.getColumnIndexOrThrow("ap");
        int columnIndexOrThrow30 = cursor2.getColumnIndexOrThrow("aq");
        int columnIndexOrThrow31 = cursor2.getColumnIndexOrThrow("at");
        int columnIndexOrThrow32 = cursor2.getColumnIndexOrThrow("al");
        int columnIndexOrThrow33 = cursor2.getColumnIndexOrThrow("am");
        int columnIndexOrThrow34 = cursor2.getColumnIndexOrThrow("an");
        while (true) {
            int i3 = columnIndexOrThrow34;
            cyf R = cyg.m5687R();
            int i4 = columnIndexOrThrow;
            R.f6009a = Optional.m16285of(Long.valueOf(cursor2.getLong(columnIndexOrThrow)));
            int i5 = columnIndexOrThrow13;
            R.mo3976e(cursor2.getLong(columnIndexOrThrow2));
            R.mo3986h(cursor2.getInt(columnIndexOrThrow3));
            R.mo3977e(cursor2.getString(columnIndexOrThrow4));
            R.mo3955a(cursor2.getInt(columnIndexOrThrow5));
            R.mo3971d(cursor2.getInt(columnIndexOrThrow6));
            R.mo3960b(cursor2.getInt(columnIndexOrThrow7));
            R.mo3972d(cursor2.getLong(columnIndexOrThrow8));
            R.mo3968c(cursor2.getLong(columnIndexOrThrow9));
            R.mo3961b(cursor2.getLong(columnIndexOrThrow10));
            R.mo3980f(cursor2.getLong(columnIndexOrThrow11));
            R.mo3957a(cursor2.getString(columnIndexOrThrow12));
            R.mo3969c(cursor2.getString(i5));
            int i6 = columnIndexOrThrow14;
            R.mo3962b(cursor2.getString(i6));
            int i7 = columnIndexOrThrow15;
            int i8 = i5;
            R.mo3973d(cursor2.getString(i7));
            int i9 = columnIndexOrThrow16;
            int i10 = columnIndexOrThrow2;
            R.mo3975e(cursor2.getInt(i9));
            int i11 = columnIndexOrThrow21;
            int i12 = i9;
            int i13 = i11;
            R.mo3970c(cursor2.getInt(i11) == 1);
            int i14 = columnIndexOrThrow22;
            int i15 = columnIndexOrThrow3;
            R.mo3974d(cursor2.getInt(i14) == 1);
            int i16 = columnIndexOrThrow23;
            int i17 = i14;
            R.mo3985g(cursor2.getInt(i16) == 1);
            int i18 = columnIndexOrThrow24;
            int i19 = i16;
            R.mo3982f(cursor2.getInt(i18) == 1);
            int i20 = columnIndexOrThrow25;
            int i21 = i18;
            R.mo3987h(cursor2.getInt(i20) == 1);
            int i22 = columnIndexOrThrow26;
            int i23 = i20;
            R.mo3988i(cursor2.getInt(i22) == 1);
            int i24 = columnIndexOrThrow17;
            int i25 = i22;
            R.mo3978e(cursor2.getInt(i24) == 1);
            int i26 = columnIndexOrThrow18;
            R.mo3983g(cursor2.getInt(i26));
            int i27 = columnIndexOrThrow19;
            int i28 = i26;
            int i29 = cursor2.getInt(i27);
            int i30 = i27;
            boolean z = true;
            if (i29 != 1) {
                z = false;
            }
            R.mo3989j(z);
            R.mo3979f(cursor2.getInt(columnIndexOrThrow29));
            int i31 = columnIndexOrThrow33;
            R.mo3967c(cursor2.getInt(i31));
            int i32 = columnIndexOrThrow28;
            if (!cursor2.isNull(i32)) {
                columnIndexOrThrow28 = i32;
                R.f6010b = Optional.m16285of(Integer.valueOf(cursor2.getInt(i32)));
            } else {
                columnIndexOrThrow28 = i32;
            }
            int i33 = columnIndexOrThrow30;
            if (!cursor2.isNull(i33)) {
                columnIndexOrThrow33 = i31;
                columnIndexOrThrow30 = i33;
                R.f6011c = Optional.m16285of(Long.valueOf((long) cursor2.getInt(i33)));
            } else {
                columnIndexOrThrow30 = i33;
                columnIndexOrThrow33 = i31;
            }
            int i34 = columnIndexOrThrow20;
            if (!cursor2.isNull(i34)) {
                columnIndexOrThrow20 = i34;
                R.mo3956a((long) cursor2.getInt(i34));
            } else {
                columnIndexOrThrow20 = i34;
            }
            if (columnIndex >= 0) {
                i = columnIndex;
                if (!cursor2.isNull(i)) {
                    R.mo3964b(cursor2.getBlob(i));
                }
            } else {
                i = columnIndex;
            }
            if (columnIndex2 >= 0) {
                i2 = columnIndex2;
                if (!cursor2.isNull(i2)) {
                    columnIndex = i;
                    R.mo3959a(cursor2.getBlob(i2));
                } else {
                    columnIndex = i;
                }
            } else {
                columnIndex = i;
                i2 = columnIndex2;
            }
            int i35 = columnIndexOrThrow27;
            if (!cursor2.isNull(i35)) {
                columnIndex2 = i2;
                String string = cursor2.getString(i35);
                columnIndexOrThrow27 = i35;
                if (!"_".equals(string)) {
                    R.mo3984g(string);
                }
            } else {
                columnIndexOrThrow27 = i35;
                columnIndex2 = i2;
            }
            int i36 = columnIndexOrThrow31;
            if (!cursor2.isNull(i36)) {
                R.mo3981f(cursor2.getString(i36));
            }
            int i37 = columnIndexOrThrow32;
            if (!cursor2.isNull(i37)) {
                columnIndexOrThrow31 = i36;
                R.f6012d = Optional.m16285of(Long.valueOf(cursor2.getLong(i37)));
            } else {
                columnIndexOrThrow31 = i36;
            }
            int i38 = i3;
            if (!cursor2.isNull(i38)) {
                R.f6013e = Optional.m16285of(Integer.valueOf(cursor2.getInt(i38)));
            }
            cyg c = R.mo3966c();
            ArrayList arrayList3 = arrayList2;
            arrayList3.add(c);
            if (!cursor.moveToNext()) {
                return arrayList3;
            }
            cursor2 = cursor;
            arrayList2 = arrayList3;
            columnIndexOrThrow34 = i38;
            columnIndexOrThrow32 = i37;
            columnIndexOrThrow2 = i10;
            columnIndexOrThrow18 = i28;
            columnIndexOrThrow16 = i12;
            columnIndexOrThrow = i4;
            columnIndexOrThrow21 = i13;
            columnIndexOrThrow19 = i30;
            int i39 = i25;
            columnIndexOrThrow17 = i24;
            columnIndexOrThrow3 = i15;
            columnIndexOrThrow22 = i17;
            columnIndexOrThrow23 = i19;
            columnIndexOrThrow24 = i21;
            columnIndexOrThrow25 = i23;
            columnIndexOrThrow26 = i39;
            int i40 = i7;
            columnIndexOrThrow14 = i6;
            columnIndexOrThrow13 = i8;
            columnIndexOrThrow15 = i40;
        }
    }

    /* renamed from: a */
    public static hgn m5894a(hgn hgn, cxd cxd) {
        int i;
        String str;
        Filter$Category filter$Category;
        cwz cwz;
        Filter$Category filter$Category2 = Filter$Category.UNKNOWN_CATEGORY;
        int i2 = cxd.f5887b;
        if (i2 != 0) {
            i = i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 5 ? 0 : 4 : 3 : 2 : 1;
        } else {
            i = 5;
        }
        int i3 = i - 1;
        if (i != 0) {
            if (i3 != 0) {
                if (i3 == 1) {
                    if (i2 != 2 || (filter$Category = Filter$Category.forNumber(((Integer) cxd.f5888c).intValue())) == null) {
                        filter$Category = Filter$Category.UNKNOWN_CATEGORY;
                    }
                    switch (filter$Category.ordinal()) {
                        case 0:
                            cwn.m5510a("MediaDao: Ignoring UNKNOWN_CATEGORY in filter.", new Object[0]);
                            hgn.mo7409a(" WHERE 1 < 0");
                            break;
                        case 1:
                            if ((cxd.f5886a & 16) != 0) {
                                hgn.mo7409a(" JOIN ( ");
                                hgn.mo7409a(" SELECT DISTINCT c AS tf_a,h AS tf_b FROM ft WHERE h =? ");
                                hgn.mo7411b(cxd.f5889d);
                                hgn.mo7409a(" ) AS tf");
                                hgn.mo7409a(" ON mt.a = tf.tf_a WHERE 1 = 1 ");
                                break;
                            } else {
                                hgn.mo7409a(" WHERE 1 < 0");
                                break;
                            }
                        case RecyclerView.SCROLL_STATE_SETTLING:
                            hgn.mo7409a(" WHERE ad = 1 ");
                            break;
                        case 3:
                            hgn.mo7409a(" WHERE ac = 1 ");
                            break;
                        case 4:
                            hgn.mo7409a(" WHERE aa = 1 ");
                            hgn.mo7409a(" AND ");
                            hgn.mo7409a("mt.a");
                            hgn.mo7409a(" NOT IN( ");
                            hgn.mo7409a(" SELECT c FROM ft");
                            hgn.mo7409a(" )");
                            break;
                        case 5:
                            hgn.mo7409a(" WHERE ae = 1 ");
                            hgn.mo7409a(" OR (m LIKE '%screenshot%' COLLATE NOCASE ");
                            hgn.mo7409a(" AND ");
                            hgn.mo7409a("c = ? )");
                            hgn.mo7411b(Integer.toString(1));
                            break;
                        case 6:
                            hgn.mo7409a(" WHERE ab = 1 ");
                            break;
                        case 7:
                            hgn.mo7409a(" WHERE c = ? ");
                            hgn.mo7411b(Integer.toString(3));
                            hgn.mo7409a(" AND h <= ?");
                            hgn.mo7411b(Integer.toString(1200000));
                            break;
                        case 8:
                            hgn.mo7409a(" WHERE c = ? ");
                            hgn.mo7411b(Integer.toString(3));
                            hgn.mo7409a(" AND h > ?");
                            hgn.mo7411b(Integer.toString(1200000));
                            break;
                        case 9:
                            hgn.mo7409a(" WHERE af = 1 ");
                            break;
                    }
                } else if (i3 == 2) {
                    if (i2 == 3) {
                        cwz = (cwz) cxd.f5888c;
                    } else {
                        cwz = cwz.f5879b;
                    }
                    ijd ijd = cwz.f5881a;
                    if (ijd.isEmpty()) {
                        hgn.mo7409a(" WHERE 1=0");
                    } else {
                        hgn.mo7409a(" WHERE b IN (?");
                        hgn.mo7411b(String.valueOf(ijd.get(0)));
                        for (int i4 = 1; i4 < ijd.size(); i4++) {
                            hgn.mo7409a(", ?");
                            hgn.mo7411b(String.valueOf(ijd.get(i4)));
                        }
                        hgn.mo7409a(")");
                    }
                } else if (i3 != 3) {
                    if (i3 == 4) {
                        hgn.mo7409a(" WHERE 1=1");
                    }
                } else if (i2 != 5 || !((Boolean) cxd.f5888c).booleanValue()) {
                    hgn.mo7409a(" WHERE 1=1");
                } else {
                    hgn.mo7409a(" WHERE n LIKE \"%/DCIM/%\"");
                }
            } else {
                hgn.mo7409a(" WHERE l = ?");
                if (cxd.f5887b != 1) {
                    str = "";
                } else {
                    str = (String) cxd.f5888c;
                }
                hgn.mo7411b(str);
            }
            if ((cxd.f5886a & 32) != 0) {
                String str2 = cxd.f5890e;
                List c = hqj.m11915a("/").mo7681c(str2);
                if (c.size() != 2) {
                    cwn.m5514b("MediaDao: Bad MIME format[%s]: Not filtering more.", str2);
                } else {
                    String str3 = (String) c.get(0);
                    if (!str3.equals("*")) {
                        if (((String) c.get(1)).equals("*")) {
                            hgn.mo7409a(" AND d LIKE ?");
                            hgn.mo7411b(String.valueOf(str3).concat("/%"));
                        } else {
                            hgn.mo7409a(" AND d = ?");
                            hgn.mo7411b(str2);
                        }
                    }
                }
            }
            if ((cxd.f5886a & 64) == 0 || !cxd.f5891f) {
                hgn.mo7409a(" AND at IS NULL ");
            }
            int b = cya.m5636b(cxd.f5892g);
            if (b == 0) {
                b = 2;
            }
            int i5 = b - 1;
            if (i5 == 0 || i5 == 1) {
                hgn.mo7409a(" AND am = ? ");
                hgn.mo7408a((Long) 0L);
            } else if (i5 == 2) {
                hgn.mo7409a(" AND am = ? ");
                hgn.mo7408a((Long) 1L);
            } else if (i5 != 3) {
                hgn.mo7409a(" AND am = ? ");
                hgn.mo7408a((Long) 3L);
            } else {
                hgn.mo7409a(" AND am = ? ");
                hgn.mo7408a((Long) 2L);
            }
            return hgn;
        }
        throw null;
    }

    /* renamed from: a */
    public static String m5897a(String str, String str2) {
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(str);
        externalStoragePublicDirectory.mkdirs();
        if (!externalStoragePublicDirectory.exists()) {
            String valueOf = String.valueOf(externalStoragePublicDirectory.getAbsolutePath());
            throw new IOException(valueOf.length() == 0 ? new String("failed to create fallback path ") : "failed to create fallback path ".concat(valueOf));
        } else if (externalStoragePublicDirectory.isDirectory()) {
            return new File(externalStoragePublicDirectory, str2).getAbsolutePath();
        } else {
            String valueOf2 = String.valueOf(externalStoragePublicDirectory.getAbsolutePath());
            throw new IOException(String.format(valueOf2.length() == 0 ? new String("failed to create fallback path %s, %s is not a directory") : "failed to create fallback path %s, %s is not a directory".concat(valueOf2), new Object[]{str2}));
        }
    }

    /* renamed from: a */
    public static String m5896a(String str) {
        return m5897a(Environment.DIRECTORY_PICTURES, str);
    }

    /* renamed from: a */
    public static ieh m5895a(Optional optional, cyr cyr, iek iek) {
        if (!optional.isPresent() || ((cyd) optional.get()).f5991e) {
            return ife.m12820a((Object) Optional.empty());
        }
        return gte.m10770a(cyr.mo3995a(((cyd) optional.get()).f5990d), dck.f6258a, (Executor) iek);
    }
}
