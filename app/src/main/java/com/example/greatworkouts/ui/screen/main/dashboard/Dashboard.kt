package com.example.greatworkouts.ui.screen.main.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaikeerthick.composable_graphs.composables.bar.BarGraph
import com.jaikeerthick.composable_graphs.composables.bar.model.BarData
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphColors
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphStyle
import com.jaikeerthick.composable_graphs.composables.bar.style.BarGraphVisibility
import com.jaikeerthick.composable_graphs.style.LabelPosition

@Composable
fun Dashboard(
    goToSleep: () -> Unit,
    goToGlucose: () -> Unit,
    goToWorkout: () -> Unit,
    goToNutrition: () -> Unit,
    goToSteps: () -> Unit,
    goToFood: () -> Unit,
    goToBloodPressure: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val category = listOf(
        "Sleep",
        "Recipes",
        "Workout",
        "Blood Glucose",
        "Blood Pressure",
        "Nutrition"
    )
    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        item {
            CaloriesCard()
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                StepsCard(
                    goToSteps = goToSteps,
                    count = 5000,
                    goal = 10000,
                    modifier = Modifier.width(250.dp)
                        .clickable { goToSteps() }
                )
                ExerciseCard(
                    goToWorkout = goToWorkout,
                    value = 1000,
                    hour = "1:00",
                    modifier = Modifier.width(250.dp)
                )
            }
        }
        item {
            // Horizontal Pager for Graphs
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                when (page) {
                    0 -> StepsGraph()
                    1 -> CaloriesBurnedGraph()
                }
            }
//            Indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(10.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterVertically)
                            .background(
                                if (pagerState.currentPage == it) Color.Blue else Color.LightGray
                            )
                    )
                }
            }
        }
        item {
            Text(
                text = "More",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 columns per row
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier= Modifier.height(400.dp)
            ) {
                items(6) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clickable {
                                when (index) {
                                    0 -> goToSleep()
                                    1 -> goToFood()
                                    2 -> goToWorkout()
                                    3 -> goToGlucose()
                                    4 -> goToBloodPressure()
                                    5 -> goToNutrition()
                                }
                            },
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors= CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier= Modifier.fillMaxSize()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Flag,
                                contentDescription = null,
                                modifier = Modifier.padding(top=16.dp, bottom = 16.dp)

                            )
                            Text(
                                text = category[index],
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StepsCard(
    modifier: Modifier = Modifier,
    count: Int,
    goal: Int,
    goToSteps: () -> Unit
) {
    val progress = count.toFloat() / goal.toFloat()
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.height(120.dp)
            .clickable {
                goToSteps()
            }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Steps",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Flag,
                    contentDescription = null,

                    )
                Text(
                    text = "$count",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Text(
                text = "Goal: 10,000 steps",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Black
                )
            )
            Canvas(
                modifier = Modifier
                    .width(150.dp)
                    .height(5.dp)
                    .padding(top = 8.dp, bottom = 8.dp)
            ) {
                val canvasWidth = 430f
                val progressWidth = canvasWidth * progress

                drawRect(
                    color = Color.LightGray,
                    size = Size(canvasWidth, 10f)
                )
                drawRect(
                    color = Color(0xFFFFA500),
                    size = Size(progressWidth, 10f)
                )
            }
        }
    }
}

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    value: Int = 0,
    hour: String,
    goToWorkout: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                goToWorkout()
            }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Exercise",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.LocalFireDepartment,
                    contentDescription = null,
                    tint = Color(0xFFFFA500)
                )
                Text(
                    text = "$value Cal",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.WatchLater,
                    contentDescription = null,
                    tint = Color(0xFFFFA500)
                )
                Text(
                    text = "$hour hr",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@Composable
fun CaloriesCard(
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    val goal = 2020
    val food = 1000
    val exercise = 500
    val remaining = goal - food + exercise
    val percentage: Float = remaining.toFloat() / goal.toFloat()
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Text(
            text = "Calories",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )
        Text(
            text = "Remaining = Goal - Food + Exercise",
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.Gray
            ),
            modifier = Modifier.padding(top = 4.dp, start = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(48.dp),
            modifier = modifier.padding(horizontal = 48.dp, vertical = 16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                CircularRing(
                    percentage = percentage
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$remaining",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "Remaining",
                        style = MaterialTheme.typography.titleMedium
                            .copy(
                                color = Color.Black
                            )
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CaloriesCardLabel(
                    label = "Goal",
                    value = goal,
                    icon = Icons.Default.Flag,
                    tint = Color.DarkGray
                )
                CaloriesCardLabel(
                    label = "Food",
                    value = food,
                    icon = Icons.Default.Restaurant,
                    tint = Color.Blue
                )
                CaloriesCardLabel(
                    label = "Exercise",
                    value = exercise,
                    icon = Icons.Default.LocalFireDepartment,
                    tint = Color(0xFFFFA500)
                )
            }
        }
    }
}

@Composable
fun CircularRing(percentage: Float) {
    Canvas(
        modifier = Modifier.size(120.dp)
    ) {
        val strokeWidth = 10.dp.toPx()
        val radius = (size.minDimension - strokeWidth) / 2
        val center = Offset(size.width / 2, size.height / 2)

        drawCircle(
            color = if (percentage >= 1f) Color(0xFFFFA500) else Color.Gray,
            center = center,
            radius = radius,
            style = Stroke(width = strokeWidth)
        )

        if (percentage < 1f) {
            drawArc(
                color = Color(0xFFFFA500),
                startAngle = -90f,
                sweepAngle = percentage * 360f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = strokeWidth)
            )
        }
    }
}

@Composable
fun CaloriesCardLabel(
    label: String,
    value: Int,
    icon: ImageVector,
    tint: Color? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (tint != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = tint
            )
        } else {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
        Column() {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Gray
                )
            )
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun StepsGraph(
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    var bardata by remember { mutableStateOf(0) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            Text(
                text = "Steps",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(100.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "This Week",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(top = 4.dp, start = 16.dp)
                )
                Text(
                    text = "${if (bardata == 0) "" else bardata}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Black
                    ),
                )
            }
            BarGraph(
                data = listOf(
                    BarData(x = "Sun", y = 5000),
                    BarData(x = "Mon", y = 4000),
                    BarData(x = "Tue", y = 8000),
                    BarData(x = "Wed", y = 7000),
                    BarData(x = "Thu", y = 6000),
                    BarData(x = "Fri", y = 3500),
                    BarData(x = "Sat", y = 9000)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                style = BarGraphStyle(
                    yAxisLabelPosition = LabelPosition.LEFT,
                    visibility = BarGraphVisibility(
                        isYAxisLabelVisible = true
                    ),
                    colors = BarGraphColors(
                        xAxisTextColor = Color.Gray,
                        yAxisTextColor = Color.Gray,
                    )
                ),
                onBarClick = { data -> bardata = data.y as Int }
            )
        }
    }
}

@Composable
fun CaloriesBurnedGraph(
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    var bardata by remember { mutableStateOf(0) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            Text(
                text = "Total Calories Burned(in kcal)",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "This Week",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(top = 4.dp, start = 16.dp)
                )
                Text(
                    text = "${if (bardata == 0) "" else bardata}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Black
                    ),
                )
            }
            BarGraph(
                data = listOf(
                    BarData(x = "Sun", y = 1500),
                    BarData(x = "Mon", y = 1800),
                    BarData(x = "Tue", y = 1700),
                    BarData(x = "Wed", y = 1650),
                    BarData(x = "Thu", y = 1500),
                    BarData(x = "Fri", y = 2000),
                    BarData(x = "Sat", y = 1900)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                style = BarGraphStyle(
                    yAxisLabelPosition = LabelPosition.LEFT,
                    visibility = BarGraphVisibility(
                        isYAxisLabelVisible = true
                    ),
                    colors = BarGraphColors(
                        xAxisTextColor = Color.Gray,
                        yAxisTextColor = Color.Gray,
                    )
                ),
                onBarClick = { data -> bardata = data.y as Int }
            )
        }
    }
}


@Composable
fun MoreCard(
    name: String,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(120.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocalFireDepartment,
                contentDescription = null,
                tint = Color(0xFFFFA500)
            )
            Text(
                text = "Calories",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
