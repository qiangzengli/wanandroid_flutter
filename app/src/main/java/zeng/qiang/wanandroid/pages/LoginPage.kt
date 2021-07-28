package zeng.qiang.wanandroid.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import zeng.qiang.wanandroid.R

@ExperimentalUnitApi
@ExperimentalAnimationApi
@Composable
            fun LoginPage() {
                Box {
                    Image(
                        painter = painterResource(
                            id = R.drawable.login_background
                        ),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillHeight
                    )
                    Column(
                        Modifier
                            .fillMaxWidth(),
                    ) {
                        Divider(
                            thickness = 42.dp,
                            color = Color.Transparent,
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_user_1024),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(74.dp)
                                    .height(74.dp)
                                    .clip(
                                        shape = RoundedCornerShape(15.dp),
                                    )
                            )
                        }

                        Divider(
                            thickness = 60.dp,
                            color = Color.Transparent,
                        )
                        DeletableTextField(
                            hint = "请输入手机号",
                            keyboardType = KeyboardType.Phone,
                            maxLength = 11,
                            deletable = true
                        )
                        Divider(
                            thickness = 10.dp,
                            color = Color.Transparent,
                        )
                        BoxWithConstraints() {
                            DeletableTextField(
                                hint = "请输入验证码",
                                keyboardType = KeyboardType.Number,
                                maxLength = 6
                            )

                        }

                        Divider(
                            thickness = 30.dp,
                            color = Color.Transparent,
                        )
                        Button(
                            onClick = {

                            },
                            modifier = Modifier
                                .padding(horizontal = 50.dp)
                                .clip(shape = CircleShape)
                                .height(50.dp)
                                .fillMaxWidth(),
                        ) {
                            Text(
                                text = "登录",
                                style = TextStyle(
                                    fontSize = TextUnit(
                                        17F,
                                        TextUnitType.Sp,
                                    )
                                ),
                            )

                        }

                    }
                }


            }
        

    

    @ExperimentalAnimationApi
    @Preview
    @Composable
    fun DeletableTextField(
        hint: String = "",
        keyboardType: KeyboardType = KeyboardType.Text,
        maxLength: Int = 11,
        deletable: Boolean = false,
    ) {
        var value by remember {
            mutableStateOf("")
        }
        TextField(
            value = value,
            onValueChange = {
                value = if (it.length >= maxLength)
                    it.substring(0, maxLength)
                else
                    it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .wrapContentHeight(),
            trailingIcon = {
                AnimatedVisibility(
                    visible = deletable && value.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Cancel,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            value = ""
                        }
                    )
                }

            },
            singleLine = true,
//            shape = RoundedCornerShape(size = 0.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.UnfocusedIndicatorLineOpacity)
            ),
            placeholder = {
                Text(hint)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
            ),
        )

        @Composable
        fun SendVerifyCode() {
            Text(text = "发送验证码")
        }
        
        
        @Composable
        fun LoadingIndicator(
        ) {
            AnimatedVisibility(visible = true) {
                CircularProgressIndicator()

            }
        }
    }