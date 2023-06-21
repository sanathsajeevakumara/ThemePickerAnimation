package com.sanathcoding.themepickeranimation.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sanathcoding.themepickeranimation.presentation.component.CirclePath
import com.sanathcoding.themepickeranimation.ui.theme.ThemePickerAnimationTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ThemePickerBottom() {
    var theme by remember { mutableStateOf(lightTheme) }
    var animationOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    
    AnimatedContent(
        targetState = theme,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) { currentTheme ->

        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp * 0.49f

        val revealSize = remember { Animatable(1f) }
        LaunchedEffect(key1 = "reveal", block = {
            if (animationOffset.x > 0f) {
                revealSize.snapTo(0f)
                revealSize.animateTo(
                    1f,
                    tween(800)
                )
            } else revealSize.snapTo(1f)
        })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CirclePath(revealSize.value, animationOffset))
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(currentTheme.backgroundColor)
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight)
                    ) {
                        Image(
                            painter = painterResource(id = currentTheme.image),
                            contentDescription = "HeaderImage",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(configuration.screenHeightDp.dp * .80f)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Transparent,
                                        currentTheme.backgroundColor.copy(1f),
                                        currentTheme.backgroundColor,
                                        currentTheme.backgroundColor,
                                    )
                                )
                            )
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ThemeButton(
                            theme = lightTheme,
                            currentTheme = currentTheme,
                            text = "Light Theme",
                        ) {
                            animationOffset = it
                            theme = lightTheme
                        }
                        Spacer(modifier = Modifier.width(8.dp))

                        ThemeButton(
                            theme = pinkTheme,
                            currentTheme = currentTheme,
                            text = "Pink Theme",
                        ) {
                            animationOffset = it
                            theme = pinkTheme
                        }
                        Spacer(modifier = Modifier.width(8.dp))

                        ThemeButton(
                            theme = darkTheme,
                            currentTheme = currentTheme,
                            text = "Dark Theme",
                        ) {
                            animationOffset = it
                            theme = darkTheme
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThemePickerBottomPreview() {
    ThemePickerAnimationTheme {
        ThemePickerBottom()
    }
}