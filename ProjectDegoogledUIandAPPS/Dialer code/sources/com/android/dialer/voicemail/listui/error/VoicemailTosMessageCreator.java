package com.android.dialer.voicemail.listui.error;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import android.text.Layout;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.URLSpan;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.compat.telephony.TelephonyManagerCompat;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.VoicemailClient;
import com.android.voicemail.VoicemailComponent;
import java.util.Locale;

public class VoicemailTosMessageCreator {
    /* access modifiers changed from: private */
    public final Context context;
    private final SharedPreferences preferences;
    /* access modifiers changed from: private */
    public final VoicemailStatus status;
    /* access modifiers changed from: private */
    public final VoicemailStatusReader statusReader;

    VoicemailTosMessageCreator(Context context2, VoicemailStatus voicemailStatus, VoicemailStatusReader voicemailStatusReader) {
        this.context = context2;
        this.status = voicemailStatus;
        this.statusReader = voicemailStatusReader;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context2.getApplicationContext());
    }

    static /* synthetic */ void access$100(VoicemailTosMessageCreator voicemailTosMessageCreator) {
        if (voicemailTosMessageCreator.isVvm3()) {
            ((LoggingBindingsStub) Logger.get(voicemailTosMessageCreator.context)).logImpression(DialerImpression$Type.VOICEMAIL_VVM3_TOS_V2_DECLINE_CLICKED);
            return;
        }
        ((LoggingBindingsStub) Logger.get(voicemailTosMessageCreator.context)).logImpression(DialerImpression$Type.VOICEMAIL_DIALER_TOS_DECLINE_CLICKED);
    }

    static /* synthetic */ void access$200(VoicemailTosMessageCreator voicemailTosMessageCreator, final PhoneAccountHandle phoneAccountHandle) {
        if (!voicemailTosMessageCreator.isVvm3() || -100 != voicemailTosMessageCreator.status.configurationState) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("showing decline ToS dialog, status=");
            outline13.append(voicemailTosMessageCreator.status);
            LogUtil.m9i("VoicemailTosMessageCreator.showDeclineVerizonTosDialog", outline13.toString(), new Object[0]);
            final TelephonyManager telephonyManager = (TelephonyManager) voicemailTosMessageCreator.context.getSystemService(TelephonyManager.class);
            AlertDialog.Builder builder = new AlertDialog.Builder(voicemailTosMessageCreator.context);
            builder.setTitle(R.string.terms_and_conditions_decline_dialog_title);
            builder.setMessage(voicemailTosMessageCreator.isVvm3() ? R.string.verizon_terms_and_conditions_decline_dialog_message : R.string.dialer_terms_and_conditions_decline_dialog_message);
            builder.setPositiveButton(voicemailTosMessageCreator.isVvm3() ? R.string.verizon_terms_and_conditions_decline_dialog_downgrade : R.string.dialer_terms_and_conditions_decline_dialog_downgrade, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((LoggingBindingsStub) Logger.get(VoicemailTosMessageCreator.this.context)).logImpression(DialerImpression$Type.VOICEMAIL_VVM3_TOS_DECLINED);
                    VoicemailClient voicemailClient = VoicemailComponent.get(VoicemailTosMessageCreator.this.context).getVoicemailClient();
                    if (voicemailClient.isVoicemailModuleEnabled()) {
                        voicemailClient.setVoicemailEnabled(VoicemailTosMessageCreator.this.context, VoicemailTosMessageCreator.this.status.getPhoneAccountHandle(), false);
                    } else {
                        TelephonyManagerCompat.setVisualVoicemailEnabled(telephonyManager, phoneAccountHandle, false);
                    }
                }
            });
            builder.setNegativeButton(17039360, new DialogInterface.OnClickListener(voicemailTosMessageCreator) {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.setCancelable(true);
            builder.show();
            return;
        }
        LogUtil.m9i("VoicemailTosMessageCreator.showDeclineTosDialog", "PIN_NOT_SET, showing set PIN dialog", new Object[0]);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(voicemailTosMessageCreator.context);
        builder2.setMessage(R.string.verizon_terms_and_conditions_decline_set_pin_dialog_message);
        builder2.setPositiveButton(R.string.verizon_terms_and_conditions_decline_set_pin_dialog_set_pin, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((LoggingBindingsStub) Logger.get(VoicemailTosMessageCreator.this.context)).logImpression(DialerImpression$Type.VOICEMAIL_VVM3_TOS_DECLINE_CHANGE_PIN_SHOWN);
                Intent intent = new Intent("android.telephony.action.CONFIGURE_VOICEMAIL");
                intent.putExtra("android.telephony.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
                VoicemailTosMessageCreator.this.context.startActivity(intent);
            }
        });
        builder2.setNegativeButton(17039360, new DialogInterface.OnClickListener(voicemailTosMessageCreator) {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder2.setCancelable(true);
        builder2.show();
    }

    static /* synthetic */ void access$500(VoicemailTosMessageCreator voicemailTosMessageCreator) {
        if (voicemailTosMessageCreator.isVvm3()) {
            voicemailTosMessageCreator.preferences.edit().putInt("vvm3_tos_version_accepted", 2).apply();
        } else {
            voicemailTosMessageCreator.preferences.edit().putInt("dialer_tos_version_accepted", 1).apply();
        }
        VoicemailComponent.get(voicemailTosMessageCreator.context).getVoicemailClient().onTosAccepted(voicemailTosMessageCreator.context, new PhoneAccountHandle(ComponentName.unflattenFromString(voicemailTosMessageCreator.status.phoneAccountComponentName), voicemailTosMessageCreator.status.phoneAccountId));
    }

    static /* synthetic */ void access$700(VoicemailTosMessageCreator voicemailTosMessageCreator) {
        if (voicemailTosMessageCreator.isVvm3()) {
            ((LoggingBindingsStub) Logger.get(voicemailTosMessageCreator.context)).logImpression(DialerImpression$Type.VOICEMAIL_VVM3_TOS_V2_ACCEPTED);
            return;
        }
        ((LoggingBindingsStub) Logger.get(voicemailTosMessageCreator.context)).logImpression(DialerImpression$Type.VOICEMAIL_DIALER_TOS_ACCEPTED);
    }

    private SpannableString addLink(SpannableString spannableString, String str, String str2) {
        int indexOf;
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && (indexOf = spannableString.toString().indexOf(str)) != -1) {
            int length = str.length() + indexOf;
            spannableString.setSpan(new URLSpan(str2), indexOf, length, 33);
            spannableString.setSpan(new TextAppearanceSpan(this.context, R.style.TosLinkStyle), indexOf, length, 33);
        }
        return spannableString;
    }

    private String getLearnMoreUrl() {
        return ((SharedPrefConfigProvider) ConfigProviderComponent.get(this.context).getConfigProvider()).getString("voicemail_transcription_learn_more_url", this.context.getString(R.string.dialer_terms_and_conditions_learn_more_url));
    }

    private CharSequence getNewUserTosMessageText() {
        String str;
        String str2;
        if (isVvm3()) {
            String string = this.context.getString(R.string.verizon_terms_and_conditions_policy_url);
            if (useSpanish()) {
                str2 = this.context.getString(R.string.f91verizon_terms_and_conditions_11_spanish, new Object[]{string});
            } else {
                str2 = this.context.getString(R.string.f90verizon_terms_and_conditions_11_english, new Object[]{string});
            }
            Context context2 = this.context;
            SpannableString spannableString = new SpannableString(context2.getString(R.string.verizon_terms_and_conditions_message, new Object[]{context2.getString(R.string.f89dialer_terms_and_conditions_for_verizon_10), str2}));
            int length = spannableString.length() - str2.length();
            spannableString.setSpan(new TextAppearanceSpan(this.context, R.style.TosDetailsTextStyle), length, str2.length() + length, 33);
            String string2 = this.context.getString(R.string.verizon_terms_and_conditions_policy_url);
            addLink(spannableString, string2, string2);
            return spannableString;
        }
        Context context3 = this.context;
        Object[] objArr = new Object[1];
        if (!isVoicemailTranscriptionAvailable()) {
            str = "";
        } else {
            String string3 = this.context.getString(R.string.dialer_terms_and_conditions_learn_more);
            str = this.context.getString(R.string.f88dialer_terms_and_conditions_10, new Object[]{string3});
        }
        objArr[0] = str;
        String string4 = context3.getString(R.string.dialer_terms_and_conditions_message, objArr);
        SpannableString spannableString2 = new SpannableString(string4);
        spannableString2.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, string4.length(), 33);
        addLink(spannableString2, this.context.getString(R.string.dialer_terms_and_conditions_learn_more), getLearnMoreUrl());
        return spannableString2;
    }

    /* access modifiers changed from: private */
    public boolean isVoicemailTranscriptionAvailable() {
        return VoicemailComponent.get(this.context).getVoicemailClient().isVoicemailTranscriptionAvailable(this.context, this.status.getPhoneAccountHandle());
    }

    private boolean isVvm3() {
        return "vvm_type_vvm3".equals(this.status.type);
    }

    private boolean useSpanish() {
        return Locale.getDefault().getLanguage().equals(new Locale("es").getLanguage());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0289 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0082 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01b9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.dialer.voicemail.listui.error.VoicemailErrorMessage maybeCreateTosMessage() {
        /*
            r13 = this;
            com.android.dialer.voicemail.listui.error.VoicemailStatus r0 = r13.status
            java.lang.String r0 = r0.type
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x000a
            goto L_0x0042
        L_0x000a:
            r4 = -1
            int r5 = r0.hashCode()
            r6 = -1479165891(0xffffffffa7d5b83d, float:-5.9319128E-15)
            if (r5 == r6) goto L_0x0033
            r6 = -1478817107(0xffffffffa7db0aad, float:-6.0796285E-15)
            if (r5 == r6) goto L_0x0029
            r6 = -1478600199(0xffffffffa7de59f9, float:-6.1714926E-15)
            if (r5 == r6) goto L_0x001f
            goto L_0x003c
        L_0x001f:
            java.lang.String r5 = "vvm_type_vvm3"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x003c
            r4 = r1
            goto L_0x003c
        L_0x0029:
            java.lang.String r5 = "vvm_type_omtp"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x003c
            r4 = r2
            goto L_0x003c
        L_0x0033:
            java.lang.String r5 = "vvm_type_cvvm"
            boolean r0 = r0.equals(r5)
            if (r0 == 0) goto L_0x003c
            r4 = r3
        L_0x003c:
            if (r4 == 0) goto L_0x0044
            if (r4 == r3) goto L_0x0044
            if (r4 == r1) goto L_0x0044
        L_0x0042:
            r0 = r2
            goto L_0x0045
        L_0x0044:
            r0 = r3
        L_0x0045:
            java.lang.String r4 = "VoicemailTosMessageCreator.canShowTos"
            if (r0 != 0) goto L_0x0060
            java.lang.String r0 = "unsupported type: "
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            com.android.dialer.voicemail.listui.error.VoicemailStatus r5 = r13.status
            java.lang.String r5 = r5.type
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r5 = new java.lang.Object[r2]
            com.android.dialer.common.LogUtil.m9i(r4, r0, r5)
            goto L_0x007e
        L_0x0060:
            com.android.dialer.voicemail.listui.error.VoicemailStatus r0 = r13.status
            android.telecom.PhoneAccountHandle r0 = r0.getPhoneAccountHandle()
            if (r0 == 0) goto L_0x0077
            com.android.dialer.voicemail.listui.error.VoicemailStatus r0 = r13.status
            android.telecom.PhoneAccountHandle r0 = r0.getPhoneAccountHandle()
            android.content.ComponentName r0 = r0.getComponentName()
            if (r0 != 0) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r0 = r3
            goto L_0x007f
        L_0x0077:
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r5 = "invalid phone account"
            com.android.dialer.common.LogUtil.m9i(r4, r5, r0)
        L_0x007e:
            r0 = r2
        L_0x007f:
            r4 = 0
            if (r0 != 0) goto L_0x0083
            return r4
        L_0x0083:
            boolean r0 = r13.isVvm3()
            if (r0 == 0) goto L_0x0094
            android.content.SharedPreferences r0 = r13.preferences
            java.lang.String r5 = "vvm3_tos_version_accepted"
            int r0 = r0.getInt(r5, r2)
            if (r0 < r1) goto L_0x00a0
            goto L_0x009e
        L_0x0094:
            android.content.SharedPreferences r0 = r13.preferences
            java.lang.String r5 = "dialer_tos_version_accepted"
            int r0 = r0.getInt(r5, r2)
            if (r0 < r3) goto L_0x00a0
        L_0x009e:
            r0 = r3
            goto L_0x00a1
        L_0x00a0:
            r0 = r2
        L_0x00a1:
            java.lang.String r5 = "VoicemailTosMessageCreator.shouldShowTos"
            java.lang.String r6 = "dialer_feature_version_acknowledged"
            if (r0 == 0) goto L_0x00af
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r7 = "already accepted TOS"
            com.android.dialer.common.LogUtil.m9i(r5, r7, r0)
            goto L_0x00d9
        L_0x00af:
            boolean r0 = r13.isVvm3()
            if (r0 == 0) goto L_0x00bd
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r7 = "showing TOS for verizon"
            com.android.dialer.common.LogUtil.m9i(r5, r7, r0)
            goto L_0x00d7
        L_0x00bd:
            boolean r0 = r13.isVoicemailTranscriptionAvailable()
            if (r0 == 0) goto L_0x00d9
            android.content.SharedPreferences r0 = r13.preferences
            int r0 = r0.getInt(r6, r2)
            if (r0 != r3) goto L_0x00cd
            r0 = r3
            goto L_0x00ce
        L_0x00cd:
            r0 = r2
        L_0x00ce:
            if (r0 != 0) goto L_0x00d9
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r7 = "showing TOS for Google transcription users"
            com.android.dialer.common.LogUtil.m9i(r5, r7, r0)
        L_0x00d7:
            r0 = r3
            goto L_0x00da
        L_0x00d9:
            r0 = r2
        L_0x00da:
            r5 = 2131231116(0x7f08018c, float:1.8078304E38)
            r7 = 2131821471(0x7f11039f, float:1.9275686E38)
            if (r0 == 0) goto L_0x01b9
            boolean r0 = r13.isVvm3()
            if (r0 == 0) goto L_0x00f6
            android.content.Context r0 = r13.context
            com.android.dialer.logging.LoggingBindings r0 = com.android.dialer.logging.Logger.get(r0)
            com.android.dialer.logging.DialerImpression$Type r4 = com.android.dialer.logging.DialerImpression$Type.VOICEMAIL_VVM3_TOS_V2_CREATED
            com.android.dialer.logging.LoggingBindingsStub r0 = (com.android.dialer.logging.LoggingBindingsStub) r0
            r0.logImpression(r4)
            goto L_0x0103
        L_0x00f6:
            android.content.Context r0 = r13.context
            com.android.dialer.logging.LoggingBindings r0 = com.android.dialer.logging.Logger.get(r0)
            com.android.dialer.logging.DialerImpression$Type r4 = com.android.dialer.logging.DialerImpression$Type.VOICEMAIL_DIALER_TOS_CREATED
            com.android.dialer.logging.LoggingBindingsStub r0 = (com.android.dialer.logging.LoggingBindingsStub) r0
            r0.logImpression(r4)
        L_0x0103:
            com.android.dialer.voicemail.listui.error.VoicemailTosMessage r0 = new com.android.dialer.voicemail.listui.error.VoicemailTosMessage
            boolean r4 = r13.isVvm3()
            if (r4 == 0) goto L_0x0112
            android.content.Context r4 = r13.context
            java.lang.String r4 = r4.getString(r7)
            goto L_0x011b
        L_0x0112:
            android.content.Context r4 = r13.context
            r6 = 2131820917(0x7f110175, float:1.9274562E38)
            java.lang.String r4 = r4.getString(r6)
        L_0x011b:
            java.lang.CharSequence r6 = r13.getNewUserTosMessageText()
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action[] r1 = new com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.Action[r1]
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action r7 = new com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action
            boolean r8 = r13.isVvm3()
            if (r8 == 0) goto L_0x0143
            boolean r8 = r13.useSpanish()
            if (r8 == 0) goto L_0x0139
            android.content.Context r8 = r13.context
            r9 = 2131821468(0x7f11039c, float:1.927568E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x015c
        L_0x0139:
            android.content.Context r8 = r13.context
            r9 = 2131821465(0x7f110399, float:1.9275674E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x015c
        L_0x0143:
            boolean r8 = r13.useSpanish()
            if (r8 == 0) goto L_0x0153
            android.content.Context r8 = r13.context
            r9 = 2131820908(0x7f11016c, float:1.9274544E38)
            java.lang.String r8 = r8.getString(r9)
            goto L_0x015c
        L_0x0153:
            android.content.Context r8 = r13.context
            r9 = 2131820907(0x7f11016b, float:1.9274542E38)
            java.lang.String r8 = r8.getString(r9)
        L_0x015c:
            com.android.dialer.voicemail.listui.error.VoicemailTosMessageCreator$1 r9 = new com.android.dialer.voicemail.listui.error.VoicemailTosMessageCreator$1
            r9.<init>()
            r7.<init>(r8, r9)
            r1[r2] = r7
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action r2 = new com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action
            boolean r7 = r13.isVvm3()
            if (r7 == 0) goto L_0x0188
            boolean r7 = r13.useSpanish()
            if (r7 == 0) goto L_0x017e
            android.content.Context r7 = r13.context
            r8 = 2131821462(0x7f110396, float:1.9275668E38)
            java.lang.String r7 = r7.getString(r8)
            goto L_0x01a1
        L_0x017e:
            android.content.Context r7 = r13.context
            r8 = 2131821461(0x7f110395, float:1.9275666E38)
            java.lang.String r7 = r7.getString(r8)
            goto L_0x01a1
        L_0x0188:
            boolean r7 = r13.useSpanish()
            if (r7 == 0) goto L_0x0198
            android.content.Context r7 = r13.context
            r8 = 2131820904(0x7f110168, float:1.9274536E38)
            java.lang.String r7 = r7.getString(r8)
            goto L_0x01a1
        L_0x0198:
            android.content.Context r7 = r13.context
            r8 = 2131820903(0x7f110167, float:1.9274534E38)
            java.lang.String r7 = r7.getString(r8)
        L_0x01a1:
            com.android.dialer.voicemail.listui.error.VoicemailTosMessageCreator$2 r8 = new com.android.dialer.voicemail.listui.error.VoicemailTosMessageCreator$2
            r8.<init>()
            r2.<init>(r7, r8, r3)
            r1[r3] = r2
            r0.<init>(r4, r6, r1)
            r0.setModal(r3)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r5)
            r0.setImageResourceId(r13)
            return r0
        L_0x01b9:
            boolean r0 = r13.isVvm3()
            if (r0 == 0) goto L_0x01c0
            goto L_0x01c8
        L_0x01c0:
            android.content.SharedPreferences r0 = r13.preferences
            int r0 = r0.getInt(r6, r2)
            if (r0 < r1) goto L_0x01ca
        L_0x01c8:
            r0 = r3
            goto L_0x01cb
        L_0x01ca:
            r0 = r2
        L_0x01cb:
            java.lang.String r6 = "VoicemailTosMessageCreator.shouldShowPromo"
            if (r0 == 0) goto L_0x01d7
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r8 = "already acknowledeged latest features"
            com.android.dialer.common.LogUtil.m9i(r6, r8, r0)
            goto L_0x01e6
        L_0x01d7:
            boolean r0 = r13.isVoicemailTranscriptionAvailable()
            if (r0 == 0) goto L_0x01e6
            java.lang.Object[] r0 = new java.lang.Object[r2]
            java.lang.String r8 = "showing promo for Google transcription users"
            com.android.dialer.common.LogUtil.m9i(r6, r8, r0)
            r0 = r3
            goto L_0x01e7
        L_0x01e6:
            r0 = r2
        L_0x01e7:
            if (r0 == 0) goto L_0x0289
            com.android.dialer.voicemail.listui.error.VoicemailTosMessage r0 = new com.android.dialer.voicemail.listui.error.VoicemailTosMessage
            boolean r4 = r13.isVvm3()
            if (r4 == 0) goto L_0x01f8
            android.content.Context r4 = r13.context
            java.lang.String r4 = r4.getString(r7)
            goto L_0x0201
        L_0x01f8:
            android.content.Context r4 = r13.context
            r6 = 2131820912(0x7f110170, float:1.9274552E38)
            java.lang.String r4 = r4.getString(r6)
        L_0x0201:
            android.content.Context r6 = r13.context
            java.lang.Object[] r7 = new java.lang.Object[r3]
            boolean r8 = r13.isVoicemailTranscriptionAvailable()
            r9 = 2131820914(0x7f110172, float:1.9274556E38)
            if (r8 != 0) goto L_0x0211
            java.lang.String r8 = ""
            goto L_0x0224
        L_0x0211:
            android.content.Context r8 = r13.context
            java.lang.String r8 = r8.getString(r9)
            android.content.Context r10 = r13.context
            r11 = 2131820909(0x7f11016d, float:1.9274546E38)
            java.lang.Object[] r12 = new java.lang.Object[r3]
            r12[r2] = r8
            java.lang.String r8 = r10.getString(r11, r12)
        L_0x0224:
            r7[r2] = r8
            r8 = 2131820916(0x7f110174, float:1.927456E38)
            java.lang.String r6 = r6.getString(r8, r7)
            android.text.SpannableString r7 = new android.text.SpannableString
            r7.<init>(r6)
            android.text.style.AlignmentSpan$Standard r8 = new android.text.style.AlignmentSpan$Standard
            android.text.Layout$Alignment r10 = android.text.Layout.Alignment.ALIGN_CENTER
            r8.<init>(r10)
            int r6 = r6.length()
            r10 = 33
            r7.setSpan(r8, r2, r6, r10)
            android.content.Context r6 = r13.context
            java.lang.String r6 = r6.getString(r9)
            java.lang.String r8 = r13.getLearnMoreUrl()
            r13.addLink(r7, r6, r8)
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action[] r1 = new com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.Action[r1]
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action r6 = new com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action
            android.content.Context r8 = r13.context
            r9 = 2131820911(0x7f11016f, float:1.927455E38)
            java.lang.String r8 = r8.getString(r9)
            com.android.dialer.voicemail.listui.error.VoicemailTosMessageCreator$3 r9 = new com.android.dialer.voicemail.listui.error.VoicemailTosMessageCreator$3
            r9.<init>()
            r6.<init>(r8, r9)
            r1[r2] = r6
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action r2 = new com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action
            android.content.Context r6 = r13.context
            r8 = 2131820910(0x7f11016e, float:1.9274548E38)
            java.lang.String r6 = r6.getString(r8)
            com.android.dialer.voicemail.listui.error.VoicemailTosMessageCreator$4 r8 = new com.android.dialer.voicemail.listui.error.VoicemailTosMessageCreator$4
            r8.<init>()
            r2.<init>(r6, r8, r3)
            r1[r3] = r2
            r0.<init>(r4, r7, r1)
            r0.setModal(r3)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r5)
            r0.setImageResourceId(r13)
            return r0
        L_0x0289:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.voicemail.listui.error.VoicemailTosMessageCreator.maybeCreateTosMessage():com.android.dialer.voicemail.listui.error.VoicemailErrorMessage");
    }
}
