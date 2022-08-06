package com.example.jetpaccomposeconcepts

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpaccomposeconcepts.ui.theme.JetpacComposeConceptsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpacComposeConceptsTheme {
                Conversation(SampleData.conversationSample)
            }
        }
    }

    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painterResource(R.drawable.trigger_personal_emblem),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by remember { mutableStateOf(false) }
            // surfaceColor will be updated gradually from one color to the other
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )

            // We toggle the isExpanded variable when we click on this Column
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {

                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }

    @Preview(
        name = "Light Mode",
        showBackground = true
    )
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = false,
        name = "Dark Mode"
    )

    @Composable
    fun PreviewMessageCard() {
        JetpacComposeConceptsTheme {
            MessageCard(
                msg = Message("Trigger", "have a look at compose")
            )
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        JetpacComposeConceptsTheme {
            Conversation(SampleData.conversationSample)
        }
    }

    object SampleData {
        // Sample conversation data
        val conversationSample = listOf(
            Message(
                "Trigger",
                "Test...Test...Test..."
            ),
            Message(
                "Trigger",
                "List of Android versions:\n" +
                        "Android KitKat (API 19)\n" +
                        "Android Lollipop (API 21)\n" +
                        "Android Marshmallow (API 23)\n" +
                        "Android Nougat (API 24)\n" +
                        "Android Oreo (API 26)\n" +
                        "Android Pie (API 28)\n" +
                        "Android 10 (API 29)\n" +
                        "Android 11 (API 30)\n" +
                        "Android 12 (API 31)\n"
            ),
            Message(
                "Trigger",
                "I think Kotlin is my favorite programming language.\n" +
                        "It's so much fun!"
            ),
            Message(
                "Trigger",
                "Searching for alternatives to XML layouts..."
            ),
            Message(
                "Trigger",
                "Hey, take a look at Jetpack Compose, it's great!\n" +
                        "It's the Android's modern toolkit for building native UI." +
                        "It simplifies and accelerates UI development on Android." +
                        "Less code, powerful tools, and intuitive Kotlin APIs :)"
            ),
            Message(
                "Trigger",
                "It's available from API 21+ :)"
            ),
            Message(
                "Trigger",
                "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
            ),
            Message(
                "Trigger",
                "Android Studio next version's name is Arctic Fox"
            ),
            Message(
                "Trigger",
                "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
            ),
            Message(
                "Trigger",
                "I didn't know you can now run the emulator directly from Android Studio"
            ),
            Message(
                "Trigger",
                "Compose Previews are great to check quickly how a composable layout looks like"
            ),
            Message(
                "Trigger",
                "Previews are also interactive after enabling the experimental setting"
            ),
            Message(
                "Trigger",
                "Have you tried writing build.gradle with KTS?"
            ),
        )
    }
}