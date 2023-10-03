package p000;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Environment;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.CompoundButton;
import android.widget.EdgeEffect;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import p003j$.util.Optional;

/* renamed from: cya */
/* compiled from: PG */
public class cya {
    /* renamed from: a */
    public static int m5630a(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i != 3) {
            return i != 4 ? 0 : 5;
        }
        return 4;
    }

    /* renamed from: b */
    public static int m5636b(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        if (i != 3) {
            return i != 4 ? 0 : 5;
        }
        return 4;
    }

    /* renamed from: a */
    public static Interpolator m5633a(float f, float f2, float f3, float f4) {
        int i = Build.VERSION.SDK_INT;
        return new PathInterpolator(f, f2, f3, f4);
    }

    /* renamed from: a */
    public static ColorStateList m5632a(CompoundButton compoundButton) {
        int i = Build.VERSION.SDK_INT;
        return compoundButton.getButtonTintList();
    }

    /* renamed from: a */
    public static void m5634a(CompoundButton compoundButton, ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        compoundButton.setButtonTintList(colorStateList);
    }

    /* renamed from: a */
    public static void m5635a(EdgeEffect edgeEffect, float f, float f2) {
        int i = Build.VERSION.SDK_INT;
        edgeEffect.onPull(f, f2);
    }

    /* renamed from: c */
    public static Optional m5638c(String str) {
        Path path = Paths.get(str.substring(0, str.lastIndexOf("/")), new String[0]);
        while (path.getNameCount() > 1) {
            Path parent = path.getParent();
            if (parent.getName(parent.getNameCount() - 1).toString().equals(Environment.DIRECTORY_DCIM)) {
                iir g = cxe.f5893f.mo8793g();
                String num = Integer.toString(m5631a(path.toString()));
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                cxe cxe = (cxe) g.f14318b;
                num.getClass();
                cxe.f5895a |= 1;
                cxe.f5896b = num;
                String path2 = path.getName(path.getNameCount() - 1).toString();
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                cxe cxe2 = (cxe) g.f14318b;
                path2.getClass();
                cxe2.f5895a |= 2;
                cxe2.f5897c = path2;
                String path3 = path.toString();
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                cxe cxe3 = (cxe) g.f14318b;
                path3.getClass();
                int i = cxe3.f5895a | 4;
                cxe3.f5895a = i;
                cxe3.f5898d = path3;
                cxe3.f5895a = i | 8;
                cxe3.f5899e = true;
                return Optional.m16285of((cxe) g.mo8770g());
            }
            path = parent;
        }
        return Optional.empty();
    }

    /* renamed from: a */
    public static int m5631a(String str) {
        File parentFile = new File(new File(str, "unusedFilePath").getAbsolutePath()).getParentFile();
        if (parentFile == null) {
            parentFile = new File("/");
        }
        return m5637b(parentFile.toString());
    }

    /* renamed from: b */
    public static int m5637b(String str) {
        return str.toLowerCase(Locale.getDefault()).hashCode();
    }
}
