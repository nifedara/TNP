package com.example.tradeline.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.tradeline.R


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
            dropDownMenu(
                Modifier
                    .weight(1f)
                    .offset(0.dp, 0.dp)
                    .width(100.dp))
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

        Row(modifier = Modifier
            .fillMaxWidth()
            .offset(0.dp, 130.dp), horizontalArrangement = Arrangement.Center){

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
fun dropDownMenu(modifier: Modifier){

    var expanded by remember{ mutableStateOf(false) }
    val list = listOf("kotlin", "Java", "Dart", "Python")
    var selectedItem by remember{ mutableStateOf("") }

    var textFiledSize by remember{ mutableStateOf(Size.Zero) }
    val icon = if (expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier.fillMaxWidth()) {

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
                value = selectedItem,
                onValueChange = {selectedItem = it},
                modifier = Modifier
                    .width(310.dp)
                    .height(50.dp)
                    .offset(10.dp, 40.dp)
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                shape = MaterialTheme.shapes.large,
                enabled = true, //TODO
                singleLine = true,
                label = {Text(text = "Select Product", color = Color(0xFF2B2B85), fontSize = 12.sp)},
                trailingIcon = {
                    Icon( icon, "", Modifier.clickable { expanded = !expanded } )
                }
            )

            DropdownMenu(
                expanded = expanded ,
                onDismissRequest = {expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){ textFiledSize.width.toDp()})
                )
            {
                list.forEach { label ->
                    androidx.compose.material.DropdownMenuItem(onClick = {
                        selectedItem = label
                        expanded = false
                    }) {
                            Text(text = label)
                    }

                }
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
fun DefaultRestockPPreview(){
  InventoryRestockProductScreen(navigateBack = { /*TODO*/ })
}