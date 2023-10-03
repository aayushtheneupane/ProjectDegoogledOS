package com.google.i18n.phonenumbers.prefixmapper;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.TreeSet;

abstract class PhonePrefixMapStorageStrategy {
    protected int numOfEntries = 0;
    protected final TreeSet<Integer> possibleLengths = new TreeSet<>();

    public abstract String getDescription(int i);

    public abstract int getPrefix(int i);

    public abstract void readExternal(ObjectInput objectInput) throws IOException;

    public abstract void writeExternal(ObjectOutput objectOutput) throws IOException;

    PhonePrefixMapStorageStrategy() {
    }

    public int getNumOfEntries() {
        return this.numOfEntries;
    }

    public TreeSet<Integer> getPossibleLengths() {
        return this.possibleLengths;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int numOfEntries2 = getNumOfEntries();
        for (int i = 0; i < numOfEntries2; i++) {
            sb.append(getPrefix(i));
            sb.append("|");
            sb.append(getDescription(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
