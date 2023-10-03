package com.google.i18n.phonenumbers;

import java.io.Serializable;
import p026b.p027a.p030b.p031a.C0632a;

public class Phonenumber$PhoneNumber implements Serializable {
    private static final long serialVersionUID = 1;
    private CountryCodeSource countryCodeSource_ = CountryCodeSource.UNSPECIFIED;
    private int countryCode_ = 0;
    private String extension_ = "";
    private boolean hasCountryCode;
    private boolean hasCountryCodeSource;
    private boolean hasExtension;
    private boolean hasItalianLeadingZero;
    private boolean hasNationalNumber;
    private boolean hasNumberOfLeadingZeros;
    private boolean hasPreferredDomesticCarrierCode;
    private boolean hasRawInput;
    private boolean italianLeadingZero_ = false;
    private long nationalNumber_ = 0;
    private int numberOfLeadingZeros_ = 1;
    private String preferredDomesticCarrierCode_ = "";
    private String rawInput_ = "";

    public enum CountryCodeSource {
        FROM_NUMBER_WITH_PLUS_SIGN,
        FROM_NUMBER_WITH_IDD,
        FROM_NUMBER_WITHOUT_PLUS_SIGN,
        FROM_DEFAULT_COUNTRY,
        UNSPECIFIED
    }

    /* renamed from: a */
    public Phonenumber$PhoneNumber mo9451a(CountryCodeSource countryCodeSource) {
        if (countryCodeSource != null) {
            this.hasCountryCodeSource = true;
            this.countryCodeSource_ = countryCodeSource;
            return this;
        }
        throw new NullPointerException();
    }

    /* renamed from: c */
    public boolean mo9452c(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        if (phonenumber$PhoneNumber == null) {
            return false;
        }
        if (this == phonenumber$PhoneNumber) {
            return true;
        }
        if (this.countryCode_ == phonenumber$PhoneNumber.countryCode_ && this.nationalNumber_ == phonenumber$PhoneNumber.nationalNumber_ && this.extension_.equals(phonenumber$PhoneNumber.extension_) && this.italianLeadingZero_ == phonenumber$PhoneNumber.italianLeadingZero_ && this.numberOfLeadingZeros_ == phonenumber$PhoneNumber.numberOfLeadingZeros_ && this.rawInput_.equals(phonenumber$PhoneNumber.rawInput_) && this.countryCodeSource_ == phonenumber$PhoneNumber.countryCodeSource_ && this.preferredDomesticCarrierCode_.equals(phonenumber$PhoneNumber.preferredDomesticCarrierCode_) && hasPreferredDomesticCarrierCode() == phonenumber$PhoneNumber.hasPreferredDomesticCarrierCode()) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Phonenumber$PhoneNumber) && mo9452c((Phonenumber$PhoneNumber) obj);
    }

    public int getCountryCode() {
        return this.countryCode_;
    }

    public CountryCodeSource getCountryCodeSource() {
        return this.countryCodeSource_;
    }

    public String getExtension() {
        return this.extension_;
    }

    public long getNationalNumber() {
        return this.nationalNumber_;
    }

    public int getNumberOfLeadingZeros() {
        return this.numberOfLeadingZeros_;
    }

    public String getPreferredDomesticCarrierCode() {
        return this.preferredDomesticCarrierCode_;
    }

    public String getRawInput() {
        return this.rawInput_;
    }

    public boolean hasCountryCodeSource() {
        return this.hasCountryCodeSource;
    }

    public boolean hasExtension() {
        return this.hasExtension;
    }

    public boolean hasItalianLeadingZero() {
        return this.hasItalianLeadingZero;
    }

    public boolean hasNumberOfLeadingZeros() {
        return this.hasNumberOfLeadingZeros;
    }

    public boolean hasPreferredDomesticCarrierCode() {
        return this.hasPreferredDomesticCarrierCode;
    }

    public boolean hasRawInput() {
        return this.hasRawInput;
    }

    public int hashCode() {
        int hashCode = (getExtension().hashCode() + ((Long.valueOf(getNationalNumber()).hashCode() + ((getCountryCode() + 2173) * 53)) * 53)) * 53;
        int i = 1231;
        int i2 = isItalianLeadingZero() ? 1231 : 1237;
        int numberOfLeadingZeros = getNumberOfLeadingZeros();
        int hashCode2 = (getPreferredDomesticCarrierCode().hashCode() + ((getCountryCodeSource().hashCode() + ((getRawInput().hashCode() + ((numberOfLeadingZeros + ((hashCode + i2) * 53)) * 53)) * 53)) * 53)) * 53;
        if (!hasPreferredDomesticCarrierCode()) {
            i = 1237;
        }
        return hashCode2 + i;
    }

    public boolean isItalianLeadingZero() {
        return this.italianLeadingZero_;
    }

    public Phonenumber$PhoneNumber setCountryCode(int i) {
        this.hasCountryCode = true;
        this.countryCode_ = i;
        return this;
    }

    public Phonenumber$PhoneNumber setExtension(String str) {
        if (str != null) {
            this.hasExtension = true;
            this.extension_ = str;
            return this;
        }
        throw new NullPointerException();
    }

    public Phonenumber$PhoneNumber setItalianLeadingZero(boolean z) {
        this.hasItalianLeadingZero = true;
        this.italianLeadingZero_ = z;
        return this;
    }

    public Phonenumber$PhoneNumber setNationalNumber(long j) {
        this.hasNationalNumber = true;
        this.nationalNumber_ = j;
        return this;
    }

    public Phonenumber$PhoneNumber setNumberOfLeadingZeros(int i) {
        this.hasNumberOfLeadingZeros = true;
        this.numberOfLeadingZeros_ = i;
        return this;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Country Code: ");
        Pa.append(this.countryCode_);
        Pa.append(" National Number: ");
        Pa.append(this.nationalNumber_);
        if (hasItalianLeadingZero() && isItalianLeadingZero()) {
            Pa.append(" Leading Zero(s): true");
        }
        if (hasNumberOfLeadingZeros()) {
            Pa.append(" Number of leading zeros: ");
            Pa.append(this.numberOfLeadingZeros_);
        }
        if (hasExtension()) {
            Pa.append(" Extension: ");
            Pa.append(this.extension_);
        }
        if (hasCountryCodeSource()) {
            Pa.append(" Country Code Source: ");
            Pa.append(this.countryCodeSource_);
        }
        if (hasPreferredDomesticCarrierCode()) {
            Pa.append(" Preferred Domestic Carrier Code: ");
            Pa.append(this.preferredDomesticCarrierCode_);
        }
        return Pa.toString();
    }
}
