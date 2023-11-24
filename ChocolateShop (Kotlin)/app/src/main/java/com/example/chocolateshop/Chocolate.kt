package com.example.chocolateshop

/**
 * Represents a chocolate item, including its name, price, and quantity.
 */
data class Chocolate(
    val name: String,
    val price: Double,
    var quantity: Int
)

var chocolateBars = listOf(
    Chocolate("Coffee Crisp", 1.79, 0),
    Chocolate("Hershey", 1.79, 0),
    Chocolate("Kit Kat", 1.79, 0),
    Chocolate("Mars", 1.79, 0),
    Chocolate("Snickers", 1.79, 0),
    Chocolate("Twix", 1.79, 0)
)

var bakedGoods = listOf(
    Chocolate("Brownie", 1.29, 0),
    Chocolate("Donut", 1.29, 0),
    Chocolate("Muffin", 1.49, 0)
)

var europeanBrands = listOf(
    Chocolate("Ferrero Rocher", 2.79, 0),
    Chocolate("Godiva", 2.79, 0),
    Chocolate("Lindt", 2.99, 0),
    Chocolate("Toblerone", 2.99, 0)
)