package rob.myappcompany.webmenagementapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Locale;

public class Settings extends AppCompatActivity {

    public static boolean backClr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        boolean backColor = prefs.getBoolean("background_color",false);

        prefs.edit().putBoolean("backColor", backColor).apply();

        backClr = prefs.getBoolean("backColor", false);


        loadLocal();

    }




    public static class MyPreferenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            Preference prf = findPreference("background_color");

            if (backClr){
                prf.setSummary("Display has Black Background");
            }else {
                prf.setSummary("Display has White Background");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lang, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        switch (item.getItemId()){
            case R.id.english_lang_menu:
                setLocal("en");
                recreate();
                return true;
            case R.id.deutsch_lang_menu:
                setLocal("de");
                recreate();
                return true;
            case R.id.farsi_lang_menu:
                setLocal("fa");
                recreate();
                return true;
            default:
                return false;
        }
    }


    public void setLocal(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        // save data to sharedPreferences
        SharedPreferences.Editor editor = (SharedPreferences.Editor) getSharedPreferences("Setting", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang).commit();
        editor.apply();

    }
    //Load language saved in Shared Preferences
    public void loadLocal(){
        SharedPreferences prefs = getSharedPreferences("Setting", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocal(language);
    }

}