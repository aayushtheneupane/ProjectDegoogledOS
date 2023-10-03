package com.android.messaging.p041ui.mediapicker;

import android.view.ViewGroup;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.mediapicker.D */
class C1276D implements Runnable {
    final /* synthetic */ CameraMediaChooserView this$0;

    C1276D(CameraMediaChooserView cameraMediaChooserView) {
        this.this$0 = cameraMediaChooserView;
    }

    public void run() {
        HardwareCameraPreview hardwareCameraPreview = (HardwareCameraPreview) this.this$0.findViewById(R.id.camera_preview);
        if (hardwareCameraPreview != null) {
            ViewGroup viewGroup = (ViewGroup) hardwareCameraPreview.getParent();
            int indexOfChild = viewGroup.indexOfChild(hardwareCameraPreview);
            C1363ya yaVar = new C1363ya(this.this$0.getContext());
            viewGroup.removeView(hardwareCameraPreview);
            viewGroup.addView(yaVar, indexOfChild);
        }
    }
}
