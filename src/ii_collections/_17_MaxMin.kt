package ii_collections

fun example4() {
    val max = listOf(1, 42, 4).max()
    val longestString = listOf("a", "b").maxBy { it.length }
}

fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = customers.maxBy { (_, _, orders) -> orders.count() }

fun Customer.getMostExpensiveOrderedProduct(): Product? = orderedProducts.maxBy(Product::price)
