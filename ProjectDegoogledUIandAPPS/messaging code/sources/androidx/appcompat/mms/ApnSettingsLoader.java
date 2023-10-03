package androidx.appcompat.mms;

import java.util.List;

public interface ApnSettingsLoader {

    public interface Apn {
        String getMmsProxy();

        int getMmsProxyPort();

        String getMmsc();

        void setSuccess();
    }

    List get(String str);
}
