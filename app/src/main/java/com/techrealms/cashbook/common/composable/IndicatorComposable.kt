package com.techrealms.cashbook.common.composable

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techrealms.cashbook.ui.theme.CashBookTheme
import com.techrealms.cashbook.ui.theme.NeutralGreyFive
import com.techrealms.cashbook.ui.theme.PrimaryBrand

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(pageCount){
            IndicatorSingleDot(isSelected = it == currentPage )
        }


    }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {

    val width = animateDpAsState(targetValue = if (isSelected) 35.dp else 15.dp, label = "")
    Box(modifier = Modifier
        .padding(3.dp)
        .height(4.dp)
        .width(16.dp)
        .clip(CircleShape)
        .background(if (isSelected) PrimaryBrand else NeutralGreyFive)
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview(){
    CashBookTheme {
        PageIndicator(pageCount = 3, currentPage = 1, modifier = Modifier)
    }
}
