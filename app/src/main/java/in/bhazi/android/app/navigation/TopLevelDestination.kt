package `in`.bhazi.android.app.navigation

import `in`.bhazi.android.R
import `in`.bhazi.core.design.icon.BhaziIcons
import `in`.bhazi.core.design.icon.Icon
import `in`.bhazi.core.design.icon.Icon.ImageVectorIcon

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int
) {
    HOME(
        selectedIcon = ImageVectorIcon(BhaziIcons.Home),
        unselectedIcon = ImageVectorIcon(BhaziIcons.Home),
        iconTextId = R.string.home,
        titleTextId = R.string.home
    ),
    CART(
        selectedIcon = ImageVectorIcon(BhaziIcons.Cart),
        unselectedIcon = ImageVectorIcon(BhaziIcons.Cart),
        iconTextId = R.string.cart,
        titleTextId = R.string.cart
    ),
    REFERRAL(
        selectedIcon = ImageVectorIcon(BhaziIcons.Add),
        unselectedIcon = ImageVectorIcon(BhaziIcons.Add),
        iconTextId = R.string.referral,
        titleTextId = R.string.referral
    ),
    ACCOUNT(
        selectedIcon = ImageVectorIcon(BhaziIcons.AccountCircle),
        unselectedIcon = ImageVectorIcon(BhaziIcons.AccountCircle),
        iconTextId = R.string.account,
        titleTextId = R.string.account
    )
}