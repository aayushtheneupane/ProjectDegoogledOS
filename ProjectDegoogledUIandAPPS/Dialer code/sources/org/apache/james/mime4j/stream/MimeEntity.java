package org.apache.james.mime4j.stream;

import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.codec.Base64InputStream;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.codec.QuotedPrintableInputStream;
import org.apache.james.mime4j.p012io.BufferedLineReaderInputStream;
import org.apache.james.mime4j.p012io.LimitedInputStream;
import org.apache.james.mime4j.p012io.LineNumberInputStream;
import org.apache.james.mime4j.p012io.LineNumberSource;
import org.apache.james.mime4j.p012io.LineReaderInputStream;
import org.apache.james.mime4j.p012io.LineReaderInputStreamAdaptor;
import org.apache.james.mime4j.p012io.MimeBoundaryInputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;
import org.apache.james.mime4j.util.MimeUtil;

class MimeEntity implements EntityStateMachine {
    private BodyDescriptor body;
    private final BodyDescriptorBuilder bodyDescBuilder;
    private final MimeConfig config;
    private MimeBoundaryInputStream currentMimePartStream;
    private LineReaderInputStreamAdaptor dataStream;
    private boolean endOfHeader = false;
    private final EntityState endState;
    private Field field;
    private final FieldBuilder fieldBuilder;
    private int headerCount = 0;
    private final BufferedLineReaderInputStream inbuffer;
    private int lineCount = 0;
    private final LineNumberSource lineSource;
    private final ByteArrayBuffer linebuf = new ByteArrayBuffer(64);
    private final DecodeMonitor monitor;
    private RecursionMode recursionMode;
    private EntityState state;
    private byte[] tmpbuf;

    MimeEntity(LineNumberSource lineNumberSource, InputStream inputStream, MimeConfig mimeConfig, EntityState entityState, EntityState entityState2, DecodeMonitor decodeMonitor, FieldBuilder fieldBuilder2, BodyDescriptorBuilder bodyDescriptorBuilder) {
        this.config = mimeConfig;
        this.state = entityState;
        this.endState = entityState2;
        this.monitor = decodeMonitor;
        this.fieldBuilder = fieldBuilder2;
        this.bodyDescBuilder = bodyDescriptorBuilder;
        this.lineSource = lineNumberSource;
        this.inbuffer = new BufferedLineReaderInputStream(inputStream, 4096, mimeConfig.getMaxLineLen());
        this.dataStream = new LineReaderInputStreamAdaptor(this.inbuffer, mimeConfig.getMaxLineLen());
    }

    private void advanceToBoundary() throws IOException {
        if (!this.dataStream.eof()) {
            if (this.tmpbuf == null) {
                this.tmpbuf = new byte[2048];
            }
            do {
            } while (getLimitedContentStream().read(this.tmpbuf) != -1);
        }
    }

    private void clearMimePartStream() {
        this.currentMimePartStream = null;
        this.dataStream = new LineReaderInputStreamAdaptor(this.inbuffer, this.config.getMaxLineLen());
    }

    private void createMimePartStream() throws MimeException, IOException {
        try {
            this.currentMimePartStream = new MimeBoundaryInputStream(this.inbuffer, ((BasicBodyDescriptor) this.body).getBoundary(), this.config.isStrictParsing());
            this.dataStream = new LineReaderInputStreamAdaptor(this.currentMimePartStream, this.config.getMaxLineLen());
        } catch (IllegalArgumentException e) {
            throw new MimeException(e.getMessage(), e);
        }
    }

    private InputStream decodedStream(InputStream inputStream) {
        String transferEncoding = ((BasicBodyDescriptor) this.body).getTransferEncoding();
        if (MimeUtil.isBase64Encoding(transferEncoding)) {
            return new Base64InputStream(inputStream, this.monitor);
        }
        return "quoted-printable".equalsIgnoreCase(transferEncoding) ? new QuotedPrintableInputStream(inputStream, this.monitor) : inputStream;
    }

    private LineReaderInputStream getDataStream() {
        return this.dataStream;
    }

    private InputStream getLimitedContentStream() {
        long maxContentLen = this.config.getMaxContentLen();
        if (maxContentLen >= 0) {
            return new LimitedInputStream(this.dataStream, maxContentLen);
        }
        return this.dataStream;
    }

    private EntityStateMachine nextMimeEntity(EntityState entityState, EntityState entityState2, InputStream inputStream) {
        if (this.recursionMode == RecursionMode.M_RAW) {
            return new RawEntity(inputStream);
        }
        MimeEntity mimeEntity = new MimeEntity(this.lineSource, inputStream, this.config, entityState, entityState2, this.monitor, this.fieldBuilder, ((FallbackBodyDescriptorBuilder) this.bodyDescBuilder).newChild());
        mimeEntity.setRecursionMode(this.recursionMode);
        return mimeEntity;
    }

    public static final String stateToString(EntityState entityState) {
        switch (entityState.ordinal()) {
            case 0:
                return "Start message";
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE:
                return "End message";
            case 2:
                return "Raw entity";
            case 3:
                return "Start header";
            case 4:
                return "Field";
            case 5:
                return "End header";
            case 6:
                return "Start multipart";
            case 7:
                return "End multipart";
            case 8:
                return "Preamble";
            case 9:
                return "Epilogue";
            case 10:
                return "Start bodypart";
            case 11:
                return "End bodypart";
            case 12:
                return "Body";
            case 13:
                return "End of stream";
            default:
                return "Unknown";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01b3, code lost:
        monitor(org.apache.james.mime4j.stream.Event.INVALID_HEADER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01be, code lost:
        if (r8.config.isMalformedHeaderStartsBody() != false) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01c0, code lost:
        r0 = getDataStream();
        r1 = ((org.apache.james.mime4j.stream.DefaultFieldBuilder) r8.fieldBuilder).getRaw();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01d4, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01d5, code lost:
        if (r1 != false) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01d7, code lost:
        r0 = org.apache.james.mime4j.stream.EntityState.T_FIELD;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01da, code lost:
        r0 = org.apache.james.mime4j.stream.EntityState.T_END_HEADER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01dc, code lost:
        r8.state = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01e6, code lost:
        throw new org.apache.james.mime4j.stream.MimeParseEventException(org.apache.james.mime4j.stream.Event.INVALID_HEADER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01e7, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01ed, code lost:
        throw new org.apache.james.mime4j.MimeException((java.lang.Throwable) r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01f3, code lost:
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        advanceToBoundary();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        if (r8.currentMimePartStream.eof() == false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        if (r8.currentMimePartStream.isLastPart() != false) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0058, code lost:
        monitor(org.apache.james.mime4j.stream.Event.MIME_BODY_PREMATURE_END);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0064, code lost:
        if (r8.currentMimePartStream.isLastPart() != false) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0066, code lost:
        clearMimePartStream();
        createMimePartStream();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0076, code lost:
        return nextMimeEntity(org.apache.james.mime4j.stream.EntityState.T_START_BODYPART, org.apache.james.mime4j.stream.EntityState.T_END_BODYPART, r8.currentMimePartStream);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0077, code lost:
        r0 = r8.currentMimePartStream.isFullyConsumed();
        clearMimePartStream();
        r8.state = org.apache.james.mime4j.stream.EntityState.T_EPILOGUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0084, code lost:
        if (r0 != false) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0088, code lost:
        r8.state = org.apache.james.mime4j.stream.EntityState.T_END_MULTIPART;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00f4, code lost:
        r0 = r8.config.getMaxHeaderCount();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00fc, code lost:
        if (r8.endOfHeader == false) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0100, code lost:
        if (r0 <= 0) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0104, code lost:
        if (r8.headerCount >= r0) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x010e, code lost:
        throw new org.apache.james.mime4j.p012io.MaxHeaderLimitException("Maximum header limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x010f, code lost:
        r8.headerCount++;
        ((org.apache.james.mime4j.stream.DefaultFieldBuilder) r8.fieldBuilder).reset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011d, code lost:
        if (r8.endOfHeader != false) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x011f, code lost:
        r3 = getDataStream();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0129, code lost:
        if (r8.linebuf.length() <= 0) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        ((org.apache.james.mime4j.stream.DefaultFieldBuilder) r8.fieldBuilder).append(r8.linebuf);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0134, code lost:
        r8.linebuf.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0140, code lost:
        if (r3.readLine(r8.linebuf) != -1) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0142, code lost:
        monitor(org.apache.james.mime4j.stream.Event.HEADERS_PREMATURE_END);
        r8.endOfHeader = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x014a, code lost:
        r4 = r8.linebuf.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0150, code lost:
        if (r4 <= 0) goto L_0x015f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0152, code lost:
        r6 = r4 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x015c, code lost:
        if (r8.linebuf.byteAt(r6) != 10) goto L_0x015f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x015e, code lost:
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x015f, code lost:
        if (r4 <= 0) goto L_0x016e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0161, code lost:
        r6 = r4 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x016b, code lost:
        if (r8.linebuf.byteAt(r6) != 13) goto L_0x016e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x016d, code lost:
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x016e, code lost:
        if (r4 != 0) goto L_0x0173;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0170, code lost:
        r8.endOfHeader = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0173, code lost:
        r8.lineCount++;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x017a, code lost:
        if (r8.lineCount <= 1) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x017c, code lost:
        r4 = r8.linebuf.byteAt(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0184, code lost:
        if (r4 == 32) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0188, code lost:
        if (r4 == 9) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        r3 = ((org.apache.james.mime4j.stream.DefaultFieldBuilder) r8.fieldBuilder).build();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0192, code lost:
        if (r3 != null) goto L_0x0196;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01a2, code lost:
        if (r3.getDelimiterIdx() == r3.getName().length()) goto L_0x01a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01a4, code lost:
        monitor(org.apache.james.mime4j.stream.Event.OBSOLETE_HEADER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
        ((org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder) r8.bodyDescBuilder).addField(r3);
        r8.field = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.james.mime4j.stream.EntityStateMachine advance() throws java.io.IOException, org.apache.james.mime4j.MimeException {
        /*
            r8 = this;
            org.apache.james.mime4j.stream.EntityState r0 = r8.state
            int r0 = r0.ordinal()
            if (r0 == 0) goto L_0x0212
            r1 = 12
            if (r0 == r1) goto L_0x020d
            r1 = 1
            r2 = 0
            switch(r0) {
                case 3: goto L_0x00ed;
                case 4: goto L_0x00f4;
                case 5: goto L_0x008e;
                case 6: goto L_0x0023;
                case 7: goto L_0x020d;
                case 8: goto L_0x0045;
                case 9: goto L_0x0088;
                case 10: goto L_0x001d;
                default: goto L_0x0011;
            }
        L_0x0011:
            org.apache.james.mime4j.stream.EntityState r0 = r8.state
            org.apache.james.mime4j.stream.EntityState r1 = r8.endState
            if (r0 != r1) goto L_0x01f4
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_END_OF_STREAM
            r8.state = r0
            goto L_0x0216
        L_0x001d:
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_START_HEADER
            r8.state = r0
            goto L_0x0216
        L_0x0023:
            org.apache.james.mime4j.io.LineReaderInputStreamAdaptor r0 = r8.dataStream
            boolean r0 = r0.isUsed()
            if (r0 == 0) goto L_0x0034
            r8.advanceToBoundary()
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_END_MULTIPART
            r8.state = r0
            goto L_0x0216
        L_0x0034:
            r8.createMimePartStream()
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_PREAMBLE
            r8.state = r0
            org.apache.james.mime4j.io.MimeBoundaryInputStream r0 = r8.currentMimePartStream
            boolean r0 = r0.isEmptyStream()
            if (r0 != 0) goto L_0x0045
            goto L_0x0216
        L_0x0045:
            r8.advanceToBoundary()
            org.apache.james.mime4j.io.MimeBoundaryInputStream r0 = r8.currentMimePartStream
            boolean r0 = r0.eof()
            if (r0 == 0) goto L_0x005e
            org.apache.james.mime4j.io.MimeBoundaryInputStream r0 = r8.currentMimePartStream
            boolean r0 = r0.isLastPart()
            if (r0 != 0) goto L_0x005e
            org.apache.james.mime4j.stream.Event r0 = org.apache.james.mime4j.stream.Event.MIME_BODY_PREMATURE_END
            r8.monitor(r0)
            goto L_0x0077
        L_0x005e:
            org.apache.james.mime4j.io.MimeBoundaryInputStream r0 = r8.currentMimePartStream
            boolean r0 = r0.isLastPart()
            if (r0 != 0) goto L_0x0077
            r8.clearMimePartStream()
            r8.createMimePartStream()
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_START_BODYPART
            org.apache.james.mime4j.stream.EntityState r1 = org.apache.james.mime4j.stream.EntityState.T_END_BODYPART
            org.apache.james.mime4j.io.MimeBoundaryInputStream r2 = r8.currentMimePartStream
            org.apache.james.mime4j.stream.EntityStateMachine r8 = r8.nextMimeEntity(r0, r1, r2)
            return r8
        L_0x0077:
            org.apache.james.mime4j.io.MimeBoundaryInputStream r0 = r8.currentMimePartStream
            boolean r0 = r0.isFullyConsumed()
            r8.clearMimePartStream()
            org.apache.james.mime4j.stream.EntityState r1 = org.apache.james.mime4j.stream.EntityState.T_EPILOGUE
            r8.state = r1
            if (r0 != 0) goto L_0x0088
            goto L_0x0216
        L_0x0088:
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_END_MULTIPART
            r8.state = r0
            goto L_0x0216
        L_0x008e:
            org.apache.james.mime4j.stream.BodyDescriptorBuilder r0 = r8.bodyDescBuilder
            org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder r0 = (org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder) r0
            org.apache.james.mime4j.stream.BodyDescriptor r0 = r0.build()
            r8.body = r0
            org.apache.james.mime4j.stream.BodyDescriptor r0 = r8.body
            org.apache.james.mime4j.stream.BasicBodyDescriptor r0 = (org.apache.james.mime4j.stream.BasicBodyDescriptor) r0
            java.lang.String r0 = r0.getMimeType()
            org.apache.james.mime4j.stream.RecursionMode r3 = r8.recursionMode
            org.apache.james.mime4j.stream.RecursionMode r4 = org.apache.james.mime4j.stream.RecursionMode.M_FLAT
            if (r3 != r4) goto L_0x00ac
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_BODY
            r8.state = r0
            goto L_0x0216
        L_0x00ac:
            boolean r3 = org.apache.james.mime4j.util.MimeUtil.isMultipart(r0)
            if (r3 == 0) goto L_0x00bb
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_START_MULTIPART
            r8.state = r0
            r8.clearMimePartStream()
            goto L_0x0216
        L_0x00bb:
            org.apache.james.mime4j.stream.RecursionMode r3 = r8.recursionMode
            org.apache.james.mime4j.stream.RecursionMode r4 = org.apache.james.mime4j.stream.RecursionMode.M_NO_RECURSE
            if (r3 == r4) goto L_0x00e7
            if (r0 == 0) goto L_0x00cc
            java.lang.String r3 = "message/rfc822"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L_0x00cc
            goto L_0x00cd
        L_0x00cc:
            r1 = r2
        L_0x00cd:
            if (r1 == 0) goto L_0x00e7
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_BODY
            r8.state = r0
            org.apache.james.mime4j.io.MimeBoundaryInputStream r0 = r8.currentMimePartStream
            if (r0 == 0) goto L_0x00d8
            goto L_0x00da
        L_0x00d8:
            org.apache.james.mime4j.io.BufferedLineReaderInputStream r0 = r8.inbuffer
        L_0x00da:
            java.io.InputStream r0 = r8.decodedStream(r0)
            org.apache.james.mime4j.stream.EntityState r1 = org.apache.james.mime4j.stream.EntityState.T_START_MESSAGE
            org.apache.james.mime4j.stream.EntityState r2 = org.apache.james.mime4j.stream.EntityState.T_END_MESSAGE
            org.apache.james.mime4j.stream.EntityStateMachine r8 = r8.nextMimeEntity(r1, r2, r0)
            return r8
        L_0x00e7:
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_BODY
            r8.state = r0
            goto L_0x0216
        L_0x00ed:
            org.apache.james.mime4j.stream.BodyDescriptorBuilder r0 = r8.bodyDescBuilder
            org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder r0 = (org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder) r0
            r0.reset()
        L_0x00f4:
            org.apache.james.mime4j.stream.MimeConfig r0 = r8.config
            int r0 = r0.getMaxHeaderCount()
        L_0x00fa:
            boolean r3 = r8.endOfHeader
            if (r3 == 0) goto L_0x0100
            goto L_0x01d4
        L_0x0100:
            if (r0 <= 0) goto L_0x010f
            int r3 = r8.headerCount
            if (r3 >= r0) goto L_0x0107
            goto L_0x010f
        L_0x0107:
            org.apache.james.mime4j.io.MaxHeaderLimitException r8 = new org.apache.james.mime4j.io.MaxHeaderLimitException
            java.lang.String r0 = "Maximum header limit exceeded"
            r8.<init>(r0)
            throw r8
        L_0x010f:
            int r3 = r8.headerCount
            int r3 = r3 + r1
            r8.headerCount = r3
            org.apache.james.mime4j.stream.FieldBuilder r3 = r8.fieldBuilder
            org.apache.james.mime4j.stream.DefaultFieldBuilder r3 = (org.apache.james.mime4j.stream.DefaultFieldBuilder) r3
            r3.reset()
            boolean r3 = r8.endOfHeader
            if (r3 != 0) goto L_0x01ee
            org.apache.james.mime4j.io.LineReaderInputStream r3 = r8.getDataStream()
        L_0x0123:
            org.apache.james.mime4j.util.ByteArrayBuffer r4 = r8.linebuf     // Catch:{ MaxLineLimitException -> 0x01e7 }
            int r4 = r4.length()     // Catch:{ MaxLineLimitException -> 0x01e7 }
            if (r4 <= 0) goto L_0x0134
            org.apache.james.mime4j.stream.FieldBuilder r4 = r8.fieldBuilder     // Catch:{ MaxLineLimitException -> 0x01e7 }
            org.apache.james.mime4j.util.ByteArrayBuffer r5 = r8.linebuf     // Catch:{ MaxLineLimitException -> 0x01e7 }
            org.apache.james.mime4j.stream.DefaultFieldBuilder r4 = (org.apache.james.mime4j.stream.DefaultFieldBuilder) r4
            r4.append(r5)     // Catch:{ MaxLineLimitException -> 0x01e7 }
        L_0x0134:
            org.apache.james.mime4j.util.ByteArrayBuffer r4 = r8.linebuf     // Catch:{ MaxLineLimitException -> 0x01e7 }
            r4.clear()     // Catch:{ MaxLineLimitException -> 0x01e7 }
            org.apache.james.mime4j.util.ByteArrayBuffer r4 = r8.linebuf     // Catch:{ MaxLineLimitException -> 0x01e7 }
            int r4 = r3.readLine(r4)     // Catch:{ MaxLineLimitException -> 0x01e7 }
            r5 = -1
            if (r4 != r5) goto L_0x014a
            org.apache.james.mime4j.stream.Event r3 = org.apache.james.mime4j.stream.Event.HEADERS_PREMATURE_END     // Catch:{ MaxLineLimitException -> 0x01e7 }
            r8.monitor(r3)     // Catch:{ MaxLineLimitException -> 0x01e7 }
            r8.endOfHeader = r1     // Catch:{ MaxLineLimitException -> 0x01e7 }
            goto L_0x018a
        L_0x014a:
            org.apache.james.mime4j.util.ByteArrayBuffer r4 = r8.linebuf     // Catch:{ MaxLineLimitException -> 0x01e7 }
            int r4 = r4.length()     // Catch:{ MaxLineLimitException -> 0x01e7 }
            if (r4 <= 0) goto L_0x015f
            org.apache.james.mime4j.util.ByteArrayBuffer r5 = r8.linebuf     // Catch:{ MaxLineLimitException -> 0x01e7 }
            int r6 = r4 + -1
            byte r5 = r5.byteAt(r6)     // Catch:{ MaxLineLimitException -> 0x01e7 }
            r7 = 10
            if (r5 != r7) goto L_0x015f
            r4 = r6
        L_0x015f:
            if (r4 <= 0) goto L_0x016e
            org.apache.james.mime4j.util.ByteArrayBuffer r5 = r8.linebuf     // Catch:{ MaxLineLimitException -> 0x01e7 }
            int r6 = r4 + -1
            byte r5 = r5.byteAt(r6)     // Catch:{ MaxLineLimitException -> 0x01e7 }
            r7 = 13
            if (r5 != r7) goto L_0x016e
            r4 = r6
        L_0x016e:
            if (r4 != 0) goto L_0x0173
            r8.endOfHeader = r1     // Catch:{ MaxLineLimitException -> 0x01e7 }
            goto L_0x018a
        L_0x0173:
            int r4 = r8.lineCount     // Catch:{ MaxLineLimitException -> 0x01e7 }
            int r4 = r4 + r1
            r8.lineCount = r4     // Catch:{ MaxLineLimitException -> 0x01e7 }
            int r4 = r8.lineCount     // Catch:{ MaxLineLimitException -> 0x01e7 }
            if (r4 <= r1) goto L_0x0123
            org.apache.james.mime4j.util.ByteArrayBuffer r4 = r8.linebuf     // Catch:{ MaxLineLimitException -> 0x01e7 }
            byte r4 = r4.byteAt(r2)     // Catch:{ MaxLineLimitException -> 0x01e7 }
            r5 = 32
            if (r4 == r5) goto L_0x0123
            r5 = 9
            if (r4 == r5) goto L_0x0123
        L_0x018a:
            org.apache.james.mime4j.stream.FieldBuilder r3 = r8.fieldBuilder     // Catch:{ MimeException -> 0x01b3 }
            org.apache.james.mime4j.stream.DefaultFieldBuilder r3 = (org.apache.james.mime4j.stream.DefaultFieldBuilder) r3
            org.apache.james.mime4j.stream.RawField r3 = r3.build()     // Catch:{ MimeException -> 0x01b3 }
            if (r3 != 0) goto L_0x0196
            goto L_0x00fa
        L_0x0196:
            int r4 = r3.getDelimiterIdx()     // Catch:{ MimeException -> 0x01b3 }
            java.lang.String r5 = r3.getName()     // Catch:{ MimeException -> 0x01b3 }
            int r5 = r5.length()     // Catch:{ MimeException -> 0x01b3 }
            if (r4 == r5) goto L_0x01a9
            org.apache.james.mime4j.stream.Event r4 = org.apache.james.mime4j.stream.Event.OBSOLETE_HEADER     // Catch:{ MimeException -> 0x01b3 }
            r8.monitor(r4)     // Catch:{ MimeException -> 0x01b3 }
        L_0x01a9:
            org.apache.james.mime4j.stream.BodyDescriptorBuilder r4 = r8.bodyDescBuilder     // Catch:{ MimeException -> 0x01b3 }
            org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder r4 = (org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder) r4
            r4.addField(r3)     // Catch:{ MimeException -> 0x01b3 }
            r8.field = r3     // Catch:{ MimeException -> 0x01b3 }
            goto L_0x01d5
        L_0x01b3:
            org.apache.james.mime4j.stream.Event r3 = org.apache.james.mime4j.stream.Event.INVALID_HEADER
            r8.monitor(r3)
            org.apache.james.mime4j.stream.MimeConfig r3 = r8.config
            boolean r3 = r3.isMalformedHeaderStartsBody()
            if (r3 == 0) goto L_0x00fa
            org.apache.james.mime4j.io.LineReaderInputStream r0 = r8.getDataStream()
            org.apache.james.mime4j.stream.FieldBuilder r1 = r8.fieldBuilder
            org.apache.james.mime4j.stream.DefaultFieldBuilder r1 = (org.apache.james.mime4j.stream.DefaultFieldBuilder) r1
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r1.getRaw()
            if (r1 == 0) goto L_0x01df
            boolean r0 = r0.unread(r1)
            if (r0 == 0) goto L_0x01df
        L_0x01d4:
            r1 = r2
        L_0x01d5:
            if (r1 == 0) goto L_0x01da
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_FIELD
            goto L_0x01dc
        L_0x01da:
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_END_HEADER
        L_0x01dc:
            r8.state = r0
            goto L_0x0216
        L_0x01df:
            org.apache.james.mime4j.stream.MimeParseEventException r8 = new org.apache.james.mime4j.stream.MimeParseEventException
            org.apache.james.mime4j.stream.Event r0 = org.apache.james.mime4j.stream.Event.INVALID_HEADER
            r8.<init>(r0)
            throw r8
        L_0x01e7:
            r8 = move-exception
            org.apache.james.mime4j.MimeException r0 = new org.apache.james.mime4j.MimeException
            r0.<init>((java.lang.Throwable) r8)
            throw r0
        L_0x01ee:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>()
            throw r8
        L_0x01f4:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Invalid state: "
            java.lang.StringBuilder r1 = com.android.tools.p006r8.GeneratedOutlineSupport.outline13(r1)
            org.apache.james.mime4j.stream.EntityState r8 = r8.state
            java.lang.String r8 = stateToString(r8)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            throw r0
        L_0x020d:
            org.apache.james.mime4j.stream.EntityState r0 = r8.endState
            r8.state = r0
            goto L_0x0216
        L_0x0212:
            org.apache.james.mime4j.stream.EntityState r0 = org.apache.james.mime4j.stream.EntityState.T_START_HEADER
            r8.state = r0
        L_0x0216:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.stream.MimeEntity.advance():org.apache.james.mime4j.stream.EntityStateMachine");
    }

    public BodyDescriptor getBodyDescriptor() {
        int ordinal = this.state.ordinal();
        if (ordinal == 6 || ordinal == 8 || ordinal == 9 || ordinal == 12 || ordinal == 13) {
            return this.body;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid state :");
        outline13.append(stateToString(this.state));
        throw new IllegalStateException(outline13.toString());
    }

    public InputStream getContentStream() {
        int ordinal = this.state.ordinal();
        if (ordinal == 6 || ordinal == 12 || ordinal == 8 || ordinal == 9) {
            return getLimitedContentStream();
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid state: ");
        outline13.append(stateToString(this.state));
        throw new IllegalStateException(outline13.toString());
    }

    public InputStream getDecodedContentStream() throws IllegalStateException {
        int ordinal = this.state.ordinal();
        if (ordinal == 6 || ordinal == 12 || ordinal == 8 || ordinal == 9) {
            return decodedStream(getLimitedContentStream());
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid state: ");
        outline13.append(stateToString(this.state));
        throw new IllegalStateException(outline13.toString());
    }

    public Field getField() {
        if (this.state.ordinal() == 4) {
            return this.field;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid state :");
        outline13.append(stateToString(this.state));
        throw new IllegalStateException(outline13.toString());
    }

    public EntityState getState() {
        return this.state;
    }

    /* access modifiers changed from: protected */
    public void monitor(Event event) throws MimeException, IOException {
        String str;
        int i;
        if (this.monitor.isListening()) {
            if (event == null) {
                str = "Event is unexpectedly null.";
            } else {
                str = event.toString();
            }
            LineNumberSource lineNumberSource = this.lineSource;
            if (lineNumberSource == null) {
                i = -1;
            } else {
                i = ((LineNumberInputStream) lineNumberSource).getLineNumber();
            }
            if (i > 0) {
                str = "Line " + i + ": " + str;
            }
            if (this.monitor.warn(str, "ignoring")) {
                throw new MimeParseEventException(event);
            }
        }
    }

    public void setRecursionMode(RecursionMode recursionMode2) {
        this.recursionMode = recursionMode2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MimeEntity.class.getName());
        sb.append(" [");
        sb.append(stateToString(this.state));
        sb.append("][");
        sb.append(((BasicBodyDescriptor) this.body).getMimeType());
        sb.append("][");
        return GeneratedOutlineSupport.outline12(sb, ((BasicBodyDescriptor) this.body).getBoundary(), "]");
    }
}
