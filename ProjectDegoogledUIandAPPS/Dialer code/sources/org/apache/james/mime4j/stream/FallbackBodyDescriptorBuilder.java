package org.apache.james.mime4j.stream;

import org.apache.james.mime4j.codec.DecodeMonitor;

class FallbackBodyDescriptorBuilder implements BodyDescriptorBuilder {
    private String boundary;
    private String charset;
    private long contentLength;
    private String mediaType;
    private String mimeType;
    private final DecodeMonitor monitor;
    private final String parentMimeType;
    private String subType;
    private String transferEncoding;

    public FallbackBodyDescriptorBuilder() {
        this.parentMimeType = null;
        this.monitor = DecodeMonitor.SILENT;
        reset();
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x013a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.james.mime4j.stream.Field addField(org.apache.james.mime4j.stream.RawField r8) throws org.apache.james.mime4j.MimeException {
        /*
            r7 = this;
            java.lang.String r0 = r8.getName()
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r0 = r0.toLowerCase(r1)
            java.lang.String r1 = "content-transfer-encoding"
            boolean r1 = r0.equals(r1)
            r2 = 0
            if (r1 == 0) goto L_0x0031
            java.lang.String r1 = r7.transferEncoding
            if (r1 != 0) goto L_0x0031
            java.lang.String r8 = r8.getBody()
            if (r8 == 0) goto L_0x0146
            java.lang.String r8 = r8.trim()
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r8 = r8.toLowerCase(r0)
            int r0 = r8.length()
            if (r0 <= 0) goto L_0x0146
            r7.transferEncoding = r8
            goto L_0x0146
        L_0x0031:
            java.lang.String r1 = "content-length"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0080
            long r3 = r7.contentLength
            r5 = -1
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0080
            java.lang.String r8 = r8.getBody()
            if (r8 == 0) goto L_0x0146
            java.lang.String r8 = r8.trim()
            java.lang.String r0 = r8.trim()     // Catch:{ NumberFormatException -> 0x0057 }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0057 }
            r7.contentLength = r0     // Catch:{ NumberFormatException -> 0x0057 }
            goto L_0x0146
        L_0x0057:
            org.apache.james.mime4j.codec.DecodeMonitor r7 = r7.monitor
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid content length: "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "ignoring Content-Length header"
            boolean r7 = r7.warn(r0, r1)
            if (r7 != 0) goto L_0x0074
            goto L_0x0146
        L_0x0074:
            org.apache.james.mime4j.MimeException r7 = new org.apache.james.mime4j.MimeException
            java.lang.String r0 = "Invalid Content-Length header: "
            java.lang.String r8 = com.android.tools.p006r8.GeneratedOutlineSupport.outline8(r0, r8)
            r7.<init>((java.lang.String) r8)
            throw r7
        L_0x0080:
            java.lang.String r1 = "content-type"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0146
            java.lang.String r0 = r7.mimeType
            if (r0 != 0) goto L_0x0146
            org.apache.james.mime4j.stream.RawFieldParser r0 = org.apache.james.mime4j.stream.RawFieldParser.DEFAULT
            org.apache.james.mime4j.stream.RawBody r8 = r0.parseRawBody(r8)
            java.lang.String r0 = r8.getValue()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.List r8 = r8.getParams()
            java.util.Iterator r8 = r8.iterator()
        L_0x00a3:
            boolean r3 = r8.hasNext()
            if (r3 == 0) goto L_0x00c1
            java.lang.Object r3 = r8.next()
            org.apache.james.mime4j.stream.NameValuePair r3 = (org.apache.james.mime4j.stream.NameValuePair) r3
            java.lang.String r4 = r3.getName()
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r4 = r4.toLowerCase(r5)
            java.lang.String r3 = r3.getValue()
            r1.put(r4, r3)
            goto L_0x00a3
        L_0x00c1:
            if (r0 == 0) goto L_0x0101
            java.lang.String r8 = r0.toLowerCase()
            java.lang.String r8 = r8.trim()
            r0 = 47
            int r0 = r8.indexOf(r0)
            r3 = -1
            r4 = 1
            r5 = 0
            if (r0 == r3) goto L_0x00fb
            java.lang.String r3 = r8.substring(r5, r0)
            java.lang.String r3 = r3.trim()
            int r0 = r0 + r4
            java.lang.String r0 = r8.substring(r0)
            java.lang.String r0 = r0.trim()
            int r6 = r3.length()
            if (r6 <= 0) goto L_0x00fd
            int r6 = r0.length()
            if (r6 <= 0) goto L_0x00fd
            java.lang.String r8 = "/"
            java.lang.String r8 = com.android.tools.p006r8.GeneratedOutlineSupport.outline9(r3, r8, r0)
            r5 = r4
            goto L_0x00fd
        L_0x00fb:
            r0 = r2
            r3 = r0
        L_0x00fd:
            if (r5 != 0) goto L_0x0104
            r8 = r2
            goto L_0x0102
        L_0x0101:
            r8 = r0
        L_0x0102:
            r0 = r2
            r3 = r0
        L_0x0104:
            java.lang.String r4 = "boundary"
            java.lang.Object r4 = r1.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            if (r8 == 0) goto L_0x0124
            java.lang.String r5 = "multipart/"
            boolean r6 = r8.startsWith(r5)
            if (r6 == 0) goto L_0x0118
            if (r4 != 0) goto L_0x011e
        L_0x0118:
            boolean r5 = r8.startsWith(r5)
            if (r5 != 0) goto L_0x0124
        L_0x011e:
            r7.mimeType = r8
            r7.mediaType = r3
            r7.subType = r0
        L_0x0124:
            java.lang.String r8 = r7.mimeType
            boolean r8 = org.apache.james.mime4j.util.MimeUtil.isMultipart(r8)
            if (r8 == 0) goto L_0x012e
            r7.boundary = r4
        L_0x012e:
            java.lang.String r8 = "charset"
            java.lang.Object r8 = r1.get(r8)
            java.lang.String r8 = (java.lang.String) r8
            r7.charset = r2
            if (r8 == 0) goto L_0x0146
            java.lang.String r8 = r8.trim()
            int r0 = r8.length()
            if (r0 <= 0) goto L_0x0146
            r7.charset = r8
        L_0x0146:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder.addField(org.apache.james.mime4j.stream.RawField):org.apache.james.mime4j.stream.Field");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.james.mime4j.stream.BodyDescriptor build() {
        /*
            r14 = this;
            java.lang.String r0 = r14.mimeType
            java.lang.String r1 = r14.mediaType
            java.lang.String r2 = r14.subType
            java.lang.String r3 = r14.charset
            java.lang.String r4 = "text"
            if (r0 != 0) goto L_0x0025
            java.lang.String r0 = r14.parentMimeType
            java.lang.String r1 = "multipart/digest"
            boolean r0 = org.apache.james.mime4j.util.MimeUtil.isSameMimeType(r1, r0)
            if (r0 == 0) goto L_0x001d
            java.lang.String r0 = "message/rfc822"
            java.lang.String r1 = "message"
            java.lang.String r2 = "rfc822"
            goto L_0x0025
        L_0x001d:
            java.lang.String r0 = "text/plain"
            java.lang.String r2 = "plain"
            r6 = r0
            r8 = r2
            r7 = r4
            goto L_0x0028
        L_0x0025:
            r6 = r0
            r7 = r1
            r8 = r2
        L_0x0028:
            if (r3 != 0) goto L_0x0032
            boolean r0 = r4.equals(r7)
            if (r0 == 0) goto L_0x0032
            java.lang.String r3 = "us-ascii"
        L_0x0032:
            r10 = r3
            org.apache.james.mime4j.stream.BasicBodyDescriptor r0 = new org.apache.james.mime4j.stream.BasicBodyDescriptor
            java.lang.String r9 = r14.boundary
            java.lang.String r1 = r14.transferEncoding
            if (r1 == 0) goto L_0x003c
            goto L_0x003e
        L_0x003c:
            java.lang.String r1 = "7bit"
        L_0x003e:
            r11 = r1
            long r12 = r14.contentLength
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.stream.FallbackBodyDescriptorBuilder.build():org.apache.james.mime4j.stream.BodyDescriptor");
    }

    public BodyDescriptorBuilder newChild() {
        return new FallbackBodyDescriptorBuilder(this.mimeType, this.monitor);
    }

    public void reset() {
        this.mimeType = null;
        this.subType = null;
        this.mediaType = null;
        this.boundary = null;
        this.charset = null;
        this.transferEncoding = null;
        this.contentLength = -1;
    }

    public FallbackBodyDescriptorBuilder(String str, DecodeMonitor decodeMonitor) {
        this.parentMimeType = str;
        this.monitor = decodeMonitor == null ? DecodeMonitor.SILENT : decodeMonitor;
        reset();
    }
}
