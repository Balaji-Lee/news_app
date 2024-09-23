package com.example.mykotlinapp.ui.screens
import CustomTopAppBar
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mykotlinapp.model.dto.ArticleDTO
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest


@Composable
fun homeScreenComposable(totalRecordsState: String, record: List<ArticleDTO>) {
    androidx.compose.material3.Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Home",
                onNavigationClick = { /*TODO*/ },
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = paddingValues,

            ) {
                items(record) { news ->
                    newsCard(news)
                }

            }

        }
    )
}

@Composable
fun newsCard(news: ArticleDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ){
        news.title.let {
            if (it != "[Removed]" && it != "null" && it != null) {
                androidx.compose.material3.Text(
                    text = it,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        news.description.let {
            if (it != "[Removed]" && it != "null" && it != null) {
                androidx.compose.material3.Text(
                    text = it,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        // Image loading with proper layout
        news.urlToImage?.let { imageUrl ->
            Log.d("ImageURL", "Image URL: $imageUrl")
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Set a specific height for the image
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,  // Adjust the scaling of the image
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }

}




