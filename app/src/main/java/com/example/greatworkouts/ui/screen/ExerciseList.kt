package com.example.greatworkouts.ui.screen

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greatworkouts.R
import com.example.greatworkouts.data.Category
import com.example.greatworkouts.data.Exercise
import com.example.greatworkouts.data.Tool
import com.example.greatworkouts.utils.getImageBitmapFromAssets


@Composable
fun ExerciseList(
    exerciseViewModel: ExerciseViewModel = viewModel(factory = ExerciseViewModel.Factory)
) {
    val containerColor = Color(0xFFCCCCCC)

    var categoryName by remember { mutableStateOf<List<String>?>(null) }
    println("Category Name: $categoryName")
    var toolName by remember { mutableStateOf<List<String>?>(null) }
    println("Tool Name: $toolName")
    var difficulty by remember { mutableStateOf<List<String>?>(null) }
    var standing by remember { mutableStateOf<Boolean?>(null) }
    var noise by remember { mutableStateOf<Boolean?>(null) }
    var searchText by remember { mutableStateOf<String>("")}

    var searchView: Boolean = categoryName != null || toolName != null || difficulty != null || standing != null || noise != null || searchText.isNotEmpty()
    var filterView: Boolean by remember { mutableStateOf(false) }
    var currentView: String by remember { mutableStateOf("Main") }

    var categoryList = exerciseViewModel.getAllCategories().collectAsState(initial = listOf()).value
    var fitnessTools = exerciseViewModel.getAllTools().collectAsState(initial = listOf()).value

    val exerciseList = if (difficulty == null) {
    exerciseViewModel.getExerciseList(
        searchText = searchText,
        categoryName = categoryName,
        toolName = toolName,
        standing = standing,
        noise = noise
    ).collectAsState(initial = listOf()).value
    } else {
        exerciseViewModel.getExerciseList(
            searchText = searchText,
            categoryName = categoryName,
            toolName = toolName,
            standing = standing,
            difficulty = difficulty!!,
            noise = noise
        ).collectAsState(initial = listOf()).value
    }

    LaunchedEffect(searchView, filterView) {
        currentView = when {
            filterView -> "Filter"
            searchView -> "Search"
            else -> "Main"
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { /* Handle back button click */ }
                    ) {
                        Icon(
                            Icons.Default.ChevronLeft,
                            tint = Color.Black,
                            contentDescription = null,
                        )
                    }
                    Text(
                        text = "Exercises",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    IconButton(
                        onClick = { filterView = !filterView }
                    ) {
                        Icon(
                            Icons.Filled.FilterList,
                            tint = Color.Black,
                            contentDescription = null,
                        )
                    }

                }
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = containerColor,
                        unfocusedContainerColor = containerColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                    },
                    placeholder = {
                        Text(
                            text = "Search",
                            color = Color.Gray
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(32.dp))
            }
        }
    ) { padding ->
        when (currentView) {
           "Main" -> Main(
                categoryList = categoryList,
                fitnessTools = fitnessTools,
                borderColor = containerColor,
               padding = padding
           )
            "Search" -> Search(
                exerciseList = exerciseList,
                padding = padding,
                borderColor = containerColor
            )
            "Filter" -> FilterView(
                addToTool = { toolName =
                    if (toolName == null) {
                        listOf(it)
                    } else {
                        toolName!!.plus(it)
                    }
                            },
                addToCategories = { categoryName =
                    if (categoryName == null) {
                        listOf(it)
                    } else {
                        categoryName!!.plus(it)
                    }
                                  },
                selectedCategories = categoryName ?: emptyList<String>(),
                selectedTools = toolName ?: emptyList<String>(),

                changeView = { currentView = it },
                padding = padding
            )
            else -> Main(
                categoryList = categoryList,
                fitnessTools = fitnessTools,
                borderColor = containerColor,
                padding = padding
            )
        }
    }
}

@Composable
fun CategoryCard(
    name: String,
    shape: Shape,
    iconName: Int,
    borderModifier: Modifier
) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .then(borderModifier)
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 16.dp)
        ) {
            Icon(
                painter = painterResource(iconName),
                contentDescription = null,
                tint = Color.Blue,
                modifier = Modifier.size(70.dp)
            )
            Spacer(Modifier.weight(0.1f))
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.weight(1f))
            Icon(
                Icons.Default.ChevronRight,
                tint = Color.DarkGray,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun ExerciseCard(
    name: String,
    shape: Shape,
    thumbnail: String,
    context: Context,
    borderModifier: Modifier
) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .then(borderModifier)
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 16.dp)
        ) {
            Image(
                bitmap= getImageBitmapFromAssets(
                    context = context,
                    imagePath= thumbnail
                )!!,
                contentScale = ContentScale.Crop,
                contentDescription = "Exercise Thumbnail",
                modifier = Modifier.padding(end= 20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .size(
                        width= 70.dp,
                        height= 50.dp
                    )
            )
            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun Main(
    categoryList: List<Category>,
    fitnessTools: List<Tool>,
    borderColor: Color,
    padding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
    ) {
        itemsIndexed(categoryList) { index, item ->
            val iconName = nameToDrawable(item.name)
            val shape = when (index) {
                0 -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                categoryList.size - 1 -> RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )

                else -> RoundedCornerShape(0.dp)
            }
            val borderModifier = when (index) {
                categoryList.size - 1 -> Modifier.border(Dp.Hairline, borderColor)
                else -> Modifier.border(BorderStroke(Dp.Hairline, borderColor))
            }
            CategoryCard(
                item.name,
                shape,
                iconName,
                borderModifier
            )
        }

        item {
            Spacer(Modifier.height(40.dp))
            Text(
                text = "Fitness Tools",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(20.dp))
        }

        itemsIndexed(fitnessTools) { index, item ->
            val iconName = nameToDrawable(item.name)
            val shape = when (index) {
                0 -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                fitnessTools.size - 1 -> RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )

                else -> RoundedCornerShape(0.dp)
            }
            val borderModifier = when (index) {
                fitnessTools.size - 1 -> Modifier.border(Dp.Hairline, borderColor)
                else -> Modifier.border(BorderStroke(Dp.Hairline, borderColor))
            }
            CategoryCard(
                item.name,
                shape,
                iconName,
                borderModifier
            )
        }
    }
}

@Composable
fun Search(
    exerciseList: List<Exercise>,
    padding: PaddingValues,
    borderColor: Color
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
    ) {
        itemsIndexed(exerciseList) { index, item ->
            val shape = when (index) {
                0 -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                categoryList.size - 1 -> RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )

                else -> RoundedCornerShape(0.dp)
            }
            val borderModifier = when (index) {
                categoryList.size - 1 -> Modifier.border(Dp.Hairline, borderColor)
                else -> Modifier.border(BorderStroke(Dp.Hairline, borderColor))
            }
            ExerciseCard(
                name = item.name,
                shape = shape,
                thumbnail = item.thumbnail,
                context = context,
                borderModifier = borderModifier
            )
        }
    }
}

@Composable
fun FilterView(
    addToTool: (String) -> Unit,
    addToCategories: (String) -> Unit,
    selectedCategories: List<String>,
    selectedTools: List<String>,
    changeView: (String) -> Unit,
    padding: PaddingValues
) {
    val categories = listOf(
        "Abs & Core",
        "Upper Body",
        "Lower Body",
        "Cardio",
        "Stretching",
        "Yoga"
    )
    val tools = listOf(
        "BOSU",
        "Barbell",
        "Body Weight",
        "Dumbbell",
        "Foam Roller",
        "Kettlebell",
        "Medicine Ball",
        "Pull Up Bar",
        "Resistance Band",
        "Suspension System",
        "Swiss Ball"
    )

    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .padding(horizontal = 16.dp)
    ) {
        item() {
            CategorySection("Category", categories, selectedCategories) { addToCategories(it) }
        }
        item() {
            CategorySection("Fitness Tool", tools, selectedTools) { addToTool(it) }
        }
        item() {
            Text(
                text = "Tool",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray
            )
        }
        item() {
            Text(
                text = "Tool",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray
            )
        }
        item() {
            Text(
                text = "Tool",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray
            )
            Button(
                onClick = { changeView("Search") }
            ) {
                Text("Done")
            }
        }
    }
}



fun nameToDrawable(name: String): Int {
    return when (name) {
        "Abs & Core" -> R.drawable.abs_core
        "Upper Body" -> R.drawable.upper_body
        "Lower Body" -> R.drawable.lower_body
        "Cardio" -> R.drawable.cardio
        "Stretching" -> R.drawable.stretching
        "Yoga" -> R.drawable.yoga
        "BOSU" -> R.drawable.bosu
        "Barbell" -> R.drawable.barbell
        "Dumbbell" -> R.drawable.dumbbell
        "Foam Roller" -> R.drawable.foam_roller
        "Swiss Ball" -> R.drawable.swissball
        "Kettlebell" -> R.drawable.kettlebell
        "Medicine Ball" -> R.drawable.medicine_ball
        "Pull Up Bar" -> R.drawable.pullup_bar
        "Resistance Band" -> R.drawable.resistance_band
        "Suspension System" -> R.drawable.suspension_system
        else -> {
            R.drawable.medicine_ball
        }
    }
}

