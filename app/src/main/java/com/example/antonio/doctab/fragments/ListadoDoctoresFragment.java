package com.example.antonio.doctab.fragments;

 import android.content.Context;
 import android.content.Intent;
 import android.os.Bundle;
 import android.support.annotation.Nullable;
 import android.support.v4.app.Fragment;
 import android.support.v4.app.FragmentManager;
 import android.support.v4.app.FragmentTransaction;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.Button;

 import com.example.antonio.doctab.MainRegisterActivity;
 import com.example.antonio.doctab.R;
 import com.example.antonio.doctab.Utils.Constants;
 import com.example.antonio.doctab.helpers.DecodeExtraHelper;
 import com.example.antonio.doctab.models.Usuarios;



/**
 * Created by Ricardo on 13/02/2018.
 */

public class ListadoDoctoresFragment extends Fragment implements View.OnClickListener{

    private static Usuarios _SESSION_USER;
    private Button btnRegistrar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listado_doctores, container, false);

        _SESSION_USER = (Usuarios) getActivity().getIntent().getSerializableExtra(Constants.KEY_SESSION_USER);

        btnRegistrar = (Button) view.findViewById(R.id.btn_agregar_citas);
        btnRegistrar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction mainFragment = fragmentManager.beginTransaction();
        mainFragment.replace(R.id.listado_doctores_container, new DoctoresFragment(), Constants.FRAGMENT_DOCTOR);
        mainFragment.commit();

        getActivity().setTitle("Los docs");

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_agregar_citas:
                DecodeExtraHelper extra = new DecodeExtraHelper();

                extra.setTituloActividad(getString(Constants.TITLE_ACTIVITY.get(v.getId())));
                extra.setTituloFormulario(getString(R.string.default_form_title_new));
                extra.setAccionFragmento(Constants.ACCION_REGISTRAR);
                extra.setFragmentTag(Constants.ITEM_FRAGMENT.get(v.getId()));

                Intent intent = new Intent(getActivity(), MainRegisterActivity.class);
                intent.putExtra(Constants.KEY_MAIN_DECODE, extra);
                intent.putExtra(Constants.KEY_SESSION_USER, _SESSION_USER);
                startActivity(intent);

                break;

        }
    }


}