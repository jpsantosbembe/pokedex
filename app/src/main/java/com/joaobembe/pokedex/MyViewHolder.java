package com.joaobembe.pokedex;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nameView;
    ImageView imageView;
    CardView cardView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ivProfile);
        nameView = itemView.findViewById(R.id.twName);
        cardView = itemView.findViewById(R.id.cardView);

    }
}
