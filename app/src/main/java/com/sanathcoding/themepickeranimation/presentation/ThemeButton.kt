package com.sanathcoding.themepickeranimation.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanathcoding.themepickeranimation.R
import com.sanathcoding.themepickeranimation.ui.theme.ThemePickerAnimationTheme

@Composable
fun ThemeButton(
    theme: CustomTheme = CustomTheme(
        primaryColor = Color(0xFFF01EE5),
        backgroundColor = Color(0xFF110910),
        textColor = Color(0xFFEE8CE1),
        image = R.drawable.pink,
    ),
    currentTheme: CustomTheme = CustomTheme(
        primaryColor = Color(0xFFF01EE5),
        backgroundColor = Color(0xFF110910),
        textColor = Color(0xFFEE8CE1),
        image = R.drawable.pink,
    ),
    text: String = "Pink Theme",
    onClick: (Offset) -> Unit = {}
) {
    val isSelected = theme == currentTheme
    var offset: Offset = remember { Offset(0f, 0f) }
    Column(
        horizontalAlignment = Alignment
            .CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .onGloballyPositioned {
                    offset = Offset(
                        x = it.positionInWindow().x + it.size.width / 2,
                        y = it.positionInWindow().y + it.size.height / 2,
                    )
                }
                .size(110.dp)
                .border(
                    4.dp,
                    color = if (isSelected) theme.primaryColor else Color.Transparent,
                    shape = CircleShape
                )
                .padding(8.dp)
                .background(color = theme.primaryColor, shape = CircleShape)
                .clip(CircleShape)
                .clickable { onClick(offset) }
        ) {
            Image(
                painter = painterResource(id = theme.image),
                contentDescription = "themeImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text.uppercase(),
            color = currentTheme.textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(2.dp)
                .alpha(if (isSelected) 1f else .5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ThemeButtonPreview() {
    ThemePickerAnimationTheme {
        ThemeButton()
    }
}