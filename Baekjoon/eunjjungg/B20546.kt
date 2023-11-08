package baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

// 성민
// 1. 전량 매수 / 전량 매도 (한정된 금액 내에서 살 수 있는 주식 전량 매수)
// 2. 상승 상승 상승 -> 담날은 무조건 하락한다고 가정하고 전량 매도 (같은건 상승이라 치지 않음)
// 3. 하락 하락 하락 -> 담날은 무조건 상승한다고 가정하고 전량 매수 (같은건 하락이라 치지 않음)
// 준현 성민 대결, 2021.01.01 ~ 2021.01.14 대결
// 같은 금액 주어지고 매일 주식 거래 가능
// 01.14 더 많은 자산을 보유한 사람이 승리함.
// 01.14 자산 계산법: 현금 + 01.14 주가 * 주식 수
// 준현 승 BNP
// 성민 승 TIMING
// 같으면 SAMESAME

fun main(args: Array<String>) {
    // input
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    var jhMoney = st.nextToken().toInt()
    var jhAmount = 0
    var smAmount = 0
    var smMoney = jhMoney
    val data = Array(14) { 0 }

    st = StringTokenizer(br.readLine())
    repeat(14) { data[it] = st.nextToken().toInt() }

    fun jh() {
        data.forEach { price ->
            if (jhMoney >= price) {
                jhAmount += jhMoney / price
                jhMoney %= price

            }
        }
    }

    fun checkThreeDay(idx: Int) : STATUS =
        if (data[idx - 3] < data[idx - 2] &&
            data[idx - 2] < data[idx - 1] &&
            data[idx - 1] < data[idx] ) {
            STATUS.UP
        } else if (data[idx - 3] > data[idx - 2] &&
            data[idx - 2] > data[idx - 1] &&
            data[idx - 1] > data[idx]) {
            STATUS.DOWN
        } else { STATUS.NOTHING }


    fun sm() {
        data.forEachIndexed { idx, price ->
            if (idx <= 2) { return@forEachIndexed }
            when (checkThreeDay(idx)) {
                STATUS.UP -> {
                    smMoney += smAmount * price
                    smAmount = 0
                }
                STATUS.DOWN -> {
                    if (smMoney >= price) {
                        smAmount += smMoney / price
                        smMoney %= price
                    }
                }
                STATUS.NOTHING -> {}
            }
        }
    }

    fun calculate() {
        val price = data.last()
        smMoney += price * smAmount
        jhMoney += price * jhAmount
    }

    jh()
    sm()
    calculate()

    // output
    if (jhMoney > smMoney) { println("BNP") } else if (jhMoney < smMoney) { println("TIMING") } else {
        println("SAMESAME")
    }
}

private sealed class STATUS {
    object UP : STATUS()
    object DOWN : STATUS()
    object NOTHING : STATUS()
}

