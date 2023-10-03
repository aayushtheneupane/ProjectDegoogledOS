package com.android.dialer.voicemail.listui;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;

public class NewVoicemailMediaPlayer {
    private final MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener newVoicemailMediaPlayerOnCompletionListener;
    private MediaPlayer.OnErrorListener newVoicemailMediaPlayerOnErrorListener;
    private MediaPlayer.OnPreparedListener newVoicemailMediaPlayerOnPreparedListener;
    private Uri pausedUri;
    private Uri voicemailLastPlayedOrPlayingUri;
    private Uri voicemailRequestedToDownload;
    private Uri voicemailUriLastPreparedOrPreparingToPlay;

    public NewVoicemailMediaPlayer(MediaPlayer mediaPlayer2) {
        Assert.isNotNull(mediaPlayer2);
        this.mediaPlayer = mediaPlayer2;
    }

    private void verifyListenersNotNull() {
        Assert.isNotNull(this.newVoicemailMediaPlayerOnErrorListener, "newVoicemailMediaPlayerOnErrorListener must be set before preparing to play voicemails", new Object[0]);
        Assert.isNotNull(this.newVoicemailMediaPlayerOnCompletionListener, "newVoicemailMediaPlayerOnCompletionListener must be set before preparing to play voicemails", new Object[0]);
        Assert.isNotNull(this.newVoicemailMediaPlayerOnPreparedListener, "newVoicemailMediaPlayerOnPreparedListener must be set before preparing to play voicemails", new Object[0]);
    }

    public int getCurrentPosition() {
        return this.mediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        Assert.checkArgument(this.mediaPlayer != null);
        return this.mediaPlayer.getDuration();
    }

    public Uri getLastPausedVoicemailUri() {
        return this.pausedUri;
    }

    public Uri getLastPlayedOrPlayingVoicemailUri() {
        if (this.mediaPlayer.isPlaying()) {
            Assert.isNotNull(this.voicemailLastPlayedOrPlayingUri);
        }
        Uri uri = this.voicemailLastPlayedOrPlayingUri;
        return uri == null ? Uri.EMPTY : uri;
    }

    public Uri getLastPreparedOrPreparingToPlayVoicemailUri() {
        Uri uri = this.voicemailUriLastPreparedOrPreparingToPlay;
        Assert.isNotNull(uri, "we expect whoever called this to have prepared a voicemail before calling this function", new Object[0]);
        return uri;
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public Uri getVoicemailRequestedToDownload() {
        return this.voicemailRequestedToDownload;
    }

    public boolean isPaused() {
        return this.pausedUri != null;
    }

    public boolean isPlaying() {
        return this.mediaPlayer.isPlaying();
    }

    /* renamed from: lambda$prepareMediaPlayerAndPlayVoicemailWhenReady$0$NewVoicemailMediaPlayer */
    public /* synthetic */ void mo7462x86b3b54(Context context, Uri uri) {
        try {
            this.mediaPlayer.setDataSource(context, uri);
            AudioManager audioManager = (AudioManager) context.getSystemService(AudioManager.class);
            audioManager.setMode(3);
            audioManager.setSpeakerphoneOn(false);
        } catch (IOException e) {
            LogUtil.m9i("NewVoicemailMediaPlayer", "threw an Exception when setting datasource " + e + " for uri: " + uri + "for context : " + context, new Object[0]);
        }
    }

    /* renamed from: lambda$prepareMediaPlayerAndPlayVoicemailWhenReady$1$NewVoicemailMediaPlayer */
    public /* synthetic */ void mo7463x8c9b8855() {
        this.mediaPlayer.prepareAsync();
    }

    public void pauseMediaPlayer(Uri uri) {
        this.pausedUri = uri;
        Assert.checkArgument(this.voicemailUriLastPreparedOrPreparingToPlay.equals(this.voicemailLastPlayedOrPlayingUri), "last prepared and last playing should be the same", new Object[0]);
        Assert.checkArgument(this.pausedUri.equals(this.voicemailLastPlayedOrPlayingUri), "only the last played uri can be paused", new Object[0]);
        this.mediaPlayer.pause();
    }

    public void prepareMediaPlayerAndPlayVoicemailWhenReady(Context context, Uri uri) throws IOException {
        Assert.checkArgument(uri != null, "Media player cannot play a null uri", new Object[0]);
        LogUtil.m9i("NewVoicemailMediaPlayer", "trying to prepare playing voicemail uri: %s", String.valueOf(uri));
        try {
            reset();
            this.voicemailUriLastPreparedOrPreparingToPlay = uri;
            verifyListenersNotNull();
            LogUtil.m9i("NewVoicemailMediaPlayer", "setData source", new Object[0]);
            StrictModeUtils.bypass((Runnable) new Runnable(context, uri) {
                private final /* synthetic */ Context f$1;
                private final /* synthetic */ Uri f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    NewVoicemailMediaPlayer.this.mo7462x86b3b54(this.f$1, this.f$2);
                }
            });
            LogUtil.m9i("NewVoicemailMediaPlayer", "prepare async", new Object[0]);
            StrictModeUtils.bypass((Runnable) new Runnable() {
                public final void run() {
                    NewVoicemailMediaPlayer.this.mo7463x8c9b8855();
                }
            });
        } catch (IllegalStateException e) {
            LogUtil.m9i("NewVoicemailMediaPlayer", GeneratedOutlineSupport.outline6("caught an IllegalStateException state exception : \n", e), new Object[0]);
        } catch (Exception e2) {
            LogUtil.m9i("NewVoicemailMediaPlayer", "threw an Exception " + e2 + " for uri: " + uri + "for context : " + context, new Object[0]);
        }
    }

    public void reset() {
        LogUtil.enterBlock("NewVoicemailMediaPlayer.reset");
        this.mediaPlayer.reset();
        this.voicemailLastPlayedOrPlayingUri = null;
        this.voicemailUriLastPreparedOrPreparingToPlay = null;
        this.pausedUri = null;
        this.voicemailRequestedToDownload = null;
    }

    public void seekTo(int i) {
        this.mediaPlayer.seekTo(i);
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        this.mediaPlayer.setOnCompletionListener(onCompletionListener);
        this.newVoicemailMediaPlayerOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.mediaPlayer.setOnErrorListener(onErrorListener);
        this.newVoicemailMediaPlayerOnErrorListener = onErrorListener;
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.mediaPlayer.setOnPreparedListener(onPreparedListener);
        this.newVoicemailMediaPlayerOnPreparedListener = onPreparedListener;
    }

    public void setVoicemailRequestedToDownload(Uri uri) {
        Assert.isNotNull(uri, "cannot download a null voicemail", new Object[0]);
        this.voicemailRequestedToDownload = uri;
    }

    public void start(Uri uri) {
        Assert.checkArgument(uri.equals(this.voicemailUriLastPreparedOrPreparingToPlay), "uri:%s was not prepared before calling start. Uri that is currently prepared: %s", uri, getLastPreparedOrPreparingToPlayVoicemailUri());
        this.mediaPlayer.start();
        this.voicemailLastPlayedOrPlayingUri = uri;
        this.pausedUri = null;
        this.voicemailRequestedToDownload = null;
    }
}
