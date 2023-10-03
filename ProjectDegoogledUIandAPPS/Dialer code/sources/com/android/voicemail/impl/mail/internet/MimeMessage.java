package com.android.voicemail.impl.mail.internet;

import android.util.Base64DataException;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.VvmLog;
import com.android.voicemail.impl.mail.Address;
import com.android.voicemail.impl.mail.Body;
import com.android.voicemail.impl.mail.BodyPart;
import com.android.voicemail.impl.mail.Message;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.Multipart;
import com.android.voicemail.impl.mail.Part;
import com.android.voicemail.impl.mail.internet.MimeHeader;
import com.android.voicemail.impl.mail.utils.LogUtils;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;
import org.apache.commons.p011io.IOUtils;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.codec.DecoderUtil;
import org.apache.james.mime4j.dom.field.DateTimeField;
import org.apache.james.mime4j.field.DateTimeFieldImpl;
import org.apache.james.mime4j.field.DefaultFieldParser;
import org.apache.james.mime4j.p012io.EOLConvertingInputStream;
import org.apache.james.mime4j.parser.ContentHandler;
import org.apache.james.mime4j.parser.MimeStreamParser;
import org.apache.james.mime4j.stream.BodyDescriptor;
import org.apache.james.mime4j.stream.Field;

public class MimeMessage extends Message {
    private static final Random random = new Random();
    private Body body;
    private Address[] from;
    private MimeHeader header = null;
    private boolean inhibitLocalMessageId = false;
    private Date sentDate;
    protected int size;

    class MimeMessageBuilder implements ContentHandler {
        private final Stack<Object> stack = new Stack<>();

        public MimeMessageBuilder() {
        }

        private void expect(Class<?> cls) {
            if (!cls.isInstance(this.stack.peek())) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Internal stack error: Expected '");
                outline13.append(cls.getName());
                outline13.append("' found '");
                outline13.append(this.stack.peek().getClass().getName());
                outline13.append("'");
                throw new IllegalStateException(outline13.toString());
            }
        }

        public void body(BodyDescriptor bodyDescriptor, InputStream inputStream) throws IOException {
            expect(Part.class);
            InputStream inputStreamForContentTransferEncoding = MimeUtility.getInputStreamForContentTransferEncoding(inputStream, bodyDescriptor.getTransferEncoding());
            BinaryTempFileBody binaryTempFileBody = new BinaryTempFileBody();
            OutputStream outputStream = binaryTempFileBody.getOutputStream();
            try {
                IOUtils.copy(inputStreamForContentTransferEncoding, outputStream);
            } catch (Base64DataException unused) {
            } catch (Throwable th) {
                outputStream.close();
                throw th;
            }
            outputStream.close();
            try {
                ((Part) this.stack.peek()).setBody(binaryTempFileBody);
            } catch (MessagingException e) {
                throw new Error(e);
            }
        }

        public void endBodyPart() {
            expect(BodyPart.class);
            this.stack.pop();
        }

        public void endHeader() {
            expect(Part.class);
        }

        public void endMessage() {
            expect(MimeMessage.class);
            this.stack.pop();
        }

        public void endMultipart() {
            this.stack.pop();
        }

        public void epilogue(InputStream inputStream) throws IOException {
            expect(MimeMultipart.class);
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = inputStream.read();
                if (read != -1) {
                    sb.append((char) read);
                } else {
                    return;
                }
            }
        }

        public void field(Field field) {
            expect(Part.class);
            try {
                String[] split = field.getRaw().toString().split(":", 2);
                ((Part) this.stack.peek()).addHeader(split[0], split[1].trim());
            } catch (MessagingException e) {
                throw new Error(e);
            }
        }

        public void preamble(InputStream inputStream) throws IOException {
            expect(MimeMultipart.class);
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = inputStream.read();
                if (read != -1) {
                    sb.append((char) read);
                } else {
                    try {
                        ((MimeMultipart) this.stack.peek()).preamble = sb.toString();
                        return;
                    } catch (MessagingException e) {
                        throw new Error(e);
                    }
                }
            }
        }

        public void raw(InputStream inputStream) throws IOException {
            throw new UnsupportedOperationException("Not supported");
        }

        public void startBodyPart() {
            expect(MimeMultipart.class);
            try {
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                ((MimeMultipart) this.stack.peek()).addBodyPart(mimeBodyPart);
                this.stack.push(mimeBodyPart);
            } catch (MessagingException e) {
                throw new Error(e);
            }
        }

        public void startHeader() {
            expect(Part.class);
        }

        public void startMessage() {
            if (this.stack.isEmpty()) {
                this.stack.push(MimeMessage.this);
                return;
            }
            expect(Part.class);
            try {
                MimeMessage mimeMessage = new MimeMessage();
                ((Part) this.stack.peek()).setBody(mimeMessage);
                this.stack.push(mimeMessage);
            } catch (MessagingException e) {
                throw new Error(e);
            }
        }

        public void startMultipart(BodyDescriptor bodyDescriptor) {
            expect(Part.class);
            Part part = (Part) this.stack.peek();
            try {
                MimeMultipart mimeMultipart = new MimeMultipart(part.getContentType());
                part.setBody(mimeMultipart);
                this.stack.push(mimeMultipart);
            } catch (MessagingException e) {
                throw new Error(e);
            }
        }
    }

    static {
        new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);
        Pattern.compile("^<?([^>]+)>?$");
        Pattern.compile("\r?\n");
    }

    private MimeHeader getMimeHeaders() {
        if (this.header == null) {
            this.header = new MimeHeader();
        }
        return this.header;
    }

    public void addHeader(String str, String str2) throws MessagingException {
        getMimeHeaders().fields.add(new MimeHeader.Field(str, str2));
    }

    public Body getBody() throws MessagingException {
        return this.body;
    }

    public String getContentType() throws MessagingException {
        String firstHeader = getFirstHeader("Content-Type");
        return firstHeader == null ? "text/plain" : firstHeader;
    }

    public Long getDuration() {
        try {
            String firstHeader = getFirstHeader("Content-Duration");
            if (firstHeader == null) {
                VvmLog.m46w("MimeMessage.getDuration", "message missing Content-Duration header");
                return null;
            }
            try {
                return Long.valueOf(firstHeader);
            } catch (NumberFormatException unused) {
                VvmLog.m46w("MimeMessage.getDuration", "cannot parse duration " + firstHeader);
                return null;
            }
        } catch (MessagingException e) {
            VvmLog.m44e("MimeMessage.getDuration", "cannot retrieve header: ", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String getFirstHeader(String str) throws MessagingException {
        String[] header2 = getMimeHeaders().getHeader(str);
        if (header2 == null) {
            return null;
        }
        return header2[0];
    }

    public Address[] getFrom() throws MessagingException {
        if (this.from == null) {
            String unfold = MimeUtility.unfold(getFirstHeader("From"));
            if (unfold == null || unfold.length() == 0) {
                unfold = MimeUtility.unfold(getFirstHeader("Sender"));
            }
            this.from = Address.parse(unfold);
        }
        return this.from;
    }

    public String[] getHeader(String str) throws MessagingException {
        return getMimeHeaders().getHeader(str);
    }

    public String getMessageId() throws MessagingException {
        String firstHeader = getFirstHeader("Message-ID");
        if (firstHeader != null || this.inhibitLocalMessageId) {
            return firstHeader;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("<");
        for (int i = 0; i < 24; i++) {
            outline13.append("0123456789abcdefghijklmnopqrstuv".charAt(random.nextInt() & 31));
        }
        outline13.append(".");
        outline13.append(Long.toString(System.currentTimeMillis()));
        outline13.append("@email.android.com>");
        String sb = outline13.toString();
        setMessageId(sb);
        return sb;
    }

    public String getMimeType() throws MessagingException {
        return MimeUtility.getHeaderParameter(getContentType(), (String) null);
    }

    public Date getSentDate() throws MessagingException {
        String str;
        String str2 = null;
        if (this.sentDate == null) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("Date: ");
                String unfold = MimeUtility.unfold(getFirstHeader("Date"));
                if (unfold == null) {
                    str = null;
                } else {
                    str = DecoderUtil.decodeEncodedWords(unfold, DecodeMonitor.STRICT);
                }
                sb.append(str);
                this.sentDate = ((DateTimeFieldImpl) ((DateTimeField) DefaultFieldParser.parse(sb.toString()))).getDate();
            } catch (Exception unused) {
                LogUtils.m55v("Email Log", "Message missing Date header", new Object[0]);
            }
        }
        if (this.sentDate == null) {
            try {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Date: ");
                String unfold2 = MimeUtility.unfold(getFirstHeader("Delivery-date"));
                if (unfold2 != null) {
                    str2 = DecoderUtil.decodeEncodedWords(unfold2, DecodeMonitor.STRICT);
                }
                sb2.append(str2);
                this.sentDate = ((DateTimeFieldImpl) ((DateTimeField) DefaultFieldParser.parse(sb2.toString()))).getDate();
            } catch (Exception unused2) {
                LogUtils.m55v("Email Log", "Message also missing Delivery-Date header", new Object[0]);
            }
        }
        return this.sentDate;
    }

    public int getSize() throws MessagingException {
        return this.size;
    }

    public void parse(InputStream inputStream) throws IOException, MessagingException, MimeException {
        getMimeHeaders().fields.clear();
        this.inhibitLocalMessageId = true;
        this.from = null;
        this.sentDate = null;
        this.body = null;
        MimeStreamParser mimeStreamParser = new MimeStreamParser();
        mimeStreamParser.setContentHandler(new MimeMessageBuilder());
        mimeStreamParser.parse(new EOLConvertingInputStream(inputStream));
    }

    public void setBody(Body body2) throws MessagingException {
        this.body = body2;
        if (body2 instanceof Multipart) {
            Multipart multipart = (Multipart) body2;
            multipart.setParent(this);
            setHeader("Content-Type", ((MimeMultipart) multipart).contentType);
            setHeader("MIME-Version", "1.0");
        }
    }

    public void setHeader(String str, String str2) throws MessagingException {
        getMimeHeaders().setHeader(str, str2);
    }

    public void setMessageId(String str) throws MessagingException {
        setHeader("Message-ID", str);
    }

    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream), 1024);
        getMessageId();
        getMimeHeaders().writeTo(outputStream);
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();
        Body body2 = this.body;
        if (body2 != null) {
            body2.writeTo(outputStream);
        }
    }
}
