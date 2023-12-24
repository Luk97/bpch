package com.nickel.bpch.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nickel.bpch.R
import com.nickel.bpch.ui.splash.UiState.Navigating
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    val scale = remember {
        Animatable(0f)
    }
    val overshootInterpolator = remember {
        OvershootInterpolator(3f)
    }

    LaunchedEffect(Unit) {
        viewModel.state.collectLatest { uiState ->
            when (uiState) {
                is Navigating -> {
                    navController.popBackStack()
                    navController.navigate(uiState.route)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = {
                    overshootInterpolator.getInterpolation(it)
                }
            )
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}