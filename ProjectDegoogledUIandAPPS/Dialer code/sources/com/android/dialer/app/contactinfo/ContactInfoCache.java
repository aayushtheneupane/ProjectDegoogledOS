package com.android.dialer.app.contactinfo;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.p002v7.appcompat.R$style;
import android.text.TextUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.logging.ContactSource$Type;
import com.android.dialer.oem.CequintCallerIdManager;
import com.android.dialer.phonenumbercache.ContactInfo;
import com.android.dialer.phonenumbercache.ContactInfoHelper;
import com.android.dialer.util.ExpirableCache;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class ContactInfoCache {
    private final ExpirableCache<NumberWithCountryIso, ContactInfo> cache;
    private CequintCallerIdManager cequintCallerIdManager;
    private final ContactInfoHelper contactInfoHelper;
    private QueryThread contactInfoQueryThread;
    /* access modifiers changed from: private */
    public final Handler handler;
    /* access modifiers changed from: private */
    public final OnContactInfoChangedListener onContactInfoChangedListener;
    private volatile boolean requestProcessingDisabled = false;
    /* access modifiers changed from: private */
    public final BlockingQueue<ContactInfoRequest> updateRequests;

    private static class InnerHandler extends Handler {
        private final WeakReference<ContactInfoCache> contactInfoCacheWeakReference;

        public InnerHandler(WeakReference<ContactInfoCache> weakReference) {
            this.contactInfoCacheWeakReference = weakReference;
        }

        public void handleMessage(Message message) {
            ContactInfoCache contactInfoCache = (ContactInfoCache) this.contactInfoCacheWeakReference.get();
            if (contactInfoCache != null) {
                int i = message.what;
                if (i == 1) {
                    contactInfoCache.onContactInfoChangedListener.onContactInfoChanged();
                } else if (i == 2) {
                    contactInfoCache.startRequestProcessing();
                }
            }
        }
    }

    public interface OnContactInfoChangedListener {
        void onContactInfoChanged();
    }

    private class QueryThread extends Thread {
        private volatile boolean done = false;

        public QueryThread() {
            super("ContactInfoCache.QueryThread");
        }

        public void run() {
            while (true) {
                boolean z = false;
                while (!this.done) {
                    try {
                        ContactInfoRequest contactInfoRequest = (ContactInfoRequest) ContactInfoCache.this.updateRequests.take();
                        z |= ContactInfoCache.this.queryContactInfo(contactInfoRequest);
                        if (z && (ContactInfoCache.this.updateRequests.isEmpty() || (contactInfoRequest.isLocalRequest() && !((ContactInfoRequest) ContactInfoCache.this.updateRequests.peek()).isLocalRequest()))) {
                            try {
                                ContactInfoCache.this.handler.sendEmptyMessage(1);
                            } catch (InterruptedException unused) {
                            }
                        }
                    } catch (InterruptedException unused2) {
                    }
                }
                return;
            }
        }

        public void stopProcessing() {
            this.done = true;
        }
    }

    public ContactInfoCache(ExpirableCache<NumberWithCountryIso, ContactInfo> expirableCache, ContactInfoHelper contactInfoHelper2, OnContactInfoChangedListener onContactInfoChangedListener2) {
        this.cache = expirableCache;
        this.contactInfoHelper = contactInfoHelper2;
        this.onContactInfoChangedListener = onContactInfoChangedListener2;
        this.updateRequests = new PriorityBlockingQueue();
        this.handler = new InnerHandler(new WeakReference(this));
    }

    private void enqueueRequest(String str, String str2, ContactInfo contactInfo, boolean z, int i) {
        ContactInfoRequest contactInfoRequest = new ContactInfoRequest(str, str2, contactInfo, i);
        if (!this.updateRequests.contains(contactInfoRequest)) {
            this.updateRequests.offer(contactInfoRequest);
        }
        if (z) {
            startRequestProcessing();
        }
    }

    /* access modifiers changed from: private */
    public boolean queryContactInfo(ContactInfoRequest contactInfoRequest) {
        ContactInfo contactInfo;
        boolean z = false;
        Object[] objArr = {LogUtil.sanitizePhoneNumber(contactInfoRequest.number), Integer.valueOf(contactInfoRequest.type)};
        if (contactInfoRequest.isLocalRequest()) {
            contactInfo = this.contactInfoHelper.lookupNumber(contactInfoRequest.number, contactInfoRequest.countryIso);
            if (contactInfo != null && !contactInfo.contactExists) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                this.contactInfoHelper.updateFromCequintCallerId(this.cequintCallerIdManager, contactInfo, contactInfoRequest.number);
                new Object[1][0] = Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime);
            }
            if (contactInfoRequest.type == 1 && !this.contactInfoHelper.hasName(contactInfo)) {
                enqueueRequest(contactInfoRequest.number, contactInfoRequest.countryIso, contactInfoRequest.callLogInfo, true, 2);
                return false;
            }
        } else {
            contactInfo = this.contactInfoHelper.lookupNumberInRemoteDirectory(contactInfoRequest.number, contactInfoRequest.countryIso);
        }
        if (contactInfo == null) {
            return false;
        }
        NumberWithCountryIso numberWithCountryIso = new NumberWithCountryIso(contactInfoRequest.number, contactInfoRequest.countryIso);
        ContactInfo possiblyExpired = this.cache.getPossiblyExpired(numberWithCountryIso);
        boolean z2 = contactInfo.sourceType != ContactSource$Type.UNKNOWN_SOURCE_TYPE;
        if (!Objects.equals(possiblyExpired, ContactInfo.EMPTY) || z2) {
            if (!(contactInfo == possiblyExpired || (possiblyExpired != null && ContactInfo.class == ContactInfo.class && R$style.areEqual(contactInfo.lookupUri, possiblyExpired.lookupUri) && TextUtils.equals(contactInfo.name, possiblyExpired.name) && TextUtils.equals(contactInfo.nameAlternative, possiblyExpired.nameAlternative) && contactInfo.type == possiblyExpired.type && TextUtils.equals(contactInfo.label, possiblyExpired.label) && TextUtils.equals(contactInfo.number, possiblyExpired.number) && TextUtils.equals(contactInfo.formattedNumber, possiblyExpired.formattedNumber) && TextUtils.equals(contactInfo.normalizedNumber, possiblyExpired.normalizedNumber) && contactInfo.photoId == possiblyExpired.photoId && R$style.areEqual(contactInfo.photoUri, possiblyExpired.photoUri) && TextUtils.equals(contactInfo.objectId, possiblyExpired.objectId) && contactInfo.userType == possiblyExpired.userType && contactInfo.carrierPresence == possiblyExpired.carrierPresence && TextUtils.equals(contactInfo.geoDescription, possiblyExpired.geoDescription)))) {
                z = true;
            }
        }
        this.cache.put(numberWithCountryIso, contactInfo);
        this.contactInfoHelper.updateCallLogContactInfo(contactInfoRequest.number, contactInfoRequest.countryIso, contactInfo, contactInfoRequest.callLogInfo);
        if (!contactInfoRequest.isLocalRequest()) {
            this.contactInfoHelper.updateCachedNumberLookupService(contactInfo);
        }
        return z;
    }

    /* access modifiers changed from: private */
    public synchronized void startRequestProcessing() {
        if (!this.requestProcessingDisabled) {
            if (this.contactInfoQueryThread == null) {
                this.contactInfoQueryThread = new QueryThread();
                this.contactInfoQueryThread.setPriority(1);
                this.contactInfoQueryThread.start();
            }
        }
    }

    private synchronized void stopRequestProcessing() {
        this.handler.removeMessages(2);
        if (this.contactInfoQueryThread != null) {
            this.contactInfoQueryThread.stopProcessing();
            this.contactInfoQueryThread.interrupt();
            this.contactInfoQueryThread = null;
        }
    }

    public void disableRequestProcessing() {
        this.requestProcessingDisabled = true;
    }

    public ContactInfo getValue(String str, String str2, ContactInfo contactInfo, boolean z) {
        ContactInfo contactInfo2;
        NumberWithCountryIso numberWithCountryIso = new NumberWithCountryIso(str, str2);
        ExpirableCache.CachedValue<ContactInfo> cachedValue = this.cache.getCachedValue(numberWithCountryIso);
        if (cachedValue == null) {
            contactInfo2 = null;
        } else {
            contactInfo2 = cachedValue.getValue();
        }
        if (cachedValue == null) {
            this.cache.put(numberWithCountryIso, ContactInfo.EMPTY);
            enqueueRequest(str, str2, contactInfo, true, z ? 1 : 0);
            return contactInfo;
        }
        if (cachedValue.isExpired()) {
            enqueueRequest(str, str2, contactInfo, false, z ? 1 : 0);
        } else {
            if (!(TextUtils.equals(contactInfo.name, contactInfo2.name) && contactInfo.type == contactInfo2.type && TextUtils.equals(contactInfo.label, contactInfo2.label))) {
                enqueueRequest(str, str2, contactInfo, false, z ? 1 : 0);
            }
        }
        return Objects.equals(contactInfo2, ContactInfo.EMPTY) ? contactInfo : contactInfo2;
    }

    public void injectContactInfoForTest(String str, String str2, ContactInfo contactInfo) {
        this.cache.put(new NumberWithCountryIso(str, str2), contactInfo);
    }

    public void invalidate() {
        this.cache.expireAll();
        stopRequestProcessing();
    }

    public void setCequintCallerIdManager(CequintCallerIdManager cequintCallerIdManager2) {
        this.cequintCallerIdManager = cequintCallerIdManager2;
    }

    public void start() {
        if (this.contactInfoQueryThread == null) {
            this.handler.sendEmptyMessageDelayed(2, 1000);
        }
    }

    public void stop() {
        stopRequestProcessing();
    }
}
