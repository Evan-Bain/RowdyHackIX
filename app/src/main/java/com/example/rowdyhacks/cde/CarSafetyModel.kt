package com.example.rowdyhacks.cde

import com.squareup.moshi.Json

data class CarSafetyModel(
    @field:Json(name ="actual") val actual: Int
)