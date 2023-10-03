package com.android.voicemail.impl.mail.store.imap;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.mail.FixedLengthInputStream;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.PeekableInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ImapResponseParser {
    private final StringBuilder bufferReadUntil = new StringBuilder();

    /* renamed from: in */
    private final PeekableInputStream f50in;
    private final int literalKeepInMemoryThreshold;
    private final StringBuilder parseBareString = new StringBuilder();
    private final ArrayList<ImapResponse> responsesToDestroy = new ArrayList<>();

    public static class ByeException extends IOException {
        public ByeException() {
            super("Received BYE");
        }
    }

    public ImapResponseParser(InputStream inputStream) {
        this.f50in = new PeekableInputStream(inputStream);
        this.literalKeepInMemoryThreshold = 2097152;
    }

    private void onParseError(Exception exc) {
        int i = 0;
        while (i < 4) {
            try {
                int readByte = readByte();
                if (readByte == -1 || readByte == 10) {
                    break;
                }
                i++;
            } catch (IOException unused) {
            }
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Exception detected: ");
        outline13.append(exc.getMessage());
        VvmLog.m46w("ImapResponseParser", outline13.toString());
    }

    private ImapString parseBareString() throws IOException, MessagingException {
        this.parseBareString.setLength(0);
        while (true) {
            int peek = peek();
            if (peek != 40 && peek != 41 && peek != 123 && peek != 32 && peek != 93 && peek != 37 && peek != 34 && ((peek < 0 || peek > 31) && peek != 127)) {
                if (peek == 91) {
                    this.parseBareString.append((char) readByte());
                    this.parseBareString.append(readUntil(']'));
                    this.parseBareString.append(']');
                } else {
                    this.parseBareString.append((char) readByte());
                }
            }
        }
        if (this.parseBareString.length() != 0) {
            String sb = this.parseBareString.toString();
            if ("NIL".equalsIgnoreCase(sb)) {
                return ImapString.EMPTY;
            }
            return new ImapSimpleString(sb);
        }
        throw new MessagingException(0, "Expected string, none found.", (Throwable) null);
    }

    private void parseElements(ImapList imapList, char c) throws IOException, MessagingException {
        ImapElement imapElement;
        while (true) {
            int peek = peek();
            if (peek != c) {
                if (peek != 32) {
                    int peek2 = peek();
                    ImapElement imapElement2 = null;
                    if (peek2 == 10) {
                        readByte();
                    } else if (peek2 == 13) {
                        readByte();
                        expect(10);
                    } else if (peek2 == 34) {
                        readByte();
                        imapElement2 = new ImapSimpleString(readUntil('\"'));
                    } else if (peek2 == 40) {
                        imapElement2 = parseList('(', ')');
                    } else if (peek2 == 91) {
                        imapElement2 = parseList('[', ']');
                    } else if (peek2 != 123) {
                        imapElement2 = parseBareString();
                    } else {
                        expect('{');
                        try {
                            int parseInt = Integer.parseInt(readUntil('}'));
                            if (parseInt >= 0) {
                                expect(13);
                                expect(10);
                                FixedLengthInputStream fixedLengthInputStream = new FixedLengthInputStream(this.f50in, parseInt);
                                if (parseInt > this.literalKeepInMemoryThreshold) {
                                    imapElement = new ImapTempFileLiteral(fixedLengthInputStream);
                                } else {
                                    imapElement = new ImapMemoryLiteral(fixedLengthInputStream);
                                }
                                imapElement2 = imapElement;
                            } else {
                                throw new MessagingException(0, "Invalid negative length in literal", (Throwable) null);
                            }
                        } catch (NumberFormatException unused) {
                            throw new MessagingException(0, "Invalid length in literal", (Throwable) null);
                        }
                    }
                    if (imapElement2 != null) {
                        imapList.add(imapElement2);
                    } else {
                        return;
                    }
                } else {
                    readByte();
                }
            } else {
                return;
            }
        }
    }

    private ImapList parseList(char c, char c2) throws IOException, MessagingException {
        expect(c);
        ImapList imapList = new ImapList();
        parseElements(imapList, c2);
        expect(c2);
        return imapList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.voicemail.impl.mail.store.imap.ImapResponse parseResponse() throws java.io.IOException, com.android.voicemail.impl.mail.MessagingException {
        /*
            r7 = this;
            r0 = 0
            int r1 = r7.peek()     // Catch:{ all -> 0x009b }
            r2 = 43
            r3 = 32
            r4 = 10
            r5 = 13
            if (r1 != r2) goto L_0x0030
            r7.readByte()     // Catch:{ all -> 0x009b }
            r7.expect(r3)     // Catch:{ all -> 0x009b }
            com.android.voicemail.impl.mail.store.imap.ImapResponse r1 = new com.android.voicemail.impl.mail.store.imap.ImapResponse     // Catch:{ all -> 0x009b }
            r2 = 1
            r1.<init>(r0, r2)     // Catch:{ all -> 0x009b }
            com.android.voicemail.impl.mail.store.imap.ImapSimpleString r0 = new com.android.voicemail.impl.mail.store.imap.ImapSimpleString     // Catch:{ all -> 0x002c }
            java.lang.String r2 = r7.readUntil(r5)     // Catch:{ all -> 0x002c }
            r7.expect(r4)     // Catch:{ all -> 0x002c }
            r0.<init>(r2)     // Catch:{ all -> 0x002c }
            r1.add(r0)     // Catch:{ all -> 0x002c }
            goto L_0x0097
        L_0x002c:
            r7 = move-exception
            r0 = r1
            goto L_0x009c
        L_0x0030:
            r2 = 42
            if (r1 != r2) goto L_0x003c
            r7.readByte()     // Catch:{ all -> 0x009b }
            r7.expect(r3)     // Catch:{ all -> 0x009b }
            r1 = r0
            goto L_0x0040
        L_0x003c:
            java.lang.String r1 = r7.readUntil(r3)     // Catch:{ all -> 0x009b }
        L_0x0040:
            com.android.voicemail.impl.mail.store.imap.ImapResponse r2 = new com.android.voicemail.impl.mail.store.imap.ImapResponse     // Catch:{ all -> 0x009b }
            r6 = 0
            r2.<init>(r1, r6)     // Catch:{ all -> 0x009b }
            com.android.voicemail.impl.mail.store.imap.ImapString r0 = r7.parseBareString()     // Catch:{ all -> 0x0098 }
            r2.add(r0)     // Catch:{ all -> 0x0098 }
            int r0 = r7.peek()     // Catch:{ all -> 0x0098 }
            if (r0 != r3) goto L_0x0090
            r7.readByte()     // Catch:{ all -> 0x0098 }
            boolean r0 = r2.isStatusResponse()     // Catch:{ all -> 0x0098 }
            if (r0 == 0) goto L_0x008c
            int r0 = r7.peek()     // Catch:{ all -> 0x0098 }
            r1 = 91
            if (r0 != r1) goto L_0x0076
            r0 = 93
            com.android.voicemail.impl.mail.store.imap.ImapList r0 = r7.parseList(r1, r0)     // Catch:{ all -> 0x0098 }
            r2.add(r0)     // Catch:{ all -> 0x0098 }
            int r0 = r7.peek()     // Catch:{ all -> 0x0098 }
            if (r0 != r3) goto L_0x0076
            r7.readByte()     // Catch:{ all -> 0x0098 }
        L_0x0076:
            java.lang.String r0 = r7.readUntil(r5)     // Catch:{ all -> 0x0098 }
            r7.expect(r4)     // Catch:{ all -> 0x0098 }
            boolean r7 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0098 }
            if (r7 != 0) goto L_0x0096
            com.android.voicemail.impl.mail.store.imap.ImapSimpleString r7 = new com.android.voicemail.impl.mail.store.imap.ImapSimpleString     // Catch:{ all -> 0x0098 }
            r7.<init>(r0)     // Catch:{ all -> 0x0098 }
            r2.add(r7)     // Catch:{ all -> 0x0098 }
            goto L_0x0096
        L_0x008c:
            r7.parseElements(r2, r6)     // Catch:{ all -> 0x0098 }
            goto L_0x0096
        L_0x0090:
            r7.expect(r5)     // Catch:{ all -> 0x0098 }
            r7.expect(r4)     // Catch:{ all -> 0x0098 }
        L_0x0096:
            r1 = r2
        L_0x0097:
            return r1
        L_0x0098:
            r7 = move-exception
            r0 = r2
            goto L_0x009c
        L_0x009b:
            r7 = move-exception
        L_0x009c:
            if (r0 == 0) goto L_0x00a1
            r0.destroy()
        L_0x00a1:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.mail.store.imap.ImapResponseParser.parseResponse():com.android.voicemail.impl.mail.store.imap.ImapResponse");
    }

    private int peek() throws IOException {
        int peek = this.f50in.peek();
        if (peek != -1) {
            return peek;
        }
        throw new IOException("End of stream reached");
    }

    private int readByte() throws IOException {
        int read = this.f50in.read();
        if (read != -1) {
            return read;
        }
        throw new IOException("End of stream reached");
    }

    public void destroyResponses() {
        Iterator<ImapResponse> it = this.responsesToDestroy.iterator();
        while (it.hasNext()) {
            it.next().destroy();
        }
        this.responsesToDestroy.clear();
    }

    /* access modifiers changed from: package-private */
    public void expect(char c) throws IOException {
        int readByte = readByte();
        if (c != readByte) {
            throw new IOException(String.format("Expected %04x (%c) but got %04x (%c)", new Object[]{Integer.valueOf(c), Character.valueOf(c), Integer.valueOf(readByte), Character.valueOf((char) readByte)}));
        }
    }

    public ImapResponse readResponse(boolean z) throws IOException, MessagingException {
        try {
            ImapResponse parseResponse = parseResponse();
            if (z || !parseResponse.mo9247is(0, "BYE")) {
                this.responsesToDestroy.add(parseResponse);
                return parseResponse;
            }
            VvmLog.m46w("ImapResponseParser", "Received BYE");
            parseResponse.destroy();
            throw new ByeException();
        } catch (RuntimeException e) {
            onParseError(e);
            throw e;
        } catch (IOException e2) {
            onParseError(e2);
            throw e2;
        }
    }

    /* access modifiers changed from: package-private */
    public String readUntil(char c) throws IOException {
        this.bufferReadUntil.setLength(0);
        while (true) {
            int readByte = readByte();
            if (readByte == c) {
                return this.bufferReadUntil.toString();
            }
            this.bufferReadUntil.append((char) readByte);
        }
    }
}
