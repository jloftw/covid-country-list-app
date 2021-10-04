package mx.tec.dataaccess

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

fun getCountryInfo(context: Context) {
    val url = "https://disease.sh/v3/covid-19/countries"
    Log.d("Request","before")
    val jsonArrayRequest = JsonArrayRequest( Request.Method.GET, url, null,
        {response ->
            try {
                /*val countries = Gson().fromJson(response,Country::class.java)
                DataProvider.countryList.addAll(countries)*/

                for (i in 0 until response.length()) {
                    val country: JSONObject = response.getJSONObject(i)
                    val title = country.getString("country")
                    val cases = country.getInt("cases")
                    val deaths = country.getInt("deaths")
                    val recovered = country.getInt("recovered")
                    val info = country.getJSONObject("countryInfo")
                    val flag = info.getString("flag")

                    val tmp = Country(title,cases,deaths,recovered,CountryInfo(flag))
                    DataProvider.countryList.add(tmp)
                    Log.d("Request",title)
                }
            }catch (e: JSONException) {
                Log.e("JsonException",e.toString())
            }
        },
        { error ->
            Log.e("ArrayRequest",error.cause.toString())
        }
    )
    VolleySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest)
}