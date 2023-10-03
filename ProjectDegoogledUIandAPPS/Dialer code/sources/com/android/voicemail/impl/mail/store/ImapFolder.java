package com.android.voicemail.impl.mail.store;

import android.text.TextUtils;
import android.util.Base64DataException;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.OmtpEvents;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.mail.AuthenticationFailedException;
import com.android.voicemail.impl.mail.Body;
import com.android.voicemail.impl.mail.FetchProfile;
import com.android.voicemail.impl.mail.Message;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.Part;
import com.android.voicemail.impl.mail.internet.BinaryTempFileBody;
import com.android.voicemail.impl.mail.internet.MimeBodyPart;
import com.android.voicemail.impl.mail.internet.MimeMultipart;
import com.android.voicemail.impl.mail.internet.MimeUtility;
import com.android.voicemail.impl.mail.store.ImapStore;
import com.android.voicemail.impl.mail.store.imap.ImapElement;
import com.android.voicemail.impl.mail.store.imap.ImapList;
import com.android.voicemail.impl.mail.store.imap.ImapResponse;
import com.android.voicemail.impl.mail.store.imap.ImapString;
import com.android.voicemail.impl.mail.utils.Utility;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ImapFolder {
    private ImapConnection connection;
    private boolean exists;
    private final String name;
    private final ImapStore store;

    public interface MessageRetrievalListener {
        void messageRetrieved(Message message);
    }

    public class Quota {
        public final int occupied;
        public final int total;

        public Quota(ImapFolder imapFolder, int i, int i2) {
            this.occupied = i;
            this.total = i2;
        }
    }

    static {
        new String[]{"deleted", "seen", "flagged", "answered"};
    }

    public ImapFolder(ImapStore imapStore, String str) {
        this.store = imapStore;
        this.name = str;
    }

    private void checkOpen() throws MessagingException {
        if (!isOpen()) {
            throw new MessagingException(GeneratedOutlineSupport.outline12(GeneratedOutlineSupport.outline13("Folder "), this.name, " is not open."));
        }
    }

    private static Body decodeBody(InputStream inputStream, String str) throws IOException {
        InputStream inputStreamForContentTransferEncoding = MimeUtility.getInputStreamForContentTransferEncoding(inputStream, str);
        BinaryTempFileBody binaryTempFileBody = new BinaryTempFileBody();
        OutputStream outputStream = binaryTempFileBody.getOutputStream();
        try {
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStreamForContentTransferEncoding.read(bArr);
                if (-1 == read) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
        } catch (Base64DataException unused) {
            outputStream.write("\n\nThere was an error while decoding the message.".getBytes());
        } catch (Throwable th) {
            outputStream.close();
            throw th;
        }
        outputStream.close();
        return binaryTempFileBody;
    }

    private void destroyResponses() {
        ImapConnection imapConnection = this.connection;
        if (imapConnection != null) {
            imapConnection.destroyResponses();
        }
    }

    private void doSelect() throws IOException, MessagingException {
        int i = -1;
        for (ImapResponse next : this.connection.executeSimpleCommand(String.format(Locale.US, "SELECT \"%s\"", new Object[]{this.name}))) {
            if (next.isDataResponse(1, "EXISTS")) {
                i = next.getStringOrEmpty(0).getNumberOrZero();
            } else if (next.isOk()) {
                ImapString responseCodeOrEmpty = next.getResponseCodeOrEmpty();
                if (!responseCodeOrEmpty.mo9269is("READ-ONLY")) {
                    responseCodeOrEmpty.mo9269is("READ-WRITE");
                }
            } else if (next.isTagged()) {
                this.store.getImapHelper().handleEvent(OmtpEvents.DATA_MAILBOX_OPEN_FAILED);
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Can't open mailbox: ");
                outline13.append(next.getStatusResponseTextOrEmpty());
                throw new MessagingException(outline13.toString());
            }
        }
        if (i != -1) {
            this.exists = true;
            return;
        }
        throw new MessagingException(0, "Did not find message count during select", (Throwable) null);
    }

    private void handleUntaggedResponses(List<ImapResponse> list) {
        for (ImapResponse next : list) {
            if (next.isDataResponse(1, "EXISTS")) {
                next.getStringOrEmpty(0).getNumberOrZero();
            }
        }
    }

    private MessagingException ioExceptionHandler(ImapConnection imapConnection, IOException iOException) {
        imapConnection.close();
        if (imapConnection == this.connection) {
            this.connection = null;
            close(false);
        }
        return new MessagingException(1, "IO Error", iOException);
    }

    protected static boolean isAsciiString(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) >= 128) {
                return false;
            }
        }
        return true;
    }

    private static void parseBodyStructure(ImapList imapList, Part part, String str) throws MessagingException {
        ImapList imapList2;
        ImapList imapList3 = imapList;
        Part part2 = part;
        String str2 = str;
        int i = 0;
        if (imapList3.getElementOrNone(0).isList()) {
            MimeMultipart mimeMultipart = new MimeMultipart();
            int size = imapList.size();
            while (true) {
                if (i >= size) {
                    break;
                }
                ImapElement elementOrNone = imapList3.getElementOrNone(i);
                if (elementOrNone.isList()) {
                    MimeBodyPart mimeBodyPart = new MimeBodyPart();
                    if (str2.equals("TEXT")) {
                        parseBodyStructure(imapList3.getListOrEmpty(i), mimeBodyPart, Integer.toString(i + 1));
                    } else {
                        ImapList listOrEmpty = imapList3.getListOrEmpty(i);
                        StringBuilder outline14 = GeneratedOutlineSupport.outline14(str2, ".");
                        outline14.append(i + 1);
                        parseBodyStructure(listOrEmpty, mimeBodyPart, outline14.toString());
                    }
                    mimeMultipart.addBodyPart(mimeBodyPart);
                    i++;
                } else if (elementOrNone.isString()) {
                    mimeMultipart.setSubType(imapList3.getStringOrEmpty(i).getString().toLowerCase(Locale.US));
                }
            }
            part2.setBody(mimeMultipart);
            return;
        }
        ImapString stringOrEmpty = imapList3.getStringOrEmpty(0);
        int i2 = 1;
        ImapString stringOrEmpty2 = imapList3.getStringOrEmpty(1);
        String lowerCase = (stringOrEmpty.getString() + "/" + stringOrEmpty2.getString()).toLowerCase(Locale.US);
        int i3 = 2;
        ImapList listOrEmpty2 = imapList3.getListOrEmpty(2);
        ImapString stringOrEmpty3 = imapList3.getStringOrEmpty(3);
        ImapString stringOrEmpty4 = imapList3.getStringOrEmpty(5);
        int numberOrZero = imapList3.getStringOrEmpty(6).getNumberOrZero();
        if (!MimeUtility.mimeTypeMatches(lowerCase, "message/rfc822")) {
            StringBuilder sb = new StringBuilder(lowerCase);
            int size2 = listOrEmpty2.size();
            while (i2 < size2) {
                Object[] objArr = new Object[i3];
                objArr[0] = listOrEmpty2.getStringOrEmpty(i2 - 1).getString();
                objArr[1] = listOrEmpty2.getStringOrEmpty(i2).getString();
                sb.append(String.format(";\n %s=\"%s\"", objArr));
                i2 += 2;
                i3 = 2;
            }
            part2.setHeader("Content-Type", sb.toString());
            if (!stringOrEmpty.mo9269is("TEXT") || !imapList3.getElementOrNone(9).isList()) {
                imapList2 = imapList3.getListOrEmpty(8);
            } else {
                imapList2 = imapList3.getListOrEmpty(9);
            }
            StringBuilder sb2 = new StringBuilder();
            if (imapList2.size() > 0) {
                String lowerCase2 = imapList2.getStringOrEmpty(0).getString().toLowerCase(Locale.US);
                if (!TextUtils.isEmpty(lowerCase2)) {
                    sb2.append(lowerCase2);
                }
                ImapList listOrEmpty3 = imapList2.getListOrEmpty(1);
                if (!(listOrEmpty3.size() == 0)) {
                    int size3 = listOrEmpty3.size();
                    for (int i4 = 1; i4 < size3; i4 += 2) {
                        sb2.append(String.format(Locale.US, ";\n %s=\"%s\"", new Object[]{listOrEmpty3.getStringOrEmpty(i4 - 1).getString().toLowerCase(Locale.US), listOrEmpty3.getStringOrEmpty(i4).getString()}));
                    }
                }
            }
            if (numberOrZero > 0 && MimeUtility.getHeaderParameter(sb2.toString(), "size") == null) {
                sb2.append(String.format(Locale.US, ";\n size=%d", new Object[]{Integer.valueOf(numberOrZero)}));
            }
            if (sb2.length() > 0) {
                part2.setHeader("Content-Disposition", sb2.toString());
            }
            if (!stringOrEmpty4.isEmpty()) {
                part2.setHeader("Content-Transfer-Encoding", stringOrEmpty4.getString());
            }
            if (!stringOrEmpty3.isEmpty()) {
                part2.setHeader("Content-ID", stringOrEmpty3.getString());
            }
            if (numberOrZero > 0) {
                if (part2 instanceof ImapStore.ImapMessage) {
                    ((ImapStore.ImapMessage) part2).setSize(numberOrZero);
                } else if (part2 instanceof MimeBodyPart) {
                    ((MimeBodyPart) part2).setSize(numberOrZero);
                } else {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown part type ");
                    outline13.append(part.toString());
                    throw new MessagingException(outline13.toString());
                }
            }
            part2.setHeader("X-Android-Attachment-StoreData", str2);
            return;
        }
        throw new MessagingException(0, "BODYSTRUCTURE message/rfc822 not yet supported.", (Throwable) null);
    }

    public void close(boolean z) {
        if (z) {
            try {
                expunge();
            } catch (MessagingException e) {
                VvmLog.m44e("ImapFolder", "Messaging Exception", e);
            }
        }
        synchronized (this) {
            this.connection = null;
        }
    }

    public Message[] expunge() throws MessagingException {
        checkOpen();
        try {
            ImapConnection imapConnection = this.connection;
            imapConnection.sendCommand("EXPUNGE", false);
            handleUntaggedResponses(imapConnection.getCommandResponses());
            destroyResponses();
            return null;
        } catch (IOException e) {
            this.store.getImapHelper().handleEvent(OmtpEvents.DATA_GENERIC_IMAP_IOE);
            throw ioExceptionHandler(this.connection, e);
        } catch (Throwable th) {
            destroyResponses();
            throw th;
        }
    }

    public void fetch(Message[] messageArr, FetchProfile fetchProfile, MessageRetrievalListener messageRetrievalListener) throws MessagingException {
        try {
            fetchInternal(messageArr, fetchProfile, messageRetrievalListener);
        } catch (RuntimeException e) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Exception detected: ");
            outline13.append(e.getMessage());
            VvmLog.m46w("ImapFolder", outline13.toString());
            throw e;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:122:0x023d A[Catch:{ IOException -> 0x0248 }, LOOP:2: B:34:0x00df->B:122:0x023d, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x023c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fetchInternal(com.android.voicemail.impl.mail.Message[] r19, com.android.voicemail.impl.mail.FetchProfile r20, com.android.voicemail.impl.mail.store.ImapFolder.MessageRetrievalListener r21) throws com.android.voicemail.impl.mail.MessagingException {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            r2 = r20
            r3 = r21
            int r4 = r0.length
            if (r4 != 0) goto L_0x000c
            return
        L_0x000c:
            r18.checkOpen()
            android.util.ArrayMap r4 = new android.util.ArrayMap
            r4.<init>()
            int r5 = r0.length
            r6 = 0
            r7 = r6
        L_0x0017:
            if (r7 >= r5) goto L_0x0025
            r8 = r0[r7]
            java.lang.String r9 = r8.getUid()
            r4.put(r9, r8)
            int r7 = r7 + 1
            goto L_0x0017
        L_0x0025:
            java.util.LinkedHashSet r5 = new java.util.LinkedHashSet
            r5.<init>()
            java.lang.String r7 = "UID"
            r5.add(r7)
            com.android.voicemail.impl.mail.FetchProfile$Item r8 = com.android.voicemail.impl.mail.FetchProfile.Item.FLAGS
            boolean r8 = r2.contains(r8)
            java.lang.String r9 = "FLAGS"
            if (r8 == 0) goto L_0x003c
            r5.add(r9)
        L_0x003c:
            com.android.voicemail.impl.mail.FetchProfile$Item r8 = com.android.voicemail.impl.mail.FetchProfile.Item.ENVELOPE
            boolean r8 = r2.contains(r8)
            java.lang.String r10 = "RFC822.SIZE"
            java.lang.String r11 = "INTERNALDATE"
            if (r8 == 0) goto L_0x0053
            r5.add(r11)
            r5.add(r10)
            java.lang.String r8 = "BODY.PEEK[HEADER.FIELDS (date subject from content-type to cc message-id content-duration)]"
            r5.add(r8)
        L_0x0053:
            com.android.voicemail.impl.mail.FetchProfile$Item r8 = com.android.voicemail.impl.mail.FetchProfile.Item.STRUCTURE
            boolean r8 = r2.contains(r8)
            java.lang.String r12 = "BODYSTRUCTURE"
            if (r8 == 0) goto L_0x0060
            r5.add(r12)
        L_0x0060:
            com.android.voicemail.impl.mail.FetchProfile$Item r8 = com.android.voicemail.impl.mail.FetchProfile.Item.BODY_SANE
            boolean r8 = r2.contains(r8)
            if (r8 == 0) goto L_0x006d
            java.lang.String r8 = com.android.voicemail.impl.mail.store.imap.ImapConstants.FETCH_FIELD_BODY_PEEK_SANE
            r5.add(r8)
        L_0x006d:
            com.android.voicemail.impl.mail.FetchProfile$Item r8 = com.android.voicemail.impl.mail.FetchProfile.Item.BODY
            boolean r8 = r2.contains(r8)
            if (r8 == 0) goto L_0x007a
            java.lang.String r8 = "BODY.PEEK[]"
            r5.add(r8)
        L_0x007a:
            java.util.Iterator r8 = r20.iterator()
        L_0x007e:
            boolean r13 = r8.hasNext()
            if (r13 == 0) goto L_0x0091
            java.lang.Object r13 = r8.next()
            com.android.voicemail.impl.mail.Fetchable r13 = (com.android.voicemail.impl.mail.Fetchable) r13
            boolean r14 = r13 instanceof com.android.voicemail.impl.mail.Part
            if (r14 == 0) goto L_0x007e
            com.android.voicemail.impl.mail.Part r13 = (com.android.voicemail.impl.mail.Part) r13
            goto L_0x0092
        L_0x0091:
            r13 = 0
        L_0x0092:
            if (r13 == 0) goto L_0x00b3
            java.lang.String r8 = "X-Android-Attachment-StoreData"
            java.lang.String[] r8 = r13.getHeader(r8)
            if (r8 == 0) goto L_0x00b3
            java.lang.String r14 = "BODY.PEEK["
            java.lang.StringBuilder r14 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r14)
            r6 = r8[r6]
            r14.append(r6)
            java.lang.String r6 = "]"
            r14.append(r6)
            java.lang.String r6 = r14.toString()
            r5.add(r6)
        L_0x00b3:
            com.android.voicemail.impl.mail.store.ImapConnection r6 = r1.connection     // Catch:{ IOException -> 0x0248 }
            java.util.Locale r8 = java.util.Locale.US     // Catch:{ IOException -> 0x0248 }
            java.lang.String r14 = "UID FETCH %s (%s)"
            r15 = 2
            java.lang.Object[] r15 = new java.lang.Object[r15]     // Catch:{ IOException -> 0x0248 }
            java.lang.String r0 = com.android.voicemail.impl.mail.store.ImapStore.joinMessageUids(r19)     // Catch:{ IOException -> 0x0248 }
            r16 = 0
            r15[r16] = r0     // Catch:{ IOException -> 0x0248 }
            int r0 = r5.size()     // Catch:{ IOException -> 0x0248 }
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ IOException -> 0x0248 }
            java.lang.Object[] r0 = r5.toArray(r0)     // Catch:{ IOException -> 0x0248 }
            r5 = 32
            java.lang.String r0 = com.android.voicemail.impl.mail.utils.Utility.combine(r0, r5)     // Catch:{ IOException -> 0x0248 }
            r5 = 1
            r15[r5] = r0     // Catch:{ IOException -> 0x0248 }
            java.lang.String r0 = java.lang.String.format(r8, r14, r15)     // Catch:{ IOException -> 0x0248 }
            r8 = 0
            r6.sendCommand(r0, r8)     // Catch:{ IOException -> 0x0248 }
        L_0x00df:
            com.android.voicemail.impl.mail.store.ImapConnection r0 = r1.connection     // Catch:{ all -> 0x0243 }
            com.android.voicemail.impl.mail.store.imap.ImapResponse r6 = r0.readResponse()     // Catch:{ all -> 0x0243 }
            java.lang.String r0 = "FETCH"
            boolean r0 = r6.isDataResponse(r5, r0)     // Catch:{ all -> 0x0243 }
            if (r0 != 0) goto L_0x00ee
            goto L_0x010b
        L_0x00ee:
            r0 = 2
            com.android.voicemail.impl.mail.store.imap.ImapList r8 = r6.getListOrEmpty(r0)     // Catch:{ all -> 0x0243 }
            com.android.voicemail.impl.mail.store.imap.ImapString r0 = r8.getKeyedStringOrEmpty(r7)     // Catch:{ all -> 0x0243 }
            java.lang.String r0 = r0.getString()     // Catch:{ all -> 0x0243 }
            boolean r14 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0243 }
            if (r14 == 0) goto L_0x0102
            goto L_0x010b
        L_0x0102:
            java.lang.Object r0 = r4.get(r0)     // Catch:{ all -> 0x0243 }
            r14 = r0
            com.android.voicemail.impl.mail.store.ImapStore$ImapMessage r14 = (com.android.voicemail.impl.mail.store.ImapStore.ImapMessage) r14     // Catch:{ all -> 0x0243 }
            if (r14 != 0) goto L_0x0114
        L_0x010b:
            r18.destroyResponses()     // Catch:{ IOException -> 0x0248 }
            r16 = r4
            r17 = r7
            goto L_0x0236
        L_0x0114:
            com.android.voicemail.impl.mail.FetchProfile$Item r0 = com.android.voicemail.impl.mail.FetchProfile.Item.FLAGS     // Catch:{ all -> 0x0243 }
            boolean r0 = r2.contains(r0)     // Catch:{ all -> 0x0243 }
            if (r0 == 0) goto L_0x0171
            com.android.voicemail.impl.mail.store.imap.ImapList r0 = r8.getKeyedListOrEmpty(r9)     // Catch:{ all -> 0x0243 }
            int r5 = r0.size()     // Catch:{ all -> 0x0243 }
            r15 = 0
        L_0x0125:
            if (r15 >= r5) goto L_0x0171
            r16 = r4
            com.android.voicemail.impl.mail.store.imap.ImapString r4 = r0.getStringOrEmpty(r15)     // Catch:{ all -> 0x0243 }
            r19 = r0
            java.lang.String r0 = "\\DELETED"
            boolean r0 = r4.mo9269is(r0)     // Catch:{ all -> 0x0243 }
            if (r0 == 0) goto L_0x013e
            java.lang.String r0 = "deleted"
            r4 = 1
            r14.setFlagInternal(r0, r4)     // Catch:{ all -> 0x0243 }
            goto L_0x016a
        L_0x013e:
            java.lang.String r0 = "\\ANSWERED"
            boolean r0 = r4.mo9269is(r0)     // Catch:{ all -> 0x0243 }
            if (r0 == 0) goto L_0x014d
            java.lang.String r0 = "answered"
            r4 = 1
            r14.setFlagInternal(r0, r4)     // Catch:{ all -> 0x0243 }
            goto L_0x016a
        L_0x014d:
            java.lang.String r0 = "\\SEEN"
            boolean r0 = r4.mo9269is(r0)     // Catch:{ all -> 0x0243 }
            if (r0 == 0) goto L_0x015c
            java.lang.String r0 = "seen"
            r4 = 1
            r14.setFlagInternal(r0, r4)     // Catch:{ all -> 0x0243 }
            goto L_0x016a
        L_0x015c:
            java.lang.String r0 = "\\FLAGGED"
            boolean r0 = r4.mo9269is(r0)     // Catch:{ all -> 0x0243 }
            if (r0 == 0) goto L_0x016a
            java.lang.String r0 = "flagged"
            r4 = 1
            r14.setFlagInternal(r0, r4)     // Catch:{ all -> 0x0243 }
        L_0x016a:
            int r15 = r15 + 1
            r0 = r19
            r4 = r16
            goto L_0x0125
        L_0x0171:
            r16 = r4
            com.android.voicemail.impl.mail.FetchProfile$Item r0 = com.android.voicemail.impl.mail.FetchProfile.Item.ENVELOPE     // Catch:{ all -> 0x0243 }
            boolean r0 = r2.contains(r0)     // Catch:{ all -> 0x0243 }
            java.lang.String r4 = "ImapFolder"
            if (r0 == 0) goto L_0x01af
            com.android.voicemail.impl.mail.store.imap.ImapString r0 = r8.getKeyedStringOrEmpty(r11)     // Catch:{ all -> 0x0243 }
            java.util.Date r0 = r0.getDateOrNull()     // Catch:{ all -> 0x0243 }
            com.android.voicemail.impl.mail.store.imap.ImapString r5 = r8.getKeyedStringOrEmpty(r10)     // Catch:{ all -> 0x0243 }
            int r5 = r5.getNumberOrZero()     // Catch:{ all -> 0x0243 }
            java.lang.String r15 = "BODY[HEADER"
            r17 = r7
            r7 = 1
            com.android.voicemail.impl.mail.store.imap.ImapString r7 = r8.getKeyedStringOrEmpty(r15, r7)     // Catch:{ all -> 0x0243 }
            java.lang.String r7 = r7.getString()     // Catch:{ all -> 0x0243 }
            r14.setInternalDate(r0)     // Catch:{ all -> 0x0243 }
            r14.setSize(r5)     // Catch:{ all -> 0x0243 }
            java.io.ByteArrayInputStream r0 = com.android.voicemail.impl.mail.utils.Utility.streamFromAsciiString(r7)     // Catch:{ Exception -> 0x01a8 }
            r14.parse(r0)     // Catch:{ Exception -> 0x01a8 }
            goto L_0x01b1
        L_0x01a8:
            r0 = move-exception
            java.lang.String r5 = "Error parsing header %s"
            com.android.voicemail.impl.VvmLog.m44e(r4, r5, r0)     // Catch:{ all -> 0x0243 }
            goto L_0x01b1
        L_0x01af:
            r17 = r7
        L_0x01b1:
            com.android.voicemail.impl.mail.FetchProfile$Item r0 = com.android.voicemail.impl.mail.FetchProfile.Item.STRUCTURE     // Catch:{ all -> 0x0243 }
            boolean r0 = r2.contains(r0)     // Catch:{ all -> 0x0243 }
            if (r0 == 0) goto L_0x01d2
            com.android.voicemail.impl.mail.store.imap.ImapList r0 = r8.getKeyedListOrEmpty(r12)     // Catch:{ all -> 0x0243 }
            int r5 = r0.size()     // Catch:{ all -> 0x0243 }
            if (r5 != 0) goto L_0x01c5
            r5 = 1
            goto L_0x01c6
        L_0x01c5:
            r5 = 0
        L_0x01c6:
            if (r5 != 0) goto L_0x01d2
            java.lang.String r5 = "TEXT"
            parseBodyStructure(r0, r14, r5)     // Catch:{ MessagingException -> 0x01ce }
            goto L_0x01d2
        L_0x01ce:
            r0 = 0
            r14.setBody(r0)     // Catch:{ all -> 0x0243 }
        L_0x01d2:
            com.android.voicemail.impl.mail.FetchProfile$Item r0 = com.android.voicemail.impl.mail.FetchProfile.Item.BODY     // Catch:{ all -> 0x0243 }
            boolean r0 = r2.contains(r0)     // Catch:{ all -> 0x0243 }
            if (r0 != 0) goto L_0x01e2
            com.android.voicemail.impl.mail.FetchProfile$Item r0 = com.android.voicemail.impl.mail.FetchProfile.Item.BODY_SANE     // Catch:{ all -> 0x0243 }
            boolean r0 = r2.contains(r0)     // Catch:{ all -> 0x0243 }
            if (r0 == 0) goto L_0x01f8
        L_0x01e2:
            java.lang.String r0 = "BODY[]"
            r5 = 1
            com.android.voicemail.impl.mail.store.imap.ImapString r0 = r8.getKeyedStringOrEmpty(r0, r5)     // Catch:{ all -> 0x0243 }
            java.io.InputStream r0 = r0.getAsStream()     // Catch:{ all -> 0x0243 }
            r14.parse(r0)     // Catch:{ Exception -> 0x01f1 }
            goto L_0x01f8
        L_0x01f1:
            r0 = move-exception
            r5 = r0
            java.lang.String r0 = "Error parsing body %s"
            com.android.voicemail.impl.VvmLog.m44e(r4, r0, r5)     // Catch:{ all -> 0x0243 }
        L_0x01f8:
            if (r13 == 0) goto L_0x022d
            java.lang.String r0 = "BODY["
            r5 = 1
            com.android.voicemail.impl.mail.store.imap.ImapString r0 = r8.getKeyedStringOrEmpty(r0, r5)     // Catch:{ all -> 0x0243 }
            java.io.InputStream r0 = r0.getAsStream()     // Catch:{ all -> 0x0243 }
            java.lang.String r7 = "Content-Transfer-Encoding"
            java.lang.String[] r7 = r13.getHeader(r7)     // Catch:{ all -> 0x0243 }
            if (r7 == 0) goto L_0x0214
            int r8 = r7.length     // Catch:{ all -> 0x0243 }
            if (r8 <= 0) goto L_0x0214
            r8 = 0
            r7 = r7[r8]     // Catch:{ all -> 0x0243 }
            goto L_0x0216
        L_0x0214:
            java.lang.String r7 = "7bit"
        L_0x0216:
            com.android.voicemail.impl.mail.store.ImapStore r8 = r1.store     // Catch:{ Exception -> 0x0226 }
            r8.getContext()     // Catch:{ Exception -> 0x0226 }
            r13.getSize()     // Catch:{ Exception -> 0x0226 }
            com.android.voicemail.impl.mail.Body r0 = decodeBody(r0, r7)     // Catch:{ Exception -> 0x0226 }
            r14.setBody(r0)     // Catch:{ Exception -> 0x0226 }
            goto L_0x022e
        L_0x0226:
            r0 = move-exception
            java.lang.String r7 = "Error fetching body %s"
            com.android.voicemail.impl.VvmLog.m44e(r4, r7, r0)     // Catch:{ all -> 0x0243 }
            goto L_0x022e
        L_0x022d:
            r5 = 1
        L_0x022e:
            if (r3 == 0) goto L_0x0233
            r3.messageRetrieved(r14)     // Catch:{ all -> 0x0243 }
        L_0x0233:
            r18.destroyResponses()     // Catch:{ IOException -> 0x0248 }
        L_0x0236:
            boolean r0 = r6.isTagged()     // Catch:{ IOException -> 0x0248 }
            if (r0 == 0) goto L_0x023d
            return
        L_0x023d:
            r4 = r16
            r7 = r17
            goto L_0x00df
        L_0x0243:
            r0 = move-exception
            r18.destroyResponses()     // Catch:{ IOException -> 0x0248 }
            throw r0     // Catch:{ IOException -> 0x0248 }
        L_0x0248:
            r0 = move-exception
            com.android.voicemail.impl.mail.store.ImapStore r2 = r1.store
            com.android.voicemail.impl.imap.ImapHelper r2 = r2.getImapHelper()
            com.android.voicemail.impl.OmtpEvents r3 = com.android.voicemail.impl.OmtpEvents.DATA_GENERIC_IMAP_IOE
            r2.handleEvent(r3)
            com.android.voicemail.impl.mail.store.ImapConnection r2 = r1.connection
            com.android.voicemail.impl.mail.MessagingException r0 = r1.ioExceptionHandler(r2, r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.mail.store.ImapFolder.fetchInternal(com.android.voicemail.impl.mail.Message[], com.android.voicemail.impl.mail.FetchProfile, com.android.voicemail.impl.mail.store.ImapFolder$MessageRetrievalListener):void");
    }

    public Message getMessage(String str) throws MessagingException {
        checkOpen();
        String[] searchForUids = searchForUids(GeneratedOutlineSupport.outline8("UID ", str));
        for (String equals : searchForUids) {
            if (equals.equals(str)) {
                return new ImapStore.ImapMessage(str, this);
            }
        }
        VvmLog.m43e("ImapFolder", "UID " + str + " not found on server");
        return null;
    }

    public Message[] getMessages(String[] strArr) throws MessagingException {
        if (strArr == null) {
            strArr = searchForUids("1:* NOT DELETED");
        }
        return getMessagesInternal(strArr);
    }

    public Message[] getMessagesInternal(String[] strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String imapMessage : strArr) {
            arrayList.add(new ImapStore.ImapMessage(imapMessage, this));
        }
        return (Message[]) arrayList.toArray(Message.EMPTY_ARRAY);
    }

    public Quota getQuota() throws MessagingException {
        try {
            for (ImapResponse next : this.connection.executeSimpleCommand(String.format(Locale.US, "GETQUOTAROOT \"%s\"", new Object[]{this.name}))) {
                if (next.isDataResponse(0, "QUOTA")) {
                    ImapList listOrEmpty = next.getListOrEmpty(2);
                    int i = 0;
                    while (i < listOrEmpty.size()) {
                        if (!listOrEmpty.getStringOrEmpty(i).mo9269is("voice")) {
                            i += 3;
                        } else {
                            Quota quota = new Quota(this, listOrEmpty.getStringOrEmpty(i + 1).getNumber(-1), listOrEmpty.getStringOrEmpty(i + 2).getNumber(-1));
                            destroyResponses();
                            return quota;
                        }
                    }
                    continue;
                }
            }
            destroyResponses();
            return null;
        } catch (IOException e) {
            this.store.getImapHelper().handleEvent(OmtpEvents.DATA_GENERIC_IMAP_IOE);
            throw ioExceptionHandler(this.connection, e);
        } catch (Throwable th) {
            destroyResponses();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public String[] getSearchUids(List<ImapResponse> list) {
        ArrayList arrayList = new ArrayList();
        for (ImapResponse next : list) {
            if (next.isDataResponse(0, "SEARCH")) {
                for (int i = 1; i < next.size(); i++) {
                    ImapString stringOrEmpty = next.getStringOrEmpty(i);
                    stringOrEmpty.isString();
                    arrayList.add(stringOrEmpty.getString());
                }
            }
        }
        return (String[]) arrayList.toArray(Utility.EMPTY_STRINGS);
    }

    public boolean isOpen() {
        return this.exists && this.connection != null;
    }

    public void open(String str) throws MessagingException {
        try {
            if (!isOpen()) {
                synchronized (this) {
                    this.connection = this.store.getConnection();
                }
                doSelect();
                destroyResponses();
                return;
            }
            throw new AssertionError("Duplicated open on ImapFolder");
        } catch (IOException e) {
            throw ioExceptionHandler(this.connection, e);
        } catch (AuthenticationFailedException e2) {
            this.connection = null;
            close(false);
            throw e2;
        } catch (MessagingException e3) {
            this.exists = false;
            close(false);
            throw e3;
        } catch (Throwable th) {
            destroyResponses();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public String[] searchForUids(String str) throws MessagingException {
        checkOpen();
        try {
            String[] searchUids = getSearchUids(this.connection.executeSimpleCommand("UID SEARCH " + str));
            "searchForUids '" + str + "' results: " + searchUids.length;
            destroyResponses();
            return searchUids;
        } catch (ImapStore.ImapException unused) {
            "ImapException in search: " + str;
            String[] strArr = Utility.EMPTY_STRINGS;
            destroyResponses();
            return strArr;
        } catch (IOException e) {
            "IOException in search: " + str;
            this.store.getImapHelper().handleEvent(OmtpEvents.DATA_GENERIC_IMAP_IOE);
            throw ioExceptionHandler(this.connection, e);
        } catch (Throwable th) {
            destroyResponses();
            throw th;
        }
    }

    public void setFlags(Message[] messageArr, String[] strArr, boolean z) throws MessagingException {
        String str;
        checkOpen();
        if (strArr.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String str2 : strArr) {
                if (str2 == "seen") {
                    sb.append(" \\SEEN");
                } else if (str2 == "deleted") {
                    sb.append(" \\DELETED");
                } else if (str2 == "flagged") {
                    sb.append(" \\FLAGGED");
                } else if (str2 == "answered") {
                    sb.append(" \\ANSWERED");
                }
            }
            str = sb.substring(1);
        } else {
            str = "";
        }
        try {
            ImapConnection imapConnection = this.connection;
            Locale locale = Locale.US;
            Object[] objArr = new Object[3];
            objArr[0] = ImapStore.joinMessageUids(messageArr);
            objArr[1] = z ? "+" : "-";
            objArr[2] = str;
            imapConnection.executeSimpleCommand(String.format(locale, "UID STORE %s %sFLAGS.SILENT (%s)", objArr));
            destroyResponses();
        } catch (IOException e) {
            this.store.getImapHelper().handleEvent(OmtpEvents.DATA_GENERIC_IMAP_IOE);
            throw ioExceptionHandler(this.connection, e);
        } catch (Throwable th) {
            destroyResponses();
            throw th;
        }
    }
}
