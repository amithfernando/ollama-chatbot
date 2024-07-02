package com.amithfernando.ollama.chatbot.views;

import com.amithfernando.ollama.chatbot.services.ChatService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Route
public class ChatView extends AppLayout {

    private List<MessageListItem> chatMessageList;

    private final ChatService chatService;

    private MessageInput messageInput;
    private MessageList messageList;

    public ChatView(ChatService chatService) {
        this.chatService = chatService;
        chatMessageList = new ArrayList<>();
        messageInput = new MessageInput(e -> sendMessage(e));
        messageList = new MessageList();

        H1 title = new H1("My Chat GPT");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "var(--lumo-space-m)");
        addToNavbar(title);

        VerticalLayout layout = new VerticalLayout();
        layout.setHeight("100%");
        layout.setWidth("100%");
        messageList.setHeight("92%");
        messageList.setWidth("100%");
        messageInput.setHeight("8%");
        messageInput.setWidth("100%");
        layout.add(messageList);
        layout.add(messageInput);
        setContent(layout);
        getElement().getStyle().set("height", "100%");
    }

    public void sendMessage(MessageInput.SubmitEvent e) {
        String message = e.getValue();
        UI ui = UI.getCurrent();
        ui.access(() -> updateUserMessageString(message));
        ui.push();

        ui.access(() -> updateChatResponse(message));
        ui.push();

    }

    private void updateUserMessageString(String message) {
        chatMessageList.add(new MessageListItem(message, Instant.now(), "You"));
        messageList.setItems(chatMessageList);
    }

    private void updateChatResponse(String message) {
        String chatResponse = chatService.getChatResponse(message);
        chatMessageList.add(new MessageListItem(chatResponse, Instant.now(), "Bot"));
        messageList.setItems(chatMessageList);
    }


}
