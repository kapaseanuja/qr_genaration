package com.example.generate_qr_code.multiLevelExpandableDemo

data class MLFTyperequestModel(
    var title : String = "",
    var isSelected : Boolean = false,
    var isHasChild : Boolean = false,
    var isChildDataSet : Boolean = false,
    var identifier :String = "",
//    var childData : ArrayList<ChildData>? = null,
    var level: Int =0
)
