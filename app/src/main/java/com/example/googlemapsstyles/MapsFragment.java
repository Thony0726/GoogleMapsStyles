package com.example.googlemapsstyles;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {


    private Button btnvista;
    private GoogleMap map;

    private final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker markerPerth;
    private Marker markerSydney;
    private Marker markerBrisbane;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this, marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

         Return false to indicate that we have not consumed the event and that we wish
         for the default behavior to occur (which is for the camera to move such that the
         marker is centered and for the marker's info window to open, if it has one).
        return false;
    }






    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Add some markers to the map, and add a data object to each marker.
        markerPerth = map.addMarker(new MarkerOptions()
                .position(PERTH)
                .title("Perth"));
        markerPerth.setTag(0);

        markerSydney = map.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .title("Sydney"));
        markerSydney.setTag(0);

        markerBrisbane = map.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane"));
        markerBrisbane.setTag(0);

        // Set a listener for marker click.
        map.setOnMarkerClickListener(this);

    }
}