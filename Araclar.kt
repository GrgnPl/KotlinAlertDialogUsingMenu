package com.kacyakiyor.aracimkacyakti.API

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.io.StringReader

data class Araclar( @SerializedName("arac_adi")
                    @Expose
                    var arac_adi:String,
                    @SerializedName("model_tipi")
                    @Expose
                    var model_tipi:String,
                    @SerializedName("yakit_tipi")
                    @Expose
                    var yakit_tipi:String,
                    @SerializedName("arac_ozel_id")
                    @Expose
                    var arac_ozel_id:String): Serializable
