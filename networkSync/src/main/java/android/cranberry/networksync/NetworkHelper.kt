package android.cranberry.networksync

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONArray
import org.json.JSONObject


/**
 * @Author: Pramod Jyotiram Waghmare
 * @Company: Cranberry Analytics Pvt. Ltd.
 * @Date: 27/7/21
 */
object NetworkHelper {

    interface NetworkCallback {
        fun onResponse(response: Any)
        fun onError(error: ANError)
    }

    fun get(
        url: String, pathParameter: Map<String, String>?, queryParameter: Map<String, String>?,
        headers: Map<String, String>, tag: String, priority: android.cranberry.networksynclibrary.Priority,
        isJsonObjectRequest: Boolean,
        networkCallback: NetworkCallback
    ) {
        var priorityToSet: Priority = when (priority) {
            android.cranberry.networksynclibrary.Priority.HIGH -> Priority.HIGH
            android.cranberry.networksynclibrary.Priority.MEDIUM -> Priority.MEDIUM
            android.cranberry.networksynclibrary.Priority.LOW -> Priority.LOW
            android.cranberry.networksynclibrary.Priority.IMMEDIATE -> Priority.IMMEDIATE
        }

        val request = AndroidNetworking.get(url)
            .addPathParameter(pathParameter)
            .addQueryParameter(queryParameter)
            .addHeaders(headers)
            .setTag(tag)
            .setPriority(priorityToSet)
            .build()
            if (isJsonObjectRequest){
                request.getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject) {
                        networkCallback.onResponse(response)
                    }

                    override fun onError(anError: ANError) {
                        networkCallback.onError(anError)
                    }
                })
            }else{
                request.getAsJSONArray(object : JSONArrayRequestListener{
                    override fun onResponse(response: JSONArray) {
                        networkCallback.onResponse(response)
                    }

                    override fun onError(anError: ANError) {
                        networkCallback.onError(anError)
                    }

                })
            }
    }


    fun post(
        url: String, headers: Map<String, String>?, tag: String,
        bodyParam: Any,
        priority: android.cranberry.networksynclibrary.Priority,
        isJsonObjectRequest: Boolean,
        networkCallback: NetworkCallback
    ) {
        var priorityToSet: Priority = when (priority) {
            android.cranberry.networksynclibrary.Priority.HIGH -> Priority.HIGH
            android.cranberry.networksynclibrary.Priority.MEDIUM -> Priority.MEDIUM
            android.cranberry.networksynclibrary.Priority.LOW -> Priority.LOW
            android.cranberry.networksynclibrary.Priority.IMMEDIATE -> Priority.IMMEDIATE
        }

        var request = AndroidNetworking.post(url)
            .addHeaders(headers)
        when (bodyParam) {
            is JSONObject -> request.addJSONObjectBody(bodyParam)
            is JSONArray -> request.addJSONArrayBody(bodyParam)
            else -> request.addBodyParameter(bodyParam)
        }

        request.setTag(tag)
        .setPriority(priorityToSet)

        if (isJsonObjectRequest){
            request.build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject) {
                    networkCallback.onResponse(response)
                }

                override fun onError(anError: ANError) {
                    networkCallback.onError(anError)
                }
            })
        }else{
            request.build()
                .getAsJSONArray(object : JSONArrayRequestListener{
                override fun onResponse(response: JSONArray) {
                    networkCallback.onResponse(response)
                }

                override fun onError(anError: ANError) {
                    networkCallback.onError(anError)
                }

            })
        }
    }
}