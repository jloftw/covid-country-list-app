package mx.tec.dataaccess

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun getCountryInfo(context: Context) {
    val url = "https://disease.sh/v3/covid-19/countries"
    Log.d("Request","before")
    val jsonArrayRequest = JsonArrayRequest( Request.Method.GET, url, null,
        {response ->
            try {
                for (i in 0 until response.length()) {
                    val country: JSONObject = response.getJSONObject(i)
                    val title = country.getString("country")
                    val cases = country.getInt("cases")
                    val deaths = country.getInt("deaths")
                    val recovered = country.getInt("recovered")
                    val info = country.getJSONObject("countryInfo")
                    val flag = info.getString("flag")

                    val tmp = Country(title,cases,deaths,recovered,flag)
                    DataProvider.countryList.add(tmp)
                    Log.d("Request","during")
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

    for (i in 0 until DataProvider.countryList.size) {
        Log.d("Country", DataProvider.countryList[i].title)
    }
    Log.d("Request","after")
}