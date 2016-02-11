package com.as.logitechchallenge.service;


import com.as.logitechchallenge.model.DeviceListResponse;
import com.as.logitechchallenge.utils.Constants;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Suresh on 09-02-2016.
 *
 */

public interface ServiceAPI {

    @GET(Constants.ServiceAPIURLs.URL_FETCH_LIST)
    public void fetchDeviceList(Callback<DeviceListResponse> deviceListResponse);

}


