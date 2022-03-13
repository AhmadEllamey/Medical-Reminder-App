package com.example.medicalreminder.editmedicin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalreminder.R;
import com.example.medicalreminder.addingmed.view.Week_Adapter;

import org.w3c.dom.Text;

import java.util.List;

public class Edit_Adapter extends RecyclerView.Adapter<Edit_Adapter.EditHandler> {
    List<String> Lines;
    Context context;
    OnClicktime onclicktimeglobal;
    public String TAG="tag";

    public Edit_Adapter(List<String> Lines, Context context , OnClicktime onclicktime){
        this.context=context;
        this.Lines=Lines;
        this.onclicktimeglobal=onclicktime;
    };

    class EditHandler extends RecyclerView.ViewHolder implements  View.OnClickListener{
        //data of custom Row
        View row;
        private TextView line;
        OnClicktime onClicktime;
        //String Line;
        public EditHandler(@NonNull View convertView, OnClicktime onClicktime) {
            super(convertView);
            row=convertView;
            this.onClicktime=onClicktime;
            line=row.findViewById(R.id.time_edit_view);
            convertView.setOnClickListener(this);

        }

        public TextView getLine() {
            return line;
        }

        @Override
        public void onClick(View view) {
            TextView holder= line.findViewById(row.getId());

            view=holder;
            onClicktime.OnClick(
                    getAdapterPosition(),view);
        }
    }

    @NonNull
    @Override
    //Create ViewHolder
    public EditHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        //parse xml custom row to view ==> so Adapter can deal withit
        View view = inflater.inflate(R.layout.custom_time_row,parent , false);
        EditHandler viewHolder= new EditHandler(view ,onclicktimeglobal);
        Log.i(TAG, "onCreateViewHolder: " + viewHolder);
        return viewHolder;
    }
    //BindData replace data on the views according to position
    @Override
    public void onBindViewHolder(@NonNull EditHandler holder, int position) {
        holder.getLine().setText(Lines.get(position));
        //holder.onClick();
    }
    //items display
    @Override
    public int getItemCount() {
        return Lines.size();
    }

    public  interface  OnClicktime{
        void OnClick(int position,View view );
    }
}
