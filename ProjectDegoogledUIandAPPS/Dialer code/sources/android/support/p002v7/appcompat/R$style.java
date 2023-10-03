package android.support.p002v7.appcompat;

import android.app.NotificationManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Trace;
import android.provider.ContactsContract;
import android.service.notification.StatusBarNotification;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.graphics.drawable.DrawableCompat;
import android.support.p000v4.graphics.drawable.RoundedBitmapDrawable;
import android.telecom.Call;
import android.telecom.PhoneAccountHandle;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.TextureView;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.android.contacts.common.Collapser$Collapsible;
import com.android.contacts.common.widget.SelectPhoneAccountDialogOptions;
import com.android.dialer.NumberAttributes;
import com.android.dialer.R;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.location.CountryDetector;
import com.android.dialer.notification.DialerNotificationManager;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.phonelookup.consolidator.PhoneLookupInfoConsolidator;
import com.android.dialer.protos.ProtoParsers;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.util.PermissionsUtil;
import com.android.incallui.incall.impl.InCallFragment;
import com.android.incallui.incall.protocol.InCallScreen;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.protocol.CvvmProtocol;
import com.android.voicemail.impl.protocol.OmtpProtocol;
import com.android.voicemail.impl.protocol.VisualVoicemailProtocol;
import com.android.voicemail.impl.protocol.Vvm3Protocol;
import com.android.voicemail.impl.sms.OmtpMessageSender;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import com.google.common.base.Optional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import org.apache.james.mime4j.util.ByteArrayBuffer;
import org.apache.james.mime4j.util.ByteSequence;

/* renamed from: android.support.v7.appcompat.R$style */
public final class R$style {
    public static boolean areEqual(Uri uri, Uri uri2) {
        if (uri == null && uri2 == null) {
            return true;
        }
        if (uri == null || uri2 == null) {
            return false;
        }
        return uri.equals(uri2);
    }

    public static SelectPhoneAccountDialogOptions.Builder builderWithAccounts(Collection<PhoneAccountHandle> collection) {
        SelectPhoneAccountDialogOptions.Builder newBuilder = SelectPhoneAccountDialogOptions.newBuilder();
        for (PhoneAccountHandle next : collection) {
            SelectPhoneAccountDialogOptions.Entry.Builder newBuilder2 = SelectPhoneAccountDialogOptions.Entry.newBuilder();
            newBuilder2.setPhoneAccountHandleComponentName(next.getComponentName().flattenToString());
            newBuilder2.setPhoneAccountHandleId(next.getId());
            newBuilder.addEntries(newBuilder2);
        }
        return newBuilder;
    }

    public static boolean canPullExternalCall(Call call) {
        int i = Build.VERSION.SDK_INT;
        return (call.getDetails().getCallCapabilities() & 8388608) == 8388608;
    }

    public static boolean canResolveBroadcast(Context context, Intent intent) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            return false;
        }
        return true;
    }

    public static void cancelAllInGroup(Context context, String str) {
        Assert.isNotNull(context);
        Assert.checkArgument(!TextUtils.isEmpty(str));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        for (StatusBarNotification statusBarNotification : notificationManager.getActiveNotifications()) {
            if (TextUtils.equals(str, statusBarNotification.getNotification().getGroup())) {
                notificationManager.cancel(statusBarNotification.getTag(), statusBarNotification.getId());
            }
        }
    }

    public static void cancelSingle(Context context, Uri uri) {
        if (uri == null) {
            LogUtil.m8e("MissedCallNotificationCanceller.cancelSingle", "unable to cancel notification, uri is null", new Object[0]);
            return;
        }
        DialerNotificationManager.cancel(context, "MissedCall_" + uri, 1);
    }

    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static String checkNotEmpty(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static <T> T checkNotNull1(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f, f3));
    }

    public static <T extends Collapser$Collapsible<T>> void collapseList(List<T> list, Context context) {
        int size = list.size();
        if (size <= 20) {
            for (int i = 0; i < size; i++) {
                Collapser$Collapsible collapser$Collapsible = (Collapser$Collapsible) list.get(i);
                if (collapser$Collapsible != null) {
                    int i2 = i + 1;
                    while (true) {
                        if (i2 >= size) {
                            break;
                        }
                        Collapser$Collapsible collapser$Collapsible2 = (Collapser$Collapsible) list.get(i2);
                        if (collapser$Collapsible2 != null) {
                            if (collapser$Collapsible.shouldCollapseWith(collapser$Collapsible2, context)) {
                                collapser$Collapsible.collapseWith(collapser$Collapsible2);
                                list.set(i2, (Object) null);
                            } else if (collapser$Collapsible2.shouldCollapseWith(collapser$Collapsible, context)) {
                                collapser$Collapsible2.collapseWith(collapser$Collapsible);
                                list.set(i, (Object) null);
                                break;
                            }
                        }
                        i2++;
                    }
                }
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                }
            }
        }
    }

    public static void copyText(Context context, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        if (!TextUtils.isEmpty(charSequence2)) {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            if (charSequence == null) {
                charSequence = "";
            }
            clipboardManager.setPrimaryClip(ClipData.newPlainText(charSequence, charSequence2));
            if (z) {
                Toast.makeText(context, context.getString(R.string.toast_text_copied), 0).show();
            }
        }
    }

    public static VisualVoicemailProtocol create(String str) {
        if (str == null) {
            return null;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1479165891) {
            if (hashCode != -1478817107) {
                if (hashCode == -1478600199 && str.equals("vvm_type_vvm3")) {
                    c = 2;
                }
            } else if (str.equals("vvm_type_omtp")) {
                c = 0;
            }
        } else if (str.equals("vvm_type_cvvm")) {
            c = 1;
        }
        if (c == 0) {
            return new OmtpProtocol();
        }
        if (c == 1) {
            return new CvvmProtocol();
        }
        if (c == 2) {
            return new Vvm3Protocol();
        }
        VvmLog.m43e("VvmProtocolFactory", "Unexpected visual voicemail type: " + str);
        return null;
    }

    public static InCallScreen createInCallScreen() {
        return new InCallFragment();
    }

    public static String decode(ByteSequence byteSequence, int i, int i2) {
        if (byteSequence == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(i2);
        for (int i3 = i; i3 < i + i2; i3++) {
            sb.append((char) (byteSequence.byteAt(i3) & 255));
        }
        return sb.toString();
    }

    public static long determineUserType(Long l, Long l2) {
        return l != null ? ContactsContract.Directory.isEnterpriseDirectoryId(l.longValue()) ? 1 : 0 : (l2 == null || l2.longValue() == 0 || !ContactsContract.Contacts.isEnterpriseContactId(l2.longValue())) ? 0 : 1;
    }

    public static float dpToPx(Context context, float f) {
        return f * context.getResources().getDisplayMetrics().density;
    }

    public static Bitmap drawableToBitmap(Drawable drawable, int i, int i2) {
        Bitmap bitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (i > 0 || i2 > 0) {
            bitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        } else if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        LogUtil.m9i("DrawableConverter.drawableToBitmap", "created bitmap with width: %d, height: %d", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()));
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static ByteSequence encode(String str) {
        if (str == null) {
            return null;
        }
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(str.length());
        for (int i = 0; i < str.length(); i++) {
            byteArrayBuffer.append((byte) str.charAt(i));
        }
        return byteArrayBuffer;
    }

    public static Uri ensureIsContactUri(ContentResolver contentResolver, Uri uri) throws IllegalArgumentException {
        if (uri != null) {
            String authority = uri.getAuthority();
            if ("com.android.contacts".equals(authority)) {
                String type = contentResolver.getType(uri);
                if ("vnd.android.cursor.item/contact".equals(type)) {
                    return uri;
                }
                if ("vnd.android.cursor.item/raw_contact".equals(type)) {
                    return ContactsContract.RawContacts.getContactLookupUri(contentResolver, ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, ContentUris.parseId(uri)));
                }
                throw new IllegalArgumentException("uri format is unknown");
            } else if ("contacts".equals(authority)) {
                return ContactsContract.RawContacts.getContactLookupUri(contentResolver, ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI, ContentUris.parseId(uri)));
            } else {
                throw new IllegalArgumentException("uri authority is unknown");
            }
        } else {
            throw new IllegalArgumentException("uri must not be null");
        }
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static CharSequence format(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Matcher matcher = Patterns.PHONE.matcher(charSequence.toString());
        int i = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (i < start) {
                spannableStringBuilder.append(charSequence.subSequence(i, start));
            }
            spannableStringBuilder.append(PhoneNumberUtils.createTtsSpannable(TextUtils.concat(new CharSequence[]{String.valueOf(8234), charSequence.subSequence(start, end), String.valueOf(8236)})));
            i = end;
        }
        if (i < charSequence.length()) {
            spannableStringBuilder.append(charSequence.subSequence(i, charSequence.length()));
        }
        return new SpannedString(spannableStringBuilder);
    }

    public static NumberAttributes.Builder fromPhoneLookupInfo(PhoneLookupInfo phoneLookupInfo) {
        String str;
        PhoneLookupInfoConsolidator phoneLookupInfoConsolidator = new PhoneLookupInfoConsolidator(phoneLookupInfo);
        NumberAttributes.Builder newBuilder = NumberAttributes.newBuilder();
        newBuilder.setName(phoneLookupInfoConsolidator.getName());
        if (!TextUtils.isEmpty(phoneLookupInfoConsolidator.getPhotoThumbnailUri())) {
            str = phoneLookupInfoConsolidator.getPhotoThumbnailUri();
        } else {
            str = phoneLookupInfoConsolidator.getPhotoUri();
        }
        newBuilder.setPhotoUri(str);
        newBuilder.setPhotoId(phoneLookupInfoConsolidator.getPhotoId());
        newBuilder.setLookupUri(phoneLookupInfoConsolidator.getLookupUri());
        newBuilder.setNumberTypeLabel(phoneLookupInfoConsolidator.getNumberLabel());
        newBuilder.setIsBusiness(phoneLookupInfoConsolidator.isBusiness());
        newBuilder.setIsBlocked(phoneLookupInfoConsolidator.isBlocked());
        newBuilder.setIsSpam(phoneLookupInfoConsolidator.isSpam());
        newBuilder.setCanReportAsInvalidNumber(phoneLookupInfoConsolidator.canReportAsInvalidNumber());
        newBuilder.setIsCp2InfoIncomplete(phoneLookupInfoConsolidator.isDefaultCp2InfoIncomplete());
        newBuilder.setContactSource(phoneLookupInfoConsolidator.getContactSource());
        newBuilder.setCanSupportCarrierVideoCall(phoneLookupInfoConsolidator.canSupportCarrierVideoCall());
        newBuilder.setGeolocation(phoneLookupInfoConsolidator.getGeolocation());
        newBuilder.setIsEmergencyNumber(phoneLookupInfoConsolidator.isEmergencyNumber());
        return newBuilder;
    }

    public static CallSpecificAppData getCallSpecificAppData(Bundle bundle) {
        if (bundle == null || !bundle.containsKey("com.android.dialer.callintent.CALL_SPECIFIC_APP_DATA")) {
            return null;
        }
        if (bundle.getByteArray("com.android.dialer.callintent.CALL_SPECIFIC_APP_DATA") != null) {
            return (CallSpecificAppData) ProtoParsers.getTrusted(bundle, "com.android.dialer.callintent.CALL_SPECIFIC_APP_DATA", CallSpecificAppData.getDefaultInstance());
        }
        LogUtil.m9i("CallIntentParser.getCallSpecificAppData", "unexpected null byte array for call specific app data proto", new Object[0]);
        return null;
    }

    public static String getCurrentCountryIso(Context context) {
        Trace.beginSection("GeoUtil.getCurrentCountryIso");
        String currentCountryIso = CountryDetector.getInstance(context).getCurrentCountryIso();
        Trace.endSection();
        return currentCountryIso;
    }

    public static Locale getLocale(Context context) {
        LocaleList locales = context.getResources().getConfiguration().getLocales();
        if (!locales.isEmpty()) {
            return locales.get(0);
        }
        return Locale.getDefault();
    }

    public static String getLookupKeyFromUri(Uri uri) {
        if (uri == null || isEncodedContactUri(uri)) {
            return null;
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() < 3) {
            return null;
        }
        return Uri.encode(pathSegments.get(2));
    }

    public static OmtpMessageSender getMessageSender(VisualVoicemailProtocol visualVoicemailProtocol, OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper) {
        int applicationPort = omtpVvmCarrierConfigHelper.getApplicationPort();
        String destinationNumber = omtpVvmCarrierConfigHelper.getDestinationNumber();
        if (!TextUtils.isEmpty(destinationNumber)) {
            return visualVoicemailProtocol.createMessageSender(omtpVvmCarrierConfigHelper.getContext(), omtpVvmCarrierConfigHelper.getPhoneAccountHandle(), (short) applicationPort, destinationNumber);
        }
        VvmLog.m46w("ProtocolHelper", "No destination number for this carrier.");
        return null;
    }

    public static String getNumber(Call call) {
        if (call == null) {
            return null;
        }
        if (call.getDetails().getGatewayInfo() != null) {
            return call.getDetails().getGatewayInfo().getOriginalAddress().getSchemeSpecificPart();
        }
        Uri handle = call.getDetails().getHandle();
        if (handle == null) {
            return null;
        }
        return handle.getSchemeSpecificPart();
    }

    /* JADX INFO: finally extract failed */
    public static int getOrientation(List<ImageHeaderParser> list, InputStream inputStream, ArrayPool arrayPool) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        }
        inputStream.mark(5242880);
        int i = 0;
        int size = list.size();
        while (i < size) {
            try {
                int orientation = ((DefaultImageHeaderParser) list.get(i)).getOrientation(inputStream, arrayPool);
                if (orientation != -1) {
                    inputStream.reset();
                    return orientation;
                }
                inputStream.reset();
                i++;
            } catch (Throwable th) {
                inputStream.reset();
                throw th;
            }
        }
        return -1;
    }

    public static int getPhoneLabelResourceId(Integer num) {
        if (num == null) {
            return R.string.call_other;
        }
        switch (num.intValue()) {
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                return R.string.call_home;
            case 2:
                return R.string.call_mobile;
            case 3:
                return R.string.call_work;
            case 4:
                return R.string.call_fax_work;
            case 5:
                return R.string.call_fax_home;
            case 6:
                return R.string.call_pager;
            case 7:
                return R.string.call_other;
            case 8:
                return R.string.call_callback;
            case 9:
                return R.string.call_car;
            case 10:
                return R.string.call_company_main;
            case 11:
                return R.string.call_isdn;
            case 12:
                return R.string.call_main;
            case 13:
                return R.string.call_other_fax;
            case 14:
                return R.string.call_radio;
            case 15:
                return R.string.call_telex;
            case 16:
                return R.string.call_tty_tdd;
            case 17:
                return R.string.call_work_mobile;
            case 18:
                return R.string.call_work_pager;
            case 19:
                return R.string.call_assistant;
            case 20:
                return R.string.call_mms;
            default:
                return R.string.call_custom;
        }
    }

    public static int getResourceIdByName(String str, Context context) {
        String str2;
        long j = ((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getLong("experiment_for_alternative_spam_word", 230150);
        LogUtil.m9i("SpamAlternativeExperimentUtil.getResourceIdByName", "using experiment %d", Long.valueOf(j));
        if (j != 230150) {
            str2 = str + "_" + j;
        } else {
            str2 = str;
        }
        int identifier = context.getResources().getIdentifier(str2, "string", context.getPackageName());
        if (identifier != 0) {
            return identifier;
        }
        LogUtil.m9i("SpamAlternativeExperimentUtil.getResourceIdByName", "not found experiment %d", Long.valueOf(j));
        return context.getResources().getIdentifier(str, "string", context.getPackageName());
    }

    public static Bitmap getRoundedBitmap(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, config);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        canvas.drawARGB(0, 0, 0, 0);
        paint.setAntiAlias(true);
        float f = (float) i;
        float f2 = (float) i2;
        RectF rectF = new RectF(0.0f, 0.0f, f, f2);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float min = Math.min(((float) width) / f, ((float) height) / f2);
        int i3 = (int) ((f * min) / 2.0f);
        int i4 = (int) ((min * f2) / 2.0f);
        int i5 = width / 2;
        int i6 = height / 2;
        canvas.drawBitmap(bitmap, new Rect(i5 - i3, i6 - i4, i5 + i3, i6 + i4), rectF, paint);
        return createBitmap;
    }

    public static Drawable getRoundedDrawable(Context context, Drawable drawable, int i, int i2) {
        Bitmap drawableToBitmap = drawableToBitmap(drawable, 0, 0);
        if (drawableToBitmap == null) {
            return null;
        }
        RoundedBitmapDrawable create = DrawableCompat.create(context.getResources(), Bitmap.createScaledBitmap(drawableToBitmap, i, i2, false));
        create.setAntiAlias(true);
        create.setCornerRadius((float) (create.getIntrinsicHeight() / 2));
        return create;
    }

    public static int getSmsLabelResourceId(Integer num) {
        if (num == null) {
            return R.string.sms_other;
        }
        switch (num.intValue()) {
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                return R.string.sms_home;
            case 2:
                return R.string.sms_mobile;
            case 3:
                return R.string.sms_work;
            case 4:
                return R.string.sms_fax_work;
            case 5:
                return R.string.sms_fax_home;
            case 6:
                return R.string.sms_pager;
            case 7:
                return R.string.sms_other;
            case 8:
                return R.string.sms_callback;
            case 9:
                return R.string.sms_car;
            case 10:
                return R.string.sms_company_main;
            case 11:
                return R.string.sms_isdn;
            case 12:
                return R.string.sms_main;
            case 13:
                return R.string.sms_other_fax;
            case 14:
                return R.string.sms_radio;
            case 15:
                return R.string.sms_telex;
            case 16:
                return R.string.sms_tty_tdd;
            case 17:
                return R.string.sms_work_mobile;
            case 18:
                return R.string.sms_work_pager;
            case 19:
                return R.string.sms_assistant;
            case 20:
                return R.string.sms_mms;
            default:
                return R.string.sms_custom;
        }
    }

    public static Spannable getTelephoneTtsSpannable(String str, String str2) {
        if (str == null) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        int indexOf = TextUtils.isEmpty(str2) ? -1 : str.indexOf(str2);
        while (indexOf >= 0) {
            int length = str2.length() + indexOf;
            spannableString.setSpan(PhoneNumberUtils.createTtsSpan(str2), indexOf, length, 33);
            indexOf = str.indexOf(str2, length);
        }
        return spannableString;
    }

    public static CharSequence getTtsSpannedPhoneNumber(Resources resources, int i, String str) {
        return getTelephoneTtsSpannable(resources.getString(i, new Object[]{str}), str);
    }

    /* JADX INFO: finally extract failed */
    public static ImageHeaderParser.ImageType getType(List<ImageHeaderParser> list, InputStream inputStream, ArrayPool arrayPool) throws IOException {
        if (inputStream == null) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        }
        inputStream.mark(5242880);
        int i = 0;
        int size = list.size();
        while (i < size) {
            try {
                ImageHeaderParser.ImageType type = ((DefaultImageHeaderParser) list.get(i)).getType(inputStream);
                if (type != ImageHeaderParser.ImageType.UNKNOWN) {
                    inputStream.reset();
                    return type;
                }
                inputStream.reset();
                i++;
            } catch (Throwable th) {
                inputStream.reset();
                throw th;
            }
        }
        return ImageHeaderParser.ImageType.UNKNOWN;
    }

    public static Optional<PhoneAccountHandle> getValidPhoneAccount(Context context, String str, String str2) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.m9i("PreferredAccountUtil.getValidPhoneAccount", "empty componentName or id", new Object[0]);
            return Optional.absent();
        }
        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
        if (unflattenFromString == null) {
            LogUtil.m8e("PreferredAccountUtil.getValidPhoneAccount", "cannot parse component name", new Object[0]);
            return Optional.absent();
        }
        PhoneAccountHandle phoneAccountHandle = new PhoneAccountHandle(unflattenFromString, str2);
        int i = Build.VERSION.SDK_INT;
        if (((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(phoneAccountHandle) != null) {
            z = true;
        }
        if (z) {
            return Optional.m67of(phoneAccountHandle);
        }
        return Optional.absent();
    }

    public static boolean hasCameraPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.CAMERA") == 0;
    }

    public static boolean hasCameraPermissionAndShownPrivacyToast(Context context) {
        return PermissionsUtil.hasCameraPrivacyToastShown(context) && hasCameraPermission(context);
    }

    public static boolean hasReceivedVideoUpgradeRequest(int i) {
        return i == 3;
    }

    public static boolean hasSentVideoUpgradeRequest(int i) {
        return i == 1 || i == 5 || i == 6 || i == 4;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isAccessibilityEnabled(Context context) {
        return ((AccessibilityManager) context.getSystemService(AccessibilityManager.class)).isEnabled();
    }

    public static boolean isConnectingOrConnected(int i) {
        if (i == 11 || i == 13 || i == 15) {
            return true;
        }
        switch (i) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }

    public static boolean isCustomPhoneType(Integer num) {
        return num.intValue() == 0 || num.intValue() == 19;
    }

    public static boolean isDialing(int i) {
        return i == 6 || i == 15 || i == 7;
    }

    public static boolean isEmergencyCall(Call call) {
        String str;
        Assert.isNotNull(call);
        Uri handle = call.getDetails().getHandle();
        if (handle == null) {
            str = "";
        } else {
            str = handle.getSchemeSpecificPart();
        }
        return PhoneNumberUtils.isEmergencyNumber(str);
    }

    public static boolean isEncodedContactUri(Uri uri) {
        String lastPathSegment;
        if (uri == null || (lastPathSegment = uri.getLastPathSegment()) == null) {
            return false;
        }
        return lastPathSegment.equals("encoded");
    }

    public static boolean isLocalEnterpriseDirectoryId(long j) {
        return ContactsContract.Directory.isEnterpriseDirectoryId(j) && !ContactsContract.Directory.isRemoteDirectoryId(j);
    }

    public static boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isTouchExplorationEnabled(Context context) {
        return ((AccessibilityManager) context.getSystemService(AccessibilityManager.class)).isTouchExplorationEnabled();
    }

    public static float lerp(float f, float f2, float f3) {
        return (f2 * f3) + ((1.0f - f3) * f);
    }

    public static Uri nullForNonContactsUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        if ("com.android.contacts".equals(uri.getAuthority())) {
            return uri;
        }
        return null;
    }

    public static Uri parseUriOrNull(String str) {
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }

    public static void putCallSpecificAppData(Bundle bundle, CallSpecificAppData callSpecificAppData) {
        Assert.isNotNull(callSpecificAppData);
        Assert.isNotNull(bundle);
        Assert.isNotNull("com.android.dialer.callintent.CALL_SPECIFIC_APP_DATA");
        bundle.putByteArray("com.android.dialer.callintent.CALL_SPECIFIC_APP_DATA", callSpecificAppData.toByteArray());
    }

    public static int saturatedCast(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    public static void scaleVideoAndFillView(TextureView textureView, float f, float f2, float f3) {
        float f4;
        float width = (float) textureView.getWidth();
        float height = (float) textureView.getHeight();
        float f5 = width / height;
        float f6 = 1.0f;
        if (f5 > f / f2) {
            f4 = ((width / f) * f2) / height;
        } else {
            f6 = ((height / f2) * f) / width;
            f4 = 1.0f;
        }
        if (f3 == 90.0f || f3 == 270.0f) {
            float f7 = f5 * f6;
            f6 = (height / width) * f4 * -1.0f;
            f4 = -1.0f * f7;
        }
        LogUtil.m9i("VideoScale.scaleVideoAndFillView", "view: %f x %f, video: %f x %f scale: %f x %f, rotation: %f", Float.valueOf(width), Float.valueOf(height), Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f6), Float.valueOf(f4), Float.valueOf(f3));
        Matrix matrix = new Matrix();
        float f8 = width / 2.0f;
        float f9 = height / 2.0f;
        matrix.setScale(f6, f4, f8, f9);
        if (f3 != 0.0f) {
            matrix.postRotate(f3, f8, f9);
        }
        textureView.setTransform(matrix);
    }

    public static String toLowerCase(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt >= 'A' && charAt <= 'Z') {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c = charArray[i];
                    if (c >= 'A' && c <= 'Z') {
                        charArray[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    public static String toSafeString(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '-' || charAt == '@' || charAt == '.') {
                sb.append(charAt);
            } else {
                sb.append('x');
            }
        }
        return sb.toString();
    }

    public static String toString2(int i) {
        if (i == 0) {
            return "AUDIO";
        }
        if (i == 1) {
            return "MUTE";
        }
        if (i == 2) {
            return "DIALPAD";
        }
        if (i == 3) {
            return "HOLD";
        }
        if (i == 4) {
            return "SWAP";
        }
        if (i == 5) {
            return "UPGRADE_TO_VIDEO";
        }
        if (i == 7) {
            return "DOWNGRADE_TO_AUDIO";
        }
        if (i == 6) {
            return "SWITCH_CAMERA";
        }
        if (i == 8) {
            return "ADD_CALL";
        }
        if (i == 9) {
            return "MERGE";
        }
        if (i == 10) {
            return "PAUSE_VIDEO";
        }
        if (i == 11) {
            return "MANAGE_VIDEO_CONFERENCE";
        }
        if (i == 12) {
            return "MANAGE_VOICE_CONFERENCE";
        }
        if (i == 13) {
            return "SWITCH_TO_SECONDARY";
        }
        if (i == 14) {
            return "SWAP_SIM";
        }
        if (i == 16) {
            return "UPGRADE_TO_RTT";
        }
        return i == 15 ? "RECORD_CALL" : GeneratedOutlineSupport.outline5("INVALID_BUTTON: ", i);
    }

    public static String uriToString(Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }
}
