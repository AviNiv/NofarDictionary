package il.co.niv.avi.nofardictionary;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Avi on 17/09/2015.
 */
public class Utilities {

    public static void showMessage(Context context, String message) {
        String msg = message;
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showException(Context context, Exception e) {
        String msg = "Exception: " + e.getMessage();
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
        textView.setTextColor(Color.YELLOW);
    }

}
