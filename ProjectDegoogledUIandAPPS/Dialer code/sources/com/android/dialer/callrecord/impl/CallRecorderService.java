package com.android.dialer.callrecord.impl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.android.dialer.R;
import com.android.dialer.callrecord.CallRecording;
import com.android.dialer.callrecord.ICallRecorderService;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CallRecorderService extends Service {
    private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmmss");
    private final ICallRecorderService.Stub mBinder = new ICallRecorderService.Stub() {
        public CallRecording getActiveRecording() throws RemoteException {
            return CallRecorderService.this.mCurrentRecording;
        }

        public boolean isRecording() throws RemoteException {
            return CallRecorderService.this.getState() == RecorderState.RECORDING;
        }

        public boolean startRecording(String str, long j) throws RemoteException {
            String str2 = str;
            long j2 = j;
            CallRecording unused = CallRecorderService.this.mCurrentRecording = new CallRecording(str2, j2, CallRecorderService.access$300(CallRecorderService.this, str), System.currentTimeMillis());
            CallRecorderService callRecorderService = CallRecorderService.this;
            MediaScannerConnection.MediaScannerConnectionClient unused2 = callRecorderService.mClient = new MediaScanner(callRecorderService, callRecorderService.getApplicationContext());
            CallRecorderService callRecorderService2 = CallRecorderService.this;
            return callRecorderService2.startRecordingInternal(callRecorderService2.mCurrentRecording.getFile());
        }

        public CallRecording stopRecording() {
            if (CallRecorderService.this.getState() != RecorderState.RECORDING) {
                return null;
            }
            CallRecorderService.this.stopRecordingInternal();
            return CallRecorderService.this.mCurrentRecording;
        }
    };
    /* access modifiers changed from: private */
    public MediaScannerConnection.MediaScannerConnectionClient mClient;
    /* access modifiers changed from: private */
    public CallRecording mCurrentRecording = null;
    private MediaRecorder mMediaRecorder = null;
    private RecorderState mState = RecorderState.IDLE;

    private final class MediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {
        private MediaScannerConnection mConnection;
        private String mFileName;

        public MediaScanner(CallRecorderService callRecorderService, Context context) {
            this.mConnection = new MediaScannerConnection(context, this);
        }

        public void connectAndScan(String str) {
            this.mFileName = str;
            this.mConnection.connect();
        }

        public void onMediaScannerConnected() {
            this.mConnection.scanFile(this.mFileName, (String) null);
        }

        public void onScanCompleted(String str, Uri uri) {
            this.mConnection.disconnect();
        }
    }

    private enum RecorderState {
        IDLE,
        RECORDING
    }

    static /* synthetic */ String access$300(CallRecorderService callRecorderService, String str) {
        String format = callRecorderService.DATE_FORMAT.format(new Date());
        if (TextUtils.isEmpty(str)) {
            str = "unknown";
        }
        String str2 = callRecorderService.getAudioFormatChoice() == 0 ? ".amr" : ".m4a";
        return "CallRecord_" + format + "_" + str + str2;
    }

    private int getAudioFormatChoice() {
        try {
            String string = getSharedPreferences(getPackageName() + "_preferences", 4).getString(getString(R.string.call_recording_format_key), (String) null);
            if (string != null) {
                return Integer.parseInt(string);
            }
            return 0;
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public synchronized RecorderState getState() {
        return this.mState;
    }

    public static boolean isEnabled(Context context) {
        return context.getResources().getBoolean(R.bool.call_recording_enabled);
    }

    /* access modifiers changed from: private */
    public synchronized boolean startRecordingInternal(File file) {
        if (this.mMediaRecorder != null) {
            stopRecordingInternal();
        }
        if (checkSelfPermission("android.permission.RECORD_AUDIO") != 0) {
            Log.w("CallRecorderService", "Record audio permission not granted, can't record call");
            return false;
        } else if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            Log.w("CallRecorderService", "External storage permission not granted, can't save recorded call");
            return false;
        } else {
            this.mMediaRecorder = new MediaRecorder();
            try {
                int i = SystemProperties.getInt("persist.call_recording.src", getResources().getInteger(R.integer.call_recording_audio_source));
                int audioFormatChoice = getAudioFormatChoice();
                this.mMediaRecorder.setAudioSource(i);
                int i2 = 2;
                this.mMediaRecorder.setOutputFormat(audioFormatChoice == 0 ? 4 : 2);
                MediaRecorder mediaRecorder = this.mMediaRecorder;
                if (audioFormatChoice != 0) {
                    i2 = 3;
                }
                mediaRecorder.setAudioEncoder(i2);
                file.getParentFile().mkdirs();
                String absolutePath = file.getAbsolutePath();
                try {
                    this.mMediaRecorder.setOutputFile(absolutePath);
                    this.mMediaRecorder.prepare();
                    this.mMediaRecorder.start();
                    this.mState = RecorderState.RECORDING;
                    return true;
                } catch (IOException e) {
                    Log.w("CallRecorderService", "Could not start recording for file " + absolutePath, e);
                    Log.w("CallRecorderService", "Deleting failed recording " + absolutePath);
                    file.delete();
                    this.mMediaRecorder.reset();
                    this.mMediaRecorder.release();
                    this.mMediaRecorder = null;
                    return false;
                } catch (IllegalStateException e2) {
                    Log.w("CallRecorderService", "Could not start recording for file " + absolutePath, e2);
                    Log.w("CallRecorderService", "Deleting failed recording " + absolutePath);
                    file.delete();
                    this.mMediaRecorder.reset();
                    this.mMediaRecorder.release();
                    this.mMediaRecorder = null;
                    return false;
                } catch (RuntimeException e3) {
                    if (e3.getMessage().indexOf("start failed") >= 0) {
                        Log.w("CallRecorderService", "Could not start recording for file " + absolutePath, e3);
                        Log.w("CallRecorderService", "Deleting failed recording " + absolutePath);
                        file.delete();
                        this.mMediaRecorder.reset();
                        this.mMediaRecorder.release();
                        this.mMediaRecorder = null;
                        return false;
                    }
                    throw e3;
                }
            } catch (IllegalStateException e4) {
                Log.w("CallRecorderService", "Error initializing media recorder", e4);
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void stopRecordingInternal() {
        if (this.mMediaRecorder != null) {
            try {
                if (getState() == RecorderState.RECORDING) {
                    this.mMediaRecorder.stop();
                    this.mMediaRecorder.reset();
                    this.mMediaRecorder.release();
                }
            } catch (IllegalStateException e) {
                Log.e("CallRecorderService", "Exception closing media recorder", e);
            }
            ((MediaScanner) this.mClient).connectAndScan(this.mCurrentRecording.getFile().getAbsolutePath());
            this.mMediaRecorder = null;
            this.mState = RecorderState.IDLE;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public void onCreate() {
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
