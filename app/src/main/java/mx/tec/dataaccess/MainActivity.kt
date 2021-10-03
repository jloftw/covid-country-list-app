package mx.tec.dataaccess

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mx.tec.dataaccess.ui.theme.DataAccessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataAccessTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Scaffold(
        content = {
            DataHomeContent()
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