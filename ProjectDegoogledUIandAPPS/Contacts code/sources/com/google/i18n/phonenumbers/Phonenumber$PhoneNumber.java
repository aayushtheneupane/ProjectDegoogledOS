package com.google.i18n.phonenumbers;

import java.io.Serializable;

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

    public boolean hasCountryCode() {
        return this.hasCountryCode;
    }

    public int getCountryCode() {
        return this.countryCode_;
    }

    public Phonenumber$PhoneNumber setCountryCode(int i) {
        this.hasCountryCode = true;
        this.countryCode_ = i;
        return this;
    }

    public long getNationalNumber() {
        return this.nationalNumber_;
    }

    public Phonenumber$PhoneNumber setNationalNumber(long j) {
        this.hasNationalNumber = true;
        this.nationalNumber_ = j;
        return this;
    }

    public boolean hasExtension() {
        return this.hasExtension;
    }

    public String getExtension() {
        return this.extension_;
    }

    public Phonenumber$PhoneNumber setExtension(String str) {
        if (str != null) {
            this.hasExtension = true;
            this.extension_ = str;
            return this;
        }
        throw new NullPointerException();
    }

    public boolean hasItalianLeadingZero() {
        return this.hasItalianLeadingZero;
    }

    public boolean isItalianLeadingZero() {
        return this.italianLeadingZero_;
    }

    public Phonenumber$PhoneNumber setItalianLeadingZero(boolean z) {
        this.hasItalianLeadingZero = true;
        this.italianLeadingZero_ = z;
        return this;
    }

    public boolean hasNumberOfLeadingZeros() {
        return this.hasNumberOfLeadingZeros;
    }

    public int getNumberOfLeadingZeros() {
        return this.numberOfLeadingZeros_;
    }

    public Phonenumber$PhoneNumber setNumberOfLeadingZeros(int i) {
        this.hasNumberOfLeadingZeros = true;
        this.numberOfLeadingZeros_ = i;
        return this;
    }

    public String getRawInput() {
        return this.rawInput_;
    }

    public Phonenumber$PhoneNumber setRawInput(String str) {
        if (str != null) {
            this.hasRawInput = true;
            this.rawInput_ = str;
            return this;
        }
        throw new NullPointerException();
    }

    public boolean hasCountryCodeSource() {
        return this.hasCountryCodeSource;
    }

    public CountryCodeSource getCountryCodeSource() {
        return this.countryCodeSource_;
    }

    public Phonenumber$PhoneNumber setCountryCodeSource(CountryCodeSource countryCodeSource) {
        if (countryCodeSource != null) {
            this.hasCountryCodeSource = true;
            this.countryCodeSource_ = countryCodeSource;
            return this;
        }
        throw new NullPointerException();
    }

    public Phonenumber$PhoneNumber clearCountryCodeSource() {
        this.hasCountryCodeSource = false;
        this.countryCodeSource_ = CountryCodeSource.UNSPECIFIED;
        return this;
    }

    public boolean hasPreferredDomesticCarrierCode() {
        return this.hasPreferredDomesticCarrierCode;
    }

    public String getPreferredDomesticCarrierCode() {
        return this.preferredDomesticCarrierCode_;
    }

    public Phonenumber$PhoneNumber setPreferredDomesticCarrierCode(String str) {
        if (str != null) {
            this.hasPreferredDomesticCarrierCode = true;
            this.preferredDomesticCarrierCode_ = str;
            return this;
        }
        throw new NullPointerException();
    }

    public boolean exactlySameAs(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
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
        return (obj instanceof Phonenumber$PhoneNumber) && exactlySameAs((Phonenumber$PhoneNumber) obj);
    }

    public int hashCode() {
        int i = 1231;
        int countryCode = (((((((((((((((2173 + getCountryCode()) * 53) + Long.valueOf(getNationalNumber()).hashCode()) * 53) + getExtension().hashCode()) * 53) + (isItalianLeadingZero() ? 1231 : 1237)) * 53) + getNumberOfLeadingZeros()) * 53) + getRawInput().hashCode()) * 53) + getCountryCodeSource().hashCode()) * 53) + getPreferredDomesticCarrierCode().hashCode()) * 53;
        if (!hasPreferredDomesticCarrierCode()) {
            i = 1237;
        }
        return countryCode + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Country Code: ");
        sb.append(this.countryCode_);
        sb.append(" National Number: ");
        sb.append(this.nationalNumber_);
        if (hasItalianLeadingZero() && isItalianLeadingZero()) {
            sb.append(" Leading Zero(s): true");
        }
        if (hasNumberOfLeadingZeros()) {
            sb.append(" Number of leading zeros: ");
            sb.append(this.numberOfLeadingZeros_);
        }
        if (hasExtension()) {
            sb.append(" Extension: ");
            sb.append(this.extension_);
        }
        if (hasCountryCodeSource()) {
            sb.append(" Country Code Source: ");
            sb.append(this.countryCodeSource_);
        }
        if (hasPreferredDomesticCarrierCode()) {
            sb.append(" Preferred Domestic Carrier Code: ");
            sb.append(this.preferredDomesticCarrierCode_);
        }
        return sb.toString();
    }
}
