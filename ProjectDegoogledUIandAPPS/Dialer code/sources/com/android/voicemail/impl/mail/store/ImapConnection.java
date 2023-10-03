package com.android.voicemail.impl.mail.store;

import android.util.ArraySet;
import android.util.Base64;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.OmtpEvents;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.mail.CertificateValidationException;
import com.android.voicemail.impl.mail.MailTransport;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.store.ImapStore;
import com.android.voicemail.impl.mail.store.imap.DigestMd5Utils;
import com.android.voicemail.impl.mail.store.imap.ImapResponse;
import com.android.voicemail.impl.mail.store.imap.ImapResponseParser;
import com.android.voicemail.impl.mail.store.imap.ImapString;
import com.android.voicemail.impl.mail.utils.LogUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLException;

public class ImapConnection {
    private Set<String> capabilities = new ArraySet();
    private ImapStore imapStore;
    private String loginPhrase;
    private final AtomicInteger nextCommandTag = new AtomicInteger(0);
    private ImapResponseParser parser;
    private MailTransport transport;

    ImapConnection(ImapStore imapStore2) {
        this.imapStore = imapStore2;
        this.loginPhrase = null;
    }

    private void createParser() {
        destroyResponses();
        this.parser = new ImapResponseParser(this.transport.getInputStream());
    }

    private void doDigestMd5Auth() throws IOException, MessagingException {
        sendCommand("AUTHENTICATE DIGEST-MD5", false);
        DigestMd5Utils.Data data = new DigestMd5Utils.Data(this.imapStore, this.transport, DigestMd5Utils.parseDigestMessage(new String(Base64.decode(getCommandResponses().get(0).getStringOrEmpty(0).getString(), 0))));
        this.transport.writeLine(Base64.encodeToString(data.createResponse().getBytes(), 2), "[IMAP command redacted]");
        data.verifyResponseAuth(new String(Base64.decode(getCommandResponses().get(0).getStringOrEmpty(0).getString(), 0)));
        this.transport.writeLine("", "");
        getCommandResponses();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0079, code lost:
        if (r4.equals("unknown client") != false) goto L_0x0091;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doLogin() throws java.io.IOException, com.android.voicemail.impl.mail.MessagingException, com.android.voicemail.impl.mail.AuthenticationFailedException {
        /*
            r7 = this;
            r0 = 1
            java.util.Set<java.lang.String> r1 = r7.capabilities     // Catch:{ ImapException -> 0x0017 }
            java.lang.String r2 = "AUTH=DIGEST-MD5"
            boolean r1 = r1.contains(r2)     // Catch:{ ImapException -> 0x0017 }
            if (r1 == 0) goto L_0x000f
            r7.doDigestMd5Auth()     // Catch:{ ImapException -> 0x0017 }
            goto L_0x0016
        L_0x000f:
            java.lang.String r1 = r7.getLoginPhrase()     // Catch:{ ImapException -> 0x0017 }
            r7.executeSimpleCommand(r1, r0)     // Catch:{ ImapException -> 0x0017 }
        L_0x0016:
            return
        L_0x0017:
            r1 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r0]
            r3 = 0
            r2[r3] = r1
            java.lang.String r4 = "ImapConnection"
            java.lang.String r5 = "ImapException"
            com.android.voicemail.impl.mail.utils.LogUtils.m51d(r4, r5, r2)
            java.lang.String r2 = r1.getStatus()
            java.lang.String r4 = r1.getStatusMessage()
            java.lang.String r5 = r1.getAlertText()
            java.lang.String r6 = "NO"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0105
            r2 = -1
            int r6 = r4.hashCode()
            switch(r6) {
                case -1793151176: goto L_0x0086;
                case -1377415711: goto L_0x007c;
                case -1368250271: goto L_0x0073;
                case -1012273476: goto L_0x0069;
                case 730001060: goto L_0x005f;
                case 1236710811: goto L_0x0055;
                case 1523643993: goto L_0x004b;
                case 1929932907: goto L_0x0041;
                default: goto L_0x0040;
            }
        L_0x0040:
            goto L_0x0090
        L_0x0041:
            java.lang.String r0 = "user is blocked"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = 6
            goto L_0x0091
        L_0x004b:
            java.lang.String r0 = "service is not activated"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = 5
            goto L_0x0091
        L_0x0055:
            java.lang.String r0 = "mailbox not initialized"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = 3
            goto L_0x0091
        L_0x005f:
            java.lang.String r0 = "invalid password"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = 2
            goto L_0x0091
        L_0x0069:
            java.lang.String r0 = "service is not provisioned"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = 4
            goto L_0x0091
        L_0x0073:
            java.lang.String r3 = "unknown client"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0090
            goto L_0x0091
        L_0x007c:
            java.lang.String r0 = "unknown user"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = r3
            goto L_0x0091
        L_0x0086:
            java.lang.String r0 = "application error"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0090
            r0 = 7
            goto L_0x0091
        L_0x0090:
            r0 = r2
        L_0x0091:
            switch(r0) {
                case 0: goto L_0x00f4;
                case 1: goto L_0x00e8;
                case 2: goto L_0x00dc;
                case 3: goto L_0x00d0;
                case 4: goto L_0x00c4;
                case 5: goto L_0x00b8;
                case 6: goto L_0x00ac;
                case 7: goto L_0x00a0;
                default: goto L_0x0094;
            }
        L_0x0094:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_BAD_IMAP_CREDENTIAL
            r7.handleEvent(r0)
            goto L_0x00ff
        L_0x00a0:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_REJECTED_SERVER_RESPONSE
            r7.handleEvent(r0)
            goto L_0x00ff
        L_0x00ac:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_AUTH_USER_IS_BLOCKED
            r7.handleEvent(r0)
            goto L_0x00ff
        L_0x00b8:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_AUTH_SERVICE_NOT_ACTIVATED
            r7.handleEvent(r0)
            goto L_0x00ff
        L_0x00c4:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_AUTH_SERVICE_NOT_PROVISIONED
            r7.handleEvent(r0)
            goto L_0x00ff
        L_0x00d0:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_AUTH_MAILBOX_NOT_INITIALIZED
            r7.handleEvent(r0)
            goto L_0x00ff
        L_0x00dc:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_AUTH_INVALID_PASSWORD
            r7.handleEvent(r0)
            goto L_0x00ff
        L_0x00e8:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_AUTH_UNKNOWN_DEVICE
            r7.handleEvent(r0)
            goto L_0x00ff
        L_0x00f4:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_AUTH_UNKNOWN_USER
            r7.handleEvent(r0)
        L_0x00ff:
            com.android.voicemail.impl.mail.AuthenticationFailedException r7 = new com.android.voicemail.impl.mail.AuthenticationFailedException
            r7.<init>(r5, r1)
            throw r7
        L_0x0105:
            com.android.voicemail.impl.mail.store.ImapStore r7 = r7.imapStore
            com.android.voicemail.impl.imap.ImapHelper r7 = r7.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r0 = com.android.voicemail.impl.OmtpEvents.DATA_REJECTED_SERVER_RESPONSE
            r7.handleEvent(r0)
            com.android.voicemail.impl.mail.MessagingException r7 = new com.android.voicemail.impl.mail.MessagingException
            r7.<init>(r3, r5, r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.mail.store.ImapConnection.doLogin():void");
    }

    private void maybeDoStartTls() throws IOException, MessagingException {
        if (this.capabilities.contains("STARTTLS")) {
            sendCommand("STARTTLS", false);
            getCommandResponses();
            this.transport.reopenTls();
            destroyResponses();
            this.parser = new ImapResponseParser(this.transport.getInputStream());
            queryCapability();
        }
    }

    private void queryCapability() throws IOException, MessagingException {
        sendCommand("CAPABILITY", false);
        List<ImapResponse> commandResponses = getCommandResponses();
        this.capabilities.clear();
        Set<String> disabledCapabilities = this.imapStore.getImapHelper().getConfig().getDisabledCapabilities();
        for (ImapResponse next : commandResponses) {
            if (!next.isTagged()) {
                for (int i = 0; i < next.size(); i++) {
                    String string = next.getStringOrEmpty(i).getString();
                    if (disabledCapabilities == null) {
                        this.capabilities.add(string);
                    } else if (!disabledCapabilities.contains(string)) {
                        this.capabilities.add(string);
                    }
                }
            }
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Capabilities: ");
        outline13.append(this.capabilities.toString());
        LogUtils.m51d("ImapConnection", outline13.toString(), new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public void close() {
        if (this.transport != null) {
            try {
                sendCommand("LOGOUT", false);
                if (!this.parser.readResponse(true).mo9247is(0, "BYE")) {
                    VvmLog.m43e("ImapConnection", "Server did not respond LOGOUT with BYE");
                }
                if (!this.parser.readResponse(false).isOk()) {
                    VvmLog.m43e("ImapConnection", "Server did not respond OK after LOGOUT");
                }
            } catch (MessagingException | IOException e) {
                VvmLog.m43e("ImapConnection", "Error while logging out:" + e);
            }
            this.transport.close();
            this.transport = null;
        }
        destroyResponses();
        this.parser = null;
        this.imapStore = null;
    }

    public void destroyResponses() {
        ImapResponseParser imapResponseParser = this.parser;
        if (imapResponseParser != null) {
            imapResponseParser.destroyResponses();
        }
    }

    public List<ImapResponse> executeSimpleCommand(String str) throws IOException, MessagingException {
        sendCommand(str, false);
        return getCommandResponses();
    }

    /* access modifiers changed from: package-private */
    public List<ImapResponse> getCommandResponses() throws IOException, MessagingException {
        ImapResponse readResponse;
        ImapString imapString;
        ImapString imapString2;
        ArrayList arrayList = new ArrayList();
        do {
            readResponse = this.parser.readResponse(false);
            arrayList.add(readResponse);
            if (readResponse.isTagged() || readResponse.isContinuationRequest()) {
            }
            readResponse = this.parser.readResponse(false);
            arrayList.add(readResponse);
            break;
        } while (readResponse.isContinuationRequest());
        if (readResponse.isOk() || readResponse.isContinuationRequest()) {
            return arrayList;
        }
        String imapResponse = readResponse.toString();
        if (!readResponse.isStatusResponse()) {
            imapString = ImapString.EMPTY;
        } else {
            imapString = readResponse.getStringOrEmpty(0);
        }
        String string = imapString.getString();
        String string2 = readResponse.getStatusResponseTextOrEmpty().getString();
        if (!readResponse.getResponseCodeOrEmpty().mo9269is("ALERT")) {
            imapString2 = ImapString.EMPTY;
        } else {
            imapString2 = readResponse.getStringOrEmpty(2);
        }
        String string3 = imapString2.getString();
        String string4 = readResponse.getResponseCodeOrEmpty().getString();
        destroyResponses();
        throw new ImapStore.ImapException(imapResponse, string, string2, string3, string4);
    }

    /* access modifiers changed from: package-private */
    public String getLoginPhrase() {
        if (!(this.loginPhrase != null || this.imapStore.getUsername() == null || this.imapStore.getPassword() == null)) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("LOGIN ");
            outline13.append(this.imapStore.getUsername());
            outline13.append(" ");
            String replaceAll = this.imapStore.getPassword().replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"");
            outline13.append("\"" + replaceAll + "\"");
            this.loginPhrase = outline13.toString();
        }
        return this.loginPhrase;
    }

    public ImapResponse readResponse() throws IOException, MessagingException {
        return this.parser.readResponse(false);
    }

    public String sendCommand(String str, boolean z) throws IOException, MessagingException {
        MailTransport mailTransport = this.transport;
        if (mailTransport == null || !mailTransport.isOpen()) {
            try {
                if (this.transport == null) {
                    this.transport = this.imapStore.cloneTransport();
                }
                this.transport.open();
                createParser();
                if (this.parser.readResponse(false).isOk()) {
                    queryCapability();
                    maybeDoStartTls();
                    doLogin();
                    destroyResponses();
                } else {
                    this.imapStore.getImapHelper().handleEvent(OmtpEvents.DATA_INVALID_INITIAL_SERVER_RESPONSE);
                    throw new MessagingException(13, "Invalid server initial response");
                }
            } catch (SSLException e) {
                LogUtils.m51d("ImapConnection", "SSLException ", e);
                this.imapStore.getImapHelper().handleEvent(OmtpEvents.DATA_SSL_EXCEPTION);
                throw new CertificateValidationException(e.getMessage(), e);
            } catch (IOException e2) {
                LogUtils.m51d("ImapConnection", "IOException", e2);
                this.imapStore.getImapHelper().handleEvent(OmtpEvents.DATA_IOE_ON_OPEN);
                throw e2;
            } catch (Throwable th) {
                destroyResponses();
                throw th;
            }
        }
        if (this.transport != null) {
            String num = Integer.toString(this.nextCommandTag.incrementAndGet());
            String outline9 = GeneratedOutlineSupport.outline9(num, " ", str);
            MailTransport mailTransport2 = this.transport;
            if (z) {
                str = "[IMAP command redacted]";
            }
            mailTransport2.writeLine(outline9, str);
            return num;
        }
        throw new IOException("Null transport");
    }

    public List<ImapResponse> executeSimpleCommand(String str, boolean z) throws IOException, MessagingException {
        sendCommand(str, z);
        return getCommandResponses();
    }
}
