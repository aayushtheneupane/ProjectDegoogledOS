package com.google.i18n.phonenumbers.prefixmapper;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.util.Iterator;

final class FlyweightMapStorage extends PhonePrefixMapStorageStrategy {
    private int descIndexSizeInBytes;
    private ByteBuffer descriptionIndexes;
    private String[] descriptionPool;
    private ByteBuffer phoneNumberPrefixes;
    private int prefixSizeInBytes;

    FlyweightMapStorage() {
    }

    private static void readExternalWord(ObjectInput objectInput, int i, ByteBuffer byteBuffer, int i2) throws IOException {
        int i3 = i2 * i;
        if (i == 2) {
            byteBuffer.putShort(i3, objectInput.readShort());
        } else {
            byteBuffer.putInt(i3, objectInput.readInt());
        }
    }

    private static void writeExternalWord(ObjectOutput objectOutput, int i, ByteBuffer byteBuffer, int i2) throws IOException {
        int i3 = i2 * i;
        if (i == 2) {
            objectOutput.writeShort(byteBuffer.getShort(i3));
        } else {
            objectOutput.writeInt(byteBuffer.getInt(i3));
        }
    }

    public String getDescription(int i) {
        ByteBuffer byteBuffer = this.descriptionIndexes;
        int i2 = this.descIndexSizeInBytes;
        int i3 = i * i2;
        return this.descriptionPool[i2 == 2 ? byteBuffer.getShort(i3) : byteBuffer.getInt(i3)];
    }

    public int getPrefix(int i) {
        ByteBuffer byteBuffer = this.phoneNumberPrefixes;
        int i2 = this.prefixSizeInBytes;
        int i3 = i * i2;
        return i2 == 2 ? byteBuffer.getShort(i3) : byteBuffer.getInt(i3);
    }

    public void readExternal(ObjectInput objectInput) throws IOException {
        this.prefixSizeInBytes = objectInput.readInt();
        this.descIndexSizeInBytes = objectInput.readInt();
        int readInt = objectInput.readInt();
        this.possibleLengths.clear();
        for (int i = 0; i < readInt; i++) {
            this.possibleLengths.add(Integer.valueOf(objectInput.readInt()));
        }
        int readInt2 = objectInput.readInt();
        String[] strArr = this.descriptionPool;
        if (strArr == null || strArr.length < readInt2) {
            this.descriptionPool = new String[readInt2];
        }
        for (int i2 = 0; i2 < readInt2; i2++) {
            this.descriptionPool[i2] = objectInput.readUTF();
        }
        this.numOfEntries = objectInput.readInt();
        ByteBuffer byteBuffer = this.phoneNumberPrefixes;
        if (byteBuffer == null || byteBuffer.capacity() < this.numOfEntries) {
            this.phoneNumberPrefixes = ByteBuffer.allocate(this.numOfEntries * this.prefixSizeInBytes);
        }
        ByteBuffer byteBuffer2 = this.descriptionIndexes;
        if (byteBuffer2 == null || byteBuffer2.capacity() < this.numOfEntries) {
            this.descriptionIndexes = ByteBuffer.allocate(this.numOfEntries * this.descIndexSizeInBytes);
        }
        for (int i3 = 0; i3 < this.numOfEntries; i3++) {
            readExternalWord(objectInput, this.prefixSizeInBytes, this.phoneNumberPrefixes, i3);
            readExternalWord(objectInput, this.descIndexSizeInBytes, this.descriptionIndexes, i3);
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this.prefixSizeInBytes);
        objectOutput.writeInt(this.descIndexSizeInBytes);
        objectOutput.writeInt(this.possibleLengths.size());
        Iterator<Integer> it = this.possibleLengths.iterator();
        while (it.hasNext()) {
            objectOutput.writeInt(it.next().intValue());
        }
        objectOutput.writeInt(this.descriptionPool.length);
        for (String writeUTF : this.descriptionPool) {
            objectOutput.writeUTF(writeUTF);
        }
        objectOutput.writeInt(this.numOfEntries);
        for (int i = 0; i < this.numOfEntries; i++) {
            writeExternalWord(objectOutput, this.prefixSizeInBytes, this.phoneNumberPrefixes, i);
            writeExternalWord(objectOutput, this.descIndexSizeInBytes, this.descriptionIndexes, i);
        }
    }
}
