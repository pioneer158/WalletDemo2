package com.google.developers.wallet.rest

import com.google.developers.wallet.rest.Common.LOGI
import java.io.File

//Gradle daemon 无法启动修复:https://blog.csdn.net/weixin_44189056/article/details/135961183

private const val userId = "3388000000022787184"
private const val classSuffix = "classSuffiTrain990xx"
private val objectSuffix = "objectSueTrain123xxxx"
const val TAG = "WalletAPI"


@Throws(Exception::class)
fun main(args: Array<String>) {
    //Train
    genericTrain()
    //Flight
    //enericFlight()
}

private fun genericTrain() {
    val demo = DemoGeneric()

    //创建卡片,会先检测卡片是否已经创建(通过检查userId、classId、objectId是否改变),如果已经创建,不再重复创建
    demo.createClass(userId, classSuffix)

    demo.createObject(userId, classSuffix, objectSuffix)

    //Auto-Linked Pass
    //demo.updateObjectAutoLinkPass(userId, classSuffix, objectSuffix)

    //向(userId, classId)卡片推送消息,前提需要(userId、classId)卡片已经创建
    //demo.sendMessage(userId, classSuffix)

    //更新卡片
    demo.updateObject(userId, objectSuffix, null,true)

    //生成JWT,返回客户端
    val tokenPair = demo.createJWTNewObjects(userId, classSuffix, objectSuffix)
    saveTokenToFile(tokenPair.second)
}

private fun genericFlight() {
    val demo = DemoGeneric()

    demo.createClass(userId, classSuffix)
    demo.createObjectWithFlight(userId, classSuffix, objectSuffix)
    demo.updateObject(userId,objectSuffix,null,true)
    val tokenPair = demo.createJWTNewObjects(userId, classSuffix, objectSuffix)
    saveTokenToFile(tokenPair.second)
}



private fun saveTokenToFile(content: String) {
    runCatching {
        val filePath =
            "/Users/chenzirui/workspace/wallet/wallet/app/src/main/assets/token.txt"
        File(filePath).writeText(content)
        LOGI(TAG, "写入完毕");
    }
}