package com.google.i18n.phonenumbers;

import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Phonemetadata$PhoneMetadataCollection implements Externalizable {
    private static final long serialVersionUID = 1;
    private List metadata_ = new ArrayList();

    public int getMetadataCount() {
        return this.metadata_.size();
    }

    public List getMetadataList() {
        return this.metadata_;
    }

    public void readExternal(ObjectInput objectInput) {
        int readInt = objectInput.readInt();
        for (int i = 0; i < readInt; i++) {
            Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata = new Phonemetadata$PhoneMetadata();
            phonemetadata$PhoneMetadata.readExternal(objectInput);
            this.metadata_.add(phonemetadata$PhoneMetadata);
        }
    }

    public void writeExternal(ObjectOutput objectOutput) {
        int metadataCount = getMetadataCount();
        objectOutput.writeInt(metadataCount);
        for (int i = 0; i < metadataCount; i++) {
            ((Phonemetadata$PhoneMetadata) this.metadata_.get(i)).writeExternal(objectOutput);
        }
    }
}
