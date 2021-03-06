package com.nimbusframework.nimbuslocal.clients

import com.nimbusframework.nimbuscore.clients.database.DatabaseClient
import com.nimbusframework.nimbuscore.clients.database.InternalClientBuilder
import com.nimbusframework.nimbuscore.clients.document.DocumentStoreClient
import com.nimbusframework.nimbuscore.clients.file.FileStorageClient
import com.nimbusframework.nimbuscore.clients.function.BasicServerlessFunctionClient
import com.nimbusframework.nimbuscore.clients.function.EnvironmentVariableClient
import com.nimbusframework.nimbuscore.clients.keyvalue.KeyValueStoreClient
import com.nimbusframework.nimbuscore.clients.notification.NotificationClient
import com.nimbusframework.nimbuscore.clients.queue.QueueClient
import com.nimbusframework.nimbuscore.clients.store.TransactionalClient
import com.nimbusframework.nimbuscore.clients.websocket.ServerlessFunctionWebSocketClient

object LocalInternalClientBuilder: InternalClientBuilder {

    override fun getBasicServerlessFunctionClient(handlerClass: Class<*>, functionName: String): BasicServerlessFunctionClient {
        return BasicServerlessFunctionClientLocal(handlerClass, functionName)
    }

    override fun <T> getDatabaseClient(databaseObject: Class<T>): DatabaseClient {
        return DatabaseClientLocal(databaseObject)
    }

    override fun <T> getDocumentStoreClient(document: Class<T>, stage: String): DocumentStoreClient<T> {
        return DocumentStoreClientLocal(document)
    }

    override fun getTransactionalClient(): TransactionalClient {
        return TransactionalClientLocal()
    }

    override fun getEnvironmentVariableClient(): EnvironmentVariableClient {
        return EnvironmentVariableClientLocal()
    }

    override fun getFileStorageClient(bucketName: String, stage: String): FileStorageClient {
        return FileStorageClientLocal(bucketName)
    }

    override fun <K, V> getKeyValueStoreClient(key: Class<K>, value: Class<V>, stage: String): KeyValueStoreClient<K, V> {
        return KeyValueStoreClientLocal(value)
    }

    override fun getNotificationClient(topic: String): NotificationClient {
        return NotificationClientLocal(topic)
    }

    override fun getQueueClient(id: String): QueueClient {
        return QueueClientLocal(id)
    }

    override fun getServerlessFunctionWebSocketClient(): ServerlessFunctionWebSocketClient {
        return ServerlessFunctionWebsocketClientLocal()
    }
}