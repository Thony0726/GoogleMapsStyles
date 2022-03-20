package com.example.googlemapsstyles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity  {
//    private Button btnMarcador;
    private GoogleMap map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

//        btnMarcador = (Button) findViewById(R.id.btnMarcador);
//
//        btnMarcador.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                insertarMarcador();
//            }
//        });
////
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync((OnMapReadyCallback) this);


//        Botton nav
        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView);
        btnNav.setOnNavigationItemSelectedListener(navListener);
        //Iniciamos el fragmato que deseamos
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new MapsFragment()).commit();

    }

//    private void insertarMarcador() {
//        mapa.addMarker(new MarkerOptions()
//                .position(new LatLng(40.3936945, -3.701519))
//                .title("Pais: España"));
//    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.itemMapa:
                    selectedFragment = new MapsFragment();
                    Toast.makeText(MainActivity.this, "Mapa", Toast.LENGTH_SHORT).show();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectedFragment).commit();
            return true;
        }
    };

    /*se controla la pulsacion del boton atras y cierra la aplicacion*/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder myBulid = new AlertDialog.Builder(this);
        myBulid.setMessage("¿Deseas salir de la aplicacion?");
        myBulid.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        myBulid.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = myBulid.create();
        dialog.show();
    }





}