package com.example.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.studycompose.item.Message

class listExample : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            prevListInit()
        }
    }
}

@Composable
fun listInit(msg:List<Message>){
    LazyRow{
        items(msg) {
            msg->
            initImage(msg = msg)
        }
    }
}

@Preview
@Composable
fun prevListInit(){
    listInit(msg = listOf(Message("jino","busan"),Message("suno","kimhae")))
}