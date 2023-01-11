package `in`.bhazi.admin.navigation

import `in`.bhazi.admin.R
import `in`.bhazi.core.design.icon.BhaziIcons
import `in`.bhazi.core.design.icon.Icon

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int
) {
    HOME(
        selectedIcon = Icon.ImageVectorIcon(BhaziIcons.Home),
        unselectedIcon = Icon.ImageVectorIcon(BhaziIcons.Home),
        iconTextId = R.string.home,
        titleTextId = R.string.home
    ),
    ACCOUNT(
        selectedIcon = Icon.ImageVectorIcon(BhaziIcons.AccountCircle),
        unselectedIcon = Icon.ImageVectorIcon(BhaziIcons.AccountCircle),
        iconTextId = R.string.account,
        titleTextId = R.string.account
    )
}
