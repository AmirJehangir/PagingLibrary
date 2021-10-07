package com.amir.mart.paginglibraryv3.presentation

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Amir.jehangir on 24,September,2021
 */
@Parcelize
data class MartContent(
    @SerializedName("content") var content : ArrayList<Mart>?= null,
    @SerializedName("last") var last :  String? = "",
    @SerializedName("size") var size : Int? = 0
): Parcelable

@Entity(tableName = "Mart")
@Parcelize
data class Mart (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("ewStoreId") var ewStoreId : Int? =0,
    @SerializedName("agentName") var agentName : String?="",
    @SerializedName("latitude") var latitude : Double?=0.0,
    @SerializedName("longitude") var longitude : Double?=0.0,
    @SerializedName("distance") var distance : Double?=0.0,
    @SerializedName("status") var status : String?="",
    @SerializedName("brandIcon") var brandIcon : String?=""
): Parcelable