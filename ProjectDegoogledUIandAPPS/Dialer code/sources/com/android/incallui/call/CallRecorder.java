package com.android.incallui.call;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.callrecord.CallRecording;
import com.android.dialer.callrecord.CallRecordingDataStore;
import com.android.dialer.callrecord.ICallRecorderService;
import com.android.dialer.callrecord.impl.CallRecorderService;
import com.android.incallui.call.CallList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CallRecorder implements CallList.Listener {
    public static final String[] REQUIRED_PERMISSIONS = {"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static CallRecorder instance = null;
    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ICallRecorderService unused = CallRecorder.this.service = ICallRecorderService.Stub.asInterface(iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            ICallRecorderService unused = CallRecorder.this.service = null;
        }
    };
    private Context context;
    /* access modifiers changed from: private */
    public Handler handler = new Handler();
    private boolean initialized = false;
    /* access modifiers changed from: private */
    public HashSet<RecordingProgressListener> progressListeners = new HashSet<>();
    /* access modifiers changed from: private */
    public ICallRecorderService service = null;
    private Runnable updateRecordingProgressTask = new Runnable() {
        public void run() {
            CallRecording activeRecording = CallRecorder.this.getActiveRecording();
            if (activeRecording != null) {
                long currentTimeMillis = System.currentTimeMillis() - activeRecording.startRecordingTime;
                Iterator it = CallRecorder.this.progressListeners.iterator();
                while (it.hasNext()) {
                    ((RecordingProgressListener) it.next()).onRecordingTimeProgress(currentTimeMillis);
                }
            }
            CallRecorder.this.handler.postDelayed(this, 500);
        }
    };

    public interface RecordingProgressListener {
        void onRecordingTimeProgress(long j);

        void onStartRecording();

        void onStopRecording();
    }

    static {
        new HashMap();
    }

    private CallRecorder() {
        CallList.getInstance().addListener(this);
    }

    public static CallRecorder getInstance() {
        if (instance == null) {
            instance = new CallRecorder();
        }
        return instance;
    }

    public void addRecordingProgressListener(RecordingProgressListener recordingProgressListener) {
        this.progressListeners.add(recordingProgressListener);
    }

    public void finishRecording() {
        ICallRecorderService iCallRecorderService = this.service;
        if (iCallRecorderService != null) {
            try {
                CallRecording stopRecording = iCallRecorderService.stopRecording();
                if (stopRecording != null) {
                    if (!TextUtils.isEmpty(stopRecording.phoneNumber)) {
                        new Thread(new Runnable(stopRecording) {
                            private final /* synthetic */ CallRecording f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                CallRecorder.this.lambda$finishRecording$0$CallRecorder(this.f$1);
                            }
                        }).start();
                    } else {
                        Toast.makeText(this.context, this.context.getResources().getString(R.string.call_recording_file_location, new Object[]{stopRecording.fileName}), 0).show();
                    }
                }
            } catch (RemoteException e) {
                Log.w("CallRecorder", "Failed to stop recording", e);
            }
        }
        Iterator<RecordingProgressListener> it = this.progressListeners.iterator();
        while (it.hasNext()) {
            it.next().onStopRecording();
        }
        this.handler.removeCallbacks(this.updateRecordingProgressTask);
    }

    public CallRecording getActiveRecording() {
        ICallRecorderService iCallRecorderService = this.service;
        if (iCallRecorderService == null) {
            return null;
        }
        try {
            return iCallRecorderService.getActiveRecording();
        } catch (RemoteException e) {
            Log.w("Exception getting active recording", e);
            return null;
        }
    }

    public boolean isRecording() {
        ICallRecorderService iCallRecorderService = this.service;
        if (iCallRecorderService == null) {
            return false;
        }
        try {
            return iCallRecorderService.isRecording();
        } catch (RemoteException e) {
            Log.w("CallRecorder", "Exception checking recording status", e);
            return false;
        }
    }

    public /* synthetic */ void lambda$finishRecording$0$CallRecorder(CallRecording callRecording) {
        CallRecordingDataStore callRecordingDataStore = new CallRecordingDataStore();
        callRecordingDataStore.open(this.context);
        callRecordingDataStore.putRecording(callRecording);
        callRecordingDataStore.close();
    }

    public void onCallListChange(CallList callList) {
        if (this.initialized || callList.getActiveCall() == null) {
            CallRecording activeRecording = getActiveRecording();
            if (activeRecording != null && callList.getCallWithStateAndNumber(8, activeRecording.phoneNumber) != null) {
                finishRecording();
            }
        } else if (CallRecorderService.isEnabled(this.context) && !this.initialized) {
            this.context.bindService(new Intent(this.context, CallRecorderService.class), this.connection, 1);
            this.initialized = true;
        }
    }

    public void onDisconnect(DialerCall dialerCall) {
        CallRecording activeRecording = getActiveRecording();
        if (activeRecording != null && TextUtils.equals(dialerCall.getNumber(), activeRecording.phoneNumber)) {
            finishRecording();
        }
        if (CallList.getInstance().getActiveCall() == null && this.initialized) {
            this.context.unbindService(this.connection);
            this.initialized = false;
        }
    }

    public void onHandoverToWifiFailed(DialerCall dialerCall) {
    }

    public void onIncomingCall(DialerCall dialerCall) {
    }

    public void onInternationalCallOnWifi(DialerCall dialerCall) {
    }

    public void onSessionModificationStateChange(DialerCall dialerCall) {
    }

    public void onUpgradeToVideo(DialerCall dialerCall) {
    }

    public void onWiFiToLteHandover(DialerCall dialerCall) {
    }

    public void removeRecordingProgressListener(RecordingProgressListener recordingProgressListener) {
        this.progressListeners.remove(recordingProgressListener);
    }

    public void setUp(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public boolean startRecording(String str, long j) {
        ICallRecorderService iCallRecorderService = this.service;
        if (iCallRecorderService == null) {
            return false;
        }
        try {
            if (iCallRecorderService.startRecording(str, j)) {
                Iterator<RecordingProgressListener> it = this.progressListeners.iterator();
                while (it.hasNext()) {
                    it.next().onStartRecording();
                }
                this.updateRecordingProgressTask.run();
                return true;
            }
            Toast.makeText(this.context, R.string.call_recording_failed_message, 0).show();
            return false;
        } catch (RemoteException e) {
            Log.w("CallRecorder", "Failed to start recording " + str + ", " + new Date(j), e);
        }
    }
}
