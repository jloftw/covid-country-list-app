package mx.tec.dataaccess

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import mx.tec.dataaccess.ui.theme.DataAccessTheme
import org.json.JSONException
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getCountryInfo(this)
        super.onCreate(savedInstanceState)
        setContent {
            DataAccessTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MyApp {
                        startActivity(CountryProfile.newIntent(this, it))
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(navigateToProfile: (Country) -> Unit = {}) {
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
        MyApp()
    }
}