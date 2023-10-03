package com.android.dialer.calllogutils;

import android.content.Context;
import android.content.res.Resources;
import android.icu.lang.UCharacter;
import android.icu.text.BreakIterator;
import android.os.Build;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import com.android.dialer.NumberAttributes;
import com.android.dialer.R;
import com.android.dialer.calllog.model.CoalescedRow;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.dialer.spam.Spam;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.time.Clock;
import com.android.dialer.util.DialerUtils;
import com.android.dialer.voicemail.model.VoicemailEntry;
import com.google.common.base.Optional;
import com.google.common.collect.Collections2;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class CallLogDates {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
        if (r1 != 7) goto L_0x0031;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00d0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.CharSequence buildDescriptionForEntry(android.content.Context r8, com.android.dialer.time.Clock r9, com.android.dialer.calllog.model.CoalescedRow r10) {
        /*
            android.content.res.Resources r0 = r8.getResources()
            int r1 = r10.getCallType()
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == r4) goto L_0x002e
            if (r1 == r3) goto L_0x002a
            r5 = 2131689475(0x7f0f0003, float:1.9007966E38)
            if (r1 == r2) goto L_0x0031
            r6 = 4
            if (r1 == r6) goto L_0x0022
            r6 = 6
            if (r1 == r6) goto L_0x001e
            r6 = 7
            if (r1 == r6) goto L_0x002e
            goto L_0x0031
        L_0x001e:
            r5 = 2131689474(0x7f0f0002, float:1.9007964E38)
            goto L_0x0031
        L_0x0022:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Voicemails not expected in call log"
            r8.<init>(r9)
            throw r8
        L_0x002a:
            r5 = 2131689476(0x7f0f0004, float:1.9007968E38)
            goto L_0x0031
        L_0x002e:
            r5 = 2131689473(0x7f0f0001, float:1.9007962E38)
        L_0x0031:
            com.android.dialer.CoalescedIds r1 = r10.getCoalescedIds()
            int r1 = r1.getCoalescedIdCount()
            java.lang.String r0 = r0.getQuantityString(r5, r1)
            java.lang.CharSequence[] r1 = new java.lang.CharSequence[r3]
            com.android.dialer.CoalescedIds r5 = r10.getCoalescedIds()
            int r5 = r5.getCoalescedIdCount()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r6 = 0
            r1[r6] = r5
            java.lang.CharSequence r5 = buildPrimaryText(r8, r10)
            r1[r4] = r5
            java.lang.CharSequence r0 = android.text.TextUtils.expandTemplate(r0, r1)
            java.util.List r9 = buildSecondaryTextListForEntries(r8, r9, r10, r6)
            com.android.dialer.calllogutils.-$$Lambda$CallLogDates$n-QWPF_PEragV0XUF5TCKwbWwzg r1 = com.android.dialer.calllogutils.$$Lambda$CallLogDates$nQWPF_PEragV0XUF5TCKwbWwzg.INSTANCE
            java.util.Collection r9 = com.google.common.collect.Collections2.filter(r9, r1)
            java.lang.String r1 = ", "
            java.lang.String r9 = android.text.TextUtils.join(r1, r9)
            java.lang.String r1 = r10.getPhoneAccountComponentName()
            java.lang.String r5 = r10.getPhoneAccountId()
            android.telecom.PhoneAccountHandle r1 = com.android.dialer.telecom.TelecomUtil.composePhoneAccountHandle(r1, r5)
            java.lang.String r5 = ""
            if (r1 != 0) goto L_0x0079
            goto L_0x00b4
        L_0x0079:
            java.lang.String r1 = getAccountLabel(r8, r1)
            boolean r7 = android.text.TextUtils.isEmpty(r1)
            if (r7 == 0) goto L_0x0084
            goto L_0x00b4
        L_0x0084:
            com.android.dialer.DialerPhoneNumber r7 = r10.getNumber()
            java.lang.String r7 = r7.getNormalizedNumber()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 == 0) goto L_0x0093
            goto L_0x00b4
        L_0x0093:
            android.content.res.Resources r5 = r8.getResources()
            r7 = 2131820560(0x7f110010, float:1.9273838E38)
            java.lang.CharSequence r5 = r5.getText(r7)
            java.lang.CharSequence[] r7 = new java.lang.CharSequence[r3]
            r7[r6] = r1
            com.android.dialer.DialerPhoneNumber r10 = r10.getNumber()
            java.lang.String r10 = r10.getNormalizedNumber()
            java.lang.CharSequence r10 = android.telephony.PhoneNumberUtils.createTtsSpannable(r10)
            r7[r4] = r10
            java.lang.CharSequence r5 = android.text.TextUtils.expandTemplate(r5, r7)
        L_0x00b4:
            boolean r10 = android.text.TextUtils.isEmpty(r5)
            if (r10 == 0) goto L_0x00d0
            android.content.res.Resources r8 = r8.getResources()
            r10 = 2131820559(0x7f11000f, float:1.9273836E38)
            java.lang.CharSequence r8 = r8.getText(r10)
            java.lang.CharSequence[] r10 = new java.lang.CharSequence[r3]
            r10[r6] = r0
            r10[r4] = r9
            java.lang.CharSequence r8 = android.text.TextUtils.expandTemplate(r8, r10)
            goto L_0x00e7
        L_0x00d0:
            android.content.res.Resources r8 = r8.getResources()
            r10 = 2131820558(0x7f11000e, float:1.9273834E38)
            java.lang.CharSequence r8 = r8.getText(r10)
            java.lang.CharSequence[] r10 = new java.lang.CharSequence[r2]
            r10[r6] = r0
            r10[r4] = r9
            r10[r3] = r5
            java.lang.CharSequence r8 = android.text.TextUtils.expandTemplate(r8, r10)
        L_0x00e7:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calllogutils.CallLogDates.buildDescriptionForEntry(android.content.Context, com.android.dialer.time.Clock, com.android.dialer.calllog.model.CoalescedRow):java.lang.CharSequence");
    }

    public static CharSequence buildPrimaryText(Context context, CoalescedRow coalescedRow) {
        if (coalescedRow.getNumberAttributes().getIsEmergencyNumber()) {
            return context.getText(R.string.emergency_number);
        }
        Optional<String> nameForPresentation = getNameForPresentation(context, coalescedRow.getNumberPresentation());
        if (nameForPresentation.isPresent()) {
            return nameForPresentation.get();
        }
        if (coalescedRow.getIsVoicemailCall() && !TextUtils.isEmpty(coalescedRow.getVoicemailCallTag())) {
            return coalescedRow.getVoicemailCallTag();
        }
        if (!TextUtils.isEmpty(coalescedRow.getNumberAttributes().getName())) {
            return coalescedRow.getNumberAttributes().getName();
        }
        if (!TextUtils.isEmpty(coalescedRow.getFormattedNumber())) {
            return PhoneNumberUtils.createTtsSpannable(coalescedRow.getFormattedNumber());
        }
        return context.getText(R.string.new_call_log_unknown);
    }

    public static CharSequence buildSecondaryTextForBottomSheet(Context context, CoalescedRow coalescedRow) {
        if (!coalescedRow.getNumberAttributes().getIsEmergencyNumber()) {
            ArrayList arrayList = new ArrayList();
            if (coalescedRow.getNumberAttributes().getIsBlocked()) {
                arrayList.add(context.getText(R.string.new_call_log_secondary_blocked));
            }
            if (Spam.shouldShowAsSpam(coalescedRow.getNumberAttributes().getIsSpam(), coalescedRow.getCallType())) {
                arrayList.add(context.getText(R.string.new_call_log_secondary_spam));
            }
            arrayList.add(getNumberTypeLabel(context, coalescedRow));
            if (getNameForPresentation(context, coalescedRow.getNumberPresentation()).isPresent()) {
                return joinSecondaryTextComponents1(arrayList);
            }
            if (TextUtils.isEmpty(coalescedRow.getNumberAttributes().getName())) {
                return joinSecondaryTextComponents1(arrayList);
            }
            if (TextUtils.isEmpty(coalescedRow.getFormattedNumber())) {
                return joinSecondaryTextComponents1(arrayList);
            }
            arrayList.add(coalescedRow.getFormattedNumber());
            return joinSecondaryTextComponents1(arrayList);
        } else if (!coalescedRow.getFormattedNumber().isEmpty()) {
            return coalescedRow.getFormattedNumber();
        } else {
            return coalescedRow.getNumber().getNormalizedNumber();
        }
    }

    public static CharSequence buildSecondaryTextForEntries(Context context, Clock clock, CoalescedRow coalescedRow) {
        return joinSecondaryTextComponents1(buildSecondaryTextListForEntries(context, clock, coalescedRow, true));
    }

    static List<CharSequence> buildSecondaryTextListForEntries(Context context, Clock clock, CoalescedRow coalescedRow, boolean z) {
        if (coalescedRow.getNumberAttributes().getIsEmergencyNumber()) {
            return Collections.singletonList(newCallLogTimestampLabel(context, clock.currentTimeMillis(), coalescedRow.getTimestamp(), z));
        }
        ArrayList arrayList = new ArrayList();
        if (coalescedRow.getNumberAttributes().getIsBlocked()) {
            arrayList.add(context.getText(R.string.new_call_log_secondary_blocked));
        }
        if (Spam.shouldShowAsSpam(coalescedRow.getNumberAttributes().getIsSpam(), coalescedRow.getCallType())) {
            arrayList.add(context.getText(R.string.new_call_log_secondary_spam));
        }
        arrayList.add(getNumberTypeLabel(context, coalescedRow));
        arrayList.add(newCallLogTimestampLabel(context, clock.currentTimeMillis(), coalescedRow.getTimestamp(), z));
        return arrayList;
    }

    public static CharSequence formatDate(Context context, long j) {
        return toTitleCase(DateUtils.formatDateTime(context, j, 23));
    }

    public static CharSequence formatDurationAndDataUsage(Context context, long j, long j2) {
        String str;
        long minutes = TimeUnit.SECONDS.toMinutes(j);
        long seconds = j - TimeUnit.MINUTES.toSeconds(minutes);
        Resources resources = context.getResources();
        if (j >= 60) {
            str = context.getString(R.string.call_duration_format_pattern, new Object[]{Long.toString(minutes), resources.getString(R.string.call_details_minutes_abbreviation), Long.toString(seconds), resources.getString(R.string.call_details_seconds_abbreviation)});
        } else {
            str = context.getString(R.string.call_duration_short_format_pattern, new Object[]{Long.toString(seconds), resources.getString(R.string.call_details_seconds_abbreviation)});
        }
        return formatDurationAndDataUsageInternal(context, str.replace("'", ""), j2);
    }

    public static CharSequence formatDurationAndDataUsageA11y(Context context, long j, long j2) {
        String str;
        Resources resources = context.getResources();
        if (j >= 60) {
            int i = (int) (j / 60);
            int i2 = ((int) j) - (i * 60);
            str = context.getString(R.string.a11y_call_duration_format, new Object[]{Integer.valueOf(i), resources.getQuantityString(R.plurals.a11y_minutes, i), Integer.valueOf(i2), resources.getQuantityString(R.plurals.a11y_seconds, i2)});
        } else {
            str = context.getString(R.string.a11y_call_duration_short_format, new Object[]{Long.valueOf(j), resources.getQuantityString(R.plurals.a11y_seconds, (int) j)});
        }
        return formatDurationAndDataUsageInternal(context, str, j2);
    }

    private static CharSequence formatDurationAndDataUsageInternal(Context context, CharSequence charSequence, long j) {
        ArrayList arrayList = new ArrayList();
        if (j <= 0) {
            return charSequence;
        }
        arrayList.add(charSequence);
        arrayList.add(Formatter.formatShortFileSize(context, j));
        return DialerUtils.join(arrayList);
    }

    public static PhotoInfo.Builder fromCoalescedRow(Context context, CoalescedRow coalescedRow) {
        PhotoInfo.Builder fromNumberAttributes = fromNumberAttributes(coalescedRow.getNumberAttributes());
        fromNumberAttributes.setName(buildPrimaryText(context, coalescedRow).toString());
        fromNumberAttributes.setFormattedNumber(coalescedRow.getFormattedNumber());
        fromNumberAttributes.setIsVoicemail(coalescedRow.getIsVoicemailCall());
        fromNumberAttributes.setIsSpam(Spam.shouldShowAsSpam(coalescedRow.getNumberAttributes().getIsSpam(), coalescedRow.getCallType()));
        boolean z = true;
        fromNumberAttributes.setIsVideo((coalescedRow.getFeatures() & 1) == 1);
        int i = Build.VERSION.SDK_INT;
        if ((coalescedRow.getFeatures() & 32) != 32) {
            z = false;
        }
        fromNumberAttributes.setIsRtt(z);
        return fromNumberAttributes;
    }

    private static PhotoInfo.Builder fromNumberAttributes(NumberAttributes numberAttributes) {
        PhotoInfo.Builder newBuilder = PhotoInfo.newBuilder();
        newBuilder.setName(numberAttributes.getName());
        newBuilder.setPhotoUri(numberAttributes.getPhotoUri());
        newBuilder.setPhotoId(numberAttributes.getPhotoId());
        newBuilder.setLookupUri(numberAttributes.getLookupUri());
        newBuilder.setIsBusiness(numberAttributes.getIsBusiness());
        newBuilder.setIsBlocked(numberAttributes.getIsBlocked());
        return newBuilder;
    }

    public static PhotoInfo.Builder fromVoicemailEntry(VoicemailEntry voicemailEntry) {
        PhotoInfo.Builder fromNumberAttributes = fromNumberAttributes(voicemailEntry.getNumberAttributes());
        fromNumberAttributes.setFormattedNumber(voicemailEntry.getFormattedNumber());
        fromNumberAttributes.setIsSpam(Spam.shouldShowAsSpam(voicemailEntry.getNumberAttributes().getIsSpam(), voicemailEntry.getCallType()));
        return fromNumberAttributes;
    }

    public static int getAccountColor(Context context, PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccount = TelecomUtil.getPhoneAccount(context, phoneAccountHandle);
        if (phoneAccount == null) {
            return 0;
        }
        return phoneAccount.getHighlightColor();
    }

    public static String getAccountLabel(Context context, PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccount;
        if (TelecomUtil.getCallCapablePhoneAccounts(context).size() <= 1) {
            phoneAccount = null;
        } else {
            phoneAccount = ((TelecomManager) context.getSystemService("telecom")).getPhoneAccount(phoneAccountHandle);
        }
        if (phoneAccount == null || phoneAccount.getLabel() == null) {
            return null;
        }
        return phoneAccount.getLabel().toString();
    }

    public static int getCallbackAction(String str, int i, boolean z) {
        boolean z2 = false;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (z) {
            return 2;
        }
        if ((i & 1) == 1) {
            z2 = true;
        }
        return z2 ? 1 : 3;
    }

    public static int getDayDifference(long j, long j2) {
        if (j2 >= j) {
            long j3 = j;
            j = j2;
            j2 = j3;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        instance.add(11, -instance.get(11));
        instance.add(12, -instance.get(12));
        instance.add(13, -instance.get(13));
        instance.add(14, -instance.get(14));
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j2);
        int i = 0;
        while (instance2.before(instance)) {
            instance.add(5, -1);
            i++;
        }
        return i;
    }

    public static CharSequence getDisplayName(Context context, CharSequence charSequence, int i, boolean z) {
        Optional<String> nameForPresentation = getNameForPresentation(context, i);
        if (nameForPresentation.isPresent()) {
            return nameForPresentation.get();
        }
        if (z) {
            return context.getResources().getString(R.string.voicemail_string);
        }
        return PhoneNumberHelper.isLegacyUnknownNumbers(charSequence) ? context.getResources().getString(R.string.unknown) : "";
    }

    static CharSequence getDisplayNumber(Context context, CharSequence charSequence, int i, CharSequence charSequence2, CharSequence charSequence3, boolean z) {
        CharSequence displayName = getDisplayName(context, charSequence, i, z);
        if (!TextUtils.isEmpty(displayName)) {
            return getTtsSpannableLtrNumber(displayName);
        }
        if (!TextUtils.isEmpty(charSequence2)) {
            return getTtsSpannableLtrNumber(charSequence2);
        }
        if (TextUtils.isEmpty(charSequence)) {
            return context.getResources().getString(R.string.unknown);
        }
        return getTtsSpannableLtrNumber(charSequence.toString() + charSequence3);
    }

    public static Optional<String> getNameForPresentation(Context context, int i) {
        if (i == 3) {
            return Optional.m67of(context.getResources().getString(R.string.unknown));
        }
        if (i == 2) {
            return Optional.m67of(PhoneNumberHelper.getDisplayNameForRestrictedNumber(context));
        }
        if (i == 4) {
            return Optional.m67of(context.getResources().getString(R.string.payphone));
        }
        return Optional.absent();
    }

    private static CharSequence getNumberTypeLabel(Context context, CoalescedRow coalescedRow) {
        String str;
        StringBuilder sb = new StringBuilder();
        String numberTypeLabel = coalescedRow.getNumberAttributes().getNumberTypeLabel();
        sb.append(numberTypeLabel);
        if ((coalescedRow.getFeatures() & 1) == 1) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            ((DuoStub) DuoComponent.get(context).getDuo()).isDuoAccount(coalescedRow.getPhoneAccountComponentName());
            sb.append(context.getText(R.string.new_call_log_carrier_video));
        }
        if (TextUtils.isEmpty(numberTypeLabel) && !Spam.shouldShowAsSpam(coalescedRow.getNumberAttributes().getIsSpam(), coalescedRow.getCallType())) {
            if (!TextUtils.isEmpty(coalescedRow.getNumberAttributes().getGeolocation())) {
                str = coalescedRow.getNumberAttributes().getGeolocation();
            } else {
                str = coalescedRow.getGeocodedLocation();
            }
            if (!TextUtils.isEmpty(str)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(str);
            }
        }
        return sb;
    }

    private static CharSequence getTtsSpannableLtrNumber(CharSequence charSequence) {
        return PhoneNumberUtils.createTtsSpannable(BidiFormatter.getInstance().unicodeWrap(charSequence.toString(), TextDirectionHeuristics.LTR));
    }

    private static CharSequence joinSecondaryTextComponents1(List<CharSequence> list) {
        return TextUtils.join(" • ", Collections2.filter(list, $$Lambda$CallLogDates$ylulDEp4adtIk90TwXabdjfjveo.INSTANCE));
    }

    static /* synthetic */ boolean lambda$joinSecondaryTextComponents$0(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence);
    }

    static /* synthetic */ boolean lambda$joinSecondaryTextComponents$01(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence);
    }

    public static CharSequence newCallLogTimestampLabel(Context context, long j, long j2, boolean z) {
        long j3;
        long j4 = j - j2;
        if (j4 < TimeUnit.MINUTES.toMillis(1)) {
            return context.getString(R.string.just_now);
        }
        if (j4 >= TimeUnit.HOURS.toMillis(1)) {
            int dayDifference = getDayDifference(j, j2);
            if (dayDifference == 0) {
                return DateUtils.formatDateTime(context, j2, 1);
            }
            if (dayDifference < 7) {
                return toTitleCase(DateUtils.formatDateTime(context, j2, z ? 32770 : 2));
            }
            if (j2 < j) {
                j3 = j2;
            } else {
                j3 = j;
                j = j2;
            }
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(j);
            instance.add(1, -1);
            Calendar instance2 = Calendar.getInstance();
            instance2.setTimeInMillis(j3);
            if (instance.before(instance2)) {
                return formatDate(context, j2, false, z);
            }
            return formatDate(context, j2, true, z);
        } else if (z) {
            return DateUtils.getRelativeTimeSpanString(j2, j, 60000, 262144).toString().replace(".", "");
        } else {
            return DateUtils.getRelativeTimeSpanString(j2, j, 60000);
        }
    }

    private static CharSequence toTitleCase(CharSequence charSequence) {
        return UCharacter.toTitleCase(Locale.getDefault(), charSequence.toString(), BreakIterator.getSentenceInstance(), 256);
    }

    private static CharSequence formatDate(Context context, long j, boolean z, boolean z2) {
        int i = z2 ? 65536 : 0;
        if (!z) {
            i |= 8;
        }
        return toTitleCase(DateUtils.formatDateTime(context, j, i));
    }
}
