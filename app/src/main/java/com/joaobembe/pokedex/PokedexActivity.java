package com.joaobembe.pokedex;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.joaobembe.pokedex.model.Pokedex;
import com.joaobembe.pokedex.network.RequestHttp;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokedexActivity extends AppCompatActivity {

    FloatingActionButton btScan;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);



        Pokedex pokedex = (Pokedex) getIntent().getSerializableExtra("pokemon");
        recyclerView = findViewById(R.id.recyclerView);

        btScan = findViewById(R.id.btScan);
        btScan.setOnClickListener(v -> {
            scanCode();
        });


        List<Item> items =  new ArrayList<Item>();

        for (int i = 0; i < pokedex.getPokemons().size(); i++){
            items.add(new Item(
                    pokedex.getPokemons().get(i).getNome(),
                    getColorCode(pokedex.getPokemons().get(i).getTipos().get(0).getNomeTipo()),
                    String.valueOf(pokedex.getPokemons().get(i).getIdNacional())));
        }

        //recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), getIntent(), items));
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Escanei seu novo Pokémon");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(ScanNewPokemon.class);
        barLaucher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(PokedexActivity.this);
                builder.setTitle("Parabéns");
                JSONObject jsonObject = new JSONObject(result.getContents());
                builder.setMessage("Você capturou um " + jsonObject.getString("nome_pokemon") + "!");
                RequestHttp requestHttp = new RequestHttp();
                System.out.println(requestHttp.post(getResources().getString(R.string.app_host) + "/inserir_pokemon", result.getContents()));
                builder.setPositiveButton("Eba!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(PokedexActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();

            } catch (JSONException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    });

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
}