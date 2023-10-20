package com.candroid.loginpagesample

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HowToReg
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.outlined.HowToReg
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginPage(context: Context, navController: NavController) {
    val selectedTab = remember { mutableIntStateOf(0) }
    val tabTitles = listOf(
        TabItem(
            title = "Sign In",
            unselectedIcon = Icons.Outlined.Login,
            selectedIcon = Icons.Filled.Login
        ),
        TabItem(
            title = "Sign Up",
            unselectedIcon = Icons.Outlined.HowToReg,
            selectedIcon = Icons.Filled.HowToReg
        )
    )
    val pagerState = rememberPagerState { tabTitles.size }

    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            painter = painterResource(id = R.drawable.pazarama_logo),
            contentDescription = "Pazarama Logo"
        )

        LaunchedEffect(selectedTab.value) {
            pagerState.animateScrollToPage(selectedTab.value)
        }
        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            //selectedTab.value = pagerState.currentPage
            if (!pagerState.isScrollInProgress)
                selectedTab.value = pagerState.currentPage
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            TabRow(selectedTabIndex = selectedTab.value) {
                tabTitles.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = index == selectedTab.value,
                        onClick = {
                            selectedTab.value = index
                        },
                        text = { Text(text = tabItem.title) },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedTab.value) {
                                    tabItem.selectedIcon
                                } else {
                                    tabItem.unselectedIcon
                                },
                                contentDescription = tabItem.title
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                Box(modifier = Modifier.fillMaxSize()/*, contentAlignment = Alignment.Center*/) {
                    when (index) {
                        0 -> SignInPage(context, navController)
                        1 -> SignUpPage(context, navController)
                    }
                }
            }
        }

    }
}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)