package com.example.nytimesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

object NewsDBModel {


    @Entity(tableName = "NewsTable")
    data class News(
        @PrimaryKey(autoGenerate = true)
        var id:Int? = 0,
        var section : String? = "",
        var subsection : String? = "",
        var title : String? = "",
        var abstract : String? = "",
        var url : String? = "",
        var byline : String? = "",
        var item_type : String? = "",
        var updated_date : String? = "",
        var created_date : String? = "",
        var published_date : String? = "",
        var imageUrl : String? = "",
        var format : String? = "",
        var height : Int? = 0,
        var width : Int? = 0,
        var type : String?= "",
        var subtype : String?= "",
        var caption : String?= "",
        var copyright : String?= ""
    )
}