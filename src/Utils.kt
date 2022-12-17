import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun String.chunkAtIndex(index: Int): List<String> {
    return when {
        index < 0 -> 0
        index > length -> length
        else -> index
    }.let {
        take(it) to substring(it)
    }.toList()
}

fun read(name: String) = File("src", "$name.txt").readText()