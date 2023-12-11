package com.joaobembe.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;

import com.joaobembe.pokedex.model.Habilidade;
import com.joaobembe.pokedex.model.Pokedex;
import com.joaobembe.pokedex.model.Pokemon;
import com.joaobembe.pokedex.model.Tipo;
import com.joaobembe.pokedex.network.RequestHttp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        getWindow().setStatusBarColor(Color.BLACK);


        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() ->{
            try {
                RequestHttp requestHttp = new RequestHttp();

                String resposta = requestHttp.get(getResources().getString(R.string.app_host) + "/pokemons");

                JSONArray jsonArray = new JSONArray(resposta);

                List<Pokemon> pokemons = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    List<Habilidade> habilidades = new ArrayList<>();

                    for (int x = 0; x < jsonArray.getJSONObject(i).getJSONArray("habilidades").length(); x++){
                        Habilidade habilidade = new Habilidade(jsonArray.getJSONObject(i).getJSONArray("habilidades").getString(x));
                        habilidades.add(habilidade);
                    }

                    List<Tipo> tipos = new ArrayList<>();

                    for (int x = 0; x < jsonArray.getJSONObject(i).getJSONArray("tipos").length(); x++){
                        Tipo tipo = new Tipo(jsonArray.getJSONObject(i).getJSONArray("tipos").getString(x));
                        tipos.add(tipo);

                    }

                    Pokemon pokemon = new Pokemon(
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("idnacional")),
                            jsonArray.getJSONObject(i).getString("nome"),
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("vida")),
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("ataque")),
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("ataqueespecial")),
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("defesa")),
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("defesaespecial")),
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("velocidade")),
                            Integer.parseInt(jsonArray.getJSONObject(i).getString("proxevolucao")),
                            Double.parseDouble(jsonArray.getJSONObject(i).getString("peso")),
                            Double.parseDouble(jsonArray.getJSONObject(i).getString("altura")),
                            habilidades,
                            tipos
                    );

                    pokemons.add(pokemon);
                }

                Pokedex pokedex = new Pokedex(pokemons);

                Intent intent= new Intent(this, PokedexActivity.class);
                intent.putExtra("pokemon", pokedex);
                startActivity(intent);
                finish();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}