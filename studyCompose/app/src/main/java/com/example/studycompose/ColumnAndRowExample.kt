package com.example.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.studycompose.item.Message

class ColumnAndRow : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            initMain(message = Message("진호","부산"))
        }
    }
}

@Composable
fun initMain(message:Message){
    Column{
        Text(text = message.name)
        Text(text = message.address)
    }
}

@Preview
@Composable
fun prevInitMain(){
    initMain(message = Message("jinho","Busan"))
}