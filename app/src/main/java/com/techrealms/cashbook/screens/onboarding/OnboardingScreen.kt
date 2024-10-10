package com.techrealms.cashbook.screens.onboarding

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.techrealms.cashbook.MainActivity
import com.techrealms.cashbook.R
import com.techrealms.cashbook.common.composable.BasicButton
import com.techrealms.cashbook.common.composable.PageIndicator
import com.techrealms.cashbook.ui.theme.CashBookTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(openAndPopup: (String,String) -> Unit,
                    viewModel: OnboardingViewModel = hiltViewModel()
)
{
    OnboardingScreenContent(onAppStart = { viewModel.onAppStart(openAndPopup) },
        shouldShowError = viewModel.showError.value)
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreenContent(modifier: Modifier = Modifier,
                             onAppStart: () -> Unit,
                             shouldShowError:Boolean)
{
    val images = listOf(
        R.drawable.onboarding_one,
        R.drawable.onboarding_two,
        R.drawable.onboarding_three
    )

    val titles = listOf(
        "Note Down Expenses",
        "Simple Money Management",
        "Easy to Track and Analyze"
    )

    val descriptions = listOf(
        "Daily note your expenses to help manage money",
        "Get your notifications or alert when you do the over expenses",
        "Tracking your expense help make sure you don't overspend"
    )
    val pagerState = rememberPagerState(
        pageCount = 3
    )


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))

        Row(modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.height(64.dp).width(48.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Cash Book",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ))
            )
        }

        Spacer(Modifier.height(32.dp))

        HorizontalPager(
            state = pagerState,
            Modifier.wrapContentSize()
        ) { currentPage ->
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(start = 26.dp, end = 26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.height(32.dp))
                //val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animations[currentPage]))
                Image(
                    painter = painterResource(id = images[currentPage]),
                    contentDescription = "Image1",
                    modifier = Modifier.padding(start = 50.dp, end = 50.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = titles[currentPage],
                    Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,

                )
                Text(
                    text = descriptions[currentPage],
                    Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp

                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        PageIndicator(
            pageCount = 3,
            currentPage = pagerState.currentPage,
            modifier = Modifier //.padding(60.dp)
        )
        val scope = rememberCoroutineScope()
        Row(modifier = Modifier.padding(top = 16.dp, bottom = 48.dp).fillMaxHeight(),
            verticalAlignment = Alignment.Bottom) {
            BasicButton(
                text = R.string.btn_lets_go, modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    )
                    .height(56.dp)
            ) {
                scope.launch {
                    if (pagerState.currentPage != 2) {
                        val nextPage = pagerState.currentPage + 1
                        pagerState.scrollToPage(nextPage)
                    } else {
                        onAppStart()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ButtonsSection(pagerState: PagerState) //, navController: NavHostController, context: MainActivity) {
{
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)){
        //BasicTextInputField()
        if (pagerState.currentPage != 2){
            BasicButton(text = R.string.app_name, modifier = Modifier.align(Alignment.BottomEnd)) { }

        }else{
            OutlinedButton(onClick = {

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                ,
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    //color = Color.Black
                )
            }
        }
    }
}


private fun onBoardingIsFinished(context: MainActivity) {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()

}


@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview(){
    CashBookTheme {
        OnboardingScreenContent(onAppStart = { /*TODO*/ }, shouldShowError = true)
    }
}