package com.techrealms.cashbook.screens.transaction.transaction_screen

enum class TransactionActionOption(val title: String)
{
    OpenTransaction("Open Transaction");

    companion object{
        fun getByTitle(title:String): TransactionActionOption
        {
            values().forEach { action -> if( title == action.title) return action }
            return OpenTransaction
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