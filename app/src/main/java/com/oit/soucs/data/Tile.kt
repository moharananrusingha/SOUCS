package com.oit.soucs.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* @Entity(tableName = "plants")
data class Plant(
    @PrimaryKey @ColumnInfo(name = "id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
)
* */
@Entity(tableName = "tiles")
data class Tile(
    @PrimaryKey @ColumnInfo(name = "id") val tileId: String, var sortId: Int, val image: ByteArray?, val name: String, val description: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tile

        if (tileId != other.tileId) return false
        if (sortId != other.sortId) return false
        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false
        if (name != other.name) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tileId.hashCode()
        result = 31 * result + sortId.hashCode()
        result = 31 * result + (image?.contentHashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }

}
