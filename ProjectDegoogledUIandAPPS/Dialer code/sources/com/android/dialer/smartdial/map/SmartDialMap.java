package com.android.dialer.smartdial.map;

import android.support.p000v4.util.SimpleArrayMap;
import com.google.common.base.Optional;

abstract class SmartDialMap {
    SmartDialMap() {
    }

    /* access modifiers changed from: package-private */
    public abstract SimpleArrayMap<Character, Character> getCharToKeyMap();

    /* access modifiers changed from: protected */
    public Optional<Byte> getDialpadIndex(char c) {
        if (isValidDialpadNumericChar(c)) {
            return Optional.m67of(Byte.valueOf((byte) (c - '0')));
        }
        if (getCharToKeyMap().containsKey(Character.valueOf(c))) {
            return Optional.m67of(Byte.valueOf((byte) (getCharToKeyMap().get(Character.valueOf(c)).charValue() - '0')));
        }
        return Optional.absent();
    }

    /* access modifiers changed from: protected */
    public Optional<Character> getDialpadNumericCharacter(char c) {
        if (getCharToKeyMap().containsKey(Character.valueOf(c))) {
            return Optional.m67of(getCharToKeyMap().get(Character.valueOf(c)));
        }
        return Optional.absent();
    }

    /* access modifiers changed from: protected */
    public boolean isValidDialpadAlphabeticChar(char c) {
        return getCharToKeyMap().containsKey(Character.valueOf(c));
    }

    /* access modifiers changed from: protected */
    public boolean isValidDialpadCharacter(char c) {
        return getCharToKeyMap().containsKey(Character.valueOf(c)) || isValidDialpadNumericChar(c);
    }

    /* access modifiers changed from: protected */
    public boolean isValidDialpadNumericChar(char c) {
        return '0' <= c && c <= '9';
    }

    /* access modifiers changed from: package-private */
    public abstract Optional<Character> normalizeCharacter(char c);
}
