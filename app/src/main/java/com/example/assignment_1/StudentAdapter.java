package com.example.assignment_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment_1.Model.Student;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {

    private Context myContext;
    private ArrayList<Student> student_list;

    public StudentAdapter(Context x, ArrayList<Student> s){
        super(x,0,s);
        myContext = x;
        student_list = s;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(myContext).inflate(R.layout.single_student_layout,parent,false);
        }
        Student currentStudent = student_list.get(position);


        TextView student_id = listItem.findViewById(R.id.student_id);
        student_id.setText(currentStudent.getStudentID()+"");

        TextView first_name = listItem.findViewById(R.id.first_name_id);
        first_name.setText(currentStudent.getName());

        TextView second_name = listItem.findViewById(R.id.s_name_id);
        second_name.setText(currentStudent.getSurName());

        TextView parent_number = listItem.findViewById(R.id.number_id);
        parent_number.setText(currentStudent.getParent_Number()+"");

        return listItem;

    }
}
