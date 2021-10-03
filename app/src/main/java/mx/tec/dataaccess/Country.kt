package mx.tec.dataaccess

data class Country(
    val id: Int,
    val title: String,
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    val flagImageID: Int = 0
)