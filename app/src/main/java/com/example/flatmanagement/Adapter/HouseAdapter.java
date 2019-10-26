package com.example.flatmanagement.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flatmanagement.Activity.MainActivity;
import com.example.flatmanagement.Database.Dbhelper;
import com.example.flatmanagement.Fragment.HomeFragment;
import com.example.flatmanagement.Model.House;
import com.example.flatmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {
    private Context context;
    private List<House> housesList;
    private Dbhelper dbhelper;

    public HouseAdapter(Context context, List<House> housesList) {
        this.context = context;
        this.housesList = housesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_house,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final House house=housesList.get(position);

        holder.houseNameTv.setText(house.getHouseName());
        holder.totalFlatTv.setText(String.valueOf(house.getTotalFlat()));
        holder.managerName.setText(house.getManagerName());
        holder.managerContactTv.setText(house.getManagerContact());

        //recycler view more button click action


        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(view.getContext(), holder.editBtn);
                //inflating menu from xml resource
                popup.inflate(R.menu.edit_house_item);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.update_house:
                                //handle menu1 click\\
                                return true;
                            case R.id.delete_house:

                                //show delete dialog to delete
                                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(view.getContext());
                                deleteDialog.setTitle("Warning!");
                                deleteDialog.setMessage("Are you sure to delete?");

                                deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            dbhelper=new Dbhelper(view.getContext());
                                            boolean a=dbhelper.deleteHouse(house.getHouseName());
                                            housesList.remove(position);
                                            notifyDataSetChanged();
                                        }catch (Exception e){
                                            Toast.makeText(view.getContext(), "Exception: "+e, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                deleteDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                deleteDialog.show();
                                return true;

                            case R.id.find_member:
                                //handle menu3 click
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                //displaying the popup
                popup.show();

            }
        });





    }

    private void Load(View view) {
        Intent intent=new Intent(view.getContext(), MainActivity.class);
        intent.putExtra("mark",1);

    }


    @Override
    public int getItemCount() {
        return housesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView houseNameTv,totalFlatTv,managerContactTv,managerName;
        private Button flatBtn,complainBtn,editBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            houseNameTv=itemView.findViewById(R.id.houseNameTv);
            totalFlatTv=itemView.findViewById(R.id.totalFlatTv);
            managerName=itemView.findViewById(R.id.managerName);
            managerContactTv=itemView.findViewById(R.id.managerContactTv);

            flatBtn=itemView.findViewById(R.id.flatBtn);
            complainBtn=itemView.findViewById(R.id.complainBtn);
            editBtn=itemView.findViewById(R.id.editBtn);
        }
    }
}
