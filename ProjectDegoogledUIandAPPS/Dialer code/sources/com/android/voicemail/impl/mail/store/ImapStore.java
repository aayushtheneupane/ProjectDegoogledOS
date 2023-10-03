package com.android.voicemail.impl.mail.store;

import android.content.Context;
import android.net.Network;
import com.android.voicemail.impl.imap.ImapHelper;
import com.android.voicemail.impl.mail.MailTransport;
import com.android.voicemail.impl.mail.Message;
import com.android.voicemail.impl.mail.MessagingException;
import com.android.voicemail.impl.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;

public class ImapStore {
    private ImapConnection connection;
    private final Context context;
    private final ImapHelper helper;
    private final String password;
    private final MailTransport transport;
    private final String username;

    static class ImapException extends MessagingException {
        private static final long serialVersionUID = 1;
        private final String alertText;
        private final String responseCode;
        private final String status;
        private final String statusMessage;

        public ImapException(String str, String str2, String str3, String str4, String str5) {
            super(0, str, (Throwable) null);
            this.status = str2;
            this.statusMessage = str3;
            this.alertText = str4;
            this.responseCode = str5;
        }

        public String getAlertText() {
            return this.alertText;
        }

        public String getStatus() {
            return this.status;
        }

        public String getStatusMessage() {
            return this.statusMessage;
        }
    }

    static class ImapMessage extends MimeMessage {
        ImapMessage(String str, ImapFolder imapFolder) {
            this.uid = str;
        }

        public void parse(InputStream inputStream) throws IOException, MessagingException, MimeException {
            super.parse(inputStream);
        }

        public void setFlagInternal(String str, boolean z) throws MessagingException {
            super.setFlag(str, z);
        }

        public void setSize(int i) {
            this.size = i;
        }
    }

    public ImapStore(Context context2, ImapHelper imapHelper, String str, String str2, int i, String str3, int i2, Network network) {
        this.context = context2;
        this.helper = imapHelper;
        this.username = str;
        this.password = str2;
        this.transport = new MailTransport(context2, this.helper, network, str3, i, i2);
    }

    static String joinMessageUids(Message[] messageArr) {
        StringBuilder sb = new StringBuilder();
        int length = messageArr.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            Message message = messageArr[i];
            if (z) {
                sb.append(',');
            }
            sb.append(message.getUid());
            i++;
            z = true;
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public MailTransport cloneTransport() {
        return this.transport.clone();
    }

    public void closeConnection() {
        ImapConnection imapConnection = this.connection;
        if (imapConnection != null) {
            imapConnection.close();
            this.connection = null;
        }
    }

    public ImapConnection getConnection() {
        if (this.connection == null) {
            this.connection = new ImapConnection(this);
        }
        return this.connection;
    }

    public Context getContext() {
        return this.context;
    }

    public ImapHelper getImapHelper() {
        return this.helper;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }
}
