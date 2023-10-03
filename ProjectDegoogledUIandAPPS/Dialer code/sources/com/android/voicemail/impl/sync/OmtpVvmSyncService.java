package com.android.voicemail.impl.sync;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Network;
import android.telecom.PhoneAccountHandle;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.voicemail.impl.OmtpEvents;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.Voicemail;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.fetch.VoicemailFetchedCallback;
import com.android.voicemail.impl.imap.ImapHelper;
import com.android.voicemail.impl.scheduling.BaseTask;
import com.android.voicemail.impl.utils.LoggerUtils;

@TargetApi(26)
public class OmtpVvmSyncService {
    private final Context context;
    private final VoicemailsQueryHelper queryHelper = new VoicemailsQueryHelper(this.context);

    public static class TranscriptionFetchedCallback {
        private Context context;
        private Voicemail voicemail;

        public TranscriptionFetchedCallback(Context context2, Voicemail voicemail2) {
            this.context = context2;
            this.voicemail = voicemail2;
        }

        public void setVoicemailTranscription(String str) {
            new VoicemailsQueryHelper(this.context).updateWithTranscription(this.voicemail, str);
        }
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    public OmtpVvmSyncService(Context context2) {
        this.context = context2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void autoDeleteAndArchiveVM(com.android.voicemail.impl.imap.ImapHelper r7, android.telecom.PhoneAccountHandle r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.context
            com.android.voicemail.VoicemailComponent r1 = com.android.voicemail.VoicemailComponent.get(r0)
            com.android.voicemail.VoicemailClient r1 = r1.getVoicemailClient()
            boolean r1 = r1.isVoicemailArchiveAvailable(r0)
            java.lang.String r2 = "isArchiveAllowedAndEnabled"
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L_0x001b
            java.lang.String r8 = "voicemail archive is not available"
            com.android.voicemail.impl.VvmLog.m45i(r2, r8)
        L_0x0019:
            r8 = r4
            goto L_0x0034
        L_0x001b:
            boolean r1 = com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil.isArchiveEnabled(r0, r8)
            if (r1 != 0) goto L_0x0027
            java.lang.String r8 = "voicemail archive is turned off"
            com.android.voicemail.impl.VvmLog.m45i(r2, r8)
            goto L_0x0019
        L_0x0027:
            boolean r8 = com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil.isEnabled(r0, r8)
            if (r8 != 0) goto L_0x0033
            java.lang.String r8 = "voicemail is turned off"
            com.android.voicemail.impl.VvmLog.m45i(r2, r8)
            goto L_0x0019
        L_0x0033:
            r8 = r3
        L_0x0034:
            java.lang.String r0 = "OmtpVvmSyncService"
            if (r8 != 0) goto L_0x0045
            java.lang.String r7 = "autoDeleteAndArchiveVM is turned off"
            com.android.voicemail.impl.VvmLog.m45i(r0, r7)
            android.content.Context r6 = r6.context
            com.android.dialer.logging.DialerImpression$Type r7 = com.android.dialer.logging.DialerImpression$Type.VVM_ARCHIVE_AUTO_DELETE_TURNED_OFF
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r6, r7)
            return
        L_0x0045:
            com.android.voicemail.impl.mail.store.ImapFolder$Quota r8 = r7.getQuota()
            if (r8 != 0) goto L_0x0058
            android.content.Context r6 = r6.context
            com.android.dialer.logging.DialerImpression$Type r7 = com.android.dialer.logging.DialerImpression$Type.VVM_ARCHIVE_AUTO_DELETE_FAILED_DUE_TO_FAILED_QUOTA_CHECK
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r6, r7)
            java.lang.String r6 = "autoDeleteAndArchiveVM failed - Can't retrieve Imap quota."
            com.android.voicemail.impl.VvmLog.m43e(r0, r6)
            return
        L_0x0058:
            int r1 = r8.occupied
            float r1 = (float) r1
            int r2 = r8.total
            float r2 = (float) r2
            float r1 = r1 / r2
            r2 = 1061158912(0x3f400000, float:0.75)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x00c0
            int r1 = android.os.Build.VERSION.SDK_INT
            com.android.voicemail.impl.Assert.isTrue(r3)
            int r1 = r8.occupied
            int r8 = r8.total
            float r8 = (float) r8
            float r8 = r8 * r2
            int r8 = (int) r8
            int r1 = r1 - r8
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r8 = r6.queryHelper
            java.util.List r8 = r8.oldestVoicemailsOnServer(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "number of voicemails to delete "
            r2.append(r5)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.android.voicemail.impl.VvmLog.m46w(r0, r1)
            boolean r1 = r8.isEmpty()
            if (r1 != 0) goto L_0x00b0
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r1 = r6.queryHelper
            r1.markArchivedInDatabase(r8)
            r7.markMessagesAsDeleted(r8)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            int r8 = r8.size()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r1[r4] = r8
            java.lang.String r8 = "successfully archived and deleted %d voicemails"
            java.lang.String r8 = java.lang.String.format(r8, r1)
            com.android.voicemail.impl.VvmLog.m45i(r0, r8)
            goto L_0x00b5
        L_0x00b0:
            java.lang.String r8 = "remote voicemail server is empty"
            com.android.voicemail.impl.VvmLog.m46w(r0, r8)
        L_0x00b5:
            r7.updateQuota()
            android.content.Context r6 = r6.context
            com.android.dialer.logging.DialerImpression$Type r7 = com.android.dialer.logging.DialerImpression$Type.VVM_ARCHIVE_AUTO_DELETED_VM_FROM_SERVER
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r6, r7)
            goto L_0x00c5
        L_0x00c0:
            java.lang.String r6 = "no need to archive and auto delete VM, quota below threshold"
            com.android.voicemail.impl.VvmLog.m45i(r0, r6)
        L_0x00c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.sync.OmtpVvmSyncService.autoDeleteAndArchiveVM(com.android.voicemail.impl.imap.ImapHelper, android.telecom.PhoneAccountHandle):void");
    }

    private void doSync(BaseTask baseTask, Network network, PhoneAccountHandle phoneAccountHandle, Voicemail voicemail, VoicemailStatus$Editor voicemailStatus$Editor) {
        boolean z;
        try {
            ImapHelper imapHelper = new ImapHelper(this.context, phoneAccountHandle, network, voicemailStatus$Editor);
            if (voicemail == null) {
                try {
                    z = syncAll(imapHelper, phoneAccountHandle);
                } catch (Throwable th) {
                    $closeResource(th, imapHelper);
                    throw th;
                }
            } else {
                if (shouldPerformPrefetch(phoneAccountHandle, imapHelper)) {
                    imapHelper.fetchVoicemailPayload(new VoicemailFetchedCallback(this.context, voicemail.getUri(), phoneAccountHandle), voicemail.getSourceData());
                }
                z = imapHelper.fetchTranscription(new TranscriptionFetchedCallback(this.context, voicemail), voicemail.getSourceData());
            }
            if (z) {
                imapHelper.updateQuota();
                autoDeleteAndArchiveVM(imapHelper, phoneAccountHandle);
                imapHelper.handleEvent(OmtpEvents.DATA_IMAP_OPERATION_COMPLETED);
                LoggerUtils.logImpressionOnMainThread(this.context, DialerImpression$Type.VVM_SYNC_COMPLETED);
            } else {
                baseTask.fail();
            }
            $closeResource((Throwable) null, imapHelper);
        } catch (ImapHelper.InitializingException e) {
            VvmLog.m47w("OmtpVvmSyncService", "Can't retrieve Imap credentials.", e);
        }
    }

    private boolean shouldPerformPrefetch(PhoneAccountHandle phoneAccountHandle, ImapHelper imapHelper) {
        return new OmtpVvmCarrierConfigHelper(this.context, phoneAccountHandle).isPrefetchEnabled() && !imapHelper.isRoaming();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003d A[LOOP:0: B:11:0x0037->B:13:0x003d, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean syncAll(com.android.voicemail.impl.imap.ImapHelper r12, android.telecom.PhoneAccountHandle r13) {
        /*
            r11 = this;
            java.util.List r0 = r12.fetchAllVoicemails()
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r1 = r11.queryHelper
            java.util.List r1 = r1.getAllVoicemails(r13)
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r2 = r11.queryHelper
            java.util.List r2 = r2.getDeletedVoicemails(r13)
            java.lang.String r3 = "OmtpVvmSyncService"
            r4 = 0
            if (r1 == 0) goto L_0x0115
            if (r0 != 0) goto L_0x0019
            goto L_0x0115
        L_0x0019:
            int r5 = r2.size()
            if (r5 <= 0) goto L_0x002d
            boolean r5 = r12.markMessagesAsDeleted(r2)
            if (r5 == 0) goto L_0x002b
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r5 = r11.queryHelper
            r5.deleteFromDatabase(r2)
            goto L_0x002d
        L_0x002b:
            r2 = r4
            goto L_0x002e
        L_0x002d:
            r2 = 1
        L_0x002e:
            android.util.ArrayMap r5 = new android.util.ArrayMap
            r5.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x0037:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L_0x004b
            java.lang.Object r6 = r0.next()
            com.android.voicemail.impl.Voicemail r6 = (com.android.voicemail.impl.Voicemail) r6
            java.lang.String r7 = r6.getSourceData()
            r5.put(r7, r6)
            goto L_0x0037
        L_0x004b:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r6 = r4
        L_0x0051:
            int r7 = r1.size()
            if (r6 >= r7) goto L_0x00b7
            java.lang.Object r7 = r1.get(r6)
            com.android.voicemail.impl.Voicemail r7 = (com.android.voicemail.impl.Voicemail) r7
            java.lang.String r8 = r7.getSourceData()
            java.lang.Object r8 = r5.remove(r8)
            com.android.voicemail.impl.Voicemail r8 = (com.android.voicemail.impl.Voicemail) r8
            if (r8 != 0) goto L_0x006f
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r8 = r11.queryHelper
            r8.deleteNonArchivedFromDatabase(r7)
            goto L_0x00b4
        L_0x006f:
            boolean r9 = r8.isRead()
            if (r9 == 0) goto L_0x0081
            boolean r9 = r7.isRead()
            if (r9 != 0) goto L_0x0081
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r9 = r11.queryHelper
            r9.markReadInDatabase(r7)
            goto L_0x0090
        L_0x0081:
            boolean r9 = r7.isRead()
            if (r9 == 0) goto L_0x0090
            boolean r9 = r8.isRead()
            if (r9 != 0) goto L_0x0090
            r0.add(r7)
        L_0x0090:
            java.lang.String r9 = r8.getTranscription()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L_0x00b4
            java.lang.String r9 = r7.getTranscription()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 == 0) goto L_0x00b4
            android.content.Context r9 = r11.context
            com.android.dialer.logging.DialerImpression$Type r10 = com.android.dialer.logging.DialerImpression$Type.VVM_TRANSCRIPTION_DOWNLOADED
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r9, r10)
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r9 = r11.queryHelper
            java.lang.String r8 = r8.getTranscription()
            r9.updateWithTranscription(r7, r8)
        L_0x00b4:
            int r6 = r6 + 1
            goto L_0x0051
        L_0x00b7:
            int r1 = r0.size()
            if (r1 <= 0) goto L_0x00d4
            java.lang.String r1 = "Marking voicemails as read"
            com.android.voicemail.impl.VvmLog.m45i(r3, r1)
            boolean r1 = r12.markMessagesAsRead(r0)
            if (r1 == 0) goto L_0x00d3
            java.lang.String r1 = "Marking voicemails as clean"
            com.android.voicemail.impl.VvmLog.m45i(r3, r1)
            com.android.voicemail.impl.sync.VoicemailsQueryHelper r1 = r11.queryHelper
            r1.markCleanInDatabase(r0)
            goto L_0x00d4
        L_0x00d3:
            return r4
        L_0x00d4:
            boolean r0 = r11.shouldPerformPrefetch(r13, r12)
            java.util.Collection r1 = r5.values()
            java.util.Iterator r1 = r1.iterator()
        L_0x00e0:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0114
            java.lang.Object r3 = r1.next()
            com.android.voicemail.impl.Voicemail r3 = (com.android.voicemail.impl.Voicemail) r3
            java.lang.String r4 = r3.getTranscription()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x00fd
            android.content.Context r4 = r11.context
            com.android.dialer.logging.DialerImpression$Type r5 = com.android.dialer.logging.DialerImpression$Type.VVM_TRANSCRIPTION_DOWNLOADED
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r4, r5)
        L_0x00fd:
            android.content.Context r4 = r11.context
            android.net.Uri r4 = com.android.voicemail.impl.utils.LoggerUtils.insert(r4, r3)
            if (r0 == 0) goto L_0x00e0
            com.android.voicemail.impl.fetch.VoicemailFetchedCallback r5 = new com.android.voicemail.impl.fetch.VoicemailFetchedCallback
            android.content.Context r6 = r11.context
            r5.<init>(r6, r4, r13)
            java.lang.String r3 = r3.getSourceData()
            r12.fetchVoicemailPayload(r5, r3)
            goto L_0x00e0
        L_0x0114:
            return r2
        L_0x0115:
            java.lang.String r11 = "syncAll: query failed"
            com.android.voicemail.impl.VvmLog.m43e(r3, r11)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.sync.OmtpVvmSyncService.syncAll(com.android.voicemail.impl.imap.ImapHelper, android.telecom.PhoneAccountHandle):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0073, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0074, code lost:
        if (r3 != null) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        $closeResource(r10, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0079, code lost:
        throw r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sync(com.android.voicemail.impl.scheduling.BaseTask r11, android.telecom.PhoneAccountHandle r12, com.android.voicemail.impl.Voicemail r13, com.android.voicemail.impl.VoicemailStatus$Editor r14) {
        /*
            r10 = this;
            if (r12 == 0) goto L_0x0004
            r0 = 1
            goto L_0x0005
        L_0x0004:
            r0 = 0
        L_0x0005:
            com.android.voicemail.impl.Assert.isTrue(r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Sync requested for account: "
            r0.append(r1)
            r0.append(r12)
            r0.toString()
            android.content.Context r0 = r10.context
            boolean r0 = com.android.voicemail.impl.settings.VisualVoicemailSettingsUtil.isEnabled(r0, r12)
            java.lang.String r1 = "OmtpVvmSyncService"
            if (r0 != 0) goto L_0x0028
            java.lang.String r10 = "Sync requested for disabled account"
            com.android.voicemail.impl.VvmLog.m43e(r1, r10)
            goto L_0x0082
        L_0x0028:
            android.content.Context r0 = r10.context
            boolean r0 = com.android.voicemail.impl.sync.VvmAccountManager.isAccountActivated(r0, r12)
            r2 = 0
            if (r0 != 0) goto L_0x0037
            android.content.Context r10 = r10.context
            com.android.voicemail.impl.ActivationTask.start(r10, r12, r2)
            goto L_0x0082
        L_0x0037:
            com.android.voicemail.impl.OmtpVvmCarrierConfigHelper r0 = new com.android.voicemail.impl.OmtpVvmCarrierConfigHelper
            android.content.Context r3 = r10.context
            r0.<init>(r3, r12)
            android.content.Context r3 = r10.context
            com.android.dialer.logging.DialerImpression$Type r4 = com.android.dialer.logging.DialerImpression$Type.VVM_SYNC_STARTED
            com.android.voicemail.impl.utils.LoggerUtils.logImpressionOnMainThread(r3, r4)
            android.content.Context r3 = r10.context
            com.android.voicemail.impl.VoicemailStatus$Editor r3 = com.android.voicemail.impl.Assert.edit(r3, r12)
            com.android.voicemail.impl.OmtpEvents r4 = com.android.voicemail.impl.OmtpEvents.DATA_IMAP_OPERATION_STARTED
            r0.handleEvent(r3, r4)
            com.android.voicemail.impl.sync.VvmNetworkRequest$NetworkWrapper r3 = com.android.voicemail.impl.sync.VvmNetworkRequest.getNetwork(r0, r12, r14)     // Catch:{ RequestFailedException -> 0x007a }
            if (r3 != 0) goto L_0x0064
            java.lang.String r10 = "unable to acquire network"
            com.android.voicemail.impl.VvmLog.m43e(r1, r10)     // Catch:{ all -> 0x0071 }
            r11.fail()     // Catch:{ all -> 0x0071 }
            if (r3 == 0) goto L_0x0082
        L_0x0060:
            $closeResource(r2, r3)     // Catch:{ RequestFailedException -> 0x007a }
            goto L_0x0082
        L_0x0064:
            android.net.Network r6 = r3.get()     // Catch:{ all -> 0x0071 }
            r4 = r10
            r5 = r11
            r7 = r12
            r8 = r13
            r9 = r14
            r4.doSync(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0071 }
            goto L_0x0060
        L_0x0071:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x0073 }
        L_0x0073:
            r12 = move-exception
            if (r3 == 0) goto L_0x0079
            $closeResource(r10, r3)     // Catch:{ RequestFailedException -> 0x007a }
        L_0x0079:
            throw r12     // Catch:{ RequestFailedException -> 0x007a }
        L_0x007a:
            com.android.voicemail.impl.OmtpEvents r10 = com.android.voicemail.impl.OmtpEvents.DATA_NO_CONNECTION_CELLULAR_REQUIRED
            r0.handleEvent(r14, r10)
            r11.fail()
        L_0x0082:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.sync.OmtpVvmSyncService.sync(com.android.voicemail.impl.scheduling.BaseTask, android.telecom.PhoneAccountHandle, com.android.voicemail.impl.Voicemail, com.android.voicemail.impl.VoicemailStatus$Editor):void");
    }
}
