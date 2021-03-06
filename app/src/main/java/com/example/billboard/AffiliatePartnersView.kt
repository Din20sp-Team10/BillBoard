package com.example.billboard

/*===================================================/
|| This is the starting page for the affiliate navigation
|| stack. It displays cards with the categories of our
|| partners services
/====================================================*/

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope

@Composable
fun AffiliatePartnersView(
    navControl: NavController,
    affiliateNavControl: NavController,
    scState: ScaffoldState,
    scope: CoroutineScope,
    partners: MutableList<AffiliatePartner>,
    categories: MutableList<String>,
    selectedCategory: MutableState<String>
) {

    Scaffold(
        scaffoldState = scState,
        topBar = { TopBar(true, scState, false, scope) },
        bottomBar = { BottomBarAffiliate( navControl, selectedCategory ) },
        content = { AffiliatePartnersContent( partners, affiliateNavControl, selectedCategory, categories ) },
        drawerContent = { DrawerMainScreen ( navControl, scState, scope )
        }
    )

}

@Composable
fun AffiliatePartnersContent(
    partners: MutableList<AffiliatePartner>,
    affiliateNavControl: NavController,
    categoryName: MutableState<String>,
    categories: MutableList<String>
) {

    ///////////////////////
    // Container column //
    /////////////////////
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(20.dp))

        ////////////////////////////////////////////////////////////////////////
        // This statement checks if there is selected category from the user //
        // and if it is empty, shows cards with the categories. If there is //
        // category selected, it shows cards with our partners from that   //
        // category                                                       //
        ///////////////////////////////////////////////////////////////////
        if ( categoryName.value == "" ) {
            categories.forEach { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .clickable {
                            categoryName.value = category
                        }
                        .height(140.dp),
                    shape = MaterialTheme.shapes.large,
                    elevation = 7.dp
                ) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text( text = category.uppercase(), textAlign = TextAlign.Center, fontSize = 25.sp, color = MaterialTheme.colors.onPrimary )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

            }
        } else {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                partners.forEach { partner ->
                    if (partner.category == categoryName.value) {
                        Card(
                            modifier = Modifier
                                .clickable { affiliateNavControl.navigate(partner.id) }
                                .fillMaxWidth(.8f)
                                .height(70.dp),
                            shape = MaterialTheme.shapes.large,
                            contentColor = MaterialTheme.colors.onPrimary,
                            elevation = 7.dp
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = partner.imgURL,
                                    contentDescription = partner.name,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.clip(MaterialTheme.shapes.large)
                                )
                            }
                        }
                        Spacer( modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}