package com.thomascbeerten.verderetesten.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fourmob.panningview.PanningView;


public class FragmentPage1 extends Fragment {

    PanningView panningView;

    public FragmentPage1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_page1, container, false);

        // Inflate the layout for this fragment
        panningView = (PanningView) v.findViewById(R.id.panningView);
        if (panningView != null) {
            panningView.startPanning();
            Log.d("panningview", "panningview not null");
        } else {
            Log.d("panningview", "panningview null");
        }
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
