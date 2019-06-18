package com.itc.app.models.home

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Product(
    val productId: String, val productName: String = "", var shortDescription: String = "",
    val longDescription: String, val price: String, val productImage: String,
    val reviewRating: Double, val reviewCount: Int, val inStock: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productId)
        parcel.writeString(productName)
        if(shortDescription == null){
            shortDescription = ""
        }
        parcel.writeString(shortDescription)
        parcel.writeString(longDescription)
        parcel.writeString(price)
        parcel.writeString(productImage)
        parcel.writeDouble(reviewRating)
        parcel.writeInt(reviewCount)
        parcel.writeByte(if (inStock) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}