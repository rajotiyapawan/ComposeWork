package com.magicbricks.prime.grid.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.magicbricks.prime.grid.data.repository.PrimeGridRepositoryImpl
import com.magicbricks.prime.grid.domain.usecase.GetPrimeGridPackageUseCase
import com.magicbricks.prime.grid.presentation.viewmodel.PrimeGridMviVM
import com.magicbricks.prime.grid.presentation.viewmodel.PrimeGridMviVMFactory
import com.rajotiya.mytestapp.aob_revamp.ui.theme.AOBTheme

class PrimeGridActivity : ComponentActivity() {

    private val viewModel: PrimeGridMviVM by viewModels {
        PrimeGridMviVMFactory(
            GetPrimeGridPackageUseCase(PrimeGridRepositoryImpl())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AOBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.uiState.collectAsState()

//                    PrimeGridScreen(
//                        state = state,
//                        onIntent = { intent -> viewModel.processIntent(intent) },
//                        effectFlow = viewModel.effect,
//                        onEffect = { effect ->
//                            when (effect) {
//                                is PrimeGridEffect.ShowToast -> {
//                                    Toast.makeText(this, effect.message, Toast.LENGTH_SHORT).show()
//                                }
//                                is PrimeGridEffect.ShowTransparencyDialog -> {
//                                    Toast.makeText(this, "Transparency Promise Clicked", Toast.LENGTH_SHORT).show()
//                                }
//                                is PrimeGridEffect.StartPayment -> {
//                                    Toast.makeText(this, "Starting payment for package", Toast.LENGTH_LONG).show()
//                                }
//                                is PrimeGridEffect.NavigateBack -> {
//                                    finish()
//                                }
//                            }
//                        }
//                    )
                    MBPrimeLandingScreen()
                }
            }
        }
    }
}
