package com.android.messaging.p041ui.mediapicker;

/* renamed from: com.android.messaging.ui.mediapicker.sa */
class C1351sa implements Runnable {

    /* renamed from: lH */
    final /* synthetic */ boolean f2147lH;
    final /* synthetic */ MediaPickerPanel this$0;

    C1351sa(MediaPickerPanel mediaPickerPanel, boolean z) {
        this.this$0 = mediaPickerPanel;
        this.f2147lH = z;
    }

    public void run() {
        MediaPickerPanel mediaPickerPanel = this.this$0;
        mediaPickerPanel.m3265d(mediaPickerPanel.getDesiredHeight(), this.f2147lH);
    }
}
