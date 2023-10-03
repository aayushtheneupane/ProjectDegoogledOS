package androidx.appcompat.mms;

import android.text.TextUtils;
import android.util.Log;
import com.google.i18n.phonenumbers.C1734f;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil$PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;

public class PhoneNumberHelper {
    static String getNumberNoCountryCode(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            C1734f instance = C1734f.getInstance();
            try {
                Phonenumber$PhoneNumber parse = instance.parse(str, str2);
                if (parse != null && instance.mo9484b(parse)) {
                    return instance.mo9481a(parse, PhoneNumberUtil$PhoneNumberFormat.NATIONAL).replaceAll("\\D", "");
                }
            } catch (NumberParseException e) {
                Log.w("MmsLib", "getNumberNoCountryCode: invalid number " + e);
            }
        }
        return str;
    }
}
