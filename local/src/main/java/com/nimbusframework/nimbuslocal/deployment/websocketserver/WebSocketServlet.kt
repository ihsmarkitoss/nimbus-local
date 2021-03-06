package com.nimbusframework.nimbuslocal.deployment.websocketserver

import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory
import org.eclipse.jetty.websocket.servlet.WebSocketServlet
import com.nimbusframework.nimbuslocal.deployment.websocket.LocalWebsocketMethod

class WebSocketServlet(
        private var connectMethod: LocalWebsocketMethod?,
        private var disconnectMethod: LocalWebsocketMethod?,
        private var defaultMethod: LocalWebsocketMethod?,
        private val topics: Map<String, LocalWebsocketMethod>,
        private val sessions: MutableMap<String, Session>
) : WebSocketServlet() {

    override fun configure(factory: WebSocketServletFactory) {
        factory.creator = LocalWebSocketCreator(connectMethod, disconnectMethod, defaultMethod, topics, sessions)
    }
}