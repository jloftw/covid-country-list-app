package mx.tec.dataaccess

import android.content.Context
import android.widget.Button
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

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