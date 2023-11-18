package baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.text.DecimalFormat
import java.util.*

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val string = st.nextToken()
    val numbers = Array<Int>(size) { 0 }

    repeat(size) { idx ->
        st = StringTokenizer(br.readLine())
        numbers[idx] = st.nextToken().toInt()
    }

    fun getNumByCode(code: Char): Double = numbers[code - 'A'].toDouble()
    fun Char.isNum(): Boolean = this != '+' && this != '*' && this != '/' && this != '-'
    fun calculate(first: Double, second: Double, operator: Char) = when(operator) {
        '+' -> first + second
        '-' -> first - second
        '*' -> first * second
        else -> first / second
    }

    val input = Stack<Double>()

    string.forEach { ch ->
        if (ch.isNum()) { input.add(getNumByCode(ch)) }
        else {
            if (input.size >= 2) {
                val second = input.pop()
                val first = input.pop()
                input.add(calculate(first, second, ch))
            } else if (input.size == 1){
                val first = input.pop()
                val second = input.pop()
                input.add(calculate(first, second, ch))
            } else {
                val second = input.pop()
                val first = input.pop()
                input.add(calculate(first, second, ch))
            }
        }
    }

    // output
    val decimalFormat = DecimalFormat("#.00")
    println(decimalFormat.format(input.pop()))
}

