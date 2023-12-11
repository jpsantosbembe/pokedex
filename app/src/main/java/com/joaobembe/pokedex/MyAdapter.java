package com.joaobembe.pokedex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joaobembe.pokedex.model.Pokedex;
import com.joaobembe.pokedex.model.Pokemon;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    Intent intent;
    List<Item> items;

    public MyAdapter(Context context, Intent intent, List<Item> items) {
        this.context = context;
        this.items = items;
        this.intent = intent;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.pokeball) // imagem a ser exibida enquanto a imagem real é carregada
                .error(R.drawable.baseline_error_24) // imagem a ser exibida em caso de erro no carregamento
                .diskCacheStrategy(DiskCacheStrategy.NONE);// desativar o cache para fins de demonstração

        holder.nameView.setText(items.get(position).nome);
        holder.cardView.setCardBackgroundColor(Color.parseColor(items.get(position).cor));
        Glide.with(context)
                .load(formatarUrlPokemon(Integer.parseInt(items.get(position).idNacional)))
                .apply(requestOptions) // aplicar as opções de carregamento
                .into(holder.imageView);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pokedex pokedex = (Pokedex) intent.getSerializableExtra("pokemon");
                Pokemon pokemon = pokedex.getPokemons().get(position);
                Intent intent = new Intent(context, PokemonViewActivity.class);
                intent.putExtra("pokemon", pokemon);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public String formatarUrlPokemon(int idNacional) {
        String idFormatado = String.format("%03d", idNacional);
        return "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + idFormatado + ".png";
    }
}
