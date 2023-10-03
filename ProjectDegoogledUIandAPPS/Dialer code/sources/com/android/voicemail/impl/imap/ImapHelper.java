package com.android.voicemail.impl.imap;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.telecom.PhoneAccountHandle;
import android.util.Base64;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.Assert;
import com.android.voicemail.impl.OmtpEvents;
import com.android.voicemail.impl.OmtpVvmCarrierConfigHelper;
import com.android.voicemail.impl.VisualVoicemailPreferences;
import com.android.voicemail.impl.Voicemail;
import com.android.voicemail.impl.VoicemailStatus$Editor;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.fetch.VoicemailFetchedCallback;
import com.android.voicemail.impl.mail.Address;
import com.android.voicemail.impl.mail.Body;
import com.android.voicemail.impl.mail.BodyPart;
import com.android.voicemail.impl.mail.FetchProfile;
import com.android.voicemail.impl.mail.Message;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.Multipart;
import com.android.voicemail.impl.mail.TempDirectory;
import com.android.voicemail.impl.mail.internet.MimeBodyPart;
import com.android.voicemail.impl.mail.internet.MimeMessage;
import com.android.voicemail.impl.mail.internet.MimeUtility;
import com.android.voicemail.impl.mail.store.ImapConnection;
import com.android.voicemail.impl.mail.store.ImapFolder;
import com.android.voicemail.impl.mail.store.ImapStore;
import com.android.voicemail.impl.mail.store.imap.ImapResponse;
import com.android.voicemail.impl.mail.utils.LogUtils;
import com.android.voicemail.impl.sync.OmtpVvmSyncService;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.apache.commons.p011io.IOUtils;

public class ImapHelper implements Closeable {
    /* access modifiers changed from: private */
    public final OmtpVvmCarrierConfigHelper config;
    private final Context context;
    private ImapFolder folder;
    private ImapStore imapStore;
    private final Network network;
    private final PhoneAccountHandle phoneAccount;
    VisualVoicemailPreferences prefs;
    private final VoicemailStatus$Editor status;

    public static class InitializingException extends Exception {
        public InitializingException(String str) {
            super(str);
        }
    }

    private final class MessageBodyFetchedListener implements ImapFolder.MessageRetrievalListener {
        private VoicemailPayload voicemailPayload;

        /* synthetic */ MessageBodyFetchedListener(C07701 r2) {
        }

        private VoicemailPayload getVoicemailPayloadFromMessage(Message message) throws MessagingException, IOException {
            Multipart multipart = (Multipart) message.getBody();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String lowerCase = MimeUtility.getHeaderParameter(((MimeBodyPart) bodyPart).getContentType(), (String) null).toLowerCase();
                arrayList.add(lowerCase);
                if (lowerCase.startsWith("audio/")) {
                    byte[] access$400 = ImapHelper.this.getDataFromBody(((MimeBodyPart) bodyPart).getBody());
                    LogUtils.m51d("ImapHelper", String.format("Fetched %s bytes of data", new Object[]{Integer.valueOf(access$400.length)}), new Object[0]);
                    return new VoicemailPayload(lowerCase, access$400);
                }
            }
            LogUtils.m52e("ImapHelper", GeneratedOutlineSupport.outline6("No audio attachment found on this voicemail, mimeTypes:", arrayList), new Object[0]);
            return null;
        }

        public VoicemailPayload getVoicemailPayload() {
            return this.voicemailPayload;
        }

        public void messageRetrieved(Message message) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Fetched message body for ");
            outline13.append(message.getUid());
            LogUtils.m51d("ImapHelper", outline13.toString(), new Object[0]);
            LogUtils.m51d("ImapHelper", "Message retrieved: " + message, new Object[0]);
            try {
                this.voicemailPayload = getVoicemailPayloadFromMessage(message);
            } catch (MessagingException e) {
                LogUtils.m52e("ImapHelper", "Messaging Exception:", e);
            } catch (IOException e2) {
                LogUtils.m52e("ImapHelper", "IO Exception:", e2);
            }
        }
    }

    private final class MessageStructureFetchedListener implements ImapFolder.MessageRetrievalListener {
        private MessageStructureWrapper messageStructure;

        public MessageStructureFetchedListener() {
        }

        private MessageStructureWrapper getMessageOrNull(Message message) throws MessagingException {
            if (!message.getMimeType().startsWith("multipart/")) {
                LogUtils.m56w("ImapHelper", "Ignored non multi-part message", new Object[0]);
                return null;
            }
            MessageStructureWrapper messageStructureWrapper = new MessageStructureWrapper();
            Multipart multipart = (Multipart) message.getBody();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String lowerCase = MimeUtility.getHeaderParameter(((MimeBodyPart) bodyPart).getContentType(), (String) null).toLowerCase();
                LogUtils.m51d("ImapHelper", GeneratedOutlineSupport.outline8("bodyPart mime type: ", lowerCase), new Object[0]);
                if (lowerCase.startsWith("audio/")) {
                    messageStructureWrapper.messageStructure = message;
                } else if (ImapHelper.this.config.ignoreTranscription() || !lowerCase.startsWith("text/")) {
                    "Unknown bodyPart MIME: " + lowerCase;
                } else {
                    messageStructureWrapper.transcriptionBodyPart = bodyPart;
                }
            }
            if (messageStructureWrapper.messageStructure != null) {
                return messageStructureWrapper;
            }
            return null;
        }

        public MessageStructureWrapper getMessageStructure() {
            return this.messageStructure;
        }

        public void messageRetrieved(Message message) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Fetched message structure for ");
            outline13.append(message.getUid());
            LogUtils.m51d("ImapHelper", outline13.toString(), new Object[0]);
            LogUtils.m51d("ImapHelper", "Message retrieved: " + message, new Object[0]);
            try {
                this.messageStructure = getMessageOrNull(message);
                if (this.messageStructure == null) {
                    LogUtils.m51d("ImapHelper", "This voicemail does not have an attachment...", new Object[0]);
                }
            } catch (MessagingException e) {
                LogUtils.m53e("ImapHelper", e, "Messaging Exception", new Object[0]);
                ImapHelper.this.closeImapFolder();
            }
        }
    }

    public static class MessageStructureWrapper {
        public Message messageStructure;
        public BodyPart transcriptionBodyPart;
    }

    private final class TranscriptionFetchedListener implements ImapFolder.MessageRetrievalListener {
        private String voicemailTranscription;

        /* synthetic */ TranscriptionFetchedListener(C07701 r2) {
        }

        public String getVoicemailTranscription() {
            return this.voicemailTranscription;
        }

        public void messageRetrieved(Message message) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Fetched transcription for ");
            outline13.append(message.getUid());
            LogUtils.m51d("ImapHelper", outline13.toString(), new Object[0]);
            try {
                this.voicemailTranscription = new String(ImapHelper.this.getDataFromBody(message.getBody()));
            } catch (MessagingException e) {
                LogUtils.m52e("ImapHelper", "Messaging Exception:", e);
            } catch (IOException e2) {
                LogUtils.m52e("ImapHelper", "IO Exception:", e2);
            }
        }
    }

    public ImapHelper(Context context2, PhoneAccountHandle phoneAccountHandle, Network network2, VoicemailStatus$Editor voicemailStatus$Editor) throws InitializingException {
        int i;
        int i2;
        OmtpVvmCarrierConfigHelper omtpVvmCarrierConfigHelper = new OmtpVvmCarrierConfigHelper(context2, phoneAccountHandle);
        this.context = context2;
        this.phoneAccount = phoneAccountHandle;
        this.network = network2;
        this.status = voicemailStatus$Editor;
        this.config = omtpVvmCarrierConfigHelper;
        this.prefs = new VisualVoicemailPreferences(context2, phoneAccountHandle);
        try {
            TempDirectory.setTempDirectory(context2);
            String string = this.prefs.getString("u", (String) null);
            String string2 = this.prefs.getString("pw", (String) null);
            String string3 = this.prefs.getString("srv", (String) null);
            int parseInt = Integer.parseInt(this.prefs.getString("ipt", (String) null));
            int sslPort = this.config.getSslPort();
            if (sslPort != 0) {
                i = 1;
                i2 = sslPort;
            } else {
                i = 0;
                i2 = parseInt;
            }
            this.imapStore = new ImapStore(context2, this, string, string2, i2, string3, i, network2);
        } catch (NumberFormatException e) {
            handleEvent(OmtpEvents.DATA_INVALID_PORT);
            LogUtils.m56w("ImapHelper", "Could not parse port number", new Object[0]);
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("cannot initialize ImapHelper:");
            outline13.append(e.toString());
            throw new InitializingException(outline13.toString());
        }
    }

    /* access modifiers changed from: private */
    public void closeImapFolder() {
        ImapFolder imapFolder = this.folder;
        if (imapFolder != null) {
            imapFolder.close(true);
        }
    }

    private Message[] convertToImapMessages(List<Voicemail> list) {
        Message[] messageArr = new Message[list.size()];
        for (int i = 0; i < list.size(); i++) {
            messageArr[i] = new MimeMessage();
            messageArr[i].setUid(list.get(i).getSourceData());
        }
        return messageArr;
    }

    private MessageStructureWrapper fetchMessageStructure(Message message) throws MessagingException {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Fetching message structure for ");
        outline13.append(message.getUid());
        LogUtils.m51d("ImapHelper", outline13.toString(), new Object[0]);
        MessageStructureFetchedListener messageStructureFetchedListener = new MessageStructureFetchedListener();
        FetchProfile fetchProfile = new FetchProfile();
        fetchProfile.addAll(Arrays.asList(new FetchProfile.Item[]{FetchProfile.Item.FLAGS, FetchProfile.Item.ENVELOPE, FetchProfile.Item.STRUCTURE}));
        this.folder.fetch(new Message[]{message}, fetchProfile, messageStructureFetchedListener);
        return messageStructureFetchedListener.getMessageStructure();
    }

    private static int getChangePinResultFromImapResponse(ImapResponse imapResponse) throws MessagingException {
        if (!imapResponse.isTagged()) {
            throw new MessagingException(19, "tagged response expected");
        } else if (!imapResponse.isOk()) {
            String string = imapResponse.getStringOrEmpty(1).getString();
            LogUtils.m51d("ImapHelper", GeneratedOutlineSupport.outline8("change PIN failed: ", string), new Object[0]);
            if ("password too short".equals(string)) {
                return 1;
            }
            if ("password too long".equals(string)) {
                return 2;
            }
            if ("password too weak".equals(string)) {
                return 3;
            }
            if ("old password mismatch".equals(string)) {
                return 4;
            }
            return "password contains invalid characters".equals(string) ? 5 : 6;
        } else {
            LogUtils.m51d("ImapHelper", "change PIN succeeded", new Object[0]);
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public byte[] getDataFromBody(Body body) throws IOException, MessagingException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        try {
            body.writeTo(bufferedOutputStream);
            return Base64.decode(byteArrayOutputStream.toByteArray(), 0);
        } finally {
            IOUtils.closeQuietly(bufferedOutputStream);
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    private Voicemail getVoicemailFromMessageStructure(MessageStructureWrapper messageStructureWrapper) throws MessagingException {
        Message message = messageStructureWrapper.messageStructure;
        String str = null;
        TranscriptionFetchedListener transcriptionFetchedListener = new TranscriptionFetchedListener((C07701) null);
        if (messageStructureWrapper.transcriptionBodyPart != null) {
            FetchProfile fetchProfile = new FetchProfile();
            fetchProfile.add(messageStructureWrapper.transcriptionBodyPart);
            this.folder.fetch(new Message[]{message}, fetchProfile, transcriptionFetchedListener);
        }
        long time = message.getSentDate().getTime();
        Address[] from = message.getFrom();
        if (from != null && from.length > 0) {
            if (from.length != 1) {
                LogUtils.m56w("ImapHelper", "More than one from addresses found. Using the first one.", new Object[0]);
            }
            String address = from[0].getAddress();
            int indexOf = address.indexOf(64);
            if (indexOf != -1) {
                address = address.substring(0, indexOf);
            }
            str = address;
        }
        boolean contains = Arrays.asList(message.getFlags()).contains("seen");
        Long duration = message.getDuration();
        Voicemail.Builder createForInsertion = Voicemail.createForInsertion(time, str);
        createForInsertion.setPhoneAccount(this.phoneAccount);
        createForInsertion.setSourcePackage(this.context.getPackageName());
        createForInsertion.setSourceData(message.getUid());
        createForInsertion.setIsRead(contains);
        createForInsertion.setTranscription(transcriptionFetchedListener.getVoicemailTranscription());
        if (duration != null) {
            createForInsertion.setDuration(duration.longValue());
        }
        return createForInsertion.build();
    }

    private ImapFolder openImapFolder(String str) {
        try {
            if (this.imapStore == null) {
                return null;
            }
            ImapFolder imapFolder = new ImapFolder(this.imapStore, "INBOX");
            imapFolder.open(str);
            return imapFolder;
        } catch (MessagingException e) {
            LogUtils.m53e("ImapHelper", e, "Messaging Exception", new Object[0]);
            return null;
        }
    }

    private boolean setFlags(List<Voicemail> list, String... strArr) {
        if (list.size() == 0) {
            return false;
        }
        try {
            this.folder = openImapFolder("mode_read_write");
            if (this.folder != null) {
                this.folder.setFlags(convertToImapMessages(list), strArr, true);
                return true;
            }
            closeImapFolder();
            return false;
        } catch (MessagingException e) {
            LogUtils.m53e("ImapHelper", e, "Messaging exception", new Object[0]);
            return false;
        } finally {
            closeImapFolder();
        }
    }

    public int changePin(String str, String str2) throws MessagingException {
        ImapConnection connection = this.imapStore.getConnection();
        try {
            String command = this.config.getProtocol().getCommand("XCHANGE_TUI_PWD PWD=%1$s OLD_PWD=%2$s");
            connection.sendCommand(String.format(Locale.US, command, new Object[]{str2, str}), true);
            return getChangePinResultFromImapResponse(connection.readResponse());
        } catch (IOException e) {
            VvmLog.m44e("ImapHelper", "changePin: ", e);
            return 6;
        } finally {
            connection.destroyResponses();
        }
    }

    public void changeVoicemailTuiLanguage(String str) throws MessagingException {
        ImapConnection connection = this.imapStore.getConnection();
        try {
            String command = this.config.getProtocol().getCommand("XCHANGE_VM_LANG LANG=%1$s");
            connection.sendCommand(String.format(Locale.US, command, new Object[]{str}), true);
        } catch (IOException e) {
            LogUtils.m52e("ImapHelper", e.toString(), new Object[0]);
        } catch (Throwable th) {
            connection.destroyResponses();
            throw th;
        }
        connection.destroyResponses();
    }

    public void close() {
        this.imapStore.closeConnection();
    }

    public void closeNewUserTutorial() throws MessagingException {
        ImapConnection connection = this.imapStore.getConnection();
        try {
            connection.executeSimpleCommand(this.config.getProtocol().getCommand("XCLOSE_NUT"), false);
            connection.destroyResponses();
        } catch (IOException e) {
            throw new MessagingException(19, e.toString());
        } catch (Throwable th) {
            connection.destroyResponses();
            throw th;
        }
    }

    public List<Voicemail> fetchAllVoicemails() {
        ArrayList arrayList = new ArrayList();
        try {
            this.folder = openImapFolder("mode_read_write");
            if (this.folder == null) {
                return null;
            }
            for (Message fetchMessageStructure : this.folder.getMessages((String[]) null)) {
                MessageStructureWrapper fetchMessageStructure2 = fetchMessageStructure(fetchMessageStructure);
                if (fetchMessageStructure2 != null) {
                    arrayList.add(getVoicemailFromMessageStructure(fetchMessageStructure2));
                }
            }
            closeImapFolder();
            return arrayList;
        } catch (MessagingException e) {
            LogUtils.m53e("ImapHelper", e, "Messaging Exception", new Object[0]);
            return null;
        } finally {
            closeImapFolder();
        }
    }

    public boolean fetchTranscription(OmtpVvmSyncService.TranscriptionFetchedCallback transcriptionFetchedCallback, String str) {
        try {
            this.folder = openImapFolder("mode_read_write");
            if (this.folder == null) {
                return false;
            }
            Message message = this.folder.getMessage(str);
            if (message == null) {
                closeImapFolder();
                return false;
            }
            MessageStructureWrapper fetchMessageStructure = fetchMessageStructure(message);
            if (fetchMessageStructure != null) {
                TranscriptionFetchedListener transcriptionFetchedListener = new TranscriptionFetchedListener((C07701) null);
                if (fetchMessageStructure.transcriptionBodyPart != null) {
                    FetchProfile fetchProfile = new FetchProfile();
                    fetchProfile.add(fetchMessageStructure.transcriptionBodyPart);
                    this.folder.fetch(new Message[]{message}, fetchProfile, transcriptionFetchedListener);
                    transcriptionFetchedCallback.setVoicemailTranscription(transcriptionFetchedListener.getVoicemailTranscription());
                }
            }
            closeImapFolder();
            return true;
        } catch (MessagingException e) {
            LogUtils.m53e("ImapHelper", e, "Messaging Exception", new Object[0]);
            return false;
        } finally {
            closeImapFolder();
        }
    }

    public boolean fetchVoicemailPayload(VoicemailFetchedCallback voicemailFetchedCallback, String str) {
        try {
            this.folder = openImapFolder("mode_read_write");
            if (this.folder == null) {
                return false;
            }
            Message message = this.folder.getMessage(str);
            if (message == null) {
                closeImapFolder();
                return false;
            }
            voicemailFetchedCallback.setVoicemailContent(fetchVoicemailPayload(message));
            closeImapFolder();
            return true;
        } catch (MessagingException unused) {
            return false;
        } finally {
            closeImapFolder();
        }
    }

    public OmtpVvmCarrierConfigHelper getConfig() {
        return this.config;
    }

    public ImapFolder.Quota getQuota() {
        try {
            this.folder = openImapFolder("mode_read_only");
            if (this.folder == null) {
                LogUtils.m52e("ImapHelper", "Unable to open folder", new Object[0]);
                return null;
            }
            ImapFolder.Quota quota = this.folder.getQuota();
            closeImapFolder();
            return quota;
        } catch (MessagingException e) {
            LogUtils.m53e("ImapHelper", e, "Messaging Exception", new Object[0]);
            return null;
        } finally {
            closeImapFolder();
        }
    }

    public void handleEvent(OmtpEvents omtpEvents) {
        this.config.handleEvent(this.status, omtpEvents);
    }

    public boolean isRoaming() {
        NetworkInfo networkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getNetworkInfo(this.network);
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isRoaming();
    }

    public boolean markMessagesAsDeleted(List<Voicemail> list) {
        return setFlags(list, "deleted");
    }

    public boolean markMessagesAsRead(List<Voicemail> list) {
        return setFlags(list, "seen");
    }

    public void updateQuota() {
        try {
            this.folder = openImapFolder("mode_read_write");
            if (this.folder == null) {
                closeImapFolder();
                return;
            }
            updateQuota(this.folder);
            closeImapFolder();
        } catch (MessagingException e) {
            LogUtils.m53e("ImapHelper", e, "Messaging Exception", new Object[0]);
        } catch (Throwable th) {
            closeImapFolder();
            throw th;
        }
    }

    private void updateQuota(ImapFolder imapFolder) throws MessagingException {
        ImapFolder.Quota quota = imapFolder.getQuota();
        if (quota == null) {
            LogUtils.m54i("ImapHelper", "quota was null", new Object[0]);
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Updating Voicemail status table with quota occupied: ");
        outline13.append(quota.occupied);
        outline13.append(" new quota total:");
        outline13.append(quota.total);
        LogUtils.m54i("ImapHelper", outline13.toString(), new Object[0]);
        VoicemailStatus$Editor edit = Assert.edit(this.context, this.phoneAccount);
        edit.setQuota(quota.occupied, quota.total);
        edit.apply();
        LogUtils.m54i("ImapHelper", "Updated quota occupied and total", new Object[0]);
    }

    private VoicemailPayload fetchVoicemailPayload(Message message) throws MessagingException {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Fetching message body for ");
        outline13.append(message.getUid());
        LogUtils.m51d("ImapHelper", outline13.toString(), new Object[0]);
        MessageBodyFetchedListener messageBodyFetchedListener = new MessageBodyFetchedListener((C07701) null);
        FetchProfile fetchProfile = new FetchProfile();
        fetchProfile.add(FetchProfile.Item.BODY);
        this.folder.fetch(new Message[]{message}, fetchProfile, messageBodyFetchedListener);
        return messageBodyFetchedListener.getVoicemailPayload();
    }
}
