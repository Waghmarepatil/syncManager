package android.cranberry.syncmanager.parser

import android.cranberry.syncmanager.mobile_database.Users
import android.cranberry.syncmanager.utility.Utils
import com.google.gson.*
import java.lang.reflect.Type

class UsersParser : JsonDeserializer<Users>, JsonSerializer<Users> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Users {
        val jsonObject = json!!.asJsonObject
        val id = Utils.parseJsonToValue(String::class.java, jsonObject["id"]) as String
        val name = Utils.parseJsonToValue(String::class.java, jsonObject["name"]) as String
        val age = Utils.parseJsonToValue(Byte::class.java, jsonObject["age"]) as Byte
        return Users(id, name, age)
    }

    override fun serialize(
        src: Users?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObject = JsonObject()
        jsonObject.addProperty("id", src!!.id)
        jsonObject.addProperty("name", src.name)
        jsonObject.addProperty("age", src.age)
        return jsonObject
    }

}
