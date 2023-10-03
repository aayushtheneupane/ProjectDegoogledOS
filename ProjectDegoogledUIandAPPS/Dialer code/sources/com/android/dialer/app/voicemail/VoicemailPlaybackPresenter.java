package com.android.dialer.app.voicemail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.webkit.MimeTypeMap;
import com.android.dialer.R;
import com.android.dialer.app.calllog.CallLogListItemViewHolder;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.AsyncTaskExecutor;
import com.android.dialer.common.concurrent.AsyncTaskExecutors;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class VoicemailPlaybackPresenter implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static final String CLIP_POSITION_KEY = (VoicemailPlaybackPresenter.class.getName() + ".CLIP_POSITION_KEY");
    private static final String IS_PLAYING_STATE_KEY = (VoicemailPlaybackPresenter.class.getName() + ".IS_PLAYING_STATE_KEY");
    private static final String IS_PREPARED_KEY = (VoicemailPlaybackPresenter.class.getName() + ".IS_PREPARED");
    private static final String IS_SPEAKERPHONE_ON_KEY = (VoicemailPlaybackPresenter.class.getName() + ".IS_SPEAKER_PHONE_ON");
    private static final String VOICEMAIL_URI_KEY = (VoicemailPlaybackPresenter.class.getName() + ".VOICEMAIL_URI");
    private static VoicemailPlaybackPresenter instance;
    private static ScheduledExecutorService scheduledExecutorService;
    private Activity activity;
    protected AsyncTaskExecutor asyncTaskExecutor;
    protected Context context;
    private final AtomicInteger duration = new AtomicInteger(0);
    private FetchResultHandler fetchResultHandler;
    private boolean isPlaying;
    private boolean isPrepared;
    private boolean isSpeakerphoneOn;
    protected MediaPlayer mediaPlayer;
    private OnVoicemailDeletedListener onVoicemailDeletedListener;
    private int position;
    private PowerManager.WakeLock proximityWakeLock;
    private long rowId;
    private View shareVoicemailButtonView;
    private DialerExecutor<Pair<Context, Uri>> shareVoicemailExecutor;
    private boolean shouldResumePlaybackAfterSeeking;
    /* access modifiers changed from: private */
    public PlaybackView view;
    private VoicemailAudioManager voicemailAudioManager;
    protected Uri voicemailUri;

    private class FetchResultHandler extends ContentObserver implements Runnable {
        private final Handler fetchResultHandler;
        /* access modifiers changed from: private */
        public AtomicBoolean isWaitingForResult = new AtomicBoolean(true);
        /* access modifiers changed from: private */
        public final Uri voicemailUri;

        public FetchResultHandler(Handler handler, Uri uri) {
            super(handler);
            this.fetchResultHandler = handler;
            this.voicemailUri = uri;
            Context context = VoicemailPlaybackPresenter.this.context;
            if (context != null) {
                if (PermissionsUtil.hasReadVoicemailPermissions(context)) {
                    VoicemailPlaybackPresenter.this.context.getContentResolver().registerContentObserver(this.voicemailUri, false, this);
                }
                this.fetchResultHandler.postDelayed(this, 20000);
            }
        }

        public void destroy() {
            Context context;
            if (this.isWaitingForResult.getAndSet(false) && (context = VoicemailPlaybackPresenter.this.context) != null) {
                context.getContentResolver().unregisterContentObserver(this);
                this.fetchResultHandler.removeCallbacks(this);
            }
        }

        public void onChange(boolean z) {
            VoicemailPlaybackPresenter.this.asyncTaskExecutor.submit(Tasks.CHECK_CONTENT_AFTER_CHANGE, new AsyncTask<Void, Void, Boolean>() {
                public Object doInBackground(Object[] objArr) {
                    Void[] voidArr = (Void[]) objArr;
                    FetchResultHandler fetchResultHandler = FetchResultHandler.this;
                    return Boolean.valueOf(VoicemailPlaybackPresenter.this.queryHasContent(fetchResultHandler.voicemailUri));
                }

                public void onPostExecute(Object obj) {
                    if (((Boolean) obj).booleanValue()) {
                        FetchResultHandler fetchResultHandler = FetchResultHandler.this;
                        if (VoicemailPlaybackPresenter.this.context != null && fetchResultHandler.isWaitingForResult.getAndSet(false)) {
                            VoicemailPlaybackPresenter.this.context.getContentResolver().unregisterContentObserver(FetchResultHandler.this);
                            VoicemailPlaybackPresenter.this.showShareVoicemailButton(true);
                            VoicemailPlaybackPresenter.this.prepareContent();
                        }
                    }
                }
            }, new Void[0]);
        }

        public void run() {
            Context context;
            if (this.isWaitingForResult.getAndSet(false) && (context = VoicemailPlaybackPresenter.this.context) != null) {
                context.getContentResolver().unregisterContentObserver(this);
                if (VoicemailPlaybackPresenter.this.view != null) {
                    VoicemailPlaybackPresenter.this.view.setFetchContentTimeout();
                }
            }
        }
    }

    protected interface OnContentCheckedListener {
        void onContentChecked(boolean z);
    }

    public interface OnVoicemailDeletedListener {
        void onVoicemailDeleteUndo(long j, int i, Uri uri);

        void onVoicemailDeleted(CallLogListItemViewHolder callLogListItemViewHolder, Uri uri);

        void onVoicemailDeletedInDatabase(long j, Uri uri);
    }

    public interface PlaybackView {
        void disableUiElements();

        void enableUiElements();

        int getDesiredClipPosition();

        void onPlaybackError();

        void onPlaybackStarted(int i, ScheduledExecutorService scheduledExecutorService);

        void onPlaybackStopped();

        void onSpeakerphoneOn(boolean z);

        void resetSeekBar();

        void setClipPosition(int i, int i2);

        void setFetchContentTimeout();

        void setIsFetchingContent();

        void setPresenter(VoicemailPlaybackPresenter voicemailPlaybackPresenter, Uri uri);

        void setSuccess();
    }

    private static class ShareVoicemailWorker implements DialerExecutor.Worker<Pair<Context, Uri>, Pair<Uri, String>> {
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

        /* synthetic */ ShareVoicemailWorker(C03521 r1) {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ad, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            $closeResource(r13, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b1, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x00bf, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00c0, code lost:
            if (r14 != null) goto L_0x00c2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
            $closeResource(r13, r14);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c5, code lost:
            throw r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x00e8, code lost:
            r14 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x00e9, code lost:
            if (r11 != null) goto L_0x00eb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x00eb, code lost:
            $closeResource(r13, r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x00ee, code lost:
            throw r14;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object doInBackground(java.lang.Object r14) throws java.lang.Throwable {
            /*
                r13 = this;
                android.util.Pair r14 = (android.util.Pair) r14
                java.lang.String r13 = "transcription"
                java.lang.String r0 = "mime_type"
                java.lang.String r1 = "date"
                java.lang.String r2 = "number"
                java.lang.Object r3 = r14.first
                android.content.Context r3 = (android.content.Context) r3
                java.lang.Object r14 = r14.second
                android.net.Uri r14 = (android.net.Uri) r14
                android.content.ContentResolver r10 = r3.getContentResolver()
                android.database.Cursor r11 = r10.query(android.content.ContentUris.withAppendedId(android.provider.CallLog.Calls.CONTENT_URI_WITH_VOICEMAIL, android.content.ContentUris.parseId(r14)), com.android.dialer.phonenumbercache.CallLogQuery.getProjection(), (java.lang.String) null, (java.lang.String[]) null, (java.lang.String) null)
                java.lang.String r4 = "_id"
                java.lang.String[] r6 = new java.lang.String[]{r4, r2, r1, r0, r13}     // Catch:{ all -> 0x00e6 }
                r7 = 0
                r8 = 0
                r9 = 0
                r4 = r10
                r5 = r14
                android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00e6 }
                boolean r5 = com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.access$200(r11)     // Catch:{ all -> 0x00dd }
                r6 = 0
                if (r5 == 0) goto L_0x00d2
                boolean r5 = com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.access$200(r4)     // Catch:{ all -> 0x00dd }
                if (r5 == 0) goto L_0x00d2
                r5 = 8
                java.lang.String r5 = r11.getString(r5)     // Catch:{ all -> 0x00dd }
                int r2 = r4.getColumnIndex(r2)     // Catch:{ all -> 0x00dd }
                java.lang.String r2 = r4.getString(r2)     // Catch:{ all -> 0x00dd }
                int r1 = r4.getColumnIndex(r1)     // Catch:{ all -> 0x00dd }
                long r7 = r4.getLong(r1)     // Catch:{ all -> 0x00dd }
                int r0 = r4.getColumnIndex(r0)     // Catch:{ all -> 0x00dd }
                java.lang.String r0 = r4.getString(r0)     // Catch:{ all -> 0x00dd }
                int r13 = r4.getColumnIndex(r13)     // Catch:{ all -> 0x00dd }
                java.lang.String r13 = r4.getString(r13)     // Catch:{ all -> 0x00dd }
                java.io.File r1 = new java.io.File     // Catch:{ all -> 0x00dd }
                java.io.File r9 = r3.getCacheDir()     // Catch:{ all -> 0x00dd }
                java.lang.String r12 = "my_cache"
                r1.<init>(r9, r12)     // Catch:{ all -> 0x00dd }
                boolean r9 = r1.exists()     // Catch:{ all -> 0x00dd }
                if (r9 != 0) goto L_0x0070
                r1.mkdirs()     // Catch:{ all -> 0x00dd }
            L_0x0070:
                java.io.File r9 = new java.io.File     // Catch:{ all -> 0x00dd }
                java.lang.String r0 = com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.access$500(r5, r2, r0, r7)     // Catch:{ all -> 0x00dd }
                r9.<init>(r1, r0)     // Catch:{ all -> 0x00dd }
                java.io.InputStream r14 = r10.openInputStream(r14)     // Catch:{ IOException -> 0x00c6 }
                android.net.Uri r0 = android.net.Uri.fromFile(r9)     // Catch:{ all -> 0x00bd }
                java.io.OutputStream r0 = r10.openOutputStream(r0)     // Catch:{ all -> 0x00bd }
                if (r14 == 0) goto L_0x00b2
                if (r0 == 0) goto L_0x00b2
                com.google.common.p007io.ByteStreams.copy(r14, r0)     // Catch:{ all -> 0x00ab }
                android.util.Pair r1 = new android.util.Pair     // Catch:{ all -> 0x00ab }
                com.android.dialer.constants.Constants r2 = com.android.dialer.constants.Constants.get()     // Catch:{ all -> 0x00ab }
                java.lang.String r2 = r2.getFileProviderAuthority()     // Catch:{ all -> 0x00ab }
                android.net.Uri r2 = android.support.p000v4.content.FileProvider.getUriForFile(r3, r2, r9)     // Catch:{ all -> 0x00ab }
                r1.<init>(r2, r13)     // Catch:{ all -> 0x00ab }
                $closeResource(r6, r0)     // Catch:{ all -> 0x00bd }
                $closeResource(r6, r14)     // Catch:{ IOException -> 0x00c6 }
                $closeResource(r6, r4)     // Catch:{ all -> 0x00e6 }
                $closeResource(r6, r11)
                r6 = r1
                goto L_0x00dc
            L_0x00ab:
                r13 = move-exception
                throw r13     // Catch:{ all -> 0x00ad }
            L_0x00ad:
                r1 = move-exception
                $closeResource(r13, r0)     // Catch:{ all -> 0x00bd }
                throw r1     // Catch:{ all -> 0x00bd }
            L_0x00b2:
                if (r0 == 0) goto L_0x00b7
                $closeResource(r6, r0)     // Catch:{ all -> 0x00bd }
            L_0x00b7:
                if (r14 == 0) goto L_0x00ce
                $closeResource(r6, r14)     // Catch:{ IOException -> 0x00c6 }
                goto L_0x00ce
            L_0x00bd:
                r13 = move-exception
                throw r13     // Catch:{ all -> 0x00bf }
            L_0x00bf:
                r0 = move-exception
                if (r14 == 0) goto L_0x00c5
                $closeResource(r13, r14)     // Catch:{ IOException -> 0x00c6 }
            L_0x00c5:
                throw r0     // Catch:{ IOException -> 0x00c6 }
            L_0x00c6:
                r13 = move-exception
                java.lang.String r14 = "VoicemailAsyncTaskUtil.shareVoicemail"
                java.lang.String r0 = "failed to copy voicemail content to new file: "
                com.android.dialer.common.LogUtil.m7e((java.lang.String) r14, (java.lang.String) r0, (java.lang.Throwable) r13)     // Catch:{ all -> 0x00dd }
            L_0x00ce:
                $closeResource(r6, r4)     // Catch:{ all -> 0x00e6 }
                goto L_0x00d9
            L_0x00d2:
                if (r4 == 0) goto L_0x00d7
                $closeResource(r6, r4)     // Catch:{ all -> 0x00e6 }
            L_0x00d7:
                if (r11 == 0) goto L_0x00dc
            L_0x00d9:
                $closeResource(r6, r11)
            L_0x00dc:
                return r6
            L_0x00dd:
                r13 = move-exception
                throw r13     // Catch:{ all -> 0x00df }
            L_0x00df:
                r14 = move-exception
                if (r4 == 0) goto L_0x00e5
                $closeResource(r13, r4)     // Catch:{ all -> 0x00e6 }
            L_0x00e5:
                throw r14     // Catch:{ all -> 0x00e6 }
            L_0x00e6:
                r13 = move-exception
                throw r13     // Catch:{ all -> 0x00e8 }
            L_0x00e8:
                r14 = move-exception
                if (r11 == 0) goto L_0x00ee
                $closeResource(r13, r11)
            L_0x00ee:
                throw r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.ShareVoicemailWorker.doInBackground(java.lang.Object):java.lang.Object");
        }
    }

    public enum Tasks {
        CHECK_FOR_CONTENT,
        CHECK_CONTENT_AFTER_CHANGE,
        SHARE_VOICEMAIL,
        SEND_FETCH_REQUEST
    }

    protected VoicemailPlaybackPresenter(Activity activity2) {
        Context applicationContext = activity2.getApplicationContext();
        this.asyncTaskExecutor = AsyncTaskExecutors.createAsyncTaskExecutor();
        this.voicemailAudioManager = new VoicemailAudioManager(applicationContext, this);
        PowerManager powerManager = (PowerManager) applicationContext.getSystemService("power");
        if (powerManager.isWakeLockLevelSupported(32)) {
            this.proximityWakeLock = powerManager.newWakeLock(32, "VoicemailPlaybackPresenter");
        }
    }

    static /* synthetic */ boolean access$200(Cursor cursor) {
        return cursor != null && cursor.moveToFirst();
    }

    static /* synthetic */ String access$500(String str, String str2, String str3, long j) {
        if (TextUtils.isEmpty(str)) {
            str = str2;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy_hhmmaa", Locale.getDefault());
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(str3);
        StringBuilder outline14 = GeneratedOutlineSupport.outline14(str, "_");
        outline14.append(simpleDateFormat.format(new Date(j)));
        outline14.append(TextUtils.isEmpty(extensionFromMimeType) ? "" : GeneratedOutlineSupport.outline8(".", extensionFromMimeType));
        return outline14.toString();
    }

    private void disableProximitySensor(boolean z) {
        PowerManager.WakeLock wakeLock = this.proximityWakeLock;
        if (wakeLock != null) {
            if (wakeLock.isHeld()) {
                LogUtil.m9i("VoicemailPlaybackPresenter.disableProximitySensor", "releasing proximity wake lock", new Object[0]);
                this.proximityWakeLock.release(z ? 1 : 0);
                return;
            }
            LogUtil.m9i("VoicemailPlaybackPresenter.disableProximitySensor", "proximity wake lock already released", new Object[0]);
        }
    }

    public static VoicemailPlaybackPresenter getInstance(Activity activity2, Bundle bundle) {
        if (instance == null) {
            instance = new VoicemailPlaybackPresenter(activity2);
        }
        instance.init(activity2, bundle);
        return instance;
    }

    private static synchronized ScheduledExecutorService getScheduledExecutorServiceInstance() {
        ScheduledExecutorService scheduledExecutorService2;
        synchronized (VoicemailPlaybackPresenter.class) {
            if (scheduledExecutorService == null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(2);
            }
            scheduledExecutorService2 = scheduledExecutorService;
        }
        return scheduledExecutorService2;
    }

    /* access modifiers changed from: private */
    public void prepareMediaPlayer() {
        try {
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setOnPreparedListener(this);
            this.mediaPlayer.setOnErrorListener(this);
            this.mediaPlayer.setOnCompletionListener(this);
            this.mediaPlayer.reset();
            this.mediaPlayer.setDataSource(this.context, this.voicemailUri);
            this.mediaPlayer.setAudioStreamType(0);
            this.mediaPlayer.prepareAsync();
        } catch (IOException e) {
            handleError(e);
        }
    }

    /* access modifiers changed from: private */
    public boolean queryHasContent(Uri uri) {
        Context context2;
        boolean z = false;
        if (!(uri == null || (context2 = this.context) == null)) {
            Cursor query = context2.getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.moveToNext()) {
                        int i = query.getInt(query.getColumnIndex("duration"));
                        this.duration.set(i > 0 ? i * 1000 : 0);
                        if (query.getInt(query.getColumnIndex("has_content")) == 1) {
                            z = true;
                        }
                        return z;
                    }
                } finally {
                    query.close();
                }
            }
            if (query != null) {
                query.close();
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void showShareVoicemailButton(boolean z) {
        Context context2 = this.context;
        if (context2 != null && ((SharedPrefConfigProvider) ConfigProviderComponent.get(context2).getConfigProvider()).getBoolean("share_voicemail_allowed", true) && this.shareVoicemailButtonView != null) {
            if (z) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.VVM_SHARE_VISIBLE);
            }
            int i = 0;
            new Object[1][0] = Boolean.valueOf(z);
            View view2 = this.shareVoicemailButtonView;
            if (!z) {
                i = 8;
            }
            view2.setVisibility(i);
        }
    }

    /* access modifiers changed from: protected */
    public void checkForContent(final OnContentCheckedListener onContentCheckedListener) {
        this.asyncTaskExecutor.submit(Tasks.CHECK_FOR_CONTENT, new AsyncTask<Void, Void, Boolean>() {
            public Object doInBackground(Object[] objArr) {
                Void[] voidArr = (Void[]) objArr;
                VoicemailPlaybackPresenter voicemailPlaybackPresenter = VoicemailPlaybackPresenter.this;
                return Boolean.valueOf(voicemailPlaybackPresenter.queryHasContent(voicemailPlaybackPresenter.voicemailUri));
            }

            public void onPostExecute(Object obj) {
                onContentCheckedListener.onContentChecked(((Boolean) obj).booleanValue());
            }
        }, new Void[0]);
    }

    public void clearInstance() {
        instance = null;
    }

    public int getMediaPlayerPosition() {
        MediaPlayer mediaPlayer2;
        if (!this.isPrepared || (mediaPlayer2 = this.mediaPlayer) == null) {
            return 0;
        }
        return mediaPlayer2.getCurrentPosition();
    }

    /* access modifiers changed from: protected */
    public void handleError(Exception exc) {
        LogUtil.m7e("VoicemailPlaybackPresenter.handlerError", "could not play voicemail", (Throwable) exc);
        if (this.isPrepared) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
            this.isPrepared = false;
        }
        PlaybackView playbackView = this.view;
        if (playbackView != null) {
            playbackView.onPlaybackError();
        }
        this.position = 0;
        this.isPlaying = false;
    }

    /* access modifiers changed from: protected */
    public void init(Activity activity2, Bundle bundle) {
        Assert.isMainThread();
        this.activity = activity2;
        this.context = activity2;
        if (bundle != null) {
            this.voicemailUri = (Uri) bundle.getParcelable(VOICEMAIL_URI_KEY);
            this.isPrepared = bundle.getBoolean(IS_PREPARED_KEY);
            this.position = bundle.getInt(CLIP_POSITION_KEY, 0);
            this.isPlaying = bundle.getBoolean(IS_PLAYING_STATE_KEY, false);
            this.isSpeakerphoneOn = bundle.getBoolean(IS_SPEAKERPHONE_ON_KEY, false);
            ((AudioManager) activity2.getSystemService(AudioManager.class)).setSpeakerphoneOn(this.isSpeakerphoneOn);
        }
        if (this.mediaPlayer == null) {
            this.isPrepared = false;
            this.isPlaying = false;
        }
        if (this.activity != null) {
            if (isPlaying()) {
                this.activity.getWindow().addFlags(128);
            } else {
                this.activity.getWindow().clearFlags(128);
            }
            this.shareVoicemailExecutor = ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(this.context).dialerExecutorFactory()).createUiTaskBuilder(this.activity.getFragmentManager(), "shareVoicemail", new ShareVoicemailWorker((C03521) null)).onSuccess(new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    VoicemailPlaybackPresenter.this.lambda$init$0$VoicemailPlaybackPresenter((Pair) obj);
                }
            }).build();
        }
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public boolean isSpeakerphoneOn() {
        return this.isSpeakerphoneOn;
    }

    public /* synthetic */ void lambda$init$0$VoicemailPlaybackPresenter(Pair pair) {
        if (pair == null) {
            LogUtil.m8e("VoicemailAsyncTaskUtil.shareVoicemail", "failed to get voicemail", new Object[0]);
            return;
        }
        Context context2 = this.context;
        Uri uri = (Uri) pair.first;
        String str = (String) pair.second;
        Intent intent = new Intent();
        if (TextUtils.isEmpty(str)) {
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.setFlags(1);
            intent.setType(context2.getContentResolver().getType(uri));
        } else {
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.putExtra("android.intent.extra.TEXT", str);
            intent.setFlags(1);
            intent.setType(context2.getContentResolver().getType(uri));
        }
        context2.startActivity(Intent.createChooser(intent, this.context.getResources().getText(R.string.call_log_action_share_voicemail)));
    }

    public /* synthetic */ void lambda$resumePlayback$2$VoicemailPlaybackPresenter(boolean z) {
        if (!z) {
            this.isPlaying = requestContent(0);
            return;
        }
        showShareVoicemailButton(true);
        this.isPlaying = true;
        prepareContent();
    }

    public /* synthetic */ void lambda$setPlaybackView$1$VoicemailPlaybackPresenter(boolean z, boolean z2) {
        if (z2) {
            showShareVoicemailButton(true);
            prepareContent();
            return;
        }
        if (z) {
            requestContent(0);
        }
        PlaybackView playbackView = this.view;
        if (playbackView != null) {
            playbackView.resetSeekBar();
            this.view.setClipPosition(0, this.duration.get());
        }
    }

    public void onAudioFocusChange(boolean z) {
        if (this.isPlaying != z) {
            if (z) {
                resumePlayback();
            } else {
                pausePlayback(true);
            }
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer2) {
        pausePlayback();
        this.position = 0;
        if (this.view != null) {
            mediaPlayer2.seekTo(0);
            this.view.setClipPosition(0, this.duration.get());
        }
    }

    public void onDestroy() {
        this.activity = null;
        this.context = null;
        ScheduledExecutorService scheduledExecutorService2 = scheduledExecutorService;
        if (scheduledExecutorService2 != null) {
            scheduledExecutorService2.shutdown();
            scheduledExecutorService = null;
        }
        FetchResultHandler fetchResultHandler2 = this.fetchResultHandler;
        if (fetchResultHandler2 != null) {
            fetchResultHandler2.destroy();
            this.fetchResultHandler = null;
        }
    }

    public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
        handleError(new IllegalStateException(GeneratedOutlineSupport.outline5("MediaPlayer error listener invoked: ", i2)));
        return true;
    }

    public void onPause() {
        this.voicemailAudioManager.unregisterReceivers();
        Activity activity2 = this.activity;
        if (activity2 == null || !this.isPrepared || !activity2.isChangingConfigurations()) {
            pausePresenter(false);
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer2) {
        if (this.view != null && this.context != null) {
            this.isPrepared = true;
            this.duration.set(this.mediaPlayer.getDuration());
            "mPosition=" + this.position;
            this.view.setClipPosition(this.position, this.duration.get());
            this.view.enableUiElements();
            this.view.setSuccess();
            if (!mediaPlayer2.isPlaying()) {
                this.mediaPlayer.seekTo(this.position);
            }
            if (this.isPlaying) {
                resumePlayback();
            } else {
                pausePlayback();
            }
        }
    }

    public void onResume() {
        this.voicemailAudioManager.registerReceivers();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.view != null) {
            bundle.putParcelable(VOICEMAIL_URI_KEY, this.voicemailUri);
            bundle.putBoolean(IS_PREPARED_KEY, this.isPrepared);
            bundle.putInt(CLIP_POSITION_KEY, this.view.getDesiredClipPosition());
            bundle.putBoolean(IS_PLAYING_STATE_KEY, this.isPlaying);
            bundle.putBoolean(IS_SPEAKERPHONE_ON_KEY, this.isSpeakerphoneOn);
        }
    }

    /* access modifiers changed from: package-private */
    public void onVoicemailDeleteUndo(int i) {
        OnVoicemailDeletedListener onVoicemailDeletedListener2 = this.onVoicemailDeletedListener;
        if (onVoicemailDeletedListener2 != null) {
            onVoicemailDeletedListener2.onVoicemailDeleteUndo(this.rowId, i, this.voicemailUri);
        }
    }

    /* access modifiers changed from: package-private */
    public void onVoicemailDeleted(CallLogListItemViewHolder callLogListItemViewHolder) {
        OnVoicemailDeletedListener onVoicemailDeletedListener2 = this.onVoicemailDeletedListener;
        if (onVoicemailDeletedListener2 != null) {
            onVoicemailDeletedListener2.onVoicemailDeleted(callLogListItemViewHolder, this.voicemailUri);
        }
    }

    /* access modifiers changed from: package-private */
    public void onVoicemailDeletedInDatabase() {
        OnVoicemailDeletedListener onVoicemailDeletedListener2 = this.onVoicemailDeletedListener;
        if (onVoicemailDeletedListener2 != null) {
            onVoicemailDeletedListener2.onVoicemailDeletedInDatabase(this.rowId, this.voicemailUri);
        }
    }

    public void pausePlayback() {
        pausePlayback(false);
    }

    public void pausePlaybackForSeeking() {
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null) {
            this.shouldResumePlaybackAfterSeeking = mediaPlayer2.isPlaying();
        }
        pausePlayback(true);
    }

    public void pausePresenter(boolean z) {
        pausePlayback();
        MediaPlayer mediaPlayer2 = this.mediaPlayer;
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
            this.mediaPlayer = null;
        }
        disableProximitySensor(false);
        this.isPrepared = false;
        this.isPlaying = false;
        if (z) {
            this.position = 0;
        }
        PlaybackView playbackView = this.view;
        if (playbackView != null) {
            playbackView.onPlaybackStopped();
            if (z) {
                this.view.setClipPosition(0, this.duration.get());
            } else {
                this.position = this.view.getDesiredClipPosition();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void prepareContent() {
        if (this.view != null && this.context != null) {
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 != null) {
                mediaPlayer2.release();
                this.mediaPlayer = null;
            }
            this.view.disableUiElements();
            this.isPrepared = false;
            Context context2 = this.context;
            if (context2 == null || !TelecomUtil.isInManagedCall(context2)) {
                StrictModeUtils.bypass((Runnable) new Runnable() {
                    public final void run() {
                        VoicemailPlaybackPresenter.this.prepareMediaPlayer();
                    }
                });
            } else {
                handleError(new IllegalStateException("Cannot play voicemail when call is in progress"));
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean requestContent(int i) {
        if (this.context == null || this.voicemailUri == null) {
            return false;
        }
        FetchResultHandler fetchResultHandler2 = new FetchResultHandler(new Handler(), this.voicemailUri);
        FetchResultHandler fetchResultHandler3 = this.fetchResultHandler;
        if (fetchResultHandler3 != null) {
            fetchResultHandler3.destroy();
        }
        this.view.setIsFetchingContent();
        this.fetchResultHandler = fetchResultHandler2;
        this.asyncTaskExecutor.submit(Tasks.SEND_FETCH_REQUEST, new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0065, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
                if (r8 != null) goto L_0x0068;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
                r8.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x006c, code lost:
                r8 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
                r7.addSuppressed(r8);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
                throw r0;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Object doInBackground(java.lang.Object[] r8) {
                /*
                    r7 = this;
                    java.lang.Void[] r8 = (java.lang.Void[]) r8
                    com.android.dialer.app.voicemail.VoicemailPlaybackPresenter r8 = com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.this
                    android.content.Context r8 = r8.context
                    android.content.ContentResolver r0 = r8.getContentResolver()
                    com.android.dialer.app.voicemail.VoicemailPlaybackPresenter r8 = com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.this
                    android.net.Uri r1 = r8.voicemailUri
                    java.lang.String r8 = "source_package"
                    java.lang.String[] r2 = new java.lang.String[]{r8}
                    r3 = 0
                    r4 = 0
                    r5 = 0
                    android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5)
                    boolean r0 = com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.access$200(r8)     // Catch:{ all -> 0x0063 }
                    java.lang.String r1 = "VoicemailPlaybackPresenter.requestContent"
                    r2 = 0
                    r3 = 0
                    if (r0 != 0) goto L_0x002e
                    java.lang.String r0 = "mVoicemailUri does not return a SOURCE_PACKAGE"
                    java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ all -> 0x0063 }
                    com.android.dialer.common.LogUtil.m8e((java.lang.String) r1, (java.lang.String) r0, (java.lang.Object[]) r4)     // Catch:{ all -> 0x0063 }
                    r0 = r3
                    goto L_0x0032
                L_0x002e:
                    java.lang.String r0 = r8.getString(r2)     // Catch:{ all -> 0x0063 }
                L_0x0032:
                    android.content.Intent r4 = new android.content.Intent     // Catch:{ all -> 0x0063 }
                    java.lang.String r5 = "android.intent.action.FETCH_VOICEMAIL"
                    com.android.dialer.app.voicemail.VoicemailPlaybackPresenter r6 = com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.this     // Catch:{ all -> 0x0063 }
                    android.net.Uri r6 = r6.voicemailUri     // Catch:{ all -> 0x0063 }
                    r4.<init>(r5, r6)     // Catch:{ all -> 0x0063 }
                    r4.setPackage(r0)     // Catch:{ all -> 0x0063 }
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0063 }
                    r5.<init>()     // Catch:{ all -> 0x0063 }
                    java.lang.String r6 = "Sending ACTION_FETCH_VOICEMAIL to "
                    r5.append(r6)     // Catch:{ all -> 0x0063 }
                    r5.append(r0)     // Catch:{ all -> 0x0063 }
                    java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0063 }
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0063 }
                    com.android.dialer.common.LogUtil.m9i(r1, r0, r2)     // Catch:{ all -> 0x0063 }
                    com.android.dialer.app.voicemail.VoicemailPlaybackPresenter r7 = com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.this     // Catch:{ all -> 0x0063 }
                    android.content.Context r7 = r7.context     // Catch:{ all -> 0x0063 }
                    r7.sendBroadcast(r4)     // Catch:{ all -> 0x0063 }
                    if (r8 == 0) goto L_0x0062
                    r8.close()
                L_0x0062:
                    return r3
                L_0x0063:
                    r7 = move-exception
                    throw r7     // Catch:{ all -> 0x0065 }
                L_0x0065:
                    r0 = move-exception
                    if (r8 == 0) goto L_0x0070
                    r8.close()     // Catch:{ all -> 0x006c }
                    goto L_0x0070
                L_0x006c:
                    r8 = move-exception
                    r7.addSuppressed(r8)
                L_0x0070:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.voicemail.VoicemailPlaybackPresenter.C03532.doInBackground(java.lang.Object[]):java.lang.Object");
            }
        }, new Void[0]);
        return true;
    }

    public void resetAll() {
        pausePresenter(true);
        this.view = null;
        this.voicemailUri = null;
    }

    public void resumePlayback() {
        if (this.view != null) {
            if (!this.isPrepared) {
                checkForContent(new OnContentCheckedListener() {
                    public final void onContentChecked(boolean z) {
                        VoicemailPlaybackPresenter.this.lambda$resumePlayback$2$VoicemailPlaybackPresenter(z);
                    }
                });
                return;
            }
            this.isPlaying = true;
            this.activity.getWindow().addFlags(128);
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 != null && !mediaPlayer2.isPlaying()) {
                this.position = Math.max(0, Math.min(this.position, this.duration.get()));
                this.mediaPlayer.seekTo(this.position);
                try {
                    this.voicemailAudioManager.requestAudioFocus();
                    this.mediaPlayer.start();
                    setSpeakerphoneOn(this.isSpeakerphoneOn);
                    this.voicemailAudioManager.setSpeakerphoneOn(this.isSpeakerphoneOn);
                } catch (RejectedExecutionException e) {
                    handleError(e);
                }
            }
            new Object[1][0] = Integer.valueOf(this.position);
            this.view.onPlaybackStarted(this.duration.get(), getScheduledExecutorServiceInstance());
        }
    }

    public void resumePlaybackAfterSeeking(int i) {
        this.position = i;
        if (this.shouldResumePlaybackAfterSeeking) {
            this.shouldResumePlaybackAfterSeeking = false;
            resumePlayback();
        }
    }

    public void seek(int i) {
        this.position = i;
        this.mediaPlayer.seekTo(this.position);
    }

    public void setOnVoicemailDeletedListener(OnVoicemailDeletedListener onVoicemailDeletedListener2) {
        this.onVoicemailDeletedListener = onVoicemailDeletedListener2;
    }

    public void setPlaybackView(PlaybackView playbackView, long j, Uri uri, boolean z, View view2) {
        this.rowId = j;
        this.view = playbackView;
        this.view.setPresenter(this, uri);
        this.view.onSpeakerphoneOn(this.isSpeakerphoneOn);
        this.shareVoicemailButtonView = view2;
        showShareVoicemailButton(false);
        if (this.mediaPlayer == null || !this.isPrepared || !uri.equals(this.voicemailUri)) {
            if (!uri.equals(this.voicemailUri)) {
                this.voicemailUri = uri;
                this.position = 0;
            }
            checkForContent(new OnContentCheckedListener(z) {
                private final /* synthetic */ boolean f$1;

                {
                    this.f$1 = r2;
                }

                public final void onContentChecked(boolean z) {
                    VoicemailPlaybackPresenter.this.lambda$setPlaybackView$1$VoicemailPlaybackPresenter(this.f$1, z);
                }
            });
            if (z) {
                this.isPlaying = z;
                return;
            }
            return;
        }
        this.position = this.mediaPlayer.getCurrentPosition();
        onPrepared(this.mediaPlayer);
        showShareVoicemailButton(true);
    }

    public void setSpeakerphoneOn(boolean z) {
        MediaPlayer mediaPlayer2;
        PlaybackView playbackView = this.view;
        if (playbackView != null) {
            playbackView.onSpeakerphoneOn(z);
            this.isSpeakerphoneOn = z;
            if (!this.isPlaying) {
                return;
            }
            if (z || this.voicemailAudioManager.isWiredHeadsetPluggedIn()) {
                disableProximitySensor(false);
            } else if (this.proximityWakeLock != null && !this.isSpeakerphoneOn && this.isPrepared && (mediaPlayer2 = this.mediaPlayer) != null && mediaPlayer2.isPlaying()) {
                if (!this.proximityWakeLock.isHeld()) {
                    LogUtil.m9i("VoicemailPlaybackPresenter.enableProximitySensor", "acquiring proximity wake lock", new Object[0]);
                    this.proximityWakeLock.acquire();
                    return;
                }
                LogUtil.m9i("VoicemailPlaybackPresenter.enableProximitySensor", "proximity wake lock already acquired", new Object[0]);
            }
        }
    }

    public void shareVoicemail() {
        this.shareVoicemailExecutor.executeParallel(new Pair(this.context, this.voicemailUri));
    }

    public void toggleSpeakerphone() {
        this.voicemailAudioManager.setSpeakerphoneOn(!this.isSpeakerphoneOn);
        setSpeakerphoneOn(!this.isSpeakerphoneOn);
    }

    private void pausePlayback(boolean z) {
        if (this.isPrepared) {
            this.isPlaying = false;
            MediaPlayer mediaPlayer2 = this.mediaPlayer;
            if (mediaPlayer2 != null && mediaPlayer2.isPlaying()) {
                this.mediaPlayer.pause();
            }
            MediaPlayer mediaPlayer3 = this.mediaPlayer;
            this.position = mediaPlayer3 == null ? 0 : mediaPlayer3.getCurrentPosition();
            new Object[1][0] = Integer.valueOf(this.position);
            PlaybackView playbackView = this.view;
            if (playbackView != null) {
                playbackView.onPlaybackStopped();
            }
            if (!z) {
                this.voicemailAudioManager.abandonAudioFocus();
            }
            Activity activity2 = this.activity;
            if (activity2 != null) {
                activity2.getWindow().clearFlags(128);
            }
            disableProximitySensor(true);
        }
    }
}
