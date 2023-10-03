package p000;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.p002v7.widget.RecyclerView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileLock;

/* renamed from: hoy */
/* compiled from: PG */
final class hoy {

    /* renamed from: a */
    public static final hoy f13186a = new hoy();

    /* renamed from: b */
    private static final hvy f13187b = hvy.m12261a("com/google/apps/tiktok/ui/locale/LocaleDataStore");

    /* renamed from: c */
    private volatile hpy f13188c = null;

    /* renamed from: a */
    private static File m11857a(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            return filesDir;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo.applicationInfo.dataDir != null) {
                return new File(packageInfo.applicationInfo.dataDir, "files");
            }
            throw new IllegalStateException("PackageInfo was invalid.");
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not find our own package, this should be impossible.");
        }
    }

    /* renamed from: a */
    public final hpy mo7634a(Context context, iij iij) {
        hpy hpy;
        iix iix;
        iix iix2;
        hpy hpy2 = this.f13188c;
        if (hpy2 == null) {
            synchronized (this) {
                hpy2 = this.f13188c;
                if (hpy2 == null) {
                    File file = new File(m11857a(context), "tiktok_configuration");
                    File file2 = new File(file, "CustomConfiguration.lock");
                    if (file2.exists()) {
                        File file3 = new File(file, "CustomConfiguration");
                        iix iix3 = null;
                        if (file3.exists()) {
                            try {
                                FileInputStream fileInputStream = new FileInputStream(file3);
                                try {
                                    hox hox = hox.f13179e;
                                    int read = fileInputStream.read();
                                    if (read != -1) {
                                        ihz a = ihz.m13261a((InputStream) new igy(fileInputStream, ihz.m13259a(read, fileInputStream)));
                                        iix2 = (iix) hox.mo8790b(4);
                                        iky a2 = ikp.f14397a.mo8876a((Object) iix2);
                                        a2.mo8865a(iix2, iia.m13289a(a), iij);
                                        a2.mo8871c(iix2);
                                        a.mo8629a(0);
                                    } else {
                                        iix2 = null;
                                    }
                                    hpy = hpy.m11893b((hox) iix.m13612b(iix2));
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    throw new ijh(e.getMessage());
                                } catch (IOException e2) {
                                    if (!(e2.getCause() instanceof ijh)) {
                                        throw new ijh(e2.getMessage());
                                    }
                                    throw ((ijh) e2.getCause());
                                } catch (RuntimeException e3) {
                                    if (e3.getCause() instanceof ijh) {
                                        throw ((ijh) e3.getCause());
                                    }
                                    throw e3;
                                } catch (ijh e4) {
                                    throw e4;
                                } catch (Throwable th) {
                                    fileInputStream.close();
                                    throw th;
                                }
                            } catch (ijh | FileNotFoundException | RuntimeException e5) {
                            } catch (Throwable th2) {
                                ifn.m12935a(th, th2);
                            }
                        }
                        FileInputStream fileInputStream2 = new FileInputStream(file2);
                        try {
                            FileLock lock = fileInputStream2.getChannel().lock(0, RecyclerView.FOREVER_NS, true);
                            try {
                                File file4 = new File(file, "CustomConfiguration.bak");
                                if (file3.exists()) {
                                    try {
                                        FileInputStream fileInputStream3 = new FileInputStream(file3);
                                        try {
                                            hox hox2 = hox.f13179e;
                                            int read2 = fileInputStream3.read();
                                            if (read2 != -1) {
                                                ihz a3 = ihz.m13261a((InputStream) new igy(fileInputStream3, ihz.m13259a(read2, fileInputStream3)));
                                                iix = (iix) hox2.mo8790b(4);
                                                iky a4 = ikp.f14397a.mo8876a((Object) iix);
                                                a4.mo8865a(iix, iia.m13289a(a3), iij);
                                                a4.mo8871c(iix);
                                                a3.mo8629a(0);
                                            } else {
                                                iix = null;
                                            }
                                            hpy b = hpy.m11893b((hox) iix.m13612b(iix));
                                            fileInputStream3.close();
                                            if (lock != null) {
                                                lock.close();
                                            }
                                            fileInputStream2.close();
                                            hpy = b;
                                        } catch (IOException e6) {
                                            throw new ijh(e6.getMessage());
                                        } catch (IOException e7) {
                                            if (!(e7.getCause() instanceof ijh)) {
                                                throw new ijh(e7.getMessage());
                                            }
                                            throw ((ijh) e7.getCause());
                                        } catch (RuntimeException e8) {
                                            if (e8.getCause() instanceof ijh) {
                                                throw ((ijh) e8.getCause());
                                            }
                                            throw e8;
                                        } catch (ijh e9) {
                                            throw e9;
                                        } catch (Throwable th3) {
                                            fileInputStream3.close();
                                            throw th3;
                                        }
                                    } catch (ijh | RuntimeException e10) {
                                        if (file4.exists()) {
                                            ((hvv) ((hvv) ((hvv) f13187b.mo8178a()).mo8202a(e10)).mo8201a("com/google/apps/tiktok/ui/locale/LocaleDataStore", "readLocaleFromFile", 236, "LocaleDataStore.java")).mo8203a("Failed to read locale, trying back up");
                                        } else {
                                            throw e10;
                                        }
                                    } catch (Throwable th4) {
                                        ifn.m12935a(th3, th4);
                                    }
                                }
                                if (file4.exists()) {
                                    FileInputStream fileInputStream4 = new FileInputStream(file4);
                                    try {
                                        hox hox3 = hox.f13179e;
                                        int read3 = fileInputStream4.read();
                                        if (read3 != -1) {
                                            ihz a5 = ihz.m13261a((InputStream) new igy(fileInputStream4, ihz.m13259a(read3, fileInputStream4)));
                                            iix iix4 = (iix) hox3.mo8790b(4);
                                            iky a6 = ikp.f14397a.mo8876a((Object) iix4);
                                            a6.mo8865a(iix4, iia.m13289a(a5), iij);
                                            a6.mo8871c(iix4);
                                            a5.mo8629a(0);
                                            iix3 = iix4;
                                        }
                                        hpy = hpy.m11893b((hox) iix.m13612b(iix3));
                                        fileInputStream4.close();
                                        if (lock != null) {
                                            lock.close();
                                        }
                                    } catch (IOException e11) {
                                        throw new ijh(e11.getMessage());
                                    } catch (IOException e12) {
                                        if (!(e12.getCause() instanceof ijh)) {
                                            throw new ijh(e12.getMessage());
                                        }
                                        throw ((ijh) e12.getCause());
                                    } catch (RuntimeException e13) {
                                        if (e13.getCause() instanceof ijh) {
                                            throw ((ijh) e13.getCause());
                                        }
                                        throw e13;
                                    } catch (ijh e14) {
                                        throw e14;
                                    } catch (Throwable th5) {
                                        fileInputStream4.close();
                                        throw th5;
                                    }
                                } else {
                                    hpy = hph.f13219a;
                                    if (lock != null) {
                                        lock.close();
                                    }
                                }
                                fileInputStream2.close();
                            } catch (Throwable th6) {
                                if (lock != null) {
                                    lock.close();
                                }
                                throw th6;
                            }
                        } catch (Throwable th7) {
                            try {
                                fileInputStream2.close();
                            } catch (Throwable th8) {
                                ifn.m12935a(th7, th8);
                            }
                            throw th7;
                        }
                    } else {
                        hpy = hph.f13219a;
                    }
                    this.f13188c = hpy;
                    hpy2 = hpy;
                }
            }
        }
        return hpy2;
    }

    /* renamed from: a */
    private static final void m11858a(File file, File file2) {
        if (file2.exists() && !file2.delete()) {
            throw new IOException("Unable to remove destination file");
        } else if (!file.renameTo(file2)) {
            throw new IOException("Unable to rename source file");
        }
    }

    /* JADX WARNING: type inference failed for: r0v11, types: [iir] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo7635a(android.content.Context r7, java.lang.Integer r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            iij r0 = p000.iij.m13501a()     // Catch:{ all -> 0x00ec }
            hpy r0 = r6.mo7634a((android.content.Context) r7, (p000.iij) r0)     // Catch:{ all -> 0x00ec }
            boolean r1 = r0.mo7646a()     // Catch:{ all -> 0x00ec }
            if (r1 == 0) goto L_0x0022
            java.lang.Object r0 = r0.mo7647b()     // Catch:{ all -> 0x00ec }
            hox r0 = (p000.hox) r0     // Catch:{ all -> 0x00ec }
            r1 = 5
            java.lang.Object r1 = r0.mo8790b((int) r1)     // Catch:{ all -> 0x00ec }
            iir r1 = (p000.iir) r1     // Catch:{ all -> 0x00ec }
            r1.mo8503a((p000.iix) r0)     // Catch:{ all -> 0x00ec }
            iit r1 = (p000.iit) r1     // Catch:{ all -> 0x00ec }
            goto L_0x002b
        L_0x0022:
            hox r0 = p000.hox.f13179e     // Catch:{ all -> 0x00ec }
            iir r0 = r0.mo8793g()     // Catch:{ all -> 0x00ec }
            r1 = r0
            iit r1 = (p000.iit) r1     // Catch:{ all -> 0x00ec }
        L_0x002b:
            int r8 = r8.intValue()     // Catch:{ all -> 0x00ec }
            boolean r0 = r1.f14319c     // Catch:{ all -> 0x00ec }
            if (r0 == 0) goto L_0x0039
            r1.mo8751b()     // Catch:{ all -> 0x00ec }
            r0 = 0
            r1.f14319c = r0     // Catch:{ all -> 0x00ec }
        L_0x0039:
            iix r0 = r1.f14318b     // Catch:{ all -> 0x00ec }
            hox r0 = (p000.hox) r0     // Catch:{ all -> 0x00ec }
            hox r2 = p000.hox.f13179e     // Catch:{ all -> 0x00ec }
            int r2 = r0.f13181a     // Catch:{ all -> 0x00ec }
            r2 = r2 | 4
            r0.f13181a = r2     // Catch:{ all -> 0x00ec }
            r0.f13184d = r8     // Catch:{ all -> 0x00ec }
            iix r8 = r1.mo8770g()     // Catch:{ all -> 0x00ec }
            hox r8 = (p000.hox) r8     // Catch:{ all -> 0x00ec }
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x00ec }
            java.io.File r7 = m11857a(r7)     // Catch:{ all -> 0x00ec }
            java.lang.String r1 = "tiktok_configuration"
            r0.<init>(r7, r1)     // Catch:{ all -> 0x00ec }
            r0.mkdirs()     // Catch:{ all -> 0x00ec }
            java.io.File r7 = new java.io.File     // Catch:{ all -> 0x00ec }
            java.lang.String r1 = "CustomConfiguration.lock"
            r7.<init>(r0, r1)     // Catch:{ all -> 0x00ec }
            r7.createNewFile()     // Catch:{ all -> 0x00ec }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x00ec }
            r1.<init>(r7)     // Catch:{ all -> 0x00ec }
            java.nio.channels.FileChannel r7 = r1.getChannel()     // Catch:{ all -> 0x00e2 }
            java.nio.channels.FileLock r7 = r7.lock()     // Catch:{ all -> 0x00e2 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x00d6 }
            java.lang.String r3 = "CustomConfiguration.tmp"
            r2.<init>(r0, r3)     // Catch:{ all -> 0x00d6 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x00d6 }
            if (r3 != 0) goto L_0x0080
            goto L_0x0086
        L_0x0080:
            boolean r3 = r2.delete()     // Catch:{ all -> 0x00d6 }
            if (r3 == 0) goto L_0x00ce
        L_0x0086:
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x00d6 }
            r3.<init>(r2)     // Catch:{ all -> 0x00d6 }
            r8.mo8514b(r3)     // Catch:{ all -> 0x00c4 }
            r3.close()     // Catch:{ all -> 0x00d6 }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x00bf }
            java.lang.String r4 = "CustomConfiguration"
            r3.<init>(r0, r4)     // Catch:{ all -> 0x00bf }
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x00bf }
            java.lang.String r5 = "CustomConfiguration.bak"
            r4.<init>(r0, r5)     // Catch:{ all -> 0x00bf }
            boolean r0 = r3.exists()     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x00a8
            m11858a((java.io.File) r3, (java.io.File) r4)     // Catch:{ all -> 0x00bf }
        L_0x00a8:
            m11858a((java.io.File) r2, (java.io.File) r3)     // Catch:{ all -> 0x00bf }
            r2.delete()     // Catch:{ all -> 0x00d6 }
            if (r7 != 0) goto L_0x00b1
            goto L_0x00b4
        L_0x00b1:
            r7.close()     // Catch:{ all -> 0x00e2 }
        L_0x00b4:
            r1.close()     // Catch:{ all -> 0x00ec }
            hpy r7 = p000.hpy.m11893b(r8)     // Catch:{ all -> 0x00ec }
            r6.f13188c = r7     // Catch:{ all -> 0x00ec }
            monitor-exit(r6)
            return
        L_0x00bf:
            r8 = move-exception
            r2.delete()     // Catch:{ all -> 0x00d6 }
            throw r8     // Catch:{ all -> 0x00d6 }
        L_0x00c4:
            r8 = move-exception
            r3.close()     // Catch:{ all -> 0x00c9 }
            goto L_0x00cd
        L_0x00c9:
            r0 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r8, (java.lang.Throwable) r0)     // Catch:{ all -> 0x00d6 }
        L_0x00cd:
            throw r8     // Catch:{ all -> 0x00d6 }
        L_0x00ce:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x00d6 }
            java.lang.String r0 = "Could not delete temp file"
            r8.<init>(r0)     // Catch:{ all -> 0x00d6 }
            throw r8     // Catch:{ all -> 0x00d6 }
        L_0x00d6:
            r8 = move-exception
            if (r7 == 0) goto L_0x00e1
            r7.close()     // Catch:{ all -> 0x00dd }
            goto L_0x00e1
        L_0x00dd:
            r7 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r8, (java.lang.Throwable) r7)     // Catch:{ all -> 0x00e2 }
        L_0x00e1:
            throw r8     // Catch:{ all -> 0x00e2 }
        L_0x00e2:
            r7 = move-exception
            r1.close()     // Catch:{ all -> 0x00e7 }
            goto L_0x00eb
        L_0x00e7:
            r8 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r7, (java.lang.Throwable) r8)     // Catch:{ all -> 0x00ec }
        L_0x00eb:
            throw r7     // Catch:{ all -> 0x00ec }
        L_0x00ec:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.hoy.mo7635a(android.content.Context, java.lang.Integer):void");
    }
}
