package com.android.voicemail.impl.transcribe.grpc;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.transcribe.TranscriptionConfigProvider;
import com.google.internal.communications.voicemailtranscription.p008v1.VoicemailTranscriptionServiceGrpc;
import java.security.MessageDigest;
import java.util.Arrays;
import p009io.grpc.CallOptions;
import p009io.grpc.Channel;
import p009io.grpc.ClientCall;
import p009io.grpc.ClientInterceptor;
import p009io.grpc.ClientInterceptors;
import p009io.grpc.ForwardingClientCall;
import p009io.grpc.ManagedChannel;
import p009io.grpc.Metadata;
import p009io.grpc.MethodDescriptor;
import p009io.grpc.okhttp.OkHttpChannelBuilder;

public class TranscriptionClientFactory {
    private static final char[] HEX_UPPERCASE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String cert;
    private final TranscriptionConfigProvider configProvider;
    private final ManagedChannel originalChannel;
    private final String packageName;

    private static final class Interceptor implements ClientInterceptor {
        /* access modifiers changed from: private */
        public static final Metadata.Key<String> ANDROID_CERT_HEADER = Metadata.Key.m93of("X-Android-Cert", Metadata.ASCII_STRING_MARSHALLER);
        /* access modifiers changed from: private */
        public static final Metadata.Key<String> ANDROID_PACKAGE_HEADER = Metadata.Key.m93of("X-Android-Package", Metadata.ASCII_STRING_MARSHALLER);
        /* access modifiers changed from: private */
        public static final Metadata.Key<String> API_KEY_HEADER = Metadata.Key.m93of("X-Goog-Api-Key", Metadata.ASCII_STRING_MARSHALLER);
        /* access modifiers changed from: private */
        public static final Metadata.Key<String> AUTHORIZATION_HEADER = Metadata.Key.m93of("authorization", Metadata.ASCII_STRING_MARSHALLER);
        /* access modifiers changed from: private */
        public final String apiKey;
        /* access modifiers changed from: private */
        public final String authToken;
        /* access modifiers changed from: private */
        public final String cert;
        /* access modifiers changed from: private */
        public final String packageName;

        public Interceptor(String str, String str2, String str3, String str4) {
            this.packageName = str;
            this.cert = str2;
            this.apiKey = str3;
            this.authToken = str4;
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("TranscriptionClientFactory.interceptCall, intercepted ");
            outline13.append(methodDescriptor.getFullMethodName());
            LogUtil.enterBlock(outline13.toString());
            return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) {
                public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
                    if (!TextUtils.isEmpty(Interceptor.this.packageName)) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("attaching package name: ");
                        outline13.append(Interceptor.this.packageName);
                        LogUtil.m9i("TranscriptionClientFactory.interceptCall", outline13.toString(), new Object[0]);
                        metadata.put(Interceptor.ANDROID_PACKAGE_HEADER, Interceptor.this.packageName);
                    }
                    if (!TextUtils.isEmpty(Interceptor.this.cert)) {
                        LogUtil.m9i("TranscriptionClientFactory.interceptCall", "attaching android cert", new Object[0]);
                        metadata.put(Interceptor.ANDROID_CERT_HEADER, Interceptor.this.cert);
                    }
                    if (!TextUtils.isEmpty(Interceptor.this.apiKey)) {
                        LogUtil.m9i("TranscriptionClientFactory.interceptCall", "attaching API Key", new Object[0]);
                        metadata.put(Interceptor.API_KEY_HEADER, Interceptor.this.apiKey);
                    }
                    if (!TextUtils.isEmpty(Interceptor.this.authToken)) {
                        LogUtil.m9i("TranscriptionClientFactory.interceptCall", "attaching auth token", new Object[0]);
                        Metadata.Key access$700 = Interceptor.AUTHORIZATION_HEADER;
                        StringBuilder outline132 = GeneratedOutlineSupport.outline13("Bearer ");
                        outline132.append(Interceptor.this.authToken);
                        metadata.put(access$700, outline132.toString());
                    }
                    super.start(listener, metadata);
                }
            };
        }
    }

    public TranscriptionClientFactory(Context context, TranscriptionConfigProvider transcriptionConfigProvider) {
        Signature[] signatureArr;
        OkHttpChannelBuilder forTarget = OkHttpChannelBuilder.forTarget(transcriptionConfigProvider.getServerAddress());
        if (transcriptionConfigProvider.shouldUsePlaintext()) {
            forTarget.usePlaintext(true);
        }
        ManagedChannel build = forTarget.build();
        this.configProvider = transcriptionConfigProvider;
        this.packageName = context.getPackageName();
        String str = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo == null || (signatureArr = packageInfo.signatures) == null || signatureArr.length <= 0) {
                LogUtil.m10w("TranscriptionClientFactory.getCertificateFingerprint", "failed to get package signature.", new Object[0]);
                this.cert = str;
                this.originalChannel = build;
            }
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            if (instance == null) {
                LogUtil.m10w("TranscriptionClientFactory.getCertificateFingerprint", "error getting digest.", new Object[0]);
            } else {
                byte[] digest = instance.digest(packageInfo.signatures[0].toByteArray());
                if (digest == null) {
                    LogUtil.m10w("TranscriptionClientFactory.getCertificateFingerprint", "empty message digest.", new Object[0]);
                } else {
                    int length = digest.length;
                    StringBuilder sb = new StringBuilder(length * 2);
                    for (int i = 0; i < length; i++) {
                        sb.append(HEX_UPPERCASE[(digest[i] & 240) >>> 4]);
                        sb.append(HEX_UPPERCASE[digest[i] & 15]);
                    }
                    str = sb.toString();
                }
            }
            this.cert = str;
            this.originalChannel = build;
        } catch (Exception e) {
            LogUtil.m7e("TranscriptionClientFactory.getCertificateFingerprint", "error getting certificate fingerprint.", (Throwable) e);
        }
    }

    public TranscriptionClient getClient() {
        LogUtil.enterBlock("TranscriptionClientFactory.getClient");
        Assert.checkState(!this.originalChannel.isShutdown());
        ManagedChannel managedChannel = this.originalChannel;
        String str = this.packageName;
        String str2 = this.cert;
        String apiKey = this.configProvider.getApiKey();
        this.configProvider.getAuthToken();
        return new TranscriptionClient(VoicemailTranscriptionServiceGrpc.newBlockingStub(ClientInterceptors.intercept(managedChannel, Arrays.asList(new ClientInterceptor[]{new Interceptor(str, str2, apiKey, (String) null)}))));
    }

    public void shutdown() {
        LogUtil.enterBlock("TranscriptionClientFactory.shutdown");
        if (!this.originalChannel.isShutdown()) {
            this.originalChannel.shutdown();
        }
    }
}
