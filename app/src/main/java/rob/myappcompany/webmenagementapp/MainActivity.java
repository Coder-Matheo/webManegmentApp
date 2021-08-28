package rob.myappcompany.webmenagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String Tag = MainActivity.class.getSimpleName();
    public  static View someView;
    public static ArrayAdapter arrayAdapter;

    public static ListView list;
    public static ArrayList<StrucktDB> array_list;
    public static DatabaseHelper databaseHelper;
    BottomNavigationView bottomNavigationView;





    String[] Names = {"c","c++","Python","Java","Javascript"};
    String[]Description = {"c Description","c++ Description","Python Description","Java Description","Javascript Description"};
    int[] Images = {R.drawable.item1, R.drawable.item2,R.drawable.item3,R.drawable.item4,R.drawable.item5};


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        someView = findViewById(R.layout.single_item_custom_view);

         databaseHelper = new DatabaseHelper(this);


        //insert(String nameIn, String titleIn, String addressIn, String descriptionIn, String andere)
        //databaseHelper.insert("YouTube","App", "https://www.youtube.com","Search YouTube App", " ");
        array_list = databaseHelper.getAllCotacts();

        ArrayList<String> Title = new ArrayList<>();

        for (int i=0; i < array_list.size(); i++){
            Log.i(Tag, String.valueOf(array_list.size()));
            Title.add(array_list.get(i).name);
        }


        list = findViewById(R.id.listView);

        bottomNavigationView = findViewById(R.id.bottom__navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.opennew:
                        startActivity(new Intent(getApplicationContext(), OpenNewActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        //----------------------------------------------------------------------------------------------------------------------------------

        ProgramAdapter programAdapter = new ProgramAdapter(this, Title, Description, Images);
        list.setAdapter(programAdapter);





        //----------------------------------------------------------------------------------------------------------------------------------

        /*arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, Title);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showIntent = new Intent(getApplicationContext(), webShowActivity.class);
                showIntent.putExtra("webAddress", array_list.get(position).address);

                overridePendingTransition(0,0);
                startActivity(showIntent);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(MainActivity.this);
                deleteAlert.setTitle("Delete Item").setMessage("Are you sure to Delete Item")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), ""+array_list.get(position).id, Toast.LENGTH_LONG).show();
                                databaseHelper.delete(array_list.get(position).id);
                                arrayAdapter.notifyDataSetChanged();

                            }
                        }).create().show();

                return false;
            }
        });*/

        sharedPref();

    }

    public void sharedPref(){

        SharedPreferences prfs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //get ViewGroup for change the BackgroundColor
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

        boolean backColor = prfs.getBoolean("background_color",false);

        if (backColor){
            viewGroup.setBackgroundColor(Color.BLACK);
        }else {
            viewGroup.setBackgroundColor(Color.WHITE);
        }
    }

}