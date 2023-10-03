package com.google.i18n.phonenumbers;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class Phonemetadata$PhoneMetadataCollection implements Externalizable {
    private static final long serialVersionUID = 1;
    private List<Phonemetadata$PhoneMetadata> metadata_ = new ArrayList();

    public List<Phonemetadata$PhoneMetadata> getMetadataList() {
        return this.metadata_;
    }

    public int getMetadataCount() {
        return this.metadata_.size();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int metadataCount = getMetadataCount();
        objectOutput.writeInt(metadataCount);
        for (int i = 0; i < metadataCount; i++) {
            this.metadata_.get(i).writeExternal(objectOutput);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException {
        int readInt = objectInput.readInt();
        for (int i = 0; i < readInt; i++) {
            Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata = new Phonemetadata$PhoneMetadata();
            phonemetadata$PhoneMetadata.readExternal(objectInput);
            this.metadata_.add(phonemetadata$PhoneMetadata);
        }
    }
}
