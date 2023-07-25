package com.example.tradeline.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradeline.R
import com.example.tradeline.ui.navigation.NavigationDestination

object Restock : NavigationDestination {
    override val route = "restock"
}

@Composable
fun InventoryRestockProductScreen(
    navigateBack: () -> Unit,
    canNavigateBack: Boolean = true,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
    ) {
        Row() {
            Text(
                text = "back",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF000000),
                modifier = Modifier.offset(20.dp),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, 0.dp), horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Restock",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF2B2B85),
                modifier = Modifier.offset(20.dp, 15.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Product Name",
                color = Color(0xFF2B2B85),
                modifier = Modifier.offset(20.dp, 30.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                /*label = { Text(stringResource(R.string.phone_number)) },*/
                modifier = Modifier
                    .width(310.dp)
                    .height(50.dp)
                    .offset(10.dp, 40.dp),
                shape = MaterialTheme.shapes.large,
                enabled = true, //TODO
                singleLine = true
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Product Quantity",
                color = Color(0xFF2B2B85),
                modifier = Modifier.offset(20.dp, 60.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                /*label = { Text(stringResource(R.string.phone_number)) },*/
                modifier = Modifier
                    .width(310.dp)
                    .height(50.dp)
                    .offset(10.dp, 70.dp),
                shape = MaterialTheme.shapes.large,
                enabled = true, //TODO
                singleLine = true
            )
        }



        Row(modifier=Modifier.offset(0.dp, 90.dp) ){
            ReCostPrice (
                Modifier
                    .weight(1f)
                    .offset(0.dp, 0.dp)
                    .width(100.dp) )
            Spacer(modifier = Modifier.width(5.dp))
            ReSellingPrice(
                Modifier
                    .weight(1f)
                    .offset(0.dp, 0.dp)
                    .width(100.dp))
        }

        Row(modifier = Modifier.fillMaxWidth().offset(0.dp, 130.dp), horizontalArrangement = Arrangement.Center){

            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(320.dp)
                    .height(50.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = Color(0xFFFFFFFF)
                ),
                enabled = true //TODO
            ) {
                Text( text = "Restock")
            }

        }
    }

}




@Composable
fun ReCostPrice(modifier: Modifier) {
    Column(modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()) {
            Text(
                text = "Cost Price",
                color = Color(0xFF2B2B85),
                modifier = Modifier.offset(20.dp)

            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                /*label = { Text(stringResource(R.string.phone_number)) },*/
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .offset(10.dp, 10.dp),
                shape = MaterialTheme.shapes.large,
                enabled = true, //TODO
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.naira_icon),
                        contentDescription = "naira icon",
                        modifier = Modifier.padding(top = 5.2.dp)
                    )
                }
            )
        }
    }
}

@Composable
fun ReSellingPrice(modifier: Modifier) {
    Column(modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()) {
            Text(
                text = "Selling Price",
                color = Color(0xFF2B2B85),
                modifier = Modifier.offset(10.dp)

            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                /*label = { Text(stringResource(R.string.phone_number)) },*/
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp)
                    .offset(0.dp, 10.dp),
                shape = MaterialTheme.shapes.large,
                enabled = true, //TODO
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.naira_icon),
                        contentDescription = "naira icon",
                        modifier = Modifier.padding(top = 5.2.dp)
                    )
                },

                )
        }
    }
}

@Composable
fun RestockProductBody(){}

@Composable
fun RestockProductForm(){}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultRestockPreview(){
    InventoryRestockProductScreen(navigateBack = { /*TODO*/ })
}