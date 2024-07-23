package com.example.my_salon.db.entity

//@Entity
 data class Order (
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name = "filter_id")
    val id: Long,
//    @ColumnInfo(name = "first_name")
    val firstName: String?,
//    @ColumnInfo(name = "second_name")
    val lastName: String?,
//    @ColumnInfo(name = "order_date")
    val orderDate: String = "", // yyyy-MM-dd-hh-mm
)