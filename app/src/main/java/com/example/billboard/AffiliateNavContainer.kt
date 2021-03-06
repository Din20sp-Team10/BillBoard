package com.example.billboard

/*===================================================/
|| Entry point for the affiliate partners navigation
|| stack
/====================================================*/

import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun AffiliateNavContainer(
    navControl: NavController,
    scState: ScaffoldState,
    scope: CoroutineScope,
    affiliateVM: AffiliatePartnersViewModel,
    userVM: UserViewModel
) {

    val partners = affiliateVM.partners

    val categories = affiliateVM.categories

    val affiliateNavControl = rememberNavController()

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    // This is used for conditional check of what should be shown on the start destination of the stack //
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    val selectedCategory = remember{ mutableStateOf("") }

    Log.d("Partners", partners.toString())

    NavHost(navController = affiliateNavControl, startDestination = "affiliate_categories" ) {

        /////////////////////////////////////////
        // Start destination of the nav stack //
        ///////////////////////////////////////
        composable( route = "affiliate_categories" ) {
            AffiliatePartnersView(  navControl, affiliateNavControl, scState, scope, partners, categories, selectedCategory )
        }

        /////////////////////////////////////////////////////////////////////
        // Partner composable for each partner, fetched from our database //
        ///////////////////////////////////////////////////////////////////
        partners.forEach { partner ->
            composable( route = partner.id) {
                PartnerView( partner, navControl, affiliateNavControl, userVM, scState, scope )
            }
        }
    }
}