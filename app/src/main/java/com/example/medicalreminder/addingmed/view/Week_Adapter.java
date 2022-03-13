package com.example.medicalreminder.addingmed.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;

import java.util.List;

public class Week_Adapter extends RecyclerView.Adapter<Week_Adapter.WeekHandler> {
    List <String> Week;
    Context context;
    public String TAG="tag";
    onclickday onclickdayf;

    //Array of pogo
    public Week_Adapter(List<String> week, Context context,onclickday onclickday){
        this.context=context;
        this.Week=week;
        onclickdayf=onclickday;
    };

    class WeekHandler extends RecyclerView.ViewHolder implements View.OnClickListener {
        //data of custom Row
        View row;
        private TextView day;
        onclickday onclickday;
        String DAY;
        public WeekHandler(@NonNull View convertView , onclickday onclickday) {
            super(convertView);
            row=convertView;
            day=row.findViewById(R.id.day);
//            Log.i(TAG, "WeekHandler: "+day.getText().toString());
            this.onclickday=onclickday;
            convertView.setOnClickListener(this);

        }
        public TextView getDay() {
            return day;
        }
        public void setDay(TextView day) {
            this.day = day;
        }

        @Override
        public void onClick(View view) {
            TextView holder = day.findViewById(row.getId());
//            DAY= holder.getText().toString();
            onclickday.OnClickDay(getAdapterPosition(),view);}
    }

    @NonNull
    @Override
    //Create ViewHolder
    public WeekHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        //parse xml custom row to view ==> so Adapter can deal withit
        View view = inflater.inflate(R.layout.customday,parent , false);
        WeekHandler viewHolder= new WeekHandler(view,onclickdayf);
        Log.i(TAG, "onCreateViewHolder: " + viewHolder);
        return viewHolder;
    }
    //BindData replace data on the views according to position
    @Override
    public void onBindViewHolder(@NonNull WeekHandler holder, int position) {
        holder.getDay().setText(Week.get(position));
        //holder.onClick();
    }
    //items display
    @Override
    public int getItemCount() {
        return Week.size();
    }

    public  interface  onclickday{
        void OnClickDay(int position,View view );
    }
}
