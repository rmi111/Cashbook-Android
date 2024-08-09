package com.techrealms.cashbook.screens.cashbook.cashbook_screen

enum class CashbookActionOption(val title: String)
{
    OpenCashbook("Open Cashbook");

    companion object{
        fun getByTitle(title:String): CashbookActionOption
        {
            values().forEach { action -> if( title == action.title) return action }
            return OpenCashbook
        }

        fun getOptions(): List<String>
        {
            val options = mutableListOf<String>()
            values().forEach { action ->
                options.add(action.title)
            }

            return options
        }
    }
}