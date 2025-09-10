package com.example.postvision

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.postvision.ui.theme.PostvisionTheme
import java.nio.file.WatchEvent

class HomeActivy : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(modifier = Modifier
                .fillMaxSize()

            ){
                screen()
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun screen(){
    Column(
        modifier = Modifier.padding(25.dp)
    ) {
        /* Header */
        FlowRow(
            modifier = Modifier.width(345.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(R.drawable.profile_icon),
                    contentDescription = "Profile Icon"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.notification_icon),
                    contentDescription = "Notification Icon"
                )
            }
        }

        Spacer(modifier = Modifier.height(31.dp))

        /* Text */
        Row {
            Text(
                text = "Olá,",
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Renan"
            )
        }

        Spacer(modifier = Modifier.height(23.dp))

        /* Analisy */

        FlowColumn(
            modifier = Modifier
                .height(222.dp)
                .width(345.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(25.dp),

        ){
            Text(
                text = "Faça sua análise"
            )
            Text(
                text = "Corrija sua postura e melhore seu desempenho nos exercícios físicos."
            )

            FlowRow(
                modifier = Modifier
                    .width(345.dp)
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Image(
                    painter = painterResource(R.drawable.graph_eyetracker),
                    contentDescription ="graph eye tracker",
                )
                FlowRow(
                    modifier = Modifier
                        .width(100.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(15.dp)
                        ),

                ){
                    Text(
                        text = "Iniciar"
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.cam),
                            contentDescription = "Notification Icon",
                            modifier = Modifier
                                .width(21.dp)
                                .height(18.dp)

                        )
                    }
                }
            }
        }



    }
}

@Preview
@Composable
fun previewApp(){
    PostvisionTheme {
        Surface(modifier = Modifier
            .fillMaxSize()
        ){
            screen()
        }
    }
}