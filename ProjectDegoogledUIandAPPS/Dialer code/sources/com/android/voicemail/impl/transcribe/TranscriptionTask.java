package com.android.voicemail.impl.transcribe;

import android.app.job.JobWorkItem;
import android.content.Context;
import android.net.Uri;
import android.telecom.PhoneAccountHandle;
import android.util.Pair;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.transcribe.TranscriptionService;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionClient;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionClientFactory;
import com.android.voicemail.impl.transcribe.grpc.TranscriptionResponse;
import com.google.internal.communications.voicemailtranscription.p008v1.AudioFormat;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscriptionStatus;
import com.google.protobuf.ByteString;

public abstract class TranscriptionTask implements Runnable {
    protected ByteString audioData;
    private final TranscriptionService.JobCallback callback;
    protected volatile boolean cancelled;
    private final TranscriptionClientFactory clientFactory;
    protected final TranscriptionConfigProvider configProvider;
    protected final Context context;
    protected final TranscriptionDbHelper dbHelper;
    protected AudioFormat encoding;
    protected final PhoneAccountHandle phoneAccountHandle;
    protected final Uri voicemailUri;
    private final JobWorkItem workItem;

    public interface Request {
        TranscriptionResponse getResponse(TranscriptionClient transcriptionClient);
    }

    TranscriptionTask(Context context2, TranscriptionService.JobCallback jobCallback, JobWorkItem jobWorkItem, TranscriptionClientFactory transcriptionClientFactory, TranscriptionConfigProvider transcriptionConfigProvider) {
        this.context = context2;
        this.callback = jobCallback;
        this.workItem = jobWorkItem;
        this.clientFactory = transcriptionClientFactory;
        this.voicemailUri = TranscriptionService.getVoicemailUri(jobWorkItem);
        this.phoneAccountHandle = (PhoneAccountHandle) jobWorkItem.getIntent().getParcelableExtra("extra_account_handle");
        this.configProvider = transcriptionConfigProvider;
        this.dbHelper = new TranscriptionDbHelper(context2, this.voicemailUri);
    }

    static void recordResult(Context context2, Pair<String, TranscriptionStatus> pair, TranscriptionDbHelper transcriptionDbHelper, boolean z) {
        if (pair.first != null) {
            VvmLog.m45i("TranscriptionTask", "recordResult, got transcription");
            transcriptionDbHelper.setTranscriptionAndState((String) pair.first, 3);
            ((LoggingBindingsStub) Logger.get(context2)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_RESPONSE_SUCCESS);
        } else if (pair.second != null) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("recordResult, failed to transcribe, reason: ");
            outline13.append(pair.second);
            VvmLog.m45i("TranscriptionTask", outline13.toString());
            int ordinal = ((TranscriptionStatus) pair.second).ordinal();
            int i = 2;
            if (ordinal == 3) {
                transcriptionDbHelper.setTranscriptionState(2);
                ((LoggingBindingsStub) Logger.get(context2)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_RESPONSE_EXPIRED);
            } else if (ordinal == 6) {
                transcriptionDbHelper.setTranscriptionState(-2);
                ((LoggingBindingsStub) Logger.get(context2)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_RESPONSE_LANGUAGE_NOT_SUPPORTED);
            } else if (ordinal != 7) {
                if (z) {
                    i = 0;
                }
                transcriptionDbHelper.setTranscriptionState(i);
                ((LoggingBindingsStub) Logger.get(context2)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_RESPONSE_EMPTY);
            } else {
                transcriptionDbHelper.setTranscriptionState(-1);
                ((LoggingBindingsStub) Logger.get(context2)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_RESPONSE_NO_SPEECH_DETECTED);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        Assert.isMainThread();
        VvmLog.m45i("TranscriptionTask", "cancel");
        this.cancelled = true;
    }

    /* access modifiers changed from: protected */
    public abstract DialerImpression$Type getRequestSentImpression();

    /* access modifiers changed from: protected */
    public abstract Pair<String, TranscriptionStatus> getTranscription();

    public /* synthetic */ void lambda$run$0$TranscriptionTask() {
        ((TranscriptionService.Callback) this.callback).onWorkCompleted(this.workItem);
    }

    public void run() {
        VvmLog.m45i("TranscriptionTask", "run");
        boolean z = false;
        if (this.voicemailUri == null) {
            VvmLog.m45i("TranscriptionTask", "Transcriber.readAndValidateAudioFile, file not found.");
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Transcriber.readAndValidateAudioFile, reading: ");
            outline13.append(this.voicemailUri);
            VvmLog.m45i("TranscriptionTask", outline13.toString());
            this.audioData = TranscriptionRatingHelper.getAudioData(this.context, this.voicemailUri);
            if (this.audioData != null) {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("readAndValidateAudioFile, read ");
                outline132.append(this.audioData.size());
                outline132.append(" bytes");
                VvmLog.m45i("TranscriptionTask", outline132.toString());
                this.encoding = TranscriptionRatingHelper.getAudioFormat(this.audioData);
                if (this.encoding == AudioFormat.AUDIO_FORMAT_UNSPECIFIED) {
                    VvmLog.m45i("TranscriptionTask", "Transcriber.readAndValidateAudioFile, unknown encoding");
                } else {
                    z = true;
                }
            } else {
                StringBuilder outline133 = GeneratedOutlineSupport.outline13("readAndValidateAudioFile, unable to read audio data for ");
                outline133.append(this.voicemailUri);
                VvmLog.m45i("TranscriptionTask", outline133.toString());
            }
        }
        if (z) {
            updateTranscriptionState(1);
            VvmLog.m45i("TranscriptionTask", "transcribeVoicemail");
            recordResult(this.context, getTranscription(), this.dbHelper, this.cancelled);
        } else {
            if (AudioFormat.AUDIO_FORMAT_UNSPECIFIED.equals(this.encoding)) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_VOICEMAIL_FORMAT_NOT_SUPPORTED);
            } else {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_VOICEMAIL_INVALID_DATA);
            }
            updateTranscriptionState(2);
        }
        DialerExecutorModule.postOnUiThread(new Runnable() {
            public final void run() {
                TranscriptionTask.this.lambda$run$0$TranscriptionTask();
            }
        });
    }

    /* access modifiers changed from: protected */
    public TranscriptionResponse sendRequest(Request request) {
        VvmLog.m45i("TranscriptionTask", "sendRequest");
        TranscriptionClient client = this.clientFactory.getClient();
        int i = 0;
        while (((long) i) < this.configProvider.getMaxTranscriptionRetries()) {
            if (this.cancelled) {
                VvmLog.m45i("TranscriptionTask", "sendRequest, cancelled");
                return null;
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("sendRequest, try: ");
            int i2 = i + 1;
            outline13.append(i2);
            VvmLog.m45i("TranscriptionTask", outline13.toString());
            if (i == 0) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(getRequestSentImpression());
            } else {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_REQUEST_RETRY);
            }
            TranscriptionResponse response = request.getResponse(client);
            if (this.cancelled) {
                VvmLog.m45i("TranscriptionTask", "sendRequest, cancelled");
                return null;
            } else if (!response.hasRecoverableError()) {
                return response;
            } else {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_RESPONSE_RECOVERABLE_ERROR);
                VvmLog.m45i("TranscriptionTask", "backoff, count: " + i);
                try {
                    Thread.sleep((1 << i) * 1000);
                } catch (InterruptedException e) {
                    VvmLog.m44e("TranscriptionTask", "interrupted", e);
                    Thread.currentThread().interrupt();
                }
                i = i2;
            }
        }
        ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.VVM_TRANSCRIPTION_RESPONSE_TOO_MANY_ERRORS);
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setAudioDataForTesting(ByteString byteString) {
        this.audioData = byteString;
        this.encoding = TranscriptionRatingHelper.getAudioFormat(byteString);
    }

    /* access modifiers changed from: protected */
    public void updateTranscriptionState(int i) {
        this.dbHelper.setTranscriptionState(i);
    }
}
