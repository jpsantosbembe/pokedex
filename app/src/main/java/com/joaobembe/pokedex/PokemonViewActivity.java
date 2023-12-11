package com.joaobembe.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.joaobembe.pokedex.model.Pokemon;
import com.joaobembe.pokedex.network.RequestHttp;

import org.json.JSONArray;

import java.util.HashMap;

public class PokemonViewActivity extends AppCompatActivity {

    View backView;
    TextView tvNomePoke;
    ImageButton ibtBack;
    ImageButton ibtFav;
    ImageView ivAmigo;
    ImageView ivFoto;
    ChipGroup chipTipos;
    TextView tvSobre;
    TextView tvPeso;
    TextView tvAltura;
    TextView tvHab1, tvHab2;
    TextView tvAtributos;
    ProgressBar pbHP, pbATK, pbDEF, pbSATK, pbSDEF, pbSPD;
    TextView tvHP, tvATK, tvDEF, tvSATK,tvSDEF, tvSPD;
    TextView lbHP, lbATK, lbDEF, lbSATK, lbSDEF, lbSPD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_view);

        backView = findViewById(R.id.backView);
        tvNomePoke = findViewById(R.id.tvNomePoke);
        ibtFav = findViewById(R.id.ibtFav);
        ibtBack = findViewById(R.id.ibtBack);
        ivAmigo = findViewById(R.id.ivAmigo);
        ivFoto = findViewById(R.id.ivFoto);
        chipTipos = findViewById(R.id.chipHabilidades);
        tvSobre = findViewById(R.id.tvSobre);
        tvAtributos = findViewById(R.id.tvAtributos);
        tvPeso = findViewById(R.id.tvPeso);
        tvAltura = findViewById(R.id.tvAltura);
        tvHab1 = findViewById(R.id.tvHab1);
        tvHab2 = findViewById(R.id.tvHab2);
        pbHP = findViewById(R.id.pbHP);
        pbATK = findViewById(R.id.pbATK);
        pbDEF = findViewById(R.id.pbDEF);
        pbSATK = findViewById(R.id.pbSATK);
        pbSDEF = findViewById(R.id.pbSDEF);
        pbSPD = findViewById(R.id.pbSPD);
        tvHP = findViewById(R.id.tvHP);
        tvATK = findViewById(R.id.tvATK);
        tvDEF = findViewById(R.id.tvDEF);
        tvSATK = findViewById(R.id.tvSATK);
        tvSDEF = findViewById(R.id.tvSDEF);
        tvSPD = findViewById(R.id.tvSPD);
        lbHP = findViewById(R.id.lbHP);
        lbATK = findViewById(R.id.lbATK);
        lbDEF = findViewById(R.id.lbDEF);
        lbSATK = findViewById(R.id.lbSATK);
        lbSDEF = findViewById(R.id.lbSDEF);
        lbSPD = findViewById(R.id.lbSPD);

        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra("pokemon");

        try {

            String ehFav = new RequestHttp().get(getResources().getString(R.string.app_host) + "/ehfavorito/" + pokemon.getIdNacional());
            JSONArray jsonArray = new JSONArray(ehFav);

            if (Boolean.parseBoolean(jsonArray.getJSONObject(0).getString("verificarfavorito"))) {
                ibtFav.setImageResource(R.drawable.baseline_star_rate_24);
            } else {
                ibtFav.setImageResource(R.drawable.baseline_star_outline_24);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            String ehFav = new RequestHttp().get(getResources().getString(R.string.app_host) + "/ehamigo/" + pokemon.getIdNacional());
            JSONArray jsonArray = new JSONArray(ehFav);

            if (Boolean.parseBoolean(jsonArray.getJSONObject(0).getString("verificaramigo"))) {
                ivAmigo.setVisibility(View.VISIBLE);
            } else {
                ivAmigo.setVisibility(View.INVISIBLE);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String ehFav = new RequestHttp().get(getResources().getString(R.string.app_host) + "/ehamigo/" + pokemon.getIdNacional());

                    JSONArray jsonArray = new JSONArray(ehFav);

                    if (Boolean.parseBoolean(jsonArray.getJSONObject(0).getString("verificaramigo"))) {
                        new RequestHttp().get(getResources().getString(R.string.app_host) + "/removeramigo/" + pokemon.getIdNacional());
                        ivAmigo.setVisibility(View.INVISIBLE);
                    } else {
                        System.out.println("oioisoisoisosiss");
                        new RequestHttp().get(getResources().getString(R.string.app_host) + "/definiramigo/" + pokemon.getIdNacional());
                        ivAmigo.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ibtFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String ehFav = new RequestHttp().get(getResources().getString(R.string.app_host) + "/ehfavorito/" + pokemon.getIdNacional());

                    JSONArray jsonArray = new JSONArray(ehFav);

                    if (Boolean.parseBoolean(jsonArray.getJSONObject(0).getString("verificarfavorito"))) {
                        new RequestHttp().get(getResources().getString(R.string.app_host) + "/removerfavorito/" + pokemon.getIdNacional());
                        ibtFav.setImageResource(R.drawable.baseline_star_outline_24);
                    } else {
                        new RequestHttp().get(getResources().getString(R.string.app_host) + "/definirfavorito/" + pokemon.getIdNacional());
                        ibtFav.setImageResource(R.drawable.baseline_star_rate_24);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ibtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getWindow().setStatusBarColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));

        backView.setBackgroundColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));

        tvNomePoke.setText(formatarPokemon(pokemon.getIdNacional(), pokemon.getNome()));

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.pokeball) // imagem a ser exibida enquanto a imagem real é carregada
                .error(R.drawable.pokeball) // imagem a ser exibida em caso de erro no carregamento
                .diskCacheStrategy(DiskCacheStrategy.NONE);// desativar o cache para fins de demonstração

        Glide.with(this)
                .load(formatarUrlPokemon(pokemon.getIdNacional()))
                .apply(requestOptions) // aplicar as opções de carregamento
                .into(ivFoto);


        for (int i = 0; i < pokemon.getTipos().size(); i++){
            Chip chip = new Chip(new ContextThemeWrapper(this, com.google.android.material.R.style.Widget_Material3_Chip_Assist_Elevated), null, 0);
            chip.setText(pokemon.getTipos().get(i).getNomeTipo());
            chip.setAllCaps(true);
            chip.setTextColor(Color.WHITE);
            chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(getColorCode(pokemon.getTipos().get(i).getNomeTipo()))));
            chipTipos.addView(chip);
        }

        tvSobre.setTextColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));

        tvPeso.setText(String.valueOf(pokemon.getPeso()));
        tvAltura.setText(String.valueOf(pokemon.getAltura()));
        tvHab1.setText(String.valueOf(pokemon.getHabilidades().get(0).getNomeHabilidade()));
        tvHab2.setText(String.valueOf(pokemon.getHabilidades().get(1).getNomeHabilidade()));

        tvAtributos.setTextColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));

        lbHP.setTextColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));
        lbATK.setTextColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));
        lbDEF.setTextColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));
        lbSATK.setTextColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));
        lbSDEF.setTextColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));
        lbSPD.setTextColor(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo())));

        tvHP.setText(String.valueOf(pokemon.getVida()));
        tvATK.setText(String.valueOf(pokemon.getAtaque()));
        tvDEF.setText(String.valueOf(pokemon.getDefesa()));
        tvSATK.setText(String.valueOf(pokemon.getAtaqueEspecial()));
        tvSDEF.setText(String.valueOf(pokemon.getDefesaEspecial()));
        tvSPD.setText(String.valueOf(pokemon.getVelocidade()));

        pbHP.setProgressTintList(ColorStateList.valueOf(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo()))));
        pbATK.setProgressTintList(ColorStateList.valueOf(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo()))));
        pbDEF.setProgressTintList(ColorStateList.valueOf(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo()))));
        pbSATK.setProgressTintList(ColorStateList.valueOf(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo()))));
        pbSDEF.setProgressTintList(ColorStateList.valueOf(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo()))));
        pbSPD.setProgressTintList(ColorStateList.valueOf(Color.parseColor(getColorCode(pokemon.getTipos().get(0).getNomeTipo()))));

        pbHP.setProgress(pokemon.getVida());
        pbATK.setProgress(pokemon.getAtaque());
        pbDEF.setProgress(pokemon.getDefesa());
        pbSATK.setProgress(pokemon.getAtaqueEspecial());
        pbSDEF.setProgress(pokemon.getDefesaEspecial());
        pbSPD.setProgress(pokemon.getVelocidade());


    }

    @Override
    public void onBackPressed() {
        finish();
        // super.onBackPressed();
    }

    private String getColorCode(String type) {
        HashMap<String, String> colorMap = new HashMap<>();
        colorMap.put("Normal", "#A8A77A");
        colorMap.put("Fighting", "#C22E28");
        colorMap.put("Flying", "#A98FF3");
        colorMap.put("Poison", "#A33EA1");
        colorMap.put("Ground", "#E2BF65");
        colorMap.put("Rock", "#B6A136");
        colorMap.put("Bug", "#A6B91A");
        colorMap.put("Ghost", "#735797");
        colorMap.put("Steel", "#B7B7CE");
        colorMap.put("Fire", "#EE8130");
        colorMap.put("Water", "#6390F0");
        colorMap.put("Grass", "#7AC74C");
        colorMap.put("Electric", "#F7D02C");
        colorMap.put("Psychic", "#F95587");
        colorMap.put("Ice", "#96D9D6");
        colorMap.put("Dragon", "#6F35FC");
        colorMap.put("Dark", "#705746");
        colorMap.put("Fairy", "#D685AD");
        return colorMap.get(type);
    }

    private String formatarPokemon(int idNacional, String nomePokemon) {
        String idFormatado = String.format("#%04d", idNacional);
        return idFormatado + " - " + nomePokemon;
    }
    private String formatarUrlPokemon(int idNacional) {
        String idFormatado = String.format("%03d", idNacional);
        return "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + idFormatado + ".png";
    }

}