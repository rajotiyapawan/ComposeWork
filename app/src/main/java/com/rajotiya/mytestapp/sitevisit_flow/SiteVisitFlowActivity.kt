package com.rajotiya.mytestapp.sitevisit_flow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rajotiya.mytestapp.sitevisit_flow.ui.FreeCabL1BookingScreen
import com.rajotiya.mytestapp.sitevisit_flow.ui.SVPickUpLocationScreen
import com.rajotiya.mytestapp.sitevisit_flow.ui.SiteVisitBooked
import com.rajotiya.mytestapp.utility.defaultEnterTransition
import com.rajotiya.mytestapp.utility.defaultExitTransition
import com.rajotiya.mytestapp.utility.defaultPopEnterTransition
import com.rajotiya.mytestapp.utility.defaultPopExitTransition


enum class SiteVisitScreens {
    FreeCabIntro, SiteVisitBooked, SVPickUpLocation
}

class SiteVisitFlowActivity : ComponentActivity() {

    private var startDestination = SiteVisitScreens.SVPickUpLocation.name

    companion object {
        const val START_SCREEN_NAME = "startScreen"
        fun launchSiteVisitFlowActivity(context: Context, startScreen: String = "") {
            val intent = Intent(context, SiteVisitFlowActivity::class.java)
            intent.putExtra(START_SCREEN_NAME, startScreen)
            context.startActivity(intent)
        }
    }

    private fun getDataFromBundle() {
        intent?.let {
            val intentStartScreen = it.getStringExtra(START_SCREEN_NAME) ?: ""
            if (intentStartScreen.isNotEmpty()) {
                startDestination = intentStartScreen
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromBundle()
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MainViews(modifier = Modifier.padding(innerPadding))
            }
        }
    }

    @Composable
    fun MainViews(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        PrepareNavGraph(modifier = modifier, navController = navController, startDestination = startDestination)
    }

    @Composable
    private fun PrepareNavGraph(modifier: Modifier = Modifier, navController: NavHostController, startDestination: String) {
        NavHost(
            navController = navController, startDestination = startDestination,
            modifier = modifier
        ) {
            composable(route = SiteVisitScreens.FreeCabIntro.name,
                enterTransition = { defaultEnterTransition() },
                exitTransition = { defaultExitTransition() },
                popEnterTransition = { defaultPopEnterTransition() },
                popExitTransition = { defaultPopExitTransition() }) {
                FreeCabL1BookingScreen(modifier = Modifier.fillMaxSize())
            }
            composable(route = SiteVisitScreens.SiteVisitBooked.name,
                enterTransition = { defaultEnterTransition() },
                exitTransition = { defaultExitTransition() },
                popEnterTransition = { defaultPopEnterTransition() },
                popExitTransition = { defaultPopExitTransition() }) {
                SiteVisitBooked(modifier = Modifier.fillMaxSize())
            }
            composable(route = SiteVisitScreens.SVPickUpLocation.name,
                enterTransition = { defaultEnterTransition() },
                exitTransition = { defaultExitTransition() },
                popEnterTransition = { defaultPopEnterTransition() },
                popExitTransition = { defaultPopExitTransition() }) {
                SVPickUpLocationScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}