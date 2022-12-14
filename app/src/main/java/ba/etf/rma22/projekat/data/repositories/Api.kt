package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface Api {

    //istrazivanje
     @GET("/istrazivanje")
     suspend fun getIstrazivanja(
     @Query ("offset" ) offset: Int = 1
    ) : List<Istrazivanje>

     @GET("/istrazivanje/{id}")
     suspend fun dajIstrazivanjaSaId(
      @Path("id") id: Int
     ) : Istrazivanje

     @GET("/grupa/{gid}/istrazivanje")
     suspend fun dajIstrazivanjaGrupeSaId(
      @Path("gid") id: Int
     ) : List<Istrazivanje>

     //grupa
     @GET("/anketa/{id}/grupa")
     suspend fun dajGrupeAnkete(
     @Path("id") id: Int
     ) : List<Grupa>

     @POST("/grupa/{gid}/student/{id}")
     suspend fun upisiUGrupu(@Path("gid") gid : Int, @Path("id") id : String)

     @GET("/student/{id}/grupa")
     suspend fun dajUpisaneGrupe(
      @Path("id") id: String
     ) : List<Grupa>

     @GET("/grupa")
     suspend fun getGrupe() : List<Grupa>

     @GET("/grupa/{id}")
     suspend fun dajGrupeSaId(
      @Path("id") id: Int
     ) : Grupa

     //anketa
     @GET("/anketa")
     suspend fun getAllAnkete(@Query("offset") offset:Int) : List<Anketa>

     @GET("/anketa/{id}")
     suspend fun getById(@Path("id") idAnkete : Int) : Anketa

     @GET("/grupa/{id}/ankete")
     suspend fun getUpisane(@Path("id") idGrupe : Int) : List<Anketa>

     //odgovor
     @GET("/student/{id}/anketataken/{ktid}/odgovori")
     suspend fun getOdgovoriAnketa(
      @Path("ktid") ktid: Int,
      @Path("id") id: String = AccountRepository.getHash()
     ) :List<Odgovor>

     @POST("/student/{id}/anketataken/{ktid}/odgovor")
     suspend fun postaviOdgovorAnketa(
      @Path("ktid") ktid: Int,
      @Body odgovor: VratiZaOdgovor,
      @Path("id") id: String = AccountRepository.getHash()
     ) : Response<VratiOdgovor>

     //anketaTaken
     @GET("/student/{id}/anketataken")
     suspend fun getPoceteAnkete(
      @Path("id") id : String = AccountRepository.getHash()
     ) : Response<List<AnketaTaken>>

     @POST("/student/{id}/anketa/{kid}")
     suspend fun zapocniAnketu(
      @Path("kid") idAnkete: Int,
      @Path("id") id : String//,
      //@Body anketaTaken: AnketaTaken
     ) : AnketaTaken

     //account
     @GET("/student/{id}")
     suspend fun dajStudentaSaHashom(
      @Path("id") id: String
     ) : Account

     @DELETE("/student/{id}/upisugrupepokusaji")
     suspend fun ksks()

     //pitanje
     @GET("/anketa/{id}/pitanja")
     suspend fun getPitanja(
      @Path("id") idAnkete: Int
     ) : Response<List<Pitanje>>


}