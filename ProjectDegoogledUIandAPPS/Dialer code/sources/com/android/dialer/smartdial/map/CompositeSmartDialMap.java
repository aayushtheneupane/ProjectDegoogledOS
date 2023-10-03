package com.android.dialer.smartdial.map;

import android.content.Context;
import android.support.p000v4.util.SimpleArrayMap;
import android.support.p002v7.appcompat.R$style;
import com.google.common.base.Optional;

public class CompositeSmartDialMap {
    private static final SmartDialMap DEFAULT_MAP = LatinSmartDialMap.getInstance();
    private static final SimpleArrayMap<String, SmartDialMap> EXTRA_MAPS = new SimpleArrayMap<>();

    static {
        EXTRA_MAPS.put("bul", BulgarianSmartDialMap.getInstance());
        EXTRA_MAPS.put("rus", RussianSmartDialMap.getInstance());
        EXTRA_MAPS.put("ukr", UkrainianSmartDialMap.getInstance());
    }

    public static byte getDialpadIndex(Context context, char c) {
        Optional<Byte> dialpadIndex = DEFAULT_MAP.getDialpadIndex(c);
        if (dialpadIndex.isPresent()) {
            return dialpadIndex.get().byteValue();
        }
        Optional<SmartDialMap> extraMap = getExtraMap(context);
        if (extraMap.isPresent()) {
            dialpadIndex = extraMap.get().getDialpadIndex(c);
        }
        if (dialpadIndex.isPresent()) {
            return dialpadIndex.get().byteValue();
        }
        return -1;
    }

    public static char getDialpadNumericCharacter(Context context, char c) {
        Optional<Character> dialpadNumericCharacter = DEFAULT_MAP.getDialpadNumericCharacter(c);
        if (dialpadNumericCharacter.isPresent()) {
            return dialpadNumericCharacter.get().charValue();
        }
        Optional<SmartDialMap> extraMap = getExtraMap(context);
        if (extraMap.isPresent()) {
            dialpadNumericCharacter = extraMap.get().getDialpadNumericCharacter(c);
        }
        return dialpadNumericCharacter.isPresent() ? dialpadNumericCharacter.get().charValue() : c;
    }

    static Optional<SmartDialMap> getExtraMap(Context context) {
        String iSO3Language = R$style.getLocale(context).getISO3Language();
        if (EXTRA_MAPS.containsKey(iSO3Language)) {
            return Optional.m67of(EXTRA_MAPS.get(iSO3Language));
        }
        return Optional.absent();
    }

    public static boolean isValidDialpadAlphabeticChar(Context context, char c) {
        if (DEFAULT_MAP.getCharToKeyMap().containsKey(Character.valueOf(c))) {
            return true;
        }
        Optional<SmartDialMap> extraMap = getExtraMap(context);
        if (!extraMap.isPresent() || !extraMap.get().getCharToKeyMap().containsKey(Character.valueOf(c))) {
            return false;
        }
        return true;
    }

    public static boolean isValidDialpadCharacter(Context context, char c) {
        if (DEFAULT_MAP.isValidDialpadCharacter(c)) {
            return true;
        }
        Optional<SmartDialMap> extraMap = getExtraMap(context);
        if (!extraMap.isPresent() || !extraMap.get().isValidDialpadCharacter(c)) {
            return false;
        }
        return true;
    }

    public static boolean isValidDialpadNumericChar(Context context, char c) {
        if (DEFAULT_MAP.isValidDialpadNumericChar(c)) {
            return true;
        }
        Optional<SmartDialMap> extraMap = getExtraMap(context);
        if (!extraMap.isPresent() || !extraMap.get().isValidDialpadNumericChar(c)) {
            return false;
        }
        return true;
    }

    public static char normalizeCharacter(Context context, char c) {
        Optional<Character> normalizeCharacter = DEFAULT_MAP.normalizeCharacter(c);
        if (normalizeCharacter.isPresent()) {
            return normalizeCharacter.get().charValue();
        }
        Optional<SmartDialMap> extraMap = getExtraMap(context);
        if (extraMap.isPresent()) {
            normalizeCharacter = extraMap.get().normalizeCharacter(c);
        }
        return normalizeCharacter.isPresent() ? normalizeCharacter.get().charValue() : c;
    }
}
