package com.rajotiya.mytestapp.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rajotiya.mytestapp.loyalty.LoyaltyUserEvents

class NavigationActivity : ComponentActivity() {

    private val viewModel: NavigationViewModel by viewModels { NavigationViewModel.Factory }

    companion object {
        fun launchLoyaltyLandingActivity(context: Context, source: String = "", position: String = "") {
            val intent = Intent(context, NavigationActivity::class.java)
            intent.putExtra("source", source)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }

        val localViewModelCompositionLocal = staticCompositionLocalOf<NavigationViewModel> {
            error("No View Model Provided")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(value = localViewModelCompositionLocal provides viewModel) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainViews(modifier = Modifier.padding(innerPadding))
                }
            }
        }

    }

    @Composable
    fun MainViews(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        val startDestination = LoyaltyScreens.LandingPage.name
        HandleUserEvents(navController = navController)
        PrepareNavGraph(modifier = modifier, navController = navController, startDestination = startDestination)
    }

    enum class LoyaltyScreens {
        LandingPage, RedeemReward, EarnMoreForReward, PointsLedger, RedeemThankYou
    }

    @Composable
    private fun PrepareNavGraph(modifier: Modifier = Modifier, navController: NavHostController, startDestination: String) {
        NavHost(
            navController = navController, startDestination = startDestination,
            modifier = modifier
        ) {
            composable(route = LoyaltyScreens.LandingPage.name,
                enterTransition = { defaultEnterTransition() },
                exitTransition = { defaultExitTransition() },
                popEnterTransition = { defaultPopEnterTransition() },
                popExitTransition = { defaultPopExitTransition() }) { backStackEntry ->
                val refresh = backStackEntry.arguments?.getBoolean("refresh")
                Column(modifier=Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("BackStack Entry : $refresh")
                    Spacer(Modifier.height(20.dp))
                    TextButton(
                        content = { Text("Next Screen") },
                        onClick = { viewModel.sendUserEvent(LoyaltyUserEvents.NavigateTo(LoyaltyScreens.EarnMoreForReward.name, refresh=true)) })
                }
            }
            composable(route = LoyaltyScreens.EarnMoreForReward.name,
                enterTransition = { defaultEnterTransition() },
                exitTransition = { defaultExitTransition() },
                popEnterTransition = { defaultPopEnterTransition() },
                popExitTransition = { defaultPopExitTransition() }) {backStackEntry ->
                val refresh = backStackEntry.arguments?.getBoolean("refresh")
                Column(modifier=Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("BackStack Entry : $refresh")
                    Spacer(Modifier.height(20.dp))
                    TextButton(
                        content = { Text("Previous Screen") },
                        onClick = { viewModel.sendUserEvent(LoyaltyUserEvents.BackBtnClicked) })
                }
            }
        }
    }

    @Composable
    private fun HandleUserEvents(navController: NavHostController) {
        val userEvent by viewModel.userEvents.observeAsState()
        userEvent?.let { event ->
            when (event) {
                LoyaltyUserEvents.DoNothing -> {}
                is LoyaltyUserEvents.NavigateTo -> {
                    navController.previousBackStackEntry?.arguments?.putBoolean("refresh", event.refresh)
                    navController.currentBackStackEntry?.arguments?.putBoolean("refresh", event.refresh)
                    if (event.saveToBackStack) {
                        navController.navigate(route = event.route)
                    } else {
                        navController.navigate(route = event.route) {
                            popUpTo(event.currentScreen) { inclusive = false }
                        }
                    }
                }

                is LoyaltyUserEvents.PopBackTo -> {
                    navController.popBackStack(route = event.route, inclusive = false)
                }

                LoyaltyUserEvents.BackBtnClicked -> {
                    navController.popBackStack()
                }
            }
            if (event !is LoyaltyUserEvents.DoNothing) {
                viewModel.clearUserEvent()
            }
        }
    }

    private fun defaultEnterTransition(): EnterTransition {
        return fadeIn(animationSpec = tween(durationMillis = 0)) +
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300))
    }

    private fun defaultExitTransition(): ExitTransition {
        return fadeOut(animationSpec = tween(durationMillis = 0)) +
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(300))
    }

    private fun defaultPopEnterTransition(): EnterTransition {
        return fadeIn(animationSpec = tween(durationMillis = 0)) +
                slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(300))
    }

    private fun defaultPopExitTransition(): ExitTransition {
        return fadeOut(animationSpec = tween(durationMillis = 0)) +
                slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300))
    }

}