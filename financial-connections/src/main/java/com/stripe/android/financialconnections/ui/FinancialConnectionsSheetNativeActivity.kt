package com.stripe.android.financialconnections.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.viewModel
import com.stripe.android.financialconnections.navigation.NavigationDirections
import com.stripe.android.financialconnections.presentation.FinancialConnectionsSheetNativeViewModel

internal class FinancialConnectionsSheetNativeActivity : AppCompatActivity() {

    val viewModel: FinancialConnectionsSheetNativeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Box(modifier = Modifier.weight(1f)) { NavHost() }
            }
        }
    }

    @Composable
    fun NavHost() {
        val navController = rememberNavController()
        NavigationEffect(navController)
        NavHost(navController, startDestination = NavigationDirections.consent.destination) {
            composable(NavigationDirections.consent.destination) {
                ConsentScreen()
            }
            composable(NavigationDirections.bankPicker.destination) {
                BankPickerScreen()
            }
        }
    }

    @Composable
    private fun NavigationEffect(navController: NavHostController) {
        LaunchedEffect(viewModel.navigationManager.commands) {
            viewModel.navigationManager.commands.collect { command ->
                if (command.destination.isNotEmpty()) {
                    navController.navigate(command.destination)
                }
            }
        }
    }
}