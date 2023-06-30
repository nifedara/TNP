package com.example.tradeline.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.tradeline.R

/** Typography for Tradeline .
 * Tradeline uses Roboto font */

/** define Roboto */
val Roboto = FontFamily(
    Font(R.font.roboto_black),
    Font(R.font.roboto_black_italic),
    Font(R.font.roboto_bold),
    Font(R.font.roboto_bold_italic),
    Font(R.font.roboto_italic),
    Font(R.font.roboto_light),
    Font(R.font.roboto_light_italic),
    Font(R.font.roboto_medium),
    Font(R.font.roboto_medium_italic),
    Font(R.font.roboto_regular),
    Font(R.font.roboto_thin),
    Font(R.font.roboto_thin_italic),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 6.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 8.sp
    )

)