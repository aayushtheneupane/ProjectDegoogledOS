package com.google.i18n.phonenumbers.prefixmapper;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.TreeSet;

abstract class PhonePrefixMapStorageStrategy {
    protected int numOfEntries = 0;
    protected final TreeSet<Integer> possibleLengths = new TreeSet<>();

    PhonePrefixMapStorageStrategy() {
    }

    public abstract String getDescription(int i);

    public abstract int getPrefix(int i);

    public abstract void readExternal(ObjectInput objectInput) throws IOException;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = this.numOfEntries;
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(getPrefix(i2));
            sb.append("|");
            sb.append(getDescription(i2));
            sb.append("\n");
        }
        return sb.toString();
    }

    public abstract void writeExternal(ObjectOutput objectOutput) throws IOException;
}
