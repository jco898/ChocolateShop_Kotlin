@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.chocolateshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.chocolateshop.ui.theme.ChocolateShopTheme

/**
 * The Main Activity for launching the Chocolate Shop application.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChocolateShopTheme(
            // Recommended
            //useDarkTheme = true
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChocolateShopApp()
                }
            }
        }
    }
}

/**
 * The main application for the Chocolate Shop.
 * Displays the top/bottom bars, as well as the chocolate sections and items.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChocolateShopApp() {
    Scaffold(
        topBar = {
            ChocolateShopTopBar()
        },
        bottomBar = {
            ChocolateShopBottomBar()
        }
    ) { padding ->
        /**
         * https://stackoverflow.com/questions/69822392/use-lazycolum-inside-the-column-has-an-error-in-the-jetpack-compose
         * Date of retrieval: 2023/10/03
         * Demonstrates how to show both views and data in a LazyColumn.
         * This was needed to show all UI elements. Different layout attempts gave errors.
         */
        LazyColumn(contentPadding = padding) {
            item {
                SectionHeader(
                    /**
                     * Chocolate icons created by Iconic Panda - Flaticon
                     * https://www.flaticon.com/free-icons/chocolate
                     */
                    imageId = R.drawable.chocolate_bar,
                    text = "Chocolate Bars"
                )
            }
            items(chocolateBars) {
                ChocolateItem(
                    chocolate = it,
                    modifier = Modifier.padding(2.dp)
                )
            }
            item {
                SectionHeader(
                    /**
                     * Food icons created by Freepik - Flaticon
                     * https://www.flaticon.com/free-icons/food
                     */
                    imageId = R.drawable.donut,
                    text = "Baked Goods"
                )
            }
            items(bakedGoods) {
                ChocolateItem(
                    chocolate = it,
                    modifier = Modifier.padding(2.dp)
                )
            }
            item {
                SectionHeader(
                    /**
                     * Europe icons created by Freepik - Flaticon
                     * https://www.flaticon.com/free-icons/europe
                     */
                    imageId = R.drawable.european_union,
                    text = "European Brands"
                )
            }
            items(europeanBrands) {
                ChocolateItem(
                    chocolate = it,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}

/**
 * Displays the name of each individual chocolate section (bars, baked goods, or European).
 */
@Composable
fun SectionHeader(
    imageId: Int,
    text: String,
    modifier: Modifier = Modifier
)
{
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = modifier
                .size(64.dp)
                .padding(8.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium
        )
    }
}

/**
 * Displays each chocolate item that can be purchased.
 * Calls a function to display its info and features +/- buttons to add to cart.
 */
@Composable
fun ChocolateItem(
    chocolate: Chocolate,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            var quantity by rememberSaveable { mutableStateOf(chocolate.quantity) }

            ChocolateInfo(chocolate.name, chocolate.price)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = {
                    if (quantity > 0) {
                        quantity--
                        chocolate.quantity--
                    }
                }) {
                    Icon(Icons.Filled.Remove, contentDescription = "Add")
                }
                Text(text = quantity.toString())
                IconButton(onClick = {
                    quantity++
                    chocolate.quantity++
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        }
    }
}

/**
 * Displays the name and price of the chocolate item.
 */
@Composable
fun ChocolateInfo(
    name: String,
    price: Double,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "$name... $price")
    }
}

/**
 * https://developer.android.com/codelabs/basic-android-kotlin-compose-material-theming#6
 * Date of retrieval: 2023/09/29
 * This top bar was based on the WoofApp top bar from the material code-lab.
 * This was needed in order to show the Chocolate Shop logo.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChocolateShopTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                /**
                 * Chocolate icons created by Freepik - Flaticon
                 * https://www.flaticon.com/free-icons/chocolate
                 */
                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.chocolate),
                    contentDescription = null
                )
                Text(
                    text = "Chocolate Shop",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    )
}

/**
 * A bottom bar for displaying a "Checkout" button.
 */
@Composable
fun ChocolateShopBottomBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        var shouldShowBill by remember { mutableStateOf(false) }

        Button(onClick = { shouldShowBill = true }) {
            Text(text = "Checkout")
        }

        if (shouldShowBill) {
            BillDialog(onDismissRequest = { shouldShowBill = false })
        }
    }
}

/**
 * Returns a bill with all the chocolates the user selected, as well as total amounts.
 */
fun getBill() : String {
    var bill = ""
    var total = 0.0

    for (chocolate in chocolateBars) {
        if (chocolate.quantity > 0) {
            total += chocolate.quantity * chocolate.price
            /**
             * https://www.tutorialspoint.com/kotlin-program-to-round-a-number-to-n-decimal-places
             * Rounding a number to 2 decimal places.
             */
            bill += "${chocolate.quantity}x ${chocolate.name} ... $${"%.2f".format(chocolate.quantity * chocolate.price)}\n"
        }
    }

    for (chocolate in bakedGoods) {
        if (chocolate.quantity > 0) {
            total += chocolate.quantity * chocolate.price
            bill += "${chocolate.quantity}x ${chocolate.name} ... $${"%.2f".format(chocolate.quantity * chocolate.price)}\n"
        }
    }

    for (chocolate in europeanBrands) {
        if (chocolate.quantity > 0) {
            total += chocolate.quantity * chocolate.price
            bill += "${chocolate.quantity}x ${chocolate.name} ... $${"%.2f".format(chocolate.quantity * chocolate.price)}\n"
        }
    }

    if (total > 0.0) {
        bill += "Total: $${"%.2f".format(total)}"
    }

    return bill
}

/**
 * https://developer.android.com/jetpack/compose/components/dialog
 * Date of retrieval: 2023/10/03
 * Demonstrates how to show a custom dialog box.
 * This was needed to display the bill after clicking "Checkout".
 */
@Composable
fun BillDialog(
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(475.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = getBill()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

/**
 * Displays a preview of the application.
 */
@Preview
@Composable
fun ChocolateShopAppPreview()
{
    ChocolateShopTheme {
        ChocolateShopApp()
    }
}

/**
 * Displays a preview of the application in dark mode.
 */
@Preview
@Composable
fun ChocolateShopDarkModePreview() {
    ChocolateShopTheme(useDarkTheme = true) {
        ChocolateShopApp()
    }
}