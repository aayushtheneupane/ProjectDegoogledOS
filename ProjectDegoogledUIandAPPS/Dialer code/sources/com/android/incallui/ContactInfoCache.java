package com.android.incallui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import android.os.Trace;
import android.support.design.R$dimen;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.ContactLookupResult$Type;
import com.android.dialer.oem.CequintCallerIdManager;
import com.android.dialer.phonenumbercache.ContactInfo;
import com.android.dialer.phonenumbercache.PhoneNumberCache;
import com.android.dialer.phonenumberutil.PhoneNumberHelper;
import com.android.incallui.CallerInfoAsyncQuery;
import com.android.incallui.call.DialerCall;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ContactInfoCache implements ContactsAsyncHelper$OnImageLoadCompleteListener {
    private static ContactInfoCache cache;
    private final DialerExecutor<CnapInformationWrapper> cachedNumberLookupExecutor;
    private final Map<String, Set<ContactInfoCacheCallback>> callBacks = new ArrayMap();
    private final Context context;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<String, ContactCacheEntry> infoMap = new ConcurrentHashMap<>();
    private int queryId;

    private static class CachedNumberLookupWorker implements DialerExecutor.Worker<CnapInformationWrapper, Void> {
        /* synthetic */ CachedNumberLookupWorker(C06341 r1) {
        }

        public Object doInBackground(Object obj) throws Throwable {
            if (((CnapInformationWrapper) obj) == null) {
                return null;
            }
            new ContactInfo();
            throw null;
        }
    }

    private static final class CallerInfoQueryToken {
        final String callId;
        final int queryId;

        CallerInfoQueryToken(int i, String str) {
            this.queryId = i;
            this.callId = str;
        }
    }

    private static final class CnapInformationWrapper {
    }

    public static class ContactCacheEntry {
        public ContactLookupResult$Type contactLookupResult = ContactLookupResult$Type.NOT_FOUND;
        Uri contactRingtoneUri;
        Uri displayPhotoUri;
        boolean hasPendingQuery;
        boolean isBusiness;
        boolean isEmergencyNumber;
        boolean isSipCall;
        boolean isVoicemailNumber;
        public String label;
        public String location;
        public String lookupKey;
        public Uri lookupUri;
        public String nameAlternative;
        public String namePrimary;
        public String number;
        String originalPhoneNumber;
        public Drawable photo;
        int photoType;
        int queryId;
        boolean shouldShowLocation;
        public long userType = 0;

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("ContactCacheEntry{name='");
            outline13.append(R$style.toSafeString(this.namePrimary));
            outline13.append('\'');
            outline13.append(", nameAlternative='");
            outline13.append(R$style.toSafeString(this.nameAlternative));
            outline13.append('\'');
            outline13.append(", number='");
            outline13.append(R$style.toSafeString(this.number));
            outline13.append('\'');
            outline13.append(", location='");
            outline13.append(R$style.toSafeString(this.location));
            outline13.append('\'');
            outline13.append(", label='");
            outline13.append(this.label);
            outline13.append('\'');
            outline13.append(", photo=");
            outline13.append(this.photo);
            outline13.append(", isSipCall=");
            outline13.append(this.isSipCall);
            outline13.append(", displayPhotoUri=");
            outline13.append(this.displayPhotoUri);
            outline13.append(", contactLookupResult=");
            outline13.append(this.contactLookupResult);
            outline13.append(", userType=");
            outline13.append(this.userType);
            outline13.append(", contactRingtoneUri=");
            outline13.append(this.contactRingtoneUri);
            outline13.append(", queryId=");
            outline13.append(this.queryId);
            outline13.append(", originalPhoneNumber=");
            outline13.append(this.originalPhoneNumber);
            outline13.append(", shouldShowLocation=");
            outline13.append(this.shouldShowLocation);
            outline13.append(", isEmergencyNumber=");
            outline13.append(this.isEmergencyNumber);
            outline13.append(", isVoicemailNumber=");
            outline13.append(this.isVoicemailNumber);
            outline13.append('}');
            return outline13.toString();
        }
    }

    public interface ContactInfoCacheCallback {
        void onContactInfoComplete(String str, ContactCacheEntry contactCacheEntry);

        void onImageLoadComplete(String str, ContactCacheEntry contactCacheEntry);
    }

    private static final class DialerCallCookieWrapper {
        final String callId;
        final String cnapName;
        final int numberPresentation;

        DialerCallCookieWrapper(String str, int i, String str2) {
            this.callId = str;
            this.numberPresentation = i;
            this.cnapName = str2;
        }
    }

    private class FindInfoCallback implements CallerInfoAsyncQuery.OnQueryCompleteListener {
        private final boolean isIncoming;
        private final CallerInfoQueryToken queryToken;

        FindInfoCallback(boolean z, CallerInfoQueryToken callerInfoQueryToken) {
            this.isIncoming = z;
            this.queryToken = callerInfoQueryToken;
        }

        public void onDataLoaded(int i, Object obj, CallerInfo callerInfo) {
            Assert.isWorkerThread();
            DialerCallCookieWrapper dialerCallCookieWrapper = (DialerCallCookieWrapper) obj;
            if (ContactInfoCache.this.isWaitingForThisQuery(dialerCallCookieWrapper.callId, this.queryToken.queryId)) {
                long uptimeMillis = SystemClock.uptimeMillis();
                ContactInfoCache.access$300(ContactInfoCache.this, callerInfo, dialerCallCookieWrapper.cnapName, this.isIncoming);
                "Cequint Caller Id look up takes " + (SystemClock.uptimeMillis() - uptimeMillis) + " ms.";
                ContactCacheEntry unused = ContactInfoCache.this.updateCallerInfoInCacheOnAnyThread(dialerCallCookieWrapper.callId, dialerCallCookieWrapper.numberPresentation, callerInfo, true, this.queryToken);
            }
        }

        public void onQueryComplete(int i, Object obj, CallerInfo callerInfo) {
            Trace.beginSection("ContactInfoCache.FindInfoCallback.onQueryComplete");
            Assert.isMainThread();
            String str = ((DialerCallCookieWrapper) obj).callId;
            if (!ContactInfoCache.this.isWaitingForThisQuery(str, this.queryToken.queryId)) {
                Trace.endSection();
                return;
            }
            ContactCacheEntry contactCacheEntry = (ContactCacheEntry) ContactInfoCache.this.infoMap.get(str);
            if (contactCacheEntry == null) {
                Bindings.m42w("ContactInfoCache", "Contact lookup done, but cache entry is not found.");
                ContactInfoCache.this.callBacks.remove(str);
                Trace.endSection();
                return;
            }
            boolean z = callerInfo.contactExists;
            ContactInfoCache.this.sendInfoNotifications(str, contactCacheEntry);
            if (!contactCacheEntry.hasPendingQuery) {
                boolean z2 = callerInfo.contactExists;
                ContactInfoCache.this.callBacks.remove(str);
            }
            Trace.endSection();
        }
    }

    private ContactInfoCache(Context context2) {
        Trace.beginSection("ContactInfoCache constructor");
        this.context = context2;
        Bindings.get(context2).newPhoneNumberService(context2);
        this.cachedNumberLookupExecutor = ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(this.context).dialerExecutorFactory()).createNonUiTaskBuilder(new CachedNumberLookupWorker((C06341) null)).build();
        Trace.endSection();
    }

    static /* synthetic */ void access$300(ContactInfoCache contactInfoCache, CallerInfo callerInfo, String str, boolean z) {
        String str2;
        CequintCallerIdManager.CequintCallerIdContact cequintCallerIdContactForCall;
        if (CequintCallerIdManager.isCequintCallerIdEnabled(contactInfoCache.context) && (str2 = callerInfo.phoneNumber) != null && (cequintCallerIdContactForCall = CequintCallerIdManager.getCequintCallerIdContactForCall(contactInfoCache.context, str2, str, z)) != null) {
            boolean z2 = false;
            if (TextUtils.isEmpty(callerInfo.name) && !TextUtils.isEmpty(cequintCallerIdContactForCall.name())) {
                callerInfo.name = cequintCallerIdContactForCall.name();
                z2 = true;
            }
            if (!TextUtils.isEmpty(cequintCallerIdContactForCall.geolocation())) {
                callerInfo.geoDescription = cequintCallerIdContactForCall.geolocation();
                callerInfo.shouldShowGeoDescription = true;
                z2 = true;
            }
            if (!callerInfo.contactExists && callerInfo.contactDisplayPhotoUri == null && cequintCallerIdContactForCall.photoUri() != null) {
                callerInfo.contactDisplayPhotoUri = Uri.parse(cequintCallerIdContactForCall.photoUri());
                z2 = true;
            }
            if (z2) {
                callerInfo.contactExists = true;
                callerInfo.contactLookupResultType = ContactLookupResult$Type.CEQUINT;
            }
        }
    }

    static ContactCacheEntry buildCacheEntryFromCall(Context context2, DialerCall dialerCall) {
        ContactCacheEntry contactCacheEntry = new ContactCacheEntry();
        populateCacheEntry(context2, CallerInfoUtils.buildCallerInfo(context2, dialerCall), contactCacheEntry, dialerCall.getNumberPresentation());
        return contactCacheEntry;
    }

    public static synchronized ContactInfoCache getInstance(Context context2) {
        ContactInfoCache contactInfoCache;
        synchronized (ContactInfoCache.class) {
            if (cache == null) {
                cache = new ContactInfoCache(context2.getApplicationContext());
            }
            contactInfoCache = cache;
        }
        return contactInfoCache;
    }

    private static String getPresentationString(Context context2, int i, String str) {
        String string = context2.getString(R.string.unknown);
        if (!TextUtils.isEmpty(str) && (i == 3 || i == 2)) {
            return str;
        }
        if (i == 2) {
            return PhoneNumberHelper.getDisplayNameForRestrictedNumber(context2);
        }
        return i == 4 ? context2.getString(R.string.payphone) : string;
    }

    /* access modifiers changed from: private */
    public boolean isWaitingForThisQuery(String str, int i) {
        ContactCacheEntry contactCacheEntry = this.infoMap.get(str);
        if (contactCacheEntry == null) {
            return true;
        }
        int i2 = contactCacheEntry.queryId;
        "waitingQueryId = " + i2 + "; queryId = " + i;
        if (i2 == i) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void populateCacheEntry(android.content.Context r11, com.android.incallui.CallerInfo r12, com.android.incallui.ContactInfoCache.ContactCacheEntry r13, int r14) {
        /*
            java.util.Objects.requireNonNull(r12)
            java.lang.String r0 = r12.phoneNumber
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 != 0) goto L_0x001e
            boolean r1 = com.android.dialer.phonenumberutil.PhoneNumberHelper.isUriNumber(r0)
            java.lang.String r3 = "sip:"
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L_0x001f
            r3 = 4
            java.lang.String r0 = r0.substring(r3)
            goto L_0x001f
        L_0x001e:
            r1 = r2
        L_0x001f:
            java.lang.String r3 = r12.name
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            java.lang.String r4 = "', displayNumber '"
            java.lang.String r5 = "'"
            r6 = 1
            r7 = 0
            if (r3 == 0) goto L_0x0107
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L_0x0053
            java.lang.String r3 = r12.cnapName
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0053
            java.lang.String r0 = r12.callSubject
            java.lang.String r11 = getPresentationString(r11, r14, r0)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "  ==> no name *or* number! displayName = "
            r14.append(r0)
            r14.append(r11)
            r14.toString()
            goto L_0x011f
        L_0x0053:
            if (r14 == r6) goto L_0x006d
            java.lang.String r0 = r12.callSubject
            java.lang.String r11 = getPresentationString(r11, r14, r0)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "  ==> presentation not allowed! displayName = "
            r14.append(r0)
            r14.append(r11)
            r14.toString()
            goto L_0x011f
        L_0x006d:
            java.lang.String r14 = r12.cnapName
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x009d
            java.lang.String r14 = r12.cnapName
            r12.name = r14
            java.lang.String r2 = r12.countryIso
            java.lang.String r11 = com.android.dialer.phonenumberutil.PhoneNumberHelper.formatNumber(r11, r0, r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "  ==> cnapName available: displayName '"
            r0.append(r2)
            r0.append(r14)
            r0.append(r4)
            r0.append(r11)
            r0.append(r5)
            r0.toString()
            r10 = r7
            r7 = r14
            r14 = r10
            goto L_0x014a
        L_0x009d:
            java.lang.String r14 = r12.countryIso
            java.lang.String r11 = com.android.dialer.phonenumberutil.PhoneNumberHelper.formatNumber(r11, r0, r14)
            java.lang.String r14 = "  ==>  no name; falling back to number: displayNumber '"
            java.lang.StringBuilder r14 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r14)
            if (r11 == 0) goto L_0x00f8
            boolean r0 = com.android.dialer.common.LogUtil.isVerboseEnabled()
            if (r0 == 0) goto L_0x00b2
            goto L_0x00f8
        L_0x00b2:
            java.lang.String r0 = "["
            java.lang.StringBuilder r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r0)
            byte[] r3 = r11.getBytes()
            java.lang.String r4 = "SHA-1"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch:{ NoSuchAlgorithmException -> 0x00f0 }
            r4.update(r3)
            byte[] r3 = r4.digest()
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            int r6 = r3.length
            int r6 = r6 * 2
            r4.<init>(r6)
        L_0x00d1:
            int r6 = r3.length
            if (r2 >= r6) goto L_0x00eb
            byte r6 = r3[r2]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r8 = 16
            if (r6 >= r8) goto L_0x00e1
            java.lang.String r9 = "0"
            r4.append(r9)
        L_0x00e1:
            java.lang.String r6 = java.lang.Integer.toString(r6, r8)
            r4.append(r6)
            int r2 = r2 + 1
            goto L_0x00d1
        L_0x00eb:
            java.lang.String r2 = r4.toString()
            goto L_0x00f1
        L_0x00f0:
            r2 = r7
        L_0x00f1:
            java.lang.String r3 = "]"
            java.lang.String r0 = com.android.tools.p006r8.GeneratedOutlineSupport.outline12(r0, r2, r3)
            goto L_0x00fc
        L_0x00f8:
            java.lang.String r0 = java.lang.String.valueOf(r11)
        L_0x00fc:
            r14.append(r0)
            r14.append(r5)
            r14.toString()
            r14 = r7
            goto L_0x014a
        L_0x0107:
            if (r14 == r6) goto L_0x0123
            java.lang.String r0 = r12.callSubject
            java.lang.String r11 = getPresentationString(r11, r14, r0)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "  ==> valid name, but presentation not allowed! displayName = "
            r14.append(r0)
            r14.append(r11)
            r14.toString()
        L_0x011f:
            r14 = r7
            r7 = r11
            r11 = r14
            goto L_0x014a
        L_0x0123:
            java.lang.String r7 = r12.name
            java.lang.String r14 = r12.nameAlternative
            r13.nameAlternative = r14
            java.lang.String r14 = r12.countryIso
            java.lang.String r11 = com.android.dialer.phonenumberutil.PhoneNumberHelper.formatNumber(r11, r0, r14)
            java.lang.String r14 = r12.phoneLabel
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "  ==>  name is present in CallerInfo: displayName '"
            r0.append(r2)
            r0.append(r7)
            r0.append(r4)
            r0.append(r11)
            r0.append(r5)
            r0.toString()
        L_0x014a:
            r13.namePrimary = r7
            r13.number = r11
            java.lang.String r11 = r12.geoDescription
            r13.location = r11
            r13.label = r14
            r13.isSipCall = r1
            long r0 = r12.userType
            r13.userType = r0
            java.lang.String r11 = r12.phoneNumber
            r13.originalPhoneNumber = r11
            boolean r11 = r12.shouldShowGeoDescription
            r13.shouldShowLocation = r11
            boolean r11 = r12.isEmergencyNumber()
            r13.isEmergencyNumber = r11
            boolean r11 = r12.isVoiceMailNumber()
            r13.isVoicemailNumber = r11
            boolean r11 = r12.contactExists
            if (r11 == 0) goto L_0x0176
            com.android.dialer.logging.ContactLookupResult$Type r11 = r12.contactLookupResultType
            r13.contactLookupResult = r11
        L_0x0176:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.ContactInfoCache.populateCacheEntry(android.content.Context, com.android.incallui.CallerInfo, com.android.incallui.ContactInfoCache$ContactCacheEntry, int):void");
    }

    /* access modifiers changed from: private */
    public void sendInfoNotifications(String str, ContactCacheEntry contactCacheEntry) {
        Trace.beginSection("ContactInfoCache.sendInfoNotifications");
        Assert.isMainThread();
        Set<ContactInfoCacheCallback> set = this.callBacks.get(str);
        if (set != null) {
            for (ContactInfoCacheCallback onContactInfoComplete : set) {
                onContactInfoComplete.onContactInfoComplete(str, contactCacheEntry);
            }
        }
        Trace.endSection();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0145  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.incallui.ContactInfoCache.ContactCacheEntry updateCallerInfoInCacheOnAnyThread(java.lang.String r11, int r12, com.android.incallui.CallerInfo r13, boolean r14, com.android.incallui.ContactInfoCache.CallerInfoQueryToken r15) {
        /*
            r10 = this;
            java.lang.String r0 = "ContactInfoCache.updateCallerInfoInCacheOnAnyThread"
            android.os.Trace.beginSection(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "updateCallerInfoInCacheOnAnyThread: callId = "
            r0.append(r1)
            r0.append(r11)
            java.lang.String r1 = "; queryId = "
            r0.append(r1)
            int r1 = r15.queryId
            r0.append(r1)
            java.lang.String r1 = "; didLocalLookup = "
            r0.append(r1)
            r0.append(r14)
            r0.toString()
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.android.incallui.ContactInfoCache$ContactCacheEntry> r0 = r10.infoMap
            java.lang.Object r0 = r0.get(r11)
            com.android.incallui.ContactInfoCache$ContactCacheEntry r0 = (com.android.incallui.ContactInfoCache.ContactCacheEntry) r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Existing cacheEntry in hashMap "
            r1.append(r2)
            r1.append(r0)
            r1.toString()
            if (r0 == 0) goto L_0x0054
            boolean r1 = r0.isEmergencyNumber
            if (r1 == 0) goto L_0x004b
            android.content.Context r1 = r10.context
            r13.markAsEmergency(r1)
            goto L_0x0054
        L_0x004b:
            boolean r1 = r0.isVoicemailNumber
            if (r1 == 0) goto L_0x0054
            android.content.Context r1 = r10.context
            r13.markAsVoiceMail(r1)
        L_0x0054:
            boolean r1 = r13.contactExists
            r2 = 1
            if (r1 != 0) goto L_0x0065
            boolean r1 = r13.isEmergencyNumber()
            if (r1 != 0) goto L_0x0065
            boolean r1 = r13.isVoiceMailNumber()
            if (r1 == 0) goto L_0x0066
        L_0x0065:
            r12 = r2
        L_0x0066:
            android.content.Context r1 = r10.context
            com.android.incallui.ContactInfoCache$ContactCacheEntry r3 = new com.android.incallui.ContactInfoCache$ContactCacheEntry
            r3.<init>()
            populateCacheEntry(r1, r13, r3, r12)
            int r12 = r13.photoResource
            r4 = 0
            r5 = 0
            if (r12 == 0) goto L_0x007d
            android.graphics.drawable.Drawable r12 = android.support.p000v4.content.ContextCompat.getDrawable(r1, r12)
            r3.photo = r12
            goto L_0x0094
        L_0x007d:
            boolean r12 = r13.isCachedPhotoCurrent
            if (r12 == 0) goto L_0x008e
            android.graphics.drawable.Drawable r12 = r13.cachedPhoto
            if (r12 == 0) goto L_0x008b
            r3.photo = r12
            r12 = 2
            r3.photoType = r12
            goto L_0x0094
        L_0x008b:
            r3.photoType = r5
            goto L_0x0094
        L_0x008e:
            android.net.Uri r12 = r13.contactDisplayPhotoUri
            r3.displayPhotoUri = r12
            r3.photo = r4
        L_0x0094:
            java.lang.String r12 = r13.lookupKeyOrNull
            if (r12 == 0) goto L_0x00a7
            long r6 = r13.contactIdOrZero
            r8 = 0
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 == 0) goto L_0x00a7
            android.net.Uri r12 = android.provider.ContactsContract.Contacts.getLookupUri(r6, r12)
            r3.lookupUri = r12
            goto L_0x00b0
        L_0x00a7:
            java.lang.String r12 = "ContactInfoCache"
            java.lang.String r1 = "lookup key is null or contact ID is 0 on M. Don't create a lookup uri."
            com.android.incallui.Bindings.m40v(r12, r1)
            r3.lookupUri = r4
        L_0x00b0:
            java.lang.String r12 = r13.lookupKeyOrNull
            r3.lookupKey = r12
            android.net.Uri r12 = r13.contactRingtoneUri
            r3.contactRingtoneUri = r12
            android.net.Uri r12 = r3.contactRingtoneUri
            if (r12 == 0) goto L_0x00c4
            android.net.Uri r13 = android.net.Uri.EMPTY
            boolean r12 = r13.equals(r12)
            if (r12 == 0) goto L_0x00ca
        L_0x00c4:
            android.net.Uri r12 = android.media.RingtoneManager.getDefaultUri(r2)
            r3.contactRingtoneUri = r12
        L_0x00ca:
            int r12 = r15.queryId
            r3.queryId = r12
            if (r14 == 0) goto L_0x0145
            android.net.Uri r12 = r3.displayPhotoUri
            if (r12 == 0) goto L_0x012f
            if (r0 == 0) goto L_0x00eb
            android.net.Uri r13 = r0.displayPhotoUri
            if (r13 == 0) goto L_0x00eb
            boolean r12 = r13.equals(r12)
            if (r12 == 0) goto L_0x00eb
            android.graphics.drawable.Drawable r12 = r0.photo
            if (r12 == 0) goto L_0x00eb
            r3.photo = r12
            int r10 = r0.photoType
            r3.photoType = r10
            return r3
        L_0x00eb:
            r3.hasPendingQuery = r2
            android.content.Context r12 = r10.context
            android.net.Uri r13 = r3.displayPhotoUri
            if (r13 != 0) goto L_0x00fd
            java.lang.Object[] r12 = new java.lang.Object[r5]
            java.lang.String r13 = "ContactsAsyncHelper.startObjectPhotoAsync"
            java.lang.String r14 = "uri is missing"
            com.android.dialer.common.LogUtil.m8e((java.lang.String) r13, (java.lang.String) r14, (java.lang.Object[]) r12)
            goto L_0x012f
        L_0x00fd:
            com.android.incallui.ContactsAsyncHelper$WorkerArgs r14 = new com.android.incallui.ContactsAsyncHelper$WorkerArgs
            r14.<init>(r4)
            r14.token = r5
            r14.cookie = r15
            r14.context = r12
            r14.displayPhotoUri = r13
            r14.listener = r10
            com.android.dialer.common.concurrent.DialerExecutorComponent r12 = com.android.dialer.common.concurrent.DialerExecutorComponent.get(r12)
            com.android.dialer.common.concurrent.DialerExecutorFactory r12 = r12.dialerExecutorFactory()
            com.android.incallui.ContactsAsyncHelper$Worker r13 = new com.android.incallui.ContactsAsyncHelper$Worker
            r13.<init>(r4)
            com.android.dialer.common.concurrent.DefaultDialerExecutorFactory r12 = (com.android.dialer.common.concurrent.DefaultDialerExecutorFactory) r12
            com.android.dialer.common.concurrent.DialerExecutor$Builder r12 = r12.createNonUiTaskBuilder(r13)
            com.android.incallui.-$$Lambda$Bindings$OFZtj2MNMZRStah_ezhHp6svnSk r13 = new com.android.incallui.-$$Lambda$Bindings$OFZtj2MNMZRStah_ezhHp6svnSk
            r13.<init>()
            com.android.dialer.common.concurrent.DialerExecutor$Builder r12 = r12.onSuccess(r13)
            com.android.dialer.common.concurrent.DialerExecutor r12 = r12.build()
            r12.executeParallel(r14)
        L_0x012f:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "put entry into map: "
            r12.append(r13)
            r12.append(r3)
            r12.toString()
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.android.incallui.ContactInfoCache$ContactCacheEntry> r10 = r10.infoMap
            r10.put(r11, r3)
            goto L_0x015a
        L_0x0145:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "put entry into map if not exists: "
            r12.append(r13)
            r12.append(r3)
            r12.toString()
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.android.incallui.ContactInfoCache$ContactCacheEntry> r10 = r10.infoMap
            r10.putIfAbsent(r11, r3)
        L_0x015a:
            android.os.Trace.endSection()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.ContactInfoCache.updateCallerInfoInCacheOnAnyThread(java.lang.String, int, com.android.incallui.CallerInfo, boolean, com.android.incallui.ContactInfoCache$CallerInfoQueryToken):com.android.incallui.ContactInfoCache$ContactCacheEntry");
    }

    public void clearCache() {
        this.infoMap.clear();
        this.callBacks.clear();
        this.queryId = 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void findInfo(com.android.incallui.call.DialerCall r10, boolean r11, com.android.incallui.ContactInfoCache.ContactInfoCacheCallback r12) {
        /*
            r9 = this;
            java.lang.String r0 = "ContactInfoCache.findInfo"
            android.os.Trace.beginSection(r0)
            com.android.dialer.common.Assert.isMainThread()
            java.util.Objects.requireNonNull(r12)
            java.lang.String r0 = "prepare callback"
            android.os.Trace.beginSection(r0)
            java.lang.String r0 = r10.getId()
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.android.incallui.ContactInfoCache$ContactCacheEntry> r1 = r9.infoMap
            java.lang.Object r1 = r1.get(r0)
            com.android.incallui.ContactInfoCache$ContactCacheEntry r1 = (com.android.incallui.ContactInfoCache.ContactCacheEntry) r1
            java.util.Map<java.lang.String, java.util.Set<com.android.incallui.ContactInfoCache$ContactInfoCacheCallback>> r2 = r9.callBacks
            java.lang.Object r2 = r2.get(r0)
            java.util.Set r2 = (java.util.Set) r2
            boolean r3 = r10.isConferenceCall()
            r4 = 0
            r5 = 1
            if (r3 == 0) goto L_0x002d
            goto L_0x005e
        L_0x002d:
            java.lang.String r3 = r10.getNumber()
            java.lang.String r3 = android.telephony.PhoneNumberUtils.stripSeparators(r3)
            if (r1 != 0) goto L_0x0038
            goto L_0x005c
        L_0x0038:
            java.lang.String r6 = r1.originalPhoneNumber
            java.lang.String r6 = android.telephony.PhoneNumberUtils.stripSeparators(r6)
            boolean r7 = android.text.TextUtils.equals(r6, r3)
            if (r7 != 0) goto L_0x005e
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "phone number has changed: "
            r7.append(r8)
            r7.append(r6)
            java.lang.String r6 = " -> "
            r7.append(r6)
            r7.append(r3)
            r7.toString()
        L_0x005c:
            r3 = r5
            goto L_0x005f
        L_0x005e:
            r3 = r4
        L_0x005f:
            android.os.Trace.endSection()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "findInfo: callId = "
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = "; forceQuery = "
            r6.append(r7)
            r6.append(r3)
            r6.toString()
            if (r1 == 0) goto L_0x009a
            if (r3 != 0) goto L_0x009a
            java.lang.String r6 = "Contact lookup. In memory cache hit; lookup "
            java.lang.StringBuilder r6 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r6)
            if (r2 != 0) goto L_0x0089
            java.lang.String r7 = "complete"
            goto L_0x008b
        L_0x0089:
            java.lang.String r7 = "still running"
        L_0x008b:
            r6.append(r7)
            r6.toString()
            r12.onContactInfoComplete(r0, r1)
            if (r2 != 0) goto L_0x009a
            android.os.Trace.endSection()
            return
        L_0x009a:
            if (r2 == 0) goto L_0x00a5
            r2.add(r12)
            if (r3 != 0) goto L_0x00b2
            android.os.Trace.endSection()
            return
        L_0x00a5:
            android.util.ArraySet r2 = new android.util.ArraySet
            r2.<init>()
            r2.add(r12)
            java.util.Map<java.lang.String, java.util.Set<com.android.incallui.ContactInfoCache$ContactInfoCacheCallback>> r12 = r9.callBacks
            r12.put(r0, r2)
        L_0x00b2:
            java.lang.String r12 = "prepare query"
            android.os.Trace.beginSection(r12)
            com.android.incallui.ContactInfoCache$CallerInfoQueryToken r6 = new com.android.incallui.ContactInfoCache$CallerInfoQueryToken
            int r12 = r9.queryId
            r6.<init>(r12, r0)
            int r12 = r9.queryId
            int r12 = r12 + r5
            r9.queryId = r12
            android.content.Context r12 = r9.context
            com.android.incallui.ContactInfoCache$DialerCallCookieWrapper r2 = new com.android.incallui.ContactInfoCache$DialerCallCookieWrapper
            int r3 = r10.getNumberPresentation()
            java.lang.String r7 = r10.getCnapName()
            r2.<init>(r0, r3, r7)
            com.android.incallui.ContactInfoCache$FindInfoCallback r3 = new com.android.incallui.ContactInfoCache$FindInfoCallback
            r3.<init>(r11, r6)
            com.android.incallui.CallerInfo r11 = com.android.incallui.CallerInfoUtils.buildCallerInfo(r12, r10)
            int r7 = r11.numberPresentation
            if (r7 != r5) goto L_0x00f3
            boolean r5 = com.android.dialer.util.PermissionsUtil.hasContactsReadPermissions(r12)
            if (r5 == 0) goto L_0x00ea
            r4 = -1
            com.android.incallui.CallerInfoAsyncQuery.startQuery(r4, r12, r11, r3, r2)
            goto L_0x00f3
        L_0x00ea:
            java.lang.Object[] r12 = new java.lang.Object[r4]
            java.lang.String r2 = "CallerInfoUtils.getCallerInfoForCall"
            java.lang.String r3 = "Dialer doesn't have permission to read contacts. Not calling CallerInfoAsyncQuery.startQuery()."
            com.android.dialer.common.LogUtil.m10w(r2, r3, r12)
        L_0x00f3:
            android.os.Trace.endSection()
            if (r1 == 0) goto L_0x00fd
            int r9 = r6.queryId
            r1.queryId = r9
            goto L_0x010c
        L_0x00fd:
            int r3 = r10.getNumberPresentation()
            r5 = 0
            r1 = r9
            r2 = r0
            r4 = r11
            com.android.incallui.ContactInfoCache$ContactCacheEntry r10 = r1.updateCallerInfoInCacheOnAnyThread(r2, r3, r4, r5, r6)
            r9.sendInfoNotifications(r0, r10)
        L_0x010c:
            android.os.Trace.endSection()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.ContactInfoCache.findInfo(com.android.incallui.call.DialerCall, boolean, com.android.incallui.ContactInfoCache$ContactInfoCacheCallback):void");
    }

    /* access modifiers changed from: package-private */
    public ContactCacheEntry getInfo(String str) {
        return this.infoMap.get(str);
    }

    /* access modifiers changed from: package-private */
    public void maybeInsertCnapInformationIntoCache(Context context2, DialerCall dialerCall, CallerInfo callerInfo) {
        PhoneNumberCache.get(context2).getCachedNumberLookupService();
        if (!R$dimen.isUserUnlocked(context2)) {
            LogUtil.m9i("ContactInfoCache", "User locked, not inserting cnap info into cache", new Object[0]);
        }
    }

    public void onImageLoadComplete(int i, Drawable drawable, Bitmap bitmap, Object obj) {
        Assert.isMainThread();
        CallerInfoQueryToken callerInfoQueryToken = (CallerInfoQueryToken) obj;
        String str = callerInfoQueryToken.callId;
        if (isWaitingForThisQuery(str, callerInfoQueryToken.queryId)) {
            ContactCacheEntry contactCacheEntry = this.infoMap.get(str);
            Trace.beginSection("ContactInfoCache.sendImageNotifications");
            Assert.isMainThread();
            Set<ContactInfoCacheCallback> set = this.callBacks.get(str);
            if (!(set == null || contactCacheEntry.photo == null)) {
                for (ContactInfoCacheCallback onImageLoadComplete : set) {
                    onImageLoadComplete.onImageLoadComplete(str, contactCacheEntry);
                }
            }
            Trace.endSection();
            this.callBacks.remove(str);
        }
    }

    public void onImageLoaded(int i, Drawable drawable, Bitmap bitmap, Object obj) {
        Assert.isWorkerThread();
        CallerInfoQueryToken callerInfoQueryToken = (CallerInfoQueryToken) obj;
        if (isWaitingForThisQuery(callerInfoQueryToken.callId, callerInfoQueryToken.queryId)) {
            Bindings.m35d("ContactInfoCache", "Image load complete with context: ", this.context);
            String str = callerInfoQueryToken.callId;
            ContactCacheEntry contactCacheEntry = this.infoMap.get(str);
            if (contactCacheEntry == null) {
                LogUtil.m8e("ContactInfoCache", "Image Load received for empty search entry.", new Object[0]);
                this.callBacks.remove(str);
                return;
            }
            Bindings.m35d("ContactInfoCache", "setting photo for entry: ", contactCacheEntry);
            if (drawable != null) {
                Bindings.m41v("ContactInfoCache", "direct drawable: ", drawable);
                contactCacheEntry.photo = drawable;
                contactCacheEntry.photoType = 2;
            } else if (bitmap != null) {
                Bindings.m41v("ContactInfoCache", "photo icon: ", bitmap);
                contactCacheEntry.photo = new BitmapDrawable(this.context.getResources(), bitmap);
                contactCacheEntry.photoType = 2;
            } else {
                Bindings.m40v("ContactInfoCache", "unknown photo");
                contactCacheEntry.photo = null;
                contactCacheEntry.photoType = 0;
            }
        }
    }
}
