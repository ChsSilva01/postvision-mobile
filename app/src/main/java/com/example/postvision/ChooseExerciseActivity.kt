package com.example.postvision

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postvision.ui.theme.PostVisionTheme
import com.example.postvision.ui.theme.Raleway
import kotlinx.coroutines.selects.select

class ChooseExerciseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PostVisionTheme {

            }
        }
    }
}

@Composable
fun WraapperChooseExercice(){
    PostVisionTheme() {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            color = MaterialTheme.colorScheme.onBackground)
        {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 22.dp)
                    .width(345.dp)
                    .height(814.dp),

                ) {
                Text(
                    "Escolha do exercício",
                    fontFamily = Raleway,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(modifier = Modifier
                    .padding(top = 39.dp)
                    ) {
                    Card(modifier = Modifier
                        .width(345.dp)
                        .height(171.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()) {
                        Row(){
                            Column() {
                                Text(
                                    "Agachamento Livre",
                                    fontFamily = Raleway,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Descrição",
                                    fontFamily = Raleway,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Exercício fundamental para fortalecer pernas e glúteos. Trabalha músculos como quadríceps, posteriores da coxa e glúteos. Deve ser feito com postura correta para evitar lesões e garantir eficiência.",
                                    fontFamily = Raleway,
                                    fontSize = 12.sp,
                                    letterSpacing = 0.sp
                                )
                            }
                            RadioButton(
                                onClick = {},
                                modifier = Modifier
                                .width(10.dp)
                                .height(10.dp),
                                selected = true)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MobilePreviewExercice(){
    WraapperChooseExercice()
}