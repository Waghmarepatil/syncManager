package android.cranberry.syncmanager.utility

import com.google.gson.*
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.lang.Double
import java.lang.reflect.Type
import java.util.*

/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 29/7/21
 */
internal object Utils {


    /**
     * To parse json and extract value from it
     */
    fun parseJsonToValue(type: Type, element: JsonElement?): Any? {
        return if (element != null && !element.isJsonNull) {
            if (type === String::class.java) {
                return element.asString.trim { it <= ' ' }
            }
            if (type === Int::class.java) {
                return try {
                    Double.valueOf(element.asString.trim { it <= ' ' }).toInt()
                } catch (e: NumberFormatException) {
                    return null
                }
            }
            if (type === Byte::class.java) {
                return try {
                    Double.valueOf(element.asString.trim { it <= ' ' }).toInt().toByte()
                } catch (e: NumberFormatException) {
                    return null
                }
            }
            if (type === Date::class.java) {
                val pattern = "yyyy-MM-dd"
                val date: Date
                date = try {
                    val fmt: DateTimeFormatter = DateTimeFormat.forPattern(pattern)
                    fmt.parseLocalDate(element.asString.trim { it <= ' ' }).toDate()
                } catch (e: IllegalArgumentException) {
                    return null
                }
                return date
            }
            if (type === Boolean::class.java) {
                return element.asBoolean
            }
            if (type === Long::class.java) {
                return try {
                    element.asString.trim { it <= ' ' }.toLong()
                } catch (e: NumberFormatException) {
                    return null
                }
            }
            null
        } else {
            null
        }
    }

}