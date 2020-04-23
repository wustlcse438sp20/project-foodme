package com.example.foodme.Activities.Data

data class User(
    val email: String? = null,
    var lunch_noti_h: Int? = null,
    var lunch_noti_m: Int? = null,
    var dinner_noti_h: Int? = null,
    var dinner_noti_m: Int? = null,
    var american_Cusi: Boolean? = false,
    var barbeque_Cusi: Boolean? = false,
    var chinese_Cusi: Boolean? = false,
    var french_Cusi: Boolean? = false,
    var hamburger_Cusi: Boolean? = false,
    var indian_Cusi: Boolean? = false,
    var italian_Cusi: Boolean? = false,
    var japanese_Cusi: Boolean? = false,
    var mexican_Cusi: Boolean? = false,
    var pizza_Cusi: Boolean? = false,
    var seafood_Cusi: Boolean? = false,
    var steak_Cusi: Boolean? = false,
    var sushi_Cusi: Boolean? = false,
    var thai_Cusi: Boolean? = false
)