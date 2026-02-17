package com.example.createinlager.presentation.theme.ui

import android.icu.text.CaseMap
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.createinlager.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )


    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val RaleWayFamily = FontFamily(
    Font(R.font.raleway_regular, FontWeight.Normal),
    Font(R.font.raleway_bold, FontWeight.Bold),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_semibold, FontWeight.SemiBold)
)
//private val RaleWayTypography = Typography(
//    bodyMedium = TextStyle(
//        fontFamily = com.example.createinlager.presentation.theme.ui.RaleWayFamily,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp
//    )
//    // можно определить и другие текстовые стили
//)
//
//@Composable
//fun RaleWayTheme(content: @Composable () -> Unit) {
//    MaterialTheme(
//        typography = RaleWayTypography,
//        content = content
//    )
//}

val TitleType = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(700),
    fontSize = 30.sp,
    lineHeight = 30.sp,
    letterSpacing = 0.sp
)

val mapText = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(700),
    fontSize = 20.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

val TitleCategoryType = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(600),
    fontSize = 16.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

val TextOnBoardType = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 34.sp,
    lineHeight = 44.sp,
    letterSpacing = 0.sp
)

val TextOnBoardTypeSmall = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
)

val CategoryName = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(500),
    fontSize = 16.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

val ButtonText = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 14.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp
)

val TypeCostDetails = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 24.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
)

val TextFieldPlace = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 14.sp,
    lineHeight = 16.sp,
)

val TypeIntCart = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 14.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp

)

val TextInfo = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp

)

val TitleAuth = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 32.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)

val nameTextField = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(500),
    fontSize = 16.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

val cartSmallTitleStyle = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(500),
    fontSize = 14.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp
)

val cartSmallTitleStyleTwo = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(500),
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

val TextUnderLine = TextStyle(fontFamily = RaleWayFamily,
fontWeight = FontWeight.Normal,
fontSize = 16.sp,
lineHeight = 17.sp,
letterSpacing = 0.sp,
    textDecoration = TextDecoration.Underline
)

val bottomText = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 16.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp,
)


val miniTextButton = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp,
)

val textInFiedMarket = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 12.sp,
    lineHeight = 14.sp,
    letterSpacing = 0.sp,
)

val textCategory = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 1.sp,
)

val TitleDetails = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 26.sp,
    lineHeight = 26.sp,
    letterSpacing = 0.sp,
)

val ProfileName = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 20.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp,
)

val checkouttype = TextStyle(
    fontFamily = RaleWayFamily,
    fontWeight = FontWeight(400),
    fontSize = 20.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp,
)



