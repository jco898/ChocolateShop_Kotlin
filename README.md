# Chocolate Shop (Kotlin)
![image](https://i.ibb.co/7KBm7q3/chocolate.png)

## About
This is a shopping application for chocolates, written in Kotlin for Android.

- Users can add/remove different selections of chocolate: bars, baked goods, and European.
- The quantity is continually updated after every click.
- Clicking the 'Checkout' button will show the user a receipt containing the list of chocolates, their subtotals, and final total amount.

## Context
This application was made as my second assignment for Application Development II.

We were tasked with creating an app that uses state and continually updates the UI after interactions by the user.

The quantity changes are tracked by using the rememberSaveable and mutableStateOf functions.

This served as a good introduction to state in Kotlin and was useful for working on my group project.

## Running the app

1. Open the application in Android Studio.
2. Below the menu bar, make sure a device is selected and its run configuration is an app.
3. In menu bar at the top, click <em>Run</em>.
4. Each chocolate item has a name and price displayed on the left.
5. On the right of the item, add and remove buttons allow the user to select a quantity.
6. Once all the items they wish to purchase are selected, pressing the "Checkout" button will show the user a receipt.
