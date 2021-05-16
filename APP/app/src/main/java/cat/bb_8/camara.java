package cat.bb_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class camara extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
    }
    public void reload(View view){
        WebView camara = findViewById(R.id.webview);
        camara.loadUrl("http://" + variables.getip() + ":8080/video");
    }
}