package com.android.incallui.videosurface.impl;

import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import com.android.dialer.common.LogUtil;
import com.android.incallui.videosurface.protocol.VideoSurfaceDelegate;
import com.android.incallui.videosurface.protocol.VideoSurfaceTexture;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Locale;
import java.util.Objects;

public class VideoSurfaceTextureImpl implements VideoSurfaceTexture {
    /* access modifiers changed from: private */
    public VideoSurfaceDelegate delegate;
    /* access modifiers changed from: private */
    public boolean isDoneWithSurface;
    private final boolean isPixel2017;
    /* access modifiers changed from: private */
    public Surface savedSurface;
    /* access modifiers changed from: private */
    public SurfaceTexture savedSurfaceTexture;
    private Point sourceVideoDimensions;
    private Point surfaceDimensions;
    private final int surfaceType;
    /* access modifiers changed from: private */
    public TextureView textureView;

    private class OnClickListener implements View.OnClickListener {
        /* synthetic */ OnClickListener(C07651 r2) {
        }

        public void onClick(View view) {
            if (VideoSurfaceTextureImpl.this.delegate != null) {
                VideoSurfaceTextureImpl.this.delegate.onSurfaceClick(VideoSurfaceTextureImpl.this);
            } else {
                LogUtil.m8e("OnClickListener.onClick", "delegate is null", new Object[0]);
            }
        }
    }

    private class SurfaceTextureListener implements TextureView.SurfaceTextureListener {
        /* synthetic */ SurfaceTextureListener(C07651 r2) {
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            LogUtil.m9i("SurfaceTextureListener.onSurfaceTextureAvailable", "newSurfaceTexture: " + surfaceTexture + " " + VideoSurfaceTextureImpl.this.toString(), new Object[0]);
            if (VideoSurfaceTextureImpl.this.savedSurfaceTexture == null) {
                SurfaceTexture unused = VideoSurfaceTextureImpl.this.savedSurfaceTexture = surfaceTexture;
                boolean unused2 = VideoSurfaceTextureImpl.this.createSurface(i, i2);
            } else {
                LogUtil.m9i("SurfaceTextureListener.onSurfaceTextureAvailable", "replacing with cached surface...", new Object[0]);
                VideoSurfaceTextureImpl.this.textureView.setSurfaceTexture(VideoSurfaceTextureImpl.this.savedSurfaceTexture);
            }
            VideoSurfaceTextureImpl.this.onSurfaceCreated();
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            LogUtil.m9i("SurfaceTextureListener.onSurfaceTextureDestroyed", "destroyedSurfaceTexture: %s, %s, isDoneWithSurface: %b", surfaceTexture, VideoSurfaceTextureImpl.this.toString(), Boolean.valueOf(VideoSurfaceTextureImpl.this.isDoneWithSurface));
            if (VideoSurfaceTextureImpl.this.delegate != null) {
                VideoSurfaceTextureImpl.this.delegate.onSurfaceDestroyed(VideoSurfaceTextureImpl.this);
            } else {
                LogUtil.m8e("SurfaceTextureListener.onSurfaceTextureDestroyed", "delegate is null", new Object[0]);
            }
            if (VideoSurfaceTextureImpl.this.isDoneWithSurface) {
                VideoSurfaceTextureImpl.this.onSurfaceReleased();
                if (VideoSurfaceTextureImpl.this.savedSurface != null) {
                    VideoSurfaceTextureImpl.this.savedSurface.release();
                    Surface unused = VideoSurfaceTextureImpl.this.savedSurface = null;
                }
            }
            return VideoSurfaceTextureImpl.this.isDoneWithSurface;
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    public VideoSurfaceTextureImpl(boolean z, int i) {
        this.isPixel2017 = z;
        this.surfaceType = i;
    }

    /* access modifiers changed from: private */
    public boolean createSurface(int i, int i2) {
        LogUtil.m9i("VideoSurfaceTextureImpl.createSurface", "width: " + i + ", height: " + i2 + " " + toString(), new Object[0]);
        this.savedSurfaceTexture.setDefaultBufferSize(i, i2);
        Surface surface = this.savedSurface;
        if (surface != null) {
            surface.release();
        }
        this.savedSurface = new Surface(this.savedSurfaceTexture);
        return true;
    }

    /* access modifiers changed from: private */
    public void onSurfaceCreated() {
        VideoSurfaceDelegate videoSurfaceDelegate = this.delegate;
        if (videoSurfaceDelegate != null) {
            videoSurfaceDelegate.onSurfaceCreated(this);
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("delegate is null. ");
        outline13.append(toString());
        LogUtil.m8e("VideoSurfaceTextureImpl.onSurfaceCreated", outline13.toString(), new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onSurfaceReleased() {
        VideoSurfaceDelegate videoSurfaceDelegate = this.delegate;
        if (videoSurfaceDelegate != null) {
            videoSurfaceDelegate.onSurfaceReleased(this);
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("delegate is null. ");
        outline13.append(toString());
        LogUtil.m8e("VideoSurfaceTextureImpl.onSurfaceReleased", outline13.toString(), new Object[0]);
    }

    public void attachToTextureView(TextureView textureView2) {
        if (this.textureView != textureView2) {
            LogUtil.m9i("VideoSurfaceTextureImpl.attachToTextureView", toString(), new Object[0]);
            TextureView textureView3 = this.textureView;
            if (textureView3 != null) {
                textureView3.setOnClickListener((View.OnClickListener) null);
                this.textureView.setSurfaceTextureListener((TextureView.SurfaceTextureListener) null);
            }
            this.textureView = textureView2;
            textureView2.setSurfaceTextureListener(new SurfaceTextureListener((C07651) null));
            textureView2.setOnClickListener(new OnClickListener((C07651) null));
            boolean equals = Objects.equals(this.savedSurfaceTexture, textureView2.getSurfaceTexture());
            LogUtil.m9i("VideoSurfaceTextureImpl.attachToTextureView", GeneratedOutlineSupport.outline10("areSameSurfaces: ", equals), new Object[0]);
            SurfaceTexture surfaceTexture = this.savedSurfaceTexture;
            if (surfaceTexture != null && !equals) {
                textureView2.setSurfaceTexture(surfaceTexture);
                Point point = this.surfaceDimensions;
                if (point != null) {
                    createSurface(point.x, point.y);
                    onSurfaceCreated();
                }
            }
            this.isDoneWithSurface = false;
        }
    }

    public Surface getSavedSurface() {
        return this.savedSurface;
    }

    public Point getSourceVideoDimensions() {
        return this.sourceVideoDimensions;
    }

    public Point getSurfaceDimensions() {
        return this.surfaceDimensions;
    }

    public void setDelegate(VideoSurfaceDelegate videoSurfaceDelegate) {
        LogUtil.m9i("VideoSurfaceTextureImpl.setDelegate", "delegate: " + videoSurfaceDelegate + " " + toString(), new Object[0]);
        this.delegate = videoSurfaceDelegate;
    }

    public void setDoneWithSurface() {
        LogUtil.m9i("VideoSurfaceTextureImpl.setDoneWithSurface", toString(), new Object[0]);
        this.isDoneWithSurface = true;
        TextureView textureView2 = this.textureView;
        if (textureView2 == null || !textureView2.isAvailable()) {
            if (this.savedSurface != null) {
                onSurfaceReleased();
                this.savedSurface.release();
                this.savedSurface = null;
            }
            SurfaceTexture surfaceTexture = this.savedSurfaceTexture;
            if (surfaceTexture != null) {
                surfaceTexture.release();
                this.savedSurfaceTexture = null;
            }
        }
    }

    public void setSourceVideoDimensions(Point point) {
        this.sourceVideoDimensions = point;
    }

    public void setSurfaceDimensions(Point point) {
        SurfaceTexture surfaceTexture;
        LogUtil.m9i("VideoSurfaceTextureImpl.setSurfaceDimensions", "surfaceDimensions: " + point + " " + toString(), new Object[0]);
        this.surfaceDimensions = point;
        if (point != null && (surfaceTexture = this.savedSurfaceTexture) != null) {
            int i = Build.VERSION.SDK_INT;
            surfaceTexture.setDefaultBufferSize(point.x, point.y);
        }
    }

    public String toString() {
        String str;
        Locale locale = Locale.US;
        Object[] objArr = new Object[4];
        objArr[0] = this.surfaceType == 1 ? "local, " : "remote, ";
        String str2 = "";
        objArr[1] = this.savedSurface == null ? "no-surface, " : str2;
        if (this.savedSurfaceTexture == null) {
            str2 = "no-texture, ";
        }
        objArr[2] = str2;
        if (this.surfaceDimensions == null) {
            str = "(-1 x -1)";
        } else {
            str = this.surfaceDimensions.x + " x " + this.surfaceDimensions.y;
        }
        objArr[3] = str;
        return String.format(locale, "VideoSurfaceTextureImpl<%s%s%s%s>", objArr);
    }
}
