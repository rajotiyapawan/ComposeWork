package com.rajotiya.mytestapp.loyalty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rajotiya.mytestapp.loyalty.ui.EarnMoreForRewardUI
import com.rajotiya.mytestapp.loyalty.ui.LandingPageUI
import com.rajotiya.mytestapp.loyalty.ui.LoyaltyPointsLedgerView
import com.rajotiya.mytestapp.loyalty.ui.LoyaltyRedeemRewardDetailsScreen


enum class LoyaltyScreens {
    LandingPage, RedeemReward, EarnMoreForReward, PointsLedger
}

class LoyaltyLandingActivity : ComponentActivity() {

    private val viewModel: LoyaltyViewModel by viewModels { LoyaltyViewModel.Factory }

    companion object {
        fun launchLoyaltyLandingActivity(context: Context) {
            val intent = Intent(context, LoyaltyLandingActivity::class.java)
            context.startActivity(intent)
        }

        val localViewModelCompositionLocal = staticCompositionLocalOf<LoyaltyViewModel> {
            error("No View Model Provided")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        HandleGaEvents()
        PrepareNavGraph(modifier = modifier, navController = navController, startDestination = startDestination)
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
                popExitTransition = { defaultPopExitTransition() }) {
                LandingPageUI(modifier = Modifier.fillMaxSize())
            }
            composable(route = LoyaltyScreens.EarnMoreForReward.name,
                enterTransition = { defaultEnterTransition() },
                exitTransition = { defaultExitTransition() },
                popEnterTransition = { defaultPopEnterTransition() },
                popExitTransition = { defaultPopExitTransition() }) {
                EarnMoreForRewardUI(modifier = Modifier.fillMaxSize())
            }
            composable(route = LoyaltyScreens.RedeemReward.name,
                enterTransition = { defaultEnterTransition() },
                exitTransition = { defaultExitTransition() },
                popEnterTransition = { defaultPopEnterTransition() },
                popExitTransition = { defaultPopExitTransition() }) {
                LoyaltyRedeemRewardDetailsScreen(modifier = Modifier.fillMaxSize())
            }
            composable(route = LoyaltyScreens.PointsLedger.name,
                enterTransition = { defaultEnterTransition() },
                exitTransition = { defaultExitTransition() },
                popEnterTransition = { defaultPopEnterTransition() },
                popExitTransition = { defaultPopExitTransition() }) {
                LoyaltyPointsLedgerView(modifier = Modifier.fillMaxSize())
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
                    if (event.saveToBackStack) {
                        navController.navigate(route = event.route)
                    } else {
                        navController.navigate(route = event.route) {
                            popUpTo(event.currentScreen) { inclusive = true }
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

    @Composable
    private fun HandleGaEvents() {
        val gaEvent by viewModel.gaEvents.observeAsState()
        gaEvent?.let { event ->
            when (event) {
                is LoyaltyGaEvents.BackClicked -> {}
                LoyaltyGaEvents.DoNothing -> {}
                is LoyaltyGaEvents.ResetClicked -> {}
                is LoyaltyGaEvents.ScreenLoad -> {}
                is LoyaltyGaEvents.SkipClicked -> {}
            }
            if (event !is LoyaltyGaEvents.DoNothing) {
                viewModel.clearGaEvent()
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

