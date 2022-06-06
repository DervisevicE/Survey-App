package ba.etf.rma22.projekat.data.repositories

import retrofit2.http.Url

class ApiConfig {

    companion object{
        var baseURL: String = "https://rma22ws.herokuapp.com"
    }

    //private var Url : String = "https://rma22ws.herokuapp.com"

    fun postaviBaseURL(baseUrl:String) : Unit{
        baseURL= baseUrl
    }

    fun getUrl() : String {
        return baseURL
    }
}