package com.example.greatworkouts.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.greatworkouts.data.foodData
import com.example.greatworkouts.ui.components.FoodCard
import com.example.greatworkouts.ui.components.GridItem

@Composable
fun Food() {
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Food",
                    style = MaterialTheme.typography.headlineMedium
                )
                Row() {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Favourite Food"
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = "Filter Food"
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Food"
                        )
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(padding)
                .padding(bottom = 48.dp)
        ) {
            item() {
                Column() {
                    Text(
                        text = "Featured",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            bottom = 16.dp
                        )
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                        items(foodData) {
                            FoodCard(
                                name = it.name,
                                cookingTime = it.cookingTime,
                                calories = it.calories,
                                coverImage = it.coverImage
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }

            item() {
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(9) { index -> // 9 items for a 3x3 grid
                        GridItem()
                    }
                }
            }

            item() {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Breakfast",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                bottom = 16.dp
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(bottom = 18.dp)
                        ) {
                            Text(
                                text = "more",
                                color = Color.DarkGray,
                            )
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = "More",
                                tint = Color.DarkGray
                            )
                        }
                    }
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                        items(foodData) {
                            FoodCard(
                                name = it.name,
                                cookingTime = it.cookingTime,
                                calories = it.calories,
                                coverImage = it.coverImage
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }

            item() {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Breakfast",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                bottom = 16.dp
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(bottom = 18.dp)
                        ) {
                            Text(
                                text = "more",
                                color = Color.DarkGray,
                            )
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = "More",
                                tint = Color.DarkGray
                            )
                        }
                    }
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                        items(foodData) {
                            FoodCard(
                                name = it.name,
                                cookingTime = it.cookingTime,
                                calories = it.calories,
                                coverImage = it.coverImage
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
            item() {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Breakfast",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                bottom = 16.dp
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(bottom = 18.dp)
                        ) {
                            Text(
                                text = "more",
                                color = Color.DarkGray,
                            )
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = "More",
                                tint = Color.DarkGray
                            )
                        }
                    }
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                        items(foodData) {
                            FoodCard(
                                name = it.name,
                                cookingTime = it.cookingTime,
                                calories = it.calories,
                                coverImage = it.coverImage
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
            item() {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Breakfast",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                bottom = 16.dp
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(bottom = 18.dp)
                        ) {
                            Text(
                                text = "more",
                                color = Color.DarkGray,
                            )
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = "More",
                                tint = Color.DarkGray
                            )
                        }
                    }
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                        items(foodData) {
                            FoodCard(
                                name = it.name,
                                cookingTime = it.cookingTime,
                                calories = it.calories,
                                coverImage = it.coverImage
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
            item() {
                Column() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Breakfast",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.DarkGray,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                bottom = 16.dp
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.padding(bottom = 18.dp)
                        ) {
                            Text(
                                text = "more",
                                color = Color.DarkGray,
                            )
                            Icon(
                                imageVector = Icons.Filled.ChevronRight,
                                contentDescription = "More",
                                tint = Color.DarkGray
                            )
                        }
                    }
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                        items(foodData) {
                            FoodCard(
                                name = it.name,
                                cookingTime = it.cookingTime,
                                calories = it.calories,
                                coverImage = it.coverImage
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }
            }
        }
    }
}



