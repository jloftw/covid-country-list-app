package mx.tec.dataaccess

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import mx.tec.dataaccess.ui.theme.DataAccessTheme

class CountryProfile : AppCompatActivity() {
    private val country: Country by lazy {
        intent?.getSerializableExtra(COUNTRY_ID) as Country
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataAccessTheme {
                ProfileScreen(country = country)
            }
        }
    }
    companion object {
        private const val COUNTRY_ID = "country_id"
        fun newIntent(context: Context, country: Country) =
            Intent(context, CountryProfile::class.java).apply {
                putExtra(COUNTRY_ID, country)
            }
    }
}