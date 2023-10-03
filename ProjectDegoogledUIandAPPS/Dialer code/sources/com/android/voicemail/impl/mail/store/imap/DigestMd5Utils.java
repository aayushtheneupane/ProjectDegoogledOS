package com.android.voicemail.impl.mail.store.imap;

import android.annotation.TargetApi;
import android.util.ArrayMap;
import android.util.Base64;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.mail.MailTransport;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.store.ImapStore;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

@TargetApi(26)
public class DigestMd5Utils {

    public static class Data {
        public String cnonce;
        public String digestUri;

        /* renamed from: nc */
        public String f49nc;
        public String nonce;
        public String password;
        public String qop;
        public String realm;
        public String username;

        private static class ResponseBuilder {
            private StringBuilder builder = new StringBuilder();

            /* synthetic */ ResponseBuilder(C07721 r1) {
            }

            public ResponseBuilder append(String str, String str2) {
                if (this.builder.length() != 0) {
                    this.builder.append(",");
                }
                StringBuilder sb = this.builder;
                sb.append(str);
                sb.append("=");
                sb.append(str2);
                return this;
            }

            public ResponseBuilder appendQuoted(String str, String str2) {
                if (this.builder.length() != 0) {
                    this.builder.append(",");
                }
                StringBuilder sb = this.builder;
                sb.append(str);
                sb.append("=\"");
                sb.append(str2);
                sb.append("\"");
                return this;
            }

            public String toString() {
                return this.builder.toString();
            }
        }

        Data() {
        }

        public String createResponse() {
            String response = DigestMd5Utils.getResponse(this, false);
            ResponseBuilder responseBuilder = new ResponseBuilder((C07721) null);
            responseBuilder.append("CHARSET", "utf-8");
            responseBuilder.appendQuoted("username", this.username);
            responseBuilder.appendQuoted("realm", this.realm);
            responseBuilder.appendQuoted("nonce", this.nonce);
            responseBuilder.append("nc", this.f49nc);
            responseBuilder.appendQuoted("cnonce", this.cnonce);
            responseBuilder.appendQuoted("digest-uri", this.digestUri);
            responseBuilder.append("response", response);
            responseBuilder.append("qop", this.qop);
            return responseBuilder.toString();
        }

        public void verifyResponseAuth(String str) throws MessagingException {
            if (!str.startsWith("rspauth=")) {
                throw new MessagingException(0, "response-auth expected", (Throwable) null);
            } else if (!str.substring(8).equals(DigestMd5Utils.getResponse(this, true))) {
                throw new MessagingException(0, "invalid response-auth return from the server.", (Throwable) null);
            }
        }

        public Data(ImapStore imapStore, MailTransport mailTransport, Map<String, String> map) {
            this.username = imapStore.getUsername();
            this.password = imapStore.getPassword();
            this.realm = map.getOrDefault("realm", "");
            this.nonce = map.get("nonce");
            byte[] bArr = new byte[8];
            new SecureRandom().nextBytes(bArr);
            this.cnonce = Base64.encodeToString(bArr, 2);
            this.f49nc = "00000001";
            this.qop = "auth";
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("imap/");
            outline13.append(mailTransport.getHost());
            this.digestUri = outline13.toString();
        }
    }

    private static class DigestMessageParser {
        private final String message;
        private int position = 0;
        private Map<String, String> result = new ArrayMap();

        public DigestMessageParser(String str) {
            this.message = str;
        }

        private void expect(char c) {
            if (pop() != c) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected character ");
                outline13.append(this.message.charAt(this.position));
                throw new IllegalStateException(outline13.toString());
            }
        }

        private void parsePair() {
            String str;
            int i = this.position;
            while (peek() != '=') {
                this.position++;
            }
            String substring = this.message.substring(i, this.position);
            expect('=');
            if (peek() == '\"') {
                expect('\"');
                StringBuilder sb = new StringBuilder();
                while (true) {
                    char pop = pop();
                    if (pop == '\\') {
                        sb.append(pop());
                    } else if (pop == '\"') {
                        break;
                    } else {
                        sb.append(pop);
                    }
                }
                str = sb.toString();
            } else {
                StringBuilder sb2 = new StringBuilder();
                while (true) {
                    char pop2 = pop();
                    if (pop2 == '\\') {
                        sb2.append(pop());
                    } else if (pop2 == ',') {
                        this.position--;
                        break;
                    } else {
                        sb2.append(pop2);
                    }
                    if (this.position == this.message.length()) {
                        break;
                    }
                }
                str = sb2.toString();
            }
            this.result.put(substring, str);
        }

        private char peek() {
            return this.message.charAt(this.position);
        }

        private char pop() {
            char peek = peek();
            this.position++;
            return peek;
        }

        public Map<String, String> parse() {
            while (this.position < this.message.length()) {
                try {
                    parsePair();
                    if (this.position != this.message.length()) {
                        expect(',');
                    }
                } catch (IndexOutOfBoundsException e) {
                    VvmLog.m43e("DigestMd5Utils", e.toString());
                    return null;
                }
            }
            return this.result;
        }
    }

    private static byte[] getMd5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes(StandardCharsets.ISO_8859_1));
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    static String getResponse(Data data, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (!z) {
            sb.append("AUTHENTICATE");
        }
        sb.append(":");
        sb.append(data.digestUri);
        return toHex(getMd5(toHex(getMd5(new String(getMd5(data.username + ":" + data.realm + ":" + data.password), StandardCharsets.ISO_8859_1) + ":" + data.nonce + ":" + data.cnonce)) + ":" + (data.nonce + ":" + data.f49nc + ":" + data.cnonce + ":" + data.qop + ":" + toHex(getMd5(sb.toString())))));
    }

    public static Map<String, String> parseDigestMessage(String str) throws MessagingException {
        Map<String, String> parse = new DigestMessageParser(str).parse();
        if (parse.containsKey("nonce")) {
            return parse;
        }
        throw new MessagingException(0, "nonce missing from server DIGEST-MD5 challenge", (Throwable) null);
    }

    private static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            byte b2 = b & 255;
            sb.append("0123456789abcdef".charAt(b2 / 16));
            sb.append("0123456789abcdef".charAt(b2 % 16));
        }
        return sb.toString();
    }
}
