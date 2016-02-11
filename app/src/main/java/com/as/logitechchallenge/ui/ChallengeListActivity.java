package com.as.logitechchallenge.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.as.logitechchallenge.R;
import com.as.logitechchallenge.model.DeviceListResponse;
import com.as.logitechchallenge.service.ServiceAPI;
import com.as.logitechchallenge.utils.Constants;
import com.as.logitechchallenge.utils.Utils;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by suresh on 09-02-2016.
 */
public class ChallengeListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public RestAdapter serviceAdapter;
    private RequestInterceptor requestInterceptor;
    private SwipeRefreshLayout swipeRefreshLayout;
    public ListView listView;
    ArrayList<String> deviceNameList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challengelist);

        listView = (ListView) findViewById(R.id.devicenamelist);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        deviceNameList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.row, deviceNameList);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);*/

        listView.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        fetchDeviceList();
                                    }
                                }
        );
    }

    @Override
    public void onRefresh() {
        fetchDeviceList();
    }

        // Network calls
    public RestAdapter getServiceAdapter(){
        OkHttpClient okHttp = new OkHttpClient();
        okHttp.setConnectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);
        okHttp.setRetryOnConnectionFailure(true);

        if(serviceAdapter == null){
            serviceAdapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.ServiceAPIURLs.getBaseURL())
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setClient(new OkClient(okHttp))
                    .build();
        }
        return serviceAdapter;
    }
    public ServiceAPI getServicesAPI(){
        return getServiceAdapter().create(ServiceAPI.class);
    }


    public void fetchDeviceList(){
        swipeRefreshLayout.setRefreshing(true);
        if(Utils.isNetworkAvailable(ChallengeListActivity.this)){
            (new Thread(new Runnable() {
                @Override
                public void run() {
                    getServicesAPI().fetchDeviceList(new Callback<DeviceListResponse>() {
                        @Override
                        public void success(DeviceListResponse deviceList, Response arg1) {
                            deviceNameList.clear();
                            for(DeviceListResponse.Devices device  : deviceList.getDevices()){
                                deviceNameList.add(device.getName());
                            }
                           // deviceNameList.add(deviceList.)
                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            error.printStackTrace();
                            Toast.makeText(ChallengeListActivity.this,"Something went wrong! Please pull down to the screen to refresh..", Toast.LENGTH_LONG).show();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            })).start();
        }else{
            Toast.makeText(ChallengeListActivity.this,"No network connection! Please enable the internet..", Toast.LENGTH_LONG).show();
            swipeRefreshLayout.setRefreshing(false);
        }


    }



}
