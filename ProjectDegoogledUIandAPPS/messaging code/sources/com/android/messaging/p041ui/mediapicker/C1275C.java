package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.hardware.Camera;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0943z;
import com.android.messaging.p041ui.mediapicker.camerafocus.RenderOverlay;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.mediapicker.C */
class C1275C extends C1323ea {

    /* renamed from: pF */
    private View f1999pF;

    /* renamed from: qF */
    private View f2000qF;
    /* access modifiers changed from: private */

    /* renamed from: rF */
    public C1277E f2001rF;

    /* renamed from: sF */
    private ImageButton f2002sF;

    /* renamed from: tF */
    private ImageButton f2003tF;

    /* renamed from: uF */
    private ImageButton f2004uF;

    /* renamed from: vF */
    private ImageButton f2005vF;

    /* renamed from: wF */
    private ImageButton f2006wF;
    /* access modifiers changed from: private */

    /* renamed from: xF */
    public Chronometer f2007xF;
    /* access modifiers changed from: private */

    /* renamed from: yF */
    public boolean f2008yF;

    /* renamed from: zF */
    private int f2009zF;

    C1275C(C1345pa paVar) {
        super(paVar);
    }

    /* renamed from: Ca */
    private void m3203Ca(boolean z) {
        View view = this.f1999pF;
        if (view != null) {
            int i = 0;
            view.setVisibility(z ? 0 : 8);
            View view2 = this.f2000qF;
            if (z) {
                i = 8;
            }
            view2.setVisibility(i);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: oo */
    public void m3212oo() {
        C1352t.get().mo7937ca(!C1352t.get().mo7949rj());
        if (C1352t.get().mo7949rj()) {
            this.f2118Dj.setFullScreen(true);
            this.f2005vF.performClick();
        }
        m3213po();
    }

    /* access modifiers changed from: private */
    /* renamed from: po */
    public void m3213po() {
        Context context;
        if (this.mView != null && (context = getContext()) != null) {
            boolean isFullScreen = this.f2118Dj.isFullScreen();
            boolean rj = C1352t.get().mo7949rj();
            boolean qj = C1352t.get().mo7948qj();
            boolean pj = C1352t.get().mo7947pj();
            Camera.CameraInfo cameraInfo = C1352t.get().getCameraInfo();
            boolean z = true;
            int i = 0;
            if (cameraInfo == null || cameraInfo.facing != 1) {
                z = false;
            }
            this.mView.setSystemUiVisibility(isFullScreen ? 1 : 0);
            this.f2002sF.setVisibility(!isFullScreen ? 0 : 8);
            this.f2002sF.setEnabled(pj);
            this.f2003tF.setVisibility((!isFullScreen || qj || !C1352t.get().mo7944oj()) ? 8 : 0);
            this.f2003tF.setImageResource(z ? R.drawable.ic_camera_front_light : R.drawable.ic_camera_rear_light);
            this.f2003tF.setEnabled(pj);
            this.f2006wF.setVisibility(qj ? 0 : 8);
            this.f2007xF.setVisibility(qj ? 0 : 8);
            this.f2004uF.setImageResource(rj ? R.drawable.ic_mp_camera_small_light : R.drawable.ic_mp_video_small_light);
            this.f2004uF.setContentDescription(context.getString(rj ? R.string.camera_switch_to_still_mode : R.string.camera_switch_to_video_mode));
            ImageButton imageButton = this.f2004uF;
            if (qj) {
                i = 8;
            }
            imageButton.setVisibility(i);
            this.f2004uF.setEnabled(pj);
            if (qj) {
                this.f2005vF.setImageResource(R.drawable.ic_mp_capture_stop_large_light);
                this.f2005vF.setContentDescription(context.getString(R.string.camera_stop_recording));
            } else if (rj) {
                this.f2005vF.setImageResource(R.drawable.ic_mp_video_large_light);
                this.f2005vF.setContentDescription(context.getString(R.string.camera_start_recording));
            } else {
                this.f2005vF.setImageResource(R.drawable.ic_checkmark_large_light);
                this.f2005vF.setContentDescription(context.getString(R.string.camera_take_picture));
            }
            this.f2005vF.setEnabled(pj);
        }
    }

    /* renamed from: Eb */
    public boolean mo7671Eb() {
        return C1352t.get().mo7949rj();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pi */
    public int mo7672Pi() {
        return 0;
    }

    /* renamed from: Qi */
    public int mo7673Qi() {
        return R.string.mediapicker_cameraChooserDescription;
    }

    /* renamed from: Ri */
    public int mo7674Ri() {
        return C1352t.get().mo7943mj() ? 3 : 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: S */
    public void mo7675S(boolean z) {
        m3213po();
    }

    /* renamed from: Ui */
    public void mo7676Ui() {
        m3213po();
    }

    /* access modifiers changed from: protected */
    public View createView(ViewGroup viewGroup) {
        C1352t.get().mo7940h(this);
        C1352t.get().mo7934a((C0943z) this);
        C1352t.get().mo7937ca(false);
        CameraMediaChooserView cameraMediaChooserView = (CameraMediaChooserView) getLayoutInflater().inflate(R.layout.mediapicker_camera_chooser, viewGroup, false);
        this.f2001rF = (C1277E) cameraMediaChooserView.findViewById(R.id.camera_preview);
        this.f2001rF.getView().setOnTouchListener(new C1354u(this));
        View findViewById = cameraMediaChooserView.findViewById(R.id.camera_shutter_visual);
        this.f2002sF = (ImageButton) cameraMediaChooserView.findViewById(R.id.camera_fullScreen_button);
        this.f2002sF.setOnClickListener(new C1356v(this));
        this.f2003tF = (ImageButton) cameraMediaChooserView.findViewById(R.id.camera_swapCamera_button);
        this.f2003tF.setOnClickListener(new C1358w(this));
        this.f2005vF = (ImageButton) cameraMediaChooserView.findViewById(R.id.camera_capture_button);
        this.f2005vF.setOnClickListener(new C1362y(this, findViewById));
        this.f2004uF = (ImageButton) cameraMediaChooserView.findViewById(R.id.camera_swap_mode_button);
        this.f2004uF.setOnClickListener(new C1364z(this));
        this.f2006wF = (ImageButton) cameraMediaChooserView.findViewById(R.id.camera_cancel_button);
        this.f2006wF.setOnClickListener(new C1273A(this));
        this.f2007xF = (Chronometer) cameraMediaChooserView.findViewById(R.id.camera_video_counter);
        C1352t.get().mo7938d((RenderOverlay) cameraMediaChooserView.findViewById(R.id.focus_visual));
        this.f1999pF = cameraMediaChooserView.findViewById(R.id.mediapicker_enabled);
        this.f2000qF = cameraMediaChooserView.findViewById(R.id.missing_permission_view);
        this.mView = cameraMediaChooserView;
        m3213po();
        m3203Ca(C1352t.m3450nj());
        return cameraMediaChooserView;
    }

    public View destroyView() {
        C1352t.get().mo7941kj();
        C1352t.get().mo7940h((C1275C) null);
        C1352t.get().mo7934a((C0943z) null);
        return super.destroyView();
    }

    public int getIconResource() {
        return R.drawable.ic_camera_light;
    }

    /* access modifiers changed from: protected */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        boolean z = true;
        if (i == 1) {
            if (iArr[0] != 0) {
                z = false;
            }
            m3203Ca(z);
            if (z) {
                this.f2001rF.mo7687ca();
            }
        } else if (i == 3) {
            C1424b.m3591ha(C1352t.get().mo7949rj());
            if (iArr[0] == 0) {
                m3212oo();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setSelected(boolean z) {
        super.setSelected(z);
        if (!z) {
            return;
        }
        if (C1352t.m3450nj()) {
            int i = this.f2009zF;
            if (i != 0 && this.mSelected) {
                C1486ya.m3848Pa(i);
                this.f2009zF = 0;
                return;
            }
            return;
        }
        this.f2118Dj.requestPermissions(new String[]{"android.permission.CAMERA"}, 1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo7678b(boolean z) {
        if (!z && C1352t.get().mo7949rj()) {
            C1352t.get().mo7937ca(false);
        }
        m3213po();
    }

    /* renamed from: a */
    public void mo7677a(int i, Exception exc) {
        if (i == 1 || i == 2) {
            this.f2009zF = R.string.camera_error_opening;
        } else if (i == 3) {
            this.f2009zF = R.string.camera_error_video_init_fail;
            m3213po();
        } else if (i == 4) {
            this.f2009zF = R.string.camera_error_storage_fail;
            m3213po();
        } else if (i != 7) {
            this.f2009zF = R.string.camera_error_unknown;
            C1430e.m3630w("MessagingApp", "Unknown camera error:" + i);
        } else {
            this.f2009zF = R.string.camera_error_failure_taking_picture;
        }
        int i2 = this.f2009zF;
        if (i2 != 0 && this.mSelected) {
            C1486ya.m3848Pa(i2);
            this.f2009zF = 0;
        }
    }
}
