package com.example.tcmbexchangeratedata

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Tarih_Date", strict = false)
data class CurrencyResponse(
    @field:ElementList(entry = "Currency", inline = true)
    var currencies: List<Currency>? = null
)

@Root(name = "Currency", strict = false)
data class Currency(
    @field:Element(name = "Unit")
    var unit: String? = null,

    @field:Element(name = "Isim")
    var name: String? = null,

    @field:Element(name = "ForexBuying", required = false)
    var forexBuying: String? = null,

    @field:Element(name = "ForexSelling", required = false)
    var forexSelling: String? = null,

    @field:Element(name = "CurrencyName")
    var currencyName: String? = null
)
