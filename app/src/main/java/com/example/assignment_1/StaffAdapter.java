package com.example.assignment_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.assignment_1.Model.DatabaseHelper;
import com.example.assignment_1.Model.Staff;

import java.util.ArrayList;

public class StaffAdapter extends ArrayAdapter<Staff> {

    private Context myContext;
    private ArrayList<Staff> staff_list;

    public StaffAdapter(Context x, ArrayList<Staff> s){
        super(x,0,s);
        myContext = x;
        staff_list = s;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.single_staff_layout,parent,false);
        }
        Staff currentStaff = staff_list.get(position);

        DatabaseHelper db = new DatabaseHelper(myContext);

        TextView Staff_id = listItem.findViewById(R.id.staff_staff_id);
        Staff_id.setText(currentStaff.getStaffID()+"");

        TextView nursery_ID = listItem.findViewById(R.id.staff_nursery_id);
        nursery_ID.setText(currentStaff.getNurseryID()+"");

        TextView username = listItem.findViewById(R.id.username);
        username.setText(currentStaff.getStaffName());

        TextView password = listItem.findViewById(R.id.password);
        password.setText(currentStaff.getPassword());

        Button pass_reset = listItem.findViewById(R.id.reset_password);
        View finalListItem = listItem;
        pass_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int staff_id_reset = currentStaff.getStaffID();
                int nursery_id_reset = currentStaff.getNurseryID();
                db.resetPassword(nursery_id_reset,staff_id_reset);

                TextView password_change = finalListItem.findViewById(R.id.password);
                password_change.setText("0000");
                Toast.makeText(myContext.getApplicationContext(),"Password was Reset!!",Toast.LENGTH_LONG).show();

            }
        });

        ImageView isAdminImg = listItem.findViewById(R.id.isAdmin);
        if(currentStaff.isAdmin()){
           isAdminImg.setColorFilter(ContextCompat.getColor(myContext, R.color.teal_700));

        }

        ImageView isSuperImg = listItem.findViewById(R.id.isSuper);
        if(currentStaff.isSuper_Auth()){
            isSuperImg.setColorFilter(ContextCompat.getColor(myContext, R.color.d_blue));
        }

        return listItem;

    }
}
