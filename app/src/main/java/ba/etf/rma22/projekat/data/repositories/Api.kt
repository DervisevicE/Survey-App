package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface Api {

    //istrazivanje
     @GET("istrazivanje")
     suspend fun getIstrazivanja() : List<Istrazivanje>

     @GET("istrazivanje/{id}")
     suspend fun dajIstrazivanjaSaId(
      @Path("id") id: Int
     ) : List<Istrazivanje>

     @GET("grupa/{gid}/istrazivanje")
     suspend fun dajIstrazivanjaGrupeSaId(
      @Path("gid") gid: Int
     ) : List<Istrazivanje>

     //grupa
     @GET("anketa/{id}/grupa")
     suspend fun dajGrupeAnkete(
     @Path("id") id: Int
     ) : List<Grupa>

     @POST("grupa/{gid}/student/{id}")
     suspend fun upisiUGrupu(@Path("gid") gid : Int, @Path("id") id : String)

     @GET("student/{id}/grupa")
     suspend fun dajUpisaneGrupe(
      @Path("id") id: String
     ) : List<Grupa>

     @GET("grupa")
     suspend fun getGrupe() : List<Grupa>

     @GET("grupa/{id}")
     suspend fun dajGrupeSaId(
      @Path("id") id: Int
     ) : List<Grupa>

     //anketa
     @GET("anketa")
     suspend fun getAllAnkete(@Query("offset") offset:Int) : List<Anketa>

     @GET("anketa/{id}")
     suspend fun getById(@Path("id") idAnkete : Int) : Anketa

     @GET("grupa/{id}/ankete")
     suspend fun getUpisane(@Path("id") idGrupe : Int) : List<Anketa>

     //odgovor
     @GET("student/{id}/anketataken/{ktid}/odgovori")
     suspend fun getOdgovoriAnketa(
      @Path("id") id: Int,
      @Path("ktid") ktid: Int
     ) : List<Odgovor>

     @POST("student/{id}/anketataken/{ktid}/odgovor")
     suspend fun postaviOdgovorAnketa(
      @Path("id") id: Int,
      @Path("ktid") ktid: Int,
      @Body odgovor: Odgovor
     ) : Response<Odgovor>

     //anketaTaken
     @GET("student/{id}/anketataken")
     suspend fun getPoceteAnkete(
      @Path("id") id : String
     ) : List<AnketaTaken>

     @POST("student/{id}/anketa/{kid}")
     suspend fun zapocniAnketu(
      @Path("kid") idAnkete: Int,
      @Path("id") id : String,
      @Body anketaTaken: AnketaTaken
     ) : Response<AnketaTaken>

     //account
     @GET("student/{id}")
     suspend fun dajStudentaSaHashom(
      @Path("id") id: String
     )

     @DELETE("student/{id}/upisugrupepokusaji")
     suspend fun ksks()

     //pitanje
     @GET("anketa/{id}/pitanja")
     suspend fun getPitanja(
      @Path("id") idAnkete: Int
     ) : List<Pitanje>


}