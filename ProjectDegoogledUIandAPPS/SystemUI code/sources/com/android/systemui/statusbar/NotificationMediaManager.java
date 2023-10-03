package com.android.systemui.statusbar;

import android.content.ComponentName;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Trace;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import android.view.KeyEvent;
import android.widget.ImageView;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.NotificationEntryListener;
import com.android.systemui.statusbar.notification.NotificationEntryManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.LockscreenWallpaper;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ShadeController;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.phone.StatusBarWindowController;
import com.android.systemui.statusbar.policy.KeyguardMonitor;
import com.android.systemui.statusbar.policy.TaskHelper;
import dagger.Lazy;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NotificationMediaManager implements Dumpable {
    private static final HashSet<Integer> PAUSED_MEDIA_STATES = new HashSet<>();
    private BackDropView mBackdrop;
    private ImageView mBackdropBack;
    /* access modifiers changed from: private */
    public ImageView mBackdropFront;
    private BiometricUnlockController mBiometricUnlockController;
    private final SysuiColorExtractor mColorExtractor = ((SysuiColorExtractor) Dependency.get(SysuiColorExtractor.class));
    /* access modifiers changed from: private */
    public final Context mContext;
    private NotificationEntryManager mEntryManager;
    private String mForegroundPackage = "";
    private final Handler mHandler = ((Handler) Dependency.get(Dependency.MAIN_HANDLER));
    protected final Runnable mHideBackdropFront = new Runnable() {
        public void run() {
            NotificationMediaManager.this.mBackdropFront.setVisibility(4);
            NotificationMediaManager.this.mBackdropFront.animate().cancel();
            NotificationMediaManager.this.mBackdropFront.setImageDrawable((Drawable) null);
        }
    };
    private final KeyguardBypassController mKeyguardBypassController;
    private final KeyguardMonitor mKeyguardMonitor = ((KeyguardMonitor) Dependency.get(KeyguardMonitor.class));
    private float mLockscreenMediaBlur;
    private LockscreenWallpaper mLockscreenWallpaper;
    private CharSequence mMediaArtist;
    /* access modifiers changed from: private */
    public final MediaArtworkProcessor mMediaArtworkProcessor;
    private MediaController mMediaController;
    private boolean mMediaIsVisible;
    private final MediaController.Callback mMediaListener = new MediaController.Callback() {
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            super.onPlaybackStateChanged(playbackState);
            if (playbackState != null) {
                if (!NotificationMediaManager.this.isPlaybackActive(playbackState.getState())) {
                    NotificationMediaManager.this.clearCurrentMediaNotification();
                }
                NotificationMediaManager.this.dispatchUpdateMediaMetaData(true, true);
            }
        }

        public void onMetadataChanged(MediaMetadata mediaMetadata) {
            super.onMetadataChanged(mediaMetadata);
            NotificationMediaManager.this.mMediaArtworkProcessor.clearCache();
            MediaMetadata unused = NotificationMediaManager.this.mMediaMetadata = mediaMetadata;
            NotificationMediaManager.this.dispatchUpdateMediaMetaData(true, true);
        }
    };
    private final ArrayList<MediaListener> mMediaListeners;
    /* access modifiers changed from: private */
    public MediaMetadata mMediaMetadata;
    private String mMediaNotificationKey;
    private final MediaSessionManager mMediaSessionManager;
    private CharSequence mMediaTitle;
    private String mNowPlayingNotificationKey;
    private String mNowPlayingTrack;
    protected NotificationPresenter mPresenter;
    private final Set<AsyncTask<?, ?, ?>> mProcessArtworkTasks = new ArraySet();
    private final DeviceConfig.OnPropertiesChangedListener mPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() {
        public void onPropertiesChanged(DeviceConfig.Properties properties) {
            for (String str : properties.getKeyset()) {
                if ("compact_media_notification_seekbar_enabled".equals(str)) {
                    boolean unused = NotificationMediaManager.this.mShowCompactMediaSeekbar = "true".equals(properties.getString(str, (String) null));
                }
            }
        }
    };
    private ScrimController mScrimController;
    private Lazy<ShadeController> mShadeController;
    /* access modifiers changed from: private */
    public boolean mShowCompactMediaSeekbar;
    private boolean mShowMediaHeadsup;
    private boolean mShowMediaMetadata;
    private StatusBar mStatusBar;
    private final StatusBarStateController mStatusBarStateController = ((StatusBarStateController) Dependency.get(StatusBarStateController.class));
    private Lazy<StatusBarWindowController> mStatusBarWindowController;
    private TaskHelper mTaskHelper = ((TaskHelper) Dependency.get(TaskHelper.class));

    public interface MediaListener {
        void onMetadataOrStateChanged(MediaMetadata mediaMetadata, int i);

        void setMediaNotificationColor(boolean z, int i) {
        }
    }

    /* access modifiers changed from: private */
    public boolean isPlaybackActive(int i) {
        return (i == 1 || i == 7 || i == 0) ? false : true;
    }

    static {
        PAUSED_MEDIA_STATES.add(0);
        PAUSED_MEDIA_STATES.add(1);
        PAUSED_MEDIA_STATES.add(2);
        PAUSED_MEDIA_STATES.add(7);
    }

    public NotificationMediaManager(Context context, Lazy<ShadeController> lazy, Lazy<StatusBarWindowController> lazy2, NotificationEntryManager notificationEntryManager, MediaArtworkProcessor mediaArtworkProcessor, KeyguardBypassController keyguardBypassController) {
        this.mContext = context;
        this.mMediaArtworkProcessor = mediaArtworkProcessor;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mMediaListeners = new ArrayList<>();
        this.mMediaSessionManager = (MediaSessionManager) this.mContext.getSystemService("media_session");
        this.mShadeController = lazy;
        this.mStatusBarWindowController = lazy2;
        this.mEntryManager = notificationEntryManager;
        notificationEntryManager.addNotificationEntryListener(new NotificationEntryListener() {
            public void onEntryRemoved(NotificationEntry notificationEntry, NotificationVisibility notificationVisibility, boolean z) {
                NotificationMediaManager.this.onNotificationRemoved(notificationEntry.key);
            }

            public void onNotificationAdded(NotificationEntry notificationEntry) {
                NotificationMediaManager.this.checkMediaNotificationColor(notificationEntry);
            }

            public void onEntryReinflated(NotificationEntry notificationEntry) {
                NotificationMediaManager.this.checkMediaNotificationColor(notificationEntry);
            }
        });
        this.mShowCompactMediaSeekbar = "true".equals(DeviceConfig.getProperty("systemui", "compact_media_notification_seekbar_enabled"));
        DeviceConfig.addOnPropertiesChangedListener("systemui", this.mContext.getMainExecutor(), this.mPropertiesChangedListener);
        new SettingsObserver(new Handler()).observe();
    }

    public void addCallback(StatusBar statusBar) {
        this.mStatusBar = statusBar;
    }

    class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            NotificationMediaManager.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("lockscreen_media_metadata"), false, this, -1);
            NotificationMediaManager.this.updateSettings();
        }

        public void onChange(boolean z) {
            NotificationMediaManager.this.updateSettings();
        }
    }

    /* access modifiers changed from: private */
    public void updateSettings() {
        boolean z = true;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "lockscreen_media_metadata", 1, -2) != 1) {
            z = false;
        }
        this.mShowMediaMetadata = z;
    }

    public static boolean isPlayingState(int i) {
        return !PAUSED_MEDIA_STATES.contains(Integer.valueOf(i));
    }

    public void setUpWithPresenter(NotificationPresenter notificationPresenter) {
        this.mPresenter = notificationPresenter;
    }

    public boolean isMediaPlayerNotification(NotificationEntry notificationEntry) {
        this.mMediaIsVisible = isPlayingState(getMediaControllerPlaybackState(this.mMediaController));
        return this.mShowMediaHeadsup && notificationEntry.notification.getKey().equals(this.mMediaNotificationKey) && this.mMediaIsVisible && !notificationEntry.notification.getPackageName().toLowerCase().equals(this.mTaskHelper.getForegroundApp());
    }

    public boolean isNewTrackNotification() {
        CharSequence charSequence;
        MediaMetadata mediaMetadata = this.mMediaMetadata;
        CharSequence charSequence2 = null;
        if (mediaMetadata != null) {
            charSequence = mediaMetadata.getText("android.media.metadata.TITLE");
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = this.mContext.getResources().getString(C1784R$string.music_controls_no_title);
            }
        } else {
            charSequence = null;
        }
        MediaMetadata mediaMetadata2 = this.mMediaMetadata;
        if (mediaMetadata2 != null) {
            charSequence2 = mediaMetadata2.getText("android.media.metadata.ARTIST");
        }
        if (TextUtils.equals(charSequence, this.mMediaTitle) && TextUtils.equals(charSequence2, this.mMediaArtist)) {
            return false;
        }
        this.mMediaTitle = charSequence;
        this.mMediaArtist = charSequence2;
        return this.mMediaIsVisible;
    }

    public void setMediaHeadsup() {
        boolean z = false;
        if (Settings.System.getIntForUser(this.mContext.getContentResolver(), "show_media_heads_up", 0, -2) != 0) {
            z = true;
        }
        this.mShowMediaHeadsup = z;
    }

    public void onNotificationRemoved(String str) {
        if (str.equals(this.mMediaNotificationKey)) {
            clearCurrentMediaNotification();
            dispatchUpdateMediaMetaData(true, true);
        }
        if (str.equals(this.mNowPlayingNotificationKey)) {
            this.mNowPlayingNotificationKey = null;
            dispatchUpdateMediaMetaData(true, true);
        }
    }

    /* access modifiers changed from: private */
    public void checkMediaNotificationColor(NotificationEntry notificationEntry) {
        if (notificationEntry.key.equals(this.mMediaNotificationKey)) {
            ArrayList arrayList = new ArrayList(this.mMediaListeners);
            for (int i = 0; i < arrayList.size(); i++) {
                ((MediaListener) arrayList.get(i)).setMediaNotificationColor(notificationEntry.notification.getNotification().isColorizedMedia(), notificationEntry.getRow().getCurrentBackgroundTint());
            }
        }
    }

    public String getMediaNotificationKey() {
        return this.mMediaNotificationKey;
    }

    public MediaMetadata getMediaMetadata() {
        return this.mMediaMetadata;
    }

    public boolean getShowCompactMediaSeekbar() {
        return this.mShowCompactMediaSeekbar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.drawable.Icon getMediaIcon() {
        /*
            r3 = this;
            java.lang.String r0 = r3.mMediaNotificationKey
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            com.android.systemui.statusbar.notification.NotificationEntryManager r0 = r3.mEntryManager
            com.android.systemui.statusbar.notification.collection.NotificationData r0 = r0.getNotificationData()
            monitor-enter(r0)
            com.android.systemui.statusbar.notification.NotificationEntryManager r2 = r3.mEntryManager     // Catch:{ all -> 0x002a }
            com.android.systemui.statusbar.notification.collection.NotificationData r2 = r2.getNotificationData()     // Catch:{ all -> 0x002a }
            java.lang.String r3 = r3.mMediaNotificationKey     // Catch:{ all -> 0x002a }
            com.android.systemui.statusbar.notification.collection.NotificationEntry r3 = r2.get(r3)     // Catch:{ all -> 0x002a }
            if (r3 == 0) goto L_0x0028
            com.android.systemui.statusbar.StatusBarIconView r2 = r3.expandedIcon     // Catch:{ all -> 0x002a }
            if (r2 != 0) goto L_0x0020
            goto L_0x0028
        L_0x0020:
            com.android.systemui.statusbar.StatusBarIconView r3 = r3.expandedIcon     // Catch:{ all -> 0x002a }
            android.graphics.drawable.Icon r3 = r3.getSourceIcon()     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return r3
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return r1
        L_0x002a:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.NotificationMediaManager.getMediaIcon():android.graphics.drawable.Icon");
    }

    public void addCallback(MediaListener mediaListener) {
        this.mMediaListeners.add(mediaListener);
        mediaListener.onMetadataOrStateChanged(this.mMediaMetadata, getMediaControllerPlaybackState(this.mMediaController));
    }

    public void findAndUpdateMediaNotifications() {
        boolean z;
        NotificationEntry notificationEntry;
        MediaController mediaController;
        MediaSession.Token token;
        synchronized (this.mEntryManager.getNotificationData()) {
            ArrayList<NotificationEntry> activeNotifications = this.mEntryManager.getNotificationData().getActiveNotifications();
            int size = activeNotifications.size();
            z = false;
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                NotificationEntry notificationEntry2 = activeNotifications.get(i);
                if (notificationEntry2.notification.getPackageName().toLowerCase().equals("com.google.intelligence.sense")) {
                    this.mNowPlayingNotificationKey = notificationEntry2.notification.getKey();
                    String string = notificationEntry2.notification.getNotification().extras.getString("android.title");
                    if (!TextUtils.isEmpty(string)) {
                        this.mNowPlayingTrack = string;
                    }
                } else {
                    i++;
                }
            }
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    notificationEntry = null;
                    mediaController = null;
                    break;
                }
                notificationEntry = activeNotifications.get(i2);
                if (notificationEntry.isMediaNotification() && (token = (MediaSession.Token) notificationEntry.notification.getNotification().extras.getParcelable("android.mediaSession")) != null) {
                    mediaController = new MediaController(this.mContext, token);
                    if (3 == getMediaControllerPlaybackState(mediaController)) {
                        break;
                    }
                }
                i2++;
            }
            if (notificationEntry == null && this.mMediaSessionManager != null) {
                for (MediaController mediaController2 : this.mMediaSessionManager.getActiveSessionsForUser((ComponentName) null, -1)) {
                    if (3 == getMediaControllerPlaybackState(mediaController2)) {
                        String packageName = mediaController2.getPackageName();
                        int i3 = 0;
                        while (true) {
                            if (i3 >= size) {
                                break;
                            }
                            NotificationEntry notificationEntry3 = activeNotifications.get(i3);
                            if (notificationEntry3.notification.getPackageName().equals(packageName)) {
                                mediaController = mediaController2;
                                notificationEntry = notificationEntry3;
                                break;
                            }
                            i3++;
                        }
                    }
                }
            }
            if (mediaController != null && !sameSessions(this.mMediaController, mediaController)) {
                clearCurrentMediaNotificationSession();
                this.mMediaController = mediaController;
                this.mMediaController.registerCallback(this.mMediaListener);
                this.mMediaMetadata = this.mMediaController.getMetadata();
                z = true;
            }
            if (notificationEntry != null && !notificationEntry.notification.getKey().equals(this.mMediaNotificationKey)) {
                this.mMediaNotificationKey = notificationEntry.notification.getKey();
            }
        }
        if (z) {
            this.mEntryManager.updateNotifications();
        }
        dispatchUpdateMediaMetaData(z, true);
    }

    public void clearCurrentMediaNotification() {
        this.mMediaNotificationKey = null;
        clearCurrentMediaNotificationSession();
    }

    /* access modifiers changed from: private */
    public void dispatchUpdateMediaMetaData(boolean z, boolean z2) {
        NotificationPresenter notificationPresenter = this.mPresenter;
        if (notificationPresenter != null) {
            notificationPresenter.updateMediaMetaData(z, z2);
        }
        int mediaControllerPlaybackState = getMediaControllerPlaybackState(this.mMediaController);
        ArrayList arrayList = new ArrayList(this.mMediaListeners);
        for (int i = 0; i < arrayList.size(); i++) {
            ((MediaListener) arrayList.get(i)).onMetadataOrStateChanged(this.mMediaMetadata, mediaControllerPlaybackState);
        }
    }

    public String getNowPlayingTrack() {
        if (this.mNowPlayingNotificationKey == null) {
            this.mNowPlayingTrack = null;
        }
        return this.mNowPlayingTrack;
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print("    mMediaSessionManager=");
        printWriter.println(this.mMediaSessionManager);
        printWriter.print("    mMediaNotificationKey=");
        printWriter.println(this.mMediaNotificationKey);
        printWriter.print("    mMediaController=");
        printWriter.print(this.mMediaController);
        if (this.mMediaController != null) {
            printWriter.print(" state=" + this.mMediaController.getPlaybackState());
        }
        printWriter.println();
        printWriter.print("    mMediaMetadata=");
        printWriter.print(this.mMediaMetadata);
        if (this.mMediaMetadata != null) {
            printWriter.print(" title=" + this.mMediaMetadata.getText("android.media.metadata.TITLE"));
        }
        printWriter.println();
    }

    private boolean sameSessions(MediaController mediaController, MediaController mediaController2) {
        if (mediaController == mediaController2) {
            return true;
        }
        if (mediaController == null) {
            return false;
        }
        return mediaController.controlsSameSession(mediaController2);
    }

    private int getMediaControllerPlaybackState(MediaController mediaController) {
        PlaybackState playbackState;
        if (mediaController == null || (playbackState = mediaController.getPlaybackState()) == null) {
            return 0;
        }
        return playbackState.getState();
    }

    private void clearCurrentMediaNotificationSession() {
        this.mMediaArtworkProcessor.clearCache();
        this.mMediaMetadata = null;
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            mediaController.unregisterCallback(this.mMediaListener);
        }
        this.mMediaController = null;
    }

    public void setMediaPlaying() {
        if (this.mStatusBar != null) {
            MediaController mediaController = this.mMediaController;
            if (mediaController == null || 3 != getMediaControllerPlaybackState(mediaController)) {
                this.mStatusBar.resetTrackInfo();
                return;
            }
            ArrayList<NotificationEntry> activeNotifications = this.mEntryManager.getNotificationData().getActiveNotifications();
            int size = activeNotifications.size();
            String packageName = this.mMediaController.getPackageName();
            int i = 0;
            while (i < size) {
                NotificationEntry notificationEntry = activeNotifications.get(i);
                if (!notificationEntry.notification.getPackageName().equals(packageName)) {
                    i++;
                } else if (this.mStatusBar.mTickerMode == 1) {
                    this.mHandler.postDelayed(new Runnable(notificationEntry) {
                        private final /* synthetic */ NotificationEntry f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            NotificationMediaManager.this.lambda$setMediaPlaying$0$NotificationMediaManager(this.f$1);
                        }
                    }, 500);
                    return;
                } else {
                    return;
                }
            }
        }
    }

    public /* synthetic */ void lambda$setMediaPlaying$0$NotificationMediaManager(NotificationEntry notificationEntry) {
        this.mStatusBar.tick(notificationEntry.notification, true, true, this.mMediaMetadata, (String) null);
    }

    public void updateMediaMetaData(boolean z, boolean z2) {
        Bitmap bitmap;
        Trace.beginSection("StatusBar#updateMediaMetaData");
        setMediaPlaying();
        if (this.mBackdrop == null) {
            Trace.endSection();
            return;
        }
        BiometricUnlockController biometricUnlockController = this.mBiometricUnlockController;
        boolean z3 = biometricUnlockController != null && biometricUnlockController.isWakeAndUnlock();
        if (this.mKeyguardMonitor.isLaunchTransitionFadingAway() || z3) {
            this.mBackdrop.setVisibility(4);
            Trace.endSection();
            return;
        }
        MediaMetadata mediaMetadata = getMediaMetadata();
        if (mediaMetadata == null || this.mKeyguardBypassController.getBypassEnabled()) {
            bitmap = null;
        } else {
            Bitmap bitmap2 = mediaMetadata.getBitmap("android.media.metadata.ART");
            bitmap = bitmap2 == null ? mediaMetadata.getBitmap("android.media.metadata.ALBUM_ART") : bitmap2;
        }
        if (z) {
            for (AsyncTask<?, ?, ?> cancel : this.mProcessArtworkTasks) {
                cancel.cancel(true);
            }
            this.mProcessArtworkTasks.clear();
        }
        if (bitmap != null) {
            this.mProcessArtworkTasks.add(new ProcessArtworkTask(this, z, z2).execute(new Bitmap[]{bitmap}));
        } else {
            finishUpdateMediaMetaData(z, z2, (Bitmap) null);
        }
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x012d A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void finishUpdateMediaMetaData(boolean r11, boolean r12, android.graphics.Bitmap r13) {
        /*
            r10 = this;
            r0 = 0
            if (r13 == 0) goto L_0x0014
            boolean r1 = r10.mShowMediaMetadata
            if (r1 != 0) goto L_0x0008
            goto L_0x0014
        L_0x0008:
            android.graphics.drawable.BitmapDrawable r1 = new android.graphics.drawable.BitmapDrawable
            android.widget.ImageView r2 = r10.mBackdropBack
            android.content.res.Resources r2 = r2.getResources()
            r1.<init>(r2, r13)
            goto L_0x0015
        L_0x0014:
            r1 = r0
        L_0x0015:
            r13 = 1
            r2 = 0
            if (r1 == 0) goto L_0x001b
            r3 = r13
            goto L_0x001c
        L_0x001b:
            r3 = r2
        L_0x001c:
            if (r1 != 0) goto L_0x004a
            com.android.systemui.statusbar.phone.LockscreenWallpaper r4 = r10.mLockscreenWallpaper
            if (r4 == 0) goto L_0x0027
            android.graphics.Bitmap r4 = r4.getBitmap()
            goto L_0x0028
        L_0x0027:
            r4 = r0
        L_0x0028:
            if (r4 == 0) goto L_0x004a
            com.android.systemui.statusbar.phone.LockscreenWallpaper$WallpaperDrawable r1 = new com.android.systemui.statusbar.phone.LockscreenWallpaper$WallpaperDrawable
            android.widget.ImageView r5 = r10.mBackdropBack
            android.content.res.Resources r5 = r5.getResources()
            r1.<init>((android.content.res.Resources) r5, (android.graphics.Bitmap) r4)
            android.graphics.drawable.BitmapDrawable r5 = new android.graphics.drawable.BitmapDrawable
            android.widget.ImageView r6 = r10.mBackdropBack
            android.content.res.Resources r6 = r6.getResources()
            r5.<init>(r6, r4)
            com.android.systemui.plugins.statusbar.StatusBarStateController r4 = r10.mStatusBarStateController
            int r4 = r4.getState()
            if (r4 != r13) goto L_0x004a
            r4 = r13
            goto L_0x004b
        L_0x004a:
            r4 = r2
        L_0x004b:
            dagger.Lazy<com.android.systemui.statusbar.phone.ShadeController> r5 = r10.mShadeController
            java.lang.Object r5 = r5.get()
            com.android.systemui.statusbar.phone.ShadeController r5 = (com.android.systemui.statusbar.phone.ShadeController) r5
            dagger.Lazy<com.android.systemui.statusbar.phone.StatusBarWindowController> r6 = r10.mStatusBarWindowController
            java.lang.Object r6 = r6.get()
            com.android.systemui.statusbar.phone.StatusBarWindowController r6 = (com.android.systemui.statusbar.phone.StatusBarWindowController) r6
            if (r5 == 0) goto L_0x0065
            boolean r7 = r5.isOccluded()
            if (r7 == 0) goto L_0x0065
            r7 = r13
            goto L_0x0066
        L_0x0065:
            r7 = r2
        L_0x0066:
            if (r1 == 0) goto L_0x006a
            r8 = r13
            goto L_0x006b
        L_0x006a:
            r8 = r2
        L_0x006b:
            com.android.systemui.colorextraction.SysuiColorExtractor r9 = r10.mColorExtractor
            r9.setHasMediaArtwork(r3)
            com.android.systemui.statusbar.phone.ScrimController r3 = r10.mScrimController
            if (r3 == 0) goto L_0x0077
            r3.setHasBackdrop(r8)
        L_0x0077:
            r3 = 2
            r9 = 0
            if (r8 != 0) goto L_0x007d
            goto L_0x0123
        L_0x007d:
            com.android.systemui.plugins.statusbar.StatusBarStateController r8 = r10.mStatusBarStateController
            int r8 = r8.getState()
            if (r8 != 0) goto L_0x0087
            if (r4 == 0) goto L_0x0123
        L_0x0087:
            com.android.systemui.statusbar.phone.BiometricUnlockController r4 = r10.mBiometricUnlockController
            if (r4 == 0) goto L_0x0123
            int r4 = r4.getMode()
            if (r4 == r3) goto L_0x0123
            if (r7 != 0) goto L_0x0123
            com.android.systemui.statusbar.BackDropView r0 = r10.mBackdrop
            int r0 = r0.getVisibility()
            r3 = 1065353216(0x3f800000, float:1.0)
            if (r0 == 0) goto L_0x00c7
            com.android.systemui.statusbar.BackDropView r11 = r10.mBackdrop
            r11.setVisibility(r2)
            if (r12 == 0) goto L_0x00b3
            com.android.systemui.statusbar.BackDropView r11 = r10.mBackdrop
            r11.setAlpha(r9)
            com.android.systemui.statusbar.BackDropView r11 = r10.mBackdrop
            android.view.ViewPropertyAnimator r11 = r11.animate()
            r11.alpha(r3)
            goto L_0x00c1
        L_0x00b3:
            com.android.systemui.statusbar.BackDropView r11 = r10.mBackdrop
            android.view.ViewPropertyAnimator r11 = r11.animate()
            r11.cancel()
            com.android.systemui.statusbar.BackDropView r11 = r10.mBackdrop
            r11.setAlpha(r3)
        L_0x00c1:
            if (r6 == 0) goto L_0x00c6
            r6.setBackdropShowing(r13)
        L_0x00c6:
            r11 = r13
        L_0x00c7:
            if (r11 == 0) goto L_0x01b9
            android.widget.ImageView r11 = r10.mBackdropBack
            android.graphics.drawable.Drawable r11 = r11.getDrawable()
            if (r11 == 0) goto L_0x00f9
            android.widget.ImageView r11 = r10.mBackdropBack
            android.graphics.drawable.Drawable r11 = r11.getDrawable()
            android.graphics.drawable.Drawable$ConstantState r11 = r11.getConstantState()
            android.widget.ImageView r12 = r10.mBackdropFront
            android.content.res.Resources r12 = r12.getResources()
            android.graphics.drawable.Drawable r11 = r11.newDrawable(r12)
            android.graphics.drawable.Drawable r11 = r11.mutate()
            android.widget.ImageView r12 = r10.mBackdropFront
            r12.setImageDrawable(r11)
            android.widget.ImageView r11 = r10.mBackdropFront
            r11.setAlpha(r3)
            android.widget.ImageView r11 = r10.mBackdropFront
            r11.setVisibility(r2)
            goto L_0x00ff
        L_0x00f9:
            android.widget.ImageView r11 = r10.mBackdropFront
            r12 = 4
            r11.setVisibility(r12)
        L_0x00ff:
            android.widget.ImageView r11 = r10.mBackdropBack
            r11.setImageDrawable(r1)
            android.widget.ImageView r11 = r10.mBackdropFront
            int r11 = r11.getVisibility()
            if (r11 != 0) goto L_0x01b9
            android.widget.ImageView r11 = r10.mBackdropFront
            android.view.ViewPropertyAnimator r11 = r11.animate()
            r12 = 250(0xfa, double:1.235E-321)
            android.view.ViewPropertyAnimator r11 = r11.setDuration(r12)
            android.view.ViewPropertyAnimator r11 = r11.alpha(r9)
            java.lang.Runnable r10 = r10.mHideBackdropFront
            r11.withEndAction(r10)
            goto L_0x01b9
        L_0x0123:
            com.android.systemui.statusbar.BackDropView r11 = r10.mBackdrop
            int r11 = r11.getVisibility()
            r12 = 8
            if (r11 == r12) goto L_0x01b9
            if (r5 == 0) goto L_0x013e
            boolean r11 = r5.isDozing()
            if (r11 == 0) goto L_0x013e
            com.android.systemui.statusbar.phone.ScrimState r11 = com.android.systemui.statusbar.phone.ScrimState.AOD
            boolean r11 = r11.getAnimateChange()
            if (r11 != 0) goto L_0x013e
            goto L_0x013f
        L_0x013e:
            r13 = r2
        L_0x013f:
            com.android.systemui.statusbar.policy.KeyguardMonitor r11 = r10.mKeyguardMonitor
            boolean r11 = r11.isBypassFadingAnimation()
            com.android.systemui.statusbar.phone.BiometricUnlockController r1 = r10.mBiometricUnlockController
            if (r1 == 0) goto L_0x014f
            int r1 = r1.getMode()
            if (r1 == r3) goto L_0x0151
        L_0x014f:
            if (r13 == 0) goto L_0x0153
        L_0x0151:
            if (r11 == 0) goto L_0x0155
        L_0x0153:
            if (r7 == 0) goto L_0x0165
        L_0x0155:
            com.android.systemui.statusbar.BackDropView r11 = r10.mBackdrop
            r11.setVisibility(r12)
            android.widget.ImageView r10 = r10.mBackdropBack
            r10.setImageDrawable(r0)
            if (r6 == 0) goto L_0x01b9
            r6.setBackdropShowing(r2)
            goto L_0x01b9
        L_0x0165:
            if (r6 == 0) goto L_0x016a
            r6.setBackdropShowing(r2)
        L_0x016a:
            com.android.systemui.statusbar.BackDropView r11 = r10.mBackdrop
            android.view.ViewPropertyAnimator r11 = r11.animate()
            android.view.ViewPropertyAnimator r11 = r11.alpha(r9)
            android.view.animation.Interpolator r12 = com.android.systemui.Interpolators.ACCELERATE_DECELERATE
            android.view.ViewPropertyAnimator r11 = r11.setInterpolator(r12)
            r12 = 300(0x12c, double:1.48E-321)
            android.view.ViewPropertyAnimator r11 = r11.setDuration(r12)
            r12 = 0
            android.view.ViewPropertyAnimator r11 = r11.setStartDelay(r12)
            com.android.systemui.statusbar.-$$Lambda$NotificationMediaManager$baxuDHdU8GmGp76sIQzcyKI_8Ec r12 = new com.android.systemui.statusbar.-$$Lambda$NotificationMediaManager$baxuDHdU8GmGp76sIQzcyKI_8Ec
            r12.<init>()
            r11.withEndAction(r12)
            com.android.systemui.statusbar.policy.KeyguardMonitor r11 = r10.mKeyguardMonitor
            boolean r11 = r11.isKeyguardFadingAway()
            if (r11 == 0) goto L_0x01b9
            com.android.systemui.statusbar.BackDropView r11 = r10.mBackdrop
            android.view.ViewPropertyAnimator r11 = r11.animate()
            com.android.systemui.statusbar.policy.KeyguardMonitor r12 = r10.mKeyguardMonitor
            long r12 = r12.getShortenedFadingAwayDuration()
            android.view.ViewPropertyAnimator r11 = r11.setDuration(r12)
            com.android.systemui.statusbar.policy.KeyguardMonitor r10 = r10.mKeyguardMonitor
            long r12 = r10.getKeyguardFadingAwayDelay()
            android.view.ViewPropertyAnimator r10 = r11.setStartDelay(r12)
            android.view.animation.Interpolator r11 = com.android.systemui.Interpolators.LINEAR
            android.view.ViewPropertyAnimator r10 = r10.setInterpolator(r11)
            r10.start()
        L_0x01b9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.NotificationMediaManager.finishUpdateMediaMetaData(boolean, boolean, android.graphics.Bitmap):void");
    }

    public /* synthetic */ void lambda$finishUpdateMediaMetaData$1$NotificationMediaManager() {
        this.mBackdrop.setVisibility(8);
        this.mBackdropFront.animate().cancel();
        this.mBackdropBack.setImageDrawable((Drawable) null);
        this.mHandler.post(this.mHideBackdropFront);
    }

    public void setup(BackDropView backDropView, ImageView imageView, ImageView imageView2, ScrimController scrimController, LockscreenWallpaper lockscreenWallpaper) {
        this.mBackdrop = backDropView;
        this.mBackdropFront = imageView;
        this.mBackdropBack = imageView2;
        this.mScrimController = scrimController;
        this.mLockscreenWallpaper = lockscreenWallpaper;
    }

    public void setBiometricUnlockController(BiometricUnlockController biometricUnlockController) {
        this.mBiometricUnlockController = biometricUnlockController;
    }

    /* access modifiers changed from: private */
    public Bitmap processArtwork(Bitmap bitmap) {
        return this.mMediaArtworkProcessor.processArtwork(this.mContext, bitmap, this.mLockscreenMediaBlur);
    }

    public void setLockScreenMediaBlurLevel() {
        this.mLockscreenMediaBlur = ((float) Settings.System.getIntForUser(this.mContext.getContentResolver(), "lockscreen_media_blur", 100, -2)) / 4.0f;
    }

    /* access modifiers changed from: private */
    public void removeTask(AsyncTask<?, ?, ?> asyncTask) {
        this.mProcessArtworkTasks.remove(asyncTask);
    }

    private static final class ProcessArtworkTask extends AsyncTask<Bitmap, Void, Bitmap> {
        private final boolean mAllowEnterAnimation;
        private final WeakReference<NotificationMediaManager> mManagerRef;
        private final boolean mMetaDataChanged;

        ProcessArtworkTask(NotificationMediaManager notificationMediaManager, boolean z, boolean z2) {
            this.mManagerRef = new WeakReference<>(notificationMediaManager);
            this.mMetaDataChanged = z;
            this.mAllowEnterAnimation = z2;
        }

        /* access modifiers changed from: protected */
        public Bitmap doInBackground(Bitmap... bitmapArr) {
            NotificationMediaManager notificationMediaManager = (NotificationMediaManager) this.mManagerRef.get();
            if (notificationMediaManager == null || bitmapArr.length == 0 || isCancelled()) {
                return null;
            }
            return notificationMediaManager.processArtwork(bitmapArr[0]);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            NotificationMediaManager notificationMediaManager = (NotificationMediaManager) this.mManagerRef.get();
            if (notificationMediaManager != null && !isCancelled()) {
                notificationMediaManager.removeTask(this);
                notificationMediaManager.finishUpdateMediaMetaData(this.mMetaDataChanged, this.mAllowEnterAnimation, bitmap);
            }
        }

        /* access modifiers changed from: protected */
        public void onCancelled(Bitmap bitmap) {
            if (bitmap != null) {
                bitmap.recycle();
            }
            NotificationMediaManager notificationMediaManager = (NotificationMediaManager) this.mManagerRef.get();
            if (notificationMediaManager != null) {
                notificationMediaManager.removeTask(this);
            }
        }
    }

    private void triggerKeyEvents(int i, final MediaController mediaController) {
        long uptimeMillis = SystemClock.uptimeMillis();
        final KeyEvent keyEvent = new KeyEvent(uptimeMillis, uptimeMillis, 0, i, 0);
        final KeyEvent changeAction = KeyEvent.changeAction(keyEvent, 1);
        this.mHandler.post(new Runnable() {
            public void run() {
                mediaController.dispatchMediaButtonEvent(keyEvent);
            }
        });
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                mediaController.dispatchMediaButtonEvent(changeAction);
            }
        }, 20);
    }

    public void onSkipTrackEvent(int i) {
        MediaSessionManager mediaSessionManager = this.mMediaSessionManager;
        if (mediaSessionManager != null) {
            for (MediaController mediaController : mediaSessionManager.getActiveSessionsForUser((ComponentName) null, -1)) {
                if (3 == getMediaControllerPlaybackState(mediaController)) {
                    triggerKeyEvents(i, mediaController);
                    return;
                }
            }
        }
    }
}
