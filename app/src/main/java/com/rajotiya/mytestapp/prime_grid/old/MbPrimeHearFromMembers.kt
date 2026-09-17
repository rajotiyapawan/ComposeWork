package com.rajotiya.mytestapp.prime_grid.old

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MbPrimeHearFromMembers (
	@SerializedName("name") val name : String,
	@SerializedName("profession") val profession : String,
	@SerializedName("city") val city : String,
	@SerializedName("imagePath") val url : String,
	@SerializedName("desc") val desc : String
)