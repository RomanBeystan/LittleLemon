package com.example.littlelemon.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

// Set of Material typography styles to start with

val littleLemonFamily = FontFamily(
    Font(R.font.karla_regular, FontWeight.Normal),

    )
val littleLemonFamilymarkazitext = FontFamily(
    Font(R.font.markazitext_regular, FontWeight.Bold),

    )

val TypographyLittleLemon = Typography(
    body1 = TextStyle(
        fontFamily = littleLemonFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    h4 = TextStyle(
        fontFamily = littleLemonFamilymarkazitext,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp
    ),
    h6 = TextStyle(
        fontFamily = littleLemonFamilymarkazitext,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.1.sp
    ),
    h3 = TextStyle(
        fontFamily = littleLemonFamilymarkazitext,
        fontWeight = FontWeight.Bold,
        color = PrimaryColorTwo,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),

    subtitle1 = TextStyle(
        fontFamily = littleLemonFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),

    body2 = TextStyle(
        fontFamily = littleLemonFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White,
        background = PrimaryColorOne
    ),

    button = TextStyle(
        fontFamily = littleLemonFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = littleLemonFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp
    )

)