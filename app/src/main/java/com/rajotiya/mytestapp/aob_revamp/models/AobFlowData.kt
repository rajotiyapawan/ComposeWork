package com.rajotiya.mytestapp.aob_revamp.models

import androidx.annotation.Keep
import com.rajotiya.mytestapp.aob_revamp.utils.SearchType

/**
 * Created by Pawan Rajotiya on 14-08-2024.
 */
@Keep
data class AobFlowData(
    val searchType: SearchType = SearchType.Property_Buy,
    val city: PopularCitiesData.CityDetail? = null,
    val localityList: ArrayList<PopularLtPrjData.LtPrjDetail>?= arrayListOf(),
    val projectList: ArrayList<PopularLtPrjData.LtPrjDetail>?= arrayListOf(),
    val poiTag: PoiTagsData.PoiTag = PoiTagsData.PoiTag(),
    val poiLocation: PopularLtPrjData.LtPrjDetail = PopularLtPrjData.LtPrjDetail(),
    val includePoiLocation: Boolean = false,
    var userDetailModel: UserDetailModel = UserDetailModel(),
    val isResidential: Boolean = true,
    val propertyTypes: List<PropertySearchModelMapping> = arrayListOf(),
    val bgMax: String = "",
    val bgMin: String = "",
    val bhk: List<String> = arrayListOf(),
    val moreServicesList: List<PoiTagsData.PoiTag> = listOf(),
    val numVerified: Boolean = false,
    val isPG:Boolean = false,
    val alertId:String? = "",
    val possessionStatus: DefaultSearchModelMapping = DefaultSearchModelMapping(),
    val pgType: DefaultSearchModelMapping = DefaultSearchModelMapping(),
)
