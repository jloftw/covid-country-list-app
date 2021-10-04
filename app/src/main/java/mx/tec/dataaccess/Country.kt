package mx.tec.dataaccess

import java.io.Serializable

data class Country(
    val title: String,
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    val flagImageID: String
) : Serializable