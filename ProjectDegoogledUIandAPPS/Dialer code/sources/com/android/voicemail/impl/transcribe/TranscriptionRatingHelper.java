package com.android.voicemail.impl.transcribe;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import com.android.dialer.common.Assert;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.voicemail.impl.transcribe.TranscriptionRatingHelper;
import com.google.internal.communications.voicemailtranscription.p008v1.AudioFormat;
import com.google.internal.communications.voicemailtranscription.p008v1.SendTranscriptionFeedbackRequest;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscriptionRating;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscriptionRatingValue;
import com.google.protobuf.ByteString;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TranscriptionRatingHelper {

    public interface FailureListener {
        void onRatingFailure(Throwable th);
    }

    private static class RatingWorker implements DialerExecutor.Worker<Void, Void> {
        private final Context context;
        private final TranscriptionRatingValue ratingValue;
        private final Uri voicemailUri;

        /* synthetic */ RatingWorker(Context context2, TranscriptionRatingValue transcriptionRatingValue, Uri uri, C07881 r4) {
            this.context = context2;
            this.ratingValue = transcriptionRatingValue;
            this.voicemailUri = uri;
        }

        public Object doInBackground(Object obj) throws Throwable {
            Void voidR = (Void) obj;
            Context context2 = this.context;
            String fingerprintFor = TranscriptionRatingHelper.getFingerprintFor(TranscriptionRatingHelper.getAudioData(context2, this.voicemailUri), this.voicemailUri.toString());
            TranscriptionRating.Builder newBuilder = TranscriptionRating.newBuilder();
            newBuilder.setTranscriptionId(fingerprintFor);
            newBuilder.setRatingValue(this.ratingValue);
            SendTranscriptionFeedbackRequest.Builder newBuilder2 = SendTranscriptionFeedbackRequest.newBuilder();
            newBuilder2.addRating((TranscriptionRating) newBuilder.build());
            TranscriptionRatingService.scheduleTask(context2, (SendTranscriptionFeedbackRequest) newBuilder2.build());
            new TranscriptionDbHelper(this.context, this.voicemailUri).setTranscriptionState(-3);
            return null;
        }
    }

    public interface SuccessListener {
        void onRatingSuccess(Uri uri);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.protobuf.ByteString getAudioData(android.content.Context r1, android.net.Uri r2) {
        /*
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ IOException -> 0x0020 }
            java.io.InputStream r1 = r1.openInputStream(r2)     // Catch:{ IOException -> 0x0020 }
            com.google.protobuf.ByteString r2 = com.google.protobuf.ByteString.readFrom(r1)     // Catch:{ all -> 0x0012 }
            if (r1 == 0) goto L_0x0011
            r1.close()     // Catch:{ IOException -> 0x0020 }
        L_0x0011:
            return r2
        L_0x0012:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0014 }
        L_0x0014:
            r0 = move-exception
            if (r1 == 0) goto L_0x001f
            r1.close()     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001b:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ IOException -> 0x0020 }
        L_0x001f:
            throw r0     // Catch:{ IOException -> 0x0020 }
        L_0x0020:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.transcribe.TranscriptionRatingHelper.getAudioData(android.content.Context, android.net.Uri):com.google.protobuf.ByteString");
    }

    static AudioFormat getAudioFormat(ByteString byteString) {
        if (byteString == null || !byteString.startsWith(ByteString.copyFromUtf8("#!AMR\n"))) {
            return AudioFormat.AUDIO_FORMAT_UNSPECIFIED;
        }
        return AudioFormat.AMR_NB_8KHZ;
    }

    @TargetApi(26)
    static String getFingerprintFor(ByteString byteString, String str) {
        Assert.checkArgument(byteString != null);
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            if (str != null) {
                instance.update(str.getBytes());
            }
            return Base64.encodeToString(instance.digest(byteString.toByteArray()), 0);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e.toString());
        }
    }

    public static void sendRating(Context context, TranscriptionRatingValue transcriptionRatingValue, Uri uri, SuccessListener successListener, FailureListener failureListener) {
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(context).dialerExecutorFactory()).createNonUiTaskBuilder(new RatingWorker(context, transcriptionRatingValue, uri, (C07881) null)).onSuccess(new DialerExecutor.SuccessListener(uri) {
            private final /* synthetic */ Uri f$1;

            {
                this.f$1 = r2;
            }

            public final void onSuccess(Object obj) {
                Void voidR = (Void) obj;
                TranscriptionRatingHelper.SuccessListener.this.onRatingSuccess(this.f$1);
            }
        }).onFailure(new DialerExecutor.FailureListener() {
            public final void onFailure(Throwable th) {
                TranscriptionRatingHelper.FailureListener.this.onRatingFailure(th);
            }
        }).build().executeParallel(null);
    }
}
