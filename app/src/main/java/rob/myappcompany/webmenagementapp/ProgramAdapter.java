package rob.myappcompany.webmenagementapp;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProgramAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> Title;
    String[] description;
    int[]images;

    public ProgramAdapter(Context context, ArrayList<String> Title, String[] descriptionIn, int[]imagesIn) {
        super(context, R.layout.single_item_custom_view, R.id.titleTextView, Title);
        this.context=context;
        this.Title=Title;
        this.description=descriptionIn;
        this.images=imagesIn;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View singleItemView = convertView;
        Pro_ViewHolder holder = null;
        if (singleItemView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItemView = layoutInflater.inflate(R.layout.single_item_custom_view, parent, false);
            holder = new Pro_ViewHolder(singleItemView);
            singleItemView.setTag(holder);
        }else {
            holder = (Pro_ViewHolder) singleItemView.getTag();
        }
        holder.itemImageView.setImageResource(images[position]);
        holder.titleTextView.setText(Title.get(position));
        holder.descripTextView.setText(description[position]);

        MainActivity.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showIntent = new Intent(getContext(), webShowActivity.class);

                showIntent.putExtra("webAddress", MainActivity.array_list.get(position).address);
                getContext().startActivity(showIntent);
                Log.i("DATA", ""+Title.get(position));
                Toast.makeText(getContext(), "Pressed"+Title.get(position), Toast.LENGTH_SHORT).show();

            }

        });

        MainActivity.list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder deleteAlert = new AlertDialog.Builder(getContext());
                deleteAlert.setTitle("Delete Item").setMessage("Are you sure to Delete Item")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("DATA",""+MainActivity.array_list.get(position).id);
                                MainActivity.databaseHelper.delete(MainActivity.array_list.get(position).id);
                                //MainActivity.arrayAdapter.notifyDataSetChanged();

                            }
                        }).create().show();

                return true;
            }
        });

        return singleItemView;
    }
}
