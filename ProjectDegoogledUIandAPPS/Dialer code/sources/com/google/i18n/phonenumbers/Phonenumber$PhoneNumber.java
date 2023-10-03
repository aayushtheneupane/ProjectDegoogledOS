package com.google.i18n.phonenumbers;

import com.android.tools.p006r8.GeneratedOutlineSupport;
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

    public Phonenumber$PhoneNumber clearCountryCodeSource() {
        this.hasCountryCodeSource = false;
        this.countryCodeSource_ = CountryCodeSource.UNSPECIFIED;
        return this;
    }

    public Phonenumber$PhoneNumber clearExtension() {
        this.hasExtension = false;
        this.extension_ = "";
        return this;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Phonenumber$PhoneNumber) && exactlySameAs((Phonenumber$PhoneNumber) obj);
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

    public boolean hasCountryCode() {
        return this.hasCountryCode;
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

    public boolean hasNationalNumber() {
        return this.hasNationalNumber;
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

    public Phonenumber$PhoneNumber mergeFrom(Phonenumber$PhoneNumber phonenumber$PhoneNumber) {
        if (phonenumber$PhoneNumber.hasCountryCode()) {
            setCountryCode(phonenumber$PhoneNumber.getCountryCode());
        }
        if (phonenumber$PhoneNumber.hasNationalNumber()) {
            setNationalNumber(phonenumber$PhoneNumber.getNationalNumber());
        }
        if (phonenumber$PhoneNumber.hasExtension()) {
            setExtension(phonenumber$PhoneNumber.getExtension());
        }
        if (phonenumber$PhoneNumber.hasItalianLeadingZero()) {
            setItalianLeadingZero(phonenumber$PhoneNumber.isItalianLeadingZero());
        }
        if (phonenumber$PhoneNumber.hasNumberOfLeadingZeros()) {
            setNumberOfLeadingZeros(phonenumber$PhoneNumber.getNumberOfLeadingZeros());
        }
        if (phonenumber$PhoneNumber.hasRawInput()) {
            setRawInput(phonenumber$PhoneNumber.getRawInput());
        }
        if (phonenumber$PhoneNumber.hasCountryCodeSource()) {
            setCountryCodeSource(phonenumber$PhoneNumber.getCountryCodeSource());
        }
        if (phonenumber$PhoneNumber.hasPreferredDomesticCarrierCode()) {
            setPreferredDomesticCarrierCode(phonenumber$PhoneNumber.getPreferredDomesticCarrierCode());
        }
        return this;
    }

    public Phonenumber$PhoneNumber setCountryCode(int i) {
        this.hasCountryCode = true;
        this.countryCode_ = i;
        return this;
    }

    public Phonenumber$PhoneNumber setCountryCodeSource(CountryCodeSource countryCodeSource) {
        if (countryCodeSource != null) {
            this.hasCountryCodeSource = true;
            this.countryCodeSource_ = countryCodeSource;
            return this;
        }
        throw new NullPointerException();
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

    public Phonenumber$PhoneNumber setPreferredDomesticCarrierCode(String str) {
        if (str != null) {
            this.hasPreferredDomesticCarrierCode = true;
            this.preferredDomesticCarrierCode_ = str;
            return this;
        }
        throw new NullPointerException();
    }

    public Phonenumber$PhoneNumber setRawInput(String str) {
        if (str != null) {
            this.hasRawInput = true;
            this.rawInput_ = str;
            return this;
        }
        throw new NullPointerException();
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Country Code: ");
        outline13.append(this.countryCode_);
        outline13.append(" National Number: ");
        outline13.append(this.nationalNumber_);
        if (hasItalianLeadingZero() && isItalianLeadingZero()) {
            outline13.append(" Leading Zero(s): true");
        }
        if (hasNumberOfLeadingZeros()) {
            outline13.append(" Number of leading zeros: ");
            outline13.append(this.numberOfLeadingZeros_);
        }
        if (hasExtension()) {
            outline13.append(" Extension: ");
            outline13.append(this.extension_);
        }
        if (hasCountryCodeSource()) {
            outline13.append(" Country Code Source: ");
            outline13.append(this.countryCodeSource_);
        }
        if (hasPreferredDomesticCarrierCode()) {
            outline13.append(" Preferred Domestic Carrier Code: ");
            outline13.append(this.preferredDomesticCarrierCode_);
        }
        return outline13.toString();
    }
}
