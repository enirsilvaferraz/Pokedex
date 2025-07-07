package com.example.pokedex

//import androidx.compose.material.icons.filled.Scale
//import androidx.compose.material.icons.filled.Height
//import androidx.compose.material.icons.filled.Category
//import androidx.compose.material.icons.filled.Shield
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.pokedex.entity.TypeVO
import com.example.pokedex.helpers.AppScaffold
import com.example.pokedex.helpers.getColorForType
import org.jetbrains.compose.ui.tooling.preview.Preview

// --- Data Classes ---

data class PokemonStatVo(
    val name: String,
    val value: Int,
    val maxValue: Int = 100, // Assuming a max value for progress indication
)

data class PokemonDetailVo(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val types: List<TypeVO>,
    val description: String,
    val weightInKg: Float,
    val heightInMeters: Float,
    val category: String,
    val abilities: List<String>,
    val genderRatioMale: Float, // e.g., 0.875 for 87.5% male
    val baseStats: List<PokemonStatVo>,
    val primaryColor: Color,
    val isFavorite: Boolean,
) {
    fun getFormattedId(): String = formatId(id)
}

// --- Constants ---
private object PokemonDetailDimens {
    val screenPadding: Dp = 16.dp
    val sectionSpacing: Dp = 24.dp
    val pokemonImageSize: Dp = 200.dp
}

// --- Main Composable ---
@Composable
internal fun PokemonDetailRoute(pokemonId: Long /* or pass full VO if fetched earlier */) {
    // In a real app, you would fetch PokemonDetailVo using pokemonId via a ViewModel
    val samplePokemon: PokemonDetailVo = createSamplePokemonDetailVo().copy(id = pokemonId) // Replace with actual data fetching

    PokemonDetailScreen(
        pokemonDetail = samplePokemon,
        onNavigateBack = { /* TODO: Implement navigation back */ },
    )
}

@Composable
private fun PokemonDetailScreen(
    pokemonDetail: PokemonDetailVo,
    onNavigateBack: () -> Unit,
) {

    AppScaffold {

        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

            TopSection(
                pokemonImageUrl = pokemonDetail.imageUrl,
                pokemonName = pokemonDetail.name,
                backgroundColor = pokemonDetail.primaryColor,
                onNavigateBack = onNavigateBack
            )

            Spacer(modifier = Modifier.height(PokemonDetailDimens.sectionSpacing))

            NameAndIdSection(
                name = pokemonDetail.name,
                formattedId = pokemonDetail.getFormattedId()
            )
            Spacer(modifier = Modifier.height(PokemonDetailDimens.screenPadding))
            TypeTagsSection(types = pokemonDetail.types)
            Spacer(modifier = Modifier.height(PokemonDetailDimens.sectionSpacing))
            DescriptionSection(description = pokemonDetail.description)
            Spacer(modifier = Modifier.height(PokemonDetailDimens.sectionSpacing))
            StatsInfoGrid(
                weight = pokemonDetail.weightInKg,
                height = pokemonDetail.heightInMeters,
                category = pokemonDetail.category,
                abilities = pokemonDetail.abilities.joinToString(", ")
            )
            Spacer(modifier = Modifier.height(PokemonDetailDimens.sectionSpacing))
            if (pokemonDetail.genderRatioMale >= 0) { // Assuming -1 or similar for genderless
                GenderRatioSection(
                    malePercentage = pokemonDetail.genderRatioMale * 100,
                    femalePercentage = (1.0f - pokemonDetail.genderRatioMale) * 100
                )
                Spacer(modifier = Modifier.height(PokemonDetailDimens.sectionSpacing))
            }
            BaseStatsSection(stats = pokemonDetail.baseStats, primaryColor = pokemonDetail.primaryColor)
            Spacer(modifier = Modifier.height(PokemonDetailDimens.screenPadding)) // Bottom padding
        }
    }
}

// --- UI Sections ---
@Composable
private fun TopSection(
    pokemonImageUrl: String,
    pokemonName: String,
    backgroundColor: Color,
    onNavigateBack: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        CurvedBackground(color = backgroundColor)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PokemonDetailDimens.screenPadding)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate Back",
                    tint = Color.White
                )
            }
        }
        AsyncImage(
            model = pokemonImageUrl,
            contentDescription = pokemonName,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(PokemonDetailDimens.pokemonImageSize)
                .align(Alignment.BottomCenter)
                .offset(y = PokemonDetailDimens.pokemonImageSize / 4) // Adjust to make it sit on the curve
        )
    }
}

@Composable
private fun CurvedBackground(color: Color) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path: Path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height * 0.75f)
            quadraticBezierTo(
                size.width / 2, size.height * 1.1f, // Control point for curve
                0f, size.height * 0.75f // End point
            )
            close()
        }
        drawPath(path = path, color = color)
    }
}

@Composable
private fun NameAndIdSection(name: String, formattedId: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = PokemonDetailDimens.pokemonImageSize / 4) // Space for overlapping image
            .padding(horizontal = PokemonDetailDimens.screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = formattedId,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun TypeTagsSection(types: List<TypeVO>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PokemonDetailDimens.screenPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        types.forEachIndexed { index: Int, type: TypeVO ->
            TypeTag(typeName = type.name, typeColor = type.getColorForType())
            if (index < types.size - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
private fun TypeTag(typeName: String, typeColor: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(typeColor.copy(alpha = 0.3f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = typeName,
            color = typeColor, // Or a contrasting color if typeColor is too light
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DescriptionSection(description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PokemonDetailDimens.screenPadding)
    ) {
        SectionTitle(title = "Description")
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
private fun StatsInfoGrid(
    weight: Float,
    height: Float,
    category: String,
    abilities: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PokemonDetailDimens.screenPadding)
    ) {
        SectionTitle(title = "Pokédex Data")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
//            StatInfoItem(icon = Icons.Filled.Scale, label = "Weight", value = "${weight}kg")
            StatInfoItem(icon = Icons.Filled.Info, label = "Weight", value = "${weight}kg")
//            StatInfoItem(icon = Icons.Filled.Height, label = "Height", value = "${height}m")
            StatInfoItem(icon = Icons.Filled.Info, label = "Height", value = "${height}m")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
//            StatInfoItem(icon = Icons.Filled.Category, label = "Category", value = category, fullWidth = true)
            StatInfoItem(icon = Icons.Filled.Info, label = "Category", value = category, fullWidth = true)

//            StatInfoItem(icon = Icons.Filled.Shield, label = "Abilities", value = abilities, fullWidth = true)
            StatInfoItem(icon = Icons.Filled.Info, label = "Abilities", value = abilities, fullWidth = true)
        }
    }
}

@Composable
private fun RowScope.StatInfoItem(
    icon: ImageVector,
    label: String,
    value: String,
    fullWidth: Boolean = false,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = if (fullWidth) Modifier.weight(1f) else Modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Composable
private fun GenderRatioSection(malePercentage: Float, femalePercentage: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PokemonDetailDimens.screenPadding)
    ) {
        SectionTitle(title = "Gender Ratio")
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (malePercentage > 0) {
//                Text(text = "♂ ${"%.1f".format(malePercentage)}%", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF6890F0))
                Text(text = "♂ 30%", style = MaterialTheme.typography.bodyMedium, color = Color(0xFF6890F0))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(malePercentage / 100f)
                        .background(Color(0xFF6890F0)) // Male color
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(femalePercentage / 100f)
                        .background(Color(0xFFF85888)) // Female color
                        .align(Alignment.CenterEnd)
                )
            }
            if (femalePercentage > 0) {
//                 Text(text = "♀ ${"%.1f".format(femalePercentage)}%", style = MaterialTheme.typography.bodyMedium, color = Color(0xFFF85888))
                Text(text = "♀ 70%", style = MaterialTheme.typography.bodyMedium, color = Color(0xFFF85888))
            }
        }
        if (malePercentage == 0f && femalePercentage == 0f) {
            Text("Genderless", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}


@Composable
private fun BaseStatsSection(stats: List<PokemonStatVo>, primaryColor: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PokemonDetailDimens.screenPadding)
    ) {
        SectionTitle(title = "Base Stats")
        stats.forEach { stat: PokemonStatVo ->
            StatRow(
                statName = stat.name,
                statValue = stat.value,
                maxValue = stat.maxValue,
                statColor = primaryColor
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun StatRow(
    statName: String,
    statValue: Int,
    maxValue: Int,
    statColor: Color,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = statName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(0.3f) // Adjust weight as needed
        )
        Text(
            text = statValue.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(40.dp) // Fixed width for alignment
        )
        LinearProgressIndicator(
            progress = { statValue.toFloat() / maxValue.toFloat() },
            modifier = Modifier.weight(0.7f).height(8.dp),
            color = statColor,
            trackColor = statColor.copy(alpha = 0.3f)
        )
    }
}


@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

// --- Preview ---
@Preview()
@Composable
private fun PokemonDetailScreenPreview() {
    val samplePokemon: PokemonDetailVo = createSamplePokemonDetailVo()
    var isFavoriteState: Boolean by remember { mutableStateOf(samplePokemon.isFavorite) }

    MaterialTheme { // Added MaterialTheme for proper preview rendering
        PokemonDetailScreen(
            pokemonDetail = samplePokemon.copy(isFavorite = isFavoriteState),
            onNavigateBack = {}
        )
    }
}

// --- Sample Data Provider ---
private fun createSamplePokemonDetailVo(): PokemonDetailVo {
    return PokemonDetailVo(
        id = 25,
        name = "Pikachu",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png", // Placeholder image
        types = listOf(
            TypeVO(1, "Electric")
        ),
        description = "Pikachu that can generate powerful electricity have cheek sacs that are extra soft and super stretchy.",
        weightInKg = 6.0f,
        heightInMeters = 0.4f,
        category = "Mouse Pokémon",
        abilities = listOf("Static", "Lightning Rod"),
        genderRatioMale = 0.5f, // 50% Male, 50% Female
        baseStats = listOf(
            PokemonStatVo("HP", 35, 255),
            PokemonStatVo("Attack", 55, 255),
            PokemonStatVo("Defense", 40, 255),
            PokemonStatVo("Sp. Atk", 50, 255),
            PokemonStatVo("Sp. Def", 50, 255),
            PokemonStatVo("Speed", 90, 255)
        ),
        primaryColor = Color(0xFFF8D030), // Electric type color
        isFavorite = false
    )
}
