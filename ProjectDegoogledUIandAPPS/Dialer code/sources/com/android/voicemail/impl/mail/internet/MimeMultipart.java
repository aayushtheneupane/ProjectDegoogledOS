package com.android.voicemail.impl.mail.internet;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.mail.BodyPart;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.Multipart;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MimeMultipart extends Multipart {
    protected String boundary;
    protected String contentType;
    protected String preamble;

    public MimeMultipart() throws MessagingException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("----");
        for (int i = 0; i < 30; i++) {
            stringBuffer.append(Integer.toString((int) (Math.random() * 35.0d), 36));
        }
        this.boundary = stringBuffer.toString().toUpperCase();
        setSubType("mixed");
    }

    public void setSubType(String str) throws MessagingException {
        this.contentType = String.format("multipart/%s; boundary=\"%s\"", new Object[]{str, this.boundary});
    }

    public void writeTo(OutputStream outputStream) throws IOException, MessagingException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream), 1024);
        if (this.preamble != null) {
            bufferedWriter.write(this.preamble + "\r\n");
        }
        int size = this.parts.size();
        for (int i = 0; i < size; i++) {
            BodyPart bodyPart = this.parts.get(i);
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("--");
            outline13.append(this.boundary);
            outline13.append("\r\n");
            bufferedWriter.write(outline13.toString());
            bufferedWriter.flush();
            ((MimeBodyPart) bodyPart).writeTo(outputStream);
            bufferedWriter.write("\r\n");
        }
        StringBuilder outline132 = GeneratedOutlineSupport.outline13("--");
        outline132.append(this.boundary);
        outline132.append("--\r\n");
        bufferedWriter.write(outline132.toString());
        bufferedWriter.flush();
    }

    public MimeMultipart(String str) throws MessagingException {
        this.contentType = str;
        try {
            String str2 = MimeUtility.getHeaderParameter(str, (String) null).split("/")[1];
            this.boundary = MimeUtility.getHeaderParameter(str, "boundary");
            if (this.boundary == null) {
                throw new MessagingException("MultiPart does not contain boundary: " + str);
            }
        } catch (Exception e) {
            throw new MessagingException(0, GeneratedOutlineSupport.outline9("Invalid MultiPart Content-Type; must contain subtype and boundary. (", str, ")"), e);
        }
    }
}
