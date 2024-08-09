package com.techrealms.cashbook.screens.business.business_screen

enum class BusinessActionOption(val title: String)
{
    OpenBusiness("Open Business");

    companion object{
        fun getByTitle(title:String): BusinessActionOption
        {
            values().forEach { action -> if( title == action.title) return action }
            return OpenBusiness
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
