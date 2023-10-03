package com.android.dialer.smartdial.map;

import android.support.p000v4.util.SimpleArrayMap;
import com.android.dialer.dialpadview.DialpadCharMappings;
import com.google.common.base.Optional;

final class UkrainianSmartDialMap extends SmartDialMap {
    private static UkrainianSmartDialMap instance;

    private UkrainianSmartDialMap() {
    }

    static UkrainianSmartDialMap getInstance() {
        if (instance == null) {
            instance = new UkrainianSmartDialMap();
        }
        return instance;
    }

    /* access modifiers changed from: package-private */
    public SimpleArrayMap<Character, Character> getCharToKeyMap() {
        return DialpadCharMappings.getCharToKeyMap("ukr");
    }

    /* access modifiers changed from: package-private */
    public Optional<Character> normalizeCharacter(char c) {
        char lowerCase = Character.toLowerCase(c);
        return isValidDialpadAlphabeticChar(lowerCase) ? Optional.m67of(Character.valueOf(lowerCase)) : Optional.absent();
    }
}
