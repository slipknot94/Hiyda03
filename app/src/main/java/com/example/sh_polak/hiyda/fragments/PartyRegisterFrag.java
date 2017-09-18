package com.example.sh_polak.hiyda.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;
import com.example.sh_polak.hiyda.LoginActivity;
import com.example.sh_polak.hiyda.PartiesList;
import com.example.sh_polak.hiyda.PremissionManger;
import com.example.sh_polak.hiyda.R;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sh-polak on 16/09/2017.
 */

public class PartyRegisterFrag extends Fragment{
    EditText partyName, partLocation;
    int REQUEST_IMAGE = 2;
    int WRITE_IMAGE = 5;
    int IMAGEREQUEST = 6;
    Uri selectedImage;
    ImageView partyImage;
    File f;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.party_register_frag,container,false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
