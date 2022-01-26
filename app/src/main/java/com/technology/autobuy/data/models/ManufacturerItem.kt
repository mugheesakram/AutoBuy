package com.technology.autobuy.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ManufacturerItem(
    var id: String? = null,
    var name: String? = null
) : Parcelable
