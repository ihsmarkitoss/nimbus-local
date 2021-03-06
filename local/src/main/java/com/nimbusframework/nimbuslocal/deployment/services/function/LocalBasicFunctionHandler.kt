package com.nimbusframework.nimbuslocal.deployment.services.function

import com.nimbusframework.nimbuscore.annotations.function.BasicServerlessFunction
import com.nimbusframework.nimbuslocal.deployment.basic.BasicFunction
import com.nimbusframework.nimbuslocal.deployment.function.FunctionIdentifier
import com.nimbusframework.nimbuslocal.deployment.function.ServerlessFunction
import com.nimbusframework.nimbuslocal.deployment.function.information.BasicFunctionInformation
import com.nimbusframework.nimbuslocal.deployment.services.LocalResourceHolder
import java.lang.reflect.Method

class LocalBasicFunctionHandler(
        private val localResourceHolder: LocalResourceHolder,
        private val stage: String
) : LocalFunctionHandler(localResourceHolder) {
    override fun handleMethod(clazz: Class<out Any>, method: Method): Boolean {

        val basicServerlessFunctions = method.getAnnotationsByType(BasicServerlessFunction::class.java)
        if (basicServerlessFunctions.isEmpty()) return false

        val functionIdentifier = FunctionIdentifier(clazz.canonicalName, method.name)

        for (basicFunction in basicServerlessFunctions) {
            if (basicFunction.stages.contains(stage)) {
                val invokeOn = clazz.getConstructor().newInstance()

                val basicMethod = BasicFunction(method, invokeOn)
                val basicFunctionInformation = BasicFunctionInformation(basicFunction.cron)
                localResourceHolder.functions[functionIdentifier] = ServerlessFunction(basicMethod, basicFunctionInformation)
                localResourceHolder.basicMethods[functionIdentifier] = basicMethod
            }
        }
        return true
    }

}