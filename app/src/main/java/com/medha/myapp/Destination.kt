package com.medha.myapp


data class Destination(var imageId: Int, var placeName: String)


object VacationSpots {

    private val images = arrayListOf(
        R.drawable.thumb_4_1, R.drawable.thumb_4_2, R.drawable.thumb_4_3,
        R.drawable.thumb_4_4, R.drawable.thumb_4_7, R.drawable.thumb_4_8,
        R.drawable.thumb_4_0, R.drawable.thumb_7_0, R.drawable.thumb_7_1,
        R.drawable.thumb_7_2, R.drawable.thumb_4_5, R.drawable.thumb_4_6,
        R.drawable.thumb_1_0, R.drawable.thumb_1_1, R.drawable.thumb_1_2

    )

    private val cityNames = arrayListOf(
        "New Delhi", "Manchester", "Nottingham", "Portsmouth", "Agra",
        "Hyderabad", "Goa", "Srinagar", "Xian", "Shanghai",
        "Buffalo", "Boise", "Pittsburgh", "Scottsdale", "Boston"
    )

    var list: ArrayList<Destination>? = null
        get() {

            if (field != null)      // backing 'field' refers to 'list' property object
                return field

            field = ArrayList()
            for (i in images.indices) {

                val imageId = images[i]
                val cityName = cityNames[i]
                val destination = Destination(imageId, cityName)
                field!!.add(destination)
            }

            return field
        }
}
