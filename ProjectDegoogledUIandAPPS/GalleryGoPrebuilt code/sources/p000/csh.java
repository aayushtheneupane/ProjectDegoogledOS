package p000;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.android.libraries.vision.visionkit.recognition.classifier.NativeClassifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: csh */
/* compiled from: PG */
final /* synthetic */ class csh implements icf {

    /* renamed from: a */
    private final csi f5557a;

    /* renamed from: b */
    private final csa f5558b;

    /* renamed from: c */
    private final cyg f5559c;

    public csh(csi csi, csa csa, cyg cyg) {
        this.f5557a = csi;
        this.f5558b = csa;
        this.f5559c = cyg;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        Throwable th;
        gce gce;
        boolean z;
        long j;
        String str;
        csi csi = this.f5557a;
        csa csa = this.f5558b;
        cyg cyg = this.f5559c;
        Bitmap a = cns.m4631a((Bitmap) obj, Bitmap.Config.ARGB_8888);
        if (!csa.f5541b) {
            int i = 1;
            boolean z2 = false;
            ife.m12847a(a.getWidth() == 224, "Bitmap must be %spx wide (it was %s).", 224, a.getWidth());
            ife.m12847a(a.getHeight() == 224, "Bitmap must be %spx high (it was %s).", 224, a.getHeight());
            try {
                hlj a2 = hnb.m11765a("Native Get Labels");
                try {
                    gce = csa.f5540a;
                    long j2 = gce.f10919a;
                    if (j2 != 0) {
                        byte[] classify = NativeClassifier.classify(j2, a);
                        gcc gcc = null;
                        gcc = (gcc) iix.m13603a((iix) gcc.f10911b, classify);
                        ife.m12898e((Object) gcc);
                        if (gcc.f10913a.size() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        ife.m12896d(z);
                        gcd gcd = (gcd) gcc.f10913a.get(0);
                        int i2 = gcd.f10918c;
                        List arrayList = new ArrayList();
                        int i3 = 0;
                        while (i3 < gcd.f10917b.size()) {
                            iir g = csk.f5574d.mo8793g();
                            float f = ((gcb) gcd.f10917b.get(i3)).f10910c;
                            if (g.f14319c) {
                                g.mo8751b();
                                g.f14319c = z2;
                            }
                            csk csk = (csk) g.f14318b;
                            csk.f5576a |= i;
                            csk.f5577b = f;
                            gce gce2 = csa.f5540a;
                            int i4 = ((gcb) gcd.f10917b.get(i3)).f10909b;
                            long j3 = gce2.f10919a;
                            if (j3 != 0) {
                                try {
                                    j = igd.m12962a(NativeClassifier.getClassName(j3, i2, i4));
                                } catch (IllegalArgumentException e) {
                                    j = -1;
                                }
                                if (g.f14319c) {
                                    g.mo8751b();
                                    g.f14319c = false;
                                }
                                csk csk2 = (csk) g.f14318b;
                                csk2.f5576a |= 2;
                                csk2.f5578c = j;
                                arrayList.add((csk) g.mo8770g());
                                i3++;
                                i = 1;
                                z2 = false;
                            } else {
                                throw new IllegalStateException("Native classifier is not initialized or has been closed");
                            }
                        }
                        Collections.sort(arrayList, cry.f5530a);
                        iir g2 = csl.f5579b.mo8793g();
                        if (arrayList.size() > 100) {
                            arrayList = arrayList.subList(0, 100);
                        }
                        if (g2.f14319c) {
                            g2.mo8751b();
                            g2.f14319c = false;
                        }
                        csl csl = (csl) g2.f14318b;
                        if (!csl.f5581a.mo8521a()) {
                            csl.f5581a = iix.m13608a(csl.f5581a);
                        }
                        igz.m12986a((Iterable) arrayList, (List) csl.f5581a);
                        csl csl2 = (csl) g2.mo8770g();
                        if (a2 != null) {
                            a2.close();
                        }
                        return csi.mo3800a(csl2, cyg);
                    }
                    throw new IllegalStateException("Native classifier is not initialized or has been closed");
                } catch (IOException e2) {
                    gca gca = gca.f10904a;
                    if (Log.isLoggable(gca.f10905b, 6)) {
                        String str2 = gca.f10905b;
                        String[] split = gce.getClass().getName().split("\\.");
                        int length = split.length;
                        if (length != 0) {
                            str = split[length - 1];
                        } else {
                            str = "";
                        }
                        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 46);
                        sb.append("[");
                        sb.append(str);
                        sb.append("] ");
                        sb.append("Bytes -> Protocol buffer conversion failed.");
                        Log.e(str2, sb.toString());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (a2 != null) {
                        a2.close();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                throw new RuntimeException(e3);
            } catch (Throwable th3) {
                ifn.m12935a(th, th3);
            }
        } else {
            throw new IllegalStateException("Image Labeler was already closed.");
        }
    }
}
