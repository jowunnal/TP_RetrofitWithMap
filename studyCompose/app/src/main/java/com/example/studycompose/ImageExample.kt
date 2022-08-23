package com.example.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studycompose.item.Message

class ImageExample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            prevInitMain2()
        }
    }
}

@Composable
fun initImage(msg:Message){
    Row(modifier = Modifier.padding(3.dp)) {
       Image(painter = painterResource(id = R.drawable.img),
           contentDescription = "what is this pic?",
           modifier = Modifier.size(60.dp).clip(RectangleShape))
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.size(80.dp)) {
            Text(text = msg.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = msg.address)
        }
    }
}

@Preview
@Composable
fun prevInitMain2(){
    initImage(Message("jino","hello"))
}