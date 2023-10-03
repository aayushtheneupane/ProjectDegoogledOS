package com.android.voicemail.impl.mail.internet;

import com.android.voicemail.impl.mail.Body;
import com.android.voicemail.impl.mail.BodyPart;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.Multipart;
import com.android.voicemail.impl.mail.internet.MimeHeader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

public class MimeBodyPart extends BodyPart {
    protected Body body = null;
    protected MimeHeader header = new MimeHeader();
    protected int size;

    static {
        Pattern.compile("^<?([^>]+)>?$");
        Pattern.compile("\r?\n");
    }

    public void addHeader(String str, String str2) throws MessagingException {
        this.header.fields.add(new MimeHeader.Field(str, str2));
    }

    public Body getBody() throws MessagingException {
        return this.body;
    }

    public String getContentType() throws MessagingException {
        String firstHeader = this.header.getFirstHeader("Content-Type");
        return firstHeader == null ? "text/plain" : firstHeader;
    }

    public String[] getHeader(String str) throws MessagingException {
        return this.header.getHeader(str);
    }

    public String getMimeType() throws MessagingException {
        String firstHeader = this.header.getFirstHeader("Content-Type");
        if (firstHeader == null) {
            firstHeader = "text/plain";
        }
        return MimeUtility.getHeaderParameter(firstHeader, (String) null);
    }

    public int getSize() throws MessagingException {
        return this.size;
    }

    public void setBody(Body body2) throws MessagingException {
        this.body = body2;
        if (body2 instanceof Multipart) {
            Multipart multipart = (Multipart) body2;
            multipart.setParent(this);
            this.header.setHeader("Content-Type", ((MimeMultipart) multipart).contentType);
        }
    }

    public void setHeader(String str, String str2) throws MessagingException {
        this.header.setHeader(str, str2);
    }

    public void setSize(int i) {
        this.size = i;
    }

    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream), 1024);
        this.header.writeTo(outputStream);
        bufferedWriter.write("\r\n");
        bufferedWriter.flush();
        Body body2 = this.body;
        if (body2 != null) {
            body2.writeTo(outputStream);
        }
    }
}
