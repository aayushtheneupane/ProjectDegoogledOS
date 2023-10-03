package com.google.i18n.phonenumbers.prefixmapper;

import com.google.i18n.phonenumbers.Phonenumber$PhoneNumber;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrefixFileReader {
    private static final Logger logger = Logger.getLogger(PrefixFileReader.class.getName());
    private Map<String, PhonePrefixMap> availablePhonePrefixMaps = new HashMap();
    private MappingFileProvider mappingFileProvider = new MappingFileProvider();
    private final String phonePrefixDataDirectory;

    public PrefixFileReader(String str) {
        this.phonePrefixDataDirectory = str;
        InputStream resourceAsStream = PrefixFileReader.class.getResourceAsStream(this.phonePrefixDataDirectory + "config");
        ObjectInputStream objectInputStream = null;
        try {
            ObjectInputStream objectInputStream2 = new ObjectInputStream(resourceAsStream);
            try {
                this.mappingFileProvider.readExternal(objectInputStream2);
                close(objectInputStream2);
            } catch (IOException e) {
                e = e;
                objectInputStream = objectInputStream2;
                try {
                    logger.log(Level.WARNING, e.toString());
                    close(objectInputStream);
                } catch (Throwable th) {
                    th = th;
                    objectInputStream2 = objectInputStream;
                    close(objectInputStream2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                close(objectInputStream2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            logger.log(Level.WARNING, e.toString());
            close(objectInputStream);
        }
    }

    private static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.log(Level.WARNING, e.toString());
            }
        }
    }

    private PhonePrefixMap getPhonePrefixDescriptions(int i, String str, String str2, String str3) {
        String fileName = this.mappingFileProvider.getFileName(i, str, str2, str3);
        ObjectInputStream objectInputStream = null;
        if (fileName.length() == 0) {
            return null;
        }
        if (!this.availablePhonePrefixMaps.containsKey(fileName)) {
            try {
                ObjectInputStream objectInputStream2 = new ObjectInputStream(PrefixFileReader.class.getResourceAsStream(this.phonePrefixDataDirectory + fileName));
                try {
                    PhonePrefixMap phonePrefixMap = new PhonePrefixMap();
                    phonePrefixMap.readExternal(objectInputStream2);
                    this.availablePhonePrefixMaps.put(fileName, phonePrefixMap);
                    close(objectInputStream2);
                } catch (IOException e) {
                    e = e;
                    objectInputStream = objectInputStream2;
                    try {
                        logger.log(Level.WARNING, e.toString());
                        close(objectInputStream);
                        return this.availablePhonePrefixMaps.get(fileName);
                    } catch (Throwable th) {
                        th = th;
                        objectInputStream2 = objectInputStream;
                        close(objectInputStream2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    close(objectInputStream2);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                logger.log(Level.WARNING, e.toString());
                close(objectInputStream);
                return this.availablePhonePrefixMaps.get(fileName);
            }
        }
        return this.availablePhonePrefixMaps.get(fileName);
    }

    public String getDescriptionForNumber(Phonenumber$PhoneNumber phonenumber$PhoneNumber, String str, String str2, String str3) {
        int countryCode = phonenumber$PhoneNumber.getCountryCode();
        boolean z = true;
        if (countryCode == 1) {
            countryCode = ((int) (phonenumber$PhoneNumber.getNationalNumber() / 10000000)) + 1000;
        }
        PhonePrefixMap phonePrefixDescriptions = getPhonePrefixDescriptions(countryCode, str, str2, str3);
        String lookup = phonePrefixDescriptions != null ? phonePrefixDescriptions.lookup(phonenumber$PhoneNumber) : null;
        if (lookup == null || lookup.length() == 0) {
            if (str.equals("zh") || str.equals("ja") || str.equals("ko")) {
                z = false;
            }
            if (z) {
                PhonePrefixMap phonePrefixDescriptions2 = getPhonePrefixDescriptions(countryCode, "en", "", "");
                if (phonePrefixDescriptions2 == null) {
                    return "";
                }
                lookup = phonePrefixDescriptions2.lookup(phonenumber$PhoneNumber);
            }
        }
        if (lookup != null) {
            return lookup;
        }
        return "";
    }
}
