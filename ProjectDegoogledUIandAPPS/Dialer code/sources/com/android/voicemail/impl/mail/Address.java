package com.android.voicemail.impl.mail;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.apache.james.mime4j.codec.DecodeMonitor;
import org.apache.james.mime4j.codec.DecoderUtil;
import org.apache.james.mime4j.codec.EncoderUtil;

public class Address implements Parcelable {
    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Object createFromParcel(Parcel parcel) {
            return new Address(parcel);
        }

        public Object[] newArray(int i) {
            return new Address[i];
        }
    };
    private static final Pattern EMAIL_ADDRESS = Pattern.compile("\\A[^@]+@([[\\w][\\d]\\-\\(\\)\\[\\]]+\\.)+[[\\w][\\d]\\-\\(\\)\\[\\]]+\\z");
    private static final Address[] EMPTY_ADDRESS_ARRAY = new Address[0];
    private static final Pattern REMOVE_OPTIONAL_BRACKET = Pattern.compile("^<?([^>]+)>?$");
    private static final Pattern REMOVE_OPTIONAL_DQUOTE = Pattern.compile("^\"?([^\"]*)\"?$");
    private static final Pattern UNQUOTE = Pattern.compile("\\\\([\\\\\"])");
    private String address;
    private String personal;

    public Address(String str) {
        setAddress(str);
    }

    public static Address firstAddress(String str) {
        Address[] fromHeader = fromHeader(str);
        if (fromHeader.length > 0) {
            return fromHeader[0];
        }
        return null;
    }

    public static Address[] fromHeader(String str) {
        Address address2;
        if (str == null || str.length() == 0) {
            return EMPTY_ADDRESS_ARRAY;
        }
        if (str.indexOf(2) == -1 && str.indexOf(1) == -1) {
            return parse(str);
        }
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        int i = 0;
        int indexOf = str.indexOf(2);
        while (i < length) {
            int indexOf2 = str.indexOf(1, i);
            if (indexOf2 == -1) {
                indexOf2 = length;
            }
            if (indexOf == -1 || indexOf2 <= indexOf) {
                address2 = new Address(str.substring(i, indexOf2), (String) null);
            } else {
                address2 = new Address(str.substring(i, indexOf), str.substring(indexOf + 1, indexOf2));
                indexOf = str.indexOf(2, indexOf2 + 1);
            }
            arrayList.add(address2);
            i = indexOf2 + 1;
        }
        return (Address[]) arrayList.toArray(new Address[arrayList.size()]);
    }

    public static String fromHeaderToString(String str) {
        return toString(fromHeader(str));
    }

    public static boolean isAllValid(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }
        for (Rfc822Token address2 : Rfc822Tokenizer.tokenize(str)) {
            String address3 = address2.getAddress();
            if (!TextUtils.isEmpty(address3) && !isValidAddress(address3)) {
                return false;
            }
        }
        return true;
    }

    static boolean isValidAddress(String str) {
        return EMAIL_ADDRESS.matcher(str).find();
    }

    public static Address[] parse(String str) {
        if (str == null || str.length() == 0) {
            return EMPTY_ADDRESS_ARRAY;
        }
        Rfc822Token[] rfc822TokenArr = Rfc822Tokenizer.tokenize(str);
        ArrayList arrayList = new ArrayList();
        for (Rfc822Token rfc822Token : rfc822TokenArr) {
            String address2 = rfc822Token.getAddress();
            if (!TextUtils.isEmpty(address2) && isValidAddress(address2)) {
                String name = rfc822Token.getName();
                if (TextUtils.isEmpty(name)) {
                    name = null;
                }
                arrayList.add(new Address(address2, name));
            }
        }
        return (Address[]) arrayList.toArray(new Address[arrayList.size()]);
    }

    public static String parseToHeader(String str) {
        Address[] parse = parse(str);
        if (parse == null || parse.length == 0) {
            return null;
        }
        if (parse.length == 1) {
            return parse[0].toHeader();
        }
        StringBuilder sb = new StringBuilder(parse[0].toHeader());
        for (int i = 1; i < parse.length; i++) {
            sb.append(", ");
            sb.append(parse[i].toHeader());
        }
        return sb.toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Address) {
            return getAddress().equals(((Address) obj).getAddress());
        }
        return super.equals(obj);
    }

    public String getAddress() {
        return this.address;
    }

    public int hashCode() {
        return getAddress().hashCode();
    }

    public void setAddress(String str) {
        this.address = REMOVE_OPTIONAL_BRACKET.matcher(str).replaceAll("$1");
    }

    public void setPersonal(String str) {
        if (str != null) {
            str = DecoderUtil.decodeEncodedWords(UNQUOTE.matcher(REMOVE_OPTIONAL_DQUOTE.matcher(str).replaceAll("$1")).replaceAll("$1"), DecodeMonitor.STRICT);
            if (str.length() == 0) {
                str = null;
            }
        }
        this.personal = str;
    }

    public String toFriendly() {
        String str = this.personal;
        if (str == null || str.length() <= 0) {
            return this.address;
        }
        return this.personal;
    }

    public String toHeader() {
        if (this.personal == null) {
            return this.address;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(EncoderUtil.encodeAddressDisplayName(this.personal));
        sb.append(" <");
        return GeneratedOutlineSupport.outline12(sb, this.address, ">");
    }

    public String toString() {
        String str = this.personal;
        if (str == null || str.equals(this.address)) {
            return this.address;
        }
        if (this.personal.matches(".*[\\(\\)<>@,;:\\\\\".\\[\\]].*")) {
            StringBuilder sb = new StringBuilder();
            String str2 = this.personal;
            if (str2 == null) {
                str2 = null;
            } else if (!str2.matches("^\".*\"$")) {
                str2 = GeneratedOutlineSupport.outline9("\"", str2, "\"");
            }
            sb.append(str2);
            sb.append(" <");
            return GeneratedOutlineSupport.outline12(sb, this.address, ">");
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.personal);
        sb2.append(" <");
        return GeneratedOutlineSupport.outline12(sb2, this.address, ">");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.personal);
        parcel.writeString(this.address);
    }

    public Address(String str, String str2) {
        setPersonal(str2);
        setAddress(str);
    }

    public static String toFriendly(Address[] addressArr) {
        if (addressArr == null || addressArr.length == 0) {
            return null;
        }
        if (addressArr.length == 1) {
            return addressArr[0].toFriendly();
        }
        StringBuilder sb = new StringBuilder(addressArr[0].toFriendly());
        for (int i = 1; i < addressArr.length; i++) {
            sb.append(", ");
            sb.append(addressArr[i].toFriendly());
        }
        return sb.toString();
    }

    public Address(Parcel parcel) {
        setPersonal(parcel.readString());
        setAddress(parcel.readString());
    }

    public static String toString(Address[] addressArr) {
        if (addressArr == null || addressArr.length == 0) {
            return null;
        }
        if (addressArr.length == 1) {
            return addressArr[0].toString();
        }
        StringBuilder sb = new StringBuilder(addressArr[0].toString());
        for (int i = 1; i < addressArr.length; i++) {
            sb.append(",");
            sb.append(addressArr[i].toString().trim());
        }
        return sb.toString();
    }
}
