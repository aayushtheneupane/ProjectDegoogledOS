package com.android.settings.backup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInputStream;
import android.app.backup.BackupHelper;
import android.os.ParcelFileDescriptor;
import com.android.settings.shortcut.CreateShortcutPreferenceController;

public class SettingsBackupHelper extends BackupAgentHelper {
    public void onCreate() {
        super.onCreate();
        addHelper("no-op", new NoOpHelper());
    }

    public void onRestoreFinished() {
        super.onRestoreFinished();
        CreateShortcutPreferenceController.updateRestoredShortcuts(this);
    }

    private static class NoOpHelper implements BackupHelper {
        private final int VERSION_CODE;

        public void restoreEntity(BackupDataInputStream backupDataInputStream) {
        }

        public void writeNewStateDescription(ParcelFileDescriptor parcelFileDescriptor) {
        }

        private NoOpHelper() {
            this.VERSION_CODE = 1;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            $closeResource(r1, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
            throw r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void performBackup(android.os.ParcelFileDescriptor r2, android.app.backup.BackupDataOutput r3, android.os.ParcelFileDescriptor r4) {
            /*
                r1 = this;
                java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x002c }
                java.io.FileDescriptor r4 = r4.getFileDescriptor()     // Catch:{ IOException -> 0x002c }
                r0.<init>(r4)     // Catch:{ IOException -> 0x002c }
                r4 = 0
                int r1 = r1.getVersionCode(r2)     // Catch:{ all -> 0x0025 }
                r2 = 1
                if (r1 == r2) goto L_0x001b
                java.lang.String r1 = "dummy"
                r3.writeEntityHeader(r1, r2)     // Catch:{ all -> 0x0025 }
                byte[] r1 = new byte[r2]     // Catch:{ all -> 0x0025 }
                r3.writeEntityData(r1, r2)     // Catch:{ all -> 0x0025 }
            L_0x001b:
                r0.write(r2)     // Catch:{ all -> 0x0025 }
                r0.flush()     // Catch:{ all -> 0x0025 }
                $closeResource(r4, r0)     // Catch:{ IOException -> 0x002c }
                goto L_0x002c
            L_0x0025:
                r1 = move-exception
                throw r1     // Catch:{ all -> 0x0027 }
            L_0x0027:
                r2 = move-exception
                $closeResource(r1, r0)     // Catch:{ IOException -> 0x002c }
                throw r2     // Catch:{ IOException -> 0x002c }
            L_0x002c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.backup.SettingsBackupHelper.NoOpHelper.performBackup(android.os.ParcelFileDescriptor, android.app.backup.BackupDataOutput, android.os.ParcelFileDescriptor):void");
        }

        private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
            if (th != null) {
                try {
                    autoCloseable.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            } else {
                autoCloseable.close();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            $closeResource(r3, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x001c, code lost:
            throw r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int getVersionCode(android.os.ParcelFileDescriptor r3) {
            /*
                r2 = this;
                r2 = 0
                if (r3 != 0) goto L_0x0004
                return r2
            L_0x0004:
                java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x001d }
                java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch:{ IOException -> 0x001d }
                r0.<init>(r3)     // Catch:{ IOException -> 0x001d }
                r3 = 0
                int r1 = r0.read()     // Catch:{ all -> 0x0016 }
                $closeResource(r3, r0)     // Catch:{ IOException -> 0x001d }
                return r1
            L_0x0016:
                r3 = move-exception
                throw r3     // Catch:{ all -> 0x0018 }
            L_0x0018:
                r1 = move-exception
                $closeResource(r3, r0)     // Catch:{ IOException -> 0x001d }
                throw r1     // Catch:{ IOException -> 0x001d }
            L_0x001d:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settings.backup.SettingsBackupHelper.NoOpHelper.getVersionCode(android.os.ParcelFileDescriptor):int");
        }
    }
}
