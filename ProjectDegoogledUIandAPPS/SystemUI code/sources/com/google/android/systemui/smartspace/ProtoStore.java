package com.google.android.systemui.smartspace;

import android.content.Context;
import android.util.Log;
import com.google.protobuf.nano.MessageNano;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ProtoStore {
    private final Context mContext;

    public ProtoStore(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void store(MessageNano messageNano, String str) {
        FileOutputStream openFileOutput;
        try {
            openFileOutput = this.mContext.openFileOutput(str, 0);
            if (messageNano != null) {
                openFileOutput.write(MessageNano.toByteArray(messageNano));
            } else {
                Log.d("ProtoStore", "deleting " + str);
                this.mContext.deleteFile(str);
            }
            if (openFileOutput != null) {
                openFileOutput.close();
            }
        } catch (FileNotFoundException unused) {
            Log.d("ProtoStore", "file does not exist");
        } catch (Exception e) {
            Log.e("ProtoStore", "unable to write file", e);
        } catch (Throwable th) {
            if (openFileOutput != null) {
                openFileOutput.close();
            }
            throw th;
        }
    }

    public <T extends MessageNano> boolean load(String str, T t) {
        File fileStreamPath = this.mContext.getFileStreamPath(str);
        try {
            FileInputStream fileInputStream = new FileInputStream(fileStreamPath);
            byte[] bArr = new byte[((int) fileStreamPath.length())];
            fileInputStream.read(bArr, 0, bArr.length);
            MessageNano.mergeFrom(t, bArr);
            fileInputStream.close();
            return true;
        } catch (FileNotFoundException unused) {
            Log.d("ProtoStore", "no cached data");
            return false;
        } catch (Exception e) {
            Log.e("ProtoStore", "unable to load data", e);
            return false;
        }
    }
}
