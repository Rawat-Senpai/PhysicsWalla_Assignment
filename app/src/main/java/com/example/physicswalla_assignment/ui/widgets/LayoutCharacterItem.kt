package com.example.physicswalla_assignment.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.physicswalla_assignment.R
import com.example.physicswalla_assignment.models.Result
import com.example.physicswalla_assignment.ui.theme.poppinsFontFamily
@Composable
fun LayoutCharacterItem(character: Result, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },

        shape = RoundedCornerShape(8.dp),

    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min) // Adjust the height based on the content
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.image),
                contentDescription = character.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterVertically), // Center the image vertically
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center // Center the text vertically in the column
            ) {
                Text(text = character.name, fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)))
                Text(text = "Species: ${character.species}", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)))
                Text(text = "Gender: ${character.gender}", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)))
                Text(text = "Status: ${character.status}", fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)))
            }
        }
    }
}
