package com.example.tradeline.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradeline.R
import com.example.tradeline.TopBar
import com.example.tradeline.ui.AppViewModelProvider
import com.example.tradeline.ui.screens.viewModel.DashboardViewModel
import java.text.DateFormat.getDateInstance
import java.util.*

@Composable
fun DashboardScreen(
    userId: Int,
    storeName: String, // store name
    navigateToProfile: () -> Unit,
    navigateToRestock: () -> Unit,
    navigateToAnalytics: () -> Unit,
    viewModel: DashboardViewModel = viewModel(factory = AppViewModelProvider.createFactory(userId = userId))

) {
    val totalSalesUiState by viewModel.totalSalesUiState.collectAsState()
    val totalProductsUiState by viewModel.totalProductsUiState.collectAsState()
    val totalProfitUiState by viewModel.totalProfitUiState.collectAsState()
    val sales = totalSalesUiState.sales
    val products = totalProductsUiState.products
    val profit = totalProfitUiState.profit

    Scaffold(
        topBar = {
            TopBar(
                canNavigateBack = false
            )
        }
    ) {
        DashboardBody(navigateToProfile = navigateToProfile, navigateToRestock = navigateToRestock, navigateToAnalytics = navigateToAnalytics,
                storeName = storeName, sales = sales, products = products, profit = profit)
    }
}

@Composable
fun DashboardBody(navigateToProfile: () -> Unit, navigateToRestock: () -> Unit, navigateToAnalytics: () -> Unit, storeName: String,
                  sales: Double?, products: Int?, profit: Double?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),

    ) {
        StoreProfile(navigateToProfile = navigateToProfile, storeName = storeName)
        CurrentDate()
        AccountCards(sales, products, profit)
        Text(text = "QUICK LINKS", modifier = Modifier.padding(top = 30.dp))

        QuickLinksCard(navigateToRestock = navigateToRestock, navigateToAnalytics = navigateToAnalytics)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "ALERTS", modifier = Modifier.padding(top = 30.dp))
        AlertsListCard()

    }
}

@Composable
fun StoreProfile(navigateToProfile: () -> Unit, storeName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    )
    {
        Text(
            text = storeName,
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF000000),
            modifier = Modifier.clickable { navigateToProfile() },
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
fun AccountCards(sales: Double?, products: Int?, profit: Double?) {
    Row {
        var salesVisible by remember { mutableStateOf(true) }
        var profitVisible by remember { mutableStateOf(true) }
        var productsVisible by remember { mutableStateOf(true) }

        SalesCard(sales, Modifier.weight(1f), salesVisible) { salesVisible = !salesVisible }

        Spacer(modifier = Modifier.width(4.dp))

        ProfitCard(profit, Modifier.weight(1f), profitVisible) { profitVisible = !profitVisible }

        Spacer(modifier = Modifier.width(4.dp))

        ProductsCard(products, Modifier.weight(1f), productsVisible) { productsVisible = !productsVisible }
    }
}

@Composable
fun SalesCard(sales: Double?, modifier: Modifier, salesVisible: Boolean, onToggleSales: () -> Unit) {
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
            IconButton(onClick = onToggleSales ) {

                    Icon(
                        painter = painterResource(id = if (salesVisible) R.drawable.eye_icon else R.drawable.invisible_eye),
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
                text = if (salesVisible) sales?.toString() ?: "0.0" else "***",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF000000),
            )
        }

    }
}

@Composable
fun ProfitCard(profit: Double?, modifier: Modifier, profitVisible: Boolean, onToggleProfit: () -> Unit) {
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
            IconButton(onClick = onToggleProfit ) {
                Icon(
                    painter = painterResource(id = if (profitVisible) R.drawable.eye_icon else R.drawable.invisible_eye),
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
                text = if (profitVisible) profit?.toString() ?: "0.0" else "***" ,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF000000),
            )
        }

    }
}


@Composable
fun ProductsCard(products: Int?, modifier: Modifier, productsVisible: Boolean, onToggleProduct: () -> Unit) {
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
            IconButton(onClick = onToggleProduct ) {

                Icon(
                    painter = painterResource(id = if (productsVisible) R.drawable.eye_icon else R.drawable.invisible_eye),
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
            Text(
                text = if (productsVisible) products?.toString() ?: "0" else "***" ,
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
        RestockLinkCard(
            Modifier
                .weight(1.5f)
                .clickable { navigateToRestock() })
        Spacer(modifier = Modifier.width(10.dp))
        AnalyticsLinkCard(
            Modifier
                .weight(2f)
                .clickable { navigateToAnalytics() })
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
                    modifier
                        .size(11.dp)
                        .offset(0.dp, 2.dp)
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
                    .size(500.dp)
                    .height(120.dp)
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