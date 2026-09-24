package com.example.footballlinksmvp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class Match(
    val league: String,
    val home: String,
    val away: String,
    val time: String,
    val links: List<Pair<String, String>>
)

private val sampleMatches = listOf(
    Match(
        "Premier League", "Arsenal", "Chelsea", "20:00",
        listOf("Official broadcaster" to "https://example.com/")
    ),
    Match(
        "La Liga", "Barcelona", "Sevilla", "22:30",
        listOf("Official broadcaster" to "https://example.com/")
    ),
    Match(
        "Champions League", "Inter", "Real Madrid", "02:00",
        listOf("Official broadcaster" to "https://example.com/")
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FootballLinksApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FootballLinksApp() {
    var selectedLeague by remember { mutableStateOf("Tất cả") }
    val leagues = listOf("Tất cả", "Premier League", "La Liga", "Champions League")
    val matches = sampleMatches.filter {
        selectedLeague == "Tất cả" || it.league == selectedLeague
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.SportsSoccer, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Football Links", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                "Lịch thi đấu & link xem hợp pháp",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                leagues.take(3).forEach { league ->
                    FilterChip(
                        selected = selectedLeague == league,
                        onClick = { selectedLeague = league },
                        label = { Text(league) }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(matches) { match ->
                    MatchCard(match)
                }
            }
        }
    }
}

@Composable
fun MatchCard(match: Match) {
    val context = LocalContext.current
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(match.league, style = MaterialTheme.typography.labelMedium)
            Spacer(Modifier.height(6.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(match.home, fontWeight = FontWeight.SemiBold)
                    Text("vs")
                    Text(match.away, fontWeight = FontWeight.SemiBold)
                }
                Text(match.time, style = MaterialTheme.typography.titleLarge)
            }

            Spacer(Modifier.height(12.dp))
            match.links.forEach { (label, url) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            context.startActivity(
                                Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            )
                        }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Link, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text(label, color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
