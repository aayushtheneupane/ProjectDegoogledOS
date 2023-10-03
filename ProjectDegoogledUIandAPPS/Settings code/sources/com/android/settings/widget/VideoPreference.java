package com.android.settings.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.R$styleable;
import com.havoc.config.center.C1715R;

public class VideoPreference extends Preference {
    boolean mAnimationAvailable;
    private int mAnimationId;
    private float mAspectRatio = 1.0f;
    private final Context mContext;
    private int mHeight = -2;
    MediaPlayer mMediaPlayer;
    private int mPreviewResource;
    /* access modifiers changed from: private */
    public Surface mSurface;
    private Uri mVideoPath;
    /* access modifiers changed from: private */
    public boolean mVideoPaused;
    boolean mVideoReady;
    /* access modifiers changed from: private */
    public boolean mViewVisible;

    public VideoPreference(Context context) {
        super(context);
        this.mContext = context;
        initialize(context, (AttributeSet) null);
    }

    public VideoPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        int i;
        int i2;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.VideoPreference, 0, 0);
        try {
            this.mAnimationAvailable = false;
            if (this.mAnimationId == 0) {
                i = obtainStyledAttributes.getResourceId(0, 0);
            } else {
                i = this.mAnimationId;
            }
            this.mAnimationId = i;
            this.mVideoPath = new Uri.Builder().scheme("android.resource").authority(context.getPackageName()).appendPath(String.valueOf(this.mAnimationId)).build();
            if (this.mPreviewResource == 0) {
                i2 = obtainStyledAttributes.getResourceId(1, 0);
            } else {
                i2 = this.mPreviewResource;
            }
            this.mPreviewResource = i2;
            if (this.mPreviewResource == 0 && this.mAnimationId == 0) {
                setVisible(false);
                obtainStyledAttributes.recycle();
                return;
            }
            initMediaPlayer();
            if (this.mMediaPlayer == null || this.mMediaPlayer.getDuration() <= 0) {
                setVisible(false);
            } else {
                setVisible(true);
                setLayoutResource(C1715R.layout.video_preference);
                this.mAnimationAvailable = true;
                updateAspectRatio();
            }
            obtainStyledAttributes.recycle();
        } catch (Exception unused) {
            Log.w("VideoPreference", "Animation resource not found. Will not show animation.");
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mAnimationAvailable) {
            TextureView textureView = (TextureView) preferenceViewHolder.findViewById(C1715R.C1718id.video_texture_view);
            final ImageView imageView = (ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.video_preview_image);
            final ImageView imageView2 = (ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.video_play_button);
            AspectRatioFrameLayout aspectRatioFrameLayout = (AspectRatioFrameLayout) preferenceViewHolder.findViewById(C1715R.C1718id.video_container);
            imageView.setImageResource(this.mPreviewResource);
            aspectRatioFrameLayout.setAspectRatio(this.mAspectRatio);
            int i = this.mHeight;
            if (i >= -1) {
                aspectRatioFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
            }
            updateViewStates(imageView, imageView2);
            textureView.setOnClickListener(new View.OnClickListener(imageView, imageView2) {
                private final /* synthetic */ ImageView f$1;
                private final /* synthetic */ ImageView f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    VideoPreference.this.lambda$onBindViewHolder$0$VideoPreference(this.f$1, this.f$2, view);
                }
            });
            textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                }

                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                    VideoPreference videoPreference = VideoPreference.this;
                    if (videoPreference.mMediaPlayer != null) {
                        Surface unused = videoPreference.mSurface = new Surface(surfaceTexture);
                        VideoPreference videoPreference2 = VideoPreference.this;
                        videoPreference2.mMediaPlayer.setSurface(videoPreference2.mSurface);
                    }
                }

                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    imageView.setVisibility(0);
                    return false;
                }

                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                    MediaPlayer mediaPlayer;
                    if (VideoPreference.this.mViewVisible) {
                        if (VideoPreference.this.mVideoReady) {
                            if (imageView.getVisibility() == 0) {
                                imageView.setVisibility(8);
                            }
                            if (!VideoPreference.this.mVideoPaused && (mediaPlayer = VideoPreference.this.mMediaPlayer) != null && !mediaPlayer.isPlaying()) {
                                VideoPreference.this.mMediaPlayer.start();
                                imageView2.setVisibility(8);
                            }
                        }
                        MediaPlayer mediaPlayer2 = VideoPreference.this.mMediaPlayer;
                        if (mediaPlayer2 != null && !mediaPlayer2.isPlaying() && imageView2.getVisibility() != 0) {
                            imageView2.setVisibility(0);
                        }
                    }
                }
            });
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$VideoPreference(ImageView imageView, ImageView imageView2, View view) {
        updateViewStates(imageView, imageView2);
    }

    /* access modifiers changed from: package-private */
    public void updateViewStates(ImageView imageView, ImageView imageView2) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null) {
            return;
        }
        if (mediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            imageView2.setVisibility(0);
            imageView.setVisibility(0);
            this.mVideoPaused = true;
            return;
        }
        imageView.setVisibility(8);
        imageView2.setVisibility(8);
        this.mMediaPlayer.start();
        this.mVideoPaused = false;
    }

    public void onDetached() {
        releaseMediaPlayer();
        super.onDetached();
    }

    public void onViewVisible(boolean z) {
        this.mViewVisible = true;
        this.mVideoPaused = z;
        initMediaPlayer();
    }

    public void onViewInvisible() {
        this.mViewVisible = false;
        releaseMediaPlayer();
    }

    public void setVideo(int i, int i2) {
        this.mAnimationId = i;
        this.mPreviewResource = i2;
        releaseMediaPlayer();
        initialize(this.mContext, (AttributeSet) null);
    }

    private void initMediaPlayer() {
        if (this.mMediaPlayer == null) {
            this.mMediaPlayer = MediaPlayer.create(this.mContext, this.mVideoPath);
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0);
                this.mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    public final void onSeekComplete(MediaPlayer mediaPlayer) {
                        VideoPreference.this.lambda$initMediaPlayer$1$VideoPreference(mediaPlayer);
                    }
                });
                this.mMediaPlayer.setOnPreparedListener($$Lambda$VideoPreference$aFxutwOOqnKuOzRYe5J9dLOLMfs.INSTANCE);
                Surface surface = this.mSurface;
                if (surface != null) {
                    this.mMediaPlayer.setSurface(surface);
                }
            }
        }
    }

    public /* synthetic */ void lambda$initMediaPlayer$1$VideoPreference(MediaPlayer mediaPlayer) {
        this.mVideoReady = true;
    }

    private void releaseMediaPlayer() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mVideoReady = false;
        }
    }

    public boolean isAnimationAvailable() {
        return this.mAnimationAvailable;
    }

    public boolean isVideoPaused() {
        return this.mVideoPaused;
    }

    public void setHeight(float f) {
        this.mHeight = (int) TypedValue.applyDimension(1, f, this.mContext.getResources().getDisplayMetrics());
    }

    /* access modifiers changed from: package-private */
    public void updateAspectRatio() {
        this.mAspectRatio = ((float) this.mMediaPlayer.getVideoWidth()) / ((float) this.mMediaPlayer.getVideoHeight());
    }
}
