package com.android.dialer.searchfragment.directories;

import com.android.dialer.searchfragment.directories.DirectoriesCursorLoader;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class AutoValue_DirectoriesCursorLoader_Directory extends DirectoriesCursorLoader.Directory {
    private final String getDisplayName;
    private final long getId;
    private final boolean supportsPhotos;

    AutoValue_DirectoriesCursorLoader_Directory(long j, String str, boolean z) {
        this.getId = j;
        this.getDisplayName = str;
        this.supportsPhotos = z;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DirectoriesCursorLoader.Directory)) {
            return false;
        }
        DirectoriesCursorLoader.Directory directory = (DirectoriesCursorLoader.Directory) obj;
        if (this.getId == ((AutoValue_DirectoriesCursorLoader_Directory) directory).getId && ((str = this.getDisplayName) != null ? str.equals(((AutoValue_DirectoriesCursorLoader_Directory) directory).getDisplayName) : ((AutoValue_DirectoriesCursorLoader_Directory) directory).getDisplayName == null) && this.supportsPhotos == ((AutoValue_DirectoriesCursorLoader_Directory) directory).supportsPhotos) {
            return true;
        }
        return false;
    }

    public String getDisplayName() {
        return this.getDisplayName;
    }

    public long getId() {
        return this.getId;
    }

    public int hashCode() {
        long j = this.getId;
        int i = (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
        String str = this.getDisplayName;
        return (this.supportsPhotos ? 1231 : 1237) ^ ((i ^ (str == null ? 0 : str.hashCode())) * 1000003);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Directory{getId=");
        outline13.append(this.getId);
        outline13.append(", getDisplayName=");
        outline13.append(this.getDisplayName);
        outline13.append(", supportsPhotos=");
        outline13.append(this.supportsPhotos);
        outline13.append("}");
        return outline13.toString();
    }
}
