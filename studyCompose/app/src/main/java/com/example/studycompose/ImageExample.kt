package com.example.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
    var expanded by remember{ mutableStateOf(true)} // UI를 객체화하여 메모리에서 기억하고 추적함 = 재구성
    val color by animateColorAsState(if(expanded) MaterialTheme.colors.secondary else MaterialTheme.colors.secondaryVariant)


    Row(modifier = Modifier
        .padding(3.dp)
        .clickable { expanded = !expanded }) {
       Image(painter = painterResource(id = R.drawable.img),
           contentDescription = "what is this pic?",
           modifier = Modifier
               .size(60.dp)
               .clip(RectangleShape)
               .border(
                   1.5.dp, MaterialTheme.colors.secondary,
                   RectangleShape
               ))
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.size(80.dp)) {
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp, color = color,
            modifier = Modifier.animateContentSize().padding(1.dp)) {
                Text(text = if(expanded) msg.name else msg.address,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = if(expanded) Int.MAX_VALUE else 1)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = msg.address, color = MaterialTheme.colors.surface)
        }// typography는 글시체, color는색상,
    }
}

@Preview
@Composable
fun prevInitMain2(){
    initImage(Message("jino","hello"))
}