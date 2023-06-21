package com.sanathcoding.themepickeranimation.presentation

import androidx.compose.ui.graphics.Color
import com.sanathcoding.themepickeranimation.R

data class CustomTheme(
    val primaryColor: Color,
    val backgroundColor: Color,
    val textColor: Color,
    val image: Int,
)

val darkTheme = CustomTheme(
    primaryColor = Color(0xFFE9B518),
    backgroundColor = Color(0xFF111111),
    textColor = Color(0x88E8C660),
    image = R.drawable.dark,
)

val lightTheme = CustomTheme(
    primaryColor = Color(0xFFFFFFFF),
    backgroundColor = Color(0xFFF1F1F1),
    textColor = Color(0xFF232526),
    image = R.drawable.light,
)

val pinkTheme = CustomTheme(
    primaryColor = Color(0xFFF01EE5),
    backgroundColor = Color(0xFF110910),
    textColor = Color(0x8FEE8CE1),
    image = R.drawable.pink,
)