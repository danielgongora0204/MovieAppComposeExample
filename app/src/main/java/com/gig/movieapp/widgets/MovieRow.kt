package com.gig.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.gig.movieapp.models.Movie
import com.gig.movieapp.ui.theme.BackgroundLight
import com.gig.movieapp.utilities.extensions.default

@Preview
@ExperimentalMaterial3Api
@Composable
fun MovieRow(movie: Movie? = null, onItemClick: (String) -> Unit = {}) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = BackgroundLight),
        onClick = { movie?.let { onItemClick.invoke(it.id) } }
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.End) {
                Surface(
                    modifier = Modifier
                        .size(width = 120.dp, height = 100.dp),
                    color = BackgroundLight,
                    shape = RectangleShape,
                    tonalElevation = 4.dp,
                    shadowElevation = 4.dp
                ) {
                    // This es les performant but more flexible. as it allows for it to have
                    // inner composables and a defined state for its painter, since it allows more functionality
                    /*SubcomposeAsyncImage(
                        model = (movie?.images?.get(0)).default(""),
                        loading = {
                            CircularProgressIndicator()
                        },
                        contentDescription = "Image Poster"
                    )*/
                    // This is better for performance however its painter lacks a state
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data((movie?.images?.get(0)).default(""))
                            .transformations(RoundedCornersTransformation(18.0f))
                            .crossfade(true)
                            .build(),
                        placeholder = rememberVectorPainter(
                            defaultWidth = Icons.Default.Image.defaultWidth,
                            defaultHeight = Icons.Default.Image.defaultHeight,
                            viewportWidth = Icons.Default.Image.viewportWidth,
                            viewportHeight = Icons.Default.Image.viewportHeight,
                            name = Icons.Default.Image.name,
                            tintColor = Color.LightGray,
                            autoMirror = Icons.Default.Image.autoMirror,
                            content = { _, _ -> RenderVectorGroup(group = Icons.Default.Image.root) }
                        ),
                        contentDescription = "Image Poster",
                        contentScale = ContentScale.Crop,
                    )
                }
                Divider(modifier = Modifier
                    .height(4.dp)
                    .width(0.dp))
                Row(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.width(60.dp),
                        text = if (expanded) movie?.genre.default("") else movie?.genre.default("").substring(0, movie?.genre.default("").length.coerceAtMost(10)),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Left,
                        color = Color.DarkGray
                    )
                    Text(
                        text = if (expanded) String() else "...",
                        style = MaterialTheme.typography.bodySmall, color = Color.DarkGray, maxLines = 1)
                    Divider(modifier = Modifier
                        .height(0.dp)
                        .width(5.dp))
                    Icon(modifier = Modifier.height(15.dp), imageVector = Icons.Default.Star, contentDescription = "Rating", tint = MaterialTheme.colorScheme.primary)
                    Divider(modifier = Modifier
                        .height(0.dp)
                        .width(3.dp))
                    Text(text = "${movie?.rating.default(0f)}", style = MaterialTheme.typography.bodySmall, color = Color.DarkGray)
                    //RatingBar(modifier = Modifier.height(15.dp), rating = movie?.rating.default(0f), color = MaterialTheme.colorScheme.primary)
                }
            }
            Column(modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 0.dp)) {
                Text(modifier = Modifier.padding(vertical = 5.dp, horizontal = 0.dp), text = movie?.title.default("Movie"), style = MaterialTheme.typography.titleLarge)
                Text(text = "Director: ${movie?.director.default("")}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Released: ${movie?.year.default("")}", style = MaterialTheme.typography.bodyMedium)

                ExpandableMovieInfo(modifier = Modifier.wrapContentSize(), isExpanded = expanded, movieActor = movie?.actors.default(""))

                IconButton(
                    modifier = Modifier
                        .padding(0.dp)
                        .size(30.dp),
                    colors = iconButtonColors(
                        contentColor = Color.DarkGray
                    ),
                    onClick = {
                        expanded = !expanded
                    }
                ) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Down Arrow"
                    )
                }
            }
        }

        ExpandableMoviePlot(Modifier, isExpanded = expanded, moviePlot = movie?.plot.default(""))
    }
}

@Composable
fun ExpandableMovieInfo(modifier: Modifier = Modifier, isExpanded: Boolean = false, movieActor: String = "") {
    AnimatedVisibility(visible = isExpanded) {
        Column(modifier = modifier.padding(0.dp, 0.dp, 4.dp , 4.dp)) {
            Text(text = "Actors: $movieActor", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ExpandableMoviePlot(modifier: Modifier = Modifier, isExpanded: Boolean = false, moviePlot: String = ""){
    AnimatedVisibility(visible = isExpanded) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(12.dp, 0.dp, 12.dp, 12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Text( buildAnnotatedString {
                this.withStyle(
                    style = SpanStyle(
                        fontSize = 12.sp,
                        fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                        letterSpacing = MaterialTheme.typography.bodySmall.letterSpacing,
                        fontSynthesis = MaterialTheme.typography.bodySmall.fontSynthesis,
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        baselineShift = MaterialTheme.typography.bodySmall.baselineShift,
                        textGeometricTransform = MaterialTheme.typography.bodySmall.textGeometricTransform,
                        textDecoration = MaterialTheme.typography.bodySmall.textDecoration,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                ){
                    append("Plot: ")
                }
                this.withStyle(
                    style = SpanStyle(
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                        letterSpacing = MaterialTheme.typography.bodySmall.letterSpacing,
                        fontSynthesis = MaterialTheme.typography.bodySmall.fontSynthesis,
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                        baselineShift = MaterialTheme.typography.bodySmall.baselineShift,
                        textGeometricTransform = MaterialTheme.typography.bodySmall.textGeometricTransform,
                        textDecoration = MaterialTheme.typography.bodySmall.textDecoration,
                        color = Color.DarkGray,
                    )
                ){
                    append(moviePlot)
                }
            })
        }
    }
}