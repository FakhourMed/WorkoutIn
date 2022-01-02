package com.fakhour.workoutin.common.database

import androidx.room.TypeConverter
import java.util.*
import kotlin.collections.ArrayList


class WorkoutTypeConverters {
    var strSeparator = "__,__"

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun fromUUIDArrayList(uuidArray: ArrayList<UUID>?): String? {

        var str = ""
        uuidArray?.let {
            for (i in 0 until uuidArray.size) {
                str += uuidArray.get(i)
                // Do not append comma at the end of last element
                if (i < uuidArray.size - 1) {
                    str = str + strSeparator
                }
            }
        }

        return str
    }

    fun toUUIDNotNull(uuid: String): UUID {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun toUUIDArrayList(str: String): ArrayList<UUID>? {
        val arrayList= str.split(strSeparator.toRegex()).toTypedArray()
        val arrayListUUID: ArrayList<UUID> = arrayListOf()
        arrayList.forEach {
            arrayListUUID.add(toUUIDNotNull(it))
        }

        return arrayListUUID
    }


}