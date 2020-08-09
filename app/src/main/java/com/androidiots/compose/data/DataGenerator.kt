package com.androidiots.compose.data

import android.content.Context
import com.androidiots.compose.R
import com.androidiots.compose.model.People
import java.util.*

class DataGenerator {

    companion object {
        @JvmStatic
        fun getPeopleData(ctx: Context): List<People?>? {
            val items: MutableList<People?> = ArrayList<People?>()
            val drw_arr = ctx.resources.obtainTypedArray(R.array.people_images)
            val name_arr = ctx.resources.getStringArray(R.array.people_names)
            for (i in 0 until drw_arr.length()) {
                val obj = People()
                obj.image = drw_arr.getResourceId(i, -1)
                obj.name = name_arr[i]
                obj.email = obj.name.toString()[0] + "@gmail.com"
                obj.imageDrw = ctx.resources.getDrawable(obj.image)
                items.add(obj)
            }
            Collections.shuffle(items)
            return items
        }
    }
}