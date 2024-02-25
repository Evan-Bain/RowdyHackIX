package com.example.rowdyhacks.cde.model

import com.squareup.moshi.Json

data class CdeData(
    @field:Json(name ="actual") val actual: Int
)