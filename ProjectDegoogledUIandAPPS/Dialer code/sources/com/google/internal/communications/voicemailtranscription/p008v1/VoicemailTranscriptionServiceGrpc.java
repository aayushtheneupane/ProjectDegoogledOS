package com.google.internal.communications.voicemailtranscription.p008v1;

import p009io.grpc.Channel;
import p009io.grpc.MethodDescriptor;
import p009io.grpc.protobuf.lite.ProtoLiteUtils;
import p009io.grpc.stub.AbstractStub;

/* renamed from: com.google.internal.communications.voicemailtranscription.v1.VoicemailTranscriptionServiceGrpc */
public class VoicemailTranscriptionServiceGrpc {
    public static final MethodDescriptor<GetTranscriptRequest, GetTranscriptResponse> METHOD_GET_TRANSCRIPT = MethodDescriptor.create(MethodDescriptor.MethodType.UNARY, MethodDescriptor.generateFullMethodName("google.internal.communications.voicemailtranscription.v1.VoicemailTranscriptionService", "GetTranscript"), ProtoLiteUtils.marshaller(GetTranscriptRequest.getDefaultInstance()), ProtoLiteUtils.marshaller(GetTranscriptResponse.getDefaultInstance()));
    public static final MethodDescriptor<SendTranscriptionFeedbackRequest, SendTranscriptionFeedbackResponse> METHOD_SEND_TRANSCRIPTION_FEEDBACK = MethodDescriptor.create(MethodDescriptor.MethodType.UNARY, MethodDescriptor.generateFullMethodName("google.internal.communications.voicemailtranscription.v1.VoicemailTranscriptionService", "SendTranscriptionFeedback"), ProtoLiteUtils.marshaller(SendTranscriptionFeedbackRequest.getDefaultInstance()), ProtoLiteUtils.marshaller(SendTranscriptionFeedbackResponse.getDefaultInstance()));
    public static final MethodDescriptor<TranscribeVoicemailRequest, TranscribeVoicemailResponse> METHOD_TRANSCRIBE_VOICEMAIL = MethodDescriptor.create(MethodDescriptor.MethodType.UNARY, MethodDescriptor.generateFullMethodName("google.internal.communications.voicemailtranscription.v1.VoicemailTranscriptionService", "TranscribeVoicemail"), ProtoLiteUtils.marshaller(TranscribeVoicemailRequest.getDefaultInstance()), ProtoLiteUtils.marshaller(TranscribeVoicemailResponse.getDefaultInstance()));
    public static final MethodDescriptor<TranscribeVoicemailAsyncRequest, TranscribeVoicemailAsyncResponse> METHOD_TRANSCRIBE_VOICEMAIL_ASYNC = MethodDescriptor.create(MethodDescriptor.MethodType.UNARY, MethodDescriptor.generateFullMethodName("google.internal.communications.voicemailtranscription.v1.VoicemailTranscriptionService", "TranscribeVoicemailAsync"), ProtoLiteUtils.marshaller(TranscribeVoicemailAsyncRequest.getDefaultInstance()), ProtoLiteUtils.marshaller(TranscribeVoicemailAsyncResponse.getDefaultInstance()));

    /* renamed from: com.google.internal.communications.voicemailtranscription.v1.VoicemailTranscriptionServiceGrpc$VoicemailTranscriptionServiceBlockingStub */
    public static final class VoicemailTranscriptionServiceBlockingStub extends AbstractStub<VoicemailTranscriptionServiceBlockingStub> {
        /* synthetic */ VoicemailTranscriptionServiceBlockingStub(Channel channel, C09131 r2) {
            super(channel);
        }
    }

    public static VoicemailTranscriptionServiceBlockingStub newBlockingStub(Channel channel) {
        return new VoicemailTranscriptionServiceBlockingStub(channel, (C09131) null);
    }
}
