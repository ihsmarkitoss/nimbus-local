package com.nimbusframework.nimbuslocal.unitTests

import com.nimbusframework.nimbuslocal.LocalNimbusDeployment
import com.nimbusframework.nimbuslocal.exampleHandlers.ExampleNotificationHandler
import com.nimbusframework.nimbuslocal.exampleModels.Person
import io.kotlintest.specs.AnnotationSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NotificationHandlerLocalTest: AnnotationSpec() {

    private val testPerson = Person("Thomas", 21)

    @Test
    fun testSendingNotificationTriggersFunction() {
        val localDeployment = LocalNimbusDeployment.getNewInstance(ExampleNotificationHandler::class.java)

        val notificationFunction = localDeployment.getMethod(ExampleNotificationHandler::class.java, "receiveNotification")

        assertEquals(0, notificationFunction.timesInvoked)

        val testTopic = localDeployment.getNotificationTopic("test-topic")
        testTopic.notify(testPerson)

        assertEquals(1, notificationFunction.timesInvoked)
        assertEquals(testPerson, notificationFunction.mostRecentInvokeArgument)
        assertEquals(testPerson, notificationFunction.mostRecentValueReturned)
    }
}