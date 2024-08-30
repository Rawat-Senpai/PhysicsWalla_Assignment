package com.example.physicswalla_assignment.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.physicswalla_assignment.models.Result

@Composable
fun  LayoutCharacterItem(character : Result ) {
    
    Row (modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()){

        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = character.name,
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
        ){
            Text(text = character.name, fontSize = 20.sp)
            Text(text = "Species: ${character.species}", fontSize = 16.sp)
            Text(text = "Gender: ${character.gender}", fontSize = 16.sp)
            Text(text = "Status: ${character.status}", fontSize = 16.sp)
        }




    }
    
}