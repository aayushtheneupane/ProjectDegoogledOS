package com.android.dialer.app.voicemail;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.app.calllog.CallLogAsyncTaskUtil;
import com.android.dialer.app.calllog.CallLogListItemViewHolder;
import com.android.dialer.app.voicemail.VoicemailPlaybackPresenter;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class VoicemailPlaybackLayout extends LinearLayout implements VoicemailPlaybackPresenter.PlaybackView, CallLogAsyncTaskUtil.CallLogAsyncTaskListener {
    /* access modifiers changed from: private */
    public Context context;
    private ImageButton deleteButton;
    private final View.OnClickListener deleteButtonListener;
    /* access modifiers changed from: private */
    public boolean isPlaying;
    private SeekBar playbackSeek;
    private ImageButton playbackSpeakerphone;
    private TextView positionText;
    private PositionUpdater positionUpdater;
    /* access modifiers changed from: private */
    public VoicemailPlaybackPresenter presenter;
    private final SeekBar.OnSeekBarChangeListener seekBarChangeListener;
    private final View.OnClickListener speakerphoneListener;
    private ImageButton startStopButton;
    private final View.OnClickListener startStopButtonListener;
    private TextView stateText;
    private TextView totalDurationText;
    /* access modifiers changed from: private */
    public CallLogListItemViewHolder viewHolder;
    private Drawable voicemailSeekHandleDisabled;
    private Drawable voicemailSeekHandleEnabled;
    /* access modifiers changed from: private */
    public Uri voicemailUri;

    private final class PositionUpdater implements Runnable {
        /* access modifiers changed from: private */
        public int durationMs;
        private final ScheduledExecutorService executorService;
        /* access modifiers changed from: private */
        public final Object lock = new Object();
        /* access modifiers changed from: private */
        public ScheduledFuture<?> scheduledFuture;
        private Runnable updateClipPositionRunnable = new Runnable() {
            public void run() {
                synchronized (PositionUpdater.this.lock) {
                    if (PositionUpdater.this.scheduledFuture != null) {
                        if (VoicemailPlaybackLayout.this.presenter != null) {
                            int mediaPlayerPosition = VoicemailPlaybackLayout.this.presenter.getMediaPlayerPosition();
                            PositionUpdater positionUpdater = PositionUpdater.this;
                            VoicemailPlaybackLayout.this.setClipPosition(mediaPlayerPosition, positionUpdater.durationMs);
                        }
                    }
                }
            }
        };

        public PositionUpdater(int i, ScheduledExecutorService scheduledExecutorService) {
            this.durationMs = i;
            this.executorService = scheduledExecutorService;
        }

        public void run() {
            VoicemailPlaybackLayout.this.post(this.updateClipPositionRunnable);
        }

        public void startUpdating() {
            synchronized (this.lock) {
                ScheduledFuture<?> scheduledFuture2 = this.scheduledFuture;
                if (scheduledFuture2 != null) {
                    scheduledFuture2.cancel(true);
                    this.scheduledFuture = null;
                }
                VoicemailPlaybackLayout.this.removeCallbacks(this.updateClipPositionRunnable);
                this.scheduledFuture = this.executorService.scheduleAtFixedRate(this, 0, 33, TimeUnit.MILLISECONDS);
            }
        }

        public void stopUpdating() {
            synchronized (this.lock) {
                ScheduledFuture<?> scheduledFuture2 = this.scheduledFuture;
                if (scheduledFuture2 != null) {
                    scheduledFuture2.cancel(true);
                    this.scheduledFuture = null;
                }
                VoicemailPlaybackLayout.this.removeCallbacks(this.updateClipPositionRunnable);
            }
        }
    }

    public VoicemailPlaybackLayout(Context context2) {
        this(context2, (AttributeSet) null);
    }

    private String formatAsMinutesAndSeconds(int i) {
        int i2 = i / 1000;
        int i3 = i2 / 60;
        int i4 = i2 - (i3 * 60);
        if (i3 > 99) {
            i3 = 99;
        }
        return String.format("%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)});
    }

    private String getString(int i) {
        return this.context.getString(i);
    }

    public void disableUiElements() {
        this.startStopButton.setEnabled(false);
        resetSeekBar();
    }

    public void enableUiElements() {
        this.deleteButton.setEnabled(true);
        this.startStopButton.setEnabled(true);
        this.playbackSeek.setEnabled(true);
        this.playbackSeek.setThumb(this.voicemailSeekHandleEnabled);
    }

    public int getDesiredClipPosition() {
        return this.playbackSeek.getProgress();
    }

    public String getStateText() {
        return this.stateText.getText().toString();
    }

    public void onDeleteVoicemail() {
        this.presenter.onVoicemailDeletedInDatabase();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.playbackSeek = (SeekBar) findViewById(R.id.playback_seek);
        this.startStopButton = (ImageButton) findViewById(R.id.playback_start_stop);
        this.playbackSpeakerphone = (ImageButton) findViewById(R.id.playback_speakerphone);
        this.deleteButton = (ImageButton) findViewById(R.id.delete_voicemail);
        this.stateText = (TextView) findViewById(R.id.playback_state_text);
        this.stateText.setAccessibilityLiveRegion(1);
        this.positionText = (TextView) findViewById(R.id.playback_position_text);
        this.totalDurationText = (TextView) findViewById(R.id.total_duration_text);
        this.playbackSeek.setOnSeekBarChangeListener(this.seekBarChangeListener);
        this.startStopButton.setOnClickListener(this.startStopButtonListener);
        this.playbackSpeakerphone.setOnClickListener(this.speakerphoneListener);
        this.deleteButton.setOnClickListener(this.deleteButtonListener);
        this.positionText.setText(formatAsMinutesAndSeconds(0));
        this.totalDurationText.setText(formatAsMinutesAndSeconds(0));
        this.voicemailSeekHandleEnabled = getResources().getDrawable(R.drawable.ic_voicemail_seek_handle, this.context.getTheme());
        this.voicemailSeekHandleDisabled = getResources().getDrawable(R.drawable.old_ic_voicemail_seek_handle_disabled, this.context.getTheme());
    }

    public void onPlaybackError() {
        PositionUpdater positionUpdater2 = this.positionUpdater;
        if (positionUpdater2 != null) {
            positionUpdater2.stopUpdating();
        }
        disableUiElements();
        this.stateText.setText(getString(R.string.voicemail_playback_error));
    }

    public void onPlaybackStarted(int i, ScheduledExecutorService scheduledExecutorService) {
        this.isPlaying = true;
        this.startStopButton.setImageResource(R.drawable.ic_pause);
        PositionUpdater positionUpdater2 = this.positionUpdater;
        if (positionUpdater2 != null) {
            positionUpdater2.stopUpdating();
            this.positionUpdater = null;
        }
        this.positionUpdater = new PositionUpdater(i, scheduledExecutorService);
        this.positionUpdater.startUpdating();
    }

    public void onPlaybackStopped() {
        this.isPlaying = false;
        this.startStopButton.setImageResource(R.drawable.ic_play_arrow);
        PositionUpdater positionUpdater2 = this.positionUpdater;
        if (positionUpdater2 != null) {
            positionUpdater2.stopUpdating();
            this.positionUpdater = null;
        }
    }

    public void onSpeakerphoneOn(boolean z) {
        if (z) {
            this.playbackSpeakerphone.setImageResource(R.drawable.quantum_ic_volume_up_vd_theme_24);
            this.playbackSpeakerphone.setContentDescription(this.context.getString(R.string.voicemail_speaker_off));
            return;
        }
        this.playbackSpeakerphone.setImageResource(R.drawable.quantum_ic_volume_down_white_24);
        this.playbackSpeakerphone.setContentDescription(this.context.getString(R.string.voicemail_speaker_on));
    }

    public void resetSeekBar() {
        this.playbackSeek.setProgress(0);
        this.playbackSeek.setEnabled(false);
        this.playbackSeek.setThumb(this.voicemailSeekHandleDisabled);
    }

    public void setClipPosition(int i, int i2) {
        int max = Math.max(0, i);
        int max2 = Math.max(max, i2);
        if (this.playbackSeek.getMax() != max2) {
            this.playbackSeek.setMax(max2);
        }
        this.playbackSeek.setProgress(max);
        this.positionText.setText(formatAsMinutesAndSeconds(max));
        this.totalDurationText.setText(formatAsMinutesAndSeconds(i2));
    }

    public void setFetchContentTimeout() {
        this.startStopButton.setEnabled(true);
        this.stateText.setText(getString(R.string.voicemail_fetching_timout));
    }

    public void setIsFetchingContent() {
        disableUiElements();
        this.stateText.setText(getString(R.string.voicemail_fetching_content));
    }

    public void setPresenter(VoicemailPlaybackPresenter voicemailPlaybackPresenter, Uri uri) {
        this.presenter = voicemailPlaybackPresenter;
        this.voicemailUri = uri;
    }

    public void setSuccess() {
        this.stateText.setText((CharSequence) null);
    }

    public void setViewHolder(CallLogListItemViewHolder callLogListItemViewHolder) {
        this.viewHolder = callLogListItemViewHolder;
    }

    public VoicemailPlaybackLayout(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.speakerphoneListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (VoicemailPlaybackLayout.this.presenter != null) {
                    VoicemailPlaybackLayout.this.presenter.toggleSpeakerphone();
                }
            }
        };
        this.deleteButtonListener = new View.OnClickListener() {
            public void onClick(View view) {
                ((LoggingBindingsStub) Logger.get(VoicemailPlaybackLayout.this.context)).logImpression(DialerImpression$Type.VOICEMAIL_DELETE_ENTRY);
                if (VoicemailPlaybackLayout.this.presenter != null) {
                    final int adapterPosition = VoicemailPlaybackLayout.this.viewHolder.getAdapterPosition();
                    VoicemailPlaybackLayout.this.presenter.pausePlayback();
                    VoicemailPlaybackLayout.this.presenter.onVoicemailDeleted(VoicemailPlaybackLayout.this.viewHolder);
                    final Uri access$300 = VoicemailPlaybackLayout.this.voicemailUri;
                    final C03471 r1 = new Runnable() {
                        public void run() {
                            if (Objects.equals(access$300, VoicemailPlaybackLayout.this.voicemailUri)) {
                                CallLogAsyncTaskUtil.deleteVoicemail(VoicemailPlaybackLayout.this.context, access$300, VoicemailPlaybackLayout.this);
                            }
                        }
                    };
                    final Handler handler = new Handler();
                    handler.postDelayed(r1, 3050);
                    Snackbar make = Snackbar.make((View) VoicemailPlaybackLayout.this, (int) R.string.snackbar_voicemail_deleted, 0);
                    make.setDuration(3000);
                    Snackbar snackbar = make;
                    snackbar.setAction((int) R.string.snackbar_undo, (View.OnClickListener) new View.OnClickListener() {
                        public void onClick(View view) {
                            VoicemailPlaybackLayout.this.presenter.onVoicemailDeleteUndo(adapterPosition);
                            handler.removeCallbacks(r1);
                        }
                    });
                    snackbar.setActionTextColor(VoicemailPlaybackLayout.this.context.getResources().getColor(R.color.dialer_snackbar_action_text_color));
                    snackbar.show();
                }
            }
        };
        this.isPlaying = false;
        this.startStopButtonListener = new View.OnClickListener() {
            public void onClick(View view) {
                if (VoicemailPlaybackLayout.this.presenter != null) {
                    if (VoicemailPlaybackLayout.this.isPlaying) {
                        VoicemailPlaybackLayout.this.presenter.pausePlayback();
                        return;
                    }
                    ((LoggingBindingsStub) Logger.get(VoicemailPlaybackLayout.this.context)).logImpression(DialerImpression$Type.VOICEMAIL_PLAY_AUDIO_AFTER_EXPANDING_ENTRY);
                    VoicemailPlaybackLayout.this.presenter.resumePlayback();
                }
            }
        };
        this.seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                VoicemailPlaybackLayout.this.setClipPosition(i, seekBar.getMax());
                if (z) {
                    VoicemailPlaybackLayout.this.presenter.seek(i);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                if (VoicemailPlaybackLayout.this.presenter != null) {
                    VoicemailPlaybackLayout.this.presenter.pausePlaybackForSeeking();
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (VoicemailPlaybackLayout.this.presenter != null) {
                    VoicemailPlaybackLayout.this.presenter.resumePlaybackAfterSeeking(seekBar.getProgress());
                }
            }
        };
        this.context = context2;
        ((LayoutInflater) context2.getSystemService("layout_inflater")).inflate(R.layout.voicemail_playback_layout, this);
    }
}
