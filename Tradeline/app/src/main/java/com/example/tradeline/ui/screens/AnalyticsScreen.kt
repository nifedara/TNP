package com.example.tradeline.ui.screens

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.screens.viewModel.AnalyticsViewModel
import com.example.tradeline.ui.screens.viewModel.BestSellingProduct

@Composable
fun AnalyticsScreen(
    userId: Int,
    viewModel: AnalyticsViewModel = viewModel(factory = AppViewModelProvider.createFactory(userId = userId))
){
    val analyticsUiState by viewModel.analyticsUiState.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = false
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),) {
            Text(text = "Best selling products", color =Color(0xFF2B2B85), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            if (analyticsUiState.dataList.isNotEmpty()) {
                PieChart(data = analyticsUiState.dataList)
            } else {
                Text(text = "No data available")
            }
        }
    }
}

@Composable
fun PieChart(
    data: List<BestSellingProduct>,
    radiusOuter: Dp = 90.dp,
    chartBarWidth: Dp = 20.dp,
    animDuration: Int = 1000,
) {

    var totalSum = 0 // Initialize totalSum to 0

    data.forEach { product ->
        totalSum += product.quantity // Sum up the quantity of each BestSellingProduct
    }

    val floatValue = mutableListOf<Float>()

    data.forEach { product ->
        floatValue.add(360 * product.quantity.toFloat() / totalSum.toFloat())
    }

    val colors = listOf(
        Color(0xFF2B2B85),
        Color(0xFFC4F0ED),
        Color(0xFF85D6EB),
        Color(0xFF6959CA),
        Color(0xFFE1F2CD)
    )

    var animationPlayed by remember { mutableStateOf(false) }

    var lastValue = 0f

    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed) radiusOuter.value * 2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) 90f * 11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    // to play the animation only once when the function is Created or Recomposed
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Pie Chart using Canvas Arc
        Box(
            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .size(radiusOuter * 2f)
                    .rotate(animateRotation)
            ) {
                // draw each Arc for each data entry in Pie Chart
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = colors[index],
                        lastValue,
                        value,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    lastValue += value
                }
            }
        }

        DetailsPieChart(
            data = data,
            colors = colors
        )

    }

}

@Composable
fun DetailsPieChart(
    data: List<BestSellingProduct>,
    colors: List<Color>
) {
    Column(
        modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth()
    ) {
        // create the data items
        data.forEachIndexed { index, value ->
            DetailsPieChartItem(
                data = Pair(value.name, value.quantity), // Access name and quantity from BestSellingProduct
                color = colors[index]
            )
        }

    }
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    height: Dp = 30.dp,
    color: Color
) {

    Surface(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 40.dp),
        color = Color.Transparent
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = color,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(height)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.first,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    color = Black
                )
                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = data.second.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }

        }

    }

}