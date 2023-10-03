package p000;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.RectF;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.editor.parameters.PipelineParams;
import java.util.Locale;

/* renamed from: ihg */
/* compiled from: PG */
public class ihg {
    /* renamed from: a */
    public static int m13013a(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 4;
        }
        if (i == 66) {
            return 67;
        }
        if (i == 67) {
            return 68;
        }
        if (i == 77) {
            return 78;
        }
        switch (i) {
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 9;
            case 9:
                return 10;
            case 10:
                return 11;
            case 11:
                return 12;
            case 12:
                return 13;
            case 13:
                return 14;
            default:
                switch (i) {
                    case 69:
                        return 70;
                    case 70:
                        return 71;
                    case 71:
                        return 72;
                    case 72:
                        return 73;
                    case 73:
                        return 74;
                    case 74:
                        return 75;
                    case 75:
                        return 76;
                    default:
                        return 0;
                }
        }
    }

    /* renamed from: b */
    public static int m13044b(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case RecyclerView.SCROLL_STATE_SETTLING:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            default:
                return 0;
        }
    }

    /* renamed from: b */
    public static ihg m13047b() {
        return new ahh();
    }

    /* renamed from: a */
    public static ihg m13029a() {
        return new ahj();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0048 A[SYNTHETIC, Splitter:B:25:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0060 A[SYNTHETIC, Splitter:B:37:0x0060] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:29:0x0050=Splitter:B:29:0x0050, B:14:0x0032=Splitter:B:14:0x0032} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static p000.ahd m13025a(byte[] r6) {
        /*
            ahd r0 = new ahd
            r0.<init>()
            if (r6 == 0) goto L_0x0071
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r6)
            r6 = 0
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x003f, all -> 0x003d }
            r2.<init>(r1)     // Catch:{ IOException -> 0x003f, all -> 0x003d }
            int r6 = r2.readInt()     // Catch:{ IOException -> 0x003b, all -> 0x0036 }
        L_0x0016:
            if (r6 <= 0) goto L_0x002a
            java.lang.String r3 = r2.readUTF()     // Catch:{ IOException -> 0x003b, all -> 0x0036 }
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ IOException -> 0x003b, all -> 0x0036 }
            boolean r4 = r2.readBoolean()     // Catch:{ IOException -> 0x003b, all -> 0x0036 }
            r0.mo465a(r3, r4)     // Catch:{ IOException -> 0x003b, all -> 0x0036 }
            int r6 = r6 + -1
            goto L_0x0016
        L_0x002a:
            r2.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r6 = move-exception
            p000.ifn.m12932a(r6)
        L_0x0032:
            r1.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0036:
            r6 = move-exception
            r0 = r6
            r6 = r2
            goto L_0x005d
        L_0x003b:
            r6 = move-exception
            goto L_0x0043
        L_0x003d:
            r0 = move-exception
            goto L_0x005d
        L_0x003f:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r5
        L_0x0043:
            p000.ifn.m12932a(r6)     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0050
            r2.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x0050
        L_0x004c:
            r6 = move-exception
            p000.ifn.m12932a(r6)
        L_0x0050:
            r1.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r6 = move-exception
            p000.ifn.m12932a(r6)
        L_0x0058:
            return r0
        L_0x0059:
            r6 = move-exception
            r0 = r6
            r6 = r2
        L_0x005d:
            if (r6 != 0) goto L_0x0060
            goto L_0x0068
        L_0x0060:
            r6.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0068
        L_0x0064:
            r6 = move-exception
            p000.ifn.m12932a(r6)
        L_0x0068:
            r1.close()     // Catch:{ IOException -> 0x006c }
            goto L_0x0070
        L_0x006c:
            r6 = move-exception
            p000.ifn.m12932a(r6)
        L_0x0070:
            throw r0
        L_0x0071:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ihg.m13025a(byte[]):ahd");
    }

    /* renamed from: e */
    public static int m13056e(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        throw new IllegalArgumentException("Could not convert " + i + " to BackoffPolicy");
    }

    /* renamed from: f */
    public static int m13058f(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 4;
        }
        if (i == 4) {
            return 5;
        }
        throw new IllegalArgumentException("Could not convert " + i + " to NetworkType");
    }

    /* renamed from: d */
    public static int m13054d(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 4;
        }
        if (i == 4) {
            return 5;
        }
        if (i == 5) {
            return 6;
        }
        throw new IllegalArgumentException("Could not convert " + i + " to State");
    }

    /* renamed from: c */
    public static int m13050c(int i) {
        int i2 = i - 1;
        if (i == 0) {
            throw null;
        } else if (i2 == 0) {
            return 0;
        } else {
            int i3 = 1;
            if (i2 != 1) {
                i3 = 2;
                if (i2 != 2) {
                    i3 = 3;
                    if (i2 != 3) {
                        i3 = 4;
                        if (i2 != 4) {
                            if (i2 == 5) {
                                return 5;
                            }
                            throw new IllegalArgumentException("Could not convert " + gbz.m9996c(i) + " to int");
                        }
                    }
                }
            }
            return i3;
        }
    }

    /* renamed from: c */
    public static float m13049c(float f, RectF rectF) {
        return (f * rectF.width()) + rectF.left;
    }

    /* renamed from: d */
    public static float m13052d(float f, RectF rectF) {
        return (f * rectF.height()) + rectF.top;
    }

    /* renamed from: a */
    public static void m13034a(RectF rectF, int i, int i2, RectF rectF2, PipelineParams pipelineParams, bxc bxc) {
        if (rectF != null && !rectF.isEmpty() && i > 0 && i2 > 0) {
            if (bxc.isCropWidthConstrained(pipelineParams, rectF2.left, rectF2.top, rectF2.right, rectF2.bottom)) {
                float width = ((((float) i) - pipelineParams.marginLeft) - pipelineParams.marginRight) / (rectF.width() / pipelineParams.zoomScale);
                pipelineParams.zoomScale = width;
                pipelineParams.zoomScale = width / (rectF2.right - rectF2.left);
            } else {
                float height = ((((float) i2) - pipelineParams.marginTop) - pipelineParams.marginBottom) / (rectF.height() / pipelineParams.zoomScale);
                pipelineParams.zoomScale = height;
                pipelineParams.zoomScale = height / (rectF2.bottom - rectF2.top);
            }
            float f = (rectF2.right + rectF2.left) / 2.0f;
            float f2 = (rectF2.bottom + rectF2.top) / 2.0f;
            float f3 = 0.5f - f;
            float f4 = 0.5f - f2;
            if (Math.abs(pipelineParams.zoomScale - 4.0f) < 0.001f) {
                pipelineParams.zoomCenterX = f;
                pipelineParams.zoomCenterY = f2;
                pipelineParams.zoomScale = 1.0f;
                return;
            }
            float f5 = pipelineParams.zoomScale - 4.0f;
            pipelineParams.zoomCenterX = f - (f3 / f5);
            pipelineParams.zoomCenterY = f2 - (f4 / f5);
        }
    }

    /* renamed from: a */
    public static int m13018a(RectF rectF, int i, float f, float f2) {
        int i2 = 0;
        if (rectF.isEmpty()) {
            return 0;
        }
        RectF rectF2 = new RectF(rectF);
        float f3 = (float) i;
        rectF2.inset(f3, f3);
        if (rectF2.contains(f, f2)) {
            return 15;
        }
        RectF rectF3 = new RectF(rectF);
        float f4 = (float) (-i);
        rectF3.inset(f4, f4);
        if (!rectF3.contains(f, f2)) {
            return 0;
        }
        RectF rectF4 = new RectF(rectF3.left, rectF3.top, rectF2.left, rectF3.bottom);
        RectF rectF5 = new RectF(rectF3.left, rectF3.top, rectF3.right, rectF2.top);
        RectF rectF6 = new RectF(rectF2.right, rectF3.top, rectF3.right, rectF3.bottom);
        RectF rectF7 = new RectF(rectF3.left, rectF2.bottom, rectF3.right, rectF3.bottom);
        if (rectF4.contains(f, f2)) {
            i2 = 1;
        } else if (rectF6.contains(f, f2)) {
            i2 = 4;
        }
        if (rectF5.contains(f, f2)) {
            return i2 | 2;
        }
        return rectF7.contains(f, f2) ? i2 | 8 : i2;
    }

    /* renamed from: a */
    public static void m13035a(RectF rectF, RectF rectF2, RectF rectF3) {
        rectF2.set(m13012a(rectF.left, rectF3), m13043b(rectF.top, rectF3), m13012a(rectF.right, rectF3), m13043b(rectF.bottom, rectF3));
    }

    /* renamed from: a */
    public static float m13012a(float f, RectF rectF) {
        return (f - rectF.left) / rectF.width();
    }

    /* renamed from: b */
    public static float m13043b(float f, RectF rectF) {
        return (f - rectF.top) / rectF.height();
    }

    /* renamed from: a */
    public static boolean m13042a(float f, float f2) {
        return Math.abs(f - f2) < 0.001f;
    }

    /* renamed from: a */
    public static void m13033a(Activity activity, Class cls, hol hol) {
        View findViewById = activity.findViewById(16908290);
        ife.m12869b((Object) findViewById, (Object) "Activity must have a content view to add a listener!");
        m13031a((int) R.id.tiktok_event_activity_listeners, findViewById, cls, hol);
    }

    /* renamed from: a */
    public static void m13037a(C0140fa faVar, Class cls, hol hol) {
        View a = m13027a(faVar);
        ife.m12869b((Object) a, (Object) "DialogFragment must have content or dialog view to add a listener!");
        m13031a((int) R.id.tiktok_event_fragment_listeners, a, cls, hol);
    }

    /* renamed from: a */
    public static void m13038a(C0147fh fhVar, Class cls, hol hol) {
        View view = fhVar.f9573L;
        ife.m12869b((Object) view, (Object) "Fragment must have a content view to add a listener!");
        m13031a((int) R.id.tiktok_event_fragment_listeners, view, cls, hol);
    }

    /* renamed from: a */
    public static void m13036a(View view, Class cls, hol hol) {
        m13031a((int) R.id.tiktok_event_view_listeners, (View) ife.m12898e((Object) view), (Class) ife.m12898e((Object) cls), (hol) ife.m12898e((Object) hol));
    }

    /* renamed from: a */
    private static void m13031a(int i, View view, Class cls, hol hol) {
        C0309lf a = m13030a(i, view);
        if (a == null) {
            a = new C0290kn();
            view.setTag(i, a);
        }
        int i2 = 0;
        while (i2 < a.f15193b) {
            Class cls2 = (Class) a.mo9320b(i2);
            if (cls.equals(cls2)) {
                throw new IllegalArgumentException(String.format(Locale.US, "Class %s is already registered as a listener.  Are you adding the same View instance twice?", new Object[]{cls.getSimpleName()}));
            } else if (cls.isAssignableFrom(cls2)) {
                throw new IllegalArgumentException(String.format(Locale.US, "For class %s, a listener is already registered as a subtype: %s", new Object[]{cls.getSimpleName(), cls2.getSimpleName()}));
            } else if (!cls2.isAssignableFrom(cls)) {
                i2++;
            } else {
                throw new IllegalArgumentException(String.format(Locale.US, "For class %s, a listener is already registered as a supertype: %s", new Object[]{cls.getSimpleName(), cls2.getSimpleName()}));
            }
        }
        a.put(cls, hol);
    }

    /* renamed from: a */
    public static View m13027a(C0140fa faVar) {
        View view = faVar.f9573L;
        if (view != null) {
            return view;
        }
        Dialog dialog = faVar.f9240d;
        if (dialog != null) {
            return dialog.findViewById(16908290);
        }
        return null;
    }

    /* renamed from: a */
    private static C0309lf m13030a(int i, View view) {
        return (C0309lf) view.getTag(i);
    }

    /* renamed from: a */
    private static View m13026a(ViewParent viewParent) {
        if (viewParent instanceof View) {
            return (View) viewParent;
        }
        if (viewParent != null) {
            return m13026a(viewParent.getParent());
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0054  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static p000.hpy m13028a(int r4, android.view.View r5, p000.hpy r6) {
        /*
            boolean r0 = r6.mo7646a()
            if (r0 == 0) goto L_0x0057
            java.lang.Object r0 = r6.mo7647b()
            hoi r0 = (p000.hoi) r0
            lf r4 = m13030a((int) r4, (android.view.View) r5)
            if (r4 != 0) goto L_0x0013
            goto L_0x0042
        L_0x0013:
            boolean r5 = r4.isEmpty()
            if (r5 != 0) goto L_0x0042
            java.lang.Class r5 = r0.getClass()
            r1 = 0
        L_0x001e:
            int r2 = r4.f15193b
            if (r1 >= r2) goto L_0x0042
            java.lang.Object r2 = r4.mo9320b((int) r1)
            java.lang.Class r2 = (java.lang.Class) r2
            java.lang.Object r3 = r4.mo9321c(r1)
            hol r3 = (p000.hol) r3
            boolean r2 = r2.isAssignableFrom(r5)
            if (r2 == 0) goto L_0x003f
            hom r4 = r3.mo2639a(r0)
            java.lang.Object r4 = p000.ife.m12898e((java.lang.Object) r4)
            hom r4 = (p000.hom) r4
            goto L_0x0044
        L_0x003f:
            int r1 = r1 + 1
            goto L_0x001e
        L_0x0042:
            hom r4 = p000.hom.f13156b
        L_0x0044:
            hom r5 = p000.hom.f13155a
            if (r4 == r5) goto L_0x0054
            hom r5 = p000.hom.f13156b
            if (r4 == r5) goto L_0x0053
            hoi r4 = r4.f13157c
            hpy r4 = p000.hpy.m11893b(r4)
            return r4
        L_0x0053:
            return r6
        L_0x0054:
            hph r4 = p000.hph.f13219a
            return r4
        L_0x0057:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ihg.m13028a(int, android.view.View, hpy):hpy");
    }

    /* renamed from: a */
    public static void m13040a(hoi hoi, C0140fa faVar) {
        View a = m13027a(faVar);
        ife.m12869b((Object) a, (Object) "DialogFragment must have content or dialog view to send an event!");
        m13032a((int) R.id.tiktok_event_fragment_listeners, hoi, a);
    }

    /* renamed from: a */
    public static void m13041a(hoi hoi, C0147fh fhVar) {
        View view = fhVar.f9573L;
        ife.m12869b((Object) view, (Object) "Fragment must have content view to send an event!");
        m13032a((int) R.id.tiktok_event_fragment_listeners, hoi, view);
    }

    /* renamed from: a */
    public static void m13039a(hoi hoi, View view) {
        m13032a((int) R.id.tiktok_event_view_listeners, hoi, (View) ife.m12898e((Object) view));
    }

    /* renamed from: a */
    public static void m13032a(int i, hoi hoi, View view) {
        fxk.m9830b();
        hpy b = hpy.m11893b(hoi);
        View view2 = view;
        while (view2 != null) {
            if (view2 != view || i == R.id.tiktok_event_view_listeners) {
                b = m13028a((int) R.id.tiktok_event_view_listeners, view2, b);
            }
            if (!(view2 == view && i == R.id.tiktok_event_activity_listeners)) {
                b = m13028a((int) R.id.tiktok_event_fragment_listeners, view2, b);
            }
            b = m13028a((int) R.id.tiktok_event_activity_listeners, view2, b);
            if (b.mo7646a()) {
                Object tag = view2.getTag(R.id.tiktok_event_parent);
                if (tag == null || (tag instanceof View)) {
                    View view3 = (View) tag;
                    if (view3 == null) {
                        view2 = m13026a(view2.getParent());
                    } else {
                        view2 = view3;
                    }
                } else {
                    String valueOf = String.valueOf(tag.getClass());
                    String valueOf2 = String.valueOf(tag);
                    String valueOf3 = String.valueOf(view2);
                    int length = String.valueOf(valueOf).length();
                    StringBuilder sb = new StringBuilder(length + 32 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
                    sb.append("Invalid tag returned: ");
                    sb.append(valueOf);
                    sb.append(valueOf2);
                    sb.append(" for view ");
                    sb.append(valueOf3);
                    throw new IllegalStateException(sb.toString());
                }
            } else {
                return;
            }
        }
    }

    /* renamed from: e */
    static int m13057e(byte[] bArr, int i, ihf ihf) {
        int a = m13023a(bArr, i, ihf);
        int i2 = ihf.f14178a;
        if (i2 < 0) {
            throw ijh.m13655b();
        } else if (i2 > bArr.length - a) {
            throw ijh.m13654a();
        } else if (i2 != 0) {
            ihf.f14180c = ihw.m13163a(bArr, a, i2);
            return a + i2;
        } else {
            ihf.f14180c = ihw.f14203b;
            return a;
        }
    }

    /* renamed from: c */
    static double m13048c(byte[] bArr, int i) {
        return Double.longBitsToDouble(m13046b(bArr, i));
    }

    /* renamed from: a */
    static int m13022a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* renamed from: b */
    static long m13046b(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    /* renamed from: d */
    static float m13053d(byte[] bArr, int i) {
        return Float.intBitsToFloat(m13022a(bArr, i));
    }

    /* renamed from: a */
    static int m13020a(iky iky, byte[] bArr, int i, int i2, int i3, ihf ihf) {
        ikh ikh = (ikh) iky;
        Object a = ikh.mo8864a();
        int a2 = ikh.mo8863a(a, bArr, i, i2, i3, ihf);
        ikh.mo8871c(a);
        ihf.f14180c = a;
        return a2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int m13021a(p000.iky r6, byte[] r7, int r8, int r9, p000.ihf r10) {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000d
            int r0 = m13017a((int) r8, (byte[]) r7, (int) r0, (p000.ihf) r10)
            int r8 = r10.f14178a
            goto L_0x000e
        L_0x000d:
        L_0x000e:
            r3 = r0
            if (r8 < 0) goto L_0x0027
            int r9 = r9 - r3
            if (r8 > r9) goto L_0x0027
            java.lang.Object r9 = r6.mo8864a()
            int r8 = r8 + r3
            r0 = r6
            r1 = r9
            r2 = r7
            r4 = r8
            r5 = r10
            r0.mo8867a(r1, r2, r3, r4, r5)
            r6.mo8871c(r9)
            r10.f14180c = r9
            return r8
        L_0x0027:
            ijh r6 = p000.ijh.m13654a()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ihg.m13021a(iky, byte[], int, int, ihf):int");
    }

    /* renamed from: a */
    static int m13019a(iky iky, int i, byte[] bArr, int i2, int i3, ije ije, ihf ihf) {
        int a = m13021a(iky, bArr, i2, i3, ihf);
        ije.add(ihf.f14180c);
        while (a < i3) {
            int a2 = m13023a(bArr, a, ihf);
            if (i != ihf.f14178a) {
                break;
            }
            a = m13021a(iky, bArr, a2, i3, ihf);
            ije.add(ihf.f14180c);
        }
        return a;
    }

    /* renamed from: a */
    static int m13024a(byte[] bArr, int i, ije ije, ihf ihf) {
        iiy iiy = (iiy) ije;
        int a = m13023a(bArr, i, ihf);
        int i2 = ihf.f14178a + a;
        while (a < i2) {
            a = m13023a(bArr, a, ihf);
            iiy.mo8801d(ihf.f14178a);
        }
        if (a == i2) {
            return a;
        }
        throw ijh.m13654a();
    }

    /* renamed from: c */
    static int m13051c(byte[] bArr, int i, ihf ihf) {
        int a = m13023a(bArr, i, ihf);
        int i2 = ihf.f14178a;
        if (i2 < 0) {
            throw ijh.m13655b();
        } else if (i2 != 0) {
            ihf.f14180c = new String(bArr, a, i2, ijf.f14336a);
            return a + i2;
        } else {
            ihf.f14180c = "";
            return a;
        }
    }

    /* renamed from: d */
    static int m13055d(byte[] bArr, int i, ihf ihf) {
        int a = m13023a(bArr, i, ihf);
        int i2 = ihf.f14178a;
        if (i2 < 0) {
            throw ijh.m13655b();
        } else if (i2 != 0) {
            ihf.f14180c = ima.m14071c(bArr, a, i2);
            return a + i2;
        } else {
            ihf.f14180c = "";
            return a;
        }
    }

    /* renamed from: a */
    static int m13016a(int i, byte[] bArr, int i2, int i3, ilm ilm, ihf ihf) {
        if (imd.m14074b(i) != 0) {
            int a = imd.m14072a(i);
            if (a == 0) {
                int b = m13045b(bArr, i2, ihf);
                ilm.mo8943a(i, (Object) Long.valueOf(ihf.f14179b));
                return b;
            } else if (a == 1) {
                ilm.mo8943a(i, (Object) Long.valueOf(m13046b(bArr, i2)));
                return i2 + 8;
            } else if (a == 2) {
                int a2 = m13023a(bArr, i2, ihf);
                int i4 = ihf.f14178a;
                if (i4 < 0) {
                    throw ijh.m13655b();
                } else if (i4 <= bArr.length - a2) {
                    if (i4 == 0) {
                        ilm.mo8943a(i, (Object) ihw.f14203b);
                    } else {
                        ilm.mo8943a(i, (Object) ihw.m13163a(bArr, a2, i4));
                    }
                    return a2 + i4;
                } else {
                    throw ijh.m13654a();
                }
            } else if (a == 3) {
                int i5 = (i & -8) | 4;
                ilm a3 = ilm.m13974a();
                int i6 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int a4 = m13023a(bArr, i2, ihf);
                    int i7 = ihf.f14178a;
                    if (i7 == i5) {
                        i6 = i7;
                        i2 = a4;
                        break;
                    }
                    i6 = i7;
                    i2 = m13016a(i7, bArr, a4, i3, a3, ihf);
                }
                if (i2 > i3 || i6 != i5) {
                    throw ijh.m13661h();
                }
                ilm.mo8943a(i, (Object) a3);
                return i2;
            } else if (a == 5) {
                ilm.mo8943a(i, (Object) Integer.valueOf(m13022a(bArr, i2)));
                return i2 + 4;
            } else {
                throw ijh.m13657d();
            }
        } else {
            throw ijh.m13657d();
        }
    }

    /* renamed from: a */
    static int m13017a(int i, byte[] bArr, int i2, ihf ihf) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            ihf.f14178a = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            ihf.f14178a = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            ihf.f14178a = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 < 0) {
            int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
            while (true) {
                int i12 = i10 + 1;
                if (bArr[i10] < 0) {
                    i10 = i12;
                } else {
                    ihf.f14178a = i11;
                    return i12;
                }
            }
        } else {
            ihf.f14178a = i9 | (b4 << 28);
            return i10;
        }
    }

    /* renamed from: a */
    static int m13023a(byte[] bArr, int i, ihf ihf) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return m13017a((int) b, bArr, i2, ihf);
        }
        ihf.f14178a = b;
        return i2;
    }

    /* renamed from: a */
    static int m13015a(int i, byte[] bArr, int i2, int i3, ije ije, ihf ihf) {
        iiy iiy = (iiy) ije;
        int a = m13023a(bArr, i2, ihf);
        iiy.mo8801d(ihf.f14178a);
        while (a < i3) {
            int a2 = m13023a(bArr, a, ihf);
            if (i != ihf.f14178a) {
                break;
            }
            a = m13023a(bArr, a2, ihf);
            iiy.mo8801d(ihf.f14178a);
        }
        return a;
    }

    /* renamed from: b */
    static int m13045b(byte[] bArr, int i, ihf ihf) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j < 0) {
            int i3 = i2 + 1;
            byte b = bArr[i2];
            long j2 = (j & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
            int i4 = 7;
            while (b < 0) {
                int i5 = i3 + 1;
                byte b2 = bArr[i3];
                i4 += 7;
                j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
                int i6 = i5;
                b = b2;
                i3 = i6;
            }
            ihf.f14179b = j2;
            return i3;
        }
        ihf.f14179b = j;
        return i2;
    }

    /* renamed from: a */
    static int m13014a(int i, byte[] bArr, int i2, int i3, ihf ihf) {
        if (imd.m14074b(i) != 0) {
            int a = imd.m14072a(i);
            if (a == 0) {
                return m13045b(bArr, i2, ihf);
            }
            if (a == 1) {
                return i2 + 8;
            }
            if (a == 2) {
                return m13023a(bArr, i2, ihf) + ihf.f14178a;
            }
            if (a == 3) {
                int i4 = (i & -8) | 4;
                int i5 = 0;
                while (i2 < i3) {
                    i2 = m13023a(bArr, i2, ihf);
                    i5 = ihf.f14178a;
                    if (i5 == i4) {
                        break;
                    }
                    i2 = m13014a(i5, bArr, i2, i3, ihf);
                }
                if (i2 <= i3 && i5 == i4) {
                    return i2;
                }
                throw ijh.m13661h();
            } else if (a == 5) {
                return i2 + 4;
            } else {
                throw ijh.m13657d();
            }
        } else {
            throw ijh.m13657d();
        }
    }
}
