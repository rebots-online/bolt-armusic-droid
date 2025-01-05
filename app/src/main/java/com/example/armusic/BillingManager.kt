package com.example.armusic

import com.android.billingclient.api.*

class BillingManager(
    private val context: Context,
    private val callback: BillingCallback
) : BillingClientStateListener, PurchasesUpdatedListener {
    
    interface BillingCallback {
        fun onPurchaseSuccess()
        fun onPurchaseFailed()
    }
    
    private lateinit var billingClient: BillingClient
    
    fun initialize() {
        billingClient = BillingClient.newBuilder(context)
            .setListener(this)
            .enablePendingPurchases()
            .build()
        billingClient.startConnection(this)
    }
    
    override fun onBillingSetupFinished(billingResult: BillingResult) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
            queryAvailableProducts()
        }
    }
    
    private fun queryAvailableProducts() {
        val skuList = listOf("premium_features")
        val params = SkuDetailsParams.newBuilder()
            .setSkusList(skuList)
            .setType(BillingClient.SkuType.INAPP)
            .build()
        
        billingClient.querySkuDetailsAsync(params) { billingResult, skuDetailsList ->
            // Handle product details
        }
    }
    
    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        when (billingResult.responseCode) {
            BillingClient.BillingResponseCode.OK -> callback.onPurchaseSuccess()
            else -> callback.onPurchaseFailed()
        }
    }
}
