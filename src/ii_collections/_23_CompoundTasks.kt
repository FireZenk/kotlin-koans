package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> = customers
        .filter { it -> it.orderedProducts.contains(product) }.toSet()

fun Customer.getMostExpensiveDeliveredProduct(): Product? = orders
        .filter(Order::isDelivered).flatMap { it.products }.maxBy { it.price }


fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int = customers
        .flatMap(Customer::orders)
        .flatMap(Order::products)
        .filter { it -> it == product }
        .count()
