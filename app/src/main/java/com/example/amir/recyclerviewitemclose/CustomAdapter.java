package com.example.amir.recyclerviewitemclose;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdapter extends
        RecyclerView.Adapter<CustomAdapter.ViewHolder>
{
    private List<Uri> images;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View

                view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i)
    {
        Glide.with(holder.itemView.getContext())
                .load(images.get(i).getPath())
                .into(holder.imgContent);

        holder.imgClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgClose,imgContent;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imgClose=itemView.findViewById(R.id.img_close);
            imgContent=itemView.findViewById(R.id.img_content);
        }
    }
}
