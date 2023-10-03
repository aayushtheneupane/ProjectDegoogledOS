package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.c */
public class C1306c {

    /* renamed from: Ds */
    private int f2066Ds;

    /* renamed from: EI */
    private boolean f2067EI;

    /* renamed from: FI */
    private boolean f2068FI;

    /* renamed from: GI */
    private boolean f2069GI;

    /* renamed from: HI */
    private boolean f2070HI;

    /* renamed from: II */
    private C1317n f2071II;

    /* renamed from: JI */
    private int f2072JI;

    /* renamed from: KI */
    private boolean f2073KI;

    /* renamed from: LI */
    private int f2074LI;

    /* renamed from: MI */
    private List f2075MI;

    /* renamed from: OI */
    private List f2076OI;

    /* renamed from: QI */
    private String f2077QI;

    /* renamed from: RI */
    private String f2078RI;

    /* renamed from: TI */
    private Camera.Parameters f2079TI;
    private Handler mHandler;
    private boolean mInitialized;
    C1304a mListener;
    private Matrix mMatrix;
    private int mState = 0;

    public C1306c(C1304a aVar, Looper looper) {
        this.mHandler = new C1305b(this, looper);
        this.mMatrix = new Matrix();
        this.mListener = aVar;
    }

    /* renamed from: Io */
    private void m3317Io() {
        if (this.f2072JI != 0 && this.f2066Ds != 0) {
            Matrix matrix = new Matrix();
            boolean z = this.f2073KI;
            int i = this.f2074LI;
            int i2 = this.f2072JI;
            int i3 = this.f2066Ds;
            matrix.setScale(z ? -1.0f : 1.0f, 1.0f);
            matrix.postRotate((float) i);
            float f = (float) i2;
            float f2 = (float) i3;
            matrix.postScale(f / 2000.0f, f2 / 2000.0f);
            matrix.postTranslate(f / 2.0f, f2 / 2.0f);
            matrix.invert(this.mMatrix);
            this.mInitialized = this.f2071II != null;
        }
    }

    /* renamed from: b */
    public static boolean m3320b(String str, List list) {
        return list != null && list.indexOf(str) >= 0;
    }

    /* access modifiers changed from: private */
    public void cancelAutoFocus() {
        C1430e.m3628v("MessagingApp", "Cancel autofocus.");
        if (this.mInitialized) {
            this.f2071II.clear();
            this.f2075MI = null;
            this.f2076OI = null;
        }
        this.mListener.cancelAutoFocus();
        this.mState = 0;
        mo7789Aj();
        this.mHandler.removeMessages(0);
    }

    /* renamed from: Aj */
    public void mo7789Aj() {
        if (this.mInitialized) {
            C1317n nVar = this.f2071II;
            int i = this.mState;
            if (i == 0) {
                if (this.f2075MI == null) {
                    nVar.clear();
                } else {
                    nVar.mo7838Bj();
                }
            } else if (i == 1 || i == 2) {
                nVar.mo7838Bj();
            } else if ("continuous-picture".equals(this.f2077QI)) {
                nVar.mo7843ga(false);
            } else {
                int i2 = this.mState;
                if (i2 == 3) {
                    nVar.mo7843ga(false);
                } else if (i2 == 4) {
                    nVar.mo7842fa(false);
                }
            }
        }
    }

    /* renamed from: C */
    public void mo7790C(int i, int i2) {
        int i3;
        if (this.mInitialized && (i3 = this.mState) != 2) {
            if (this.f2075MI != null && (i3 == 1 || i3 == 3 || i3 == 4)) {
                cancelAutoFocus();
            }
            int size = this.f2071II.getSize();
            int size2 = this.f2071II.getSize();
            if (size != 0) {
                C1317n nVar = this.f2071II;
                if (nVar.mRight - nVar.mLeft != 0 && nVar.mBottom - nVar.mTop != 0) {
                    int i4 = this.f2072JI;
                    int i5 = this.f2066Ds;
                    if (this.f2067EI) {
                        if (this.f2075MI == null) {
                            this.f2075MI = new ArrayList();
                            this.f2075MI.add(new Camera.Area(new Rect(), 1));
                        }
                        m3318a(size, size2, 1.0f, i, i2, i4, i5, ((Camera.Area) this.f2075MI.get(0)).rect);
                    }
                    if (this.f2068FI) {
                        if (this.f2076OI == null) {
                            this.f2076OI = new ArrayList();
                            this.f2076OI.add(new Camera.Area(new Rect(), 1));
                        }
                        m3318a(size, size2, 1.5f, i, i2, i4, i5, ((Camera.Area) this.f2076OI.get(0)).rect);
                    }
                    this.f2071II.mo7840E(i, i2);
                    this.mListener.mo7784N();
                    if (this.f2067EI) {
                        C1430e.m3628v("MessagingApp", "Start autofocus.");
                        this.mListener.mo7786ia();
                        this.mState = 1;
                        mo7789Aj();
                        this.mHandler.removeMessages(0);
                        return;
                    }
                    mo7789Aj();
                    this.mHandler.removeMessages(0);
                    this.mHandler.sendEmptyMessageDelayed(0, 3000);
                }
            }
        }
    }

    /* renamed from: da */
    public void mo7792da(boolean z) {
        if (!this.mInitialized || this.mState != 0) {
            return;
        }
        if (z) {
            this.f2071II.mo7838Bj();
        } else {
            this.f2071II.mo7843ga(true);
        }
    }

    /* renamed from: ea */
    public void mo7793ea(boolean z) {
        this.f2073KI = z;
        m3317Io();
    }

    public List getFocusAreas() {
        return this.f2075MI;
    }

    public String getFocusMode() {
        String str = this.f2078RI;
        if (str != null) {
            return str;
        }
        List<String> supportedFocusModes = this.f2079TI.getSupportedFocusModes();
        if (!this.f2067EI || this.f2075MI == null) {
            this.f2077QI = "continuous-picture";
        } else {
            this.f2077QI = "auto";
        }
        if (!m3320b(this.f2077QI, supportedFocusModes)) {
            if (m3320b("auto", this.f2079TI.getSupportedFocusModes())) {
                this.f2077QI = "auto";
            } else {
                this.f2077QI = this.f2079TI.getFocusMode();
            }
        }
        return this.f2077QI;
    }

    public List getMeteringAreas() {
        return this.f2076OI;
    }

    /* renamed from: l */
    public void mo7797l(boolean z, boolean z2) {
        int i = this.mState;
        if (i == 2) {
            if (z) {
                this.mState = 3;
            } else {
                this.mState = 4;
            }
            mo7789Aj();
            if (this.mListener.mo7787n()) {
                this.mState = 0;
                this.mHandler.removeMessages(0);
            }
        } else if (i == 1) {
            if (z) {
                this.mState = 3;
            } else {
                this.mState = 4;
            }
            mo7789Aj();
            if (this.f2075MI != null) {
                this.mHandler.sendEmptyMessageDelayed(0, 3000);
            }
            if (z2 && this.f2069GI && !this.f2070HI) {
                this.f2070HI = true;
                this.mListener.mo7784N();
            }
        }
    }

    public void setParameters(Camera.Parameters parameters) {
        if (parameters != null) {
            this.f2079TI = parameters;
            boolean z = true;
            this.f2067EI = parameters.getMaxNumFocusAreas() > 0 && m3320b("auto", parameters.getSupportedFocusModes());
            this.f2068FI = parameters.getMaxNumMeteringAreas() > 0;
            if (!"true".equals(this.f2079TI.get("auto-exposure-lock-supported")) && !"true".equals(this.f2079TI.get("auto-whitebalance-lock-supported"))) {
                z = false;
            }
            this.f2069GI = z;
        }
    }

    public void setPreviewSize(int i, int i2) {
        if (this.f2072JI != i || this.f2066Ds != i2) {
            this.f2072JI = i;
            this.f2066Ds = i2;
            m3317Io();
        }
    }

    /* renamed from: yj */
    public void mo7800yj() {
        this.mState = 0;
    }

    /* renamed from: zj */
    public void mo7801zj() {
        this.mState = 0;
        if (this.mInitialized) {
            this.f2071II.clear();
            this.f2075MI = null;
            this.f2076OI = null;
        }
        mo7789Aj();
    }

    /* renamed from: a */
    public void mo7791a(C1317n nVar) {
        this.f2071II = nVar;
        this.mInitialized = this.mMatrix != null;
    }

    /* renamed from: a */
    private void m3318a(int i, int i2, float f, int i3, int i4, int i5, int i6, Rect rect) {
        int i7 = (int) (((float) i) * f);
        int i8 = (int) (((float) i2) * f);
        int i9 = i3 - (i7 / 2);
        int i10 = i5 - i7;
        boolean z = true;
        C1424b.m3592ia(i10 >= 0);
        if (i9 <= i10) {
            i10 = i9 < 0 ? 0 : i9;
        }
        int i11 = i4 - (i8 / 2);
        int i12 = i6 - i8;
        if (i12 < 0) {
            z = false;
        }
        C1424b.m3592ia(z);
        if (i11 > i12) {
            i11 = i12;
        } else if (i11 < 0) {
            i11 = 0;
        }
        RectF rectF = new RectF((float) i10, (float) i11, (float) (i10 + i7), (float) (i11 + i8));
        this.mMatrix.mapRect(rectF);
        rect.left = Math.round(rectF.left);
        rect.top = Math.round(rectF.top);
        rect.right = Math.round(rectF.right);
        rect.bottom = Math.round(rectF.bottom);
    }
}
