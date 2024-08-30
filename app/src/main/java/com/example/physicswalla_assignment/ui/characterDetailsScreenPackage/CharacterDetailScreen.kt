package com.example.physicswalla_assignment.ui.characterDetailsScreenPackage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.physicswalla_assignment.models.AnimeCharacterDetailsResponseModel
import com.example.physicswalla_assignment.ui.homePackage.CharacterViewModel
import com.example.physicswalla_assignment.ui.widgets.ErrorPopup
import com.example.physicswalla_assignment.utils.NetworkResult

@Composable
fun CharacterDetailScreen(characterId: String?,viewModel: CharacterViewModel) {
    // Display character details based on the characterId
    // You can retrieve details via the ViewModel or other means



    val characterDetailsState by viewModel.getCharacterDetails.observeAsState()
    LaunchedEffect(Unit) {
        Log.d("valueCheck",characterId.toString())
        characterId?.let {
            viewModel.getCharacterDetails(characterId.toString())
        }

    }



    when(characterDetailsState){
        is NetworkResult.Error -> {
            Log.d("checking",characterDetailsState?.message.toString())
            val errorMessage = (characterDetailsState as NetworkResult.Error).message
            ErrorPopup(message = errorMessage ?: "An unknown error occurred")
        }
        is NetworkResult.Loading -> {
//            ProgressBar()
        }
        is NetworkResult.Success -> {

            Log.d("checking",characterDetailsState?.data.toString())
            characterDetailsState?.data?.let { SetDetailUi(it) }

        }
        null -> {}
    }

}

@Composable
fun SetDetailUi(character: AnimeCharacterDetailsResponseModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFf6f7f9),
                        Color(0xFFe2e8f0)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // Character Image
        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = character.name,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Character Name
        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1f2937)
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Character Status
        StatusChip(character.status)

        Spacer(modifier = Modifier.height(8.dp))

        // Character Details
        CharacterDetailItem("Species", character.species)
        CharacterDetailItem("Gender", character.gender)
        CharacterDetailItem("Origin", character.origin.name)
        CharacterDetailItem("Location", character.location.name)

        Spacer(modifier = Modifier.height(16.dp))

        // Episodes
        Text(
            text = "Episodes:",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1f2937)
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(character.episode) { episode ->
                Text(
                    text = episode,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .background(
                            color = Color(0xFFe5e7eb),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun StatusChip(status: String) {
    val backgroundColor = when (status) {
        "Alive" -> Color(0xFF34D399)
        "Dead" -> Color(0xFFEF4444)
        else -> Color(0xFFF59E0B)
    }

    Text(
        text = status,
        color = Color.White,
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 4.dp, horizontal = 8.dp),
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun CharacterDetailItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(2f)
        )
    }
}

