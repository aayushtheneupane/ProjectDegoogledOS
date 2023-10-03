package p000;

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlException;
import java.security.AccessController;
import java.util.MissingResourceException;
import java.util.Properties;

/* renamed from: cm */
/* compiled from: PG */
public final class C0069cm {

    /* renamed from: a */
    private static final Properties f4662a = new Properties();

    static {
        InputStream inputStream;
        Class<C0071co> cls = C0071co.class;
        try {
            if (System.getSecurityManager() != null) {
                inputStream = (InputStream) AccessController.doPrivileged(new C0070cn(cls, "/android/icumessageformat/ICUConfig.properties"));
            } else {
                inputStream = cls.getResourceAsStream("/android/icumessageformat/ICUConfig.properties");
            }
            if (inputStream != null) {
                f4662a.load(inputStream);
            }
        } catch (MissingResourceException e) {
        } catch (IOException e2) {
        }
    }

    /* renamed from: a */
    public static String m4533a(String str, String str2) {
        String str3;
        if (System.getSecurityManager() != null) {
            try {
                str3 = (String) AccessController.doPrivileged(new C0068cl(str));
            } catch (AccessControlException e) {
                str3 = null;
            }
        } else {
            str3 = System.getProperty(str);
        }
        return str3 == null ? f4662a.getProperty(str, str2) : str3;
    }
}
