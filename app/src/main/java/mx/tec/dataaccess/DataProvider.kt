package mx.tec.dataaccess

object DataProvider {
    var countryList = mutableListOf<Country>(
        Country(
            title = "America",
            cases = 100000,
            deaths = 11111134,
            recovered = 1111111,
            flagImageID = "https://disease.sh/assets/img/flags/af.png"
        )
    )
}