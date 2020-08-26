package com.droidplusplus.moviedbapp.utils

/**
 * Search data from below parameter
 * @param inputStr
 * @param sourceStr
 * Check below 1 and 2 cases
 * Case 1. First letter of each word from string
 * Case 2. Start with or contains word from string
 */
fun searchInputData(inputStr: String, sourceStr: String): Boolean {
    var isMatch = false
    inputStr.split(" ").takeIf { it.isNotEmpty() }?.forEach inputLoop@{ inputWord ->
        sourceStr.split(" ").forEach sourceLoop@{ word ->

            if (word.equals(inputWord, ignoreCase = true) || word.startsWith(
                    inputStr,
                    ignoreCase = true
                )
            ) {
                isMatch = true
                return@inputLoop
            }
        } //end source loop

    } //end input loop

        ?: run {
            sourceStr.split(" ").forEach sourceLoop@{ word ->

                if (word.equals(inputStr, ignoreCase = true) || word.startsWith(
                        inputStr,
                        ignoreCase = true
                    )
                ) {
                    isMatch = true
                    return@sourceLoop
                }

                inputStr.forEachIndexed inputLoop@{ index, char ->
                    if (word.startsWith(char, ignoreCase = true)) {
                        isMatch = true
                        return@sourceLoop
                    }
                }
            }//end source loop
        }
    return isMatch
}