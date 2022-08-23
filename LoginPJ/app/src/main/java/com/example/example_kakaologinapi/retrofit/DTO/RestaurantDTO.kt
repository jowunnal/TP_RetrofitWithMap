package com.example.example_kakaologinapi.retrofit.DTO

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="rfcOpenApi")
data class RfcOpenApi(
    @Element(name="body")
    val body: Body,
    @Element(name="header")
    val header: Header
)

@Xml(name="header")
data class Header(
    @PropertyElement(name="resultCode")
    val resultCode: Int,
    @PropertyElement(name="resultMsg")
    val resultMsg: String
    )

@Xml(name="body")
data class Body(
    @Element(name="data")
    val data: Data,
    @PropertyElement(name="numOfRows")
    val numOfRows: Int,
    @PropertyElement(name="pageIndex")
    val pageIndex: Int,
    @PropertyElement(name="pageNo")
    val pageNo: Int,
    @PropertyElement(name="totalCount")
    val totalCount: Int
)

@Xml(name="data")
data class Data(
    @Element(name="list")
    val list: kotlin.collections.List<Item>
)

@Xml(name="list")
data class Item(
    @PropertyElement(name="address")
    val address: String?,
    @PropertyElement(name="city")
    val city: String?,
    @PropertyElement(name="company")
    val company: String?,
    @PropertyElement(name="foodType")
    val foodType: String?,
    @PropertyElement(name="lat")
    val lat: Double?,
    @PropertyElement(name="lng")
    val lng: Double?,
    @PropertyElement(name="mainMenu")
    val mainMenu: String?,
    @PropertyElement(name="phoneNumber")
    val phoneNumber: String?
){
    constructor() : this(null,null,null,null,null,null,null,null)
}