package com.nickel.bpch.feature_login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nickel.bpch.R
import com.nickel.bpch.core.ui.components.StandardTextField
import com.nickel.bpch.core.ui.theme.Shapes
import com.nickel.bpch.core.ui.theme.SpaceLarge
import com.nickel.bpch.core.ui.theme.SpaceMedium
import com.nickel.bpch.core.util.UiEvent.Navigate
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is Navigate -> {
                    navController.navigate(event.route)
                }
                else -> {}
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SpaceLarge),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .padding(top = SpaceLarge),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.bpch_logo) ,
                contentDescription = stringResource(id = R.string.logo),
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(4.dp, MaterialTheme.colorScheme.primary, Shapes.extraLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(SpaceLarge))
            Text(text = "Login", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.email,
                hint = stringResource(id = R.string.email_hint),
                onValueChange = viewModel::onEmailValueChange,
                keyboardType = KeyboardType.Email,
                leadingIcon = Icons.Rounded.Email
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = state.password,
                hint = stringResource(id = R.string.password_hint),
                onValueChange = viewModel::onPasswordValueChange,
                onPasswordVisibilityChange = viewModel::onPasswordVisibilityChange,
                passwordVisible = state.passwordVisible,
                keyboardType = KeyboardType.Password,
                leadingIcon = Icons.Rounded.Password
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                onClick = viewModel::onLoginClick
            ) {
                Text(text = stringResource(id = R.string.login))
            }
            Spacer(modifier = Modifier.height(SpaceLarge))
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}