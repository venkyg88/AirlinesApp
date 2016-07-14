package venkat.com.kayakapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import venkat.com.kayakapp.Adapter.NewAirlineAdapter;
import venkat.com.kayakapp.Controller.Access;
import venkat.com.kayakapp.api.KayakApi;
import venkat.com.kayakapp.model.Airlines;

/**
 * Created by venkatgonuguntala on 3/19/16.
 */
public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private KayakApi kayakApi;
    private NewAirlineAdapter airlinesAdapter;
    private Switch favSwitch;
    private List<Airlines> airlinesList;
    private RecyclerView recyclerView;
    private List<Airlines> tempList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        kayakApi = Access.getInstance().getKayakAirlinesAPi();
        setContentView(R.layout.airlines_recyclerview);
        airlinesList = new ArrayList<Airlines>();
        tempList = new CopyOnWriteArrayList<>();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kayak Airlines");
        favSwitch = (Switch) findViewById(R.id.switch_fav);
        recyclerView = (RecyclerView) findViewById(R.id.airlinesList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (kayakApi != null) {
            kayakApi.getAirlinesList(new Callback<List<Airlines>>() {
                @Override
                public void success(List<Airlines> airlines, Response response) {
                    setAirlines(airlines);
                    MainActivity.this.airlinesList = airlinesList;
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e(TAG, error+"");
                }
            });
        }

        favSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isSwitched) {
                if (isSwitched) {
                    updateCheckedList(airlinesAdapter.getList());
                    airlinesAdapter.setData(getCheckedData(),isSwitched);
                } else {
                    Log.d(TAG,"Airlines List size - "+airlinesList);
                    airlinesAdapter.setData(airlinesList, isSwitched);
                }
            }
        });
    }

    public void updateCheckedList(List<Airlines> newAirLinesList){
        airlinesList.clear();
        airlinesList.addAll(newAirLinesList);
    }

    private List<Airlines> getCheckedData(){
        tempList.clear();
        for (Airlines airline:airlinesList){
            if(airline.isChecked()) {
                tempList.add(airline);
            }
        }
        return tempList;
    }

    private void setAirlines(final List<Airlines> airlinesList) {
        airlinesAdapter = new NewAirlineAdapter(airlinesList, this);
        recyclerView.setAdapter(airlinesAdapter);
    }

    @Override
    protected void onDestroy() {
        //EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
