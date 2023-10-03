package com.android.dialer.phonelookup.consolidator;

import com.android.dialer.common.Assert;
import com.android.dialer.logging.ContactSource$Type;
import com.android.dialer.phonelookup.PhoneLookupInfo;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

public final class PhoneLookupInfoConsolidator {
    private static final ImmutableList<Integer> NAME_SOURCES_IN_PRIORITY_ORDER = ImmutableList.m79of(1, 2, 3, 4, 5, 6);
    private final PhoneLookupInfo.Cp2Info.Cp2ContactInfo firstDefaultCp2Contact;
    private final PhoneLookupInfo.Cp2Info.Cp2ContactInfo firstExtendedCp2Contact;
    private final int nameSource;
    private final PhoneLookupInfo phoneLookupInfo;

    public PhoneLookupInfoConsolidator(PhoneLookupInfo phoneLookupInfo2) {
        this.phoneLookupInfo = phoneLookupInfo2;
        PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo = null;
        int i = 0;
        this.firstDefaultCp2Contact = this.phoneLookupInfo.getDefaultCp2Info().getCp2ContactInfoCount() > 0 ? this.phoneLookupInfo.getDefaultCp2Info().getCp2ContactInfo(0) : null;
        this.firstExtendedCp2Contact = this.phoneLookupInfo.getExtendedCp2Info().getCp2ContactInfoCount() > 0 ? this.phoneLookupInfo.getExtendedCp2Info().getCp2ContactInfo(0) : cp2ContactInfo;
        UnmodifiableIterator<Integer> it = NAME_SOURCES_IN_PRIORITY_ORDER.iterator();
        while (true) {
            if (it.hasNext()) {
                int intValue = it.next().intValue();
                switch (intValue) {
                    case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                        PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo2 = this.firstDefaultCp2Contact;
                        if (cp2ContactInfo2 != null && !cp2ContactInfo2.getName().isEmpty()) {
                            i = 1;
                            break;
                        }
                    case 2:
                        PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo3 = this.firstExtendedCp2Contact;
                        if (cp2ContactInfo3 != null && !cp2ContactInfo3.getName().isEmpty()) {
                            i = 2;
                            break;
                        }
                    case 3:
                        if (this.phoneLookupInfo.hasPeopleApiInfo() && !this.phoneLookupInfo.getPeopleApiInfo().getDisplayName().isEmpty()) {
                            i = 3;
                            break;
                        }
                    case 4:
                        if (!this.phoneLookupInfo.getCequintInfo().getName().isEmpty()) {
                            i = 4;
                            break;
                        } else {
                            continue;
                        }
                    case 5:
                        if (!this.phoneLookupInfo.getCnapInfo().getName().isEmpty()) {
                            i = 5;
                            break;
                        } else {
                            continue;
                        }
                    case 6:
                        if (!this.phoneLookupInfo.getMigratedInfo().getName().isEmpty()) {
                            i = 6;
                            break;
                        } else {
                            continue;
                        }
                    default:
                        throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(intValue)}));
                }
            }
        }
        this.nameSource = i;
    }

    public boolean canReportAsInvalidNumber() {
        int i = this.nameSource;
        switch (i) {
            case 0:
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
            case 2:
            case 4:
            case 5:
            case 6:
                return false;
            case 3:
                PhoneLookupInfo.PeopleApiInfo peopleApiInfo = this.phoneLookupInfo.getPeopleApiInfo();
                if (peopleApiInfo.getInfoType() == PhoneLookupInfo.PeopleApiInfo.InfoType.UNKNOWN || peopleApiInfo.getPersonId().isEmpty()) {
                    return false;
                }
                return true;
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public boolean canSupportCarrierVideoCall() {
        int i = this.nameSource;
        switch (i) {
            case 0:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return false;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo = this.firstDefaultCp2Contact;
                Assert.isNotNull(cp2ContactInfo);
                return cp2ContactInfo.getCanSupportCarrierVideoCall();
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public ContactSource$Type getContactSource() {
        int i = this.nameSource;
        boolean z = false;
        switch (i) {
            case 0:
                return ContactSource$Type.UNKNOWN_SOURCE_TYPE;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                return ContactSource$Type.SOURCE_TYPE_DIRECTORY;
            case 2:
                return ContactSource$Type.SOURCE_TYPE_EXTENDED;
            case 3:
                if (i == 3) {
                    z = true;
                }
                Assert.checkState(z);
                int ordinal = this.phoneLookupInfo.getPeopleApiInfo().getInfoType().ordinal();
                if (ordinal == 1) {
                    return ContactSource$Type.SOURCE_TYPE_PROFILE;
                }
                if (ordinal != 2) {
                    return ContactSource$Type.SOURCE_TYPE_REMOTE_OTHER;
                }
                return ContactSource$Type.SOURCE_TYPE_PLACES;
            case 4:
                return ContactSource$Type.SOURCE_TYPE_CEQUINT_CALLER_ID;
            case 5:
                return ContactSource$Type.SOURCE_TYPE_CNAP;
            case 6:
                ContactSource$Type forNumber = ContactSource$Type.forNumber(this.phoneLookupInfo.getMigratedInfo().getSourceType());
                return forNumber == null ? ContactSource$Type.UNKNOWN_SOURCE_TYPE : forNumber;
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public String getGeolocation() {
        int i = this.nameSource;
        switch (i) {
            case 0:
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
            case 2:
            case 3:
            case 5:
            case 6:
                return "";
            case 4:
                return this.phoneLookupInfo.getCequintInfo().getGeolocation();
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public String getLookupUri() {
        int i = this.nameSource;
        switch (i) {
            case 0:
            case 4:
            case 5:
            case 6:
                return "";
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo = this.firstDefaultCp2Contact;
                Assert.isNotNull(cp2ContactInfo);
                return cp2ContactInfo.getLookupUri();
            case 2:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo2 = this.firstExtendedCp2Contact;
                Assert.isNotNull(cp2ContactInfo2);
                return cp2ContactInfo2.getLookupUri();
            case 3:
                String lookupUri = this.phoneLookupInfo.getPeopleApiInfo().getLookupUri();
                Assert.isNotNull(lookupUri);
                return lookupUri;
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public String getName() {
        int i = this.nameSource;
        switch (i) {
            case 0:
                return "";
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo = this.firstDefaultCp2Contact;
                Assert.isNotNull(cp2ContactInfo);
                return cp2ContactInfo.getName();
            case 2:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo2 = this.firstExtendedCp2Contact;
                Assert.isNotNull(cp2ContactInfo2);
                return cp2ContactInfo2.getName();
            case 3:
                return this.phoneLookupInfo.getPeopleApiInfo().getDisplayName();
            case 4:
                return this.phoneLookupInfo.getCequintInfo().getName();
            case 5:
                return this.phoneLookupInfo.getCnapInfo().getName();
            case 6:
                return this.phoneLookupInfo.getMigratedInfo().getName();
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public String getNumberLabel() {
        int i = this.nameSource;
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
                return "";
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo = this.firstDefaultCp2Contact;
                Assert.isNotNull(cp2ContactInfo);
                return cp2ContactInfo.getLabel();
            case 2:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo2 = this.firstExtendedCp2Contact;
                Assert.isNotNull(cp2ContactInfo2);
                return cp2ContactInfo2.getLabel();
            case 6:
                return this.phoneLookupInfo.getMigratedInfo().getLabel();
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public long getPhotoId() {
        int i = this.nameSource;
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
                return 0;
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo = this.firstDefaultCp2Contact;
                Assert.isNotNull(cp2ContactInfo);
                return Math.max(cp2ContactInfo.getPhotoId(), 0);
            case 2:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo2 = this.firstExtendedCp2Contact;
                Assert.isNotNull(cp2ContactInfo2);
                return Math.max(cp2ContactInfo2.getPhotoId(), 0);
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public String getPhotoThumbnailUri() {
        int i = this.nameSource;
        switch (i) {
            case 0:
            case 3:
            case 4:
            case 5:
                return "";
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo = this.firstDefaultCp2Contact;
                Assert.isNotNull(cp2ContactInfo);
                return cp2ContactInfo.getPhotoThumbnailUri();
            case 2:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo2 = this.firstExtendedCp2Contact;
                Assert.isNotNull(cp2ContactInfo2);
                return cp2ContactInfo2.getPhotoThumbnailUri();
            case 6:
                return this.phoneLookupInfo.getMigratedInfo().getPhotoUri();
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public String getPhotoUri() {
        int i = this.nameSource;
        switch (i) {
            case 0:
            case 3:
            case 5:
                return "";
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo = this.firstDefaultCp2Contact;
                Assert.isNotNull(cp2ContactInfo);
                return cp2ContactInfo.getPhotoUri();
            case 2:
                PhoneLookupInfo.Cp2Info.Cp2ContactInfo cp2ContactInfo2 = this.firstExtendedCp2Contact;
                Assert.isNotNull(cp2ContactInfo2);
                return cp2ContactInfo2.getPhotoUri();
            case 4:
                return this.phoneLookupInfo.getCequintInfo().getPhotoUri();
            case 6:
                return this.phoneLookupInfo.getMigratedInfo().getPhotoUri();
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public boolean isBlocked() {
        if (this.phoneLookupInfo.getSystemBlockedNumberInfo().hasBlockedState()) {
            return this.phoneLookupInfo.getSystemBlockedNumberInfo().getBlockedState().equals(PhoneLookupInfo.BlockedState.BLOCKED);
        }
        return false;
    }

    public boolean isBusiness() {
        int i = this.nameSource;
        switch (i) {
            case 0:
            case NewSearchFragment.READ_CONTACTS_PERMISSION_REQUEST_CODE /*1*/:
            case 2:
            case 4:
            case 5:
                return false;
            case 3:
                if (this.phoneLookupInfo.getPeopleApiInfo().getInfoType() == PhoneLookupInfo.PeopleApiInfo.InfoType.NEARBY_BUSINESS) {
                    return true;
                }
                return false;
            case 6:
                return this.phoneLookupInfo.getMigratedInfo().getIsBusiness();
            default:
                throw new UnsupportedOperationException(String.format("Unsupported name source: %s", new Object[]{Integer.valueOf(i)}));
        }
    }

    public boolean isDefaultCp2InfoIncomplete() {
        return this.phoneLookupInfo.getDefaultCp2Info().getIsIncomplete();
    }

    public boolean isEmergencyNumber() {
        return this.phoneLookupInfo.getEmergencyInfo().getIsEmergencyNumber();
    }

    public boolean isSpam() {
        return this.phoneLookupInfo.getSpamInfo().getIsSpam();
    }
}
