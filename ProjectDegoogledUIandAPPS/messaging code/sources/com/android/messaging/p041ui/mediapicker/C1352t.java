package com.android.messaging.p041ui.mediapicker;

import android.app.Activity;
import android.content.res.Resources;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.android.messaging.datamodel.data.C0943z;
import com.android.messaging.p041ui.mediapicker.camerafocus.C1304a;
import com.android.messaging.p041ui.mediapicker.camerafocus.C1306c;
import com.android.messaging.p041ui.mediapicker.camerafocus.RenderOverlay;
import com.android.messaging.sms.C1024t;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1486ya;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.mediapicker.t */
class C1352t implements C1304a {
    /* access modifiers changed from: private */

    /* renamed from: jI */
    public static C1346q f2148jI = new C1328h();
    private static C1352t sInstance;
    /* access modifiers changed from: private */

    /* renamed from: Ak */
    public int f2149Ak;

    /* renamed from: Hg */
    private C1359wa f2150Hg;

    /* renamed from: VH */
    private final Camera.CameraInfo f2151VH = new Camera.CameraInfo();
    /* access modifiers changed from: private */

    /* renamed from: WH */
    public int f2152WH = -1;

    /* renamed from: XH */
    private final boolean f2153XH;
    /* access modifiers changed from: private */

    /* renamed from: YH */
    public boolean f2154YH;

    /* renamed from: ZH */
    private boolean f2155ZH;

    /* renamed from: _H */
    private C1360x f2156_H;
    /* access modifiers changed from: private */

    /* renamed from: aI */
    public C1278F f2157aI;

    /* renamed from: bI */
    private C1348r f2158bI;

    /* renamed from: cI */
    private boolean f2159cI;
    /* access modifiers changed from: private */

    /* renamed from: dI */
    public AsyncTask f2160dI;
    /* access modifiers changed from: private */

    /* renamed from: eI */
    public int f2161eI = -1;
    /* access modifiers changed from: private */

    /* renamed from: fI */
    public Camera f2162fI;
    /* access modifiers changed from: private */

    /* renamed from: gI */
    public boolean f2163gI;
    /* access modifiers changed from: private */

    /* renamed from: hI */
    public final C1306c f2164hI;

    /* renamed from: iI */
    private Integer f2165iI = null;
    /* access modifiers changed from: private */
    public C1275C mListener;

    /* renamed from: pb */
    private C0943z f2166pb;

    private C1352t() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = ((C1328h) f2148jI).getNumberOfCameras();
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i < numberOfCameras) {
            try {
                ((C1328h) f2148jI).getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == 1) {
                    z2 = true;
                } else if (cameraInfo.facing == 0) {
                    z3 = true;
                }
                if (z2 && z3) {
                    break;
                }
                i++;
            } catch (RuntimeException e) {
                C1430e.m3623e("MessagingApp", "Unable to load camera info", e);
            }
        }
        if (z2 && z3) {
            z = true;
        }
        this.f2153XH = z;
        this.f2164hI = new C1306c(this, Looper.getMainLooper());
        this.f2159cI = true;
    }

    /* renamed from: Ao */
    private Camera.Size m3424Ao() {
        int i;
        int i2;
        Resources resources = this.f2157aI.getContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int i3 = resources.getConfiguration().orientation;
        int i4 = this.f2151VH.orientation;
        if (i3 == 2) {
            i4 += 90;
        }
        if (i4 % 180 == 0) {
            i2 = displayMetrics.widthPixels;
            i = displayMetrics.heightPixels;
        } else {
            i2 = displayMetrics.heightPixels;
            i = displayMetrics.widthPixels;
        }
        C0943z zVar = this.f2166pb;
        C1024t tVar = C1024t.get(zVar != null ? zVar.mo6582H() : -1);
        int oi = tVar.mo6843oi();
        int mi = tVar.mo6842mi();
        float f = 1.0f;
        if (oi <= 0 || mi <= 0) {
            C1430e.m3630w("MessagingApp", "Max image size not loaded in MmsConfig");
        } else if (i2 > oi || i > mi) {
            f = Math.min((((float) oi) * 1.0f) / ((float) i2), (((float) mi) * 1.0f) / ((float) i));
        }
        float f2 = ((float) ((int) (((float) i2) * f))) / ((float) ((int) (((float) i) * f)));
        C1449g.get().getFloat("bugle_camera_aspect_ratio", f2);
        ArrayList arrayList = new ArrayList(this.f2162fI.getParameters().getSupportedPictureSizes());
        Collections.sort(arrayList, new C1350s(oi, mi, f2, oi * mi));
        return (Camera.Size) arrayList.get(0);
    }

    /* renamed from: Bo */
    private void m3425Bo() {
        Activity B = C1486ya.m3844B(this.f2157aI.getContext());
        int rotation = ((WindowManager) B.getSystemService("window")).getDefaultDisplay().getRotation();
        this.f2165iI = Integer.valueOf(B.getRequestedOrientation());
        if (rotation == 0) {
            B.setRequestedOrientation(1);
        } else if (rotation == 1) {
            B.setRequestedOrientation(0);
        } else if (rotation == 2) {
            B.setRequestedOrientation(9);
        } else if (rotation == 3) {
            B.setRequestedOrientation(8);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: Co */
    public void m3426Co() {
        if (this.f2165iI != null) {
            Activity B = C1486ya.m3844B(this.f2157aI.getContext());
            if (B != null) {
                B.setRequestedOrientation(this.f2165iI.intValue());
            }
            this.f2165iI = null;
        }
    }

    /* renamed from: Da */
    private void m3427Da(boolean z) {
        C1359wa waVar = this.f2150Hg;
        if (waVar != null) {
            this.f2155ZH = false;
            if (z) {
                waVar.mo7964sb();
                C1360x xVar = this.f2156_H;
                if (xVar != null) {
                    this.f2156_H = null;
                    xVar.mo7968a((Uri) null, (String) null, 0, 0);
                }
            }
            this.f2150Hg.mo7965tb();
            this.f2150Hg.release();
            this.f2150Hg = null;
            Camera camera = this.f2162fI;
            if (camera != null) {
                try {
                    camera.reconnect();
                } catch (IOException e) {
                    C1430e.m3623e("MessagingApp", "IOException in CameraManager.releaseMediaRecorder", e);
                    C1275C c = this.mListener;
                    if (c != null) {
                        c.mo7677a(1, (Exception) e);
                    }
                } catch (RuntimeException e2) {
                    C1430e.m3623e("MessagingApp", "RuntimeException in CameraManager.releaseMediaRecorder", e2);
                    C1275C c2 = this.mListener;
                    if (c2 != null) {
                        c2.mo7677a(1, (Exception) e2);
                    }
                }
            }
            m3426Co();
        }
    }

    /* renamed from: Do */
    private void m3428Do() {
        Camera camera;
        if (!this.f2155ZH || (camera = this.f2162fI) == null || this.f2157aI == null) {
            m3427Da(true);
        } else if (this.f2150Hg == null) {
            try {
                camera.unlock();
                C0943z zVar = this.f2166pb;
                this.f2150Hg = new C1359wa(this.f2162fI, this.f2152WH, this.f2149Ak, C1024t.get(zVar != null ? zVar.mo6582H() : -1).getMaxMessageSize());
                this.f2150Hg.prepare();
                m3430Fo();
            } catch (FileNotFoundException e) {
                C1430e.m3623e("MessagingApp", "FileNotFoundException in CameraManager.tryInitOrCleanupVideoMode", e);
                C1275C c = this.mListener;
                if (c != null) {
                    c.mo7677a(4, (Exception) e);
                }
                mo7937ca(false);
            } catch (IOException e2) {
                C1430e.m3623e("MessagingApp", "IOException in CameraManager.tryInitOrCleanupVideoMode", e2);
                C1275C c2 = this.mListener;
                if (c2 != null) {
                    c2.mo7677a(3, (Exception) e2);
                }
                mo7937ca(false);
            } catch (RuntimeException e3) {
                C1430e.m3623e("MessagingApp", "RuntimeException in CameraManager.tryInitOrCleanupVideoMode", e3);
                C1275C c3 = this.mListener;
                if (c3 != null) {
                    c3.mo7677a(3, (Exception) e3);
                }
                mo7937ca(false);
            }
        }
    }

    /* renamed from: Eo */
    private void m3429Eo() {
        Camera camera;
        boolean z = true;
        if (this.f2157aI == null || (camera = this.f2162fI) == null) {
            C1348r rVar = this.f2158bI;
            if (rVar != null) {
                rVar.disable();
                this.f2158bI = null;
            }
            m3427Da(true);
            this.f2164hI.mo7801zj();
            return;
        }
        try {
            camera.stopPreview();
            m3431Go();
            Camera.Parameters parameters = this.f2162fI.getParameters();
            Camera.Size Ao = m3424Ao();
            Camera.Size a = m3433a(Ao);
            parameters.setPreviewSize(a.width, a.height);
            parameters.setPictureSize(Ao.width, Ao.height);
            m3437a("Setting preview size: ", a);
            m3437a("Setting picture size: ", Ao);
            this.f2157aI.mo7691a(a, this.f2151VH.orientation);
            Iterator<String> it = parameters.getSupportedFocusModes().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (TextUtils.equals(next, "continuous-picture")) {
                    parameters.setFocusMode(next);
                    break;
                }
            }
            this.f2162fI.setParameters(parameters);
            this.f2157aI.mo7692a(this.f2162fI);
            this.f2162fI.startPreview();
            this.f2162fI.setAutoFocusMoveCallback(new C1338m(this));
            this.f2164hI.setParameters(this.f2162fI.getParameters());
            C1306c cVar = this.f2164hI;
            if (this.f2151VH.facing != 0) {
                z = false;
            }
            cVar.mo7793ea(z);
            this.f2164hI.mo7800yj();
            m3428Do();
            if (this.f2158bI == null) {
                this.f2158bI = new C1348r(this, this.f2157aI.getContext());
                this.f2158bI.enable();
            }
        } catch (IOException e) {
            C1430e.m3623e("MessagingApp", "IOException in CameraManager.tryShowPreview", e);
            C1275C c = this.mListener;
            if (c != null) {
                c.mo7677a(2, (Exception) e);
            }
        } catch (RuntimeException e2) {
            C1430e.m3623e("MessagingApp", "RuntimeException in CameraManager.tryShowPreview", e2);
            C1275C c2 = this.mListener;
            if (c2 != null) {
                c2.mo7677a(2, (Exception) e2);
            }
        }
    }

    /* renamed from: Fo */
    private void m3430Fo() {
        C1359wa waVar = this.f2150Hg;
        if (waVar != null && this.f2156_H != null) {
            waVar.setOnErrorListener(new C1340n(this));
            this.f2150Hg.setOnInfoListener(new C1342o(this));
            try {
                this.f2150Hg.start();
                C1486ya.m3844B(this.f2157aI.getContext()).getWindow().addFlags(128);
                m3425Bo();
            } catch (IllegalStateException e) {
                C1430e.m3623e("MessagingApp", "IllegalStateException in CameraManager.tryStartVideoCapture", e);
                C1275C c = this.mListener;
                if (c != null) {
                    c.mo7677a(5, (Exception) e);
                }
                mo7937ca(false);
                m3426Co();
            } catch (RuntimeException e2) {
                C1430e.m3623e("MessagingApp", "RuntimeException in CameraManager.tryStartVideoCapture", e2);
                C1275C c2 = this.mListener;
                if (c2 != null) {
                    c2.mo7677a(5, (Exception) e2);
                }
                mo7937ca(false);
                m3426Co();
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: Go */
    public void m3431Go() {
        C1278F f;
        int i;
        int i2;
        if (this.f2162fI != null && (f = this.f2157aI) != null && !this.f2163gI) {
            int rotation = ((WindowManager) f.getContext().getSystemService("window")).getDefaultDisplay().getRotation();
            int i3 = 0;
            if (rotation != 0) {
                if (rotation == 1) {
                    i3 = 90;
                } else if (rotation == 2) {
                    i3 = 180;
                } else if (rotation == 3) {
                    i3 = 270;
                }
            }
            Camera.CameraInfo cameraInfo = this.f2151VH;
            if (cameraInfo.facing == 1) {
                i2 = (cameraInfo.orientation + i3) % 360;
                i = (360 - i2) % 360;
            } else {
                i2 = ((cameraInfo.orientation - i3) + 360) % 360;
                i = i2;
            }
            this.f2149Ak = i2;
            if (this.f2150Hg == null) {
                try {
                    this.f2162fI.setDisplayOrientation(i);
                    Camera.Parameters parameters = this.f2162fI.getParameters();
                    parameters.setRotation(i2);
                    this.f2162fI.setParameters(parameters);
                } catch (RuntimeException e) {
                    C1430e.m3623e("MessagingApp", "RuntimeException in CameraManager.updateCameraOrientation", e);
                    C1275C c = this.mListener;
                    if (c != null) {
                        c.mo7677a(1, (Exception) e);
                    }
                }
            }
        }
    }

    static C1352t get() {
        if (sInstance == null) {
            sInstance = new C1352t();
        }
        return sInstance;
    }

    /* renamed from: nj */
    static boolean m3450nj() {
        return C1464na.m3750Ha("android.permission.CAMERA");
    }

    /* access modifiers changed from: private */
    public void setCamera(Camera camera) {
        if (this.f2162fI != camera) {
            m3427Da(true);
            m3442c(this.f2162fI);
            this.f2162fI = camera;
            m3429Eo();
            C1275C c = this.mListener;
            if (c != null) {
                c.mo7676Ui();
            }
        }
    }

    static void setCameraWrapper(C1346q qVar) {
        f2148jI = qVar;
        sInstance = null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
        r6.f2152WH = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        ((com.android.messaging.p041ui.mediapicker.C1328h) f2148jI).getCameraInfo(r4, r6.f2151VH);
     */
    /* renamed from: Ja */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo7931Ja(int r7) {
        /*
            r6 = this;
            r0 = 1
            r1 = 0
            int r2 = r6.f2152WH     // Catch:{ RuntimeException -> 0x005d }
            if (r2 < 0) goto L_0x000d
            android.hardware.Camera$CameraInfo r2 = r6.f2151VH     // Catch:{ RuntimeException -> 0x005d }
            int r2 = r2.facing     // Catch:{ RuntimeException -> 0x005d }
            if (r2 != r7) goto L_0x000d
            return r0
        L_0x000d:
            com.android.messaging.ui.mediapicker.q r2 = f2148jI     // Catch:{ RuntimeException -> 0x005d }
            com.android.messaging.ui.mediapicker.h r2 = (com.android.messaging.p041ui.mediapicker.C1328h) r2
            int r2 = r2.getNumberOfCameras()     // Catch:{ RuntimeException -> 0x005d }
            if (r2 <= 0) goto L_0x0019
            r3 = r0
            goto L_0x001a
        L_0x0019:
            r3 = r1
        L_0x001a:
            com.android.messaging.util.C1424b.m3592ia(r3)     // Catch:{ RuntimeException -> 0x005d }
            r3 = -1
            r6.f2152WH = r3     // Catch:{ RuntimeException -> 0x005d }
            r3 = 0
            r6.setCamera(r3)     // Catch:{ RuntimeException -> 0x005d }
            android.hardware.Camera$CameraInfo r3 = new android.hardware.Camera$CameraInfo     // Catch:{ RuntimeException -> 0x005d }
            r3.<init>()     // Catch:{ RuntimeException -> 0x005d }
            r4 = r1
        L_0x002a:
            if (r4 >= r2) goto L_0x0046
            com.android.messaging.ui.mediapicker.q r5 = f2148jI     // Catch:{ RuntimeException -> 0x005d }
            com.android.messaging.ui.mediapicker.h r5 = (com.android.messaging.p041ui.mediapicker.C1328h) r5
            r5.getCameraInfo(r4, r3)     // Catch:{ RuntimeException -> 0x005d }
            int r5 = r3.facing     // Catch:{ RuntimeException -> 0x005d }
            if (r5 != r7) goto L_0x0043
            r6.f2152WH = r4     // Catch:{ RuntimeException -> 0x005d }
            com.android.messaging.ui.mediapicker.q r7 = f2148jI     // Catch:{ RuntimeException -> 0x005d }
            android.hardware.Camera$CameraInfo r2 = r6.f2151VH     // Catch:{ RuntimeException -> 0x005d }
            com.android.messaging.ui.mediapicker.h r7 = (com.android.messaging.p041ui.mediapicker.C1328h) r7
            r7.getCameraInfo(r4, r2)     // Catch:{ RuntimeException -> 0x005d }
            goto L_0x0046
        L_0x0043:
            int r4 = r4 + 1
            goto L_0x002a
        L_0x0046:
            int r7 = r6.f2152WH     // Catch:{ RuntimeException -> 0x005d }
            if (r7 >= 0) goto L_0x0055
            r6.f2152WH = r1     // Catch:{ RuntimeException -> 0x005d }
            com.android.messaging.ui.mediapicker.q r7 = f2148jI     // Catch:{ RuntimeException -> 0x005d }
            android.hardware.Camera$CameraInfo r2 = r6.f2151VH     // Catch:{ RuntimeException -> 0x005d }
            com.android.messaging.ui.mediapicker.h r7 = (com.android.messaging.p041ui.mediapicker.C1328h) r7
            r7.getCameraInfo(r1, r2)     // Catch:{ RuntimeException -> 0x005d }
        L_0x0055:
            boolean r7 = r6.f2154YH     // Catch:{ RuntimeException -> 0x005d }
            if (r7 == 0) goto L_0x005c
            r6.mo7950sj()     // Catch:{ RuntimeException -> 0x005d }
        L_0x005c:
            return r0
        L_0x005d:
            r7 = move-exception
            java.lang.String r2 = "MessagingApp"
            java.lang.String r3 = "RuntimeException in CameraManager.selectCamera"
            com.android.messaging.util.C1430e.m3623e(r2, r3, r7)
            com.android.messaging.ui.mediapicker.C r6 = r6.mListener
            if (r6 == 0) goto L_0x006c
            r6.mo7677a((int) r0, (java.lang.Exception) r7)
        L_0x006c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.C1352t.mo7931Ja(int):boolean");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ka */
    public void mo7932Ka(int i) {
        if (this.f2152WH != i) {
            try {
                this.f2152WH = i;
                ((C1328h) f2148jI).getCameraInfo(this.f2152WH, this.f2151VH);
                if (this.f2154YH) {
                    mo7950sj();
                }
            } catch (RuntimeException e) {
                C1430e.m3623e("MessagingApp", "RuntimeException in CameraManager.selectCameraByIndex", e);
                C1275C c = this.mListener;
                if (c != null) {
                    c.mo7677a(1, (Exception) e);
                }
            }
        }
    }

    /* renamed from: N */
    public void mo7784N() {
        Camera camera = this.f2162fI;
        if (camera != null) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFocusMode(this.f2164hI.getFocusMode());
                if (parameters.getMaxNumFocusAreas() > 0) {
                    parameters.setFocusAreas(this.f2164hI.getFocusAreas());
                }
                parameters.setMeteringAreas(this.f2164hI.getMeteringAreas());
                this.f2162fI.setParameters(parameters);
            } catch (RuntimeException unused) {
                C1430e.m3622e("MessagingApp", "RuntimeException in CameraManager setFocusParameters");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ca */
    public void mo7937ca(boolean z) {
        if (this.f2155ZH != z) {
            this.f2155ZH = z;
            m3428Do();
        }
    }

    public void cancelAutoFocus() {
        Camera camera = this.f2162fI;
        if (camera != null) {
            try {
                camera.cancelAutoFocus();
            } catch (RuntimeException e) {
                C1430e.m3623e("MessagingApp", "RuntimeException in CameraManager.cancelAutoFocus", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Camera.CameraInfo getCameraInfo() {
        if (this.f2152WH == -1) {
            return null;
        }
        return this.f2151VH;
    }

    /* renamed from: ia */
    public void mo7786ia() {
        Camera camera = this.f2162fI;
        if (camera != null) {
            try {
                camera.autoFocus(new C1344p(this));
            } catch (RuntimeException e) {
                C1430e.m3623e("MessagingApp", "RuntimeException in CameraManager.autoFocus", e);
                this.f2164hI.mo7797l(false, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: kj */
    public void mo7941kj() {
        this.f2154YH = false;
        setCamera((Camera) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lj */
    public int mo7942lj() {
        return this.f2152WH;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: mj */
    public boolean mo7943mj() {
        return ((C1328h) f2148jI).getNumberOfCameras() > 0;
    }

    /* renamed from: n */
    public boolean mo7787n() {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: oj */
    public boolean mo7944oj() {
        return this.f2153XH;
    }

    /* access modifiers changed from: package-private */
    public void onPause() {
        setCamera((Camera) null);
    }

    /* access modifiers changed from: package-private */
    public void onResume() {
        if (this.f2154YH) {
            mo7950sj();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pj */
    public boolean mo7947pj() {
        return this.f2162fI != null && !this.f2163gI && this.f2159cI;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: qj */
    public boolean mo7948qj() {
        return this.f2155ZH && this.f2156_H != null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: rj */
    public boolean mo7949rj() {
        return this.f2155ZH;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: sj */
    public void mo7950sj() {
        boolean z;
        if (this.f2152WH == -1) {
            mo7931Ja(0);
        }
        this.f2154YH = true;
        if (this.f2161eI != this.f2152WH && this.f2162fI == null) {
            if (this.f2160dI != null) {
                this.f2161eI = -1;
                z = true;
            } else {
                z = false;
            }
            this.f2161eI = this.f2152WH;
            this.f2160dI = new C1332j(this);
            if (Log.isLoggable("MessagingApp", 2)) {
                StringBuilder Pa = C0632a.m1011Pa("Start opening camera ");
                Pa.append(this.f2152WH);
                C1430e.m3628v("MessagingApp", Pa.toString());
            }
            if (!z) {
                this.f2160dI.execute(new Integer[]{Integer.valueOf(this.f2152WH)});
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007b  */
    /* renamed from: tj */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo7951tj() {
        /*
            r9 = this;
            r0 = -1
            r1 = 0
            r2 = 0
            com.android.messaging.ui.mediapicker.F r3 = r9.f2157aI     // Catch:{ RuntimeException -> 0x0057, all -> 0x0053 }
            android.content.Context r3 = r3.getContext()     // Catch:{ RuntimeException -> 0x0057, all -> 0x0053 }
            android.app.Activity r3 = com.android.messaging.util.C1486ya.m3844B(r3)     // Catch:{ RuntimeException -> 0x0057, all -> 0x0053 }
            android.view.Window r3 = r3.getWindow()     // Catch:{ RuntimeException -> 0x0057, all -> 0x0053 }
            r4 = 128(0x80, float:1.794E-43)
            r3.clearFlags(r4)     // Catch:{ RuntimeException -> 0x0057, all -> 0x0053 }
            com.android.messaging.ui.mediapicker.wa r3 = r9.f2150Hg     // Catch:{ RuntimeException -> 0x0057, all -> 0x0053 }
            r3.stop()     // Catch:{ RuntimeException -> 0x0057, all -> 0x0053 }
            com.android.messaging.ui.mediapicker.wa r3 = r9.f2150Hg     // Catch:{ RuntimeException -> 0x0057, all -> 0x0053 }
            int r3 = r3.getVideoWidth()     // Catch:{ RuntimeException -> 0x0057, all -> 0x0053 }
            com.android.messaging.ui.mediapicker.wa r4 = r9.f2150Hg     // Catch:{ RuntimeException -> 0x004d, all -> 0x0047 }
            int r0 = r4.getVideoHeight()     // Catch:{ RuntimeException -> 0x004d, all -> 0x0047 }
            com.android.messaging.ui.mediapicker.wa r4 = r9.f2150Hg     // Catch:{ RuntimeException -> 0x004d, all -> 0x0047 }
            android.net.Uri r4 = r4.mo7966ub()     // Catch:{ RuntimeException -> 0x004d, all -> 0x0047 }
            com.android.messaging.ui.mediapicker.wa r5 = r9.f2150Hg     // Catch:{ RuntimeException -> 0x0045, all -> 0x0043 }
            java.lang.String r5 = r5.getContentType()     // Catch:{ RuntimeException -> 0x0045, all -> 0x0043 }
            com.android.messaging.ui.mediapicker.x r6 = r9.f2156_H
            r9.f2156_H = r2
            r9.m3427Da(r1)
            if (r4 != 0) goto L_0x003f
            r9.m3428Do()
        L_0x003f:
            r6.mo7968a(r4, r5, r3, r0)
            goto L_0x0070
        L_0x0043:
            r5 = move-exception
            goto L_0x0049
        L_0x0045:
            r5 = move-exception
            goto L_0x004f
        L_0x0047:
            r5 = move-exception
            r4 = r2
        L_0x0049:
            r8 = r3
            r3 = r0
            r0 = r8
            goto L_0x0072
        L_0x004d:
            r5 = move-exception
            r4 = r2
        L_0x004f:
            r8 = r3
            r3 = r0
            r0 = r8
            goto L_0x005a
        L_0x0053:
            r5 = move-exception
            r3 = r0
            r4 = r2
            goto L_0x0072
        L_0x0057:
            r5 = move-exception
            r3 = r0
            r4 = r2
        L_0x005a:
            java.lang.String r6 = "MessagingApp"
            java.lang.String r7 = "RuntimeException in CameraManager.stopVideo"
            com.android.messaging.util.C1430e.m3623e(r6, r7, r5)     // Catch:{ all -> 0x0071 }
            com.android.messaging.ui.mediapicker.x r5 = r9.f2156_H
            r9.f2156_H = r2
            r9.m3427Da(r1)
            if (r4 != 0) goto L_0x006d
            r9.m3428Do()
        L_0x006d:
            r5.mo7968a(r4, r2, r0, r3)
        L_0x0070:
            return
        L_0x0071:
            r5 = move-exception
        L_0x0072:
            com.android.messaging.ui.mediapicker.x r6 = r9.f2156_H
            r9.f2156_H = r2
            r9.m3427Da(r1)
            if (r4 != 0) goto L_0x007e
            r9.m3428Do()
        L_0x007e:
            r6.mo7968a(r4, r2, r0, r3)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.C1352t.mo7951tj():void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: uj */
    public void mo7952uj() {
        int i = 0;
        C1424b.m3592ia(this.f2152WH >= 0);
        if (this.f2151VH.facing != 1) {
            i = 1;
        }
        mo7931Ja(i);
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m3442c(Camera camera) {
        if (camera != null) {
            this.f2164hI.mo7801zj();
            new C1336l(this, camera).execute(new Void[0]);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo7938d(RenderOverlay renderOverlay) {
        this.f2164hI.mo7791a(renderOverlay != null ? renderOverlay.mo7780Lb() : null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo7940h(C1275C c) {
        C1275C c2;
        C1424b.m3593oc();
        this.mListener = c;
        if (!this.f2159cI && (c2 = this.mListener) != null) {
            c2.mo7677a(6, (Exception) null);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo7935a(C1278F f) {
        if (f != this.f2157aI) {
            if (f != null) {
                C1424b.m3592ia(f.isValid());
                f.setOnTouchListener(new C1330i(this));
            }
            this.f2157aI = f;
            m3429Eo();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo7934a(C0943z zVar) {
        this.f2166pb = zVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo7933a(float f, C1360x xVar) {
        C1424b.m3592ia(!this.f2155ZH);
        C1424b.m3592ia(!this.f2163gI);
        C1424b.m3594t(xVar);
        if (this.f2162fI == null) {
            xVar.mo7969b((Exception) null);
            return;
        }
        C1334k kVar = new C1334k(this, xVar, f);
        this.f2163gI = true;
        try {
            this.f2162fI.takePicture((Camera.ShutterCallback) null, (Camera.PictureCallback) null, (Camera.PictureCallback) null, kVar);
        } catch (RuntimeException e) {
            C1430e.m3623e("MessagingApp", "RuntimeException in CameraManager.takePicture", e);
            this.f2163gI = false;
            C1275C c = this.mListener;
            if (c != null) {
                c.mo7677a(7, (Exception) e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo7936a(C1360x xVar) {
        C1424b.m3594t(xVar);
        C1424b.m3592ia(!mo7948qj());
        this.f2156_H = xVar;
        m3430Fo();
    }

    /* renamed from: a */
    private Camera.Size m3433a(Camera.Size size) {
        ArrayList arrayList = new ArrayList(this.f2162fI.getParameters().getSupportedPreviewSizes());
        int i = size.width;
        int i2 = size.height;
        Collections.sort(arrayList, new C1350s(Integer.MAX_VALUE, Integer.MAX_VALUE, ((float) i) / ((float) i2), i * i2));
        return (Camera.Size) arrayList.get(0);
    }

    /* renamed from: a */
    private void m3437a(String str, Camera.Size size) {
        StringBuilder Pa = C0632a.m1011Pa(str);
        Pa.append(size.width);
        Pa.append("x");
        Pa.append(size.height);
        Pa.append(" (");
        Pa.append(((float) size.width) / ((float) size.height));
        Pa.append(")");
        C1430e.m3625i("MessagingApp", Pa.toString());
    }
}
