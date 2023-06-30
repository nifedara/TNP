package com.example.tradeline.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tradeline.R


/**
 * The store creation screen. Has the form fields to collect sign up data
 */

/* The top level call for the store creation success screen.
It calls the "AddProductButton" Composable */
@Composable
fun StoreCreationSuccessScreen(){
    Column(
        modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.create_store_success_image),
            contentDescription = "store creation successful",
            modifier = Modifier
                .height(175.dp)
                .width(263.dp))

        Spacer(modifier = Modifier.height(40.dp))

        Row {
            Text("Hason ") //TODO
            Text(stringResource(R.string.store_creation_success))
        }

        Spacer(modifier = Modifier.height(20.dp))

        //call to the composables to add product
        AddProductButton()
    }
}

/* The Composable to add product */
@Composable
fun AddProductButton(){
    OutlinedButton(
        onClick = {}, /*TODO*/
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, colorResource(R.color.blue1)),
        modifier = Modifier
    )
    {
        Icon(
            painter = painterResource(R.drawable.add_button_icon), contentDescription = "add button",
            modifier = Modifier.size(10.dp),
            colorResource(R.color.blue1)
        )
        Spacer(modifier = Modifier.width(4.dp))

        Text(stringResource(R.string.add_product))
    }
}

@Preview(showBackground = true)
@Composable
fun StoreCreationSuccessPreview() {
    StoreCreationSuccessScreen()
}