package com.google.i18n.phonenumbers;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Phonemetadata$NumberFormat implements Externalizable {
    private static final long serialVersionUID = 1;
    private String domesticCarrierCodeFormattingRule_ = "";
    private String format_ = "";
    private boolean hasDomesticCarrierCodeFormattingRule;
    private boolean hasFormat;
    private boolean hasNationalPrefixFormattingRule;
    private boolean hasNationalPrefixOptionalWhenFormatting;
    private boolean hasPattern;
    private List<String> leadingDigitsPattern_ = new ArrayList();
    private String nationalPrefixFormattingRule_ = "";
    private boolean nationalPrefixOptionalWhenFormatting_ = false;
    private String pattern_ = "";

    public Phonemetadata$NumberFormat setPattern(String str) {
        this.hasPattern = true;
        this.pattern_ = str;
        return this;
    }

    public Phonemetadata$NumberFormat setFormat(String str) {
        this.hasFormat = true;
        this.format_ = str;
        return this;
    }

    public int leadingDigitsPatternSize() {
        return this.leadingDigitsPattern_.size();
    }

    public Phonemetadata$NumberFormat setNationalPrefixFormattingRule(String str) {
        this.hasNationalPrefixFormattingRule = true;
        this.nationalPrefixFormattingRule_ = str;
        return this;
    }

    public Phonemetadata$NumberFormat setNationalPrefixOptionalWhenFormatting(boolean z) {
        this.hasNationalPrefixOptionalWhenFormatting = true;
        this.nationalPrefixOptionalWhenFormatting_ = z;
        return this;
    }

    public Phonemetadata$NumberFormat setDomesticCarrierCodeFormattingRule(String str) {
        this.hasDomesticCarrierCodeFormattingRule = true;
        this.domesticCarrierCodeFormattingRule_ = str;
        return this;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeUTF(this.pattern_);
        objectOutput.writeUTF(this.format_);
        int leadingDigitsPatternSize = leadingDigitsPatternSize();
        objectOutput.writeInt(leadingDigitsPatternSize);
        for (int i = 0; i < leadingDigitsPatternSize; i++) {
            objectOutput.writeUTF(this.leadingDigitsPattern_.get(i));
        }
        objectOutput.writeBoolean(this.hasNationalPrefixFormattingRule);
        if (this.hasNationalPrefixFormattingRule) {
            objectOutput.writeUTF(this.nationalPrefixFormattingRule_);
        }
        objectOutput.writeBoolean(this.hasDomesticCarrierCodeFormattingRule);
        if (this.hasDomesticCarrierCodeFormattingRule) {
            objectOutput.writeUTF(this.domesticCarrierCodeFormattingRule_);
        }
        objectOutput.writeBoolean(this.nationalPrefixOptionalWhenFormatting_);
    }

    public void readExternal(ObjectInput objectInput) throws IOException {
        setPattern(objectInput.readUTF());
        setFormat(objectInput.readUTF());
        int readInt = objectInput.readInt();
        for (int i = 0; i < readInt; i++) {
            this.leadingDigitsPattern_.add(objectInput.readUTF());
        }
        if (objectInput.readBoolean()) {
            setNationalPrefixFormattingRule(objectInput.readUTF());
        }
        if (objectInput.readBoolean()) {
            setDomesticCarrierCodeFormattingRule(objectInput.readUTF());
        }
        setNationalPrefixOptionalWhenFormatting(objectInput.readBoolean());
    }
}
