package com.example.distancecoupleapp.presentation.main_board.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.distancecoupleapp.presentation.main_board.MainBoardState
import com.example.distancecoupleapp.presentation.main_board.MainBoardViewModel
import com.example.distancecoupleapp.presentation.theme.Grey

@Composable
fun PhotoItem(
    viewModel: MainBoardViewModel,
    state:MainBoardState,
    it: Int,
    roomId:String,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .height(50.dp)
                    .aspectRatio(1f),
                tint = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(3.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = viewModel.getUserNameById(state.photoList[it].owner),
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                    )
                Text(
                    text = viewModel.convertMillisToDateTime(state.photoList[it].timestamp),
                    color = Grey
                )
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(19.dp)),
        ){
            Image(painter = rememberAsyncImagePainter(state.photoList[it].imageUrl),
                contentDescription = "photo",
                modifier = Modifier.aspectRatio(0.7f),
                contentScale = ContentScale.Crop)
        }
        Text(
            text = state.photoList[it].description,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(text = "Add a comment...",
            color = Grey,
            modifier = Modifier.clickable {
                viewModel.navigateToCommentScreen(navController, roomId, state.photoList[it].id)
            })
        Spacer(modifier = Modifier.height(12.dp))
    }
}