package com.google.i18n.phonenumbers;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Phonemetadata$PhoneNumberDesc implements Externalizable {
    private static final long serialVersionUID = 1;
    private String exampleNumber_ = "";
    private boolean hasExampleNumber;
    private boolean hasNationalNumberPattern;
    private String nationalNumberPattern_ = "";
    private List possibleLengthLocalOnly_ = new ArrayList();
    private List possibleLength_ = new ArrayList();

    public String getNationalNumberPattern() {
        return this.nationalNumberPattern_;
    }

    public int getPossibleLength(int i) {
        return ((Integer) this.possibleLength_.get(i)).intValue();
    }

    public int getPossibleLengthCount() {
        return this.possibleLength_.size();
    }

    public List getPossibleLengthList() {
        return this.possibleLength_;
    }

    public int getPossibleLengthLocalOnlyCount() {
        return this.possibleLengthLocalOnly_.size();
    }

    public List getPossibleLengthLocalOnlyList() {
        return this.possibleLengthLocalOnly_;
    }

    public void readExternal(ObjectInput objectInput) {
        if (objectInput.readBoolean()) {
            setNationalNumberPattern(objectInput.readUTF());
        }
        int readInt = objectInput.readInt();
        for (int i = 0; i < readInt; i++) {
            this.possibleLength_.add(Integer.valueOf(objectInput.readInt()));
        }
        int readInt2 = objectInput.readInt();
        for (int i2 = 0; i2 < readInt2; i2++) {
            this.possibleLengthLocalOnly_.add(Integer.valueOf(objectInput.readInt()));
        }
        if (objectInput.readBoolean()) {
            setExampleNumber(objectInput.readUTF());
        }
    }

    public Phonemetadata$PhoneNumberDesc setExampleNumber(String str) {
        this.hasExampleNumber = true;
        this.exampleNumber_ = str;
        return this;
    }

    public Phonemetadata$PhoneNumberDesc setNationalNumberPattern(String str) {
        this.hasNationalNumberPattern = true;
        this.nationalNumberPattern_ = str;
        return this;
    }

    public void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeBoolean(this.hasNationalNumberPattern);
        if (this.hasNationalNumberPattern) {
            objectOutput.writeUTF(this.nationalNumberPattern_);
        }
        int possibleLengthCount = getPossibleLengthCount();
        objectOutput.writeInt(possibleLengthCount);
        for (int i = 0; i < possibleLengthCount; i++) {
            objectOutput.writeInt(((Integer) this.possibleLength_.get(i)).intValue());
        }
        int possibleLengthLocalOnlyCount = getPossibleLengthLocalOnlyCount();
        objectOutput.writeInt(possibleLengthLocalOnlyCount);
        for (int i2 = 0; i2 < possibleLengthLocalOnlyCount; i2++) {
            objectOutput.writeInt(((Integer) this.possibleLengthLocalOnly_.get(i2)).intValue());
        }
        objectOutput.writeBoolean(this.hasExampleNumber);
        if (this.hasExampleNumber) {
            objectOutput.writeUTF(this.exampleNumber_);
        }
    }
}
