package model;

import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

public class EdgeToEdge {

    // Método para ativar o modo edge-to-edge na Activity
    public static void enable(AppCompatActivity activity) {
        Window window = activity.getWindow();

        // Para Android 11 ou superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false);
        } else {
            // Para versões abaixo do Android 11
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
    }
}
