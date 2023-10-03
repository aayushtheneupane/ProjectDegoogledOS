package com.google.i18n.phonenumbers;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class MultiFileMetadataSourceImpl {
    private final ConcurrentHashMap<String, Phonemetadata$PhoneMetadata> geographicalRegions = new ConcurrentHashMap<>();
    private final MetadataLoader metadataLoader;
    private final ConcurrentHashMap<Integer, Phonemetadata$PhoneMetadata> nonGeographicalRegions = new ConcurrentHashMap<>();
    private final String phoneNumberMetadataFilePrefix = "/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto";

    MultiFileMetadataSourceImpl(MetadataLoader metadataLoader2) {
        this.metadataLoader = metadataLoader2;
    }

    public Phonemetadata$PhoneMetadata getMetadataForNonGeographicalRegion(int i) {
        List list = AlternateFormatsCountryCodeSet.getCountryCodeToRegionCodeMap().get(Integer.valueOf(i));
        boolean z = false;
        if (list.size() == 1 && "001".equals(list.get(0))) {
            z = true;
        }
        if (!z) {
            return null;
        }
        return MetadataManager.getMetadataFromMultiFilePrefix(Integer.valueOf(i), this.nonGeographicalRegions, this.phoneNumberMetadataFilePrefix, this.metadataLoader);
    }

    public Phonemetadata$PhoneMetadata getMetadataForRegion(String str) {
        return MetadataManager.getMetadataFromMultiFilePrefix(str, this.geographicalRegions, this.phoneNumberMetadataFilePrefix, this.metadataLoader);
    }
}
