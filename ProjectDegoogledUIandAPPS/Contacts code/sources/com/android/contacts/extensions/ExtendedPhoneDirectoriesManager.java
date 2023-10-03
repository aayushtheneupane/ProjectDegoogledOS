package com.android.contacts.extensions;

import android.content.Context;
import com.android.contacts.list.DirectoryPartition;
import java.util.List;

public interface ExtendedPhoneDirectoriesManager {
    List<DirectoryPartition> getExtendedDirectories(Context context);
}
