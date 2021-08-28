package rob.myappcompany.webmenagementapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Pro_ViewHolder {
    ImageView itemImageView;
    TextView titleTextView;
    TextView descripTextView;
    Pro_ViewHolder(View view){
        itemImageView = view.findViewById(R.id.imageViewcustom);
        titleTextView = view.findViewById(R.id.titleTextView);
        descripTextView = view.findViewById(R.id.decriptTextView);
    }
}
