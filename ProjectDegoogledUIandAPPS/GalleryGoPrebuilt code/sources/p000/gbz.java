package p000;

import android.graphics.Matrix;
import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import android.system.Os;
import android.system.StructStat;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.devicefolders.NewFolderView;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.concurrent.Executor;

/* renamed from: gbz */
/* compiled from: PG */
public class gbz {

    /* renamed from: a */
    private static Field f10900a;

    /* renamed from: b */
    private static boolean f10901b;

    public gbz() {
    }

    /* renamed from: a */
    public static int m9992a(int i) {
        if (i == 200) {
            return 201;
        }
        if (i == 300) {
            return 301;
        }
        if (i == 302) {
            return 303;
        }
        if (i == 312) {
            return 313;
        }
        if (i == 15000) {
            return 15001;
        }
        if (i == 304) {
            return 305;
        }
        if (i == 305) {
            return 306;
        }
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case RecyclerView.SCROLL_STATE_SETTLING:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            default:
                switch (i) {
                    case 9:
                        return 10;
                    case 10:
                        return 11;
                    case 11:
                        return 12;
                    case 12:
                        return 13;
                    case 13:
                        return 14;
                    default:
                        switch (i) {
                            case 43:
                                return 44;
                            case 44:
                                return 45;
                            case 45:
                                return 46;
                            default:
                                switch (i) {
                                    case 220:
                                        return 221;
                                    case 221:
                                        return 222;
                                    case 222:
                                        return 223;
                                    case 223:
                                        return 224;
                                    case 224:
                                        return 225;
                                    case 225:
                                        return 226;
                                    case 226:
                                        return 227;
                                    case 227:
                                        return 228;
                                    default:
                                        switch (i) {
                                            case 238:
                                                return 239;
                                            case 239:
                                                return 240;
                                            case 240:
                                                return 241;
                                            case 241:
                                                return 242;
                                            default:
                                                switch (i) {
                                                    case 314:
                                                        return 315;
                                                    case 315:
                                                        return 316;
                                                    case 316:
                                                        return 317;
                                                    default:
                                                        return 0;
                                                }
                                        }
                                }
                        }
                }
        }
    }

    /* renamed from: b */
    public static /* synthetic */ String m9995b(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "null" : "PROMO" : "DEVICE_FOLDER" : "NEW_FOLDER";
    }

    /* renamed from: c */
    public static /* synthetic */ String m9996c(int i) {
        switch (i) {
            case 1:
                return "ENQUEUED";
            case RecyclerView.SCROLL_STATE_SETTLING:
                return "RUNNING";
            case 3:
                return "SUCCEEDED";
            case 4:
                return "FAILED";
            case 5:
                return "BLOCKED";
            case 6:
                return "CANCELLED";
            default:
                return "null";
        }
    }

    /* renamed from: d */
    public static /* synthetic */ int m9997d(int i) {
        if (i != 0) {
            return i;
        }
        throw null;
    }

    /* renamed from: e */
    public static boolean m9998e(int i) {
        return i == 3 || i == 4 || i == 6;
    }

    /* renamed from: a */
    public float mo345a(View view) {
        throw null;
    }

    /* renamed from: a */
    public void mo346a(View view, float f) {
        throw null;
    }

    /* renamed from: a */
    public void mo349a(View view, int i, int i2, int i3, int i4) {
        throw null;
    }

    /* renamed from: a */
    public void mo347a(View view, Matrix matrix) {
        throw null;
    }

    /* renamed from: b */
    public void mo348b(View view, Matrix matrix) {
        throw null;
    }

    /* renamed from: a */
    public void mo350a(View view, int i) {
        if (!f10901b) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                f10900a = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            f10901b = true;
        }
        Field field = f10900a;
        if (field != null) {
            try {
                f10900a.setInt(view, i | (field.getInt(view) & -13));
            } catch (IllegalAccessException e2) {
            }
        }
    }

    public gbz(NewFolderView newFolderView, hbl hbl, hos hos) {
        LayoutInflater.from(hbl).inflate(R.layout.new_folder_contents, newFolderView);
        hos.mo7633a((View) newFolderView, (hoi) new bsh());
    }

    /* renamed from: a */
    public static fzx m9993a(String str, ieh ieh, ikf ikf, Executor executor, fyx fyx, iij iij, fxr fxr) {
        return new fzx(new fzg(str, ieh, ikf, executor, fxr, fyx, iij), ife.m12820a((Object) ""));
    }

    /* renamed from: a */
    public static IOException m9994a(File file, IOException iOException) {
        try {
            String valueOf = String.valueOf(String.format(Locale.US, " canonical[%s] freeSpace[%d] exists[%b] isFile[%b] canRead[%b] canWrite[%b]", new Object[]{file.getCanonicalPath(), Long.valueOf(file.getFreeSpace()), Boolean.valueOf(file.exists()), Boolean.valueOf(file.isFile()), Boolean.valueOf(file.canRead()), Boolean.valueOf(file.canWrite())}));
            String str = valueOf.length() == 0 ? new String("Inoperable file:") : "Inoperable file:".concat(valueOf);
            int i = Build.VERSION.SDK_INT;
            try {
                StructStat stat = Os.stat(file.getCanonicalPath());
                String valueOf2 = String.valueOf(str);
                String valueOf3 = String.valueOf(String.format(Locale.US, " mode[%d]", new Object[]{Integer.valueOf(stat.st_mode)}));
                str = valueOf3.length() == 0 ? new String(valueOf2) : valueOf2.concat(valueOf3);
            } catch (Exception e) {
            }
            return new IOException(str, iOException);
        } catch (IOException e2) {
            return iOException;
        }
    }
}
