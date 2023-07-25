//package com.example.tradeline.ui.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.example.tradeline.R
//import com.example.tradeline.TopBar
//import java.text.DateFormat.getDateInstance
//import java.util.*
//
//@Composable
//fun DashboardScreen(
//    navigateToProfile: () -> Unit,
//    navigateToRestock: () -> Unit,
//    navigateToAnalytics: () -> Unit,
//    canNavigateBack: Boolean = false,
//) {
//    Scaffold(
//        topBar = {
//            TopBar(
//                canNavigateBack = canNavigateBack
//            )
//        }
//    ) {
//        DashboardBody(navigateToProfile = navigateToProfile, navigateToRestock = navigateToRestock, navigateToAnalytics = navigateToAnalytics)
//    }
//}
//
//@Composable
//fun DashboardBody(navigateToProfile: () -> Unit, navigateToRestock: () -> Unit, navigateToAnalytics: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(24.dp),
//        verticalArrangement = Arrangement.Center,
//        //horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        StoreProfile(navigateToProfile = navigateToProfile)
//        CurrentDate()
//        AccountCards()
//        Text(text = "QUICK LINKS", modifier = Modifier.padding(top = 30.dp))
//
//        QuickLinksCard(navigateToRestock = navigateToRestock, navigateToAnalytics = navigateToAnalytics)
//
//        Spacer(modifier = Modifier.height(4.dp))
//
//        Text(text = "ALERTS", modifier = Modifier.padding(top = 30.dp))
//        AlertsListCard()
//
//    }
//}
//
//@Composable
//fun StoreProfile(navigateToProfile: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth(),
//        horizontalArrangement = Arrangement.End
//    )
//    {
//        Text(
//            text = "Store", //TODO
//            style = MaterialTheme.typography.labelMedium,
//            color = MaterialTheme.colorScheme.primaryContainer,
//        )
//
//        Image(
//            modifier = Modifier
//                .clickable { navigateToProfile() }
//                .size(18.dp)
//                .padding(4.dp)
//                .clip(MaterialTheme.shapes.extraSmall),
//            contentScale = ContentScale.Crop,
//            painter = painterResource(R.drawable.default_store_logo),
//            contentDescription = null
//        )
//    }
//}
//
//@Composable
//fun CurrentDate() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth(),
//        // .padding(start = 24.dp),
//        horizontalArrangement = Arrangement.Start
//    )
//    {
//        val currentDate = getDateInstance().format(Date())
//
//        Icon(
//            painter = painterResource(R.drawable.calender_icon),
//            contentDescription = "time icon",
//            modifier = Modifier.padding(top = 1.3.dp)
//        )
//
//        Spacer(modifier = Modifier.width(4.dp))
//
//        Text(
//            text = currentDate,
//            style = MaterialTheme.typography.labelSmall,
//            color = MaterialTheme.colorScheme.primaryContainer
//        )
//    }
//}
//
//@Composable
//fun AlertsListCard() {
//    Card(
//        modifier = Modifier,
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
//    ) {
//
//    }
//}
//
//@Composable
//fun AlertsItem() { //TODO
//    Row {
//        Icon(painter = painterResource(id = R.drawable.dot_icon), contentDescription = "dot")
//        Text(text = "alert ")
//    }
//}
//
//@Composable
//fun QuickLinksCard(navigateToRestock: () -> Unit, navigateToAnalytics: () -> Unit) {
//    Row {
//        RestockLinkCard(Modifier.weight(1f).clickable { navigateToRestock() })
//        Spacer(modifier = Modifier.width(4.dp))
//        AnalyticsLinkCard(Modifier.weight(2f).clickable { navigateToAnalytics() })
//    }
//}
//
//@Composable
//fun RestockLinkCard(modifier: Modifier) {
//    ElevatedCard(
//        modifier
//            .height(108.dp)
//            .clickable {}, //TODO
//        shape = MaterialTheme.shapes.small,
//        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.tertiary)
//    ) {
//        Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
//
//            Row(modifier.padding(top = 10.dp)) {
//                Icon(
//                    painter = painterResource(R.drawable.restock_icon),
//                    contentDescription = "restock",
//                    modifier.size(11.dp)
//                )
//                Text(
//                    text = "Restock",
//                    style = MaterialTheme.typography.labelSmall,
//                    color = MaterialTheme.colorScheme.onPrimary
//                )
//            }
//
//            Text(
//                text = "update your supply as you buy",
//                style = MaterialTheme.typography.labelSmall,
//                color = MaterialTheme.colorScheme.primaryContainer,
//                modifier = modifier.padding(top = 16.dp, start = 10.dp)
//            )
//
//            Text(
//                text = "Inventory",
//                style = MaterialTheme.typography.labelSmall,
//                color = MaterialTheme.colorScheme.primaryContainer,
//                modifier = modifier.padding(top = 15.dp, start = 8.dp)
//            )
//        }
//    }
//}
//
//@Composable
//fun AnalyticsLinkCard(modifier: Modifier) {
//    ElevatedCard(
//        modifier
//            .height(108.dp)
//            .clickable {}, //TODO
//        shape = MaterialTheme.shapes.small,
//        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.tertiary)
//    ) {
//        Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
//            Row(modifier.padding(top = 10.dp) .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Start
//            ) {
//                Icon(
//                    painter = painterResource(R.drawable.metrics_icon),
//                    contentDescription = "metrics",
//                    modifier.size(11.dp)
//                )
//                Text(
//                    text = "Metrics",
//                    style = MaterialTheme.typography.labelSmall,
//                    color = MaterialTheme.colorScheme.onTertiary
//                )
//            }
//
//            Icon(
//                painter = painterResource(R.drawable.metrics_demo_icon),
//                contentDescription = "metrics",
//                modifier = modifier
//                    .padding(top = 11.dp, start = 60.dp)
//                    .size(35.dp)
//            )
//
//            Text(
//                text = "Analytics",
//                style = MaterialTheme.typography.labelSmall,
//                color = MaterialTheme.colorScheme.onTertiary,
//                modifier = modifier.padding(top = 15.dp, start = 8.dp)
//            )
//        }
//    }
//}
//
//@Composable
//fun AccountCards() {
//    Row {
//        SalesCard(Modifier.weight(1f))
//
//        Spacer(modifier = Modifier.width(4.dp))
//
//        ProfitCard(Modifier.weight(1f))
//
//        Spacer(modifier = Modifier.width(4.dp))
//
//        ProductsCard(Modifier.weight(1f))
//    }
//}
//
//@Composable
//fun SalesCard(modifier: Modifier) {
//    ElevatedCard(
//        modifier.height(80.dp),
//        shape = MaterialTheme.shapes.small,
//        colors = CardDefaults.elevatedCardColors(
//            containerColor = MaterialTheme.colorScheme.primary
//        )
//    )
//    {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Text(
//                text = "Sales",
//                style = MaterialTheme.typography.labelSmall,
//                color = MaterialTheme.colorScheme.primaryContainer,
//                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
//            )
//
//            Icon(
//                //Icons.Filled.Visibility,
//                painter = painterResource(R.drawable.eye_icon),
//                contentDescription = "hide icon",
//                modifier = Modifier.padding(top = 13.dp, start = 62.dp, end = 8.dp)
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        )
//        {
//            Icon(
//                painter = painterResource(R.drawable.naira_icon),
//                contentDescription = "naira icon",
//                modifier = Modifier.padding(top = 5.2.dp)
//            )
//            Spacer(modifier = Modifier.width(2.dp))
//
//            Text(
//                text = "2000", //TODO
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.primaryContainer,
//            )
//        }
//
//    }
//}
//
//@Composable
//fun ProfitCard(modifier: Modifier) {
//    ElevatedCard(
//        modifier.height(80.dp),
//        shape = MaterialTheme.shapes.small,
//        colors = CardDefaults.elevatedCardColors(
//            containerColor = MaterialTheme.colorScheme.primary
//        )
//    )
//    {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Text(
//                text = "Profit",
//                style = MaterialTheme.typography.labelSmall,
//                color = MaterialTheme.colorScheme.primaryContainer,
//                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
//            )
//
//            Icon(
//                //Icons.Filled.Visibility,
//                painter = painterResource(R.drawable.eye_icon),
//                contentDescription = "hide icon",
//                modifier = Modifier.padding(top = 13.dp, start = 62.dp, end = 8.dp)
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        )
//        {
//            Icon(
//                painter = painterResource(R.drawable.naira_icon),
//                contentDescription = "naira icon",
//                modifier = Modifier.padding(top = 5.2.dp)
//            )
//            Spacer(modifier = Modifier.width(2.dp))
//
//            Text(
//                text = "2000", //TODO
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.primaryContainer,
//            )
//        }
//
//    }
//}
//
//@Composable
//fun ProductsCard(modifier: Modifier) {
//    ElevatedCard(
//        modifier.height(80.dp),
//        shape = MaterialTheme.shapes.small,
//        colors = CardDefaults.elevatedCardColors(
//            containerColor = MaterialTheme.colorScheme.tertiaryContainer
//        )
//    )
//    {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start
//        ) {
//            Text(
//                text = "Products",
//                style = MaterialTheme.typography.labelSmall,
//                color = MaterialTheme.colorScheme.primaryContainer,
//                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
//            )
//
//            Icon(
//                //Icons.Filled.Visibility,
//                painter = painterResource(R.drawable.eye_icon),
//                contentDescription = "hide icon",
//                modifier = Modifier.padding(top = 13.dp, start = 47.dp, end = 8.dp)
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center
//        )
//        {
//            Text(
//                text = "200", //TODO
//                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.primaryContainer,
//            )
//        }
//
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun CurrentDatePreview() {
//    //DashboardBody()
//}