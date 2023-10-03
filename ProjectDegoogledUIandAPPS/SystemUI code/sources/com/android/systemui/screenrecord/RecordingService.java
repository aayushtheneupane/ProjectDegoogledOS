package com.android.systemui.screenrecord;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Icon;
import android.hardware.display.VirtualDisplay;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaMuxer;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1779R$layout;
import com.android.systemui.C1784R$string;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordingService extends Service {
    private static int AUDIO_CHANNEL_TYPE = 16;
    private static int TOTAL_NUM_TRACKS = 1;
    private static int VIDEO_BIT_RATE;
    private static int VIDEO_FRAME_RATE;
    /* access modifiers changed from: private */
    public int audioTrackIndex = -1;
    private int mAudioBufferBytes;
    /* access modifiers changed from: private */
    public MediaCodec mAudioEncoder;
    /* access modifiers changed from: private */
    public final Object mAudioEncoderLock = new Object();
    /* access modifiers changed from: private */
    public boolean mAudioEncoding;
    /* access modifiers changed from: private */
    public boolean mAudioRecording;
    private int mAudioSourceOpt;
    private boolean mDotShowing;
    /* access modifiers changed from: private */
    public FrameLayout mFrameLayout;
    private LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public Surface mInputSurface;
    /* access modifiers changed from: private */
    public AudioRecord mInternalAudio;
    /* access modifiers changed from: private */
    public boolean mIsDotAtRight;
    private boolean mIsLowRamEnabled;
    /* access modifiers changed from: private */
    public MediaProjection mMediaProjection;
    private MediaProjectionManager mMediaProjectionManager;
    private MediaRecorder mMediaRecorder;
    /* access modifiers changed from: private */
    public MediaMuxer mMuxer;
    /* access modifiers changed from: private */
    public final Object mMuxerLock = new Object();
    /* access modifiers changed from: private */
    public boolean mMuxerStarted = false;
    /* access modifiers changed from: private */
    public boolean mPausedRecording = false;
    private Notification.Builder mRecordingNotificationBuilder;
    private boolean mShowDot;
    private boolean mShowTaps;
    private File mTempFile;
    private int mVideoBitrateOpt;
    private MediaCodec.BufferInfo mVideoBufferInfo;
    /* access modifiers changed from: private */
    public MediaCodec mVideoEncoder;
    /* access modifiers changed from: private */
    public boolean mVideoEncoding;
    private VirtualDisplay mVirtualDisplay;
    /* access modifiers changed from: private */
    public WindowManager mWindowManager;
    /* access modifiers changed from: private */
    public final Object mWriteAudioLock = new Object();
    /* access modifiers changed from: private */
    public final Object mWriteVideoLock = new Object();
    /* access modifiers changed from: private */
    public int videoTrackIndex = -1;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public static Intent getStartIntent(Context context, int i, Intent intent, int i2, boolean z, boolean z2, int i3) {
        return new Intent(context, RecordingService.class).setAction("com.android.systemui.screenrecord.START").putExtra("extra_resultCode", i).putExtra("extra_data", intent).putExtra("extra_audioSource", i2).putExtra("extra_showTaps", z).putExtra("extra_showDot", z2).putExtra("extra_videoBitrate", i3);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onStartCommand(android.content.Intent r7, int r8, int r9) {
        /*
            r6 = this;
            r8 = 2
            if (r7 != 0) goto L_0x0004
            return r8
        L_0x0004:
            java.lang.String r9 = r7.getAction()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onStartCommand "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "RecordingService"
            android.util.Log.d(r1, r0)
            java.lang.String r0 = "notification"
            java.lang.Object r0 = r6.getSystemService(r0)
            android.app.NotificationManager r0 = (android.app.NotificationManager) r0
            r2 = -1
            int r3 = r9.hashCode()
            r4 = 0
            r5 = 1
            switch(r3) {
                case -1691100604: goto L_0x006d;
                case -1688140755: goto L_0x0063;
                case -1687783248: goto L_0x0059;
                case -1256913972: goto L_0x004f;
                case -1224647939: goto L_0x0045;
                case -823616129: goto L_0x003b;
                case -470086188: goto L_0x0031;
                default: goto L_0x0030;
            }
        L_0x0030:
            goto L_0x0077
        L_0x0031:
            java.lang.String r3 = "com.android.systemui.screenrecord.STOP"
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L_0x0077
            r9 = r8
            goto L_0x0078
        L_0x003b:
            java.lang.String r3 = "com.android.systemui.screenrecord.RESUME"
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L_0x0077
            r9 = 4
            goto L_0x0078
        L_0x0045:
            java.lang.String r3 = "com.android.systemui.screenrecord.DELETE"
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L_0x0077
            r9 = 6
            goto L_0x0078
        L_0x004f:
            java.lang.String r3 = "com.android.systemui.screenrecord.CANCEL"
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L_0x0077
            r9 = r5
            goto L_0x0078
        L_0x0059:
            java.lang.String r3 = "com.android.systemui.screenrecord.START"
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L_0x0077
            r9 = r4
            goto L_0x0078
        L_0x0063:
            java.lang.String r3 = "com.android.systemui.screenrecord.SHARE"
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L_0x0077
            r9 = 5
            goto L_0x0078
        L_0x006d:
            java.lang.String r3 = "com.android.systemui.screenrecord.PAUSE"
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L_0x0077
            r9 = 3
            goto L_0x0078
        L_0x0077:
            r9 = r2
        L_0x0078:
            java.lang.String r2 = "extra_path"
            java.lang.String r3 = "android.intent.action.CLOSE_SYSTEM_DIALOGS"
            switch(r9) {
                case 0: goto L_0x0150;
                case 1: goto L_0x0124;
                case 2: goto L_0x011c;
                case 3: goto L_0x010b;
                case 4: goto L_0x00fa;
                case 5: goto L_0x00bb;
                case 6: goto L_0x0081;
                default: goto L_0x007f;
            }
        L_0x007f:
            goto L_0x019b
        L_0x0081:
            android.content.Intent r8 = new android.content.Intent
            r8.<init>(r3)
            r6.sendBroadcast(r8)
            android.content.ContentResolver r8 = r6.getContentResolver()
            java.lang.String r7 = r7.getStringExtra(r2)
            android.net.Uri r7 = android.net.Uri.parse(r7)
            r9 = 0
            r8.delete(r7, r9, r9)
            int r8 = com.android.systemui.C1784R$string.screenrecord_delete_description
            android.widget.Toast r6 = android.widget.Toast.makeText(r6, r8, r5)
            r6.show()
            r0.cancel(r5)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "Deleted recording "
            r6.append(r8)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            android.util.Log.d(r1, r6)
            goto L_0x019b
        L_0x00bb:
            java.lang.String r7 = r7.getStringExtra(r2)
            android.net.Uri r7 = android.net.Uri.parse(r7)
            android.content.Intent r8 = new android.content.Intent
            java.lang.String r9 = "android.intent.action.SEND"
            r8.<init>(r9)
            java.lang.String r9 = "video/mp4"
            android.content.Intent r8 = r8.setType(r9)
            java.lang.String r9 = "android.intent.extra.STREAM"
            android.content.Intent r7 = r8.putExtra(r9, r7)
            android.content.res.Resources r8 = r6.getResources()
            int r9 = com.android.systemui.C1784R$string.screenrecord_share_label
            java.lang.String r8 = r8.getString(r9)
            android.content.Intent r9 = new android.content.Intent
            r9.<init>(r3)
            r6.sendBroadcast(r9)
            r0.cancel(r5)
            android.content.Intent r7 = android.content.Intent.createChooser(r7, r8)
            r8 = 268435456(0x10000000, float:2.5243549E-29)
            android.content.Intent r7 = r7.setFlags(r8)
            r6.startActivity(r7)
            goto L_0x019b
        L_0x00fa:
            int r7 = r6.mAudioSourceOpt
            if (r7 == r5) goto L_0x0104
            android.media.MediaRecorder r7 = r6.mMediaRecorder
            r7.resume()
            goto L_0x0106
        L_0x0104:
            r6.mPausedRecording = r4
        L_0x0106:
            r6.setNotificationActions(r4, r0)
            goto L_0x019b
        L_0x010b:
            int r7 = r6.mAudioSourceOpt
            if (r7 == r5) goto L_0x0115
            android.media.MediaRecorder r7 = r6.mMediaRecorder
            r7.pause()
            goto L_0x0117
        L_0x0115:
            r6.mPausedRecording = r5
        L_0x0117:
            r6.setNotificationActions(r5, r0)
            goto L_0x019b
        L_0x011c:
            r6.stopRecording()
            r6.saveRecording(r0)
            goto L_0x019b
        L_0x0124:
            r6.stopRecording()
            java.io.File r7 = r6.mTempFile
            boolean r7 = r7.delete()
            if (r7 != 0) goto L_0x013e
            java.lang.String r7 = "Error canceling screen recording!"
            android.util.Log.e(r1, r7)
            int r7 = com.android.systemui.C1784R$string.screenrecord_delete_error
            android.widget.Toast r7 = android.widget.Toast.makeText(r6, r7, r5)
            r7.show()
            goto L_0x0147
        L_0x013e:
            int r7 = com.android.systemui.C1784R$string.screenrecord_cancel_success
            android.widget.Toast r7 = android.widget.Toast.makeText(r6, r7, r5)
            r7.show()
        L_0x0147:
            android.content.Intent r7 = new android.content.Intent
            r7.<init>(r3)
            r6.sendBroadcast(r7)
            goto L_0x019b
        L_0x0150:
            java.lang.String r9 = "extra_resultCode"
            int r9 = r7.getIntExtra(r9, r4)
            java.lang.String r0 = "extra_audioSource"
            int r0 = r7.getIntExtra(r0, r4)
            r6.mAudioSourceOpt = r0
            java.lang.String r0 = "extra_showTaps"
            boolean r0 = r7.getBooleanExtra(r0, r4)
            r6.mShowTaps = r0
            java.lang.String r0 = "extra_showDot"
            boolean r0 = r7.getBooleanExtra(r0, r4)
            r6.mShowDot = r0
            java.lang.String r0 = "extra_videoBitrate"
            int r8 = r7.getIntExtra(r0, r8)
            r6.mVideoBitrateOpt = r8
            java.lang.String r8 = "extra_data"
            android.os.Parcelable r7 = r7.getParcelableExtra(r8)
            android.content.Intent r7 = (android.content.Intent) r7
            if (r7 == 0) goto L_0x019b
            android.media.projection.MediaProjectionManager r8 = r6.mMediaProjectionManager
            android.media.projection.MediaProjection r7 = r8.getMediaProjection(r9, r7)
            r6.mMediaProjection = r7
            android.os.Handler r7 = new android.os.Handler
            android.os.Looper r8 = android.os.Looper.getMainLooper()
            r7.<init>(r8)
            com.android.systemui.screenrecord.-$$Lambda$RecordingService$Yz5PM_sAa2AIwYONiQ9XEYrEDjo r8 = new com.android.systemui.screenrecord.-$$Lambda$RecordingService$Yz5PM_sAa2AIwYONiQ9XEYrEDjo
            r8.<init>()
            r0 = 500(0x1f4, double:2.47E-321)
            r7.postDelayed(r8, r0)
        L_0x019b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenrecord.RecordingService.onStartCommand(android.content.Intent, int, int):int");
    }

    public void onCreate() {
        super.onCreate();
        this.mInflater = (LayoutInflater) getSystemService("layout_inflater");
        this.mMediaProjectionManager = (MediaProjectionManager) getSystemService("media_projection");
        this.mWindowManager = (WindowManager) getSystemService("window");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0115 A[Catch:{ IOException -> 0x02c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x017c A[Catch:{ IOException -> 0x02c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x027e A[Catch:{ IOException -> 0x02c6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0284 A[Catch:{ IOException -> 0x02c6 }] */
    /* renamed from: startRecording */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void lambda$onStartCommand$0$RecordingService() {
        /*
            r18 = this;
            r1 = r18
            java.lang.String r2 = "audio/mp4a-latm"
            java.lang.String r3 = "bitrate"
            java.lang.String r4 = "RecordingService"
            java.lang.String r0 = "temp"
            java.lang.String r5 = ".mp4"
            java.io.File r0 = java.io.File.createTempFile(r0, r5)     // Catch:{ IOException -> 0x002d }
            r1.mTempFile = r0     // Catch:{ IOException -> 0x002d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x002d }
            r0.<init>()     // Catch:{ IOException -> 0x002d }
            java.lang.String r5 = "Writing video output to: "
            r0.append(r5)     // Catch:{ IOException -> 0x002d }
            java.io.File r5 = r1.mTempFile     // Catch:{ IOException -> 0x002d }
            java.lang.String r5 = r5.getAbsolutePath()     // Catch:{ IOException -> 0x002d }
            r0.append(r5)     // Catch:{ IOException -> 0x002d }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x002d }
            android.util.Log.d(r4, r0)     // Catch:{ IOException -> 0x002d }
            goto L_0x0031
        L_0x002d:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ IOException -> 0x02c6 }
        L_0x0031:
            android.content.res.Resources r0 = r18.getResources()     // Catch:{ IOException -> 0x02c6 }
            int r5 = com.android.systemui.C1773R$bool.config_useNewScreenRecEncoder     // Catch:{ IOException -> 0x02c6 }
            boolean r0 = r0.getBoolean(r5)     // Catch:{ IOException -> 0x02c6 }
            android.content.Context r5 = r18.getApplicationContext()     // Catch:{ IOException -> 0x02c6 }
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r6 = "peak_refresh_rate"
            android.content.Context r7 = r18.getApplicationContext()     // Catch:{ IOException -> 0x02c6 }
            android.content.res.Resources r7 = r7.getResources()     // Catch:{ IOException -> 0x02c6 }
            r8 = 17694784(0x10e0040, float:2.608146E-38)
            int r7 = r7.getInteger(r8)     // Catch:{ IOException -> 0x02c6 }
            int r5 = android.provider.Settings.System.getInt(r5, r6, r7)     // Catch:{ IOException -> 0x02c6 }
            android.util.DisplayMetrics r6 = new android.util.DisplayMetrics     // Catch:{ IOException -> 0x02c6 }
            r6.<init>()     // Catch:{ IOException -> 0x02c6 }
            android.view.WindowManager r7 = r1.mWindowManager     // Catch:{ IOException -> 0x02c6 }
            android.view.Display r7 = r7.getDefaultDisplay()     // Catch:{ IOException -> 0x02c6 }
            r7.getRealMetrics(r6)     // Catch:{ IOException -> 0x02c6 }
            int r10 = r6.widthPixels     // Catch:{ IOException -> 0x02c6 }
            int r11 = r6.heightPixels     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r7 = "ro.config.low_ram"
            java.lang.String r7 = android.os.SystemProperties.get(r7)     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r8 = "true"
            boolean r7 = r7.equals(r8)     // Catch:{ IOException -> 0x02c6 }
            r1.mIsLowRamEnabled = r7     // Catch:{ IOException -> 0x02c6 }
            int r7 = r1.mVideoBitrateOpt     // Catch:{ IOException -> 0x02c6 }
            r8 = 10485760(0xa00000, float:1.469368E-38)
            r9 = 3
            r12 = 2
            r15 = 1
            if (r7 == 0) goto L_0x00b6
            if (r7 == r15) goto L_0x00aa
            if (r7 == r12) goto L_0x00a1
            if (r7 == r9) goto L_0x0095
            r8 = 4
            if (r7 == r8) goto L_0x0090
            r7 = 6000000(0x5b8d80, float:8.407791E-39)
            VIDEO_BIT_RATE = r7     // Catch:{ IOException -> 0x02c6 }
            goto L_0x00bf
        L_0x0090:
            r7 = 1048576(0x100000, float:1.469368E-39)
            VIDEO_BIT_RATE = r7     // Catch:{ IOException -> 0x02c6 }
            goto L_0x00bf
        L_0x0095:
            boolean r7 = r1.mIsLowRamEnabled     // Catch:{ IOException -> 0x02c6 }
            if (r7 == 0) goto L_0x009c
            r7 = 4194304(0x400000, float:5.877472E-39)
            goto L_0x009e
        L_0x009c:
            r7 = 5242880(0x500000, float:7.34684E-39)
        L_0x009e:
            VIDEO_BIT_RATE = r7     // Catch:{ IOException -> 0x02c6 }
            goto L_0x00bf
        L_0x00a1:
            boolean r7 = r1.mIsLowRamEnabled     // Catch:{ IOException -> 0x02c6 }
            if (r7 == 0) goto L_0x00a7
            r8 = 6291456(0x600000, float:8.816208E-39)
        L_0x00a7:
            VIDEO_BIT_RATE = r8     // Catch:{ IOException -> 0x02c6 }
            goto L_0x00bf
        L_0x00aa:
            boolean r7 = r1.mIsLowRamEnabled     // Catch:{ IOException -> 0x02c6 }
            if (r7 == 0) goto L_0x00b1
            r7 = 8388608(0x800000, float:1.17549435E-38)
            goto L_0x00b3
        L_0x00b1:
            r7 = 15728640(0xf00000, float:2.2040519E-38)
        L_0x00b3:
            VIDEO_BIT_RATE = r7     // Catch:{ IOException -> 0x02c6 }
            goto L_0x00bf
        L_0x00b6:
            boolean r7 = r1.mIsLowRamEnabled     // Catch:{ IOException -> 0x02c6 }
            if (r7 == 0) goto L_0x00bb
            goto L_0x00bd
        L_0x00bb:
            r8 = 20971520(0x1400000, float:3.526483E-38)
        L_0x00bd:
            VIDEO_BIT_RATE = r8     // Catch:{ IOException -> 0x02c6 }
        L_0x00bf:
            int r7 = r1.mVideoBitrateOpt     // Catch:{ IOException -> 0x02c6 }
            if (r7 <= r12) goto L_0x00d6
            boolean r7 = r1.mIsLowRamEnabled     // Catch:{ IOException -> 0x02c6 }
            if (r7 == 0) goto L_0x00c9
            r5 = 30
        L_0x00c9:
            VIDEO_FRAME_RATE = r5     // Catch:{ IOException -> 0x02c6 }
            boolean r5 = r1.mIsLowRamEnabled     // Catch:{ IOException -> 0x02c6 }
            if (r5 != 0) goto L_0x00f4
            TOTAL_NUM_TRACKS = r12     // Catch:{ IOException -> 0x02c6 }
            r5 = 12
            AUDIO_CHANNEL_TYPE = r5     // Catch:{ IOException -> 0x02c6 }
            goto L_0x00f4
        L_0x00d6:
            double r7 = (double) r5     // Catch:{ IOException -> 0x02c6 }
            r13 = 4605380978949069210(0x3fe999999999999a, double:0.8)
            double r7 = r7 * r13
            long r7 = java.lang.Math.round(r7)     // Catch:{ IOException -> 0x02c6 }
            int r5 = (int) r7     // Catch:{ IOException -> 0x02c6 }
            boolean r7 = r1.mIsLowRamEnabled     // Catch:{ IOException -> 0x02c6 }
            if (r7 == 0) goto L_0x00e8
            r5 = 25
        L_0x00e8:
            VIDEO_FRAME_RATE = r5     // Catch:{ IOException -> 0x02c6 }
            boolean r5 = r1.mIsLowRamEnabled     // Catch:{ IOException -> 0x02c6 }
            if (r5 != 0) goto L_0x00f4
            TOTAL_NUM_TRACKS = r15     // Catch:{ IOException -> 0x02c6 }
            r5 = 16
            AUDIO_CHANNEL_TYPE = r5     // Catch:{ IOException -> 0x02c6 }
        L_0x00f4:
            boolean r5 = r1.mShowTaps     // Catch:{ IOException -> 0x02c6 }
            r1.setTapsVisible(r5)     // Catch:{ IOException -> 0x02c6 }
            boolean r5 = r1.mShowDot     // Catch:{ IOException -> 0x02c6 }
            if (r5 == 0) goto L_0x0100
            r18.showDot()     // Catch:{ IOException -> 0x02c6 }
        L_0x0100:
            boolean r5 = r1.mIsLowRamEnabled     // Catch:{ IOException -> 0x02c6 }
            if (r5 != 0) goto L_0x0109
            if (r0 != 0) goto L_0x0107
            goto L_0x0109
        L_0x0107:
            r0 = 0
            goto L_0x010a
        L_0x0109:
            r0 = r15
        L_0x010a:
            int r5 = r1.mAudioSourceOpt     // Catch:{ IOException -> 0x02c6 }
            r8 = 128000(0x1f400, float:1.79366E-40)
            r13 = 44100(0xac44, float:6.1797E-41)
            r14 = 0
            if (r5 == r15) goto L_0x017c
            android.media.MediaRecorder r2 = new android.media.MediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r2.<init>()     // Catch:{ IOException -> 0x02c6 }
            r1.mMediaRecorder = r2     // Catch:{ IOException -> 0x02c6 }
            int r2 = r1.mAudioSourceOpt     // Catch:{ IOException -> 0x02c6 }
            if (r2 != r12) goto L_0x0125
            android.media.MediaRecorder r2 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r2.setAudioSource(r15)     // Catch:{ IOException -> 0x02c6 }
        L_0x0125:
            android.media.MediaRecorder r2 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r2.setVideoSource(r12)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r2 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r2.setOutputFormat(r12)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r2 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            if (r0 == 0) goto L_0x0135
            r0 = r12
            goto L_0x0136
        L_0x0135:
            r0 = 5
        L_0x0136:
            r2.setVideoEncoder(r0)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r0.setVideoSize(r10, r11)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            int r2 = VIDEO_FRAME_RATE     // Catch:{ IOException -> 0x02c6 }
            r0.setVideoFrameRate(r2)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            int r2 = VIDEO_BIT_RATE     // Catch:{ IOException -> 0x02c6 }
            r0.setVideoEncodingBitRate(r2)     // Catch:{ IOException -> 0x02c6 }
            int r0 = r1.mAudioSourceOpt     // Catch:{ IOException -> 0x02c6 }
            if (r0 != r12) goto L_0x0166
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r0.setAudioEncoder(r9)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            int r2 = TOTAL_NUM_TRACKS     // Catch:{ IOException -> 0x02c6 }
            r0.setAudioChannels(r2)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r0.setAudioEncodingBitRate(r8)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r0.setAudioSamplingRate(r13)     // Catch:{ IOException -> 0x02c6 }
        L_0x0166:
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            java.io.File r2 = r1.mTempFile     // Catch:{ IOException -> 0x02c6 }
            r0.setOutputFile(r2)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r0.prepare()     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            android.view.Surface r0 = r0.getSurface()     // Catch:{ IOException -> 0x02c6 }
            r1.mInputSurface = r0     // Catch:{ IOException -> 0x02c6 }
            goto L_0x0263
        L_0x017c:
            android.media.MediaCodec$BufferInfo r5 = new android.media.MediaCodec$BufferInfo     // Catch:{ IOException -> 0x02c6 }
            r5.<init>()     // Catch:{ IOException -> 0x02c6 }
            r1.mVideoBufferInfo = r5     // Catch:{ IOException -> 0x02c6 }
            int r5 = AUDIO_CHANNEL_TYPE     // Catch:{ IOException -> 0x02c6 }
            int r5 = android.media.AudioRecord.getMinBufferSize(r13, r5, r12)     // Catch:{ IOException -> 0x02c6 }
            r1.mAudioBufferBytes = r5     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r5 = "video/avc"
            java.lang.String r9 = "video/hevc"
            if (r0 == 0) goto L_0x0193
            r7 = r5
            goto L_0x0194
        L_0x0193:
            r7 = r9
        L_0x0194:
            android.media.MediaFormat r7 = android.media.MediaFormat.createVideoFormat(r7, r10, r11)     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r8 = "color-format"
            r12 = 2130708361(0x7f000789, float:1.701803E38)
            r7.setInteger(r8, r12)     // Catch:{ IOException -> 0x02c6 }
            int r8 = VIDEO_BIT_RATE     // Catch:{ IOException -> 0x02c6 }
            r7.setInteger(r3, r8)     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r8 = "frame-rate"
            int r12 = VIDEO_FRAME_RATE     // Catch:{ IOException -> 0x02c6 }
            r7.setInteger(r8, r12)     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r8 = "capture-rate"
            int r12 = VIDEO_FRAME_RATE     // Catch:{ IOException -> 0x02c6 }
            r7.setInteger(r8, r12)     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r8 = "repeat-previous-frame-after"
            r12 = 1000000(0xf4240, float:1.401298E-39)
            int r17 = VIDEO_FRAME_RATE     // Catch:{ IOException -> 0x02c6 }
            int r12 = r12 / r17
            r7.setInteger(r8, r12)     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r8 = "channel-count"
            r7.setInteger(r8, r15)     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r8 = "i-frame-interval"
            r7.setInteger(r8, r15)     // Catch:{ IOException -> 0x02c6 }
            if (r0 == 0) goto L_0x01cc
            goto L_0x01cd
        L_0x01cc:
            r5 = r9
        L_0x01cd:
            android.media.MediaCodec r0 = android.media.MediaCodec.createEncoderByType(r5)     // Catch:{ IOException -> 0x02c6 }
            r1.mVideoEncoder = r0     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaCodec r0 = r1.mVideoEncoder     // Catch:{ IOException -> 0x02c6 }
            r0.configure(r7, r14, r14, r15)     // Catch:{ IOException -> 0x02c6 }
            int r0 = TOTAL_NUM_TRACKS     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaFormat r0 = android.media.MediaFormat.createAudioFormat(r2, r13, r0)     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r5 = "aac-profile"
            r7 = 2
            r0.setInteger(r5, r7)     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r5 = "max-input-size"
            r7 = 16384(0x4000, float:2.2959E-41)
            r0.setInteger(r5, r7)     // Catch:{ IOException -> 0x02c6 }
            r5 = 128000(0x1f400, float:1.79366E-40)
            r0.setInteger(r3, r5)     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaCodec r2 = android.media.MediaCodec.createEncoderByType(r2)     // Catch:{ IOException -> 0x02c6 }
            r1.mAudioEncoder = r2     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaCodec r2 = r1.mAudioEncoder     // Catch:{ IOException -> 0x02c6 }
            r2.configure(r0, r14, r14, r15)     // Catch:{ IOException -> 0x02c6 }
            int r0 = VIDEO_FRAME_RATE     // Catch:{ IOException -> 0x02c6 }
            int r0 = r0 * 1024
            int r2 = r1.mAudioBufferBytes     // Catch:{ IOException -> 0x02c6 }
            if (r0 >= r2) goto L_0x0208
            int r0 = r1.mAudioBufferBytes     // Catch:{ IOException -> 0x02c6 }
            int r0 = r0 / 1024
        L_0x0208:
            android.media.MediaMuxer r0 = new android.media.MediaMuxer     // Catch:{ IOException -> 0x02c6 }
            java.io.File r2 = r1.mTempFile     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ IOException -> 0x02c6 }
            r3 = 0
            r0.<init>(r2, r3)     // Catch:{ IOException -> 0x02c6 }
            r1.mMuxer = r0     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioPlaybackCaptureConfiguration$Builder r0 = new android.media.AudioPlaybackCaptureConfiguration$Builder     // Catch:{ IOException -> 0x02c6 }
            android.media.projection.MediaProjection r2 = r1.mMediaProjection     // Catch:{ IOException -> 0x02c6 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioPlaybackCaptureConfiguration$Builder r0 = r0.addMatchingUsage(r15)     // Catch:{ IOException -> 0x02c6 }
            r2 = 0
            android.media.AudioPlaybackCaptureConfiguration$Builder r0 = r0.addMatchingUsage(r2)     // Catch:{ IOException -> 0x02c6 }
            r2 = 14
            android.media.AudioPlaybackCaptureConfiguration$Builder r0 = r0.addMatchingUsage(r2)     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioPlaybackCaptureConfiguration r0 = r0.build()     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioRecord$Builder r2 = new android.media.AudioRecord$Builder     // Catch:{ IOException -> 0x02c6 }
            r2.<init>()     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioFormat$Builder r3 = new android.media.AudioFormat$Builder     // Catch:{ IOException -> 0x02c6 }
            r3.<init>()     // Catch:{ IOException -> 0x02c6 }
            r5 = 2
            android.media.AudioFormat$Builder r3 = r3.setEncoding(r5)     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioFormat$Builder r3 = r3.setSampleRate(r13)     // Catch:{ IOException -> 0x02c6 }
            int r5 = AUDIO_CHANNEL_TYPE     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioFormat$Builder r3 = r3.setChannelMask(r5)     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioFormat r3 = r3.build()     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioRecord$Builder r2 = r2.setAudioFormat(r3)     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioRecord$Builder r0 = r2.setAudioPlaybackCaptureConfig(r0)     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioRecord r0 = r0.build()     // Catch:{ IOException -> 0x02c6 }
            r1.mInternalAudio = r0     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaCodec r0 = r1.mVideoEncoder     // Catch:{ IOException -> 0x02c6 }
            android.view.Surface r0 = r0.createInputSurface()     // Catch:{ IOException -> 0x02c6 }
            r1.mInputSurface = r0     // Catch:{ IOException -> 0x02c6 }
        L_0x0263:
            android.media.projection.MediaProjection r8 = r1.mMediaProjection     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r9 = "Recording Display"
            int r12 = r6.densityDpi     // Catch:{ IOException -> 0x02c6 }
            r13 = 16
            android.view.Surface r0 = r1.mInputSurface     // Catch:{ IOException -> 0x02c6 }
            r2 = 0
            r16 = 0
            r3 = r14
            r14 = r0
            r0 = r15
            r15 = r2
            android.hardware.display.VirtualDisplay r2 = r8.createVirtualDisplay(r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ IOException -> 0x02c6 }
            r1.mVirtualDisplay = r2     // Catch:{ IOException -> 0x02c6 }
            int r2 = r1.mAudioSourceOpt     // Catch:{ IOException -> 0x02c6 }
            if (r2 == r0) goto L_0x0284
            android.media.MediaRecorder r0 = r1.mMediaRecorder     // Catch:{ IOException -> 0x02c6 }
            r0.start()     // Catch:{ IOException -> 0x02c6 }
            goto L_0x02c2
        L_0x0284:
            android.media.MediaCodec r2 = r1.mVideoEncoder     // Catch:{ IOException -> 0x02c6 }
            r2.start()     // Catch:{ IOException -> 0x02c6 }
            java.lang.Thread r2 = new java.lang.Thread     // Catch:{ IOException -> 0x02c6 }
            com.android.systemui.screenrecord.RecordingService$VideoEncoderTask r5 = new com.android.systemui.screenrecord.RecordingService$VideoEncoderTask     // Catch:{ IOException -> 0x02c6 }
            r5.<init>()     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r6 = "VideoEncoderTask"
            r2.<init>(r5, r6)     // Catch:{ IOException -> 0x02c6 }
            r2.start()     // Catch:{ IOException -> 0x02c6 }
            android.media.MediaCodec r2 = r1.mAudioEncoder     // Catch:{ IOException -> 0x02c6 }
            r2.start()     // Catch:{ IOException -> 0x02c6 }
            java.lang.Thread r2 = new java.lang.Thread     // Catch:{ IOException -> 0x02c6 }
            com.android.systemui.screenrecord.RecordingService$AudioEncoderTask r5 = new com.android.systemui.screenrecord.RecordingService$AudioEncoderTask     // Catch:{ IOException -> 0x02c6 }
            r5.<init>()     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r6 = "AudioEncoderTask"
            r2.<init>(r5, r6)     // Catch:{ IOException -> 0x02c6 }
            r2.start()     // Catch:{ IOException -> 0x02c6 }
            android.media.AudioRecord r2 = r1.mInternalAudio     // Catch:{ IOException -> 0x02c6 }
            r2.startRecording()     // Catch:{ IOException -> 0x02c6 }
            r1.mAudioRecording = r0     // Catch:{ IOException -> 0x02c6 }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ IOException -> 0x02c6 }
            com.android.systemui.screenrecord.RecordingService$AudioRecorderTask r2 = new com.android.systemui.screenrecord.RecordingService$AudioRecorderTask     // Catch:{ IOException -> 0x02c6 }
            r2.<init>()     // Catch:{ IOException -> 0x02c6 }
            java.lang.String r3 = "AudioRecorderTask"
            r0.<init>(r2, r3)     // Catch:{ IOException -> 0x02c6 }
            r0.start()     // Catch:{ IOException -> 0x02c6 }
        L_0x02c2:
            r18.createRecordingNotification()
            return
        L_0x02c6:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Error starting screen recording: "
            r1.append(r2)
            java.lang.String r2 = r0.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r4, r1)
            r0.printStackTrace()
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenrecord.RecordingService.lambda$onStartCommand$0$RecordingService():void");
    }

    private void createRecordingNotification() {
        NotificationChannel notificationChannel = new NotificationChannel("screen_record", getString(C1784R$string.screenrecord_name), 2);
        notificationChannel.setDescription(getString(C1784R$string.screenrecord_channel_description));
        notificationChannel.enableVibration(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        notificationManager.createNotificationChannel(notificationChannel);
        this.mRecordingNotificationBuilder = new Notification.Builder(this, "screen_record").setSmallIcon(C1776R$drawable.ic_screenrecord).setContentTitle(getResources().getString(C1784R$string.screenrecord_name)).setUsesChronometer(true).setOngoing(true);
        setNotificationActions(false, notificationManager);
        startForeground(1, this.mRecordingNotificationBuilder.build());
    }

    private void setNotificationActions(boolean z, NotificationManager notificationManager) {
        int i;
        Resources resources = getResources();
        if (z) {
            i = C1784R$string.screenrecord_resume_label;
        } else {
            i = C1784R$string.screenrecord_pause_label;
        }
        this.mRecordingNotificationBuilder.setActions(new Notification.Action[]{new Notification.Action.Builder(Icon.createWithResource(this, C1776R$drawable.ic_screenrecord), getResources().getString(C1784R$string.screenrecord_stop_label), getStopPendingIntent()).build(), new Notification.Action.Builder(Icon.createWithResource(this, C1776R$drawable.ic_screenrecord), resources.getString(i), PendingIntent.getService(this, 2, z ? getResumeIntent(this) : getPauseIntent(this), 134217728)).build(), new Notification.Action.Builder(Icon.createWithResource(this, C1776R$drawable.ic_screenrecord), getResources().getString(C1784R$string.screenrecord_cancel_label), PendingIntent.getService(this, 2, getCancelIntent(this), 134217728)).build()});
        notificationManager.notify(1, this.mRecordingNotificationBuilder.build());
    }

    private Notification createSaveNotification(Uri uri) {
        Bitmap bitmap;
        Intent dataAndType = new Intent("android.intent.action.VIEW").setFlags(268435457).setDataAndType(uri, "video/mp4");
        Notification.Action build = new Notification.Action.Builder(Icon.createWithResource(this, C1776R$drawable.ic_screenrecord), getResources().getString(C1784R$string.screenrecord_share_label), PendingIntent.getService(this, 2, getShareIntent(this, uri.toString()), 134217728)).build();
        Notification.Builder autoCancel = new Notification.Builder(this, "screen_record").setSmallIcon(C1776R$drawable.ic_screenrecord).setContentTitle(getResources().getString(C1784R$string.screenrecord_name)).setContentText(getResources().getString(C1784R$string.screenrecord_save_message)).setContentIntent(PendingIntent.getActivity(this, 2, dataAndType, 67108864)).addAction(build).addAction(new Notification.Action.Builder(Icon.createWithResource(this, C1776R$drawable.ic_screenrecord), getResources().getString(C1784R$string.screenrecord_delete_label), PendingIntent.getService(this, 2, getDeleteIntent(this, uri.toString()), 134217728)).build()).setAutoCancel(true);
        try {
            bitmap = getContentResolver().loadThumbnail(uri, Point.convert(MediaStore.ThumbnailConstants.MINI_SIZE), (CancellationSignal) null);
        } catch (IOException e) {
            Log.e("RecordingService", "Error creating thumbnail: " + e.getMessage());
            e.printStackTrace();
            bitmap = null;
        }
        if (bitmap != null) {
            autoCancel.setLargeIcon(bitmap).setStyle(new Notification.BigPictureStyle().bigPicture(bitmap).bigLargeIcon((Bitmap) null));
        }
        return autoCancel.build();
    }

    private void stopRecording() {
        setTapsVisible(false);
        if (this.mDotShowing) {
            stopDot();
        }
        try {
            if (this.mAudioSourceOpt != 1) {
                this.mMediaRecorder.stop();
                this.mMediaRecorder.release();
                this.mMediaRecorder = null;
                this.mMediaProjection.stop();
                this.mMediaProjection = null;
                this.mInputSurface.release();
                this.mVirtualDisplay.release();
            } else {
                this.mAudioRecording = false;
                this.mAudioEncoding = false;
                this.mVideoEncoding = false;
            }
        } catch (Exception unused) {
        }
        stopSelf();
    }

    private void saveRecording(NotificationManager notificationManager) {
        String format = new SimpleDateFormat("'ScreenRecord-'yyyyMMdd-HHmmss'.mp4'").format(new Date());
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", format);
        contentValues.put("mime_type", "video/mp4");
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        ContentResolver contentResolver = getContentResolver();
        Uri insert = contentResolver.insert(MediaStore.Video.Media.getContentUri("external_primary"), contentValues);
        try {
            OutputStream openOutputStream = contentResolver.openOutputStream(insert, "w");
            Files.copy(this.mTempFile.toPath(), openOutputStream);
            openOutputStream.close();
            notificationManager.notify(1, createSaveNotification(insert));
            this.mTempFile.delete();
        } catch (IOException e) {
            Log.e("RecordingService", "Error saving screen recording: " + e.getMessage());
            Toast.makeText(this, C1784R$string.screenrecord_delete_error, 1).show();
        }
    }

    private void setTapsVisible(boolean z) {
        Settings.System.putInt(getApplicationContext().getContentResolver(), "show_touches", z ? 1 : 0);
    }

    private void showDot() {
        this.mDotShowing = true;
        this.mIsDotAtRight = true;
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1775R$dimen.screenrecord_dot_size);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2002, 40, -3);
        layoutParams.gravity = 53;
        layoutParams.width = dimensionPixelSize;
        layoutParams.height = dimensionPixelSize;
        this.mFrameLayout = new FrameLayout(this);
        this.mWindowManager.addView(this.mFrameLayout, layoutParams);
        this.mInflater.inflate(C1779R$layout.screenrecord_dot, this.mFrameLayout);
        final ImageView imageView = (ImageView) this.mFrameLayout.findViewById(C1777R$id.dot);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    RecordingService.this.getStopPendingIntent().send();
                } catch (PendingIntent.CanceledException unused) {
                }
            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                imageView.setAnimation((Animation) null);
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) RecordingService.this.mFrameLayout.getLayoutParams();
                layoutParams.gravity = (RecordingService.this.mIsDotAtRight ? 3 : 5) | 48;
                RecordingService recordingService = RecordingService.this;
                boolean unused = recordingService.mIsDotAtRight = !recordingService.mIsDotAtRight;
                RecordingService.this.mWindowManager.updateViewLayout(RecordingService.this.mFrameLayout, layoutParams);
                imageView.startAnimation(RecordingService.this.getDotAnimation());
                return true;
            }
        });
        imageView.startAnimation(getDotAnimation());
    }

    private void stopDot() {
        this.mDotShowing = false;
        ImageView imageView = (ImageView) this.mFrameLayout.findViewById(C1777R$id.dot);
        if (imageView != null) {
            imageView.setAnimation((Animation) null);
            this.mWindowManager.removeView(this.mFrameLayout);
        }
    }

    /* access modifiers changed from: private */
    public Animation getDotAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setStartOffset(100);
        alphaAnimation.setRepeatMode(2);
        alphaAnimation.setRepeatCount(-1);
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public PendingIntent getStopPendingIntent() {
        return PendingIntent.getService(this, 2, getStopIntent(this), 134217728);
    }

    private static Intent getStopIntent(Context context) {
        return new Intent(context, RecordingService.class).setAction("com.android.systemui.screenrecord.STOP");
    }

    private static Intent getPauseIntent(Context context) {
        return new Intent(context, RecordingService.class).setAction("com.android.systemui.screenrecord.PAUSE");
    }

    private static Intent getResumeIntent(Context context) {
        return new Intent(context, RecordingService.class).setAction("com.android.systemui.screenrecord.RESUME");
    }

    private static Intent getCancelIntent(Context context) {
        return new Intent(context, RecordingService.class).setAction("com.android.systemui.screenrecord.CANCEL");
    }

    private static Intent getShareIntent(Context context, String str) {
        return new Intent(context, RecordingService.class).setAction("com.android.systemui.screenrecord.SHARE").putExtra("extra_path", str);
    }

    private static Intent getDeleteIntent(Context context, String str) {
        return new Intent(context, RecordingService.class).setAction("com.android.systemui.screenrecord.DELETE").putExtra("extra_path", str);
    }

    private class AudioRecorderTask implements Runnable {
        ByteBuffer inputBuffer;
        int readResult;

        private AudioRecorderTask() {
        }

        public void run() {
            int dequeueInputBuffer;
            int dequeueInputBuffer2;
            byte[] bArr = new byte[1024];
            while (RecordingService.this.mAudioRecording) {
                if (!RecordingService.this.mPausedRecording) {
                    long nanoTime = System.nanoTime();
                    this.readResult = RecordingService.this.mInternalAudio.read(bArr, 0, 1024);
                    int i = this.readResult;
                    if (!(i == -2 || i == -3)) {
                        try {
                            synchronized (RecordingService.this.mAudioEncoderLock) {
                                if (RecordingService.this.mAudioEncoding && (dequeueInputBuffer2 = RecordingService.this.mAudioEncoder.dequeueInputBuffer(-1)) >= 0) {
                                    this.inputBuffer = RecordingService.this.mAudioEncoder.getInputBuffer(dequeueInputBuffer2);
                                    this.inputBuffer.clear();
                                    this.inputBuffer.put(bArr);
                                    RecordingService.this.mAudioEncoder.queueInputBuffer(dequeueInputBuffer2, 0, bArr.length, nanoTime / 1000, 0);
                                }
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }
            }
            long nanoTime2 = System.nanoTime();
            this.readResult = RecordingService.this.mInternalAudio.read(bArr, 0, 1024);
            int i2 = this.readResult;
            if (i2 == -2 || i2 == -3) {
                try {
                    synchronized (RecordingService.this.mAudioEncoderLock) {
                        if (RecordingService.this.mAudioEncoding && (dequeueInputBuffer = RecordingService.this.mAudioEncoder.dequeueInputBuffer(-1)) >= 0) {
                            this.inputBuffer = RecordingService.this.mAudioEncoder.getInputBuffer(dequeueInputBuffer);
                            this.inputBuffer.clear();
                            this.inputBuffer.put(bArr);
                            RecordingService.this.mAudioEncoder.queueInputBuffer(dequeueInputBuffer, 0, bArr.length, nanoTime2 / 1000, 4);
                        }
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            RecordingService.this.mInternalAudio.stop();
            RecordingService.this.mInternalAudio.release();
            AudioRecord unused = RecordingService.this.mInternalAudio = null;
        }
    }

    private class VideoEncoderTask implements Runnable {
        private MediaCodec.BufferInfo videoBufferInfo;

        private VideoEncoderTask() {
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(4:68|(2:70|71)|72|73) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:72:0x018a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r9 = this;
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                r1 = 1
                boolean unused = r0.mVideoEncoding = r1
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                r2 = -1
                int unused = r0.videoTrackIndex = r2
                android.media.MediaCodec$BufferInfo r0 = new android.media.MediaCodec$BufferInfo
                r0.<init>()
                r9.videoBufferInfo = r0
            L_0x0013:
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                boolean r0 = r0.mVideoEncoding
                r3 = 0
                if (r0 == 0) goto L_0x011f
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                boolean r0 = r0.mPausedRecording
                if (r0 != 0) goto L_0x0013
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                android.media.MediaCodec r0 = r0.mVideoEncoder
                android.media.MediaCodec$BufferInfo r4 = r9.videoBufferInfo
                r5 = 10
                int r0 = r0.dequeueOutputBuffer(r4, r5)
                if (r0 != r2) goto L_0x0035
                goto L_0x0013
            L_0x0035:
                r4 = -2
                if (r0 != r4) goto L_0x0093
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                int r0 = r0.videoTrackIndex
                if (r0 >= 0) goto L_0x008b
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                java.lang.Object r0 = r0.mMuxerLock
                monitor-enter(r0)
                com.android.systemui.screenrecord.RecordingService r3 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x0088 }
                com.android.systemui.screenrecord.RecordingService r4 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x0088 }
                android.media.MediaMuxer r4 = r4.mMuxer     // Catch:{ all -> 0x0088 }
                com.android.systemui.screenrecord.RecordingService r5 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x0088 }
                android.media.MediaCodec r5 = r5.mVideoEncoder     // Catch:{ all -> 0x0088 }
                android.media.MediaFormat r5 = r5.getOutputFormat()     // Catch:{ all -> 0x0088 }
                int r4 = r4.addTrack(r5)     // Catch:{ all -> 0x0088 }
                int unused = r3.videoTrackIndex = r4     // Catch:{ all -> 0x0088 }
                com.android.systemui.screenrecord.RecordingService r3 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x0088 }
                boolean r3 = r3.mMuxerStarted     // Catch:{ all -> 0x0088 }
                if (r3 != 0) goto L_0x0086
                com.android.systemui.screenrecord.RecordingService r3 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x0088 }
                int r3 = r3.videoTrackIndex     // Catch:{ all -> 0x0088 }
                if (r3 < 0) goto L_0x0086
                com.android.systemui.screenrecord.RecordingService r3 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x0088 }
                int r3 = r3.audioTrackIndex     // Catch:{ all -> 0x0088 }
                if (r3 < 0) goto L_0x0086
                com.android.systemui.screenrecord.RecordingService r3 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x0088 }
                android.media.MediaMuxer r3 = r3.mMuxer     // Catch:{ all -> 0x0088 }
                r3.start()     // Catch:{ all -> 0x0088 }
                com.android.systemui.screenrecord.RecordingService r3 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x0088 }
                boolean unused = r3.mMuxerStarted = r1     // Catch:{ all -> 0x0088 }
            L_0x0086:
                monitor-exit(r0)     // Catch:{ all -> 0x0088 }
                goto L_0x0013
            L_0x0088:
                r9 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0088 }
                throw r9
            L_0x008b:
                java.lang.RuntimeException r9 = new java.lang.RuntimeException
                java.lang.String r0 = "format changed twice"
                r9.<init>(r0)
                throw r9
            L_0x0093:
                if (r0 >= 0) goto L_0x0097
                goto L_0x0013
            L_0x0097:
                com.android.systemui.screenrecord.RecordingService r4 = com.android.systemui.screenrecord.RecordingService.this
                android.media.MediaCodec r4 = r4.mVideoEncoder
                java.nio.ByteBuffer r4 = r4.getOutputBuffer(r0)
                if (r4 == 0) goto L_0x0108
                android.media.MediaCodec$BufferInfo r5 = r9.videoBufferInfo
                int r6 = r5.flags
                r6 = r6 & 2
                if (r6 == 0) goto L_0x00ad
                r5.size = r3
            L_0x00ad:
                android.media.MediaCodec$BufferInfo r5 = r9.videoBufferInfo
                int r5 = r5.size
                if (r5 == 0) goto L_0x00f1
                com.android.systemui.screenrecord.RecordingService r5 = com.android.systemui.screenrecord.RecordingService.this
                boolean r5 = r5.mMuxerStarted
                if (r5 == 0) goto L_0x00f1
                android.media.MediaCodec$BufferInfo r5 = r9.videoBufferInfo
                int r5 = r5.offset
                r4.position(r5)
                android.media.MediaCodec$BufferInfo r5 = r9.videoBufferInfo
                int r6 = r5.offset
                int r5 = r5.size
                int r6 = r6 + r5
                r4.limit(r6)
                com.android.systemui.screenrecord.RecordingService r5 = com.android.systemui.screenrecord.RecordingService.this
                java.lang.Object r5 = r5.mWriteVideoLock
                monitor-enter(r5)
                com.android.systemui.screenrecord.RecordingService r6 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x00ee }
                boolean r6 = r6.mMuxerStarted     // Catch:{ all -> 0x00ee }
                if (r6 == 0) goto L_0x00ec
                com.android.systemui.screenrecord.RecordingService r6 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x00ee }
                android.media.MediaMuxer r6 = r6.mMuxer     // Catch:{ all -> 0x00ee }
                com.android.systemui.screenrecord.RecordingService r7 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x00ee }
                int r7 = r7.videoTrackIndex     // Catch:{ all -> 0x00ee }
                android.media.MediaCodec$BufferInfo r8 = r9.videoBufferInfo     // Catch:{ all -> 0x00ee }
                r6.writeSampleData(r7, r4, r8)     // Catch:{ all -> 0x00ee }
            L_0x00ec:
                monitor-exit(r5)     // Catch:{ all -> 0x00ee }
                goto L_0x00f1
            L_0x00ee:
                r9 = move-exception
                monitor-exit(r5)     // Catch:{ all -> 0x00ee }
                throw r9
            L_0x00f1:
                com.android.systemui.screenrecord.RecordingService r4 = com.android.systemui.screenrecord.RecordingService.this
                android.media.MediaCodec r4 = r4.mVideoEncoder
                r4.releaseOutputBuffer(r0, r3)
                android.media.MediaCodec$BufferInfo r0 = r9.videoBufferInfo
                int r0 = r0.flags
                r0 = r0 & 4
                if (r0 == 0) goto L_0x0013
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                boolean unused = r0.mVideoEncoding = r3
                goto L_0x011f
            L_0x0108:
                java.lang.RuntimeException r9 = new java.lang.RuntimeException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "couldn't fetch buffer at index "
                r1.append(r2)
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                r9.<init>(r0)
                throw r9
            L_0x011f:
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                android.media.MediaCodec r0 = r0.mVideoEncoder
                r0.stop()
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                android.media.MediaCodec r0 = r0.mVideoEncoder
                r0.release()
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                r1 = 0
                android.media.MediaCodec unused = r0.mVideoEncoder = r1
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                android.view.Surface r0 = r0.mInputSurface
                if (r0 == 0) goto L_0x014d
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                android.view.Surface r0 = r0.mInputSurface
                r0.release()
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                android.view.Surface unused = r0.mInputSurface = r1
            L_0x014d:
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                android.media.projection.MediaProjection r0 = r0.mMediaProjection
                if (r0 == 0) goto L_0x0163
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                android.media.projection.MediaProjection r0 = r0.mMediaProjection
                r0.stop()
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                android.media.projection.MediaProjection unused = r0.mMediaProjection = r1
            L_0x0163:
                com.android.systemui.screenrecord.RecordingService r0 = com.android.systemui.screenrecord.RecordingService.this
                java.lang.Object r0 = r0.mWriteAudioLock
                monitor-enter(r0)
                com.android.systemui.screenrecord.RecordingService r2 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x01a3 }
                java.lang.Object r2 = r2.mMuxerLock     // Catch:{ all -> 0x01a3 }
                monitor-enter(r2)     // Catch:{ all -> 0x01a3 }
                com.android.systemui.screenrecord.RecordingService r4 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x01a0 }
                android.media.MediaMuxer r4 = r4.mMuxer     // Catch:{ all -> 0x01a0 }
                if (r4 == 0) goto L_0x019d
                com.android.systemui.screenrecord.RecordingService r4 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x01a0 }
                boolean r4 = r4.mMuxerStarted     // Catch:{ all -> 0x01a0 }
                if (r4 == 0) goto L_0x018a
                com.android.systemui.screenrecord.RecordingService r4 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ IllegalStateException -> 0x018a }
                android.media.MediaMuxer r4 = r4.mMuxer     // Catch:{ IllegalStateException -> 0x018a }
                r4.stop()     // Catch:{ IllegalStateException -> 0x018a }
            L_0x018a:
                com.android.systemui.screenrecord.RecordingService r4 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x01a0 }
                android.media.MediaMuxer r4 = r4.mMuxer     // Catch:{ all -> 0x01a0 }
                r4.release()     // Catch:{ all -> 0x01a0 }
                com.android.systemui.screenrecord.RecordingService r4 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x01a0 }
                android.media.MediaMuxer unused = r4.mMuxer = r1     // Catch:{ all -> 0x01a0 }
                com.android.systemui.screenrecord.RecordingService r9 = com.android.systemui.screenrecord.RecordingService.this     // Catch:{ all -> 0x01a0 }
                boolean unused = r9.mMuxerStarted = r3     // Catch:{ all -> 0x01a0 }
            L_0x019d:
                monitor-exit(r2)     // Catch:{ all -> 0x01a0 }
                monitor-exit(r0)     // Catch:{ all -> 0x01a3 }
                return
            L_0x01a0:
                r9 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x01a0 }
                throw r9     // Catch:{ all -> 0x01a3 }
            L_0x01a3:
                r9 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x01a3 }
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenrecord.RecordingService.VideoEncoderTask.run():void");
        }
    }

    private class AudioEncoderTask implements Runnable {
        private MediaCodec.BufferInfo audioBufferInfo;

        private AudioEncoderTask() {
        }

        public void run() {
            int dequeueOutputBuffer;
            boolean unused = RecordingService.this.mAudioEncoding = true;
            int unused2 = RecordingService.this.audioTrackIndex = -1;
            this.audioBufferInfo = new MediaCodec.BufferInfo();
            while (true) {
                if (!RecordingService.this.mAudioEncoding) {
                    break;
                } else if (!RecordingService.this.mPausedRecording && (dequeueOutputBuffer = RecordingService.this.mAudioEncoder.dequeueOutputBuffer(this.audioBufferInfo, 10)) != -1) {
                    if (dequeueOutputBuffer == -2) {
                        if (RecordingService.this.audioTrackIndex < 0) {
                            synchronized (RecordingService.this.mMuxerLock) {
                                int unused3 = RecordingService.this.audioTrackIndex = RecordingService.this.mMuxer.addTrack(RecordingService.this.mAudioEncoder.getOutputFormat());
                                if (!RecordingService.this.mMuxerStarted && RecordingService.this.videoTrackIndex >= 0 && RecordingService.this.audioTrackIndex >= 0) {
                                    RecordingService.this.mMuxer.start();
                                    boolean unused4 = RecordingService.this.mMuxerStarted = true;
                                }
                            }
                        } else {
                            throw new RuntimeException("format changed twice");
                        }
                    } else if (dequeueOutputBuffer >= 0 && RecordingService.this.mMuxerStarted && RecordingService.this.audioTrackIndex >= 0) {
                        ByteBuffer outputBuffer = RecordingService.this.mAudioEncoder.getOutputBuffer(dequeueOutputBuffer);
                        if (outputBuffer != null) {
                            MediaCodec.BufferInfo bufferInfo = this.audioBufferInfo;
                            if ((bufferInfo.flags & 2) != 0) {
                                bufferInfo.size = 0;
                            }
                            if (this.audioBufferInfo.size != 0 && RecordingService.this.mMuxerStarted) {
                                outputBuffer.position(this.audioBufferInfo.offset);
                                MediaCodec.BufferInfo bufferInfo2 = this.audioBufferInfo;
                                outputBuffer.limit(bufferInfo2.offset + bufferInfo2.size);
                                synchronized (RecordingService.this.mWriteAudioLock) {
                                    if (RecordingService.this.mMuxerStarted) {
                                        RecordingService.this.mMuxer.writeSampleData(RecordingService.this.audioTrackIndex, outputBuffer, this.audioBufferInfo);
                                    }
                                }
                            }
                            RecordingService.this.mAudioEncoder.releaseOutputBuffer(dequeueOutputBuffer, false);
                            if ((this.audioBufferInfo.flags & 4) != 0) {
                                boolean unused5 = RecordingService.this.mAudioEncoding = false;
                                break;
                            }
                        } else {
                            throw new RuntimeException("encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                        }
                    }
                }
            }
            synchronized (RecordingService.this.mAudioEncoderLock) {
                RecordingService.this.mAudioEncoder.stop();
                RecordingService.this.mAudioEncoder.release();
                MediaCodec unused6 = RecordingService.this.mAudioEncoder = null;
            }
            synchronized (RecordingService.this.mWriteVideoLock) {
                synchronized (RecordingService.this.mMuxerLock) {
                    if (RecordingService.this.mMuxer != null) {
                        if (RecordingService.this.mMuxerStarted) {
                            RecordingService.this.mMuxer.stop();
                        }
                        RecordingService.this.mMuxer.release();
                        MediaMuxer unused7 = RecordingService.this.mMuxer = null;
                        boolean unused8 = RecordingService.this.mMuxerStarted = false;
                    }
                }
            }
        }
    }
}
