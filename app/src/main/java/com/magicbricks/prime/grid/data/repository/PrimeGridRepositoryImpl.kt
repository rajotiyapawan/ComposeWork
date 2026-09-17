package com.magicbricks.prime.grid.data.repository

import com.google.gson.Gson
import com.magicbricks.prime.grid.domain.repository.PrimeGridRepository
import com.magicbricks.prime.model.PrimePackageResponse
import com.rajotiya.mytestapp.utility.MBCoreResultEvent

class PrimeGridRepositoryImpl : PrimeGridRepository {
    override suspend fun getPrimePackage(
        category: String,
        paymentSource: String,
        pid: String,
        mid: String,
        propertyId: String?
    ): MBCoreResultEvent<PrimePackageResponse> {
        return try {
            val response = Gson().fromJson(DUMMY_JSON, PrimePackageResponse::class.java)
            if (response != null) {
                MBCoreResultEvent.OnSuccess(response)
            } else {
                MBCoreResultEvent.OnFailure("Failed to parse prime package response")
            }
        } catch (e: Exception) {
            MBCoreResultEvent.OnFailure(e.message ?: "Unknown Error")
        }
    }

    override suspend fun subscribeWhatsApp(isSubscribed: Boolean): MBCoreResultEvent<Boolean> {
        return MBCoreResultEvent.OnSuccess(true)
    }

    companion object {
        const val DUMMY_JSON = """{
  "status":"1",
  "packageDetails":{
    "rmMobile":"7303439363",
    "rmWhatsAppMobileIsd":"50",
    "applicableMagicCash":0,
    "packageID":284407,
    "gst":324,
    "discount":750,
    "mbPrimeBenifits":[
      {
        "bannerId":"mbprimebenifitsrent1",
        "heading":"Access new properties by Owners",
        "icon":"https://img.staticmb.com/mbimages/app_images/priorityaccess.png",
        "moreText":"Be the first to view, negotiate & close deal"
      }
    ],
    "subTotal":1800,
    "isFreePackage":true,
    "payableAmount":"1050",
    "isRequestCallBack":true,
    "discountPercentage":"41%",
    "packageList":[
      {"packegeName":"Free","packegeid":0,"oprice":0,"bprice":0,"durationInDays":0,"percentDiscountOff":0,"isDefault":false},
      {"packegeName":"Basic","packegeid":284407,"oprice":"1050","bprice":1800,"durationInDays":3,"percentDiscountOff":41,"isDefault":true},
      {"packegeName":"Pro","packegeid":284425,"oprice":"1199","bprice":2198,"durationInDays":3,"percentDiscountOff":45,"isDefault":false}
    ]
  },
  "subHeading":"Save Time & Money in Property Search",
  "termCondition":"<p>Terms &amp; Conditions</p>",
  "tpromise":"<html><body><b>MB's transparency Promise</b></body></html>"
}"""
    }
}
