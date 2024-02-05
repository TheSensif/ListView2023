package com.thesensif.listview2023;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    // Model: Record (intents=puntuació, nom)
    class Record {
        public int intents;
        public String nom;

        public Record(int _intents, String _nom) {
            intents = _intents;
            nom = _nom;
        }
    }

    // Model = Taula de records: utilitzem ArrayList
    ArrayList<Record> records;

    // ArrayAdapter serà l'intermediari amb la ListView
    ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] nombres = {"Sergi","Alejandro","Miguel","Alex","Sara","Maria","Carla","Jose","Javier",
                "Noa","Alberto","Ivan","Cristian","Biel","Nati"};
        List<String> listNombres = Arrays.asList(nombres);
        // Inicialitzem model
        records = new ArrayList<Record>();
        // Afegim alguns exemples
        records.add(new Record(33, "Manolo"));
        records.add(new Record(12, "Pepe"));
        records.add(new Record(42, "Laura"));

        // Inicialitzem l'ArrayAdapter amb el layout pertinent
        adapter = new ArrayAdapter<Record>(this, R.layout.list_item, records) {
            @Override
            public View getView(int pos, View convertView, ViewGroup container) {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if (convertView == null) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.nom)).setText(getItem(pos).nom);
                ((TextView) convertView.findViewById(R.id.intents)).setText(Integer.toString(getItem(pos).intents));
                return convertView;
            }

        };

        // busquem la ListView i li endollem el ArrayAdapter
        ListView lv = (ListView) findViewById(R.id.recordsView);
        lv.setAdapter(adapter);

        // botó per afegir entrades a la ListView
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();

                // Generar un número aleatorio en el rango de 15 a 20 (incluyendo ambos extremos)
                int ran = random.nextInt(6) + 15;
                for (int i=0;i<ran;i++) {
                    records.add(new Record((int) (Math.random() * 100), listNombres.get((int) (Math.random() * 15))));
                }
                // notificar l'adapter dels canvis al model
                adapter.notifyDataSetChanged();
            }
        });
    }
}