package com.android.incallui.video.impl;

import android.telecom.CallAudioState;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.incallui.VideoCallPresenter;
import com.android.incallui.incall.protocol.InCallButtonUiDelegate;
import com.android.incallui.video.impl.CheckableImageButton;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;
import com.android.tools.p006r8.GeneratedOutlineSupport;

public class SpeakerButtonController implements CheckableImageButton.OnCheckedChangeListener, View.OnClickListener {
    private CheckableImageButton button;
    private boolean checkable;
    private CharSequence contentDescription;
    private int icon = R.drawable.quantum_ic_volume_up_vd_theme_24;
    private final InCallButtonUiDelegate inCallButtonUiDelegate;
    private boolean isChecked;
    private boolean isEnabled;
    private final VideoCallScreenDelegate videoCallScreenDelegate;

    SpeakerButtonController(CheckableImageButton checkableImageButton, InCallButtonUiDelegate inCallButtonUiDelegate2, VideoCallScreenDelegate videoCallScreenDelegate2) {
        Assert.isNotNull(inCallButtonUiDelegate2);
        this.inCallButtonUiDelegate = inCallButtonUiDelegate2;
        Assert.isNotNull(videoCallScreenDelegate2);
        this.videoCallScreenDelegate = videoCallScreenDelegate2;
        Assert.isNotNull(checkableImageButton);
        this.button = checkableImageButton;
    }

    public void onCheckedChanged(CheckableImageButton checkableImageButton, boolean z) {
        LogUtil.m9i("SpeakerButtonController.onCheckedChanged", (String) null, new Object[0]);
        this.inCallButtonUiDelegate.toggleSpeakerphone();
        ((VideoCallPresenter) this.videoCallScreenDelegate).resetAutoFullscreenTimer();
    }

    public void onClick(View view) {
        LogUtil.m9i("SpeakerButtonController.onClick", (String) null, new Object[0]);
        this.inCallButtonUiDelegate.showAudioRouteSelector();
        ((VideoCallPresenter) this.videoCallScreenDelegate).resetAutoFullscreenTimer();
    }

    public void setAudioState(CallAudioState callAudioState) {
        LogUtil.m9i("SpeakerButtonController.setSupportedAudio", GeneratedOutlineSupport.outline6("audioState: ", callAudioState), new Object[0]);
        int supportedRouteMask = callAudioState.getSupportedRouteMask() & 2;
        int i = R.string.incall_content_description_speaker;
        if (supportedRouteMask == 2) {
            this.checkable = false;
            this.isChecked = false;
            if ((callAudioState.getRoute() & 2) == 2) {
                this.icon = R.drawable.quantum_ic_bluetooth_audio_vd_theme_24;
                i = R.string.incall_content_description_bluetooth;
            } else if ((callAudioState.getRoute() & 8) == 8) {
                this.icon = R.drawable.quantum_ic_volume_up_vd_theme_24;
            } else if ((callAudioState.getRoute() & 4) == 4) {
                this.icon = R.drawable.quantum_ic_headset_vd_theme_24;
                i = R.string.incall_content_description_headset;
            } else {
                this.icon = R.drawable.quantum_ic_phone_in_talk_vd_theme_24;
                i = R.string.incall_content_description_earpiece;
            }
        } else {
            boolean z = true;
            this.checkable = true;
            if (callAudioState.getRoute() != 8) {
                z = false;
            }
            this.isChecked = z;
            this.icon = R.drawable.quantum_ic_volume_up_vd_theme_24;
        }
        this.contentDescription = this.button.getContext().getText(i);
        updateButtonState();
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
        updateButtonState();
    }

    /* access modifiers changed from: package-private */
    public void updateButtonState() {
        this.button.setVisibility(0);
        this.button.setEnabled(this.isEnabled);
        this.button.setChecked(this.isChecked);
        SpeakerButtonController speakerButtonController = null;
        this.button.setOnClickListener(this.checkable ? null : this);
        CheckableImageButton checkableImageButton = this.button;
        if (this.checkable) {
            speakerButtonController = this;
        }
        checkableImageButton.setOnCheckedChangeListener(speakerButtonController);
        this.button.setImageResource(this.icon);
        this.button.setContentDescription(this.contentDescription);
    }
}
