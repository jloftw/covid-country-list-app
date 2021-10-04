package mx.tec.dataaccess

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import mx.tec.dataaccess.ui.theme.DataAccessTheme
import org.json.JSONException
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        VolleySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)

        for (i in 0 until DataProvider.countryList.size) {
            Log.d("Country", DataProvider.countryList[i].title)
        }
        Log.d("Request","after")
        setContent {
            DataAccessTheme {
                Surface(color = MaterialTheme.colors.background) {
                    getCountryInfo(this)
                    MyApp {
                        startActivity(CountryProfile.newIntent(this, it))
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(navigateToProfile: (Country) -> Unit) {
    Scaffold(
        content = {
            DataHomeContent(navigateToProfile = navigateToProfile)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DataAccessTheme {
    }
}