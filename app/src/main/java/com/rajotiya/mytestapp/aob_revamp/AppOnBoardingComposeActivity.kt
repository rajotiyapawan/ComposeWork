package com.rajotiya.mytestapp.aob_revamp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rajotiya.mytestapp.aob_revamp.screens.AddLocalityProjectScreen
import com.rajotiya.mytestapp.aob_revamp.screens.BuyFlowMoreProductsScreen
import com.rajotiya.mytestapp.aob_revamp.screens.CitySelectionScreen
import com.rajotiya.mytestapp.aob_revamp.screens.FilterScreen
import com.rajotiya.mytestapp.aob_revamp.screens.InitialSelectionScreen
import com.rajotiya.mytestapp.aob_revamp.screens.PLaceOfInterestScreen
import com.rajotiya.mytestapp.aob_revamp.screens.UserDetailsScreen
import com.rajotiya.mytestapp.aob_revamp.screens.VerifyOtpScreen
import com.rajotiya.mytestapp.aob_revamp.ui.theme.AOBTheme

class AppOnBoardingComposeActivity : ComponentActivity() {

    private val viewModel: AOBViewModel by viewModels()

    companion object {
        fun launchAOBActivity(context: Context) {
            val intent = Intent(context, AppOnBoardingComposeActivity::class.java)
            context.startActivity(intent)
        }

        val localViewModelCompositionLocal = staticCompositionLocalOf<AOBViewModel> {
            error("No View Model Provided")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(value = localViewModelCompositionLocal provides viewModel) {
                AOBTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = MaterialTheme.colorScheme.background
                    ) { innerPadding ->
                        MainViews(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainViews(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = AOBScreens.InitialSelection.name
    val context = LocalContext.current

    NavHost(
        navController = navController, startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = AOBScreens.InitialSelection.name) {
            InitialSelectionScreen(modifier = Modifier.fillMaxWidth())
        }
        composable(route = AOBScreens.CitySelection.name) {
            CitySelectionScreen(modifier = Modifier.fillMaxWidth()) {

            }
        }
        composable(route = AOBScreens.AddLocalityProject.name) {
            AddLocalityProjectScreen(modifier = Modifier.fillMaxWidth()) {

            }
        }
        composable(route = AOBScreens.BuyMoreProducts.name) {
            BuyFlowMoreProductsScreen(modifier = Modifier.fillMaxWidth()) {

            }
        }
        composable(route = AOBScreens.VerifyOtpScreen.name) {
            VerifyOtpScreen(modifier = Modifier.fillMaxWidth()) {

            }
        }
        composable(route = AOBScreens.UserDetailsScreen.name) {
            UserDetailsScreen(modifier = Modifier.fillMaxWidth()) {

            }
        }
        composable(route = AOBScreens.POIScreen.name) {
            PLaceOfInterestScreen(modifier = Modifier.fillMaxWidth()) {

            }
        }
        composable(route = AOBScreens.FilterScreen.name) {
            FilterScreen(modifier = Modifier.fillMaxWidth()) {

            }
        }
    }
}

enum class AOBScreens {
    InitialSelection, CitySelection, BuyMoreProducts, VerifyOtpScreen, UserDetailsScreen, POIScreen, FilterScreen, AddLocalityProject
}