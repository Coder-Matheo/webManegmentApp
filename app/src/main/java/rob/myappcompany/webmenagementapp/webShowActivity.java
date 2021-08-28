package rob.myappcompany.webmenagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class webShowActivity extends AppCompatActivity {

    String Tag = webShowActivity.class.getSimpleName();

    WebView webView;


    public static boolean IsValidUrl(String URLName){
        try{
            URL url = new URL(URLName);
            return URLUtil.isValidUrl(URLName) && Patterns.WEB_URL.matcher(URLName).matches();
        }catch (MalformedURLException e){
            e.printStackTrace();
            return false;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_show);


        webView = findViewById(R.id.webView);

        Intent getAddressIntent = getIntent();
        String webURL = getAddressIntent.getStringExtra("webAddress");
        Log.i(Tag, webURL);

        if (IsValidUrl(webURL) == true){

            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());

            webView.loadUrl(webURL);
        }else {
            Toast.makeText(getApplicationContext(), getString(R.string.fehler_url), Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }


        //Back Button up the Menubar and enabled in Manifest
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}