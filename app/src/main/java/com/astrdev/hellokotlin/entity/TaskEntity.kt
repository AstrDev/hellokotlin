package com.astrdev.hellokotlin.entity

import android.os.Parcel
import android.os.Parcelable

data class TaskEntity(var id: Long, var title: String, var text: String, var status: Status, var createdAt: Long): Parcelable {

    companion object {
        public val CREATOR = object: Parcelable.Creator<TaskEntity> {

            override fun createFromParcel(source: Parcel?) = TaskEntity(
                    source!!.readLong(),
                    source.readString(),
                    source.readString(),
                    source.readParcelable(TaskEntity::class.java.classLoader),
                    source.readLong()
            )

            override fun newArray(size: Int): Array<out TaskEntity?> {
                return arrayOfNulls(size)
            }
        }
    }

    enum class Status: Parcelable {
        CREATED, DONE, DELETED;

        companion object {
            public val CREATOR = object: Parcelable.Creator<Status> {
                override fun newArray(size: Int): Array<out Status?> {
                    return arrayOfNulls(size)
                }

                override fun createFromParcel(source: Parcel?): Status? {
                    return Status.values()[source!!.readInt()]
                }

            }
        }

        override fun describeContents() = 0

        override fun writeToParcel(dest: Parcel?, flags: Int) {
            if (dest == null) throw IllegalArgumentException("Parcel can't be null!")

            dest.writeInt(ordinal)
        }
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        if (dest == null) throw IllegalArgumentException("Parcel can't be null!")

        dest.writeLong(id)
        dest.writeString(title)
        dest.writeString(text)
        dest.writeParcelable(status, flags)
        dest.writeLong(createdAt)
    }

    override fun describeContents(): Int {
        return 0
    }

}
