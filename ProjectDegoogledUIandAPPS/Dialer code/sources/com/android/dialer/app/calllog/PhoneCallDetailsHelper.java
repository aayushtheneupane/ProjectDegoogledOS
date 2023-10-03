package com.android.dialer.app.calllog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.dialer.R;
import com.android.dialer.app.calllog.calllogcache.CallLogCache;
import com.android.dialer.calllogutils.PhoneCallDetails;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.ContactSource$Type;
import com.android.dialer.phonenumbercache.PhoneNumberCache;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.spannable.ContentWithLearnMoreSpanner;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.dialer.util.DialerUtils;
import com.android.voicemail.VoicemailComponent;
import com.android.voicemail.impl.transcribe.TranscriptionRatingHelper;
import com.google.internal.communications.voicemailtranscription.p008v1.TranscriptionRatingValue;
import java.util.ArrayList;
import java.util.Calendar;

public class PhoneCallDetailsHelper implements TranscriptionRatingHelper.SuccessListener, TranscriptionRatingHelper.FailureListener {
    private final Calendar calendar;
    private final CallLogCache callLogCache;
    /* access modifiers changed from: private */
    public final Context context;
    private Long currentTimeMillisForTest;
    private ArrayList<CharSequence> descriptionItems = new ArrayList<>();
    private CharSequence phoneTypeLabelForTest;
    private final Resources resources;

    public PhoneCallDetailsHelper(Context context2, Resources resources2, CallLogCache callLogCache2) {
        this.context = context2;
        this.resources = resources2;
        this.callLogCache = callLogCache2;
        this.calendar = Calendar.getInstance();
        PhoneNumberCache.get(context2).getCachedNumberLookupService();
    }

    private static int dpsToPixels(Context context2, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context2.getResources().getDisplayMetrics());
    }

    static boolean hasSeenVoicemailDonationPromo(Context context2) {
        return StorageComponent.get(context2.getApplicationContext()).unencryptedSharedPrefs().getBoolean("pref_voicemail_donation_promo_shown_key", false);
    }

    private void recordTranscriptionRating(final TranscriptionRatingValue transcriptionRatingValue, final PhoneCallDetails phoneCallDetails, final View view) {
        LogUtil.enterBlock("PhoneCallDetailsHelper.recordTranscriptionRating");
        Context context2 = this.context;
        if (VoicemailComponent.get(context2).getVoicemailClient().isVoicemailDonationAvailable(context2, phoneCallDetails.accountHandle) && !hasSeenVoicemailDonationPromo(context2)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setMessage(new ContentWithLearnMoreSpanner(this.context).create(this.context.getString(R.string.voicemail_donation_promo_content), this.context.getString(R.string.voicemail_donation_promo_learn_more_url)));
            builder.setPositiveButton(R.string.voicemail_donation_promo_opt_in, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    LogUtil.m9i("PhoneCallDetailsHelper.showVoicemailDonationPromo", "onClick", new Object[0]);
                    dialogInterface.cancel();
                    StorageComponent.get(PhoneCallDetailsHelper.this.context.getApplicationContext()).unencryptedSharedPrefs().edit().putBoolean("pref_voicemail_donation_promo_shown_key", true).apply();
                    VoicemailComponent.get(PhoneCallDetailsHelper.this.context).getVoicemailClient().setVoicemailDonationEnabled(PhoneCallDetailsHelper.this.context, phoneCallDetails.accountHandle, true);
                    Context access$000 = PhoneCallDetailsHelper.this.context;
                    TranscriptionRatingValue transcriptionRatingValue = transcriptionRatingValue;
                    Uri parse = Uri.parse(phoneCallDetails.voicemailUri);
                    PhoneCallDetailsHelper phoneCallDetailsHelper = PhoneCallDetailsHelper.this;
                    TranscriptionRatingHelper.sendRating(access$000, transcriptionRatingValue, parse, new TranscriptionRatingHelper.SuccessListener() {
                        public final void onRatingSuccess(Uri uri) {
                            PhoneCallDetailsHelper.this.onRatingSuccess(uri);
                        }
                    }, new TranscriptionRatingHelper.FailureListener() {
                        public final void onRatingFailure(Throwable th) {
                            PhoneCallDetailsHelper.this.onRatingFailure(th);
                        }
                    });
                    view.setVisibility(8);
                }
            });
            builder.setNegativeButton(R.string.voicemail_donation_promo_opt_out, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    VoicemailComponent.get(PhoneCallDetailsHelper.this.context).getVoicemailClient().setVoicemailDonationEnabled(PhoneCallDetailsHelper.this.context, phoneCallDetails.accountHandle, false);
                    dialogInterface.cancel();
                    StorageComponent.get(PhoneCallDetailsHelper.this.context.getApplicationContext()).unencryptedSharedPrefs().edit().putBoolean("pref_voicemail_donation_promo_shown_key", true).apply();
                    view.setVisibility(8);
                }
            });
            builder.setCancelable(true);
            AlertDialog create = builder.create();
            TextView textView = new TextView(this.context);
            textView.setText(R.string.voicemail_donation_promo_title);
            textView.setTypeface(Typeface.create("sans-serif-medium", 0));
            textView.setTextSize(2, 20.0f);
            textView.setTextColor(ContextCompat.getColor(this.context, R.color.dialer_primary_text_color));
            textView.setPadding(dpsToPixels(this.context, 24), dpsToPixels(this.context, 10), dpsToPixels(this.context, 24), dpsToPixels(this.context, 0));
            create.setCustomTitle(textView);
            create.show();
            TextView textView2 = (TextView) create.findViewById(16908299);
            textView2.setLineSpacing(0.0f, 1.2f);
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            Button button = create.getButton(-1);
            if (button != null) {
                button.setTextColor(((AospThemeImpl) ThemeComponent.get(this.context).theme()).getColorPrimary());
            }
            Button button2 = create.getButton(-2);
            if (button2 != null) {
                button2.setTextColor(((AospThemeImpl) ThemeComponent.get(this.context).theme()).getTextColorSecondary());
                return;
            }
            return;
        }
        TranscriptionRatingHelper.sendRating(this.context, transcriptionRatingValue, Uri.parse(phoneCallDetails.voicemailUri), new TranscriptionRatingHelper.SuccessListener() {
            public final void onRatingSuccess(Uri uri) {
                PhoneCallDetailsHelper.this.onRatingSuccess(uri);
            }
        }, new TranscriptionRatingHelper.FailureListener() {
            public final void onRatingFailure(Throwable th) {
                PhoneCallDetailsHelper.this.onRatingFailure(th);
            }
        });
    }

    public CharSequence getCallDate(PhoneCallDetails phoneCallDetails) {
        long j;
        String str;
        long j2;
        int i = 4;
        if (phoneCallDetails.callTypes[0] == 4) {
            Resources resources2 = this.resources;
            Object[] objArr = new Object[2];
            long j3 = phoneCallDetails.date;
            if (DateUtils.isToday(j3)) {
                str = this.resources.getString(R.string.voicemailCallLogToday);
            } else {
                Context context2 = this.context;
                Calendar calendar2 = this.calendar;
                Long l = this.currentTimeMillisForTest;
                if (l == null) {
                    j2 = System.currentTimeMillis();
                } else {
                    j2 = l.longValue();
                }
                calendar2.setTimeInMillis(j2);
                int i2 = this.calendar.get(1);
                this.calendar.setTimeInMillis(j3);
                if (!(i2 != this.calendar.get(1))) {
                    i = 8;
                }
                str = DateUtils.formatDateTime(context2, j3, i | 65552);
            }
            objArr[0] = str;
            objArr[1] = DateUtils.formatDateTime(this.context, phoneCallDetails.date, 1);
            return resources2.getString(R.string.voicemailCallLogDateTimeFormat, objArr);
        }
        long j4 = phoneCallDetails.date;
        Long l2 = this.currentTimeMillisForTest;
        if (l2 == null) {
            j = System.currentTimeMillis();
        } else {
            j = l2.longValue();
        }
        return DateUtils.getRelativeTimeSpanString(j4, j, 60000, 262144);
    }

    public CharSequence getCallLocationAndDate(PhoneCallDetails phoneCallDetails) {
        this.descriptionItems.clear();
        if (phoneCallDetails.callTypes[0] != 4) {
            CharSequence callTypeOrLocation = getCallTypeOrLocation(phoneCallDetails);
            if (!TextUtils.isEmpty(callTypeOrLocation)) {
                this.descriptionItems.add(callTypeOrLocation);
            }
        }
        this.descriptionItems.add(getCallDate(phoneCallDetails));
        return DialerUtils.join(this.descriptionItems);
    }

    public CharSequence getCallTypeOrLocation(PhoneCallDetails phoneCallDetails) {
        if (phoneCallDetails.isSpam) {
            return this.resources.getString(R.string.spam_number_call_log_label);
        }
        if (phoneCallDetails.isBlocked) {
            return this.resources.getString(R.string.blocked_number_call_log_label);
        }
        CharSequence charSequence = null;
        if (!TextUtils.isEmpty(phoneCallDetails.number) && !PhoneNumberHelper.isUriNumber(phoneCallDetails.number.toString()) && !this.callLogCache.isVoicemailNumber(phoneCallDetails.accountHandle, phoneCallDetails.number)) {
            boolean z = false;
            if (!TextUtils.isEmpty(phoneCallDetails.geocode) && (phoneCallDetails.sourceType == ContactSource$Type.SOURCE_TYPE_CEQUINT_CALLER_ID || TextUtils.isEmpty(phoneCallDetails.namePrimary))) {
                z = true;
            }
            if (z) {
                charSequence = phoneCallDetails.geocode;
            } else if ((phoneCallDetails.numberType != 0 || !TextUtils.isEmpty(phoneCallDetails.numberLabel)) && (charSequence = this.phoneTypeLabelForTest) == null) {
                charSequence = ContactsContract.CommonDataKinds.Phone.getTypeLabel(this.resources, phoneCallDetails.numberType, phoneCallDetails.numberLabel);
            }
        }
        return (TextUtils.isEmpty(phoneCallDetails.namePrimary) || !TextUtils.isEmpty(charSequence)) ? charSequence : phoneCallDetails.displayNumber;
    }

    public /* synthetic */ void lambda$setPhoneCallDetails$0$PhoneCallDetailsHelper(PhoneCallDetails phoneCallDetails, View view, View view2) {
        recordTranscriptionRating(TranscriptionRatingValue.GOOD_TRANSCRIPTION, phoneCallDetails, view);
    }

    public /* synthetic */ void lambda$setPhoneCallDetails$1$PhoneCallDetailsHelper(PhoneCallDetails phoneCallDetails, View view, View view2) {
        recordTranscriptionRating(TranscriptionRatingValue.BAD_TRANSCRIPTION, phoneCallDetails, view);
    }

    public void onRatingFailure(Throwable th) {
        LogUtil.m7e("PhoneCallDetailsHelper.onRatingFailure", "failed to send rating", th);
    }

    public void onRatingSuccess(Uri uri) {
        LogUtil.enterBlock("PhoneCallDetailsHelper.onRatingSuccess");
        Toast makeText = Toast.makeText(this.context, R.string.voicemail_transcription_rating_thanks, 1);
        makeText.setGravity(81, 0, 50);
        makeText.show();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0240, code lost:
        if (hasSeenVoicemailDonationPromo(r0.context) == false) goto L_0x0242;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x028c  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0244  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x026d  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0270  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPhoneCallDetails(com.android.dialer.app.calllog.PhoneCallDetailsViews r20, com.android.dialer.calllogutils.PhoneCallDetails r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            com.android.dialer.calllogutils.CallTypeIconsView r3 = r1.callTypeIcons
            r3.clear()
            int[] r3 = r2.callTypes
            int r3 = r3.length
            r4 = 0
            r5 = r4
            r6 = r5
        L_0x0011:
            r7 = 4
            r8 = 3
            r9 = 1
            if (r5 >= r3) goto L_0x002f
            if (r5 >= r8) goto L_0x002f
            com.android.dialer.calllogutils.CallTypeIconsView r8 = r1.callTypeIcons
            int[] r10 = r2.callTypes
            r10 = r10[r5]
            r8.add(r10)
            if (r5 != 0) goto L_0x002c
            int[] r6 = r2.callTypes
            r6 = r6[r5]
            if (r6 != r7) goto L_0x002a
            goto L_0x002b
        L_0x002a:
            r9 = r4
        L_0x002b:
            r6 = r9
        L_0x002c:
            int r5 = r5 + 1
            goto L_0x0011
        L_0x002f:
            com.android.dialer.calllogutils.CallTypeIconsView r5 = r1.callTypeIcons
            int r10 = r2.features
            r10 = r10 & r9
            if (r10 != r9) goto L_0x0038
            r10 = r9
            goto L_0x0039
        L_0x0038:
            r10 = r4
        L_0x0039:
            r5.setShowVideo(r10)
            com.android.dialer.calllogutils.CallTypeIconsView r5 = r1.callTypeIcons
            int r10 = r2.features
            r10 = r10 & r7
            if (r10 != r7) goto L_0x0045
            r10 = r9
            goto L_0x0046
        L_0x0045:
            r10 = r4
        L_0x0046:
            r5.setShowHd(r10)
            com.android.dialer.calllogutils.CallTypeIconsView r5 = r1.callTypeIcons
            android.content.Context r10 = r0.context
            int r11 = r2.features
            boolean r10 = com.android.dialer.oem.MotorolaUtils.shouldShowWifiIconInCallLog(r10, r11)
            r5.setShowWifi(r10)
            com.android.dialer.calllogutils.CallTypeIconsView r5 = r1.callTypeIcons
            int r10 = r2.features
            java.lang.Integer r11 = com.android.dialer.compat.telephony.TelephonyManagerCompat.FEATURES_ASSISTED_DIALING
            int r11 = r11.intValue()
            r10 = r10 & r11
            java.lang.Integer r11 = com.android.dialer.compat.telephony.TelephonyManagerCompat.FEATURES_ASSISTED_DIALING
            int r11 = r11.intValue()
            if (r10 != r11) goto L_0x006b
            r10 = r9
            goto L_0x006c
        L_0x006b:
            r10 = r4
        L_0x006c:
            r5.setShowAssistedDialed(r10)
            int r5 = android.os.Build.VERSION.SDK_INT
            com.android.dialer.calllogutils.CallTypeIconsView r5 = r1.callTypeIcons
            int r10 = r2.features
            r11 = 32
            r10 = r10 & r11
            if (r10 != r11) goto L_0x007c
            r10 = r9
            goto L_0x007d
        L_0x007c:
            r10 = r4
        L_0x007d:
            r5.setShowRtt(r10)
            com.android.dialer.calllogutils.CallTypeIconsView r5 = r1.callTypeIcons
            r5.requestLayout()
            com.android.dialer.calllogutils.CallTypeIconsView r5 = r1.callTypeIcons
            r5.setVisibility(r4)
            if (r3 <= r8) goto L_0x0091
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x0092
        L_0x0091:
            r3 = 0
        L_0x0092:
            java.lang.CharSequence r5 = r2.callLocationAndDate
            r10 = 2
            if (r3 == 0) goto L_0x00a6
            android.content.res.Resources r11 = r0.resources
            r12 = 2131820738(0x7f1100c2, float:1.92742E38)
            java.lang.Object[] r13 = new java.lang.Object[r10]
            r13[r4] = r3
            r13[r9] = r5
            java.lang.String r5 = r11.getString(r12, r13)
        L_0x00a6:
            int[] r3 = r2.callTypes
            r3 = r3[r4]
            if (r3 != r7) goto L_0x00fb
            long r11 = r2.duration
            r13 = 0
            int r3 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r3 <= 0) goto L_0x00fb
            android.widget.TextView r3 = r1.callLocationAndDate
            android.content.res.Resources r7 = r0.resources
            java.lang.Object[] r14 = new java.lang.Object[r10]
            r14[r4] = r5
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS
            long r11 = r5.toMinutes(r11)
            r15 = r14
            long r13 = r2.duration
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MINUTES
            long r16 = r5.toSeconds(r11)
            long r13 = r13 - r16
            r16 = 99
            int r5 = (r11 > r16 ? 1 : (r11 == r16 ? 0 : -1))
            if (r5 <= 0) goto L_0x00d4
            goto L_0x00d6
        L_0x00d4:
            r16 = r11
        L_0x00d6:
            android.content.res.Resources r5 = r0.resources
            r11 = 2131821505(0x7f1103c1, float:1.9275755E38)
            java.lang.Object[] r12 = new java.lang.Object[r10]
            java.lang.Long r16 = java.lang.Long.valueOf(r16)
            r12[r4] = r16
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            r12[r9] = r13
            java.lang.String r5 = r5.getString(r11, r12)
            r15[r9] = r5
            r11 = r15
            r5 = 2131821503(0x7f1103bf, float:1.9275751E38)
            java.lang.String r5 = r7.getString(r5, r11)
            r3.setText(r5)
            goto L_0x0100
        L_0x00fb:
            android.widget.TextView r3 = r1.callLocationAndDate
            r3.setText(r5)
        L_0x0100:
            com.android.dialer.app.calllog.calllogcache.CallLogCache r3 = r0.callLogCache
            android.telecom.PhoneAccountHandle r5 = r2.accountHandle
            java.lang.String r3 = r3.getAccountLabel(r5)
            java.lang.String r5 = r2.viaNumber
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x0137
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 != 0) goto L_0x0128
            android.content.res.Resources r5 = r0.resources
            r7 = 2131820743(0x7f1100c7, float:1.927421E38)
            java.lang.Object[] r11 = new java.lang.Object[r10]
            r11[r4] = r3
            java.lang.String r3 = r2.viaNumber
            r11[r9] = r3
            java.lang.String r3 = r5.getString(r7, r11)
            goto L_0x0137
        L_0x0128:
            android.content.res.Resources r3 = r0.resources
            r5 = 2131820742(0x7f1100c6, float:1.9274208E38)
            java.lang.Object[] r7 = new java.lang.Object[r9]
            java.lang.String r11 = r2.viaNumber
            r7[r4] = r11
            java.lang.String r3 = r3.getString(r5, r7)
        L_0x0137:
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            r7 = 8
            if (r5 != 0) goto L_0x016c
            android.widget.TextView r5 = r1.callAccountLabel
            r5.setVisibility(r4)
            android.widget.TextView r5 = r1.callAccountLabel
            r5.setText(r3)
            com.android.dialer.app.calllog.calllogcache.CallLogCache r3 = r0.callLogCache
            android.telecom.PhoneAccountHandle r5 = r2.accountHandle
            int r3 = r3.getAccountColor(r5)
            if (r3 != 0) goto L_0x0166
            r3 = 2131099738(0x7f06005a, float:1.7811838E38)
            android.widget.TextView r5 = r1.callAccountLabel
            android.content.Context r11 = r0.context
            android.content.res.Resources r11 = r11.getResources()
            int r3 = r11.getColor(r3)
            r5.setTextColor(r3)
            goto L_0x0171
        L_0x0166:
            android.widget.TextView r5 = r1.callAccountLabel
            r5.setTextColor(r3)
            goto L_0x0171
        L_0x016c:
            android.widget.TextView r3 = r1.callAccountLabel
            r3.setVisibility(r7)
        L_0x0171:
            java.lang.CharSequence r3 = r21.getPreferredName()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x018a
            com.android.dialer.widget.BidiTextView r3 = r1.nameView
            java.lang.CharSequence r5 = r21.getPreferredName()
            r3.setText(r5)
            com.android.dialer.widget.BidiTextView r3 = r1.nameView
            r3.setTextDirection(r4)
            goto L_0x01ac
        L_0x018a:
            java.lang.String r3 = r2.displayNumber
            boolean r3 = android.telephony.PhoneNumberUtils.isEmergencyNumber(r3)
            if (r3 == 0) goto L_0x01a0
            com.android.dialer.widget.BidiTextView r3 = r1.nameView
            r5 = 2131820961(0x7f1101a1, float:1.9274652E38)
            r3.setText(r5)
            com.android.dialer.widget.BidiTextView r3 = r1.nameView
            r3.setTextDirection(r4)
            goto L_0x01ac
        L_0x01a0:
            com.android.dialer.widget.BidiTextView r3 = r1.nameView
            java.lang.String r5 = r2.displayNumber
            r3.setText(r5)
            com.android.dialer.widget.BidiTextView r3 = r1.nameView
            r3.setTextDirection(r8)
        L_0x01ac:
            if (r6 == 0) goto L_0x0269
            r3 = 7
            android.widget.TextView r5 = r1.voicemailTranscriptionView
            r5.setAutoLinkMask(r3)
            java.lang.String r3 = r2.transcription
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            java.lang.String r5 = ""
            if (r3 != 0) goto L_0x01d6
            java.lang.String r3 = r2.transcription
            int r6 = r2.transcriptionState
            if (r6 == r8) goto L_0x01c7
            r10 = -3
            if (r6 != r10) goto L_0x01d0
        L_0x01c7:
            android.content.res.Resources r5 = r0.resources
            r6 = 2131821580(0x7f11040c, float:1.9275907E38)
            java.lang.String r5 = r5.getString(r6)
        L_0x01d0:
            r18 = r5
            r5 = r3
            r3 = r18
            goto L_0x020b
        L_0x01d6:
            int r3 = r2.transcriptionState
            r6 = -2
            if (r3 == r6) goto L_0x0202
            r6 = -1
            if (r3 == r6) goto L_0x01f8
            if (r3 == r9) goto L_0x01ee
            if (r3 == r10) goto L_0x01e4
            r3 = r5
            goto L_0x020b
        L_0x01e4:
            android.content.res.Resources r3 = r0.resources
            r6 = 2131821581(0x7f11040d, float:1.927591E38)
            java.lang.String r3 = r3.getString(r6)
            goto L_0x020b
        L_0x01ee:
            android.content.res.Resources r3 = r0.resources
            r6 = 2131821584(0x7f110410, float:1.9275915E38)
            java.lang.String r3 = r3.getString(r6)
            goto L_0x020b
        L_0x01f8:
            android.content.res.Resources r3 = r0.resources
            r6 = 2131821583(0x7f11040f, float:1.9275913E38)
            java.lang.String r3 = r3.getString(r6)
            goto L_0x020b
        L_0x0202:
            android.content.res.Resources r3 = r0.resources
            r6 = 2131821582(0x7f11040e, float:1.9275911E38)
            java.lang.String r3 = r3.getString(r6)
        L_0x020b:
            android.widget.TextView r6 = r1.voicemailTranscriptionView
            r6.setText(r5)
            android.widget.TextView r5 = r1.voicemailTranscriptionBrandingView
            r5.setText(r3)
            android.view.View r3 = r1.voicemailTranscriptionRatingView
            int r5 = r2.transcriptionState
            android.telecom.PhoneAccountHandle r6 = r2.accountHandle
            if (r5 == r8) goto L_0x021f
        L_0x021d:
            r9 = r4
            goto L_0x0242
        L_0x021f:
            android.content.Context r5 = r0.context
            com.android.voicemail.VoicemailComponent r5 = com.android.voicemail.VoicemailComponent.get(r5)
            com.android.voicemail.VoicemailClient r5 = r5.getVoicemailClient()
            android.content.Context r8 = r0.context
            boolean r8 = r5.isVoicemailDonationEnabled(r8, r6)
            if (r8 == 0) goto L_0x0232
            goto L_0x0242
        L_0x0232:
            android.content.Context r8 = r0.context
            boolean r5 = r5.isVoicemailDonationAvailable(r8, r6)
            if (r5 == 0) goto L_0x021d
            android.content.Context r5 = r0.context
            boolean r5 = hasSeenVoicemailDonationPromo(r5)
            if (r5 != 0) goto L_0x021d
        L_0x0242:
            if (r9 == 0) goto L_0x0266
            r3.setVisibility(r4)
            r4 = 2131297014(0x7f0902f6, float:1.821196E38)
            android.view.View r4 = r3.findViewById(r4)
            com.android.dialer.app.calllog.-$$Lambda$PhoneCallDetailsHelper$DGshUSHv4ZCYKjQjvliX297abYs r5 = new com.android.dialer.app.calllog.-$$Lambda$PhoneCallDetailsHelper$DGshUSHv4ZCYKjQjvliX297abYs
            r5.<init>(r2, r3)
            r4.setOnClickListener(r5)
            r4 = 2131297013(0x7f0902f5, float:1.8211959E38)
            android.view.View r4 = r3.findViewById(r4)
            com.android.dialer.app.calllog.-$$Lambda$PhoneCallDetailsHelper$lxbaXpdUatJQgmSe1las08nGN0Y r5 = new com.android.dialer.app.calllog.-$$Lambda$PhoneCallDetailsHelper$lxbaXpdUatJQgmSe1las08nGN0Y
            r5.<init>(r2, r3)
            r4.setOnClickListener(r5)
            goto L_0x0269
        L_0x0266:
            r3.setVisibility(r7)
        L_0x0269:
            boolean r3 = r2.isRead
            if (r3 == 0) goto L_0x0270
            android.graphics.Typeface r3 = android.graphics.Typeface.SANS_SERIF
            goto L_0x0272
        L_0x0270:
            android.graphics.Typeface r3 = android.graphics.Typeface.DEFAULT_BOLD
        L_0x0272:
            com.android.dialer.widget.BidiTextView r4 = r1.nameView
            r4.setTypeface(r3)
            android.widget.TextView r4 = r1.voicemailTranscriptionView
            r4.setTypeface(r3)
            android.widget.TextView r4 = r1.voicemailTranscriptionBrandingView
            r4.setTypeface(r3)
            android.widget.TextView r4 = r1.callLocationAndDate
            r4.setTypeface(r3)
            android.widget.TextView r1 = r1.callLocationAndDate
            boolean r2 = r2.isRead
            if (r2 == 0) goto L_0x029d
            android.content.Context r0 = r0.context
            com.android.dialer.theme.base.ThemeComponent r0 = com.android.dialer.theme.base.ThemeComponent.get(r0)
            com.android.dialer.theme.base.Theme r0 = r0.theme()
            com.android.dialer.theme.base.impl.AospThemeImpl r0 = (com.android.dialer.theme.base.impl.AospThemeImpl) r0
            int r0 = r0.getTextColorSecondary()
            goto L_0x02ad
        L_0x029d:
            android.content.Context r0 = r0.context
            com.android.dialer.theme.base.ThemeComponent r0 = com.android.dialer.theme.base.ThemeComponent.get(r0)
            com.android.dialer.theme.base.Theme r0 = r0.theme()
            com.android.dialer.theme.base.impl.AospThemeImpl r0 = (com.android.dialer.theme.base.impl.AospThemeImpl) r0
            int r0 = r0.getTextColorPrimary()
        L_0x02ad:
            r1.setTextColor(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.PhoneCallDetailsHelper.setPhoneCallDetails(com.android.dialer.app.calllog.PhoneCallDetailsViews, com.android.dialer.calllogutils.PhoneCallDetails):void");
    }
}
