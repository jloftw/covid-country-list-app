package mx.tec.dataaccess

import android.content.Intent.getIntent
import android.widget.Space
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.puculek.pulltorefresh.PullToRefresh
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent.getIntent

import android.content.Intent
import androidx.core.content.ContextCompat


@Composable
fun DataHomeContent(navigateToProfile: (Country) -> Unit) {
    val countries = remember {DataProvider.countryList}
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = countries,
            itemContent = {
                CountryListItem(country = it, navigateToProfile)
            }
        )
    }
}