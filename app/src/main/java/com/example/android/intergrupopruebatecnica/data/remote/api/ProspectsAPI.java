package com.example.android.intergrupopruebatecnica.data.remote.api;

import com.example.android.intergrupopruebatecnica.data.remote.response.ProspectResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProspectsAPI {

    String BASE_URL = "https://intergrupodb.firebaseio.com/";

    @GET("/prospects.json")
    Observable<Map<String, ProspectResponse>> getProspects();

    @GET("/prospects/{id}.json")
    Observable<ProspectResponse> getProspect(@Path("id") int id);

    @POST("/prospects")
    Observable<ProspectResponse> addProspect(@Body ProspectResponse prospect);

    @PUT("/prospects/{id}.json")
    Observable<ProspectResponse> editProspect(@Path("id") int id, @Body ProspectResponse prospect);

    @DELETE("/prospects/{id}.json")
    Observable<ProspectResponse> deleteProspect(@Path("id") int id);
}