package com.amithfernando.ollama.chatbot;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.shared.ui.Transport;

@Push(transport = Transport.LONG_POLLING)
public class ChatApplication implements AppShellConfigurator {
}
