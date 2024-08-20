package com.techrealms.cashbook.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techrealms.cashbook.R
import com.techrealms.cashbook.model.Category
import com.techrealms.cashbook.ui.theme.CashBookTheme

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(modifier.clickable { onClick() }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(

                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopCenter
            )
            Text(
                text = "category.title",
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .padding(horizontal = 8.dp)
//                    .padding(bottom = 8.dp, top = 4.dp)
//                    .fillMaxWidth(),
//            ) {
////                PlatformLogos(platformList = game.platformList)
////                GameScore(score = game.rating.toInt())toInt
//            }
        }
    }
}

@Composable
@ExperimentalMaterial3Api
fun CategoryItem(category: Category,
                 onCategoryClick: (Category)-> Unit)
{
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier
            //.padding(8.dp, 0.dp, 8.dp, 8.dp)
            .clickable {
                onCategoryClick(category)
            }
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
           CategoryCard(category = category, onClick = { /*TODO*/ })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CategoryItemPreview(){
    CashBookTheme {
        CategoryItem(
            category = Category(),
            onCategoryClick = { })
    }
}