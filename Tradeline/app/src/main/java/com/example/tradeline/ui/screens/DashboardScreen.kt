package com.example.tradeline.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradeline.R
import com.example.tradeline.TopBar
import java.text.DateFormat.getDateInstance
import java.util.*

@Composable
fun DashboardScreen(
    storeName: String, // store name
    navigateToProfile: () -> Unit,
    navigateToRestock: () -> Unit,
    navigateToAnalytics: () -> Unit,

) {
    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = false
            )
        }
    ) {
        DashboardBody(navigateToProfile = navigateToProfile, navigateToRestock = navigateToRestock, navigateToAnalytics = navigateToAnalytics)
    }
}

@Composable
fun DashboardBody(navigateToProfile: () -> Unit, navigateToRestock: () -> Unit, navigateToAnalytics: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),

    ) {
        StoreProfile(navigateToProfile = navigateToProfile)
        CurrentDate()
        AccountCards()
        Text(text = "QUICK LINKS", modifier = Modifier.padding(top = 30.dp))

        QuickLinksCard(navigateToRestock = navigateToRestock, navigateToAnalytics = navigateToAnalytics)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "ALERTS", modifier = Modifier.padding(top = 30.dp))
        AlertsListCard()

    }
}

@Composable
fun StoreProfile(navigateToProfile: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToProfile() },
        horizontalArrangement = Arrangement.End
    )
    {
        Text(
            text = "Store", //TODO
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF000000),
        )

        Image(
            modifier = Modifier
                .size(18.dp)
                .padding(4.dp)
                .clip(MaterialTheme.shapes.extraSmall),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.default_store_logo),
            contentDescription = null
        )
    }
}

@Composable
fun CurrentDate() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    )
    {
        val currentDate = getDateInstance().format(Date())

        Icon(
            painter = painterResource(R.drawable.calender_icon),
            contentDescription = "time icon",
            modifier = Modifier.padding(top = 1.3.dp)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = currentDate,
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF000000),

        )
    }
}


@Composable
fun AccountCards() {
    Row {
        SalesCard(Modifier.weight(1f))

        Spacer(modifier = Modifier.width(4.dp))

        ProfitCard(Modifier.weight(1f))

        Spacer(modifier = Modifier.width(4.dp))

        ProductsCard(Modifier.weight(1f))
    }
}

@Composable
fun SalesCard(modifier: Modifier) {
    ElevatedCard(
        modifier
            .height(80.dp)
            .offset(0.dp, 4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFE7F7FB)
        )
    )
    {
      /*  var amountVisibility by remember{mutableStateOf(false) }

        val icon = if (amountVisibility)
            painterResource(id = R.drawable.eye_icon)
        else
            painterResource(id = R.drawable.invisible_eye_icon)*/


        Row(modifier.padding(top = 8.dp)) {

            Text(
                text = "Sales",
                style = MaterialTheme.typography.labelSmall,
                modifier=(Modifier.offset(6.dp)),
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier
                .width(60.dp)
                .height(30.dp))
            IconButton(onClick = {
                /*amountVisibility = !amountVisibility*/
            }) {
                /*AnimatedVisibility(amountVisibility) {}*/
                    Icon(
                        painter = painterResource(id = R.drawable.eye_icon),
                        /*painter = icon,*/
                        contentDescription = "hide icon",
                        modifier
                            .size(11.dp)
                            .offset((-3).dp, (-3.5).dp)
                    )


            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, (-22).dp),
            horizontalArrangement = Arrangement.Center
        )
        {
            Icon(
                painter = painterResource(R.drawable.naira_icon),
                contentDescription = "naira icon",
                modifier = Modifier.padding(top = 5.2.dp)

            )
            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "2000", /*if (amountVisibility) "2000" else ".....",*/ //TODO
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF000000),
            )
        }

    }
}

@Composable
fun ProfitCard(modifier: Modifier) {
    ElevatedCard(
        modifier
            .height(80.dp)
            .offset(0.dp, 4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFE1F2CD)
        )
    )
    {
        Row(modifier.padding(top = 8.dp)) {

            Text(
                text = "Profit",
                style = MaterialTheme.typography.labelSmall,
                modifier=(Modifier.offset(6.dp)),
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier
                .width(60.dp)
                .height(30.dp))
            IconButton(onClick = {
                /*amountVisibility = !amountVisibility*/
            }) {
                /*AnimatedVisibility(amountVisibility) {}*/
                Icon(
                    painter = painterResource(id = R.drawable.eye_icon),
                    /*painter = icon,*/
                    contentDescription = "hide icon",
                    modifier
                        .size(11.dp)
                        .offset((-3).dp, (-3.5).dp)
                )


            }
        }


        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, (-22).dp),
            horizontalArrangement = Arrangement.Center
        )
        {
            Icon(
                painter = painterResource(R.drawable.naira_icon),
                contentDescription = "naira icon",
                modifier = Modifier.padding(top = 5.2.dp)

            )
            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "2000", /*if (amountVisibility) "2000" else ".....",*/ //TODO
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF000000),
            )
        }

    }
}


@Composable
fun ProductsCard(modifier: Modifier) {
    ElevatedCard(
        modifier
            .height(80.dp)
            .offset(0.dp, 4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFCDCDE6)
        )
    )
    {
        Row(modifier.padding(top = 8.dp)) {

            Text(
                text = "Products",
                style = MaterialTheme.typography.labelSmall,
                modifier=(Modifier.offset(6.dp)),
                color = Color(0xFF000000)
            )
            Spacer(modifier = Modifier
                .width(35.dp)
                .height(30.dp))
            IconButton(onClick = {
                /*amountVisibility = !amountVisibility*/
            }) {
                /*AnimatedVisibility(amountVisibility) {}*/
                Icon(
                    painter = painterResource(id = R.drawable.eye_icon),
                    /*painter = icon,*/
                    contentDescription = "hide icon",
                    modifier
                        .size(11.dp)
                        .offset((-1).dp, (-3.5).dp)
                )


            }
        }


        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, (-22).dp),
            horizontalArrangement = Arrangement.Center
        )
        {
            Icon(
                painter = painterResource(R.drawable.naira_icon),
                contentDescription = "naira icon",
                modifier = Modifier.padding(top = 5.2.dp)

            )
            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = "2000", /*if (amountVisibility) "2000" else ".....",*/ //TODO
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF000000),
            )
        }

    }
}

@Composable
fun AlertsListCard() {
    Card(
        modifier = Modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
    ) {

    }
}

@Composable
fun AlertsItem() { //TODO
    Row {
        Icon(painter = painterResource(id = R.drawable.dot_icon), contentDescription = "dot")
        Text(text = "alert ")
    }
}

@Composable
fun QuickLinksCard(navigateToRestock: () -> Unit, navigateToAnalytics: () -> Unit) {
    Row {
        RestockLinkCard(Modifier.weight(1.5f).clickable { navigateToRestock()})
        Spacer(modifier = Modifier.width(10.dp))
        AnalyticsLinkCard(Modifier.weight(2f).clickable { navigateToAnalytics()})
    }
}

@Composable
fun RestockLinkCard(modifier: Modifier) {
    ElevatedCard(
        modifier
            .offset(0.dp, 4.dp)
            .height(108.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(modifier.fillMaxSize()) {

            Row(modifier.padding(top = 10.dp)) {
                Icon(
                    painter = painterResource(R.drawable.restock_icon),
                    contentDescription = "restock",
                    modifier
                        .size(11.dp)
                        .offset(0.dp, 2.dp)
                )
                Spacer(modifier = Modifier.width(70.dp))
                Text(
                    text = "Restock",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF2B2B85),
                    modifier=(Modifier.offset((-70).dp))
                )
            }

            Text(
                text = "update your supply as you buy",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF000000),
                modifier = modifier
                    .padding(top = 16.dp, start = 5.dp)
                    .size(500.dp),
                fontSize = 9.sp
            )

            Text(
                text = "Inventory",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF2B2B85),
                modifier = modifier.padding(top = 15.dp, start = 8.dp)
            )
        }
    }
}

@Composable
fun AnalyticsLinkCard(modifier: Modifier) {
    ElevatedCard(
        modifier
            .height(108.dp)
            .clickable {}, //TODO
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.elevatedCardColors(Color(0xFFF5F5F5))
    ) {
        Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Row(
                modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(R.drawable.metrics_icon),
                    contentDescription = "metrics",
                    modifier.size(11.dp).offset(0.dp, 2.dp)
                )
                Spacer(modifier = Modifier.width(120.dp))
                Text(
                    text = "Metrics",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF2B2B85),
                    modifier=(Modifier.offset((-120).dp))
                )
            }

            Icon(
                painter = painterResource(R.drawable.metrics_demo_icon),
                contentDescription = "metrics",
                modifier = modifier
                    /*.padding(top = 11.dp, start = 60.dp)*/
                    .size(500.dp).height(120.dp)
            )

            Text(
                text = "Analytics",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF2B2B85),
                modifier = modifier.padding(top = 15.dp, start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CurrentDatePreview() {
    //DashboardBody()
}