package mx.tec.dataaccess

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONException
import org.json.JSONObject

fun getCountryInfo() {
    val url = "https://disease.sh/v3/covid-19/countries"
    val jsonArrayRequest = JsonArrayRequest( Request.Method.GET, url, null,
        {response ->
            try {
                for (i in 0 until response.length()) {
                    val country: JSONObject = response.getJSONObject(i)
                    val title = country.getString("country")
                    val cases = country.getInt("cases")
                    val deaths = country.getInt("deaths")
                    val recovered = country.getInt("recovered")
                    val flag = country.getString("flag")

                    val tmp = Country(1, title,cases,deaths,recovered,"flag")
                    DataProvider.countryList.add(tmp)
                    Log.e("Country: $title, Cases $cases",title)
                }
            }catch (e: JSONException) {
                Log.e("JsonException",e.toString())
            }
        },
        { error ->
            Log.e("ArrayRequest",error.cause.toString())
        }
    )
}

object DataProvider {
    var countryList = mutableListOf<Country>()
}