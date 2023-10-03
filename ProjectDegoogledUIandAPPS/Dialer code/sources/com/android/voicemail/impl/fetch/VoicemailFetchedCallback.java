package com.android.voicemail.impl.fetch;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.imap.VoicemailPayload;
import com.android.voicemail.impl.transcribe.TranscriptionService;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.p011io.IOUtils;

public class VoicemailFetchedCallback {
    private final ContentResolver contentResolver;
    private final Context context;
    private final PhoneAccountHandle phoneAccountHandle;
    private final Uri uri;

    public VoicemailFetchedCallback(Context context2, Uri uri2, PhoneAccountHandle phoneAccountHandle2) {
        this.context = context2;
        this.contentResolver = context2.getContentResolver();
        this.uri = uri2;
        this.phoneAccountHandle = phoneAccountHandle2;
    }

    private boolean updateVoicemail(ContentValues contentValues) {
        int update = this.contentResolver.update(this.uri, contentValues, (String) null, (String[]) null);
        if (update == 1) {
            return true;
        }
        VvmLog.m43e("VoicemailFetchedCallback", "Updating voicemail should have updated 1 row, was: " + update);
        return false;
    }

    public /* synthetic */ void lambda$setVoicemailContent$0$VoicemailFetchedCallback() {
        if (!TranscriptionService.scheduleNewVoicemailTranscriptionJob(this.context, this.uri, this.phoneAccountHandle, true)) {
            VvmLog.m46w("VoicemailFetchedCallback", String.format("Failed to schedule transcription for %s", new Object[]{this.uri}));
        }
    }

    public void setVoicemailContent(VoicemailPayload voicemailPayload) {
        Assert.isWorkerThread();
        if (voicemailPayload == null) {
            VvmLog.m45i("VoicemailFetchedCallback", "Payload not found, message has unsupported format");
            ContentValues contentValues = new ContentValues();
            Context context2 = this.context;
            contentValues.put("transcription", context2.getString(R.string.vvm_unsupported_message_format, new Object[]{((TelecomManager) context2.getSystemService(TelecomManager.class)).getVoiceMailNumber(this.phoneAccountHandle)}));
            updateVoicemail(contentValues);
            return;
        }
        String.format("Writing new voicemail content: %s", new Object[]{this.uri});
        try {
            OutputStream openOutputStream = this.contentResolver.openOutputStream(this.uri);
            byte[] bytes = voicemailPayload.getBytes();
            if (bytes != null) {
                openOutputStream.write(bytes);
            }
            IOUtils.closeQuietly(openOutputStream);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("mime_type", voicemailPayload.getMimeType());
            contentValues2.put("has_content", true);
            if (updateVoicemail(contentValues2)) {
                DialerExecutorModule.postOnUiThread(new Runnable() {
                    public final void run() {
                        VoicemailFetchedCallback.this.lambda$setVoicemailContent$0$VoicemailFetchedCallback();
                    }
                });
            }
        } catch (IOException unused) {
            VvmLog.m46w("VoicemailFetchedCallback", String.format("File not found for %s", new Object[]{this.uri}));
            IOUtils.closeQuietly((OutputStream) null);
        } catch (Throwable th) {
            IOUtils.closeQuietly((OutputStream) null);
            throw th;
        }
    }
}
