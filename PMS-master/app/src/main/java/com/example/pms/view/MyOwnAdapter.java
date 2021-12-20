package com.example.pms.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pms.Controller.AHNMS;
import com.example.pms.Model.Plant;
import com.example.pms.R;

public class MyOwnAdapter extends RecyclerView.Adapter<MyOwnAdapter.MyHolder>{

    AHNMS potsController;
    Context ctx;
    MyOwnAdapter(Context context){
        potsController = AHNMS.getInstance();
        ctx = context;
    }

    @NonNull
    @Override
    public MyOwnAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater in = LayoutInflater.from(ctx);
        View myView =   in.inflate(R.layout.pots_list, parent, false);
        return new MyHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnAdapter.MyHolder holder, int position) {
        if(checkPots(position)){
            Plant plant = potsController.retrieveInfo(position);
            holder.name.setText(potsController.getPotName(position));
            if(plant.getPlanttype().compareTo("daffodils") == 0){
                holder.image.setImageResource(R.drawable.daffoldils);
            }
            else if(plant.getPlanttype().compareTo("sunflower") == 0){
                holder.image.setImageResource(R.drawable.sunflower);
            }
            else if(plant.getPlanttype().compareTo("rose") == 0){
                holder.image.setImageResource(R.drawable.rose);
            }
            else if(plant.getPlanttype().compareTo("cactus") == 0){
                holder.image.setImageResource(R.drawable.cactus);
            }
            else if(plant.getPlanttype().compareTo("lilly") == 0){
                holder.image.setImageResource(R.drawable.lily);
            }
            //holder.image.setImageResource(R.drawable.sunflower);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(ctx, PlantDetailScreen.class);
                    i1.putExtra("idx",holder.getAdapterPosition());

                    ctx.startActivity(i1);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return potsController.getNoOfPots();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.plantName);
            image =(ImageView) itemView.findViewById(R.id.plantImage);
        }
    }

    public boolean checkPots(int index){
        if(index < potsController.getNoOfPots()){
            return true;
        }
        else{
            return false;
        }
    }
}
