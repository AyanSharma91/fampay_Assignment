package com.world.fampayassignment.util


import com.world.fampayassignment.model.FormatEntity

/*
////////////////////////////////////////////////////////////////////////////////////////
This class returns the formatted string
////////////////////////////////////////////////////////////////////////////////////////
 */
class SpannableString {

     private fun getColoredSpanned(text: String?, color: String): String? {
        return "<font color=$color>$text</font>"
    }

    fun formatString(originalText: String, formattedText: String, entity: List<FormatEntity?>) : String {
        var result = ""
        var str = ""

        if (entity.isNullOrEmpty()) return originalText

        var i = 0
        var count = 0
        while (i < formattedText.length) {
            if (formattedText[i] != '{') {
                result += formattedText[i]
                i+=1
            } else {
                i += 2

                result += getColoredSpanned(
                    (entity[count]!!.text),
                    entity[count]!!.color)

                count++;
            }
        }
        return result
    }

}