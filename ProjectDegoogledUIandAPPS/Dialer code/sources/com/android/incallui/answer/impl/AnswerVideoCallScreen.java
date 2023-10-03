package com.android.incallui.answer.impl;

import android.graphics.Point;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.appcompat.R$style;
import android.view.TextureView;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.incallui.VideoCallPresenter;
import com.android.incallui.video.protocol.VideoCallScreen;
import com.android.incallui.video.protocol.VideoCallScreenDelegate;
import com.android.incallui.video.protocol.VideoCallScreenDelegateFactory;
import com.android.incallui.videosurface.impl.VideoSurfaceTextureImpl;

public class AnswerVideoCallScreen implements VideoCallScreen {
    private final String callId;
    private final VideoCallScreenDelegate delegate;
    private final Fragment fragment;
    private final TextureView textureView;

    public AnswerVideoCallScreen(String str, Fragment fragment2, View view) {
        Assert.isNotNull(str);
        this.callId = str;
        Assert.isNotNull(fragment2);
        this.fragment = fragment2;
        TextureView textureView2 = (TextureView) view.findViewById(R.id.incoming_preview_texture_view);
        Assert.isNotNull(textureView2);
        this.textureView = textureView2;
        View findViewById = view.findViewById(R.id.incoming_preview_texture_view_overlay);
        Assert.isNotNull(findViewById);
        view.setBackgroundColor(-16777216);
        this.delegate = ((VideoCallScreenDelegateFactory) FragmentUtils.getParentUnsafe(fragment2, VideoCallScreenDelegateFactory.class)).newVideoCallScreenDelegate(this);
        ((VideoCallPresenter) this.delegate).initVideoCallScreenDelegate(fragment2.getContext(), this);
        this.textureView.setVisibility(0);
        findViewById.setVisibility(0);
    }

    private void updatePreviewVideoScaling() {
        boolean z = false;
        if (this.textureView.getWidth() == 0 || this.textureView.getHeight() == 0) {
            LogUtil.m9i("AnswerVideoCallScreen.updatePreviewVideoScaling", "view layout hasn't finished yet", new Object[0]);
            return;
        }
        Point surfaceDimensions = ((VideoSurfaceTextureImpl) ((VideoCallPresenter) this.delegate).getLocalVideoSurfaceTexture()).getSurfaceDimensions();
        if (surfaceDimensions == null) {
            LogUtil.m9i("AnswerVideoCallScreen.updatePreviewVideoScaling", "camera dimensions not set", new Object[0]);
            return;
        }
        if (this.fragment.getResources().getConfiguration().orientation == 2) {
            z = true;
        }
        if (z) {
            R$style.scaleVideoAndFillView(this.textureView, (float) surfaceDimensions.x, (float) surfaceDimensions.y, (float) ((VideoCallPresenter) this.delegate).getDeviceOrientation());
        } else {
            R$style.scaleVideoAndFillView(this.textureView, (float) surfaceDimensions.y, (float) surfaceDimensions.x, (float) ((VideoCallPresenter) this.delegate).getDeviceOrientation());
        }
    }

    public String getCallId() {
        return this.callId;
    }

    public Fragment getVideoCallScreenFragment() {
        return this.fragment;
    }

    public void onHandoverFromWiFiToLte() {
    }

    public void onLocalVideoDimensionsChanged() {
        LogUtil.m9i("AnswerVideoCallScreen.onLocalVideoDimensionsChanged", (String) null, new Object[0]);
        updatePreviewVideoScaling();
    }

    public void onLocalVideoOrientationChanged() {
        LogUtil.m9i("AnswerVideoCallScreen.onLocalVideoOrientationChanged", (String) null, new Object[0]);
        updatePreviewVideoScaling();
    }

    public void onRemoteVideoDimensionsChanged() {
    }

    public void onVideoScreenStart() {
        LogUtil.m9i("AnswerVideoCallScreen.onStart", (String) null, new Object[0]);
        ((VideoCallPresenter) this.delegate).onVideoCallScreenUiReady();
        ((VideoSurfaceTextureImpl) ((VideoCallPresenter) this.delegate).getLocalVideoSurfaceTexture()).attachToTextureView(this.textureView);
    }

    public void onVideoScreenStop() {
        LogUtil.m9i("AnswerVideoCallScreen.onStop", (String) null, new Object[0]);
        ((VideoCallPresenter) this.delegate).onVideoCallScreenUiUnready();
    }

    public void showVideoViews(boolean z, boolean z2, boolean z3) {
        LogUtil.m9i("AnswerVideoCallScreen.showVideoViews", "showPreview: %b, shouldShowRemote: %b", Boolean.valueOf(z), Boolean.valueOf(z2));
    }

    public void updateFullscreenAndGreenScreenMode(boolean z, boolean z2) {
    }
}
