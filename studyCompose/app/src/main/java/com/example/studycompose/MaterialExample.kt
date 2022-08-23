package com.example.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.studycompose.item.Message
import com.example.studycompose.ui.theme.StudyComposeTheme

class MaterialExample:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyComposeTheme() {
                Surface {
                    initImage(msg = Message("jino","busan"))
                }
            }
        }
    }
}

@Preview
@Composable
fun materialInit(){
    StudyComposeTheme{
        Surface {
            initImage(msg = Message("진호","부산"))
        }
    }
}