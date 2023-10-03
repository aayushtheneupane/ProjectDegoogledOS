package com.android.voicemail.impl.mail;

import android.content.Context;
import android.net.Network;
import android.net.TrafficStats;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.android.voicemail.impl.OmtpEvents;
import com.android.voicemail.impl.imap.ImapHelper;
import com.android.voicemail.impl.mail.utils.LogUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

public class MailTransport {
    private static final HostnameVerifier HOSTNAME_VERIFIER = HttpsURLConnection.getDefaultHostnameVerifier();
    private InetSocketAddress address;
    private final Context context;
    private final int flags;
    private final String host;
    private final ImapHelper imapHelper;

    /* renamed from: in */
    private BufferedInputStream f47in;
    private final Network network;
    private BufferedOutputStream out;
    private final int port;
    private Socket socket;
    private SocketCreator socketCreator;

    interface SocketCreator {
        Socket createSocket() throws MessagingException;
    }

    public MailTransport(Context context2, ImapHelper imapHelper2, Network network2, String str, int i, int i2) {
        this.context = context2;
        this.imapHelper = imapHelper2;
        this.network = network2;
        this.host = str;
        this.port = i;
        this.flags = i2;
    }

    private void verifyHostname(Socket socket2, String str) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) socket2;
        sSLSocket.startHandshake();
        SSLSession session = sSLSocket.getSession();
        if (session == null) {
            this.imapHelper.handleEvent(OmtpEvents.DATA_CANNOT_ESTABLISH_SSL_SESSION);
            throw new SSLException("Cannot verify SSL socket without session");
        } else if (!HOSTNAME_VERIFIER.verify(str, session)) {
            this.imapHelper.handleEvent(OmtpEvents.DATA_SSL_INVALID_HOST_NAME);
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Certificate hostname not useable for server: ");
            outline13.append(session.getPeerPrincipal());
            throw new SSLPeerUnverifiedException(outline13.toString());
        }
    }

    public boolean canTrustAllCertificates() {
        return (this.flags & 8) != 0;
    }

    public boolean canTrySslSecurity() {
        return (this.flags & 1) != 0;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r1 = this;
            java.io.BufferedInputStream r0 = r1.f47in     // Catch:{ Exception -> 0x0005 }
            r0.close()     // Catch:{ Exception -> 0x0005 }
        L_0x0005:
            java.io.BufferedOutputStream r0 = r1.out     // Catch:{ Exception -> 0x000a }
            r0.close()     // Catch:{ Exception -> 0x000a }
        L_0x000a:
            java.net.Socket r0 = r1.socket     // Catch:{ Exception -> 0x000f }
            r0.close()     // Catch:{ Exception -> 0x000f }
        L_0x000f:
            r0 = 0
            r1.f47in = r0
            r1.out = r0
            r1.socket = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.mail.MailTransport.close():void");
    }

    /* access modifiers changed from: protected */
    public Socket createSocket() throws MessagingException {
        SocketCreator socketCreator2 = this.socketCreator;
        if (socketCreator2 != null) {
            return socketCreator2.createSocket();
        }
        if (this.network == null) {
            LogUtils.m55v("MailTransport", "createSocket: network not specified", new Object[0]);
            return new Socket();
        }
        try {
            LogUtils.m55v("MailTransport", "createSocket: network specified", new Object[0]);
            TrafficStats.setThreadStatsTag(7);
            Socket createSocket = this.network.getSocketFactory().createSocket();
            TrafficStats.clearThreadStatsTag();
            return createSocket;
        } catch (IOException e) {
            LogUtils.m51d("MailTransport", e.toString(), new Object[0]);
            throw new MessagingException(1, e.toString());
        } catch (Throwable th) {
            TrafficStats.clearThreadStatsTag();
            throw th;
        }
    }

    public String getHost() {
        return this.host;
    }

    public InputStream getInputStream() {
        return this.f47in;
    }

    public OutputStream getOutputStream() {
        return this.out;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0008, code lost:
        r0 = r1.socket;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isOpen() {
        /*
            r1 = this;
            java.io.BufferedInputStream r0 = r1.f47in
            if (r0 == 0) goto L_0x001c
            java.io.BufferedOutputStream r0 = r1.out
            if (r0 == 0) goto L_0x001c
            java.net.Socket r0 = r1.socket
            if (r0 == 0) goto L_0x001c
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x001c
            java.net.Socket r1 = r1.socket
            boolean r1 = r1.isClosed()
            if (r1 != 0) goto L_0x001c
            r1 = 1
            goto L_0x001d
        L_0x001c:
            r1 = 0
        L_0x001d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.mail.MailTransport.isOpen():boolean");
    }

    public void open() throws MessagingException {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("*** IMAP open ");
        outline13.append(this.host);
        outline13.append(":");
        outline13.append(String.valueOf(this.port));
        LogUtils.m51d("MailTransport", outline13.toString(), new Object[0]);
        ArrayList arrayList = new ArrayList();
        Network network2 = this.network;
        if (network2 == null) {
            arrayList.add(new InetSocketAddress(this.host, this.port));
        } else {
            try {
                InetAddress[] allByName = network2.getAllByName(this.host);
                if (allByName.length != 0) {
                    for (InetAddress inetSocketAddress : allByName) {
                        arrayList.add(new InetSocketAddress(inetSocketAddress, this.port));
                    }
                } else {
                    throw new MessagingException(1, "Host name " + this.host + "cannot be resolved on designated network");
                }
            } catch (IOException e) {
                LogUtils.m51d("MailTransport", e.toString(), new Object[0]);
                this.imapHelper.handleEvent(OmtpEvents.DATA_CANNOT_RESOLVE_HOST_ON_NETWORK);
                throw new MessagingException(1, e.toString());
            }
        }
        while (arrayList.size() > 0) {
            this.socket = createSocket();
            try {
                this.address = (InetSocketAddress) arrayList.remove(0);
                this.socket.connect(this.address, 10000);
                if (canTrySslSecurity()) {
                    reopenTls();
                    return;
                }
                this.f47in = new BufferedInputStream(this.socket.getInputStream(), 1024);
                this.out = new BufferedOutputStream(this.socket.getOutputStream(), 512);
                this.socket.setSoTimeout(60000);
                return;
            } catch (IOException e2) {
                LogUtils.m51d("MailTransport", e2.toString(), new Object[0]);
                if (arrayList.size() != 0) {
                    try {
                        this.socket.close();
                        this.socket = null;
                    } catch (IOException e3) {
                        throw new MessagingException(1, e3.toString());
                    }
                } else {
                    this.imapHelper.handleEvent(OmtpEvents.DATA_ALL_SOCKET_CONNECTION_FAILED);
                    throw new MessagingException(1, e2.toString());
                }
            } catch (Throwable th) {
                try {
                    this.socket.close();
                    this.socket = null;
                    throw th;
                } catch (IOException e4) {
                    throw new MessagingException(1, e4.toString());
                }
            }
        }
    }

    public void reopenTls() throws MessagingException {
        try {
            LogUtils.m51d("MailTransport", "open: converting to TLS socket", new Object[0]);
            this.socket = HttpsURLConnection.getDefaultSSLSocketFactory().createSocket(this.socket, this.address.getHostName(), this.address.getPort(), true);
            if (!canTrustAllCertificates()) {
                verifyHostname(this.socket, this.host);
            }
            this.socket.setSoTimeout(60000);
            this.f47in = new BufferedInputStream(this.socket.getInputStream(), 1024);
            this.out = new BufferedOutputStream(this.socket.getOutputStream(), 512);
        } catch (SSLException e) {
            LogUtils.m51d("MailTransport", e.toString(), new Object[0]);
            throw new CertificateValidationException(e.getMessage(), e);
        } catch (IOException e2) {
            LogUtils.m51d("MailTransport", e2.toString(), new Object[0]);
            throw new MessagingException(1, e2.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void setSocketCreator(SocketCreator socketCreator2) {
        this.socketCreator = socketCreator2;
    }

    public void writeLine(String str, String str2) throws IOException {
        if (str2 != null) {
            LogUtils.m51d("MailTransport", GeneratedOutlineSupport.outline8(">>> ", str2), new Object[0]);
        } else {
            LogUtils.m51d("MailTransport", GeneratedOutlineSupport.outline8(">>> ", str), new Object[0]);
        }
        OutputStream outputStream = getOutputStream();
        outputStream.write(str.getBytes());
        outputStream.write(13);
        outputStream.write(10);
        outputStream.flush();
    }

    public MailTransport clone() {
        return new MailTransport(this.context, this.imapHelper, this.network, this.host, this.port, this.flags);
    }
}
