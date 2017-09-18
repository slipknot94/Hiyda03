package com.example.sh_polak.hiyda;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by sh-polak on 21/08/2017.
 */

public class PremissionManger {
    public static void check(Activity activity, String permission, int requestCode){
        if(ActivityCompat.checkSelfPermission(activity,permission) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(activity, new String[]{permission},requestCode);
    }
}
