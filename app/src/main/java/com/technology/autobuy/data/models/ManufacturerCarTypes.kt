package com.technology.autobuy.data.models

import com.google.gson.annotations.SerializedName
import com.technology.autobuy.data.base.BaseResponse

data class ManufacturerCarTypes(
    @SerializedName("page")
    var page: String? = null,
    @SerializedName("pageSize")
    var pageSize: String? = null,
    @SerializedName("totalPageCount")
    var totalPageCount: String? = null,
    @SerializedName("wkda")
    var carTypes: Map<String, String>? = null
) : BaseResponse()
