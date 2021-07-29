package android.cranberry.syncmanager.mobile_database

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 26/7/21
 */
open class Users() : RealmObject(), Serializable, Parcelable {
    @PrimaryKey
    public var id: String = ""
    public var name: String = ""
    public var age: Byte = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readString().toString()
        name = parcel.readString().toString()
        age = parcel.readByte()
    }

    constructor(id: String, name: String, age: Byte) : this() {
        this.id = id
        this.name = name
        this.age = age
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeByte(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "Users(id='$id', name='$name', age=$age)"
    }

    companion object CREATOR : Parcelable.Creator<Users> {
        override fun createFromParcel(parcel: Parcel): Users {
            return Users(parcel)
        }

        override fun newArray(size: Int): Array<Users?> {
            return arrayOfNulls(size)
        }
    }


}