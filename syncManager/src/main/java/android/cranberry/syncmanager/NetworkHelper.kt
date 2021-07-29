package android.cranberry.syncmanager

import com.androidnetworking.AndroidNetworking
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
internal class NetworkHelper {

    interface NetworkCallback {
        fun onResponse(response: Any)
        fun onError(errorCode: Int, errorMessage: String?)
    }

    fun get(
        url: String, pathParameter: Map<String, String>?, queryParameter: Map<String, String>?,
        headers: Map<String, String>, tag: String, priority: Priority,
        isJsonObjectRequest: Boolean,
        networkCallback: NetworkCallback
    ) {
        var priorityToSet: com.androidnetworking.common.Priority = when (priority) {
            Priority.HIGH -> com.androidnetworking.common.Priority.HIGH
            Priority.MEDIUM -> com.androidnetworking.common.Priority.MEDIUM
            Priority.LOW -> com.androidnetworking.common.Priority.LOW
            Priority.IMMEDIATE -> com.androidnetworking.common.Priority.IMMEDIATE
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
                        networkCallback.onError(anError.errorCode, anError.message)
                    }
                })
            }else{
                request.getAsJSONArray(object : JSONArrayRequestListener{
                    override fun onResponse(response: JSONArray) {
                        networkCallback.onResponse(response)
                    }

                    override fun onError(anError: ANError) {
                        networkCallback.onError(anError.errorCode, anError.message)
                    }

                })
            }
    }


    fun post(
        url: String, headers: Map<String, String>?, tag: String,
        bodyParam: Any,
        priority: Priority,
        isJsonObjectRequest: Boolean,
        networkCallback: NetworkCallback
    ) {
        var priorityToSet: com.androidnetworking.common.Priority = when (priority) {
            Priority.HIGH -> com.androidnetworking.common.Priority.HIGH
            Priority.MEDIUM -> com.androidnetworking.common.Priority.MEDIUM
            Priority.LOW -> com.androidnetworking.common.Priority.LOW
            Priority.IMMEDIATE -> com.androidnetworking.common.Priority.IMMEDIATE
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
                    networkCallback.onError(anError.errorCode, anError.message)
                }
            })
        }else{
            request.build()
                .getAsJSONArray(object : JSONArrayRequestListener{
                override fun onResponse(response: JSONArray) {
                    networkCallback.onResponse(response)
                }

                override fun onError(anError: ANError) {
                    networkCallback.onError(anError.errorCode, anError.message)
                }

            })
        }
    }
}