package com.genericslab.droidplate.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.StatFs;

import com.genericslab.droidplate.log.Tracer;

import java.io.File;

/**
 * Utility functions for handling storage errors
 */
public class StorageUtils {
    private static final String TAG = "StorageUtils";

    public static boolean storageAvailable(Context context) {
        File dir = context.getExternalFilesDir(null);
        if (dir != null) {
            return dir.exists() && dir.canRead() && dir.canWrite();
        } else {
            Tracer.e("Storage not available: data folder is null");
            return false;
        }
    }

    /**
     * Checks if external storage is available. If external storage isn't
     * available, the current activity is finsished an an error activity is
     * launched.
     *
     * @param activity the activity which would be finished if no storage is
     *                 available
     * @return true if external storage is available
     */
    public static boolean checkStorageAvailability(Activity activity) {
        boolean storageAvailable = storageAvailable(activity);
        if (!storageAvailable) {
            activity.finish();
            StorageErrorActivity_.intent(activity).start();
        }
        return storageAvailable;
    }

    /**
     * Get the number of free bytes that are available on the external storage.
     */
    public static long getFreeSpaceAvailable(Context context) {
        StatFs stat = new StatFs(context.getExternalFilesDir(null).getAbsolutePath());
        long availableBlocks;
        long blockSize;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            availableBlocks = stat.getAvailableBlocksLong();
            blockSize = stat.getBlockSizeLong();
        } else {
            availableBlocks = stat.getAvailableBlocks();
            blockSize = stat.getBlockSize();
        }
        return availableBlocks * blockSize;
    }
}
