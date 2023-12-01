package com.example.distancecoupleapp.presentation.main_board

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.distancecoupleapp.presentation.main_board.components.PhotoItem

@Composable
fun MainBoardScreen(
    viewModel: MainBoardViewModel,
    navController: NavController,
    roomId: String
) {
    val state = viewModel.mainBoardState
    viewModel.getPhotosFromDatabase(roomId)
    viewModel.getUsersFromDatabase(roomId)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .weight(10f)
        ) {
            items(state.photoList.size) {
                PhotoItem(viewModel = viewModel, state = state, it, roomId, navController)
            }
        }
        Box(modifier = Modifier
            .border(7.dp, Color.White, CircleShape)
            .size(80.dp)
            .clickable {
                viewModel.navigateToCameraScreen(navController, roomId)
            }
        )
    }
}