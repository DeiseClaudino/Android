package br.com.alura.ageda;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.alura.ageda.dao.AlunoDao;
import br.com.alura.ageda.modelo.Aluno;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getMapAsync( this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng posicaoDaEscola = PegaCoordenadaDoEndereco("Rua Vergeiro 3185, Vila Mariana, São Paulo");
        if (posicaoDaEscola != null) {

            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoDaEscola, 17);
            googleMap.moveCamera(update);
        }

        AlunoDao alunoDao = new AlunoDao(getContext());
        for (Aluno aluno : alunoDao.buscaAlunos()) {
            LatLng coordenada = PegaCoordenadaDoEndereco(aluno.getEndereco());

            if (coordenada != null) {

                MarkerOptions marcador = new MarkerOptions();
                marcador.position(coordenada);
                marcador.title(aluno.getNome());
                marcador.snippet(String.valueOf(aluno.getNota()));
                googleMap.addMarker(marcador);

            }

        }
        alunoDao.close();

        new Localizador(getContext(), googleMap);

    }

    private LatLng PegaCoordenadaDoEndereco(String endereco) {
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados = geocoder.getFromLocationName(endereco, 1);

            if (!resultados.isEmpty()){
               LatLng posicao = new LatLng(resultados.get(0).getLatitude(), resultados.get(0).getLongitude());
               return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

