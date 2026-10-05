package com.gabo.app6_supabase;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SupabaseApi {

    // LEER (READ)
    @GET("rest/v1/mascotas?select=*")
    Call<List<Mascota>> getMascotas();

    // CREAR (CREATE)
    @POST("rest/v1/mascotas")
    Call<Void> createMascota(@Body Mascota mascota);

    // ACTUALIZAR (UPDATE)
    @PATCH("rest/v1/mascotas")
    Call<Void> updateMascota(@Query("id") String eqId, @Body Mascota mascota);

    // ELIMINAR (DELETE)
    @DELETE("rest/v1/mascotas")
    Call<Void> deleteMascota(@Query("id") String eqId);
}