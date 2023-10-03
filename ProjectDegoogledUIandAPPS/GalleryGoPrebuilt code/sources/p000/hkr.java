package p000;

/* renamed from: hkr */
/* compiled from: PG */
final class hkr extends IllegalStateException {
    public hkr(String str) {
        super(m11655a((String) null, str));
    }

    public hkr(String str, String str2, Throwable th) {
        super(m11655a(str, str2), th);
    }

    /* renamed from: a */
    private static String m11655a(String str, String str2) {
        String str3 = str != null ? str.length() == 0 ? new String(": ") : ": ".concat(str) : "";
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 131 + String.valueOf(str3).length());
        sb.append("Starting new trace ");
        sb.append(str2);
        sb.append(" when already associated with a trace");
        sb.append(str3);
        sb.append(". For more help, see http://go/tiktok-tracing#fixing-duplicate-trace-issues");
        return sb.toString();
    }
}
