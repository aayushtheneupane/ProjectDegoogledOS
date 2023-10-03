package com.android.incallui.videosurface.protocol;

public interface VideoSurfaceDelegate {
    void onSurfaceClick(VideoSurfaceTexture videoSurfaceTexture);

    void onSurfaceCreated(VideoSurfaceTexture videoSurfaceTexture);

    void onSurfaceDestroyed(VideoSurfaceTexture videoSurfaceTexture);

    void onSurfaceReleased(VideoSurfaceTexture videoSurfaceTexture);
}
