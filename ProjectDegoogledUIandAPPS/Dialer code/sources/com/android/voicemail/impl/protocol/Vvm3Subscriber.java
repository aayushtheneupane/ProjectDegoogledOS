package com.android.voicemail.impl.protocol;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Network;
import android.os.Build;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.util.ArrayMap;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.ActivationTask;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.OmtpEvents;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.VvmLog;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

@TargetApi(26)
public class Vvm3Subscriber {
    static final String VVM3_SUBSCRIBE_LINK_PATTERNS_JSON_ARRAY = "vvm3_subscribe_link_pattern_json_array";
    private final Bundle data;
    private final PhoneAccountHandle handle;
    private final OmtpVvmCarrierConfigHelper helper;
    /* access modifiers changed from: private */
    public final String number;
    private RequestQueue requestQueue;
    private final VoicemailStatus$Editor status;
    private final ActivationTask task;

    private static class NetworkSpecifiedHurlStack extends HurlStack {
        private final Network network;

        public NetworkSpecifiedHurlStack(Network network2) {
            this.network = network2;
        }

        /* access modifiers changed from: protected */
        public HttpURLConnection createConnection(URL url) throws IOException {
            return (HttpURLConnection) this.network.openConnection(url);
        }
    }

    static class ProvisioningException extends Exception {
        public ProvisioningException(String str) {
            super(str);
        }
    }

    static {
        CookieHandler.setDefault(new CookieManager());
    }

    public Vvm3Subscriber(ActivationTask activationTask, PhoneAccountHandle phoneAccountHandle, OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper, VoicemailStatus$Editor voicemailStatus$Editor, Bundle bundle) {
        Assert.isNotMainThread();
        this.task = activationTask;
        this.handle = phoneAccountHandle;
        this.helper = omtpVvmCarrierConfigHelper;
        this.status = voicemailStatus$Editor;
        this.data = bundle;
        String line1Number = ((TelephonyManager) this.helper.getContext().getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(this.handle).getLine1Number();
        this.number = line1Number.startsWith("+1") ? line1Number.substring(2) : line1Number;
    }

    private void clickSubscribeLink(String str) throws ProvisioningException {
        VvmLog.m45i("Vvm3Subscriber", "Clicking subscribe link");
        RequestFuture newFuture = RequestFuture.newFuture();
        this.requestQueue.add(new StringRequest(1, str, newFuture, newFuture));
        try {
            newFuture.get(30, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.helper.handleEvent(this.status, OmtpEvents.VVM3_SPG_CONNECTION_FAILED);
            throw new ProvisioningException(e.toString());
        }
    }

    private String extractText(String str, String str2) throws ProvisioningException {
        Matcher matcher = Pattern.compile("<" + str2 + ">(.*)<\\/" + str2 + ">").matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new ProvisioningException(GeneratedOutlineSupport.outline9("Tag ", str2, " not found in xml response"));
    }

    static String findSubscribeLink(List<Pattern> list, String str) throws ProvisioningException {
        if (!list.isEmpty()) {
            Spanned fromHtml = Html.fromHtml(str, 0);
            URLSpan[] uRLSpanArr = (URLSpan[]) fromHtml.getSpans(0, fromHtml.length(), URLSpan.class);
            StringBuilder sb = new StringBuilder();
            for (URLSpan uRLSpan : uRLSpanArr) {
                String charSequence = fromHtml.subSequence(fromHtml.getSpanStart(uRLSpan), fromHtml.getSpanEnd(uRLSpan)).toString();
                for (Pattern matcher : list) {
                    if (matcher.matcher(charSequence).matches()) {
                        return uRLSpan.getURL();
                    }
                }
                sb.append(charSequence);
            }
            throw new ProvisioningException(GeneratedOutlineSupport.outline6("Subscribe link not found: ", sb));
        }
        throw new IllegalArgumentException("empty patterns");
    }

    private String getSelfProvisionResponse(String str) throws ProvisioningException {
        VvmLog.m45i("Vvm3Subscriber", "Retrieving self provisioning response");
        RequestFuture newFuture = RequestFuture.newFuture();
        this.requestQueue.add(new StringRequest(1, str, newFuture, newFuture) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() {
                ArrayMap arrayMap = new ArrayMap();
                arrayMap.put("VZW_MDN", Vvm3Subscriber.this.number);
                arrayMap.put("VZW_SERVICE", "BVVM");
                arrayMap.put("DEVICE_MODEL", "DROID_4G");
                arrayMap.put("APP_TOKEN", "q8e3t5u2o1");
                arrayMap.put("SPG_LANGUAGE_PARAM", "ENGLISH");
                return arrayMap;
            }
        });
        try {
            return (String) newFuture.get(30, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.helper.handleEvent(this.status, OmtpEvents.VVM3_SPG_CONNECTION_FAILED);
            throw new ProvisioningException(e.toString());
        }
    }

    private String getSelfProvisioningGateway() throws ProvisioningException {
        String str;
        VvmLog.m45i("Vvm3Subscriber", "retrieving SPG URL");
        "Sending vvm3XmlRequest for " + "retrieveSPGURL";
        String string = this.data.getString("vmg_url");
        if (string == null) {
            VvmLog.m43e("Vvm3Subscriber", "voicemailManagementGateway url unknown");
            str = null;
        } else {
            String valueOf = String.valueOf(Math.abs(new Random().nextLong()));
            final String format = String.format(Locale.US, "<?xml version=\"1.0\" encoding=\"UTF-8\"?><VMGVVMRequest>  <MessageHeader>    <transactionid>%1$s</transactionid>  </MessageHeader>  <MessageBody>    <mdn>%2$s</mdn>    <operation>%3$s</operation>    <source>Device</source>    <devicemodel>%4$s</devicemodel>  </MessageBody></VMGVVMRequest>", new Object[]{valueOf, this.number, "retrieveSPGURL", Build.MODEL});
            RequestFuture newFuture = RequestFuture.newFuture();
            this.requestQueue.add(new StringRequest(this, 1, string, newFuture, newFuture) {
                public byte[] getBody() throws AuthFailureError {
                    return format.getBytes();
                }
            });
            try {
                String str2 = (String) newFuture.get(30, TimeUnit.SECONDS);
                if (valueOf.equals(extractText(str2, "transactionid"))) {
                    str = str2;
                } else {
                    throw new ProvisioningException("transactionId mismatch");
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                this.helper.handleEvent(this.status, OmtpEvents.VVM3_VMG_CONNECTION_FAILED);
                throw new ProvisioningException(e.toString());
            }
        }
        return extractText(str, "spgurl");
    }

    static List<Pattern> getSubscribeLinkPatterns(Context context) {
        String string = ((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getString(VVM3_SUBSCRIBE_LINK_PATTERNS_JSON_ARRAY, "[\"(?i)Subscribe to Basic Visual Voice Mail\",\"(?i)Subscribe to Basic Visual Voicemail\"]");
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(string);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(Pattern.compile(jSONArray.getString(i)));
            }
            return arrayList;
        } catch (JSONException e) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline6("Unable to parse patterns", e));
        }
    }

    private void processSubscription() {
        try {
            clickSubscribeLink(findSubscribeLink(getSubscribeLinkPatterns(this.helper.getContext()), getSelfProvisionResponse(getSelfProvisioningGateway())));
        } catch (ProvisioningException e) {
            VvmLog.m43e("Vvm3Subscriber", e.toString());
            this.helper.handleEvent(this.status, OmtpEvents.CONFIG_SERVICE_NOT_AVAILABLE);
            this.task.fail();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        if (r0 != null) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003d, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subscribe() {
        /*
            r4 = this;
            com.android.voicemail.impl.Assert.isNotMainThread()
            java.lang.String r0 = "Vvm3Subscriber"
            java.lang.String r1 = "Subscribing"
            com.android.voicemail.impl.VvmLog.m45i(r0, r1)
            com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r0 = r4.helper     // Catch:{ RequestFailedException -> 0x003e }
            android.telecom.PhoneAccountHandle r1 = r4.handle     // Catch:{ RequestFailedException -> 0x003e }
            com.android.voicemail.impl.VoicemailStatus$Editor r2 = r4.status     // Catch:{ RequestFailedException -> 0x003e }
            com.android.voicemail.impl.sync.VvmNetworkRequest$NetworkWrapper r0 = com.android.voicemail.impl.sync.VvmNetworkRequest.getNetwork(r0, r1, r2)     // Catch:{ RequestFailedException -> 0x003e }
            android.net.Network r1 = r0.get()     // Catch:{ all -> 0x0030 }
            com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r2 = r4.helper     // Catch:{ all -> 0x0030 }
            android.content.Context r2 = r2.getContext()     // Catch:{ all -> 0x0030 }
            com.android.voicemail.impl.protocol.Vvm3Subscriber$NetworkSpecifiedHurlStack r3 = new com.android.voicemail.impl.protocol.Vvm3Subscriber$NetworkSpecifiedHurlStack     // Catch:{ all -> 0x0030 }
            r3.<init>(r1)     // Catch:{ all -> 0x0030 }
            com.android.volley.RequestQueue r1 = com.android.volley.toolbox.HttpHeaderParser.newRequestQueue(r2, r3)     // Catch:{ all -> 0x0030 }
            r4.requestQueue = r1     // Catch:{ all -> 0x0030 }
            r4.processSubscription()     // Catch:{ all -> 0x0030 }
            r0.close()     // Catch:{ RequestFailedException -> 0x003e }
            goto L_0x004c
        L_0x0030:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r2 = move-exception
            if (r0 == 0) goto L_0x003d
            r0.close()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ RequestFailedException -> 0x003e }
        L_0x003d:
            throw r2     // Catch:{ RequestFailedException -> 0x003e }
        L_0x003e:
            com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r0 = r4.helper
            com.android.voicemail.impl.VoicemailStatus$Editor r1 = r4.status
            com.android.voicemail.impl.OmtpEvents r2 = com.android.voicemail.impl.OmtpEvents.VVM3_VMG_CONNECTION_FAILED
            r0.handleEvent(r1, r2)
            com.android.voicemail.impl.ActivationTask r4 = r4.task
            r4.fail()
        L_0x004c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.protocol.Vvm3Subscriber.subscribe():void");
    }
}
