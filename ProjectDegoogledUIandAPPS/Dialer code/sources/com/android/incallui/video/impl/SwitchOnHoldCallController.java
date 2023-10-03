package com.android.incallui.video.impl;

import android.view.View;
import com.android.dialer.common.Assert;
import com.android.incallui.VideoCallPresenter;
import com.android.incallui.incall.protocol.InCallScreenDelegate;
import com.android.incallui.incall.protocol.SecondaryInfo;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;

public class SwitchOnHoldCallController implements View.OnClickListener {
    private InCallScreenDelegate inCallScreenDelegate;
    private boolean isEnabled;
    private boolean isVisible;
    private View onHoldBanner;
    private SecondaryInfo secondaryInfo;
    private View switchOnHoldButton;
    private VideoCallScreenDelegate videoCallScreenDelegate;

    public SwitchOnHoldCallController(View view, View view2, InCallScreenDelegate inCallScreenDelegate2, VideoCallScreenDelegate videoCallScreenDelegate2) {
        Assert.isNotNull(view);
        this.switchOnHoldButton = view;
        view.setOnClickListener(this);
        Assert.isNotNull(view2);
        this.onHoldBanner = view2;
        Assert.isNotNull(inCallScreenDelegate2);
        this.inCallScreenDelegate = inCallScreenDelegate2;
        Assert.isNotNull(videoCallScreenDelegate2);
        this.videoCallScreenDelegate = videoCallScreenDelegate2;
    }

    public void onClick(View view) {
        this.inCallScreenDelegate.onSecondaryInfoClicked();
        ((VideoCallPresenter) this.videoCallScreenDelegate).resetAutoFullscreenTimer();
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
        updateButtonState();
    }

    public void setOnScreen() {
        SecondaryInfo secondaryInfo2 = this.secondaryInfo;
        this.isVisible = secondaryInfo2 != null && secondaryInfo2.shouldShow();
        updateButtonState();
    }

    public void setSecondaryInfo(SecondaryInfo secondaryInfo2) {
        this.secondaryInfo = secondaryInfo2;
        SecondaryInfo secondaryInfo3 = this.secondaryInfo;
        this.isVisible = secondaryInfo3 != null && secondaryInfo3.shouldShow();
    }

    public void setVisible(boolean z) {
        this.isVisible = z;
        updateButtonState();
    }

    public void updateButtonState() {
        this.switchOnHoldButton.setEnabled(this.isEnabled);
        int i = 0;
        this.switchOnHoldButton.setVisibility(this.isVisible ? 0 : 4);
        View view = this.onHoldBanner;
        if (!this.isVisible) {
            i = 4;
        }
        view.setVisibility(i);
    }
}
