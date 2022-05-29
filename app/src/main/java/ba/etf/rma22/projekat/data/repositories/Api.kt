package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import retrofit2.http.GET

interface Api {

    @GET("anketa")
    suspend fun getAll() : List<Anketa>
}