package com.technology.autobuy.data.models

import com.google.gson.annotations.SerializedName
import com.technology.autobuy.data.base.BaseResponse

data class CarTypeInfo(
    @SerializedName("wkda")
    var carTypesDates: Map<String, String>? = null
) : BaseResponse()
