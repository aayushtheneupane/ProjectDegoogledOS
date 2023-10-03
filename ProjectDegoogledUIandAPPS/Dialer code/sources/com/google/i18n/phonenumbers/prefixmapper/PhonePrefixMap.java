package com.google.i18n.phonenumbers.prefixmapper;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.SortedSet;
import java.util.logging.Logger;

public class PhonePrefixMap implements Externalizable {
    private PhonePrefixMapStorageStrategy phonePrefixMapStorage;
    private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    static {
        Logger.getLogger(PhonePrefixMap.class.getName());
    }

    /* access modifiers changed from: package-private */
    public String lookup(long j) {
        PhonePrefixMapStorageStrategy phonePrefixMapStorageStrategy = this.phonePrefixMapStorage;
        int i = phonePrefixMapStorageStrategy.numOfEntries;
        if (i == 0) {
            return null;
        }
        int i2 = i - 1;
        SortedSet sortedSet = phonePrefixMapStorageStrategy.possibleLengths;
        while (sortedSet.size() > 0) {
            Integer num = (Integer) sortedSet.last();
            String valueOf = String.valueOf(j);
            int i3 = 0;
            if (valueOf.length() > num.intValue()) {
                j = Long.parseLong(valueOf.substring(0, num.intValue()));
            }
            int i4 = 0;
            while (i3 <= i2) {
                i4 = (i3 + i2) >>> 1;
                int i5 = (((long) this.phonePrefixMapStorage.getPrefix(i4)) > j ? 1 : (((long) this.phonePrefixMapStorage.getPrefix(i4)) == j ? 0 : -1));
                if (i5 == 0) {
                    break;
                } else if (i5 > 0) {
                    i4--;
                    i2 = i4;
                } else {
                    i3 = i4 + 1;
                }
            }
            i2 = i4;
            if (i2 < 0) {
                return null;
            }
            if (j == ((long) this.phonePrefixMapStorage.getPrefix(i2))) {
                return this.phonePrefixMapStorage.getDescription(i2);
            }
            sortedSet = sortedSet.headSet(num);
        }
        return null;
    }

    public void readExternal(ObjectInput objectInput) throws IOException {
        if (objectInput.readBoolean()) {
            this.phonePrefixMapStorage = new FlyweightMapStorage();
        } else {
            this.phonePrefixMapStorage = new DefaultMapStorage();
        }
        this.phonePrefixMapStorage.readExternal(objectInput);
    }

    public String toString() {
        return this.phonePrefixMapStorage.toString();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeBoolean(this.phonePrefixMapStorage instanceof FlyweightMapStorage);
        this.phonePrefixMapStorage.writeExternal(objectOutput);
    }

    public String lookup(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        return lookup(Long.parseLong(phonenumber$PhoneNumber.getCountryCode() + this.phoneUtil.getNationalSignificantNumber(phonenumber$PhoneNumber)));
    }
}
