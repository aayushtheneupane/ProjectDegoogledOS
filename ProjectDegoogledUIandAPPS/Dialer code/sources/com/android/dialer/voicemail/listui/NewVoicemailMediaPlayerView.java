package com.android.dialer.voicemail.listui;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.support.p000v4.util.Pair;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.precall.PreCall;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.voicemail.listui.NewVoicemailViewHolder;
import com.android.dialer.voicemail.model.VoicemailEntry;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Locale;

public final class NewVoicemailMediaPlayerView extends LinearLayout {
    /* access modifiers changed from: private */
    public TextView currentSeekBarPosition;
    private ImageButton deleteButton;
    private final View.OnClickListener deleteButtonListener = new View.OnClickListener() {
        public void onClick(View view) {
            LogUtil.m9i("NewVoicemailMediaPlayer.deleteButtonListener", "delete voicemailUri %s", String.valueOf(NewVoicemailMediaPlayerView.this.voicemailUri));
            ((NewVoicemailAdapter) NewVoicemailMediaPlayerView.this.newVoicemailViewHolderListener).deleteViewHolder(NewVoicemailMediaPlayerView.this.getContext(), NewVoicemailMediaPlayerView.this.fragmentManager, NewVoicemailMediaPlayerView.this.newVoicemailViewHolder, NewVoicemailMediaPlayerView.this.voicemailUri);
        }
    };
    /* access modifiers changed from: private */
    public FragmentManager fragmentManager;
    /* access modifiers changed from: private */
    public NewVoicemailMediaPlayer mediaPlayer;
    /* access modifiers changed from: private */
    public NewVoicemailViewHolder newVoicemailViewHolder;
    /* access modifiers changed from: private */
    public NewVoicemailViewHolder.NewVoicemailViewHolderListener newVoicemailViewHolderListener;
    /* access modifiers changed from: private */
    public String numberVoicemailFrom;
    private ImageButton pauseButton;
    private final View.OnClickListener pauseButtonListener = new View.OnClickListener() {
        public void onClick(View view) {
            LogUtil.m9i("NewVoicemailMediaPlayer.pauseButtonListener", "pauseMediaPlayerAndSetPausedStateOfViewHolder button for voicemailUri: %s", NewVoicemailMediaPlayerView.this.voicemailUri.toString());
            Assert.checkArgument(NewVoicemailMediaPlayerView.this.playButton.getVisibility() == 8);
            Assert.checkArgument(NewVoicemailMediaPlayerView.this.mediaPlayer != null);
            Assert.checkArgument(NewVoicemailMediaPlayerView.this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(NewVoicemailMediaPlayerView.this.voicemailUri), "the voicemail being played is the only voicemail that should be paused. last played voicemail:%s, uri:%s", NewVoicemailMediaPlayerView.this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().toString(), NewVoicemailMediaPlayerView.this.voicemailUri.toString());
            Assert.checkArgument(NewVoicemailMediaPlayerView.this.newVoicemailViewHolder.getViewHolderVoicemailUri().equals(NewVoicemailMediaPlayerView.this.voicemailUri), "viewholder uri and mediaplayer view should be the same.", new Object[0]);
            ((NewVoicemailAdapter) NewVoicemailMediaPlayerView.this.newVoicemailViewHolderListener).pauseViewHolder(NewVoicemailMediaPlayerView.this.newVoicemailViewHolder);
        }
    };
    /* access modifiers changed from: private */
    public String phoneAccountComponentName;
    /* access modifiers changed from: private */
    public String phoneAccountId;
    private ImageButton phoneButton;
    private final View.OnClickListener phoneButtonListener = new View.OnClickListener() {
        public void onClick(View view) {
            LogUtil.m9i("NewVoicemailMediaPlayer.phoneButtonListener", "phone request for voicemailUri: %s with number:%s", NewVoicemailMediaPlayerView.this.voicemailUri.toString(), NewVoicemailMediaPlayerView.this.numberVoicemailFrom);
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("number cannot be empty:");
            outline13.append(NewVoicemailMediaPlayerView.this.numberVoicemailFrom);
            Assert.checkArgument(!TextUtils.isEmpty(NewVoicemailMediaPlayerView.this.numberVoicemailFrom), outline13.toString(), new Object[0]);
            PreCall.start(NewVoicemailMediaPlayerView.this.getContext(), new CallIntentBuilder(NewVoicemailMediaPlayerView.this.numberVoicemailFrom, CallInitiationType$Type.VOICEMAIL_LOG).setPhoneAccountHandle(TelecomUtil.composePhoneAccountHandle(NewVoicemailMediaPlayerView.this.phoneAccountComponentName, NewVoicemailMediaPlayerView.this.phoneAccountId)));
        }
    };
    /* access modifiers changed from: private */
    public ImageButton playButton;
    private final View.OnClickListener playButtonListener = new View.OnClickListener() {
        public void onClick(View view) {
            LogUtil.m9i("NewVoicemailMediaPlayer.playButtonListener", "play button for voicemailUri: %s", String.valueOf(NewVoicemailMediaPlayerView.this.voicemailUri));
            if (NewVoicemailMediaPlayerView.this.mediaPlayer.getLastPausedVoicemailUri() == null || !NewVoicemailMediaPlayerView.this.mediaPlayer.getLastPausedVoicemailUri().toString().contentEquals(NewVoicemailMediaPlayerView.this.voicemailUri.toString())) {
                NewVoicemailMediaPlayerView.this.playVoicemailWhenAvailableLocally();
                return;
            }
            LogUtil.m9i("NewVoicemailMediaPlayer.playButtonListener", "resume playing voicemailUri: %s", NewVoicemailMediaPlayerView.this.voicemailUri.toString());
            ((NewVoicemailAdapter) NewVoicemailMediaPlayerView.this.newVoicemailViewHolderListener).resumePausedViewHolder(NewVoicemailMediaPlayerView.this.newVoicemailViewHolder);
        }
    };
    private SeekBar seekBarView;
    private final SeekBar.OnSeekBarChangeListener seekbarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (i < 100) {
                LogUtil.m9i("NewVoicemailMediaPlayer.seekbarChangeListener", "onProgressChanged, progress:%d, seekbarMax: %d, fromUser:%b", Integer.valueOf(i), Integer.valueOf(seekBar.getMax()), Boolean.valueOf(z));
            }
            if (z) {
                NewVoicemailMediaPlayerView.this.mediaPlayer.seekTo(i);
                NewVoicemailMediaPlayerView.this.currentSeekBarPosition.setText(NewVoicemailMediaPlayerView.this.formatAsMinutesAndSeconds(i));
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            LogUtil.m9i("NewVoicemailMediaPlayer.onStartTrackingTouch", "does nothing for now", new Object[0]);
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            LogUtil.m9i("NewVoicemailMediaPlayer.onStopTrackingTouch", "does nothing for now", new Object[0]);
        }
    };
    private ImageButton speakerButton;
    private final View.OnClickListener speakerButtonListener = new View.OnClickListener() {
        public void onClick(View view) {
            LogUtil.m9i("NewVoicemailMediaPlayer.speakerButtonListener", "speaker request for voicemailUri: %s", NewVoicemailMediaPlayerView.this.voicemailUri.toString());
            AudioManager audioManager = (AudioManager) NewVoicemailMediaPlayerView.this.getContext().getSystemService(AudioManager.class);
            audioManager.setMode(3);
            if (audioManager.isSpeakerphoneOn()) {
                LogUtil.m9i("NewVoicemailMediaPlayer.speakerButtonListener", "speaker was on, turning it off", new Object[0]);
                audioManager.setSpeakerphoneOn(false);
                return;
            }
            LogUtil.m9i("NewVoicemailMediaPlayer.speakerButtonListener", "speaker was off, turning it on", new Object[0]);
            audioManager.setSpeakerphoneOn(true);
        }
    };
    private TextView totalDurationView;
    private TextView voicemailLoadingStatusView;
    private Drawable voicemailSeekHandleDisabled;
    /* access modifiers changed from: private */
    public Uri voicemailUri;

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    public NewVoicemailMediaPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LogUtil.enterBlock("NewVoicemailMediaPlayer");
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.new_voicemail_media_player_layout, this);
    }

    /* access modifiers changed from: private */
    public String formatAsMinutesAndSeconds(int i) {
        int i2 = i / 1000;
        int i3 = i2 / 60;
        int i4 = i2 - (i3 * 60);
        if (i3 > 99) {
            i3 = 99;
        }
        return String.format(Locale.US, "%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)});
    }

    private void initializeMediaPlayerButtonsAndViews() {
        this.playButton = (ImageButton) findViewById(R.id.playButton);
        this.pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        this.currentSeekBarPosition = (TextView) findViewById(R.id.playback_position_text);
        this.seekBarView = (SeekBar) findViewById(R.id.playback_seek);
        this.speakerButton = (ImageButton) findViewById(R.id.speakerButton);
        this.phoneButton = (ImageButton) findViewById(R.id.phoneButton);
        this.deleteButton = (ImageButton) findViewById(R.id.deleteButton);
        this.totalDurationView = (TextView) findViewById(R.id.playback_seek_total_duration);
        this.voicemailLoadingStatusView = (TextView) findViewById(R.id.playback_state_text);
        this.voicemailSeekHandleDisabled = getContext().getResources().getDrawable(R.drawable.ic_voicemail_seek_handle_disabled, getContext().getTheme());
    }

    /* access modifiers changed from: private */
    public void playVoicemailWhenAvailableLocally() {
        LogUtil.enterBlock("playVoicemailWhenAvailableLocally");
        C0578x44a52d3b r0 = new DialerExecutor.Worker() {
            public final Object doInBackground(Object obj) {
                return NewVoicemailMediaPlayerView.this.queryVoicemailHasContent((Pair) obj);
            }
        };
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getContext()).dialerExecutorFactory()).createUiTaskBuilder(this.fragmentManager, "lookup_voicemail_content", r0).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                NewVoicemailMediaPlayerView.this.prepareMediaPlayer((Pair) obj);
            }
        }).build().executeSerial(new Pair(getContext(), this.voicemailUri));
    }

    /* access modifiers changed from: private */
    public void prepareMediaPlayer(Pair<Boolean, Uri> pair) {
        boolean booleanValue = ((Boolean) pair.first).booleanValue();
        Uri uri = (Uri) pair.second;
        boolean z = true;
        LogUtil.m9i("NewVoicemailMediaPlayer.prepareMediaPlayer", "voicemail available locally: %b for voicemailUri: %s", Boolean.valueOf(booleanValue), uri.toString());
        if (booleanValue) {
            try {
                if (this.mediaPlayer == null) {
                    z = false;
                }
                Assert.checkArgument(z, "media player should not have been null", new Object[0]);
                this.mediaPlayer.prepareMediaPlayerAndPlayVoicemailWhenReady(getContext(), uri);
            } catch (Exception e) {
                LogUtil.m8e("NewVoicemailMediaPlayer.prepareMediaPlayer", "Exception when mediaPlayer.prepareMediaPlayerAndPlayVoicemailWhenReady(getContext(), uri)\n" + e + "\n uri:" + uri + "context should not be null, its value is :" + getContext(), new Object[0]);
            }
        } else {
            LogUtil.m9i("NewVoicemailMediaPlayer.prepareVoicemailForMediaPlayer", "need to download content", new Object[0]);
            this.mediaPlayer.setVoicemailRequestedToDownload(uri);
            this.voicemailLoadingStatusView.setVisibility(0);
            LogUtil.m9i("NewVoicemailMediaPlayer.sendIntentToDownloadVoicemail", "uri:%s", uri.toString());
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getContext()).dialerExecutorFactory()).createUiTaskBuilder(this.fragmentManager, "lookup_voicemail_pkg", new DialerExecutor.Worker() {
                public final Object doInBackground(Object obj) {
                    return NewVoicemailMediaPlayerView.this.queryVoicemailSourcePackage((Pair) obj);
                }
            }).onSuccess(new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    NewVoicemailMediaPlayerView.this.sendIntent((Pair) obj);
                }
            }).build().executeSerial(new Pair(getContext(), this.voicemailUri));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        if (r6 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
        $closeResource(r7, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.p000v4.util.Pair<java.lang.Boolean, android.net.Uri> queryVoicemailHasContent(android.support.p000v4.util.Pair<android.content.Context, android.net.Uri> r7) {
        /*
            r6 = this;
            F r6 = r7.first
            android.content.Context r6 = (android.content.Context) r6
            S r7 = r7.second
            android.net.Uri r7 = (android.net.Uri) r7
            android.content.ContentResolver r0 = r6.getContentResolver()
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r1 = r7
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)
            r0 = 0
            r1 = 0
            if (r6 == 0) goto L_0x003a
            boolean r2 = r6.moveToFirst()     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x003a
            android.support.v4.util.Pair r2 = new android.support.v4.util.Pair     // Catch:{ all -> 0x0049 }
            java.lang.String r3 = "has_content"
            int r3 = r6.getColumnIndex(r3)     // Catch:{ all -> 0x0049 }
            int r3 = r6.getInt(r3)     // Catch:{ all -> 0x0049 }
            r4 = 1
            if (r3 != r4) goto L_0x002f
            r0 = r4
        L_0x002f:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0049 }
            r2.<init>(r0, r7)     // Catch:{ all -> 0x0049 }
            $closeResource(r1, r6)
            return r2
        L_0x003a:
            android.support.v4.util.Pair r2 = new android.support.v4.util.Pair     // Catch:{ all -> 0x0049 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0049 }
            r2.<init>(r0, r7)     // Catch:{ all -> 0x0049 }
            if (r6 == 0) goto L_0x0048
            $closeResource(r1, r6)
        L_0x0048:
            return r2
        L_0x0049:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x004b }
        L_0x004b:
            r0 = move-exception
            if (r6 == 0) goto L_0x0051
            $closeResource(r7, r6)
        L_0x0051:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.voicemail.listui.NewVoicemailMediaPlayerView.queryVoicemailHasContent(android.support.v4.util.Pair):android.support.v4.util.Pair");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006c, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006d, code lost:
        if (r0 != null) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006f, code lost:
        $closeResource(r9, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0072, code lost:
        throw r10;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041 A[Catch:{ all -> 0x006c }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.p000v4.util.Pair<java.lang.String, android.net.Uri> queryVoicemailSourcePackage(android.support.p000v4.util.Pair<android.content.Context, android.net.Uri> r10) {
        /*
            r9 = this;
            java.lang.String r9 = "NewVoicemailMediaPlayer.queryVoicemailSourcePackage"
            com.android.dialer.common.LogUtil.enterBlock(r9)
            F r0 = r10.first
            android.content.Context r0 = (android.content.Context) r0
            S r10 = r10.second
            android.net.Uri r10 = (android.net.Uri) r10
            android.content.ContentResolver r1 = r0.getContentResolver()
            java.lang.String r0 = "source_package"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r10
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x002b
            boolean r3 = r0.moveToFirst()     // Catch:{ all -> 0x006a }
            if (r3 == 0) goto L_0x002b
            r3 = r1
            goto L_0x002c
        L_0x002b:
            r3 = r2
        L_0x002c:
            r4 = 2
            java.lang.String r5 = "uri: %s has a SOURCE_PACKAGE: %s"
            r6 = 0
            if (r3 != 0) goto L_0x0041
            java.lang.String r3 = "uri: %s does not return a SOURCE_PACKAGE"
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ all -> 0x006a }
            java.lang.String r8 = r10.toString()     // Catch:{ all -> 0x006a }
            r7[r2] = r8     // Catch:{ all -> 0x006a }
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r9, (java.lang.String) r3, (java.lang.Object[]) r7)     // Catch:{ all -> 0x006a }
            r3 = r6
            goto L_0x0052
        L_0x0041:
            java.lang.String r3 = r0.getString(r2)     // Catch:{ all -> 0x006a }
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch:{ all -> 0x006a }
            java.lang.String r8 = r10.toString()     // Catch:{ all -> 0x006a }
            r7[r2] = r8     // Catch:{ all -> 0x006a }
            r7[r1] = r3     // Catch:{ all -> 0x006a }
            com.android.dialer.common.LogUtil.m9i(r9, r5, r7)     // Catch:{ all -> 0x006a }
        L_0x0052:
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x006a }
            java.lang.String r7 = r10.toString()     // Catch:{ all -> 0x006a }
            r4[r2] = r7     // Catch:{ all -> 0x006a }
            r4[r1] = r3     // Catch:{ all -> 0x006a }
            com.android.dialer.common.LogUtil.m9i(r9, r5, r4)     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x0064
            $closeResource(r6, r0)
        L_0x0064:
            android.support.v4.util.Pair r9 = new android.support.v4.util.Pair
            r9.<init>(r3, r10)
            return r9
        L_0x006a:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x006c }
        L_0x006c:
            r10 = move-exception
            if (r0 == 0) goto L_0x0072
            $closeResource(r9, r0)
        L_0x0072:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.voicemail.listui.NewVoicemailMediaPlayerView.queryVoicemailSourcePackage(android.support.v4.util.Pair):android.support.v4.util.Pair");
    }

    /* access modifiers changed from: private */
    public void sendIntent(Pair<String, Uri> pair) {
        String str = (String) pair.first;
        Uri uri = (Uri) pair.second;
        LogUtil.m9i("NewVoicemailMediaPlayer.sendIntent", "srcPkg:%s, uri:%s", str, String.valueOf(uri));
        Intent intent = new Intent("android.intent.action.FETCH_VOICEMAIL", uri);
        intent.setPackage(str);
        this.voicemailLoadingStatusView.setVisibility(0);
        getContext().sendBroadcast(intent);
    }

    private void setupListenersForMediaPlayerButtons() {
        this.playButton.setOnClickListener(this.playButtonListener);
        this.pauseButton.setOnClickListener(this.pauseButtonListener);
        this.seekBarView.setOnSeekBarChangeListener(this.seekbarChangeListener);
        this.speakerButton.setOnClickListener(this.speakerButtonListener);
        this.phoneButton.setOnClickListener(this.phoneButtonListener);
        this.deleteButton.setOnClickListener(this.deleteButtonListener);
    }

    /* access modifiers changed from: package-private */
    public void bindValuesFromAdapterOfExpandedViewHolderMediaPlayerView(NewVoicemailViewHolder newVoicemailViewHolder2, VoicemailEntry voicemailEntry, FragmentManager fragmentManager2, NewVoicemailMediaPlayer newVoicemailMediaPlayer, NewVoicemailViewHolder.NewVoicemailViewHolderListener newVoicemailViewHolderListener2) {
        Assert.isNotNull(voicemailEntry);
        Uri parse = Uri.parse(voicemailEntry.getVoicemailUri());
        this.numberVoicemailFrom = voicemailEntry.getNumber().getNormalizedNumber();
        this.phoneAccountId = voicemailEntry.getPhoneAccountId();
        this.phoneAccountComponentName = voicemailEntry.getPhoneAccountComponentName();
        Assert.isNotNull(newVoicemailViewHolder2);
        Assert.isNotNull(parse);
        Assert.isNotNull(newVoicemailViewHolderListener2);
        Assert.isNotNull(this.totalDurationView);
        Assert.checkArgument(parse.equals(newVoicemailViewHolder2.getViewHolderVoicemailUri()));
        LogUtil.m9i("NewVoicemailMediaPlayerView.bindValuesFromAdapterOfExpandedViewHolderMediaPlayerView", "Updating the viewholder:%d mediaPlayerView with uri value:%s", Long.valueOf(newVoicemailViewHolder2.getViewHolderId()), parse.toString());
        this.fragmentManager = fragmentManager2;
        this.newVoicemailViewHolder = newVoicemailViewHolder2;
        this.newVoicemailViewHolderListener = newVoicemailViewHolderListener2;
        this.mediaPlayer = newVoicemailMediaPlayer;
        this.voicemailUri = parse;
        this.totalDurationView.setText(VoicemailEntryText.getVoicemailDuration(getContext(), voicemailEntry));
        initializeMediaPlayerButtonsAndViews();
        setupListenersForMediaPlayerButtons();
        this.seekBarView.setEnabled(false);
        this.seekBarView.setThumb(this.voicemailSeekHandleDisabled);
        if (TextUtils.isEmpty(this.numberVoicemailFrom)) {
            this.phoneButton.setEnabled(false);
            this.phoneButton.setClickable(false);
        } else {
            this.phoneButton.setEnabled(true);
            this.phoneButton.setClickable(true);
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Updating media player values for id:");
        outline13.append(newVoicemailViewHolder2.getViewHolderId());
        LogUtil.m9i("NewVoicemailMediaPlayerView.bindValuesFromAdapterOfExpandedViewHolderMediaPlayerView", outline13.toString(), new Object[0]);
        if (newVoicemailMediaPlayer.isPlaying() && newVoicemailMediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(this.voicemailUri)) {
            Assert.checkArgument(newVoicemailMediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(newVoicemailMediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri()));
            LogUtil.m9i("NewVoicemailMediaPlayerView.bindValuesFromAdapterOfExpandedViewHolderMediaPlayerView", "show playing state", new Object[0]);
            this.playButton.setVisibility(8);
            this.pauseButton.setVisibility(0);
            this.currentSeekBarPosition.setText(formatAsMinutesAndSeconds(newVoicemailMediaPlayer.getCurrentPosition()));
            if (this.seekBarView.getMax() != newVoicemailMediaPlayer.getDuration()) {
                this.seekBarView.setMax(newVoicemailMediaPlayer.getDuration());
            }
            this.seekBarView.setProgress(newVoicemailMediaPlayer.getCurrentPosition());
        } else if (!this.mediaPlayer.isPaused() || !newVoicemailMediaPlayer.getLastPausedVoicemailUri().equals(this.voicemailUri)) {
            LogUtil.m9i("NewVoicemailMediaPlayerView.bindValuesFromAdapterOfExpandedViewHolderMediaPlayerView", "show reset state", new Object[0]);
            this.playButton.setVisibility(0);
            this.pauseButton.setVisibility(8);
            this.seekBarView.setProgress(0);
            this.seekBarView.setMax(100);
            this.currentSeekBarPosition.setText(formatAsMinutesAndSeconds(0));
        } else {
            LogUtil.m9i("NewVoicemailMediaPlayerView.bindValuesFromAdapterOfExpandedViewHolderMediaPlayerView", "show paused state", new Object[0]);
            Assert.checkArgument(newVoicemailViewHolder2.getViewHolderVoicemailUri().equals(this.voicemailUri));
            this.playButton.setVisibility(0);
            this.pauseButton.setVisibility(8);
            this.currentSeekBarPosition.setText(formatAsMinutesAndSeconds(newVoicemailMediaPlayer.getCurrentPosition()));
            if (this.seekBarView.getMax() != newVoicemailMediaPlayer.getDuration()) {
                this.seekBarView.setMax(newVoicemailMediaPlayer.getDuration());
            }
            this.seekBarView.setProgress(newVoicemailMediaPlayer.getCurrentPosition());
        }
    }

    public final void clickPlayButton() {
        this.playButtonListener.onClick((View) null);
    }

    public Uri getVoicemailUri() {
        return this.voicemailUri;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        LogUtil.enterBlock("NewVoicemailMediaPlayer.onFinishInflate");
        initializeMediaPlayerButtonsAndViews();
        setupListenersForMediaPlayerButtons();
    }

    public void reset() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("the uri for this is ");
        outline13.append(this.voicemailUri);
        outline13.append(" and number is ");
        outline13.append(this.numberVoicemailFrom);
        LogUtil.m9i("NewVoicemailMediaPlayer.reset", outline13.toString(), new Object[0]);
        this.voicemailUri = null;
        this.voicemailLoadingStatusView.setVisibility(8);
        this.numberVoicemailFrom = null;
        this.phoneAccountId = null;
        this.phoneAccountComponentName = null;
    }

    public void setToPausedState(Uri uri, NewVoicemailMediaPlayer newVoicemailMediaPlayer) {
        Object[] objArr = new Object[3];
        boolean z = false;
        objArr[0] = uri == null ? "null" : this.voicemailUri.toString();
        objArr[1] = Boolean.valueOf(this.playButton.getVisibility() == 0);
        objArr[2] = Boolean.valueOf(this.pauseButton.getVisibility() == 0);
        LogUtil.m9i("NewVoicemailMediaPlayer.setToPausedState", "toPausedState uri:%s, play button visible:%b, pause button visible:%b", objArr);
        this.playButton.setVisibility(0);
        this.pauseButton.setVisibility(8);
        this.currentSeekBarPosition.setText(formatAsMinutesAndSeconds(this.mediaPlayer.getCurrentPosition()));
        if (this.seekBarView.getMax() != this.mediaPlayer.getDuration()) {
            this.seekBarView.setMax(this.mediaPlayer.getDuration());
        }
        this.seekBarView.setProgress(this.mediaPlayer.getCurrentPosition());
        Assert.checkArgument(this.voicemailUri.equals(uri));
        Assert.checkArgument(!newVoicemailMediaPlayer.isPlaying());
        Assert.checkArgument(newVoicemailMediaPlayer.equals(this.mediaPlayer), "there should only be one instance of a media player", new Object[0]);
        Assert.checkArgument(this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri().equals(this.voicemailUri));
        Assert.checkArgument(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(this.voicemailUri));
        Assert.checkArgument(this.mediaPlayer.getLastPausedVoicemailUri().equals(this.voicemailUri));
        Assert.isNotNull(this.mediaPlayer, "media player should have been set on bind", new Object[0]);
        Assert.checkArgument(this.mediaPlayer.getCurrentPosition() >= 0);
        Assert.checkArgument(this.mediaPlayer.getDuration() >= 0);
        Assert.checkArgument(this.playButton.getVisibility() == 0);
        Assert.checkArgument(this.pauseButton.getVisibility() == 8);
        Assert.checkArgument(this.seekBarView.getVisibility() == 0);
        if (this.currentSeekBarPosition.getVisibility() == 0) {
            z = true;
        }
        Assert.checkArgument(z);
    }

    public void setToResetState(NewVoicemailViewHolder newVoicemailViewHolder2, NewVoicemailMediaPlayer newVoicemailMediaPlayer) {
        Object[] objArr = new Object[4];
        objArr[0] = Long.valueOf(newVoicemailViewHolder2.getViewHolderId());
        objArr[1] = String.valueOf(this.voicemailUri);
        objArr[2] = Boolean.valueOf(this.playButton.getVisibility() == 0);
        objArr[3] = Boolean.valueOf(this.pauseButton.getVisibility() == 0);
        LogUtil.m9i("NewVoicemailMediaPlayer.setToResetState", "update the seekbar for viewholder id:%d, mediaplayer view uri:%s, play button visible:%b, pause button visible:%b", objArr);
        if (this.playButton.getVisibility() == 8) {
            this.playButton.setVisibility(0);
            this.pauseButton.setVisibility(8);
        }
        Assert.checkArgument(this.playButton.getVisibility() == 0);
        Assert.checkArgument(this.pauseButton.getVisibility() == 8);
        Assert.checkArgument(!newVoicemailMediaPlayer.isPlaying(), "when resetting an expanded state, there should be no voicemail playing", new Object[0]);
        Assert.checkArgument(newVoicemailMediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(Uri.EMPTY), "reset should have been called before updating its media player view", new Object[0]);
        this.currentSeekBarPosition.setText(formatAsMinutesAndSeconds(0));
        this.seekBarView.setProgress(0);
        this.seekBarView.setMax(100);
    }

    public void updateSeekBarDurationAndShowPlayButton(NewVoicemailMediaPlayer newVoicemailMediaPlayer) {
        if (newVoicemailMediaPlayer.isPlaying()) {
            this.playButton.setVisibility(8);
            this.pauseButton.setVisibility(0);
            this.voicemailLoadingStatusView.setVisibility(8);
            Assert.checkArgument(newVoicemailMediaPlayer.equals(this.mediaPlayer), "there should only be one instance of a media player", new Object[0]);
            Assert.checkArgument(this.mediaPlayer.getLastPreparedOrPreparingToPlayVoicemailUri().equals(this.voicemailUri));
            Assert.checkArgument(this.mediaPlayer.getLastPlayedOrPlayingVoicemailUri().equals(this.voicemailUri));
            Assert.isNotNull(this.mediaPlayer, "media player should have been set on bind", new Object[0]);
            Assert.checkArgument(this.mediaPlayer.isPlaying());
            boolean z = true;
            Assert.checkArgument(this.mediaPlayer.getCurrentPosition() >= 0);
            Assert.checkArgument(this.mediaPlayer.getDuration() >= 0);
            Assert.checkArgument(this.playButton.getVisibility() == 8);
            Assert.checkArgument(this.pauseButton.getVisibility() == 0);
            Assert.checkArgument(this.seekBarView.getVisibility() == 0);
            if (this.currentSeekBarPosition.getVisibility() != 0) {
                z = false;
            }
            Assert.checkArgument(z);
            this.currentSeekBarPosition.setText(formatAsMinutesAndSeconds(this.mediaPlayer.getCurrentPosition()));
            if (this.seekBarView.getMax() != this.mediaPlayer.getDuration()) {
                this.seekBarView.setMax(this.mediaPlayer.getDuration());
            }
            this.seekBarView.setProgress(this.mediaPlayer.getCurrentPosition());
        }
    }
}
