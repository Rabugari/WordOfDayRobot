package wordofday.domain

data class Word (
    val word: String,
    val meanings: List<String>,
    val examples: List<String>)