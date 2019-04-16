package com.claire.traveldiary.map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.claire.traveldiary.MainActivity;
import com.claire.traveldiary.R;
import com.claire.traveldiary.data.room.DiaryDAO;
import com.claire.traveldiary.data.room.DiaryDatabase;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MapFragment extends Fragment implements OnMapReadyCallback, MapContract.View {

    private static final String TAG = "MapFragment";

    private GoogleMap mMap;
    private Marker mMarker;

    private MapContract.Presenter mPresenter;

    private DiaryDatabase mDatabase;

    private float mLat;
    private float mLng;
    private ArrayList<Marker> mMarkerArray = new ArrayList<Marker>();

    public MapFragment() {

    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setPresenter(MapContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = DiaryDatabase.getIstance(getContext());


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //map style
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.mapstyle_retro));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        //database
        DiaryDAO diaryDAO = mDatabase.getDiaryDAO();

        //find all my places
        for (int i = 0; i < diaryDAO.getDiarys().size(); i++ ) {

            LatLng location = new LatLng(diaryDAO.getDiarys().get(i).getDiaryPlace().getLat(), diaryDAO.getDiarys().get(i).getDiaryPlace().getLng());

            mMap.addMarker(new MarkerOptions()
                    .position(location)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .title(diaryDAO.getDiarys().get(i).getDiaryPlace().getPlaceName()));

            //mMarkerArray.add(marker);

            int finalI = i;
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    ((MainActivity) getActivity()).openShowDiaryDialog(diaryDAO.getDiarys().get(finalI));
                }
            });
        }


    }

}
