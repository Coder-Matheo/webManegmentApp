package rob.myappcompany.webmenagementapp;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OpenNewActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    WebView webView;
    AlertDialog.Builder alertDialog;

    String Tag = OpenNewActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_new);

        bottomNavigationView = findViewById(R.id.bottom__navigation);
        webView = findViewById(R.id.webView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://www.google.com");

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.opennew:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });




    }

    public boolean isNetzwerkAvialibale(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
    public void getURL(View view){

        Log.i(Tag, "Title"+webView.getTitle());
        Log.i(Tag, "Title"+webView.getTransitionName());
        if (isNetzwerkAvialibale(getApplicationContext()) == true){

            alertDialog = new AlertDialog.Builder(OpenNewActivity.this);
            alertDialog.setTitle("Get and Save URL").setMessage("Are you sure to save URL Address")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(Tag,webView.getUrl());
                            Intent backMain = new Intent(getApplicationContext(), MainActivity.class);

                            DatabaseHelper databaseHelper =new DatabaseHelper(getApplicationContext());
                            databaseHelper.insert(webView.getTitle(),"App", webView.getUrl(),"Search YouTube App", " ");

                            MainActivity.arrayAdapter.notifyDataSetChanged();
                            startActivity(backMain);
                        }
                    }).setNegativeButton("No", null).create().show();

        }
    }


}