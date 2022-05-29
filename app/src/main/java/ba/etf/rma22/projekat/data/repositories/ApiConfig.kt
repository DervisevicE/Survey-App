package ba.etf.rma22.projekat.data.repositories

import retrofit2.http.Url

class ApiConfig {

    private var Url : String = "https://rma22ws.herokuapp.com"

    fun postaviBaseURL(baseUrl:String) : Unit{
        Url = baseUrl
    }

    fun getUrl() : String {
        return Url
    }
}