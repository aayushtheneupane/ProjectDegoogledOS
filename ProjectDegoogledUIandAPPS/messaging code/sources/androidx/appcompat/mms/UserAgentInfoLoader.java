package androidx.appcompat.mms;

public interface UserAgentInfoLoader {
    public static final String CONFIG_UA_PROF_URL = "uaProfUrl";
    public static final String CONFIG_USER_AGENT = "userAgent";

    String getUAProfUrl();

    String getUserAgent();
}
