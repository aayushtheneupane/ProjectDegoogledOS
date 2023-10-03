package com.android.dialer.postcall;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;

public class PostCall {
    private static Snackbar activeSnackbar;

    /* access modifiers changed from: private */
    public static void clear(Context context) {
        activeSnackbar = null;
        StorageComponent.get(context).unencryptedSharedPrefs().edit().remove("post_call_call_disconnect_time").remove("post_call_call_number").remove("post_call_message_sent").remove("post_call_call_connect_time").remove("post_call_disconnect_pressed").apply();
    }

    public static void closePrompt() {
        Snackbar snackbar = activeSnackbar;
        if (snackbar != null && snackbar.isShown()) {
            activeSnackbar.dismiss();
            activeSnackbar = null;
        }
    }

    private static String getPhoneNumber(Context context) {
        return StorageComponent.get(context).unencryptedSharedPrefs().getString("post_call_call_number", (String) null);
    }

    static /* synthetic */ void lambda$promptUserToSendMessage$0(Activity activity, String str, boolean z, View view) {
        ((LoggingBindingsStub) Logger.get(activity)).logImpression(DialerImpression$Type.POST_CALL_PROMPT_USER_TO_SEND_MESSAGE_CLICKED);
        activity.startActivity(PostCallActivity.newIntent(activity, str, z));
    }

    static /* synthetic */ void lambda$promptUserToViewSentMessage$1(Activity activity, String str, View view) {
        ((LoggingBindingsStub) Logger.get(activity)).logImpression(DialerImpression$Type.POST_CALL_PROMPT_USER_TO_VIEW_SENT_MESSAGE_CLICKED);
        DialerUtils.startActivityWithErrorToast(activity, CallUtil.getSendSmsIntent(str), R.string.activity_not_available);
    }

    public static void onMessageSent(Context context, String str) {
        StorageComponent.get(context).unencryptedSharedPrefs().edit().putString("post_call_call_number", str).putBoolean("post_call_message_sent", true).apply();
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x019b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void promptUserForMessageIfNecessary(android.app.Activity r17, android.view.View r18) {
        /*
            r0 = r17
            r1 = r18
            com.android.dialer.configprovider.ConfigProviderComponent r2 = com.android.dialer.configprovider.ConfigProviderComponent.get(r17)
            com.android.dialer.configprovider.ConfigProvider r2 = r2.getConfigProvider()
            com.android.dialer.configprovider.SharedPrefConfigProvider r2 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r2
            r3 = 1
            java.lang.String r4 = "enable_post_call_prod"
            boolean r2 = r2.getBoolean(r4, r3)
            if (r2 == 0) goto L_0x019e
            com.android.dialer.storage.StorageComponent r2 = com.android.dialer.storage.StorageComponent.get(r17)
            android.content.SharedPreferences r2 = r2.unencryptedSharedPrefs()
            java.lang.String r4 = "post_call_message_sent"
            r5 = 0
            boolean r2 = r2.getBoolean(r4, r5)
            r6 = 2131099739(0x7f06005b, float:1.781184E38)
            if (r2 == 0) goto L_0x008f
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r3 = "PostCall.promptUserToViewSentMessage"
            java.lang.String r7 = "returned from sending a post call message, message sent."
            com.android.dialer.common.LogUtil.m9i(r3, r7, r2)
            r2 = 2131821225(0x7f1102a9, float:1.9275187E38)
            java.lang.String r2 = r0.getString(r2)
            r3 = 2131821487(0x7f1103af, float:1.9275719E38)
            java.lang.String r3 = r0.getString(r3)
            java.lang.String r7 = getPhoneNumber(r17)
            com.android.dialer.common.Assert.isNotNull(r7)
            com.android.dialer.postcall.-$$Lambda$PostCall$qNOviO6V9ViV3rsJjolQ8p_AaKc r8 = new com.android.dialer.postcall.-$$Lambda$PostCall$qNOviO6V9ViV3rsJjolQ8p_AaKc
            r8.<init>(r0, r7)
            android.support.design.widget.Snackbar r1 = android.support.design.widget.Snackbar.make((android.view.View) r1, (java.lang.CharSequence) r2, (int) r5)
            r1.setAction((java.lang.CharSequence) r3, (android.view.View.OnClickListener) r8)
            android.content.res.Resources r2 = r17.getResources()
            int r2 = r2.getColor(r6)
            r1.setActionTextColor(r2)
            com.android.dialer.postcall.PostCall$1 r2 = new com.android.dialer.postcall.PostCall$1
            r2.<init>()
            r1.addCallback(r2)
            activeSnackbar = r1
            android.support.design.widget.Snackbar r1 = activeSnackbar
            r1.show()
            com.android.dialer.logging.LoggingBindings r1 = com.android.dialer.logging.Logger.get(r17)
            com.android.dialer.logging.DialerImpression$Type r2 = com.android.dialer.logging.DialerImpression$Type.POST_CALL_PROMPT_USER_TO_VIEW_SENT_MESSAGE
            com.android.dialer.logging.LoggingBindingsStub r1 = (com.android.dialer.logging.LoggingBindingsStub) r1
            r1.logImpression(r2)
            com.android.dialer.storage.StorageComponent r0 = com.android.dialer.storage.StorageComponent.get(r17)
            android.content.SharedPreferences r0 = r0.unencryptedSharedPrefs()
            android.content.SharedPreferences$Editor r0 = r0.edit()
            android.content.SharedPreferences$Editor r0 = r0.remove(r4)
            r0.apply()
            goto L_0x019e
        L_0x008f:
            com.android.dialer.storage.StorageComponent r2 = com.android.dialer.storage.StorageComponent.get(r17)
            android.content.SharedPreferences r2 = r2.unencryptedSharedPrefs()
            r7 = -1
            java.lang.String r4 = "post_call_call_disconnect_time"
            long r9 = r2.getLong(r4, r7)
            java.lang.String r11 = "post_call_call_connect_time"
            long r11 = r2.getLong(r11, r7)
            long r13 = java.lang.System.currentTimeMillis()
            long r13 = r13 - r9
            long r15 = r9 - r11
            java.lang.String r6 = "post_call_disconnect_pressed"
            boolean r2 = r2.getBoolean(r6, r5)
            com.android.dialer.configprovider.ConfigProviderComponent r6 = com.android.dialer.configprovider.ConfigProviderComponent.get(r17)
            com.android.dialer.configprovider.ConfigProvider r6 = r6.getConfigProvider()
            int r9 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x0101
            int r7 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r7 == 0) goto L_0x0101
            java.lang.Class<android.telephony.TelephonyManager> r7 = android.telephony.TelephonyManager.class
            java.lang.Object r7 = r0.getSystemService(r7)
            android.telephony.TelephonyManager r7 = (android.telephony.TelephonyManager) r7
            int r7 = r7.getSimState()
            r8 = 5
            if (r7 != r8) goto L_0x00d3
            r7 = r3
            goto L_0x00d4
        L_0x00d3:
            r7 = r5
        L_0x00d4:
            if (r7 == 0) goto L_0x0101
            r7 = 30000(0x7530, double:1.4822E-319)
            com.android.dialer.configprovider.SharedPrefConfigProvider r6 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r6
            java.lang.String r9 = "postcall_last_call_threshold"
            long r7 = r6.getLong(r9, r7)
            int r7 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r7 <= 0) goto L_0x0101
            r7 = 0
            int r7 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r7 == 0) goto L_0x00f7
            r7 = 35000(0x88b8, double:1.72923E-319)
            java.lang.String r9 = "postcall_call_duration_threshold"
            long r6 = r6.getLong(r9, r7)
            int r6 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r6 <= 0) goto L_0x0101
        L_0x00f7:
            java.lang.String r6 = getPhoneNumber(r17)
            if (r6 == 0) goto L_0x0101
            if (r2 == 0) goto L_0x0101
            r2 = r3
            goto L_0x0102
        L_0x0101:
            r2 = r5
        L_0x0102:
            if (r2 == 0) goto L_0x019b
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r6 = "PostCall.promptUserToSendMessage"
            java.lang.String r7 = "returned from call, showing post call SnackBar"
            com.android.dialer.common.LogUtil.m9i(r6, r7, r2)
            r2 = 2131821221(0x7f1102a5, float:1.927518E38)
            java.lang.String r2 = r0.getString(r2)
            com.android.dialer.enrichedcall.EnrichedCallComponent r7 = com.android.dialer.enrichedcall.EnrichedCallComponent.get(r17)
            com.android.dialer.enrichedcall.EnrichedCallManager r7 = r7.getEnrichedCallManager()
            java.lang.String r8 = getPhoneNumber(r17)
            com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub r7 = (com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub) r7
            r7.getCapabilities(r8)
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.String r8 = getPhoneNumber(r17)
            java.lang.String r8 = com.android.dialer.common.LogUtil.sanitizePhoneNumber(r8)
            r7[r5] = r8
            r8 = 0
            r7[r3] = r8
            java.lang.String r3 = "number: %s, capabilities: %s"
            com.android.dialer.common.LogUtil.m9i(r6, r3, r7)
            r3 = 2131821227(0x7f1102ab, float:1.9275191E38)
            java.lang.String r3 = r0.getString(r3)
            java.lang.String r6 = getPhoneNumber(r17)
            com.android.dialer.common.Assert.isNotNull(r6)
            com.android.dialer.postcall.-$$Lambda$PostCall$-EKt8P9xNJMLZNB7koiWygqo5a4 r7 = new com.android.dialer.postcall.-$$Lambda$PostCall$-EKt8P9xNJMLZNB7koiWygqo5a4
            r7.<init>(r0, r6, r5)
            com.android.dialer.configprovider.ConfigProviderComponent r5 = com.android.dialer.configprovider.ConfigProviderComponent.get(r17)
            com.android.dialer.configprovider.ConfigProvider r5 = r5.getConfigProvider()
            r8 = 8000(0x1f40, double:3.9525E-320)
            com.android.dialer.configprovider.SharedPrefConfigProvider r5 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r5
            java.lang.String r6 = "post_call_prompt_duration_ms"
            long r5 = r5.getLong(r6, r8)
            int r5 = (int) r5
            android.support.design.widget.Snackbar r1 = android.support.design.widget.Snackbar.make((android.view.View) r1, (java.lang.CharSequence) r2, (int) r5)
            r1.setAction((java.lang.CharSequence) r3, (android.view.View.OnClickListener) r7)
            android.content.res.Resources r2 = r17.getResources()
            r3 = 2131099739(0x7f06005b, float:1.781184E38)
            int r2 = r2.getColor(r3)
            r1.setActionTextColor(r2)
            activeSnackbar = r1
            android.support.design.widget.Snackbar r1 = activeSnackbar
            r1.show()
            com.android.dialer.logging.LoggingBindings r1 = com.android.dialer.logging.Logger.get(r17)
            com.android.dialer.logging.DialerImpression$Type r2 = com.android.dialer.logging.DialerImpression$Type.POST_CALL_PROMPT_USER_TO_SEND_MESSAGE
            com.android.dialer.logging.LoggingBindingsStub r1 = (com.android.dialer.logging.LoggingBindingsStub) r1
            r1.logImpression(r2)
            com.android.dialer.storage.StorageComponent r0 = com.android.dialer.storage.StorageComponent.get(r17)
            android.content.SharedPreferences r0 = r0.unencryptedSharedPrefs()
            android.content.SharedPreferences$Editor r0 = r0.edit()
            android.content.SharedPreferences$Editor r0 = r0.remove(r4)
            r0.apply()
            goto L_0x019e
        L_0x019b:
            clear(r17)
        L_0x019e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.postcall.PostCall.promptUserForMessageIfNecessary(android.app.Activity, android.view.View):void");
    }

    public static void restartPerformanceRecordingIfARecentCallExist(Context context) {
        if (StorageComponent.get(context).unencryptedSharedPrefs().getLong("post_call_call_disconnect_time", -1) != -1 && PerformanceReport.isRecording()) {
            PerformanceReport.startRecording();
        }
    }
}
