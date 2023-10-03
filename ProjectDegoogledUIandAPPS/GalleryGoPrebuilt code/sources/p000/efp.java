package p000;

import android.net.Uri;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* renamed from: efp */
/* compiled from: PG */
final /* synthetic */ class efp implements icf {

    /* renamed from: a */
    private final efr f8166a;

    /* renamed from: b */
    private final File f8167b;

    public efp(efr efr, File file) {
        this.f8166a = efr;
        this.f8167b = file;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        efr efr = this.f8166a;
        File file = this.f8167b;
        Map map = (Map) obj;
        StorageVolume storageVolume = ((StorageManager) efr.f8168a.getSystemService("storage")).getStorageVolume(file);
        if (storageVolume != null) {
            String uuid = storageVolume.getUuid();
            if (uuid != null) {
                String str = (String) map.get(uuid);
                if (!TextUtils.isEmpty(str)) {
                    abt a = abt.m164a(efr.f8168a, Uri.parse(str));
                    List<String> pathSegments = Uri.parse(file.getPath()).getPathSegments();
                    if (!pathSegments.isEmpty()) {
                        int indexOf = pathSegments.indexOf(uuid);
                        int i = indexOf != -1 ? indexOf + 1 : -1;
                        if (i != -1) {
                            return ife.m12820a((Object) new efl(a, hso.m12041a((Collection) pathSegments.subList(i, pathSegments.size()))));
                        }
                        throw new IOException("Volume path not found");
                    }
                    throw new IOException("File path with no segments");
                }
                throw new IOException("No writable volume for file");
            }
            throw new IOException("Storage volume UUID is undefinded");
        }
        throw new IOException("No volume for file");
    }
}
